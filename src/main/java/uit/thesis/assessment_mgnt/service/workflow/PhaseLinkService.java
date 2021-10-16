package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseLinkDto;
import uit.thesis.assessment_mgnt.model.workflow.PhaseLink;

public interface PhaseLinkService extends GenericService<PhaseLink, Long> {
    PhaseLink addPhaseLink(CreatePhaseLinkDto dto) throws Exception;
}
