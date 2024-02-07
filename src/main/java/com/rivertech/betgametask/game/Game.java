package com.rivertech.betgametask.game;

import lombok.Data;
import lombok.Builder;
import java.util.List;
import java.io.Serial;
import java.util.Random;
import java.time.Instant;
import java.io.Serializable;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import com.rivertech.betgametask.bet.Bet;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.ToString;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Game to bet on")
public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 6616075746479326243L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @JsonIgnore
    @ToString.Exclude
    @Schema(description = "Bets related to a game")
    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Bet> bets;

    @Schema(description = "Time when the game was played")
    private Instant executedAt;

    private Integer gameResult;

    public Game play() {
        Random rand = new Random();
        this.setGameResult(rand.nextInt(1,11));
        this.setExecutedAt(Instant.now());
    return this;
    }
}
