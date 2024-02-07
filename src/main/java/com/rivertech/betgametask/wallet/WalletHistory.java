package com.rivertech.betgametask.wallet;

import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private String transactionType;

    @ManyToOne
    @JsonIgnore
    private Wallet wallet;

    public WalletHistory(Wallet wallet, Long balanceBefTrans, TransactionType transactionType) {
        this.wallet = wallet;
        this.transactionType = transactionType.action;
        this.balanceBeforeTransaction = balanceBefTrans;
        this.balanceAfterTransaction = wallet.getBalance();
    }

    public WalletHistory(Wallet wallet, TransactionType transactionType) {
        this.wallet = wallet;
        this.transactionType = transactionType.action;
        this.balanceBeforeTransaction = 0L;
        this.balanceAfterTransaction = wallet.getBalance();
    }
}
