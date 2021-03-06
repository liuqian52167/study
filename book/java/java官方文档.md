java语言
# 第一章java介绍
java关键特性：简单性、安全性、可移植性、面向对象、健壮性、多线程、体系结构中立、
解释执行、高性能、分布式、动态性
# 第二章 java综述
## 2.1面向对象编程
面向对象：
    面向对象变成围绕数据（就是对象），以及一套为数据精心定义的接口组织程序。面向对象编程的
特点数据控制对代码的访问。

抽象：
    抽象是面向对象编程的本质元素之一。
    
面向对象三原则：
   1封装：将代码机器操作的数据绑到一起的机制。并且保证代码和数据不会收到外界的干扰也不会
    被误用。可以理解一个包装盒。封装是类的基础。
    
   2继承：继承是一个对象获取另一个对象的属性的过程。
   
   3多态：多种形态，允许将一个接口用于一类通用动作的特征。具体事宜哪一个动作与应用场合有关。
   一般多态的概念被表达为一个接口，多种方法。
## 2.2 简单程序
1输入程序
2编译程序
## 2.4 控制语句
if语句
for循环
代码块{ }

空白符：空格、制表符、换行符

标识符：用于命名事物，例如类、变量以及方法。

分隔符：
    1（）圆括号：在定义调用方法时用于包含参数列表，也可以用于在表达式中定义优先权、
    在控制语句中包含表达式以及包围强制类型转换

   2{}花括号：用于包含自动初始化数组的值，也用于定义代码块、类、方法以及局部作用域
   
   3[] 方括号：用于声明数组类型，也可以解引用数组值。
   
   4；分号结束语句。
   
   5，逗号在变量声明中分割连续的标识符，也可以用于for循环，将圆括号的语句连接到一起。
   
   6。句点：用于将包的名称与子包以及类的名称分隔开，也可以用于将变量或方法与引用变量分隔开。
   
   7::冒号：用于创建方法或者构造函数引用jdk8引入
   
java关键字：

java类库：


    
# 第三章 数据类型变量和数组
## 3.1 java是强类型化的语言
java是一种强类型化的语言。
## 3.2基本类型
整型
byte：8 一字节 -128  127
short 16 二字节

int 32 四字节

long 64 八字节

浮点型
float：32 四字节
double 64 八字节
布尔型
boolean 1位

字符型：char 16位 unicode

深入分析字面值：

整型字面值：

浮点型字面值

布尔类型字面值：true 和false

字符串字面值：


##3.8变量
在java中变量是基本存储单元。变量通过联合标识符、类型以及可选的初始化器来定义。所有变量
都有变量的作用域，作用域定义了变量的可见性和生存期。

变量的声明：int a =17;

动态初始化：除了使用常量作为初始化器，

变量的作用域和生存期：
所有变量都是在main（）方法开始时声明，java也允许在代码块中声明。分为全局作用域和局部作用域。


## 3.9 类型转换和强制类型转换

java的自动类型转换（隐式转换）

强制转换（显示转换） int a；
byte b； b=(byte) a;

表达式中的自动类型提升
如java将
## 3.11数组
数组通常是以通用名称引用的一组类型相同的变量。可以闯将任意类型的数组，并且数组可以是一维或者多维的。
数组中的特定元素通过索引进行访问。数组为分组相关信息提供便利方法。
如int array[];

多维数组： int array[][]= new int [4][];

int a[] = new int[3];等价于int[] a= new int[3];

java支持字符串类型String，他不是基本类型也不是字符数组，string定义了一个对象。
String类型用于ushengming字符串类型，也可以声明字符串数组。

java不允许使用指针。java不支持程序能够访问和修改指针。


# 第四章 运算符
运算符java分为四种：算术运算符、位运算符、关系运算符和逻辑运算符。还有比较运算符 instanceof箭头运算符
->。
## 4.1 算术运算符
算数运算符操作数必须是数值类型。char类型可以使用算数运算符，本质上他是int的子集。
+ 加

—减

*乘

/除

% 求模

+= 加并赋值

++ 自增

-= 减并赋值

*= 乘并赋值

/= 除并赋值

%= 求模并赋值

-- 自减

##4.2 位运算符
java定义的位运算符他们可以作用于整数类型：long int short char 以及byte

- 按位一元取反

& 按位 与

| 按位或

^ 按位异或

  （> >右移）
  
  （>>> 右移0填充）
  
 << 左移
 
 &= 按位与并赋值
 
 |= 按位或并赋值
 
 ^= 按位异或并赋值
 
 (>>= 左移并赋值)
 
 （>>>= 右移0填充并赋值）
 
 <<=左移并赋值
 
 ##4.3 关系运算符
 关系运算符用于判定一个操作数与另外一个操作数之间的关系。他可以判断相等和排序关系。
 
 == 等于
 
 ！= 不等于
 
 （> 大于）
 
 <小于
 
（>= 大于等于）

<= 小于等于

## 4.4 布尔逻辑运算符
& 逻辑与

| 逻辑或

^ 逻辑异或

|| 短路或 

&& 短路与

！逻辑一元非

&= 逻辑与并赋值。

|= 逻辑非并赋值

^= 逻辑异或并赋值

== 等于
！= 不等于

？： 三元if then else

# 4.5 赋值运算符
 =赋值运算符
 
 ？运算符 三元运算符
 k = i<0 ? -1:i;
 
 
 ##4.6运算符优先级
 

# 第五章 控制语句
## 5.1 选择语句
if语句

if(){
    if(){
    
    }else{}
}else{

}

if（）{}
else if(){}
else if(){}


switch 语句
switch（）{
    case value1：
    break；
    case value2：
    break；
    
    default：
}

## 5.2迭代语句
java迭代语句包括for 、while、以及dowhile

# 第六章 类
# 第七章 方法和类
# 第八章 继承
# 第九章包和接口
# 第十章 异常处理
# 第十一章 多线程编程
# 第十二章 美剧自动装箱和注解
# 第十三章 i/o、applet以及其他主题
#第十四章 泛型
# 第十五章lambda表达式
java库
# 第十六章 字符串处理
# 第十七章java.lang
# 第十八章javautil一
# 第十九章 java.util二
# 第二十章 输入输出 探究java.io
# 第二十一章探究NIO
# 第二十二章 联网
# 第二十三章 Applet
# 第二十四章 事件处理
