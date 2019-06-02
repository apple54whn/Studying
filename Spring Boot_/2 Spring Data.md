# 1 Spring Data JPA

## 1 ORM、Hibernate与JPA的概述

### 1.1 ORM

**ORM（Object-Relational Mapping）** 表示**对象关系映射**。简单的说：ORM就是**建立实体类和数据库表之间的关系**（包括表和字段），从而达到操作实体类就相当于操作数据库表的目的。解决了可能会写特别多数据访问层的代码、从数据库保存数据、修改数据、删除数据等重复代码问题。

常见的orm框架：~~Mybatis（ibatis）~~、Hibernate



### 1.2 Hibernate

Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常**轻量级**的对象封装，它将POJO与数据库表建立映射关系，是一个**全自动的ORM框架**。Hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。



### 1.3 JPA

**JPA（Java Persistence API）**， 即**Java 持久化API**，是SUN公司推出的一套**基于ORM的规范**（操作ORM框架，JDBC是操作不同数据库），内部是由一系列的接口和抽象类构成。

------

JPA的优势：

- **1.** **标准化**

  JPA 是 JCP 组织发布的 Java EE 标准之一，因此任何声称符合 JPA 标准的框架都遵循同样的架构，提供相同的访问API，这保证了基于JPA开发的企业应用能够经过少量的修改就能够在不同的JPA框架下运行。

- **2.** **容器级特性的支持**

  JPA框架中支持大数据集、事务、并发等容器级事务，使得 JPA 超越了简单持久化框架的局限，在企业应用发挥更大作用。

- **3.** **简单方便**

  JPA的主要目标之一就是提供更加简单的编程模型：在JPA框架下创建实体和创建Java 类一样简单，没有任何的约束和限制，只需要使用 javax.persistence.Entity进行注释，JPA的框架和接口也都非常简单，没有太多特别的规则和设计模式的要求，开发者可以很容易的掌握。JPA基于非侵入式原则设计，因此可以很容易的和其它框架或者容器集成

- **4.** **查询能力**

  JPA的查询语言是**面向对象**而非面向数据库的，它以面向对象的自然语法构造查询语句，可以看成是Hibernate HQL的等价物。JPA定义了独特的JPQL（Java Persistence Query Language），JPQL是EJB QL的一种扩展，它是针对实体的一种查询语言，操作对象是实体，而不是关系数据库的表，而且能够支持批量更新和修改、JOIN、GROUP BY、HAVING 等通常只有 SQL 才能够提供的高级查询特性，甚至还能够支持子查询。

- **5.** **高级特性**

  JPA 中能够支持面向对象的高级特性，如类之间的继承、多态和类之间的复杂关系，这样的支持能够让开发者最大限度的使用面向对象的模型设计企业应用，而不需要自行处理这些特性在关系数据库的持久化。

------

JPA和Hibernate的关系就像JDBC和JDBC驱动的关系，JPA是规范，Hibernate除了作为ORM框架之外，它也是一种JPA实现。JPA怎么取代Hibernate呢？JDBC规范可以驱动底层数据库吗？答案是否定的，也就是说，如果使用JPA规范进行数据库操作，底层需要Hibernate作为其实现类完成数据持久化工作。



## 2 JPA的API介绍

### 2.1 Persistence

**`Persistence`对象主要作用是用于获取`EntityManagerFactory`对象**的 。通过调用该类的`createEntityManagerFactory()`静态方法，根据配置文件中**持久化单元名称**创建`EntityManagerFactory`。

### 2.2 EntityManagerFactory

`EntityManagerFactory` 接口主要用`createEntityManager()`来创建 `EntityManager` 实例

由于**`EntityManagerFactory`是一个==线程安全==的对象**（即多个线程访问同一个`EntityManagerFactory` 对象不会有线程安全问题），并且`EntityManagerFactory` 的创建极其浪费资源，所以在使用JPA编程时，我们可以对`EntityManagerFactory`
的创建进行优化，只需要做到**一个工程只存在一个`EntityManagerFactory`** 即可

### 2.3 EntityManager

在 JPA 规范中, **`EntityManager`是完成持久化操作的核心对象**。实体类作为普通 java对象，只有在调用`EntityManager`将其持久化后才会变成持久化对象。**EntityManager对象在一组实体类与底层数据源之间进行 O/R 映射的管理**。它可以用来管理和更新 Entity Bean, 根椐主键查找 Entity Bean, 还可以通过JPQL语句查询实体。

我们可以通过调用`EntityManager`的方法完成**获取事务**，以及**持久化数据库**的操作

- **`getTransaction` **: 获取事务对象
- **`persist`** ： 保存操作
- **`find/getReference` **： 根据id查询
- **`merge` **： 更新操作
- **`remove`** ： 删除操作

### 2.4 EntityTransaction

在 JPA 规范中, EntityTransaction是完成事务操作的核心对象，对于EntityTransaction在我们的java代码中承接的功能比较简单

- `begin`：开启事务
- `commit`：提交事务
- `rollback`：回滚事务



## 3 JPA的CRUD入门案例

3.1 Maven坐标导入

```xml
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.hibernate.version>5.0.7.Final</project.hibernate.version>
</properties>

<dependencies>
    <!-- Mysql and MariaDB -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.12</version>
    </dependency>

    <!-- hibernate对jpa的支持包 -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>${project.hibernate.version}</version>
    </dependency>

    
    <!-- c3p0 -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-c3p0</artifactId>
        <version>${project.hibernate.version}</version>
    </dependency>
    <!-- junit -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>

    <!-- log日志 -->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
</dependencies>
```

### 3.2 persistence.xml配置文件

1. JPA的核心配置文件：`META-INF/persistence.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">
   
       <!--配置持久化单元
           name：持久化单元名称
           transaction-type：事务类型
               RESOURCE_LOCAL：本地事务管理
               JTA：分布式事务管理 -->
       <persistence-unit name="myJpa" transaction-type="RESOURCE_LOCAL">
           <!--配置JPA规范的服务提供商（这里是Hibernate） -->
           <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
           <properties>
               <!-- 数据库驱动 -->
               <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver" />
               <!-- 数据库地址 -->
               <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3308/jpa?serverTimezone=GMT%2B8" />
               <!-- 数据库用户名 -->
               <property name="javax.persistence.jdbc.user" value="root" />
               <!-- 数据库密码 -->
               <property name="javax.persistence.jdbc.password" value="w111151" />
   
   
               <!--JPA提供者的可选配置：我们的JPA规范的提供者为hibernate，所以JPA的核心配置中兼容Hibernate的配置-->
               <!--显示sql，false | true-->
               <property name="hibernate.show_sql" value="true" />
               <property name="hibernate.format_sql" value="true" />
               <!--自动创建数据库表
                   create : 程序运行时创建数据库表（如果有表，先删除表再创建）
                   update ：程序运行时创建表（如果有表，不会创建表）
                   none   ：不会创建表  -->
               <property name="hibernate.hbm2ddl.auto" value="update" />
           </properties>
   
       </persistence-unit>
   </persistence>
   ```



### 3.3 ==注解实体类和数据库映射关系==

1. 创建客户的数据库表（不创建也行）

2. **在实体类上使用JPA注解的形式配置映射关系**

   所有的注解都是使用JPA的规范提供的注解，导入`javax.persistence`下注解包

   ```java
   @Entity
   @Table(name = "cst_customer")
   public class Customer {
       
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "cust_id")
       private Long custId; //客户的主键
   
       @Column(name = "cust_name")
       private String custName;//客户名称
   
       @Column(name = "cust_source")
       private String custSource;//客户来源
   
       @Column(name = "cust_level")
       private String custLevel;//客户级别
   
       @Column(name = "cust_industry")
       private String custIndustry;//客户所属行业
   
       @Column(name = "cust_phone")
       private String custPhone;//客户的联系方式
   
       @Column(name = "cust_address")
       private String custAddress;//客户地址
       
       //getter setter   
   }
   ```

   - **`@Entity`**

     - 作用：指定当前类是**实体类**。

   - **`@Table`**

     - 作用：指定**实体类和表之间的对应关系**。
     - 属性：
       - `name`：指定数据库**表的名称**，不指定则为类名首字母小写

   - **`@Id`**

     - 作用：指定当前字段是**主键**。

   - **`@GeneratedValue`**

     - 作用：指定**主键的生成策略**。

     - 属性：

       - **`strategy`** ：指定主键生成策略。JPA提供的四种标准用法为`TABLE`,`SEQUENCE`,`IDENTITY`,`AUTO`。

         - **`IDENTITY`**：主键由数据库**自动生成**（主要是自动增长型，必须数据库底层支持，如MySQL）

           ```java
           @Id  
           @GeneratedValue(strategy = GenerationType.IDENTITY) 
           private Long custId;
           ```

         - **`SEQUENCE`**：根据底层数据库的**序列**来生成主键，条件是数据库支持序列，如Oracle。

           ```java
           @Id  
           @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="payablemoney_seq")  
           @SequenceGenerator(name="payablemoney_seq", sequenceName="seq_payment")  
           private Long custId;
           ```

           ```java
           //@SequenceGenerator源码中的定义
           @Target({TYPE, METHOD, FIELD})   
           @Retention(RUNTIME)  
           public @interface SequenceGenerator {  
               //表示该表主键生成策略的名称，它被引用在@GeneratedValue中设置的“generator”值中
               String name();  
               //属性表示生成策略用到的数据库序列名称。
               String sequenceName() default "";  
               //表示主键初识值，默认为0
               int initialValue() default 0;  
               //表示每次主键值增加的大小，例如设置1，则表示每次插入新记录后自动加1，默认为50
               int allocationSize() default 50;  
           }
           ```

         - `AUTO`：主键由程序控制（测试了下使用的是TABLE）

           ```java
           @Id  
           @GeneratedValue(strategy = GenerationType.AUTO)  
           private Long custId;
           ```

         - `TABLE`：使用一个特定的数据库表格来保存主键

           ```java
           @Id  
           @GeneratedValue(strategy = GenerationType.TABLE, generator="payablemoney_gen")  
           @TableGenerator(name = "pk_gen",  
                           table="tb_generator",  
                           pkColumnName="gen_name",  
                           valueColumnName="gen_value",  
                           pkColumnValue="PAYABLEMOENY_PK",  
                           allocationSize=1  
                          ) 
           private Long custId;
           ```

           ```java
           //@TableGenerator的定义：
           @Target({TYPE, METHOD, FIELD})   
           @Retention(RUNTIME)  
           public @interface TableGenerator {  
               //表示该表主键生成策略的名称，它被引用在@GeneratedValue中设置的“generator”值中
               String name();  
               //表示表生成策略所持久化的表名，例如，这里表使用的是数据库中的“tb_generator”。
               String table() default "";  
               //catalog和schema具体指定表所在的目录名或是数据库名
               String catalog() default "";  
               String schema() default "";  
               //属性的值表示在持久化表中，该主键生成策略所对应键值的名称。例如在“tb_generator”中将“gen_name”作为主键的键值
               String pkColumnName() default "";  
               //属性的值表示在持久化表中，该主键当前所生成的值，它的值将会随着每次创建累加。例如，在“tb_generator”中将“gen_value”作为主键的值 
               String valueColumnName() default "";  
               //属性的值表示在持久化表中，该生成策略所对应的主键。例如在“tb_generator”表中，将“gen_name”的值为“CUSTOMER_PK”。 
               String pkColumnValue() default "";  
               //表示主键初识值，默认为0。 
               int initialValue() default 0;  
               //表示每次主键值增加的大小，例如设置成1，则表示每次创建新记录后自动加1，默认为50。
               int allocationSize() default 50;  
               UniqueConstraint[] uniqueConstraints() default {};  
           } 
           /*==================================================================*/
           //这里应用表tb_generator，定义为 ：
           CREATE TABLE  tb_generator (  
               id NUMBER NOT NULL,  
               gen_name VARCHAR2(255) NOT NULL,  
               gen_value NUMBER NOT NULL,  
               PRIMARY KEY(id)  
           )
           ```

   - **`@Column`**

     - 作用：指定实体类属性和数据库表之间的对应关系

     - 属性：

       - `name`：指定数据库表的列名称。不指定则为属性名
       - `unique`：是否唯一  
       - `nullable`：是否可以为空 
       - `inserttable`：是否可以插入          		
       - `updateable`：是否可以更新          		
       - `columnDefinition`: 定义建表时创建此列的DDL          		
       - `secondaryTable`: 从表名。如果此列不建在主表上（默认建在主表），该属性定义该列所在从表的名字搭建开发环境[重点]

       

   - **`@OneToMany`**：建立一对多的关系映射

     - 属性：
       - **`mappedBy`**：指定**在从表实体类中引用主表对象的名称**，即**主表放弃外键维护**或**被维护表放弃维护中间表**
       - **`fetch`**：指定是否采用**延迟加载**，枚举类`FetchType`
         - `LAZY`（**默认**），`EAGER`
       - **`cascade`**：指定要使用的**级联**操作，枚举类`CascadeType`
         - `ALL`，`PERSIST`，`MERGE`，`REMOVE`，`REFRESH`，`DETACH`；
       - `orphanRemoval`：是否使用孤儿删除，true；false（默认）
       - `targetEntity`：指定多的多方的类的字节码，可省略会自动推倒

   - **`@ManyToOne`**：建立多对一的关系

     - 属性：
       - `fetch`：指定是否采用**延迟**加载（同上）
       - `cascade`：指定要使用的**级联**操作（同上）
       - `optional`：关联是否可选。如果设置为false，则必须始终存在非空关系。默认为true
       - `targetEntity`：指定一的一方实体类字节码，可省略会自动推导

   - **`ManyToMany`**：用于映射多对多关系

     - 属性：
       - `fetch`：指定是否采用**延迟**加载（同上）
       - `cascade`：指定要使用的**级联**操作（同上）
       - `targetEntity`：配置目标的实体类。映射多对多的时候不用写。可省略会自动推导

   - `OneToOne`

   - **`@JoinColumn`**：用于定义**主键字段和外键字段的对应关系**，主要用于**从表中定义外键的引用**

     - 属性：
       - `name`：指定**外键字段的名称**，不配置则根据被维护表中属性名和`referencedColumnName`组合
       - `referencedColumnName`：指定**引用主表的主键字段名称**
       - `unique`：是否唯一。默认false
       - `nullable`：是否允许为空。默认true。
       - `insertable`：是否允许插入。默认true。
       - `updatable`：是否允许更新。默认true。
       - `columnDefinition`：列的定义信息。

   - **`@JoinTable`**：针对**中间表的配置**，主要**用于维护表来设置**

     - 属性：
       - `name`：配置**中间表的名称**，不配置则自动根据维护表和被维护表的类名组合来生成
       - `@joinColumns`：需要中间表的外键字段**关联当前实体类所对应表的主键字段**
       - `@inverseJoinColumn`：需要中间表的外键字段**关联对方表的主键字段**

### 3.4 基本CRUD

1. 工具类

   ```java
   public final class JPAUtil {
       // JPA的实体管理器工厂：相当于Hibernate的SessionFactory
       private static EntityManagerFactory em;
       // 使用静态代码块赋值
       static {
           // 注意：该方法参数必须和persistence.xml中persistence-unit标签name属性取值一致
           em = Persistence.createEntityManagerFactory("myJpa");
       }
   
       /**
   	 * 使用管理器工厂生产一个管理器对象
   	 */
       public static EntityManager getEntityManager() {
           return em.createEntityManager();
       }
   }
   
   ```

2. **CRUD**

   ```java
   /**
    * Jpa的操作步骤
    * 1.加载配置文件创建工厂（实体管理器工厂）对象
    * 2.通过实体管理器工厂获取实体管理器
    * 3.获取事务对象，开启事务
    * 4.完成增删改查操作
    * 5.提交事务（回滚事务）
    * 6.释放资源
    **/
   public class JPATest {
   
       private EntityManager entityManager;
       private EntityTransaction transaction;
   
       @Before
       public void init() {
           //通过工具类获取EntityManager对象
           entityManager = JPAUtil.getEntityManager();
           //获取事务对象，开启事务
           transaction = entityManager.getTransaction();
           transaction.begin();
       }
   
       @After
       public void destroy() {
           //释放资源，工厂对象不用释放，其他方法还需要使用
           entityManager.close();
       }
   
       /**
        * 保存客户
        */
       @Test
       public void testSave() {
           Customer customer = new Customer();
           customer.setCustName("博学谷");
           customer.setCustPhone("10086");
           try {
               //保存
               entityManager.persist(customer);
               //提交事务
               transaction.commit();
           } catch (Exception e) {
               //或回滚事务
               transaction.rollback();
               e.printStackTrace();
           }
       }
   
       /**
        * 根据ID查询客户
        * find为立即加载
        */
       @Test
       public void testFind() {
           try {
               //第一个参数为要封装的对象的字节码，第二个参数为主键
               Customer customer = entityManager.find(Customer.class, 3L);
               System.out.println(customer.getCustName());
               transaction.commit();
           } catch (Exception e) {
               e.printStackTrace();
               transaction.rollback();
           }
       }
   
       /**
        * 根据ID查询客户
        * getReference为延迟加载（懒加载），使用的时候才查询数据库。一般使用这个！
        * （IDEA需要去掉Debug中几个选项才可以看到）
        */
       @Test
       public void testReference() {
           try {
               //第一个参数为要封装的对象的字节码，第二个参数为主键
               Customer customer = entityManager.getReference(Customer.class, 4L);
               System.out.println(customer.getCustName());
               transaction.commit();
           } catch (Exception e) {
               e.printStackTrace();
               transaction.rollback();
           }
       }
   
       /**
        * 跟新客户信息（先根据ID查询，再修改对象并Merge更新）
        */
       @Test
       public void testMerge() {
           try {
               Customer customer = entityManager.getReference(Customer.class, 3L);
               customer.setCustIndustry("IT666");
               entityManager.merge(customer);
               transaction.commit();
           } catch (Exception e) {
               e.printStackTrace();
               transaction.rollback();
           }
       }
   
       /**
        * 删除客户（要先根据ID查询，再删除该对象）
        */
       @Test
       public void testRemove() {
           try {
               Customer customer = entityManager.getReference(Customer.class, 4L);
               entityManager.remove(customer);
               transaction.commit();
           } catch (Exception e) {
               e.printStackTrace();
               transaction.rollback();
           }
       }
   }
   
   ```

### 3.5 JPQL查询

JPQL（Java Persistence Query Language）。基于首次在EJB2.0中引入的EJB查询语言(EJB QL)，Java持久化查询语言(JPQL)是一种可移植的查询语言，旨在以面向对象表达式语言的表达式，将SQL语法和简单查询语义绑定在一起，使用这种语言编写的查询是可移植的，可以被编译成所有主流数据库服务器上的SQL。

其特征与原生SQL语句类似，并且完全**面向对象**，通过**类名**和**属性**访问，而**不是表名和表中字段**。（不支持`SELECT *`）

