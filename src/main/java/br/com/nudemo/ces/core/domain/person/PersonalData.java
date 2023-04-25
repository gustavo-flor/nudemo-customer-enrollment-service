package br.com.nudemo.ces.core.domain.person;

import br.com.nudemo.ces.common.validation.mapping.FullName;
import br.com.nudemo.ces.common.validation.mapping.LegalBirthDate;
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
                           @NotNull @LegalBirthDate LocalDate birthDate) {

    @Override
    public String cpf() {
        return removeNonDigits(cpf);
    }

}
