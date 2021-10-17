package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;

public interface PhaseService extends GenericService<Phase, Long> {
    Phase addPhase(CreatePhaseDto dto) throws NotFoundException;

    Phase generateStartPhase(String workflowName) throws NotFoundException;

    Survey submitPhase(String sourceName, String surveyCode) throws NotFoundException, Exception;
}
