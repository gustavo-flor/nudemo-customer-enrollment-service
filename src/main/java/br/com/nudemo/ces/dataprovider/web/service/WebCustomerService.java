package br.com.nudemo.ces.dataprovider.web.service;

import br.com.nudemo.ces.core.domain.customer.Customer;
import br.com.nudemo.ces.core.domain.person.PersonalData;
import br.com.nudemo.ces.core.service.CustomerService;
import br.com.nudemo.ces.dataprovider.web.client.CustomerServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WebCustomerService implements CustomerService {

    private final CustomerServiceClient customerServiceClient;

    @Override
    public Customer create(PersonalData personalData) {
        return customerServiceClient.create(personalData);
    }

}
