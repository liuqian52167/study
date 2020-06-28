mybatis 入门与精通
# 第一章mybatis入门

# 第二章mybatis xml 方式的基本用法
## 2.1 简单的权限控制需求
## 2.2 使用xml方式
mybatis 真正的强大之处在于它的映射语句，这也是他的魅力所在。mybatis的映射语句非常强大，映射器的xml文件
也就相对简单。mybatis就是针对sql构建的。

### 题记 Java的动态代理和静态代理
静态代理：代理对象创建在代码运行前，绑定时机，即代理类实现时就指定与目标对象类相同的接口。
原理：无，效率无，具体使用为代理单一目标对象。

动态代理：代理对象创建在代码运行时，绑定时机不需要显示实现与目标对象相同的接口，而是将这种实现推迟到程序
运行时让jvm实现。原理是依靠反射，效率低，代理多个目标。


静态代理的好处;
    1隐藏委托类的具体实现
    2可以在不改变委托类的情况下增加额外的操作
    
 /**
  * 静态代理，这个代理类也必须要实现和被代理类相同的Person接口
  *
  */
 public class ProxyTest implements Person{	
 	private Person o;	
 	public ProxyTest(Person o){
 		this.o = o;
 	}
 	public static void main(String[] args) {
 		// TODO Auto-generated method stub
 		//s为被代理的对象，某些情况下 我们不希望修改已有的代码，我们采用代理来间接访问
 		Student s = new Student();
 		//创建代理类对象
 		ProxyTest proxy = new ProxyTest(s);
 		//调用代理类对象的方法
 		proxy.sayHello("welcome to java", 20);
 	}
  
 	@Override
 	public void sayHello(String content, int age) {
 		// TODO Auto-generated method stub
 		System.out.println("ProxyTest sayHello begin");
 		//在代理类的方法中 间接访问被代理对象的方法
 		o.sayHello(content, age);
 		System.out.println("ProxyTest sayHello end");
 	}
 }
 
 动态代理：在Java的动态代理机制中有两个重要的类或者接口一个是InvocationHandler（Interface），
 还有一个是Proxy（是一个class）
 
 proxy类：动态的创建一个代理对象的类，它提供了许多方法，我们用的最多是newProxyInstance（）方法
 该方法的作用就是得到一个动态的代理对象。
 
 代理类要实现的内容：
 1因为动态代理不知道要被代理的类是哪一个，所以实现InvocationHandler的代理类定义了一个Object类，在代理的构造函数中作为参数传过来。
 2实现InvocationHandler中invoke方法
 3写main方法，并在main方法中根据定义的被代理类的生成
 
 动态代理的步骤：
 1首先获取一个被代理对象的引用
 2获得该引用的接口
 3生成一个类，这个类实现了我们给的代理对象所实现的接口
 4上述编译生成了。class字节码共jvm使用
 5调用上述生成的class
 
 /**
  * 动态代理类
  * @author Martina
  *
  */
 public class MyInvocationHandler implements InvocationHandler{
 	
 	private Object object;
 	
 	public MyInvocationHandler(Object object){
 		this.object = object;
 	}
  
 	@Override
 	public Object invoke(Object proxy, Method method, Object[] args)
 			throws Throwable {
 		// TODO Auto-generated method stub
 		System.out.println("MyInvocationHandler invoke begin");
 		System.out.println("proxy: "+ proxy.getClass().getName());
 		System.out.println("method: "+ method.getName());
 		for(Object o : args){
 			System.out.println("arg: "+ o);
 		}
 		//通过反射调用 被代理类的方法
 		method.invoke(object, args);
 		System.out.println("MyInvocationHandler invoke end");
 		return null;
 	}
 	
 	public static void main(String [] args){
 		//创建需要被代理的类
 		Student s = new Student();
 		//这一句是生成代理类的class文件，前提是你需要在工程根目录下创建com/sun/proxy目录，不然会报找不到路径的io异常
 		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
 		//获得加载被代理类的 类加载器
 		ClassLoader loader = Thread.currentThread().getContextClassLoader();
 		//指明被代理类实现的接口
 		Class<?>[] interfaces = s.getClass().getInterfaces();
 		// 创建被代理类的委托类,之后想要调用被代理类的方法时，都会委托给这个类的invoke(Object proxy, Method method, Object[] args)方法
 		MyInvocationHandler h = new MyInvocationHandler(s);
 		//生成代理类
 		//注意newProxyInstance的三个参数所代表的含义
 		Person proxy = (Person)Proxy.newProxyInstance(loader, interfaces, h);
 		//通过代理类调用 被代理类的方法
 		proxy.sayHello("yujie.wang", 20);
 		proxy.sayGoodBye(true, 100);
 		System.out.println("end");
 	}
  
 }
