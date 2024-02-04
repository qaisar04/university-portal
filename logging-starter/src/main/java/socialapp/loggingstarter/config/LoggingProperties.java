package socialapp.loggingstarter.config;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * The {@code LoggingProperties} class is a configuration properties class for common logging AOP on the service layer.
 * It reads properties from the 'app.common.logging' prefix in the application's configuration files.
 *
 * <p>Example usage:
 * <pre>
 * // Define properties in application.properties or application.yml
 * app.common.logging.enabled=true
 * </pre>
 */
@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "app.common.logging")
public class LoggingProperties {
    /**
     * Flag to enable common logging AOP on the service layer.
     * Defaults to true.
     */
    private boolean enabled = true;
    /**
     * Logger for the {@code LoggingProperties} class.
     */
    public static final Logger logger = LoggerFactory.getLogger(LoggingProperties.class);

    /**
     * Post-construct method to log the initialization of the configuration properties.
     */
    @PostConstruct
    void init() {
        logger.info("Logging properties initialized: {}", this);
    }
}
