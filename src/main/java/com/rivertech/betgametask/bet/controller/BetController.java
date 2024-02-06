package com.rivertech.betgametask.bet.controller;

import com.rivertech.betgametask.utils.exception.WalletRequestException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.bet.BetForm;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.rivertech.betgametask.bet.service.BetService;
import com.rivertech.betgametask.utils.exception.DefaultApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;

@RestController
@Tag(name = "Bet")
@AllArgsConstructor
@RequestMapping("/bet")
class BetController {

    private final BetService betService;

    @DefaultApiResponses
    @Operation(summary = "Place a bet")
    @PostMapping
    public Game placeBet(@Valid @RequestBody BetForm betForm) throws NotFoundException, GameRequestException, WalletRequestException {
        return betService.placeBet(betForm);
    }

}
