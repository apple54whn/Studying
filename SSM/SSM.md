# 第一部分 Spring

## 1 IoC和DI

IoC和DI其实是一个问题从两个方面看的结果

* IoC：控制反转。控制代表对象的生成方式，传统方法是调用类new个某一接口实现类来持有其引用。反转代表不再new对象（即某一接口实现类的选择控制权从调用类中移除），交给容器来控制
* DI：依赖注入。即调用类对某一接口实现类的依赖关系由第三方容器或协作类注入，移除调用类对某一接口实现类的依赖
  * 一般完成特定的业务逻辑可能会需要多个类之间进行协作。按传统的做法，每个对象负责管理与自己互相协作的对象(它所依赖的对象)的引用，这会导致高度耦合并难以测试的代码。利用依赖注入，每个对象可以直接获取所依赖的对象

### 1.1 装配Bean的方式

#### 1.1.1 xml中显式配置

* 属性注入(set方法注入)：**property**

```xml
//spring-config 或 applicationContext.xml
<bean name="user" class="cn.itcast.pojo.User">
    <!--注入字面量-->
    <property name="name" value="tom"></property>
    <!--注入引用-->
    <property name="car" ref="car"></property>
    <!--注入数组、List、Set-->     <!--如果只准备注入一个值(对象)，直接使用上述即可-->
    <property name="array">
            <array>
                <value>666</value>
                <ref bean="car"></ref><!--引用类型-->
            </array>
        </property>
	<!--注入map-->
    <property name="map">
		<map>
            <entry key="6" value="666"></entry><!--4种组合-->
            <entry key-ref="car" value-ref="car"></entry>
		</map>	<!--value-type="cn.itcast.pojo.Car"可以不设置，设置后值类型唯一-->
    </property>
    <!--注入properties-->
    <property name="properties">
        <props>
            <prop key="driverClass">com.jdbc.mysql.Driver</prop>
            <prop key="username">root</prop>
        </props>
    </property>
</bean>
    
<bean name="car" class="cn.itcast.pojo.Car">
    <property name="name" value="BMW"></property>
    <property name="color" value="red"></property>
</bean>
```

* p-命名空间注入：无法实现装配集合



* 构造器注入：**constructor-arg**

```xml
<bean name="user1" class="cn.itcast.pojo.User">
    			<!--构造方法参数名    参数类型                参数索引   参数值-->
    <constructor-arg name="name" type="java.lang.String" index="0" value="tom">             </constructor-arg>
    <constructor-arg name="car" type="cn.itcast.pojo.Car" index="1" ref="car">             </constructor-arg>
    
    <!--数组、List、Set、Map、properties同属性注入方式一致-->
    <constructor-arg name="list" type="java.util.List" index="2">
        <list>
            <value>222</value>
        </list>
    </constructor-arg>
</bean>
```

* c-命名空间注入：无法实现装配集合



#### 1.1.2 java中显式配置(JavaConfig)

* 通过java配置类定义Spring**装配规则**

  ```java
  @Configuration
  public class JavaConfig {
  	
      @Bean(name="user")//name不存在则bean的id或name为方法名
      public User user(Car car){//构造器注入，直接返回new User即可；下面是setter注入方式
          User user = new User();
          user.setName("66");//setter注入
          user.setCar(car);//setter注入
          return user;
      }
  
      @Bean
      public Car car(){
          return new Car();
      }
  }
  ```

#### 1.1.3 隐式的bean发现机制和自动装配(注解)

* Spring从两个角度来实现自动化装配：component scanning和autowiring

* 通过java配置类定义Spring**装配规则**

  ```java
  //配置的java文件
  @Configuration
  //在Spring中启用组件扫描，并可设置组件扫描的基础包
  @ComponentScan({"pojo","web"})或@ComponentScan("pojo")
  public class Config {}//类名任意起
  ```

* **被扫描**的bean

  ```java
  /**
   *  Spring中提供@Component的三个衍生注解,为了让标注类本身的用途清晰，在后续版本会对其增强 
   *  @Controller :WEB层 
   *  @Service    :业务层 
   *  @Repository :持久层
   */
  @Component("user")//为组件扫描的bean命名
  @Scope(scopeName = "prototype")//单例：singleton
  public class User {...}
  ```

* 实现**自动装配**

  ```java
  public class User{
      /**
       * 通过反射装配，破坏了封装性
       */
      @Value("tom")
      private String name;
      
      //@Autowired不仅可以用于构造器，还可以用在属性的Setter方法上
      		  //但是当匹配多个类型一致的对象时，无法选择注入哪个对象
      //@Qualifier("car")告诉Spring容器自动装配哪个名称的对象
      
      @Resource(name="car")//手动注入，指定注入哪个名称的对象
      private Car car;
      public User(Car car){
          this.car = car;
      }
      
      /**
       * 推荐使用，一般也不使用，创建后才赋值
       */
      @Value("tom")
      public void setName(String name){
          this.name = name;
      }
      
      /**
       *初始化、销毁方法
       */
      @PostConstruct//在对象被创建后调用，与init-method一样
  	@PreDestroy//在对象被销毁前调用，与destroy-method一样
  }
  ```

* **Spring中整合的JUnit使用**

  ```java
  @RunWith(SpringJUnit4ClassRunner.class)//在测试开始时自动创建应用上下文
  @ContextConfiguration(classes = Config.class)//在xxx中加载配置
  public class UserTest {
  
      @Resource(name = "user")
      private User user;
  
      @Test
      public void fun1(){
          System.out.println(user);
      }
  }
  ```

 

### 1.2 Bean的作用域

