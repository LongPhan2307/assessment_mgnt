package uit.thesis.assessment_mgnt.utils;

public class ResponseMessage {
    public static final String UN_KNOWN(String param){
        return  " Could not found " + param;
    }
    public static final String DELETE_SUCCESSFULLY = "Delete successfully";
    public static final String NO_DATA = "There is no data";
    public static final String NOT_BLANK(String param){
        return param + " Could not be blank";
    };
}
