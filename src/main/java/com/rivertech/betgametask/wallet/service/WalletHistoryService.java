package com.rivertech.betgametask.wallet.service;


import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.WalletHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

public interface WalletHistoryService {

    void save(WalletHistory walletHistory);

    Page<WalletHistory> retrievePlayerWalletHistory(String userName, Pageable pageable) throws NotFoundException;
}

