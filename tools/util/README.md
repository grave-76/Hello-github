[TOC]

# Enum

### 1、枚举转下拉字典

##### 1.1 枚举类

```java
public enum NationEnum
{
    汉族("汉族", "01"), 满族("满族", "02"), 蒙古族("蒙古族", "03"), 回族("回族", "04"), 藏族("藏族", "05"), 
    维吾尔族("维吾尔族", "06"), 苗族("苗族", "07"), 彝族("彝族", "08"), 壮族("壮族", "09"), 布依族("布依族", "10"), 
    侗族("侗族", "11"), 瑶族("瑶族", "12"), 白族("白族", "13"), 土家族("土家族", "14"), 哈尼族("哈尼族", "15"), 
    哈萨克族("哈萨克族", "16"), 傣族("傣族", "17"), 黎族("黎族", "18"), 傈僳族("傈僳族", "19"), 佤族("佤族", "20"), 
    畲族("畲族", "21"), 高山族("高山族", "22"), 拉祜族("拉祜族", "23"), 水族("水族", "24"), 东乡族("东乡族", "25"), 
    纳西族("纳西族", "26"), 景颇族("景颇族", "27"), 柯尔克孜族("柯尔克孜族", "28"), 土族("土族", "29"), 达斡尔族("达斡尔族", "30"), 
    仫佬族("仫佬族", "31"), 羌族("羌族", "32"), 布朗族("布朗族", "33"), 撒拉族("撒拉族", "34"), 毛南族("毛南族", "35"), 
    仡佬族("仡佬族", "36"), 锡伯族("锡伯族", "37"), 阿昌族("阿昌族", "38"), 普米族("普米族", "39"), 朝鲜族("朝鲜族", "40"), 
    塔吉克族("塔吉克族", "41"), 怒族("怒族", "42"), 乌孜别克族("乌孜别克族", "43"), 俄罗斯族("俄罗斯族", "44"), 鄂温克族("鄂温克族", "45"), 
    德昂族("德昂族", "46"), 保安族("保安族", "47"), 裕固族("裕固族", "48"), 京族("京族", "49"), 塔塔尔族("塔塔尔族","50"), 
    独龙族("独龙族", "51"), 鄂伦春族("鄂伦春族", "52"), 赫哲族("赫哲族", "53"), 门巴族("门巴族", "54"), 珞巴族("珞巴族", "55"), 
    基诺族("基诺族", "56");

    private NationEnum(String text, String value)
    {
        this.text = text;
        this.value = value;
    }

    /** 展示值 */
    private String text;
    
    /** 实际值 */
    private String value;

    /**
     * 展示值
     * @return the name
     */
    public String getText()
    {
        return text;
    }

    /**
     * 实际值
     * @return the value
     */
    public String getValue()
    {
        return value;
    }

    /**
     * @Description 获取展示值
     * @param value 实际值
     * @return
     */
    public static String getText(String value)
    {
        Optional<NationEnum> item = Stream.of(values()).filter(t -> t.getValue().equals(value)).findFirst();
        return item.isPresent() ? item.get().getText() : "";
    }

    /**
     * @Description 获取实际值
     * @param name 展示值
     * @return
     */
    public static String getValue(String name)
    {
        Optional<NationEnum> item = Stream.of(values()).filter(t -> t.getText().equals(name)).findFirst();
        return item.isPresent() ? item.get().getValue() : "";
    }

}
```

##### 1.2 字典项

```java
public class DicItems
{
    /** 字典数据名称 */
    private String name;
    /** 字典列表 */
    private List<Map<String, Object>> values;

    public DicItems(String name, List<Map<String, Object>> values)
    {
        this.name = name;
        this.values = values;
    }

    // .......

}
```

##### 1.3 转换工具类

