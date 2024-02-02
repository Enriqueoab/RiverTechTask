package com.rivertech.betgametask.player.service;

import com.rivertech.betgametask.player.Player;
import com.rivertech.betgametask.player.repository.PlayerRepository;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
//@Transactional(readOnly = true) // specifies that the transaction will only read data from the database and will not modify any data
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepo;

    // Add repo instance
    // Add an instance of BetService
    // ENDPOINT:
        // Add "playerRegistration"
            // The endpoint have to receive a "player" data
            // Then we have to create a "Wallet" for him and register
            // Return 200 with a "Register successful" message
            // Check if the "userName" exist already

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
