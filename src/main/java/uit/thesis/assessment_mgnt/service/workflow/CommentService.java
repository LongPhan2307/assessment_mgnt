package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.dto.workflow.CreateCommentDto;
import uit.thesis.assessment_mgnt.model.workflow.Comment;

public interface CommentService extends GenericService<Comment, Long> {
    Comment addComment(CreateCommentDto dto) throws NotFoundException;
}
