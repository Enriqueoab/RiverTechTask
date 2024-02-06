package com.rivertech.betgametask.utils.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class PlayerRequestException extends RiverTechGameException {

    @Serial
    private static final long serialVersionUID = -1937502271770395826L;

    public PlayerRequestException(String message) {
        super(message);
    }
}
