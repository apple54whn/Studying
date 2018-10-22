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



### 3.6 *高级参数绑定

#### 3.6.1 绑定到数组

* 需求：在商品列表页面**选中多个商品**，选中多个商品后点击删除按钮把商品**id**传递给Controller，然后**删除**

* Controller方法中可以用**数组接收**，或者**pojo中某数组属性接收**，两种方式任选其一即可。表单中name不用改

  ```java
  @RequestMapping("queryItem")
  public String queryItem(Integer[] ids) { //或QueryVo queryVo
  
  	system.out.println(ids);//两种方法都是这样调用即可显示
      return "success";
  }
  ```

#### 3.6.2 绑定到List

* 需求：在商品列表页面中可以对商品信息进行修改，可以**批量提交修改**后的商品数据

* 定义pojo：**List中存放对象**，并将定义的List**放在包装类QueryVo中**

  ```java
  private List<Item> itemList;//QueryVo类中
  ```

* 前端页面：name属性必须是**list属性名+下标+元素属性**

  ```jsp
  <input type="hidden" name="itemList[${s.index}].id" value="${item.id }"/>
  <input type="text" name="itemList[${s.index}].name" value="${item.name }"/>
  ```

* **接收List类型的数据必须是pojo的属性**，如3.6.1中pojo方法



## 4 @RequestMapping

* 通过@RequestMapping注解可以**定义不同的处理器映射规则**

### 4.1 URL路径映射

* **value**的值是**数组**，可以将**多个url映射到同一个方法**

  ```java
  @RequestMapping(value="itemList")
  @RequestMapping(value={"itemList","itemListAll"})
  // @RequestMapping("/item"） 只有在其值时一个类型时使用
  ```

  * **添加在类上面(前缀)**，限制此类下的所有方法请求url必须以请求前缀开头，**以便对url进行分类管理**

### 4.2 请求方法限定

* **method**限定请求进来的方法，值也是**数组**

  ```java
  @RequestMapping(value = "itemList",method = {RequestMethod.GET,RequestMethod.POST})
  ```


## 5 Controller方法返回值

### 5.1. 返回ModelAndView

* controller方法中定义ModelAndView对象并返回，对象中可添加model数据、指定view

  ```java
  @RequestMapping(value = "/iteam/iteamlist.action")
  public ModelAndView itemList(){
      ...
      ModelAndView modelAndView = new ModelAndView();
      modelAndView.addObject("iteamList",list);
      modelAndView.setViewName("/WEB-INF/jsp/iteamList.jsp");
      return modelAndView;
  }
  ```

### 5.2 *void(Ajax使用)

* 在Controller方法形参上可以**定义request和response**，使用request或response**指定响应结果**

  ```java
  //使用request请求转发页面
  request.getRequestDispatcher("页面路径").forward(request, response);
  //通过response页面重定向
  response.sendRedirect("url")
  //通过response指定响应结果，例如响应json数据如下
  response.getWriter().print("{\"abc\":123}");
  ```

### 5.3 *返回字符串(推荐)

#### 5.3.1 返回逻辑视图名

* controller方法返回字符串可以**指定逻辑视图名**，通过视图解析器解析为物理视图地址

  ```java
  //指定逻辑视图名，经过视图解析器解析为jsp物理路径：/WEB-INF/jsp/itemList.jsp
  return "itemList";
  ```

#### 5.3.2 Redirect重定向

* Controller方法返回字符串可以**重定向到一个url地址**

  ```java
  @RequestMapping("updateItem")
  public String updateItemById(Item item) {
      // 更新商品
      this.itemService.updateItemById(item);
  
      // 修改商品成功后，重定向到商品编辑页面
      // 重定向后浏览器地址栏变更为重定向的地址，
      // 重定向相当于执行了新的request和response，所以之前的请求参数都会丢失
      // 如果要指定请求参数，需要在重定向的url后面添加 ?itemId=1 这样的请求参数
      return "redirect:/itemEdit.action?itemId=" + item.getId();
  }
  ```

#### 5.3.3 forward转发

* Controller方法执行后继续执行另一个Controller方法，如商品修改提交后转向到商品修改页面，修改商品的id参数可以带到商品修改方法中

  ```java
  @RequestMapping("updateItem")
  public String updateItemById(Item item) {
      // 更新商品
      this.itemService.updateItemById(item);
  
      // 修改商品成功后，继续执行另一个方法
      // 使用转发的方式实现。转发后浏览器地址栏还是原来的请求地址，
      // 转发并没有执行新的request和response，所以之前的请求参数都存在
      return "forward:/itemEdit.action";
  }
  ```


## 6 异常处理器

* SpringMVC在处理请求过程中出现异常信息交由异常处理器进行处理，自定义异常处理器可以实现一个系统的异常处理逻辑

* 思路：

  * 系统中异常包括两类：**预期异常**和运行时异常**RuntimeException**，前者通过捕获异常从而获取异常信息，后者主要通过规范代码开发、测试通过手段减少运行时异常的发生
  * 系统的dao、service、controller出现都通过throws Exception向上抛出，最后由SpringMVC**前端控制器交由异常处理器**进行异常处理

* **自定义异常类(继承Exception或RuntimeException)**：为了区别不同的异常,通常根据异常类型进行区分

  ```java
  public class MyException{
      public MyException(){};
      public MyException(String msg){
          super(msg);
      };
  }
  ```

