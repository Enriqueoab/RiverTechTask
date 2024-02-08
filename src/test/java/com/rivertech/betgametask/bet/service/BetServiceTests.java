package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.TestUtils;
import com.rivertech.betgametask.game.service.GameServiceImpl;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.WalletRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.Instant;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BetServiceTests extends TestUtils {


    @InjectMocks
    private BetServiceImpl betService;

    @Mock
    private GameServiceImpl gameService;

    @Mock
    private BetHistoryService betHistoryService;

    @Mock
    private PlayerService playerService;



	@Test
	void placeBet_Success() throws NotFoundException, GameRequestException, WalletRequestException {

        Mockito.when(betHistoryService.generateBetHistoryRecord(any())).thenReturn(Arrays.asList(betHistory));
        Mockito.when(playerService.findByUserName(betForm.getPlayerUserName())).thenReturn(player);
        Mockito.lenient().when(betService.placeBet(betForm)).thenReturn(game);

        Mockito.verify(gameService).findByIdAndNotExecuted(betForm.getGameId());
        Mockito.verify(playerService).findByUserName(betForm.getPlayerUserName());
	}

    @Test
    public void toBet_Success() {

        Mockito.when(betHistoryService.generateBetHistoryRecord(any())).thenReturn(Arrays.asList(betHistory));

        var bet = betService.toBet(betForm, player, game);

        var oldState = game;
        Assertions.assertEquals(oldState,
                betService.toBet(betForm, player, game).getGame());

        Assertions.assertEquals(betForm.getBetNum(), bet.getBetNum());
        Assertions.assertEquals(betForm.getBetAmount(), bet.getBetAmount());
        Assertions.assertEquals(player, bet.getPlayer());
        Assertions.assertEquals(game, bet.getGame());
        Assertions.assertEquals(Instant.class, bet.getPlacedAt().getClass());
        Assertions.assertNotNull(bet.getBetHistory());


    }

//    @Test
//    public void executeGame_NotFoundException() throws GameRequestException, NotFoundException {
//
//        Mockito.when(gameService.executeGame(NON_EXISTING_GAME_ID)).thenThrow(new NotFoundException("Game Not Found"));
//        Assertions.assertThrows(NotFoundException.class, () -> gameService.executeGame(NON_EXISTING_GAME_ID));

//        Mockito.when(gameRepo.findById(game.getId())).thenReturn(Optional.ofNullable(game));
//        Mockito.when(gameService.executeGame(game.getId())).thenReturn(game);
//        Mockito.doReturn(game).when(gameRepo.save(game));
//        Mockito.lenient().when(betService.priceBetCalculator(game)).thenReturn(game);
//        Mockito.when(betService.priceBetCalculator(game)).thenReturn(game);
//        Mockito.when(betHistoryService.generateBetHistoryRecord(bet)).thenReturn(Arrays.asList(new BetHistory()));
//        System.err.println("---- game.getGameResult() ----> "+game.getGameResult() );
//        Mockito.when(gameService.executeGame(NON_EXISTING_GAME_ID))
//                .thenThrow(new NotFoundException("Game already executed, not accepting more bets"));
//        Assertions.assertThrows(GameRequestException.class, () -> gameService.executeGame(NON_EXISTING_GAME_ID));
//
//    }

//    @Test
//    public void addBetToGame_Success() {
//        Mockito.when(gameService.addBetToGame(bet, game)).thenReturn(game);
//        System.err.println("---- game.getBets() ----> "+game.getBets() );
//        var gameOldState = game;
//        Assertions.assertNotNull(game.getBets().get(0));
//        Assertions.assertNotEquals(gameOldState.getBets().size(),
//                                    gameService.addBetToGame(bet, game).getBets().size());
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
