package ar.edu.unq.weather_loader_component.infrastructure.web.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GenericErrorResponseDto {

    private String message;
}
