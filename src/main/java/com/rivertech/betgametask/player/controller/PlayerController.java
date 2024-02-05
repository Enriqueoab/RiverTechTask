package com.rivertech.betgametask.player.controller;

import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.RegistrationForm;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.utils.DefaultApiResponses;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
