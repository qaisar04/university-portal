package kz.baltabayev.mailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean;

/**
 * Configuration class for FreeMarker.
 * FreeMarker is a template engine, a generic tool to generate text output based on templates.
 */
@Configuration
public class FreeMarkerConfig {

    // The path to the FreeMarker template loader.
    @Value("${spring.freemarker.template-loader-path}")
    private String ftlLoaderPath;

    /**
     * Bean definition for FreeMarkerConfigurationFactoryBean.
     * This bean is responsible for setting up FreeMarker configuration.
     * The @Primary annotation gives it higher preference when multiple beans of the same type are present.
     *
     * @return FreeMarkerConfigurationFactoryBean with the template loader path set.
     */
    @Bean
    @Primary
    public FreeMarkerConfigurationFactoryBean ftl() {
        var bean = new FreeMarkerConfigurationFactoryBean();
        bean.setTemplateLoaderPath(ftlLoaderPath);
        return bean;
    }
}