package com.rivertech.betgametask.bet.service;

import java.time.Instant;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import org.springframework.data.domain.Page;
import com.rivertech.betgametask.bet.BetForm;
import org.springframework.stereotype.Service;
import com.rivertech.betgametask.bet.BetResult;
import com.rivertech.betgametask.player.Player;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.BetHistoryForm;
import com.rivertech.betgametask.game.service.GameService;
import com.rivertech.betgametask.player.service.PlayerService;
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
    private final PlayerService playerService;
    private final BetHistoryService betHistoryService;

    @Override
    @Transactional
    public Game placeBet(BetForm betForm) throws NotFoundException, GameRequestException, WalletRequestException {
        var player = playerService.findByUserName(betForm.getPlayerUserName());
        var game = gameService.findByIdAndNotExecuted(betForm.getGameId());
        if (player.getWallet().getBalance() - betForm.getBetAmount() < 0) {
            throw new WalletRequestException("Your wallet balance is insufficient...");
        }
        log.info("Placing bet for game id: {} and player {}...",betForm.getGameId(), betForm.getPlayerUserName());

        var bet = toBet(betForm, player, game);
        playerService.updateBalance(player, betForm.getBetAmount(), DEDUCT_AMOUNT);
        return gameService.addBetToGame(bet, game);
    }

    @Override
    public Bet toBet(BetForm betForm, Player player, Game game) {
        var bet =  Bet.builder()
                .player(player)
                .game(game)
                .betNum(betForm.getBetNum())
                .betHistory(new ArrayList<BetHistory>())
                .betAmount(betForm.getBetAmount())
                .placedAt(Instant.now())
                .build();

        bet.getBetHistory().add(betHistoryService.generateBetHistoryRecord(bet));
        return bet;
    }

    @Override
    @Transactional
    public Game priceBetCalculator(Game game) {
        log.info("Calculating bet price of game {}, with ID:{}...", game.getDescription(), game.getId());
        var bets = game.getBets().stream()
                .map(bet -> {
                    var betResult = betResultSetter(game.getGameResult(), bet.getBetNum());
                    bet.setBetResult(betResult);

                    var betPrice = betResult.calculatePrice(bet.getBetAmount());
                    bet.setWonAmount(betPrice);

                    bet.getBetHistory().add(betHistoryService.generateBetHistoryRecord(bet));
                    return bet;
                })
                .toList();
        playerService.updateBalanceByBetWonAmount(bets);
        return game;
    }

    @Override
    @Transactional
    public Page<BetHistory> retrieveBetResults(BetHistoryForm betForm, Pageable pageable) throws NotFoundException {
        var player = playerService.findByUserName(betForm.getPlayerUserName());
        return betHistoryService.retrieveBetResults(player, betForm.isJustExecutedBets(),pageable);
    }

    protected BetResult betResultSetter(int gameResult, int betNum) {

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
