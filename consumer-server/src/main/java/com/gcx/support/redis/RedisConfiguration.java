package com.gcx.support.redis;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis配置
 * @author CZH
 */
@Configuration
@EnableCaching

@ConfigurationProperties(prefix = "spring.cache.redis")

public class RedisConfiguration {
	
	//@Value("${spring.redis.defaultExpiration}")
	//private long defaultExpiration;
	private Duration timeToLive = Duration.ZERO;
	public void setTimeToLive(Duration timeToLive) {
		this.timeToLive = timeToLive;
	}


	@Bean
	public CacheManager cacheManager(RedisConnectionFactory factory) {
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

		//解决查询缓存转换异常的问题
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		// 配置序列化（解决乱码的问题）
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(0)) // Duration.ofSeconds(10) 过期时间
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
				.disableCachingNullValues();

		// 设置一个初始化的缓存空间set集合
		Set<String> cacheNames =  new HashSet<>();
		cacheNames.add("UserDate");
		cacheNames.add("Useryzm");

		// 对每个缓存空间应用不同的配置
		Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
		//configMap.put("my-redis-cache1", config);
		configMap.put("UserDate", config.entryTtl(Duration.ofSeconds(1800)));
		configMap.put("Useryzm", config.entryTtl(Duration.ofSeconds(120)));


		RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
				.cacheDefaults(config)
				.initialCacheNames(cacheNames)  // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
				.withInitialCacheConfigurations(configMap)
				.build();
		return cacheManager;


	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		template.setKeySerializer(new StringRedisSerializer());
		template.setValueSerializer(new RedisObjectSerializer());
		return template;
	}

	@Bean
	public RedisTemplate<String, String> redisStringTemplate(RedisConnectionFactory factory){
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String,String>();
		redisTemplate.setConnectionFactory(factory);
		// key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
		// 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
		// 或者JdkSerializationRedisSerializer序列化方式;
		RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long类型不可以会出现异常信息;
		redisTemplate.setKeySerializer(redisSerializer);
		redisTemplate.setHashKeySerializer(redisSerializer);
		return redisTemplate;
	}

	/*
	  @Bean
	    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate){
	        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
	        return cacheManager;
	    }
	    @Bean
	    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
	        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String,String>();
	        redisTemplate.setConnectionFactory(factory);
	        // key序列化方式;（不然会出现乱码;）,但是如果方法上有Long等非String类型的话，会报类型转换错误；
	        // 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer
	        // 或者JdkSerializationRedisSerializer序列化方式;
	        RedisSerializer<String> redisSerializer = new StringRedisSerializer();// Long类型不可以会出现异常信息;
	        redisTemplate.setKeySerializer(redisSerializer);
	        redisTemplate.setHashKeySerializer(redisSerializer);
	        return redisTemplate;
	    }
	*/
}
