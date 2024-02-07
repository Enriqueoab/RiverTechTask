package com.rivertech.betgametask.bet;

import lombok.Data;
import lombok.Setter;
import java.io.Serial;
import lombok.Builder;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import java.time.Instant;
import java.io.Serializable;
import java.util.List;
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

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bet implements Serializable {

    @Serial
    private static final long serialVersionUID = 5572813763057549987L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long betAmount;

    private Long wonAmount;

    private int betNum;

    @ManyToOne
    @JsonIgnore
    @Schema(description = "Game related to a specific bet")
    private Game game;

    @ManyToOne
    @Schema(description = "Player that made the bet")
    private Player player;

    @Schema(description = "The time the bet was made")
    private Instant placedAt;

    @Schema(description = "Bet result, calculated by how close the bet against the game result was",
            nullable = true, example = "SECOND_PRICE", allowableValues = {"FIRST_PRICE", "SECOND_PRICE", "THIRD_PRICE", "LOST"})
    private BetResult betResult;

    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "bet", cascade = CascadeType.ALL)
    private List<BetHistory> betHistory;

}
