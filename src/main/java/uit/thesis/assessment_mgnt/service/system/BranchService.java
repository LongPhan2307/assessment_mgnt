package uit.thesis.assessment_mgnt.service.system;

import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.system.CreateBranchDto;
import uit.thesis.assessment_mgnt.model.system.Branch;

public interface BranchService extends GenericService<Branch, Long> {
    Branch save(CreateBranchDto branchDto);

    Branch update(CreateBranchDto branchDto, long id);
}
