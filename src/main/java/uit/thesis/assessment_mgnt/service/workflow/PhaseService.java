package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseDto;
import uit.thesis.assessment_mgnt.dto.workflow.UpdatePhaseDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;

import java.util.List;

public interface PhaseService extends GenericService<Phase, Long> {
    Phase addPhase(CreatePhaseDto dto) throws NotFoundException;

    Phase generateStartPhase(String workflowName) throws NotFoundException;

    Survey submitPhase(UpdatePhaseDto dto) throws NotFoundException, Exception;

    Survey returnPhase(UpdatePhaseDto dto) throws Exception;

    Survey declineAssignInspector(String surveyCode) throws NotFoundException;

    Phase findByNodeOrder(int nodeOrder);

    List<Phase> mockupData();

    Phase updatePhaseRole(String phaseName, String roleName) throws NotFoundException;
}
