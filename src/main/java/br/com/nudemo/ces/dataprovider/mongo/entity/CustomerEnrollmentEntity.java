package br.com.nudemo.ces.dataprovider.mongo.entity;

import br.com.nudemo.ces.core.domain.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.PersonalData;
import br.com.nudemo.ces.core.domain.Status;
import lombok.*;
import org.springframework.data.annotation.Id;
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
                .build();
    }

    @Id
    private String id;
    private Status status;
    private PersonalData personalData;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerEnrollment toDomain() {
        return CustomerEnrollment.builder()
                .id(getId())
                .status(getStatus())
                .personalData(getPersonalData())
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }

}