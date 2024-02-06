package com.rivertech.betgametask.game.controller;

import lombok.AllArgsConstructor;
import com.rivertech.betgametask.game.Game;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.rivertech.betgametask.game.service.GameService;
import org.springframework.web.bind.annotation.GetMapping;
import com.rivertech.betgametask.utils.exception.DefaultApiResponses;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;

@RestController
@AllArgsConstructor
@Tag(name = "Game")
@RequestMapping("/game")
class GameController {

    private final GameService gameService;

    @DefaultApiResponses
    @Operation(summary = "Execute game")
    @GetMapping(path = "/{gameId}")
    public Game executeGame(@PathVariable Long gameId) throws NotFoundException, GameRequestException {
        return gameService.executeGame(gameId);
    }

}
