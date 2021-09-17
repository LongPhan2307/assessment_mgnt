package uit.thesis.assessment_mgnt.service.human_resource;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.human_resource.CreateDaysWorkingDto;
import uit.thesis.assessment_mgnt.model.human_resource.DaysWorking;

import java.util.List;

public interface DaysWorkingService extends GenericService<DaysWorking, Long> {
    DaysWorking save(CreateDaysWorkingDto dto) throws Exception;

    DaysWorking update(CreateDaysWorkingDto dto, String code) throws Exception;

    List<DaysWorking> getAllDaysWorkingByEmployeeId(String employeeId);

    List<DaysWorking> getAll();
}
