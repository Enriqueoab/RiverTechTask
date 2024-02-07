package com.rivertech.betgametask.wallet.service;

import java.util.List;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.wallet.Wallet;

public interface WalletService {

    Wallet updateBalance(Wallet wallet, Long betAmount, boolean isDeduct);

    void updateBalanceByBetWonAmount(List<Bet> bets);

}
