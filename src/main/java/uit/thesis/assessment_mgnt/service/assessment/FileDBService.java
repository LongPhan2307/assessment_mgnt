package uit.thesis.assessment_mgnt.service.assessment;

import javassist.NotFoundException;
import org.springframework.web.multipart.MultipartFile;
import uit.thesis.assessment_mgnt.common.GenericService;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;


public interface FileDBService{

    FileDB store(MultipartFile files) throws IOException;

    FileDB storeInDocument(MultipartFile files, long documentId) throws IOException, NotFoundException;

    FileDB storeInInvoice(MultipartFile files, long invoiceId) throws NotFoundException, IOException;

    FileDB storeInCertificate(MultipartFile files, String certificateCode) throws IOException, NotFoundException;

    List<FileDB> storeFiles(List<MultipartFile> files) throws IOException;

    public FileDB getFileById(String id);

    public Stream<FileDB> getAllFiles();

    public Stream<FileDB> getAllFilesByCondition(Long documentId, Long invoiceId, Long certificateId);
}
