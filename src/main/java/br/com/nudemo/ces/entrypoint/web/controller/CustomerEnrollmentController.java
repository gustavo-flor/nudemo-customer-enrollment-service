package br.com.nudemo.ces.entrypoint.web.controller;

import br.com.nudemo.ces.core.domain.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.PersonalData;
import br.com.nudemo.ces.core.usecase.CreateCustomerEnrollmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/customer-enrollments")
@RequiredArgsConstructor
public class CustomerEnrollmentController {

    private final CreateCustomerEnrollmentUseCase createCustomerEnrollmentUseCase;

    @PostMapping
    @ResponseStatus(CREATED)
    public CustomerEnrollment create(@RequestBody PersonalData personalData) {
        final var input = new CreateCustomerEnrollmentUseCase.Input(personalData);
        return createCustomerEnrollmentUseCase.execute(input).customerEnrollment();
    }

}
