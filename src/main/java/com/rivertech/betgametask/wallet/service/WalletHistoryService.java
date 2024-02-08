package com.rivertech.betgametask.wallet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.utils.exception.NotFoundException;

public interface WalletHistoryService {

    void save(WalletHistory walletHistory);

    Page<WalletHistory> retrievePlayerWalletHistory(String userName, Pageable pageable) throws NotFoundException;
}

