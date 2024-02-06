package com.rivertech.betgametask.game.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.utils.exception.NotFoundException;

public interface GameService {


    Game placeBet(BetForm betForm, int gameId) throws NotFoundException;

    Game addBet(Game game, Bet bet);

    Game save(Game game);

    Game findById(int gameId) throws NotFoundException;

    Game executeGame(int gameId) throws NotFoundException;
}
