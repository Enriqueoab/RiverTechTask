package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.wallet.TransactionType;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.wallet.repository.WalletHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
//@Transactional(readOnly = true)
public class WalletHistoryServiceImpl implements WalletHistoryService {

    private final WalletHistoryRepository walletHistoryRepo;


    @Override
    @Transactional
    public void save(WalletHistory walletHistory) {
        walletHistoryRepo.save(walletHistory);
    }
}
