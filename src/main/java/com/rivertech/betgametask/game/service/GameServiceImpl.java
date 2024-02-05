package com.rivertech.betgametask.game.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.bet.service.BetService;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import com.rivertech.betgametask.game.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.rivertech.betgametask.game.repository.GameRepository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepo;
    private final BetService betService;
    private final WalletService walletService;

    @Override
    @Transactional
    public Game placeBet(BetForm betForm, int gameId) throws NotFoundException {
        // TODO: Move method to betService and add a call to betHistoryService.generateBetHistoryRecords(bets);
        log.info("Placing bet for game id: {} and player {}...",gameId, betForm.getPlayerUserName());
        var game = findById(gameId);
        var bet = betService.toBet(betForm, game);
        var wallet = walletService.findById(bet.getPlayer().getWallet().getId());
        walletService.updateBalance(wallet, betForm.getBetAmount(), true);
        var savedBet = betService.save(bet);
        game.getBets().add(savedBet);
        return save(game);
    }

    @Override
    @Transactional
    public Game addBet(Game game, Bet bet) {
        game.getBets().add(bet);
        return save(game);

    }

    @Override
    @Transactional
    public Game save(Game game) {
        return gameRepo.save(game);

    }

    @Override
    public Game findById(int gameId) throws NotFoundException {
        return gameRepo.findById(gameId).orElseThrow (
                () -> new NotFoundException("Game by ID not found"));
    }

    @Override
    public Game executeGame(int gameId) throws NotFoundException {
        log.info("Executing game with ID: {}...",gameId);
        var game = gameRepo.findById(gameId).orElseThrow(() -> new NotFoundException("Game Not Found"));
        gameRepo.saveAndFlush(game.play(game));
        betService.priceBetCalculator(game);
        return game;
    }
}
