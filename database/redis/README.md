# 1、redis概述

> Remote Dictionary Server 远程字典服务

- redis是一个开源的使用ANSI C语言编写、支持网络、可基于内存亦可持久化的日志型、key-value数据库，提供多种语言的API；

- redis数据缓存在内存中，会周期性地把更新数据写入到磁盘或把修改操作写入追加的记录文件中；
- redis默认有16个数据库DB 0 ~ DB 15，默认使用DB 0；

> redis 为什么快？

- redis基于内存操作，使用单线程操作效率是最高的，因为多线程需要CPU切换上下文，较为耗时；
- 对于内存系统来说，没有上下文切换的操作效率是最高的，多次读写都是在一个CPU上；

- redis是单线程的，因此redis的性能瓶颈不是CPU，而是机器内存和网络带宽；

# 2、数据类型

# 3、事务

# 4、Jedis

> Redis 官方推荐的使用Java连接redis的客户端；简单封装了Redis的API库，可看作是Redis客户端；

- 导入依赖

  ```xml
  <!--jredis-->
  <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>3.2.0</version>
  </dependency>
  <!--fastjson-->
  <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>fastjson</artifactId>
      <version>1.2.70</version>
  </dependency>
  ```

- java中使用

  ```java
  public class TestJedis {
      public static void main(String[] args) {
          
          // 指定redis服务与端口
          Jedis jedis = new Jedis("127.0.0.1", 6379);
          // 如需密码
          jedis.auth("123456");
          // 测试redis连接结果:PONG
          String response = jedis.ping();
          System.out.println("ping结果：" + response);
  
          // 访问redis
          jedis.append("mykey", "this is a value");
          String myValue = jedis.get("mykey");
          System.out.println("获取的结果：" + myValue);
          // 关闭连接
          jedis.close();
  
          // ************ 使用jedis连接池 ***********
  
          // 连接池配置
          JedisPoolConfig poolConfig = new JedisPoolConfig();
          // 设置最大连接数，默认8
          poolConfig.setMaxTotal(1024);
          // 设置最大空闲数
          poolConfig.setMaxIdle(100);
          // ......
  
          // 创建jedis连接池
          JedisPool jedisPool = new JedisPool(poolConfig, "127.0.0.1", 6379);
          // 使用
          Jedis jedis1 = jedisPool.getResource();
          jedis1.ping();
      }
  }
  ```

# 5、SpringBoot整合

- SpringBoot 2.X的后续版本中，使用`lettuce`代替原先的`jedis`

  - `jedis`：采用直连方式，多线程不安全，可使用 `JedisPool`连接池解决；
  - `luttuce`：采用 netty ，实例可在多个线程中共享，线程安全；

- 引入依赖

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
  </dependency>
  ```

- 整合到`SpringBoot`会有一个`RedisAutoConfiguration`自动配置类与`RedisProperties`类

  ```java
  @Configuration(proxyBeanMethods = false)
  @ConditionalOnClass(RedisOperations.class)
  @EnableConfigurationProperties(RedisProperties.class)
  @Import({ LettuceConnectionConfiguration.class, JedisConnectionConfiguration.class })
  public class RedisAutoConfiguration {
  
      // 操作redis
  	@Bean
  	@ConditionalOnMissingBean(name = "redisTemplate") // 条件注解。若Spring容器中已存在RedisTemplate对象，则此对象不再实例化
  	@ConditionalOnSingleCandidate(RedisConnectionFactory.class)
  	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
  		RedisTemplate<Object, Object> template = new RedisTemplate<>();
  		template.setConnectionFactory(redisConnectionFactory);
  		return template;
  	}
  
      // 操作redis中的String数据类型
  	@Bean
  	@ConditionalOnMissingBean // 条件注解
  	@ConditionalOnSingleCandidate(RedisConnectionFactory.class)
  	public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
  		StringRedisTemplate template = new StringRedisTemplate();
  		template.setConnectionFactory(redisConnectionFactory);
  		return template;
  	}
  
  }
  ```

- redis配置

  ```yaml
  spring:
    redis:
      host: 127.0.0.1
      port: 6379
      # 无密码可不配
      # password: 123456
  ```

- 使用 RedisTemplate

  ```java
  @SpringBootTest
  class SpringbootDemoApplicationTests {
  
      // 注入RedisTemplate即可操作redis
      @Autowired
      private RedisTemplate redisTemplate;
  
      @Test
      void contextLoads() {
          // redis测试
          redisTemplate.opsForValue().set("mykey", "this is a value");
  		Object value = redisTemplate.opsForValue().get("mykey");
          System.out.println("redis操作结果：" + value);
      }
  
  }
  ```

- 乱码问题

  - `RedisTemplate`存储对象后默认采用JDK序列化器，会存在乱码情况，可自定义配置；

  - 调用 `RedisSerializer`的静态方法获取序列化器，然后设置；
  - 亦或实现 `RedisSerializer<T>` 接口，设置相应序列化器；

- 定制 RedisTemplate 模板

  - 创建一个Bean放入Spring容器中，Spring启动时会触发 `RedisTemplate` 上的条件注解，使默认的 `RedisTemplate` 失效；

    ```java
    @Configuration
    public class RedisConfig {
    
        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
    
            // 将template 泛型设置为 <String, Object>
            RedisTemplate<String, Object> template = new RedisTemplate();
            // 连接工厂，不必修改
            template.setConnectionFactory(redisConnectionFactory);
            // key、hash的key 采用 String序列化方式
            template.setKeySerializer(RedisSerializer.string());
            template.setHashKeySerializer(RedisSerializer.string());
            // value、hash的value 采用 Jackson 序列化方式
            template.setValueSerializer(RedisSerializer.json());
            template.setHashValueSerializer(RedisSerializer.json());
            template.afterPropertiesSet();
    
            return template;
        }
    
    }
    ```

- Redis 工具类

  - `RedisTemplate` 需要通过调用`.opForxxx` 进行相应的操作，可使用工具类封装相关操作；

    ```java
    // Redis工具类
    @Component
    public class RedisUtil {
    
        // ......
      	
    }
    ```









