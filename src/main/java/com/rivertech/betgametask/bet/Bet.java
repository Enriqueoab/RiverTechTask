package com.rivertech.betgametask.bet;

import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.Instant;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Game related to a specific bet")
    @ManyToOne
    private Game game;

//    @JoinColumn(name = "player_id", nullable = false)
    @Schema(description = "Player that made the bet")
    @ManyToOne
    private Player player;

    @CreatedDate
    @Schema(description = "The time the bet was made")
    private Instant placedAt;

    @Schema(description = "Time when the game for the bet was played", nullable = true)
    private Instant executedAt;

    @Schema(description = "Bet result, calculated by how close the bet against the game result was",
            nullable = true, example = "SECOND_PRICE", allowableValues = {"FIRST_PRICE", "SECOND_PRICE", "THIRD_PRICE", "LOST"})
    private BetResult betResult;

}
