package com.rivertech.betgametask.player.service;

import java.util.List;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.RegistrationForm;
import com.rivertech.betgametask.player.LeaderboardProjection;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;

public interface PlayerService {

    Player save(Player player);

    Player findByUserName(String userName) throws NotFoundException;

    Player registerPlayer(RegistrationForm register) throws PlayerRequestException;

    void updateBalance(Player player, Long betAmount, boolean isDeduct);

    List<LeaderboardProjection> getLeaderBoard();
}
