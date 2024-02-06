package com.rivertech.betgametask.utils.exception;

import java.io.Serial;

public abstract class RiverTechGameException extends Exception {

    @Serial
    private static final long serialVersionUID = 4969688172982128054L;

    protected RiverTechGameException(String message){
        super(message);
    }

}
