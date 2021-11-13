package uit.thesis.assessment_mgnt.controller.assessment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.dto.assessment.customer.CreateCustomerDto;
import uit.thesis.assessment_mgnt.model.assessment.Customer;
import uit.thesis.assessment_mgnt.service.assessment.CustomerService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.Domain;
import uit.thesis.assessment_mgnt.utils.domain.assessment.CustomerDomain;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Domain.API)
public class CustomerController {
    private CustomerService customerService;

    @GetMapping(CustomerDomain.CUSTOMER_DOMAIN)
    public ResponseEntity<Object> findAll(){
        List<Customer> list = customerService.findAll();
        if(list.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(list, HttpStatus.OK);
    }

    @PostMapping(CustomerDomain.CUSTOMER_DOMAIN)
    public ResponseEntity<Object> addNewCustomer(@RequestBody CreateCustomerDto dto,
                                                 BindingResult errors){
        if(errors.hasErrors())
            return ResponseObject.getResponse(errors, HttpStatus.BAD_REQUEST);
        Customer customer = customerService.addNewCustomer(dto);
        return ResponseObject.getResponse(customer, HttpStatus.CREATED);
    }
}
