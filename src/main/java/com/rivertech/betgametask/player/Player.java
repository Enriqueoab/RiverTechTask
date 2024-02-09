package com.rivertech.betgametask.player;

import lombok.Data;
import lombok.Setter;
import java.util.List;
import java.io.Serial;
import lombok.AccessLevel;
import java.io.Serializable;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import com.rivertech.betgametask.bet.Bet;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.rivertech.betgametask.wallet.Wallet;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User of the bet application")
public class Player implements Serializable {

    @Serial
    private static final long serialVersionUID = -3095287111893006464L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String userName;

    @JsonIgnore
    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @Schema(description = "Bets made by the player", nullable = true)
    private List<Bet> bets;

    @Setter(AccessLevel.NONE)
    @OneToOne(cascade = CascadeType.ALL)
    @Schema(description = "Player's balance")
    private Wallet wallet;

    public Player(String name, String surname, String userName) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.wallet =  new Wallet();
    }
}
