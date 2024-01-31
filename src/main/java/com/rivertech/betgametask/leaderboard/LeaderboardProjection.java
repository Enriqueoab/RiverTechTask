package com.rivertech.betgametask.leaderboard;

import java.util.List;
import com.rivertech.betgametask.bet.Bet;
import org.springframework.beans.factory.annotation.Value;

public interface LeaderboardProjection {

    String getName();

    String getSurname();

    @Value("#{@leaderboardProjectionUtil.orderByWonBets(target.bets)}")
    List<Bet> getBets();

}
