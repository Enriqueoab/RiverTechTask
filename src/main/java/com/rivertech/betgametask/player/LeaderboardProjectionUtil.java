package com.rivertech.betgametask.player;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import com.rivertech.betgametask.bet.Bet;
import org.springframework.stereotype.Component;

@Component
public class LeaderboardProjectionUtil {

    public List<Bet> orderByWonBets(final List<Bet> bets) {

        Map<Player, Long> totalWonAmountByPlayer = bets.stream()
                .filter(bet -> bet.getWonAmount() != null)
                .collect(Collectors.groupingBy(Bet::getPlayer,
                        Collectors.summingLong(Bet::getWonAmount)));

        return totalWonAmountByPlayer.entrySet().stream()
                .map(entry -> {
                    Bet bet = new Bet();
                    bet.setPlayer(entry.getKey());
                    bet.setWonAmount(entry.getValue());
                    return bet;
                })
                .toList();
    }

}