* **自定义异常处理器(实现HandlerExceptionResolver)**，并**在springmvc.xml中实例化配置**

  ```java
  public class CustomHandleException implements HandlerExceptionResolver {
     	//Object:发生异常的地方，包名+类名+方法名(形参)的字符串，用于日志
      @Override
      public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception exception) {
          
          ModelAndView modelAndView = new ModelAndView();
          // 定义异常信息
          String msg;
  
          // 判断异常类型
          if (exception instanceof MyException) {
              // 如果是自定义异常，读取异常信息
              msg = exception.getMessage();
          } else {
              //简写
              modelAndView.addObject("msg", "未知异常");
              //或 如果是运行时异常，则取错误堆栈，从堆栈中获取异常信息
              Writer out = new StringWriter();
              PrintWriter s = new PrintWriter(out);
              exception.printStackTrace(s);
              msg = out.toString();
  
          }
          // 把错误信息发给相关人员,邮件,短信等方式
          // 返回错误页面，给用户友好页面显示错误信息
         
          modelAndView.addObject("msg", msg);
          modelAndView.setViewName("error");
  
          return modelAndView;
      }
  }
  ```



## 7 处理multipart数据

* 需要配置**MultipartResolver**接口的实现类，下面不提供xml配置

  * CommonsMultipartResolver：使用Jakarta Commons FileUpload解析multipart请求

  * **StandardServletMultipartResolver**：依赖于**Servlet3.0**对multipart请求支持（**始于Spring3.1**）

    选择这个，它使用Servlet所提供的功能支持，不依赖其他项目。它**没有构造器参数和属性**

    ```java
    @Bean
    public MultipartResolver multipartResolver() throws IOException {
        return new StandardServletMultipartResolver();
    }
    ```

    如果配置DispatcherServlet的Servlet初始化类继承了**AbstractAnnotationConfigDispatcherServletInitializer**或AbstractDispatcherServletInitializer的话，通过**重载customize Registration()方法**（它会得到Dynamic参数）来配置multipart的具体细节

    ```java
    //class Config extends AbstractAnnotationConfigDispatcherServletInitializer
    @Override
    protected void customizeRegistration(Dynamic registration) {
        registration.setMultipartConfig(new MultipartConfigElement("/tmp/file/uploads",2097152,4194304,0));
        //location,maxFileSize,maxRequestSize,fileSizeThreshold(为0则上传文件写到磁盘)
    }
    ```

* **处理multipart请求**

  * form标签中**enctype**属性设置为**multipart/form-data**，告诉浏览器以multipart数据形式提交表单

* **MultipartFile接口**

  ```java
  @RequestMapping("updateItem")
  public String updateItemById(Item item, MultipartFile pictureFile) throws Exception {
      // 图片上传
      // 设置图片名称，不能重复，可以使用uuid
      String picName = UUID.randomUUID().toString();
  
      // 获取文件名
      String oriName = pictureFile.getOriginalFilename();
      // 获取图片后缀
      String extName = oriName.substring(oriName.lastIndexOf("."));
  
      // 开始上传
      pictureFile.transferTo(new File("C:/upload/image/" + picName + extName));
  
      // 设置图片名到商品中
      item.setPic(picName + extName);
  
      // 更新商品
      this.itemService.updateItemById(item);
      return "forward:/itemEdit.action";
  }
  ```



  ## 8 Json数据交换及RESTful

### 8.1 @RequestBody

* **读取http请求的内容(字符串)**，通过SpringMVC提供的HttpMessageConverter接口将读到的内容(json数据)转换为java对象并**绑定到Controller方法的参数上，如8.2**

  * 传统的请求参数：itemEdit.action?id=1&name=zhangsan&age=12

  * 现在的请求参数：使用POST请求，在请求体里面加入json数据

    ```json
    {
        "id": 1,
        "name": "测试商品",
        "price": 99.9,
        "detail": "测试商品描述",
        "pic": "123456.jpg"
    }
    ```


### 8.2 @ResponseBody

* **将Controller的方法返回的对象**，通过SpringMVC提供的HttpMessageConverter接口**转换为指定格式的数据**如：json,xml等，**通过Response响应给客户端**
  * 如果需要SpringMVC支持json，必须加入json的处理**jar包**：Jackson

    > jackson-annotations.jar
    >
    > jackson-core-2.4.2.jar
    >
    > jackson-databind-2.4.2.jar

  ```java
  @RequestMapping("testJson")
  @ResponseBody
  public Item testJson(@RequestBody Item item) {
      //    发送json串                   接收json串
      return item;
  }
  ```



### 8.3 RESTful

* RESTful是一个资源定位及资源操作的风格。使用POST、DELETE、PUT、GET，使用不同方法对资源进行操作，分别对应 添加、 删除、修改、查询

* 需求：RESTful方式实现商品信息查询，返回json数据

  * **从URL上获取参数**：根据id查询商品，使用RESTful风格开发的接口地址是：http://127.0.0.1/item/1

    * 注解**@RequestMapping("item/{id}")**声明请求的URL，{xxx}为占位符，请求的URL是“item /1”

    * 使用**@PathVariable() Integer id**获取url上的数据

      ```java
      @RequestMapping("item/{id}")
      @ResponseBody
      public Item queryItemById(@PathVariable Integer id) {
          Item item = this.itemService.queryItemById(id);
          return item;
      }
      ```

      * 如果@RequestMapping中表示为"item/{id}"，id和形参名称一致，@PathVariable不用指定名称。如果不一致，例如"item/{ItemId}"则需要指定名称@PathVariable("itemId")

    * **注意**：

      * @PathVariable是获取url上数据的。@RequestParam获取请求参数的（包括post表单提交）
      * 如果加上@ResponseBody注解，就不会走视图解析器，不会返回页面，目前返回的json数据。如果不加，就走视图解析器，返回页面



## 9 拦截器

* 类似于Servlet 开发中的过滤器Filter，用于对处理器进行预处理和后处理

### 9.1 拦截器定义

