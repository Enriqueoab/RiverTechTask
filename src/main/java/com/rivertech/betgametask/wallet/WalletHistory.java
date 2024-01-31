package com.rivertech.betgametask.wallet;

import lombok.Data;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false) // the methods, Equals And HashCode, will only consider the fields in the current class.
@Schema(description = "Record of the wallet in and out transactions")
public class WalletHistory extends Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "Kind of transaction", example = "ADDED_BALANCE", allowableValues = {"ADDED_BALANCE","PLACED_BET","EARNED_BET"})
    private TransactionType  transactionType;

}
