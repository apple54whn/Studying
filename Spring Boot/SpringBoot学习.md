# 第一部分 Maven

## 1 Maven简介

是apache下的一个开源项目，是纯java开发，用于对java项目进行**构建**、**依赖管理**

- **项目的一键构建**：一个项目从编写源代码到编译、测试、运行、打包、部署、运行的过程
  - Maven项目构建过程：
    - 清理(**clean**)——>编译(**compile**)——>测试(test)——>报告——>打包(**package**)——>部署
    - 运行一个Maven工程(web项目)的命令：**`mvn tomcat:run`**
- **依赖管理**：Maven工程队Java项目所依赖jar包的规范化管理
  - Maven项目的jar包只需在**`pom.xml`**添加jar包的**坐标**，自动从**Maven仓库**下载jar包、运行

![](images\maven概念模型图.png)

下载二进制文件，解压后，**配置环境变量**即可使用

Maven文件夹目录

​	|——bin：**`mvn.bat`**(run方式运行项目)、**`mvnDebug.bat`**(debug方式运行项目)

​	|——boot：Maven运行所需要的类加载器

​	|——conf：**`settings.xml`**(整个Maven工具核心配置文件。配置本地仓库)

​	|——lib：Maven运行依赖的jar包



Maven仓库分三类：本地仓库、远程仓库（私服）、中央仓库。可以配置本地仓库的路径

> Maven乱码问题：修改Runner中VM Options：`-Dfile.encoding=gb2312`
>

## 2 Maven项目目录结构

Project

​	|—src

​		|—main

​			|—java				存放项目的java文件

​			|—resources			存放项目的资源文件，如spring、mybatis等配置文件

​			|—webapp			web工程主目录

​				|—WEB-INF

​					|—web.xml

​		|—test

​			|—java				存放所有测试.java文件，如JUnit测试类

​			|—resources			测试资源文件，一般不放东西，调用的main中的资源文件

​	|—target						目标文件输出位置，如.class、.jar、.war文件

​	|—pom.xml					Maven项目核心配置文件





## 3 常用的Maven命令

- **`mvn clean`**：**删除target及其内容**。接手他人项目一般先执行此命令
- **`mvn compile`**：只**编译main**目录的文件，存放至target目录
- **`mvn test`**：**编译test**和mian目录的代码并运行
- **`mvn package`**：编译test和mian目录的代码，并根据项目类型**打包**（jar、war）
- **`mvn install`**：执行**以上操作**，并把项目**发布到本地仓库**
- **`mvn tomcat:run`**：在**当前项目的路径**中执行后，运行Maven工程项目
- mvn **spring-boot:run**：运行SpringBoot项目



## 4 Maven的生命周期（了解）

* Clean生命周期：clean

- Default生命周期：
  - compile——>test——>package——>install——>deploy
- Site生命周期：mvn site生成项目的站点文档

命令和生命周期的阶段的关系：不同的生命周期的命令可以同时执行(mvn clean package)



Maven 包含了一个项目对象模型 (Project Object Model)，一组标准集合，一个项目生命周期(Project Lifecycle)，一个依赖管理系统(Dependency Management System)，和用来运行定义在生命周期阶段 (phase)中插件(plugin)目标(goal)的逻辑。 

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

## 6 IDEA创建Maven工程

Java工程
* 使用骨架（archetype）创建Java工程

![](images\Maven_Java.PNG)

​	需要补齐IDEA没有自动创建的目录，并设置其属性。如resources（**Resources Root**）

* 不使用骨架（archetype）创建Java项目：resources自动创建并设置，仅仅少了test下resources目录。建议使用！



Web工程

* 使用骨架（archetype）创建Java Web工程

  ![](images\WebApp.PNG)

  需要补齐main目录下java（**Sources Root**）、resources（**Resources Root**）



资源包修改：Project Structure—>Modules即可设置，但是一般不会修改



Maven中project标签下初始配置可以如下，稍加修改

```xml
<modelVersion>4.0.0</modelVersion>

<groupId>com.itheima</groupId>
<artifactId>maven-01</artifactId>
<version>1.0-SNAPSHOT</version>
<packaging>jar</packaging>
```

------









> **框架**（Framework）是整个或部分系统的**可重用设计**，表现为一组抽象构件及构件实例间交互的方法；另一种定义认为，框架是可被应用开发者定制的应用骨架。前者是从应用方面而后者是从目的方面给出的定义。
>
> 框架一般处在低层应用平台（如 J2EE）和高层业务逻辑之间的中间层。 

# 第二部分 MyBatis

> MyBatis 本是 apache 的一个开源项目 iBatis，2010年这个项目由 apache software foundation 迁移到了 google code，并且改名为 MyBatis ，2013年11月迁移到 Github。
>
> MyBatis 是一个优秀的基于 java 的**持久层框架**，采用 **ORM 思想（对象关系映射）**解决了**实体和数据库映射**的问题，对 **jdbc进行了封装**，使开发者只需要关注 sql语句本身， 而不需要花费精力去处理加载驱动、创建连接、创建 statement 、处理结果集、关闭资源等繁杂的过程。 
>
> MyBatis 通过xml 或注解的方式将要执行的各种statement配置起来，并通过**java对象**和**statement 中 sql 的动态参数**进行**映射生成最终执行的 sql 语句**，最后由 mybatis 框架执行 sql 并将结果映射为 java 对象并返回。
>
>

## 1 入门

### 1.1 Maven依赖

