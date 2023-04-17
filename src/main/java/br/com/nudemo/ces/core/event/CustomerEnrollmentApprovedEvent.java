package br.com.nudemo.ces.core.event;

import br.com.nudemo.ces.core.domain.CustomerEnrollment;
import br.com.nudemo.ces.core.message.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static lombok.AccessLevel.PRIVATE;

@Getter
@RequiredArgsConstructor(access = PRIVATE)
public class CustomerEnrollmentApprovedEvent implements Message {

    public static final String NAME = "CustomerEnrollmentApprovedEvent";

    private final CustomerEnrollment customerEnrollment;
    private final LocalDateTime occurredAt;

    public static CustomerEnrollmentApprovedEvent of(final CustomerEnrollment customerEnrollment) {
        if (!customerEnrollment.isApproved()) {
            throw new IllegalStateException();
        }
        return new CustomerEnrollmentApprovedEvent(customerEnrollment, now());
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
