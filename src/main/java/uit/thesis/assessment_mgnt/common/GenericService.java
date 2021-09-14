package uit.thesis.assessment_mgnt.common;

import java.util.List;
import java.util.Optional;

public interface GenericService<T extends AbstractEntity, ID> {
    List<T> findAll();
    Optional<T> findById(ID id);
    T save(T entity);
    void deleteById(ID id);
}
