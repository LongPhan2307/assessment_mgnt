package uit.thesis.assessment_mgnt.service.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.system.CreateBranchDto;
import uit.thesis.assessment_mgnt.model.system.Branch;
import uit.thesis.assessment_mgnt.repository.system.BranchRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Builder
public class BranchServiceImpl extends GenericServiceImpl<Branch, Long> implements BranchService {
    private BranchRepository branchRepository;


    @Override
    public Branch save(CreateBranchDto branchDto) {
        Branch branch = new Branch();
        branch.setName(branchDto.getName());
        branch.setAddress(branchDto.getAddress());
        branch.setCode(branchDto.getCode());
        branch.setHeadQuaters(branchDto.getHeadQuaters());
        return branchRepository.save(branch);
    }

    @Override
    public Branch update(CreateBranchDto branchDto, long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isEmpty())
            return null;
        branch.get().setName(branchDto.getName());
        branch.get().setAddress(branchDto.getAddress());
        branch.get().setCode(branchDto.getCode());
        branch.get().setHeadQuaters(branchDto.getHeadQuaters());
        return branchRepository.save(branch.get());
    }
}
