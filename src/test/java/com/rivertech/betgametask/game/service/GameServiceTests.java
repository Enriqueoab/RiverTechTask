package com.rivertech.betgametask.game.service;

import org.mockito.Mock;
import java.time.Instant;
import java.util.Optional;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import com.rivertech.betgametask.TestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rivertech.betgametask.bet.service.BetServiceImpl;
import com.rivertech.betgametask.game.repository.GameRepository;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class GameServiceTests extends TestUtils {


    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private BetServiceImpl betService;

    @Mock
    private GameRepository gameRepo;

	@Test
	void findById_Success() throws NotFoundException {

		Mockito.when(gameRepo.findById(game.getId())).thenReturn(Optional.ofNullable(game));
		Assertions.assertEquals(game.getId(), gameService.findById(game.getId()).getId());
        Mockito.verify(gameRepo).findById(game.getId());
	}

    @Test
    void findById_NotFoundException() {

        Mockito.when(gameRepo.findById(NON_EXISTING_GAME_ID)).thenReturn(Optional.empty());
        var exception = Assertions.assertThrows(NotFoundException.class,
                () -> gameService.findById(NON_EXISTING_GAME_ID));

        Assertions.assertEquals("Game by ID not found",
                exception.getMessage());
    }

	@Test
	void saveGame_Success() {

		Mockito.when(gameRepo.save(game)).thenReturn(game);
		Assertions.assertEquals(game.getDescription(), gameService.save(game).getDescription());
        Mockito.verify(gameRepo).save(game);
	}

    @Test
    public void executeGame_Success() throws GameRequestException, NotFoundException {

        Mockito.when(gameRepo.findById(game.getId())).thenReturn(Optional.ofNullable(game));
        Mockito.lenient().when(gameService.executeGame(game.getId())).thenReturn(game);

        Assertions.assertNotNull(game.getExecutedAt());
        Assertions.assertNotNull(game.getGameResult());
        Mockito.verify(betService).priceBetCalculator(game);
    }

    @Test
    public void executeGame_NotFoundException() {

        Mockito.when(gameRepo.findById(NON_EXISTING_GAME_ID)).thenReturn(Optional.empty());
        var exception = Assertions.assertThrows(NotFoundException.class,
                                () -> gameService.executeGame(NON_EXISTING_GAME_ID));

        Assertions.assertEquals("Game by ID not found", exception.getMessage());
    }

    @Test
    public void executeGame_GameAlreadyExecuted() throws GameRequestException, NotFoundException {

        game.setExecutedAt(Instant.now());
        Mockito.when(gameRepo.findById(game.getId())).thenReturn(Optional.ofNullable(game));
        var exception = Assertions.assertThrows(GameRequestException.class,
                () -> gameService.executeGame(game.getId()));

        Assertions.assertEquals("Game already executed, not accepting more bets", exception.getMessage());
    }

    @Test
    public void addBetToGame_Success() {

        Mockito.when(gameService.addBetToGame(bet, game)).thenReturn(game);
        var gameOldState = game;
        Assertions.assertNotNull(game.getBets().get(0));
        Assertions.assertNotEquals(gameOldState.getBets().size(),
                                    gameService.addBetToGame(bet, game).getBets().size());
    }

}
