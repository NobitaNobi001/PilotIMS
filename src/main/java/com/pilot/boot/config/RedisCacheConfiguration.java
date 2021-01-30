package com.pilot.boot.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * redis cache
 * @author ezuy
 * @date 21/1/22 14:55
 */
@EnableCaching
@Configuration
public class RedisCacheConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        //1. template
        RedisTemplate<String, Object> template = new RedisTemplate<>();

        //2.redis factory
        template.setConnectionFactory(redisConnectionFactory);

        //3.serialize way -> json
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<Object>(Object.class);

        //4.add serialize to template
        template.setDefaultSerializer(serializer);

        //5.return IOC
        return template;
    }


}
