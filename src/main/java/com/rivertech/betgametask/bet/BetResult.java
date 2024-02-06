package com.rivertech.betgametask.bet;

public enum BetResult {

    WIN_10X(0,10, "FIRST_PRICE"),
    WIN_5X(1, 5, "SECOND_PRICE"),
    WIN_HALF(2, 0.5, "THIRD_PRICE"),
    LOSE(3, 0, "LOST");

    public final int betNumDifference;
    public final double multiplier;
    public final String message;

    BetResult(int betNumDifference, double multiplier, String message) {
        this.betNumDifference = betNumDifference;
        this.multiplier = multiplier;
        this.message = message;
    }

    public Long calculatePrice(Long playerBetAmount) {
        return (long) (multiplier * playerBetAmount);
    }

}