```java
/**
 * 进行jpql查询
 * 1.根据jpql语句创建query查询对象
 * 2.对参数进行赋值
 * 3.查询，并得到返回结果
 **/
public class JPQLTest {

    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void init() {
        //通过工具类获取EntityManager对象
        entityManager = JPAUtil.getEntityManager();
        //获取事务对象，开启事务
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @After
    public void destroy() {
        //释放资源，工厂对象不用释放，其他方法还需要使用
        entityManager.close();
    }

    /**
     * 查找所有
     */
    @Test
    public void testFindAll() {
        try {
            String jpql = "from cn.itcast.domain.Customer";//也可以省略掉全限定类名，只写类名
            //创建Query查询对象，这个对象才是执行jpql的对象
            Query query = entityManager.createQuery(jpql);
            List<Customer> resultList = query.getResultList();

            transaction.commit();
            for (Customer customer : resultList) {
                System.out.println(customer.getCustName());
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * 排序查询（根据ID倒序查询所有客户）
     */
    @Test
    public void testOrder() {
        try {
            String jpql = "from Customer order by custId desc";//也可以省略掉全限定类名，只写类名
            //创建Query查询对象，这个对象才是执行jpql的对象
            Query query = entityManager.createQuery(jpql);
            List<Customer> resultList = query.getResultList();

            transaction.commit();
            for (Customer customer : resultList) {
                System.out.println(customer.getCustName());
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount() {
        try {
            String jpql = "select count(custId) from Customer";//也可以省略掉全限定类名，只写类名
            //创建Query查询对象，这个对象才是执行jpql的对象
            Query query = entityManager.createQuery(jpql);
            Long count = (Long) query.getSingleResult();

            transaction.commit();
            System.out.println(count);

        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * 分页查询
     */
    @Test
    public void testPage() {
        try {
            String jpql = "from Customer";//也可以省略掉全限定类名，只写类名
            //创建Query查询对象，这个对象才是执行jpql的对象
            Query query = entityManager.createQuery(jpql);
            query.setFirstResult(0);//起始索引
            query.setMaxResults(2);//每页查询条数
            List<Customer> resultList = query.getResultList();//此处为查询前两条

            transaction.commit();
            for (Customer customer : resultList) {
                System.out.println(customer.getCustName());
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * 条件查询
     */
    @Test
    public void testCondition() {
        try {
            String jpql = "from Customer where custName like ?";//也可以省略掉全限定类名，只写类名
            //创建Query查询对象，这个对象才是执行jpql的对象
            Query query = entityManager.createQuery(jpql);
            query.setParameter(1, "传智播客%");//第1个参数：占位符索引位置，从1开始；第2个参数：值
            List<Customer> resultList = query.getResultList();//此处为查询前两条

            transaction.commit();
            for (Customer customer : resultList) {
                System.out.println(customer.getCustName());
            }
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }
}
```





## 4 Spring Data JPA

> Spring Data JPA 让我们解脱了DAO层的操作，基本上所有CRUD都可以依赖于它来实现,在实际的工作工程中，推荐使用Spring Data JPA + ORM（如：Hibernate）完成操作，这样在切换不同的ORM框架时提供了极大的方便，同时也使数据库层操作更加简单，方便解耦。
>
> Spring Data JPA 极大简化了数据库访问层代码。 如何简化的呢？ 使用了Spring Data JPA，我们的dao层中只需要写接口，就自动具有了增删改查、分页查询等方法。

Spring Data JPA 封装了 JPA ，JPA 规范的一个实现为 Hibernate ，Hibernate 又封装了 JDBC ，JDBC 来操作数据库如MySQL

### 4.1 Maven坐标导入

```xml
<properties>
    <spring.version>5.0.2.RELEASE</spring.version>
    <hibernate.version>5.0.7.Final</hibernate.version>
    <slf4j.version>1.6.6</slf4j.version>
    <log4j.version>1.2.12</log4j.version>
    <c3p0.version>0.9.1.2</c3p0.version>
    <mysql.version>8.0.12</mysql.version>
</properties>

<dependencies>
    <!-- junit单元测试 -->
    <dependency>
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <scope>test</scope>
    </dependency>

    <!-- spring beg -->
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.6.8</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-aop</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context-support</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- spring对orm框架的支持包-->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-beans</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>${spring.version}</version>
    </dependency>
    <!-- spring end -->

    <!-- hibernate beg -->
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
        <version>${hibernate.version}</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>${hibernate.version}</version>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-validator</artifactId>
        <version>5.2.1.Final</version>
    </dependency>
    <!-- hibernate end -->

    <!-- c3p0 beg -->
    <dependency>
        <groupId>c3p0</groupId>
        <artifactId>c3p0</artifactId>
        <version>${c3p0.version}</version>
    </dependency>
    <!-- c3p0 end -->

    <!-- log end -->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>${log4j.version}</version>
    </dependency>

    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>${slf4j.version}</version>
    </dependency>

    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-log4j12</artifactId>
        <version>${slf4j.version}</version>
    </dependency>
    <!-- log end -->


    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>${mysql.version}</version>
    </dependency>

    <!-- spring data jpa 的坐标-->
    <dependency>
        <groupId>org.springframework.data</groupId>
        <artifactId>spring-data-jpa</artifactId>
        <version>1.9.0.RELEASE</version>
    </dependency>

    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-test</artifactId>
        <version>${spring.version}</version>
    </dependency>

    <!-- el beg 使用spring data jpa 必须引入 -->
    <dependency>
        <groupId>javax.el</groupId>
        <artifactId>javax.el-api</artifactId>
        <version>2.2.4</version>
    </dependency>

    <dependency>
        <groupId>org.glassfish.web</groupId>
        <artifactId>javax.el</artifactId>
        <version>2.2.4</version>
    </dependency>
    <!-- el end -->
</dependencies>

```



### 4.2 Spring Data JPA 配置文件

applicationContext.xml

```xml
<!--spring 和 spring data jpa的配置-->

<!-- 1.创建entityManagerFactory对象交给spring容器管理-->
<bean id="entityManagerFactoty" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
    <property name="dataSource" ref="dataSource" />
    <!--配置的扫描的包（实体类所在的包） -->
    <property name="packagesToScan" value="cn.itcast.domain" />
    <!-- jpa的实现厂家 -->
    <property name="persistenceProvider">
        <bean class="org.hibernate.jpa.HibernatePersistenceProvider"/>
    </property>

    <!--jpa的供应商适配器 -->
    <property name="jpaVendorAdapter">
        <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
            <!--配置是否自动创建数据库表 -->
            <property name="generateDdl" value="false" />
            <!--指定数据库类型 -->
            <property name="database" value="MYSQL" />
            <!--数据库方言：支持的特有语法 -->
            <property name="databasePlatform" value="org.hibernate.dialect.MySQLDialect" />
            <!--是否显示sql -->
            <property name="showSql" value="true" />
        </bean>
    </property>

    <!--jpa的方言 ：高级的特性 -->
    <property name="jpaDialect" >
        <bean class="org.springframework.orm.jpa.vendor.HibernateJpaDialect" />
    </property>

    <!--注入jpa的配置信息。在多表操作时为了方便会使用到create和update
            加载jpa的基本配置信息和jpa实现方式（hibernate）的配置信息
            hibernate.hbm2ddl.auto : 自动创建数据库表
                create ： 每次都会重新创建数据库表
                update：有表不会重新创建，没有表会重新创建表
        -->
    <property name="jpaProperties" >
        <props>
            <prop key="hibernate.hbm2ddl.auto">create</prop>
        </props>
    </property>

</bean>

<!--2.创建数据库连接池 -->
<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
    <property name="user" value="root"/>
    <property name="password" value="w111151"/>
    <property name="jdbcUrl" value="jdbc:mysql://localhost:3308/jpa?serverTimezone=GMT%2B8"/>
    <property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
</bean>

<!--3.整合spring dataJpa-->
<jpa:repositories base-package="cn.itcast.dao" transaction-manager-ref="transactionManager"
                  entity-manager-factory-ref="entityManagerFactoty" />

<!--4.配置事务管理器 -->
<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
    <property name="entityManagerFactory" ref="entityManagerFactoty"/>
</bean>

<!----------------------------5.声明式事务，暂时用不到，直接使用注解配置@Transactional ------------------------->
<!-- 4.txAdvice-->
<tx:advice id="txAdvice" transaction-manager="transactionManager">
    <tx:attributes>
        <tx:method name="save*" propagation="REQUIRED"/>
        <tx:method name="insert*" propagation="REQUIRED"/>
        <tx:method name="update*" propagation="REQUIRED"/>
        <tx:method name="delete*" propagation="REQUIRED"/>
        <tx:method name="get*" read-only="true"/>
        <tx:method name="find*" read-only="true"/>
        <tx:method name="*" propagation="REQUIRED"/>
    </tx:attributes>
</tx:advice>

<!-- 5.aop-->
<aop:config>
    <aop:pointcut id="pointcut" expression="execution(* cn.itcast.service.*.*(..))" />
    <aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut" />
</aop:config>
<!---------------------------------------暂时用不到---------------------------------------->

<!-- 6. 配置包扫描-->
<context:component-scan base-package="cn.itcast"/>

```



### 4.3 注解实体类和数据库映射关系

同3.3一致

```java
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; //客户的主键

    @Column(name = "cust_name")
    private String custName;//客户名称

    @Column(name = "cust_source")
    private String custSource;//客户来源

    @Column(name = "cust_level")
    private String custLevel;//客户级别

    @Column(name = "cust_industry")
    private String custIndustry;//客户所属行业

    @Column(name = "cust_phone")
    private String custPhone;//客户的联系方式

    @Column(name = "cust_address")
    private String custAddress;//客户地址

    //getter setter   
}

```



### 4.4 Spring Data JPA规范Dao接口

> 别忘了接口之间是可以多继承的！！！

```java
/**
 * JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
 * JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
}
```

![](images/springdatajpa%E7%9A%84%E8%BF%90%E8%A1%8C%E8%BF%87%E7%A8%8B.png)

### 4.5 基本CRUD和复杂查询

#### 1、接口中的定义好的方法

> 以下是继承了JpaRepository接口后获得的

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")//locations可省略
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;
    
    @Test
    public void testFindById() {
        Optional<Customer> optional = customerDao.findById(3L);
        System.out.println(optional.orElse(null));
    }
    

    /**
     * 根据ID查询（立即加载，底层调用find()方法）
     */
    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(3L);
        System.out.println(customer);
    }

    /**
     * 根据ID查询（延迟加载，底层调用getReference()方法）
     * 返回的是动态代理对象；什么时候用，什么时候查询（IDEA需要去掉Debug中几个选项才可以看到）
     */
    @Test
    @Transactional
    public void testGetOne() {
        Customer customer = customerDao.getOne(3L);
        System.out.println(customer);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll() {
        List<Customer> customerList = customerDao.findAll();
        System.out.println(customerList);
    }

    /**
     * 保存或更新（！！！不要直接使用这个来更新，否则会将其他没有值的数据置null。）
     * 根据传递的对象是否包含主键ID，不包含则是保存；包含则是先根据ID查询数据，并更新
     *
     * 更新需要根据ID手动查找，然后修改再保存！！！
     */
    @Test
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("黑马程序员");
        customer.setCustAddress("西安");
        customerDao.save(customer);
    }
    @Test
    public void testUpdate() {
        Customer customer = new Customer();
        customer.setCustId(8L);
        customer.setCustName("黑马程序员牛逼");
        customer.setCustAddress("西安");
        customer.setCustLevel("VIP");
        customerDao.save(customer);
    }

    /**
     * 根据ID删除（底层是先查询，若有再删除）
     */
    @Test
    public void testDelete() {
        customerDao.delete(8L);
    }

    /**
     * 统计查询
     */
    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println(count);
    }

    /**
     * 根据ID判断是否存在该数据（可以根据查询的对象是否为null；或统计出的数量是否大于0（这里底层用的是这个））
     */
    @Test
    public void testExists() {
        boolean exists = customerDao.exists(7L);
        System.out.println(exists);
    }
}
```



#### 2、JPQL的查询方式

- 特有的查询：需要在**Dao接口上配置方法**
- 在新添加的方法上，使用**`@Query`注解的形式配置JPQL查询语句**

```java
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 根据客户名称查询客户。使用JPQL查询
     * ?1代表参数的占位符，其中1对应方法中的参数索引
     */
    @Query("from Customer where custName = ?1")
    Customer findByName(String custName);

    /**
     * 根据客户名称和客户ID查询客户。使用JPQL查询
     * ?1代表参数的占位符，其中1对应方法中的参数索引。不指定则按照方法中参数位置对应
     */
    @Query("from Customer where custName = ?1 and custId=?2")
    Customer findByNameAndId(String custName,Long custId);


    /**
     * 根据ID更新客户名称。使用JPQL
     * ?1代表参数的占位符，其中1对应方法中的参数索引。不指定则按照方法中参数位置对应。“:参数名称“ 也可以当做占位符
     */
    @Query("update Customer set custName = ?1 where custId = ?2")
    @Modifying//代表是更新操作
    void updateNameById(String custName,Long custId);
}

```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")//locations可省略
public class JPQLTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindByName() {
        Customer customer = customerDao.findByName("传智播客6");
        System.out.println(customer);
    }

    @Test
    public void testFindByNameAndId() {
        Customer customer = customerDao.findByNameAndId("传智播客", 5L);
        System.out.println(customer);
    }

    /**
     * Spring Data JPA中使用JPQL完成 更新、删除操作需要手动添加事务，但默认执行后会回滚事务，需要添加注解设置为不回滚
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateNameById() {
        customerDao.updateNameById("传智播客最牛逼", 5L);
    }
}

```



#### 3、方法名称规则查询

方法命名规则查询就是根据方法的名字，就能创建查询，是对JPQL更深层次封装。只需要按照Spring Data JPA提供的方法命名规则定义方法的名称，不需要配置JPQL语句就可以完成查询工作。Spring Data JPA在程序执行的时候会根据方法名称进行解析，并自动生成查询语句进行查询。

按照Spring Data JPA 定义的规则，查询方法以**`findBy`**开头，涉及条件查询时，**条件的属性用条件关键字连接**，要注意的是：条件**属性首字母需大写**。框架在进行方法名解析时，会先把方法名多余的前缀截取掉，然后对剩下部分进行解析。也可以**删除**。

会根据**先后顺序**传参！！名称无所谓

```java
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 方法名称查询（精准匹配）
     */
    Customer findByCustName(String custName);

    /**
     * 方法名称查询（like模糊匹配）
     */
    List<Customer> findByCustNameLike(String custName);

    /**
     * 方法名称查询（使用客户名称模糊匹配和客户行业精准匹配）
     */
    List<Customer> findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
}

```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")//locations可省略
public class JPQLTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试方法命名查询
     */
    @Test
    public void testFindByCustName(){
        Customer customer = customerDao.findByCustName("传智播客6");
        System.out.println(customer);
    }

    /**
     * 测试方法命名查询（模糊匹配）
     */
    @Test
    public void testFindByCustNameLike(){
        List<Customer> customerList = customerDao.findByCustNameLike("传智%");
        System.out.println(customerList);
    }

    /**
     * 测试方法命名查询（使用客户名称模糊匹配和客户行业精准匹配）
     */
    @Test
    public void testFindByCustNameLikeAndCustIndustry(){
        List<Customer> customerList = customerDao.findByCustNameLikeAndCustIndustry("传智%","IT");
        System.out.println(customerList);
    }
}

```



具体的关键字，使用方法和生产成SQL如下表所示

|    **Keyword**    |                 **Sample**                 |                           **JPQL**                           |
| :---------------: | :----------------------------------------: | :----------------------------------------------------------: |
|        And        |         findByLastnameAndFirstname         |        …   where x.lastname = ?1 and x.firstname = ?2        |
|        Or         |         findByLastnameOrFirstname          |        …   where x.lastname = ?1 or x.firstname = ?2         |
|     Is,Equals     | findByFirstnameIs,   findByFirstnameEquals |                  …   where x.firstname = ?1                  |
|      Between      |           findByStartDateBetween           |           …   where x.startDate between ?1 and ?2            |
|     LessThan      |             findByAgeLessThan              |                     …   where x.age < ?1                     |
|   LessThanEqual   |           findByAgeLessThanEqual           |                     …   where x.age ⇐ ?1                     |
|    GreaterThan    |            findByAgeGreaterThan            |                     …   where x.age > ?1                     |
| GreaterThanEqual  |         findByAgeGreaterThanEqual          |                    …   where x.age >= ?1                     |
|       After       |            findByStartDateAfter            |                  …   where x.startDate > ?1                  |
|      Before       |           findByStartDateBefore            |                  …   where x.startDate < ?1                  |
|      IsNull       |              findByAgeIsNull               |                   …   where x.age is null                    |
| IsNotNull,NotNull |            findByAge(Is)NotNull            |                   …   where x.age not null                   |
|       Like        |            findByFirstnameLike             |                …   where x.firstname like ?1                 |
|      NotLike      |           findByFirstnameNotLike           |              … where   x.firstname not like ?1               |
|   StartingWith    |        findByFirstnameStartingWith         | …   where x.firstname like ?1 (parameter bound with appended %) |
|    EndingWith     |         findByFirstnameEndingWith          | …   where x.firstname like ?1 (parameter bound with prepended %) |
|    Containing     |         findByFirstnameContaining          | …   where x.firstname like ?1 (parameter bound wrapped in %) |
|      OrderBy      |        findByAgeOrderByLastnameDesc        |        …   where x.age = ?1 order by x.lastname desc         |
|        Not        |             findByLastnameNot              |                  …   where x.lastname <> ?1                  |
|        In         |        findByAgeIn(Collection ages)        |                    …   where x.age in ?1                     |
|       NotIn       |       findByAgeNotIn(Collection age)       |                  …   where x.age not in ?1                   |
|       TRUE        |             findByActiveTrue()             |                  …   where x.active = true                   |
|       FALSE       |            findByActiveFalse()             |                  …   where x.active = false                  |
|    IgnoreCase     |         findByFirstnameIgnoreCase          |           …   where UPPER(x.firstame) = UPPER(?1)            |



#### 4、SQL 语句查询

- 特有的查询：需要在**Dao接口上配置方法**
- 在新添加的方法上，使用**`@Query`注解的形式配置SQL查询语句**，还需设置`nativeQuery`属性

```java
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 使用SQL查询全部客户/模糊查询
     * nativeQuery：默认false为JPQL查询，true为SQL查询
     */
    //@Query(value = "select * from cst_customer", nativeQuery = true)
    @Query(value = "select * from cst_customer where cust_name like ?1", nativeQuery = true)
    List<Object[]> findBySQL(String name);
}

```

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")//locations可省略
public class JPQLTest {

    @Autowired
    private CustomerDao customerDao;
    @Test
    public void testFindBySQL() {
        List<Object[]> list = customerDao.findBySQL("传智播客%");
        for (Object[] obj : list) {
            System.out.println(Arrays.toString(obj));
        }
    }
}

```





### 4.6 Specification 动态查询

> 以下是继承了JpaSpecificationExecutor接口后获得的

Spring Data JPA中可以通过`JpaSpecificationExecutor`接口查询。相比JPQL，其优势是类型安全，更加的面向对象。

- `T findOne(Specification<T> spec);`  //查询单个对象
- `List<T> findAll(Specification<T> spec);`  //查询列表
- `Page<T> findAll(Specification<T> spec, Pageable pageable);`
  - Pageable：分页参数
  - 返回值：Spring Data JPA提供的分页Bean
- `List<T> findAll(Specification<T> spec, Sort sort);`//排序查询
- `long count(Specification<T> spec);`//统计查询

对于`JpaSpecificationExecutor`，这个接口基本是围绕着`Specification`接口来定义的。我们可以简单的理解为，**`Specification`构造的就是查询条件**。

```java
/**
  *	root	：Root接口，代表查询的根对象，可以通过root获取实体中的属性
  *	query	：代表一个顶层查询对象，用来自定义查询（用的少，但是很强大）
  *	cb		：用来构建查询，此对象里有很多条件方法
  **/
public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);

```

