第一部分 入门
# 第一章  简介
## 1.2 redis 数据结构简介
redis 可以存储键与五种不同类型之间的映射，包括：String字符串、set集合 Hash 散列 List 列表|Zset 有序集合
### 1.2.1 redis 中的字符串
redis 中的字符串，有get和SET Del 。还有对一部分内容进行读取和写入命令
set命令在执行成功后返回ok，
### 1.2.2 redis中的列表
redis 对链表LinkedList 结构的支持是他在键值对 存储的世界独树一帜。一个列表结构可以有序的存储多个字符串。LPUSH和RPUSH 分别将元素推入
左端和右端。LPOP和RPOP 分别从左端和右端推出元素。LINDEX用于获取列表在给定的位置的一个元素LREANGE 命令用于获取列表在给定范围
上的所有元素。
还包括从来列表移除元素、将元素插入列表中间的命令，将列表剪至指定长度。
### 1.2.3 redis 的集合
列表和集合相同，不同在于列表可以存储多个相同的字符串，集合使用散列表来保证自己存储的每个字符串都是不同的。
SADD 添加元素至集合，或者使用SREM 移除元素，通过SISMENMBER 检查元素是否已存在。SMEMBERS 获取集合包含的所有元素

### 1.2.4 redis 的散列
存储键值对
### 1.2.5 redis的有序集合
用于存储键值对，有序集合的键被称为成员，每个成员各不相同，值被称为分值，必须是浮点数。有序集合是唯一一个可以更具成员访问
元素，又可以更具分支以及分支的排列顺序来访问元素的数据结构。
# 第二章 使用redis 构建web应用
## 2.1 登录和cookie缓存
 kookie 一种是签名signed 和令牌token
 
第二部分 核心概念
# 第三章 redis 命令
## 3.1 字符串
字符串存取字节串、整数、浮点型三种类型的值
INCR  INCR key-name ----将健存值加1
DECR  DECR key-name ----将键存值减 1
INCRBY INCRBY key-name ----将健存储的值加上整数amount
DERBY DECRBY key-name ----将健存储的值减去整数amount
INCRBYFLOUAT  INCRBYFLOUAT key-name ----将键存储的值加上浮点数amount

用户将一个值存储到redis 字符串字符串里面，这个值可以被解释成为十进制整数或者浮点数，那么redis 会觉察得到这一点，并允许用户进行INCR
EECR 操作。如果用户对一个不存在的键或者保存了空串的键执行自增或者自减 操作，redis在这ing下会把这个键的值当作是0来处理。

   ![avatar](/work/study/study_book/resource/image/1.png)
   
GETRANGE he SUBSTR 

### 3.2 列表 list
 ![avatar](/work/study/study_book/resource/image/2.png)
 ### 3.3 set集合
  ![avatar](/work/study/study_book/resource/image/3.png)
  

# 第四章

# 第五章
# 第六章
# 第七章
# 第八章
第三部分进阶
# 第九章
# 第十章
# 第十一章
