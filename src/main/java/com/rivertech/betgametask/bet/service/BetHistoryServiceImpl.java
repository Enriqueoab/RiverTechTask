package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.repository.BetHistoryRepository;
import com.rivertech.betgametask.bet.repository.BetRepository;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class BetHistoryServiceImpl implements BetHistoryService {

    private final BetHistoryRepository betHistoryRepository;

    public List<BetHistory> createBetHistoryRecords(List<Bet> bets) {
        return bets.stream()
                .map(bet -> BetHistory.builder()
                        .game(bet.getGame())
                        .player(bet.getPlayer())
                        .betNum(bet.getBetNum())
                        .placedAt(bet.getPlacedAt())
                        .gameResult(bet.getGame().getGameResult())
                        .executedAt(bet.getGame().getExecutedAt())
                        .betResultMessage(bet.getBetResult().message)
                        .playerUserName(bet.getPlayer().getUserName())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public void generateBetHistoryRecords(List<Bet> bets) {
        var BetHistories = createBetHistoryRecords(bets);
        betHistoryRepository.saveAll(BetHistories);
    }

}
