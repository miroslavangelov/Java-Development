package app.config;

import app.utils.FileUtil;
import app.utils.FileUtilImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {
    @Bean
    public FileUtil fileIOUtil() {
        return new FileUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}