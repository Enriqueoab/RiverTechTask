package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.Wallet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.wallet.repository.WalletRepository;

import java.util.List;

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
            // TODO: Create a walletHistory record as TransactionType.BET_PLACED
        } else {
            wallet.setBalance(wallet.getBalance() + betAmount);
            // TODO: Create a walletHistory record as TransactionType.BET_EARNED
        }
        walletRepo.save(wallet);
    }

    @Override
    public Wallet findById(int id) throws NotFoundException {
        return walletRepo.findById(id).orElseThrow (
                () -> new NotFoundException("Wallet by ID Not Found"));
    }

    @Override
    public void updateBalanceByBetResult(List<Player> players) {
//        TODO: Loop stream by player getting the wallet and adding or resting
//         up to what betResult value has.
//            Create method Async to make a betHistory record the method has to be
//            @Transactional(Transactional.TxType.NEVER) (I think)
//        var wallets = players.stream().map(pw -> {
//            pw.getWallet().getBalance() +
//            pw.getWallet().setBalance();
//
//        });

    }

}
