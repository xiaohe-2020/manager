package com.example.test.comm.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.integration.redis.util.RedisLockRegistry;
import redis.clients.jedis.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Redis缓存配置类
 * @author szekinwin
 *
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

    private Logger logger = LoggerFactory.getLogger(getClass());
    public static final String PROJECT_PREFIX = "com.example.test";
    public static final String SEPARATOR = "_";
    @Value("${spring.redis.database:0}")
    private Integer database;
    @Value("${spring.redis.cluster.nodes:}")
    private String clusterNodes;
    @Value("${spring.redis.sentinel.master:}")
    private String masterName;
    @Value("${spring.redis.sentinel.nodes:}")
    private String sentinelNodes;
    @Value("${spring.redis.host:}")
    private String host;
    @Value("${spring.redis.port:6379}")
    private int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;
    @Value("${spring.redis.pool.max-wait}")
    private long maxWaitMillis;
    @Value("${spring.redis.password}")
    private String password;


    //redis-分布式锁
    @Bean
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory){
        return new RedisLockRegistry(redisConnectionFactory, PROJECT_PREFIX+"::REDIS_LOCK");
    }

    @Bean
    public JedisPool redisPoolFactory()  throws Exception{
        if(host !=null && !"".equals(host)){
            logger.info("JedisPool注入成功！！");
            logger.info("redis地址：" + host + ":" + port);
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            jedisPoolConfig.setBlockWhenExhausted(true);
            // 是否启用pool的jmx管理功能, 默认true
            jedisPoolConfig.setJmxEnabled(true);
            JedisPool jedisPool = new JedisPool(jedisPoolConfig, host, port, timeout, password,database);
            return jedisPool;
        }else{
            return null;
        }
    }

    @Bean
    public JedisCluster redisClusterFactory()  throws Exception{
        if(clusterNodes !=null && !"".equals(clusterNodes)){
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            jedisPoolConfig.setBlockWhenExhausted(true);
            // 是否启用pool的jmx管理功能, 默认true
            jedisPoolConfig.setJmxEnabled(true);
            Set<HostAndPort> nodes = new HashSet<>();
            String[] nodesArray =  clusterNodes.split(",");
            for(String nodeElement : nodesArray){
                String[] nodeArray = nodeElement.split(":");
                HostAndPort node = new HostAndPort(nodeArray[0],Integer.valueOf(nodeArray[1]));
                nodes.add(node);
            }
            JedisCluster jedisCluster =  new JedisCluster(nodes,timeout,timeout,timeout,password,jedisPoolConfig);
            return jedisCluster;
        }else{
            return null;
        }

    }
    /**
     *
     * @author Fire Monkey
     * @date 下午7:20
     * @return redis.clients.jedis.JedisSentinelPool
     * 生成JedisSentinelPool并且放入Spring容器
     *
     */
    @Bean(value = "sentinelPool")
    public JedisSentinelPool redisSentinelFactory(){
        if(sentinelNodes !=null && !"".equals(sentinelNodes)) {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(maxIdle);
            jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
            // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            jedisPoolConfig.setBlockWhenExhausted(true);
            // 是否启用pool的jmx管理功能, 默认true
            jedisPoolConfig.setJmxEnabled(true);


            Set<String> nodeSet = new HashSet<>();
            String[] nodseArray = sentinelNodes.split(",");
            //判断是否为空
            if (nodseArray == null || nodseArray.length == 0) {
                throw new RuntimeException("RedisSentinelConfiguration initialize error nodeArray is null");
            }
            //循环注入至Set中
            for (String node : nodseArray) {
                nodeSet.add(node);
            }
            //创建连接池对象
            JedisSentinelPool jedisPool = new JedisSentinelPool(masterName, nodeSet, jedisPoolConfig, timeout, password);
            return jedisPool;
        }else {
            return  null;
        }
    }

    @Bean
	public CacheManager cacheManager(@SuppressWarnings("rawtypes") RedisConnectionFactory redisConnectionFactory) {

        return new RedisCacheManager(
                RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory),
                this.getRedisCacheConfigurationWithTtl(7200), // 默认策略，未配置的 key 会使用这个
                this.getRedisCacheConfigurationMap() // 指定 key 策略

        );
	}

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = new HashMap<>();

        redisCacheConfigurationMap.put("currVersion", this.getRedisCacheConfigurationWithTtl(0));

        return redisCacheConfigurationMap;
    }

    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        redisCacheConfiguration = redisCacheConfiguration.serializeValuesWith(
                RedisSerializationContext
                        .SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer)
        ).entryTtl(Duration.ofSeconds(seconds));

        return redisCacheConfiguration;
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory){
        StringRedisTemplate template = new StringRedisTemplate(factory);
        setSerializer(template);//设置序列化工具
        template.afterPropertiesSet();
        return template;
    }
     private void setSerializer(StringRedisTemplate template){
            @SuppressWarnings({ "rawtypes", "unchecked" })
            Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
            ObjectMapper om = new ObjectMapper();
            om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
            jackson2JsonRedisSerializer.setObjectMapper(om);
            template.setValueSerializer(jackson2JsonRedisSerializer);
     }
}
