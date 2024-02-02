package com.rivertech.betgametask.player.service;

import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.utils.exception.NotFoundException;

public interface PlayerService {
    Player findByUserName(String userName) throws NotFoundException;
}
