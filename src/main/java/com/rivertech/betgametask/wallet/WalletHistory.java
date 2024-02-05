package com.rivertech.betgametask.wallet;

import com.rivertech.betgametask.game.Game;
import com.rivertech.betgametask.player.Player;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Builder;
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

//    @Schema(description = "Credit amount available")
    private Long balance;

    private String name;

    private String surname;

    private String userName;

    private Long playerId;

//    private int gameId;

    private int walletId;

    @Schema(description = "Kind of transaction", example = "BALANCE_ADDED", allowableValues = {"BALANCE_ADDED, BET_PLACED, BET_EARNED"})
    private TransactionType  transactionType;

    public WalletHistory(Player player, TransactionType transactionType) {
        this.balance = player.getWallet().getBalance();
        this.name = player.getName();
        this.surname = player.getSurname();
        this.userName = player.getUserName();
        this.playerId = player.getId();
//        this.gameId = gameId;
        this.walletId = player.getWallet().getId();
        this.transactionType = transactionType;
    }
}
