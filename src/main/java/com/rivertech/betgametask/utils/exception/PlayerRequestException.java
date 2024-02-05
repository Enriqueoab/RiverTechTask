package com.rivertech.betgametask.utils.exception;

import java.io.Serial;

public class PlayerRequestException extends RiverTechGameException {

    @Serial
    private static final long serialVersionUID = -1937502271770395826L;

    public PlayerRequestException(String message) {
        super(message);
    }
}
