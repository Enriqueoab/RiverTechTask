package com.rivertech.betgametask.game.service;

import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.NotFoundException;

public interface GameService {

    Game save(Game game);

    Game findById(Long gameId) throws NotFoundException;

    Game executeGame(Long gameId) throws NotFoundException, GameRequestException;
}
