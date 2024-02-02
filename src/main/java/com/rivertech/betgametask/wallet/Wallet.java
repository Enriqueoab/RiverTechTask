package com.rivertech.betgametask.wallet;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import com.rivertech.betgametask.player.Player;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Player balance to bet with")
public class Wallet {

    private final static Long REGISTRATION_INITIAL_BALANCE = 1000L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Schema(description = "Credit amount available")
    @Builder.Default
    private Long balance = REGISTRATION_INITIAL_BALANCE;

    @OneToOne
    @Schema(description = "Player's wallet")
    private Player player;

}
