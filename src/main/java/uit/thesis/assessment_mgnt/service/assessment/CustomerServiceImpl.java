package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.customer.CreateCustomerDto;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.repository.assessment.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Long> implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;

    @Override
    public Customer addNewCustomer(CreateCustomerDto dto) {
        Customer customer = new Customer();
        customer = modelMapper.map(dto, Customer.class);
        return customerRepository.save(customer);
    }
}
