package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import org.springframework.data.domain.Page;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.bet.BetHistory;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.bet.BetHistoryForm;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.WalletRequestException;

public interface BetService {

    Game priceBetCalculator(Game game) throws NotFoundException;

    Bet toBet(BetForm betForm, Player player, Game game) throws NotFoundException;

    Game placeBet(BetForm betForm) throws NotFoundException, GameRequestException, WalletRequestException;
    Page<BetHistory> retrieveBetResults(BetHistoryForm betHistoryForm, Pageable pageable) throws NotFoundException;
}
