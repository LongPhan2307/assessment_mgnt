package uit.thesis.assessment_mgnt.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.LinkedList;
import java.util.List;

public class ErrorUtils {
    public static List<String> getErrorMessages(BindingResult bindingResult){
        List<String> messages = new LinkedList<>();
        List<ObjectError> errors = bindingResult.getAllErrors();

        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }

        return messages;

    }

    public static List<String> errorOf(String message){
        List<String> messages = new LinkedList<String>();
        messages.add(message);
        return messages;
    }
}
