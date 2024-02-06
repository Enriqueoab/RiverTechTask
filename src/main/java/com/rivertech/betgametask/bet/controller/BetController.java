package com.rivertech.betgametask.bet.controller;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.bet.service.BetService;
import com.rivertech.betgametask.utils.DefaultApiResponses;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Bet")
@AllArgsConstructor
@RequestMapping("/bet")
class BetController {

    private final BetService betService;

//    @DefaultApiResponses
//    @Operation(summary = "Place a bet")
//    @PostMapping(path = "/{gameId}")
//    public void placeBet( @Valid @RequestBody BetForm betForm, @PathVariable int gameId) throws NotFoundException {
//        betService.placeBet(betForm, gameId);
//    }

}