```java
/**
 * 1.实现Specification接口（提供泛型：查询的对象类型）
 * 2.实现toPredicate方法（构造查询条件）
 * 3.需要借助方法参数中的两个参数（
 * root：获取需要查询的对象属性（不是数据库中字段名！！）
 * CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 单条件查询，根据客户名称查询单个客户对象（equal）
     */
    @Test
    public void testFindOne1() {
        Specification<Customer> specification = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                //第1个参数为比较的属性（Path对象）；第2个参数为要比较的值
                return criteriaBuilder.equal(custName, "博学谷");
            }
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * 多条件查询，根据客户名和客户所属行业查询单个客户（equal，and）
     */
    @Test
    public void testFindOne2() {

        Specification<Customer> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Path<Object> custName = root.get("custName");
            Path<Object> custIndustry = root.get("custIndustry");

            Predicate predicate1 = criteriaBuilder.equal(custName, "传智播客");
            Predicate predicate2 = criteriaBuilder.equal(custIndustry, "IT");
            //组合谓词
            return criteriaBuilder.and(predicate1, predicate2);//还有or等等方法来组合
        };
        Customer customer = customerDao.findOne(specification);
        System.out.println(customer);
    }

    /**
     * 普通查询；排序查询；分页查询，根据客户名模糊匹配查询客户列表（like）；
     * equal ：直接的到path对象（属性），然后进行比较即可
     * gt，lt,ge,le,like : 得到Path对象，根据Path指定比较的参数类型，再去进行比较
     */
    @Test
    public void testFindAll1() {
        Specification<Customer> specification = ((root, criteriaQuery, criteriaBuilder) -> {
            Path<Object> custName = root.get("custName");
            return criteriaBuilder.like(custName.as(String.class), "%传智%");//或在获取Path时直接封装为String类型
        });
        //普通查询
        List<Customer> customerList1 = customerDao.findAll(specification);

        //排序查询
        //第一个参数：排序的顺序（倒序，正序）；第二个参数：排序的属性名称（不是字段名！）
        Sort sort = new Sort(Sort.Direction.DESC, "custId");
        List<Customer> customerList2 = customerDao.findAll(specification, sort);

        //分页查询
        Pageable pageable = PageRequest.of(0,2);//页码（从0开始），每页显示条数
        Page<Customer> customerPage = customerDao.findAll(specification, pageable);
        System.out.println("总记录数:" + customerPage.getTotalElements());
        System.out.println("总页数:" + customerPage.getTotalPages());
        System.out.println("分页查询的数据:" + customerPage.getContent());
    }


    /**
	 * Specification的多表查询
	 */
    @Test
    public void testFind() {
        Specification<LinkMan> specification = (root, criteriaQuery, criteriaBuilder) ->  {
                //Join代表链接查询，通过root对象获取
                //创建的过程中，第一个参数为关联对象的属性名称，第二个参数为连接查询的方式（inner，left，right）
                //JoinType.LEFT : 左外连接,JoinType.INNER：内连接,JoinType.RIGHT：右外连接
                Join<LinkMan, Customer> join = root.join("customer",JoinType.INNER);
                return criteriaBuilder.like(join.get("custName").as(String.class),"传智播客1");
            }
        };
        List<LinkMan> list = linkManDao.findAll(specification);
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
    }
}

```

> 方法对应关系
>
> | 方法名称                    | Sql对应关系            |
> | --------------------------- | ---------------------- |
> | equle                       | filed =   value        |
> | gt（greaterThan ）          | filed   > value        |
> | lt（lessThan ）             | filed   < value        |
> | ge（greaterThanOrEqualTo ） | filed   >= value       |
> | le（ lessThanOrEqualTo）    | filed   <= value       |
> | notEqule                    | filed   != value       |
> | like                        | filed   like value     |
> | notLike                     | filed   not like value |



### 4.7 多表操作

在实际开发中，我们数据库的表难免会有相互的关联关系，在操作表的时候就有可能会涉及到多张表的操作。而在这种实现了ORM思想的框架中（如JPA），可以让我们通过操作实体类就实现对数据库表的操作。所以今天我们的学习重点是：掌握配置实体之间的关联关系。

- 第一步：首先确定两张表之间的关系。（如果关系确定错了，后面做的所有操作就都不可能正确。）
- 第二步：在数据库中实现两张表的关系
- 第三步：在实体类中描述出两个实体的关系
- 第四步：配置出实体类和数据库表的关系映射（重点）



#### 1、一对多

案例：客户和联系人的案例（一对多关系，一个客户可以具有多个联系人，一个联系人从属于一家公司）

- 客户：一家公司
- 联系人：这家公司的员工

分析步骤：

1. 明确表关系：一对多关系
2. 确定表关系（外键）
   - **主表**：客户表
   - **从表**：联系人表，在从表添加**外键**
3. 编写实体类，再实体类中描述表关系（**包含关系**）
   - 客户：再客户的实体类中包含一个联系人的集合
   - 联系人：在联系人的实体类中包含一个客户的对象
4. 配置映射关系
   - 使用 **JPA 注解配置一对多映射关系**

```java
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; //客户的主键

    
    @OneToMany(mappedBy = "customer")//主表放弃维护外键
    private Set<LinkMan> linkMans = new HashSet<>();

    @Column(name = "cust_name")
    private String custName;//客户名称
    
    ...
}

```

```java
@Entity
@Table(name = "cst_linkman")
public class LinkMan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lkm_id")
    private Long lkmId;

    @ManyToOne
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;

    @Column(name = "lkm_name")
    private String lkmName;
    ...
}

```

------

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class One2ManyTest {

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LinkManDao linkManDao;

    @Test
    @Transactional//开启事务
    @Rollback(false)//不回滚
    public void testSave() {
        Customer customer = new Customer();
        customer.setCustName("腾讯");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小马");

        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
    
    @Test
    @Transactional
    @Rollback(false)//设置为不回滚
    public void testDelete() {
        customerDao.delete(3L);
    }
}

```

**删除操作的说明如下**

- 删除从表数据：可以随时任意删除

- 删除主表数据：

  - 没有从表数据引用：随便删

  - 有从表数据：

    - 在默认情况下，它会把外键字段置为null，然后删除主表数据。如果在数据库的表结构上，外键字段有非空约束，默认情况就会报错了

    - 如果配置了放弃维护关联关系的权利，则不能删除（与外键字段是否允许为null，没有关系）因为在删除时，它根本不会去更新从表的外键字段了

    - 如果还想删除，使用级联删除引用（在实际开发中，级联删除请慎用！(在一对多的情况下)）

      

**级联**：操作一个对象的同时操作他的关联对象

- 级联操作：
  1.需要**区分操作主体**
  2.需要在操作主体的实体类上（主表或维护表），添加级联属性`cascade`（需要添加到多表映射关系的注解上）
- 级联添加，案例：当我保存一个客户的同时保存联系人（在代码中只需保存客户！）
- 级联删除，案例：当我删除一个客户的同时删除此客户的所有联系人，有中间表会先删除中间表（在代码中只需删除客户！）



#### 2、多对多

案例：用户和角色（多对多关系）

分析步骤：

1. 明确表关系：多对多关系
2. 确定表关系（中间表）
3. 编写实体类，再实体类中描述表关系（**包含关系**）
   - 用户：包含角色的集合
   - 角色：包含用户的集合
4. 配置映射关系
   - 使用 **JPA 注解配置多对多映射关系**

```java
@Entity
@Table//不配置name则为类目首字母小写
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column//不配置字段名则为属性名
    private Long userId;

    @ManyToMany
    //维护中间表
    @JoinTable(
        name = "sys_user_role",
        //当前对象在中间表中的外键
        joinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "userId")},
        //对方对象在中间表中的外键
        inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "roleId")}
    )
    private Set<Role> roles = new HashSet<>();
    ...
}

```

```java
@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long roleId;
    
    @ManyToMany(mappedBy = "roles")//被维护表放弃维护中间表！
    private Set<User> users = new HashSet<>();
    ...
}

```

其他删除/级联等同一对多，删除时会先删除中间表数据



#### 3、对象导航查询

对象图导航检索方式是根据已经加载的对象，导航到他的关联对象。它利用类与类之间的关系来检索对象。例如：我们通过ID查询方式查出一个客户，可以调用Customer类中的getLinkMans()方法来获取该客户的所有联系人。对象导航查询的使用要求是：两个对象之间必须存在关联关系。

对象导航查询

- **从一方查询多方：默认延迟加载**
- **从多方查询一方：默认立即加载**

可以修改配置将其改为立即加载（不推荐！），**fetch配置在多表关系中主体（或一方或多方）的注解上**，查看3.3



#### 4、Specification的多表查询

代码查看4.6







# 2 Spring Data Redis

> Spring-data-redis是spring大家族的一部分，提供了在srping应用中通过简单的配置访问redis服务，对reids底层开发包(Jedis,  JRedis, and RJC)进行了高度封装，RedisTemplate提供了redis各种操作、异常处理及序列化，支持发布订阅，并对spring 3.1 cache进行了实现

spring-data-redis针对jedis提供了如下功能：

- **连接池自动管理**，提供了一个高度封装的“RedisTemplate”类

- 针对jedis客户端中大量api进行了归类封装，将同一类型操作封装为**operation接口**

  redis存储的是：**key,value格式**的数据，其中**key都是字符串**，**value有5种不同的数据结构**

  - ValueOperations：简单字符串的操作
  - ListOperations：针对List类型的数据操作
  - SetOperations：针对Sets即set类型数据操作
  - ZSetOperations：针对Sorted Sets即排序的set类型数据操作
  - HashOperations：针对Hashes即map类型的数据操作



## 1 环境搭建

- 首先要开启Redis服务

- Maven依赖引入（Spring相关、JUnit），还需要 Jedis 和 SpringDataRedis 依赖

  ```xml
  <!-- 缓存 -->
  <dependency> 
      <groupId>redis.clients</groupId> 
      <artifactId>jedis</artifactId> 
      <version>2.8.1</version> 
  </dependency> 
  <dependency> 
      <groupId>org.springframework.data</groupId> 
      <artifactId>spring-data-redis</artifactId> 
      <version>1.7.2.RELEASE</version> 
  </dependency>	
  
  ```

- resources/properties目录下的`redis-config.properties`配置文件

  ```properties
  # Redis settings 
  # server IP 
  redis.host=127.0.0.1
  # server port 
  redis.port=6379
  # server pass 
  redis.pass=
  # use dbIndex 
  redis.database=0
  # 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例 
  redis.maxIdle=300
  # 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间(毫秒)，则直接抛出JedisConnectionException；  
  redis.maxWait=3000
  # 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的  
  redis.testOnBorrow=true
  
  ```

- resources/spring目录下的Spring配置文件`applicationContext-redis.xml`

  ```xml
  <context:property-placeholder location="classpath*:properties/*.properties" />   
  <!-- redis 相关配置 --> 
  <bean id="poolConfig" class="redis.clients.jedis.JedisPoolConfig">  
      <property name="maxIdle" value="${redis.maxIdle}" />   
      <property name="maxWaitMillis" value="${redis.maxWait}" />  
      <property name="testOnBorrow" value="${redis.testOnBorrow}" />  
  </bean>  
  
  <bean id="JedisConnectionFactory" class="org.springframework.data.redis.connection.jedis.JedisConnectionFactory" 
        p:host-name="${redis.host}" p:port="${redis.port}" p:password="${redis.pass}" p:pool-config-ref="poolConfig"/>  <!--p为p命名空间，可以不用写property。下面的就是不用p命名空间-->
  
  <bean id="redisTemplate" class="org.springframework.data.redis.core.RedisTemplate">  
      <property name="connectionFactory" ref="JedisConnectionFactory" />  
  </bean>  
  
  ```

## 2 不同类型value

- String

  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:spring/applicationContext-redis.xml")//可以省略locations
  public class TestString {
      @Autowired
      private RedisTemplate redisTemplate;
  
      @Test
      public void setValue(){
          redisTemplate.boundValueOps("StringName").set("itcast");
      }
  
      @Test
      public void getValue(){
          String name = (String) redisTemplate.boundValueOps("StringName").get();
          Assert.assertEquals("itcast",name);//name值为null
      }
  
      @Test
      public void deleteValue(){
          redisTemplate.delete("StringName");//还可以传入集合，删除集合中所有key对应的key-value
      }
  }
  
  ```

- List

  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:spring/applicationContext-redis.xml")//可以省略locations
  public class TestList {
  
      @Autowired
      private RedisTemplate redisTemplate;
  
      @Test
      public void rightPushValue(){
          redisTemplate.boundListOps("ListName").rightPushAll(new Object[]{"张三", "李四"});
          redisTemplate.boundListOps("ListName").rightPush("王五");
          //一个一个压栈：[张三, 李四, 王五]
      }
  
      @Test
      public void leftPushValue(){
          redisTemplate.boundListOps("ListName").leftPushAll(new Object[]{"zhangsan", "lisi"});
          redisTemplate.boundListOps("ListName").leftPush("wangwu");
          //一个一个压栈：[wangwu, lisi, zhangsan]
      }
  
      @Test
      public void getValueByIndex(){
          String  listName = (String) redisTemplate.boundListOps("ListName").index(1);//下标从0开始
          System.out.println(listName);
      }
      
      @Test
      public void getValue(){
          List list = redisTemplate.boundListOps("ListName").range(0, -1);
          System.out.println(list);
          list.forEach(System.out::println);
      }
      
      @Test
      public void removeValue(){
          redisTemplate.boundListOps("ListName").remove(2,"李四");//第一个为要删除的数量
      }
      
      @Test
      public void deleteValue(){
          redisTemplate.delete("ListName");//删除ListName对应的List集合。直接打印显示“[]”。还可以传入集合
      }
  }
  
  ```

- Set

  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:spring/applicationContext-redis.xml")//可以省略locations
  public class TestSet {
      @Autowired
      private RedisTemplate redisTemplate;
  
      @Test
      public void addValue(){
          redisTemplate.boundSetOps("SetName").add("张三");
          redisTemplate.boundSetOps("SetName").add("李四");
          redisTemplate.boundSetOps("SetName").add("王五");
      }
  
      @Test
      public void getValue(){
          Set setName = redisTemplate.boundSetOps("SetName").members();
          System.out.println(setName);
                  setName.forEach(System.out::println);//方法引用，setName.forEach(o -> System.out.println(o))
      }
  
      @Test
      public void removeValue(){
          redisTemplate.boundSetOps("SetName").remove("张三");//移除Set其中一个
      }
  
      @Test
      public void deleteValue(){
          redisTemplate.delete("SetName");//删除SetName对应的Set集合。直接打印显示“[]”。还可以传入集合
      }
  }
  
  
  ```

- ZSet（方法和Set基本一致，除了查找有个`range()`方法）

  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:spring/applicationContext-redis.xml")//可以省略locations
  public class TestZSet {
      @Autowired
      private RedisTemplate redisTemplate;
  
      @Test
      public void setValue(){
          redisTemplate.boundZSetOps("ZSetName").add("张三",0);
          redisTemplate.boundZSetOps("ZSetName").add("李四",2);
          redisTemplate.boundZSetOps("ZSetName").add("王五",-2);//由小到大排序
      }
  
      @Test
      public void getValue(){
          Set setName = redisTemplate.boundZSetOps("ZSetName").range(0,-1);
          setName.forEach(System.out::println);//方法引用，setName.forEach(o -> System.out.println(o))lambda的简写
      }
  
      @Test
      public void removeValue(){
          redisTemplate.boundZSetOps("ZSetName").remove("张三");//移除ZSet其中一个
      }
  
      @Test
      public void deleteValue(){
          redisTemplate.delete("ZSetName");//删除ZSetName对应的ZSet集合。直接打印显示“[]”。还可以传入集合
      }
  }
  
  ```

- Hash（即map类型，用得比较多）

  ```java
  @RunWith(SpringJUnit4ClassRunner.class)
  @ContextConfiguration("classpath:spring/applicationContext-redis.xml")//可以省略locations
  public class TestHash {
  
      @Autowired
      private RedisTemplate redisTemplate;
  
      @Test
      public void putValue(){
          Map<String,String> map = new HashMap<>();
          map.put("a","张三");
          map.put("b","李四");
          redisTemplate.boundHashOps("HashName").putAll(map);
          redisTemplate.boundHashOps("HashName").put("c","王五");
  
      }
  
      @Test
      public void getValueByKey(){
          String o = (String) redisTemplate.boundHashOps("HashName").get("c");
          System.out.println(o);
      }
  
      @Test
      public void getValue(){
          Map hashName = redisTemplate.boundHashOps("HashName").entries();
          System.out.println(hashName);
          hashName.forEach((key, value) -> System.out.println(key+"=="+value));
      }
  
      @Test
      public void removeValue(){
          redisTemplate.boundHashOps("HashName").delete("c");//注意，此处不再是remove方法，是delete方法
  
      }
  
      @Test
      public void deleteValue(){
          redisTemplate.delete("HashName");//删除HashName对应的Map集合。直接打印显示“{}”。还可以传入集合
      }
  }
  ```



# 3 Spring Data MongoDB

1. 起步依赖

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-data-mongodb</artifactId>
   </dependency>
   ```

2. application.yml

   ```yaml
   spring:
     data:
       mongodb:
         uri: mongodb://root:123@localhost:27017
         database: xc_cms
   ```

3. 启动类注解`@EntityScan("com.xuecheng.domain")`扫描实体类

4. MongoDB的实体类注解例如：`@Document(collection = "cms_page")`集合，`@Id`主键，没指定值则自动生成

   ```java
   @Data
   @Document(collection = "cms_page")
   public class CmsPage {
       //站点ID
       private String siteId;
       //页面ID
       @Id
       private String pageId;
       //页面名称
       private String pageName;
       //......
   
   }
   ```

5. 类似Spring Data JPA，Repository接口只需继承，指定domian和主键类型

   ```java
   public interface CmsRepository extends MongoRepository<CmsPage,String> {
   }
   ```

## 1 基本CRUD

