package com.rivertech.betgametask.bet;

import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Records about the bets, from creation to result")
public class BetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Game related to a specific bet")
    @ManyToOne
    private Game game;

    @Schema(description = "Player that made the bet")
    @ManyToOne
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
