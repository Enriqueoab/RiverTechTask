package com.rivertech.betgametask.player;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;

public interface LeaderboardProjection {

    String getName();

    String getUserName();

    @Value("#{@leaderboardProjectionUtil.orderByWonBets(target.bets)}")
    List<BetProjection> getBets();

}
