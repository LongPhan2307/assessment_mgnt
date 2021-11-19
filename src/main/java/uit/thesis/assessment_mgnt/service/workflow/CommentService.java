package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreateCommentDto;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;

import java.util.List;

public interface CommentService extends GenericService<Comment, Long> {
    Comment addComment(CreateCommentDto dto) throws NotFoundException;

    Comment generateComment(Object obj, Survey survey, User user);

    List<Comment> findBySurvey(String surveyCode) throws NotFoundException;
}
