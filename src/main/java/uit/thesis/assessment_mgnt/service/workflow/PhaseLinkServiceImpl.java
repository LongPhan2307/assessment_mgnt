package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreatePhaseLinkDto;
import uit.thesis.assessment_mgnt.model.workflow.Phase;
import uit.thesis.assessment_mgnt.model.workflow.PhaseLink;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseLinkRepository;
import uit.thesis.assessment_mgnt.repository.workflow.PhaseRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

@Service
@AllArgsConstructor
public class PhaseLinkServiceImpl extends GenericServiceImpl<PhaseLink, Long> implements PhaseLinkService {
    private PhaseLinkRepository phaseLinkRepository;
    private PhaseRepository phaseRepository;

    @Override
    public PhaseLink addPhaseLink(CreatePhaseLinkDto dto) throws NotFoundException {
        PhaseLink phaseLink = new PhaseLink();
        Phase linkBy = phaseRepository.findByName(dto.getLinkBy());
        Phase linkTo = phaseRepository.findByName(dto.getLinkTo());
        if(linkBy == null || linkTo == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Link By or Link To"));
        phaseLink.setTransition(dto.getTransition());
        phaseLink.setLinkBy(linkBy);
        phaseLink.setLinkTo(linkTo);
        return phaseLinkRepository.save(phaseLink);
    }
}
