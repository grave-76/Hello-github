# 1、MySQL 数据类型

> **数值**

| 类型        | 大小（byte）                             | 范围（无符号X2）                | 说明         | 对应Java类型                      |
| ----------- | ---------------------------------------- | ------------------------------- | ------------ | --------------------------------- |
| tinyint     | 1                                        | (-128，127)                     | 小整数值     | java.lang.Integer                 |
| smallint    | 2                                        | (-32 768，32 767)               | 大整数值     | java.lang.Integer                 |
| mediumint   | 3                                        | (-8 388 608，8 388 607)         | 大整数值     | java.lang.Integer                 |
| int/integer | 4                                        | (-2 147 483 648，2 147 483 647) | 大整数值     | java.lang.Integer（无符号是Long） |
| bigint      | 8                                        |                                 | 极大整数值   | java.math.BigInteger              |
| float       | 4                                        |                                 | 单精度浮点数 | java.lang.Float                   |
| double      | 8                                        |                                 | 双精度浮点数 | java.lang.Double                  |
| decimal     | 对DECIMAL(M,D) ，如果M>D，为M+2否则为D+2 | 取决于M和D的值                  | 小数值       | java.math.BigDecimal              |

> **日期和时间**

| 类型      | 大小（byte） | 格式                | 范围                                    | 说明             | 对应Java类型       |
| --------- | ------------ | ------------------- | --------------------------------------- | ---------------- | ------------------ |
| date      | 3            | YYYY-MM-DD          | 1000-01-01/9999-12-31                   | 日期值           | java.sql.Date      |
| time      | 3            | HH:MM:SS            |                                         | 时间值或持续时间 | java.sql.Time      |
| year      | 1            | YYYY                |                                         | 年份值           | java.sql.Date      |
| datetime  | 8            | YYYY-MM-DD HH:MM:SS | 1000-01-01 00:00:00/9999-12-31 23:59:59 | 混合日期和时间值 | java.sql.Timestamp |
| timestamp | 4            | YYYYMMDD HHMMSS     | 1970-01-01 00:00:00/2038                | 时间戳           | java.sql.Timestamp |

> **字符串**

| 类型       | 大小（byte）    | 说明                            | 对应Java类型     |
| ---------- | --------------- | ------------------------------- | ---------------- |
| char       | 0-255           | 定长字符串                      | java.lang.String |
| varchar    | 0-65535         | 变长字符串                      | java.lang.String |
| tinyblob   | 0-255           | 不超过 255 个字符的二进制字符串 | byte[]           |
| tinytext   | 0-255           | 短文本字符串                    | java.lang.String |
| blob       | 0-65535         | 二进制形式的长文本数据          | byte[]           |
| text       | 0-655           | 长文本数据                      | java.lang.String |
| mediumblob | 0-16 777 215    | 二进制形式的中等长度文本数据    | byte[]           |
| mediumtext | 0-16 777 215    | 中等长度文本数据                | java.lang.String |
| longblob   | 0-4 294 967 295 | 二进制形式的极大文本数据        | byte[]           |
| longtext   | 0-4 294 967 295 | 极大文本数据                    | java.lang.String |

# 2、数据语言

### 1）操作数据库

- 创建数据库

  ```sql
  CREATE DATABASE [IF NOT EXISTS] myDataBase;
  ```

- 删除数据库

  ```sql
  DROP DATABASE [IF EXISTS] myDataBase;
  ```

- 使用数据库

  ```sql
  USE myDataBase;
  ```

- 查看数据库

  ```sql
  SHOW DATABASES myDataBase;
  ```

### 2）DDL

> 数据定义语言 Data Defination Language

- 建表

  ```sql
  CREATE TABLE `tb_user` (
    `id` int(11) NOT NULL,
    `name` varchar(30) NOT NULL COMMENT '名称',
    `age` int(3) unsigned DEFAULT NULL COMMENT '年龄',
    `address` varchar(200) DEFAULT NULL COMMENT '地址',
    `money` decimal(16,4) unsigned zerofill DEFAULT NULL COMMENT '钱',
    `create_time` datetime DEFAULT NULL COMMENT '创建日期',
    `update_time` datetime DEFAULT NULL COMMENT '更新日期',
    PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
  ```

- 修改表

  ```sql
  -- 修改表名
  ALTER TABLE `tb_user` RENAME AS `newName`;
  
  -- 删除表字段
  ALTER TABLE `tb_user` DROP `age`
  
  -- 添加表字段
  ALTER TABLE `tb_user` ADD `age` INT(3);
  
  -- 修改表字段
  ALTER TABLE `tb_user` CHANGE `age1` `age` INT(10);
  
  -- 修改表的属性名字
  -- modify 方式
  ALTER TABLE `tb_user` MODIFY `age` INT(10);
  -- change 方式：后面紧跟要修改的字段与指定新的字段名及类型
  ALTER TABLE `tb_user` CHANGE `age` `new_age` INT(3);
  ```

