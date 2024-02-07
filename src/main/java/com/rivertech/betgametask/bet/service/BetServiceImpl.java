package com.rivertech.betgametask.bet.service;

import java.time.Instant;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.bet.BetForm;
import org.springframework.stereotype.Service;
import com.rivertech.betgametask.bet.BetResult;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.game.service.GameService;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.bet.repository.BetRepository;
import com.rivertech.betgametask.wallet.service.WalletService;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.WalletRequestException;

@Slf4j
@Service
@AllArgsConstructor
public class BetServiceImpl implements BetService {

    private final static boolean DEDUCT_AMOUNT = true;

    private final GameService gameService;
    private final BetRepository betRepository;
    private final PlayerService playerService;
    private final WalletService walletService;
    private final BetHistoryService betHistoryService;

    @Override
    public Game placeBet(BetForm betForm) throws NotFoundException, GameRequestException, WalletRequestException {
        var player = playerService.findByUserName(betForm.getPlayerUserName());
        var game = gameService.findById(betForm.getGameId());
        if (player.getWallet().getBalance() - betForm.getBetAmount() < 0) {
            throw new WalletRequestException("Your wallet balance is insufficient...");
        }
        if (game.getExecutedAt() != null) {
            log.warn("The game with ID: {}, was already executed...",game.getId());
            throw new GameRequestException("Game executed, not accepting more bets");
        }
        log.info("Placing bet for game id: {} and player {}...",betForm.getGameId(), betForm.getPlayerUserName());

        var bet = toBet(betForm, game);
        playerService.updateBalance(player, betForm.getBetAmount(), DEDUCT_AMOUNT);;
        return gameService.addBetToGame(bet, game);
    }

    @Transactional
    public Bet save(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public Bet toBet(BetForm betForm, Game game) throws NotFoundException {
        var player = playerService.findByUserName(betForm.getPlayerUserName());
        var bet =  Bet.builder()
                .player(player)
                .game(game)
                .betNum(betForm.getBetNum())
                .betHistory(new ArrayList<BetHistory>())
                .betAmount(betForm.getBetAmount())
                .placedAt(Instant.now())
                .build();
        bet.getBetHistory().add(betHistoryService.generateBetHistoryRecord(bet).get(0));
        return bet;
    }

    @Override
    @Transactional
    public void priceBetCalculator(Game game) {
        log.info("Calculating bet price of game {}, with ID:{}...", game.getDescription(), game.getId());
        var bets = game.getBets().stream()
                .map(bet -> {
                    var betResult = betResultSetter(game.getGameResult(), bet.getBetNum());
                    bet.setBetResult(betResult);

                    var betPrice = betResult.calculatePrice(bet.getBetAmount());
                    bet.setWonAmount(betPrice);

                    bet.getBetHistory().add(betHistoryService.generateBetHistoryRecord(bet).get(0));
                    return bet;
                })
                .toList();
        walletService.updateBalanceByBetWonAmount(bets);
    }

    private BetResult betResultSetter(int gameResult, int betNum) {

        var difference = Math.abs(betNum - gameResult);
        BetResult betResult;

        if (difference == BetResult.WIN_10X.betNumDifference) {
            betResult = BetResult.WIN_10X;
        } else if (difference == BetResult.WIN_5X.betNumDifference) {
            betResult = BetResult.WIN_5X;
        } else if (difference == BetResult.WIN_HALF.betNumDifference) {
            betResult = BetResult.WIN_HALF;
        } else {
            betResult = BetResult.LOSE;
        }
        return betResult;
    }

}
