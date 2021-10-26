package uit.thesis.assessment_mgnt.service.workflow;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.model.workflow.Comment;

@Service
@AllArgsConstructor
public class CommentServiceImpl extends GenericServiceImpl<Comment,Long> implements CommentService {
}
