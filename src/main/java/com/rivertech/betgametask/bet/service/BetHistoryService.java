package com.rivertech.betgametask.bet.service;

import java.util.List;

import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;

public interface BetHistoryService {

    void generateBetHistoryRecords(List<Bet> bets);
}
