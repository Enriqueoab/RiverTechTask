package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.WalletRequestException;

public interface BetService {

    Game placeBet(BetForm betForm) throws NotFoundException, GameRequestException, WalletRequestException;

    Bet toBet(BetForm betForm, Game game) throws NotFoundException;

    Bet save(Bet bet);

    void priceBetCalculator(Game game) throws NotFoundException;
}
