package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.assessment.survey.CreateConfirmDto;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;

import java.util.List;

public interface ConfirmationService extends GenericService<Confirmation, Long> {
    List<Confirmation> createConfirmation(String[] confirmedUsers, Comment comment) throws NotFoundException;
}
