package com.rivertech.betgametask.wallet.service;

import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.TransactionType;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.wallet.repository.WalletHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletHistoryServiceImpl implements WalletHistoryService {

    private final WalletHistoryRepository walletHistoryRepo;
    private final PlayerService playerService;

    public WalletHistoryServiceImpl(WalletHistoryRepository walletHistoryRepo, @Lazy PlayerService playerService) {
        this.walletHistoryRepo = walletHistoryRepo;
        this.playerService = playerService;
    }


    @Override
    @Transactional
    public void save(WalletHistory walletHistory) {
        walletHistoryRepo.save(walletHistory);
    }

    @Override
    public Page<WalletHistory> retrievePlayerWalletHistory(String userName, Pageable pageable) throws NotFoundException {
        var player = playerService.findByUserName(userName);
        return walletHistoryRepo.findAllByWalletId(player.getWallet().getId(), pageable);
    }
}
