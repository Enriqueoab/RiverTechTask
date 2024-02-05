package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.bet.service.BetService;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.wallet.TransactionType;
import com.rivertech.betgametask.wallet.Wallet;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.wallet.repository.WalletHistoryRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WalletJpaListener {

    private final WalletHistoryService walletHistoryService;

    public WalletJpaListener(@Lazy WalletHistoryService walletHistoryService) {
        this.walletHistoryService = walletHistoryService;
    }

    @PostPersist
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void afterPersist(Player player) {
        log.info("New player wallet History record, wallet ID: {}", player.getWallet().getBalance());
        var walletHistory = new WalletHistory(player, TransactionType.BALANCE_ADDED);
        walletHistoryService.save(walletHistory);
    }

}
