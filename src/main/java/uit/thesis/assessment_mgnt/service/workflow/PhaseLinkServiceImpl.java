package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseLinkDto;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.PhaseLink;
import uit.thesis.assessment_mgnt.model.workflow.Workflow;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseLinkRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.repository.workflow.WorkflowRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class PhaseLinkServiceImpl extends GenericServiceImpl<PhaseLink, Long> implements PhaseLinkService {
    private PhaseLinkRepository phaseLinkRepository;
    private PhaseRepository phaseRepository;
    private WorkflowRepository workflowRepository;

    @Override
    public PhaseLink addPhaseLink(CreatePhaseLinkDto dto) throws Exception {
        PhaseLink phaseLink = new PhaseLink();
        Phase linkBy = phaseRepository.findByName(dto.getLinkBy());
        Phase linkTo = phaseRepository.findByName(dto.getLinkTo());
        Workflow workflow =workflowRepository.findByName(dto.getWorkflowName());
        if(workflow == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Worflow "));
        if(linkBy == null || linkTo == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Link By or Link To"));
        if(linkBy.getName().equals(linkTo.getName()))
            throw new Exception("link By and link To must be different");
        phaseLink.setTransition(dto.getTransition());
        phaseLink.setLinkBy(linkBy);
        phaseLink.setWorkflow(workflow);
        phaseLink.setLinkTo(linkTo);
        return phaseLinkRepository.save(phaseLink);
    }
}
