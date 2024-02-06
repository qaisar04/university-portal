package kz.baltabayev.mailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

@Configuration
public class FreeMarkerConfig {

    @Value("${spring.freemarker.template-loader-path}")
    private String ftlLoaderPath;

    @Bean
    @Primary
    public FreeMarkerConfigurationFactoryBean ftl() {
        var bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath(ftlLoaderPath);
        return bean;
    }
}
