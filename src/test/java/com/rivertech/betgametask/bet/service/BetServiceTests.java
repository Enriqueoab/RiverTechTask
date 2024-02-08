package com.rivertech.betgametask.bet.service;

import java.util.List;
import org.mockito.Mock;
import java.time.Instant;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import com.rivertech.betgametask.TestUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageImpl;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import com.rivertech.betgametask.game.service.GameServiceImpl;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;
import com.rivertech.betgametask.utils.exception.WalletRequestException;

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

        Mockito.when(betHistoryService.generateBetHistoryRecord(ArgumentMatchers.any())).thenReturn(betHistory);
        Mockito.when(playerService.findByUserName(betForm.getPlayerUserName())).thenReturn(player);
        Mockito.lenient().when(betService.placeBet(betForm)).thenReturn(game);

        Mockito.verify(gameService).findByIdAndNotExecuted(betForm.getGameId());
        Mockito.verify(playerService).findByUserName(betForm.getPlayerUserName());
	}

    @Test
    public void toBet_Success() {

        Mockito.when(betHistoryService.generateBetHistoryRecord(ArgumentMatchers.any())).thenReturn(betHistory);

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

    @Test
    public void retrieveBetResults_Success() throws NotFoundException {

        Mockito.when(playerService.findByUserName(betForm.getPlayerUserName())).thenReturn(player);
        Mockito.when(betHistoryService.retrieveBetResults(player, true, Pageable.unpaged())).thenReturn(new PageImpl<>(List.of(betHistory)));

        var betHistory = betService.retrieveBetResults(betHisForm, Pageable.unpaged());
        Assertions.assertNotEquals(betHistory, this.betHistory);
        Mockito.verify(betHistoryService).retrieveBetResults(player, true, Pageable.unpaged());
        Mockito.verify(playerService).findByUserName(betForm.getPlayerUserName());

    }

}
