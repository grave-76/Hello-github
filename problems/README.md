[TOC]



# DataBase

### 特殊字符如%_等进行like模糊查询问题

> `sql` 语句使用`escape`关键字；



```sql
select name, code FROM tb_user WHERE name LIKE '%/%%' ESCAPE '/' and code like '%/_%' ESCAPE '/';
```

> 使用`MyBatis`拦截器

