package com.rivertech.betgametask.bet.controller;

import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.BetHistoryForm;
import com.rivertech.betgametask.bet.service.BetHistoryService;
import com.rivertech.betgametask.utils.DefaultApiResponses;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Bet History")
@AllArgsConstructor
@RequestMapping("/betHistory")
class BetHistoryController {

    private final BetHistoryService BetHistoryService;

    @DefaultApiResponses
    @Operation(summary = "Retrieve all the bet operations made by a player.")
    @GetMapping
    public Page<BetHistory> retrieveBetResults(@Valid @RequestBody BetHistoryForm betHistoryForm, Pageable pageable) throws NotFoundException {
       return BetHistoryService.retrieveBetResults(betHistoryForm, pageable);
    }

}
