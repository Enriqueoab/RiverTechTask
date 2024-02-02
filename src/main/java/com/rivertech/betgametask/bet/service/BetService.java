package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.utils.exception.NotFoundException;

public interface BetService {

    Bet placeBet(BetForm betForm) throws NotFoundException;

}
