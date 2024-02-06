package com.rivertech.betgametask.player.service;

import java.util.List;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.wallet.Wallet;
import com.rivertech.betgametask.player.RegistrationForm;
import com.rivertech.betgametask.player.LeaderboardProjection;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;


public interface PlayerService {
    @Transactional
    Player save(Player player);

    Player findByUserName(String userName) throws NotFoundException;

    Player registerPlayer(RegistrationForm register) throws PlayerRequestException;

    void updateBalance(Wallet wallet, Long betAmount, boolean isDeduct);

    List<LeaderboardProjection> getLeaderBoard();
}
