package br.com.nudemo.ces.dataprovider.mongo.repository;

import br.com.nudemo.ces.core.domain.Status;
import br.com.nudemo.ces.dataprovider.mongo.entity.CustomerEnrollmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerEnrollmentEntityRepository extends MongoRepository<CustomerEnrollmentEntity, String> {

    boolean existsByPersonalDataCpfAndStatus(String cpf, Status status);

}
