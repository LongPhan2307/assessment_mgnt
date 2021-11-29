package uit.thesis.assessment_mgnt.service.assessment;

import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uit.thesis.assessment_mgnt.model.assessment.Certificate;
import uit.thesis.assessment_mgnt.model.assessment.Document;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;
import uit.thesis.assessment_mgnt.model.system.User;
import uit.thesis.assessment_mgnt.model.workflow.Comment;
import uit.thesis.assessment_mgnt.model.workflow.Invoice;
import uit.thesis.assessment_mgnt.repository.assessment.CertificateRepository;
import uit.thesis.assessment_mgnt.repository.assessment.DocumentRepository;
import uit.thesis.assessment_mgnt.repository.assessment.FileDBRepository;
import uit.thesis.assessment_mgnt.repository.system.UserRepository;
import uit.thesis.assessment_mgnt.repository.workflow.InvoiceRepository;
import uit.thesis.assessment_mgnt.service.workflow.CommentService;
import uit.thesis.assessment_mgnt.utils.ResponseMessage;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class FileDBServiceImpl implements FileDBService {
    private FileDBRepository fileDBRepository;
    private DocumentRepository documentRepository;
    private CertificateRepository certificateRepository;
    private CommentService commentService;
    private UserRepository userRepository;
    private InvoiceRepository invoiceRepository;

    @Override
    public FileDB store(MultipartFile files) throws IOException {
        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, files.getContentType(), files.getBytes());
        return fileDBRepository.save(fileDB);
    }

    @Override
    public FileDB storeInDocument(MultipartFile files, long documentId) throws IOException, NotFoundException {
        Optional<Document> document = documentRepository.findById(documentId);
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(document.isEmpty())
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Document "));
        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, files.getContentType(), files.getBytes());
        fileDB.setDocument(document.get());
        commentService.generateComment(fileDB, document.get().getSurvey(), user);
        return fileDBRepository.save(fileDB);
    }

    @Override
    public FileDB storeInInvoice(MultipartFile files, long invoiceId) throws NotFoundException, IOException {
        Optional<Invoice> invoice = invoiceRepository.findById(invoiceId);
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(invoice.isEmpty())
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Invoice "));
        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, files.getContentType(), files.getBytes());
        fileDB.setInvoice(invoice.get());
        commentService.generateComment(invoice, invoice.get().getSurvey(), user);
        return fileDBRepository.save(fileDB);
    }

    @Override
    public FileDB storeInCertificate(MultipartFile files, String certificateCode) throws IOException, NotFoundException {
        Certificate certificate = certificateRepository.findByCode(certificateCode);
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        if(certificate == null)
            throw new NotFoundException(ResponseMessage.UN_KNOWN("Certificate "));
        String fileName = StringUtils.cleanPath(files.getOriginalFilename());
        FileDB fileDB = new FileDB(fileName, files.getContentType(), files.getBytes());
        fileDB.setCertificate(certificate);
        commentService.generateComment(fileDB, certificate.getSurvey(), user);
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
    public FileDB getFileById(String id) {
        Optional<FileDB> fileDB = fileDBRepository.findById(id);
        if(fileDB.isEmpty())
            return null;
        return fileDB.get();
    }

    @Override
    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    @Transactional
    @Override
    public Stream<FileDB> getAllFilesByCondition(Long documentId, Long invoiceId, Long certificateId) {
        if( documentId != null){
            return fileDBRepository.findByDocumentId(documentId).stream();
        }
        if(invoiceId != null)
            return fileDBRepository.findByInvoice(invoiceId).stream();
        if(certificateId != null)
            return fileDBRepository.findByCertificate(certificateId).stream();
        return null;
    }
}
