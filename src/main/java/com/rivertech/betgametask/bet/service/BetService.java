package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.BetHistoryForm;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.WalletRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BetService {

    Game placeBet(BetForm betForm) throws NotFoundException, GameRequestException, WalletRequestException;

    Bet toBet(BetForm betForm, Player player, Game game) throws NotFoundException;

    Game priceBetCalculator(Game game) throws NotFoundException;

    Page<BetHistory> retrieveBetResults(BetHistoryForm betHistoryForm, Pageable pageable) throws NotFoundException;
}
