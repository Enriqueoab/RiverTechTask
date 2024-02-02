package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.Wallet;
import org.springframework.transaction.annotation.Transactional;

public interface WalletService {

    void updateBalance(Wallet wallet, Long betAmount, boolean isDeduct);

    Wallet findById(int id) throws NotFoundException;
}
