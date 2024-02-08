package com.rivertech.betgametask.bet.controller;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import com.rivertech.betgametask.TestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rivertech.betgametask.bet.service.BetService;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.WalletRequestException;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BetControllerTests extends TestUtils {

    @InjectMocks
    private BetController betController;

    @Mock
    private BetService betService;


    @Test
    public void placeBet_Success() throws GameRequestException, NotFoundException, WalletRequestException {
        Mockito.when(betController.placeBet(betForm)).thenReturn(game);
        Assertions.assertEquals(game, betController.placeBet(betForm));
        Mockito.verify(betService).placeBet(betForm);
    }


    @Test
    public void placeBet_NotFoundException() throws GameRequestException, NotFoundException, WalletRequestException {

        betForm.setGameId(NON_EXISTING_GAME_ID);
        Mockito.when(betController.placeBet(betForm)).thenThrow(new NotFoundException("Game Not Found"));
        Assertions.assertThrows(NotFoundException.class, () -> betService.placeBet(betForm));
    }

    @Test
    public void placeBet_GameAlreadyExecuted() throws GameRequestException, NotFoundException, WalletRequestException {

        Mockito.when(betService.placeBet(betForm))
                .thenThrow(new GameRequestException("Game already executed, not accepting more bets"));
        Assertions.assertThrows(GameRequestException.class, () -> betController.placeBet(betForm));
    }

}
