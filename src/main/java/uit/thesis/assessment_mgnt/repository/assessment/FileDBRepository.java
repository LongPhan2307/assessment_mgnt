package uit.thesis.assessment_mgnt.repository.assessment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uit.thesis.assessment_mgnt.model.assessment.FileDB;

import java.util.List;

@Repository
public interface FileDBRepository extends JpaRepository<FileDB, String> {

    @Query("select file from FileDB file where file.document.id = ?1")
    List<FileDB> findByDocumentId(long documentId);

    @Query("select file from FileDB file where file.certificate.id = ?1")
    List<FileDB> findByCertificate(long certificateId);

    @Query("select file from FileDB file where file.invoice.id = ?1")
    List<FileDB> findByInvoice(long invoiceId);
}
