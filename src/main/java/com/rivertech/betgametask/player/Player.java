package com.rivertech.betgametask.player;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rivertech.betgametask.wallet.WalletHistory;
import com.rivertech.betgametask.wallet.service.WalletJpaListener;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import lombok.Builder;

import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.OneToOne;


import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import com.rivertech.betgametask.bet.Bet;
import com.rivertech.betgametask.wallet.Wallet;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Entity
//@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User of the game application")
@EntityListeners(WalletJpaListener.class)
public class Player  implements Serializable {

    @Serial
    private static final long serialVersionUID = -3095287111893006464L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String surname;

    private String userName;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
    @Schema(description = "Bets that belongs to / made by  the player", nullable = true)
    @JsonIgnore
    private List<Bet> bets;

    @OneToOne(cascade = CascadeType.ALL)
    @Schema(description = "Player's balance")
    @JsonIgnore
    private Wallet wallet;

    public Player(String name, String surname, String userName, Wallet wallet) {
        this.name = name;
        this.surname = surname;
        this.userName = userName;
        this.wallet = wallet;
    }

//    @OneToMany(mappedBy = "player")
//    @JsonIgnore
//    private Set<WalletHistory> jobHistory;


}
