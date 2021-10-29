package ru.trofimov.commission.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(InvalidRequestBodyException.class)
    ResponseEntity<BaseException> invalidPhoneNumber (HttpServletRequest request, InvalidRequestBodyException e) {
        HttpStatus status = HttpStatus.FORBIDDEN;

        BaseException exception = new BaseException(
                status,
                e.getMessage(),
                "invalid phone number",
                request.getRequestURI());
        return new ResponseEntity<>(exception, status);
    }
}
