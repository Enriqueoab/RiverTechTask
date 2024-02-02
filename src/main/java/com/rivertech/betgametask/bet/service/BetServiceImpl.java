package com.rivertech.betgametask.bet.service;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.bet.BetForm;
import com.rivertech.betgametask.bet.repository.BetRepository;
import com.rivertech.betgametask.game.service.GameService;
import com.rivertech.betgametask.player.service.PlayerService;
import com.rivertech.betgametask.utils.exception.NotFoundException;
import com.rivertech.betgametask.wallet.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

//@Transactional(readOnly = true) // specifies that the transaction will only read data from the database and will not modify any data
@Service
@AllArgsConstructor
public class BetServiceImpl implements BetService {

    private final static boolean ADD_AMOUNT = false;
    private final static boolean DEDUCT_AMOUNT = true;

    private final BetRepository betRepository;
    private final GameService gameService;
    private final PlayerService playerService;
    private final WalletService walletService;

    @Override
    @Transactional
    public Bet placeBet(BetForm betForm) throws NotFoundException {
        var bet = toBet(betForm);
        var wallet = walletService.findById(bet.getPlayer().getWallet().getId());
        walletService.updateBalance(wallet, betForm.getBetAmount(), DEDUCT_AMOUNT);
        return betRepository.save(bet);
    }

    private Bet toBet(BetForm betForm) throws NotFoundException {
        var game = gameService.findById(betForm.getGameId());
        var player = playerService.findByUserName(betForm.getPlayerUserName());
        return Bet.builder()
                .game(game)
                .player(player)
                .build();
    }

    // ENDPOINT:  To retrieve the results of their bets (betHistory).

}