* @Scope注解

  ```java
  @Scope("singleton")
  ```

* xml配置

  ```xml
  <bean name="user" class="" scope="prototype"></bean>
  ```

  * singleton：默认值，单例，在spring容器中只会存在一个实例
  * prototype：多例，每次获得时才会创建，每次创建都是新的对象
  * session：web环境下，对象与session生命周期一致
  * request：web环境下，对象与request生命周期一致



## 2 AOP

### 2.1 注解、JavaConfig完成

* **配置文件**

  ```java
  @Configuration
  @EnableAspectJAutoProxy//开启AspectJ自动代理
  @ComponentScan
  public class JavaConfig {
  }
  ```

* **定义切面**(包括通知和切点)

  ```java
  @Component
  @Aspect
  public class MyAspect {
      /**
       * 切点
       * execution：用于匹配是连接点的执行方法
       * bean：限制bean的id/name(可选)
       * within：限制连接点匹配指定的类型
       * ..为任意类型任意的参数列表
       */
      @Pointcut("execution(* cn.itcast.User.*(..)) && bean(user) && within(cn.*)")
  
      public void pointcut(){ }
  
      @Before("pointcut()")//后置通知
      public void before(){
          System.out.println("before");
      }
  
      @After("pointcut()")//前置通知
      public void after(){
          System.out.println("after");
      }
  
      @AfterReturning("pointcut()")//返回通知
      public void afterReturning(){
          System.out.println("afterReturning");
      }
  
      @AfterThrowing("pointcut()")//异常通知
      public void afterThrowing(){
          System.out.println("afterThrowing");
      }
  
      @Around("pointcut()")//环绕通知
      public void around(ProceedingJoinPoint pjp) {
          try {
              System.out.println("before around");
              pjp.proceed();
              System.out.println("after around");
          } catch (Throwable throwable) {
              System.out.println("false!!!");
          }
      }
  }
  ```

* 要增强的类

  ```java
  @Component
  public class User {
      public void say(){
          System.out.println("user say hello");
      }
  }
  ```

* 测试类同上

#### 2.1.1处理通知中的参数

```java
//获取实际方法中参数，并传递给通知进行增强
@Before("execution(* cn.itcast.User.*(..)) && args(name,age)")
public void before(String name,int age){
    System.out.println(name);
    System.out.println("before");
    System.out.println(age);
}
```


#### 2.1.2 引入新功能

**切面为一个对象增加新方法**

```java
@DeclareParents(value = "cn.itcast.User",//指定哪种bean要引入该接口，可以是接口+即所有其实现类
                defaultImpl = ByerImpl.class)//为引用功能提供实现的类
public Byer byer;//指定要引入的接口
```

**当引入接口方法被调用时，代理对象会把此调用委托给实现了新接口的某个其他对象。实际上，一个Bean的实现被拆分到多个类中**



### 2.2 xml配置

* 要想使用**注解的方式**使用Spring AOP就**必须要有源码**(因为我们要在切面类上添加注解)。如果没有源码的话，我们就得使用XML来声明切面了

  | AOP配置元素                | 用途                                            |
  | -------------------------- | ----------------------------------------------- |
  | **< aop:config>**          | 顶层的AOP配置元素                               |
  | **< aop:aspect>**          | 定义一个切面                                    |
  | **< aop:pointcut>**        | 定义一个切点                                    |
  | < aop:advesor>             | 定义AOP通知器                                   |
  | < aop:before>              | 定义AOP前置通知                                 |
  | < aop:after>               | 定义AOP后置通知（无论被通知的方法是否执行成功） |
  | < aop:after-returning>     | 定义AOP返回通知                                 |
  | < aop:after-throwing>      | 定义AOP异常通知                                 |
  | < aop:around>              | 定义AOP环绕通知                                 |
  | < aop:aspect-autoproxy>    | 启用@AspectJ注解驱动的切面                      |
  | **< aop:declare-parents>** | 以透明的方式为被通知的对象引入额外的接口        |

* bean的装配、定义切面(包括通知和切点)

  ```xml
  <bean name="user" class="cn.itcast.xml.User"></bean>
  <bean name="advice" class="cn.itcast.xml.Advice"></bean>
  
  <aop:config>
      <aop:aspect ref="advice">//切面
          <aop:pointcut id="pc" expression="execution(* cn.itcast.xml.User.*(..))"/>
          <aop:before pointcut-ref="pc" method="before" />//前置通知
          
          <aop:around pointcut-ref="pc" method="around" />//环绕通知
          
          <aop:before pointcut="target(cn.itcast.User) and args(name,age)" 
                      method="bindParams" />//处理通知中的参数
          
          <aop:declare-parents
                      types-matching="cn.itcast.Person+"
                      default-impl="cn.itcast.ByerImpl"
                      implement-interface="cn.itcast.Byer"
                      />//实现接口，引入新功能
      </aop:aspect>
  </aop:config>
  ```

* 通知的定义

  ```java
  public class Advice {
      public void before(){
          System.out.println("before");
      }
  }
  ```

* 测试类同上





# 第二部分 MyBatis

MyBatis 本是 apache 的一个开源项目 iBatis, 2010年这个项目由 apache software foundation 迁移到了 google code，并且改名为 MyBatis 。2013年11月迁移到 Github。MyBatis 是一个优秀的**持久层框架**，它对 jdbc 的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身。

## 1 入门

### 1.1 导包

* MyBatis核心包、依赖包、数据驱动包

### 1.2 config

