# 第八部分 Spring Boot

## Spring Boot 简介

- Spring Boot来简化Spring应用开发，约定大于配置， 去繁从简，just run就能创建一个独立的，产品级别应用
- 背景：J2EE笨重的开发、繁多的配置、低下的开发效率、 复杂的部署流程、第三方技术集成难度大
- 解决：
  - “Spring全家桶”时代。
  - Spring Boot：J2EE**一站式解决方案** 
  - Spring Cloud：分布式整体解决方案
- **优点**：
  - **快速创建**独立运行的Spring项目以及与主流框架集成
  - 使用**嵌入式的Servlet容器**，应用无需打成WAR包
  - **starters自动依赖与版本控制**
  - 大量的**自动配置**，简化开发，也可修改默认值
  - **无需配置XML，无代码生成**，开箱即用 
  - 准生产环境的运行时应用**监控**
  - 与**云计算**的天然集成

## 微服务

- 2014，martin fowler提出的架构风格（服务微化）。一个应用应该是一组小型服务，可以通过HTTP的方式进行互通
  - 单体应用：ALL IN ONE
  - 微服务：每一个功能元素最终都是一个可独立替换和独立升级的软件单元
- [详细参照微服务文档](https://martinfowler.com/articles/microservices.html#MicroservicesAndSoa)

## 环境准备

- **Maven设置**：给maven 的settings.xml配置文件的profiles标签添加如下内容

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

- IDEA设置：整合安装的Maven

  - Settings—build—Build Tools—Maven



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

- **父项目**

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

- **启动器**

  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  		...
  </dependencies>
  ```

  - **spring-boot-starter**：spring-boot**场景启动器**，帮我们**导入**了web模块**正常运行所依赖的组件**
  - Spring Boot将所有的功能场景都抽取出来，做成一个个的starters（启动器），只需要在项目里面引入这些starter相关场景的所有依赖都会导入进来

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

- **@SpringBootApplication** : Spring Boot应用标注在某个类上说明这个类是SpringBoot的**主配置类**，SpringBoot就应该**运行这个类的main方法**来**启动**SpringBoot应用。是如下注解的**简写**：

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

    - @**AutoConfigurationPackage**：**自动配置包**，**如下注解的简写**

      - @**Import**(AutoConfigurationPackages.Registrar.class)

        Spring底层注解@Import，给容器中导入一个组件；导入的组件由AutoConfigurationPackages.**Registrar**.class**指定**，这个类有一个方法，通过注解metadata，将

        ==**主配置类**（@SpringBootApplication）所在**包及下面所有子包**里面的**所有组件扫描到Spring容器**==

    - @**Import**({AutoConfigurationImportSelector.class})

      **AutoConfigurationImportSelector**：导入哪些组件的选择器

      将所有需要导入的组件以全类名的方式返回，这些组件就会被添加到容器中； 会给容器中**导入非常多的自动配置类**（xxxAutoConfiguration），就是给容器中导入这个场景需要的所有组件，并配置好这些组件；

      ![自动配置类](images\自动配置类.PNG)

      - 有了自动配置类，免去了我们手动编写配置注入功能组件等的工作
      - 调用了SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader)方法。Spring Boot在**启动的时候从类路径下**的META-INF/spring.factories中获取**EnableAutoConfiguration指定的值**，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作
      - J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-1.5.9.RELEASE.jar





## 2 Spring Boot 配置

### 2.1 配置文件

- SpringBoot使用一个全局的配置文件，配置文件名是固定的；

  - **application.properties**
  - **application.yml**

- 配置文件的**作用**：修改SpringBoot自动配置的默认值；SpringBoot在底层都给我们自动配置好；

- YAML（YAML Ain't Markup Language）

  YAML A Markup Language：是一个标记语言

  YAML isn't Markup Language：不是一个标记语言

- 标记语言：

  - 以前的配置文件；大多都使用的是 **xxx.xml**文件

    ```xml
    <server>
        <port>8081</port>
    </server>
    ```

  - YAML：**以数据为中心**，比json、xml等更适合做配置文件

    ```yml
    server:
      port: 8081
    ```

### 2.2 YML语法

- 基本语法：**k:(空格)v**：表示一对键值对（空格必须有）

  - 以**空格**的**缩进**来控制层级关系；只要是左对齐的一列数据，都是同一个层级的；属性和值也是**大小写敏感**

- **值的写法**

  - **字面量**：普通的值（**数字，字符串，布尔**）

    - k: v：字面**直接来写**；**字符串默认不用加上单引号或者双引号**；
      - ""：**双引号**；**不会转义**字符串里面的特殊字符；特殊字符会作为本身想表示的意思
        - name: "zhangsan \n lisi"：输出；zhangsan 换行 lisi
      - ''：**单引号**；**会转义**特殊字符，特殊字符最终只是一个普通的字符串数据
        - name: ‘zhangsan \n lisi’：输出；zhangsan \n lisi

  - **对象、Map**（属性和值）（键值对）：

    - k: v：在下一行来写对象的属性和值的关系；注意缩进；对象还是k: v的方式

      ```yaml
      friends:
      	lastName: zhangsan
      	age: 20
      ```

      ```yaml
      #行内写法
      friends: {lastName: zhangsan, age: 18}
      ```

  - **数组（List、Set）：**

    - 用**-** 值表示数组中的一个元素

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

- **配置文件**

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

- **JavaBean**

  - **将配置文件中配置的每一个属性的值，映射到这个组件中**
  - **@ConfigurationProperties**：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定；
  - **prefix** = "person"：**全局配置文件**中哪个下面的所有属性进行一一映射
  - **@Component**只有这个组件是容器中的组件，才能容器提供的@ConfigurationProperties功能；
  - @Data是一个插件，免去了getter/setter和toString这些繁琐的东西

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

- 出现以下提示，进入官网，在pom中**导入配置文件处理器**，以后编写配置就有提示了

  ![](images\配置文件处理器.PNG)

  ```xml
  <!--导入配置文件处理器，配置文件进行绑定就会有提示-->
  <dependency>
  	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-configuration-processor</artifactId>
  	<optional>true</optional>
  </dependency>
  ```



#### 2.3.1 properties配置文件在idea中默认utf-8可能会乱码

![](images\properties乱码配置.png)



#### 2.3.2 @Value获取值和@ConfigurationProperties获取值比较

|                      | @ConfigurationProperties | @Value     |
| -------------------- | ------------------------ | ---------- |
| 功能                 | 批量注入配置文件中的属性 | 一个个指定 |
| 松散绑定（松散语法） | 支持                     | 不支持     |
| SpEL                 | 不支持                   | 支持       |
| JSR303数据校验       | 支持                     | 不支持     |
| 复杂类型封装         | 支持                     | 不支持     |

- **松散语法绑定**：last_name = last-name = lastName 他们取的值都是相同的
- 配置文件yml还是properties他们都能获取到值，怎么选择呢？
  - 若我们只是在某个业务逻辑中需要**获取**一下**配置文件**中的**某项值**，使用@Value；
  - 若我们专门编写了一个javaBean来和配置文件**映射**，我们就直接使用@ConfigurationProperties



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

- @**PropertySource**：加载**指定的配置文件**，由于@ConfigurationProperties(prefix = "person")默认从全局配置文件中获取值；

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

- @**ImportResource**：**导入Spring的配置文件，如beans.xml，加载bean**，让配置文件里面的内容生效

  - Spring Boot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别；

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

- 之前编写xml配置文件，现在不再推荐

- ==SpringBoot推荐**给容器中添加组件**的方式：使用**全注解**的方式==

  - 配置类**@Configuration** --> Spring配置文件
  - 使用**@Bean**给容器中添加组件

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



### 2.4 配置文件占位符

- 随机数

  ```yaml
  ${random.value}
  ${random.int}
  ${random.long}
  ${random.int(10)}
  ${random.int[1024,65536]}
  ```

- **占位符获取之前配置的值**，如果没有可以使用**:**指定**默认值**

  ```properties
  person.last-name=张三${random.uuid}
  person.age=${random.int}
  # 没有取到:后面是默认值
  person.dog.name=${person.hello:hello}_dog
  ```

### 2.5 Profile

- Profile是Spring对**不同环境（开发、测试、上线等）提供不同配置功能**的支持，可以通过激活、 指定参数等方式快速切换环境

#### 2.5.1 多Profile文件

- 我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml，{profile}名任起
  - 默认使用application.properties的配置；



#### 2.5.2 yml支持多文档块方式

```yaml
server:
  port: 8081
spring:
  profiles:
    active: prod  #指定激活哪个环境，不设置则为这个默认的

---
server:
  port: 8083
spring:
  profiles: dev  #指定属于哪个环境

---

server:
  port: 8084
spring:
  profiles: prod  #指定属于哪个环境
```

- 若文档块都没有指定环境，则默认使用最后一个
- 若某个文档块没有指定环境，则默认使用那个，**一般第一个不指定环境**

#### 2.5.3 激活指定profile

- 在**默认配置文件中指定** spring.profiles.**active**=dev

- **命令行参数**

  ```shell
  java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
  ```

- **虚拟机参数**

  ```
  -Dspring.profiles.active=dev
  
  ```

  在IDEA中也可以配置，以下三者任选其一，但权限 Program arguments > Active profiles > VM options

  ![](images\active_profile.png)



### 2.6 配置文件加载位置

- Spring Boot启动会扫描以下位置的application.properties/yml文件作为Spring boot的默认配置文件

  - –file:./config/     ——项目目录下的config

  - –file:./                ——项目目录下

  - –classpath:/config/     ——resources目录下的config

  - –classpath:/                ——resources目录下

    **优先级由高到底**，**高**优先级的配置会**覆盖低**优先级的配置；SpringBoot会从这四个位置全部加载主配置文件；**互补配置**；

- 我们还可以通过**spring.config.location**来**改变默认的配置文件位置**

  - 用于运维时，**项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置；指定配置文件和默认加载的这些配置文件共同起作用形成互补配置；**

    ```shell
    java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --spring.config.location=G:/application.properties
    ```



### 2.7 外部配置文件加载顺序

> 所有支持的配置加载来源[查看这里](https://docs.spring.io/spring-boot/docs/2.0.6.RELEASE/reference/htmlsingle/#boot-features-external-config)第24节 Externalized Configuration

**SpringBoot也可以从以下位置加载配置； 优先级从高到低；高优先级的配置覆盖低优先级的配置，所有的配置会形成互补配置**

1. **命令行参数**：所有的配置都可以在命令行上进行指定。多个配置用空格分开； --配置项=值

   ```shell
   java -jar spring-boot-02-config-02-0.0.1-SNAPSHOT.jar --server.port=8087 --server.context-path=/abc
   ```

2. 来自java:comp/env的JNDI属性

3. Java系统属性（System.getProperties()）

4. 操作系统环境变量

5. RandomValuePropertySource配置的random.*属性值

==**由jar包外向jar包内进行寻找**==

==**优先加载带profile**==

1. **jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件**
2. **jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件**

==**再来加载不带profile**==

1. **jar包外部的application.properties或application.yml(不带spring.profile)配置文件**
2. **jar包内部的application.properties或application.yml(不带spring.profile)配置文件**
3. @Configuration注解类上的@PropertySource
4. 通过SpringApplication.setDefaultProperties指定的默认属性



### <span name="autoConfigure">2.8 自动配置</span>

> 配置文件到底能写什么？怎么写？参考文档[这里](https://docs.spring.io/spring-boot/docs/2.0.6.RELEASE/reference/htmlsingle/#common-application-properties)

#### 2.8.1 自动配置原理

1. SpringBoot启动的时候**加载主配置类**（**@SpringBootApplication**），**开启了自动配置**功能 @EnableAutoConfiguration

2. **@EnableAutoConfiguration 作用：**

   1. @AutoConfigurationPackage

      ==**主配置类**（@SpringBootApplication）所在**包及下面所有子包**里面的**所有组件扫描到Spring容器**==

   2. @Import(AutoConfigurationImportSelector.class)

      1. AutoConfigurationImportSelector**选择器**给容器中**导入一些组件**？

      2. selectImports()方法的内容，**获取候选的配置**

         ```java
         List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
         ```

      3. SpringFactoriesLoader.loadFactoryNames()方法，扫描所有jar包类路径下META-INF/spring.factories，把扫描到的这些文件的内容包装成properties对象，从properties中获取到EnableAutoConfiguration.class类（类名）对应的值，然后把他们添加在容器中

      ==**将类路径下 META-INF/spring.factories 里面配置的所有EnableAutoConfiguration的值加入到了容器中**==

      ```properties
      # Auto Configure，省略大部分
      org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
      org.springframework.boot.autoconfigure.admin.SpringApplicationAdminJmxAutoConfiguration,\
      org.springframework.boot.autoconfigure.aop.AopAutoConfiguration,\
      org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration,\
      org.springframework.boot.autoconfigure.batch.BatchAutoConfiguration,\
      org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration,\
      
      org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration,\
      org.springframework.boot.autoconfigure.websocket.WebSocketMessagingAutoConfiguration,\
      org.springframework.boot.autoconfigure.webservices.WebServicesAutoConfiguration
      ```

      每一个这样的**xxxAutoConfiguration**类都是容器中的一个组件；**用他们来做自动配置**；

3. **每一个自动配置类进行自动配置功能**

4. 以**HttpEncodingAutoConfiguration（Http编码自动配置）**为例解释自动配置原理

   ```java
   @Configuration   //表示这是一个配置类，以前编写的配置文件一样，也可以给容器中添加组件
   @EnableConfigurationProperties(HttpEncodingProperties.class)  //启动指定类的ConfigurationProperties功能；将配置文件中对应的值和HttpEncodingProperties绑定起来；并把HttpEncodingProperties加入到ioc容器中
   
   @ConditionalOnWebApplication //Spring底层@Conditional注解（Spring注解版），根据不同的条件，如果满足指定的条件，整个配置类里面的配置就会生效；    判断当前应用是否是web应用，如果是，当前配置类生效
   
   @ConditionalOnClass(CharacterEncodingFilter.class)  //判断当前项目有没有这个类CharacterEncodingFilter；SpringMVC中进行乱码解决的过滤器；
   
   @ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)  //判断配置文件中是否存在某个配置  spring.http.encoding.enabled；如果不存在，判断也是成立的
   //即使我们配置文件中不配置pring.http.encoding.enabled=true，也是默认生效的；
   public class HttpEncodingAutoConfiguration {
   
       //他已经和SpringBoot的配置文件映射了
       private final HttpEncodingProperties properties;
   
       //只有一个有参构造器的情况下，参数的值就会从容器中拿
       public HttpEncodingAutoConfiguration(HttpEncodingProperties properties) {
           this.properties = properties;
       }
   
       @Bean   //给容器中添加一个组件，这个组件的某些值需要从properties中获取
       @ConditionalOnMissingBean(CharacterEncodingFilter.class) //判断容器没有这个组件？
       public CharacterEncodingFilter characterEncodingFilter() {
           CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
           filter.setEncoding(this.properties.getCharset().name());
           filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
           filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
           return filter;
       }
   ```

   根据当前不同的条件判断，决定这个配置类是否生效？

   一但这个配置类生效；这个配置类就会给容器中添加各种组件；这些组件的属性是从**对应的properties类**中获取的，这些类里面的每一个属性又是和配置文件绑定的；

5. 所有在配置文件中能配置的属性都是在**xxxxProperties类**中封装着；配置文件能配置什么就可以参照某个功能**对应的这个属性类**

   ```java
   @ConfigurationProperties(prefix = "spring.http.encoding")
   public class HttpEncodingProperties {
   
   	public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
   }
   ```

#### 2.8.2 精髓

- **SpringBoot启动会加载大量的自动配置类**
- **我们看我们需要的功能有没有SpringBoot默认写好的自动配置类；**
- **我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件有，我们就不需要再来配置了）**
- **给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们就可以在配置文件中指定这些属性的值；**
- **自动配置类对应属性类**
  - xxxxAutoConfigurartion：自动配置类；给容器中添加组件
  - xxxxProperties:封装配置文件中相关属性；



#### 2.8.3 @Conditional细节

- **@Conditional派生注解（Spring注解版原生的@Conditional作用）**

- 作用：必须是@Conditional**指定的条件成立**，才给容器中**添加组件**，**配置类**里面的所有内容才**生效**

  | @Conditional扩展注解            | 作用（判断是否满足当前指定条件）                 |
  | ------------------------------- | ------------------------------------------------ |
  | @ConditionalOnJava              | 系统的java版本是否符合要求                       |
  | @ConditionalOnBean              | 容器中存在指定Bean；                             |
  | @ConditionalOnMissingBean       | 容器中不存在指定Bean；                           |
  | @ConditionalOnExpression        | 满足SpEL表达式指定                               |
  | @ConditionalOnClass             | 系统中有指定的类                                 |
  | @ConditionalOnMissingClass      | 系统中没有指定的类                               |
  | @ConditionalOnSingleCandidate   | 容器中只有一个指定的Bean，或者这个Bean是首选Bean |
  | @ConditionalOnProperty          | 系统中指定的属性是否有指定的值                   |
  | @ConditionalOnResource          | 类路径下是否存在指定资源文件                     |
  | @ConditionalOnWebApplication    | 当前是web环境                                    |
  | @ConditionalOnNotWebApplication | 当前不是web环境                                  |
  | @ConditionalOnJndi              | JNDI存在指定项                                   |

- 我们可以通过在**配置文件中启用 debug=true属性；来让控制台打印自动配置报告**，这样我们就可以很方便的知道哪些自动配置类生效；

  ```java
  =========================
  AUTO-CONFIGURATION REPORT
  =========================
  
  Positive matches:（自动配置类启用的）
  -----------------
  
     DispatcherServletAutoConfiguration matched:
        - @ConditionalOnClass found required class 'org.springframework.web.servlet.DispatcherServlet'; @ConditionalOnMissingClass did not find unwanted class (OnClassCondition)
        - @ConditionalOnWebApplication (required) found StandardServletEnvironment (OnWebApplicationCondition)
          
      
  Negative matches:（没有启动，没有匹配成功的自动配置类）
  -----------------
  
     ActiveMQAutoConfiguration:
        Did not match:
           - @ConditionalOnClass did not find required classes 'javax.jms.ConnectionFactory', 'org.apache.activemq.ActiveMQConnectionFactory' (OnClassCondition)        
  ```



## 3 Spring Boot 日志

> 也可查看Spring Boot 官方文档第26节，[这里](https://docs.spring.io/spring-boot/docs/2.0.6.RELEASE/reference/htmlsingle/#boot-features-logging)

### 3.1 日志框架

- 小张开发一个大型系统；

​	1、System.out.println("")；将关键数据打印在控制台；去掉？写在一个文件？

​	2、框架来记录系统的一些运行时信息；日志框架 ； zhanglogging.jar；

​	3、高大上的几个功能？异步模式？自动归档？xxxx？ zhanglogging-good.jar；

​	4、将以前框架卸下来？换上新的框架，重新修改之前相关的API；zhanglogging-prefect.jar；

​	5、借鉴JDBC---数据库驱动；

​		写了一个统一的接口层；日志门面（日志的一个抽象层）；logging-abstract.jar；

​		给项目中导入具体的日志实现就行了；我们之前的日志框架都是实现的抽象层；

- 市面上的日志框架
  JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j....

  | 日志门面 （日志的抽象层）                                    | 日志实现                                                |
  | ------------------------------------------------------------ | ------------------------------------------------------- |
  | ~~JCL（Jakarta Commons Logging）~~  SLF4j（Simple Logging Facade for Java）~~**jboss-logging**~~ | Log4j     JUL（java.util.logging） Log4j2   **Logback** |

- 左边选一个门面（抽象层）：SLF4J。右边来选一个实现：Logback；

- SpringBoot：底层是Spring框架，Spring框架默认是用JCL；

  - ==**SpringBoot选用 SLF4j和logback；**==



### 3.2 SLF4j使用

#### 3.2.1 如何在系统中使用SLF4j

- 以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是**调用日志抽象层里面的方法**；

  - 给系统里面导入slf4j的jar和 logback的实现jar

  ```java
  import org.slf4j.Logger;
  import org.slf4j.LoggerFactory;
  
  public class HelloWorld {
    public static void main(String[] args) {
      Logger logger = LoggerFactory.getLogger(HelloWorld.class);
      logger.info("Hello World");
    }
  }
  ```

![](images\concrete-bindings.png)

- 每一个日志的实现框架都有自己的配置文件。使用slf4j**配置文件还是做成日志实现框架自己本身的配置文件；**



#### 3.2.2 遗留问题

- a系统（slf4j+logback）: Spring（commons-logging）、Hibernate（jboss-logging）、MyBatis、xxxx
- **统一日志记录**，即**如何让系统中所有的日志都统一到slf4j？**
  - ==**将系统中其他日志框架先排除出去；**==
  - ==**用中间包来替换原有的日志框架；**==
  - ==**我们导入slf4j其他的实现**==

![](images\legacy.png)



### 3.3 SpringBoot日志关系

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
</dependency>
```

SpringBoot使用它来做日志功能：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-logging</artifactId>
</dependency>
```

**底层依赖关系如下**

- Spring Boot 1.5.10如下：

![](images\底层依赖关系.png)

- SpringBoot 2.0后底层依赖如下：

![](images\底层依赖关系2.png)

- **总结 Spring Boot 1.5.10**

  - SpringBoot底层也是使用slf4j+logback的方式进行日志记录

  - SpringBoot也把其他的日志都替换成了slf4j

  - 中间替换包细节如下截取部分：

    ```java
    @SuppressWarnings("rawtypes")
    public abstract class LogFactory {
    
        static String UNSUPPORTED_OPERATION_IN_JCL_OVER_SLF4J = "http://www.slf4j.org/codes.html#unsupported_operation_in_jcl_over_slf4j";
    
        static LogFactory logFactory = new SLF4JLogFactory();
    ```

    Spring Boot 1.5.10中的中间转换包如下，2.0后改了名字，实现的名称也不同，不放图了

    ![](images\中间替换包.png)

  - **若要引入其他框架**？一定要把这个框架的**默认日志依赖移除**掉。例如Spring框架用的是commons-logging。但是Spring Boot2.0后的没有这个，因为底层不再依赖它

    ```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <exclusions>
            <exclusion>
                <groupId>commons-logging</groupId>
                <artifactId>commons-logging</artifactId>
            </exclusion>
        </exclusions>
    </dependency>
    ```

==**SpringBoot能自动适配所有的日志，而且底层使用slf4j+logback的方式记录日志，引入其他框架的时候，只需要把这个框架依赖的日志框架排除掉即可；**==

- Spring Boot 2.0开始使用 Commons Logging 进行所有内部日志记录，但保留底层日志实现。 为Java Util Logging，Log4J2，和 Logback提供了默认配置。默认情况下，如果使用“Starters”，则使用Logback进行日志记录

### 3.4 日志使用

#### 3.4.1 默认配置

- SpringBoot默认帮我们配置好了日志

  ```java
  //记录器
  Logger logger = LoggerFactory.getLogger(getClass());
  
  @Test
  public void contextLoads() {
  
      //日志的级别；由低到高 
        trace<debug<info<warn<error
      //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
      logger.trace("系统详细信息, 主要开发人员用, 一般来说线上系统可以认为是临时输出, 而且随时可以通过开关将其关闭");
      logger.debug("主要给开发人员看,开发环境中使用");
      //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
      logger.info("重要的业务逻辑处理完成. 在理想情况下, INFO的日志信息要能让高级用户和系统管理员理解, 并从日志信息中能知道系统当前的运行状态");
      logger.warn("系统能继续运行, 但是必须引起关注");
      logger.error("系统发生了严重的错误, 必须马上进行处理, 否则系统将无法继续运行");
  
  }
  ```

  ```java
  日志输出格式：
  		%d表示日期时间，
  		%thread表示线程名，
  		%-5level：级别从左显示5个字符宽度
  		%logger{50} 表示logger名字最长50个字符，否则按照句点分割。 
  		%msg：日志消息，
  		%n是换行符
  
  %d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n
  ```

- SpringBoot修改日志的默认配置

  ```properties
  #指定某个包的日志记录级别
  logging.level.com.itcast=trace
  
  # 不指定路径在当前项目下生成springboot.log日志
  # 可以指定完整的路径；
  logging.file=G:/springboot.log
  
  # 在当前磁盘的根路径下创建spring文件夹和里面的log文件夹；使用 spring.log 作为默认文件
  logging.path=/spring/log
  
  # 在控制台输出的日志的格式
  logging.pattern.console=%d{yyyy-MM-dd} [%thread] %-5level %logger{50} - %msg%n
  # 指定文件中日志输出的格式
  logging.pattern.file=%d{yyyy-MM-dd} === [%thread] === %-5level === %logger{50} ==== %msg%n
  ```

  | logging.file               | logging.path | Example  | Description                        |
  | -------------------------- | ------------ | -------- | ---------------------------------- |
  | (none)                     | (none)       |          | 只在控制台输出                     |
  | 指定文件名，可指定完整路径 | (none)       | my.log   | 输出日志到my.log文件               |
  | (none)                     | 指定目录     | /var/log | 输出到指定目录的 spring.log 文件中 |

#### 3.4.2 指定配置

- 给**类路径**下放上**每个日志框架自己的配置文件**即可；SpringBoot就不使用他默认配置的了

  | Logging System         | Customization                                                |
  | ---------------------- | ------------------------------------------------------------ |
  | Logback                | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml` or `logback.groovy` |
  | Log4j2                 | `log4j2-spring.xml` or `log4j2.xml`                          |
  | JDK(Java Util Logging) | `logging.properties`                                         |

  - logback.xml：直接就被日志框架识别了；

    **logback-spring.xml**：**推荐！**日志框架就不直接加载日志的配置项，**由SpringBoot解析日志配置**，可以使用SpringBoot的高级Profile功能

    ```xml
    <springProfile name="staging">
        <!-- configuration to be enabled when the "staging" profile is active -->
      	可以指定某段配置只在某个环境下生效
    </springProfile>
    ```

    如下：

    ```xml
    <appender name="stdout" class="ch.qos.logback.core.ConsoleAppender">
        <layout class="ch.qos.logback.classic.PatternLayout">
            <springProfile name="dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ----> [%thread] ---> %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
            <springProfile name="!dev">
                <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} ==== [%thread] ==== %-5level %logger{50} - %msg%n</pattern>
            </springProfile>
        </layout>
    </appender>
    ```

    如果使用logback.xml作为日志配置文件，还要使用profile功能，会有以下错误`no applicable action for [springProfile]`

### 3.5 切换日志框架

- 可以按照slf4j的日志适配图，进行相关的切换

  - slf4j+log4j的方式（没意义）

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <artifactId>logback-classic</artifactId>
                <groupId>ch.qos.logback</groupId>
            </exclusion>
            <exclusion>
                <artifactId>log4j-over-slf4j</artifactId>
                <groupId>org.slf4j</groupId>
            </exclusion>
        </exclusions>
    </dependency>
    
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
    </dependency>
    ```

  - 切换为log4j2

    ```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
        <exclusions>
            <exclusion>
                <artifactId>spring-boot-starter-logging</artifactId>
                <groupId>org.springframework.boot</groupId>
            </exclusion>
        </exclusions>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-log4j2</artifactId>
    </dependency>
    ```



## 4 Web开发

### 4.1 简介

- 使用SpringBoot
  1. **创建SpringBoot应用，选中我们需要的模块**
  2. **SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来**
  3. **自己编写业务代码**
- 最根本需要理解**自动配置原理？**<a href="#autoConfigure">见2.8节</a>
  - 这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？xxx
    - xxxxAutoConfiguration：帮我们给容器中自动配置组件
    - xxxxProperties:配置类来封装配置文件的内容；



### 4.2 SpringBoot对静态资源的映射规则

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties implements ResourceLoaderAware {
  //可以设置和静态资源有关的参数，缓存时间等
```

- **WebMvcAuotConfiguration.java**： SpringMVC相关配置都在这个类中

```java
//org\springframework\boot\autoconfigure\web\servlet\WebMvcAutoConfiguration.java
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			if (!this.resourceProperties.isAddMappings()) {
				logger.debug("Default resource handling disabled");
				return;
			}
            //webjars/映射
			Integer cachePeriod = this.resourceProperties.getCachePeriod();
			if (!registry.hasMappingForPattern("/webjars/**")) {
				customizeResourceHandlerRegistration(
						registry.addResourceHandler("/webjars/**")
								.addResourceLocations(
										"classpath:/META-INF/resources/webjars/")
						.setCachePeriod(cachePeriod));
			}
			String staticPathPattern = this.mvcProperties.getStaticPathPattern();
          	// 静态资源文件夹映射
			if (!registry.hasMappingForPattern(staticPathPattern)) {
				customizeResourceHandlerRegistration(
						registry.addResourceHandler(staticPathPattern)
								.addResourceLocations(
										this.resourceProperties.getStaticLocations())
						.setCachePeriod(cachePeriod));
			}
		}

         // 配置欢迎页映射
		@Bean
		public WelcomePageHandlerMapping welcomePageHandlerMapping(
				ResourceProperties resourceProperties) {
			return new WelcomePageHandlerMapping(resourceProperties.getWelcomePage(),
					this.mvcProperties.getStaticPathPattern());
		}

         // 配置喜欢的图标
		@Configuration
		@ConditionalOnProperty(value = "spring.mvc.favicon.enabled", matchIfMissing = true)
		public static class FaviconConfiguration {

			private final ResourceProperties resourceProperties;

			public FaviconConfiguration(ResourceProperties resourceProperties) {
				this.resourceProperties = resourceProperties;
			}

			@Bean
			public SimpleUrlHandlerMapping faviconHandlerMapping() {
				SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
				mapping.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);
              	 // 所有  **/favicon.ico 
				mapping.setUrlMap(Collections.singletonMap("**/favicon.ico",
						faviconRequestHandler()));
				return mapping;
			}

			@Bean
			public ResourceHttpRequestHandler faviconRequestHandler() {
				ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
				requestHandler
						.setLocations(this.resourceProperties.getFaviconLocations());
				return requestHandler;
			}

		}
```



- ==所有**/webjars/\****访问 ，都去 **classpath:/META-INF/resources/webjars/** 找资源==

  - `webjars`：以jar包的方式引入静态资源；[进官网查看详细信息](<http://www.webjars.org/>)

  - 引入依赖

    ```xml
    <!--引入jquery-webjar;在访问的时候只需要写webjars下面资源的名称即可-->
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>jquery</artifactId>
        <version>3.3.1-1</version>
    </dependency>
    ```

    ![](images\jquery.png)

  - 访问：localhost:8080/webjars/jquery/3.3.1-1/jquery.js

- =="/**" 访问当前项目的任何资源，都去（静态资源的文件夹里）找映射==

  ```java
  "classpath:/META-INF/resources/", 
  "classpath:/resources/",
  "classpath:/static/", 
  "classpath:/public/" 
  "/"：当前项目的根路径
  ```

  - 访问：localhost:8080/abc === 去静态资源文件夹里面找abc

- ==**欢迎页**：静态资源文件夹下的所有index.html页面；被"/**"映射==

  - 访问：localhost:8080/，找index页面

- ==**图标**：所有的 **/favicon.ico 都是在静态资源文件下找==



### 4.3 模板引擎

JSP、Velocity、Freemarker、Thymeleaf（Spring推荐，语法更简单，功能更强大）

![](images\template-engine.png)

#### 4.3.1 引入Thymeleaf

- [官方文档查看更详细内容](https://www.thymeleaf.org/)

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
  ```



#### 4.3.2 Thymeleaf使用

```java
@ConfigurationProperties(prefix = "spring.thymeleaf")
public class ThymeleafProperties {

    private static final Charset DEFAULT_ENCODING = Charset.forName("UTF-8");

    private static final MimeType DEFAULT_CONTENT_TYPE = MimeType.valueOf("text/html");

    public static final String DEFAULT_PREFIX = "classpath:/templates/";

    public static final String DEFAULT_SUFFIX = ".html";
```

- 只要我们把HTML页面**放在classpath:/templates/**，thymeleaf就能自动渲染；

- 使用：

  - 导入thymeleaf的名称空间，才能有语法提示

    ```html
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    ```

  - 使用thymeleaf语法

    ```html
    <!DOCTYPE html>
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
        <head>
            <meta charset="UTF-8">
            <title>Title</title>
        </head>
        <body>
            <h1>成功！</h1>
            <!--th:text 将div里面的文本内容设置为 -->
            <div th:text="${hello}">这是显示欢迎信息</div>
        </body>
    </html>
    ```

#### 4.3.3 Thymeleaf语法

- `th:text`：改变当前元素里面的文本内容。**th:任意html属性**：来替换原生属性的值

![](images\2018-02-04_123955.png)

* `th:each`：遍历

  ```html
  <tr th:each="p : ${pageInfo.list}">
      <td th:text="${p.id}"></td>
      <td th:text="${p.name}"></td>
  ```

- 

- Simple expressions:（表达式语法）

  - **==Variable Expressions==: `${...}`**：获取变量值；底层是OGNL；

    - 获取对象的属性、调用方法。
    - 使用内置的基本对象：
      - `${#ctx}` : the context object.
      - `${#vars}`: the context variables.
      - `${#locale}` : the context locale. 如`${#locale.country}`
      - `${#request}` : (only in Web Contexts) the HttpServletRequest object.
      - `${#response}` : (only in Web Contexts) the HttpServletResponse object.
      - `${#session}`#session : (only in Web Contexts) the HttpSession object.
      - `${#servletContext}` : (only in Web Contexts) the ServletContext object.
    - 使用内置的**工具**对象（同上，放在`${...}`里）：
      - `#execInfo` : information about the template being processed.
      - `#messages` : methods for obtaining externalized messages inside variables expressions, in the same way as they would be obtained using #{…} syntax.
      - `#uris` : methods for escaping parts of URLs/URIs
      - `#conversions` : methods for executing the configured conversion service (if any).
      - `#dates` : methods for java.util.Date objects: formatting, component extraction, etc.
      - `#calendars` : analogous to #dates , but for java.util.Calendar objects.
      - `#numbers` : methods for formatting numeric objects.
      - `#strings` : methods for String objects: contains, startsWith, prepending/appending, etc.
      - `#objects` : methods for objects in general.
      - `#bools` : methods for boolean evaluation.
      - `#arrays` : methods for arrays.
      - `#lists` : methods for lists.
      - `#sets` : methods for sets.
      - `#maps` : methods for maps.
      - `#aggregates` : methods for creating aggregates on arrays or collections.
      - `#ids` : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).

  - Selection Variable Expressions: `*{...}`：选择表达式：和`${...}`在功能上是一样；配合`th:object`使用如下：

    ```html
    <div th:object="${session.user}">
        <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>	<!--${session.user.firstName}-->
        <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>	<!--${session.user.lastName}-->
        <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>	<!--${session.user.nationality}-->
    </div>
    ```

  - Message Expressions: `#{...}`：获取国际化内容

  - Link URL Expressions: `@{...}`：定义URL；

  - Fragment Expressions: `~{...}`：片段引用表达式

- Literals（字面量）

  - Text literals: 'one text' , 'Another one!' ,…
  - Number literals: 0 , 34 , 3.0 , 12.3 ,…
  - Boolean literals: true , false
  - Null literal: null
  - Literal tokens: one , sometext , main ,…

- Text operations:（文本操作）

  - String concatenation: +
  - Literal substitutions: |The name is ${name}|

- Arithmetic operations:（数学运算）

  - Binary operators: + , - , * , / , %
  - Minus sign (unary operator): -

- Boolean operations:（布尔运算）

  - Binary operators: and , or
  - Boolean negation (unary operator): ! , not

- Comparisons and equality:（比较运算）

  - Comparators: > , < , >= , <= ( gt , lt , ge , le )
  - Equality operators: == , != ( eq , ne )

- Conditional operators:条件运算（三元运算符）

  - If-then: (if) ? (then)
  - If-then-else: (if) ? (then) : (else)
  - Default: (value) ?: (defaultvalue)

- Special tokens:

  - No-Operation: _ 

- 


























































## 2 请求数据

- **@RestController**：Spring4的新注解，将类标记为控制器，其中每个方法都返回一个域对象而不是视图，这是@Controller和@ResponseBody的简写

- @**RequestMapping**、@GetMapping、@PostMapping、@PutMapping、@DeleteMapping。value中也可以用正则表达式限制类型。若不符合要求会返回4xx的错误信息，表示请求有问题

  - 当POST方法传递的是表单数据时，可在上述注解加**consumes参数**（根据请求的**Content-Type**缩小请求映射范围）

    - 设置consumes ={MediaType.MULTIPART_FORM_DATA_VALUE ,MediaType.APPLICATION_ATOM_XML_VALUE})，任选，<a href="#postman">详细区别看这里</a>。第一个可以传文件，第二个传文件慢

    - 在@PostMapping**上传文件**方法中使用**MultipartFile**接口的流保存文件

      ```java
      public void uploadFile(@RequestParam MultipartFile file) {...}
      ```

  - GET方法**下载文件**，设置**produces参数**根据**Accept**请求标头和控制器方法生成的内容类型列表来缩小请求映射

- @**PathVariable**：访问URL模板变量

- @**RequestBody**：通过HttpMessageConverter读取请求体并反序列化为Object

- @**RequestParam**：将Servlet请求参数（即查询参数或表单数据）绑定到控制器中方法的参数上，value可不写（必须和参数一致），默认required=true，可以设置为false防止异常

- @RequestHeader：将请求头绑定到控制器方法的参数上，value可不写（必须和参数一致），不区分大小写

- @CookieValue：将HTTP cookie的值绑定到控制器方法的参数上，同上。除非为了兼容老客户端，否则不用

- Authenticated：获取当前用户，直接在方法中增加参数，类型为它即可

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

- 不要相信前端传入的数据，尽量要前端少传数据

- **使用基于注解的验证**

  > JSR303 是一套JavaBean参数校验的标准，它定义了很多常用的校验注解，我们可以直接将这些注解加在我们JavaBean的属性上面，就可以在需要校验的时候进行校验了。
  >
  > Hibernate validator是Bean Validation1.0（JSR303）的一个实现
  >
  > 目前最新的Hibernate validator 6.0 是Bean Validation 2.0（JSR380，于2017年8月完成）的一个实现
  >
  > [Bean Validation官网点这里](https://beanvalidation.org/)

- Hibernate Validator包含一组基本的常用约束，这些最重要的是Bean Validation规范定义的约束。此外，Hibernate Validator还提供了有用的自定义约束。

- @**Valid**      级联验证，**确保这个对象满足校验限制**。校验的对象后**紧跟着Errors或BindingResult参数**

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
      </style>
  </head>
      <body>
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
      </body>

  - 以上**每个注解都有groups、message、payload三个参数**可选
  - 除表中特别说明的外，null值都是合法的
  - 每个注解都还有一个名字后面跟.List的注解，例如@Null.List，推荐在标记一组同样注解时使用；还有.Iterable、.Map等
  - 表中提到的**CharSequence的子类**有：String、StringBuffer、StringBuilder、CharBuffer、Segment

- **注解的位置**

  - 成员变量（Filed域）上
  - 方法（get/is）上
  - 类上

- **约束规则对子类依然有效**

### 2.2 对同一bean的不同验证需求

- **groups参数**：每个约束的注解都有这个参数，可以接收多个class类型（必须是接口）

  不声明groups参数默认为javax.validation.groups.Default，声明了groups参数的会从Default组移除，如需加入Default组需显示声明，例如：@Null(groups={Default.class,Step1.class})

- **@Valid和@Validated区别**

  - @Valid是JSR标准定义的注解，只验证Default组的约束
  - @Validated是Spring定义注解，可通过参数来指定验证组，例：@Validated({Step1.class,Default.class})
  - @Valid可以用在成员变量上，进行级联验证；@Validated只能用在参数上，表示这个参数需要验证

- 自定义注解和不同验证更详细的[看这个博客](https://www.cnblogs.com/beiyan/p/5946345.html)

- **手动验证**：Spring调用Controller层的方法时，其中有@Valid或@Validated注解，会自动数据校验。当在Service层也需要数据校验时，需手动验证

  ```java
  //装载验证器
  @Autowired Validator validator;
  //验证某个类，下面是执行默认的验证组，如需指定可多传一个class参数
  Set<ConstraintViolation<?>> result = validator.validate(obj);
  //通不过校验的result集合会有值，可以通过size()判断
  ```



## 3 SpringBoot中使用MyBatis

### 3.1 后端程序结构层次

- Web控制层：@**RestController**、@Controller
- 业务逻辑层：@**Service**
- 数据访问层：@**Repository**
- 不能分清层次的：@**Component**，需要Spring来管理，可能被以上三个层调用



- 分包问题：
  - 按功能划分（PBF）：微服务、IDEA中module
  - 按层次划分（PBL）

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

- Assert：断言

- Mockito 框架引入

  ```xml
  <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <!--<version>2.23.0</version> springboot不需要提供版本-->
      <scope>test</scope>
  </dependency>
  ```

- TDD（Test-Driven Development，测试驱动开发）先写测试用例，后写实现代码。重构现有代码时很好用

  - RDD（Resume-Driven Development，简历驱动开发）





















# 其他

## <span name="postman">1 Postman的几种参数格式</span>

- form-data

  > 即multipart/form-data，它将表单的数据组织成Key-Value形式，用分隔符boundary（boundary可任意设置）处理成一条消息。由于有boundary隔离，所以既可以上传文件，也可以上传参数

- x-www-form-urlencoded

  > 即application/x-www-from-urlencoded，将表单内的数据转换为Key-Value

- raw

  > 可以上传任意格式的文本，text、json、xml、html等

- binary 

  > 即Content-Type:application/octet-stream，只可以上传二进制数据，通常用来上传文件。由于没有键值，所以一次只能上传一个文件

- 注意：multipart/form-data与x-www-form-urlencoded**区别**

  - html中的form 表单有**两种：**
    - **application/x-www-form-urlencoded**是默认的MIME内容编码类型，它在传输比较大的二进制或者文本数据时效率极低
      - MIME：简单说，MIME类型就是设定某种扩展名的文件用一种应用程序来打开的方式类型。服务器会将它们发送的多媒体数据的类型告诉浏览器，而通知手段就是说明该多媒体数据的MIME类型，服务器将 MIME标志符放入传送的数据中来告诉浏览器使用哪种插件读取相关文件
    - **multipart/form-data**：既可以上传文件等二进制数据，也可以上传表单键值对，只是最后会转化为一条信息。当设置multipart/form-data，http会忽略 contentType 属性。