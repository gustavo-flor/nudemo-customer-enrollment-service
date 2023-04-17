package br.com.nudemo.ces.dataprovider.kafka;

import br.com.nudemo.ces.core.alert.MessageNotSentAlert;
import br.com.nudemo.ces.core.exception.MessageDeliveryException;
import br.com.nudemo.ces.core.message.Message;
import br.com.nudemo.ces.core.service.AlertService;
import br.com.nudemo.ces.core.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaMessageService implements MessageService {

    private static final String TARGET_SUFFIX = "-out-0";

    private final StreamBridge streamBridge;
    private final AlertService alertService;

    @Override
    public void send(final Message message) {
        final var data = MessageBuilder.withPayload(message.value())
                .copyHeaders(message.headers())
                .setHeader(KafkaHeaders.KEY, message.key().getBytes())
                .setHeader(KafkaHeaders.TIMESTAMP, System.currentTimeMillis())
                .build();
        final var bindingName = message.target() + TARGET_SUFFIX;
        final var sent = streamBridge.send(bindingName, data);
        if (!sent) {
            final var alert = new MessageNotSentAlert(message);
            alertService.dispatch(alert);
            throw new MessageDeliveryException(message);
        }
    }

}