继承了MongoRepository之后获得方法

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsPageRepositoryTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    //查询所有
    @Test
    public void testFindAll(){
        List<CmsPage> all = cmsPageRepository.findAll();
        all.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testFindPage(){
        //参数返回的时Pageable对象
        Page<CmsPage> all = cmsPageRepository.findAll(PageRequest.of(0, 10));//页码（从0开始），每页数据个数
        System.out.println("总记录数"+all.getTotalElements());
        System.out.println("总页数"+all.getTotalPages());
        all.getContent().forEach(System.out::println);
    }

    //自定义条件查询测试
    @Test
    public void testFindAllByExample() {
        //分页参数
        int page = 0;//从0开始
        int size = 10;
        Pageable pageable = PageRequest.of(page,size);

        //条件值对象
        CmsPage cmsPage= new CmsPage();
        //要查询5a751fab6abb5044e0d19ea1站点的页面
        cmsPage.setSiteId("5b30b052f58b4411fc6cb1cf");
        //设置模板id条件
        cmsPage.setTemplateId("5ad9a24d68db5239b8fef199");
        //设置页面别名
        cmsPage.setPageAliase("轮播");
        
        //条件匹配器（多用于模糊查询等），指定实体类中属性名称和其匹配方式。不指定则为精确匹配
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
            .withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        //ExampleMatcher.GenericPropertyMatchers.contains() 包含关键字
        //ExampleMatcher.GenericPropertyMatchers.startsWith()//前缀匹配
        //ExampleMatcher.GenericPropertyMatchers.exact()//如果不设置匹配器默认精确匹配，这样设置也是精确匹配
        
        //定义Example条件对象
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        all.getContent().forEach(System.out::println);
    }

    //修改
    //Optional是jdk1.8引入的类型，Optional是一个容器对象，它包括了我们需要的对象，使用isPresent方法判断所包
    //含对象是否为空，isPresent方法返回false则表示Optional包含对象为空，否则可以使用get()取出对象进行操作。
    //Optional的优点是：
    //1、提醒你非空判断。
    //2、将对象非空检测标准化。
    @Test
    public void testUpdate() {
        //查询对象
        Optional<CmsPage> optional = cmsPageRepository.findById("5b4b1d8bf73c6623b03f8cec");
        if(optional.isPresent()){
            CmsPage cmsPage = optional.get();
            //设置要修改值
            cmsPage.setPageAliase("test01");
            //修改
            CmsPage save = cmsPageRepository.save(cmsPage);
            System.out.println(save);
        }
    }

    //删除
    @Test
    public void testDelete() {
        cmsPageRepository.deleteById("5b17a2c511fe5e0c409e5eb3");
    }

    //添加
    @Test
    public void testInsert(){
        //定义实体类
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);
    }
}
```

## 2 方法名称规则查询

同Spring Data JPA一样Spring Data mongodb也提供自定义方法的规则，如下：按照findByXXX，findByXXXAndYYY、countByXXXAndYYY等规则定义方法，实现查询操作。

```java
public interface CmsPageRepository extends MongoRepository<CmsPage,String> {
    //根据页面名称查询
    CmsPage findByPageName(String pageName);
    //根据页面名称和类型查询
    CmsPage findByPageNameAndPageType(String pageName,String pageType);
    //根据站点和页面类型查询记录数
    int countBySiteIdAndPageType(String siteId,String pageType);
    //根据站点和页面类型分页查询
    Page<CmsPage> findBySiteIdAndPageType(String siteId,String pageType, Pageable pageable);
}
```



......





# 4 Lucene—全文检索技术

## 1 什么是全文检索

### 1.1 数据分类

- **结构化数据**：指具有固定格式或有限长度的数据，如数据库，元数据等
- **非结构化数据**：指不定长或无固定格式的数据，如邮件，word文档等磁盘上的文件

### 1.2 结构化数据搜索

​	常见的**结构化数据**也就是**数据库中**的数据。在数据库中搜索很容易实现，通常都是使用sql语句进行查询，而且能很快的得到查询结果（数据库中的数据存储是有规律的，有行有列而且数据格式、数据长度都是固定的）。

### 1.3 非结构化数据查询方法

- **顺序扫描法**(Serial Scanning)：如对于每一个文档，从头看到尾......

- **全文检索**(Full-text Search)：

  ​	将非结构化数据中的一部分信息提取出来，重新组织，使其变得有一定结构，然后对此有一定结构的数据进行搜索，从而达到搜索相对较快的目的。这部分**从非结构化数据中提取出的然后重新组织的信息**，我们称之**索引**。

  ​	例如：字典。字典的拼音表和部首检字表就相当于字典的索引，对每一个字的解释是非结构化的，如果字典没有音节表和部首检字表，在茫茫辞海中找一个字只能顺序扫描。然而字的某些信息可以提取出来进行结构化处理，比如读音，就比较结构化，分声母和韵母，分别只有几种可以一一列举，于是将读音拿出来按一定的顺序排列，每一项读音都指向此字的详细解释的页数。我们搜索时按结构化的拼音搜到读音，然后按其指向的页数，便可找到我们的非结构化数据——也即对字的解释。

  ​	**这种先建立索引，再对索引进行搜索的过程就叫全文检索(Full-text Search)。**虽然创建索引的过程也是非常耗时的，但是**索引**一旦创建就可以**多次使用**，全文检索主要处理的是查询，所以耗时间创建索引是值得的。

### 1.4 如何实现全文检索

​	可以使用Lucene实现全文检索。Lucene是apache下的一个开放源代码的全文检索引擎工具包。提供了完整的查询引擎和索引引擎，部分文本分析引擎。Lucene的目的是为软件开发人员提供一个简单易用的工具包，以方便的在目标系统中实现全文检索的功能。

### 1.5 全文检索的应用场景

​	对于**数据量大**、**数据结构不固定的数据**可采用全文检索方式搜索，比如百度、Google等**搜索引擎**、**论坛站内搜索**、**电商网站站内搜索**等。



## 2 Lucene实现全文检索的流程

![1549724395120](images/1549724395120.png)

- 绿色表示索引过程，对要搜索的原始内容进行索引构建一个索引库，索引过程包括：

  确定原始内容即要搜索的内容（原始文档）—>采集文档—>创建文档—>分析文档—>索引文档

- 红色表示搜索过程，从索引库中搜索内容，搜索过程包括：

  用户通过搜索界面—>创建查询—>执行搜索，从索引库搜索—>渲染搜索结果



### 2.1 创建索引

> 将用户要搜索的文档内容创建索引，索引存储在索引库（index）中

1. **获得原始文档**

   原始文档是指**要索引和搜索的内容**。原始内容包括互联网上的网页（爬虫）、数据库中的数据、磁盘上的文件等

2. **创建文档对象**

   获取原始内容的目的是为了索引，在索引前需要将原始内容创建成文档（**Document**），文档中包括**一个一个的域（Field，如文件名/文件内容/大小等）**，域中存储内容（key-value类型）

   注意：每个Document可以有多个Field，不同的Document可以有不同的Field，同一个Document可以有相同的Field（域名和域值都相同）。**每个文档都有一个唯一的编号，就是文档ID**。

3. **分析文档**

   将原始内容创建为包含域（Field）的文档（Document），需要再对域中的内容进行分析，分析的过程是经过对原始文档**提取单词**、将**字母转为小写**、**去除标点符号**、**去除停用词等**过程生成最终的语汇单元，可以将语汇单元理解为一个一个的单词。

   **每个单词叫做一个Term**，不同的域中拆分出来的相同的单词是不同的Term。**Term中**包含两部分一部分是**文档的域名**，另一部分是**单词的内容**。

4. **创建索引**

   对所有文档分析得出的语汇单元进行索引，索引的目的是为了搜索，最终要实现**只搜索被索引的语汇单元**从而**找到Document（文档）**

   注意：创建索引是对语汇单元索引，==**通过词语（内容）找文档**==，这种索引的结构叫==**倒排索引结构**==。

   传统方法是根据**文件找到该文件的内容**，在文件内容中匹配搜索关键字，这种方法是顺序扫描方法，数据量大、搜索慢。

   **倒排索引结构也叫反向索引结构，包括索引和文档两部分，索引即词汇表，它的规模较小，而文档集合较大。**

   ![1549726346135](images/1549726346135.png)

### 2.2 查询索引

> 查询索引也是搜索的过程。搜索就是用户输入关键字，从索引（index）中进行搜索的过程。根据关键字搜索索引，根据索引找到对应的文档，从而找到要搜索的内容（此处案例指磁盘上的文件）

1. **用户查询接口**

   全文检索系统提供用户搜索的界面供用户提交**搜索的关键字**，搜索完成展示搜索结果。

   > Lucene不提供制作用户搜索界面的功能，需要根据自己的需求开发搜索界面

2. **创建查询**

   用户输入查询关键字执行搜索之前需要先**构建一个查询对象**，查询对象中可以指定查询要搜索的**Field**文档域、**查询关键字**等，查询对象会生成具体的查询语法，例如：语法 “fileName:lucene”表示要搜索Field域的文件名为“lucene”的文档

3. **执行查询**

   搜索索引过程：根据查询语法**在倒排索引词典表中分别找出对应搜索词的索引**，从而找到索引所链接的**文档链表**。

   比如搜索语法为“fileName:lucene”表示搜索出fileName域中包含Lucene的文档。搜索过程就是**在索引上查找域为fileName，并且关键字为Lucene的Term，并根据Term找到文档id列表**。

4. **渲染结果**

   以一个友好的界面将查询结果展示给用户，用户根据搜索结果找自己想要的信息，为了帮助用户很快找到自己的结果，提供了很多展示的效果，比如搜索结果中将关键字高亮显示，百度提供的快照等。



## 3 Demo

> 实现一个文件的搜索功能，通过关键字搜索文件，凡是文件名或文件内容包括关键字的文件都需要找出来。还可以根据中文词语进行查询，并且需要支持多个条件查询。

1. Lucene是开发全文检索功能的工具包，从[官方网站](http://lucene.apache.org/)下载如`lucene-7.4.0`（要求JDK1.8以上），并解压

   其中core为核心包；analysis为分析包；queryparse为查询分析器

   需要使用的有：`lucene-core-7.4.0.jar` `lucene-analyzers-common-7.4.0.jar`，还需要`common-io`来操作文件

2. **创建索引**

   1. 创建一个java工程，并导入jar包

   2. 创建`Directory`对象，指定索引库的存放位置

   3. 创建一个`IndexWriterConfig`对象

   4. 创建一个`IndexWriter`对象。

   5. 创建`Document`对象

   6. 创建`Field`对象，将field**添加**到Document对象中

   7. 使用`IndexWriter`对象将`Document`对象写入索引库，此过程进行索引创建。并将索引和`Document`对象写入索引库。

   8. 关闭`IndexWriter`对象。

      ```java
      @Test
      public void createIndex() throws IOException {
          //1.指定索引库存放的路径，存入磁盘
          Directory directory = FSDirectory.open(new File("C:\\Develop\\lucene-7.4.0\\index").toPath());
          //索引库还可以存放到内存中
          //Directory directory = new RAMDirectory();
      
          //2.创建IndexWriterConfig对象，若不指定参数则为使用标准分析器
          IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());
          //3.创建IndexWriter对象
          IndexWriter indexWriter = new IndexWriter(directory,config);
          //原始文档的路径
          File dir = new File("F:\\00000\\0000000博学谷JavaEE\\5-流行框架\\1 lucene\\02.参考资料\\searchsource");
          File[] files = dir.listFiles();
          for (File file : files) {
              //文件名
              String fileName = file.getName();
              //文件内容
              String fileContent = FileUtils.readFileToString(file,"UTF-8");
              //文件路径
              String filePath = file.getPath();
              //文件的大小
              long fileSize  = FileUtils.sizeOf(file);
              //4.创建文件名域
              //第一个参数：域的名称；第二个参数：域的内容；第三个参数：是否存储
              Field fileNameField = new TextField("filename", fileName, Field.Store.YES);
              //文件内容域
              Field fileContentField = new TextField("content", fileContent, Field.Store.YES);
              //文件路径域（不分析、不索引、只存储）
              Field filePathField = new TextField("path", filePath, Field.Store.YES);
              //文件大小域
              Field fileSizeField = new TextField("size", fileSize + "", Field.Store.YES);
      
              //5.创建document对象
              Document document = new Document();
              document.add(fileNameField);
              document.add(fileContentField);
              document.add(filePathField);
              document.add(fileSizeField);
              //6.创建索引，并写入索引库
              indexWriter.addDocument(document);
          }
          //7.关闭Indexwriter对象
          indexWriter.close();
      }
      ```

3. 可以使用luke-7.4.0（利用JavaFx开发的应用，版本和lucene对应）查看index目录中索引库的信息，软件要求JDK9.0以上

4. **查询索引库**

   1. 创建一个`Directory`对象，也就是索引库存放的位置。

   2. 创建一个`IndexReader`对象，需要指定`Directory`对象。

   3. 创建一个`Indexsearcher`对象，需要指定`IndexReader`对象

   4. 创建一个`Query`对象`TermQuery`，指定查询的**域**和查询的**关键词**。

   5. 执行查询，得到`TopDocs`对象

   6. 返回查询结果。遍历查询结果并输出。

   7. 关闭`IndexReader`对象。

      ```java
      @Test
      public void searchIndex() throws Exception {
          //1.指定索引库存放的路径
          Directory directory = FSDirectory.open(new File("C:\\Develop\\lucene-7.4.0\\index").toPath());
          //2.创建IndexReader对象
          IndexReader indexReader = DirectoryReader.open(directory);
          //3.创建IndexSearcher对象
          IndexSearcher indexSearcher = new IndexSearcher(indexReader);
          //4.创建查询，指定查询的域和查询的关键词
          Query query = new TermQuery(new Term("content", "spring"));
          //执行查询
          //第一个参数是查询对象，第二个参数是查询结果返回的最大值
          TopDocs topDocs = indexSearcher.search(query, 10);
          //查询结果的总条数
          System.out.println("查询结果的总条数："+ topDocs.totalHits);
          //遍历查询结果
          //topDocs.scoreDocs存储了document对象的id
          for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
              //scoreDoc.doc属性就是document对象的id
              //根据document的id找到document对象
              Document document = indexSearcher.doc(scoreDoc.doc);
              System.out.println(document.get("filename"));
              //System.out.println(document.get("content"));
              System.out.println(document.get("path"));
              System.out.println(document.get("size"));
              System.out.println("-------------------------");
          }
          //关闭Indexreader对象
          indexReader.close();
      }
      
      ```



## 4 分析器

查看分析器的分词效果

1. 创建一个标准分析器对象`Analyzer`

2. 获得`TokenStream`对象

3. 添加一个引用，可以获得每个关键词

4. 将指针调整到列表的头部

5. 遍历关键词列表，通过`incrementToken`方法判断列表是否结束

6. 关闭`TokenStream`

   ```java
   @Test
   public void testTokenStream() throws Exception {
       //创建一个标准分析器对象，对中文分词只能按字分
       //Analyzer analyzer = new StandardAnalyzer();
       
       //创建一个IKAnalyzer分析器对象
       Analyzer analyzer = new IKAnalyzer();
       //获得tokenStream对象
       //第一个参数：Field，可以随便给一个（这里只是测试）；第二个参数：要分析的文本内容
       TokenStream tokenStream = analyzer.tokenStream("test", "The Spring Framework provides a comprehensive programming and configuration model.");
       //添加一个引用，可以获得每个关键词
       CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
       //添加一个偏移量的引用，记录了关键词的开始位置以及结束位置
       OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
       //将指针调整到列表的头部
       tokenStream.reset();
       //遍历关键词列表，通过incrementToken方法判断列表是否结束
       while(tokenStream.incrementToken()) {
           //关键词的起始位置
           System.out.println("start->" + offsetAttribute.startOffset());
           //取关键词，直接打印调用toString()方法
           System.out.println(charTermAttribute);
           //结束位置
           System.out.println("end->" + offsetAttribute.endOffset());
       }
       tokenStream.close();
   }
   ```



### 4.1 中文分析器

- Lucene自带中文分词器

  - StandardAnalyzer：单字分词
  - SmartChineseAnalyzer：对中文支持较好，但扩展性差，扩展词库，禁用词库和同义词库等不好处理

- **IKAnalyzer**

  1. 把`IK-Analyzer-1.0-SNAPSHOT.jar`包添加到工程中

  2. 把配置文件和扩展词典和停用词词典添加到classpath下

     注意：hotword.dic和ext_stopword.dic文件的格式为UTF-8，注意是无BOM 的UTF-8 编码

  3. 使用自定义分析器`IndexWriterConfig config = new IndexWriterConfig(new IKAnalyzer());`



## 5 索引库的维护（Field及增删改）

### 5.1 Field域的属性

- **是否分析**：是否对域的内容进行**分词**处理。前提是我们要对域的内容进行查询。

- **是否索引**：将Field分析后的词或整个Field值进行索引，只有索引方可搜索到。

  比如：商品名称、商品简介分析后进行索引，订单号、身份证号不用分析但也要索引，这些将来都要作为**查询条件**。

- **是否存储**：将Field值存储在文档中，存储在文档中的Field才可以从Document中获取。

  比如：商品名称、订单号，凡是将来要从Document中获取的Field都要存储。**是否要将内容展示给用户**。

|                           Field类                            |        数据类型        | Analyzed是否分析 | Indexed是否索引 | Stored是否存储 |                             说明                             |
| :----------------------------------------------------------: | :--------------------: | :--------------: | :-------------: | :------------: | :----------------------------------------------------------: |
|              StoredField(FieldName, FieldValue)              | 重载方法，支持多种类型 |        N         |        N        |       Y        | 这个Field用来构建不同类型Field   不分析，不索引，但要Field存储在文档中 |
|            LongPoint(String name, long... point)             |         Long型         |        Y         |        Y        |       N        | 可以使用LongPoint、IntPoint等类型存储数值类型的数据。让数值类型可以进行索引。但是不能存储数据，如果想存储数据还需要使用StoredField。 |
|       StringField(FieldName,   FieldValue,Store.YES))        |         字符串         |        N         |        Y        |      Y或N      | 这个Field用来构建一个字符串Field，但是不会进行分析，会将整个串存储在索引中，比如(订单号,姓名等)   是否存储在文档中用Store.YES或Store.NO决定 |
| TextField(FieldName, FieldValue, Store.NO)或   TextField(FieldName, reader) |    字符串   或   流    |        Y         |        Y        |      Y或N      | 如果是一个Reader, lucene猜测内容比较多,会采用Unstored的策略. |

修改之前的创建索引中方法

```java
//不同的document可以有不同的域，同一个document可以有相同的域。
document.add(new TextField("filename", "新添加的文档", Field.Store.YES));
document.add(new TextField("content", "新添加的文档的内容", Field.Store.NO));
//LongPoint创建索引
document.add(new LongPoint("size", 1000l));
//StoreField存储数据
document.add(new StoredField("size", 1000l));
//不需要创建索引的就使用StoreField存储
document.add(new StoredField("path", "d:/temp/1.txt"));
```



### 5.2 索引库增删改

```java
public class IndexManager {

    private IndexWriter indexWriter;

    @Before
    public void init() throws Exception {
        //创建一个IndexWriter对象，需要使用IKAnalyzer作为分析器
        indexWriter =
                new IndexWriter(FSDirectory.open(new File("C:\\temp\\index").toPath()),
                        new IndexWriterConfig(new IKAnalyzer()));
    }

    @Test
    public void addDocument() throws Exception {
        //创建一个IndexWriter对象，需要使用IKAnalyzer作为分析器
        IndexWriter indexWriter =
                new IndexWriter(FSDirectory.open(new File("C:\\temp\\index").toPath()),
                new IndexWriterConfig(new IKAnalyzer()));
        //创建一个Document对象
        Document document = new Document();
        //向document对象中添加域
        document.add(new TextField("name", "新添加的文件", Field.Store.YES));
        document.add(new TextField("content", "新添加的文件内容", Field.Store.NO));
        document.add(new StoredField("path", "c:/temp/helo"));
        // 把文档写入索引库
        indexWriter.addDocument(document);
        //关闭索引库
        indexWriter.close();
    }

    //将索引目录的索引信息全部删除，直接彻底删除，无法恢复！！！！！
    @Test
    public void deleteAllDocument() throws Exception {
        //删除全部文档
        indexWriter.deleteAll();
        //关闭索引库
        indexWriter.close();
    }

    @Test
    public void deleteDocumentByQuery() throws Exception {
        indexWriter.deleteDocuments(new Term("name", "apache"));//参数为Query也可以，好像只是再封装了一层
        indexWriter.close();
    }

    //原理是先删除！！！后添加！！！
    @Test
    public void updateDocument() throws Exception {
        //创建一个新的文档对象
        Document document = new Document();
        //向文档对象中添加域
        document.add(new TextField("name", "更新之后的文档", Field.Store.YES));
        document.add(new TextField("name1", "更新之后的文档2", Field.Store.YES));
        document.add(new TextField("name2", "更新之后的文档3", Field.Store.YES));
        //更新操作
        indexWriter.updateDocument(new Term("name", "spring"), document);
        //关闭索引库
        indexWriter.close();
    }
}
```



### 5.3 索引库查询

对要搜索的信息创建Query查询对象，Lucene会根据Query查询对象生成最终的查询语法，类似关系数据库Sql语法一样Lucene也有自己的查询语法，比如：“name:lucene”表示查询Field的name为“lucene”的文档信息。可通过两种方法创建查询对象：

- 使用Lucene提供Query子类

  - **TermQuery**，根据**域**和**关键词**进行查询，**TermQuery不使用分析器所以建议匹配不分词的Field域查询**，比如订单号
  - LongPoint.**newRangeQuery**，**数值范围查询**

- 使用**QueryParse**解析查询表达式（**先分词**，再查询，需要指定**默认查找Field**和**分析器**）

  - 通过QueryParser也可以创建Query，QueryParser提供一个Parse方法，此方法可以直接根据查询语法来查询。Query对象执行的查询语法可通过`System.out.println(query);`查询。

    需要使用到分析器。建议创建索引时使用的分析器和查询索引时使用的分析器要一致。

    需要加入queryParser依赖的`lucene-queryparser-7.4.0.jar`包

```java
public class SearchIndex {
    private IndexReader indexReader;
    private IndexSearcher indexSearcher;
    @Before
    public void init() throws Exception {
        indexReader = DirectoryReader.open(FSDirectory.open(new File("C:\\temp\\index").toPath()));
        indexSearcher = new IndexSearcher(indexReader);
    }

    @Test
    public void testTermQuery() throws Exception {
        //创建一个Query对象
        Query query = new TermQuery(new Term("content", "lucene"));
        printResult(query);
    }
    
    
    //数值范围查询
    @Test
    public void testRangeQuery() throws Exception {
        //创建一个Query对象
        Query query = LongPoint.newRangeQuery("size", 0L, 100L);
        printResult(query);
    }

