package com.rivertech.betgametask.bet;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(description = "Form to retrieve a player bets")
public class BetHistoryForm {

    @NotNull
    @Size(min = 3, max = 12)
    private String playerUserName;

    @Schema(description = "Filter to retrieve just the bets linked to games already played", nullable = true)
    private boolean justExecutedBets;

}
