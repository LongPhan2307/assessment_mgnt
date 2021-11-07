package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.customer.CreateCustomerDto;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.repository.assessment.CustomerRepository;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<Customer> mockupData() {
        List<Customer> list = new LinkedList<>();
        list.add(new Customer("TNHH0001", "CÔNG TY CỔ PHẦN THƯƠNG MẠI VÀ DỊCH VỤ TIẾN THÀNH"
                , "TIEN THANH TRADING AND SERVICE JOINT STOCK COMPANY",
                "122 Khuat Duy Tien - Nhan Chinh - Thanh Xuan - Hanoi",
                "024 73055586"));
        return customerRepository.saveAll(list);
    }
}
