package socialapp.loggingstarter.config;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import socialapp.loggingstarter.aspects.LoggingMethodExecutionAspect;
import socialapp.loggingstarter.aspects.LoggingTimeExecutionAspect;

/**
 * The {@code LoggingAutoConfiguration} class is responsible for configuring and enabling logging aspects
 * based on specified conditions in the application properties.
 *
 * <p>It creates instances of {@code LoggingMethodExecutionAspect} and {@code LoggingTimeExecutionAspect}
 * beans when the conditions are met.
 *
 * <p>Example usage:
 * <pre>
 * // Enable logging aspects by setting 'app.common.logging.enabled=true' in application properties.
 * app.common.logging.enabled=true
 * </pre>
 */
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnProperty(prefix = "app.common.logging", name = "enabled", havingValue = "true")
@EnableAspectJAutoProxy
public class LoggingAutoConfiguration {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * Post-construct method to log the initialization of the configuration.
     */
    @PostConstruct
    void init() {
        log.info("LoggingAutoConfiguration initialized");
    }

    /**
     * Bean definition for the {@code LoggingMethodExecutionAspect}.
     *
     * @return Instance of {@code LoggingMethodExecutionAspect}
     */
    @Bean
    LoggingMethodExecutionAspect loggingMethodExecutionAspect() {
        log.info("LoggingMethodExecutionAspect bean start to create.");
        return new LoggingMethodExecutionAspect();
    }

    /**
     * Bean definition for the {@code LoggingTimeExecutionAspect}.
     *
     * @return Instance of {@code LoggingTimeExecutionAspect}
     */
    @Bean
    LoggingTimeExecutionAspect loggingTimeExecutionAspect() {
        log.info("LoggingTimeExecutionAspect bean start to create.");
        return new LoggingTimeExecutionAspect();
    }
}

