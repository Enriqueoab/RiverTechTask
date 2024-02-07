package com.rivertech.betgametask.bet.service;

import java.util.List;

import com.rivertech.betgametask.player.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.rivertech.betgametask.bet.Bet;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.player.service.PlayerService;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.bet.repository.BetHistoryRepository;

@Slf4j
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
                        .betResultMessage(bet.getBetResult() == null ? null : bet.getBetResult().message)
                        .playerUserName(bet.getPlayer().getUserName())
                        .build())
                .toList();
    }

    @Transactional
    public List<BetHistory> generateBetHistoryRecord(Bet bet) {
        log.info("Generating bet history records...");
        return createBetHistoryRecords(List.of(bet));
    }

    @Override
    @Transactional
    public Page<BetHistory> retrieveBetResults(Player player, boolean isJustExecutedBets, Pageable pageable) {

        if (isJustExecutedBets) {
            return betHistoryRepository.findAllByPlayerIdAndGameResultNotNull(player.getId(), pageable);
        }
        return betHistoryRepository.findAllByPlayerId(player.getId(), pageable);
    }

}
