package com.rivertech.betgametask.wallet.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.context.annotation.Lazy;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.player.service.PlayerService;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.repository.WalletHistoryRepository;

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
