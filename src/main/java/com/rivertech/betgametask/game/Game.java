package com.rivertech.betgametask.game;

import com.rivertech.betgametask.bet.Bet;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "game")
@Schema(description = "Game to bet on")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;

    @Schema(description = "Bets related to a game")
    @OneToMany
    private List<Bet> bets;

    @Schema(description = "Time when the game was played")
    private Instant executedAt;

    private Integer gameResult;

}
