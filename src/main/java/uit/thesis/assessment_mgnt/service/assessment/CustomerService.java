package uit.thesis.assessment_mgnt.service.assessment;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.customer.CreateCustomerDto;
import uit.thesis.assessment_mgnt.model.assessment.Customer;

import java.util.List;

public interface CustomerService extends GenericService<Customer, Long> {

    Customer addNewCustomer(CreateCustomerDto dto);

    List<Customer> mockupData();
}
