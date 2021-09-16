package uit.thesis.assessment_mgnt.service.system;

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

import javax.transaction.Transactional;

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
}
