package com.rivertech.betgametask.bet;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Form to place a bet")
public class BetForm {

    @NotNull
    @Size(min = 3, max = 12)
    private String playerUserName;

    @NotNull
    @DecimalMin(value = "1")
    @DecimalMax(value = "10")
    private int betNum;

    @NotNull
    private Long gameId;

    @NotNull
    private Long betAmount;

    public BetForm(String playerUserName, int betNum, Long gameId, Long betAmount) {
        this.playerUserName = playerUserName;
        this.betNum = betNum;
        this.gameId = gameId;
        this.betAmount = betAmount;
    }
}
