package br.com.nudemo.ces.core.exception;

public class IntegrationException extends RuntimeException {

    public IntegrationException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