```java
/**
* 若使用该工具类，则相关枚举类需按规范定义
*/
public class DictUtil
{
    /** 缓存map<类全路径名，字典数据> */
    private static Map<String, DicItems> cacheMap = Maps.newConcurrentMap();

    private DictUtil()
    {
    }

    /**
     * 构建字典项
     * @param name 字典名称
     * @param t 枚举类
     * @return
     */
    public static <T> DicItems buildDicItems(String name, Class<T> t)
    {
        DicItems result = null;
        try
        {
            if (!t.isEnum())
            {
                throw new BaseRuntimeException("请传入枚举类！");
            }
            String className = t.getName(); // 全路径类名 
            if (cacheMap.containsKey(className))
            {
                return cacheMap.get(className);
            }

            // 1、获取相关字典数据
            List<Map<String, Object>> itemList = Lists.newArrayList();
            Method m = null;
            Map<String, Object> item = null;
            for (Object object : t.getEnumConstants())
            {
                item = Maps.newHashMap();
                // 获取预定义方法（枚举类定义需规范）
                m = object.getClass().getDeclaredMethod("getText");
                item.put("text", String.valueOf(m.invoke(object)));
                m = object.getClass().getDeclaredMethod("getValue");
                item.put("value", m.invoke(object));

                itemList.add(item);
            }
            // 2、组装字典项
            result = new DicItems(name, itemList);
            // 3、缓存数据
            cacheMap.put(className, result);
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
    
}
```

# Validate

### 1、javax.validation 注解校验

##### 1.1 实体字段使用注解校验

```java
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public class MyModel
{
    @NotBlank(message = "【名称】不可为空")
    @Length(max = 50, message = "【名称】长度不可超出{max}")
    private String name;

    @NotNull(message = "【年龄】不可为空")
    @Range(min = 1, max = 999, message = "【年龄】范围应为{min}-{max}")
    private Integer age;
    
    @NotNull(message = "【手机号】不可为空")
    @Pattern(regexp = "(1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8})", message = "【手机号号】格式不正确")
    private String phone;

    // ......

}
```

##### 1.2 校验工具

```java
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class ValidateUtils
{
    private ValidateUtils()
    {

    }

    /**
     * 校验对象字段-并将错误信息一起抛出
     * @param obj
     */
    public static void validateThenException(Object obj)
    {
        StringBuilder sb = new StringBuilder();
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(obj);
        if (CollectionUtils.isEmpty(constraintViolations))
        {
            return;
        }
        for (ConstraintViolation<Object> constraintViolation : constraintViolations)
        {
            sb.append(constraintViolation.getMessage() + ";");
        }
        if (sb.length() > 0)
        {
            throw new BaseRuntimeException(sb.toString());
        }
    }

    /**
     * 校验对象字段-并返回所有错误信息
     * @param obj 对象
     * @param propertyName 属性名-若不为null则校验整个对象属性
     * @param lastChar 末尾字符-null时默认句号
     */
    public static String validateFields(Object obj, String propertyName, String lastChar)
    {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = null;
        // 校验对象 | 校验对象某个属性
        if (StringUtils.isEmpty(propertyName))
        {
            constraintViolations = validator.validate(obj);
        } else
        {
            constraintViolations = validator.validateProperty(obj, propertyName);
        }
        // 处理错误信息
        if (CollectionUtils.isEmpty(constraintViolations))
        {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation<Object> constraintViolation : constraintViolations)
        {
            sb.append(constraintViolation.getMessage() + ";");
        }
        if (sb.length() > 0)
        {
            lastChar = lastChar != null ? lastChar : "。";
            sb.replace(sb.length() - 1, sb.length(), lastChar);
        }
        return sb.toString();
    }

}
```

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

# 真实IP地址

```java
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

public class IPUtils
{
    private IPUtils()
    {

    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值 如果使用了nginx代理
     * 记得在代理中把真实IP转发过来。
     * @return ip
     */
    public static String getRealIP(HttpServletRequest request)
    {
        String[] headers =
        { "X-Forwarded-For", "X-Real-IP", "Proxy-Client-IP", "WL-Proxy-Client-IP", "HTTP_CLIENT_IP",
                "HTTP_X_FORWARDED_FOR" };

        String ip;
        for (String header : headers)
        {
            ip = request.getHeader(header);
            if (!isUnknow(ip))
            {
                return getMultistageReverseProxyIp(ip);
            }
        }

        ip = request.getRemoteAddr();
        // 本机
        if ("127.0.0.1".equals(ip))
        {
            try
            {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e)
            {
                e.printStackTrace();
            }
        }
        return ip;
    }

    /**
     * 从多级反向代理中获得第一个非unknown IP地址
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    private static String getMultistageReverseProxyIp(String ip)
    {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(",") > 0)
        {
            final String[] ips = ip.trim().split(",");
            for (String subIp : ips)
            {
                if (!isUnknow(subIp))
                {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br>
     * @param checkString 被检测的字符串
     * @return 是否未知
     */
    private static boolean isUnknow(String checkString)
    {
        return StringUtil.isEmpty(checkString) || "unknown".equalsIgnoreCase(checkString);
    }
	
}
```

