package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uit.thesis.assessment_mgnt.common.GenericServiceImpl;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.repository.assessment.FileDBRepository;

import java.io.IOException;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileDBServiceImpl implements FileDBService {
    private FileDBRepository fileDBRepository;

    @Override
    public FileDB store(MultipartFile files) throws IOException {
        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, files.getContentType(), files.getBytes());
        return fileDBRepository.save(fileDB);
    }

    @Override
    public FileDB getFileByName(String name) {
        return null;
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }
}
