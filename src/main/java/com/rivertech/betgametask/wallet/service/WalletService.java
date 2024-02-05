package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.Wallet;

import java.util.List;

public interface WalletService {

    void updateBalance(Wallet wallet, Long betAmount, boolean isDeduct);

    Wallet findById(int id) throws NotFoundException;

    void updateBalanceByBetResult(List<Player> players);
}