# 二维码

```java
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.bw.excel.common.utils.StreamUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 二维码工具类
 * @author yuhuihuang
 *
 * 2021-6-25 13:34:06
 */
public class QRCodeUtil
{

    private static final Logger logger = LoggerFactory.getLogger(QRCodeUtil.class);

    // 二维码颜色  
    private static final int BLACK = 0xFF000000;
    // 二维码颜色  
    private static final int WHITE = 0xFFFFFFFF;
    // 二维码宽 
    private static final int qrWidth = 175;
    // 二维码高
    private static final int qrHeight = 175;
    // 二维码生成格式
    private static final String imageType = "png";
    // LOGO宽度
    private static final int logoWidth = 30;
    // LOGO高度
    private static final int logoHeight = 30;
    
    private static final String imagePath = "/erc.png";
    
    /** 
     * 生成二维码BufferedImage
     * @param text    二维码内容
     */
    public static BufferedImage codeBufferCreate(String text)
    {
        Map<EncodeHintType, Object> his = new HashMap<EncodeHintType, Object>();
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        his.put(EncodeHintType.MARGIN, 1);
        BufferedImage image = null;
        try
        {
            // 1、生成二维码  
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, qrWidth, qrHeight, his);

            // 2、获取二维码宽高  
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            // 3、将二维码放入缓冲流  
            image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++)
            {
                for (int j = 0; j < codeHeight; j++)
                {
                    // 4、循环将二维码内容定入图片  
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }
            
        } catch (Exception e)
        {
            logger.error("生成二维码图片失败", e);
        } 
        return image;
    }
    
    
    /** 
     * 生成二维码
     * @param text    二维码内容
     */
    public static String codeCreate(String text)
    {
        Map<EncodeHintType, Object> his = new HashMap<EncodeHintType, Object>();
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        his.put(EncodeHintType.MARGIN, 1);
        String base64 = null;
        ByteArrayOutputStream stream = null;
        try
        {
            // 1、生成二维码  
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, qrWidth, qrHeight, his);

            // 2、获取二维码宽高  
            int codeWidth = encode.getWidth();
            int codeHeight = encode.getHeight();

            // 3、将二维码放入缓冲流  
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++)
            {
                for (int j = 0; j < codeHeight; j++)
                {
                    // 4、循环将二维码内容定入图片  
                    image.setRGB(i, j, encode.get(i, j) ? BLACK : WHITE);
                }
            }

            //5、插入图片
            QRCodeUtil.insertImage(image);
            //6、将二维码写入图片  
            stream = new ByteArrayOutputStream();
            ImageIO.write(image, imageType, stream);
            base64 = Base64.getEncoder().encodeToString(stream.toByteArray());
            stream.flush();
        } catch (Exception e)
        {
            logger.error("生成二维码图片失败", e);
        } finally
        {
            StreamUtil.closeStream(stream);
        }
        return "data:image/png;base64," + base64;
    }

    private static void insertImage(BufferedImage source) throws Exception
    {
        ClassPathResource classPathResource = new ClassPathResource(imagePath);
        InputStream inputStreamImg = classPathResource.getInputStream();
        Image src = ImageIO.read(inputStreamImg);
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        // 压缩LOGO
        if (width > logoWidth)
        {
            width = logoWidth;
        }
        if (height > logoHeight)
        {
            height = logoHeight;
        }
        Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.drawImage(image, 0, 0, null); // 绘制缩小后的图
        g.dispose();
        src = image;
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (qrWidth - width) / 2;
        int y = (qrHeight - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }
}
```

# 