    @Test
    public void testQueryParser() throws Exception {
        //创建一个QueryPaser对象，两个参数
        //参数1：默认搜索域，参数2：分析器对象
        QueryParser queryParser = new QueryParser("name", new IKAnalyzer());
        //使用QueryPaser对象创建一个Query对象
        Query query = queryParser.parse("lucene是一个Java开发的全文检索工具包");
        //执行查询
        printResult(query);
    }


//========================================================================================
    private void printResult(Query query) throws Exception {
        //执行查询
        TopDocs topDocs = indexSearcher.search(query, 10);
        System.out.println("总记录数：" + topDocs.totalHits);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc doc:scoreDocs){
            //取文档id
            int docId = doc.doc;
            //根据id取文档对象
            Document document = indexSearcher.doc(docId);
            System.out.println(document.get("name"));
            System.out.println(document.get("path"));
            System.out.println(document.get("size"));
            //System.out.println(document.get("content"));
            System.out.println("-----------------寂寞的分割线");
        }
        indexReader.close();
    }
}
```







# 5 Spring Data Elasticsearch

> Elastic：[ɪ'læstɪk]。adj.橡皮圈（或带）的；有弹性的；有弹力的；灵活的

## 1 Elasticsearch 简介

### 1.1 简介

[ElasticSearch](https://www.elastic.co/cn/products/elasticsearch)（还有中文文档，与阿里云合作）是一个**基于Lucene的搜索服务器**。它提供了一个**分布式多用户能力**的全文搜索引擎，基于**RESTful web接口**。Elasticsearch是用**Java开发**的，并作为Apache许可条款下的**开放源码**发布，是当前流行的企业级搜索引擎。设计用于云计算中，能够达到**实时搜索**，稳定，可靠，快速，安装**使用方便**。Github、Stack Overflow等都在使用。

突出优点：

* **速度快**：通过有限状态转换器实现了用于全文检索的**倒排索引**，实现了用于存储数值数据和地理位置数据的 BKD 树，以及用于分析的列存储
* **可扩展性**：可以在笔记本电脑上运行。 也可以在**承载了 PB 级数据的成百上千台服务器上运行**。
* **弹性**：Elasticsearch 运行在一个分布式环境中，检测硬件故障、网络分割故障并确保您的**集群（和数据）的安全性和可用性**

### 1.2 原理—倒排索引

下图是ElasticSearch的索引结构，下边黑色部分是物理结构，上边黄色部分是逻辑结构，逻辑结构也是为了更好的去描述ElasticSearch的工作原理及去使用物理结构中的索引文件。

![1551515215556](images/1551515215556.png)

逻辑结构部分是一个**倒排索引表**：

1、将要搜索的**文档内容分词**，所有不重复的词组成**分词列表Term**。

2、将搜索的**文档最终以Document方式存**储起来。

3、每个词**Term和Docment都有关联**。

如下：

![1551515296662](images/1551515296662.png)

如果我们想搜索`quick brown` ，我们只需要查找包含每个词条的文档：

![1551515315036](images/1551515315036.png)

两个文档都匹配，但是第一个文档比第二个匹配度更高。如果我们使用仅计算匹配词条数量的简单相似性算法 ，那么我们可以说，对于我们查询的相关性来讲，第一个文档比第二个文档更佳。

### 1.3 RESTful应用方法

Elasticsearch 使用的是标准的 RESTful 风格的 API 和 JSON。此外，我们还构建和维护了很多其他语言的客户端，例如 Java、Python、.NET、SQL 和 PHP。下图是Elasticsearch 在项目中的应用方式：

![1551515502082](images/1551515502082.png)

## 2 Elasticsearch 安装

### 2.1 安装

安装配置：

* 新版本要求至少jdk1.8以上
* 支持tar、zip、rpm等多种安装方式（在windows下开发建议使用ZIP安装方式），详细[参见](https://www.elastic.co/cn/downloads/elasticsearch#ga-release)

* 支持Docker方式安装：详细[参见](https://www.elastic.co/guide/en/elasticsearch/reference/current/install-elasticsearch.html)

安装步骤：

1. 本次使用Windows环境的Elasticsearch 6.2.1版本，下载好zip并解压即可（可以改名，为了之后的集群做区别）
   - **bin**：脚本目录，包括：启动、停止等可执行脚本
   - **config**：配置文件目录
   - **data**：索引目录，存放索引文件的地方。可能需要手动创建该目录
   - logs：日志目录
   - modules：模块目录，包括了es的功能模块（需要Node.js环境）
   - plugins :插件目录，es支持插件机制



### 2.2 配置文件

ES的配置文件的地址根据安装形式的不同而不同：

* 使用RPM安装，配置文件在/etc/elasticsearch下。
* 使用zip、tar安装，配置文件的地址在安装目录的**config**下。
* 使用MSI安装，配置文件的地址在安装目录的config下，并且会自动将config目录地址写入环境变量ES_PATH_CONF。

配置文件如下：

* **elasticsearch.yml** ： 用于配置Elasticsearch运行参数 
* jvm.options ： 用于配置Elasticsearch JVM设置
* log4j2.properties： 用于配置Elasticsearch日志



#### 2.2.1 elasticsearch.yml

配置格式是YAML，可以采用如下两种方式：

* 方式1：层次方式`path: data: /var/lib/elasticsearch logs: /var/log/elasticsearch`
* 方式2：属性方式`path.data: /var/lib/elasticsearch path.logs: /var/log/elasticsearch`

本项目采用方式2，例子如下：

```yaml
#配置elasticsearch的集群名称，默认是elasticsearch。
cluster.name: xuecheng
#节点名，通常一台物理服务器就是一个节点，es会默认随机指定一个名字。一个或多个节点组成一个cluster集群，集群逻辑概念，节点物理概念
node.name: xc_node_1
#设置绑定主机的ip地址，设置为0.0.0.0表示绑定任何ip，允许外网访问，生产环境建议设置为具体的ip
network.host: 0.0.0.0
#设置对外服务的http端口，默认为9200
http.port: 9200
#集群结点之间通信端口
transport.tcp.port: 9300
#指定该节点是否有资格被选举成为master结点，默认是true，如果原来的master宕机会重新选举新的master
node.master: true
#指定该节点是否存储索引数据，默认为true
node.data: true
#设置集群中master节点的初始列表
discovery.zen.ping.unicast.hosts: ["0.0.0.0:9300", "0.0.0.0:9301"]
#主结点数量的最少值 ,此值的公式为：(master_eligible_nodes / 2) + 1 ，比如：有3个符合要求的主结点，那么这里要设置为2。
discovery.zen.minimum_master_nodes: 1
node.ingest: true
#设置为true可以锁住ES使用的内存，避免内存与swap分区交换数据
bootstrap.memory_lock: false
#单机允许的最大存储结点数，通常单机启动一个结点建议设置为1，开发环境如果单机启动多个节点可设置大于1
node.max_local_storage_nodes: 2

#设置索引数据的存储路径，默认是es根目录下的data文件夹，可设置多个，用逗号隔开
path.data: C:\Develop\Elasticsearch\elasticsearch-1\data
#设置日志文件的存储路径，默认是es根目录下的logs文件夹
path.logs: C:\Develop\Elasticsearch\elasticsearch-1\logs

#开启cors跨域访问支持，默认为false
http.cors.enabled: true
#跨域访问允许的域名地址，(允许所有域名)以上使用正则
http.cors.allow-origin: /.*/
```

* `path.conf`：设置配置文件的存储路径tar或zip包安装在config文件夹，rpm安装默认在/etc/elasticsearch 
* `path.plugins`: 设置插件的存放路径，默认是es根目录下的plugins文件夹
* `discovery.zen.ping.timeout: 3s` 设置ES自动发现节点连接超时的时间，默认为3秒，如果网络延迟高可设置大些



#### 2.2.2 jvm.options

设置最小及最大的JVM堆内存大小，在jvm.options中设置 `-Xms`和`-Xmx`：两个值设置为相等；将`Xmx`为不超过物理内存的一半

```
-Xms2g
-Xmx2g
```



#### 2.2.3 log4j2.properties

日志文件设置，ES使用log4j，注意日志级别的配置



#### 2.2.4 系统配置

在linux上根据系统资源情况，可将每个进程最多允许打开的文件数设置大些。

`su limit -n` 查询当前文件数

使用命令设置`limit`：先切换到root，设置完成再切回elasticsearch用户

```
sudo su
ulimit ‐n 65536
su elasticsearch
```

也可通过下边的方式修改文件进行持久设置：

/etc/security/limits.conf，将下边的行加入此文件：

```
elasticsearch ‐ nofile 65536
```



### 2.3 启动

进入bin目录，在命令行下运行：`elasticsearch.bat`，浏览器输入http://localhost:9200/，返回如下（根据配置内容不同）

```json
{
    "name": "xc_node_1",
    "cluster_name": "xuecheng",
    "cluster_uuid": "R5sqEbnhTUuEIMPpGgwZdA",
    "version": {
        "number": "6.2.1",
        "build_hash": "7299dc3",
        "build_date": "2018-02-07T19:34:26.990113Z",
        "build_snapshot": false,
        "lucene_version": "7.2.1",
        "minimum_wire_compatibility_version": "5.6.0",
        "minimum_index_compatibility_version": "5.0.0"
    },
    "tagline": "You Know, for Search"
}
```



### 2.4 head 插件安装

[head](https://github.com/mobz/elasticsearch-head)插件是ES的一个可视化管理插件，用来监视ES的状态，并通过head客户端和ES服务进行交互，比如创建映射、创建索引等

从ES6.0开始，head插件支持使用Node.js运行

1. 安装Node.js，略
2. 下载head并运行
   1. `git clone git://github.com/mobz/elasticsearch-head.git`
   2. `cd elasticsearch-head`
   3. `npm install`
   4. `npm run start`
3. 浏览器打开http://localhost:9100/并连接http://localhost:9200/

> 打开浏览器调试工具发现报错：Origin null is not allowed by Access-Control-Allow-Origin
>
> 原因是：head插件作为客户端要连接ES服务（localhost:9200），此时存在跨域问题，elasticsearch默认不允许跨域访问。
>
> 设置Elasticsearch允许跨域访问。在config/elasticsearch.yml 后面增加2个参数，详细参靠2.2.1中配置文件



## 3 Elasticsearch 快速入门

ES作为一个索引及搜索服务，对外提供丰富的REST接口，快速入门部分的实例使用head插件或Postman来测试，目的是对ES的使用方法及流程有个初步的认识。

### 3.1 创建索引库

==ES的**索引库**是一个逻辑概念，它包括了**分词列表**及**文档列表**==，同一个索引库中存储了相同类型的文档。它就相当于MySQL中的**表**，或相当于Mongodb中的**集合**。

> 索引（名词）：ES是基于Lucene构建的一个搜索服务，它要从索引库搜索符合条件索引数据。
> 索引（动词）：索引库刚创建起来是空的，将**文档数据添加到索引库**的过程称为索引。

创建索引库相当于关系数据库中的数据库还是表？

- 如果相当于==**数据库**==就表示一个索引库可以创建很多不同类型的文档，这在ES中也是允许的。
- 如果相当于==**表**==就表示一个索引库只能存储相同类型的文档，ES官方**建议**在一个索引库中只存**储相同类型的文档**。

------

两种创建索引库的方法，它们的工作原理是相同的，都是客户端向ES服务发送命令：

* 使用Postm或curl这样的工具创建（使用**PUT**，因为幂等）

  `put http://localhost:9200/索引库名称`

  ```json
  {
      "settings":{
          "index":{
              "number_of_shards":1,
              "number_of_replicas":0
          }
      }
  }
  ```

  * `number_of_shards`：设置**分片的数量**，在集群中通常设置多个分片，表示一个索引库将拆分成多片分别存储不同的结点，提高了ES的处理能力和高可用性，入门程序使用单机环境，这里设置为1。

  * `number_of_replicas`：设置**副本**的数量，设置副本是为了提高ES的高可靠性，单机环境设置为0。

* 使用head插件创建

  ![1551538977021](images/1551538977021.png)

------

创建xc_course索引库，共1个分片，0个副本：

![1551538334761](images/1551538334761.png)



### 3.2 创建映射

在索引中每个文档都包括了一个或多个Field，创建映射就是向==**索引库**中**创建Field**==的过程，下边是Document和Field与关系数据库的概念的类比：

* 文档（Document）----------------Row记录
* 字段（Field）-------------------Columns 列

注意：6.0之前的版本有type（类型）概念，type相当于关系数据库的表，ES官方将在ES9.0版本中彻底删除type。暂时把type起一个没有特殊意义的名字

------

我们要把课程信息存储到ES中，这里我们创建课程信息的映射，先来一个简单的映射，如下：

`post http://localhost:9200/索引库名称/类型名称/_mapping`

------

例如：创建类型为xc_course的映射，共包括三个字段：name、description、studymondel

`post http://localhost:9200/xc_course/doc/_mapping`，表示在xc_course索引库下的doc类型（无意义）下创建映射。

```json
{
    "properties": {
        "name": {
            "type": "text"
        },
        "description": {
            "type": "text"
        },
        "studymodel": {
            "type": "keyword"
        }
    }
}
```

查看head中数据浏览界面即可看到



### 3.3 创建文档

ES中的文档相当于MySQL数据库表中的==**记录**==。

发送：`put 或Post http://localhost:9200/索引库名称/type/id值`，如果不指定id值ES会自动生成ID。**修改**也是这样！

```json
{
    "name":"Bootstrap开发框架",
    "description":"Bootstrap是由Twitter推出的一个前台页面开发框架，在行业之中使用较为广泛。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长页面开发的程序人员）轻松的实现一个不受浏览器限制的精美界面效果。",
    "studymodel":"201001"
}
```

------

`put 或 Post http://localhost:9200/xc_course/doc/4028e58161bcf7f40161bcf8b77c0000`

![1551540000953](images/1551540000953.png)

查看head中数据浏览界面即可看到

### 3.4 搜索文档

* 根据课程id查询文档

  `get http://localhost:9200/xc_course/doc/4028e58161bcf7f40161bcf8b77c0000`

* 查询所有记录，利用_search

  `get http://localhost:9200/xc_course/doc/_search`

* 查询名称中包括bootstrap关键字的的记录

  `get http://localhost:9200/xc_course/doc/_search?q=name:bootstrap`

  查询学习模式为201001的记录

  `get http://localhost:9200/xc_course/doc/_search?q=studymodel:201001`

查询结果分析：

```json
{
    "took": 72,
    "timed_out": false,
    "_shards": {
        "total": 1,
        "successful": 1,
        "skipped": 0,
        "failed": 0
    },
    "hits": {
        "total": 1,
        "max_score": 0.2876821,
        "hits": [
            {
                "_index": "xc_course",
                "_type": "doc",
                "_id": "4028e58161bcf7f40161bcf8b77c0000",
                "_score": 0.2876821,
                "_source": {
                    "name": "Bootstrap开发框架",
                    "description": "Bootstrap是由Twitter推出的一个前台页面开发框架，在行业之中使用较为广泛。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长页面开发的程序人员）轻松的实现一个不受浏览器限制的精美界面效果。",
                    "studymodel": "201008"
                }
            }
        ]
    }
}
```

took：本次操作花费的时间，单位为毫秒

timed_out：请求是否超时

_shards：说明本次操作共搜索了哪些分片

hits：搜索命中的记录

hits.total ： 符合条件的文档总数 hits.hits ：匹配度较高的前N个文档

hits.max_score：文档匹配得分，这里为最高分

_score：每个文档都有一个匹配度得分，按照降序排列

_source：显示了文档的原始内容



## 4 IK分词器

添加文档时会进行分词，索引中存放的是一个个的词（term），当你去搜索时就是拿关键字去匹配词，最终找到词关联的文档。

测试当前索引库使用的分词器：

`post localhost:9200/_analyze`

```json
{"text":"中华人民共和国人民大会堂"}
```

会发现当前索引库使用的分词器对中文就是单字分词



### 4.1 安装IK分词器