* config目录下，**mybatis-config.xml**、log4j.properties(日志输出)

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <!-- 和spring整合后 environments配置将废除 -->
      <environments default="development">
          <environment id="development">
              <!-- 使用jdbc事务管理 -->
              <transactionManager type="JDBC" />
              <!-- 数据库连接池 -->
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver" />
                  <property name="url"
                            value="jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC" />
                  <property name="username" value="root" />
                  <property name="password" value="password" />
              </dataSource>
          </environment>
      </environments>
  
      <mappers>
          <!--  <mapper class="cn.itcast.mybatis.pojo"/>  -->
          <backage name = "cn.itcast.mybatis.dao"/>
      </mappers>
  </configuration>
  ```

* 配置的内容和**顺序**如下

  * **properties**（属性）

    * mybatis-config.xml可以引用java属性文件中的配置信息(如db.properties)，配置如下：

      ```xml
      <!-- 是用resource属性加载外部配置文件 -->
      <properties resource="db.properties">
          <!-- 在properties内部用property定义属性 -->
          <!-- 如果外部配置文件有该属性，则内部定义属性被外部属性覆盖 -->
          <property name="jdbc.username" value="root123" />
          <property name="jdbc.password" value="root123" />
      </properties>
      
      ···<!--<dataSource>中配置如下-->
      <!--在 properties 元素体内定义的属性首先被读取。 
      然后会读取properties 元素中resource或 url 加载的属性，它会覆盖已读取的同名属性-->
      <property name="driver" value="${jdbc.driver}" />
      ...
      ```

  * settings（全局配置参数）

  * **typeAliases**（**类型别名**）

    ```xml
    <typeAliases>
        <!-- 单个别名定义 -->
        <typeAlias alias="user" type="cn.itcast.mybatis.pojo.User" />
        <!-- 批量别名定义，扫描整个包下的类，别名为类名（大小写不敏感） -->
        <package name="cn.itcast.mybatis.pojo" />
    </typeAliases>
    
    ```

  * typeHandlers（类型处理器）

  * objectFactory（对象工厂）

  * plugins（插件）

  * environments（环境集合属性对象）

    * environment（环境子属性对象）
      * transactionManager（事务管理）
      * dataSource（数据源）

  * **mappers**（映射器）

    - **在config中加载**可以使用相对于类路径的资源引用， 或完全限定资源定位符(包括 `file:///` 的URL)，或类名和包名等

      ```xml
      <!-- 使用相对于类路径的资源引用 -->
      <mappers>
        <mapper resource="org/mybatis/builder/AuthorMapper.xml"/>
        <mapper resource="org/mybatis/builder/BlogMapper.xml"/>
        <mapper resource="org/mybatis/builder/PostMapper.xml"/>
      </mappers>
      ```

      ```xml
      <!-- 使用完全限定资源定位符（URL） -->
      <mappers>
        <mapper url="file:///var/mappers/AuthorMapper.xml"/>
        <mapper url="file:///var/mappers/BlogMapper.xml"/>
        <mapper url="file:///var/mappers/PostMapper.xml"/>
      </mappers>
      ```

      ```xml
      <!-- 使用映射器接口实现类的完全限定类名 -->
      <mappers>
        <mapper class="org.mybatis.builder.AuthorMapper"/>
        <mapper class="org.mybatis.builder.BlogMapper"/>
        <mapper class="org.mybatis.builder.PostMapper"/>
      </mappers>
      ```

      ```xml
      <!-- 将包内的映射器接口实现全部注册为映射器 -->
      <mappers>
        <package name="org.mybatis.builder"/>
      </mappers>
      ```


### 1.3 POJO类

* 作为mybatis进行sql映射使用，po类通常与数据库表对应

### 1.4 mapper

