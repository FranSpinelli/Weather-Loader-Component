package ar.edu.unq.weather_loader_component.infrastructure.web.in;

import ar.edu.unq.weather_loader_component.application.exceptions.InformationNotAvailableException;
import ar.edu.unq.weather_loader_component.application.exceptions.RateLimiterException;
import ar.edu.unq.weather_loader_component.infrastructure.web.in.dto.GenericErrorResponseDto;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GenericErrorResponseDto> handleValidationExceptions(MethodArgumentTypeMismatchException ex) {
        String errors = String.format(
                "Invalid value '%s' for parameter '%s'. Expected type: %s",
                ex.getValue(),
                ex.getName(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown"
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericErrorResponseDto(errors));
    }

    @ExceptionHandler
    public ResponseEntity<GenericErrorResponseDto> handleInformationNotAvailableException(InformationNotAvailableException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericErrorResponseDto(exception.getMessage()));
    }

    @ExceptionHandler(RateLimiterException.class)
    public ResponseEntity<GenericErrorResponseDto> handleRateLimiterException(RateLimiterException exception) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(new GenericErrorResponseDto(exception.getMessage()));
    }

}
