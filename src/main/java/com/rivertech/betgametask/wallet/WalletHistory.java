package com.rivertech.betgametask.wallet;

import com.rivertech.betgametask.player.Player;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) // the methods, Equals And HashCode, will only consider the fields in the current class.
@Schema(description = "Record of the wallet in and out transactions")
public class WalletHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Schema(description = "Credit amount available")
    private Long balance;

    @OneToOne
    @Schema(description = "Player's wallet")
    private Player player;

    @Schema(description = "Kind of transaction", example = "ADDED_BALANCE", allowableValues = {"ADDED_BALANCE","PLACED_BET","EARNED_BET"})
    private TransactionType  transactionType;

}