mybatis使用接口调用，mybatis使用Java的动态代理可以直接通过接口来调用相应的方法，不要提供接口的实现类，更不需要在实现类使用sqlSession
以通过命名空间间接调用。另外当有多个参数时，通过参数注解@Param设置参数的名字省去了手动构造map参数的过程，尤其spring中使用的时候。
可以配置为自动扫描所以的接口类，直接将接口注入到需要用到的地方

映射文件加载：
手动加载
自动加载：
    1<package name = "tk..com.mapper"/>
     这种配置方式会先查找这个包下的所有接口，循环对接口进行操作
   
   2判断接口对应的命名空间是否已经存在
    
   3加载对应的xml映射文件，将接口全限定名转换为路径，将mapper文件转换为Usermapper。xml，以.xml为后缀搜索xml资源，如果找到就解析xml。
    
   4处理接口的注解方法
   
   ## 2.3 select 用法
   1查询系统的用户角色、权限等数据，传统的jdbc需要写查询语句对接国际进行手动处理，将结果集映射到对象的属性中。
   使用mybatis 只需要将xml中添加一个select元素，写一个sql，和一些简单配置，就可以将查询结果映射到对象中。
   
   ＜resultMap ＞
   <mapper namespace=” tk.mybatis . simple .mapper . UserMapper” >
   <resultMap id=” userMap ” type=” tk.mybatis . simple.model.SysUser” >
   <id property=” id” column=” id” />
   <result property=” userName ” column=” user name ” />
   <result property=” u serPassword" column=”user_password” />
   <re sult property=” u serEmail” column=” user_email” />
   <result property=” userinf o ” column=” user_info ” />
   <result property=” head I mg ” column=” head img ” jdbcType=” BLOB” />
   <result property=” createTime ” column=” create time ” 
   jdbcType= ” TIMES TAMP” />
   </resultMap> 
   
   接口和映射文件xml 通过namespace的设置为全限定名称进行关联。
   方法和xml映射文件通过标签的id属性值和接口方法名一致进行关联
   
   当只使用xml而不使用接口的时候，namespace的值可以设置为任意不重复的值
   
   当标签的id属性值任何时候都不能出现.，并且同一个命名空间下不能出现重复的id
   
   接口的方法是可以重载的，所以接口中可以出现多个同名单参数不同的方法，但xml
   的id值不能重复。因而接口中的所有的同名方法都会对应着xml中的同一个id的方法。
   最常见的做法就是同名方法其中一个方法加一个RowBound类型的参数实现分页查询。
   
   resultMap 用于设置返回值的类型和映射关系。
   （#{id}） mybatissql中使用预编译参数的一种形式，大括号的id时传入的参数名。
   
   resultMap 标签用于配置Java对象的属性和查询结果列的对应关系，通过resultMap
   中配置cloumn和prpperty可以将查询列的值映射到type对象的属性上，因此当我们
   使用select查询所有列时，mybatis也可以将正确结果返回
   
   ###resultMap 是一种很重要的配置结果映射的方法。属性
   id 必填。唯一，在select标签中resultMap指定的值即为id在此设定的指
   
   type：必填，用于配置查询列所映射的Java对象类型
   
   extends： 选填，可以配置当前的resultMap继承自其他的ResultMap，属性值为继承
   resultMap的id
   
   autoMapping：选填，可选值true or false 用于配置是否启用非映射字段，（没有在resultMap中配置的字段）
   的自动映射功能，该配置可以覆盖全局的automappingBehavior配置。
   
   ### resultMap标签
   
   constructor 配置使用构造方法注入的结果包含两个子标签
   
            idArg id 参数，标记结果作为id为一只，可以帮助提高整体性能
            arg 注入到构造方法的一个普通结果。
            
            constructor：通过构造方法注入属性的结果集。构造方法中的idArg、
            arg参数分别对应着resultMap中的id、result标签，他们含义相同但注入方式不同。
            
            resultMap 中的id和result标签包含的属性相同，不同的地方在于，id代表的是主键或唯一值的字段
            他们的属性值都是通过setter方法注入的。
                column 从数据库中得到的列明，或者列的别名
                property：映射到列结果的属性，映射间的username，这样的属性，也
                可以映射一些复杂的对象中的属性如address。street.namuber，这会通过“.”方式的属性嵌套赋值。
                
                JavaType：Java类的完全限定名，或一个类型别名（通过typeAlias配置或则默认的类型）
                如果映射到一个javaBean，mybatis会自动判断属性，如果映射到hashMap则需要指定JavaType的属性
                
                TypeHandler 使用这个属性可以覆盖默认的类型处理器。这个属性是类的完全限定名或类型别名。
                
            
           
   
   id，一个id结果，标记结果作为id的为一只，可以帮助提高整体性能。
   
   result 注入到Java对象的属性的普通结果
   
   association：复杂的类型关联，许多结果将包装成这种类型。
   
   collection 复杂类型的集合。
   
   discriminator：根据结果值来决定使用哪个结果映射。
   
   case：基于某些值得结果映射。
   
   
   
  ### resultType
  当sql返回多个结果时，必须使用list《user》，如果使用user就会抛出 TooManyResultsException异常。
  
  多实体的可以参照下面这种方式
  
  SysRoleExtend extends Sysrolr{
    多余的Java列，然后settergetter方法。
  }
  
  当多余字段为大量时 ，不掐套xml配置的情况下使用如下方法
  直接在一个对象中增加另一个对象 如role
  Private Sys Role role；
  
  然后修改xml中的selectRole的方法
  增加查询列的增加的两行：
  u.user_name as "user.userName",
  u.user_email as "user.userEmail"
  
  
  
  ## 2.4 insert 方法
  
  <insert >元素
  id 命名空间唯一标识符
  
  ParameterType，即将出入的语句参数完全限定类名或者别名。这个属性可选，mybatis可以
  推断出传入语句的具体参数，因此不建议配置该属性。
   
  FlushCache：默认值为true 任何时候只要语句被调用都会清除一级缓存和二级缓存。
  
  timeOut：设在在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。
  
  statementType：对于statement 、prepared、callablestatement 默认值为Prepared。
  
  userGenerateKeys：默认值false，设置为true mybatis会根据jdbc的getGennerrateedKeys方法去除有数据库内部生成的主键。
  
  keyProperty：mybtis通过getGennertedKey获取主键值后将要赋值的属性名。
  如果希望获取多个数据库自动生成的列，属性值也可以是以都好分割的属性名称列表。
  
  keyColumn：进队insert和update有用。通过生成的键值设置的别名，这个设置尽在某些数据库中是必须的，当主键
  不是表中第一列时 需要设置。如果希望获得得到多个生成的列，也可以是逗号分割的属性名称列表。
  
  databaseId：如果设置了datebaseidprovider，mybatis回家再所有的不带databaseId的或是配当前的databaseId的语句。
  如果同时存在databaseId和不带databaseId的语句，不带的会被忽略。
  
  注意
  Java类型 date 对于的jdbc类型是DATE
  
  Java类型 time 对应的jdbcshiTIME
  
  Java类型 dataTime 对应的jdbc是TIMESTAMP
  
  使用jdbc方式返回主键自增的值
  <insert id="insert"userGeneratedKey="true" keyProperty="id">
  ………………
  </insert>
  
  
  selectKey 返回主键的值
  在insert中加入这个
  <selectKey keyColumn ="id" resultType ="long" keyProperty="id" oerder="after">
  select LAST_INSERT_ID()
  </selectKey>
  
  数据库不同，MySQLorder设置为after ，主要是因为当前记录的主键值在insert语句执行完成后才能获取到
  Oracle要设置为before 因为orcale中要先从序列获取值，再将值 作为主键插入到数据库中。
  
  ## 2.5 update
  
  ## 2.6 delete
  
  ## 2.7 多个接口参数的用法
  目前接口参数就一种，参数的类型分为两种一种为基本类型一种为javabean
  
  多个参数的我们可以将多个参数合并到一个JavaBean里。当参数两三个我们不可能创建新的JavaBean
  当比较小的时候可以选择用Map类型作为参数或者使用@param注解
  
  1马匹作为参数的方法，在Map中通过key来映射xml中sql中的使用参数值名字，value存放值。
  
  2@param 
  给参数配置@Param注解后mybatis会自动将参数封装成Map类型。@Param注解值
  会作为map的key，因此sql部分可以通过配置的注解值来使用参数。
  
  当传出来的是javaBean就要指定参数了，xml要获取就要使用#{user.id}
  和#{role.name}.
  
  ## 2.8 mapper接口的动态代理实现原理
  ###jdk动态代理基础
  
  
  从代理类中可以看到，当调用一个接口的方法时，会通过接口的全限定名称和当前调用的方法名组合
  得到一个方法id，这个id值就是namespace和当前调用的方法名
# 第三章
# 第四章
# 第五章
# 第六章
# 第七章
# 第八章
# 第九章
# 第十章
# 第十一章
