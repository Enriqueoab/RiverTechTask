package com.rivertech.betgametask.game.service;

import lombok.extern.slf4j.Slf4j;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import com.rivertech.betgametask.bet.service.BetService;
import com.rivertech.betgametask.game.repository.GameRepository;
import org.springframework.transaction.annotation.Transactional;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.utils.exception.GameRequestException;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepo;
    private final BetService betService;

    public GameServiceImpl(GameRepository gameRepo, @Lazy BetService betService) {
        this.gameRepo = gameRepo;
        this.betService = betService;
    }

    @Override
    @Transactional
    public Game save(Game game) {
        return gameRepo.save(game);

    }

    @Override
    public Game findById(Long gameId) throws NotFoundException {
        return gameRepo.findById(gameId).orElseThrow (
                () -> new NotFoundException("Game by ID not found"));
    }

    @Override
    @Transactional
    public Game executeGame(Long gameId) throws NotFoundException, GameRequestException {
        log.info("Executing game with ID: {}...",gameId);
        var game = gameRepo.findById(gameId).orElseThrow(() -> new NotFoundException("Game Not Found"));
        if (game.getExecutedAt() != null) {
            log.warn("The game with ID: {}, was already executed...",game.getId());
            throw new GameRequestException("Game already executed, not accepting more bets");
        }
        var GameWithBetsPriceCalculated = betService.priceBetCalculator(game.play());
        return save(GameWithBetsPriceCalculated);
    }

    @Override
    @Transactional
    public Game addBetToGame(Bet bet, Game game) {
        game.getBets().add(bet);
        return save(game);
    }
}
