package br.com.nudemo.ces.core.usecase.impl;

import br.com.nudemo.ces.core.domain.customer.Customer;
import br.com.nudemo.ces.core.domain.customer.enrollment.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.person.PersonalData;
import br.com.nudemo.ces.core.event.CustomerEnrollmentApprovedEvent;
import br.com.nudemo.ces.core.event.CustomerEnrollmentDiscardedEvent;
import br.com.nudemo.ces.core.exception.CustomerEnrollmentNotRegisteredException;
import br.com.nudemo.ces.core.exception.IntegrationException;
import br.com.nudemo.ces.core.exception.MessageDeliveryException;
import br.com.nudemo.ces.core.mapping.UseCase;
import br.com.nudemo.ces.core.service.CustomerEnrollmentService;
import br.com.nudemo.ces.core.service.CustomerService;
import br.com.nudemo.ces.core.service.MessageService;
import br.com.nudemo.ces.core.usecase.CheckCustomerEnrollmentUseCase;
import lombok.RequiredArgsConstructor;

import static br.com.nudemo.ces.core.domain.customer.enrollment.Status.APPROVED;

@UseCase
@RequiredArgsConstructor
public class CheckCustomerEnrollmentUseCaseImpl implements CheckCustomerEnrollmentUseCase {

    private static final String APPROVE_MESSAGE = "Successful check, customer created with ID = %s";
    private static final String ALREADY_APPROVED_MESSAGE = "Already exists a enrollment approved for that client";

    private final CustomerEnrollmentService customerEnrollmentService;
    private final MessageService messageService;
    private final CustomerService customerService;

    @Override
    public Output execute(final Input input) {
        final var customerEnrollment = customerEnrollmentService.findById(input.customerEnrollmentId()).orElseThrow();
        if (customerEnrollment.isNotRegistered()) {
            throw new CustomerEnrollmentNotRegisteredException();
        }
        final var cpf = customerEnrollment.getPersonalData().cpf();
        if (customerEnrollmentService.existsByCpfAndStatus(cpf, APPROVED)) {
            tryDiscard(customerEnrollment);
        } else {
            tryApprove(customerEnrollment);
        }
        return new Output(customerEnrollment);
    }

    private void tryDiscard(final CustomerEnrollment customerEnrollment) {
        try {
            customerEnrollmentService.discard(customerEnrollment, ALREADY_APPROVED_MESSAGE);
            messageService.send(CustomerEnrollmentDiscardedEvent.of(customerEnrollment));
        } catch (MessageDeliveryException exception) {
            handleException(customerEnrollment, exception);
        }
    }

    private void tryApprove(final CustomerEnrollment customerEnrollment) {
        final var personalData = customerEnrollment.getPersonalData();
        try {
            final var customer = tryCreateCustomer(personalData);
            customerEnrollmentService.approve(customerEnrollment, String.format(APPROVE_MESSAGE, customer.id()));
            messageService.send(CustomerEnrollmentApprovedEvent.of(customerEnrollment));
        } catch (IntegrationException | MessageDeliveryException exception) {
            handleException(customerEnrollment, exception);
        }
    }

    private Customer tryCreateCustomer(final PersonalData personalData) {
        try {
            return customerService.create(personalData);
        } catch (RuntimeException exception) {
            throw new IntegrationException(exception);
        }
    }

    private void handleException(final CustomerEnrollment customerEnrollment, final RuntimeException exception) {
        customerEnrollmentService.fail(customerEnrollment, exception.getMessage());
    }

}
