package com.rivertech.betgametask.game.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;

public interface GameService {

    Game save(Game game);

    Game findById(Long gameId) throws NotFoundException;

    Game findByIdAndNotExecuted(Long gameId) throws NotFoundException, GameRequestException;

    Game executeGame(Long gameId) throws NotFoundException, GameRequestException;

    Game addBetToGame(Bet bet, Game game);
}
