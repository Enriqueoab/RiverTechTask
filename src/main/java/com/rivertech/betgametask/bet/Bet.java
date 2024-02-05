package com.rivertech.betgametask.bet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "")
public class Bet implements Serializable {

    @Serial
    private static final long serialVersionUID = 5572813763057549987L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long betAmount;

    private Long wonAmount;

    private int betNum;

    @ToString.Exclude
    @JsonIgnore
    @Schema(description = "Game related to a specific bet")
    @ManyToOne
    private Game game;

    @Schema(description = "Player that made the bet")
    @ManyToOne
    @ToString.Exclude
    private Player player;

    @Schema(description = "The time the bet was made")
    private Instant placedAt;

    @Schema(description = "Bet result, calculated by how close the bet against the game result was",
            nullable = true, example = "SECOND_PRICE", allowableValues = {"FIRST_PRICE", "SECOND_PRICE", "THIRD_PRICE", "LOST"})
    private BetResult betResult;

}
