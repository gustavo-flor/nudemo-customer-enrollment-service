package br.com.nudemo.ces.core.alert;

import br.com.nudemo.ces.core.message.Message;

import java.util.Map;

public record MessageNotSentAlert(Message message) implements Alert {

    @Override
    public Map<String, Object> properties() {
        return Map.of(
                "target", message().target(),
                "key", message().key(),
                "value", message().value(),
                "headers", message().headers()
        );
    }

}
