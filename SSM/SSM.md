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
    <constructor-arg name="name" type="java.lang.String" index="0" value="tom"></constructor-arg>
    <constructor-arg name="car" type="cn.itcast.pojo.Car" index="1" ref="car"></constructor-arg>
    
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
          User user = new User(car);
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
                            value="jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8" />
                  <property name="username" value="root" />
                  <property name="password" value="root" />
              </dataSource>
          </environment>
      </environments>
  
      <mappers>
          <mapper class="cn.itcast.mybatis.pojo"/>
      </mappers>
  </configuration>
  ```

### 1.3 POJO类

* 作为mybatis进行sql映射使用，po类通常与数据库表对应

### 1.4 mappers

* **映射器文件**config\mappers目录中，创建与POJO类**对应的mapper.xml**文件

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!-- namespace：命名空间，用于隔离sql，还有一个很重要的作用，后面会讲 -->
  <mapper namespace="cn.itcast.mybatis.pojo.User">
      <select id="selectBlog" resultType="Blog">
      select * from Blog where id = #{id}
    </select>
  </mapper>
  ```

* **在config中加载**可以使用相对于类路径的资源引用， 或完全限定资源定位符(包括 `file:///` 的URL)，或类名和包名等

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