使用IK分词器可以实现对中文分词的效果。[下载IK分词器](https://github.com/medcl/elasticsearch-analysis-ik)，与Elasticsearch版本统一！！！

解压，并将解压的文件中elasticsearch文件夹下的内容拷贝到ES安装目录的plugins下的ik目录下。重新启动Elasticsearch！

测试：

`post localhost:9200/_analyze`

```json
{"text":"中华人民共和国人民大会堂","analyzer":"ik_max_word"}
```



### 4.2 两种分词模式

IK分词器有两种分词模式：`ik_max_word`和`ik_smart`模式

* `ik_max_word`会将文本做**最细粒度**的拆分

  比如会将“**中华人民共和国人民大会堂**”拆分为“中华人民共和国、中华人民、中华、华人、人民共和国、人民、共和国、大会堂、大会、会堂等词语。

* `ik_smart`会做**最粗粒度**的拆分

  比如会将“中华人民共和国人民大会堂”拆分为中华人民共和国、人民大会堂



### 4.3 自定义词库

如果要让分词器支持一些专有词语，可以自定义如：扩展词字典、停止词字典（敏感词汇）

IK分词器自带一个`C:\Develop\Elasticsearch\elasticsearch-1\plugins\ik\config\main.dic`文件，此文件为词库文件。

要自定义词库，先在当前目录下配置`IKAnalyzer.cfg.xml`文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
	<comment>IK Analyzer 扩展配置</comment>
	<!--用户可以在这里配置自己的扩展字典 -->
	<entry key="ext_dict">mydic/myword.dic</entry>
	 <!--用户可以在这里配置自己的扩展停止词字典-->
	<entry key="ext_stopwords">mydic/mystopword.dic</entry>
	<!--用户可以在这里配置远程扩展字典 -->
	<!-- <entry key="remote_ext_dict">words_location</entry> -->
	<!--用户可以在这里配置远程扩展停止词字典-->
	<!-- <entry key="remote_ext_stopwords">words_location</entry> -->
</properties>
```

在上述配置文件所在目录创建mydic目录，放入`myword.dic`和`mystopword.dic`文件（格式为utf-8，不要选择utf-8 BOM！）

```
传智播客
黑马程序员
```

然后重启Elasticsearch，测试上述分词，即可看到效果：黑马程序员也被认为是一个完整的词。在停止词词典中的词不会被分词！



## 5 映射

如何在索引和搜索时去使用ik分词器呢？如何指定其它类型的field，比如日期类型、数值类型等。

### 5.1 映射维护方法

* **查询**所有索引的映射：`GET http://localhost:9200/_mapping`

* **创建/添加**映射：`post http://localhost:9200/xc_course/doc/_mapping`

  ```json
  {
      "properties": {
          "name": {
              "type": "text"
          },
          "description": {
              "type": "text"
          },
          "studymodel": {
              "type": "keyword"
          },
          "pic":{
              "type":"text"
          }
      }
  }
  ```

* ~~更新映射~~：映射创建成功可以添加新字段，**已有字段不允许更新**。

* ~~删除映射~~：通过删除索引库来删除映射

  总之就是**不能改变已有映射字段**！！！

### 5.2 常用映射类型

> 字符串包括text和keyword两种类型

#### 5.2.1 text 文本字段

* **index**

  通过index属性指定**是否索引**，**默认为index=true**，即要进行索引，只有进行索引才可以从索引库搜索到。

  但是也有一些内容不需要索引，比如：商品图片地址只被用来展示图片，不进行搜索图片，此时可以将index设置为false

* **analyzer**

  通过analyzer属性指定分词器。下边指定name的字段类型为text，使用ik分词器的ik_max_word分词模式。

  ```json
  "name": {
      "type": "text",
      "analyzer":"ik_max_word"
  }
  ```

* **search_analyzer**

  上边指定了analyzer是指在索引和搜索都使用ik_max_word，如果**单独**想定义**搜索时使用的分词器**则可以通过该属性

  对于ik分词器建议是**索引**时使用**`ik_max_word`**将搜索内容进行细粒度分词，**搜索**时使用**`ik_smart`**提高搜索精确性

  ```json
  "name": {
      "type": "text",
      "analyzer":"ik_max_word",
      "search_analyzer":"ik_smart"
  }
  ```

* **store**

  是否在source之外存储，默认为false。每个文档索引后会在 ES中保存一份原始文档，存放在"`_source`"中，一般情况下不需要设置store为true，因为在_source中已经有一份原始文档了。

测试

* 手动删除xc_course/doc下的映射，即删除索引库，创建索引库

* 创建新映射：`Post http://localhost:9200/xc_course/doc/_mapping`

  ```json
  {
      "properties": {
          "name": {
              "type": "text",
              "analyzer":"ik_max_word",
              "search_analyzer":"ik_smart"
          },
          "description": {
              "type": "text",
              "analyzer":"ik_max_word",
              "search_analyzer":"ik_smart"
          },
          "pic":{
              "type":"text",
              "index":false
          },
          "studymodel":{
              "type":"text"
          }
      }
  }
  ```

* 插入文档：`post http://localhost:9200/xc_course/doc/4028e58161bcf7f40161bcf8b77c0000`

  ```json
  {
      "name":"Bootstrap开发框架",
      "description":"Bootstrap是由Twitter推出的一个前台页面开发框架，在行业之中使用较为广泛。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长页面开发的程序人员）轻松的实现一个不受浏览器限制的精美界面效果。",
      "pic":"group1/M00/00/01/wKhlQFqO4MmAOP53AAAcwDwm6SU490.jpg",
      "studymodel":"201002"
  }
  ```

* 查询测试：

  * `Get http://localhost:9200/xc_course/_search?q=name:java开发`。也能搜到，会先搜索分词
  * `Get http://localhost:9200/xc_course/_search?q=description:开发`
  * `Get http://localhost:9200/xc_course/_search?
    q=pic:group1/M00/00/01/wKhlQFqO4MmAOP53AAAcwDwm6SU490.jpg`
  * `Get http://localhost:9200/xc_course/_search?q=studymodel:201002`

  【结论】name、description和studymodel都支持全文检索，pic不可作为查询条件

#### 5.2.2 keyword 关键字字段

上边介绍的text文本字段在映射时要设置分词器，keyword字段为关键字字段，通常搜索keyword按照**整体搜索精确匹配**，所以创建keyword字段的索引时是**不进行分词**的，比如：邮政编码、手机号码、身份证等。keyword字段通常用于**过虑、排序、聚合**等

测试：

* 手动删除xc_course/doc下的映射，即删除索引库，创建索引库

* 创建新映射：`Post http://localhost:9200/xc_course/doc/_mapping`

  ```json
  {
      "properties": {
          "name": {
              "type": "keyword"
          },
          "description": {
              "type": "text",
              "analyzer":"ik_max_word",
              "search_analyzer":"ik_smart"
          },
          "pic":{
              "type":"text",
              "index":false
          },
          "studymodel":{
              "type":"keyword"
          }
      }
  }
  ```

* 插入文档：`post http://localhost:9200/xc_course/doc/4028e58161bcf7f40161bcf8b77c0000`

  ```json
  {
      "name": "java编程基础",
      "description": "java语言是世界第一编程语言，在软件开发领域使用人数最多。",
      "pic":"group1/M00/00/01/wKhlQFqO4MmAOP53AAAcwDwm6SU490.jpg",
      "studymodel": "201001"
  }
  ```

* 根据studymodel查询文档：`get http://localhost:9200/xc_course/_search?q=name:java`

  name是keyword类型，所以查询方式是精确查询，全部匹配！！！



#### 5.2.3 date 日期类型

日期类型不用设置分词器。通常用于**排序**。

* format：设置日期格式

测试

* 下边的设置允许date字段存储年月日时分秒、年月日及毫秒三种格式：

  ```json
  {
      "properties": {
          "timestamp": {
              "type": "date",
              "format": "yyyy‐MM‐dd HH:mm:ss||yyyy‐MM‐dd"
          }
      }
  }
  ```

* 插入文档：`Post http://localhost:9200/xc_course/doc/3`

  ```json
  {
      "name": "spring开发基础",
      "description": "spring 在java领域非常流行，java程序员都在用。",
      "studymodel": "201001",
      "pic":"group1/M00/00/01/wKhlQFqO4MmAOP53AAAcwDwm6SU490.jpg",
      "timestamp":"2018‐07‐04 18:28:58"
  }
  ```



#### 5.2.4 数值类型

下边是ES支持的数值类型

![1551547245658](images/1551547245658.png)

* 尽量选择**范围小**的类型，提高搜索效率（比如long类型别乱选）

* 对于**浮点数**尽量用**比例因子**，比如一个价格字段，单位为元，我们将比例因子设置为100这在ES中会按分存储，映射如下：

  ```json
  "price": {
      "type": "scaled_float",
      "scaling_factor": 100
  },
  ```

  由于比例因子为100，如果我们输入的价格是23.45则ES中会将23.45乘以100存储在ES中

  如果输入的价格是23.456，ES会将23.456乘以100再取一个接近原始值的数，得出2346

  使用比例因子的好处是**整型比浮点型更易压缩**，**节省磁盘空间**

  如果比例因子不适合，则从下表选择范围小的去用：

  ![1551547392387](images/1551547392387.png)





#### 5.2.5 综合例子

创建如下映射：`post http://localhost:9200/xc_course/doc/_mapping`

```json
{
    "properties": {
        "description": {
            "type": "text",
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_smart"
        },
        "name": {
            "type": "text",
            "analyzer": "ik_max_word",
            "search_analyzer": "ik_smart"
        },
        "pic":{
            "type":"text",
            "index":false
        },
        "price": {
            "type": "float"
        },
        "studymodel": {
            "type": "keyword"
        },
        "timestamp": {
            "type": "date",
            "format": "yyyy‐MM‐dd HH:mm:ss||yyyy‐MM‐dd||epoch_millis"
        }
    }
}
```





## 6 索引管理—RestClient

### 6.1 搭建工程

ES提供多种不同的客户端：

* ~~TransportClient：ES提供的传统客户端，官方计划8.0版本删除此客户端。~~
* RestClient：官方推荐使用的，它包括两种 Java Low Level REST Client和 Java High Level REST Client ES在6.0之后提供 Java High Level REST Client 还处于完善中，有些功能还没有。

Maven依赖

```xml
<dependency>
    <groupId>org.elasticsearch.client</groupId>
    <artifactId>elasticsearch-rest-high-level-client</artifactId>
    <version>6.2.1</version>
</dependency>
<dependency>
    <groupId>org.elasticsearch</groupId>
    <artifactId>elasticsearch</artifactId>
    <version>6.2.1</version>
</dependency>
```

------

创建搜索工程（maven工程，直接导入）：xc-service-search，添加RestHighLevelClient依赖及junit依赖。

application.yml

```yaml
server:
  port: ${port:40100}
spring:
  application:
    name: xc-search-service
xuecheng:
  elasticsearch:
    hostlist: ${eshostlist:127.0.0.1:9200} #多个结点中间用逗号分隔
```

配置类`com.xuecheng.search.config.ElasticsearchConfig`

```java
@Configuration
public class ElasticsearchConfig {

    @Value("${xuecheng.elasticsearch.hostlist}")
    private String hostlist;

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        //解析hostlist配置信息
        String[] split = hostlist.split(",");
        //创建HttpHost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHostArray = new HttpHost[split.length];
        for(int i=0;i<split.length;i++){
            String item = split[i];
            httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }
        //创建RestHighLevelClient客户端
        return new RestHighLevelClient(RestClient.builder(httpHostArray));
    }

    //项目主要使用RestHighLevelClient，对于低级的客户端暂时不用
    @Bean
    public RestClient restClient(){
        //解析hostlist配置信息
        String[] split = hostlist.split(",");
        //创建HttpHost数组，其中存放es主机和端口的配置信息
        HttpHost[] httpHostArray = new HttpHost[split.length];
        for(int i=0;i<split.length;i++){
            String item = split[i];
            httpHostArray[i] = new HttpHost(item.split(":")[0], Integer.parseInt(item.split(":")[1]), "http");
        }
        return RestClient.builder(httpHostArray).build();
    }

}
```

启动类

```java
@SpringBootApplication
@EntityScan("com.xuecheng.framework.domain.search")//扫描实体类
@ComponentScan(basePackages={"com.xuecheng.api"})//扫描接口
@ComponentScan(basePackages={"com.xuecheng.search"})//扫描本项目下的所有类
@ComponentScan(basePackages={"com.xuecheng.framework"})//扫描common下的所有类
public class SearchApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SearchApplication.class, args);
    }

}
```



### 6.2 API+RestClient

#### 6.2.1 创建索引/映射

创建索引（库）：`put http://localhost:9200/索引名称`

```json
{
    "settings":{
        "index":{
            "number_of_shards":1,
            "number_of_replicas":0
        }
    }
}
```

创建映射：`put http://localhost:9200/xc_course/doc/_mapping`

```json
{
    "properties": {
        "name": {
            "type": "text",
            "analyzer":"ik_max_word",
            "search_analyzer":"ik_smart"
        },
        "description": {
            "type": "text",
            "analyzer":"ik_max_word",
            "search_analyzer":"ik_smart"
        },
        "studymodel": {
            "type": "keyword"
        },
        "price": {
            "type": "float"
        },
        "timestamp": {
            "type": "date",
            "format": "yyyy‐MM‐dd HH:mm:ss||yyyy‐MM‐dd||epoch_millis"
        }
    }
}
```

#### 6.2.2 添加文档

`POST/PUT http://localhost:9200/{index}/{type}/{id} { "field": "value", ... }`，不指定id会自动生成

```json
{
    "name":"spring cloud实战",
    "description":"本课程主要从四个章节进行讲解： 1.微服务架构入门 2.spring cloud 基础入门 3.实战SpringBoot 4.注册中心eureka。",
    "studymodel":"201001",
    "price":5.6
}
```

#### 6.2.3 根据ID查询文档

`POST/GET /{index}/{type}/{id}`

#### 6.2.4 更新文档

ES更新文档的顺序是：先检索到文档、将原来的文档标记为删除、创建新文档、删除旧文档，创建新文档就会重建索引。

通过请求Url有两种方法：

* 完全替换`POST http://localhost:9200/xc_test/doc/3`

  ```json
  {
      "name":"spring cloud实战",
      "description":"本课程主要从四个章节进行讲解： 1.微服务架构入门 2.spring cloud 基础入门 3.实战SpringBoot 4.注册中心eureka。",
      "studymodel":"201001"
      "price":5.6
  }
  ```

* 局部更新`POST http://localhost:9200/xc_test/doc/3/_update`

  ```json
  {
      "doc":{"price":66.6}
  }
  ```

  

#### 6.2.5 删除文档

根据id删除`DELETE /{index}/{type}/{id}`

搜索匹配删除，将搜索出来的记录删除`POST /{index}/{type}/_delete_by_query`

```json
{
    "query":{
        "term":{
            "studymodel":"201001"
        }
    }
}
```

上边例子的搜索匹配删除会将studymodel为201001的记录全部删除

> 搜索匹配删除还没有具体的api，可以采用先搜索出文档id，根据文档id删除。



#### 6.2.6 删除索引库

手动



#### 6.2.7 Java Client

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestIndex {

    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private RestClient restClient;

    //创建索引库
    @Test
    public void testCreateIndex() throws IOException {
        //创建索引对象
        CreateIndexRequest createIndexRequest = new CreateIndexRequest("xc_course");
        //设置参数
        createIndexRequest.settings(Settings.builder().put("number_of_shards", "1").put("number_of_replicas", "0"));
        //指定映射
        createIndexRequest.mapping("doc", " {\n" +
                " \t\"properties\": {\n" +
                "            \"studymodel\":{\n" +
                "             \"type\":\"keyword\"\n" +
                "           },\n" +
                "            \"name\":{\n" +
                "             \"type\":\"keyword\"\n" +
                "           },\n" +
                "           \"description\": {\n" +
                "              \"type\": \"text\",\n" +
                "              \"analyzer\":\"ik_max_word\",\n" +
                "              \"search_analyzer\":\"ik_smart\"\n" +
                "           },\n" +
                "           \"pic\":{\n" +
                "             \"type\":\"text\",\n" +
                "             \"index\":false\n" +
                "           }\n" +
                " \t}\n" +
                "}", XContentType.JSON);
        //操作索引的客户端
        IndicesClient indices = client.indices();
        //执行创建索引库
        CreateIndexResponse createIndexResponse = indices.create(createIndexRequest);
        //得到响应
        boolean acknowledged = createIndexResponse.isAcknowledged();
        System.out.println(acknowledged);

    }

    //删除索引库
    @Test
    public void testDeleteIndex() throws IOException {
        //删除索引对象
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest("xc_course");
        //操作索引的客户端
        IndicesClient indices = client.indices();
        //执行删除索引
        DeleteIndexResponse delete = indices.delete(deleteIndexRequest);
        //得到响应
        boolean acknowledged = delete.isAcknowledged();
        System.out.println(acknowledged);
    }

    //添加文档
    @Test
    public void testAddDoc() throws IOException {
        //文档内容
        //准备json数据
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "spring cloud实战");
        jsonMap.put("description", "本课程主要从四个章节进行讲解： 1.微服务架构入门 2.spring cloud 基础入门 3.实战Spring Boot 4.注册中心eureka。");
        jsonMap.put("studymodel", "201001");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonMap.put("timestamp", dateFormat.format(new Date()));
        jsonMap.put("price", 5.6f);

        //创建索引创建对象
        IndexRequest indexRequest = new IndexRequest("xc_course", "doc");
        //文档内容
        indexRequest.source(jsonMap);
        //通过client进行http的请求
        IndexResponse indexResponse = client.index(indexRequest);
        DocWriteResponse.Result result = indexResponse.getResult();
        System.out.println(result);
    }

    //根据ID查询文档
    @Test
    public void testGetDoc() throws IOException {
        //查询请求对象
        GetRequest getRequest = new GetRequest("xc_course", "doc", "ZEqZP2kBDP-dgPpQBZbx");
        GetResponse getResponse = client.get(getRequest);
        //得到文档的内容
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
        System.out.println(sourceAsMap);
    }

    //更新文档—局部更新
    @Test
    public void updateDoc() throws IOException {
        UpdateRequest updateRequest = new UpdateRequest("xc_course", "doc","ZEqZP2kBDP-dgPpQBZbx");
        Map<String, String> map = new HashMap<>();
        map.put("name", "spring cloud实战2");
        updateRequest.doc(map);
        UpdateResponse update = client.update(updateRequest);
        RestStatus status = update.status();
        System.out.println(status);
    }

    //根据id删除文档
    @Test
    public void testDelDoc() throws IOException {
        //删除索引请求对象
        DeleteRequest deleteRequest = new DeleteRequest("xc_course","doc","ZEqZP2kBDP-dgPpQBZbx");
        //响应对象
        DeleteResponse deleteResponse = client.delete(deleteRequest);
        //获取响应结果
        DocWriteResponse.Result result = deleteResponse.getResult();
        System.out.println(result);
    }
    //搜索匹配删除还没有具体的api，可以采用先搜索出文档id，根据文档id删除。
}
```





## 7 搜索管理—DSL搜索

DSL(Domain Specific Language)是ES提出的基于json的搜索方式，在搜索时传入特定的json数据来完成不同的搜索需求。

DSL比URI搜索方式功能强大，在项目中建议使用DSL方式来完成搜索。

### 7.1 准备环境

环境准备

1. 创建xc_course**索引（库）**：`put http://localhost:9200/xc_course`

2. 创建如下**映射**：`post http://localhost:9200/xc_course/doc/_mapping`

   ```json
   {
       "properties": {
           "description": {
               "type": "text",
               "analyzer": "ik_max_word",
               "search_analyzer": "ik_smart"
           },
           "name": {
               "type": "text",
               "analyzer": "ik_max_word",
               "search_analyzer": "ik_smart"
           },
           "pic":{
               "type":"text",
               "index":false
           },
           "price": {
               "type": "float"
           },
           "studymodel": {
               "type": "keyword"
           },
           "timestamp": {
               "type": "date",
               "format": "yyyy‐MM‐dd HH:mm:ss||yyyy‐MM‐dd||epoch_millis"
           }
       }
   }
   ```

3. 向xc_course/doc中插入以下数据，如

   `POST http://localhost:9200/xc_course/doc/1`，指定id分别为1、2、3

   ```json
   {
       "name": "Bootstrap开发",
       "description": "Bootstrap是由Twitter推出的一个前台页面开发框架，是一个非常流行的开发框架，此框架集成了多种页面效果。此开发框架包含了大量的CSS、JS程序代码，可以帮助开发者（尤其是不擅长页面开发的程序人员）轻松的实现一个不受浏览器限制的精美界面效果。",
       "studymodel": "201002",
       "price":38.6,
       "timestamp":"2018‐04‐25 19:11:35",
       "pic":"group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg"
   }
   ```

   ```json
   {
       "name": "spring开发基础",
       "description": "spring 在java领域非常流行，java程序员都在用。",
       "studymodel": "201001",
       "price":88.6,
       "timestamp":"2018‐02‐24 19:11:35",
       "pic":"group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg"
   }
   ```

   ```json
   {
       "name": "spring开发基础",
       "description": "spring 在java领域非常流行，java程序员都在用。",
       "studymodel": "201001",
       "price":88.6,
       "timestamp":"2018‐02‐24 19:11:35",
       "pic":"group1/M00/00/00/wKhlQFs6RCeAY0pHAAJx5ZjNDEM428.jpg"
   }
   ```

   

4. 简单搜索：通过url进行查询，以get方式请求ES

   `GET ../_search?q=.....`

   如`http://localhost:9200/xc_course/doc/_search?q=name:java`，搜索name中包括java的文档



### 7.2 查询所有文档（search**中）

* 查询所有索引（库）的文档：`POST http://localhost:9200/_search`

* 查询指定索引库指定类型下的文档（通过使用此方法）：`POST http://localhost:9200/xc_course/doc/_search`

  ```json
  {
      "query": {
          "match_all": {}
      },
      "_source" : ["name","studymodel"]
  }
  ```

  `_source`：source源**过滤**设置，指定结果中所**包括的字段**有哪些。

  查询结果分析：

  ```json
  {
      "took": 13,
      "timed_out": false,
      "_shards": {
          "total": 1,
          "successful": 1,
          "skipped": 0,
          "failed": 0
      },
      "hits": {
          "total": 3,
          "max_score": 1,
          "hits": [
              {
                  "_index": "xc_course",
                  "_type": "doc",
                  "_id": "1",
                  "_score": 1,
                  "_source": {
                      "studymodel": "201002",
                      "name": "Bootstrap开发"
                  }
              },
          ]
      }
  }
  ```

  took：本次操作花费的时间，单位为毫秒

  timed_out：请求是否超时

  _shards：说明本次操作共搜索了哪些分片

  hits：搜索命中的记录

  hits.total ： 符合条件的文档总数 hits.hits ：匹配度较高的前N个文档

  hits.max_score：文档匹配得分，这里为最高分

  _score：每个文档都有一个匹配度得分，按照降序排列

  _source：显示了文档的原始内容

  

  

### 7.3 分页查询（search**中）

`POST http://localhost:9200/xc_course/doc/_search`

```json
{
    "from" : 0, "size" : 1,
    "query": {
        "match_all": {}
    },
    "_source" : ["name","studymodel"]
}
```

* form：表示起始文档的下标，从0开始。

* size：查询的文档数量（记录数）。

【注意】命中的总记录数就是全部的，但是在遍历记录时只会遍历到符合分页条件的



### 7.4 精确查询 term/ id（search**中）

在搜索时会**整体匹配关键字**，**不**再将搜索关键字**分词**

`POST http://localhost:9200/xc_course/doc/_search`

```json
{
    "query": {
        "term" : {
            "name": "spring"
        }
    },
    "_source" : ["name","studymodel"]
}
```

ES提供根据多个id值匹配的方法：

`POST http://127.0.0.1:9200/xc_course/doc/_search`

```json
{
    "query": {
        "ids" : {
            "type" : "doc",
            "values" : ["2", "3", "100"]
        }
    }
}
```

【注意】termQuery 和 termsQuery 区别



### 7.5 match（search**中）

match query与Term query区别是match query在**搜索前先将搜索关键字分词**，再拿各各词语去索引中搜索。

`POST http://localhost:9200/xc_course/doc/_search`

```json
{
    "query": {
        "match" : {
            "description" : {
                "query" : "spring开发框架",
                "operator" : "or",
                "minimum_should_match": "80%"
            }
        }
    }
}
```

* `query`：搜索的关键字，对于英文关键字如果有多个单词则中间要用半角逗号分隔，而对于中文关键字中间可以用逗号分隔也可以不用。

* `operator`

  * `or` 表示只要有一个词在文档中出现则就符合条件
  * `and`表示每个词都在文档中出现则才符合条件。

* `minimum_should_match`指定文档匹配词的占比。“spring开发框架”会被分为三个词：spring、开发、框架

  `"80%"`表示，三个词在文档的匹配占比为80%，即3*0.8=2.4，向下取整得2，表示至少有两个词在文档中要匹配成功。



