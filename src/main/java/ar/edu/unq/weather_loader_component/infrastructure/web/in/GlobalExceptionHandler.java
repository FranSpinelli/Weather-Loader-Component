package ar.edu.unq.weather_loader_component.infrastructure.web.in;

import ar.edu.unq.weather_loader_component.application.exceptions.InformationNotAvailableException;
import ar.edu.unq.weather_loader_component.infrastructure.web.in.dto.GenericErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<GenericErrorResponseDto> handleInformationNotAvailableException(InformationNotAvailableException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GenericErrorResponseDto(exception.getMessage()));
    }
}
