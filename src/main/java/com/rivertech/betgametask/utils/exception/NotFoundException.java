package com.rivertech.betgametask.utils.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NotFoundException extends RiverTechGameException {

    @Serial
    private static final long serialVersionUID = 1453316100622498951L;

    public NotFoundException(String message) {
        super(message);
    }
}
