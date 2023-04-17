package br.com.nudemo.ces.dataprovider.console;

import br.com.nudemo.ces.core.alert.Alert;
import br.com.nudemo.ces.core.service.AlertService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsoleAlertService implements AlertService {

    private final ObjectMapper objectMapper;

    @Override
    public void dispatch(final Alert alert) {
        final var properties = getProperties(alert);
        log.warn("Alert name = {} with properties = {}", alert.name(), properties);
    }

    private String getProperties(final Alert alert) {
        try {
            return objectMapper.writeValueAsString(alert.properties());
        } catch (JsonProcessingException e) {
            return alert.properties().toString();
        }
    }

}
