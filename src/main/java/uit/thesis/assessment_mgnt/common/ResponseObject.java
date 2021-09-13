package uit.thesis.assessment_mgnt.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import uit.thesis.assessment_mgnt.utils.ErrorUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ResponseObject {
    public static List<String> emptyString = new LinkedList<>();
    public static ResponseEntity<Object> getResponse(Object content, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        map.put("error", emptyString);
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> getResponse(BindingResult bindingResult, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("content", emptyString);
        map.put("error", ErrorUtils.getErrorMessages(bindingResult));
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }

    public static ResponseEntity<Object> getResponse(String error, HttpStatus status){
        Map<String, Object> map = new HashMap<>();
        map.put("content", emptyString);
        map.put("error", ErrorUtils.errorOf(error));
        map.put("status", status.value());

        return new ResponseEntity<>(map, status);
    }

}
