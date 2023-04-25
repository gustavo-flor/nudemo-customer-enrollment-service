package br.com.nudemo.ces.core.usecase.impl;

import br.com.nudemo.ces.core.domain.customer.enrollment.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.person.PersonalData;
import br.com.nudemo.ces.core.event.CustomerEnrollmentRegisteredEvent;
import br.com.nudemo.ces.core.mapping.UseCase;
import br.com.nudemo.ces.core.service.CustomerEnrollmentService;
import br.com.nudemo.ces.core.service.MessageService;
import br.com.nudemo.ces.core.usecase.CreateCustomerEnrollmentUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@UseCase
@RequiredArgsConstructor
public class CreateCustomerEnrollmentUseCaseImpl implements CreateCustomerEnrollmentUseCase {

    private final CustomerEnrollmentService customerEnrollmentService;
    private final MessageService messageService;

    @Transactional
    @Override
    public Output execute(final Input input) {
        final var personalData = input.personalData();
        final var customerEnrollment = createCustomerEnrollment(personalData);
        sendCustomerEnrollmentRegisteredEvent(customerEnrollment);
        return new Output(customerEnrollment);
    }

    private CustomerEnrollment createCustomerEnrollment(final PersonalData personalData) {
        return customerEnrollmentService.save(CustomerEnrollment.of(personalData));
    }

    private void sendCustomerEnrollmentRegisteredEvent(final CustomerEnrollment customerEnrollment) {
        final var event = CustomerEnrollmentRegisteredEvent.of(customerEnrollment);
        messageService.send(event);
    }

}
