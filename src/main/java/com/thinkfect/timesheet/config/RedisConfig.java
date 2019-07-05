package com.thinkfect.timesheet.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.thinkfect.timesheet.domain.Effort;
import com.thinkfect.timesheet.domain.Project;
import com.thinkfect.timesheet.domain.Task;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Value("${spring.redis.host}")
	private String hostName;
	
	
	@Bean(name = "CommandRedisServer1")
	@Primary
	public JedisConnectionFactory cmd1RedisConnectionFactory(){
	    
	    JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(20);
		poolConfig.setMinIdle(10);
		poolConfig.setMaxIdle(30);
		
	       RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
	        redisStandaloneConfiguration.setHostName(hostName);
	        redisStandaloneConfiguration.setPort(6379);
	        JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
	        jedisClientConfiguration.connectTimeout(Duration.ofSeconds(60));// 60s connection timeout

	        JedisConnectionFactory jedisConFactory = new JedisConnectionFactory(redisStandaloneConfiguration,
	                jedisClientConfiguration.build());

	        return jedisConFactory;
	        
	}
	

	@Bean(name = "RedisTemplateForProject")
	@Autowired
	public RedisTemplate<String, Project> projecteRedisTemplate(@Qualifier(value = "CommandRedisServer1") RedisConnectionFactory redisConnectionFactory) {
	    RedisTemplate<String, Project> template = new RedisTemplate<String, Project>();
	    template.setConnectionFactory(cmd1RedisConnectionFactory());

	    
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<Project> assetSerializer = new Jackson2JsonRedisSerializer<>(Project.class);

		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(assetSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(assetSerializer);
		
		return template;
	}

	@Bean(name = "RedisTemplateForTask")
	@Autowired
	public RedisTemplate<String, Task> taskRedisTemplate(@Qualifier(value = "CommandRedisServer1") RedisConnectionFactory redisConnectionFactory) {
	    RedisTemplate<String, Task> template = new RedisTemplate<String, Task>();
	    template.setConnectionFactory(cmd1RedisConnectionFactory());

	    
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<Task> assetSerializer = new Jackson2JsonRedisSerializer<>(Task.class);

		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(assetSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(assetSerializer);
		
		return template;
	}
	
	@Bean(name = "RedisTemplateForEffort")
	@Autowired
	public RedisTemplate<String, Effort> effortRedisTemplate(@Qualifier(value = "CommandRedisServer1") RedisConnectionFactory redisConnectionFactory) {
	    RedisTemplate<String, Effort> template = new RedisTemplate<String, Effort>();
	    template.setConnectionFactory(cmd1RedisConnectionFactory());

	    
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		RedisSerializer<Effort> assetSerializer = new Jackson2JsonRedisSerializer<>(Effort.class);

		template.setKeySerializer(stringSerializer);
		template.setValueSerializer(assetSerializer);
		template.setHashKeySerializer(stringSerializer);
		template.setHashValueSerializer(assetSerializer);
		
		return template;
	}
	
}