### 7.6 ==multi match==（search**中）

上边学习的termQuery和matchQuery一次只能匹配一个Field，本节学习multiQuery，一次可以匹配多个字段。

`POST http://localhost:9200/xc_course/doc/_search`

```json
{
    "query": {
        "multi_match" : {
            "query" : "spring css",
            "minimum_should_match": "50%",
            "fields": [ "name^10", "description" ]
        }
    }
}
```

**提升boost**，通常关键字匹配上name的权重要比匹配上description的权重高，这里可以对name的权重提升。

“`name^10`” 表示**权重提升10倍**



### 7.7 ==布尔查询==（search**中，组合其他）

布尔查询对应于Lucene的BooleanQuery查询，实现**将多个查询组合起来**。三个参数：

* **must**：文档必须匹配must所包括的查询条件，相当于 “**AND**” 
* **should**：文档应该匹配should所包括的查询条件其中的一个或多个，相当于 "**OR**" 
* **must_not**：文档不能匹配must_not所包括的该查询条件，相当于“**NOT**”

`POST http://localhost:9200/xc_course/doc/_search`

```json
{
    "_source" : [ "name", "studymodel", "description"],
    "from" : 0, "size" : 1,
    "query": {
        "bool" : {
            "must":[
                {
                    "multi_match" : {
                        "query" : "spring框架",
                        "minimum_should_match": "50%",
                        "fields": [ "name^10", "description" ]
                    }
                },
                {
                    "term":{
                        "studymodel" : "201001"
                    }
                }
            ]
        }
    }
}
```



### 7.8 ==过滤器==（布尔中）

过虑是针对搜索的**结果进行过虑**，过虑器主要判断的是文档是否匹配，**不去计算和判断文档的匹配度得分**，所以过虑器性能比查询要高，且方便缓存，**推荐**尽量使用过虑器去实现查询或者过虑器和查询共同使用。**过虑器在布尔查询中使用**

```json
{
    "_source" : [ "name", "studymodel", "description","price"],
    "query": {
        "bool" : {
            "must":[
                {
                    "multi_match" : {
                        "query" : "spring框架",
                        "minimum_should_match": "50%",
                        "fields": [ "name^10", "description" ]
                    }
                }
            ],
            "filter": [
                { "term": { "studymodel": "201001" }},
                { "range": { "price": { "gte": 60 ,"lte" : 100}}}
            ]
        }
    }
}
```

* `term`：项匹配过虑，保留studymodel等于"201001"的记录。
* `range`：范围过虑，保留大于等于60 并且小于等于100的记录。

【注意】：range和term一次只能对一个Field设置范围过虑。





### 7.9 ==排序==（search**中）

可以在字段上添加一个或多个排序，**支持在keyword、date、float等类型上添加**，**text类型的字段上不允许**添加排序。

过虑0--10元价格范围的文档，并且对结果进行排序，先按studymodel降序，再按价格升序：

`POST http://localhost:9200/xc_course/doc/_search`

```json
{
    "_source": ["name","studymodel","description","price"],
    "query": {
        "bool": {
            "filter": [
                { "range": { "price": {"gte": 0,"lte": 100}} }
            ]
        }
    },
    "sort": [
        { "studymodel": "desc" },
        { "price": "asc" }
    ]
}
```



### 7.10 ==高亮==（只在search**中）

高亮显示可以将搜索结果一个或多个字突出显示，以便向用户展示匹配关键字的位置。

`POST http://127.0.0.1:9200/xc_course/doc/_search`

```json
{
    "_source" : [ "name", "studymodel", "description","price"],
    "query": {
        "bool" : {
            "must":[
                {
                    "multi_match" : {
                        "query" : "开发框架",
                        "minimum_should_match": "50%",
                        "fields": [ "name^10", "description" ],
                        "type":"best_fields"
                    }
                }
            ],
            "filter": [
                { "range": { "price": { "gte": 0 ,"lte" : 100}}}
            ]
        }
    },
    "sort" : [
        {
            "price" : "asc"
        }
    ],
    "highlight": {
        "pre_tags": ["<tag>"],
        "post_tags": ["</tag>"],
        "fields": {
            "name": {},
            "description":{}
        }
    }
}
```



### 7.11 RestClient

```java
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSearch {

    @Autowired
    RestHighLevelClient client;
    @Autowired
    RestClient restClient;

    private SearchRequest searchRequest = null;////搜索请求对象，传入索引（可变参数）、类型
    private SearchSourceBuilder searchSourceBuilder = null;//搜索源构建对象

    private SearchHit[] searchHits = null;//匹配度高的文档数组

    @Before
    public void init() {
        //搜索请求对象，传入索引（可变参数）
        searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        //搜索源构建对象
        searchSourceBuilder = new SearchSourceBuilder();
    }

    //搜索全部记录
    @Test
    public void testSearchAll() {
        //搜索方式
        //matchAllQuery搜索全部
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    }

    //分页查询
    @Test
    public void testSearchPage() {
        searchSourceBuilder.from(0);//(当前页-1)*每页显示记录数
        searchSourceBuilder.size(2);
        //搜索方式
        //matchAllQuery搜索全部
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
    }

    //TermQuery，注意termQuery
    @Test
    public void testTermQuery() {
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "spring"));
    }

    //根据id查询，注意termsQuery
    @Test
    public void testTermQueryByIds() {
        searchSourceBuilder.query(QueryBuilders.termsQuery("_id", "1", "2", "100"));

    }

    //MatchQuery。以下三个任选其一
    @Test
    public void testMatchQuery() {
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "spring开发框架").operator(Operator.OR));
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "spring开发框架").operator(Operator.AND));
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "spring开发框架").minimumShouldMatch("8%"));
    }

    //MultiMatchQuery
    @Test
    public void testMultiMatchQuery() {
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery("spring css", "name", "description").field("name", 10));
    }

    //BoolQuery
    @Test
    public void testBoolQuery() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
            .must(QueryBuilders.multiMatchQuery("spring框架", "name", "description").field("name", 10))
            .must(QueryBuilders.termQuery("studymodel", "201001"));

        searchSourceBuilder.query(boolQueryBuilder);
    }

    //filter，在BoolQuery中使用
    @Test
    public void testFilter() {
        //定义一个boolQuery，并用must和filter过滤
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
            .must(QueryBuilders.multiMatchQuery("spring框架", "name", "description").field("name", 10))
            .filter(QueryBuilders.termQuery("studymodel", "201001"))
            .filter(QueryBuilders.rangeQuery("price").gte(90).lte(100));

        QueryBuilders
            searchSourceBuilder.query(boolQueryBuilder);
    }

    //Sort，在searchSourceBuilder中使用
    @Test
    public void testSort() {
        //定义一个boolQuery，用filter过滤
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
            .filter(QueryBuilders.rangeQuery("price").gte("0").lte("100"));

        searchSourceBuilder.query(boolQueryBuilder)
            .sort("studymodel", SortOrder.DESC)
            .sort("price", SortOrder.ASC);
    }

    //Highlight
    @Test
    public void testHighlight() {
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
            .must(QueryBuilders.multiMatchQuery("开发框架", "name", "description").field("name", 10))
            .filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
        searchSourceBuilder
            .query(boolQueryBuilder)
            .highlighter(new HighlightBuilder().preTags("<tag>").postTags("</tag>").field("name").field("description"));

    }

    //字段过滤等
    @After
    public void loopShow() throws IOException, ParseException {
        //设置源字段过虑,第一个参数结果集包括哪些字段，第二个参数表示结果集不包括哪些字段
        searchSourceBuilder.fetchSource(new String[]{"name", "studymodel", "price", "timestamp"}, new String[]{});
        //向搜索请求对象中设置搜索源
        searchRequest.source(searchSourceBuilder);

        
        //执行搜索,向ES发起http请求
        SearchResponse searchResponse = client.search(searchRequest);
        //搜索结果
        SearchHits hits = searchResponse.getHits();
        //匹配到的总记录数（此时总记录数是1，是真正匹配的数目）
        long totalHits = hits.getTotalHits();
        //得到匹配度高的文档
        searchHits = hits.getHits();//SearchHit[]

        for (SearchHit hit : searchHits) {
            String index = hit.getIndex();//索引（库）名称
            String id = hit.getId();//文档的主键
            //源文档内容
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            //由于前边设置了源文档字段过虑，这时description是取不到的，为null
            String description = (String) sourceAsMap.get("description");
            //学习模式
            String studymodel = (String) sourceAsMap.get("studymodel");
            //价格
            Double price = (Double) sourceAsMap.get("price");
            //日期（有可能因为传入的字符串格式不是UTF-8，编码问题，会解析时报错）
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date timestamp = dateFormat.parse((String) sourceAsMap.get("timestamp"));


            //源文档的name字段内容（由于description过滤了，所以不考虑）
            String name = (String) sourceAsMap.get("name");
            //取出高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            if (Objects.nonNull(highlightFields)) {
                //取出name高亮字段
                HighlightField nameHighlightField = highlightFields.get("name");
                if (Objects.nonNull(nameHighlightField)) {
                    //组合name高亮片段
                    Text[] fragments = nameHighlightField.getFragments();
                    StringBuilder buffer = new StringBuilder();
                    for (Text text : fragments) {
                        buffer.append(text);
                    }
                    name = buffer.toString();
                }
            }
        }
    }
}
```



## 8 集群管理

### 8.1 集群结构

ES通常以集群方式工作，这样做不仅能够提高 ES的**搜索能力**还可以**处理大数据搜索的能力**，同时也增加了系统的**容错**能力及**高可用**，ES可以实现PB级数据的搜索。

![1551718617433](images/1551718617433.png)

* 结点

  ES集群由多个服务器组成，**每个服务器即为一个Node结点**(该服务**只部署了一个**ES进程)。

* 分片

  当我们的**文档量很大**时，由于内存和硬盘的限制，同时也为了提高ES的处理能力、容错能力及高可用能力，我们将
  **索引分成若干分片**，每个分片可以放在**不同的服务器**，这样就实现了多个服务器共同对外提供索引及搜索服务。
  一个搜索请求过来，会分别从各个分片去查询，最后将查询到的数据**合并**返回给用户。

* 副本

  为了提高ES的**高可用**同时也为了**提高搜索的吞吐量**，我们**将分片复制一份或多份存储在其它的服务器**，这样即使当
  前的服务器挂掉了，拥有副本的服务器照常可以提供服务。

* 主结点

  一个集群中会有一个或多个主结点，**主结点的作用是集群管理**，比如增加节点，移除节点等，主结点挂掉后ES会**重**
  **新选**一个主结点。

* 结点转发

  **每个结点都知道其它结点的信息**，我们可以对任意一个结点发起请求，接收请求的结点会转发给其它结点查询数据。



### 8.2 搭建集群

下边的例子实现创建一个2结点的集群，并且索引的分片我们设置2片，每片一个副本。

#### 8.2.1 结点的三个角色

**主结点**：master节点主要用于**集群的管理及索引**比如新增结点、分片分配、索引的新增和删除等。 

**数据结点**：data 节点上保存了数据分片，它负责**索引和搜索**操作。 

**客户端结点**：client 节点**仅作为请求客户端存在**，client的作用也作为**负载均衡器**，client 节点**不存数据**，只是将请求均衡转发到其它结点。

通过下边参数来配置结点的功能：

* `node.master`：是否允许为主结点
* `node.data`：允许存储数据作为数据结点
* `node.ingest`：是否允许成为协调节点

四种组合方式：

* master=true，data=true：即是主结点又是数据结点
* master=false，data=true：仅是数据结点
* master=true，data=false：仅是主结点，不存储数据
* master=false，data=false：即不是主结点也不是数据结点，此时可设置ingest为true表示它是一个客户端



#### 8.2.2 创建结点1

解压elasticsearch.zip，结点1名称：xc_node_1，对外服务的http端口是：9200，集群管理端口是9300，elasticsearch.yml：

```yaml
cluster.name: xuecheng
node.name: xc_node_1
network.host: 0.0.0.0
http.port: 9200
transport.tcp.port: 9300
node.master: true
node.data: true
discovery.zen.ping.unicast.hosts: ["0.0.0.0:9300", "0.0.0.0:9301"]
discovery.zen.minimum_master_nodes: 1
node.ingest: true
bootstrap.memory_lock: false
node.max_local_storage_nodes: 2

path.data: C:\Develop\Elasticsearch\elasticsearch-1\data #路径要设置正确 
path.logs: C:\Develop\Elasticsearch\elasticsearch-1\logs #路径要设置正确

http.cors.enabled: true
http.cors.allow-origin: /.*/
```

启动结点1



#### 8.2.3 创建结点2

同上，结点2名：xc_node_2，对外服务的http端口是：9201，集群管理端口是9302，elasticsearch.yml：

```yaml
cluster.name: xuecheng
node.name: xc_node_2
network.host: 0.0.0.0
http.port: 9201
transport.tcp.port: 9301
node.master: true
node.data: true
discovery.zen.ping.unicast.hosts: ["0.0.0.0:9300", "0.0.0.0:9301"]
discovery.zen.minimum_master_nodes: 1
node.ingest: true
bootstrap.memory_lock: false
node.max_local_storage_nodes: 2

path.data: C:\Develop\Elasticsearch\elasticsearch-2\data #路径要设置正确 
path.logs: C:\Develop\Elasticsearch\elasticsearch-2\logs #路径要设置正确

http.cors.enabled: true
http.cors.allow-origin: /.*/
```

启动结点2



#### 8.2.4 创建索引库

1. 使用head连上其中一个结点，下图表示两个结点已经创建成功。

   ![1551720416905](images/1551720416905.png)

2. 创建索引（库），共2个分片，每个分片1个副本。

   ![1551720503312](images/1551720503312.png)

   创建成功，刷新head。下图可以看到共有4个分片，其中两个分片是副本。

   ![1551720577040](images/1551720577040.png)



#### 8.2.5 集群的健康

通过访问 `GET /_cluster/health` 来查看Elasticsearch 的集群健康情况。

green：所有的主分片和副本分片都正常运行。 

yellow：所有的主分片都正常运行，但有些副本分片运行不正常。

red：存在主分片运行不正常。



### 8.3 测试

1. 连接其中任意一台结点，创建映射写入文档。

   `POST http://localhost:9200/xc_course/doc/1`

   ```json
   {
       "name": "spring开发基础",
       "description": "spring 在java领域非常流行，java软件开发人员都在用。",
       "studymodel": "201001",
       "price":66.6
   }
   ```

   响应结果：提示可看出，两个分片都保存成功。

   ```json
   {
       "_index": "xc_course",
       "_type": "doc",
       "_id": "1",
       "_version": 1,
       "result": "created",
       "_shards": {
           "total": 2,
           "successful": 2,
           "failed": 0
       },
       "_seq_no": 0,
       "_primary_term": 1
   }
   ```

2. 搜索：向其它一个结点发起搜索请求，查询全部数据。

3. 关闭一个结点，ES会**重新选中一个主结点**（前提在配置结点时**允许**它可以为主结点）

   此时向活的结点发起搜索请求，仍然正常。

4. 添加一个结点3

   结点名：xc_node_3，http端口是：9202，集群管理端口是9302，配置：`node.master: false node.data: true`

   启动结点3，刷新head，下图显示ES将分片分在了3个结点

   ![1551720958917](images/1551720958917.png)

   向结点3发起搜索请求：全部数据可被正常搜索到。

   

## 9 Spring Data Elasticsearch









# 6 Spring Data Solr

​	大多数搜索引擎应用都必须具有某种搜索功能，问题是搜索功能往往是巨大的资源消耗，并且它们由于沉重的数据库加载而拖垮你的应用的性能。这就是为什么转移负载到一个**外部的搜索服务器**是一个不错的主意，**Apache Solr**是一个流行的开源搜索服务器，它通过使用类似**REST的HTTP API**，这就确保你能从几乎==**任何编程语言来使用solr**==。

