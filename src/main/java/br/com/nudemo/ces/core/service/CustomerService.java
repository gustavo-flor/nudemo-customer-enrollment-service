package br.com.nudemo.ces.core.service;

import br.com.nudemo.ces.core.domain.customer.Customer;
import br.com.nudemo.ces.core.domain.person.PersonalData;

public interface CustomerService {

    Customer create(PersonalData personalData);

}
