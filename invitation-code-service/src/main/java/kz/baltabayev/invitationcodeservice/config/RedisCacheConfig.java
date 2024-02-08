package kz.baltabayev.invitationcodeservice.config;

import kz.baltabayev.invitationcodeservice.model.types.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair.fromSerializer;

/**
 * Redis cache configuration
 *
 * @Author Qaisar
 */
@Configuration
public class RedisCacheConfig {

    // Time to live for cache in minutes
    private static final int CACHE_TTL_MINUTES = 60;

    /**
     * Bean for RedisTemplate.
     * This bean is used to perform Redis operations with key-value pairs.
     * The key is a string and the value is a Role object.
     *
     * @param connectionFactory the Redis connection factory
     * @return the RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Role> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Role> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Role.class));
        return redisTemplate;
    }

    /**
     * Bean for RedisCacheConfiguration.
     * This bean is used to configure the Redis cache.
     * It sets the time to live for cache entries, disables caching of null values,
     * and sets the serializer for cache values.
     *
     * @return the RedisCacheConfiguration
     */
    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(CACHE_TTL_MINUTES))
                .disableCachingNullValues()
                .serializeValuesWith(fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
}
