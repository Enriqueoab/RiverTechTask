package com.rivertech.betgametask.player.service;

import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.RegistrationForm;
import com.rivertech.betgametask.player.repository.PlayerRepository;
import com.rivertech.betgametask.utils.exception.PlayerRequestException;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.Wallet;
import com.rivertech.betgametask.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
//@Transactional(readOnly = true) // specifies that the transaction will only read data from the database and will not modify any data
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepo;
    private final WalletService walletService;


    // Add repo instance
    // Add an instance of BetService
    // ENDPOINT:
        // Add "playerRegistration"
            // Then we have to create a "Wallet" for him and register
            // Return 200 with a "Register successful" message
            // Check if the "userName" exist already
    @Override
    @Transactional
    public Player registerPlayer(RegistrationForm register) throws PlayerRequestException {
        if (playerRepo.existsByUserName(register.getUserName())){
            throw new PlayerRequestException("The user name has to be unique, user name already in DB...");
        }
        var player = new Player(register.getName(), register.getSurname(),register.getUserName(), new Wallet());

        return save(player);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return playerRepo.existsByUserName(userName);
    }

    @Override
    @Transactional
    public Player save(Player player) {
        log.info("Storing player  ->  user name: {}, wallet ID: {}", player.getUserName(), player.getWallet().getId());
        return playerRepo.save(player);
    }

    // Get getLeaderboard
            // Return from repo LeaderboardProjection @EntityGraph(value = "Job.jobHistory")?
                //Ex: 	@EntityGraph(value = "Job.jobHistory")
                //	    JobDetailsProjection findWithAssigneeAndJobHistoryById(int id);

    @Override
    public Player findByUserName(String userName) throws NotFoundException {
        var player = playerRepo.findByUserName(userName);
        if (player == null) {
            throw new NotFoundException("Player Not Found by user name");
        }

        return player ;
    }


}
