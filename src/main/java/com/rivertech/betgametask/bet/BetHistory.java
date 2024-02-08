package com.rivertech.betgametask.bet;

import lombok.Data;
import lombok.Builder;
import java.time.Instant;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Records about the bets, from creation to result")
public class BetHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @Schema(description = "Game related to a specific bet")
    private Game game;

    @ManyToOne
    @Schema(description = "Player that made the bet")
    private Player player;

    private int betNum;

    private Integer gameResult;

    private String playerUserName;

    @CreatedDate
    @Schema(description = "The time the bet was made")
    private Instant placedAt;

    @Schema(description = "Time when the game for the bet was played", nullable = true)
    private Instant executedAt;

    @Schema(description = "Bet result, calculated by how close the bet against the game result was",
            nullable = true, example = "SECOND_PRICE", allowableValues = {"FIRST_PRICE","SECOND_PRICE","THIRD_PRICE","LOST"})
    private String betResultMessage;

    @ManyToOne
    @JsonIgnore
    private Bet bet;

}
