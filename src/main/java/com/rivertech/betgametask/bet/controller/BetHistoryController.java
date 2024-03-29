package com.rivertech.betgametask.bet.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Pageable;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.BetHistoryForm;
import com.rivertech.betgametask.bet.service.BetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.DefaultApiResponses;

@RestController
@AllArgsConstructor
@Tag(name = "Bet History")
@RequestMapping("/betHistory")
class BetHistoryController {

    private final BetService BetService;

    @DefaultApiResponses
    @Operation(summary = "Retrieve all the bet operations made by a player.")
    @GetMapping
    public Page<BetHistory> retrieveBetResults(@Valid @RequestBody BetHistoryForm betHistoryForm, Pageable pageable) throws NotFoundException {
       return BetService.retrieveBetResults(betHistoryForm, pageable);
    }

}