- 删除表

  ```sql
  -- drop 删除表数据与结构
  DROP TABLE `tb_user`;
  -- truncate 删除表数据
  truncate `tb_user`;
  ```

### 3）DML

> 数据操作语言 Data Manipulation Language

- 新增

  ```mysql
  -- 插入一条数据（若不指定特定字段，后面字段数据要一一对应）
  insert into `tb_user` (`id`, `name`) values (1, '张三');
  
  -- 插入多个语句，英文逗号隔开
  insert into `tb_user` (`id`, `name`) values (2, '李四'), (3, '王五');
  ```

- 修改

  ```mysql
  -- 修改指定数据
  update `tb_user` set `name` = '张三三', age = 18 where `id` = 1;
  -- 不带条件将修改所有数据
  update `tb_user` set `name` = '张三三', age = 18;
  ```

- 删除

  ```mysql
  -- 不带条件将删除所有数据
  delete from `tb_user` where `id` = 1;
  -- 若给表设置了别名
  delete t from `tb_user` t;
  ```

### 4）DQL

> 数据查询语言 Data Query Language 

- 查询

  ```mysql
  -- 查询指定数据
  select `id`, `name`, `age` from `tb_user`;
  -- 查询所有
  select * from `tb_user`;
  ```

- 多条件查询

  ```mysql
  -- 
  select * from `tb_user` where `age` > 20 group by `money` having `money` > 100 order by `money` desc;
  ```

- 联表查询

  ```mysql
  -- 内联接/等值连接
  select * from `tb_user` t inner join `tb_deptment` d on t.dept_id = d.id;
  -- 左连接left join、右连接right join 语法一致，返回数据不一致
  -- 无论是否匹配条件，都会返回左、右连接表的所有数据
  select * from `tb_user` t left join `tb_deptment` d on t.dept_id = d.id;
  ```

- 子查询

  ```mysql
  -- 子查询数据作为结果
  select `name`, (select `name` from `tb_deptment` where `id` = 1 ) as deptName 
  from `tb_user` where `id` = 1;
  -- 子查询作为条件
  select `name` from `tb_user` where `dept_id` = (select `id` from `tb_deptment` where `id` = 1);
  -- 多重嵌套
  select * from 
  (select * from `tb_user` where `dept_id` <> 1) t where `id` = 1;
  ```

### 5）DCL

> 数据控制语言 Data Control Language

- 权限

  ```mysql
  -- grant、revoke
  ```

# 3、注意事项

### 1）where 与 having

- where在分组前对数据进行过滤；
- having则是在分组后对数据进行过滤；
- where后面不能跟聚合函数；

### 2）where 与 on

- 联表查询会生成一张临时表；

- `on`是生成临时表时使用的条件，在左连接（或右连接）时，不管`on`后面的条件是否起到作用，都会返回左表（或右表）的所有数据；
- `where`是在生成临时表之后的过滤条件；
- 若是内连接则`where`与`on`无区别；

# 4、索引

### 1）简介

- 索引是对数据库表中一列或多列的值进行排序的一种**数据结构**，包含着对数据表里记录的引用指针；
- 索引可类比为书籍的目录，可通过索引快速访问数据表中特定信息；
- 用途
  - 排序
  - 快速查找
- 索引存储在索引表中，该表保存了数据的主键与索引字段，并指向实体表的记录；
- 优点
  - 提高数据检索效率，降低数据库IO成本；
  - 可以帮助服务器避免排序和创建临时表；
  - 通过索引列对数据排序（将随机IO转变为顺序IO），降低数据排序成本（CPU的消耗）;
  - 通过创建唯一索引可以保证一条数据的唯一性；
- 缺点
  - 索引需要占用物理空间（储存于磁盘）；
  - 每次更新表（插入、更新、删除数据）都需要动态维护索引文件；
  - 对于数据量较小的表，大部分情况下全表扫描更高效；

### 2）索引原则

- 需要建立索引的列
  - 主键强制建立唯一索引；
  - 经常需要搜索的列；
  - 经常用于连接（join）的列；
  - 范围搜索和排序的列（因为索引已经排好序）；
  - 统计或分组的列；
- 不应建立索引的列
  - 数据量较小的表不应该建立索引；
  - 重复值多的列，例如性别、国籍等，检索的结果集占表中总数据很大比例，检索效率不高；
  - 大文本或超长字段不适合建立索引；
  - 修改需求远远大于检索需求时不适合建立索引，索引的建立能够提升检索性能，但会降低修改性能；