​	Solr是一个**开源**搜索平台，用于**构建搜索应用程序**。 它建立在[Lucene](http://www.yiibai.com/lucene/)(全文搜索引擎)之上。 Solr是企业级的、快速的和高度可扩展的。 使用Solr构建的应用程序非常复杂，可提供高性能。

​	为了在CNET网络的公司网站上添加搜索功能，Yonik Seely于2004年创建了Solr。并在2006年1月，它成为Apache软件基金会下的一个开源项目。并于2016年发布最新版本Solr 6.0，支持并行SQL查询的执行。

​	**Solr可以和[Hadoop](http://www.yiibai.com/hadoop/)一起使用**。由于Hadoop处理大量数据，Solr帮助我们从这么大的源中找到所需的信息。不仅限于搜索，**Solr也可以用于存储**目的。像其他**NoSQL**数据库一样，它是一种非关系数据存储和处理技术。

​	总之，Solr是一个**可扩展**的，**可部署**，**搜索/存储引擎**，优化搜索**大量以文本为中心的数据**。



## 1 Solr 环境搭建

### 1.1 Solr 安装

1. 安装 Tomcat（解压即可）

2. 解压 solr

3. 把 solr 下的dist目录`solr-4.10.3.war`部署到 `Tomcat\webapps`下(去掉版本号)

4. 启动 Tomcat，自动解压缩 war 包

5. 把solr下example/lib/ext 目录下的所有的 jar 包，添加到 solr 的工程中(\WEB-INF\lib目录下)

6. solrhome，solr 下的/example/solr 目录就是一个 solrhome。复制此目录到如D盘并改名为solrhome（存储数据）

7. 关联 solr 及 solrhome。需要修改 solr 工程的 web.xml 文件

   ```xml
   <env-entry>
       <env-entry-name>solr/home</env-entry-name>
       <env-entry-value>d:\solrhome</env-entry-value>
       <env-entry-type>java.lang.String</env-entry-type>
   </env-entry>
   ```

8. 重新启动该Tomcat，访问`http://localhost:8080/solr/`即可看到界面，可以查询、分析



### 1.2 中文分析器 IK Analyzer

> IK Analyzer 是一个开源的，基亍 java 语言开发的轻量级的中文分词工具包。从 2006年 12 月推出 1.0 版开始，IKAnalyzer 已经推出了 4 个大版本。最初，它是以开源项目Luence 为应用主体的，结合词典分词和文法分析算法的中文分词组件。3.0 版本开始，IK 发展为面向 Java 的公用分词组件，独立于 Lucene 项目，同时提供了对 Lucene 的默认优化实现。在 2012 版本中，IK 实现了简单的分词歧义排除算法，标志着 IK 分词器从单纯的词典分词向模拟语义分词衍化

IK Analyzer配置：

1. 把IKAnalyzer2012FF_u1.jar 添加到 solr 工程的 lib 目录下

2. 创建WEB-INF/classes文件夹，把扩展词典、停用词词典（辅助词或敏感词）、配置文件放到 solr 工程的 WEB-INF/classes 目录下

3. 修改 Solrhome 的 schema.xml 文件，配置一个 FieldType，使用 IKAnalyzer来做中文分析器

   ```xml
   <fieldType name="text_ik" class="solr.TextField">
       <analyzer class="org.wltea.analyzer.lucene.IKAnalyzer"/>
   </fieldType>
   ```



### 1.3 配置域

域相当于数据库的表字段，用户存放数据，因此用户根据业务需要去定义相关的Field（域），一般来说，每一种对应着一种数据，用户对同一种数据进行相同的操作。域的常用属性：

- name：指定域的名称
- type：指定域的类型
- indexed：是否索引（要搜索的域）
- stored：是否存储
- required：是否必须
- multiValued：是否多值

#### 1.3.1 基本域

修改solrhome的`schema.xml`文件，设置业务系统 Field。根据搜索的、被搜索出的、要用到的Field来配置

```xml
<field name="item_goodsid" type="long" indexed="true" stored="true"/> <!--goodsId,商品SKU。这些都是item表数据-->
<field name="item_title" type="text_ik" indexed="true" stored="true"/><!--title,商品标题-->
<field name="item_price" type="double" indexed="true" stored="true"/><!--price,商品价格-->
<field name="item_image" type="string" indexed="false" stored="true" /><!--image,商品图片地址-->
<field name="item_category" type="string" indexed="true" stored="true" /><!--category,商品分类-->
<field name="item_seller" type="text_ik" indexed="true" stored="true" /><!--seller,商家-->
<field name="item_brand" type="string" indexed="true" stored="true" /><!--brand,品牌-->
<!--后添加的-->
<field name="item_updatetime" type="date" indexed="true" stored="true" /><!--updatetime,上架时间-->
```

#### 1.3.2 复制域

多条件查询时（同时输入标题、分类、商家、品牌），复制域的作用在于将某一个Field中的数据复制到另一个域中，不需要存储

```xml
<field name="item_keywords" type="text_ik" indexed="true" stored="false" multiValued="true"/>
<copyField source="item_title" dest="item_keywords"/>
<copyField source="item_category" dest="item_keywords"/>
<copyField source="item_seller" dest="item_keywords"/>
<copyField source="item_brand" dest="item_keywords"/>
```

#### 1.3.3 动态域

当我们需要动态扩充字段时，我们需要使用动态域。对于品优购，规格的值是不确定的，所以我们需要使用动态域来实现。需要实现的效果如下：

![1548005515777](../SSM_/images/1548005515777.png)

```xml
<dynamicField name="item_spec_*" type="string" indexed="true" stored="true" />	
```





## 2 Spring Data Solr 入门

Spring Data Solr就是为了方便Solr的开发所研制的一个框架，其底层是对SolrJ（官方API）的封装。

> 官方类库 SolrJ：原理是**RESTful的HTTP请求和响应**
>
> Spring Data Solr：是对SolrJ的封装
>
> 也可不使用上述工具，手动请求Solr，手动处理响应，httpClient

### 2.1 环境搭建

- Maven依赖

  ```xml
  <dependency>
      <groupId>org.springframework.data</groupId>
      <artifactId>spring-data-solr</artifactId>
      <version>1.5.5.RELEASE</version>
  </dependency>
  <!--Demo中还会用到JUnit和Spring与JUnit整合-->
  ```

- resources下创建`applicationContext-solr.xml`

  ```xml
  <!-- solr服务器地址，其实就是配置了一个bean -->
  <solr:solr-server id="solrServer" url="http://127.0.0.1:8080/solr" />
  
  <!-- solr模板，使用solr模板可对索引库进行CRUD的操作 -->
  <bean id="solrTemplate" class="org.springframework.data.solr.core.SolrTemplate">
      <constructor-arg ref="solrServer" />
  </bean>
  
  ```

### 2.2 `@Field` 、`@Dynamic`注解

> `@Field`是Solr官方的SolrJ提供的注解，`@Dynamic`是SpringDataSolr的注解

创建 cn.itcast.pojo 包，将品优购的TbItem实体类拷入本工程，属性（即Field域）使用`@Field`注解标识。如果属性与配置文件定义的域名称不一致，需要在注解中指定域名称。（上面Solr中基本域配置和这个对应，复制域不需要，动态域后面讲）

```java
public class TbItem implements Serializable{
    
    @Dynamic
    @Field("item_spec_*")
    private Map<String,String> specMap;//动态域，在数据导入时需利用fastJSON转换JSON为map

    @Field
    private Long id;

    @Field("item_title")
    private String title;

    @Field("item_price")
    private BigDecimal price;

    @Field("item_image")
    private String image;

    @Field("item_goodsid")//注意大小写对应solr中配置的基本域
    private Long goodsId;

    @Field("item_category")
    private String category;

    @Field("item_brand")
    private String brand;

    @Field("item_seller")
    private String seller;
    
    @Field("item_updatetime")
    private Date updateTime;//后添加的，排序需要
    .......
}

```

### 2.3 增加（修改）

```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-solr.xml")//可以省略locations
public class TestTemplate {
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void testAdd(){

        TbItem tbItem = new TbItem();
        tbItem.setId(1L);
        tbItem.setTitle("华为Mate10");
        tbItem.setCategory("手机");
        tbItem.setBrand("华为");
        tbItem.setSeller("华为旗舰店");
        tbItem.setGoodsId(10L);
        tbItem.setPrice(new BigDecimal(4500.00));
        solrTemplate.saveBean(tbItem);//增加；修改时只需对象的id（主键，非空）不变，其他修改后save即可
        //solrTemplate.saveBeans(collection);（可以传递集合）
        solrTemplate.commit();//必须commit
    }
}

```

### 2.4 按主键删除

```java
@Test
public void deleteById(){
    solrTemplate.deleteById("1");//自动转换
    solrTemplate.commit();
}

```

### 2.5 删除所有（条件）

```java
@Test
public void deleteAll(){
    Query query = new SimpleQuery("*:*");
    solrTemplate.delete(query);
    solrTemplate.commit();
}

```

### 2.5 按主键查询

```java
@Test
public void getById(){
    TbItem item = solrTemplate.getById(1L, TbItem.class);
    System.out.println(item.getTitle());
}

```

### 2.6 条件查询（含分页查询）

```java
//条件查询
@Test
public void testQuery(){
    Query query = new SimpleQuery("*:*");//查询所有字段所有信息（利用封装好的方法，不用写表达式）
    Criteria criteria = new Criteria("item_category").contains("手机")
        .and("item_title").contains("2");
    query.addCriteria(criteria);

    ScoredPage<TbItem> items = solrTemplate.queryForPage(query, TbItem.class);

    List<TbItem> content = items.getContent();//当前页所有数据
    content.forEach(o-> System.out.println(o.getTitle()+"--"+o.getBrand()+"--"+o.getPrice()));

    System.out.println("总记录数："+items.getTotalElements());
    System.out.println("总页数："+items.getTotalPages());
}


//分页
@Test
public void testQueryPage(){
    Query query = new SimpleQuery("*:*");//查询所有字段所有信息
    query.setOffset(20);//开始索引
    query.setRows(5);//每页记录数
    ScoredPage<TbItem> items = solrTemplate.queryForPage(query, TbItem.class);

    List<TbItem> content = items.getContent();//当前页所有数据
    content.forEach(o-> System.out.println(o.getTitle()+"--"+o.getBrand()+"--"+o.getPrice()));

    System.out.println("总记录数："+items.getTotalElements());
    System.out.println("总页数："+items.getTotalPages());
}


//首先利用saveBeans()方法批量添加数据
@Test
public void testAddList(){
    List<TbItem> list=new ArrayList();

    for(int i=0;i<100;i++){
        TbItem item=new TbItem();
        item.setId(i+1L);
        item.setBrand("华为");
        item.setCategory("手机");
        item.setGoodsId(1L);
        item.setSeller("华为2号专卖店");
        item.setTitle("华为Mate"+i);
        item.setPrice(new BigDecimal(2000+i));
        list.add(item);
    }
    solrTemplate.saveBeans(list);
    solrTemplate.commit();
}
```











## 3 批量数据导入

> 可以使用Solr自带插件来导入，但不灵活，推荐查询增加的方式，根据条件筛选数据并导入Solr中

```java
//审核通过才导入
TbItemExample example = new TbItemExample();
TbItemExample.Criteria criteria = example.createCriteria();
criteria.andStatusEqualTo("1");

List<TbItem> items = itemMapper.selectByExample(example);
for (TbItem item : items) {
    Map specMap = JSON.parseObject(item.getSpec(), Map.class);//fastJSON将数据库中json数据转为Map
    item.setSpecMap(specMap);
}
solrTemplate.saveBeans(items);//saveBeans()
solrTemplate.commit();
```



## 4 关键字搜索（复制域）

`ItemSearchServiceImpl`

```java
@Service(timeout = 5000)//推荐写在服务提供方。当两方同时写，以消费方为准
public class ItemSearchServiceImpl implements ItemSearchService {
    @Autowired
    private SolrTemplate solrTemplate;
    
    //前端传入的数据有多好多类型，如关键字、类型多选框之类的，前端封装为map类似对象
    //后端不仅仅返回列表数据，还要返回根据关键字搜索后的分类信息，选择框等等，所以封装为map
    @Override
    public Map search(Map searchMap) {
        Map map = new HashMap();
        //Query query = new SimpleQuery("*:*");//好像不设置"*:*"也可以
        //Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));//is匹配（利用分词实现）
        //query.addCriteria(criteria);
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));//is匹配（利用分词实现）
        Query query = new SimpleQuery(criteria);
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);

        map.put("list",tbItems.getContent());
        return map;
    }
}

```

`ItemSearchController`

```java
@RestController
@RequestMapping("/itemSearch")
public class ItemSearchController {
    @Reference(timeout = 5000)//默认为1秒
    private ItemSearchService itemSearchService;

    @RequestMapping("/search")
    public Map search(@RequestBody Map searchMap){ //@RequestBody接收前端json数据
        return itemSearchService.search(searchMap);
    }
}

```

`ItemSearchService.js`

```js
app.service("itemSearchService",function ($http) {
    this.search = function (searchMap) {
        return $http.post("/itemsearch/search.do",searchMap);
    }
})

```

`ItemSearchController.js`

```js
app.controller("itemSearchController",function ($scope,itemSearchService) {
    $scope.search = function () {
        itemSearchService.search($scope.searchMap).success(function (data) {
            $scope.resultMap = data;
        })
    }
})

```

HTML遍历展示即可





## 5 高亮显示关键字搜索（复制域）

> 高亮显示标题title中的字符

需要修改关键字搜索中的service方法，其他不变

```java
@Override
public Map search(Map searchMap) {
    Map<String,Map> map = new HashMap<>();
    map.putAll(searchList(searchMap));//1.查询列表数据
    map.put("categoryList",searchCategoryList(searchMap));//2.分组查询商品分类列表


    return map;
}

/**
 * 列表查询
 */
private Map searchList(Map searchMap) {
    Map map = new HashMap();
    //关键字的条件查询（利用复制域），相当于where
    Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
    HighlightQuery query = new SimpleHighlightQuery(criteria);
	//高亮选项
    HighlightOptions highlightOptions = new HighlightOptions()
        .addField("item_title")//高亮Filed域
        .setSimplePrefix("<span style='color: red;'>")//HTML前缀
        .setSimplePostfix("</span>");//HTML后缀
    query.setHighlightOptions(highlightOptions);//设置高亮设置
    HighlightPage<TbItem> items = solrTemplate.queryForHighlightPage(query, TbItem.class);

    //设置高亮
    List<HighlightEntry<TbItem>> highlighted = items.getHighlighted();//高亮entry集合（每条记录）
    for (HighlightEntry<TbItem> entry : highlighted) {
        //高亮列表（高亮Filed域个数可能多个）。并且每个Filed域可能存储多值（此处没有）
        TbItem item = entry.getEntity();//获取源实体类
        List<HighlightEntry.Highlight> highlights = entry.getHighlights();
        if (highlights.size() >= 0 && highlights.get(0).getSnipplets().size() >= 0) {
            item.setTitle(highlights.get(0).getSnipplets().get(0));
        }
    }
    map.put("list", items.getContent());
    return map;
}

```

但是此时HTML中显示的是HTML代码，不是解析后的页面。这是AngularJS为了防止html攻击采取的安全机制。此时需要用到AngularJS的`$sce`服务中的`trustAsHtml`方法。由于该方法具有通用性，定义在`filter`过滤器中（放入base.js）

```js
app.filter("trustHtml",['$sce',function ($sce) {
    return function (data) {
        return $sce.trustAsHtml(data);
    }
}])
```

HTML中就不能使用`{{}}`来绑定了，需要使用`ng-bing-html`

```html
<div class="attr" ng-bind-html="item.title | trustHtml"></div>
```



## 6 分组查询

service中添加私有方法并调用

```java
/**
 * 分组查询商品分类列表
 */
private List searchCategoryList(Map searchMap){
    List<String> list = new ArrayList<>();
    //关键字的条件查询（利用复制域），相当于where
    Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
    Query query = new SimpleQuery(criteria);
    //设置分组选项，相当于group by。可能有多个分组（继续addGroupByField即可）
    GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");
    query.setGroupOptions(groupOptions);
    //分组页
    GroupPage<TbItem> items = solrTemplate.queryForGroupPage(query, TbItem.class);
    //分组结果
    GroupResult<TbItem> item_category = items.getGroupResult("item_category");
    //分组entry页
    Page<GroupEntry<TbItem>> groupEntries = item_category.getGroupEntries();
    //分组entryj集合，便利获取值
    List<GroupEntry<TbItem>> content = groupEntries.getContent();
    for (GroupEntry<TbItem> tbItemGroupEntry : content) {
        list.add(tbItemGroupEntry.getGroupValue());
    }
    return list;
}
```

HTML中遍历

```html
<div ng-if="resultMap.categoryList!=null" >
    <a ng-repeat="category in resultMap.categoryList" href="#{{$index}}">{{category}} </a>
</div>
```



## 7 过滤查询（以上所有代码）

```java
@Service(timeout = 5000)//推荐写在服务提供方。所两方同时写，以消费方为准
public class ItemSearchServiceImpl implements ItemSearchService {

    @Autowired
    private SolrTemplate solrTemplate;

    /**
     * 搜索
     */
    @Override
    public Map search(Map searchMap) {
        //去掉搜索关键字中的空格
        String  keywords = (String) searchMap.get("keywords");
        searchMap.put("keywords",keywords.replace(" ",""));
        
        Map map = new HashMap<>();
        //1.查询列表数据
        map.putAll(searchList(searchMap));
        //2.分组查询商品分类列表
        List<String> categoryList = searchCategoryList(searchMap);
        map.put("categoryList", categoryList);
        //3.根据分类名称查询品牌和规格列表
        String category = (String) searchMap.get("category");
        if ("".equals(category)) { //若没选择分类，按第一个分类查询
            if (categoryList.size() > 0) {
                map.putAll(searchBrandAndSpecList(categoryList.get(0)));
            }
        } else { //若选择了分类，按选择的分类名称查询
            map.putAll(searchBrandAndSpecList(category));
        }
        return map;
    }

    /**
     * 列表查询（过滤查询等等）
     */
    private Map searchList(Map searchMap) {
        Map map = new HashMap();

        //1.1关键字查询（利用复制域）
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        HighlightQuery query = new SimpleHighlightQuery(criteria);
        //高亮选项设置
        HighlightOptions highlightOptions = new HighlightOptions()
            .addField("item_title")//高亮Filed域
            .setSimplePrefix("<span style='color: red;'>")//HTML前缀
            .setSimplePostfix("</span>");//HTML后缀
        query.setHighlightOptions(highlightOptions);//设置高亮设置

        //1.2按照商品分类过滤
        if (!"".equals(searchMap.get("category"))) { //若用户选择了分类
            Criteria filterCriteria = new Criteria("item_category").is(searchMap.get("category"));
            FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
            query.addFilterQuery(filterQuery);
        }

        //1.3按照品牌过滤
        if (!"".equals(searchMap.get("brand"))) { //若用户选择了品牌
            Criteria filterCriteria = new Criteria("item_brand").is(searchMap.get("brand"));
            FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
            query.addFilterQuery(filterQuery);
        }

        //1.4按照规格过滤
        if (searchMap.get("spec") != null) { //若用户选择了规格

            Map<String, String> specMap = (Map<String, String>) searchMap.get("spec");
            Set<Map.Entry<String, String>> entries = specMap.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                System.out.println(entry.getKey() + "====" + entry.getValue());
                Criteria filterCriteria = new Criteria("item_spec_" + entry.getKey()).is(entry.getValue());
                FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
                query.addFilterQuery(filterQuery);
            }
        }
        
        //1.5按照价格过滤
        if (!"".equals(searchMap.get("price"))){
            String price = (String) searchMap.get("price");
            String[] split = price.split("-");

            Criteria filterCriteria = new Criteria("item_price");
            if (!split[0].equals("0")){ //起始条件
                filterCriteria.greaterThanEqual(split[0]);//大于等于
            }
            if (!split[1].equals("*")){ //终止条件
                filterCriteria.lessThanEqual(split[1]); //小于等于
            }
            FilterQuery filterQuery = new SimpleFilterQuery(filterCriteria);
            query.addFilterQuery(filterQuery);
        }
        
        //1.6分页
        Integer pageNum = (Integer) searchMap.get("pageNum");//当前页
        if (pageNum==null){
            pageNum = 1;
        }
        Integer pageSize = (Integer) searchMap.get("pageSize");//每页记录数
        if (pageSize==null){
            pageSize = 20;
        }
        query.setOffset((pageNum-1)*pageSize);//起始索引（不是页码）
        query.setRows(pageSize);//每页记录数
        //在最后返回getContent()后还需传递pages、total；

        //1.7排序（根据传入的Field字段）
        String  sortValue = (String) searchMap.get("sort");//升序或降序
        String sortField = (String) searchMap.get("sortField");//排序的Field字段
        if (sortValue!=null && !sortValue.equals("")){
            if (sortValue.equals("ASC")){
                Sort sort = new Sort(Sort.Direction.ASC,"item_"+sortField);
                query.addSort(sort);
            } else if (sortValue.equals("DESC")){
                Sort sort = new Sort(Sort.Direction.DESC,"item_"+sortField);
                query.addSort(sort);
            }
        }
        
        //高亮页对象
        HighlightPage<TbItem> items = solrTemplate.queryForHighlightPage(query, TbItem.class);

        //遍历并设置高亮
        List<HighlightEntry<TbItem>> highlighted = items.getHighlighted();//高亮entry集合（每条记录）
        for (HighlightEntry<TbItem> entry : highlighted) {
            //高亮列表（高亮Filed域个数可能多个）。并且每个Filed域可能存储多值（此处没有）
            TbItem item = entry.getEntity();//获取源实体类
            List<HighlightEntry.Highlight> highlights = entry.getHighlights();
            if (highlights.size() > 0 && highlights.get(0).getSnipplets().size() > 0) {
                item.setTitle(highlights.get(0).getSnipplets().get(0));
            }
        }

        map.put("list", items.getContent());//当前页所有数据
        map.put("pages",items.getTotalPages());//总页数
        map.put("total",items.getTotalElements());//总记录数
        return map;
    }

    /**
     * 分组查询商品分类列表
     */
    private List searchCategoryList(Map searchMap) {
        List<String> list = new ArrayList<>();
        //关键字的条件查询（利用复制域），相当于where
        Criteria criteria = new Criteria("item_keywords").is(searchMap.get("keywords"));
        Query query = new SimpleQuery(criteria);
        //设置分组选项，相当于group by。可能有多个分组（继续addGroupByField即可）
        GroupOptions groupOptions = new GroupOptions().addGroupByField("item_category");
        query.setGroupOptions(groupOptions);
        //分组页
        GroupPage<TbItem> items = solrTemplate.queryForGroupPage(query, TbItem.class);
        //分组结果
        GroupResult<TbItem> item_category = items.getGroupResult("item_category");
        //分组entry页
        Page<GroupEntry<TbItem>> groupEntries = item_category.getGroupEntries();
        //分组entryj集合，便利获取值
        List<GroupEntry<TbItem>> content = groupEntries.getContent();
        for (GroupEntry<TbItem> tbItemGroupEntry : content) {
            list.add(tbItemGroupEntry.getGroupValue());
        }
        return list;
    }

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * Redis查询品牌和规格列表
     */
    private Map searchBrandAndSpecList(String categoryName) {
        Map map = new HashMap();
        //1.根据商品分类名称得到模板id
        Long categoryId = (Long) redisTemplate.boundHashOps("itemCat").get(categoryName);
        if (categoryId != null) {
            //2.根据模板id获取品牌列表
            List brandList = (List) redisTemplate.boundHashOps("brandList").get(categoryId);
            map.put("brandList", brandList);
            //3.根据模板id获取规格列表
            List specdList = (List) redisTemplate.boundHashOps("specList").get(categoryId);
            map.put("specList", specdList);
        }
        return map;
    }
    
    //审核通过时导入SKU数据
    @Override
    public void importList(List list){
        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    //删除商品时同时删除solr中数据
    @Override
    public void deleteByGoodsIds(List goodsIds) {
        Criteria criteria = new Criteria("item_goodsid").in(goodsIds);
        Query query = new SimpleQuery(criteria);
        solrTemplate.delete(query);
        solrTemplate.commit();
    }
}
```



## 8 更新索引库

> 实时更新solr库，而不总是批量导入。还需实现删除方法。查看7中代码。理解即可



