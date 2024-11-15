package br.com.pf.api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class ErrorInfo {

    private HttpStatus httpStatus;
    private OffsetDateTime dateTime;
    private String message;
}
