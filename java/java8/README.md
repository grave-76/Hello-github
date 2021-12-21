[TOC]

# Interface

- 新`interface`新增默认方法（`default`修饰）与静态方法（`static`修饰）；
- 默认方法-`default`
  - 修饰的方法是普通实例方法，可以用`this`调用，可以被继承、重写；
  - 可以在不破坏现有架构情况下在接口中新增方法；
- 静态方法-`static`
  - 修饰的方法，为静态方法，只能用`interface`调用；
  - 可在接口中调用程序入口`main()`方法；

- 接口和抽象类
  - 接口多实现，类单继承；
  - 接口方法只能是`public abstract`修饰；变量是`public static final`修饰；抽象类可以用其他修饰符；

# 函数式接口

- 也称为SAM接口（single Abstract Method interfaces）；

- 有且只有一个抽象方法，但可以有多个非抽象方法；

- 广泛应用于`Lambda`表达式中；

- 自定义函数式接口

  ```java
  @FunctionalInterface // 该注解只是在编译期起到强制规范定义的作用，可不使用
  public interface LambdaInterface {
  	void customInterface();
  }
  ```

# Lambda 表达式

### 简介

- 是一个匿名函数，Java8 允许将函数作为参数传递进方法中；

  ```java
  // parameters 可有可无，且无需指定数据类型
  (parameters) -> expression 
  或
  (parameters) ->{ statements; }
  ```

- 可以引用外部变量，但该变量默认拥有`final`属性，不可被修改；

### 用法

- 代替匿名内部类

  ```java
  // Java8 之前
  new Thread(new Runnable() {
              @Override
              public void run() {
                  System.out.println("The runable now is using!");
              }
  }).start();
  
  // Java8 用lambda
  new Thread(() -> System.out.println("It's a lambda function!")).start();
  
  ```

- 方法引用

  - Java8 使用 `::`关键字传递方法或者构造函数引用；
  - 可引用静态方法、实例方法、构造方法；

# Stream 流

### 简介

- Java8 新增`java.util.stream`包，和之前的流大同小异；
- `Stream` 不存储数据，不改变数据源，但可以检索和逻辑处理集合数据（筛选、排序、统计等），可理解为`sql`语句；
- 它的方法参数都是函数式接口，通常与`Lambda`表达式一同使用；
- 分为`stream`（串行流）和`parallelStream`（并行流，可多线程执行）；
- 一个`stream`流只能操作一次，一次过后就关闭；

### 使用

- 待完善

# Optinoal 类

- 用于防止`NPE`（`java.lang.NullPointerException`）；

  ```java
  /**
  * Common instance for {@code empty()}. 全局EMPTY对象
  */
  private static final Optional<?> EMPTY = new Optional<>();
  
  /**
  * Optional维护的值
  */
  private final T value;
  
  /**
  * 如果value是null就返回EMPTY，否则就返回of(T)
  */
  public static <T> Optional<T> ofNullable(T value) {
     return value == null ? empty() : of(value);
  }
  /**
  * 返回 EMPTY 对象
  */
  public static<T> Optional<T> empty() {
     Optional<T> t = (Optional<T>) EMPTY;
     return t;
  }
  /**
  * 返回Optional对象
  */
  public static <T> Optional<T> of(T value) {
      return new Optional<>(value);
  }
  /**
  * 私有构造方法，给value赋值
  */
  private Optional(T value) {
    this.value = Objects.requireNonNull(value);
  }
  /**
  * 所以如果of(T value) 的value是null，会抛出NullPointerException异常，这样貌似就没处理NPE问题
  */
  public static <T> T requireNonNull(T obj) {
    if (obj == null)
           throw new NullPointerException();
    return obj;
  }
  ```

- `ofNullable()` 与 `of()` 的区别是当`value`为`null`时，`ofNullable()` 返回 `Empty`，`of`则会抛出 `NullPointerException` 异常；

- `flatMap()` 参数返回值如果是 `null` 会抛 `NullPointerException`，而 `map()` 返回`EMPTY`；

- 判断`value`是否为`null`

  ```java
  /**
  * value是否为null
  */
  public boolean isPresent() {
      return value != null;
  }
  /**
  * 如果value不为null执行consumer.accept
  */
  public void ifPresent(Consumer<? super T> consumer) {
     if (value != null)
      consumer.accept(value);
  }
  ```

