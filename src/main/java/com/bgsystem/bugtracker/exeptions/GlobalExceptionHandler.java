package com.bgsystem.bugtracker.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request){
        //String errorMessage = "You do not have the required permissions to perform this action";

        ErrorResponseBody errorResponseBody = ErrorResponseBody.builder()
                .message("You do not have the required permissions to perform this action")
                .error("Forbidden")
                .path(request.getRequestURI())
                .status(HttpStatus.FORBIDDEN.value())
                .timestamp(LocalDateTime.now())
                .trace(e.getLocalizedMessage())
                .build();

        return new ResponseEntity<>(errorResponseBody, HttpStatus.FORBIDDEN);
    }

}
