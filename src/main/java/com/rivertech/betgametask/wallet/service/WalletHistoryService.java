package com.rivertech.betgametask.wallet.service;


import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.wallet.WalletHistory;
import org.springframework.stereotype.Service;

public interface WalletHistoryService {

    void save(WalletHistory walletHistory);
}
