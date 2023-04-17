package br.com.nudemo.ces.entrypoint.worker.listener;

import br.com.nudemo.ces.core.event.CustomerEnrollmentRegisteredEvent;
import br.com.nudemo.ces.core.usecase.CheckCustomerEnrollmentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component(CustomerEnrollmentRegisteredEvent.NAME)
@RequiredArgsConstructor
public class CustomerEnrollmentRegisteredEventListener implements Consumer<CustomerEnrollmentRegisteredEvent> {

    private final CheckCustomerEnrollmentUseCase checkCustomerEnrollmentUseCase;

    @Override
    public void accept(final CustomerEnrollmentRegisteredEvent event) {
        final var customerEnrollment = event.getCustomerEnrollment();
        final var input = new CheckCustomerEnrollmentUseCase.Input(customerEnrollment.getId());
        try {
            checkCustomerEnrollmentUseCase.execute(input);
        } catch (RuntimeException e) {
            log.error("Something went wrong on check customer enrollment use case", e);
        }
    }

}
