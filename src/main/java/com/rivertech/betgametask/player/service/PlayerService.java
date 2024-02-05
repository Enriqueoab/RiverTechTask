package com.rivertech.betgametask.player.service;

import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.RegistrationForm;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlayerService {
    @Transactional
    Player save(Player player);

    Player findByWalletId(int walletId);

    Player findByUserName(String userName) throws NotFoundException;

    Player registerPlayer(RegistrationForm register) throws PlayerRequestException;

    Player findById(Long id) throws NotFoundException;

    List<Player> findAllByIdIn(List<Long> playerIds);

}
