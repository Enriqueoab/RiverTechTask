package com.rivertech.betgametask.game.controller;

import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.game.service.GameService;
import com.rivertech.betgametask.utils.DefaultApiResponses;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@AllArgsConstructor
@Tag(name = "Game")
@RequestMapping("/game")
class GameController {

    private final GameService gameService;

    // TODO: Should be in bet
    @DefaultApiResponses
    @Operation(summary = "Place a bet")
    @PostMapping(path = "/{gameId}/bet")
    public Game placeBet(@Valid @RequestBody BetForm betForm, @PathVariable int gameId) throws NotFoundException {
        return gameService.placeBet(betForm, gameId);
    }

    @DefaultApiResponses
    @Operation(summary = "Execute game")
    @GetMapping(path = "/{gameId}")
    public Game executeGame(@PathVariable int gameId) throws NotFoundException {
        return gameService.executeGame(gameId);
    }

}
