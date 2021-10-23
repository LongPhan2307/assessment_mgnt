package uit.thesis.assessment_mgnt.common;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        String currentName = SecurityContextHolder.getContext().getAuthentication().getName();
        return Optional.ofNullable(currentName);
    }
}
