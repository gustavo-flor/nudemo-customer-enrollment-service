package br.com.nudemo.ces.core.exception;

import br.com.nudemo.ces.core.message.Message;
import lombok.Getter;

@Getter
public class MessageDeliveryException extends RuntimeException {

    private final Message undeliveredMessage;

    public MessageDeliveryException(final Message undeliveredMessage) {
        super();
        this.undeliveredMessage = undeliveredMessage;
    }

}
