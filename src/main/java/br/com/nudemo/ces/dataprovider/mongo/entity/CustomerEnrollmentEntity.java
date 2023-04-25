package br.com.nudemo.ces.dataprovider.mongo.entity;

import br.com.nudemo.ces.core.domain.customer.enrollment.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.person.PersonalData;
import br.com.nudemo.ces.core.domain.customer.enrollment.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "customer_enrollment")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEnrollmentEntity {

    public static CustomerEnrollmentEntity of(final CustomerEnrollment customerEnrollment) {
        return CustomerEnrollmentEntity.builder()
                .id(customerEnrollment.getId())
                .status(customerEnrollment.getStatus())
                .personalData(customerEnrollment.getPersonalData())
                .createdAt(customerEnrollment.getCreatedAt())
                .updatedAt(customerEnrollment.getUpdatedAt())
                .expiresOn(customerEnrollment.getExpiresOn())
                .build();
    }

    @Id
    private String id;
    private Status status;
    private PersonalData personalData;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Indexed(name = "expiresOnStatusDiscarded", partialFilter = "{ 'status': 'DISCARDED' }", expireAfterSeconds = 1)
    private LocalDateTime expiresOn;

    public CustomerEnrollment toDomain() {
        return CustomerEnrollment.builder()
                .id(getId())
                .status(getStatus())
                .personalData(getPersonalData())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .expiresOn(getExpiresOn())
                .build();
    }

}
