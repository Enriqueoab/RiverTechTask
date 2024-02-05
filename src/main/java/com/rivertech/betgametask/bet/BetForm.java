package com.rivertech.betgametask.bet;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Builder;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
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
    private Long betAmount;

}