- 获取 `value`

  ```java
  /**
  * 如果value != null 返回value，否则返回other的执行结果
  */
  public T orElseGet(Supplier<? extends T> other) {
      return value != null ? value : other.get();
  }
  
  /**
  * 如果value != null 返回value，否则返回T	
  */
  public T orElse(T other) {
      return value != null ? value : other;
  }
  
  /**
  * 如果value != null 返回value，否则抛出参数返回的异常
  */
  public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
          if (value != null) {
              return value;
          } else {
              throw exceptionSupplier.get();
          }
  }
  /**
  * value为null抛出NoSuchElementException，不为空返回value。
  */
  public T get() {
    if (value == null) {
        throw new NoSuchElementException("No value present");
    }
    return value;
  }
  
  ```

- 过滤值

  ```java
  /**
  * 1. 如果是empty返回empty
  * 2. predicate.test(value)==true 返回this，否则返回empty
  */
  public Optional<T> filter(Predicate<? super T> predicate) {
          Objects.requireNonNull(predicate);
          if (!isPresent())
              return this;
          else
              return predicate.test(value) ? this : empty();
  }
  ```

# Date-Time API

### java.time 包主要时间类

- Java8 新增`java.time`包，简化日期转换、计算、获取指定日期等功能；

  - `LocalDateTime.class`：日期+时间 `yyyy-MM-ddTHH:mm:ss.SSS`；
  - `LocalDate.class`：日期 `yyyy-MM-dd`；
  - `LocalTime.class`：时间 `HH:mm:ss`；

- 格式化

  ```java
  // Java8 之前
  	Date now = new Date();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String date  = sdf.format(now);
      System.out.println(String.format("date format : %s", date));
  
  // Java8 后
  	LocalDateTime dateTime = LocalDateTime.now();
      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String dateTimeStr = dateTime.format(dateTimeFormatter);
      System.out.println(String.format("dateTime format : %s", dateTimeStr));
  ```

- 字符串转日期

  ```java
  // Java8 之前
  	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  Date date1 = sdf.parse("2021-12-12");
  
  // Java8 后
  	LocalDate date1 = LocalDate.of(2021, 1, 26);
      LocalDate date2 = LocalDate.parse("2021-12-12");
  
      LocalDateTime dateTime1 = LocalDateTime.of(2021, 1, 26, 12, 12, 22);
      LocalDateTime dateTime2 = LocalDateTime.parse("2021-12-12 13:14:22");
  
      LocalTime time1 = LocalTime.of(13, 14, 22);
      LocalTime time2 = LocalTime.parse("13:14:22");
  ```

- JDBC 对应 类型

  - `Date` ---> `LocalDate`
  - `Time` ---> `LocalTime`
  - `Timestamp` ---> `LocalDateTime`

### 时区

- 时区：正式的时区划分为每隔经度 15° 划分一个时区，全球共 24 个时区，每个时区相差 1 小时；

- 但为了行政上的方便，常将 1 个国家或 1 个省份划在一起，比如我国幅员宽广，大概横跨 5 个时区，实际上只用东八时区的标准时即北京时间为准；

- `java.util.Date` 本身不支持国际化，需要借助`java.util.TimeZone`

  - 北京时间：`TimeZone.getTimeZone("Asia/Shanghai")`；
  - 东京时间：`TimeZone.getTimeZone("Asia/Tokyo")`；

- Java8 新特性中引入`java.time.ZonedDateTime`表示带时区的时间，可看成是`LocalDateTime + ZoneId`

  ```java
  // 当前时区时间
  ZonedDateTime zonedDateTime = ZonedDateTime.now();
  
  // 东京时间-JST
  ZoneId zoneId = ZoneId.of(ZoneId.SHORT_IDS.get("JST"));
  ZonedDateTime tokyoTime = zonedDateTime.withZoneSameInstant(zoneId);
  
  // ZonedDateTime 转 LocalDateTime
  LocalDateTime localDateTime = tokyoTime.toLocalDateTime();
  
  //LocalDateTime 转 ZonedDateTime
  ZonedDateTime localZoned = localDateTime.atZone(ZoneId.systemDefault());
  ```

# 