package ar.edu.unq.weather_loader_component.domain.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class WindData {

    private Double windSpeed;
    private Double directionDegrees;
    private Double windGustsSpeed;
}
