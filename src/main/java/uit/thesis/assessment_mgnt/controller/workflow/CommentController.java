package uit.thesis.assessment_mgnt.controller.workflow;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uit.thesis.assessment_mgnt.common.ResponseObject;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.service.workflow.CommentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;
import uit.thesis.assessment_mgnt.utils.domain.workflow.CommentDomain;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(CommentDomain.COMMENT_DOMAIN)
public class CommentController {
    private CommentService commentService;

    @GetMapping("")
    public ResponseEntity<Object> findAll(){
        List<Comment> comments = commentService.findAll();
        if(comments.isEmpty())
            return ResponseObject.getResponse(ResponseMessage.NO_DATA, HttpStatus.OK);
        return ResponseObject.getResponse(comments, HttpStatus.OK);
    }
}
