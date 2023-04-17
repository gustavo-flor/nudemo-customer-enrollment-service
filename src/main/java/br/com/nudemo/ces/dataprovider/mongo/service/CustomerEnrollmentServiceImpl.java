package br.com.nudemo.ces.dataprovider.mongo.service;

import br.com.nudemo.ces.core.domain.CustomerEnrollment;
import br.com.nudemo.ces.core.domain.Status;
import br.com.nudemo.ces.core.service.CustomerEnrollmentService;
import br.com.nudemo.ces.dataprovider.mongo.entity.CustomerEnrollmentEntity;
import br.com.nudemo.ces.dataprovider.mongo.repository.CustomerEnrollmentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerEnrollmentServiceImpl implements CustomerEnrollmentService {

    private final CustomerEnrollmentEntityRepository customerEnrollmentEntityRepository;

    @Override
    public CustomerEnrollment save(final CustomerEnrollment customerEnrollment) {
        final var entity = CustomerEnrollmentEntity.of(customerEnrollment);
        return customerEnrollmentEntityRepository.save(entity).toDomain();
    }

    @Override
    public Optional<CustomerEnrollment> findById(final String id) {
        return customerEnrollmentEntityRepository.findById(id).map(CustomerEnrollmentEntity::toDomain);
    }

    @Override
    public boolean existsByCpfAndStatus(final String cpf, final Status status) {
        return customerEnrollmentEntityRepository.existsByPersonalDataCpfAndStatus(cpf, status);
    }

}
