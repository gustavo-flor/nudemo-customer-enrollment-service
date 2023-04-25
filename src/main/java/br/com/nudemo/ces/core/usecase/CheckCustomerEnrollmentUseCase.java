package br.com.nudemo.ces.core.usecase;

import br.com.nudemo.ces.core.domain.customer.enrollment.CustomerEnrollment;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CheckCustomerEnrollmentUseCase {

    record Input(@NotBlank String customerEnrollmentId) {

    }

    record Output(CustomerEnrollment customerEnrollment) {

    }

    Output execute(@Valid Input input);

}
