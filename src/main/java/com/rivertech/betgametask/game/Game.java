package com.rivertech.betgametask.game;

import com.rivertech.betgametask.bet.Bet;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Game to bet on")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Bets related to a game")
    @OneToMany(mappedBy = "game")
    private List<Bet> bets;

    @Schema(description = "Time when the game was played")
    private Instant executedAt;

    private int gameResult;

}
