package br.com.nudemo.ces.core.usecase.impl;

import br.com.nudemo.ces.core.event.CustomerEnrollmentApprovedEvent;
import br.com.nudemo.ces.core.event.CustomerEnrollmentDeniedEvent;
import br.com.nudemo.ces.core.mapping.UseCase;
import br.com.nudemo.ces.core.service.CustomerEnrollmentService;
import br.com.nudemo.ces.core.service.MessageService;
import br.com.nudemo.ces.core.usecase.CheckCustomerEnrollmentUseCase;
import lombok.RequiredArgsConstructor;

import static br.com.nudemo.ces.core.domain.Status.APPROVED;

@UseCase
@RequiredArgsConstructor
public class CheckCustomerEnrollmentUseCaseImpl implements CheckCustomerEnrollmentUseCase {

    private static final String APPROVE_MESSAGE = "Successful check";
    private static final String DENY_MESSAGE = "Already exists a enrollment approved for that client";

    private final CustomerEnrollmentService customerEnrollmentService;
    private final MessageService messageService;

    @Override
    public Output execute(final Input input) {
        final var customerEnrollment = customerEnrollmentService.findById(input.customerEnrollmentId()).orElseThrow();
        if (customerEnrollment.isNotRegistered()) {
            return new Output(customerEnrollment);
        }
        final var cpf = customerEnrollment.getPersonalData().cpf();
        if (customerEnrollmentService.existsByCpfAndStatus(cpf, APPROVED)) {
            customerEnrollmentService.deny(customerEnrollment, DENY_MESSAGE);
            messageService.send(CustomerEnrollmentDeniedEvent.of(customerEnrollment));
            return new Output(customerEnrollment);
        }
        customerEnrollmentService.approve(customerEnrollment, APPROVE_MESSAGE);
        messageService.send(CustomerEnrollmentApprovedEvent.of(customerEnrollment));
        return new Output(customerEnrollment);
    }

}
