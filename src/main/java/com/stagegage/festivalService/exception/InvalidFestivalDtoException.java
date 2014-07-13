package com.stagegage.festivalService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Scott on 7/12/14.
 *
 * @author Scott Hendrickson
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidFestivalDtoException extends RuntimeException {
    public InvalidFestivalDtoException(String s) {
        super(s);
    }
}
