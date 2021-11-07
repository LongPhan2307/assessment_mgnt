package uit.thesis.assessment_mgnt.service.system;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.system.CreateDepartmentDto;
import uit.thesis.assessment_mgnt.model.system.Company;
import uit.thesis.assessment_mgnt.model.system.Department;
import uit.thesis.assessment_mgnt.repository.system.CompanyRepository;
import uit.thesis.assessment_mgnt.repository.system.DepartmentRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.role.RoleName;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class DepartmentServiceImpl extends GenericServiceImpl<Department, Long> implements DepartmentService {
    private DepartmentRepository departmentRepository;
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    @Override
    public Department save(CreateDepartmentDto dto) throws Exception {
        Department department = new Department();
        Company company = companyRepository.findByCode(dto.getCompanyCode());
        if(company == null)
            throw new Exception(ResponseMessage.UN_KNOWN("Company Code"));
        department = modelMapper.map(dto, Department.class);
        department.setCompany(company);
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> mockupData() throws NotFoundException {
        Company company = companyRepository.findByCode("172020");
        if(company == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Company "));
        Department department1 = new Department("IT", "", company);
        Department department2 = new Department(RoleName.ACCOUNTANT.toString(), "", company);
        Department department3 = new Department(RoleName.DIRECTOR.toString(), "", company);
        Department department4 = new Department("ASSESSMENT", "", company);
        List<Department> list = new LinkedList<>();
        list.add(department1);
        list.add(department2);
        list.add(department3);
        list.add(department4);
        return departmentRepository.saveAll(list);
    }
}
