package uit.thesis.assessment_mgnt.service.workflow;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.dto.assessment.survey.CreateConfirmDto;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Confirmation;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.repository.workflow.CommentRepository;
import uit.thesis.assessment_mgnt.repository.workflow.ConfirmationRepository;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

import java.util.*;

@Service
@AllArgsConstructor
public class ConfirmationServiceImpl extends GenericServiceImpl<Confirmation, Long> implements ConfirmationService {
    private ConfirmationRepository confirmationRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Override
    public List<Confirmation> createConfirmation(String[] confirmedUsers, Comment comment) throws NotFoundException {
        List<Confirmation> confirmations = new LinkedList<>();
            for (int i = 0; i < confirmedUsers.length; i++) {
                User confirmedUser = userRepository.findByUsername(confirmedUsers[i]);
                if(confirmedUser == null)
                    continue;
                Confirmation confirmation = new Confirmation(comment, confirmedUser);
                confirmations.add(confirmation);
            }
        return confirmationRepository.saveAll(confirmations);
    }

    @Override
    public Comment changeStatusConfirmation(long commentId) throws NotFoundException {
        Optional<Comment> comment = commentRepository.findById(commentId);
        User confirmedUser = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(confirmedUser == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("User "));
        if(comment.isEmpty())
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Confirmation "));
        Iterator iterator = comment.get().getConfirmations().iterator();
        while (iterator.hasNext()){
            Confirmation confirmation = (Confirmation) iterator.next();
            if(confirmation.getUser().getUsername().equals(confirmedUser.getUsername())){
                confirmation.setStatus(!confirmation.isStatus());
                confirmationRepository.save(confirmation);
            }
        }
        return comment.get();
    }

    @Override
    public List<Confirmation> findByCommentId(long commentId) {
        List<Confirmation> list = confirmationRepository.findByCommentId(commentId);
        return list;
    }
}
