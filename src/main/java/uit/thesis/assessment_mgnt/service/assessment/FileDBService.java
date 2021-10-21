package uit.thesis.assessment_mgnt.service.assessment;

import org.springframework.web.multipart.MultipartFile;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


public interface FileDBService{

    FileDB store(MultipartFile files) throws IOException;

    List<FileDB> storeFiles(List<MultipartFile> files) throws IOException;

    public FileDB getFileById(String id);

    public Stream<FileDB> getAllFiles();
}
