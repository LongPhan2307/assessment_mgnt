package uit.thesis.assessment_mgnt.service.system;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.system.CreateCompanyDto;
import uit.thesis.assessment_mgnt.dto.system.UpdateCompanyDto;
import uit.thesis.assessment_mgnt.model.system.Company;

public interface CompanyService extends GenericService<Company, Long> {
    Company save(CreateCompanyDto dto);

    Company update(UpdateCompanyDto dto, String code);

    Company mockupCompanyData();

    public boolean deleteByCode(String code);
}
