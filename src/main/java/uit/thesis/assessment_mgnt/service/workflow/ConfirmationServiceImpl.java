package uit.thesis.assessment_mgnt.service.workflow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;

@Service
@AllArgsConstructor
public class ConfirmationServiceImpl extends GenericServiceImpl<Confirmation, Long> implements ConfirmationService {
}
