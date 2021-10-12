package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseDto;
import uit.thesis.assessment_mgnt.model.workflow.Phase;

public interface PhaseService extends GenericService<Phase, Long> {
    Phase addPhase(CreatePhaseDto dto) throws NotFoundException;

    Phase generateStartPhase();

    String submitPhase(String sourceName, String surveyCode) throws NotFoundException, Exception;
}
