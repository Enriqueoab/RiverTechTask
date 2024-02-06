package com.rivertech.betgametask.wallet.controller;

import com.rivertech.betgametask.utils.exception.DefaultApiResponses;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.wallet.service.WalletHistoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "WalletHistory")
@AllArgsConstructor
@RequestMapping("/walletHistory")
class WalletHistoryController {

    private final WalletHistoryService walletHistoryService;

    @DefaultApiResponses
    @Operation(summary = "Retrieve all the wallet transactions made.")
    @GetMapping
    public Page<WalletHistory> retrieveBetResults(@RequestParam String userName, Pageable pageable) throws NotFoundException {
        return walletHistoryService.retrievePlayerWalletHistory(userName, pageable);
    }

}
