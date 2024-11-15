package br.com.pf.api.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class GlobalControllerAdvice {

    @Autowired
    private ObjectMapper objectMapper;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorInfo> handleGenericException(GenericException genericException) {
        return ResponseEntity
                .status(genericException.getHttpStatus().value())
                .body(ErrorInfo
                        .builder()
                        .httpStatus(genericException.getHttpStatus())
                        .dateTime(OffsetDateTime.now())
                        .message(genericException.getMessage())
                        .build());
    }
}
