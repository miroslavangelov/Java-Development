package app.config;

import app.util.HtmlReader;
import app.util.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public ValidatorUtil validatorUtil() {
        return new ValidatorUtil();
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public HtmlReader htmlReader(){
        return new HtmlReader();
    }
}
