package br.com.nudemo.ces.dataprovider.web.client;

import br.com.nudemo.ces.core.domain.customer.Customer;
import br.com.nudemo.ces.core.domain.person.PersonalData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CustomerServiceClient")
public interface CustomerServiceClient {

    @PostMapping("/v1/customers")
    Customer create(@RequestBody PersonalData personalData);

}
