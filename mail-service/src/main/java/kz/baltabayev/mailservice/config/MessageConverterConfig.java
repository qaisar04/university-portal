package kz.baltabayev.mailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.ByteArrayJsonMessageConverter;
import org.springframework.kafka.support.converter.JsonMessageConverter;

/**
 * Configuration class for Message Converters.
 * This class provides a bean definition for a JsonMessageConverter.
 */
@Configuration
public class MessageConverterConfig {

    /**
     * Bean definition for JsonMessageConverter.
     * This bean is responsible for converting Kafka messages to JSON format.
     *
     * @return a new instance of ByteArrayJsonMessageConverter.
     */
    @Bean
    public JsonMessageConverter jsonMessageConverter() {
        return new ByteArrayJsonMessageConverter();
    }
}