* **映射器文件**config\mappers目录中，创建与Dao类**对应的mapper.xml**文件，**有几个表创建几个mapper**

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!-- namespace：命名空间，用于隔离sql，mapper动态代理开发(只需写接口) -->
  <mapper namespace="cn.itcast.mybatis.dao.UserDao">
      <!--根据用户id查询用户-->
      <select id="findUserById" parameterType="int" 			                                 resultType="cn.itcast.mybatis.pojo.User">
          select * from user where id = #{id}
      </select>
  </mapper>
  ```

* **parameterType**：指定输入参数类型，mybatis通过ognl从输入对象中获取参数值拼接在sql中

* **resultType**：指定输出结果类型，mybatis将sql查询结果的一行记录数据映射为resultType指定类型的对象。如果有多条数据，则分别进行映射，并把对象放到容器List中

## 2 Dao开发方法

​	使用MyBatis开发Dao，通常有两个方法，即原始Dao开发方法和Mapper动态代理开发方法

### 2.1 SqlSession的使用范围

* **SqlSession**中封装了对数据库的操作，如：查询、插入、更新、删除等。SqlSession通过**SqlSessionFactory**创建。SqlSessionFactory是通过**SqlSessionFactoryBuilder**进行创建。

#### 2.1.1 SqlSessionFactoryBuilder

* SqlSessionFactoryBuilder用于创建SqlSessionFactory，SqlSessionFactory一旦创建完成就不需要SqlSessionFactoryBuilder了，因为SqlSession是通过SqlSessionFactory创建的。所以可以将SqlSessionFactoryBuilder当成一个工具类使用，最佳使用范围是**方法范围即方法体内局部变量**

#### 2.1.2 SqlSessionFactory

* SqlSessionFactory是一个**接口**，接口中定义了**openSession**的不同重载方法，SqlSessionFactory的最佳使用范围是**整个应用运行期间**，一旦创建后可以重复使用，**通常以单例模式管理SqlSessionFactory**

#### 2.1.3 SqlSession

* SqlSession是一个面向用户的接口，sqlSession中**定义数据库操作方法**。**每个线程都应有自己的SqlSession实例**。SqlSession的实例**不能共享使用**，它也是**线程不安全**的。因此最佳的范围是**请求或方法范围**。绝对不能将SqlSession实例的引用放在一个类的静态字段或实例字段中

* 打开一个 SqlSession；**使用完毕就要关闭它**。通常把这个关闭操作放到 finally 块中以确保每次都能执行关闭

### 2.2 原始Dao开发方式

​	原始Dao开发方法需要程序员编写Dao接口和Dao实现类

* 问题：
  * Dao方法体存在重复代码：通过SqlSessionFactory创建SqlSession，调用SqlSession的数据库操作方法
  * 调用sqlSession的数据库操作方法需要指定statement的id，这里存在硬编码，不得于开发维护

### 2.3 Mapper动态代理方式

#### 2.3.1 开发规范

​	Mapper接口开发方法只需要程序员编写**Mapper接口**（相当于**Dao接口**），由**Mybatis框架根据接口定义创建接口的动态代理对象(sqlSession.getMapper(Class c))**，代理对象的方法体同上边Dao接口实现类方法

* Mapper接口开发需要遵循以下规范：
  * Mapper.xml文件中的namespace与mapper接口的类路径相同
  * Mapper接口方法名和Mapper.xml中定义的每个statement的id相同
  * Mapper接口方法的输入参数类型和mapper.xml中定义的每个sql 的parameterType的类型相同
  * Mapper接口方法的输出参数类型和mapper.xml中定义的每个sql的resultType的类型相同

* 注意：使用Mapper动态代理方式时，Dao接口和其xml文件放置在**同一目录**下

动态代理对象调用sqlSession.selectOne()和sqlSession.selectList()是**根据mapper接口方法的返回值**决定，如果返回list则调用**selectList**方法，如果返回单个对象则调用**selectOne**方法

#### 2.3.2 案例

```java
public interface UserDao {
    User findUserById(int id);
    List<User> findUserByUsername(String username);
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(int id);
}
```

```xml
<!--UserDao.xml-->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace：命名空间，用于隔离sql，mapper动态代理开发(只需写接口) -->
<mapper namespace="cn.itcast.mybatis.dao.UserDao">

    <!--只有查询操作有返回值-->
    <!--根据用户id查询用户-->
    <select id="findUserById" parameterType="int" resultType="cn.itcast.mybatis.pojo.User">
        select * from user where id = #{id}
    </select>
    <!--根据用户名模糊查询用户-->
    <select id="findUserByUsername" parameterType="String" resultType="cn.itcast.mybatis.pojo.User">
        select * from user where username like '%${value}%'
    </select>

    <!--添加用户-->
    <insert id="addUser" parameterType="cn.itcast.mybatis.pojo.User" >
    <!-- selectKey 标签实现主键返回 -->
    <!-- keyColumn:主键对应的表中的哪一列 -->
    <!-- keyProperty：主键对应的pojo中的哪一个属性 -->
    <!-- order：设置在执行insert语句前执行查询id的sql，还是在执行insert语句之后执行查询id的sql-->
        <selectKey keyColumn="id" keyProperty="id" resultType="int" order="AFTER">
            <!--LAST_INSERT_ID():是mysql的函数，返回auto_increment自增列新记录id值-->
            select last_insert_id()
            <!-- 或   通过select uuid()得到uuid值 order="BEFORE"-->
        </selectKey>
        insert into user (username,birthday,sex,address)
        values (#{username},#{birthday},#{sex},#{address});
    </insert>

    <!--修改用户-->
    <update id="updateUser" parameterType="cn.itcast.mybatis.pojo.User">
        update user set username=#{username},sex=#{sex} where id=#{id}
    </update>

    <!--删除用户-->
    <delete id="deleteUser" parameterType="int">
        delete from user where id=#{id}
    </delete>
</mapper>
```

##### 注意 #{}和${}

- #{}表示一个**占位符**号，通过#{}可以实现preparedStatement向占位符中设置值，自动进行java类型和jdbc类型转换。#{}可以有效**防止sql注入**。 #{}可以接收简单类型值或pojo属性值。 如果parameterType传输单个简单类型值，#{}括号中可以是**value或其它名称**
- *${ }*表示**拼接sql串**，通过 *${}*可以将parameterType 传入的内容拼接在sql中且不进行jdbc类型转换， *${}*可以接收简单类型值或pojo属性值，如果parameterType**传输单个简单类型值**，*${}*括号中**只能是value**



## 3 输入映射与输出映射

**OGNL**是Object-Graph Navigation Language的缩写，它是一种功能强大的**表达式语言**，通过它简单一致的表达式语法，可以存取对象的**任意属性**，调用对象的**方法**，遍历整个对象的结构图，实现字段类型转化等功能。它使用相同的表达式去存取对象的属性。

### 3.1 parameterType(输入类型)

* **传递简单类型**
  * Integer、String等等。使用#{}占位符，或者${}进行sql拼接

* **传递pojo对象**
  * Mybatis使用ognl表达式解析对象字段的值，#{}或者${}括号中的值为pojo属性名称

* **传递pojo包装对象**
  * 需求：根据用户名模糊查询用户信息，查询条件放到QueryVo的user属性中(包装类)
  * 查询条件可能是**综合的查询条件**，不仅包括用户查询条件还包括其它的查询条件（比如查询用户信息的时候，将用户购买商品信息也作为查询条件），这时可以**使用包装对象传递输入参数**
  * 包装对象：**Pojo类中的一个属性是另外一个pojo**

### 3.2 resultType(输出类型)

* **输出简单类型**

  * Integer、String等等。需求:查询用户表数据条数

* **输出pojo对象**

  * `resultType = "cn.itcast.mybatis.pojo.User"`

* **输出pojo列表**

  * `resultType = "cn.itcast.mybatis.pojo.User"`，分别进行映射，并把对象放到容器List中


### 3.3 resultMap

* 如果**sql查询字段名和pojo的属性名不一致**，可以通过resultMap将字段名和属性名作一个对应关系。resultMap实质上还需要将查询结果映射到pojo对象中

  ```xml
  <!--查询所有订单数据，数据库中user_id有值，但是pojo中表示为userid不匹配，log显示为null-->
  <select id="queryOrderAll" resultType="order">
      SELECT id, user_id, number, createtime, note FROM `order`
  </select>
  ```

  ```xml
  <!-- resultMap最终还是要将结果映射到pojo上，type就是指定映射到哪一个pojo -->
  <!-- id：设置ResultMap的id -->
  <resultMap type="order" id="orderResultMap">
      <!-- 定义主键 ,非常重要。如果是多个字段,则定义多个id -->
      <!-- property：主键在pojo中的属性名 -->
      <!-- column：主键在数据库中的列名 -->
      <id property="id" column="id" />
  
      <!-- 定义普通属性，后三个可以不写；但是多表查询时都得写-->
      <result property="userId" column="user_id" />
      <result property="number" column="number" />
      <result property="createtime" column="createtime" />
      <result property="note" column="note" />
  </resultMap>
  
  <!-- 查询所有的订单数据 -->
  <select id="queryOrderAll" resultMap="orderResultMap">
      SELECT id, user_id,
      number,
      createtime, note FROM `order`
  </select>
  ```



## 4 动态SQL

* 通过mybatis提供的各种标签方法实现**动态拼接sql**
* 需求：根据性别和名字查询用户
  sql：SELECT id, username, birthday, sex, address FROM user WHERE sex = 1 AND username LIKE '%张%'

### 4.1 if、where

* 用于**拼接sql**；where可以去掉第一个**前and**

  ```xml
  <!--通过用户名和性别模糊查询-->
  <select id="findUserByUsernameAndSex" parameterType="User" resultType="User" >
      select * from user
      <where>
          <if test="username!=null and username!=''">
              username like '%${username}%'
          </if>
          <if test="sex!=null and sex!=''">
              and sex=#{sex}
          </if>
      </where>
  </select>
  ```

### 4.2 foreach

* 向sql传递**数组**或**List**，mybatis使用foreach解析

  需求：**根据多个id查询用户信息**

  查询sql：SELECT * FROM user WHERE id IN (1,10,24)

* **包装对象**：在pojo中定义list或数组属性ids存储多个用户id，并添加getter/setter方法

  ```java
  List<Integer> ids;
  //或 Integer[] ids;
  ```

  ```java
  public List<User> selectUserByIds(QueryVo vo);
  ```

  ```xml
  <!-- 根据ids查询用户 -->
  <select id="queryUserByIds" parameterType="queryVo" resultType="user">
  	SELECT * FROM `user`
  	<where>
  		<!-- foreach标签，进行遍历 -->
  		<!-- collection：遍历的集合，这里是QueryVo的ids属性 -->
  		<!-- item：遍历的项目，可以随便写，，但是和后面的#{}里面要一致 -->
  		<!-- open：在前面添加的sql片段 -->
  		<!-- close：在结尾处添加的sql片段 -->
  		<!-- separator：指定遍历的元素之间使用的分隔符 -->
  	   <foreach collection="ids" item="item" open="id IN (" close=")" separator=",">
  			#{item}
  	   </foreach>
  	</where>
  </select>
  ```

* **List或数组等：collection的值为array或list**

  ```java
  public List<User> selectUserByIds(Integer[] ids);
  public List<User> selectUserByIds(List<Integer> ids); 
  ```

  ```xml
  <select id="findUserByIds" parameterType="Integer" resultType="User" >
      select * from user where
      <foreach collection="array" item="item" open="id in (" close=")" separator=",">
          #{item}
      </foreach>
  </select>
  ```

### 4.3 sql片段

* Sql中可将重复的sql提取出来，使用时用include引用即可，最终达到sql重用的目的。暂时没啥用

  ```xml
  <sql id="selector">
      select * from user
  </sql>
  <select id="findUserById" parameterType="int" resultType="User">
      <include refid="selector"/>
      where id = #{id}
  </select>
  ```



## 5 关联查询

* 需要使用**resultMap**，resultType也行但是得创建继承类麻烦

### 5.1 一对一映射(association)

* 查询所有**订单**信息，**关联**查询下单**用户**信息

  ```java
  List<Order> oneToOne();
  ```

  ```xml
  <resultMap id="order" type="Order">
      <id property="id" column="id"/>
      <result property="number" column="number"/>
      <result property="createtime" column="createtime"/>
      <!--一对一使用association，property是pojo类属性名，javaType为附加属性对象的类名-->
      <association property="user" javaType="User">
          <id property="id" column="user_id"/>
          <result property="username" column="username"/>
      </association>
  </resultMap>
  <select id="oneToOne" resultMap="order">
      SELECT o.id,
      o.number,
      o.createtime,
      u.id user_id,
      u.username
      FROM `order` o LEFT JOIN `user` u
      ON o.user_id = u.id
  </select>
  ```


### 5.2 一对多映射(collection)

* 用户信息和订单信息为一对多关系

  ```java
  List<User> oneToMore();
  ```

  ```xml
  <resultMap id="user" type="User">
      <id property="id" column="id"/>
      <result property="username" column="username"/>
      <!--一对多使用collection，property是pojo类属性名，由于泛型，使用其值类型用ofType-->
      <collection property="orderList" ofType="Order">
          <id property="id" column="order_id"/>
          <result property="number" column="number"/>
          <result property="createtime" column="createtime"/>
      </collection>
  </resultMap>
  <select id="oneToMore" resultMap="user">
      SELECT u.id,
      u.username,
      o.id order_id,
      o.number,
      o.createtime
      FROM  `user` u LEFT JOIN `order` o
      ON o.user_id = u.id
  </select>
  ```

## 6 与Spring整合

### 6.1 整合思路

* SqlSessionFactory对象应该放到spring容器中作为单例存在
* 传统dao的开发方式中，应该从spring容器中获得sqlsession对象
* Mapper代理形式中，应该从spring容器中直接获得mapper的代理对象
* 数据库的连接以及数据库连接池事务管理都交给spring容器来完成

### 6.2 需要的jar包

- spring的jar包
- Mybatis的jar包
- Spring+mybatis的整合包
- Mysql的数据库驱动jar包（记得用**和自己mysql版本一致**的jar包）
- 数据库连接池的jar包

### 6.3 配置文件

```properties
//log4j.properties
# Configure logging for testing: optionally with log file
log4j.rootLogger=WARN, stdout
# log4j.rootLogger=WARN, stdout, logfile

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m%n

log4j.appender.logfile=org.apache.log4j.FileAppender
log4j.appender.logfile.File=target/spring.log
log4j.appender.logfile.layout=org.apache.log4j.PatternLayout
log4j.appender.logfile.layout.ConversionPattern=%d %p [%c] - %m%n
```

```properties
jdbc.driverClass=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/mybatis?serverTimezone=UTC
jdbc.username=root
jdbc.password=w1994
```

```xml
//spring-config.xml
<!-- 加载配置文件 -->
<context:property-placeholder location="classpath:db.properties"/>

<!--数据库连接池 -->
<!--<bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">-->
<!--<property name="driverClassName" value="${jdbc.driverClassName}"/>-->
<!--<property name="url" value="${jdbc.url}"/>-->
<!--<property name="username" value="${jdbc.username}"/>-->
<!--<property name="password" value="${jdbc.password}"/>-->
<!--</bean>-->

<!-- 数据库连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="driverClass" value="${jdbc.driverClass}"></property>
    <property name="jdbcUrl" value="${jdbc.url}"></property>
    <property name="user" value="${jdbc.username}"></property>
    <property name="password" value="${jdbc.password}"></property>
</bean>

<!-- Mybatis的工厂 -->
<bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"/>
    <!-- 核心配置文件的位置 -->
    <property name="configLocation" value="classpath:mybatis-config.xml"/>
</bean>

<!-- 原始Dao -->
<!--<bean id="userDao" class="cn.itcast.zhenghe.dao.UserDao">-->
<!--<property name="sqlSessionFactory" ref="sqlSessionFactoryBean"/>-->
<!--</bean>-->

<!-- Mapper动态代理开发 -->
<!--<bean id="userDao" class="org.mybatis.spring.mapper.MapperFactoryBean">-->
<!--<property name="sqlSessionFactory" ref="sqlSessionFactoryBean"/>-->
<!--<property name="mapperInterface" value="cn.itcast.zhenghe.dao.UserDao"/>-->
<!--</bean>-->

<!-- Mapper动态代理开发，扫描。并且不需要注入工厂，自动寻找 -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
    <!-- 基本包，会查询包及其子包 -->
    <property name="basePackage" value="com.itheima.mybatis.mapper"/>
</bean>
```

* mybatis-config.xml只需定义别名、mappers（整合后也不需要提供）即可；其他的不变

```java
//原始Dao
//需自己写实现类，继承SqlSessionDaoSupper(声明了一个工厂，set注入)，直接调用this.getSqlSession
//ApplicationContext ac = new ClasspathApplicationContext("spring-config.xml");
//UserDao userDao = (UserDao) ac.getBean("userDao");

//Mapper动态代理、扫描
@Test
public void fun1(){
    ClassPathXmlApplicationContext ac =
        new ClassPathXmlApplicationContext("spring-config.xml");

    UserDao userDao = ac.getBean(UserDao.class);
    //UserDao userDao = (UserDao) ac.getBean("userDao");扫描没有id值所以不再使用这个方法
    User user1 = userDao.findUserById(10);
    System.out.println(user1);
}
```



### 逆向工程

* 配置文件修改：
  * 修改要生成的数据库表
  * **pojo**文件所在包路径
  * **Dao**所在的包路径

* 注意：

  * 逆向工程生成的代码只能做**单表查询**

  * 不能在生成的代码上进行扩展，因为如果数据库变更，需要重新使用逆向工程生成代码，原来编写的代码就被覆盖了。

  *  一张表会生成**4个文件**
    * User.java(返回值对象); UserExample.java(条件查询时装数据的对象); UserDao.java; UserDao.xml



# 第三部分 SpringMVC

* 三大组件：在SpringMVC的各组件中，**处理器映射器**、**处理器适配器**、**视图解析器**称为SpringMVC的三大组件
  * 需要用户开发的组件有**controller(也称handler)**、**view**

## 1 入门程序

* 导入jar包，spring包即包含了

### 1.1 xml配置

- **web.xml**配置**前端控制器**DispatcherServlet，拦截请求到Controller层（自己编码）

```xml
//web.xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- 指定Spring配置文件(下部分代码由IDEA自动生成)-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    <!-- 配置监听器加载spring配置文件 -->
    <listener>
        <listener-class>
            org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
    
    <!-- 配置过滤器，解决post的乱码问题 -->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>
            org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>

    <!-- 配置SpringMVC前端控制器 -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <!-- 指定SpringMVC配置文件，可以不配置，默认找/WEB-INF/[servlet的名称]-servlet.xml -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/springmvc.xml</param-value>
        </init-param>
        <!-- 配置springmvc什么时候启动，参数必须为整数
		为0或者大于0，则springMVC随着容器启动而启动；小于0，则在第一次请求进来的时候启动 -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <!-- 1. /* 拦截所有 jsp js png .css 建议不使用 
   2. *.action *.do 拦截以do action 结尾的请求 肯定能使用 ERP 
         3. / 拦截所有（只不包括jsp) 强烈建议使用 前台 面向消费者 对静态资源放行 -->
        <url-pattern>*.form</url-pattern>
    </servlet-mapping>
</web-app>
```

* springmvc.xml。**开启扫描**@Controller、@Service等

```xml
//springmvc.xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans    http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <context:component-scan base-package="cn.itcast"/>
    <!-- 处理器映射器 从spring3.1版本开始默认的废除了，所以配置新的-->
    <!-- <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/> -->
    <!-- 处理器适配器 从spring3.1版本开始默认的废除了，所以配置新的-->
    <!--<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/> -->
    <!-- 注解驱动 替代上两个-->
    <mvc:annotation-driven/>
    <!-- 视图解释器 省略前后缀-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property value="/WEB-INF/jsp/" name="prefix"/>
        <property value=".jsp" name="suffix"/>
    </bean>
    <!-- 对静态资源放行 -->
    <mvc:resources location="/css/" mapping="/css/**"/>
    <mvc:resources location="/js/" mapping="/js/**"/>
    <mvc:resources location="/fonts/" mapping="/fonts/**"/>
    <!-- 配置Controller扫描 -->
    <context:component-scan base-package="com.itheima.crm.controller"/>
    <context:property-placeholder location="classpath:resource.properties"/>
</beans>
```

* **Controller层**

```java
public class ItemController {

    @RequestMapping(value = "/iteam/iteamlist.action")
    public ModelAndView itemList(){
        List<Items> list = new List<Items>();
        list.add(new Items());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("iteamList",list);
        modelAndView.setViewName("/WEB-INF/jsp/iteamList.jsp");
        return modelAndView;
    }
}
```

### 1.2 javaConfig配置



## 2 整合MyBatis

* 整合目标：控制层采用SpringMVC、持久层使用MyBatis实现

* 需要的jar包：

  * spring（包括SpringMVC）
  * mybatis
  * mybatis-spring整合包
  * 数据库驱动
  * 第三方连接池

* **整合思路**：

  * Dao层：
    * mybatis-config.xml。空文件即可，也可以配置POJO别名
    * **applicationContext-dao.xml**：1.数据库连接池；2.SqlSessionFactory对象；3.mapper文件扫描器

  ```xml
  //applicationContext-dao.xml
  <!-- 加载配置文件，在第二部分6.3节-->
  <context:property-placeholder location="classpath:db.properties"/>
  
  <!-- 数据库连接池 -->
  <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
      <property name="driverClass" value="${jdbc.driverClass}"></property>
      <property name="jdbcUrl" value="${jdbc.url}"></property>
      <property name="user" value="${jdbc.username}"></property>
      <property name="password" value="${jdbc.password}"></property>
  </bean>
  
  <!-- Mybatis的工厂 -->
  <bean id="sqlSessionFactoryBean" class="org.mybatis.spring.SqlSessionFactoryBean">
      <property name="dataSource" ref="dataSource"/>
      <!-- 核心配置文件的位置 -->
      <property name="configLocation" value="classpath:mybatis-config.xml"/>
  </bean>
  
  <!-- Mapper动态代理开发，扫描。并且不需要注入工厂，自动寻找 -->
  <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
      <!-- 基本包，会查询包及其子包 -->
      <property name="basePackage" value="cn.itcast.ssm.mapper"/>
  </bean>
  ```

  * Service层：

    * applicationContext-service.xml：**包扫描器**，扫描**@service**注解的类

    ```xml
    <!-- 配置service扫描 -->
    <context:component-scan base-package="cn.itcast.ssm.service" />
    ```

    * applicationContext-trans.xml：配置事务	

  * Controller层：

    * **springmvc.xml**：1.**包扫描器**，扫描**@Controller**注解的类；2.配置**注解驱动**；3.配置**视图解析器**

    ```xml
    <!-- 配置Controller扫描 -->
    <context:component-scan base-package="com.itheima.crm.controller"/>
    
    <!-- 注解驱动 替代上两个-->
    <mvc:annotation-driven/>
    
    <!-- 视图解释器 省略前后缀-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property value="/WEB-INF/jsp/" name="prefix"/>
        <property value=".jsp" name="suffix"/>
    </bean>
    <!-- 对静态资源放行 -->
    <mvc:resources location="/css/" mapping="/css/**"/>
    <mvc:resources location="/js/" mapping="/js/**"/>
    <mvc:resources location="/fonts/" mapping="/fonts/**"/>
    ```

  * **web.xml**：1.**配置spring**；2.配置**前端控制器**

    ```xml
    <!-- 指定Spring配置文件(下部分代码由IDEA自动生成)-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    <!-- 配置监听器加载spring配置文件 -->
    <listener>
        <listener-class>
            org.springframework.web.context.ContextLoaderListener
        </listener-class>
    </listener>
    
    <!-- 配置过滤器，解决post的乱码问题 -->
    <filter>
        <filter-name>encoding</filter-name>
        <filter-class>
            org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <!-- 配置SpringMVC前端控制器 -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>
            org.springframework.web.servlet.DispatcherServlet
        </servlet-class>
        <!-- 指定SpringMVC配置文件，可以不配置，默认找/WEB-INF/[servlet的名称]-servlet.xml -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring/springmvc.xml</param-value>
        </init-param>
        <!-- 配置springmvc什么时候启动，参数必须为整数
      为0或者大于0，则springMVC随着容器启动而启动；小于0，则在第一次请求进来的时候启动 -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <!-- 1. /* 拦截所有 jsp js png .css 建议不使用 
       2. *.action *.do 拦截以do action 结尾的请求 肯定能使用 ERP 
             3. / 拦截所有（只不包括jsp) 强烈建议使用 前台 面向消费者 对静态资源放行 -->
        <url-pattern>*.form</url-pattern>
    </servlet-mapping>
    ```


## 3 参数绑定

### 3.1 默认支持的参数类型

* **控制器**(处理器)**形参**中添加如下类型的参数，处理适配器会默认识别并进行赋值

  * HttpServletRequest：通过request对象获取请求信息
  * HttpServletResponse：通过response处理响应信息
  * HttpSession：通过session对象得到session中存放的对象

* Model/ModelMap

  * 除了ModelAndView以外，还可以使用Model(接口)来向页面传递数据，在参数里直接声明model即可

    使用Model则可以不使用ModelAndView对象，Model对象可以向页面传递数据，View对象则可以使用String返回值替代。其本质都是使用Request对象向jsp传递数据

    ```java
    model.addAttribute("item", item);
    return "itemEdit";
    ```

  * ModelMap是Model接口的实现类，也可以通过ModelMap向页面传递数据。效果一样，同上代码

### 3.2 *绑定简单类型

* 当**请求的参数名称**和**处理器形参名称**一致时会将请求参数与形参进行绑定

  ```java
  @RequestMapping("/itemEdit")
  public String queryItemById(int id, ModelMap model) {
      // 根据id查询商品数据
      Item item = this.itemService.queryItemById(id);
      // 把商品数据放在模型中
      model.addAttribute("item", item);
      return "itemEdit";
  }
  ```

  * 参数类型**推荐**使用**包装数据类型**，因为基础数据类型不可以为null
    * 布尔类型的参数，请求的参数值为true或false，或者1或0

* @**RequestParam**常用于处理简单类型的绑定，但是还不如参数名称一致好使

  ```java
  @RequestMapping("/itemEdit")
  //value：参数名字，即入参的请求参数名字
  public String queryItemById(@RequestParam(value = "itemId", required = true, defaultValue = "1") Integer id,ModelMap modelMap) {
      // 根据id查询商品数据
      Item item = this.itemService.queryItemById(id);
      // 把商品数据放在模型中
      modelMap.addAttribute("item", item);
      return "itemEdit";
  }
  ```

### 3.3 *绑定POJO类型

* 需求：将页面修改后的商品信息保存到数据库中

* **使用pojo接收表单数据**：如果**提交的参数很多**，或者提交的表单中的**内容很多**的时候

* **要求**：pojo对象中的**属性名**和表单中**input的name**属性**一致**

* **解决POST乱码问题**：配置在web.xml中；GET乱码对参数进行重新编码

  ```java
  String name = new String(request.getParamter("name").getBytes("ISO8859-1"),"utf-8")
  ```

### 3.4 *绑定包装POJO类型

* 包装对象定义如下：

  ```java
  public class QueryVo {
      private Item item;
      set/get。。。
  }
  ```

* 页面定义如下：

  ```html
  商品id：<input type="text" name = "item.id">
  商品名称：<input type="text" name = "item.name">
  ```

* 接收查询条件

  ```java
  // 绑定包装数据类型
  @RequestMapping("/queryItem")
  public String queryItem(QueryVo queryVo) {
      System.out.println(queryVo.getItem().getId());
      System.out.println(queryVo.getItem().getName());
      return "success";
  }
  ```


### 3.5 *自定义参数绑定

* 需求：在商品修改页面可以修改商品的生产**日期**，并且根据业务需求**自定义日期格式**

* 日期数据有很多种格式，SpringMVC没办法把字符串转换成日期类型，所以需要自定义参数绑定
* 前端控制器接**收到请求**后，找到注解形式的**处理器适配器**，对@RequestMapping标记的方法进行适配，并对方法中的形参进行参数绑定。可以在SpringMVC处理器适配器上**自定义转换器Converter**进行参数绑定
* 一般使用**< mvc:annotation-driven/>注解驱动加载处理器适配器**，可以在此标签上进行配置

* **自定义的Converter**

  ```java
  //Converter<S, T>
  //S:source,需要转换的源的类型
  //T:target,需要转换的目标类型
  public class DateConverter implements Converter<String, Date> {
      @Override
      public Date convert(String source) {
          try {
              // 把字符串转换为日期类型
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              Date date = simpleDateFormat.parse(source);
              return date;
          } catch (ParseException e) {
              e.printStackTrace();
          }
          return null;// 如果转换异常则返回空
      }
  }
  ```

* **配置Converter**，我们同时可以配置多个的转换器

  ```xml
  <!-- 配置注解驱动 -->
  <!-- 如果配置此标签,可以不用配置... -->
  <mvc:annotation-driven conversion-service="conversionService" />
  
  <!-- 转换器配置 -->
  <bean id="conversionService" class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
      <property name="converters">
          <set>
              <bean class="cn.itcast.springmvc.converter.DateConverter" />
          </set>
      </property>
  </bean>
  ```


