package com.rivertech.betgametask.wallet.repository;

import com.rivertech.betgametask.wallet.WalletHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletHistoryRepository extends JpaRepository<WalletHistory, Integer> {

    Page<WalletHistory> findAllByWalletId(Long walletId, Pageable pageable);
}
