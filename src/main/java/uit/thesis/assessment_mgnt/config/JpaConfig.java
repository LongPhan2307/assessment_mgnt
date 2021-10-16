package uit.thesis.assessment_mgnt.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Configuration
@EnableJpaAuditing
public class JpaConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        //modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
//        modelMapper.getConfiguration().isSkipNullEnabled();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }

    @Component
    public interface GenericRepository<T, ID> extends JpaRepository<T, ID> {

    }
}
