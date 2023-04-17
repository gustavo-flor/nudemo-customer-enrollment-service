package br.com.nudemo.ces.core.domain;

import br.com.nudemo.ces.common.validation.mapping.FullName;
import br.com.nudemo.ces.common.validation.mapping.LegalAge;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.time.Period;

import static br.com.nudemo.ces.common.util.StringUtil.removeNonDigits;
import static java.util.Objects.isNull;

public record PersonalData(@NotBlank @CPF String cpf,
                           @NotBlank @FullName String fullName,
                           @NotBlank @Email String email,
                           @NotNull @Past LocalDate birthDate) {

    @Override
    public String cpf() {
        return removeNonDigits(cpf);
    }

    @NotNull
    @LegalAge
    public Integer age() {
        if (isNull(birthDate)) {
            return null;
        }
        final var currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

}
