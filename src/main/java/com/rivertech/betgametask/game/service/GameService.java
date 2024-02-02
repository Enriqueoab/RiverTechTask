package com.rivertech.betgametask.game.service;

import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import org.springframework.stereotype.Service;

public interface GameService {

    Game findById(int gameId) throws NotFoundException;

}
