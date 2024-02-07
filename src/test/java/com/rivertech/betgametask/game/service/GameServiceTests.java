package com.rivertech.betgametask.game.service;

import com.rivertech.betgametask.TestUtils;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.repository.BetHistoryRepository;
import com.rivertech.betgametask.bet.service.BetHistoryService;
import com.rivertech.betgametask.bet.service.BetHistoryServiceImpl;
import com.rivertech.betgametask.bet.service.BetService;
import com.rivertech.betgametask.bet.service.BetServiceImpl;
import com.rivertech.betgametask.game.repository.GameRepository;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.NotFoundException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class GameServiceTests extends TestUtils {


    @InjectMocks
    private GameServiceImpl gameService;

    @Mock
    private BetServiceImpl betService;

    @Mock
    private BetHistoryRepository BetHistoryRepo;

    @Mock
    private BetHistoryServiceImpl betHistoryService;

    @Mock
    private GameRepository gameRepo;

	@Test
	void findById_Success() throws NotFoundException {
		Mockito.when(gameRepo.findById(game.getId())).thenReturn(Optional.ofNullable(game));
		Assertions.assertEquals(game.getId(), gameService.findById(game.getId()).getId());
        Mockito.verify(gameRepo).findById(game.getId());
	}

	@Test
	void saveGame_Success()  {
		Mockito.when(gameRepo.save(game)).thenReturn(game);
		Assertions.assertEquals(game.getDescription(), gameService.save(game).getDescription());
        Mockito.verify(gameRepo).save(game);
	}

    @Test
    public void executeGame_Success() throws GameRequestException, NotFoundException {
        Mockito.lenient().when(gameRepo.findById(game.getId())).thenReturn(Optional.ofNullable(game));
//        Mockito.when(gameService.executeGame(game.getId())).thenReturn(game);
        Mockito.lenient().when(gameService.executeGame(game.getId())).thenReturn(game);
//        Mockito.doReturn(game).when(gameRepo.save(game));
        Mockito.lenient().when(betService.priceBetCalculator(game)).thenReturn(game);
//        Mockito.when(betService.priceBetCalculator(game)).thenReturn(game);
//        Mockito.when(betHistoryService.generateBetHistoryRecord(bet)).thenReturn(Arrays.asList(new BetHistory()));
        System.err.println("---- game.getGameResult() ----> "+game.getGameResult() );
//        Mockito.when(gameService.executeGame(game.getId()))
//                .thenThrow(new GameRequestException("Game already executed, not accepting more bets"));
//        Assertions.assertThrows(GameRequestException.class, () -> gameService.executeGame(game.getId()));

        Assertions.assertNotNull(game.getExecutedAt());
        Assertions.assertNotNull(game.getGameResult());
        Mockito.verify(betService).priceBetCalculator(game);
    }
//
//
//    @Test
//    public void executeGame_GameNotFound() throws GameRequestException, NotFoundException {
//
//        Mockito.when(gameService.executeGame(NON_EXISTING_GAME_ID)).thenThrow(new NotFoundException("Game Not Found"));
//        Assertions.assertThrows(NotFoundException.class, () -> gameService.executeGame(NON_EXISTING_GAME_ID));
//    }
//
//    @Test
//    public void executeGame_GameAlreadyExecuted() throws GameRequestException, NotFoundException {
//        Mockito.when(gameRepo.findById(game.getId())).thenReturn(Optional.ofNullable(game));
//        game.setExecutedAt(Instant.now());
//        Mockito.when(gameService.executeGame(game.getId()))
//                .thenThrow(new GameRequestException("Game already executed, not accepting more bets"));
//        Assertions.assertThrows(GameRequestException.class, () -> gameService.executeGame(game.getId()));
//    }

}