- 单mybatis的依赖参考[官方文档](http://www.mybatis.org/mybatis-3/zh/index.html)，还需要mysql-connector-java的依赖

### 1.2 mybatis-config.xml

- resources目录中创建**mybatis-config.xml**、log4j.properties(日志输出)

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <configuration>
      <!-- 配置MyBatis环境。和spring整合后 environments配置将废除 -->
      <environments default="development">
          <!--配置mysql环境-->
          <environment id="development">
              <!-- 配置事务类型，使用jdbc事务管理 -->
              <transactionManager type="JDBC" />
              <!-- 数据库连接池 -->
              <dataSource type="POOLED">
                 <property name="driver" value="com.mysql.jdbc.Driver" />
                 <property name="url" value="jdbc:mysql:///mybatis?serverTimezone=GMT%2B8" /><!--不能带useSSL-->
                 <property name="username" value="root" />
                 <property name="password" value="password" />
              </dataSource>
          </environment>
      </environments>
  
      <!--告知MyBatis映射文件的位置-->
      <mappers>
          <!--注解开发  <mapper class="cn.itcast.dao.UserMapper"/>  -->
          <package name = "cn.itcast.mybatis.dao"/>
      </mappers>
  </configuration>
  ```

- 配置的内容和**顺序**如下

  - **==properties==**（属性）

    - mybatis-config.xml可以引用属性文件中的配置信息(如db.properties)，配置如下：

      ```properties
      jdbc.driver=com.mysql.cj.jdbc.Driver
      jdbc.url=jdbc:mysql:///mybatis?serverTimezone=GMT%2B8
      ```

      ```xml
      <!-- 用resource或url属性加载外部配置文件，也可以使用内部定义的属性 -->
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

  - settings（全局配置参数）

  - **==typeAliases==**（**类型别名**）：**==package==**

    ```xml
    <typeAliases>
        <!-- 单个别名定义， 大小写不敏感 -->
        <typeAlias alias="user" type="cn.itcast.mybatis.pojo.User" />
        <!-- 批量别名定义，扫描整个包下的类，别名为类名，大小写不敏感 -->
        <package name="cn.itcast.mybatis.pojo" />
    </typeAliases>
    ```

  - typeHandlers（类型处理器）

  - objectFactory（对象工厂）

  - plugins（插件）

  - environments（环境集合属性对象）

    - environment（环境子属性对象）
      - transactionManager（事务管理）
      - dataSource（数据源）

  - **==mappers==**（映射器）：告诉MyBatis去哪里找映射文件

    - 使用相对于类路径的**资源引用(xml)**；或完全限定**URL**；或**类名(可用于注解)**；或**==package==(可用于注解)**；

      - **resource**：指定的文件必须存在于**类路径下**，其封装了类加载器的方法
      - **URL**：统一资源定位符，可以唯一标示一个资源位置。写法：`协议 主机 端口 URI`。如HTTP协议，File协议
      - **URI**：统一资源标识符，可以在**应用中**唯一标示一个资源位置。

      ```xml
      <!-- 使用相对于类路径的资源引用 -->
      <mappers>
        <mapper resource="cn/itcast/dao/UserMapper.xml"/>
        <mapper resource="cn/itcast/dao/BookMapper.xml"/>
      </mappers>
      ```

      ```xml
      <!-- 使用完全限定资源定位符（URL） -->
      <mappers>
        <mapper url="file:///var/mappers/AuthorMapper.xml"/>
        <mapper url="file:///var/mappers/BlogMapper.xml"/>
      </mappers>
      ```

      ```xml
      <!-- 使用映射器接口实现类的完全限定类名 -->
      <mappers>
        <mapper class="cn/itcast/dao/UserMapper"/>
        <mapper class="cn/itcast/dao/BookMapper"/>
      </mappers>
      ```

      ```xml
      <!-- 将包内的映射器接口实现全部注册为映射器 -->
      <mappers>
        <package name="cn.itcast.dao"/>
      </mappers>
      ```


### 1.3 实体类、Mapper(Dao)接口

- domain中实体类通常与数据库表对应，作为mybatis进行sql映射使用
- `~Mapper`接口就是我们的持久层接口（也可以写成 `~Dao`接口），放在java中

### 1.4 Mapper.xml

Mapper接口开发方法只需要程序员编写**Mapper接口**和对应的**Mapper.xml文件**，由**Mybatis框架根据接口定义创建接口的==动态代理对象==(`sqlSession.getMapper(Class c)`)**

- Mapper接口开发需要遵循以下规范：
  - Mapper.xml和Mapper接口放置在**==同一包==**中，可以分别在java、resources同名目录下（注意resources中目录创建）
  - Mapper.xml文件中的**namespace**与mapper接口的**全限定类名**相同
  - Mapper.xml中定义的每个statement的**id**与Mapper接口**方法名**相同
  - Mapper.xml中定义的每个sql 的**parameterType**的类型和Mapper接口方法的**输入参数类型**相同
  - Mapper.xml中定义的每个sql的**resultType**的类型和Mapper接口方法的**输出参数类型**相同

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace：命名空间，用于隔离sql，必须是Mapper接口的全限定类名 -->
<mapper namespace="cn.itcast.mybatis.dao.UserDao">
    <!--id：取值必须是Mapper接口的方法名-->
    <select id="findUserById" parameterType="int" resultType="cn.itcast.mybatis.pojo.User">
        select * from user where id = #{id}
    </select>
</mapper>
```

注意 

- **`#{}`**表示一个**占位符**号，通过`#{}`可以实现preparedStatement向占位符中设置值，自动进行java类型和jdbc类型转换。#{}可以有效**防止sql注入**。 #{}可以接收**简单类型**值或**POJO中属性值**。 如果parameterType传输单个简单类型值，#{}括号中可以是**value或其它名称**
- ~~`${}`表示**拼接sql串**，不能防止SQL注入。通过 `${}`可以将parameterType 传入的内容拼接在sql中且不进行jdbc类型转换，`${}`可以接收**简单类型**值或**POJO中属性值**，如果parameterType**传输单个简单类型值**，`${}`括号中**只能是value**~~



### 1.5 注解Mapper接口

不需要Mapper.xml，直接在Mapper接口的方法上使用注解即可完成。原理同Mapper.xml。

```java
@Select("select * from user")  
List<User> findAll(); 
```

### ~~1.6 Dao实现类开发~~

​	原始Dao开发方法需要程序员编写Dao接口和Dao实现类

- 问题：
  - Dao方法体存在重复代码：通过SqlSessionFactory创建SqlSession，调用SqlSession的数据库操作方法，如selectOne、selectList、insert、update、delete
  - 调用sqlSession的数据库操作方法需要指定statement的namespace.id和参数，这里存在硬编码，不得于开发维护



### 1.7 SqlSession

```java
//1.读取配置文件   
InputStream is = Resources.getResourceAsStream("mybatis-config.xml");     
//2.使用构建者创建工厂对象 SqlSessionFactory。      使用构建者模式 
SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is); 
//3.使用 SqlSessionFactory 生产 SqlSession 对象。  使用工厂模式
SqlSession session = factory.openSession(); 
//4.使用 SqlSession 创建 dao 接口的动态代理对象。   使用动态代理
UserMapper userMapper = session.getMapper(UserMapper.class);   
//5.使用代理对象执行查询所有方法   
List<User> users = userMapper.findAll();   
for(User user : users) {
    System.out.println(user);   
} 
//6.释放资源   
session.close();   
in.close();
```

> 动态代理对象调用sqlSession.selectOne()和sqlSession.selectList()等方法是**根据mapper接口方法的返回值**决定，如果返回list则调用**selectList**方法，如果返回单个对象则调用**selectOne**方法……

1. **==构建者模式==**：**SqlSessionFactoryBuilder接收`mybatis-config.xml`文件流，构建出SqlSessionFactory对象**。最佳使用范围是**方法范围即方法体内局部变量**

2. **==工厂模式==**：**SqlSessionFactory读取`mybatis-config.xml`中连接数据库和mapper映射的信息，生产出真正操作数据库的SqlSession对象**。（SqlSessionFactory是一个接口，最佳使用范围是**整个应用运行期间**，创建后可以重复使用，通常以**单例模式**管理SqlSessionFactory）

3. **==动态代理==**：**SqlSession接口用于生成接口代理对象，定义通用增删查改方法**。每个线程都应有自己的SqlSession实例，不能共享使用，它也是**线程不安全**的。因此最佳的范围是**请求或方法范围**，绝对不能将SqlSession实例的引用放在一个类的静态字段或实例字段中。每次使用后关闭资源。


总结：使用mybatis创建的动态代理对象，还是直接使用其提供的CRUD方法。==本质都是**得到JDBC的Connection对象**，**执行对应的Sql语句**，最后**封装结果集**。==

注意：使用==**增删改**==方法后，要调用**SqlSession的`commit()`方法**==提交事务==才能成功



### 1.8 输入映射与输出映射

**OGNL**(Object-Graph Navigation Language)，它是一种功能强大的**表达式语言**，通过它简单一致的表达式语法（省略get、set），可以存取对象的**任意属性**，调用对象的**方法**，遍历整个对象的结构图，实现字段类型转化等功能。它使用相同的表达式去存取对象的属性

* **parameterType**：将要传入语句的参数的完全限定类名或别名。可选，MyBatis推断出具体传入语句的参数
  * **传递简单类型**：Integer、int、String等等，写全限定类名或简单类型都可以。使用`#{}`占位符，任意取值
  * **传递POJO对象**：Mybatis使用ognl表达式解析对象字段的值，`#{}`括号中的值为提供的POJO属性名称
  * **传递POJO包装对象**
    * 需求：根据用户名模糊查询用户信息。查询条件放到QueryVo的user属性中(包装类)
    * 查询条件可能是**综合的查询条件**，不仅包括用户查询条件还包括其它的查询条件（比如查询用户信息的时候，将用户购买商品信息也作为查询条件），这时可以**使用包装对象传递输入参数**
    * 包装对象：**POJO类中的一个属性是另外一个POJO**

* **resultType**：从这条语句中返回的期望类型的类的完全限定名或别名，若是集合，那应该是集合包含的类型
  * **输出简单类型**：Integer、int、String等等，写全限定类名或简单类型都可以。需求：查询用户表数据条数
  * **输出POJO对象**：`resultType = "cn.itcast.mybatis.pojo.User"`
  * **输出POJO列表**：`resultType = "cn.itcast.mybatis.pojo.User"`，分别进行映射，并把对象放到容器List中

* **resultMap（常用于多表查询）**

  * **SQL查询字段名和POJO的属性名不一致**（**MySQL**在**Windows**中字段名**不区分大小写**；在**Linux**中**区分**大小写）

    * 可以在使用**SQL语句**查询时用AS（可省略）**起别名**，执行效率比下面的高

    * 可以通过resultMap将字段名和属性名作**对应关系**。resultMap实质上还需要将查询结果映射到POJO对象中

      ```xml
      <!-- resultMap最终还是要将结果映射到POJO上 -->
      <!-- id：设置ResultMap的id；type就是指定映射到哪一个POJO -->
      <resultMap id="UserMap" type="User">
          <!-- 定义主键 ,非常重要。如果是多个字段,则定义多个id -->
          <!-- property：主键在POJO中的属性名；column：主键在数据库中的列名 -->
          <id property="userId" column="id" />
      
          <!-- 定义非主键字段映射-->
          <result property="userName" column="username" />
          <result property="userAddress" column="address" />
          <result property="userSex" column="sex" />
      </resultMap>
      
      <!-- 查询所有的订单数据 -->
      <select id="findAll" resultMap="UserMap">
          select * from user;
      </select>
      ```


### 案例

```java
public interface UserMapper {
    List<User> findAll(); //查询所有
    User findById(Integer id); //根据id查询
    List<User> findByUsername(String username); //根据用户名模糊查询
    Integer findCount(); //查询总记录数
    void addUser(User user); //增
    void deleteUser(Integer id); //删
    void updateUser(User user); //改
    List<User> findByQueryVo(QueryVo queryVo); //QueryVo
}
```

```xml
<!--UserMapper.xml-->
<mapper namespace="cn.itcast.dao.UserMapper">
    <select id="findAll" resultType="cn.itcast.domain.User">
        select * from user;
    </select>
    
    <select id="findById" parameterType="java.lang.Integer" resultType="cn.itcast.domain.User">
        select * from user where id=#{id}; <!--占位符可以随便写uid等-->
    </select>

    <select id="findByUsername" parameterType="java.lang.String" resultType="cn.itcast.domain.User">
        select * from user where username like #{username}; <!--占位符可以随便写name等-->
        <!--select * from user where username like '%${value}%';了解，不防SQL注入。$拼接字符串，占位符只能写value-->
    </select>

    <select id="findCount" resultType="java.lang.Integer">
        select count(*) from user;
    </select>

    
    <insert id="addUser" parameterType="cn.itcast.domain.User"> <!--下面占位符必须对应User中属性-->
    	<!-- selectKey 插入操作后获取插入的数据的id -->
    	<!-- keyProperty：主键对应的POJO中的哪一个属性 -->
    	<!-- keyColumn:主键对应的表中的哪一列 -->
    	<!-- order：设置在执行insert语句前执行查询id的sql，还是在执行insert语句之后执行查询id的sql-->
        <selectKey keyProperty="id" keyColumn="id" resultType="int" order="AFTER">
            <!--LAST_INSERT_ID():是mysql的函数，返回auto_increment自增列新记录id值-->
            select last_insert_id();
            <!-- 或   通过select uuid()得到uuid值 order="BEFORE"-->
        </selectKey>
        insert into user (username,birthday,sex,address) values (#{username},#{birthday},#{sex},#{address});
    </insert>

     <delete id="deleteUser" parameterType="java.lang.Integer">
        delete from user where id=#{id}; <!--占位符可以随便写uid等-->
    </delete>
    
    <update id="updateUser" parameterType="cn.itcast.domain.User"> <!--下面占位符必须对应User中属性-->
        update user set username = #{username},sex=#{sex},address=#{address},birthday=#{birthday} where id=#{id};
    </update>
    
    
    <select id="findByQueryVo" parameterType="cn.itcast.domain.QueryVo" resultType="cn.itcast.domain.User">
        select * from user where username like #{user.username};
    </select>
  
</mapper>
```

```java
public class TestUserMapper {
    InputStream is;
    SqlSession sqlSession;
    UserMapper userMapper;

    @Before
    public void init() throws IOException {
        is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(is);
        sqlSession = sqlSessionFactory.openSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void destroy() throws IOException {
        //提交事务
        sqlSession.commit();
        sqlSession.close();
        is.close();
    }
    
    @Test
    public void testFindAll() {
        List<User> users = userMapper.findAll();
    }    
    @Test
    public void testFindById() {
        User user = userMapper.findById(48);
    }
    @Test
    public void testFindByUsername() {
        List<User> users = userMapper.findByUsername("%王%"); //模糊查询在此时需要添加%
    }
    @Test
    public void testFindCount() {
        Integer count = userMapper.findCount()
    }

    
    @Test
    public void testAddUser() {
        User user = new User(null, "张三", "西安", "男", new Date());
        System.out.println(user);//User{id=null, username='张三', address='西安'......}
        userMapper.addUser(user);
        System.out.println(user);//User{id=53, username='张三', address='西安'.....}
    }
 	@Test
    public void testDeleteUser() {
        userMapper.deleteUser(49);
    }
    @Test
    public void testUpdateUser() {
        User user = new User(49, "王五", "北京", "女", new Date());
        userMapper.updateUser(user);
    }
    
    @Test
    public void testFindByQueryVo(){
        QueryVo queryVo = new QueryVo(new User( "%王%"));
        List<User> user = userMapper.findByQueryVo(queryVo);
    }
}
```



## 2 Mybatis中连接池和事务

### 2.1 连接池

我们在 WEB 课程中也学习过类似的连接池技术，而在 Mybatis 中也有连接池技术，但是它采用的是**自己的连接池技术**。在 Mybatis的`mybatis-config.xml`配置文件中，**通过`<dataSource type="pooled">`来实现** Mybatis 中连接池的配置。 

在 Mybatis 中我们将它的数据源 dataSource 分为以下几类： (运行测试用例后日志中即可看到区别)

* **==POOLED==**：实现了`java.sql.DataSource`接口规范的`PooledDataSource`类，**使用**连接池的数据源

  * > 当一个线程来获取连接池，先查找是否有空闲的连接池，有就返回，没有则查询活动的连接池数量是否等于允许的最大连接池数量，若不等于则创建并返回，否则获取使用时间最久的那个连接池（会判断是否失效）并返回

* UNPOOLED：同样实现了上述接口的`UnpooledDataSource`类，**不使用**连接池的数据源

* JNDI：采用**服务器提供的JNDI技术实现**，获取DadaSource对象，不同服务器获取的不一样。只能用于**web工程**或**Maven的war工程**。我们使用的**Tomcat**采用的连接池是**dbcp连接池**



### 2.2 事务

> 什么是事务？
>
> 事务的四大特性（ACID）
>
> 不考虑隔离级别会产生的3个问题
>
> 解决办法：四种隔离级别

MyBatis是对JDBC的封装，通过**==SqlSession对象==的`commit()`和`rollback()`**方法实现事务的提交和回滚。因为在连接池中取出的连接，都会将调用 `connection.setAutoCommit(false)`方法，所以需手动提交。

可以在获取SqlSession对象时设置`openSession(true)`（底层还是调用`connection.setAutoCommit(true)`方法），即可自动提交，但是不推荐。



### 2.3 JNDI

**JNDI**(Java Naming and Directory Interface)：Java名称和目录接口，是SUN公司推出的一套规范，属于JavaEE技术之一。目的是模仿windows系统中的注册表（map结构，key为目录+名称）

只能用于**web工程**或**Maven的war工程**

1. 创建Maven的war工程并导入坐标。还需要servlet-api和jsp-api依赖

2. 在webapp文件下创建META-INF目录，建立一个名为context.xml的配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <Context>
   <Resource 
   name="jdbc/mybatis"                       数据源的名称，JNDI中name，任意起名          
   type="javax.sql.DataSource"               数据源类型         
   auth="Container"                          数据源提供者  
   maxActive="20"                            最大活动数
   maxWait="10000"                           最大等待时间 
   maxIdle="5"                               最大空闲数
   username="root"                            
   password="1234"                            
   driverClassName="com.mysql.jdbc.Driver"          
   url="jdbc:mysql://localhost:3306/mybatis" 
   />
   </Context>
   ```

3. 修改mybatis-config.xml中配置

   ```xml
   <dataSource type="JNDI">
   	<property name = "data_source" value="java:comp/env/jdbc/mybatis"/>
   </dataSource>
   ```

4. 由于这个数据源是服务器提供的，所以需要经过服务器才能使用，不能用Test测试类来测试，需要在JSP或Servlet中测试





## 3 XML映射文件的动态SQL语句

- 通过mybatis提供的各种标签方法实现**动态拼接sql**

### 3.1 if、where、set

- where元素只会在**至少有一个子元素**的条件返回SQL子句的情况下才去**插入WHERE子句**。
- 而且，若语句的**开头**为“**AND**”或“**OR**”，where元素也会将它们**去除**。

```xml
<!--通过用户名和性别模糊条件查询-->
<select id="findUserByUsernameAndSex" parameterType="User" resultType="User" >
    select * from user
    <where>
        <if test="username!=null and username!=''">  <!--不能使用&&-->
            username like ${username} <!--需调用时提供%-->
        </if>
        <if test="sex!=null and sex!=''">
            and sex=#{sex}
        </if>
    </where>
</select>
```

* set元素会动态**前置 SET 关键字**，同时也会**删掉无关的逗号**

```xml
<update id="updateUser" parameterType="User" resultType="User">
    update Author
    <set>
        <if test="username != null">username=#{username},</if>
        <if test="address != null">password=#{password},</if>
        <if test="sex != null">bio=#{bio}</if>
    </set>
    where id=#{id}
</update>
```



### 3.2 foreach

- 向sql传递迭代对象(如 **List**、**Set**等)、**Map**对象或者**数组**对象或包装有他们的**QueryVo包装对象**

  需求：**根据多个id查询用户信息**

  查询sql：SELECT * FROM user WHERE id IN (1,10,24)

- **QueryVo包装对象**

  ```java
  //QueryVo.java
  List<Integer> ids;
  //或 Integer[] ids;
  //getter,setter...
  ```

  ```java
  List<User> selectUserByIds(QueryVo vo);
  ```

  ```xml
  <!-- 根据ids查询用户 -->
  <select id="queryUserByIds" parameterType="queryVo" resultType="user">
  	SELECT * FROM `user`
  	<where>
  		<!-- foreach标签，进行遍历 -->
  		<!-- collection：遍历的集合，指的是传入参数的属性，这里是QueryVo的ids属性 -->
  		<!-- 当使用可迭代对象或者数组时，index 是当前迭代的次数，item 的值是本次迭代获取的元素 -->
           <!-- 当使用 Map 对象（或者 Map.Entry 对象的集合）时，index 是键，item 是值 -->
  		<!-- open：在前面添加的sql片段 -->
  		<!-- close：在结尾处添加的sql片段 -->
  		<!-- separator：指定遍历的元素之间使用的分隔符，不指定默认为逗号“，” -->
  	   <foreach collection="ids" item="item" open="id IN (" close=")" separator=",">
  			#{item}
  	   </foreach>
  	</where>
  </select>
  ```

- 当传递的参数是**List对象**，collection应赋值**collection**或**list**

  当传递的参数是**Set对象**，collection应赋值**collection**

  当传递的参数是**Array对象**，collection应赋值**array**

  当传递的参数是Map对象，collection没有默认值。暂时未找到应用场景，没有实现

  ```java
  public List<User> selectUserByIds(Integer[] ids);
  public List<User> selectUserByIds(List<Integer> ids); 
  ```

  ```xml
  <select id="findUserByIds"  resultType="User" >
      select * from user where
      <foreach collection="array" item="item" open="id in (" close=")" separator=",">
          #{item}
      </foreach>
  </select>
  ```

### 3.3 sql片段（了解）

- Sql中可将重复的sql提取出来，使用时用include引用即可，最终达到sql重用的目的。暂时没啥用

  ```xml
  <sql id="selector">
      select * from user
  </sql>
  <select id="findUserById" parameterType="int" resultType="User">
      <include refid="selector"/>
      where id = #{id}
  </select>
  ```



## 4 MyBatis中多表查询

* 4.1和4.2案例使用的是**用户**和**账户**：一个用户可以有多个账户；一个账户只能属于一个用户（多个账户也可以属于一个用户）
* 步骤
  1. 建立两张表：**用户表**、**账户表**。让用户表和账户表**具备一对多**关系，需使用**外键**在账户表中添加
  2. 建立两个实体类：**用户类**、**账户类**，让让用户类和账户类能**体现出一对多**关系
     - **从表类引用主表类**；**主表类包含从表类集合的引用**；
  3. 建立两个配置文件：用户和账户的配置文件
  4. 实现配置：查询用户时，同时得到用户下所包含的账户信息；查询账户时，可以得到账户所属用户的信息

- 需要使用**==resultMap==**，resultType也行但是得创建继承类麻烦

### 4.1 一对一（多对一）(association)

- 查询所有**账户**信息，关联查询**用户**信息。 **==从表类引用主表类==**`private User user;`

  因为一个账户信息只能供某个用户使用，所以从查询账户信息出发关联查询用户信息为一对一查询。

  如果从用户信息出发查询用户下的账户信息则为一对多查询，因为一个用户可以有多个账户。 

  ```java
  //AccountMapper.java
  List<Account> findAll();
  ```

  ```xml
  <!--AccountMapper.xml-->
  <!--以下主从表column不能相同；使用了别名，有关type的不用写全限定类名，大小写不敏感；id为主键列，result为其他列-->
  <resultMap id="AccountUserMap" type="Account">
      <id property="id" column="aid" />
      <result property="uid" column="uid"/>
      <result property="money" column="money"/>
      <!--一对一映射association，配置引用的User内容-->
      <association property="user" javaType="User">
          <id property="id" column="id"/>
          <result property="username" column="username"/>
          <result property="address" column="address"/>
          <result property="sex" column="sex"/>
          <result property="birthday" column="birthday"/>
      </association>
  </resultMap>
  
  <select id="findAll" resultMap="AccountUserMap">
      SELECT u.*,a.id aid,a.uid,a.money FROM account a JOIN user u ON a.uid=u.id;
  </select>
  ```

### 4.2 一对多(collection)

- 查询所有**用户**信息及用户关联的**账户**信息。 **==主表类包含从表类集合的引用==**`private List<Account> accounts`

  用户信息和他的账户信息为一对多关系，并且查询过程中如果用户没有账户信息，此时也要将用户信息查询出来，我们想到了**左外连接**查询比较合适。 

  ```java
  //UserMapper.java
  List<User> findAll();
  ```

  ```xml
  <!--UserMapper.xml-->
  <!--以下主从表column不能相同；使用了别名，有关type的不用写全限定类名，大小写不敏感；id为主键列，result为其他列-->
  <resultMap id="UserAccountMap" type="User">
      <id property="id" column="id"/>
      <result property="username" column="username"/>
      <result property="address" column="address"/>
      <result property="sex" column="sex"/>
      <result property="birthday" column="birthday"/>
      <!--一对多映射collection，配置引用的Account集合内容，由于泛型，使用其值类型-->
      <collection property="accounts" ofType="Account">
          <id property="id" column="aid"/>
          <result property="uid" column="uid"/>
          <result property="money" column="money"/>
      </collection>
  </resultMap>
  
  <select id="findAll" resultMap="UserAccountMap">
      SELECT u.*,a.id aid,a.uid,a.money FROM `user` u LEFT JOIN account a ON u.id = a.uid;
  </select>
  ```

### 4.3 多对多

* 多对多关系其实我们看成是**==双向的一对多关系==**。原理同4.2

* 案例使用**用户**和**角色**：一个用户可以有多个角色；一个角色可以赋予多个用户

* 步骤

  1. 建立两张表：**用户表**、**角色表**。让用户表和角色表**具备多对多**关系，需要中间表，**中间表外键引用**两张表各自的**主键**
  2. 建立两个实体类：**用户类**、**角色类**，让让用户类和角色类能**体现出多对多**关系
     - **==各自包含对方一个集合的引用==**；`private List<User> users;` 、`private List<Role> roles;`
  3. 建立两个配置文件：用户和角色的配置文件
  4. 实现配置：查询用户时，同时得到用户下所包含的角色信息；查询角色时，可以得到角色所赋予用户的信息

  ```java
  //RoleMapper.java
  List<Role> findAll();
  ```

  ```xml
  <mapper namespace="cn.itcast.dao.RoleMapper">
      <resultMap id="roleMap" type="Role">
          <id property="roleId" column="rid"/>
          <result property="roleName" column="role_name"/>
          <result property="roleDesc" column="role_desc"/>
          <collection property="users" ofType="User">
              <id property="id" column="id"/>
              <result property="username" column="username"/>
              <result property="address" column="address"/>
              <result property="sex" column="sex"/>
              <result property="birthday" column="birthday"/>
          </collection>
      </resultMap>
  
      <select id="findAll" resultMap="roleMap">
          SELECT u.*, r.id rid, r.role_name, r.role_desc
          FROM role r
          LEFT JOIN user_role ur ON r.id = ur.rid
          LEFT JOIN user u ON ur.uid = u.id
      </select>
  </mapper>
  ```

  ```java
  //UserMapper.java
  List<User> findAll();
  ```

  ```xml
  <mapper namespace="cn.itcast.dao.UserMapper">    
      <resultMap id="userRoleMap" type="User">
          <id property="id" column="id"/>
          <result property="username" column="username"/>
          <result property="address" column="address"/>
          <result property="sex" column="sex"/>
          <result property="birthday" column="birthday"/>
          <collection property="roles" ofType="Role">
              <id property="roleId" column="rid"/>
              <result property="roleName" column="role_name"/>
              <result property="roleDesc" column="role_desc"/>
          </collection>
      </resultMap>
  
      <select id="findAll" resultMap="userRoleMap">
          SELECT u.*, r.id rid, r.role_name, r.role_desc
          FROM user u
          LEFT JOIN user_role ur ON u.id = ur.uid
          LEFT JOIN role r ON ur.rid = r.id
      </select>
  </mapper>
  ```





## 5 MyBatis延迟加载策略

在查询用户时，用户下的账户信息应该是什么时候使用什么时候查询

在查询账户时，账户所属的用户信息应该随着账户查询一起查询出来

* **延迟加载**：就是在**需要==用到数据==时才进行加载**，不需要用到数据时就不加载数据。也称懒加载或按需加载
  * 弊端：在大批量数据查询时，因为查询工作也要消耗时间，所以可能造成用户等待时间变长，造成用户体验下降
* **立即加载**：无论用不用数据都进行加载

在对应的四种表关系中，**延迟加载**用于**一对多**、**多对多**；**立即加载**用于**一对一**、**多对一**

* **mybatis-config.xml中开启延迟加载**配置如下

```xml
<settings>
    <setting name="lazyLoadingEnabled" value="true"/> <!--延迟加载的全局开关。当开启时，所有关联对象都会延迟加载-->
    <!--<setting name="aggressiveLazyLoading" value="false"/>此属性在版本>3.4.1时值默认为false，可以不设置-->
</settings>
```

### 5.1 一对一、多对一

虽然理论上一对一、多对一可以使用立即加载，但是为方便学习

```java
//AccountMapper.java
List<Account> findAll();
```

```xml
<!--AccountMapper.xml-->
<mapper namespace="cn.itcast.dao.AccountMapper">
    <!--有关type的不用写全限定类名，大小写不敏感；id为主键列，result为其他列-->
    <resultMap id="AccountUserMap" type="Account">
        <id property="id" column="id" />
        <result property="uid" column="uid"/>
        <result property="money" column="money"/>
        <!--一select：查询用户的唯一标示；column：查询用户时需要传递的字段-->
        <association property="user" column="uid" javaType="User" select="cn.itcast.dao.UserMapper.findById" >
        </association>
    </resultMap>

    <select id="findAll" resultMap="AccountUserMap">
        SELECT * from account
    </select>
</mapper>
```

```java
//UserMapper.java
User findById(Integer id);
```

```xml
<!--UserMapper.xml-->
<mapper namespace="cn.itcast.dao.UserMapper">
    <select id="findById" resultType="cn.itcast.domain.User">
        select * from user where id=#{id}; <!--占位符可以随便写uid等-->
    </select>
</mapper>
```

测试类中若**仅调用`findAll`方法**，不对其返回值操作，则**只会查询Account数据**，没有其所属User的数据

### 5.2 一对多、多对多

```java
//UserMapper.java
List<User> findAll();
```

```xml
<!--UserMapper.xml-->
<mapper namespace="cn.itcast.dao.UserMapper">
    <!--使用了别名，有关type的不用写全限定类名，大小写不敏感；id为主键列，result为其他列-->
    <resultMap id="UserAccountMap" type="User">
        <id property="id" column="id"/>
        <result property="username" column="username"/>
        <result property="address" column="address"/>
        <result property="sex" column="sex"/>
        <result property="birthday" column="birthday"/>
        <!--一select：查询账户的唯一标示；column：查询账户时需要传递的字段-->
        <collection property="accounts" ofType="Account" select="cn.itcast.dao.AccountMapper.findById" column="id">
        </collection>
    </resultMap>

    <select id="findAll" resultMap="UserAccountMap">
        SELECT * from user
    </select>
</mapper>
```

```java
//AccountMapper.java
List<Account> findById(Integer id);
```

```xml
<!--AccountMapper.xml-->
<mapper namespace="cn.itcast.dao.AccountMapper">
    <select id="findById" resultType="Account">
        select * from account where uid = #{uid}
    </select>
</mapper>
```



## 6 MyBatis的缓存

* 缓存：存在于内存中的临时数据
* 为什么使用缓存：通过缓存策略来**减少数据库的查询次数**，从而**提高性能**
* 什么数据能使用缓存，什么数据不能使用缓存？
  * **适用**于缓存：**经常被查询**且**不经常改变的**；数据的正确性对**结果影响不大**的
  * **不适用**于缓存：**经常改变的数据**；数据的正确性对最终**结果影响很大**（商品库存、银行汇率、股市牌价）

Mybatis中缓存分为一级缓存，二级缓存

### 6.1 一级缓存

* 一级缓存是 **==SqlSession==** 对象的缓存，当调用SqlSession的**添加，删除，修改，`commit()`，`close()`，`clearCache()`等**方法时，就会**清空一级缓存**。 默认开启。

  > 执行查询后，结果会同时存入SqlSession为我们提供的一块区域中（结构为map），再次查询时先从SqlSession中找...

* 验证：两次查询同一User对象并打印，结果Hash值相同，并且SQL语句只执行了一次。若中间修改数据库数据，则执行两次

### 6.2 二级缓存

* 二级缓存是值**==SqlSessionFactory==**对象的缓存，由同一个SqlSessionFactory对象创建的SqlSession共享

* 二级缓存使用步骤

  1. 让MyBatis框架支持二级缓存，在mybatis-config.xml中配置。不配置也行，默认为true开启。

     ```xml
     <settings>
         <setting name="cacheEnabled" value="true"/>
     </settings>
     ```

  2. 让映射文件支持二级缓存，如在UserMapper.xml中配置，在mapper标签内

     ```xml
     <cache/>
     ```

  3. 让当前的操作支持二级缓存，如在select标签中配置

     ```xml
     <select id="findById" resultType="User" useCache="true">
         select * from user where id=#{id};
     </select>
     ```

* 验证：同一个SqlSessionFactory对象创建的SqlSession并获取代理接口实现类，查询同一User对象（期间清空一级缓存），发现日志中**SQL语句只执行了一次**，但是**Hash值却不同**。原因是**==二级缓存存储的只是数据==**，当被查询时**创建对象并返回**。



## 7 MyBatis的注解开发

> 注解不能和xml在同一目录同时存在，否则运行直接报错。一般也没人这么干

### 7.1 mybatis的常用注解说明

* @Select：实现查询

* @Insert：实现新增

* @Delete：实现删除

* @Update：实现更新   

* @**Result**：实现结果集封装。id、property、column

  * @**One**：实现一对一结果集封装。select、fetchType（枚举类，延迟加载选项）
  * @**Many**：实现一对多结果集封装 

* @**Results**：可以与@Result 一起使用（放在value中），**封装多个结果集**。id、value

* @**ResultMap**：实现**引用@Results** 定义的封装 

* @**CacheNamespace**：实现注解**二级缓存**的使用，blocking，布尔值



* @SelectProvider：实现动态 SQL 映射，还有@InsertProvider、@DeleteProvider、@UpdateProvider

  需自定义类和方法，之后添加注解`@SelectProvider(type = UserDaoProvider.class, method = "findUserById")  `



### 7.2 单表CRUD

```java
@Select("select * from user")
List<User> findAll();

@Select("select * from user where id = #{id}")
User findById(Integer id);

@Select("select * from user where username like #{username}")
List<User> findByUsername(String username);

@Select("select count(*) from user")
Integer findCount();

@Insert("insert into user (username,address,sex,birthday) values (#{username},#{address},#{sex},#{birthday})")
void addUser(User user);

@Update("update user set username=#{username} where id = #{id}")
void updateUser(User user);

@Delete("delete from user where id=#{id}")
void deleteUser(Integer id);
```

### 7.3 单表结果集封装

数据库字段名和JavaBean属性名称不一致

```java
@Select("select * from user")
@Results(id = "UserMap",value = {
    @Result(id = true,property = "userId",column = "id"), //主键要设置id为true，id的默认值为false
    @Result(property = "userName",column = "username"),
    @Result(property = "userBirthday",column = "birthday"),
    @Result(property = "userSex",column = "sex"),
    @Result(property = "userAddress",column = "address")
})
List<User> findAll();


@Select("select * from user where id = #{id}")
@ResultMap("UserMap")
User findById(Integer id);

@Select("select * from user where username like #{username}")
@ResultMap("UserMap")
List<User> findByUsername(String username);
```

### 7.4 一对一（多对一）

* 采用立即加载

```java
//AccountMapper.java
@Select("select * from account")
@Results(id = "AccountMap",value = {
    @Result(id = true,property = "id",column = "id"),
    @Result(property = "uid",column = "uid"),
    @Result(property = "money",column = "money"),
    @Result(property = "user",column = "uid",
            one = @One(select = "cn.itcast.dao.UserMapper.findById",fetchType = FetchType.EAGER ))

})
List<Account> findAll();

//应给7.5提供的
@Select("select * from account where uid = #{uid}")
List<Account> findById(Integer uid);
```

### 7.5 一对多

* 采用懒加载（延迟加载）

```java
//UserMapper.java
@Select("select * from user")
@Results(id = "UserMap",value = {
    @Result(id = true,property = "id",column = "id"),
    @Result(property = "username",column = "username"),
    @Result(property = "birthday",column = "birthday"),
    @Result(property = "sex",column = "sex"),
    @Result(property = "address",column = "address"),
    @Result(property = "accounts",column = "id",
            many = @Many(select = "cn.itcast.dao.AccountMapper.findById",fetchType = FetchType.LAZY))
})
List<User> findAll();

//应给7.4提供的
@Select("select * from user where id = #{id}")
User findById(Integer id);
```

> 注意：7.4和7.5的findById都使用ResultMap的话，互相无限调用导致栈溢出



### 7.6 缓存

* 一级缓存不需要开启，直接就可使用

* 二级缓存的配置文件默认支持（同XML），只需给Mapper接口添加注释

  ```java
  @CacheNamespace(blocking = true)
  public interface UserMapper {
  	...
  }
  ```






## 其他 与Spring整合

### 8.1 整合思路

- SqlSessionFactory对象应该放到spring容器中作为单例存在
- 传统dao的开发方式中，应该从spring容器中获得sqlsession对象
- Mapper代理形式中，应该从spring容器中直接获得mapper的代理对象
- 数据库的连接以及数据库连接池事务管理都交给spring容器来完成

### 8.2 需要的jar包

- spring的jar包
- Mybatis的jar包
- Spring+mybatis的整合包
- Mysql的数据库驱动jar包（记得用**和自己mysql版本一致**的jar包）
- 数据库连接池的jar包

### 8.3 配置文件

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
jdbc.url=jdbc:mysql://localhost:3306/mybatis?serverTimezone=GMT%2B8
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

- mybatis-config.xml只需定义别名、mappers（整合后也不需要提供）即可；其他的不变

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

- 配置文件修改：
  - 修改要生成的数据库表
  - **pojo**文件所在包路径
  - **Dao**所在的包路径
- 注意：
  - 逆向工程生成的代码只能做**单表查询**
  - 不能在生成的代码上进行扩展，因为如果数据库变更，需要重新使用逆向工程生成代码，原来编写的代码就被覆盖了。
  - 一张表会生成**4个文件**
  - User.java(返回值对象); UserExample.java(条件查询时装数据的对象); UserDao.java; UserDao.xml





# 第三部分 Spring

> 以5.0.2版本讲解。要求JDK8及以上，Tomcat8.5及以上

## 1 Spring概述

Spring是分层的 Java SE/EE应用 **full-stack 轻量级开源框架**，**以 IoC（Inverse Of Control： 控制反转）和 AOP（Aspect Oriented Programming：面向切面编程）为内核**，提供了展现层 Spring MVC 和持久层 Spring JDBC 以及业务层事务管理等众多的企业级应用技术，还能整合开源世界众多著名的第三方框架和类库，逐渐成为使用最多的Java EE 企业应用开源框架。 

------

Spring的**优势**：

* **方便解耦，简化开发**

  通过Spring提供的IoC容器，可以将对象间的依赖关系交由 Spring进行控制，避免硬编码所造成的过度程序耦合。用户也不必再为单例模式类、属性文件解析等这些很底层的需求编写代码，可以更专注于上层的应用

* **AOP编程**的支持

  通过 Spring的 AOP 功能，方便进行面向切面的编程，许多不容易用传统OOP实现的功能可以通过 AOP 轻松应付

* **声明式事务**的支持  

  可以将我们从单调烦闷的事务管理代码中解脱出来，通过声明式方式灵活的进行事务的管理， 提高开发效率和质量

* 方便程序的**测试**  

  可以用非容器依赖的编程方式进行几乎所有的测试工作，测试不再是昂贵的操作，而是随手可做的事情

* 方便**集成各种优秀框架**  

  Spring可以降低各种框架的使用难度，提供了对各种优秀框架（Struts、Hibernate、Hessian、Quartz 等）的直接支持

* **降低JavaEE API的使用难度**  

  Spring对 JavaEE API（如 JDBC、JavaMail、远程调用等）进行了薄薄的封装层，使这些 API 的 使用难度大为降低

* **Java源码是经典学习范例**  

  Spring的源代码设计精妙、结构清晰、匠心独用，处处体现着大师对Java 设计模式灵活运用以 及对 Java技术的高深造诣。它的源代码无意是 Java 技术的最佳实践的范例

------

**Spring的体系结构**

![](images\spring-overview.png)

## 2 IoC

引入：程序间耦合

- 在软件工程中，耦合指的就是就是**==对象之间的依赖性==**。对象之间的耦合越高，维护成本越高。因此对象的设计应使类和构件之间的耦合最小。软件设计中通常用耦合度和内聚度作为衡量模块独立程度的标准。**划分模块的一个准则**就是**==高内聚低耦合==**。

- **Bean**：计算机英语中，有**可重用组件**的含义

  JavaBean：用**Java语言编写的可重用组件**。JavaBean>实体类

- 实际开发中，应做到：**编译期不依赖**，**运行时才依赖**

- 解耦的思路（**==工厂模式==**）：
  1. **读取配置文件的key**获取要创建对象的**全限定类名value**；
  2. 使用**反射创建对象**，避免使用new关键字；
  3. 定义一个map**容器**，在静态代码块中创建所有对象并存放。获取对象时直接返回对应key的value，是单例的

------

* ==**IoC（控制反转）**：**把创建对象的权利交给容器（或工厂）**。可以**解决程序之间的耦合**，解除代码中的依赖关系。==
* DI：依赖注入。即调用类对某一接口实现类的依赖关系由第三方容器或协作类注入，移除调用类对某一接口实现类的依赖
  * 一般完成特定的业务逻辑可能会需要多个类之间进行协作。按传统的做法，每个对象负责管理与自己互相协作的对象(它所依赖的对象)的引用，这会导致高度耦合并难以测试的代码。利用依赖注入，每个对象可以直接获取所依赖的对象

------

Maven中添加Spring依赖的坐标

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>5.1.1.RELEASE</version>
</dependency>
```

上述是复合依赖，总共依赖了6项：

* Core container中四项`spring-beans`、`spring-core`、`spring-context`、`spring-expression` 和 `spring-jcl`（封装logging）以及注解时要依赖的`spring-aop`

### 2.1 基于xml的配置

步骤：

1. 在resources中创建**spring-config.xml**文件。（约束在参考中搜索`xmlns`即可找到）

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd">
   </beans>
   ```

2. 在配置文件中**配置让 spring 创建的对象**（原理和引入中工厂模式一致）

   ```xml
   <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
   <bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"></bean>
   ```

3. 获取Spring IoC核心容器，并根据id获取对象

   ```java
   ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");
   AccountDao accountDao = (AccountDao) ac.getBean("accountDao"); //需要强转
   AccountService accountService = ac.getBean("accountService", AccountService.class); //传字节码文件对象，不用强转
   ```

------

Spirng中容器接口及其实现类

* ~~`BeanFactory`顶层容器接口。创建对象采用**延迟加载**方式（何时使用何时创建，多例适用）~~
* **`ApplicationContext`**容器接口。创建对象采用**立即加载**方式（==**读完配置文件**，**默认情况下就创建(可修改)**==，单例适用）
  * **`ClassPathXmlApplicationContext`**，基于xml配置的实现类。只能加载**类路径**下的配置文件
  * `FileSystemXmlApplicationContext`，基于xml配置的实现类。可以加载**磁盘任意路径**的配置文件（需有访问权限）
  * **`AnnotationConfigApplicationContext`**，基于注解配置的实现类。读取**注解**创建容器

------

#### 2.1.1 Spirng对Bean的管理细节

##### 1、bean标签

* 作用：==**配置对象让spring来创建的**==。默认情况下它调用的是类中的**==无参构造函数==**。如果没有无参构造函数则不能创建成功。 
* 属性：
  * **`id`**：给对象在容器中提供一个**唯一标识**，用于获取对象
  * **`class`**：指定**类的全限定类名**，用于**反射创建对象**，默认情况下调用无参构造函数
  * **`scope`**：==**指定对象的作用范围**==
    * **`singleton`**：**==单例==**的（默认），一个应用只有一个对象的实例，它的作用范围就是**整个应用** 
    * **`prototype`**：**==多例==**的，每次访问对象时，都会重新创建对象实例。
    * `request`：WEB 项目中，Spring 为**每个请求**创建一个bean实例
    * `session`：WEB 项目中，Spring 为**每个会话**创建一个bean实例
    * `global-session`：作用于**集群(Portlet)环境的全局会话范围**，当不是集群(Portlet)环境时，它就是session
  * `init-method`：指定类中的初始化方法名称
  * `destroy-method`：指定类中销毁方法名称。单例中需要关闭容器（使用实现类的方法）

##### 2、Bean对象的生命周期

- **==单例对象：生命周期和容器相同==**，一个应用只有一个对象的实例，它的作用范围就是整个应用
  - 出生：当容器创建时出生（读取完配置文件）
  - 存活：只要容器存在，对象一直存活
  - 死亡：容器销毁，对象消亡
- **==多例对象==**：每次访问对象时，都会重新创建对象实例。
  - **出生**：当我们**==使用对象==**时，**Spring框架为我们创建**
  - **存活**：对象只要在**使用过程中，就一直存活**
  - **死亡**：当对象**长时间不使用，且没有别的对象引用**时，由Java的**GC**机制回收

##### 3、创建Bean的三种方式

1. **使用==默认构造方法==创建**

   配置文件中使用`bean`标签以及`id`和`class`属性，且没有其他属性和标签时~。若类中没有默认构造方法，则对象无法创建

   ```xml
   <bean id="accountDao" class="com.itheima.dao.impl.AccountDaoImpl"></bean>
   ```

2. 使用**==普通工厂中的方法==**创建对象（也称使用某个类中的方法创建对象），并存入Spirng容器

   ```xml
   <bean id="accountDaoFactory" class="com.itheima.factory.AccountDaoFactory"></bean>
   <bean id="accountDao" factory-bean="accountDaoFactory" factory-method="getAccountDao"></bean>
   ```

3. 使用**==静态工厂中的静态方法==**创建对象（也称使用某个类中的静态方法创建对象），并存入Spirng容器

   ```xml
   <bean id="accountDao" class="com.itheima.factory.AccountDaoFactory" factory-method="getAccountDao"></bean>
   ```



#### 2.1.2 依赖注入

> 我们的业务层仍会调用持久层的方法，在使用 spring 之后，就让 **spring 来维护**了他们的**依赖关系**。就是让框架把持久层对象传入业务层，不用自己去获取。

DI（Dependency Injection）：**依赖注入**，即是**依赖关系的维护**交由Spirng管理。

能注入的数据有三类：（不适用于经常变换的数据）

* **基本类型和String**
* **其他bean类型**（在配置文件或注解中配置过的bean）
* **复杂类型/集合类型**：如Array、List、Set、Map、Properties



##### 1、构造方法注入

使用类中的构造方法给成员变量赋值。注意：赋值的操作不是我们自己做的，而是通过配置的方式，让spring框架来为我们注入

* 要求：类中需要提供一个**对应参数列表的构造方法**
* 标签：`bean`标签中**`constructor-arg`**
  * 属性：
    * ~~`index`：指定参数在构造函数参数列表的索引位置，从0开始。不常用。~~
    * ~~`type`：指定参数在构造函数中的数据类型。不常用。~~
    * **`name`**：指定**参数在构造函数中的名称**
    * **`value`**：它能赋的值是**基本数据类型**和 **String 类型** 
    * **`ref`**：它能赋的值是**其他 bean 类型**，也就是说，必须得是**配置过的 bean** 

```xml
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"> 
    <constructor-arg name="name" value=" 张三 "></constructor-arg>  <!--String类型-->
    <constructor-arg name="age" value="18"></constructor-arg>       <!--基本类型-->
    <constructor-arg name="birthday" ref="now"></constructor-arg>   <!--其他bean类型-->
</bean> 
<bean id="now" class="java.util.Date"></bean> 
```

* 优势：在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功。
* 弊端：优势也是其弊端，改变了bean对象的实例化方式。创建对象时，即使用不到这些数据，也必须提供。

> c-命名空间注入（本质是构造方法注入）：无法实现装配集合



##### 2、set方法注入(使用最多)

* 要求：就是在类中**提供需要注入成**员的 **set 方法**
* 标签：`bean`标签中**`property`**
  * 属性：
    * **`name`**：找的是类中 **set 方法后面的部分** 
    * **`value`**：它能赋的值是**基本数据类型**和 **String 类型**
    *  **`ref`**：它能赋的值是**其他 bean 类型**，也就是说，必须得是**配置过的 bean** 

```xml
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl">   
    <property name="name" value="test"></property> <!--String类型-->
    <property name="age" value="21"></property>    <!--基本类型-->
    <property name="birthday" ref="now"></property> <!--其他bean类型-->
</bean> 
<bean id="now" class="java.util.Date"></bean>
```

* 优势：创建对象时，注入的参数没有明确限制，可以直接使用默认构造方法
* 弊端：优势也是其弊端，若某个成员必须有值，则获取对象时有可能set方法没有执行

> p-命名空间注入（本质还是调用 set 方法）：无法实现装配集合



##### 3、注入集合属性

就是给类中的**集合成员传值**，可以采用set方法注入的方式，只不过变量的数据类型都是集合

* 注入集合数据：只要**==结构相同，标签可以互换==** 
  * List结构可以用：array、list、set
  * Map结构可以用：map、entry；props、prop。但是properties只能存储键值都是字符串的数据。

```xml
<bean id="accountService" class="com.itheima.service.impl.AccountServiceImpl"> 
 <!-- 在注入集合数据时，只要结构相同，标签可以互换 -->  
    <!-- 给数组注入数据 -->  
    <property name="myStrs"> 
        <set>    
            <value>AAA</value>    
            <ref bean="car"></ref><!--也可以引用类型。只是列出来参考，实际不会这样存储不同类型数据-->
        </set> 
    </property> 
    <!-- 注入 list 集合数据 -->  
    <property name="myList">   
        <array>    
            <value>AAA</value>    
            <ref bean="car"></ref><!--也可以引用类型。只是列出来参考，实际不会这样存储不同类型数据-->
        </array>  
    </property>  
    <!-- 注入 set 集合数据 -->  
    <property name="mySet"> 
        <list>    
            <value>AAA</value>    
            <ref bean="car"></ref><!--也可以引用类型。只是列出来参考，实际不会这样存储不同类型数据-->
        </list>  
    </property> 
    <!--=============================================-->
    <!-- 注入 Map 数据 -->  
    <property name="myMap">   
        <map>    
            <entry key="testA" value="aaa"></entry> 
            <entry key-ref="car" value-ref="car"></entry> <!--key,value,key-ref,value-ref  共4种组合 -->
            
            <!--value-type="cn.itcast.pojo.Car"   可以不设置，设置后值类型唯一-->
      <!--  <entry key="testB">   也可以这样写，但是麻烦  
                <value>bbb</value> 
            </entry>  --> 
        </map>
    </property> 
    <!-- 注入 properties 数据--> 
    <property name="myProps"> 
          <props> 
            <prop key="testA">aaa</prop>    
            <prop key="testB">bbb</prop>   
        </props> 
    </property> 
</bean>
<!--========================被引用的测试bean =====================================-->
<bean name="car" class="cn.itcast.pojo.Car">
    <property name="name" value="BMW"></property>
    <property name="color" value="red"></property>
</bean>
```





### 2.2 基于注解的配置

在spring-config.xml**开启对注解的支持**，并告知Spring**创建容器时扫描的包**。（约束在参考中搜索`xmlns:context`即可找到） 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="com.itheima"/>
</beans>
```



#### 2.2.1 创建对象

> 作用和xml配置文件中编写`<bean>`标签实现功能一致

* **`@Component`**：用于==**把当前类对象存入Spirng容器中**==

  * 属性：`value`：用于指定bean的id。不写时默认为当前类名，且首字母小写

* **`@Controller`**：表现层

* **`@Service`**：业务层

* **`@Repository`**：持久层

  以上三个注解的作用和属性与`@Componet`一致，是Spring提供明确的三层架构使用的注解，使三层对象更加清晰

#### 2.2.2 依赖注入

> 作用和xml配置文件中的`<bean>`标签编写`<property>`标签实现功能一致

* **`@Autowired`**：==自动**按照类型注入**==

  只要容器中**有唯一一个**bean对象类型和要注入的变量**类型匹配**，就注入成功。没有一个则报错

  如果有**多个类型匹配到**，使用要注入的对象**变量名称**作为bean的id，在spring容器查找，找到了也可以注入成功。否则报错

  * 出现的位置：可以是变量上；可以是方法上
  * 细节：使用注解注入时，set方法就不是必须的

* **`@Qualifier`**：在自动按照类型注入的**基础之上**，**再按照 Bean 的 id 注入**

  在给**类成员注入时不能单独使用**，但是**给方法参数注入时可以单独使用**

  * 属性：`value`用于指定注入的bean的id

* **`@Resource`**：直接按照bean的id注入，可以独立使用。找不到再按照类型查找。JaveEE的注解，耦合性低

  * 属性：`name`用于指定注入的bean的id

  以上三个注解都**只能注入其他bean类型数据**，而基本类型和String无法注入。集合类型注入只能用xml、javaConfig实现

* **`@Value`**：用于注入基本类型和String数据

  * 属性：`value`指定数据的值。可以使用SpEL（写法：`${表达式}`）

#### 2.2.3 改变作用范围

> 作用和xml配置文件中的`<bean>`标签编写`<scope>`标签实现功能一致

* **`@Scope`**：用于指定bean的作用范围
  * 属性：`value`指定范围的取值。常用有：`singleton`、`prototype`、request、session、globalsession

#### 2.2.4 生命周期相关（了解）

> 作用和xml配置文件中的`<bean>`标签编写`<init-method>`和`<destroy-method>`标签实现功能一致

* `@PostConstruct`：用于指定初始化方法
* `@PreDestroy`：用于指定销毁方法。单例中需要关闭容器（使用实现类的方法）



在开始的配置文件中添加这些配置，其他的采用基于注解的配置

```xml
<!-- 告知spring在创建容器时要扫描的包 -->
<context:component-scan base-package="com.itheima"></context:component-scan>
<!--配置QueryRunner-->
<bean id="runner" class="org.apache.commons.dbutils.QueryRunner" scope="prototype">
    <!--注入数据源-->
    <constructor-arg name="ds" ref="dataSource"></constructor-arg>
</bean>

<!-- 配置数据源 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <!--连接数据库的必备信息-->
    <property name="driverClass" value="com.mysql.jdbc.Driver"></property>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3306/eesy"></property>
    <property name="user" value="root"></property>
    <property name="password" value="1234"></property>
</bean>
```



### 2.3 Spring纯注解配置(JavaConfig)

> 写到此处，基于注解的 IoC 配置已经完成，但是大家都发现了一个问题：我们依然离不开 spring 的 xml 配 置文件，那么能不能不写这个 xml，所有配置都用注解来实现呢？ 当然，同学们也需要注意一下，我们选择哪种配置的原则是简化开发和配置方便，而非追求某种技术。 

待改造的问题：我们发现，之所以我们现在离不开 xml 配置文件，是因为我们有一句很关键的配置

* 告知Spring框架在读取配置文件创建容器时，**扫描注解**，依据注解**创建对象**，并**存入容器**中
* 另外，**数据源和 JdbcTemplate 的配置**也需要靠注解来实现

------

* **`@Configuration`**

  * 用于**指定当前类是一个Spring配置类**，当创建容器时会从该类上加载注解。获取容器时需要使用 `AnnotationApplicationContext(有@Configuration 注解的类.class)`
  * 属性：`value`用于指定配置类的字节码，一般不用
  * 细节：当**配置类**作为`AnnotationConfigApplicationContext`对象创建的**参数**时，该注解可以不写

* **` @ComponentScan`**

  * 用于指定Spring在**初始化容器**时**要扫描的包**。还有`@ComponentScans`注解
  * 属性：`value`或`basePackages`都是用于指定要扫描的包，都是数组类型

* **`@Bean`**

  * ==用于把当前**方法的返回值**作为bean对象**存入Spring容器**中==。该注解**只能写在方法上**
  * 属性：`name`用于指定**bean的id**。没写这默认为**当前方法的名称**
  * 细节：当我们使用注解配置方法时，如果**方法有参数**，Spring框架会去容器中查找有没有可用的bean对象。查找的方式和`@Autowired`注解的作用是一样的

* **`@Import`**

  * 用于**导入其他的配置类**

  * 属性：`value`用于指定其他配置类的字节码，是数组，其他配置类也不用加`@Configuration注解`

    当我们使用Import的注解之后，有Import注解的类就父配置类，而导入的都是子配置类

* **`@PropertySource`**

  * 用于**指定properties文件的位置**
  * 属性：`value`指定文件的**名称和路径**。关键字：**classpath**，表示类路径下



注意：`@Qualifier`给方法参数注入时可以单独使用

```java
//@Configuration
@ComponentScan("com.itheima")
@Import(JdbcConfig.class)
@PropertySource("classpath:jdbcConfig.properties")
public class SpringConfiguration {

}
```

```java
public class JdbcConfig {

    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    /**
     * 用于创建一个QueryRunner对象
     * @param dataSource
     * @return
     */
    @Bean(name="runner")
    @Scope("prototype")
    public QueryRunner createQueryRunner(@Qualifier("ds2") DataSource dataSource){
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return
     */
    @Bean(name="ds2")
    public DataSource createDataSource(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl(url);
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Bean(name="ds1")
    public DataSource createDataSource1(){
        try {
            ComboPooledDataSource ds = new ComboPooledDataSource();
            ds.setDriverClass(driver);
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/eesy02");
            ds.setUser(username);
            ds.setPassword(password);
            return ds;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
```

```properties
#jdbcConfig.properties
jdbc.driver=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/eesy
jdbc.username=root
jdbc.password=1234
```

```java
ApplicationContext ac = new AnnotationConfigApplicationContext(com.itheima.config.SpringConfiguration.class);
JdbcTemplate jdbcTemplate = ac.getBean("runner", QueryRunner.class);
.....
```



**注解和 XML的选择问题** 

- 注解的优势：  配置简单，维护方便（我们找到类，就相当于找到了对应的配置）
- XML的优势：  修改时，不用改源码。不涉及重新编译和部署

当bean来自第三方，可以使用xml；当bean的实现类由用户自己开发，则使用注解



## 3 Spring整合JUnit

> 在测试类中，每个测试方法都有以下两行代码：  
>
> `ApplicationContext ac = new ClassPathXmlApplicationContext("spring-config.xml");`
>
> `AccountService as = ac.getBean("accountService",IAccountService.class); `
>
> 这两行代码的作用是获取容器，如果不写的话，直接会提示空指针异常，所以又不能轻易删掉

分析：

1. 应用程序的入口：`main`方法

2. JUnit单元测试中，没有`main`方法也能执行是由于JUnit集成了一个运行器。执行带有@Test注解的方法

3. JUnit不会管我们是否采用spring框架，也就不会为我们读取配置文件/配置类创建spring核心容器

   所以当测试方法执行时，没有IoC容器，就算写了@Autowired注解，也无法实现注入

------

解决：JUnit给我们暴露 了一个注解，可以让我们替换掉它的运行器

1. 在pom.xml中添加Spring-test依赖

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-test</artifactId>
       <version>5.1.3.RELEASE</version>
       <!-- <scope>test</scope> -->
   </dependency>
   ```

2. 使用**`@RunWith`**注解**替换原有运行器** 

3. 使用**`@ContextConfiguration`** 指定 spring 配置文件的位置 

   - 属性：
     - `locations`：用于指定**配置文件**的位置。如果是类路径下，需要用 `classpath:`表明 
     - `classes`：用于指定**注解的类**。当不使用 xml 配置时，需要用此属性指定注解类的位置

4. 使用**`@Autowired`**给测试类中的变量注入数据（其他注解也行）

   ```java
   @RunWith(SpringJUnit4ClassRunner.class) 
   //@ContextConfiguration(locations= "classpath:spring-config.xml") 
   @ContextConfiguration(classes = com.itheima.config.SpringConfiguration.class)
   public class AccountServiceTest {
   	@Autowired 
   	private AccountService as ; 
   }
   ```

注意：`SpringJUnit4ClassRunner`要求的JUnit版本在**4.12及以上**





## 4 AOP

AOP(Aspect Oriented Programming)：**面向切面编程**

* 作用：**在程序运行期间，不修改源码对已有方法进行增强**。 
* 优势：
  * 减少重复代码     
  * 提高开发效率     
  * 维护方便
* 实现方式：**动态代理**

------

引入

之前使用`DBUtils`做的增删改查例子中，客户的业务层实现类存在的问题：**事务被自动控制了**。换言之，我们使用了 connection 对象的 `setAutoCommit(true)`  此方式控制事务，如果我们每次都执行一条 sql 语句，没有问题，但是如果业务方法一次要执行多条 sql 语句，这种方式就无法实现功能了。 

日志记录需要给每个方法都添加记录的方法。。。

权限控制需要给每个方法都添加权限校验。。。

……

------

**AOP相关术语**

* Joinpoint(连接点)：所谓连接点是指那些被拦截到的点。在 spring 中这些点指的是方法，因为 spring 只支持方法类型的连接点。 
* Pointcut(**切入点**)：所谓切入点是指我们要对哪些 Joinpoint 进行拦截的定义。即**被增强的连接点**
* Advice(**通知**/增强):   所谓通知是指拦截到 Joinpoint 之后所**要做的事情**就是通知。   
  * 通知的类型：前置通知、后置通知、异常通知、最终通知、环绕通知。 根据`method.invoke`之前、之后、catch中、finally中，以及实现类重写的`invoke`方法在执行（环绕通知中有明确的切入点方法调用）
* Introduction(引介):   引介是一种特殊的通知在不修改类代码的前提下, Introduction 可以在运行期为类动态地添加一些方法或 Field
* Target(目标对象):   代理的目标对象
* Weaving(织入):   是指把增强应用到目标对象来创建新的代理对象的**过程**。spring 采用动态代理织入，而 AspectJ 采用编译期织入和类装载期织入。 
* Proxy（代理）：一个类被 AOP 织入增强后，就产生一个结果代理类
* Aspect(**切面**):   是**切入点和通知（引介）的结合**。 

------

**学习AOP要明确的事**

* 开发阶段（我们做的）
  * 编写核心业务代码（开发主线）：大部分程序员来做，要求熟悉业务需求。  
  * 把公用代码抽取出来，制作成通知。（开发阶段最后再做）：AOP 编程人员来做。  
  * 在配置文件中，声明切入点与通知间的关系，即切面。：AOP 编程人员来做
* 运行阶段（Spring框架完成的）
  * Spring 框架监控切入点方法的执行。一旦监控到切入点方法被运行，使用代理机制，动态创建目标对象的代理对象，根据通知类别，在代理对象的对应位置，将通知对应的功能织入，完成完整的代码逻辑运行。 



### 4.1 基于xml的AOP配置

1. 在pom.xml中添加依赖

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.1.1.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.aspectj</groupId>
       <artifactId>aspectjweaver</artifactId>
       <version>1.9.1</version>
   </dependency>
   ```

2. 在spirng-config.xml中添加AOP约束。在指南中搜索`xmlns:aop`即可

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
           http://www.springframework.org/schema/beans/spring-beans.xsd
           http://www.springframework.org/schema/aop
           http://www.springframework.org/schema/aop/spring-aop.xsd">
   </beans>
   ```

3. 配置 spring 的 ioc 

4. **==抽取公共代码制作成通知==** 

5. spring中基于XML的**==AOP配置==**步骤

   1. 把**通知Bean也交给spring来管理**

   2. 使用`aop:config`标签表明开始**AOP的配置**

   3. 使用`aop:aspect`标签表明配置**切面**

      - `id`属性：是给切面提供一个唯一标识
      - `ref`属性：是指定通知类bean的Id

   4. 在`aop:aspect`标签的内部使用对应标签来配置**通知的类型**。示例是让printLog方法在切入点方法执行之前之前

      - `aop:before`：表示配置前置通知。其他通知类似
        - `method`属性：用于指定Logger类中**哪个方法是前置通知**
        - `pointcut`属性：用于指定切入点表达式，该表达式的含义指的是对业务层中**哪些方法增强**
        - `ponitcut-ref`属性：用于指定切入点的表达式的引用 

   5. **切入点表达式**的写法：关键字：`execution(表达式)`

      * 表达式`访问修饰符  返回值  包名.包名.包名...类名.方法名(参数列表)`

      * 标准写法`public void com.itheima.service.impl.AccountServiceImpl.saveAccount()`

      * 访问修饰符可以省略`void com.itheima.service.impl.AccountServiceImpl.saveAccount()`

      * 返回值可以使用通配符，表示任意返回值

        `* com.itheima.service.impl.AccountServiceImpl.saveAccount()`

      * 包名可以使用通配符，表示任意包。但是有几级包，就需要写几个`*.`

        `* *.*.*.*.AccountServiceImpl.saveAccount()`

      * 包名可以使用`*..`表示当前包及其子包
        `* *..AccountServiceImpl.saveAccount()`

      * 类名和方法名都可以使用`*`来实现通配

        `* *..*.*()`

      * 参数列表：

        * 可以直接写数据类型：
          * 基本类型直接写名称           int
          * 引用类型写包名.类名的方式   java.lang.String
        * 可以使用通配符表示任意类型，但是必须有参数
        * 可以使用..表示有无参数均可，有参数可以是任意类型

      * 全通配写法：`* *..*.*(..)`

      * **实际开发中切入点表达式的通常写法**：**切到业务层实现类下的所有方法**

        `* com.itheima.service.impl.*.*(..)`

      ```xml
      <!-- 配置Logger类 -->
      <bean id="logger" class="com.itheima.utils.Logger"></bean>
      
      <!--配置AOP-->
      <aop:config>
          <!-- 配置切入点表达式 id属性用于指定表达式的唯一标识。expression属性用于指定表达式内容
                    此标签写在aop:aspect标签内部只能当前切面使用。
                    它还可以写在aop:aspect外面，此时就变成了所有切面的通知可可使用此切点  -->
          <aop:pointcut id="pt1" expression="execution(* com.itheima.service.impl.*.*(..))"></aop:pointcut>
          
          <!--配置切面 -->
          <aop:aspect id="logAdvice" ref="logger">  <!--引入通知-->
              <!-- 配置前置通知：在切入点方法执行之前执行
              <aop:before method="beforePrintLog" pointcut-ref="pt1" ></aop:before>-->
      
              <!-- 配置后置通知：在切入点方法正常执行之后值。它和异常通知永远只能执行一个
              <aop:after-returning method="afterReturningPrintLog" pointcut-ref="pt1"></aop:after-returning>-->
      
              <!-- 配置异常通知：在切入点方法执行产生异常之后执行。它和后置通知永远只能执行一个
              <aop:after-throwing method="afterThrowingPrintLog" pointcut-ref="pt1"></aop:after-throwing>-->
      
              <!-- 配置最终通知：无论切入点方法是否正常执行它都会在其后面执行
              <aop:after method="afterPrintLog" pointcut-ref="pt1"></aop:after>-->
      
              <!-- 配置环绕通知 详细的注释请看Logger类中。通常情况下，环绕通知都是独立使用的 -->
              <aop:around method="aroundPringLog" pointcut-ref="pt1"></aop:around>
          </aop:aspect>
      </aop:config>
      ```

      ```java
      /**
       * 用于记录日志的工具类，它里面提供了公共的代码
       */
      public class Logger {
      
      	//前置通知
          public  void beforePrintLog(){
              System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
          }
      	//后置通知
          public  void afterReturningPrintLog(){
              System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了。。。");
          }
          //异常通知
          public  void afterThrowingPrintLog(){
              System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
          }
          //最终通知
          public  void afterPrintLog(){
              System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
          }
      
          /**
           * 环绕通知
           * 问题：
           *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
           * 分析：
           *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
           * 解决：
           *      Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
           *      该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
           *
           * spring中的环绕通知：
           *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
           */
          public Object aroundPringLog(ProceedingJoinPoint pjp){
              Object rtValue = null;
              try{
                  Object[] args = pjp.getArgs();//得到方法执行所需的参数
      
                  System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");
      
                  rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）
      
                  System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");
      
                  return rtValue;
              }catch (Throwable t){
                  System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");
                  throw new RuntimeException(t);
              }finally {
                  System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。最终");
              }
          }
      }
      ```

------

要想使用**注解的方式**使用Spring AOP就**必须要有源码**(因为我们要在切面类上添加注解)。如果没有源码的话，我们就得使用XML来声明切面了

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





### 4.2 基于注解的AOP配置

在spring-config.xml**开启对注解的支持**，并添加AOP依赖，告知Spring**创建容器时扫描的包**，开启注解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                           http://www.springframework.org/schema/beans/spring-beans.xsd
                           http://www.springframework.org/schema/aop
                           http://www.springframework.org/schema/aop/spring-aop.xsd
                           http://www.springframework.org/schema/context
                           http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置spring创建容器时要扫描的包-->
    <context:component-scan base-package="com.itheima"></context:component-scan>

    <!-- 配置spring开启注解AOP的支持 -->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>
```

添加Component等注解

配置切面（切点、通知）

```java
/**
 * 用于记录日志的工具类，它里面提供了公共的代码
 */
@Component("logger")
@Aspect//表示当前类是一个切面类
public class Logger {

    @Pointcut("execution(* com.itheima.service.impl.*.*(..))")
    private void pt1(){}

    //前置通知
    //@Before("pt1()")
    public  void beforePrintLog(){
        System.out.println("前置通知Logger类中的beforePrintLog方法开始记录日志了。。。");
    }
    //后置通知
    //@AfterReturning("pt1()")
    public  void afterReturningPrintLog(){
        System.out.println("后置通知Logger类中的afterReturningPrintLog方法开始记录日志了。。。");
    }
    //异常通知
    //@AfterThrowing("pt1()")
    public  void afterThrowingPrintLog(){
        System.out.println("异常通知Logger类中的afterThrowingPrintLog方法开始记录日志了。。。");
    }
    //最终通知
    //@After("pt1()")
    public  void afterPrintLog(){
        System.out.println("最终通知Logger类中的afterPrintLog方法开始记录日志了。。。");
    }

    /**
     * 环绕通知
     * 问题：
     *      当我们配置了环绕通知之后，切入点方法没有执行，而通知方法执行了。
     * 分析：
     *      通过对比动态代理中的环绕通知代码，发现动态代理的环绕通知有明确的切入点方法调用，而我们的代码中没有。
     * 解决：
     *  Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *  该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     * spring中的环绕通知：
     *      它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     */
    @Around("pt1()")
    public Object aroundPringLog(ProceedingJoinPoint pjp){
        Object rtValue = null;
        try{
            Object[] args = pjp.getArgs();//得到方法执行所需的参数

            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。前置");

            rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）

            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。后置");

            return rtValue;
        }catch (Throwable t){ //只能catch Throwable 
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。异常");
            throw new RuntimeException(t);
        }finally {
            System.out.println("Logger类中的aroundPringLog方法开始记录日志了。。。最终");
        }
    }
}
```

注意：基于配置的AOP，最终通知会在后置通知或异常通知之前执行。环绕通知没有此问题，推荐使用

### 4.3 纯注解配置(JavaConfig)

spring-config.xml文件替换为如下配置类

```java
@Configuration 
@ComponentScan(basePackages="com.itheima") 
@EnableAspectJAutoProxy 
public class SpringConfiguration {
}
```



## 5 Spring的JdbcTemplate

* pom.xml中导入依赖

  ```xml
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.1.1.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-tx</artifactId>
      <version>5.1.1.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-jdbc</artifactId>
      <version>5.1.1.RELEASE</version>
  </dependency>
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.12</version>
  </dependency>
  ```

* 配置类

  ```java
  @Configuration
  @ComponentScan("com.itheima")
  public class SpringConfiguration {
  
      @Bean(name = "jdbcTemplate")
      public JdbcTemplate jdbcTemplate(DataSource dataSource){
          return new JdbcTemplate(dataSource);
      }
  
      @Bean
      public DataSource dataSource(){
          DriverManagerDataSource dataSource = new DriverManagerDataSource();//使用spring提供的DataSource
          dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
          dataSource.setUrl("jdbc:mysql:///bxgaccount?serverTimezone=GMT%2B8");
          dataSource.setUsername("root");
          dataSource.setPassword("w111151");
          return dataSource;
      }
  }
  ```

* **调用JdbcTemplate的方法**来完成CRUD的操作（查找语句若未找到，直接抛异常，不是返回null，所以要**try...catch...**）

  - `update()`：执行**DML**语句。增、删、改语句
  - `query()`：查询并将结果封装为==**JavaBean对象的List集合**==。JavaBean属性使用**包装类型**最好，可以存储null。
    - query的参数：RowMapper函数式接口
      - 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
      - **`new BeanPropertyRowMapper<>(类型.class)`**（不用在尖括号中添加泛型）
  - `queryForObject()`：查询并将结果封装为==**对象**==。
    - 参数同`query()`的参数可封装为==**JavaBean对象**==；参数传**包装类型**，可用于聚合函数的查询。
  - `queryForMap()`：查询并将结果的列名作为key(String)、值作为value(Object)封装为一个==**Map**==集合
    - 注意：这个方法查询的结果集长度只能是1
  - `queryForList()`：查询并将结果集封装为==**List**==集合
    - 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中

* Dao层

  ```java
  @Repository("accountDao")
  public class AccountDaoImpl implements AccountDao {
      @Autowired
      private JdbcTemplate jdbcTemplate;
  
      @Override
      public void addAccount(Account a) {
          jdbcTemplate.update("insert into account (name,money) values(?,?)", a.getName(), a.getMoney());
      }
  }
  ```

* JUnit测试（其他方法可以查看数据库中笔记）

  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration(classes = com.itheima.config.SpringConfiguration.class)
  public class JdbcTemplateTest {
  
      @Autowired
      AccountDao accountDao;
  
      @Test
      public void test(){
          Account account = new Account("xxx",888);
          accountDao.addAccount(account);
      }
  }
  ```

* 当有多个Dao时，依赖注入会重复写在多个Dao中。此时Spring提供了**`JdbcDaoSupport`类**，可以直接**继承**。配置中也**只需要配置DataSource即可**，这个类会**自动根据DataSource创建JdbcTemplate**。由于JdbcTemplate是私有的，所以可以使用`getJdbcTemplate()`方法来获取。但是**只能xml配置时使用**，注解时由于JdbcTemplate上不能添加注入，非常麻烦。

  ```java
  public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao { }
  ```





## 6 Spring中事务控制

### 6.1 明确要点

* JavaEE 体系进行分层开发，**==事务处理位于业务层==**，Spring 提供了分层设计业务层的事务处理解决方案。 
* Spring 框架为我们提供了一组事务控制的**接口**，在 `spring-tx-5.*.*.RELEASE.jar` 中。具体在后面的第二小节介绍。
* Spring的事务控制都是基于 AOP 的，它既可以使用编程的方式实现，也可以使用**配置的方式实现**（重点）。

### 6.2  Spring中事务控制的 API介绍 

PlatformTransactionManager：此接口是 spring 的事务管理器，它里面提供了我们常用的操作事务的方法，如下：

* 获取事务状态信息：`TransactionStatus getTransaction(TransactionDefinition definition)`
* 提交事务：`void commit(TransactionStatus status)`
* 回滚事务：`void rollback(TransactionStatus status)`

我们在开发中都是使用它的实现类（**真正管理事务的对象** ）：

* **`org.springframework.jdbc.datasource.DataSourceTransactionManager`**：**Spring JDBC或iBatis持久化数据使用**
* `org.springframework.orm.hibernate5.HibernateTransactionManager`：用 Hibernate 版本进行持久化数据使用

------

TransactionDefinition：是事务的定义信息对象，里面有如下方法：

* 获取事务对象名称：`String getName()`
* 获取事务隔离级别：`int getIsolationLevel()`，采用数据库的默认级别
* 获取事务**传播行为**：`int getPropagationBehavior()`
  * **REQUIRED**：如果当前没有事务就新建一个事务，如果已经存在一个事务中，加入到这个事务中。一般的选择(默认值)
  * **SUPPORTS**：支持当前事务，如果当前没有事务，就以非事务方式执行(没有事务) 
  * MANDATORY：使用当前的事务，如果当前没有事务，就抛出异常
  * REQUERS_NEW:新建事务，如果当前在事务中，把当前事务挂起
  * NOT_SUPPORTED:以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
  * NEVER:以非事务方式运行，如果当前存在事务，抛出异常
  * NESTED:如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行 REQUIRED 类似的操作
* 获取事务超时时间：`int getTimeout()`，默认值是-1，没有超时限制。如果有，以秒为单位进行设置
* 获取事务**是否只读**：`boolean isReadOnly()`，建议查询时设置为只读。 

------

TransactionStatus：此接口提供的是**事务具体的运行状态**，方法介绍如下：

* 刷新事务：`void flush()`
* 获取是否存在存储点：`boolean hasSavepoint()`
* 获取事务是否完成：`boolean isCompleted()`
* 获取事务是否为新的事务：`boolean isNewTransaction()`
* 获取事务是否回滚：`boolean isRollbackOnly()`
* 设置事务回滚：`void setRollbackOnly()`



### 6.3 基于xml的声明式事务控制

1. 在pom.xml中添加依赖坐标

   ```xml
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-context</artifactId>
       <version>5.1.1.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-tx</artifactId>
       <version>5.1.1.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>org.aspectj</groupId>
       <artifactId>aspectjweaver</artifactId>
       <version>1.9.1</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-jdbc</artifactId>
       <version>5.1.1.RELEASE</version>
   </dependency>
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
       <version>8.0.12</version>
   </dependency>
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.12</version>
   </dependency>
   <dependency>
       <groupId>org.springframework</groupId>
       <artifactId>spring-test</artifactId>
       <version>5.1.1.RELEASE</version>
   ```

2. spring中基于XML的声明式事务控制配置步骤

   1. 配置**事务管理器**
   2. 配置**事务的通知**引用事务管理器 
      此时我们需要导入事务的约束 tx名称空间和约束，同时也需要aop的
      使用`tx:advice`标签配置事务通知
      属性：
      ​        `id`：给事务通知起一个唯一标识
      ​        `transaction-manager`：给事务通知提供一个事务管理器引用
   3. 配置**事务的属性**（在上述`tx:advice`中）
   4. 配置AOP中的通用**切入点**表达式
   5. 建立事务**通知和切入点表达式的对应关系**

   ```xml
   <!-- 配置事务管理器 -->
   <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <property name="dataSource" ref="dataSource"></property>
   </bean>
   
   <!-- 配置事务的通知-->
   <tx:advice id="txAdvice" transaction-manager="transactionManager">
       <!-- 配置事务的属性
         isolation：用于指定事务的隔离级别。默认值是DEFAULT，表示使用数据库的默认隔离级别。
         propagation：用于指定事务的传播行为。默认值是REQUIRED，表示一定会有事务，增删改的选择。查询方法可以选择SUPPORTS。
         read-only：用于指定事务是否只读。只有查询方法才能设置为true。默认值是false，表示读写。
         timeout：用于指定事务的超时时间，默认值是-1，表示永不超时。如果指定了数值，以秒为单位。
     rollback-for：用于指定一个异常，当产生该异常时，事务回滚，产生其他异常时，事务不回滚。没有默认值。表示任何异常都回滚。
    no-rollback-for：用于指定一个异常，当产生该异常时，事务不回滚，产生其他异常时事务回滚。没有默认值。表示任何异常都回滚。
       -->
       <tx:attributes>
           <tx:method name="*" propagation="REQUIRED" read-only="false"/>
           <tx:method name="find*" propagation="SUPPORTS" read-only="true"></tx:method>
       </tx:attributes>
   </tx:advice>
   
   <!-- 配置aop-->
   <aop:config>
       <!-- 配置切入点表达式-->
       <aop:pointcut id="pt1" expression="execution(* com.itheima.service.impl.*.*(..))"></aop:pointcut>
       <!--建立切入点表达式和事务通知的对应关系 -->
       <aop:advisor advice-ref="txAdvice" pointcut-ref="pt1"></aop:advisor>
   </aop:config>
   ```



### 6.4 基于注解的声明式事务控制

> aop约束，注解约束、事务约束。配置spring创建容器时要扫描的包。配置JdbcTemplate、配置数据源

1. 在pom.xml中添加依赖坐标（同xml）

2. spring中基于注解 的声明式事务控制配置步骤

   1. 配置事务管理器
   2. 开启spring对注解事务的支持
   3. 在需要事务支持的地方使用@Transactional注解

   ```xml
   <!-- 配置事务管理器 -->
   <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
       <property name="dataSource" ref="dataSource"></property>
   </bean>
   
   <!-- 开启spring对注解事务的支持-->
   <tx:annotation-driven transaction-manager="transactionManager"></tx:annotation-driven>
   ```

   ```java
   /**
    * 账户的业务层实现类
    * 事务控制应该都是在业务层
    */
   @Service("accountService")
   @Transactional(propagation= Propagation.SUPPORTS,readOnly=true)//只读型事务的配置
   public class AccountServiceImpl implements IAccountService{
   
       @Autowired
       private IAccountDao accountDao;
   
       @Override
       public Account findAccountById(Integer accountId) {
           return accountDao.findAccountById(accountId);
   
       }
       
       @Transactional(propagation= Propagation.REQUIRED,readOnly=false)//需要的是读写型事务配置
       @Override
       public void transfer(String sourceName, String targetName, Float money) {
           System.out.println("transfer....");
           //2.1根据名称查询转出账户
           Account source = accountDao.findAccountByName(sourceName);
           //2.2根据名称查询转入账户
           Account target = accountDao.findAccountByName(targetName);
           //2.3转出账户减钱
           source.setMoney(source.getMoney()-money);
           //2.4转入账户加钱
           target.setMoney(target.getMoney()+money);
           //2.5更新转出账户
           accountDao.updateAccount(source);
   
           int i=1/0;
           //2.6更新转入账户
           accountDao.updateAccount(target);
       }
   }
   ```

   注意：比xml配置麻烦的一处在于对Service中多个方法的事务注解，除了类上的只读事务配置，其他读写事务都得一个个配



### 6.5 纯注解事务配置(JavaConfig)

```java
@Configuration
@ComponentScan("com.itheima")
@Import({JdbcConfig.class,TransactionConfig.class})
@PropertySource("classpath:jdbcConfig.properties")
@EnableTransactionManagement
public class SpringConfiguration {

}
```

```java
//连接数据库相关的配置类
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();//使用spring提供的DataSource
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
```

```java
//和事务相关的配置类
public class TransactionConfig {
    //创建事务管理器对象
    @Bean
    public PlatformTransactionManager transactionManager (DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
```

```properties
#jdbcConfig.properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql:///bxgaccount?serverTimezone=GMT%2B8
jdbc.username=root
jdbc.password=w111151
```

AccountServiceImpl和6.4中一致







# 第四部分 SpringMVC

> 三层架构
>
> * 表现层（web）：接收客户端请求，向客户端响应结果。包括控制层(接收请求)、展示层(展示结果)，采用MVC模型 
> * 业务逻辑层（service）：处理具体的业务逻辑，包括事务
> * 持久层（dao）：数据持久化，即和数据库交互，对数据库表进行增删改查 
>
> MVC模型（Model View Controller），即模型视图控制器，每个部分各司其职。 
>
> * Model：数据模型，JavaBean的类，用来进行数据封装
> * View：指jsp或者html。作用一般就是展示数据的。通常视图是依据模型数据创建的
> * Controller：是应用程序中处理用户交互的部分，作用一般就是处理程序逻辑的，整个流程的控制器。可以数据校验等

SpringMVC概述

* 是一种基于**Java**实现的MVC设计模型的**请求驱动**类型的**轻量级WEB框架**。 
* Spring MVC属于SpringFrameWork的后续产品，已经**融合在Spring Web Flow**里面。Spring 框架提供 了构建 Web 应用程序的全功能 MVC 模块。 
* 使用 Spring **可插入的 MVC 架构**，从而在使用Spring进行WEB开发时，可以选择使用Spring的 SpringMVC框架或集成其他MVC开发框架，如Struts1(现在一般不用)，Struts2等。 

SpringMVC 和 Struts2 的优劣分析

* 共同点：  
  * 它们都是表现层框架，都是基于 MVC 模型编写的。  
  * 它们的底层都离不开原始 ServletAPI。  
  * 它们处理请求的机制都是一个核心控制器。 
* 区别：
  * Spring MVC 的入口是 Servlet, 而 Struts2 是 Filter 
  * Spring MVC 是基于方法设计的，而 Struts2 是基于类，Struts2 每次执行都会创建一个动作类，比Spring MVC会稍慢
  * Spring MVC 使用更加简洁,同时还支持 JSR303, 处理 ajax 的请求更方便 
  * Struts2 的 OGNL 表达式使页面的开发效率相比 Spring MVC 更高些，但执行效率并没有比 JSTL 提 升，尤其是 struts2 的表单标签，远没有 html 执行效率高



## 1 入门

### 1.1 入门程序

* pom.xml中添加依赖的坐标

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>5.1.1.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-web</artifactId>
        <version>5.1.1.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-webmvc</artifactId>
        <version>5.1.1.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>servlet-api</artifactId>
        <version>2.5</version>
        <scope>provided</scope>
    </dependency>

    <dependency>
        <groupId>javax.servlet.jsp</groupId>
        <artifactId>jsp-api</artifactId>
        <version>2.0</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```

- **`web.xml`**配置**前端控制器`DispatcherServlet`**，拦截请求到Controller层（自己编码）

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    <!-- 配置SpringMVC前端控制器 -->
    <servlet>
        <servlet-name>dispatcher</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <!-- 配置Servlet的初始化参数，读取spring的xml配置文件或JavaCOnfig，创建spring容器 -->
        <!-- 可以不配置，那就默认找/WEB-INF/[servlet的名称]-servlet.xml -->
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath:spring-config.xml</param-value>
        </init-param>
        
        <!--可以配置JavaConfig配置类替代上面的配置-->
        <init-param>
            <param-name>contextClass</param-name>
           <param-value>org.springframework.web.context.support.AnnotationConfigWebApplicationContext</param-value>
        </init-param>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>cn.itcast.config.SpringConfiguration</param-value>
        </init-param>
        
        <!-- 配置servlet启动时加载对象，参数必须为整数
		    1、为0或者大于0，则springMVC随着容器启动而启动；（越小权限越大）
             2、小于0，则在第一次请求进来的时候启动 -->
        <load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
        <servlet-name>dispatcher</servlet-name>
        <!-- 1. /* 拦截所有 jsp js png .css 建议不使用 
             2. *.action *.do 拦截以do action 结尾的请求 肯定能使用 ERP 
             3. / 拦截所有（只不包括jsp) 强烈建议使用 前台 面向消费者 需要配置对静态资源放行 -->
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    
    
    <!-- 以下入门不使用 -->
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
        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
        <init-param>
            <param-name>encoding</param-name>
            <param-value>UTF-8</param-value>
        </init-param>
        <!-- 启动过滤器，好像不配置这个也行 -->    
        <init-param>     
            <param-name>forceEncoding</param-name>   
            <param-value>true</param-value>    
        </init-param> 
    </filter>
    <filter-mapping>
        <filter-name>encoding</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    
    <!-- 静态资源放行。location 表示路径，mapping 表示文件，**表示该目录下的文件以及子目录的文件 --> 
    <mvc:resources location="/css/" mapping="/css/**"/>   
    <mvc:resources location="/images/" mapping="/images/**"/>   
    <mvc:resources location="/scripts/" mapping="/javascript/**"/> 
</web-app>
```

* **`spring-config.xml`**或**`SpringConfiguration`**配置类。开启扫描**@Controller、@Service**等

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans    http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 配置spring创建容器时要扫描的包 -->
    <context:component-scan base-package="cn.itcast"/>
    
    <!-- 处理器映射器 从spring3.1版本开始默认的废除了，所以配置新的-->
    <!-- <bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping"/> -->
    <!-- 处理器适配器 从spring3.1版本开始默认的废除了，所以配置新的-->
    <!--<bean class="org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter"/> -->
    
    <!-- 配置spring开启注解mvc的支持  替代上两个-->
    <mvc:annotation-driven/>
    
    <!-- 配置视图解释器 省略前后缀-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/WEB-INF/jsp/" />
        <property name="suffix" value=".jsp" />
    </bean>
</beans>
```

```java
@Configuration
@ComponentScan("cn.itcast")
@EnableWebMvc
public class SpringConfiguration {

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}
```

* **Controller层**

```java
@Controller
public class HelloController {

    @RequestMapping("/hello")
    public String sayHello(){
        System.out.println("HelloController 的 sayHello 方法执行了。。。。");
        return "success";
    }
}
```



### 1.2 执行过程分析

1. 当启动Tomcat服务器的时候，因为配置了`load-on-startup`标签，所以会创建`DispatcherServlet`对象， 并分局初始化参数加载`spring-config.xml`配置文件或`SpringConfiguration`配置类 
2. 由于开启了注解扫描，那么`HelloController`对象就会被创建
3. 从index.jsp发送请求，请求会先到达`DispatcherServlet`前端控制器，根据配置`@RequestMapping`注解找到执行的方法
4. 根据执行方法的返回值，再根据配置的视图解析器，去指定的目录下查找指定名称的JSP文件 
5. Tomcat服务器渲染页面，做出响应 



### 1.3 入门案例中组件分析

在 SpringMVC 的各个组件中，**处理器映射器**、**处理器适配器**、**视图解析器**称为 SpringMVC 的三大组件。 

我们只需要编写处理具体业务的**控制器**以及**视图**

------

* DispatcherServlet

前端控制器：用户请求到达前端控制器，它就相当于 mvc 模式中的 c，dispatcherServlet 是整个流程控制的中心，由 它调用其它组件处理用户的请求，dispatcherServlet 的存在降低了组件之间的耦合性

* HandlerMapping

处理器映射器：HandlerMapping 负责根据用户请求找到 Handler 即处理器，SpringMVC 提供了不同的映射器实现不同的 映射方式，例如：配置文件方式，实现接口方式，注解方式等。 

* Handler

它就是我们开发中要编写的具体业务控制器。由 DispatcherServlet 把用户请求转发到 Handler。由 Handler 对具体的用户请求进行处理。

* HandlAdapter

通过 HandlerAdapter 对处理器进行执行，这是**适配器模式**的应用，通过扩展适配器可以对更多类型的处理器进行执行

* View Resolver

View Resolver 负责将处理结果生成 View 视图，View Resolver 首先根据逻辑视图名解析成物理视图名 即具体的页面地址，再生成 View 视图对象，最后对 View 进行渲染将处理结果通过页面展示给用户

* View

SpringMVC 框架提供了很多的 View 视图类型的支持，包括：jstlView、freemarkerView、pdfView 等。最常用的视图就是jsp

一般情况下需要通过页面标签或页面模版技术将模型数据通过页面展示给用户，需要由程序员根据业务需求开发具体的页面



## 2 @RequestMapping

作用：用于**建立请求 URL** 和**处理请求方法**之间的对应**关系**

```java
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {
    String name() default "";

    @AliasFor("path")
    String[] value() default {};
    @AliasFor("value")
    String[] path() default {};

    RequestMethod[] method() default {};
    String[] params() default {};
    String[] headers() default {};
    String[] consumes() default {};
    String[] produces() default {};
}
```

出现位置： 

* **类**上：请求URL的**第一级访问目录**，模块化管理。此处不写的话，就相当于应用的根目录。写的话需要以`/`开头，如`/user`

* **方法**上：请求 URL 的**第二级访问目录**。 如`/add`

  当使用分级配置时，前端页面的请求路径中，要么写绝对路径即带`/`和项目名，要么写**相对路径不带`/`**（表示相对应用的根目录），否则404

属性：

* **`value`**：用于指定**请求的 URL**。它和 **path** 属性的作用是一样的。 
* **`method`**：用于指定**请求的方式**，值为`RequestMethod`枚举类
* `params`：用于指定限制请求参数的条件。它支持简单的表达式。要求请求参数的key和value必须和配置的一模一样。如：
  * `params = {"accountName"}`，表示请求参数必须有 accountName 
  * `params = {"moeny!100"}`，表示请求参数中 money 不能是 100
* `headers`：用于限定发送的请求中必须包含某请求头



## 2 请求参数的绑定

绑定机制 

1. 表单提交的数据都是k=v格式的 `username=haha&password=123`
2. SpringMVC的参数绑定过程是把**表单提交的请求参数**，作为控制器中**方法的参数**进行绑定的 

------

> **默认支持的参数类型**
>
> - Model/ModelMap
>
>   - 除了ModelAndView以外，还可以使用Model(接口)来向页面传递数据，在参数里直接声明model即可
>
>     使用Model则可以不使用ModelAndView对象，Model对象可以向页面传递数据，View对象则可以使用String返回值替代。其本质都是使用Request对象向jsp传递数据
>
>     ```java
>     model.addAttribute("item", item);
>     return "itemEdit";
>     ```
>
>   - ModelMap是Model接口的实现类，也可以通过ModelMap向页面传递数据。效果一样，同上代码



### 2.1 基本数据类型和字符串类型

==当**请求的参数名称**和**处理器形参名称**一致时（**区分大小写**）会将请求参数与形参进行绑定==

注意：参数类型**推荐**使用**包装数据类型**，因为基础数据类型不可为null；布尔类型的参数，请求的参数值为true或false，1或0

```java
@RequestMapping("/edit")
public String queryItemById(Integer id) {
    System.out.println(id);
    return "success";
}
```

------

> @**RequestParam**常用于处理简单类型的绑定，但是还不如参数名称一致好使
>
> ```java
> @RequestMapping("/itemEdit")
> //value：参数名字，即入参的请求参数名字
> public String queryItemById(@RequestParam(value = "itemId", required = true, defaultValue = "1") Integer id,
>                             ModelMap modelMap) {
>     // 根据id查询商品数据
>     Item item = this.itemService.queryItemById(id);
>     // 把商品数据放在模型中
>     modelMap.addAttribute("item", item);
>     return "itemEdit";
> }
> ```



### 2.2 POJO类型及它的关联对象

要求表单中**参数名称**和 POJO 类的**属性名称**保持一致。并且控制器方法的**参数类型是 POJO 类型**

```java
public class Account implements Serializable { 
    private Integer id;  
    private String name;  
    private Float money;  
    private Address address;  
    //getters and setters 
} 
```

```java
public class Address implements Serializable {    
    private String provinceName;  
    private String cityName;  
    //getters and setters 
}
```

```html
<form action="account/saveAccount" method="post"> 
    账户名称：<input type="text" name="name" ><br/> 
    账户金额：<input type="text" name="money" ><br/>  
    账户省份：<input type="text" name="address.provinceName" ><br/>  
    账户城市：<input type="text" name="address.cityName" ><br/>  
    <input type="submit" value=" 保存 "> 
</form>
```

```java
@RequestMapping("/saveAccount") 
public String saveAccount(Account account) {  
    System.out.println("保存了账户。。。。"+account);  
    return "success"; 
}
```

#### 2.2.1 POST请求参数乱码问题

`web.xml`中配置过滤器。Tomcat8及之后的已经解决GET请求参数乱码问题

```xml
<!-- 配置过滤器，解决post的乱码问题 -->
<filter>
    <filter-name>encoding</filter-name>
    <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
    <init-param>
        <param-name>encoding</param-name>
        <param-value>UTF-8</param-value>
    </init-param>
    <!-- 启动过滤器，好像不配置这个也行 -->    
    <init-param>     
        <param-name>forceEncoding</param-name>   
        <param-value>true</param-value>    
    </init-param>
</filter>
<filter-mapping>
    <filter-name>encoding</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```



### 2.3 POJO 类中包含集合类型参数

第一种：（如下面代码）

* 要求集合类型的请求参数必须在 **POJO** 中。在表单中**请求参数名称**要和 POJO 中集合**属性名称相同**。
* 给 List 集合中的元素赋值，使用下标。 
* 给 Map 集合中的元素赋值，使用键值对。 

第二种

* 接收的请求参数是 json 格式数据。需要借助一个注解实现。 

```java
public class User implements Serializable {    
    private String username;  
    private String password;  
    private Integer age;  
    private List<Account> accountList;  
    private Map<String,Account> accountMap;    
    //getters and setters
}
```

```html
<form action="account/updateAccount" method="post">
    用户名称：<input type="text" name="username"><br/>
    用户密码：<input type="password" name="password"><br/>
    用户年龄：<input type="text" name="age"><br/>
    账户 1 名称：<input type="text" name="accountList[0].name"><br/>
    账户 1 金额：<input type="text" name="accountList[0].money"><br/>
    账户 2 名称：<input type="text" name="accountList[1].name"><br/>
    账户 2 金额：<input type="text" name="accountList[1].money"><br/>
    账户 3 名称：<input type="text" name="accountMap['one'].name"><br/>
    账户 3 金额：<input type="text" name="accountMap['one'].money"><br/>
    账户 4 名称：<input type="text" name="accountMap['two'].name"><br/>
    账户 4 金额：<input type="text" name="accountMap['two'].money"><br/>
    <input type="submit" value=" 保存 ">
</form>
```

```java
@RequestMapping("/updateAccount") 
public String updateAccount(User user) {  
    System.out.println("更新了账户。。。。"+user);  
    return "success"; 
}
```



### 2.4 自定义类型转换器

SpringMVC还可以实现一些**数据类型自动转换**。内置转换器全都在`org.springframework.core.convert.support`包下。如String转Integer等等

如遇**特殊类型转换**要求，比如日期数据有很多种格式，SpringMVC没办法把带`-`字符串转换成日期类型，需要我们自己编写**自定义类型转换器**。步骤如下：

1. 定义一个类，**实现 Spring提供的 `Converter` 接口**，该接口有两个泛型。

   ```java
   //Converter<S, T>：  S:source,需要转换的源的类型；T:target,需要转换的目标类型
   @Componet
   public class StringToDateConverter implements Converter<String, Date> {
       @Override
       public Date convert(String source) {
           DateFormat format = null;
           try {
               if (StringUtils.isEmpty(source)) {
                   throw new NullPointerException("请输入要转换的日期");
               }
               format = new SimpleDateFormat("yyyy-MM-dd");
               Date date = format.parse(source);
               return date;
           } catch (Exception e) {
               throw new RuntimeException("输入日期有误");
           }
       }
   }
   ```

2. 在 spring配置文件中**配置类型转换器**。

   spring 配置类型转换器的机制是，将自定义的转换器注册到类型转换服务中去。 

   ```xml
   <!-- 配置类型转换器工厂 --> 
   <bean id="converterService"   class="org.springframework.context.support.ConversionServiceFactoryBean"> 
   <!--FormattingConversionServiceFactoryBean可以让SpringMVC支持和@DateTimeFormat等Spring内部自定义的转换器，建议-->
    <!-- 给工厂注入一个新的类型转换器 -->      
       <property name="converters"> 
         <array> 
          <!-- 配置自定义类型转换器 -->       
             <ref bean="stringToDateConverter"/>
         </array>      
       </property> 
   </bean>
   <!-- 配置spring开启注解mvc的支持，替代处理器映射器和处理器适配器配置，并配置类型转换器 -->
   <mvc:annotation-driven conversion-service="conversionService" />
   ```



### 2.5 获取Servlet原生API

**控制器**(处理器)**形参**中添加如下类型的参数，处理适配器会默认识别并进行赋值

- `HttpServletRequest`：通过request对象获取请求信息
- `HttpServletResponse`：通过response处理响应信息
- `HttpSession`：通过session对象得到session中存放的对象









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

    * @**AutoConfigurationPackage**：**自动配置包**，**如下注解的简写**

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



### 2.4 配置文件占位符

* 随机数

  ```yaml
  ${random.value}
  ${random.int}
  ${random.long}
  ${random.int(10)}
  ${random.int[1024,65536]}
  ```

* **占位符获取之前配置的值**，如果没有可以使用**:**指定**默认值**

  ```properties
  person.last-name=张三${random.uuid}
  person.age=${random.int}
  # 没有取到:后面是默认值
  person.dog.name=${person.hello:hello}_dog
  ```


### 2.5 Profile

* Profile是Spring对**不同环境（开发、测试、上线等）提供不同配置功能**的支持，可以通过激活、 指定参数等方式快速切换环境

#### 2.5.1 多Profile文件

* 我们在主配置文件编写的时候，文件名可以是 application-{profile}.properties/yml，{profile}名任起
  * 默认使用application.properties的配置；



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

* 若文档块都没有指定环境，则默认使用最后一个

* 若某个文档块没有指定环境，则默认使用那个，**一般第一个不指定环境**


#### 2.5.3 激活指定profile

* 在**默认配置文件中指定** spring.profiles.**active**=dev

* **命令行参数**

  ```shell
  java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
  ```

* **虚拟机参数**

  ```
  -Dspring.profiles.active=dev
  ```

  在IDEA中也可以配置，以下三者任选其一，但权限 Program arguments > Active profiles > VM options

  ![](F:\GitHub\Studying\Spring Boot\images\active_profile.png)



### 2.6 配置文件加载位置

* Spring Boot启动会扫描以下位置的application.properties/yml文件作为Spring boot的默认配置文件
  * –file:./config/     ——项目目录下的config

  * –file:./                ——项目目录下

  * –classpath:/config/     ——resources目录下的config

  * –classpath:/                ——resources目录下

    **优先级由高到底**，**高**优先级的配置会**覆盖低**优先级的配置；SpringBoot会从这四个位置全部加载主配置文件；**互补配置**；

* 我们还可以通过**spring.config.location**来**改变默认的配置文件位置**

  * 用于运维时，**项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置；指定配置文件和默认加载的这些配置文件共同起作用形成互补配置；**

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

6. **jar包外部的application-{profile}.properties或application.yml(带spring.profile)配置文件**

7. **jar包内部的application-{profile}.properties或application.yml(带spring.profile)配置文件**

==**再来加载不带profile**==

8. **jar包外部的application.properties或application.yml(不带spring.profile)配置文件**

9. **jar包内部的application.properties或application.yml(不带spring.profile)配置文件**

10. @Configuration注解类上的@PropertySource

11. 通过SpringApplication.setDefaultProperties指定的默认属性



### <span name="autoConfigure">2.8 自动配置</span>

> 配置文件到底能写什么？怎么写？参考文档[这里](https://docs.spring.io/spring-boot/docs/2.0.6.RELEASE/reference/htmlsingle/#common-application-properties)
>

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

* **SpringBoot启动会加载大量的自动配置类**
* **我们看我们需要的功能有没有SpringBoot默认写好的自动配置类；**
* **我们再来看这个自动配置类中到底配置了哪些组件；（只要我们要用的组件有，我们就不需要再来配置了）**
* **给容器中自动配置类添加组件的时候，会从properties类中获取某些属性。我们就可以在配置文件中指定这些属性的值；**
* **自动配置类对应属性类**
  * xxxxAutoConfigurartion：自动配置类；给容器中添加组件
  * xxxxProperties:封装配置文件中相关属性；



#### 2.8.3 @Conditional细节

* **@Conditional派生注解（Spring注解版原生的@Conditional作用）**

* 作用：必须是@Conditional**指定的条件成立**，才给容器中**添加组件**，**配置类**里面的所有内容才**生效**

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

* 我们可以通过在**配置文件中启用 debug=true属性；来让控制台打印自动配置报告**，这样我们就可以很方便的知道哪些自动配置类生效；

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

* 小张开发一个大型系统；

​	1、System.out.println("")；将关键数据打印在控制台；去掉？写在一个文件？

​	2、框架来记录系统的一些运行时信息；日志框架 ； zhanglogging.jar；

​	3、高大上的几个功能？异步模式？自动归档？xxxx？ zhanglogging-good.jar；

​	4、将以前框架卸下来？换上新的框架，重新修改之前相关的API；zhanglogging-prefect.jar；

​	5、借鉴JDBC---数据库驱动；

​		写了一个统一的接口层；日志门面（日志的一个抽象层）；logging-abstract.jar；

​		给项目中导入具体的日志实现就行了；我们之前的日志框架都是实现的抽象层；

* 市面上的日志框架
  JUL、JCL、Jboss-logging、logback、log4j、log4j2、slf4j....

  | 日志门面 （日志的抽象层）                                    | 日志实现                                                |
  | ------------------------------------------------------------ | ------------------------------------------------------- |
  | ~~JCL（Jakarta Commons Logging）~~  SLF4j（Simple Logging Facade for Java）~~**jboss-logging**~~ | Log4j     JUL（java.util.logging） Log4j2   **Logback** |

* 左边选一个门面（抽象层）：SLF4J。右边来选一个实现：Logback；

* SpringBoot：底层是Spring框架，Spring框架默认是用JCL；

  * ==**SpringBoot选用 SLF4j和logback；**==



### 3.2 SLF4j使用

#### 3.2.1 如何在系统中使用SLF4j

* 以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是**调用日志抽象层里面的方法**；

  * 给系统里面导入slf4j的jar和 logback的实现jar

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

![](F:\GitHub\Studying\Spring Boot\images\concrete-bindings.png)

* 每一个日志的实现框架都有自己的配置文件。使用slf4j**配置文件还是做成日志实现框架自己本身的配置文件；**



#### 3.2.2 遗留问题

* a系统（slf4j+logback）: Spring（commons-logging）、Hibernate（jboss-logging）、MyBatis、xxxx

* **统一日志记录**，即**如何让系统中所有的日志都统一到slf4j？**
  * ==**将系统中其他日志框架先排除出去；**==
  * ==**用中间包来替换原有的日志框架；**==
  * ==**我们导入slf4j其他的实现**==

![](F:\GitHub\Studying\Spring Boot\images\legacy.png)



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

* Spring Boot 1.5.10如下：

![](F:\GitHub\Studying\Spring Boot\images\底层依赖关系.png)

* SpringBoot 2.0后底层依赖如下：

![](F:\GitHub\Studying\Spring Boot\images\底层依赖关系2.png)

* **总结 Spring Boot 1.5.10**

  * SpringBoot底层也是使用slf4j+logback的方式进行日志记录

  * SpringBoot也把其他的日志都替换成了slf4j

  * 中间替换包细节如下截取部分：

    ```java
    @SuppressWarnings("rawtypes")
    public abstract class LogFactory {
    
        static String UNSUPPORTED_OPERATION_IN_JCL_OVER_SLF4J = "http://www.slf4j.org/codes.html#unsupported_operation_in_jcl_over_slf4j";
    
        static LogFactory logFactory = new SLF4JLogFactory();
    ```

    Spring Boot 1.5.10中的中间转换包如下，2.0后改了名字，实现的名称也不同，不放图了

    ![](F:\GitHub\Studying\Spring Boot\images\中间替换包.png)

  * **若要引入其他框架**？一定要把这个框架的**默认日志依赖移除**掉。例如Spring框架用的是commons-logging。但是Spring Boot2.0后的没有这个，因为底层不再依赖它

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

* Spring Boot 2.0开始使用 Commons Logging 进行所有内部日志记录，但保留底层日志实现。 为Java Util Logging，Log4J2，和 Logback提供了默认配置。默认情况下，如果使用“Starters”，则使用Logback进行日志记录

### 3.4 日志使用

#### 3.4.1 默认配置

* SpringBoot默认帮我们配置好了日志

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

* SpringBoot修改日志的默认配置

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

* 给**类路径**下放上**每个日志框架自己的配置文件**即可；SpringBoot就不使用他默认配置的了

  | Logging System         | Customization                                                |
  | ---------------------- | ------------------------------------------------------------ |
  | Logback                | `logback-spring.xml`, `logback-spring.groovy`, `logback.xml` or `logback.groovy` |
  | Log4j2                 | `log4j2-spring.xml` or `log4j2.xml`                          |
  | JDK(Java Util Logging) | `logging.properties`                                         |
  * logback.xml：直接就被日志框架识别了；

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

* 可以按照slf4j的日志适配图，进行相关的切换

  * slf4j+log4j的方式（没意义）

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

  * 切换为log4j2

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

* 使用SpringBoot
  1. **创建SpringBoot应用，选中我们需要的模块**
  2. **SpringBoot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来**
  3. **自己编写业务代码**

* 最根本需要理解**自动配置原理？**<a href="#autoConfigure">见2.8节</a>
  * 这个场景SpringBoot帮我们配置了什么？能不能修改？能修改哪些配置？能不能扩展？xxx
    * xxxxAutoConfiguration：帮我们给容器中自动配置组件
    * xxxxProperties:配置类来封装配置文件的内容；



### 4.2 SpringBoot对静态资源的映射规则

```java
@ConfigurationProperties(prefix = "spring.resources", ignoreUnknownFields = false)
public class ResourceProperties implements ResourceLoaderAware {
  //可以设置和静态资源有关的参数，缓存时间等
```

* **WebMvcAuotConfiguration.java**： SpringMVC相关配置都在这个类中

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



* ==所有**/webjars/\****访问 ，都去 **classpath:/META-INF/resources/webjars/** 找资源==

     * `webjars`：以jar包的方式引入静态资源；[进官网查看详细信息](<http://www.webjars.org/>)

     * 引入依赖

       ```xml
       <!--引入jquery-webjar;在访问的时候只需要写webjars下面资源的名称即可-->
       <dependency>
           <groupId>org.webjars</groupId>
           <artifactId>jquery</artifactId>
           <version>3.3.1-1</version>
       </dependency>
       ```

       ![](F:\GitHub\Studying\Spring Boot\images\jquery.png)

     * 访问：localhost:8080/webjars/jquery/3.3.1-1/jquery.js


* =="/**" 访问当前项目的任何资源，都去（静态资源的文件夹里）找映射==

  ```java
  "classpath:/META-INF/resources/", 
  "classpath:/resources/",
  "classpath:/static/", 
  "classpath:/public/" 
  "/"：当前项目的根路径
  ```

  * 访问：localhost:8080/abc === 去静态资源文件夹里面找abc

* ==**欢迎页**：静态资源文件夹下的所有index.html页面；被"/**"映射==

  * 访问：localhost:8080/，找index页面
* ==**图标**：所有的 **/favicon.ico 都是在静态资源文件下找==



### 4.3 模板引擎

JSP、Velocity、Freemarker、Thymeleaf（Spring推荐，语法更简单，功能更强大）

![](F:\GitHub\Studying\Spring Boot\images\template-engine.png)

#### 4.3.1 引入Thymeleaf

* [官网查看更详细内容](https://www.thymeleaf.org/)

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

* 只要我们把HTML页面**放在classpath:/templates/**，thymeleaf就能自动渲染；

* 使用：

  * 导入thymeleaf的名称空间，才能有语法提示

    ```html
    <html lang="en" xmlns:th="http://www.thymeleaf.org">
    ```

  * 使用thymeleaf语法

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

* th:text：改变当前元素里面的文本内容
  * **th:任意html属性**：来替换原生属性的值

![](F:\GitHub\Studying\Spring Boot\images\2018-02-04_123955.png)

* **表达式**

  ```properties
  Simple expressions:（表达式语法）
  a. Variable Expressions: ${...}：获取变量值；OGNL；
  	1）、获取对象的属性、调用方法
  	2）、使用内置的基本对象：
      #ctx : the context object.
      #vars: the context variables.
      #locale : the context locale.
      #request : (only in Web Contexts) the HttpServletRequest object.
      #response : (only in Web Contexts) the HttpServletResponse object.
      #session : (only in Web Contexts) the HttpSession object.
      #servletContext : (only in Web Contexts) the ServletContext object.
  	${session.foo}
      3）、内置的一些工具对象：
          #execInfo : information about the template being processed.
          #messages : methods for obtaining externalized messages inside variables expressions,
          #          in the same way as they would be obtained using #{…} syntax.
          #uris : methods for escaping parts of URLs/URIs
          #conversions : methods for executing the configured conversion service (if any).
          #dates : methods for java.util.Date objects: formatting, component extraction, etc.
          #calendars : analogous to #dates , but for java.util.Calendar objects.
          #numbers : methods for formatting numeric objects.
          #strings : methods for String objects: contains, startsWith, prepending/appending, etc.
          #objects : methods for objects in general.
          #bools : methods for boolean evaluation.
          #arrays : methods for arrays.
          #lists : methods for lists.
          #sets : methods for sets.
          #maps : methods for maps.
          #aggregates : methods for creating aggregates on arrays or collections.
          #ids : methods for dealing with id attributes that might be repeated (for example, as a result of an iteration).
  
  b. Selection Variable Expressions: *{...}：选择表达式：和${}在功能上是一样；
      	补充：配合 th:object="${session.user}：
          <div th:object="${session.user}">
          <p>Name: <span th:text="*{firstName}">Sebastian</span>.</p>
          <p>Surname: <span th:text="*{lastName}">Pepper</span>.</p>
          <p>Nationality: <span th:text="*{nationality}">Saturn</span>.</p>
          </div>
      
  c. Message Expressions: #{...}：获取国际化内容
  d. Link URL Expressions: @{...}：定义URL；
      		@{/order/process(execId=${execId},execType='FAST')}
  f. Fragment Expressions: ~{...}：片段引用表达式
      		<div th:insert="~{commons :: main}">...</div>
      		
  Literals（字面量）
      Text literals: 'one text' , 'Another one!' ,…
      Number literals: 0 , 34 , 3.0 , 12.3 ,…
      Boolean literals: true , false
      Null literal: null
      Literal tokens: one , sometext , main ,…
  Text operations:（文本操作）
      String concatenation: +
      Literal substitutions: |The name is ${name}|
  Arithmetic operations:（数学运算）
      Binary operators: + , - , * , / , %
      Minus sign (unary operator): -
  Boolean operations:（布尔运算）
      Binary operators: and , or
      Boolean negation (unary operator): ! , not
  Comparisons and equality:（比较运算）
      Comparators: > , < , >= , <= ( gt , lt , ge , le )
      Equality operators: == , != ( eq , ne )
  Conditional operators:条件运算（三元运算符）
      If-then: (if) ? (then)
      If-then-else: (if) ? (then) : (else)
      Default: (value) ?: (defaultvalue)
  Special tokens:
      No-Operation: _ 
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