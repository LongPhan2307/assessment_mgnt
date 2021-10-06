package uit.thesis.assessment_mgnt.service.assessment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.repository.assessment.FileDBRepository;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
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
    public List<FileDB> storeFiles(List<MultipartFile> files) throws IOException {
        List<FileDB> fileDBList = new LinkedList<>();
        Iterable<FileDB> iterable = (Iterable<FileDB>) fileDBList.iterator();
//        StringBuilder sb = new StringBuilder(files.length);
//        for (int i = 0; i < files.length; i++) {
//            String originalName = files[i].getOriginalFilename();
//            String name = files[i].getName();
//            String contentType = files[i].getContentType();
//            long size = files[i].getSize();
//
//            sb.append("File Name: " + originalName + "\n");
//            FileDB fileDB = new FileDB(originalName, contentType, files[i].getBytes());
//            fileDBList.add((FileDB) files[i]);
//        }
        return null;
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
