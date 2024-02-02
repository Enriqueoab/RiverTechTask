package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.Wallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.wallet.repository.WalletRepository;

@Service
@AllArgsConstructor
//@Transactional(readOnly = true) // specifies that the transaction will only read data from the database and will not modify any data
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepo;

    // Method to deduct or add balance when a bet is placed. Ex: isDeduct boolean
    // ENDPOINT: To allowed the player see wallet transactions (wallet history)

    @Override
    @Transactional
    public void updateBalance(Wallet wallet, Long betAmount, boolean isDeduct) {
        if (isDeduct) {
            wallet.setBalance(wallet.getBalance() - betAmount);
        } else {
            wallet.setBalance(wallet.getBalance() + betAmount);
        }
        walletRepo.save(wallet);
    }

    @Override
    public Wallet findById(int id) throws NotFoundException {
        return walletRepo.findById(id).orElseThrow (
                () -> new NotFoundException("Wallet by ID Not Found"));
    }

}
