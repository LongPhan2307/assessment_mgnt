package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.workflow.CreateCommentDto;
import uit.thesis.assessment_mgnt.model.assessment.Document;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.model.assessment.Survey;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Expense;
import uit.thesis.assessment_mgnt.model.workflow.Payment;
import uit.thesis.assessment_mgnt.repository.assessment.SurveyRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.repository.workflow.CommentRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.survey.Const;

@Service
@AllArgsConstructor
public class CommentServiceImpl extends GenericServiceImpl<Comment,Long> implements CommentService {
    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private SurveyRepository surveyRepository;

    @Override
    public Comment addComment(CreateCommentDto dto) throws NotFoundException {
        Survey survey = surveyRepository.findByCode(dto.getSurveyCode());
        if(survey == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Survey "));
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("User "));
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        return commentRepository.save(comment);
    }

    @Override
    public Comment generateComment(Object obj, Survey survey, User user) {
        Comment comment = new Comment();
        if(obj instanceof Expense){
            String content = user.getUsername() + "has added new expense: " + ((Expense) obj).getCost();
            comment.setSurvey(survey);
            comment.setUser(user);
            comment.setTitle(Const.ADDING_EXPENSE);
            comment.setContent(content);
        }
        if(obj instanceof Document){
            String content = user.getUsername() + "has added new folder: " + ((Document) obj).getName();
            comment.setSurvey(survey);
            comment.setUser(user);
            comment.setTitle(Const.ADDING_FOLDER);
            comment.setContent(content);
        }
        if(obj instanceof FileDB){
            String content = user.getUsername() + "has added new files: " + ((FileDB) obj).getName();
            comment.setSurvey(survey);
            comment.setUser(user);
            comment.setTitle(Const.ADDING_FOLDER);
            comment.setContent(content);
        }
        return commentRepository.save(comment);
    }
}