* **实现HandlerInterceptor接口**

  ```java
  public class HandlerInterceptor1 implements HandlerInterceptor {
      // Controller执行前调用此方法
      // 返回true表示继续执行，返回false中止执行
      // 这里可以加入登录校验、权限拦截等
      @Override
      public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
          System.out.println("HandlerInterceptor1....preHandle");
          return true;
      }
  
      // controller执行后但未返回视图前调用此方法
      // 这里可在返回用户前对模型数据进行加工处理，比如这里加入公用信息以便页面显示
      @Override
      public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) throws Exception {
          System.out.println("HandlerInterceptor1....postHandle");
      }
  
      // controller执行后且视图返回后调用此方法
      // 这里可得到执行controller时的异常信息
      // 这里可记录操作日志
      @Override
      public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
          System.out.println("HandlerInterceptor1....afterCompletion");
      }
  }
  ```

### 9.2 拦截器配置

```xml
//springmvc-config.xml
<!-- 配置拦截器 -->
<mvc:interceptors>
    <mvc:interceptor>
        <!-- 所有的请求都进入拦截器 -->
        <mvc:mapping path="/**" />
        <!-- 配置具体的拦截器 -->
        <bean class="cn.itcast.ssm.interceptor.HandlerInterceptor1" />
    </mvc:interceptor>
    <mvc:interceptor>
        <!-- 所有的请求都进入拦截器 -->
        <mvc:mapping path="/**" />
        <!-- 配置具体的拦截器 -->
        <bean class="cn.itcast.ssm.interceptor.HandlerInterceptor2" />
    </mvc:interceptor>
</mvc:interceptors>
```

* 总结：

  * preHandle按拦截器定义顺序调用，**返回false时后续拦截器将不调用**

  * postHandler按拦截器定义逆序调用

  * postHandler在拦截器链内**所有拦截器返回成功时调用**

  * afterCompletion按拦截器定义逆序调用

  * afterCompletion**只有preHandle返回true才调用**



### 9.3 应用

* 有一个登录页面，需要写一个Controller访问登录页面
* 登录页面有一提交表单的动作。需要在Controller中处理
  * 判断用户名密码是否正确（在控制台打印）
  * 如果正确,向session中写入用户信息（写入用户名username）
  * 跳转到商品列表
* 拦截器
  * 拦截用户请求，判断用户是否登录（登录请求不能拦截）
  * 如果用户已经登录。放行
  * 如果用户未登录，跳转到登录页面。





# 第四部分 Maven

## 1 Maven简介

是apache下的一个开源项目，是纯java开发，用于对java项目进行**构建**、**依赖管理**

- **项目构建**：一个项目从编写源代码到编译、测试、运行、打包、部署、运行的过程
  - Maven项目构建过程：
    - 清理(**clean**)——>编译(**compile**)——>测试(test)——>报告——>打包(**package**)——>部署
    - 运行一个Maven工程(web项目)的命令：tomcat:run
- **依赖管理**：java项目所依赖jar包的规范化管理
  - Maven项目的jar包只需在**pom.xml**添加jar包的**坐标**，自动从Maven仓库下载jar包、运行



Maven文件夹目录

​	|——bin：mvn.bat(run方式运行项目)、mvnDebug.bat(debug方式运行项目)

​	|——boot：Maven运行需要类加载器

​	|——conf：**settings.xml**(整个Maven工具核心配置文件。配置本地仓库)

​	|——lib：Maven运行依赖的jar包



## 2 Maven项目工程目录

Project

​	|—src

​		|—main

​			|—java				存放项目的java文件

​			|—resources			存放项目的资源文件，如spring、mybatis等配置文件

​			|—webapp				web工程主目录

​				|—WEB-INF

​					|—web.xml

​		|—test

​			|—java				存放所有测试.java文件，如JUnit测试类

​			|—resources			测试资源文件，一般不放东西，调用的main中的资源文件

​	|—target						目标文件输出位置，如.class、.jar、.war文件

​	|—pom.xml					Maven项目核心配置文件





## 3 常用的Maven命令

- **tomcat:run**：在**当前项目的路径**中执行后，运行Maven工程项目
- mvn **spring-boot:run**：运行SpringBoot项目
- mvn **clean**：删除target及其内容
- mvn **compile**：只编译了main目录的文件
- mvn **test**：只编译test目录文件并运行
- mvn **package**：根据项目类型打包
- mvn **install**：把项目发布到本地仓库，web项目不用(因为是war包)，一般java项目用来打jar包



## 4 Maven的生命周期（了解）

compile——>test——>package——>install——deploy（按顺序）

- Clean生命周期：clean命令
- Default生命周期：上面4个
- Site生命周期：mvn site生成项目的站点文档

命令和生命周期的阶段的关系：不同的生命周期的命令可以同时执行(mvn clean package)



## 5 依赖管理

- 在pom.xml中，添加dependency标签，并填写坐标。
  - 可以在Maven repository网站上查找
  - 在本地重建索引，以索引方式搜索
- **依赖范围**（A依赖B，需要在A的pom.xml文件中添加B的坐标，同时指定依赖范围）
  - Compile：编译范围，指A在编译时依赖B，为默认依赖范围。在编译、测试、运行、打包时需要
    - 如：struts2-core
  - **Provided**：依赖只有在当JDK或者一个容器已经提供该依赖后才使用，在编译、测试时需要
    - 如：jsp-api.jar   servlet-api.jar
  - Runtime：在测试、运行、打包时需要
    - 如：数据库驱动包
  - Test：只测试时需要
    - 如：JUnit.jar
- 依赖传递：只添加一个依赖，有关这个依赖的依赖都添加过来了
- 解决依赖冲突：
  - 调解：
    - 第一声明者优先原则（先声明的用）
    - 路径近者优先原则（A依赖spring-bean1，A依赖B依赖spring-bean2，则应该用1）
  - 排除依赖：
  - 锁定版本：







