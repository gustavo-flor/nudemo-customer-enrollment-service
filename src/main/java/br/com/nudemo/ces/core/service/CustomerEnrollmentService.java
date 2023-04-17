package br.com.nudemo.ces.core.service;

import br.com.nudemo.ces.core.domain.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.Status;

import java.util.Optional;

import static br.com.nudemo.ces.core.domain.Status.APPROVED;
import static br.com.nudemo.ces.core.domain.Status.DENIED;
import static java.time.LocalDateTime.now;

public interface CustomerEnrollmentService {

    CustomerEnrollment save(final CustomerEnrollment customerEnrollment);

    Optional<CustomerEnrollment> findById(final String id);

    boolean existsByCpfAndStatus(final String cpf, final Status status);

    default CustomerEnrollment update(final CustomerEnrollment customerEnrollment) {
        customerEnrollment.setUpdatedAt(now());
        return save(customerEnrollment);
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

}
