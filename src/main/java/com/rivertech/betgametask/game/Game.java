package com.rivertech.betgametask.game;

import com.rivertech.betgametask.bet.Bet;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
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
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Table(name = "game")
@Schema(description = "Game to bet on")
public class Game implements Serializable {

    @Serial
    private static final long serialVersionUID = 6616075746479326243L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

//    @JoinTable(name = "bet", joinColumns = @JoinColumn(name = "game_id"))
//    @ToString.Exclude
    @Schema(description = "Bets related to a game")
    @OneToMany(mappedBy = "game", cascade = CascadeType.PERSIST)
    private List<Bet> bets;

    @Schema(description = "Time when the game was played")
    private Instant executedAt;

    private Integer gameResult;

    public Game play(Game game) {
        Random rand = new Random();
        game.setGameResult(rand.nextInt(1,11));
        game.setExecutedAt(Instant.now());
    return game;
    }
}