### 3）索引分类

- 普通索引

  - `index` / `key`
  - 最基本的索引，无限制，值可以为空；

  ```mysql
  -- 直接创建索引
  create index index_name on `tb_user`(`name`(50));
  -- 修改表结构从而创建索引
  alter table `tb_user` add index index_name (`name`(50));
  -- 删除索引
  drop index index_name on `tb_user`;
  ```

- 唯一索引

  - `unique index`
  - 与普通索引唯一不同的是索引列的值在表中必须唯一；

- 主键索引

  - `primary key`
  - 主键索引是一种特殊的唯一索引，一张表只能存在一个主键索引，不允许空值；

- 组合索引

  - 指将多列组成一个索引；

  ```mysql
  alter table `tb_user` add index name_age (`name`, `age`);
  ```

- 全文索引

  - `fulltext`
  - 主要用来查找文本中的关键字，并非等值查询；
  - 对于大数据量，全文检索比 like 的模糊查询更为高效；

  ```mysql
  -- 创建全文索引
  create fulltext index fulltext_name_address on `tb_user` (`name`, `address`);
  -- 修改表结构方式
  alter table `tb_user` add fulltext index fulltext_name_address on (`name`, `address`);
  ```

### 4）explain

- 通过explain 命令获取 select 语句的执行计划，可以知道表的读取顺序、哪些索引被使用等信息

  ```mysql
  explain select * from `tb_user` where name = '张三';
  ```

  ![image-20210904222949347](README.assets/image-20210904222949347.png)

- id

  - select 查询序号，表示查询中执行select子句或操作表的顺序；
  - id越大，执行优先级越高；id相同，执行顺序从上到下;

- select_type

  - select语句的类型，有以下几种；
  - simple：表示简单查询，不包括连接查询与子查询；
  - primary：复杂查询中最外层的查询语句；
  - union：表示连接查询的第二个或后面的查询语句，不依赖于外部查询的结果集；
  - dependent union：同union，但依赖于外部查询；
  - union result：连接查询的结果；
  - subquery：子查询中的第一个语句，不依赖于外部查询的结果集；
  - ......

- table

  - 表示访问的表；

- partitions

- type

  - 关联类型或访问类型；
  - system：仅有一行的系统表，const的一个特例；
  - const：数据表最多只有一个匹配行，只读取一次，速度快；
  - eq_ref：最多只会返回一条符合条件的记录，primary key或 unique key 索引被使用；
  - ref：相比 eq_ref 不使用唯一索引，可能匹配到多条记录；
  - ref_or_null：类似ref, 但可以搜索值为NULL的行；
  - index_merge：表示使用索引合并优化；
  - range：使用一个索引检索指定范围的行；
  - index：全表扫描索引树；
  - all：全表扫描；

- possible_keys

  - 可能使用的索引；

- key

  - 实际采用哪个索引进行优化；
  - 若possible_key列有值，key列为NULL，则表示mysql认为索引对此查询帮助不大，选择了全表扫描；

- key_len

  - 实际使用的索引的字节数，计算规则如下；

  - 字符串

  - - char(n)：n字节长度
    - varchar(n)：2字节存储字符串长度，如果是utf-8，则长度 3n + 2

  - 数值类型

  - - tinyint：1字节
    - smallint：2字节
    - int：4字节
    - bigint：8字节　　

  - 时间类型

  - - date：3字节
    - timestamp：4字节
    - datetime：8字节

  - 如果字段允许为 NULL，需要1字节记录是否为 NULL

  - 索引最大长度是768字节，当字符串过长时，mysql会做一个类似左前缀索引的处理，将前半部分的字符提取出来做索引；

- ref

  - 表示使用哪一列或常数与索引一起检索数据；

- rows

  - 表示必须检索的行数；

- Extra
  - 额外信息。

### 5）索引结构

### 6）索引失效

- 范围搜索
  - 符合范围搜索的数据量超过总数据量的10%时，索引会失效，此时mysql优化器会将索引查询优化为全表扫描；
  - 范围之后的索引列也会生效；

- 索引列上进行相关操作
  - 在索引列上进行计算、函数、类型转换等，会导致索引失效进而变成全表扫描；
- 不符合最左前缀原则
  - 多列组合查询，左边列有索引时后面跟着的列的索引才会生效；
  - where a = 1 and b = 2 and c = 3；a有索引b索引才会生效，a b都有索引c索引才会生效；
- 使用 不等于
  - where age != 20；不等于会使索引失效，致使全表扫描；

# 5、其他

### 1）存储过程

### 2）视图

### 3）游标

# 6、体系结构

### 1）层级划分

### 2）存储引擎

### 3）索引结构
