package com.rivertech.betgametask.wallet;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Record of the wallet in and out transactions")
public class WalletHistory implements Serializable {

    @Serial
    private static final long serialVersionUID = -7477013461887820482L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private Long balanceBeforeTransaction;

    private Long balanceAfterTransaction;

    private int walletId;

    private String transactionType;

    public WalletHistory(Wallet wallet, Long balanceBefTrans, TransactionType transactionType) {
        this.walletId = wallet.getId();
        this.transactionType = transactionType.action;
        this.balanceBeforeTransaction = balanceBefTrans;
        this.balanceAfterTransaction = wallet.getBalance();
    }
}
