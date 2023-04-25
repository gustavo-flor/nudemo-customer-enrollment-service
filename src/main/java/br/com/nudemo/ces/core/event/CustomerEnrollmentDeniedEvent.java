package br.com.nudemo.ces.core.event;

import br.com.nudemo.ces.core.domain.customer.enrollment.CustomerEnrollment;
import br.com.nudemo.ces.core.message.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class CustomerEnrollmentDeniedEvent implements Message {

    public static final String NAME = "CustomerEnrollmentDeniedEvent";

    private final CustomerEnrollment customerEnrollment;
    private final LocalDateTime occurredAt;

    public static CustomerEnrollmentDeniedEvent of(final CustomerEnrollment customerEnrollment) {
        if (!customerEnrollment.isDenied()) {
            throw new IllegalStateException();
        }
        return new CustomerEnrollmentDeniedEvent(customerEnrollment, now());
    }

    @Override
    public String key() {
        return getCustomerEnrollment().getPersonalData().cpf();
    }

    @Override
    public String target() {
        return NAME;
    }

    @Override
    public Object value() {
        return new Source(getCustomerEnrollment(), getOccurredAt());
    }

    private record Source(CustomerEnrollment customerEnrollment, LocalDateTime occurredAt) {

    }

}
