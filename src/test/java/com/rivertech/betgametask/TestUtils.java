package com.rivertech.betgametask;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetResult;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.wallet.Wallet;
import org.junit.jupiter.api.BeforeEach;

import java.time.Instant;
import java.util.List;

public abstract class TestUtils {

    protected static final Long NON_EXISTING_GAME_ID = 33L;


    protected Player player;

    protected Wallet wallet;

    protected Game game;

    protected Bet bet;

    @BeforeEach
    public void init()  {

        game = Game.builder()
                .id(1L)
                .description("Testing Game")
//                .bets(someListOfBets)
//                .executedAt(Instant.now())
//                .gameResult(1)
                .build();

        wallet = Wallet.builder()
                .id(1L)
                .build();

        player = new Player("Alma", "Test lady", "Tester");

        bet = Bet.builder()
                .id(1L)
                .betAmount(100L)
//                .wonAmount(0L)
                .betNum(1)
                .game(game)
                .player(player)
//                .placedAt(Instant.now())
//                .betResult(BetResult.WIN_5X)
                .build();

        game.setBets(List.of(bet));
    }

}
