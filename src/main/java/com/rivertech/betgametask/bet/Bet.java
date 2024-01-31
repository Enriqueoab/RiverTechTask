package com.rivertech.betgametask.bet;

import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @Schema(description = "Game related to a specific bet")
    private Game game;

    @ManyToOne
    @Schema(description = "Player that made the bet")
    private Player player;

    @CreatedDate
    @Schema(description = "The time the bet was made")
    private Instant placedAt;

    @Schema(description = "Time when the game for the bet was played", nullable = true)
    private Instant executedAt;

    @Schema(description = "Bet result, calculated by how close the bet against the game result was",
            nullable = true, example = "ADDED_BALANCE", allowableValues = {"ADDED_BALANCE","PLACED_BET","EARNED_BET"})
    private BetResult betResult;

}
