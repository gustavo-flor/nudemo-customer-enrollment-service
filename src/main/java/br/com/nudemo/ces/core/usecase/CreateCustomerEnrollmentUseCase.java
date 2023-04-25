package br.com.nudemo.ces.core.usecase;

import br.com.nudemo.ces.core.domain.customer.enrollment.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.person.PersonalData;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CreateCustomerEnrollmentUseCase {

    record Input(@NotNull @Valid PersonalData personalData) {

    }

    record Output(CustomerEnrollment customerEnrollment) {

    }

    Output execute(@Valid Input input);

}
