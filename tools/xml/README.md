[TOC]

# JAXB

> 能够使用Jackson对JAXB注解的支持实现(jackson-module-jaxb-annotations)，既方便生成XML，也方便生成JSON，这样一来可以更好的标志可以转换为JSON对象的JAVA类。

### xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<country name="中国" code="China">
	<province name="江西省">
		<city>南昌</city>
		<city>赣州</city>
		<city>上饶</city>
	</province>
	<province name="浙江省">
		<city>杭州</city>
		<city>金华</city>
	</province>
	<province name="广东省">
		<city>深圳</city>
		<city>河源</city>
		<city>东莞</city>
	</province>
</country>
```

### Java对象

```java
// 省份对象
@XmlRootElement(name = "country") // 根节点
@XmlAccessorType(XmlAccessType.FIELD) // 使用类中非静态字段作为XML的序列化属性
public class CountryInfo
{
    /** 国家名称 */
    @XmlAttribute(name = "name") // 节点属性
    private String name;
    
    /** 编码 */
    @XmlAttribute(name = "code")
    private String code;
    
    /** 省份列表 */
    @XmlElement(name = "province") // 节点元素
    private List<ProvinceInfo> cityList;
    
    // getter and setter
}

// 城市对象
@XmlAccessorType(XmlAccessType.FIELD)
public class ProvinceInfo
{
    /** 省份名称 */
    @XmlAttribute(name = "name")
    private String name;
    
    /** 城市列表 */
    @XmlElement(name = "city")
    private List<String> cityList;
    
    // getter and setter
}
```

### 解析工具类

```java
public class XmlUtil
{
    private static final Log LOG = LogFactory.getLog(XmlUtil.class);
    
    private XmlUtil() {
        
    }

    /**
     * xml文件转java对象
     * @param clazz 对象类型
     * @param filePath 文件路径
     * @return
     */
    public static <T> T xmlToBean(Class<T> clazz, String filePath) {
        return xmlToBean(clazz, new File(filePath));
    }

    /**
     * xml文件转java对象
     * @param clazz 对象类型
     * @param file 文件对象
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlToBean(Class<T> clazz, File file) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return t;
    }

    /**
     * 对象转xml
     * @param obj
     * @return
     */
    public static String beanToXml(Object obj) {
        
        StringWriter writer = new StringWriter();
        try {
            getMarshaller(obj).marshal(obj, writer);
        } catch (JAXBException | NullPointerException e) {
            LOG.error("捕捉异常信息:" + e.getMessage(), e);
        }
        return writer.toString();
    }
    
    /**
     * 对象序列化
     * @param obj
     * @return
     * @throws JAXBException
     */
    public static Marshaller getMarshaller(Object obj) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化输出
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // 编码格式,默认为utf-8
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false); // 是否省略xml头信息
        
        return marshaller;
    }
    
}
```

# DOM

# SAX

# JDOM

# DOM4J

