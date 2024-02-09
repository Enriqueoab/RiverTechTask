package com.rivertech.betgametask.player.controller;

import com.rivertech.betgametask.utils.exception.LeaderBoardRequestException;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import com.rivertech.betgametask.TestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rivertech.betgametask.player.LeaderboardProjection;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PlayerControllerTests extends TestUtils {

    @InjectMocks
    private PlayerController playerController;

    @Mock
    private PlayerService playerService;

    @Test
    void registerPlayer_Success() throws PlayerRequestException {

        Mockito.when(playerService.registerPlayer(registrationForm)).thenReturn(player);
        Assertions.assertNotNull(playerController.registerPlayer(registrationForm));
    }

    @Test
    void registerPlayer_PlayerRequestException() throws PlayerRequestException {
        Mockito.when(playerService.registerPlayer(registrationForm))
                .thenThrow(new PlayerRequestException("Player already in the DB"));

        Assertions.assertThrows(PlayerRequestException.class, () -> playerController.registerPlayer(registrationForm));

    }

    @Test
    void getLeaderBoard_PlayerRequestException() throws LeaderBoardRequestException {

        var expectedLeaderboard = new ArrayList<LeaderboardProjection>();
        Mockito.when(playerService.getLeaderBoard()).thenReturn(expectedLeaderboard);
        var actualLeaderboard = playerController.getLeaderBoard();

        Assertions.assertEquals(expectedLeaderboard, actualLeaderboard);
        Mockito.verify(playerService).getLeaderBoard();
    }

}
