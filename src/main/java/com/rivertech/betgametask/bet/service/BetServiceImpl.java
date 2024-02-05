package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.BetResult;
import com.rivertech.betgametask.bet.repository.BetRepository;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
@Service
@AllArgsConstructor
public class BetServiceImpl implements BetService {

    private final static boolean ADD_AMOUNT = false;
    private final static boolean DEDUCT_AMOUNT = true;

    private final BetRepository betRepository;
    private final PlayerService playerService;
    private final WalletService walletService;
    private final BetHistoryService betHistoryService;

    @Transactional
    public Bet save(Bet bet) {
        return betRepository.save(bet);
    }

    @Override
    public Bet toBet(BetForm betForm, Game game) throws NotFoundException {
        var player = playerService.findByUserName(betForm.getPlayerUserName());
        return  Bet.builder()
                .player(player)
                .game(game)
                .betNum(betForm.getBetNum())
                .betAmount(betForm.getBetAmount())
                .placedAt(Instant.now())
                .build();
    }

    // ENDPOINT:  To retrieve the results of their bets (betHistory).

    public BetHistory getBetResults(String userName) throws NotFoundException {
        var player = playerService.findByUserName(userName);
        var bets = player.getBets();
        return  null;
    }

    @Override
    public void priceBetCalculator(Game game) {
        var bets = game.getBets().stream()
                .peek(bet -> {
                    var betResult = betResultSetter(game.getGameResult(), bet.getBetNum());
                    bet.setBetResult(betResult);

                    var betPrice = betResult.calculatePrice(bet.getBetAmount());
                    bet.setWonAmount(betPrice);
                })
                .toList();
        bets = betRepository.saveAllAndFlush(bets);
        betHistoryService.generateBetHistoryRecords(bets);
        // Update wallet "balance" and create walletHistory
        var players = bets.stream().map(Bet::getPlayer).toList();
        walletService.updateBalanceByBetResult(players);
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
