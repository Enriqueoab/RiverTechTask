package com.rivertech.betgametask.player;

import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
import java.util.Set;
import lombok.Builder;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.OneToOne;


import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.wallet.Wallet;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User of the game application")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String surname;

    private String userName;

    @OneToMany(mappedBy = "player")
    @Schema(description = "Bets that belongs to / made by  the player", nullable = true)
    private List<Bet> bets;

    @OneToOne
    @Schema(description = "Player's balance")
    private Wallet wallet;


}
