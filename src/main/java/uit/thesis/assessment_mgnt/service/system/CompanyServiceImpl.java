package uit.thesis.assessment_mgnt.service.system;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.system.CreateCompanyDto;
import uit.thesis.assessment_mgnt.dto.system.UpdateCompanyDto;
import uit.thesis.assessment_mgnt.model.system.Company;
import uit.thesis.assessment_mgnt.repository.system.CompanyRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class CompanyServiceImpl extends GenericServiceImpl<Company, Long> implements CompanyService {
    private CompanyRepository companyRepository;
    private ModelMapper modelMapper;

    @Override
    public Company save(CreateCompanyDto dto) {
        Company company = this.convertDtoToEntity(dto);
        return companyRepository.save(company);
    }

    @Override
    public Company update(UpdateCompanyDto dto, String code) {
        Company company = companyRepository.findByCode(code);
        if(company == null)
            return null;
        company.setName(dto.getName());
        company.setAddress(dto.getAddress());
        return companyRepository.save(company);
    }

    @Override
    public Company mockupCompanyData() {
        Company company = new Company("Công ty TNHH giám định Bảo Định (BADINCO)"
                , "172020"
                , "P.14 T.2 Tòa Thành Đạt, 03 Lê Thánh Tông, Ngô Quyền,Hải Phòng");
        return companyRepository.save(company);
    }

    @Override
    public boolean deleteByCode(String code) {
        Company company = companyRepository.findByCode(code);
        if(company == null)
            return false;
        companyRepository.deleteByCode(code);
        return true;
    }

    private Company convertDtoToEntity(CreateCompanyDto dto){
        Company company = new Company();
        company = modelMapper.map(dto, Company.class);
        if(dto.getHeadquaterCode() == null || dto.getHeadquaterCode() == ""){
            company.setHeadquater(null);
        } else {
            Company branch = companyRepository.findByCode(dto.getHeadquaterCode());
            if(branch == null){
                company.setHeadquater(null);
            } else {
                company.setHeadquater(branch);
            }
        }

        return company;
    }
}
