package com.rivertech.betgametask.utils.exception;

import java.io.Serial;

public class NotFoundException extends RiverTechGameException {

    private static final long serialVersionUID = 1453316100622498951L;

    public NotFoundException(String message) {
        super(message);
    }
}
