package com.rivertech.betgametask.wallet.repository;

import com.rivertech.betgametask.wallet.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

}
