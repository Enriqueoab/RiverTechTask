package com.rivertech.betgametask.game.service;

import com.rivertech.betgametask.utils.exception.NotFoundException;
import lombok.AllArgsConstructor;
import com.rivertech.betgametask.game.Game;
import org.springframework.stereotype.Service;
import com.rivertech.betgametask.game.repository.GameRepository;

@Service
@AllArgsConstructor
//@Transactional(readOnly = true) // specifies that the transaction will only read data from the database and will not modify any data
public class GameServiceImpl implements GameService {

    private GameRepository gameRepo;

    @Override
    public Game findById(int gameId) throws NotFoundException {
        return gameRepo.findById(gameId).orElseThrow (
                () -> new NotFoundException("Game by ID not found"));
    }
}
