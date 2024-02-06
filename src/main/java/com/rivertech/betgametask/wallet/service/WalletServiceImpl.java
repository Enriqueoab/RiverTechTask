package com.rivertech.betgametask.wallet.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import com.rivertech.betgametask.bet.Bet;
import org.springframework.stereotype.Service;
import com.rivertech.betgametask.wallet.Wallet;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.wallet.TransactionType;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.wallet.repository.WalletRepository;

@Slf4j
@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepo;
    private final WalletHistoryService walletHisService;

    @Override
    @Transactional
    public void updateBalance(Wallet wallet, Long amount, boolean isDeduct) {
        log.info("Updating wallet balance...");
        var balanceBefore = wallet.getBalance();
        WalletHistory walletHistory;

        if (isDeduct) {
            wallet.setBalance(wallet.getBalance() - amount);
            walletHistory = new WalletHistory(wallet, balanceBefore,TransactionType.BET_PLACED);
        } else {
            wallet.setBalance(wallet.getBalance() + amount);
            walletHistory = new WalletHistory(wallet, balanceBefore,TransactionType.BET_WON);
        }
        walletRepo.save(wallet);
        walletHisService.save(walletHistory);
    }

    @Override
    @Transactional
    public void updateBalanceByBetWonAmount(List<Bet> bets) {
        bets.forEach( bet -> {
            if (bet.getWonAmount() != 0) {
                updateBalance(bet.getPlayer().getWallet(), bet.getWonAmount(), false);
            }
        });
    }

}
