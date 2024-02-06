package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.TransactionType;
import com.rivertech.betgametask.wallet.Wallet;
import com.rivertech.betgametask.wallet.WalletHistory;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.wallet.repository.WalletRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepo;
    private final WalletHistoryService walletHisService;

    // Method to deduct or add balance when a bet is placed. Ex: isDeduct boolean
    // ENDPOINT: To allowed the player see wallet transactions (wallet history)

    @Override
    @Transactional
    public void updateBalance(Wallet wallet, Long amount, boolean isDeduct) {

        var balanceBefore = wallet.getBalance();
        WalletHistory walletHistory;

        if (isDeduct) {
            wallet.setBalance(wallet.getBalance() - amount);
            // TODO: Create a walletHistory record as TransactionType.BET_PLACED
            walletHistory = new WalletHistory(wallet, balanceBefore,TransactionType.BET_PLACED);
        } else {
            wallet.setBalance(wallet.getBalance() + amount);
            // TODO: Create a walletHistory record as TransactionType.BET_WON
            walletHistory = new WalletHistory(wallet, balanceBefore,TransactionType.BET_WON);
        }
        walletRepo.save(wallet);
        walletHisService.save(walletHistory);
    }

    @Override
    public Wallet findById(int id) throws NotFoundException {
        return walletRepo.findById(id).orElseThrow (
                () -> new NotFoundException("Wallet by ID Not Found"));
    }

    @Override
    @Transactional
    public void updateBalanceByBetWonAmount(List<Bet> bets) {
        log.info("Updating wallets balance, if needed...");
        bets.forEach( bet -> {
            if (bet.getWonAmount() != 0) {
                updateBalance(bet.getPlayer().getWallet(), bet.getWonAmount(), false);
            }
        });
    }

}
