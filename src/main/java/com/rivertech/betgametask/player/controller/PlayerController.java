package com.rivertech.betgametask.player.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.RegistrationForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.rivertech.betgametask.utils.exception.DefaultApiResponses;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.player.LeaderboardProjection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;

import java.util.List;

@RestController
@Tag(name = "Player")
@AllArgsConstructor
@RequestMapping("/player")
class PlayerController {

    private final PlayerService playerService;

    @DefaultApiResponses
    @Operation(summary = "Place a bet")
    @PostMapping(path = "/register")
    public Player playerRegistration(@Valid @RequestBody RegistrationForm register) throws PlayerRequestException {
        return playerService.registerPlayer(register);
    }

    @DefaultApiResponses
    @Operation(summary = "Retrieve players leader board")
    @GetMapping(path = "/leaderBoard")
    public List<LeaderboardProjection> getLeaderBoard() {
        return playerService.getLeaderBoard();
    }

}
