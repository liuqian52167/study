# 第一章 mybatis 入门
mybatis是一个优秀的支持自定义sql查询、存储和高级映射的持久层框架，消除了
几乎所有的jdbc方法和参数的手动设置以及结果集的检索。mybatis可以使用xml或者注解的
形式进行配置和映射，mybatis通过参数映射daosql
## 1.1 创建maven项目
## 1.2 简单配置让mybatis 跑起来
### 1.2.1 准备数据库
### 1.2.2 配置mybatis
### 1.2.3 创建实体类和Mapper.xml文件
### 1.2.4 配置Log4j以便查看mybatis操作数据库的过程
### 1.2.5 编写测试代码让mybatis 跑起来
## 1.3 总结

# 第二章Mybatis xml方式的基本用法
## 2.1简单的权限控制需求
### 2.1.1 创建数据表
### 2.1.2 创建实体类
## 2.2 使用xml方式
## 2.3 select 用法
## 2.4 insert 用法
### 2.4.1简单的insert方法
### 2.4.2 使用jdbc返回主键自增的值
### 2.4.3使用selectKey 返回主键的值
## 2.5 update 用法
## 2.6 delete 用法
## 2.7 多个接口参数的用法
## 2.8 mapper接口动态代理的实现原理
## 2.9 小结

# 第三章 mybatis 注解方式的基本用法
## 3.1@select注解
### 3.1.1 mapUnderscoreToCamelCase配置
### 3.1.2 使用resultMap方式
## 3.2@Insert
### 3.2.1 不需要返回主键
### 3.2.2 返回自增主键
### 3.2.3 返回非自增主键
## 3.3@Upadate和delete 注解
## 3.4Provider 注解

# 第四章 mybatis 动态sql
## 4.1 if 用法
### 4.1.1 在where 条件中使用if
### 4.1.2 在update更新列中使用if
### 4.1.3 在insert动态插入列中使用if

## 4.2 choose用法
## 4.3 where set trim用法
### 4.3.1where用法
### 4.3.2 set用法
### 4.3.3 trim用法
## 4.4foreach用法
### 4.4.1 foreach实现IN集合
### 4.4.2 foreach实现批量插入
### 4.4.3 foreach实现动态update
## 4.5 bind用法
## 4.6 多数据库支持
## 4.7 ognl用法
## 小结

# 第五章 mybatis 代码生成器
##5.1 xml配置
### 5.1.1 property标签
### 5.1.2plugin标签
### 5.1.3 commentGenerator标签
### 5.1.4 jdbcConnection标签
### 5.1.5JavaTypeResolver标签
### 5.1.6 JavaModelGenerator 标签
### 5.1.7 sqlmapGenerator 标签
### 5.1.8 JavaClientGennerator标签
### 5.1.9 table标签
## 5.2 配置的参考示例
## 5.3 运行mybatisGenerator
### 5.3.1 使用Java编写代码运行
### 5.3.2 从命令符运行
### 5.3.3 使用mavenPlugin运行
### 5.3.4 使用eclipse插件运行
## 5.4 Example 介绍
## 5.5 总结

# 第六章 mybatis 高级查询
## 6.1 高级结果映射
### 6.1.1一对一映射
### 6.1.2 一对多映射
### 6.1.3 鉴别器映射
##６.2存储过程
### 6.2.1第一个存储过程
### 6.2.2 在Oracle中使用游标参数的存储过程
##6.3 使用枚举类型或其他类型
### 6.3.1 使用mybatis提供的枚举处理器
### 6.3.2使用自定义类型处理器
### 6.3.3 对Java8 日期（jsr-310）的支持
### 6.4 小结

#第七章 mybatis 缓存配置
## 7.1 一级缓存
## 7.2 二级缓存和三级缓存
### 7.2.1 配置二级缓存
### 7.2.2 使用二级缓存
## 7.3 集成Ehcache
## 7.4 继承redis 缓存
## 7.5 脏数据的产生和规避
##７.６　二级缓存的适用场景
## 7.7 小结 

# 第八章 mybatis 插件开发

# 第九章 spring集成mybatis
# 第十章 SpringBoot集成mybatis
# 第十一章 mybatis 开源项目

