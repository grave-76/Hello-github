# Json

### 1、Jackson

##### 1.1 依赖

```xml
<!-- Jackson 三个核心模块 -->

    <!-- 1、数据绑定包 -->
    <!-- 包含核心包与注解支持包，只需引入此包即可 -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.3</version>
    </dependency>
    <!-- 2、核心包 -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-core</artifactId>
        <version>2.12.3</version>
    </dependency>
    <!-- 3、注解支持包 -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-annotations</artifactId>
        <version>2.12.3</version>
    </dependency>
```

##### 1.2 使用

```java
// 测试对象
Object obj = new Object();

// 创建 ObjectMapper 对象，用于读写 json
ObjectMapper objectMapper = new ObjectMapper();

// ****************************** 设置相关配置信息 ******************************

// 反序列化时忽略 json 中存在但 Java对象中不存在的属性
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

// 序列化时日期默认采用时间戳格式，设置false后变为 yyyy-MM-dd'T'HH:mm:ss.SSSSZ
objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

// 序列化时忽略为null的属性
objectMapper.setSerializationInclusion(Include.NON_NULL);

// 序列化时忽略值为默认值的属性
objectMapper.setDefaultPropertyInclusion(Include.NON_DEFAULT);

// ****************************** 序列化与反序列化 ******************************

// 将对象的所有属性序列化成 json 字符串，无论是否有值
String asString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);

// 反序列化，json 中的属性必须在 java 对象中找到相对应的属性，可少不可多
Object readValue = objectMapper.readValue(asString, Object.class);

// 也可从 Reader、File、InputStream等反序列化为对象
// ...
```

##### 1.3 注解 

- `@JsonFormat`

  - 序列化时格式日期字段，可指定时区，反序列化无效；
  - 指定日期格式后，需反序列化的 json 中的日期格式需按此指定格式，否则抛出异常，月份与日可个位数；

  ```java
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date createTime;
  ```

- `@JsonInclude`

  - 作用于类或属性上；

  - 序列化时指定属性在某些条件下可序列化json，例如属性不为null；

  ```java
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public class A {
  
      private String id;
      private String username;
  
      @JsonInclude(JsonInclude.Include.NON_EMPTY)
      private String password;
      
      // ...
  }
  ```

- `@JsonIgnore`

  - 作用与属性或 getter/setter 方法；
  - 序列化时忽略该属性；

- `@JsonIgnoreProperties`

  - 作用于类；功能类似`@JsonIgnore`；

  ```java
  @JsonIgnoreProperties(value = {"createTime","updateTime"})
  public class A {
  
      private String id;
      private String username;
      private String password;
      private Timestamp createTime;
      private Timestamp updateTime;
      // ...
  }
  ```

- `@JsonProperty`

  - 关联属性与 json 映射名，即取别名；

- `@JsonIgnoreType`

  - 作用于类；
  - 当此类作为其他类的属性时，将被忽略；

##### 1.4 说明

- 序列化时会序列化目标对象的所有属性，无论属性是否赋值；
- 反序列化时，json中存在的属性必须在目标对象中有对应的属性存在，json中属性可少但不可多；
- 若不使用`@JsonFormat`指定日期格式，则日期类型不支持 `yyyy年MM月dd日`、`yyyy/MM/dd`、`yyyy.MM.dd` 格式；且使用 `yyyy-MM-dd` 格式时注意月份与日需两位数，否则反序列化时抛出异常；

### 2、FastJson

##### 2.1 依赖

```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>fastjson</artifactId>
    <version>1.2.76</version>
</dependency>
```

##### 2.2 使用

```java
// 1、创建json对象

    // JSONObject extends JSON implements Map<String, Object>, ... 
	// 可当作 map 操作元素
    JSONObject jsonObject = new JSONObject(new HashMap<String, Object>());

    // JSONArray extends JSON implements List<Object>, ... 
	// 可当作 List 操作元素
    JSONArray jsonArray = new JSONArray(new ArrayList<Object>());

// 2、对象或集合 => json 字符串

	// JSON 类的静态方法
	String jsonStr = JSON.toJSONString(new Object()); // JSON.toJSON(account).toString();
	// JSON 子类实例对象方法
    String jsonString = jsonObject.toJSONString(); // JSONArray.toJSONString();

// 3、json 字符串 => java 对象
	Object obj = JSON.parseObject(jsonStr, Object.class);
```

##### 2.3 @JSONField

- 可配置在 属性字段或 getter/setter 方法上；
- 常用注解属性说明：
  - name：指定字段名称，类似于别名；
  - format：序列化时格式化日期，对日期字段起效，反序列化无效；
  - serialize/deserialize ：boolean 是否序列化/反序列化；
  - ordinal : int 序列化与反序列化顺序，默认首字母排序；

```java
public class A {
    
    @JSONField(name="ID")
    private int id;
    
    @JSONField(format = "yyyy-MM-dd")
    private Date createTime;

	// getter and setter
    
}
```

##### 2.3 说明

- 序列化时，为null的属性默认忽略；
- json 中的日期格式不支持`yyyy.MM.dd`，且`yyyy/MM/dd`月份与日必须为两位数，`yyyy-MM-dd`格式时当月份为两位数时，日必须也为两位数；
- 反序列化时，`@JSONField`中指定的日期格式不影响 json 字符串中的日期格式，而 jsckson 的`@JsonFormat`会影响；
- 反序列化时，json 字符串中的属性在目标对象属性中匹配到即可，多余属性或减少属性不影响反序列化；

### 3、Gson

##### 3.1 依赖

```xml
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.7</version>
</dependency>
```

##### 3.2 使用

```java
// 1、创建

// 方式一
Gson gson = new Gson();
// 方式二
GsonBuilder builder = new GsonBuilder();
Gson gson2 = builder.create();

// 2、序列化
String json = gson.toJson(account);

// 3、反序列化
Account account2 = gson.fromJson(json, Account.class);
```

##### 3.3 说明

- 默认忽略null值；
- j'son 字符串中的属性相较于目标对象的属性可多可少；

### 4、比较

| 名称 | Jackson                                                      | FastJson                                                     | Gson                                                    |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------- |
| 来源 |                                                              | alibaba                                                      | google                                                  |
| 原理 | 反射+反射缓存、良好的stream支持、高效的内存管理；            | jvm虚拟机：通过ASM库运行时生成parser字节码，支持的field不能超过200个；<br />android虚拟机：反射的方式； | 反射+反射缓存、支持部分stream、内存性能较差（gc问题）； |
| 使用 | 创建一个`ObjectMapper`对象；                                 | 可直接使用`JSON`的静态方法，节省了创建json处理对象的时间；   | 创建一个`Gson`对象；                                    |
| 其他 | Spring Boot 默认JSON类库；                                   | 处理复杂对象时可能会出现问题；                               |                                                         |
| 日期 | 若不使用`@JsonFormat`指定日期格式，则日期类型不支持 `yyyy年MM月dd日`、`yyyy/MM/dd`、`yyyy.MM.dd` 格式；且使用 `yyyy-MM-dd` 格式时注意月份与日需两位数，否则反序列化时抛出异常； | json 中的日期格式不支持`yyyy.MM.dd`，且`yyyy/MM/dd`月份与日必须为两位数，`yyyy-MM-dd`格式时当月份为两位数时，日必须也为两位数；<br />反序列化时，`@JSONField`中指定的日期格式不影响 json 字符串中的日期格式，而 jsckson 的`@JsonFormat`会影响； |                                                         |

