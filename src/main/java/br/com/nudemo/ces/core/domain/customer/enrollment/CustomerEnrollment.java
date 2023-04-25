package br.com.nudemo.ces.core.domain.customer.enrollment;

import br.com.nudemo.ces.core.domain.person.PersonalData;
import lombok.*;

import java.time.LocalDateTime;

import static br.com.nudemo.ces.core.domain.customer.enrollment.Status.*;
import static java.time.LocalDateTime.now;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEnrollment {

    public static CustomerEnrollment of(final PersonalData personalData) {
        final var now = now();
        return CustomerEnrollment.builder()
                .status(REGISTERED)
                .message("Waiting check status...")
                .personalData(personalData)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    private String id;
    private Status status;
    private String message;
    private PersonalData personalData;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiresOn;

    public boolean isRegistered() {
        return REGISTERED == getStatus();
    }

    public boolean isNotRegistered() {
        return !isRegistered();
    }

    public boolean isApproved() {
        return APPROVED == getStatus();
    }

    public boolean isDenied() {
        return DENIED == getStatus();
    }

    public boolean isDiscarded() {
        return DISCARDED == getStatus();
    }

}