# 第五部分 Spring Boot

## Spring Boot 简介

* Spring Boot来简化Spring应用开发，约定大于配置， 去繁从简，just run就能创建一个独立的，产品级别应用
* 背景：J2EE笨重的开发、繁多的配置、低下的开发效率、 复杂的部署流程、第三方技术集成难度大
* 解决：
  * “Spring全家桶”时代。
  * Spring Boot：J2EE**一站式解决方案** 
  * Spring Cloud：分布式整体解决方案
* **优点**：
  * **快速创建**独立运行的Spring项目以及与主流框架集成
  * 使用**嵌入式的Servlet容器**，应用无需打成WAR包
  * **starters自动依赖与版本控制**
  * 大量的**自动配置**，简化开发，也可修改默认值
  * **无需配置XML，无代码生成**，开箱即用 
  * 准生产环境的运行时应用**监控**
  * 与**云计算**的天然集成

## 微服务

* 2014，martin fowler提出的架构风格（服务微化）。一个应用应该是一组小型服务，可以通过HTTP的方式进行互通
  * 单体应用：ALL IN ONE
  * 微服务：每一个功能元素最终都是一个可独立替换和独立升级的软件单元
* [详细参照微服务文档](https://martinfowler.com/articles/microservices.html#MicroservicesAndSoa)

## 环境准备

* **Maven设置**：给maven 的settings.xml配置文件的profiles标签添加如下内容

  ```xml
  <profile>
      <id>jdk-1.8</id>
      <activation>
          <activeByDefault>true</activeByDefault>
          <jdk>1.8</jdk>
      </activation>
      <properties>
          <maven.compiler.source>1.8</maven.compiler.source>
          <maven.compiler.target>1.8</maven.compiler.target>
          <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
      </properties>
  </profile>
  ```

* IDEA设置：整合安装的Maven

  * Settings—build—Build Tools—Maven



## 1 Spring Boot 入门

- 使用Spring Initializr初始化Spring Boot项目： https://start.spring.io/

- 默认生成的Spring Boot项目；

  - 主程序已经生成好了，我们只需要我们自己的逻辑
  - **resources**文件夹中目录结构
    - **static**：保存所有的静态资源； js css images；
    - **templates**：保存所有的模板页面；（Spring Boot默认jar包使用嵌入式的Tomcat，默认不支持JSP页面）；可以使用模板引擎（freemarker、thymeleaf）；
    - **application.properties**：Spring Boot应用的配置文件；可以修改一些默认设置；

- pom.xml之打包插件，**简化部署**，通过**java -jar** 包名来运行应用，Spring Boot使用嵌入式的Tomcat无需配置

  ```xml
  <!--这个插件可将应用打包成可执行的jar包，使用Spring Initializr 创建web项目会自动添加这个依赖-->
  <build>
      <plugins>
          <plugin>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-maven-plugin</artifactId>
          </plugin>
      </plugins>
  </build>
  ```

### 1.1 HelloWorld探究

#### 1.1.1 POM文件

* **父项目**

  ```xml
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.0.6.RELEASE</version>
      <relativePath/> <!-- lookup parent from repository -->
  </parent>
  ```

  他的父项目是：

  ```xml
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>2.0.6.RELEASE</version>
      <relativePath>../../spring-boot-dependencies</relativePath>
  </parent>
  ```

  这是真正管理Spring Boot应用里面所依赖的版本。Spring Boot的版本仲裁中心，以后我们**导入依赖默认是不需要写版本**；（没有在dependencies里面管理的依赖自然需要声明版本号）

* **启动器**

  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  		...
  </dependencies>
  ```

  * **spring-boot-starter**：spring-boot**场景启动器**，帮我们**导入**了web模块**正常运行所依赖的组件**
  * Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（启动器），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来

#### 1.1.2 主程序类、主入口类

```java
//@SpringBootApplication 来标注一个主程序，说明这是一个SpringBoot应用
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // Spring应用启动起来
        SpringApplication.run(Application.class, args);
    }
}
```

* **@SpringBootApplication** : Spring Boot应用标注在某个类上说明这个类是SpringBoot的**主配置类**，SpringBoot就应该**运行这个类的main方法**来**启动**SpringBoot应用。是如下注解的**简写**：

  ```java
  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  @Inherited
  @SpringBootConfiguration
  @EnableAutoConfiguration
  @ComponentScan(excludeFilters = {
        @Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
        @Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
  public @interface SpringBootApplication {
  ```

  - @**SpringBootConfiguration** : Spring Boot的配置类，标注在某个类上表示这是一个Spring Boot配置类

  - @**Configuration** : 配置类上来标注这个注解，配置类也是容器中的一个组件@Component

  - @**EnableAutoConfiguration**：开启自动配置功能，**如下注解的简写**

    以前我们需要配置的东西，Spring Boot帮我们自动配置；@**EnableAutoConfiguration**告诉SpringBoot开启自动配置功能；这样自动配置才能生效；

    ```java
    @AutoConfigurationPackage
    @Import(EnableAutoConfigurationImportSelector.class)
    public @interface EnableAutoConfiguration {...}
    ```

    * @**AutoConfigurationPackage**：自动配置包，**如下注解的简写**

      * @**Import**(AutoConfigurationPackages.Registrar.class)

        Spring底层注解@Import，给容器中导入一个组件；导入的组件由AutoConfigurationPackages.**Registrar**.class**指定**，这个类有一个方法，通过注解metadata，将

        ==**主配置类**（@SpringBootApplication）所在**包及下面所有子包**里面的**所有组件扫描到Spring容器**==

    * @**Import**({AutoConfigurationImportSelector.class})

      **AutoConfigurationImportSelector**：导入哪些组件的选择器

      将所有需要导入的组件以全类名的方式返回，这些组件就会被添加到容器中； 会给容器中**导入非常多的自动配置类**（xxxAutoConfiguration），就是给容器中导入这个场景需要的所有组件，并配置好这些组件；

      ![自动配置类](F:\GitHub\Studying\Spring Boot\images\自动配置类.PNG)

      * 有了自动配置类，免去了我们手动编写配置注入功能组件等的工作

      * 调用了SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader)方法。Spring Boot在**启动的时候从类路径下**的META-INF/spring.factories中获取**EnableAutoConfiguration指定的值**，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作

      * J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.9.RELEASE.jar





## 2 Spring Boot 配置

### 2.1 配置文件

* SpringBoot使用一个全局的配置文件，配置文件名是固定的；
  * **application.properties**
  * **application.yml**

* 配置文件的**作用**：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；

* YAML（YAML Ain't Markup Language）

  YAML A Markup Language：是一个标记语言

  YAML isn't Markup Language：不是一个标记语言

* 标记语言：

  * 以前的配置文件；大多都使用的是 **xxx.xml**文件

    ```xml
    <server>
        <port>8081</port>
    </server>
    ```

  * YAML：**以数据为中心**，比json、xml等更适合做配置文件

    ```yml
    server:
      port: 8081
    ```

### 2.2 YML语法

* 基本语法：**k:(空格)v**：表示一对键值对（空格必须有）

  * 以**空格**的**缩进**来控制层级关系；只要是左对齐的一列数据，都是同一个层级的；属性和值也是**大小写敏感**

* **值的写法**

  * **字面量**：普通的值（**数字，字符串，布尔**）

    * k: v：字面**直接来写**；**字符串默认不用加上单引号或者双引号**；
      * ""：**双引号**；**不会转义**字符串里面的特殊字符；特殊字符会作为本身想表示的意思
        * name: "zhangsan \n lisi"：输出；zhangsan 换行 lisi
      * ''：**单引号**；**会转义**特殊字符，特殊字符最终只是一个普通的字符串数据
        * name: ‘zhangsan \n lisi’：输出；zhangsan \n lisi

  * **对象、Map**（属性和值）（键值对）：

    * k: v：在下一行来写对象的属性和值的关系；注意缩进；对象还是k: v的方式

      ```yaml
      friends:
      	lastName: zhangsan
      	age: 20
      ```

      ```yaml
      #行内写法
      friends: {lastName: zhangsan, age: 18}
      ```

  * **数组（List、Set）：**

    * 用**-** 值表示数组中的一个元素

      ```yaml
      pets:
       - cat
       - dog
       - pig
      ```

      ```yaml
      #行内写法
      pets: [cat, dog, pig]
      ```


### 2.3 配置文件值注入

* **配置文件**

  ```yaml
  person:
      lastName: hello
      age: 18
      boss: false
      birth: 2017/12/12
      maps: {k1: v1, k2: 12}
      lists:
        - lisi
        - zhaoliu
      dog:
        name: 小狗
        age: 12
  ```

  ```properties
  #IDEA使用的是UTF-8，但properties文件以前都是ASCII码，需在IDEA中设置
  person.last-name=张三
  person.age=15
  person.birth=2017/12/12
  person.boss=false
  person.lists=a,b,c
  person.maps.k1=v1
  person.maps.k2=v2
  person.dog.name=dog
  person.dog.age=5
  ```

* **JavaBean**

  * **将配置文件中配置的每一个属性的值，映射到这个组件中**
  * **@ConfigurationProperties**：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
  * **prefix** = "person"：**全局配置文件**中哪个下面的所有属性进行一一映射
  * **@Component**只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
  * @Data是一个插件，免去了getter/setter和toString这些繁琐的东西

  ```java
  @Data
  @Component
  @ConfigurationProperties(prefix = "person")
  public class Person {
      private String lastName;
      private Integer age;
      private Boolean boss;
      private Date birth;
  
      private Map<String,Object> maps;
      private List<Object> lists;
      private Dog dog;
  }
  ```

* 出现以下提示，进入官网，在pom中**导入配置文件处理器**，以后编写配置就有提示了

  ![](F:\GitHub\Studying\Spring Boot\images\配置文件处理器.PNG)

  ```xml
  <!--导入配置文件处理器，配置文件进行绑定就会有提示-->
  <dependency>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-configuration-processor</artifactId>
  	<optional>true</optional>
  </dependency>
  ```



#### 2.3.1 properties配置文件在idea中默认utf-8可能会乱码

![](F:\GitHub\Studying\Spring Boot\images\properties乱码配置.png)



#### 2.3.2 @Value获取值和@ConfigurationProperties获取值比较

|                      | @ConfigurationProperties | @Value     |
| -------------------- | ------------------------ | ---------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定（松散语法） | 支持                     | 不支持     |
| SpEL                 | 不支持                   | 支持       |
| JSR303数据校验       | 支持                     | 不支持     |
| 复杂类型封装         | 支持                     | 不支持     |

* **松散语法绑定**：last_name = last-name = lastName 他们取的值都是相同的

* 配置文件yml还是properties他们都能获取到值，怎么选择呢？
  * 若我们只是在某个业务逻辑中需要**获取**一下**配置文件**中的**某项值**，使用@Value；
  * 若我们专门编写了一个javaBean来和配置文件**映射**，我们就直接使用@ConfigurationProperties



#### 2.3.3 配置文件注入值数据校验

```java
@Component
@ConfigurationProperties(prefix = "person")
@Validated
public class Person {
    /**
     * <bean class="Person">
     *      <property name="lastName" value="字面量/${key}从环境变量、配置文件中获取值/#{SpEL}">
     *      </property>
     * <bean/>
     */
    
    // lastName必须是邮箱格式
	@Email
    // @Value("${person.last-name}")
    private String lastName;
    // @Value("#{11*2}")
    private Integer age;
    // @Value("true")
    private Boolean boss;

    private Date birth;
    // @Value("${person.maps}")
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
}
```



#### 2.3.4 @PropertySource & @ImportResource

* @**PropertySource**：加载**指定的配置文件**，由于@ConfigurationProperties(prefix = "person")默认从全局配置文件中获取值；

  ```java
  /**
   * 将配置文件中配置的每一个属性的值，映射到这个组件中
   * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
   *      prefix = "person"：配置文件中哪个下面的所有属性进行一一映射
   *
   * 只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
   *
   */
  @PropertySource(value = {"classpath:person.properties"})
  @Component
  @ConfigurationProperties(prefix = "person")
  //@Validated
  public class Person {
  
      private String lastName;
      private Integer age;
      private Boolean boss;
  ```

* @**ImportResource**：**导入Spring的配置文件，如beans.xml，加载bean**，让配置文件里面的内容生效

  * Spring Boot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别；

    想让Spring的配置文件生效，加载进来；@**ImportResource**标注在一个配置类上

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <beans xmlns="http://www.springframework.org/schema/beans"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
  
      <bean id="helloService" class="com.cuzz.springboot.service.HelloService"></bean>
  </beans>
  ```

  ```java
  @ImportResource(locations = {"classpath:beans.xml"})
  @SpringBootApplication
  public class SpringbootApplication {
  
  	public static void main(String[] args) {
  		SpringApplication.run(SpringbootApplication.class, args);
  	}
  }
  ```



#### 2.3.5 @Bean

* 之前编写xml配置文件，现在不再推荐

* ==SpringBoot推荐**给容器中添加组件**的方式：使用**全注解**的方式==

  * 配置类**@Configuration** --> Spring配置文件

  * 使用**@Bean**给容器中添加组件

  ```java
  /**
   * @Configuration：指明当前类是一个配置类；就是来替代之前的Spring配置文件
   */
  @Configuration
  public class MyAppConfig {
  
      // 将方法的返回值添加到容器中；容器中这个组件默认的id就是方法名
      @Bean
      public HelloService helloService02(){
          System.out.println("配置类@Bean给容器中添加组件了...");
          return new HelloService();
      }
  }
  ```






## 2 请求数据

* **@RestController**：Spring4的新注解，将类标记为控制器，其中每个方法都返回一个域对象而不是视图，这是@Controller和@ResponseBody的简写

* @**RequestMapping**、@GetMapping、@PostMapping、@PutMapping、@DeleteMapping。value中也可以用正则表达式限制类型。若不符合要求会返回4xx的错误信息，表示请求有问题

  * 当POST方法传递的是表单数据时，可在上述注解加**consumes参数**（根据请求的**Content-Type**缩小请求映射范围）

    * 设置consumes ={MediaType.MULTIPART_FORM_DATA_VALUE ,MediaType.APPLICATION_ATOM_XML_VALUE})，任选，<a href="#postman">详细区别看这里</a>。第一个可以传文件，第二个传文件慢

    * 在@PostMapping**上传文件**方法中使用**MultipartFile**接口的流保存文件

      ```java
      public void uploadFile(@RequestParam MultipartFile file) {...}
      ```

  * GET方法**下载文件**，设置**produces参数**根据**Accept**请求标头和控制器方法生成的内容类型列表来缩小请求映射

* @**PathVariable**：访问URL模板变量

* @**RequestBody**：通过HttpMessageConverter读取请求体并反序列化为Object

* @**RequestParam**：将Servlet请求参数（即查询参数或表单数据）绑定到控制器中方法的参数上，value可不写（必须和参数一致），默认required=true，可以设置为false防止异常

* @RequestHeader：将请求头绑定到控制器方法的参数上，value可不写（必须和参数一致），不区分大小写

* @CookieValue：将HTTP cookie的值绑定到控制器方法的参数上，同上。除非为了兼容老客户端，否则不用

* Authenticated：获取当前用户，直接在方法中增加参数，类型为它即可

```java
//此代码使用Spring 4的新注解@RestController，该注注解将类标记为控制器，
//其中每个方法都返回一个域对象而不是视图。 这是@Controller和@ResponseBody的简写。
@RestController
@RequestMapping(value = "/tvSeries")
public class TvSeriesController {

    /**
     * GET查询所有电视剧
     */
    @GetMapping
    public List<TvSeries> queryAll() {
        List<TvSeries> tvSeriesList = new ArrayList<>();
        tvSeriesList.add(new TvSeries(0, "天龙八部", 50, new Date()));
        tvSeriesList.add(new TvSeries(1, "笑傲江湖", 40, new Date()));
        return tvSeriesList;
    }

    /**
     * GET按id查询电视剧
     */
    @GetMapping(value = "/{id}")
    public TvSeries queryOne(@PathVariable Integer id) {

        if (id == 1) {
            return new TvSeries(0, "天龙八部", 50, new Date());
        } else if (id == 2) {
            return new TvSeries(1, "笑傲江湖", 40, new Date());
        } else
            return null;
    }

    /**
     * POST增加电视剧（没写持久层代码）
     */
    @PostMapping
    public TvSeries addOne(@RequestBody TvSeries tvSeries) {
        System.out.println(tvSeries);
        return tvSeries;
    }

    /**
     * PUT更改电视剧
     */
    @PutMapping(value = "/{id}")
  	public TvSeries updateOne(@PathVariable Integer id, @RequestBody TvSeries tvSeries) {
        if (id == 1) {
            TvSeries tvSeries1 = new TvSeries(0, "天龙八部", 50, new Date());
            tvSeries1.setName(tvSeries.getName());
            return tvSeries1;
        } else {
            return null;
        }
    }
    
    /**
     * DELETE删除电视节目
     */
    @DeleteMapping(value = "/{id}")
    public Map<String, String> deleteOne(@PathVariable Integer id, HttpServletRequest request,                                      @RequestParam(value = "deleteReason", required = false) String deleteReason) {
        Map<String, String> result = new HashMap<>();
        if (id == 0) {
            //执行删除代码
            result.put("message", "#0被" + request.getRemoteAddr() + "删除，原因：" + deleteReason);
        } else if (id == 1) {
            //不能删除这个，抛异常。spring security处理更好，之后学习
            throw new RuntimeException("#1不能删除");
        } else
            throw new ResourceNotFoundException();
        return result;
    }
    
    /**
     * MultipartFile上传文件,利用consumes您根据请求的Content-Type缩小请求映射范围
     */
    @PostMapping(value = "/{id}/file" ,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@PathVariable Integer id,@RequestParam MultipartFile file) throws IOException {
        file.transferTo(new File("F:\\GitHub\\Studying\\SSM\\"+file.getOriginalFilename()));
        System.out.println(id+"id上传了文件");
    }
    
    /**
     * 下载文件，根据Accept请求标头和控制器方法生成的内容类型列表来缩小请求映射，如以下示例所示：
     */
    @GetMapping(value = "/{id}/file" ,produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] downloadFile(@PathVariable Integer id) throws IOException {
        System.out.println(id+"id下载了文件");
        return IOUtils.toByteArray(new FileInputStream("target/classes/WindowsPic.jpg"));

    }
}
```



### 2.1 数据校验(Bean Validation)

* 不要相信前端传入的数据，尽量要前端少传数据

* **使用基于注解的验证**

  > JSR303 是一套JavaBean参数校验的标准，它定义了很多常用的校验注解，我们可以直接将这些注解加在我们JavaBean的属性上面，就可以在需要校验的时候进行校验了。
  >
  > Hibernate validator是Bean Validation1.0（JSR303）的一个实现
  >
  > 目前最新的Hibernate validator 6.0 是Bean Validation 2.0（JSR380，于2017年8月完成）的一个实现
  >
  > [Bean Validation官网点这里](https://beanvalidation.org/)

* Hibernate Validator包含一组基本的常用约束，这些最重要的是Bean Validation规范定义的约束。此外，Hibernate Validator还提供了有用的自定义约束。

* @**Valid**      级联验证，**确保这个对象满足校验限制**。校验的对象后**紧跟着Errors或BindingResult参数**

  ```java
  @NotNull
  private List<@Valid Image> images;//images不可为null，每个Image元素需要级联验证
  
  @NotNull
  @Size(min=1,max=10)//messages不为null，长度至少为1，最长10；
  private List<@Size(min=10) @NotNull String> messages;//每个String不为空，长度至少为10
  ```

  ```java
  @PutMapping
  public TvSeries updateOne(@Valid @RequestBody TvSeries tvSeries,BindingResult result) {
      //参数没过校验也会进入方法执行，校验结果通过result参数传递进来
      if(result.hasErrors()){
          //没通过校验
      }
  }
  ```

  <!DOCTYPE html>
  <html>
  <head>
      <style>
          tr{
              color: rgb(76, 78, 47)
          }
          tr>td:first-child{
              font-weight: bold;
              color: black;
              font-family: consolas;

      <table border="3">
          <caption>注解说明表</caption>
          <tr style="background-color:#90CAF9">
             <td style="color: rgb(76, 78, 47);font-weight: normal;">注解</td>
             <td>说明</td>
             <td>支持的数据类型</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@Null</td>
             <td>被注释的元素必须为 null</td>
             <td rowspan="2">所有类型</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@NotNull</td>
             <td>被注释的元素必须不为 null</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@NotEmpty</td>
             <td>被注释的元素非null且非空（字符串是不是空格无所谓）</td>
             <td>CharSequence, Collection, Map and arrays</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@NotBlank</td>
             <td>被注释的元素非null，且必须包含至少一个非空格字符</td>
             <td>CharSequence</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@Pattern(regex=, flags=)</td>
             <td>被注释的字符串是否与给定的正则表达式匹配</td>
             <td>CharSequence</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@Email</td>
             <td>检查指定的字符序列是否是有效的电子邮件地址。 可选参数regexp和flags允许指定电子邮件必须匹配的附加正则表达式（包括正则表达式标志）</td>
             <td>CharSequence</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@AssertTrue</td>
             <td>被注释的元素必须为 true</td>
             <td rowspan="2">Boolean、boolean</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@AssertFalse</td>
             <td>被注释的元素必须为 false</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@DecimalMin(value=, inclusive=)</td>
             <td>当inclusive = false时，检查带注释的值是否大于指定的最小值；否则该值是否大于或等于指定的最小值。参数值是根据BigDecimal字符串表示形式的最大值的字符串表示形式</td>
             <td rowspan="3">BigDecimal，BigInteger，CharSequence，byte，short，int，long和原始类型相应包装类型</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@DecimalMax(value=, inclusive=)</td>
             <td>与上相反</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@Digits(integer=, fraction=)</td>
             <td>整数位数，小数位数必须在设置的范围内</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@Size(min=, max=)</td>
             <td>被注释元素的大小是否在最小值和最大值（包括）之间</td>
             <td>CharSequence, Collection, Map and arrays</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@Min(value=)</td>
             <td>被注释值是否大于或等于指定的最小值</td>
             <td rowspan="6">BigDecimal，BigInteger，byte，short，int，long和原始类型相应包装类型; 另外由HV支持：任何子类型的CharSequence（由字符序列表示的数值被评估），任何子类型的Number和javax.money.MonetaryAmount </td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@Max(value=)</td>
             <td>与上相反</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@Negative</td>
             <td>检查元素是否为负数，0为无效</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@NegativeOrZero</td>
             <td>检查元素是否为负数或0</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@Positive</td>
             <td>检查元素是否为正数，0为无效</td>
          </tr>
          <tr style="background-color:#90CAF9">
             <td>@PositiveOrZero</td>
             <td>检查元素是否为正数或0</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@Past</td>
             <td>被注释的元素必须是一个过去的日期</td>
             <td rowspan="4">java.util.Date、java.util.Calendar、java.time和java.time.chrono两个包下的一些类</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@PastOrPresent</td>
             <td>过去或现在</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@Future</td>
             <td>被注释的元素必须是一个将来的日期</td>
          </tr>
          <tr style="background-color:#69F0AE">
             <td>@FutureOrPresent</td>
             <td>现在或将来</td>
          </tr>
       </table>
  * 以上**每个注解都有groups、message、payload三个参数**可选
  * 除表中特别说明的外，null值都是合法的
  * 每个注解都还有一个名字后面跟.List的注解，例如@Null.List，推荐在标记一组同样注解时使用；还有.Iterable、.Map等
  * 表中提到的**CharSequence的子类**有：String、StringBuffer、StringBuilder、CharBuffer、Segment

* **注解的位置**

  * 成员变量（Filed域）上
  * 方法（get/is）上
  * 类上

* **约束规则对子类依然有效**

### 2.2 对同一bean的不同验证需求

* **groups参数**：每个约束的注解都有这个参数，可以接收多个class类型（必须是接口）

  不声明groups参数默认为javax.validation.groups.Default，声明了groups参数的会从Default组移除，如需加入Default组需显示声明，例如：@Null(groups={Default.class,Step1.class})

* **@Valid和@Validated区别**
  * @Valid是JSR标准定义的注解，只验证Default组的约束
  * @Validated是Spring定义注解，可通过参数来指定验证组，例：@Validated({Step1.class,Default.class})
  * @Valid可以用在成员变量上，进行级联验证；@Validated只能用在参数上，表示这个参数需要验证

* 自定义注解和不同验证更详细的[看这个博客](https://www.cnblogs.com/beiyan/p/5946345.html)

* **手动验证**：Spring调用Controller层的方法时，其中有@Valid或@Validated注解，会自动数据校验。当在Service层也需要数据校验时，需手动验证

  ```java
  //装载验证器
  @Autowired Validator validator;
  //验证某个类，下面是执行默认的验证组，如需指定可多传一个class参数
  Set<ConstraintViolation<?>> result = validator.validate(obj);
  //通不过校验的result集合会有值，可以通过size()判断
  ```




## 3 SpringBoot中使用MyBatis

### 3.1 后端程序结构层次

* Web控制层：@**RestController**、@Controller
* 业务逻辑层：@**Service**
* 数据访问层：@**Repository**
* 不能分清层次的：@**Component**，需要Spring来管理，可能被以上三个层调用



* 分包问题：
  * 按功能划分（PBF）：微服务、IDEA中module
  * 按层次划分（PBL）

### 3.2 添加MyBatis支持步骤

1. 修改pom.xml，添加MyBatis支持

   ```xml
   <dependency>
       <groupId>org.mybatis.spring.boot</groupId>
       <artifactId>mybatis-spring-boot-starter</artifactId>
       <version>1.3.2</version>
   </dependency>
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <scope>runtime</scope>
   </dependency>
   ```

2. 修改application.properties，添加数据库连接

3. 修改启动类，增加@MapperScan("cn.itcast.myapplication.dao")注解

4. 编写dao中mapping接口

5. 添加mapping接口对应的xml文件



## 4 JUnit单元测试

* Assert：断言

* Mockito 框架引入

  ```xml
  <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <!--<version>2.23.0</version> springboot不需要提供版本-->
      <scope>test</scope>
  </dependency>
  ```

* TDD（Test-Driven Development，测试驱动开发）先写测试用例，后写实现代码。重构现有代码时很好用

  * RDD（Resume-Driven Development，简历驱动开发）






















# 其他

## <span name="postman">1 Postman的几种参数格式</span>

* form-data

  > 即multipart/form-data，它将表单的数据组织成Key-Value形式，用分隔符boundary（boundary可任意设置）处理成一条消息。由于有boundary隔离，所以既可以上传文件，也可以上传参数

* x-www-form-urlencoded

  > 即application/x-www-from-urlencoded，将表单内的数据转换为Key-Value

* raw

  > 可以上传任意格式的文本，text、json、xml、html等

* binary 

  > 即Content-Type:application/octet-stream，只可以上传二进制数据，通常用来上传文件。由于没有键值，所以一次只能上传一个文件

* 注意：multipart/form-data与x-www-form-urlencoded**区别**
  * html中的form 表单有**两种：**
    * **application/x-www-form-urlencoded**是默认的MIME内容编码类型，它在传输比较大的二进制或者文本数据时效率极低
      * MIME：简单说，MIME类型就是设定某种扩展名的文件用一种应用程序来打开的方式类型。服务器会将它们发送的多媒体数据的类型告诉浏览器，而通知手段就是说明该多媒体数据的MIME类型，服务器将 MIME标志符放入传送的数据中来告诉浏览器使用哪种插件读取相关文件
    * **multipart/form-data**：既可以上传文件等二进制数据，也可以上传表单键值对，只是最后会转化为一条信息。当设置multipart/form-data，http会忽略 contentType 属性。