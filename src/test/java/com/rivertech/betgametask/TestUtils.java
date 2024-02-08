package com.rivertech.betgametask;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.bet.BetHistory;
import com.rivertech.betgametask.bet.BetHistoryForm;
import com.rivertech.betgametask.player.RegistrationForm;

public abstract class TestUtils {

    protected static final String NON_EXISTING_USER_NAME = "Cornelius";
    protected static final Long NON_EXISTING_GAME_ID = 33L;
    protected static final int GOOD_BETTING_NUM = 7;

    protected RegistrationForm registrationForm;

    protected BetHistoryForm betHisForm;

    protected BetHistory betHistory;

    protected BetForm betForm;

    protected Player player;

    protected Game game;

    protected Bet bet;

    @BeforeEach
    public void init()  {

        game = Game.builder()
                .id(1L)
                .description("Testing Game")
                .bets(new ArrayList<>())
                .build();

        player = new Player("Alma", "Test lady", "Tester");

        bet = Bet.builder()
                .id(1L)
                .betAmount(100L)
                .betNum(1)
                .player(player)
                .betHistory(new ArrayList<>())
                .build();

        registrationForm = new RegistrationForm(player.getName(), player.getSurname(), player.getUserName());

        betForm = new BetForm(player.getName(), GOOD_BETTING_NUM, game.getId(), 250L);

        betHisForm = new BetHistoryForm(player.getName(), true);

        betHistory = BetHistory.builder()
                        .game(bet.getGame())
                        .player(bet.getPlayer())
                        .betNum(bet.getBetNum())
                        .placedAt(bet.getPlacedAt())
                        .betResultMessage(bet.getBetResult() == null ? null : bet.getBetResult().message)
                        .playerUserName(bet.getPlayer().getUserName())
                        .build();

    }

}
