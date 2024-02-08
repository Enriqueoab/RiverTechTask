package com.rivertech.betgametask.player.service;

import org.mockito.Mock;
import java.util.Arrays;
import java.util.ArrayList;
import org.mockito.Mockito;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import com.rivertech.betgametask.TestUtils;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.rivertech.betgametask.wallet.service.WalletService;
import com.rivertech.betgametask.player.LeaderboardProjection;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.player.repository.PlayerRepository;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class PlayerServiceTests extends TestUtils {

    @InjectMocks
    private PlayerServiceImpl playerService;

    @Mock
    private PlayerRepository playerRepo;

    @Mock
    private WalletService walletService;


    @Test
    void registerPlayer_Success() throws PlayerRequestException {
        Mockito.when(playerRepo.save(player)).thenReturn(player);
        var player = playerService.registerPlayer(registrationForm);

        Mockito.verify(playerRepo).existsByUserName(player.getUserName());
        Mockito.verify(playerRepo).save(player);
        Assertions.assertNotNull(player);
        Assertions.assertEquals(registrationForm.getName(), player.getName());
        Assertions.assertEquals(registrationForm.getSurname(), player.getSurname());
        Assertions.assertEquals(registrationForm.getUserName(), player.getUserName());
    }

    @Test
    void registerPlayer_PlayerRequestException() {

        Mockito.when(playerRepo.existsByUserName(registrationForm.getUserName())).thenReturn(true);
        var exception = Assertions.assertThrows(PlayerRequestException.class,
                        () -> playerService.registerPlayer(registrationForm));

        Assertions.assertEquals("The user name has to be unique, user name already in DB",
                                exception.getMessage());
    }

    @Test
    void updateBalance_Success() {

        Mockito.when(playerRepo.save(player)).thenReturn(player);
        playerService.updateBalance(player, 100L, true);

        Mockito.verify(walletService).updateBalance(player.getWallet(), 100L, true);
        Mockito.verify(playerRepo).save(player);
    }

    @Test
    void getLeaderBoard_Success() {
        var leaderboard = new ArrayList<LeaderboardProjection>();
        Mockito.when(playerRepo.findAllByBetsNotEmpty()).thenReturn(leaderboard);

        var result = playerService.getLeaderBoard();
        Assertions.assertEquals(leaderboard, result);
        Mockito.verify(playerRepo).findAllByBetsNotEmpty();
    }

    @Test
    void updateBalanceByBetWonAmount_Success() {
        var bets = Arrays.asList(bet);
        playerService.updateBalanceByBetWonAmount(bets);
        Mockito.verify(walletService).updateBalanceByBetWonAmount(bets);
    }

    @Test
    void findByUserName_Success() throws NotFoundException {

        Mockito.when(playerRepo.findByUserName(player.getUserName())).thenReturn(player);
        var result = playerService.findByUserName(player.getUserName());

        Assertions.assertEquals(player, result);
        Mockito.verify(playerRepo).findByUserName(player.getUserName());
    }

    @Test
    void findByUserName_NotFoundException() {
        Mockito.when(playerRepo.findByUserName(NON_EXISTING_USER_NAME)).thenReturn(null);

        var exception =Assertions.assertThrows(NotFoundException.class, () -> playerService.findByUserName(NON_EXISTING_USER_NAME));
        Mockito.verify(playerRepo).findByUserName(NON_EXISTING_USER_NAME);

        Assertions.assertEquals("Player Not Found by user name", exception.getMessage());
    }

}
