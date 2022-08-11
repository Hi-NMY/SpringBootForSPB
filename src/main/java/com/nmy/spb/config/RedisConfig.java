package com.nmy.spb.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author nmy
 * @title: RedisConfig
 * @date 2022-08-06 9:54
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String,Object> t = new RedisTemplate<>();
        t.setConnectionFactory(factory);
        //json序列化配置
        Jackson2JsonRedisSerializer<Object> jrs = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL);
        jrs.setObjectMapper(om);
        //String序列化
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        t.setKeySerializer(stringRedisSerializer);
        t.setHashKeySerializer(stringRedisSerializer);
        t.setValueSerializer(jrs);
        t.setHashValueSerializer(jrs);
        t.afterPropertiesSet();
        return t;
    }


}
