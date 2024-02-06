package com.rivertech.betgametask.utils.exception;

import java.io.Serial;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PAYMENT_REQUIRED)
public class WalletRequestException extends RiverTechGameException {

    @Serial
    private static final long serialVersionUID = 7332267373216143936L;

    public WalletRequestException(String message) {
        super(message);
    }
}
