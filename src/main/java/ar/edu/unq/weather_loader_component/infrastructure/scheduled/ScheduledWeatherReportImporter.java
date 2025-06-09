package ar.edu.unq.weather_loader_component.infrastructure.scheduled;

import ar.edu.unq.weather_loader_component.domain.port.in.ImportCurrentWeatherReportUseCasePort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledWeatherReportImporter {

    private final ImportCurrentWeatherReportUseCasePort importCurrentWeatherReportUseCasePort;

    public ScheduledWeatherReportImporter(ImportCurrentWeatherReportUseCasePort importCurrentWeatherReportUseCasePort) {
        this.importCurrentWeatherReportUseCasePort = importCurrentWeatherReportUseCasePort;
    }

    @Scheduled(cron = "${ar.edu.unq.weather.loader.component.import.cron}")
    public void importCurrentWeatherReport() {
        importCurrentWeatherReportUseCasePort.importCurrentWeatherReport();
    }
}
