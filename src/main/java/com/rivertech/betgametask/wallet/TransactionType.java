package com.rivertech.betgametask.wallet;

public enum TransactionType {
    BALANCE_ADDED("BALANCE_ADDED_NEW_WALLET"), BET_PLACED("BET_PLACED"), BET_WON("BET_WON");

    public final String action;

    TransactionType(String action) {
        this.action = action;
    }
}
