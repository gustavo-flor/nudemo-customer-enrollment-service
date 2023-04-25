package br.com.nudemo.ces.core.service;

import br.com.nudemo.ces.core.domain.customer.enrollment.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.customer.enrollment.Status;

import java.util.Optional;

import static br.com.nudemo.ces.core.domain.customer.enrollment.Status.*;
import static java.time.LocalDateTime.now;

public interface CustomerEnrollmentService {

    CustomerEnrollment save(final CustomerEnrollment customerEnrollment);

    Optional<CustomerEnrollment> findById(final String id);

    boolean existsByCpfAndStatus(final String cpf, final Status status);

    default CustomerEnrollment update(final CustomerEnrollment customerEnrollment) {
        customerEnrollment.setUpdatedAt(now());
        return save(customerEnrollment);
    }

    default CustomerEnrollment fail(final CustomerEnrollment customerEnrollment, final String message) {
        customerEnrollment.setStatus(FAILED);
        customerEnrollment.setMessage(message);
        return update(customerEnrollment);
    }

    default CustomerEnrollment approve(final CustomerEnrollment customerEnrollment, final String message) {
        customerEnrollment.setStatus(APPROVED);
        customerEnrollment.setMessage(message);
        return update(customerEnrollment);
    }

    default CustomerEnrollment deny(final CustomerEnrollment customerEnrollment, final String message) {
        customerEnrollment.setStatus(DENIED);
        customerEnrollment.setMessage(message);
        return update(customerEnrollment);
    }

    default CustomerEnrollment discard(final CustomerEnrollment customerEnrollment, final String message) {
        customerEnrollment.setStatus(DISCARDED);
        customerEnrollment.setMessage(message);
        customerEnrollment.setExpiresOn(now().plusMinutes(5));
        return update(customerEnrollment);
    }

}
