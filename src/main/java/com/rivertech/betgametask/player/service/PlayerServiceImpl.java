package com.rivertech.betgametask.player.service;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;
import com.rivertech.betgametask.bet.Bet;
import org.springframework.stereotype.Service;
import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.RegistrationForm;
import com.rivertech.betgametask.wallet.service.WalletService;
import com.rivertech.betgametask.player.LeaderboardProjection;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.player.repository.PlayerRepository;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;

@Slf4j
@Service
@AllArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepo;
    private final WalletService walletService;

    @Override
    @Transactional
    public Player registerPlayer(RegistrationForm register) throws PlayerRequestException {
        if (playerRepo.existsByUserName(register.getUserName())){
            throw new PlayerRequestException("The user name has to be unique, user name already in DB");
        }
        var player = new Player(register.getName(), register.getSurname(),register.getUserName());
        return save(player);
    }
    @Override
    @Transactional
    public void updateBalance(Player player, Long betAmount, boolean isDeduct) {
       walletService.updateBalance(player.getWallet(), betAmount, isDeduct);
       save(player);
    }

    @Override
    public List<LeaderboardProjection> getLeaderBoard() {
        return playerRepo.findAllByBetsNotEmpty();
    }

    @Override
    public void updateBalanceByBetWonAmount(List<Bet> bets) {
        walletService.updateBalanceByBetWonAmount(bets);
    }

    @Override
    @Transactional
    public Player save(Player player) {
        log.info("Saving player  ->  user name: {}", player.getUserName());
        return playerRepo.save(player);
    }

    @Override
    public Player findByUserName(String userName) throws NotFoundException {
        var player = playerRepo.findByUserName(userName);
        if (player == null) {
            throw new NotFoundException("Player Not Found by user name");
        }

        return player ;
    }

}
