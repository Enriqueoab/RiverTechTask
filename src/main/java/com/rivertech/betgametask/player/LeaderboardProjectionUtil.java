package com.rivertech.betgametask.player;

import java.util.List;
import java.util.Comparator;
import com.rivertech.betgametask.bet.Bet;
import org.springframework.stereotype.Component;

@Component
public class LeaderboardProjectionUtil {

    public List<Bet> orderByWonBets(final List<Bet> bets) {
        return bets.stream().sorted(Comparator.comparing(Bet::getBetResult)).toList();
    }

}
