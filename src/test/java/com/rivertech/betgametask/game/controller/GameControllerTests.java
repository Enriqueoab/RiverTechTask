package com.rivertech.betgametask.game.controller;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import com.rivertech.betgametask.TestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rivertech.betgametask.game.service.GameService;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class GameControllerTests extends TestUtils {

    @InjectMocks
    private GameController gameController;

    @Mock
    private GameService gameService;


    @Test
    public void executeGame_Success() throws GameRequestException, NotFoundException {
        Mockito.when(gameService.executeGame(game.getId())).thenReturn(game);
        Assertions.assertNotNull(gameController.executeGame(game.getId()));
        Mockito.verify(gameService).executeGame(game.getId());
    }


    @Test
    public void executeGame_NotFoundException() throws GameRequestException, NotFoundException {

        Mockito.when(gameService.executeGame(NON_EXISTING_GAME_ID)).thenThrow(new NotFoundException("Game Not Found"));
        Assertions.assertThrows(NotFoundException.class, () -> gameController.executeGame(NON_EXISTING_GAME_ID));
    }

    @Test
    public void executeGame_GameRequestException() throws GameRequestException, NotFoundException {
        Mockito.when(gameService.executeGame(game.getId()))
                .thenThrow(new GameRequestException("Game already executed, not accepting more bets"));
        Assertions.assertThrows(GameRequestException.class, () -> gameController.executeGame(game.getId()));
    }

}
