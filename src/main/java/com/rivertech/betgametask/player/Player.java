package com.rivertech.betgametask.player;

import lombok.Data;

import java.util.List;
import java.util.Set;
import lombok.Builder;
import javax.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.OneToOne;
import jakarta.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.wallet.Wallet;
import io.swagger.v3.oas.annotations.media.Schema;

@Data // Lombok automatically generates common methods such as toString, equals, hashCode, and getters/setters
@Entity // It indicates that the class is a JPA entity, representing a table in a relational database.
@Builder
@NoArgsConstructor // Class 'Player' should have [public, protected] no-arg constructor 
@AllArgsConstructor // Builder need a proper constructor
@Schema(description = "User of the game application")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    private String userName;

    @OneToMany(mappedBy = "player")
    @Schema(description = "Bets that belongs to / made by  the player", nullable = true)
    private List<Bet> bets;

    @OneToOne(mappedBy = "player")
    @Schema(description = "Player's balance")
    private Wallet wallet;


}
