[TOC]

# DataBase

## DBMS

- **DBMS**（数据库管理系统）由一个**互相关联的数据的集合**和一组用以**访问这些数据的程序**组成。这个数据集合通常称作数据库（database），其本质上是一个文件系统。DBMS 可以实现多个用户同时安全简单地操作大量数据。 

- DBMS 主要通过数据的保存格式(数据库的种类)来进行分类，现阶段主要有以下 5 种类型：

  * 层次数据库(Hierarchical Database，HDB)

    最古老的数据库之一，它把数据通过层次结构(树形结构)的方式表现出来。很少使用。

  * **关系数据库(Relational Database，RDB)**

    1969 年诞生，和 Excel 工作表一样，它也采用**由行和列组成的二维表来管理数据**，使用专门的 **SQL**(Structured
    Query Language，结构化查询语言)对数据进行操作。这种类型的 DBMS 称为关系数据库管理系统(Relational Database
    Management System，RDBMS)。比较具有代表性的 RDBMS 有如下几种：

    * **MySQL**：**开源免费**的小型的数据库，**功能强大**。已经被 Oracle 收购了。
    * PostgreSQL：开源的RDBMS
    * **Oracle**：收费的大型数据库，Oracle 公司的产品。收购了 Sun 和 MySql
    * SQL Server：MicroSoft 公司收费的中型的数据库。C#、.net 等语言常使用
    * DB2 ：IBM 公司的数据库产品，收费的。常应用在银行系统中
    * SQLite: 嵌入式的小型数据库，应用在手机端，如：Android

  * 面向对象数据库(Object Oriented Database，OODB)

    把数据以及对数据的操作 集合起来以对象为单位进行管理。面向对象数据库就是用来保存这些对象的数据库。 

  * XML 数据库(XML Database，XMLDB)

    最近几年（SQL基础教程书17年出版），XMLB 作为在网络上进行交互的数据的形式逐渐普及起来。 XML 数据库可以对 XML 形式的大量数据进行高速处理。 

  * **键值存储系统(Key-Value Store，KVS)**

    这是一种单纯用来保存查询所使用的主键(Key)和值(Value)的组合的数据库。具有编程语言知识的读者可以把它想象成关联数组或者散列 (hash)。需要对大量数据进行超高速查询的 Web 服务当中。

- **RDBMS（关系数据库管理系统）** 

  - n个**DataBase**（文件夹）
    - n个**table**（文件），其中有定义表的列名和列类型的表结构
      - n个**表记录**：一行一行的数据记录

- 数据库的特点：
  * **持久化存储数据**的。其实数据库就是一个文件系统
  * **方便存储和管理**数据
  * 使用了**统一的方式**操作关系型数据库 -- **SQL**



### MySQL

#### Windows

文件夹：

- C:\Program Files\MySQL\MySQL Server 8.0：**DBMS管理程序**

- C:\ProgramData\MySQL\MySQL Server 8.0\data：**DBMS数据库文件**（卸载MySQL时不会删除）

  每个目录表示一个数据库，MySQl8的每个数据库目录下会有0~N个扩展名为ibd的table文件

文件：

- C:\Program Files\MySQL\MySQL Server 8.0\bin\ **mysqld**.exe：**服务器程序**，必须先启动它

- C:\Program Files\MySQL\MySQL Server 8.0\bin\ **mysql**.exe：**客户端程序**操作服务器，服务器需先开启

  > mysqld 是 MySQL 的主程序，服务器端。mysql 是 MySQL 的命令行工具，客户端。 

- C:\ProgramData\MySQL\MySQL Server 8.0\ **my.ini**：**服务器配置文件**，之前版本放在安装目录中bin下

  - 配置MySQL的端口：**默认为3306**（一般不建议修改，本电脑MySQL5为3306、MySQL8为3308端口）
  - 配置字符编码：（一般不建议修改）
    - [mysql]下配置默认客户端编码：default-character-set=gbk
    - [mysqld]下配置默认服务器编码：character-set-server=utf8
  - 配置二进制数据大小上限：（一般不建议修改）
    - 在[mysqld]下配置：max_allowed_packet=8M

服务器操作

> 我的服务名称为mysql8

- 开启服务器(必须保证mysql为windows服务)：**`net start mysql8`**，mysqld.exe进程存在
- 关闭服务器(必须保证mysql为windows服务)：**`net stop mysql8`**，mysqld.exe进程不存在

客户端操作

- 登录服务器：**`mysql -u root -p 123 `**或**`mysql -u root -p`**，然后输入密码

  远程登录：**` mysql -h 127.0.0.1 -P 3306 -uroot -p`**，`-h`和`IP`分开，**`-P`必须大写**，其他同上

  还有一种写全称的：`mysql --host=ip地址 --user=用户名 --password=密码`（端口号不知怎么写？）

- 退出服务器：**`exit`或`quit`**

#### Docker

> 详细查看Docker 笔记

1. ` docker pull mysql`，会自动下载 lasted 版本；

2. `docker run -d --name mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root [mysqlname/id]`

    会设置ROOT用户的密码为root

3. `docker restart [mysqlname/id]`，之后每次使用前需要开启mysql

4. `docker exec -it [mysqlname/id] /bin/bash`，进入mysql

5. 登陆服务器如上，略



### PostgreSQL

### Oracle

## 数据库的结构

- RDBMS 通常使用客户端 / 服务器这样的系统结构。

  RDBMS 既可以和其客户端安装在同一台计算机上，也可以分别安装在不同的计算机上。这样一来，不仅可以通过网络使二者相互关联，还可以实现多个客户端访问同一个 RDBMS。

- 通过从客户端向服务器端发送SQL语句来实现数据库的读写操作。

![image-20190609161548886](images/image-20190609161548886.png)

## 表的结构

* 关系数据库采用被称为数据库表的二维表来管理数据。表存储在由 RDBMS 管理的数据库中，可以存储多个表。
* 数据库表由表示数据项目的**列(字段)**和表示一条数据的**行(记录)**所组成，以**行(记录)为单位进行数据读写**。 

![image-20190609161932236](images/image-20190609161932236.png) 					 



# SQL 的执行原理

## MySQL 中 SQL 如何执行

![img](images/9b515e012856099b05d9dc3a5eaabe7f.png)

MySQL 是 C/S架构，服务端程序适用mysqld，整体流程如下：

![img](images/c4b24ef2377e0d233af69925b0d7139e.png)

* **连接层**：客户端和服务器端建立连接，客户端发送 SQL 至服务端；
* **SQL 层**：对 SQL 语句进行查询处理；与数据库文件的存储方式无关；其结构如下：
* **存储引擎层**：与数据库文件打交道，负责数据的存储和读取。

![img](images/30819813cc9d53714c08527e282ede79.jpg)

* 查询缓存：Server 如果在查询缓存中发现了这条 SQL 语句，就会直接将结果返回给客户端；如果没有，就进入到解析器阶段。需要说明的是，因为查询缓存往往效率不高，所以在 MySQL8.0 之后就抛弃了这个功能。
* **解析器**：在解析器中对 SQL 语句进行语法分析、语义分析。
* **优化器**：在优化器中会确定 SQL 语句的执行路径，比如是根据全表检索，还是根据索引来检索等。
* **执行器**：在执行之前需要判断该用户是否具备权限，如果具备权限就执行 SQL 查询并返回结果。在 MySQL8.0 以下的版本，如果设置了查询缓存，这时会将查询结果进行缓存。

与 Oracle 不同的是，MySQL 的存储引擎采用了插件的形式，每个存储引擎都面向一种特定的数据库应用环境。同时开源的 MySQL 还允许开发人员设置自己的存储引擎，**且MySQL中每张表都可以设置为不同的存储引擎**。下面是一些常见的存储引擎：

* InnoDB 存储引擎：它是 MySQL 5.5 版本之后默认的存储引擎，最大的特点是支持事务、行级锁定、外键约束等。
* MyISAM 存储引擎：MySQL 5.5 版本之前是默认的存储引擎，不支持事务，也不支持外键，最大特点是速度快，占用资源少。
* Memory 存储引擎：使用系统内存作为存储介质，以便得到更快的响应速度。不过如果 mysqld 进程崩溃，则会导致所有的数据丢失，因此我们只有当数据是临时的情况下才使用 Memory 存储引擎。
* NDB 存储引擎：也叫做 NDB Cluster 存储引擎，主要用于 MySQL Cluster 分布式集群环境，类似于 Oracle 的 RAC 集群。
* Archive 存储引擎：它有很好的压缩机制，用于文件归档，在请求写入时会进行压缩，所以也经常用来做仓库。





# SQL

> 每隔几年，ANSI(美国国家标准协会)或 ISO(国际标准化组织)等便会修订 SQL 的标准，进行语法的修订并追加新功能。《SQL基础教程》编写时(2016 年 5 月)使用的是 2011 年修订的最新版本(SQL:2011)。但是，SQL 的标准并不强制RDBMS必须使用
>
> 国际标准化组织(ISO)为 SQL 制定了相应的标准，以此为基准的 SQL 称为标准 SQL 

* **结构化查询语言**(Structured Query Language)

  * 是一种所有**关系型数据库**的**查询规范**，不同的数据库都支持。 
  * **通用**的数据库操作语言，可以用在不同的数据库中。 
  * 不同的数据库 SQL 语句有一些**区别**，称为方言

* SQL语句**分类**

  - **DDL**（Data Definition Language）：**数据定义语言**

    **数据库、表**的操作：**CREATE / DROP / ALTER**

  - **DML**（Data Manipulation Language）：**数据操作语言**

    对**表记录**的操作：**SELECT / INSERT / UPDATE / DELETE**

  - **DQL**（Data Query Language）：**数据查询语言**，对**表**的**记录**进行查询

  - **DCL**（Data Control Language）：**数据控制语言**

    确认或者取消对数据库中的数据进行的变更；对用户权限的设置； COMMIT / ROLLABCK / GRANT / REVOKE

* **标准SQL语法**

  * SQL语句可以在**单行或多行**书写，以**`;`结尾**
  
  * SQL**不区别大小写**。但建议关键字使用大写，表名、别名、字段名等小写。**插入的数据是区分大小写的！**
  
    > MySQL在Windows下都不区分大小写，在Linux下，MySQL对表名和数据库名是区分大小写的。
    >
    > 关于**表名大小**写的问题：
    >
    > * Oracle: `SELECT * FROM HEROS`与`SELECT * FROM heros`一样，因为 Oracle 会自动把`SELECT * FROM heros`转化为`SELECT * FROM HEROS`进行查询。数据库中存的表是大写的。
    > * MySQL: `SELECT * FROM HEROS`与`SELECT * FROM heros`是否一样取决于参数`lower_case_table_names`，如`lower_case_table_names=1`，这两个查询是一样的。`SELECT * FROM HEROS`会自动转化为`SELECT * FROM heros`。因为数据库中存的表名是小写的。否则就有区别了。
    >
    > 关于**字符大小写**的问题：
    >
    > * Oracle: `SELECT * FROM heros WHERE role_main = 'ZHANGSHI'`与`SELECT * FROM heros WHERE role_main = 'zhangshi'`不一样，字符大小写敏感
    > * MySQL: `SELECT * FROM heros WHERE role_main = 'ZHANGSHI'`与`SELECT * FROM heros WHERE role_main = 'zhangshi'`是否一样与该字段的`collate`定义有关
  
  * 在 SQL 语句中**直接书写的字符串、日期或者数字**等称为**常数**
    
    * **字符串、日期** 需要用单引号`''`括起来
    * **数字不需要，直接写**
    
  * 只能使用**半角英文字母、数字、下划线(_)作为数据库、表和列的名称**。名称必须以**半角英文字母开头**
  
  * 单词之间需要使用半角空格或者换行符进行分隔。MySQL 中也可以使用双引号做为分隔符。 
  
  * 注释的三种写法
    
    - 单行（MySQL需加空格）：`--空格`；多行：`/* */`；mysql特有：`#`

## DDL

无需COMMIT

### DATABASE

* Create
  * **创建**数据库(CREATE DATABASE)

    ```mysql
    CREATE DATABASE [IF NOT EXISTS] 数据库名 [CHARSET=UTF8];
    ```

* Retrieve

  * **查看**所有数据库(SHOW DATABASES)

    ```mysql
    SHOW DATABASES;
    ```

  * **查看**数据库**定义信息**，包括创建语句和字符集

    ```mysql
    SHOW CREATE DATABASE 数据库名;
    ```

* Update

  * **修改**数据库**编码**

    ```mysql
    ALTER DATABASE 数据库名 CHARACTER  SET UTF8;
    ```

* Delete

  * **删除**数据库(DROP DATABASE)

    ```mysql
    DROP DATABASE [IF EXISTS] 数据库名;
    ```

* 使用

  * **切换**数据库(USE 数据库名)

    ```mysql
    USE 数据库名;
    ```

  * 查看**当前使用**的数据库，MySQL特有

    ```mysql
    SELECT DATABASE();
    ```



### TABLE

* 创建表

  * **创建表**(CREATE TABLE)

    ```mysql
    CREATE TABLE [IF NOT EXISTS] 表名(
      列名 列类型 该列所需约束,
      列名 列类型 该列所需约束,
      ...
      列名 列类型 该列所需约束,
      PRIMARY KEY (列名)
    );
  -- 约束可以在定义列的时候进行设置，也可以在语句的末尾进行设置。但是NOT NULL约束只能以列为单位进行设置
    
    ```

  DROP TABLE IF EXISTS `player`;
    CREATE TABLE `player`  (
      `player_id` int(11) NOT NULL AUTO_INCREMENT,
      `team_id` int(11) NOT NULL,
      `player_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
      `height` float(3, 2) NULL DEFAULT 0.00,
      PRIMARY KEY (`player_id`) USING BTREE,
      UNIQUE INDEX `player_name`(`player_name`) USING BTREE
    ) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;
    ```
    
    * 其中 player_name 字段的字符集是 utf8（推荐utf-8mb4），排序规则是utf8_general_ci，代表对大小写不敏感，如果设置为utf8_bin，代表对大小写敏感，还有许多其他排序规则这里不进行介绍。
    
    **复制表**结构
    
    ```mysql
    CREATE TABLE 表名 LIKE 被复制的表名;
    ```

* 查询表

  * **查看**当前数据库中**所有表名称**

    ```mysql
    SHOW TABLES;
    ```

    **查看表结构**

    ```mysql
    DESC 表名;
    ```

    **查看**指定表的**定义信息**，包括SQL创建语句、字符集

    ```mysql
    SHOW CREATE TABLE 表名;
    ```

* **删除表**(DROP TABLE)

  ```mysql
  DROP TABLE [IF EXISTS] 表名;
  ```

* **修改表**：前缀：ALTER TABLE 表名

  * 修改**表名称**(RENAME TO)

    ```mysql
    ALTER TABLE 原表名 RENAME TO 新表名; -- Oracle和PostgreSQL使用这个，MySQL8测试也可以使用
    RENAME TABLE 原表名 TO 新表名;
    ```
  * 修改之**添加列**(ADD)
    
      ```sql
      ALTER TABLE 表名 ADD /*COLUMN*/(
        列名 列类型 该列所需约束,
          列名 列类型 该列所需约束,
        ...
      );
      -- MySQL、Oracle、SQL Server中可以不用写COLUMN，PostgreSQL需写上
      ```
    
  * 修改之**删除列**(DROP)
  
    ```mysql
    ALTER TABLE 表名 DROP /*COLUMN*/ 列名;
    -- MySQL、Oracle可以用 (列名，列名...)来删除多个列
    -- MySQL、Oracle、SQL Server中可以不用写COLUMN，PostgreSQL需写上
    ```
  
  * 修改之**修改列名/类型**(CHANGE)
  
    ```mysql
    ALTER TABLE 表名 CHANGE 原列名 新列名 列类型 主键自增长 非空约束; -- 新的类型可能会影响到已存在数据
    ALTER TABLE player RENAME COLUMN age to player_age -- RENAME也可以
    ```
  
  * 修改之**修改列类型**(MODIFY)
  
    ```mysql
    ALTER TABLE 表名 MODIFY 列名 列类型 主键自增长 非空约束 -- /新的类型可能会影响到已存在数据
    ```
  
  * 修改表的字符集
  
    ```mysql
    ALTER TABLE 表名 CHARACTER  SET UTF8;
    ```
  
    

### 列数据类型

#### 数字类型

- NUMBER 整数类型（精确值）

  |      类型      | 存储（字节） | 有符号最小值 | 无符号最小值 | 有符号最大值 | 无符号最大值 |
  | :------------: | :----------: | :----------: | :----------: | :----------: | :----------: |
  | `INT(INTEGER)` |      4       | -2147483648  |      0       |  2147483647  |  4294967295  |
  |   `SMALLINT`   |      2       |    -32768    |      0       |    32767     |    65535     |
  | 上部为标准SQL  |              |              |              |              |              |
  |   `TINYINT`    |      1       |     -128     |      0       |     127      |     255      |
  |  `MEDIUMINT`   |      3       |   -8388608   |      0       |   8388607    |   16777215   |
  |    `BIGINT`    |      8       |    -2^63     |      0       |    2^63-1    |    2^64-1    |

- Fixed-Point 定点类型（精确值）

  `DECIMA` 和 `NUMERIC` 类型的存储精确的数值数据。在保持精确精度很重要时使用这些类型，例如使用货币数据。MySQL DECIMAL 以二进制格式存储值。

  在 DECIMAL 列声明中，可以（通常是）指定精度和小数位数。例如：`salary DECIMAL(5,2)`

  标准SQL要求`DECIMAL(5,2)`能够存储五位数和两位小数的任何值，因此可以存储在 salary 列中的值的范围`-999.99`是 `999.99`。

- Floating-Point 浮点类型（近似值）

  `FLOAT`和`DOUBLE`类型代表近似数字数据值。MySQL对于单精度值使用四个字节，对于双精度值使用八个字节。

  对于FLOAT，SQL标准允许在括号中的关键字FLOAT后面的位中选择性地指定精度（但不是指数的范围）；也就是FLOAT（p）。 MySQL还支持这种可选的精度规范，但FLOAT（p）中的精度值仅用于确定存储大小。 精度从0到23会产生一个4字节的单精度FLOAT列。 从24到53的精度产生8字节双精度DOUBLE列。

  例如``DOUBLE(5,2)``表示最多5位，其中必须有2位小数，即最大值为999.99。从MySQL 8.0.17开始不推荐使用非标准语法，并且在将来的版本中将删除对它的支持。`FLOAT(M,D)` `DOUBLE(M,D)`

- Bit 位类型

- ……

#### 日期和时间类型

* `DATE`：日期类型，格式为`yyyy-MM-dd`，只有年月日，没时分秒

  > 除了年月日之外，Oracle 中使用的 DATE 型还包含时分秒

  `TIME`：时间类型，格式为`HH:mm:ss`

  `DATETIME`：同时可以表示日期和时间，格式为`yyyy-MM-dd HH:mm:ss`

  `TIMESTAMP`：同上，若不给这个字段赋值，或赋值为null，则默认使用当前系统时间

#### 字符串类型

* `CHAR`：固定长度字符串类型； `CHAR(255)`，数据的长度不足指定长度，补空格到指定长度！

  `VARCHAR`：可变长度**字符**串类型；`VARCHAR(65535)`

  > Oracle 中使用 VARCHAR2 型 ( Oracle 中也有 VARCHAR 这种数据类型，但并不推荐使用)

* `BLOB`：**大字节类型**；tinyblob(2^ 8-1B)、blob(2^ 16-1B)、mediumblob(2^ 24-1B)、longblob(2^32-1B)

  `TEXT(CLOB)`：**大字符串类型**；tinytext(2^ 8-1B)、text(2^ 16-1B)、mediumtext(2^24-1 B)、longtext(2^32-1B)

……



### 约束

对表中的数据进行限制，保证数据的正确性、有效性和完整性。用来**约束列**

#### PRIMARY KEY

- 特点：**非空**、**唯一**、**被外键引用**。一般设置id为主键，不是业务字段（身份证等不建议做主键），一张表只能有一个字段

  - **创建表时设置主键**

    ```mysql
    CREATE TABLE stu(
    	stuid CHAR(6) PRIMARY KEY
    );
    ```

    ```mysql
    CREATE TABLE stu(
    	stuid CHAR(6),
      PRIMARY KEY(stuid)
    );
    ```

  - **修改表时添加主键**

    ```mysql
    ALTER TABLE stu ADD PRIMARY KEY(sid); -- CHANGE、MODIFY也行
    ```

  - **删除主键**

    ```mysql
    ALTER TABLE stu DROP PRIMARY KEY;
    ```

#### AUTO_INCREMENT

- 保证在插入数据时主键列的唯一和非空特性，默认为1，根据读取的**上一条记录**的值

  > DELETE和TRUNCATE对自增长的影响 
  >
  > - DELETE：删除所有的记录之后，自增长没有影响
  > - TRUNCATE：删除以后，自增长又重新开始

  - 创建表时指定主键自增长

    ```mysql
    CREATE TABLE stu(
    	stuid CHAR(6) PRIMARY KEY AUTO_INCREMENT
    )[auto_increment = 1000]; -- []中设置主键起始值
    ```

  - 修改表时设置主键自增长

    ```mysql
    ALTER TABLE stu MODIFY sid INT AUTO_INCREMENT; -- 或用CHANGE，需添加新名称
    ALTER TABLE stu AUTO_INCREMENT = 2000; -- 设置主键起始值
    ```

  - 修改表时删除主键自增长

    ```mysql
    ALTER TABLE stu MODIFY sid INT; -- 或用CHANGE，需添加新名称
    ```

#### NOT NULL

- 因为某些列**不能设置为NULL**值，所以可以对列添加非空约束。可以设置**默认约束**

  - 创建表时设置

    ```mysql
    CREATE TABLE stu(
    		sid INT PRIMARY KEY AUTO_INCREMENT,
    		sname VARCHAR(20) NOT NULL DEFAULT `王八蛋`, -- 默认值，不设置时使用默认值，设置为DEFAULT时使用默认值
    );
    ```

  - 修改表时设置

    ```mysql
    ALTER TABLE stu MODIFY name VARCHAR(20) NOT NULL; -- 或用CHANGE，需添加新名称
    ```

  - 删除

    ```mysql
    ALTER TABLE stu MODIFY name VARCHAR(20); -- 或用CHANGE，需添加新名称
    ```

#### UNIQUE

> Navicat中显示在索引中

- 数据库某些列**不能设置重复**的值，所以可以对列添加唯一约束。**NULL中没有值，不存在重复的问题**

  - 创建表时设置

    ```mysql
    CREATE TABLE stu(
    		sid INT PRIMARY KEY AUTO_INCREMENT,
    		phone_number VARCHAR(11) UNIQUE
    );
    ```

  - 修改表时设置

    ```mysql
    ALTER TABLE student MODIFY name VARCHAR(20); -- 或用CHANGE，需添加新名称
    ```

  - 删除

    ```mysql
    ALTER TABLE stu DROP INDEX phone_number;
    ```

#### FOREIGN KEY

> 外键：在从表（多）中与主表（一）主键对应的那一列，如：员工表中的 dep_id 

- 一张表中可以有**多个外键**。外键**可以为空**、**可以重复**、必须是另一表(可以是自己)的主键的值(**外键要引用主键**)

- 语法：

  ```mysql
  [CONSTRAINT 约束名称] FOREIGN KEY(外键列名) REFERENCES 关联表(关联表的主键) -- 约束名称可省略
  ```

  - **创建表时指定外键约束**

    ```mysql
    create talbe emp (
        empno int primary key,
        ...
        deptno int,
        CONSTRAINT fk_emp_dpet FOREIGN KEY(dpetno) REFERENCES dpet(dpetno)
        /*CONSTRAINT fk_emp FOREIGN KEY(mgr) REFERENCES emp(empno)*/
    );
    ```

  - **修改表时添加外键约束**

    ```mysql
    ALERT TABLE emp
    ADD CONSTRAINT fk_emp_dept FOREIGN KEY(deptno) REFERENCES dept(deptno);
    ```

  - **删除外键约束**

    ```mysql
    ALTER TABLE emp DROP FOREIGN KEY fk_emp_dept;/*约束名称*/
    ```

- **外键的级联（谨慎使用）**：在修改和删除主表的主键时，同时更新或删除副表的外键值，称为级联操作。

  ```mysql
  create talbe emp (
  	...
      deptno int,
      CONSTRAINT fk_emp_dpet FOREIGN KEY(dpetno) REFERENCES dpet(dpetno) on update cascade on delete cascade 
  );
  ```



## DML

* **插入数据**(INSERT INTO) 

  ```mysql
  START TRANSACTION; -- PostgreSQL中使用 BEGIN TRANSACTION; Oracle和DB2种不需要该语句
  
  INSERT INTO 表名(
      列名1,列名2, ...
  ) VALUES(列值1, 列值2, ...),(列值1, 列值2, ...);-- 直接插入多行数据
  
  COMMIT;
  -- 列名和表名要一一对应
  -- 没有指定的列等同于插入null值,插入记录总是插入一行
  -- 要插入的值为null，则输入null
  ```

  ```mysql
  INSERT INTO 表名 
  VALUES(列值1, 列值2);
  -- 插入所有列。值的顺序必须与表创建时给出的列的顺序相同
  ```

  > ```sql
  > -- Oracle中的多行INSERT
  > INSERT ALL INTO ProductIns VALUES ('0002', '打孔器',  '办公用品', 500, 320, '2009-09-11')
  > INTO ProductIns VALUES ('0003', '运动T恤',  '衣服', 4000, 2800, NULL)
  > ```

  **蠕虫复制**：将一张已经存在的表中的数据复制到另一张表中。SELECT子句可以使用任何SQL语法，但ORDER BY无效

  ```mysql
  INSERT INTO 表名1 SELECT * FROM 表名2; -- 复制所有
  INSERT INTO 表名1(列1, 列2) SELECT 列1, 列2 FROM 表名2; -- 复制部分列
  ```

* **删除数据**(DELETE FROM)。DELETE语句中不能使用GROUP BY、HAVING和ORDER BY三类子句，只能使用WHERE。

  ```mysql
  DELETE FROM 表名 [WHERE 条件];-- 若不加条件，则删除所有记录
  -- DELETE每次从表中删除一行，并将该操作作为事务记录在日志中以便回滚，不清空 AUTO_INCREMENT 记录数
  ```

  **删除表中所有数据**(TRUNCATE)

  ```mysql
  TRUNCATE [TABLE] 表名; 
  -- TRUNCATE直接删除表并重新创建一张相同结构的新表，不能回滚， AUTO_INCREMENT置为0，效率比DELETE高
  ```

* **修改数据**(UPDATE…SET)。使用UPDATE语句可以将值清空为NULL(但只限于未设置NOT NULL约束的列)。

  ```mysql
  UPDATE 表名 SET 列名1=列值1, 列名2=列值2, ... [WHERE 条件] -- 不加条件则将表中所有记录修改
  UPDATE 表名 SET (列名1,列名2) = (列值1,列值2) [WHERE 条件] -- PostgreSQL和DB2中使用
  ```




## DQL

* <span style="color:red;font-weight:bold">查询不会修改数据库表记录！</span>
* <span style="color:red;font-weight:bold">顺序：FROM、WHERE、GROUP BY、HAVING、SELECT、ORDER BY、LIMIT</span>

### 基本查询(SELECT...FROM)

* <span style="color:red;font-weight:bold">字段(列)控制</span>

  * <span style="color:red;font-weight:bold">查询列(SELECT...FROM)</span>

    ```mysql
    SELECT * FROM 表名; -- 其中“*”表示查询所有列
    ```

    ```mysql
    SELECT 列1 [, 列2, ... 列N] FROM 表名; -- 查询指定列
    ```

  * <span style="color:red;font-weight:bold">去重复(DISTINCT)</span>，只能放在第一列之前

    ```mysql
    SELECT DISTINCT 列1 [, 列2, ... 列N]  FROM 表名; -- 结果集完全重复
    -- NULL 也被视为一类数据。NULL 存在于多行中时，也会被合并为一条 NULL 数据
    ```

* <span style="color:red;font-weight:bold">列运算</span>

  * <span style="color:red;font-weight:bold">给列起别名</span>，可以使用中文（使用双引号括起来，MySQL中可以单双引或不用，其他数据库暂不知道）

    ```mysql
    SELECT IFNULL(comm, 0)+1000 AS 奖金 FROM emp; -- AS可以省略
    ```
  
* <span style="color:red;font-weight:bold">常数查询</span>
  
  ```sql
    SELECT '商品' AS string, 38 AS number, '2009-02-24' AS date, product_id, product_name FROM Product;
  ```
  

![image-20190609192938116](images/image-20190609192938116.png)

* 数字类型的列可以做<span style="color:red;font-weight:bold">加、减、乘、除</span>运算
  
    > 【注意】与NULL运算时，结果都为NULL。甚至NULL / 0 都是NULL。
  
  ```mysql
    SELECT sal*1.5 FROM emp;
  SELECT sal+comm FROM emp;
  ```
  
  * <span style="color:red;font-weight:bold">替换NULL值</span>
  
    ```mysql
    SELECT IFNULL(comm, 0)+1000 FROM emp; -- 如果comm中存在NULL值，那么当成0来运算
    ```
  
  * 字符串类型可以做<span style="color:red;font-weight:bold">拼接运算</span>
  
    ```mysql
    SELECT CONCAT('$', sal) FROM emp;
    ```

> FROM子句真的有必要吗？实际上，通过执行 SELECT 语句来代替计算器的情况基本上是不存在的。Oracle 必须有！
>
> 只希望得到一行临时数据的情况，还是可以通过使用没有 FROM 子句的 SELECT 语句来实现某种业务的
>
> ```sql
> SELECT (100 + 200) * 3 AS calculation;
> ```



### 条件查询(WHERE)

* 运算符：可用于列运算、条件查询

  | 算术运算符 |      |
  | :--------: | ---- |
  |     +      |      |
  |     -      |      |
  |     *      |      |
  |     /      |      |
  |     ()     |      |

  | 比较运算符              | 说明                                                         |
  | ----------------------- | ------------------------------------------------------------ |
  | =、<>、<、>、<=、>=     | 对**数字、日期和字符（字符按照字典顺序）**等几乎所有数据类型的列和值进行比较<br />**<>在 SQL 中表示不等于**，很多RDBMS的方言可以使用!=。 |
  | **[NOT] BETWEEN...AND** | 在一个范围之内，如：between 100 and 200 相当于条件在 [100 到 200] 之间 |
  | **IS [NOT] NULL**       | 查询某一列为[不为] NULL 的值，注：不能写=NULL                |
| **LIKE**                | 模糊查询：_匹配一个任意字符；%匹配0~N个任意字符              |
  |                         |                                                              |
  | **[NOT] IN**、ALL、ANY  | 集合表示多个值，使用逗号分隔。ALL为所有，ANY为任意一个即可，可用最值替代 |
  | **[NOT] EXIST**         | 通常使用关联子查询作为参数（是否有该条记录，经常会使用 SELECT *） |
  
  > 实际上即使不使用 EXIST，基本上也都可以使用 IN(或者 NOT IN)来代替
  >
  > ```SQL
  > SELECT product_name, sale_price
  > FROM Product AS P 1
  > WHERE EXISTS (SELECT *  -- 关联子查询
  > 							FROM ShopProduct AS SP 2
  > 							WHERE SP.shop_id = '000C'
  > 							AND SP.product_id = P.product_id); -- 将 Product 表和 ShopProduct 表进行了联接
  > ```
  >
  > 
  
  | 逻辑运算符     | 说明                                                         |
  | -------------- | ------------------------------------------------------------ |
  | **AND**或 &&   | 与，后者并不通用；**优先于OR**，**“()”优先级最高**           |
  | **OR** 或 \|\| | 或                                                           |
  | **NOT**或 !    | 非；WHERE NOT代表后面的表达式的非运算（但是不要滥用，不清晰） |
  
```mysql
  WHERE age >= 18 AND age <= 80;
  WHERE age BETWEEN 18 AND 80;
  WHERE name='zhangSan' OR name='liSi';
  WHERE name IN ('zhangSan', 'liSi');
  WHERE age IS NULL; -- 不能使用等号
  WHERE age IS NOT NULL;
  WHERE sale_price - purchase_price >= 500;
```

  ```mysql
  SELECT * FROM emp WHERE ename LIKE '张_'; -- 姓张，名字由两个字组成的员工
  SELECT * FROM emp WHERE ename LIKE '___'; -- 姓名由3个字组成的员工
  
  SELECT * FROM emp WHERE ename LIKE '张%'; -- 查询的是姓张的所有员工
  SELECT * FROM emp WHERE ename LIKE '%阿%';-- 查询姓名中间带有阿字的员工
  SELECT * FROM emp WHERE ename LIKE '%'; -- 条件不存在，如果姓名为NULL的查询不出来
  ```

  > 与 NULL 运算时，结果都为 NULL。甚至 NULL / 0 都是 NULL。
  >
  > 查询 NULL 时不能使用比较运算符(= 或者 <>、>、<等)，因为结果为空不满足该条件，需使用 IS [ NOT ] NULL。
  >
  > 查询 NULL 时不能使用逻辑运算符，因为结果不为真也不为假，是不确定（UNKNOWN）



### 聚合函数(列的纵向运算)

<span style="color:red;font-weight:bold">只能用于 **SELECT** 子句和 **HAVING** 子句和 **ORDER BY**子句</span>

- 注意：聚合函数的计算，**排除了NULL值**。结果为单行单列的值。也可以修改聚合函数列名

  解决：**选择不包含NULL的列（主键）**；`IFNULL`函数

- <span style="color:red;font-weight:bold">COUNT</span>

  ```mysql
  SELECT COUNT(*) FROM emp; -- 计算emp表中所有列中只要有一列不为空的记录的行数，一般用主键代替*更好
  SELECT COUNT(comm) FROM emp; -- 计算emp表中comm列不为NULL的记录的行数
  ```

  <span style="color:red;font-weight:bold">MAX</span>（**原则上适用任何数据类型，能排序的，如日期、字符串**）

  ```mysql
  SELECT MAX(sal) FROM emp; -- 查询最高工资
  ```

- <span style="color:red;font-weight:bold">MIN</span>（**原则上适用任何数据类型，能排序的，如日期、字符串**）

  ```mysql
  SELECT MIN(sal) FROM emp; -- 查询最低工资
  ```

- <span style="color:red;font-weight:bold">SUM</span>（适用于**数值类型**）

  ```mysql
  SELECT SUM(sal) FROM emp; -- 查询工资合计
  ```

- <span style="color:red;font-weight:bold">AVG</span>（适用于**数值类型**）

  ```mysql
  SELECT AVG(sal) FROM emp; -- 查询平均工资
  ```

  > 与四则运算中若存在NULL，结果一定为NULL不同。这里会先排除为NULL的的值，之后才进行运算！

  **以上聚合函数 都可以配合`DISTINCT`来查询，如：**

  ```sql
  SELECT COUNT(DISTINCT product_type) FROM Product;
  ```

  

### 分组查询(GROUP BY)

* 在GROUP BY 子句中**指定的列称为聚合键**或者**分组列**。**一般与聚合函数一起使用！**可以有多个分组！
* **聚合键中包含 NULL** 时，在结果中会以**“不确定”行(空行)**的形式表现出来。

- **记录使用某一列进行<span style="color:red;font-weight:bold">分组(GROUP BY)</span>，然后查询组信息**

  ```mysql
  SELECT deptno, COUNT(*) FROM emp GROUP BY deptno; -- deptno分组，查询部门编号和每个部门记录
  SELECT job, MAX(SAL) FROM emp GROUP BY job; -- 使用job分组，查询每种工作的最高工资
  ```

**【与聚合函数和GROUP BY子句有关的常见错误】**

* 在 **SELECT** 子句中书写了多余的列。使用聚合函数时，SELECT 子句中只能存在以下三种元素：
  * 常数：数字 123，或者字符串 '测试'，或日期
  * 聚合函数
  * GROUP BY子句中指定的列名(也就是**聚合键**)，其实应该是每组共有的。
* 在**GROUP BY**子句中写了列的别名（MySQL 和 PostgreSQL 可以这样写，但不推荐）
  - 由于SQL的执行顺序为SELECT最后，所以别名不能用做GROUP BY中。
* **GROUP BY**子句的结果能排序吗
  - 通常 SELECT 语句的执行结果的显示顺序都是**随机**的，需要排序则需在SELECT中指定
* 在 **WHERE** 子句中使用聚合函数（不能使用）

> **DISTINCT**和**GROUP BY**都能够删除后续列中的重复数据。在“想要删除选择结果中的重复记录”时使用DISTINCT，在“想要计算汇总结果”时使用GROUP BY。不使用COUNT等聚合函数，而只使用GROUP BY子句的SELECT语句，会让人觉得非常奇怪



### 分组后条件(HAVING)

WHERE 和 HAVING 区别：

- **WHERE：分组前过滤数据，指定行对应条件；HAVING：分组后过滤数据，指定组对应条件**

  > 聚合键所对应的条件不应该书写在 HAVING 子句当中，而应该书写在 WHERE 子句当中。
  >
  > 通常情况下，将条件写在 WHERE 子句中要比写在 HAVING 子句中的处理速度更快。
  >
  > * 使用 COUNT 函数等对表中的数据进行**聚合**操作时，DBMS 内部就会进行**排序**处理。排序处理是会大大增加机器负担的高负荷的处理 。因此，只有尽可能减少排序的行数，才能提高处理速度。通过 WHERE 子句指定条件时，由于排序之前就对数据进行了过滤，因此能够减少排序的数据量。
  > * 可以对 WHERE 子句指定条件所对应的列**创建索引**，这样也可以大幅提高处理速度。

- **WHERE后不可以使用聚合函数，HAVING后可以使用聚合函数**

- ```sql
  SELECT deptno, COUNT(*) FROM emp GROUP BY deptno HAVING COUNT(*) > 3;-- 以部门分组...。条件为记录数大于3
  ```

* HAVING 子句中能够使用的 3 种要素如下所示：
  - 常数：数字 123，或者字符串 '测试'，或日期
  - 聚合函数
  - GROUP BY子句中指定的列名(也就是**聚合键**)



### 排序(ORDER BY...*SC)

> 通常，从表中抽取数据时，如果没有特别指定顺序，最终排列顺序便无从得知

* <span style="color:red;font-weight:bold">升序(ORDER BY...ASC)</span>，ascendent

  ```mysql
  SELECT * FROM WHERE emp ORDER BY sal ASC; -- ASC可以省略
  ```

* <span style="color:red;font-weight:bold">降序(ORDER BY...DESC)</span>，descendent

  ```mysql
  SELECT * FROM WHERE emp ORDER BY comm DESC; -- DESC不能省略
  ```

* 多字段作为排序条件

  ```mysql
  SELECT * FROM WHERE emp ORDER BY sal ASC, comm DESC; -- 按sal升序排，如果sal相同，按comm降序排
  ```
  
* **排序键中包含 NULL 时，会在开头或末尾进行汇总。**

* **在 ORDER BY 子句中可以使用 SELECT 子句中定义的别名。**与SQL执行顺序有关！

* 在 ORDER BY 子句中可以使用 SELECT 子句中未使用的列和聚合函数。

* 在 ORDER BY 子句中**不要使用列编号**：阅读困难；该功能将会删除



###  分页查询(LIMIT)

* MySQL、PostgreSQL、MariaDB 和 SQLite方言LIMIT用来限定查询结果的**起始索引（从0开始）**，以及**总行数**：**`开始的索引 = (当前页-1) * 每页记录数`**

* 第一个参数是0可以省略；最后不足总行数的话，有多少显示多少。

  ```mysql
/*1. 一页的记录数：10行;2. 查询第3页*/
  select * from emp limit 20, 10;
  ```

* 如果是 Oracle，你需要基于 ROWNUM 来统计行数

  ```sql
  SELECT name, hp_max 
  FROM (SELECT name, hp_max 
        FROM heros 
        ORDER BY hp_max) 
  WHERE ROWNUM <=5
  ```

  

## DCL

* DBA：数据库管理员

* 管理用户

  1. 查询用户：

     1. 切换到mysql数据库：`USE myql;`

     2. 查询user表：`SELECT * FROM USER;`

        > **通配符： %** 表示可以在任意主机使用用户登录数据库		

  2. 添加用户：

     * 语法：`CREATE USER '用户名'@'主机名' IDENTIFIED BY '密码';`，密码可以为空，不用密码即可登陆

  3. 删除用户：

     * 语法：`DROP USER '用户名'@'主机名';`

  4. 修改用户密码：

     - 语法：`UPDATE USER SET PASSWORD = PASSWORD('新密码') WHERE USER = '用户名';`

       ​          `SET PASSWORD FOR '用户名'@'主机名' = PASSWORD('新密码');`

     - 修改管理员密码：`mysqladmin -uroot -p password 新密码 `

       > 注意：需要在未登陆 MySQL 的情况下操作，新密码不需要加上引号。 

     - mysql中忘记了root用户的密码？

       1. cmd(管理员) -- > `net stop mysql`，停止mysql服务
       2. 使用无验证方式启动mysql服务： `mysqld --skip-grant-tables`
       3. 打开新的cmd窗口,直接输入`mysql`命令，敲回车。就可以登录成功
       4. `use mysql;`
       5. `update user set password = password('你的新密码') where user = 'root';`
       6. 关闭两个窗口
       7. 打开任务管理器，手动结束`mysqld.exe` 的进程
       8. 启动mysql服务：`net startmysql`
       9. 使用新密码登录

* 权限管理（新添加的用户没有权限）

  1. 查询权限：`SHOW GRANTS FOR '用户名'@'主机名';`

  2. 授予权限：`grant 权限列表 on 数据库名.表名 to '用户名'@'主机名';`

     ​		`GRANT ALL ON *.* TO 'zhangsan'@'localhost';`：给张三用户授予所有权限，在任意数据库任意表上

  3. 撤销权限：`revoke 权限列表 on 数据库名.表名 from '用户名'@'主机名';`，撤销所有同上



# 复杂查询

## 视图

表中存储的是实际数据，而**视图**中保存的是**从表中取出数据所使用的 SELECT 语句**。将经常使用的 SELECT 语句做成视图。

* 创建视图

  ```SQL
  CREATE VIEW 视图名称(<视图列名1>, <视图列名2>, ......) AS
  <SELECT 语句 >
  ```

  SELECT 语句需要书写在 AS 关键字之后。SELECT 语句中列的排列顺序和视图中列的排列顺序相同

* 删除视图

  ```sql
  DROP VIEW 视图名称(<视图列名1>, <视图列名2>, ......)
  ```

  在 PostgreSQL 中，如果删除以视图为基础创建出来的多重视图，由于存在关联的视图，因此会发生错误。使用 CASCADE 选项来删除关联视图。`DROP VIEW ProductSum CASCADE;`

> 应该避免在视图的基础上创建视图，因为对多数DBMS来说多重视图会降低 SQL 的性能。

* 注意：
  * 定义视图时不能使用ORDER BY子句（PostgreSQL可以使用）
  * （视图和表需要同时进行更新，因此通过汇总得到的视图无法进行更新）对视图进行更新需满足：
    * SELECT 子句中未使用 DISTINCT
    * FROM 子句中只有一张表
    * 未使用GROUP BY子句
    * 未使用 HAVING 子句



## 子查询

子查询就是将用来定义视图的 SELECT 语句直接用于FROM子句当中，但是不会像视图那样保存在存储介质(硬盘)之中，而是在 SELECT 语句执行之后就消失了。实际上 SELECT 语句包含嵌套的结构，**首先会执行 FROM 子句中的 SELECT 语句，然后才会执行外层的 SELECT 语句**。尽量避免使用多层嵌套的子查询。

**子查询必须设定名称**。Oracle 需要省略 AS 关键字才可以使用。

* **标量子查询**(scalar subquery)：必须而且**只能返回 1 行 1 列**的结果，即返回单一值的子查询。任何位置都可以使用。

  可用在= 或者 <> 等需要单一值的比较运算符中

  > 由于在WHERE中不能使用聚合函数，所以可以使用标量自查询来代替！SELECT 中也比较常见！
  
  ```sql
  SELECT product_id, product_name, product_type, sale_price,
         (SELECT AVG(sale_price) FROM Product  )
  FROM Product;
  ```
  



- 有查询的嵌套，内部的查询称为子查询  （看SELECT关键字的个数！）

- **FROM**后作为**表**存在，或用普通内连接添加多个条件来查询

  - **多行多列**

    ```mysql
    SELECT * FROM 表1 别名1 , (SELECT ....) 别名2 WHERE 条件
    ```

- **WHERE**后作为**条件**存在

  - **单行单列**：运算符为 >、<、>=、<=、=、<>

    ```mysql
    SELECT * FROM 表1 别名1 WHERE 列1 [=、>、<、>=、<=、!=] (SELECT 列 FROM 表2 别名2 WHERE 条件)
    ```

  - **多行单列**：运算符为 IN、ALL、ANY

    ```mysql
    SELECT * FROM 表1 别名1 WHERE 列1 [IN, ALL, ANY] (SELECT 列 FROM 表2 别名2 WHERE 条件)
    ```



## 关联子查询

在**细分的组内进行比较时**，需要使用关联子查询。通常会使用“限定(绑定)”或者“限制”这样的语言。必须执行时返回1行1列结果。

```SQL
SELECT product_type, product_name, sale_price
FROM Product P1
WHERE sale_price > (SELECT AVG(sale_price)
                    FROM Product P2
                    WHERE P1.product_type = P2.product_type -- 关键在这句！
                    GROUP BY product_type); -- 其实可以省略这句，因为WHERE的条件已经令AVG按照type分组了
-- WHERE条件的意思是 在同一商品种类中对各商品的销售单价和平均单价进行比较
-- 且不能把WHERE放在外部，首先P2是有作用域的，且SQL执行是按照先内部再外部的顺序
```

换个角度来看，其实关联子查询也和GROUP BY子句一样，可以对集合进行切分。

![image-20190623002605492](images/image-20190623002605492.png)





# 函数、谓词、CASE表达式

## 函数

> 所谓函数，就是输入某一值得到相应输出结果的功能，输入值称为参数(parameter)，输出值称为返回值。

### 算术函数

* **ABS**——绝对值

  ```sql
  SELECT m, ABS(m) AS abs_col FROM SampleMath;
  ```

  > ABS 函数的参数为 NULL 时，结果也是 NULL。绝大多数函数对于 NULL 都返回 NULL，但是转换函数中的COALESCE   
  >
  > 函数除外 

* **MOD**——求余

  ```sql
  SELECT n, p, MOD(n, p) AS mod_col FROM SampleMath;
  ```

  > 主流的 DBMS 都支持 MOD 函数，只有SQL Server 不支持该函数，使用“%”来计算余数。

* **ROUND**——四舍五入

  ```sql
  SELECT m, n, ROUND(m, n) AS round_col FROM SampleMath;
  ```

  > m:对象数值；n:四舍五入位数（保留）。

  

### 字符串函数

* **||**——拼接

  ```sql
  SELECT str1, str2, str1 | | str2 AS str_concat FROM SampleStr;
  ```

  > 进行字符串拼接时，如果其中包含 NULL，那么得到的结果也是NULL。这是因为“||”也是变了形的函数。多个也可以。
  >
  > 但是 || 函数在 SQL Server 和 MySQL 中无法使用。 
  >
  > * SQL Server使用“+”
  > * MySQL使用CONCAT函数（可多个）

* **LENGTH**——字符串长度

  ```sql
  SELECT str1, LENGTH(str1) AS len_str FROM SampleStr;
  ```

  > 该函数也无法在 SQL Server 中使用，可以使用LEN函数来计算。

  > **MySQL 中的 LENGTH** 以字节为单位的函数进行计算时，“LENGTH( 山田 )”的返回结果是 4。MySQL中还存在计算字符串长度的自有函数 CHAR_LENGTH 。

* **LOWER / UPPER**——小大写转换

  ```sql
  SELECT str1, LOWER(str1) AS low_str ....
  ```

  > LOWER / UPPER 函数只能针对英文字母使用

* **REPLACE**——字符串的替换

  ```sql
  SELECT str1, str2, str3, REPLACE(str1, str2, str3) AS rep_str... -- 对象字符串，替换前的字符串，替换后的字符串
  ```

* **SUBSTRING**——字符串的截取

  ```sql
  SELECT str1, SUBSTRING(str1 FROM START FOR LEN) AS sub_str ...
  ```

  > 虽然上述 SUBSTRING 函数的语法是标准 SQL 承认的正式语法，但是现在只有 PostgreSQL 和 MySQL 支持该语法。 
  >
  > 该函数也存在和LENGTH函数同样的**多字节字符的问题**。
>
  > 起始位置都是从1开始！！！

  ```sql
  SUBSTRING(对象字符串 FROM 截取的起始位置 FOR 截取的字符数) -- PostgreSQL 、 MySQL 都可以
  SUBSTRING(对象字符串，截取的起始位置，截取的字符数 ) -- SQL Server、MySQL 都可以
  SUBSTR(对象字符串，截取的起始位置，截取的字符数) -- Oracle/DB2 专用语法;MySQL也可以使用，可以有前2个参数或3个参数
  ```



### 日期函数

* **CURRENT_DATE**——当前日期

  ```sql
  SELECT CURRENT_DATE; -- PostgreSQL 和 MySQL
  SELECT CAST(CURRENT_TIMESTAMP AS DATE) AS CUR_DATE;-- SQL Server CAST函数将CURRENT_TIMESTAMP转换为日期类型
  
  /* 在 Oracle 中使用该函数时，需要在 FROM 子句中指定临时表(DUAL)。在 DB2 中使用时，需要在CRUUENT和DATE之间添加半角空   格，并且还需要指定临时表 SYSIBM.SYSDUMMY1(相当于 Oracle 中的 DUAL)。 */
  SELECT CURRENT_DATE FROM dual; -- Oracle
  SELECT CURRENT DATE FROM SYSIBM.SYSDUMMY1; -- DB2
  ```

* **CURRENT_TIME**——当前时间

  ```sql
  SELECT CURRENT_TIME; -- PostgreSQL 和 MySQL
  SELECT CAST(CURRENT_TIMESTAMP AS TIME) AS CUR_TIME; --SQL Server CAST函数将CURRENT_TIMESTAMP转换为时间类型
  
  /* 需要注意的地方和 CURRENT_DATE 函数相同。在 Oracle 中使用时所得到的结果还包含日期。 */
  SELECT CURRENT_TIMESTAMP FROM dual; -- Oracle
  SELECT CURRENT TIME FROM SYSIBM.SYSDUMMY1; --DB2 CURRENT和TIME之间使用了半角空格，指定临时表SYSIBM.SYSDUMMY1
  ```

* **CURRENT_TIMESTAMP**——当前日期和时间

  ```sql
  SELECT CURRENT_TIMESTAMP; -- PostgreSQL、MySQL 和 SQL Server
  
  /* 需要注意的 地方与 CURRENT_DATE 时完全相同。 */
  SELECT CURRENT_TIMESTAMP FROM dual; -- Oracle
  SELECT CURRENT TIMESTAMP FROM SYSIBM.SYSDUMMY1;--DB2 CURRENT和TIME之间使用了半角空格，指定临时表SYSIBM.SYSDUMMY1
  ```

* **EXTRACT**——截取日期元素

  ```sql
  SELECT CURRENT_TIMESTAMP,
  EXTRACT(YEAR/MONTH/DAY/HOUR/MINUTE/SECOND) FROM CURRENT_TIMESTAMP) .. -- PostgreSQL 和 MySQL
  
  SELECT CURRENT_TIMESTAMP, DATEPART(YEAR , CURRENT_TIMESTAMP) -- SQL Server
  SELECT CURRENT_TIMESTAMP, EXTRACT(YEAR FROM CURRENT_TIMESTAMP) AS year -- Oracle
  /* CURRENT和TIME之间使用了半角空格，指定临时表SYSIBM.SYSDUMMY1 */
  SELECT CURRENT TIMESTAMP, EXTRACT(YEAR FROM CURRENT TIMESTAMP) AS year, -- DB2
  ```

  > 该函数的返回值并不是日期类型而是**数值类型**。 

* DATE()：返回时间的日期部分，下同

  > DATE 日期格式必须是 yyyy-mm-dd 的形式。

* YEAR()

* MONTH()

* DAY()

* HOUR()

* MINUTE()

* SECOND()



### 转换函数

“转换”这个词的含义非常广泛，在 SQL 中主要有两层意思：一是数据类型的转换，简称为类型转换，在英语中称为 castA ；另一层意思是值的转换。

* **CAST**——类型转换

  ```sql
  CAST(转换前的值 AS 想要转换的数据类型);
  
  SELECT CAST('0001' AS INTEGER) AS int_col; -- SQL Server PostgreSQL
  SELECT CAST('0001' AS SIGNED INTEGER) AS int_col; -- MySQL
  SELECT CAST('0001' AS INTEGER) AS int_col FROM DUAL; -- Oracle
  SELECT CAST('0001' AS INTEGER) AS int_col FROM SYSIBM.SYSDUMMY1; -- DB2
  ```

  ```SQL
  SELECT CAST('2009-12-14' AS DATE) AS date_col; -- SQL Server PostgreSQL MySQL
  SELECT CAST('2009-12-14' AS DATE) AS date_col FROM DUAL; -- Oracle
  SELECT CAST('2009-12-14' AS DATE) AS date_col FROM SYSIBM.SYSDUMMY1; -- DB2
  ```

  > 将字符串类型转换为整数类型时，前面的 “000”消失了，能够切实感到发生了转换。但是，将字符串转换为日期类型时，从结果上并不能看出数据发生了什么变化。类型转换其实并不是为了方便用户使用而开发的功能，而是为了方便 DBMS 内部处理而开发的功能。

* **COALESCE**——将 **NULL** 转换为其他值，返回第一个非空数值

  ```sql
  SELECT COALESCE(数据1,数据2,数据3...)
  /* COALESCE 是 SQL 特有的函数。该函数会返回可变参数中左侧开始第1个不是 NULL 的值。*/
  SELECT COALESCE(NULL, NULL, '2009-11-01') ... --SQL Server PostgreSQL MySQL
  SELECT COALESCE(NULL, NULL, '2009-11-01') AS col_1 FROM DUAL; -- Oracle
  SELECT COALESCE(NULL, NULL, '2009-11-01') AS col_1 FROM SYSIBM.SYSDUMMY1; --  DB2
  ```

  > 多数 DBMS 中都提供了特有的 COALESCE 的简化版函数(如Oracle 中的 NVL 等)



### 聚合函数（略）

## 谓词

> 谓词就是**返回值为真值的函数**。例如，=、<、>、<> 等比较运算符，其正式的名称就是比较谓词。是需要满足特定条件的函数，该条件就是返回值是真值TRUE/FALSE/UNKNOWN)。如 : LIKE、IS NULL、IS NOT NULL、BETWEEN、IN、EXISTS。查看DQL中WHERE条件查询。



## CASE表达式

> CASE表达式分为简单CASE表达式和搜索CASE表达式两种。搜索 CASE 表达式包含简单 CASE 表达式的全部功能。 
>
> * 虽然CASE表达式中的ELSE子句可以省略，这时会被默认为ELSE NULL，但为了阅读方便尽量写上
> * CASE 表达式中的 END 不能省略
> * 使用CASE表达式能够将SELECT语句的结果进行组合

### 搜索 CASE 表达式

```sql
SELECT
  product_name,
  CASE
    WHEN product_type = '衣服' THEN 'A :' | | product_type
    WHEN product_type = '办公用品' THEN 'B :' | | product_type
    WHEN product_type = '厨房用具' THEN 'C :' | | product_type
    ELSE NULL
  END AS abc_product_type
FROM
  Product;
```

在对 SELECT 语句的**结果进行编辑**时，CASE 表达式能够发挥较大作用。

### 简单 CASE 表达式

```sql
SELECT
  product_name,
  CASE
    product_type
    WHEN '衣服' THEN 'A :' || product_type
    WHEN '办公用品' THEN 'B :' || product_type
    WHEN '衣厨房用具' THEN 'C :' || product_type
    ELSE NULL
  END AS abc_product_type
FROM
  Product;
```

想要在 WHEN 子句中指定不同列时，简单 CASE 表达式就无能为力了。 



> 有些 DBMS 还提供了一些特有的 CASE 表达式的简化函数，例如 Oracle 中的 DECODE、MySQL 中的 IF 等。



# 集合运算

> 集合在数学领域表示“(各 种各样的)事物的总和”，在数据库领域表示记录的集合。具体来说，表、 视图和查询的执行结果都是记录的集合。 

## 表的加减法

### 表的加法——**UNION** 

UNION 等集合运算符通常都会除**去重复**的记录。注意（同样适用于其他）：

* 作为运算对象的记录的**列数必须相同**
* 作为运算对象的记录中**列的类型必须一致**。CAST转换后一致也可以。
* 可以使用任何**SELECT、WHERE、GROUP BY、HAVING等**语句，但**ORDER BY**子句只能在最后使用一次



### 包含重复行——**UNION ALL** 



### 公共部分——**INTERSECT**

选取两个记录集合中公共部分的 **INTERSECT(交集)**



### 表的减法——**EXCEPT**

减法运算 EXCEPT(差集)，其语法也与 UNION 相同。

EXCEPT 有一点与 UNION 和 INTERSECT 不同，那就是在减法运算中减数和被减数的**位置**不同，所得到的结果也不相同。

> 只 有 Oracle 不 使 用 EXCEPT，而是使用其特有的MINUS运算符。使用Oracle的用户，请用MINUS代替 EXCEPT。
>
> 此外，MySQL 还不支持 EXCEPT，因此也无法使用。



## 连接/联结(以列为单位)

> 联结(JOIN)就是将其他表中的列添加过来，进行“添加列”的集合运算。 UNION 是以行(纵向)为单位进行操作，而联结则是以列(横向)为单位 进行的。分为内联结和外联结两种。
>
> ON 是专门用来指定联结条件的（联结键），它能起到与 WHERE 相同的作用。需要指定多个键时，同样可以使用 AND、OR。并且 ON 必须书写在 FROM 和 WHERE 之间。
>
> 联结条件也可以使用“=”来记述。在语法上，还可以使用 <= 和 BETWEEN 等谓词。但是九成以上都用=。

### 内连接——JOIN...ON

- 内连接查询出的所有记录**都满足条件**

- 显式内连接 (**[INNER] JOIN...ON**)

  ```mysql
  SELECT * FROM 表1 (AS) 别名1 INNER JOIN 表2 别名2 ON 别名1.xx=别名2.xx -- 还有不等关系
  ```

- 隐式内连接( , WHERE)（MySQL和Oracle都支持，与JOIN不同！主表为第一个！）

  ```mysql
  SELECT * FROM 表1 别名1, 表2 别名2 WHERE 别名1.xx=别名2.xx -- 还有不等关系
  ```

### 外连接——* JOIN...ON

- **左外**(**LEFT [OUTER] JOIN...ON**)

  **左表记录**无论是否满足条件**都会查询出**，而**右表满足条件才能查出**。左表中不满条件的记录，右表补**NULL**

  ```mysql
  SELECT * FROM 表1 别名1 LEFT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx -- 还有不等关系
  ```

- **右外**(**RIGHT [OUTER] JOIN...ON**)

  **右表记录**无论是否满足条件**都会查询出**，而**左表满足条件才能查出**。右表中不满条件的记录，左表补**NULL**

  ```mysql
  SELECT * FROM 表1 别名1 RIGHT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx -- 还有不等关系
  ```

### 交叉联结——CROSS JOIN

> 这种联结在实际业务中并不会使用，但是交叉联结是所有联结运算的基础。
>
> 对满足相同规则的表进行交叉联结的集合运算符是CROSS JOIN(笛卡儿积)。进行交叉联结时无法使用内联结和外联结中所使用的 ON 子句

## DQL练习

### 单表查询

- 找出奖金高于工资60%的员工

  ```mysql
  SELECT * 
  FROM emp
  WHERE COMM>SAL*0.6;
  ```

- 找出部门编号10所有经理，部门编号20中所有销售员，即不是经理又不是销售员但其工资大或等于20000的...

  ```mysql
  select * 
  from emp
  where (DEPTNO=10 AND JOB='经理') OR (DEPTNO=20 AND JOB='销售员') OR (JOB NOT IN('经理','销售员') AND SAL>=20000);
  ```

- 查询2000年入职的员工

  ```mysql
  select * 
  from emp
  WHERE HIREDATE LIKE '2002%';
  ```

- 查询所有员工详细信息，用工资降序排序，如果工资相同使用入职日期升序排序

  ```mysql
  select * 
  from emp
  order by SAL desc,HIREDATE ASC;
  ```

- 查询每种工作的最高工资、最低工资、平均工资、人数

  ```mysql
  select JOB,AVG(SAL) 平均工资,MAX(SAL) AS 最高工资,MIN(SAL)最低工资,COUNT(*) 人数 
  from emp
  group by JOB;
  ```

- 有奖金的工种

  ```mysql
  select JOB 
  FROM emp
  where COMM IS NOT NULL
  group by JOB;
  ```

- 显示非销售人员工作名称以及从事同一工作雇员的月工资的总和，并且要满足从事同一工作的雇员的月工资合计大于50000，输出结果按月工资的合计升序排列

  ```mysql
  select JOB,SUM(SAL) 
  FROM EMP
  GROUP BY JOB
  HAVING SUM(SAL)>50000 AND JOB<>'销售员'
  ORDER BY SUM(SAL) ASC;
  ```

### 多表查询

- 查出至少有一个员工的部门。显示部门编号、部门名称、部门位置、部门人数

  ```mysql
  SELECT d.deptno,d.dname,d.loc,t.cnt
  FROM dept d 
  	JOIN (SELECT emp.deptno, COUNT(*) cnt FROM emp GROUP BY emp.deptno HAVING COUNT(*)>1) t 
  	ON d.deptno = t.deptno;
  ```

- 列出薪金比关羽高的所有员工

  ```mysql
  SELECT * 
  FROM emp
  WHERE sal>(SELECT sal FROM emp WHERE ename='关羽')
  ```

- 列出所有员工的姓名及其直接上级的姓名

  ```mysql
  SELECT e.ename, IFNULL(m.ename, 'BOSS') 领导
  FROM emp e LEFT JOIN emp m
  ON e.mgr=m.empno;
  ```

- 列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称(**三张表**)

  ```mysql
  SELECT e.empno, e.ename, d.dname
  FROM emp e 
  	JOIN emp m ON e.mgr=m.empno 
  	JOIN dept d ON e.deptno=d.deptno
  WHERE e.hiredate<m.hiredate;
  ```

- 列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级(**四张表**)

  ```mysql
  SELECT emp.*,dept.dname,e2.ename,salgrade.grade
  FROM emp  
  	LEFT JOIN dept ON emp.deptno = dept.deptno 
  	LEFT JOIN emp e2  ON emp.mgr = e2.empno
  	LEFT JOIN salgrade ON emp.sal BETWEEN salgrade.losal AND salgrade.hisal 
  WHERE emp.sal>(SELECT AVG(emp.sal) FROM emp);
  ```

- **查出年份、利润、年度增长比**

  ```mysql
  SELECT E1.*,IFNULL(CONCAT((E1.zz-E2.zz)/E2.zz*100,'%'),0)
  FROM lirun E1 
  	JOIN lirun E2 ON E1.`year`=E2.`year`+1
  ORDER BY E1.`year` ASC;
  ```



# SQL 高级处理（暂停）

## 窗口函数

> 窗口函数也称为 OLAP 函数。OLAP 是 OnLine Analytical Processing 的简称，意思是对数据库数据进行实时分析处理。例如，市场分析、创建财务报表、创建计划等日常性商务工作。窗口函数就是为了实现 OLAP 而添加的标准 SQL 功能。
>
> 截至 2016 年 5 月，Oracle、SQL Server、DB2、PostgreSQL 的最新版本都已经支持了该功能，但是 MySQL 的最新版本5.7 还是不支持该功能。
>
> 窗口函数可以进行排序、生成序列号等一般的聚合函数无法实现的高级操作。

语法

```sql
< 窗口函数 > OVER ([PARTITION BY < 列清单 >] ORDER BY < 排序用列清单 >) -- []中的内容可以省略
```

能够作为窗口函数使用的函数:

* 能够作为窗口函数的聚合函数(SUM、AVG、COUNT、MAX、MIN)
* RANK、DENSE_RANK、ROW_NUMBER 等专用窗口函数

重要的关键字是PARTITION BY和ORDER BY



## GROUPING 运算符



# 事务(transaction)

在 RDBMS 中，**事务是对表中数据进行更新的单位**。简单来讲，**事务就是需要在同一个处理单元中执行的一系列更新处理的集合**。通过使用事务，可以对数据库中的数据更新处理的提交和取消进行管理。

> 如果一个包含多个步骤的业务操作，被事务管理，那么这些操作**要么同时成功，要么同时失败**。

- 开启事务：`START TRANSACTION`（MySQL）；`BEGIN TRANSACTION`（PostgreSQL、SQL Server）；

  Oracle 和 DB2 无，标准SQL手册中记述，事务会自动开始。但是结束必须手动；

- 结束事务：`COMMIT`（没有问题则提交）或`ROLLBACK`（有问题则回滚）

事务处理何时开始？几乎所有的数据库产品的事务都无需开始指令，事务在数据库连接建立时就已经悄悄开始了，并不需要用户再明确发出开始指令。如何区分各个事务呢？有如下两种情况：

- 自动提交：每条DML语句就是一个事务(**自动提交模式**)。有SQL Server、PostgreSQL 和 MySQL
- 手动提交：直到用户执行COMMIT或者ROLLBACK为止算作一个事务。有Oracle

- **MySQL数据库中事务默认自动提交**
  - 事务提交的两种方式：
    - 自动提交：
      - **MySQL就是自动提交的**
      - **一条DML(增删改)**语句会自动提交一次事务。
    - 手动提交：
      - **Oracle 数据库默认是手动提交事务**
      - 需要**先开启事务，再提交**
  - 修改事务的默认提交方式：
    - 查看事务的默认提交方式(MySQL命令)：`SELECT @@autocommit;`1 代表自动提交；0 代表手动提交
    - 修改默认提交方式： `set @@autocommit = 0;`

## 事务的四大特性（ACID）

- **原子性**（Atomicity）：事务中所有操作是不可再分割的原子单位。即事务中所有操作要么全部执行成功，要么全部失败。
- **一致性**（Consistency）：事务执行后，**数据库状态保持一致**。如转账业务，无论事务执行成功与否，参与转账的两个账号余额之和应该是不变的。
- **隔离性**（Isolation）：隔离性是指在并发操作中，不同事务之间应该隔离开来，使每个并发中的事务不会相互干扰。
- **持久性**（Durability）：一旦事务提交成功，它对数据库的改变必须是永久的，即使出现系统故障。

## 事务的隔离级别

- 概念：如果多个事务操作同一批数据，则会引发一些问题，设置不同的隔离级别就可以解决这些问题

- 并发事务**问题** 

  - **脏读**：一个事务读取到了另一个事务中**尚未提交的数据** 
  - **不可重复读**：一个事务中**两次读取的数据内容不一致**，要求的是一个事务中多次读取时数据是一致的，这是事务update 时引发的问题 
  - **幻读**（虚读）：一个事务中**两次读取的数据的数量不一致**，要求在一个事务多次读取的数据的数量是一致 的，这是 insert 或 delete 时引发的问题。

- **四大隔离级别**（“×”表示会出现这种问题；“ ”表示不会出现这种问题）

  | 级别 |   名称   |     隔离级别     | 脏读 | 不可重复读 | 幻读 |  数据库默认隔离级别  |
  | :--: | :------: | :--------------: | :--: | :--------: | :--: | :------------------: |
  |  1   | 读未提交 | read uncommitted |  ×   |     ×      |  ×   |                      |
  |  2   | 读已提交 |  read committed  |      |     ×      |  ×   | Oracle 和 SQL Server |
  |  3   | 可重复读 | repeatable read  |      |            |  ×   |        MySQL         |
  |  4   |  串行化  |   serializable   |      |            |      |                      |

  > 上面的级别最低，下面的级别最高。隔离级别越高，性能越差，安全性越高。 

  - 查询隔离级别(MySQL语句)：`select @@tx_isolation;`
  - 设置隔离级别(MySQL语句)：`set global transaction isolation level 隔离级别字符串; `

- 演示：通过转账示例，每次设置不同的隔离级别，甲乙两方**分别开启事务**，可查看到不同的结果。MySQL中不能演示幻读。

  - read uncommitted：在一次事务中甲方执行转账操作未提交时，乙方也可查询到账情况（脏读、不可重复读）

  - read committed：在一次事务中甲方执行转账操作只有提交后，乙方才能查询到账情况。两次结果不同（不可重复读）

  - repeatable read：在一次事务中甲方执行转账操作提交后，乙方只能在提交后才能查询到账情况（解决上述俩问题）

  - serializable：在一次事务中甲方执行转账操作未提交，此时乙方查询一直等待，只有甲方提交后才显示。（解决幻读）

    ```mysql
    set global transaction isolation level read uncommitted;
    start transaction;
    -- 转账操作
    update account set balance = balance - 500 where id = 1;
    update account set balance = balance + 500 where id = 2;
    ...
    ```

- JDBC设置隔离级别(con. setTransactionIsolation(int level))

  - 参数可选值如下：

    Connection.TRANSACTION_READ_UNCOMMITTED；

    Connection.TRANSACTION_READ_COMMITTED；

    Connection.TRANSACTION_REPEATABLE_READ；

    Connection.TRANSACTION_SERIALIZABLE。


# 4 数据库的设计

## 4.1 多表之间关系

概念模型（了解）

- 对象模型：可以双向关联，而且引用的是对象，而不是一个主键！在java中是domain！！！例如：User
- 关系模型：只能多方引用一方，而且引用的只是主键，而不是一整行记录。在数据库中就是表
- 当我们要完成一个软件系统时，需要把系统中的**实体抽取出来，形成概念模型**。  例如部门、员工都是系统中的实体。概念模型中的实体最终会成为**Java中的类**、**数据库中表**。实体之间还存在着关系：
  - 1对1：例如老公和老婆就是一对一的关系，一个老公只能有一个老婆，一个老婆只能有一个老公。
  - 1对多：例如每个员工都从属一个部门，而一个部门可以有多个员工，其中员工是多方，而部门是一方。
  - 多对多：老师与学生的关系就是多对多，一个老师可以有多个学生，一个学生可以有多个老师。

### 4.4.1 一对一关系

- 任意一方中**外键**又是**主键**（外键保证主表必须有记录；主键保证记录唯一）。开发不用！不如合成一张表！

  ```mysql
  create table husband(
      hid int PRIMARY KEY,
      ...
  );
  create table wife(
      wid int PRIMARY KEY,
      ...
      ADD CONSTRAINT fk_wife_wid FOREIGN KEY(wid) REFERENCES husband(hid)
  );
  ```

### 4.4.2 一对多关系

* 在多的一方（从表）建立**外键**，指向一的一方（主表）的**主键**

### 4.4.3 多对多关系

- 需要使用**中间表**，在中间表中使用两个**外键**，分别引用其他两个表的主键

  ```mysql
  create table student(
    sid int PRIMARY KEY,
    ...
  );
  create table teacher(
    tid int PRIMARY KEY,
    ...
  );
  create table stu_tea(
    sid int,
    tid int,
    PRIMARY KEY(sid,tid), -- 设置复合主键，同一个学生不能选择同一个老师两次 
    ADD CONSTRAINT fk_stu_tea_sid FOREIGN KEY(sid) REFERENCES student(sid),
    ADD CONSTRAINT fk_stu_tea_tid FOREIGN KEY(tid) REFERENCES teacher(tid)
  );
  ```



## 4.2 三大范式

> 好的数据库设计对数据的存储性能和后期的程序开发，都会产生重要的影响。建立科学的，**规范的数据库就需要满足一些规则来优化数据的设计和存储**，这些**规则就称为范式**。 

* 目前关系数据库有六种范式：**第一范式（1NF）**、**第二范式（2NF）**、**第三范式（3NF）**、巴斯-科德范式（BCNF） 、 第四范式(4NF）和 第五范式（5NF，又称完美范式）。 

  > 满足最低要求的范式是第一范式（1NF）。在第一范式的基础上进一步满足更多规范要求的称为第二范式（2NF）， 其余范式以次类推。一般说来，数据库只需满足第三范式(3NF）就行了。 

### 4.2.1 第一范式（1NF）

* 第一范式（1NF）：**每一列都是不可分割的原子数据项**

![](.\images\1NF.PNG)

### 4.2.2 第二范式（2NF）

* 第二范式（2NF）：在1NF的基础上**非主属性必须完全依赖于码**（在1NF基础上<u>消除非主属性对主码的**部分函数依赖**</u>）

  > **码**：如果在一张表中，**一个属性(属性组)，被其他所有属性所完全依赖**，则称这个属性(属性组)为该表的码
  > 例如：该表中码为：（学号，课程名称）
  >
  > - 主属性：码中的所有属性
  > - 非主属性：除过码中属性的属性
  >
  > **函数依赖**：A-->B,如果通过A属性(属性组)的值，可以确定唯一B属性的值。则称B依赖于A
  > 例如：学号-->姓名。  （学号，课程名称） --> 分数
  >
  > **完全函数依赖**：A-->B， 如果A是一个属性组，则B属性值的确定需要依赖于A属性组中所有的属性值。
  > 例如：（学号，课程名称） --> 分数
  >
  > **部分函数依赖**：A-->B， 如果A是一个属性组，则B属性值得确定只需要依赖于A属性组中某一些值即可。
  > 例如：（学号，课程名称） -- > 姓名
  >
  > **传递函数依赖**：A-->B, B -- >C,通过A属性(属性组)的值，可以确定唯一B属性的值，~~，则称 C 传递函数依赖于A      例如：学号-->系名，系名-->系主任

![](.\images\2NF.PNG)

### 4.2.3 第三范式（3NF）

* 第三范式（3NF）：在2NF基础上**任何非主属性不依赖于其它非主属性**（在2NF基础上<u>消除非主属性对于码的**传递函数依赖**</u>）

![](.\images\3NF.PNG)







## 4.3 备份

* 查看MySQL数据库编码

  ```mysql
  SHOW VARIABLES LIKE 'char%';
  ```

  * **character_set_client**：MySQL使用该编码来解读客户端发送过来的数据，例如该编码为UTF8，那么如果客户端发送过来的数据不是UTF8，那么就会出现乱码。8.0默认为gbk。

    * **character_set_results**：MySQL会把数据转换成该编码后，再发送给客户端，例如该编码为UTF8，那么如果客户端不使用UTF8来解读，那么就会出现乱码。8.0默认为gbk。

  * 在my.ini中设置(8.0中默认没有值，但默认为gbk)

    ```ini
    default-character-set=gbk
    #可以修改三个变量：client、results、connection
    ```

* 数据库导出SQL脚本(备份数据库**内容**，并不是备份数据库！)

  **生成的脚本文件中不包含create database语句**

  ```powershell
  # mysqldump –u用户名 –p密码 数据库名 > 生成的脚本文件路径
  mysqldump -uusername -ppassword db1 <db1.sql
  ```

* SQL脚本导入到数据库

  ```powershell
  mysql -uusername -ppassword db1 <db1.sql
  ```

  ```mysql
  use mydb; -- 要先创建数据库并使用
  source c:/mydb.sql
  ```







- - 



# 7 JDBC

* JDBC（Java DataBase Connectivity）就是Java数据库连接，说白了就是用Java语言来操作数据库
* **本质**：其实是官方（sun公司）定义的一套**操作所有关系型数据库的规则（接口）**。各个数据库厂商去实现这套接口，提供**数据库驱动jar包**。我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类。

## 7.1 快速入门

- 导入驱动jar包
  - libs目录中放入：`mysql-connector-java-8.0.12.jar`，并右键libs，**`Add as Library...`添加成库**

  ```java
  Connection connection = null;
  Statement statement = null;
  ResultSet resultSet = null;
  try {
      //1.注册驱动。Driver类中有静态代码块，自动注册到DriverManager中
      //注：MySQL5后提供了配置文件，可以不用注册驱动而直接使用，Class.forName 这句话可以省略。 
      //Class.forName("com.mysql.cj.jdbc.Driver");//MySQL8中要添加cj，不添加也可以执行，但会警告
  
      //2.通过DriverManager获取数据库连接对象 Connection。不同数据库写法不同
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","w19");
      	//若出现乱码，可以指定参数：?characterEncoding=utf8
      	//在使用6以上版本时，会碰到时区异常以及SSl警告，需要在url添加：?serverTimezone=GMT%2B8&useSSL=false
      	//url编码后的GMT%2B8，或Asia/Shanghai等
      
  
      //3.获得语句执行者。
      Statement statement = connection.createStatement();
  
      //4.执行SQL语句。
      	//executeUpdate执行DDL，DML。返回影响的行数(int型),DDL返回0
      	//executeQuery执行DQL。返回ResultSet
      statement.executeUpdate("INSERT INTO stu VALUES ('0002','李四','女')");//增
      statement.executeUpdate("delete from stu");//删
      statement.executeUpdate("update stu set sname='王八蛋' where sid='0001'");//改
      ResultSet resultSet = statement.executeQuery("select * from stu");//查
  
      //5.处理结果集
      while(resultSet.next()){
          System.out.println("sid="+resultSet.getObject(1)+                   		         	                       ",sname="+resultSet.getString(2)+",gander="+resultSet.getString("gander"));
      }
  } catch (Exception e) {
      throw new RuntimeException(e);
  } finally {
      try {
          //6.释放资源。这里应该分开try..catch，否则第一个抛异常后，后两个也释放不掉资源
          if(resultSet!=null) resultSet.close();
          if(statement!=null) statement.close();
          if(connection!=null) connection.close();
  
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
  ```

## 7.2 各类详解

### 7.2.1 DriverManager

- **管理和注册数据库驱动**：告诉程序该使用哪一个数据库驱动jar
- **获取数据库连接**：`static Connection getConnection(String url, String user, String password)`

| JDBC连接数据库的参数 | 说明                                                         |
| -------------------- | ------------------------------------------------------------ |
| 用户名               | 登录的用户名                                                 |
| 密码                 | 登录的密码                                                   |
| 连接字符串 URL       | **协议名:子协议://服务器名或 IP 地址:端口号/数据库名?参数=参数值** <br />mysql为： jdbc:mysql://localhost:3306/数据库[?参数名=参数值]  <br />若果是本地服务器，端口号为3306可以简写：jdbc:mysql:///数据库 |
| 驱动类的字符串名     | com.mysql.cj.jdbc.Driver                                     |

### 7.2.2 Connection

> Connection接口，具体的实现类由数据库的厂商实现，代表一个连接对象。

- **获取执行sql 的对象**
  - `Statement createStatement()`
  - `PreparedStatement prepareStatement(String sql)`
- **管理事务**
  - 开启事务：`setAutoCommit(boolean autoCommit)` ：调用该方法设置参数为`false`，即开启事务
  - 提交事务：`commit() `
  - 回滚事务：`rollback() `

### 7.2.3 Statement

* `boolean execute(String sql)`：执行CRUD等语句，返回值为是否有结果集

- `int executeUpdate(String sql) `：执行DDL、DML语句。返回值为影响的行数，DDL返回0
- `ResultSet executeQuery(String sql)`：执行DQL语句



### 7.2.4 PreparedStatement

* **预编译**，是Statement接口的子接口。尽量使用这个而不是父类

* 好处：

  - **防SQL注入**。拼接sql时，有一些sql的特殊关键字参与字符串的拼接。会造成安全性问题
    1. 输入用户随便，输入密码：`a' or 'a' = 'a`
    2. sql：`select * from user where username = 'fhdsjkf' and password = 'a' or 'a' = 'a'`
  - **提高效率**（重复使用同一SQL模板）

* 用法：

  - 使用Connection的`prepareStatement(String sql)`：即创建它时就让它**与一条SQL模板绑定**

  - 调用PreparedStatement的`setXXX(int index, Xxx value)`系列方法为**?参数设置值**，?从1开始

  - 调用`executeUpdate()`或`executeQuery()`方法，但要注意，**调用没有参数的方法**

    ```java
    String sql = "select * from tab_student where s_number=?";
    PreparedStatement pstmt = con.prepareStatement(sql);
    pstmt.setString(1, "S_1001");
    ResultSet rs = pstmt.executeQuery();
    ```

### 7.2.5 ResultSet

* `boolean next()`: 游标向下移动一行，判断当前行是否是最后一行，如果是返回false，否则返回true
  * **ResultSet**就是一张二维的表格，它内部有一个“行光标”，光标默认的位置在“第一行上方”，我们可以调用对象的next()方法把“行光标”向下移动一行，当第一次调用next()方法时，“行光标”就到了第一行记录位置

* `getXxx(参数)`:获取数据。Xxx：代表数据类型   如： int getInt() ,	String getString()
   * 参数：
       1. ~~int：代表列的编号，从1开始   如： getString(1)~~
       2. **String**：代表列名称。如： getDouble("balance")
   * 不确定数据类型使用**`getString()`**或**`getObject()`**方法



## 7.3 JDBC事务管理

- 在JDBC中处理事务，都是通过**Connection**完成。同一事务中所有的操作，都在使用<span style="color:red;font-weight:bold">**同一个Connection对象**</span>

- **Connection**的三个方法与事务相关：

  - **setAutoCommit**(boolean)：设置是否为自动提交事务，如果true（默认值就是true）表示自动提交，也就是每条执行的SQL语句都是一个单独的事务，如果设置**false**，那么就相当于开启了事务了；
  - **commit**()：提交事务
  - **rollback**()：回滚事务

  ```java
  try {
    con.setAutoCommit(false);//执行SQL前开启事务…
    …
    …
    con.commit();//所有SQL都执行完毕，即try的最后提交事务
  } catch(Exception e) {
    con.rollback();//回滚事务
  }
  ```



## 7.4 数据库连接池(DataSource)

* DataSource其实就是一个存放数据库连接的**容器(集合)**。当系统初始化好后，容器被创建，容器中会申请一些连接对象，当用户来访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还给容器。

* 好处：
  * **节约资源**
  * 用户访问**高效**
* 实现：
  * 标准接口：`javax.sql.DataSource`包
     * **获取连接：`getConnection()`**
    * **归还连接：`Connection.close()`**。如果连接对象Connection是从连接池中获取的，那么调用该方法，则不会再关闭连接了，而是归还连接
  * 一般我们**不去实现它**，有数据库厂商来实现
    * **C3P0**：数据库连接池实现技术
    * **Druid**：数据库连接池实现技术，由阿里巴巴提供的

> DBCP是Apache提供的一款开源免费的数据库连接池。看下面代码（硬编码）了解即可，不再详细介绍
>
> ```java
> BasicDataSource ds = new BasicDataSource();
> //ds.setUsername("root");
> //ds.setPassword("123");
> //ds.setUrl("jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT%2B8&useSSL=false");
> //ds.setDriverClassName("com.mysql.jdbc.Driver");		
> Connection con = ds.getConnection();
> System.out.println(con.getClass().getName());
> con.close();
> ```

### 7.4.1 C3P0

* 导包：`c3p0-0.9.5.2.jar` 和`mchange-commons-java-0.2.12.jar`，别忘记数据库驱动的jar包

* 定义配置文件：

  * 名称：`c3p0.properties` 或者 `c3p0-config.xml`
     * 路径：直接将**文件放在类路径即**`src`目录下即可

  ```xml
  <c3p0-config>
      <!-- 使用默认的配置读取连接池对象 -->
      <default-config>
          <!--  连接参数 -->
          <property name="driverClass">com.mysql.jdbc.Driver</property>
          <!-- &useSSL=false添加时显示语法错误，Druid添加时正常 -->
          <property name="jdbcUrl">jdbc:mysql://localhost:3306/mydb?serverTimezone=GMT%2B8</property>
          <property name="user">root</property>
          <property name="password">root</property>
  
          <!-- 连接池参数 -->
          <property name="initialPoolSize">5</property>     <!--初始化申请的连接数量-->
          <property name="maxPoolSize">10</property>        <!--最大连接数量-->
          <property name="checkoutTimeout">3000</property>  <!--超时时间。等待多久没获取到就抛异常-->
      </default-config>
  
      <!-- new ComboPooledDataSource("otherc3p0")时，使用的就是这个配置 -->
      <named-config name="otherc3p0"> 
          <!--  连接参数 -->
          <property name="driverClass">com.mysql.jdbc.Driver</property>
          <property name="jdbcUrl">jdbc:mysql://localhost:3306/mydb</property>
          <property name="user">root</property>
          <property name="password">root</property>
  
          <!-- 连接池参数 -->
          <property name="initialPoolSize">5</property>
          <property name="maxPoolSize">8</property>
          <property name="checkoutTimeout">1000</property>
      </named-config>
  </c3p0-config>
  ```

   * 创建数据库连接池对象：`ComboPooledDataSource`

   * 获取连接：`getConnection`

     ```java
     //创建连接池对象，获取连接
     DataSource dataSource = new ComboPooledDataSource();//不传参使用默认配置，传参使用指定配置
     Connection connection = dataSource.getConnection();
     ```

### 7.4.2 Druid

* 导包：`druid-1.0.9.jar`，别忘记数据库驱动的jar包

* 定义配置文件：可以叫**任意名称**，可以放在**任意目录下**。是`properties`形式的

  ```properties
  driverClassName=com.mysql.jdbc.Driver
  url=jdbc:mysql://127.0.0.1:3306/db3?serverTimezone=GMT%2B8&useSSL=false
  username=root
  password=root
  initialSize=5 
  maxActive=10
  maxWait=3000
  ```

   * 加载配置文件：**Properties集合**的`load(InputStream is)`

   * 通过工厂来获取数据库连接池对象：**`DruidDataSourceFactory`**

   * 获取连接：`getConnection`

     ```java
     Properties prop = new Properties();
     InputStream is = DruidDemo.class.getClassLoader().getResourceAsStream("druid.properties");
     prop.load(is);
     
     DataSource dataSource = DruidDataSourceFactory.createDataSource(prop); //获取连接池对象
     Connection conn = dataSource.getConnection(); //获取连接
     ```



### 7.4.3 JDBCUtils工具类

1. 定义一个类 JDBCUtils

2. 提供静态代码块加载配置文件，初始化连接池对象

3. 提供方法

   1. 获取连接方法：通过数据库连接池获取连接
   2. 释放资源
   3. 获取连接池的方法

   ```java
   public class JDBCUtils {
       //加载Class文件时就通过配置文件创建数据库连接池对象
       private static DataSource dataSource;
       static {
           try {
               Properties prop = new Properties();
               prop.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
               dataSource = DruidDataSourceFactory.createDataSource(prop);
           } catch (IOException e) {
               e.printStackTrace();
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       /**
        * 获取数据源
        */
       public static DataSource getDataSource(){
           return dataSource;
       }
   
       /**
        * 获取连接
        */
       public static Connection getConnection() throws SQLException {
           return dataSource.getConnection();
       }
   
       /**
        * 释放资源
        */
       public static void close(Statement stmt,Connection con){
           close(null,stmt,con);
       }
   
       /**
        * 释放资源
        */
       public static void close(ResultSet rs, Statement stmt, Connection con){
           if (rs!=null) {
               try {
                   rs.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
           if (stmt!=null) {
               try {
                   stmt.close();
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
           if (con!=null) {
               try {
                   con.close(); //归还连接
               } catch (SQLException e) {
                   e.printStackTrace();
               }
           }
       }
   }
   ```


# 8 Spring JDBC

Spring框架对JDBC的简单封装，提供了一个**JdbcTemplate**对象简化JDBC的开发

* **导包**：5个jar包

* **创建JdbcTemplate对象**：依赖于数据源**`DataSource`**，`JdbcTemplate template = new JdbcTemplate(dateSource);`

* **调用JdbcTemplate的方法**来完成CRUD的操作（查找语句若未找到，直接抛异常，不是返回null，所以要**try...catch...**）

  * `update()`：执行**DML**语句。增、删、改语句
  * `query()`：查询并将结果封装为**JavaBean对象的List集合**。JavaBean属性使用**包装类型**最好，可以存储null。
    - query的参数：RowMapper函数式接口
      - 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
      - **`new BeanPropertyRowMapper<>(类型.class)`**（不用在尖括号中添加泛型）
  * `queryForObject()`：查询并将结果封装为**对象**。
    * 参数同`query()`的参数可封装为**JavaBean对象**；参数传**包装类型**，可用于聚合函数的查询。
  * `queryForMap()`：查询并将结果的列名作为key(String)、值作为value(Object)封装为一个**Map**集合
    * 注意：这个方法查询的结果集长度只能是1
  * `queryForList()`：查询并将结果集封装为**List**集合
    * 注意：将每一条记录封装为一个Map集合，再将Map集合装载到List集合中

  ```java
  public class JDBCTemplateDemo {
      private JdbcTemplate jdbcTemplate;
  
      @Before
      public void init() {
          jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
      }
  	//增
      @Test
      public void testInsert() {
          String insert = "insert into emp (id,ename) values (?,?)";
          jdbcTemplate.update(insert, 6, "六六"); //返回影响的行数
      }
  	//改
      @Test
      public void testUpdate() {
          String update = "update emp set id=?,salary=?,ename=? where id=?";
          jdbcTemplate.update(update, 66, 66666, "女流六六", 6); //返回影响的行数
      }
  	//删
      @Test
      public void testDelete() {
          String delete = "delete from emp where id=?";
          jdbcTemplate.update(delete, 66); //返回影响的行数
      }
  	//查询结果封装到JavaBean对象的List集合中
      @Test
      public void testQuery() {
          String sql = "select * from emp";
          List<Emp> emps = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Emp.class));
          emps.forEach(System.out::println);//方法引用改写lambda
      }
  	//查询结果封装为Map对象，列为key，值为value
      @Test
      public void testQueryForMap() {
          String sql = "select * from emp where id = ?";
          Map<String, Object> map = jdbcTemplate.queryForMap(sql, 1001);
          System.out.println(map);
      }
  	//查询结构封装为装有Map的List对象
      @Test
      public void testQueryForList() {
          String sql = "select * from emp where id = ? or id = ?";
          List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, 1001, 1002);
          list.forEach(System.out::println);//方法引用改写lambda
      }
  	//查询结果封装为对象，一般用于聚合函数，单个JavaBean查询（其实用Query即可）
      @Test
      public void testQueryForObject() {
          String sql = "select count(id) from emp";
          int i = jdbcTemplate.queryForObject(sql, Integer.class);
          System.out.println(i);
      }
  }
  ```





# MySQL 高级

## SELECT 的执行顺序

FROM > WHERE > GROUP BY > HAVING > SELECT 的字段 > DISTINCT > ORDER BY > LIMIT（MySQL和Oracle基本相同），如：

```sql
SELECT DISTINCT player_id, player_name, count(*) as num -- 顺序 5
FROM player JOIN team ON player.team_id = team.team_id -- 顺序 1
WHERE height > 1.80 -- 顺序 2
GROUP BY player.team_id -- 顺序 3
HAVING num > 2 -- 顺序 4
ORDER BY num DESC -- 顺序 6
LIMIT 2 -- 顺序 7
```

在 SELECT 语句执行这些步骤的时候，每个步骤都会产生一个**虚拟表**，然后将这个虚拟表传入下一个步骤中作为输入。

首先SELECT 是先执行 FROM 这一步的。在这个阶段，如果是多张表联查，还会经历下面的几个步骤：

1. 首先先通过 CROSS JOIN 求笛卡尔积，相当于得到虚拟表 vt（virtual table）1-1；
2. 通过 ON 进行筛选，在虚拟表 vt1-1 的基础上进行筛选，得到虚拟表 vt1-2；
3. 添加外部行。如果我们使用的是左连接、右链接或者全连接，就会涉及到外部行，也就是在虚拟表 vt1-2 的基础上增加外部行，得到虚拟表 vt1-3。

如果我们操作的是两张以上的表，还会重复上面的步骤，直到所有表都被处理完为止。

当我们拿到了查询数据表的原始数据，也就是最终的虚拟表 vt1，就可以在此基础上再进行 WHERE 阶段。在这个阶段中，会根据 vt1 表的结果进行筛选过滤，得到虚拟表 vt2。

然后进入第三步和第四步，也就是 GROUP 和 HAVING 阶段。在这个阶段中，实际上是在虚拟表 vt2 的基础上进行分组和分组过滤，得到中间的虚拟表 vt3 和 vt4。

首先在 SELECT 阶段会提取想要的字段，然后在 DISTINCT 阶段过滤掉重复的行，分别得到中间的虚拟表 vt5-1 和 vt5-2。

当我们提取了想要的字段数据之后，就可以按照指定的字段进行排序，也就是 ORDER BY 阶段，得到虚拟表 vt6。

最后在 vt6 的基础上，取出指定行的记录，也就是 LIMIT 阶段，得到最终的结果，对应的是虚拟表 vt7。



## 什么情况下用 SELECT*，如何提升 SELECT 查询效率？

使用SELECT *的查询效率和把所有列名都写出来再进行查询的效率相差并不大，但是在生产环境下，不推荐直接使用，应是查询需要的列。



## COUNT(*)和COUNT(1)

在MySQL InnoDB存储引擎中，COUNT(*)和COUNT(1)都是对的所有结果进行的COUNT。如果有WHERE子句，则是对所有符合筛选条件的数据行进行统计。如果没有WHERE子句，则是对数据表的数据行数进行统计。因此COUNT(\*)和COUNT(1)本质上没有区别，执行的复杂度都是O(N)，也就是采用全表扫描，进行循环+计数的方式进行统计。

如果是MySQL MyISAM存储引擎，统计数据表的行数只需要O(1)复杂度，这是因为每张MyISAM的数据表都有一个meta信息有存储了row_count值。而一致性由表级锁来保证。而InnoDB支持事务，采用行级锁和MVCC机制，所以无法像MyISAM一样，只维护一个row_count变量。因此就需要采用扫描全表，进行循环+计数的方式来完成统计。

另外在InnoDB引擎中，如果是采用COUNT(*)和COUNT(1)来统计数据行数，要尽量采用二级索引。因为主键采用的索引是聚簇索引，聚簇索引包含的信息多，明显会大于二级索引（非聚簇索引）。对于查找具体的行来说，采用主键索引效率更高。而对于COUNT(\*)和COUNT(1)这种，不需要查找具体的行，只是统计行数来说，系统会自动采用占用空间更小的二级索引来进行统计。如果有多个二级索引的时候，会使用key_len小的二级索引进行扫描。当没有二级索引的时候，才会采用主键索引来进行统计。

优化总结：
1、一般情况下：COUNT(\*) = COUNT(1) > COUNT(字段)
所以尽量使用COUNT(*)，当然如果你要统计的是就是某个字段的非空数据行数，那另当别论。毕竟执行效率比较的前提是要结果一样才行。
2、如果要统计COUNT(\*)，尽量在数据表上建立二级索引，系统会自动采用key_len小的二级索引进行扫描，这样当我们使用SELECT COUNT(\*)的时候效率就会提升，有时候提升几倍甚至更高都是有可能的。

## show profiling

```mysql
mysql> select @@profiling; -- 查看 profiling 是否开启。0为关闭，1为开启；
mysql> set profiling=1; -- 开启profileing
mysql> show profiles; -- 查看当前会话产生的所有 profiles
mysql> show profile; -- 获取上一次查询的执行时间
mysql> show profile for query 2; -- 指定id的执行时间
```



## 索引

要避免全表扫描，所以我们会考虑**在 WHERE 及 ORDER BY 涉及到的列上增加索引**。目的是在WHERE子句中避免全表扫描，ORDER BY子句避免使用FileSort排序。如果WHERE和ORDER BY相同列就使用单索引列；如果不同使用联合索引。

在MySQL中，支持两种排序方式：FileSort和Index排序。Index排序的效率更高，尽量使用Index索引：

* Index排序：索引可以**保证数据的有序性**，因此不需要再进行排序。
* FileSort排序：一般在内存中进行排序，占用CPU较多。如果待排结果较大，会产生临时文件I/O到磁盘进行排序，效率较低。





# 第二部分 Oracle



# 1 Oracle 安装及简介

Oracle 安装：

1. 使用的是 Oracle 快捷版，OracleXE112。安装时需要指定口令（用于SYS和SYSTEM账户），此处设置为`admin`；
2. Oracle 数据库监听程序端口默认为1521；Oracle Service for Microsoft Transaction Server 端口默认为2030；Oracle HTTP 监听程序端口默认为8080；
3. 打开"运行SQL命令行（即 sq"，键入`conn system/admin;`测试连接是否成功；
4. 创建账号等，需要以SYSDBA身份连接，键入`conn system/admin as sysdba;`
5. 创建账号，键入`create user htdms identified by 111111;`
6. 对该账号授权，键入`grant connect,resource,dba to htdms;`连接测试；
7. 使用其他工具连接时注意，OracleXE112只能有一个实例，所以SID需填写`XE`；

Oracle数据库系统是美国Oracle公司（甲骨文）提供的以分布式数据库为核心的一组软件产品，是目前最流行的C/S或B/S体系结构的数据库之一。比如SilverStream就是基于数据库的一种中间件。安装请参考文档。

![](images\Oracle体系结构.PNG)



* **DataBase**

  Oracle 数据库是数据的**物理存储**。这就包括（数据文件 ORA 或者 DBF、控制文件、联机日志、参数文件等存储在计算机**硬盘上的文件**）。和其它数据库不一样，这里的数据库是**一个操作系统只有一个库**。可以看作Oracle 就只有一个大数据库。 Oracle 数据库必须**与内存里实例合作**才能对外提供数据管理服务。

* **Instance**

  **位于物理内存里的数据结构**，一个 Oracle 实例（Oracle Instance）有一系列的后台进程（Backguound Processes）和内存结构（Memory Structures)组成。Oracle 用他们来管理数据库访问，实际上，Oracle 实例就是平常所说的数据库服务（service）。

  > <span style="color:red;font-weight:bold">Instance 可以操作数据库；一个 Instance 只能与一个 DataBase 关联，访问一个 DataBase；而同一个Database 可以由多个 Instance 访问（RAC）。一般一个 DataBase 只创建一个Instance。</span>

* **User（用户）**

  用户是在实例下建立的，不同实例可以建相同名字的用户。**管理表的基本单位**，和MySQL中数据库是管理表的基本单位不同

* **表空间** 

  表空间是 Oracle 对物理数据库上相关数据文件（ORA或者 DBF 文件）的逻辑映射。一个数据库在逻辑上被划分成一到若干个表空间，每个表空间包含了在逻辑上相关联的一组结构。每个数据库至少有一个表空间(称之为system表空间)。 

  每个表空间由同一磁盘上的一个或多个文件组成，这些文件叫数据文件(datafile)。一个数据文件只能属于一个表空间。 

* **数据文件**（DBF、ORA） 

  数据文件是数据库的物理存储单位。数据库的数据是存储在表空间中的，真正是在某一个或者多个数据文件中。而一个表空间可以由一个或多个数据文件组成，一个数据文件只能属于一个表空间。一旦数据文件被加入到某个表空间后，就不能删除这个文件，如果要删除某个数据文件，只能删除其所属于的表空间才行。 

![](images\Oracle体系结构简示.PNG)



# 2 Oracle的基本操作

## 2.1 表空间（理解）

Oracle数据库的逻辑单元。一个数据库下可以建立多个表空间，一个表空间可以建立多个用户、一个用户下可以建立多个表

```sql
-- 创建表空间（与MySQL注释不同，可以不用带空格）
CREATE tablespace itheima -- 表空间名称
datafile 'c:\itheima.dbf' -- 指定表空间对应的数据文件在Oracle服务器下路径
size 100m -- 后定义的是表空间的初始大小 
autoextend on --当表空间存储都占满时，自动增长 
next 10m; -- 后指定的是一次自动增长的大小 

-- 删除表空间
DROP tablespace itheima;
```



## 2.2 用户（理解）

Oracle数据库与其它数据库产品的区别在于，表和其它的数据库对象都是存储在用户下的

```sql
-- 创建用户
CREATE user itheima -- 用户名
IDENTIFIED by itheima -- 密码
DEFAULT tablespace itheima; -- 指定用户出生的表空间
```

新创建的用户没有任何权限，Oracle 中已存在三个重要的角色：

- CONNECT角色：是授予最终用户的典型权利，最基本的 
- RESOURCE角色：是授予开发人员的 
- DBA角色：拥有全部特权，是系统最高权限，只有 DBA才可以创建数据库结构，并且系统权限也需要DBA授出，且 DBA用户可以操作全体用户的任意基表，包括删除 

进入 system 用户下给用户赋予 DBA 权限，否则无法正常登陆

```sql
-- 给用户授权
GRANT DBA to itheima;
```



## 2.3 常用数据类型（应用）

| 数据类型                    | 描述                                                       |
| --------------------------- | ---------------------------------------------------------- |
| Varchar(n)，**Varchar2(n)** | 表示一个字符串                                             |
| **NUMBER**                  | NUMBER(n)表示一个整数，长度是n                             |
|                             | NUMBER(m,n)表示一个小数，总长度是 m，小 数是 n，整数是 m-n |
| **DATA、timestamp**         | 表示日期类型；更精确的日期类型（都包含到秒）               |
| CLOB                        | 大对象，表示大文本数据类型，可存 4G                        |
| BLOB                        | 大对象，表示二进制数据，可存 4G                            |



## 2.4 表的创建修改（应用）

* **创建表**

  ```sql
  -- 创建表
  CREATE TABLE person(
  	pid NUMBER(20),
  	pname VARCHAR2(10)
  );
  ```

* **修改表**

  ```sql
  -- 添加一列
  ALTER TABLE person ADD (gender NUMBER(1)); -- 与MySQL一样
  -- 修改列类型
  ALTER TABLE person MODIFY (gender char(1)); -- 与MySQL一样
  -- 修改列名称
  ALTER TABLE person RENAME COLUMN gender to sex;
  -- 删除一列
  ALTER TABLE person DROP COLUMN sex;
  ```



## 2.5 数据的增删改

**注意：必须commit提交**

```sql
-- 添加记录
INSERT INTO person (pid,pname) VALUES(1,'张三');
commit;

-- 修改记录
UPDATE person SET pname = '李四' WHERE pid = 1;
commit;

-- 三个删除方法
-- 删除表中全部记录，若添加where则删除满足条件的记录
DELETE FROM person;
-- 先删除表，再次创建表。效果等同于删除表中全部记录
-- 但是在数据量大的情况下，尤其在表中带有索引（索引可以提高查询效率，影响增删改效率）的情况下，该操作效率高
truncate TABLE person;
-- 删除表结构
DROP TABLE person;
commit;
```



## 2.6 序列

在很多数据库中都存在一个**自动增长的列**，如果现在要想在 oracle 中完成自动增长的功能，则只能依靠序列完成，所有的自动增长操作，需要用户手工完成处理

```sql
-- 序列不真的属于任何一张表，但是可以逻辑和表做绑定
-- 序列：默认从1开始，依次递增（无论删除回滚），主要用来给主键赋值使用
-- dual：虚表，只是为了补全语法，没有任何意义
CREATE sequence s_person;
-- SELECT s_person.nextval FROM dual; --取得序列的下一个内容；currval为当前内容
-- 插入数据SQL应改为
INSERT INTO person (pid,pname) VALUES(s_person.nextval,'张三');
commit;
```



# 3 Oracle的查询

Scott用户（了解）：用于新手练习

```sql
----scott用户，密码tiger。
--使用DBA解锁scott用户
alter user scott account unlock;
--解锁scott用户的密码【此句也可以用来重置密码】
alter user scott identified by tiger;
--切换到scott用户下
```



## 3.1 单行函数

作用于一行，返回一个值

* **字符函数**：接收字符输入返回字符或者数值，dual是伪表 

  ```sql
  select upper('yes') from dual;--YES
  select lower('YES') from dual;--yes
  ```

* **数值函数**、

  ```sql
  select round(56.16, -2) from dual;---“100”，四舍五入，后面的参数表示保留的位数（可以不写）
  select trunc(56.16, -1) from dual;---“50”，直接截取，不在看后面位数的数字是否大于5（可以不写）
  select mod(10, 3) from dual;---“1”，求余数，一般Java代码解决
  ```

* **日期函数**

  ```sql
  ----查询出emp表中所有员工入职距离现在几天。
  select sysdate-e.hiredate from emp e;
  ----算出明天此刻时间
  select sysdate+1 from dual;
  ----查询出emp表中所有员工入职距离现在几月。
  select months_between(sysdate,e.hiredate) from emp e;
  ----查询出emp表中所有员工入职距离现在几年。
  select months_between(sysdate,e.hiredate)/12 from emp e;
  ----查询出emp表中所有员工入职距离现在几周。
  select round((sysdate-e.hiredate)/7) from emp e;
  ```

* **转换函数**

  ```sql
  ---日期转字符串，fm为去掉前缀0，hh24为24小时制，mi替代mm（因为格式不区分大小写）
  select to_char(sysdate, 'fm yyyy-mm-dd hh24:mi:ss') from dual; -- 
  ---字符串转日期
  select to_date('2018-6-7 16:39:50', 'fm yyyy-mm-dd hh24:mi:ss') from dual;
  ```

* **通用函数**

  * **nvl空值处理**

    ```sql
    ----算出emp表中所有员工的年薪。奖金里面有null值，如果null值和任意数字做算术运算，结果都是null。
    select e.sal*12 + nvl(e.comm, 0) from emp e;
    ```

  * **条件表达式**

    ```sql
    ---条件表达式的通用写法，mysql和oracle通用
    ---给emp表中员工起中文名
    select e.ename, 
           case e.ename
             when 'SMITH' then '曹贼'
               when 'ALLEN' then '大耳贼'
                 when 'WARD' then '诸葛小儿'
                   --else '无名'
                     end
    from emp e;
    
    ---判断emp表中员工工资，如果高于3000显示高收入，如果高于1500低于3000显示中等收入，
    -----其余显示低收入
    select e.sal, 
           case 
             when e.sal>3000 then '高收入'
               when e.sal>1500 then '中等收入'
                   else '低收入'
                     end
    from emp e;
    ```

    ```sql
    ----oracle中除了起别名（可以双引号，或不用任何引号），都用单引号。
    ----oracle专用条件表达式，但是尽量选择通用写法
    select e.ename, 
            decode(e.ename,
              'SMITH',  '曹贼',
                'ALLEN',  '大耳贼',
                  'WARD',  '诸葛小儿',
                    '无名') "中文名"             
    from emp e;
    ```




## 3.2 多行函数（聚合函数）

作用于多行，返回一个值

```sql
select count(1) from emp;---查询总数量，写*底层也是走1，调用的是主键
select sum(sal) from emp;---工资总和
select max(sal) from emp;---最大工资
select min(sal) from emp;---最低工资
select avg(sal) from emp;---平均工资
```



## 3.3 分组查询

```sql
---分组查询
---查询出每个部门的平均工资
---分组查询中，出现在group by后面的原始列，才能出现在select后面
---没有出现在group by后面的列，想在select后面，必须加上聚合函数。聚合函数有一个特性，可以把多行记录变成一个值。
select e.deptno, avg(e.sal)--, e.ename
from emp e
group by e.deptno;



---查询出每个部门工资高于800的员工的平均工资，然后再查询出平均工资高于2000的部门
select e.deptno, avg(e.sal) asal
from emp e
where e.sal>800
group by e.deptno
having avg(e.sal)>2000;
---所有条件都不能使用select中的别名来判断。
---where是过滤分组前的数据，having是过滤分组后的数据。表现形式：where必须在group by之前，having是在group by之后。
```



## 3.4 多表查询

与MySQL一样

```sql
---等值连接，推荐
select *
from emp e, dept d
where e.deptno=d.deptno;
---内连接
select *
from emp e inner join dept d
on e.deptno = d.deptno;

---查询出所有部门，以及部门下的员工信息。【外连接】
select *
from emp e right join dept d
on e.deptno=d.deptno;
---查询所有员工信息，以及员工所属部门
select *
from emp e left join dept d
on e.deptno=d.deptno;

---oracle中专用外连接
select *
from emp e, dept d
where e.deptno(+) = d.deptno; -- (+)在左边为左外，否则为右外，不推荐
```

* 自连接：其实就是站在不同的角度把一张表看成多张表

  ```sql
  ---查询出员工姓名，员工领导姓名
  select e1.ename, e2.ename
  from emp e1, emp e2
  where e1.mgr = e2.empno;
  
  ------查询出员工姓名，员工部门名称，员工领导姓名，员工领导部门名称
  select e1.ename, d1.dname, e2.ename, d2.dname
  from emp e1, emp e2, dept d1, dept d2
  where e1.mgr = e2.empno
  and e1.deptno=d1.deptno
  and e2.deptno=d2.deptno;
  ```


## 3.5 子查询

```sql
---子查询返回一个值
---查询出工资和SCOTT一样的员工信息
select * from emp where sal in -- 由于不确定下面的子查询是否是一个值（ename不是主键）
(select sal from emp where ename = 'SCOTT');

---子查询返回一个集合
---查询出工资和10号部门任意员工一样的员工信息
select * from emp where sal in
(select sal from emp where deptno = 10);


---子查询返回一张表
---查询出每个部门最低工资，和最低工资员工姓名，和该员工所在部门名称
---1，先查询出每个部门最低工资
select deptno, min(sal) msal
from emp 
group by deptno;
---2，三表联查，得到最终结果。
select t.deptno, t.msal, e.ename, d.dname
from (select deptno, min(sal) msal
      from emp 
      group by deptno) t, emp e, dept d
where t.deptno = e.deptno
and t.msal = e.sal
and e.deptno = d.deptno;
```



## 3.6 分页查询

```sql
---rownum行号：当我们做select操作的时候，每查询出一行记录，就会在该行上加上一个行号，行号从1开始，依次递增，不能跳着走。

----排序操作会影响rownum的顺序（由于先执行select，后排序）
select rownum, e.* from emp e order by e.sal desc

----如果涉及到排序，但是还要使用rownum的话，我们可以再次嵌套查询。
select rownum, t.* from(
select rownum, e.* from emp e order by e.sal desc) t;


----emp表工资倒叙排列后，每页五条记录，查询第二页。
----rownum行号不支持大于号。
select * from(
    select rownum rn, tt.* from(
          select * from emp order by sal desc
    ) tt where rownum<11
) where rn>5
```





# 4 Oracle对象

## 4.1 视图

视图就是**封装**了一条复杂**查询**的语句。 就是提供一个查询的窗口，**所有数据来自于原表**。

视图的作用？

* 视图可以**屏蔽掉一些敏感字段**。比如工资
* **保证总部和分部数据及时统一**。（总部查表，分布查视图）

```sql
---查询语句创建表
create table emp as select * from scott.emp;

---创建视图【必须有dba权限】
create view v_emp as select ename, job from emp;

---查询视图
select * from v_emp;

---修改视图[不推荐]
update v_emp set job='CLERK' where ename='ALLEN';
commit;
---创建只读视图
create view v_emp1 as select ename, job from emp with read only;
```



## 4.2 索引

索引就是在表的**列上构建一个二叉树**，达到大幅度**提高查询效率**的目的，但是索引会影响增删改的效率。

* 单列索引

  **单列索引触发规则**，条件必须是索引列中的**原始值**。单行函数，模糊查询，都会影响索引的触发。

  ```sql
  ---创建单列索引
  create index idx_ename on emp(ename);
  ---单列索引触发规则，条件必须是索引列中的原始值。单行函数，模糊查询，都会影响索引的触发。
  select * from emp where ename='SCOTT'
  ```

* 复合索引

  复合索引中**第一列为优先检索列**。如果要触发复合索引，必须**包含有优先检索列**中的**原始值**。

  ```sql
  ---创建复合索引
  create index idx_enamejob on emp(ename, job);
  ---复合索引中第一列为优先检索列。如果要触发复合索引，必须包含有优先检索列中的原始值。
  select * from emp where ename='SCOTT' and job='xx';---触发复合索引
  select * from emp where ename='SCOTT' or job='xx';---不触发索引，因为这个看做两条查询语句，一条触发一条不触发，so...
  select * from emp where ename='SCOTT';---触发单列索引。
  ```



# 5 PL/SQL[了解]

PL/SQL（Procedure Language/SQL）：是 Oracle 对SQL语言的过程化扩展，使得SQL语言具有过程化编程的特性。PL/SQL编程语言比一般的过程化编程语言，更加灵活高效，主要用来编写存储过程和存储函数等

> 示例：按职工的职称长工资,总裁涨 1000元,经理涨800 元，其他人员涨 400 元。 
>
> 这样的需求我们就无法使用一条 SQL来实现，需要借助其他程序来帮助完成，也可以使用 PL/SQL。 

## 5.1 语法

```sql
declare       
	说明部分（变量说明，游标申明，例外说明 〕  
begin       
	语句序列（DML 语句〕…  
exception     -- 可以没有  
	例外处理语句    
End; 
```

## 5.2 变量

```sql
---声明方法
---赋值操作可以使用:=也可以使用into查询语句赋值
declare
    i number(2) := 10;
    s varchar2(10) := '小明';
    ena emp.ename%type;---引用型变量
    emprow emp%rowtype;---记录型变量
begin
    dbms_output.put_line(i);
    dbms_output.put_line(s);
    select ename into ena from emp where empno = 7788;
    dbms_output.put_line(ena);
    select * into emprow from emp where empno = 7788;
    dbms_output.put_line(emprow.ename || '的工作为：' || emprow.job);
exception --可以没有
	例外处理语句
end;
```

## 5.3 if判断

```sql
---输入小于18的数字，输出未成年
---输入大于18小于40的数字，输出中年人
---输入大于40的数字，输出老年人
declare
  i number(3) := &ii;
begin
  if i<18 then
    dbms_output.put_line('未成年');
  elsif i<40 then
    dbms_output.put_line('中年人');
  else
    dbms_output.put_line('老年人');
  end if;
end;
```

## 5.4 loop循环

```sql
---用三种方式输出1到10是个数字
---while循环
declare
  i number(2) := 1;
begin
  while i<11 loop
     dbms_output.put_line(i);
     i := i+1;
  end loop;  
end;

---exit循环
declare
  i number(2) := 1;
begin
  loop
    exit when i>10;
    dbms_output.put_line(i);
    i := i+1;
  end loop;
end;

---for循环
declare

begin
  for i in 1..10 loop
     dbms_output.put_line(i);  
  end loop;
end;
```



## 5.5 游标 Cursor

在写java程序中有集合的概念，在 pl/sql 中也会用到多条记录，这时候我们就要用到游标， 游标可以存储查询返回的多条数据。 

```sql
---输出emp表中所有员工的姓名
declare
  cursor c1 is select * from emp;
  emprow emp%rowtype;
begin
  open c1; -- 打开游标执行查询
     loop
         fetch c1 into emprow; -- 取一行到变量中
         exit when c1%notfound; -- 游标的结束方式 
         dbms_output.put_line(emprow.ename);
     end loop;
  close c1; -- 关闭游标释放资源
end;

-----给指定部门员工涨工资
declare
  cursor c2(eno emp.deptno%type) 
  is select empno from emp where deptno = eno;
  en emp.empno%type;
begin
  open c2(10);
     loop
        fetch c2 into en;
        exit when c2%notfound;
        update emp set sal=sal+100 where empno=en;
        commit;
     end loop;  
  close c2;
end;
----查询10号部门员工信息
select * from emp where deptno = 10;
```





## 5.6 存储过程[理解]

存储过程就是**提前已经编译好的一段pl/sql语言**，**放置在数据库端**，可以直接被调用。这一段pl/sql一般都是**固定步骤的业务**。 

创建存储过程语法： 

```sql
create [or replace] PROCEDURE 过程名[(参数名 in/out 数据类型)]   --不写默认为in
AS  
begin         
	PLSQL子程序体； 
End; 

--或者
create [or replace] PROCEDURE 过程名[(参数名 in/out 数据类型)]   
is 
begin         
	PLSQL子程序体； 
End  过程名; 
```

```sql
----给指定员工涨100块钱
create or replace procedure p1(eno emp.empno%type)
is

begin
   update emp set sal=sal+100 where empno = eno;
   commit;
end;

select * from emp where empno = 7788;

----测试p1
declare

begin
  p1(7788);
end;
```





## 5.7 存储函数[理解]

```sql
create or replace function 函数名(Name in type, Name in type, ...) return 数据类型 is   
	结果变量 数据类型; 
begin      
	
	return(结果变量); 
end 函数名; 
```

```sql
----通过存储函数实现计算指定员工的年薪
----存储过程和存储函数的参数都不能带长度
----存储函数的返回值类型不能带长度
create or replace function f_yearsal(eno emp.empno%type) return number
is
  s number(10);     
begin
  select sal*12+nvl(comm, 0) into s from emp where empno = eno;
  return s;
end;

----测试f_yearsal
----存储函数在调用的时候，返回值需要接收。
declare
  s number(10); 
begin
  s := f_yearsal(7788);
  dbms_output.put_line(s);
end;
```

**存储过程和存储函数的区别：**

- 语法区别：关键字不一样；存储函数比存储过程多了两个return。

- 本质区别：存储函数有返回值，而存储过程没有返回值。

  如果存储过程想实现有返回值的业务，我们就必须使用out类型的参数。
  即便是存储过程使用了out类型的参数，起本质也不是真的有了返回值，而是在存储过程内部给out类型参数赋值，在执行完毕后，我们直接拿到输出类型参数的值。

```sql
----我们可以使用存储函数有返回值的特性，来自定义函数。而存储过程不能用来自定义函数。
----案例需求：查询出员工姓名，员工所在部门名称。
----案例准备工作：把scott用户下的dept表复制到当前用户下。
create table dept as select * from scott.dept;

----使用传统方式来实现案例需求
select e.ename, d.dname
from emp e, dept d
where e.deptno=d.deptno;

----使用存储函数来实现提供一个部门编号，输出一个部门名称。
create or replace function fdna(dno dept.deptno%type) return dept.dname%type
is
  dna dept.dname%type;
begin
  select dname into dna from dept where deptno = dno;
  return dna;
end;

---使用fdna存储函数来实现案例需求：查询出员工姓名，员工所在部门名称。
select e.ename, fdna(e.deptno)
from emp e;
```





## 5.8 out类型输出参数

```sql
---使用存储过程来算年薪
create or replace procedure p_yearsal(eno emp.empno%type, yearsal out number)
is
   s number(10);
   c emp.comm%type;
begin
   select sal*12, nvl(comm, 0) into s, c from emp where empno = eno;
   yearsal := s+c;
end;

---测试p_yearsal
declare
  yearsal number(10);
begin
  p_yearsal(7788, yearsal);
  dbms_output.put_line(yearsal);
end;
```

in和out类型参数的区别是什么？

凡是涉及到into查询语句赋值或者:=赋值操作的参数，都必须使用out来修饰。



## 5.9 触发器[理解] 

触发器，就是制定一个规则，在我们做增删改操作的时候，只要满足该规则，自动触发，无需调用。

* 语句级触发器：不包含有for each row的触发器。
* 行级触发器：包含有for each row的就是行级触发器。加for each row是为了使用`:old`或者`:new`对象或者一行记录。

```sql
---语句级触发器
----插入一条记录，输出一个新员工入职
create or replace trigger t1
after
insert
on person
declare

begin
  dbms_output.put_line('一个新员工入职');
end;

---触发t1
insert into person values (1, '小红');
commit;
select * from person;
```

```sql
---行级别触发器
---不能给员工降薪
---raise_application_error(-20001~-20999之间, '错误提示信息');
create or replace trigger t2
before
update
on emp
for each row
declare

begin
  if :old.sal>:new.sal then
     raise_application_error(-20001, '不能给员工降薪');
  end if;
end;

----触发t2
select * from emp where empno = 7788;
update emp set sal=sal-1 where empno = 7788;
commit;
```

```sql
----触发器实现主键自增。【行级触发器】
---分析：在用户做插入操作的之前，拿到即将插入的数据；给该数据中的主键列赋值。
create or replace trigger auid
before
insert
on person
for each row
declare

begin
  select s_person.nextval into :new.pid from dual;
end;
--查询person表数据
select * from person;
---使用auid实现主键自增
insert into person (pname) values ('a');
commit;
```



## 5.10 Java调用存储过程

oracle10g对应jar包为：ojdbc14.jar
oracle11g对应jar包为：ojdbc6.jar

1. Maven工程中pom.xml添加坐标依赖（添加不上则需要利用Maven上传jar包到本地仓库）

   ```xml
   <dependency>
       <groupId>com.oracle</groupId>
       <artifactId>ojdbc14</artifactId>
       <version>10.2.0.1.0</version>
       <scope>runtime</scope>
   </dependency>
   <dependency>
       <groupId>junit</groupId>
       <artifactId>junit</artifactId>
       <version>4.12</version>
       <scope>test</scope>
   </dependency>
   ```

2. 测试连接等

   ```java
   public class OracleDemo {
   
       @Test
       public void javaCallOracle() throws Exception {
           //加载数据库驱动
           Class.forName("oracle.jdbc.driver.OracleDriver");
           //得到Connection连接
           Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@Conanan:1521:orcl", "itheima", "itheima");//Conanan写Oracle所在服务器IP
           //得到预编译的Statement对象
           PreparedStatement pstm = connection.prepareStatement("select * from emp where empno = ?");
           //给参数赋值
           pstm.setObject(1, 7788);
           //执行数据库查询操作
           ResultSet rs = pstm.executeQuery();
           //输出结果
           while(rs.next()){
               System.out.println(rs.getString("ename"));
           }
           //释放资源
           rs.close();
           pstm.close();
           connection.close();
       }
   
       /**
        * java调用存储过程
        * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储函数使用
        *  {call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储过程使用
        * @throws Exception
        */
       @Test
       public void javaCallProcedure() throws Exception {
           //加载数据库驱动
           Class.forName("oracle.jdbc.driver.OracleDriver");
           //得到Connection连接
           Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.88.6:1521:orcl", "itheima", "itheima");
           //得到预编译的Statement对象
           CallableStatement pstm = connection.prepareCall("{call p_yearsal(?, ?)}");
           //给参数赋值
           pstm.setObject(1, 7788);
           pstm.registerOutParameter(2, OracleTypes.NUMBER);
           //执行数据库查询操作
           pstm.execute();
           //输出结果[第二个参数]
           System.out.println(pstm.getObject(2));
           //释放资源
           pstm.close();
           connection.close();
       }
   
   
       /**
        * java调用存储函数
        * {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储函数使用
        *  {call <procedure-name>[(<arg1>,<arg2>, ...)]}   调用存储过程使用
        * @throws Exception
        */
       @Test
       public void javaCallFunction() throws Exception {
           //加载数据库驱动
           Class.forName("oracle.jdbc.driver.OracleDriver");
           //得到Connection连接
           Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.88.6:1521:orcl", "itheima", "itheima");
           //得到预编译的Statement对象
           CallableStatement pstm = connection.prepareCall("{?= call f_yearsal(?)}");
           //给参数赋值
           pstm.setObject(2, 7788);
           pstm.registerOutParameter(1, OracleTypes.NUMBER);
           //执行数据库查询操作
           pstm.execute();
           //输出结果[第一个参数]
           System.out.println(pstm.getObject(1));
           //释放资源
           pstm.close();
           connection.close();
       }
   }
   ```

3. s







# 













# 第三部分 NoSQL

**NoSQL**(Not Only SQL)，意即“不仅仅是SQL”，是一项全新的数据库理念，泛指**非关系型的数据库**。

> 随着互联网web2.0网站的兴起，传统的关系数据库在应付web2.0网站，特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。NoSQL数据库的产生就是为了解决**大规模数据集合多重数据种类**带来的挑战，尤其是大数据应用难题。
>
> NOSQL和SQL数据库比较
>
> - 优点：
>   1. 成本：nosql数据库简单易部署，基本都是开源软件，不需要像使用oracle那样花费大量成本购买使用
>   2. 查询速度：nosql数据库将数据存储于**缓存**之中，关系型数据库将数据存储在硬盘中
>   3. 存储数据的格式：nosql的存储格式是key,value形式、文档形式、图片形式等等，所以可以存储基础类型以及对象或者是集合等各种格式，而sql数据库则只支持基础类型。
>   4. 扩展性：关系型数据库有类似join这样的多表查询机制的限制导致扩展很艰难。
> - 缺点
>   1. 维护的工具和资料有限，因为nosql是属于新的技术，不能和关系型数据库10几年的技术同日而语。
>   2. 不提供对sql的支持，如果不支持sql这样的工业标准，将产生一定用户的学习和使用成本。
>   3. 不提供关系型数据库对事务的处理。

* 非关系型数据库的优势：
  * **性能高**：NOSQL是基于键值对，可以想象成表中的主键和值的对应关系，且不需要经过SQL层的解析，所以性能高。
  * **可扩展性**：同样也是因为基于键值对，数据之间没有耦合性，所以非常容易水平扩展。
* 关系型数据库的优势
  * **复杂查询**：可以用SQL语句方便的在一个表以及多个表之间做非常复杂的数据查询。
  * **事务**：事务使得对于安全性能很高的数据访问要求得以实现。
* 总结：SQL数据库与NoSQL数据库是互补的关系，一般会将数据**存储**在SQL数据库中，在NoSQL数据库中**备份存储**关系型数据库的数据
* 主流的NOSQL产品
  * **键值(Key-Value)存储数据库**
    相关产品： Tokyo Cabinet/Tyrant、**Redis**、Voldemort、Berkeley DB
    典型应用： **内容缓存**，主要用于处理**大量数据的高访问负载**。 
    数据模型： 一系列键值对
    **优势： 快速查询**
    劣势： 存储的数据**缺少结构化**
  * 列存储数据库
    相关产品：Cassandra, HBase, Riak
    典型应用：分布式的文件系统
    数据模型：以列簇式存储，将同一列数据存在一起
    优势：查找速度快，可扩展性强，更容易进行分布式扩展
    劣势：功能相对局限
  * 文档型数据库
    相关产品：CouchDB、MongoDB
    典型应用：Web应用（与Key-Value类似，Value是结构化的）
    数据模型： 一系列键值对
    优势：数据结构要求不严格
    劣势： 查询性能不高，而且缺乏统一的查询语法
  * 图形(Graph)数据库
    相关数据库：Neo4J、InfoGrid、Infinite Graph
    典型应用：社交网络
    数据模型：图结构
    优势：利用图结构相关算法。
    劣势：需要对整个图做计算才能得出结果，不容易做分布式的集群方案。

# 1 Redis

## 1.1 Redis简介

Redis是用C语言开发的一个开源的高性能键值对（key-value）数据库，官方提供测试数据，50个并发执行100000个请求,读的速度是110000次/s,写的速度是81000次/s ，且Redis通过提供多种键值数据类型来适应不同场景下的存储需求，目前为止Redis支持的键值数据类型：字符串类型 string、哈希类型 hash、列表类型 list、集合类型 set、有序集合类型 sortedset

redis的应用场景

* 缓存（数据查询、短连接、新闻内容、商品内容等等）
* 聊天室的在线好友列表
* 任务队列。（秒杀、抢购、12306等等）
* 应用排行榜
* 网站访问统计
* 数据过期处理（可以精确到毫秒）
* 分布式集群架构中的session分离

## 1.2 下载安装

1. [官网](https://redis.io)
2. [中文网](http://www.redis.net.cn/)
3. 解压直接可以使用：
  * redis.windows.conf：配置文件
  * redis-cli.exe：redis的客户端
  * redis-server.exe：redis服务器端

## 1.3 命令操作

### 1.3.1 redis的数据结构

* redis存储的是：**key,value格式**的数据，其中**key都是字符串**，**value有5种不同的数据结构**
   * **字符串**类型 string
   * **哈希**类型 hash ： map格式
   * **列表**类型 list ： linkedlist格式。支持重复元素
   * **集合**类型 set  ： 不允许重复元素
   * **有序集合**类型 sortedset：不允许重复元素，且元素有顺序

### 1.3.2 字符串 Strings

* 存储： **set** key value

   ```
   127.0.0.1:6379> set username zhangsan
   OK
   ```

* 获取： **get** key

  ```
  127.0.0.1:6379> get username
  "zhangsan"
  ```

* 删除： **del** key

  ```
  127.0.0.1:6379> del age
  (integer) 1
  ```

### 1.3.3 哈希 Hashes

* 存储： **hset** key field value

  ```
  127.0.0.1:6379> hset myhash username lisi
  (integer) 1
  127.0.0.1:6379> hset myhash password 123
  (integer) 1
  ```

* 获取

  * **hget** key field: 获取指定的field对应的值

    ```
    127.0.0.1:6379> hget myhash username
    "lisi"
    ```

  * **hgetall** key：获取所有的field和value

    ```
    127.0.0.1:6379> hgetall myhash
    1) "username"
    2) "lisi"
    3) "password"
    4) "123"
    ```

* 删除： **hdel** key field

  ```
  127.0.0.1:6379> hdel myhash username
  (integer) 1
  ```

### 1.3.4 列表 Lists

可以添加一个元素到列表的头部（左边）或者尾部（右边）

* 添加

  * **lpush** key value: 将元素加入列表左表

  * **rpush** key value：将元素加入列表右边

    ```
    127.0.0.1:6379> lpush myList a
    (integer) 1
    127.0.0.1:6379> lpush myList b
    (integer) 2
    127.0.0.1:6379> rpush myList c
    (integer) 3
    ```

* 获取

  * **lrange** key start end ：范围获取

    ```
    127.0.0.1:6379> lrange myList 0 -1
    1) "b"
    2) "a"
    3) "c"
    ```

* 删除

  * **lpop** key： 删除列表最左边的元素，并将元素返回
  * **rpop** key： 删除列表最右边的元素，并将元素返回

### 1.3.5 集合 Sets

不允许重复元素

* 存储：**sadd** key value

  ```
  127.0.0.1:6379> sadd myset a
  (integer) 1
  127.0.0.1:6379> sadd myset a
  (integer) 0
  ```

* 获取：**smembers** key:获取set集合中所有元素

  ```
  127.0.0.1:6379> smembers myset
  1) "a"
  ```

* 删除：**srem** key value:删除set集合中的某个元素	

  ```
  127.0.0.1:6379> srem myset a
  (integer) 1
  ```

### 1.3.6 有序集合 Sorted sets

不允许重复元素，且元素有顺序。每个元素都会关联一个double类型的分数，redis正是通过分数来为集合中的成员进行从小到大的排序。

* 存储：**zadd** key score value

  ```
  127.0.0.1:6379> zadd mysort 60 zhangsan
  (integer) 1
  127.0.0.1:6379> zadd mysort 50 lisi
  (integer) 1
  127.0.0.1:6379> zadd mysort 80 wangwu
  (integer) 1
  ```

* 获取：**zrange** key start end [withscores]

  ```
  127.0.0.1:6379> zrange mysort 0 -1
  1) "lisi"
  2) "zhangsan"
  3) "wangwu"
  
  127.0.0.1:6379> zrange mysort 0 -1 withscores
  1) "zhangsan"
  2) "60"
  3) "wangwu"
  4) "80"
  5) "lisi"
  6) "500"
  ```

* 删除：**zrem** key value

  ```
  127.0.0.1:6379> zrem mysort lisi
  (integer) 1
  ```

### 1.3.7 通用命令

* `keys *` : 查询所有的键
* `type key` ： 获取键对应的value的类型
* `del key`：删除指定的key value

## 1.4 Redis持久化

redis中数据存储在内存，当redis服务器重启或电脑重启，数据会丢失，可以将redis内存中的数据持久化保存到硬盘的文件中。

* redis持久化机制

  * **RDB**：默认方式，不需要进行配置。在一定的间隔时间中，检测key的变化情况，然后持久化数据

    1. 编辑redis.windwos.conf文件

       ```nginx
       #   after 900 sec (15 min) if at least 1 key changed
       save 900 1
       #   after 300 sec (5 min) if at least 10 keys changed
       save 300 10
       #   after 60 sec if at least 10000 keys changed
       save 60 10000
       ```

    2. 命令行中重新启动redis服务器，并指定配置文件名称`redis-server.exe redis.windows.conf`

  * AOF：日志记录的方式，可以记录每一条命令的操作。可以每一次命令操作后，持久化数据。不推荐

    1. 编辑redis.windwos.conf文件

       ```nginx
       appendonly no（关闭aof） # --> appendonly yes （开启aof）
       
       # appendfsync always ： 每一次操作都进行持久化
       appendfsync everysec ： 每隔一秒进行一次持久化
       # appendfsync no	 ： 不进行持久化
       ```

    2. 命令行中重新启动redis服务器，并指定配置文件名称。同上



## 1.5 Jedis

### 1.5.1 Jedis简介

Jedis: Redis官方推出的一款java操作redis数据库的工具（后续会使用Spring Data Redis）

* 使用步骤

  1. 导入jedis的jar包

  2. 使用

     ```java
     //1. 获取连接
     Jedis jedis = new Jedis("localhost",6379);
     //2. 操作
     jedis.set("username","zhangsan");
     //3. 关闭连接
     jedis.close();
     ```

### 1.5.2 Jedis操作Redis中的数据结构

```java
//获取连接
@Before
public void init(){
    Jedis jedis = new Jedis();//如果使用空参构造，默认值 "localhost",6379端口
}
//关闭连接
@After
public void close(){
    jedis.close();
}
```

* 字符串 Strings：`set`、`setex`、`del`、`get`

  ```java
  //存储
  jedis.set("username","zhangsan");
  //获取
  String username = jedis.get("username"); //zhangsan
  
  //可以使用setex()方法存储可以指定过期时间的 key value
  jedis.setex("activecode",20,"hehe");//将activecode：hehe键值对存入redis，并且20秒后自动删除该键值对
  ```

* 哈希 Hashes（map格式）：`hset`、`hdel`、`hget`、`hgetAll`

  ```java
  // 存储hash
  jedis.hset("user","name","lisi");
  jedis.hset("user","age","23");
  jedis.hset("user","gender","female");
  
  // 获取hash
  String name = jedis.hget("user", "name"); //lisi
  // 获取hash的所有map中的数据
  Map<String, String> user = jedis.hgetAll("user");
  ```

* 列表 Lists：`lpush/rpush`、`lpop/rpop`、`lrange`

  ```java
  jedis.lpush("mylist","a","b","c");//从左边存
  jedis.rpush("mylist","a","b","c");//从右边存
  
  // list 范围获取
  List<String> mylist = jedis.lrange("mylist", 0, -1); // [c,b,a,a,b,c]
  
  // list 弹出
  String element1 = jedis.lpop("mylist");//c
  String element2 = jedis.rpop("mylist");//c
  List<String> mylist2 = jedis.lrange("mylist", 0, -1); // ,b,a,a,b]
  ```

* 集合 Sets：`sadd`、`srem`、`smembers`

  ```java
  // set 存储
  jedis.sadd("myset","java","php","c++");
  
  // set 获取
  Set<String> myset = jedis.smembers("myset"); //[c++,java,php]
  ```

* 有序集合 Sorted sets：`zadd`、`zrem`、`zrange [withscores]`

  ```java
  jedis.zadd("mysortedset",3,"亚瑟");
  jedis.zadd("mysortedset",30,"后裔");
  jedis.zadd("mysortedset",55,"孙悟空");
  
  // sortedset 获取
  Set<String> mysortedset = jedis.zrange("mysortedset", 0, -1);
  ```


### 1.5.3 Jedis连接池-JedisPool

* 使用

  1. 创建`JedisPool`连接池对象
  2. 调用方法`getResource()`方法获取Jedis连接

  ```java
  //0.创建一个配置对象，耦合度高！
  JedisPoolConfig config = new JedisPoolConfig();
  config.setMaxTotal(50);
  config.setMaxIdle(10);
  
  //1.创建Jedis连接池对象
  JedisPool jedisPool = new JedisPool(config,"localhost",6379);
  
  //2.获取连接
  Jedis jedis = jedisPool.getResource();
  //3. 使用
  jedis.set("hehe","heihei");
  //4. 关闭 归还到连接池中
  jedis.close();
  ```

* **JedisUtils**

  ```java
  /**
   JedisPool工具类
      加载配置文件，配置连接池的参数
      提供获取连接的方法
   */
  public class JedisPoolUtils {
      private static JedisPool jedisPool;
      static{
          //读取配置文件
          InputStream is = JedisPoolUtils.class.getClassLoader().getResourceAsStream("jedis.properties");
          //创建Properties对象
          Properties pro = new Properties();
          //关联文件
          try {
              pro.load(is);
          } catch (IOException e) {
              e.printStackTrace();
          }
          //获取数据，设置到JedisPoolConfig中
          JedisPoolConfig config = new JedisPoolConfig();
          config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
          config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
          //初始化JedisPool
          jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
      }
      /**
       * 获取连接方法
       */
      public static Jedis getJedis(){
          return jedisPool.getResource();
      }
  }
  ```


## 1.6 案例

需求：

* 提供index.html页面，页面中有一个省份 下拉列表
* 当页面加载完成后 发送ajax请求，加载所有省份

注意：使用**redis**缓存一些**不经常发生变化的数据**。
* **数据库的数据一旦发生改变，则需要更新缓存**。
  * 数据库的表执行增删改的相关操作，需要将redis缓存数据清空，再次存入
  * 在service对应的增删改方法中，将redis数据删除。



# 2 MongoDB

## 1 MongoDB 简介

MongoDB 是一个跨平台的，面向文档的数据库，是当前 NoSQL 数据库产品中最热门的一种。它介于关系数据库和非关系数据库之间，是非关系数据库当中功能最丰富，最像关系数据库的产品。它支持的数据结构非常松散，是**类似 JSON  的 BSON 格式**，因此可以**存储比较复杂的数据类型**。但是安全性不高！！！

MongoDB**特点**：

​	MongoDB 最大的特点是他支持的**查询语言非常强大**，其语法有点**类似于面向对象的查询语言**，几乎可以实现类似关系数据库单表查询的绝大部分功能，而且还支持对数据建立索引。它是一个**面向集合的，模式自由的文档型数据库**。具体特点如下：

- 面向集合存储，易于存储对象类型的数据
- 模式自由
- 支持动态查询
- 支持完全索引，包含内部对象
- 支持复制和故障恢复
- 使用高效的二进制数据存储，包括大型对象（如视频等）
- 自动处理碎片，以支持云计算层次的扩展性
- 支持 Python，PHP，Ruby，Java，C，C#，Javascript，Perl 及 C++语言的驱动程序，社区中也提供了对 Erlang 及.NET 等平台的驱动程序
- 文件存储格式为 BSON（一种 JSON 的扩展）

MongoDB**体系结构**：

​	MongoDB 的逻辑结构是一种层次结构。在MongoDB 中是通过**数据库**、**集合**、**文档**的方式来管理数据。逻辑结构是面向用户的，用户使用 MongoDB 开发应用程序使用的就是逻辑结构。

| SQL术语/概念 | MongoDB术语/概念 | 解释/说明                                  |
| ------------ | ---------------- | ------------------------------------------ |
| database     | database         | 数据库                                     |
| table        | collection       | 数据库表/集合                              |
| row          | document         | 数据记录行/文档                            |
| column       | field            | 数据字段/域                                |
| index        | index            | 索引                                       |
| table joins  |                  | 表连接（MongoDB不支持）                    |
| primary key  | primary key      | 主键，MongoDB自动在每个集合中添加_id的主键 |

一个mongodb实例可以创建多个数据库；一个数据库可以创建多个集合；一个集合可以包括多个文档。





## 2 下载安装

MongoDB 的[官方网站](http://www.mongodb.com/)，本次使用的是3.4版本。下载好msi安装即可。

在按照的目录下创建`mongo.conf`（配置文件）、``data\`（存放数据）、`logs\mongo.log`（日志，起名任意）

```properties
#数据库路径
dbpath=C:\Develop\MongoDB\Server\3.4\data
#日志输出文件路径
logpath=C:\Develop\MongoDB\Server\3.4\logs\mongo.log
#错误日志采用追加模式
logappend=true
#启用日志文件，默认启用
journal=true
#这个选项可以过滤掉一些无用的日志信息，若需要调试使用请设置为false
quiet=true
#端口号 默认为27017，不配置也是这样
port=27017
```

这个版本安装后可能没有安装服务，需要手动安装（4.0之后安装时有选项）

进入`bin\`使用`--install`选项来安装服务，使用`--config`选项来指定之前创建的配置文件（省去使用`--dbpath`）

```
.\mongod.exe --config "C:\Develop\MongoDB\Server\3.4\mongo.conf" --install
```

启动服务：`net start MongoDB`

关闭服务：`net stop MongoDB`

移除服务：`"C:\Develop\MongoDB\Server\3.4\bin\mongod.exe" ‐‐remove`

启动mongodb服务后，浏览器中输入http://127.0.0.1:27017，看到`It looks like you are trying.....`安装启动成功

也可以通过bin目录下的`mongo.exe`连接mongodb

> studio3t是mongodb优秀的客户端工具。官方地址在https://studio3t.com/



## 3 入门

### 3.1 连接MongoDB

MongoDB的使用方式是**客户服务器模式**，即使用一个客户端连接MongoDB数据库（服务端）。命令格式如下：

```
mongodb://[username:password@]host1[:port1][,host2[:port2],...[,hostN[:portN]]][/[database][?options]]
```

* `mongodb://`：固定前缀
* `username`：账号，本地连接可不填
* `password`：密码，本地连接可不填
* `host`：主机名或ip地址，只有host主机名为必填项
* `port`：端口，可不填，默认27017
* `/database`：连接某一个数据库
* `?options`：连接参数，key/value对

```
mongodb://localhost  连接本地数据库27017端口
mongodb://root:itcast@localhost  使用用户名root密码为itcast连接本地数据库27017端口
mongodb://localhost,localhost:27018,localhost:27019  连接三台主从服务器，端口为27017、27018、27019
```

------

1. 使用MongoDB自带的javascript shell（`mongo.exe`）连接，不用输入用户名密码等直接连接成功

2. 使用studio3T等工具连接

3. 使用Java程序连接（[3.4版本详细参考](http://mongodb.github.io/mongo-java-driver/3.4/driver/tutorials/connect-to-mongodb/)）

   1. Maven依赖

      ```xml
      <dependency>
          <groupId>org.mongodb</groupId>
          <artifactId>mongo‐java‐driver</artifactId>
          <version>3.4.3</version>
      </dependency>
      ```

   2. 测试程序（实际中可能使用Spring Data MongoDB使用）

      ```java
      @Test
      public void testConnection(){
          //创建mongodb 客户端
          MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
          //或者采用连接字符串
          //MongoClientURI connectionString = new
          MongoClientURI("mongodb://root:root@localhost:27017");
          //MongoClient mongoClient = new MongoClient(connectionString);
          //连接数据库
          MongoDatabase database = mongoClient.getDatabase("test");
          // 连接collection
          MongoCollection<Document> collection = database.getCollection("student");
          //查询第一个文档
          Document myDoc = collection.find().first();
          //得到文件内容 json串
          String json = myDoc.toJson();
          System.out.println(json);
      }
      ```

### 3.2 数据库

- **查询**数据库

  - `show dbs` 查询全部数据库
  - `db` 显示当前数据库

- **创建**数据库（没有此名称的则创建，有则切换） `use DATABASE_NAME`

  【注意】新创建的数据库不显示，需要至少包括一个集合

- **删除**数据库（慎用！！！需要先切换到被删除的数据库） `db.dropDatabase()`

### 3.3 集合

* 创建集合 `db.createCollection(name, options)` name: 新创建的集合名称；options: 创建参数，可省略
* 删除集合 `db.collection_Name.drop()` 需先切换到某这个集合所属的数据库，collectionName: 集合名称

### 3.4 文档

mongodb中文档的格式是json格式，下边就是一个文档，包括两个key：_id主键和name

```json
{
    "_id" : ObjectId("5b2cc4bfa6a44812707739b5"),
    "name" : "黑马程序员"
}
```

#### 3.4.1 插入文档

`db.COLLECTION_NAME.insert(document)`

每个文档默认以`_id`作为**主键**，主键默认类型为**ObjectId**（对象类型），MongoDB会自动生成主键值

如：`db.student.insert({"name":"黑马程序员","age":10})`

【注意】同一个集合中的文档的key可以不相同！但是建议设置为相同的。

#### 3.4.2 更新文档（有2种方式）

`db.collection.update(<query>,<update>,<options>)`

query：查询条件，相当于 sql 语句的 where；update：更新文档内容；options：选项

* **替换文档**

  将符合条件 "name":"北京黑马程序"的第一个文档替换为{"name":"北京黑马程序员","age":10}。

  `db.student.update({"name":"黑马程序员"},{"name":"北京黑马程序员","age":10})`

* **`$set`修改器**（使用$set修改器指定要更新的key，key不存在则创建，存在则更新。）

  将符合条件 "name":"北京黑马程序"的所有文档更新name和age的值。

  `db.student.update({"name":"黑马程序员"},{$set:{"name":"北京黑马程序员","age":10}},{multi:true})`

  multi：false表示更新第一个匹配的文档，true表示更新所有匹配的文档。

#### 3.4.3 删除文档

`db.student.remove(<query>)`

query：删除条件，相当于sql语句中的where

* **删除所有文档**

  `db.student.remove({})`

* **删除符合条件的文档**

  `db.student.remove({"name":"黑马程序员"})`

#### 3.4.4 查询文档

`db.collection.find(query, projection)`

query：查询条件，可不填；projection：投影查询key，可不填

* **查询全部**

  `db.student.find()`

* **查询符合条件的记录**

  查询name等为"黑马程序员"的文档：`db.student.find({"name":"黑马程序员"})`

* **投影查询**

  只显示name和age两个key，`_id`主键不显示：`db.student.find({"name":"黑马程序员"},{name:1,age:1,_id:0})`



### 3.5 用户

#### 3.5.1 创建用户

必须选择admin数据库

语法格式：

```
mongo>db.createUser(
{ 	user: "<name>",
	pwd: "<cleartext password>",
	customData: { <any information> },
	roles: [
		{ role: "<role>", db: "<database>" } | "<role>",
		...
	]}
)
```

例创建root用户，角色为root

```
use admin
db.createUser(
	{
		user:"root",
		pwd:"123",
		roles:[{role:"root",db:"admin"}]
	}
)
```

内置角色如下：

* 数据库用户角色：read、readWrite;
* 数据库管理角色：dbAdmin、dbOwner、userAdmin；
* 集群管理角色：clusterAdmin、clusterManager、clusterMonitor、hostManager；
* 备份恢复角色：backup、restore；
* 所有数据库角色：readAnyDatabase、readWriteAnyDatabase、userAdminAnyDatabase、dbAdminAnyDatabase
* 超级用户角色：root



#### 3.5.2 认证登录

为了安全需要，Mongodb要打开认证开关，即用户连接Mongodb要进行认证，其中就可以通过账号密码方式进行认证

1. 在`mongo.conf`中设置 `auth=true`
2. 重启MongoDB服务
3. 使用账号和密码连接数据库
   1. mongo.exe连接 `.\mongo.exe ‐u root ‐p 123 ‐‐authenticationDatabase admin`
   2. Studio 3T等工具连接：需要输入用户名、密码、验证的数据库



#### 2.5.3 查询用户

查询当前库下的所有用户：`show users`



#### 2.5.4 删除用户

语法：`db.dropUser("用户名")`，还是要先切换到admin数据库



#### 2.5.5 修改用户

语法格式：

```
db.updateUser(
	"<username>",
	{
		customData : { <any information> },
		roles : [
				{ role: "<role>", db: "<database>" } | "<role>",
				...
				],
		pwd: "<cleartext password>"
	},
	writeConcern: { <write concern> })
```

例如，先创建test1用户：

```
db.createUser(
	{
		user:"test1",
		pwd:"test1",
		roles:[{role:"root",db:"admin"}]
	}
)
```

修改test1用户的角色为readWriteAnyDatabase

```
use admin
db.updateUser("test1",{roles:[{role:"readWriteAnyDatabase",db:"admin"}]})
```



#### 2.5.6 修改密码

语法格式：`db.changeUserPassword("username","newPasswd")`

例如，修改test1用户的密码为123

```
use admin
db.changeUserPassword("test1","123")
```















# 其他旧知识

## 10.1 common-dbutils.jar

* Apache提供的用于简化JDBC的代码

* **QueryRunner(对数据库的增删改查)查询多一个参数比较麻烦**

  * **update方法：**

    * **int update(String sql, Object... params)** -->  可执行增、删、改语句
    * int update(**Connection con**, String sql, Object... parmas) --> 需要调用者提供Connection，这说明本方法不再管理Connection了。**支持事务**!

  * **query方法：**

    * **T query(String sql, ResultSetHandler rsh, Object... params)** --> 可执行查询

      它会先通过其他俩参数得到ResultSet，然后调用rsh的handle()把rs转换成需要的类型！

    * T query(**Connection con**, String sql, ResultSetHadler rsh, Object... params)，**支持事务**

* **ResultSetHandler接口的实现类（结果集处理器）：**

  * **BeanHandler**(单行) --> 构造器需要一个Class类型的参数，用来把一行结果转换成指定类型的javaBean对象

    ```java
    QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
    String sql = "select * from t_stu where sid=?";
    Object[] params = {1001};
    Stu stu = qr.query(sql, new BeanHandler<Stu>(Stu.class), params);
    System.out.println(stu);
    ```

  * **BeanListHandler**(多行) --> 构造器也是需要一个Class类型的参数，用来把一行结果集转换成一个javabean，那么多行就是转换成List对象，一堆javabean

    ```java
    QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
    String sql = "select * from t_stu";
    List<Stu> stuList = qr.query(sql, new BeanListHandler<Stu>(Stu.class));
    System.out.println(stuList);
    ```

  * **MapHandler**(单行) --> 把一行结果集转换Map对象

    ```java
    QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
    String sql = "select * from t_stu where sid=?";
    Object[] params = {1001};
    Map map = qr.query(sql, new MapHandler(), params);
    System.out.println(map);
    ```

  * **MapListHandler**(多行) --> 把一行记录转换成一个Map，多行就是多个Map，即List< Map>！

    ```java
    QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
    String sql = "select * from t_stu";
    List<Map<String,Object>> mapList = qr.query(sql, new MapListHandler());
    System.out.println(mapList);
    ```

  * **ScalarHandler**(单行单列) --> 通常用与select count(*) from t_stu语句！结果集是单行单列的！它返回一个Object

    ```java
    QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
    String sql = "select count(*) from t_stu";
    //版本等原因类型可能为Long、Integer、BigInteger，Number是他们父类，或用Object类接收
    Number cnt = (Number)qr.query(sql, new ScalarHandler());
    long c = cnt.longValue();
    System.out.println(c);
    ```






## 10.3 Service事务

* **发展：**

  1. **DAO中不是处理事务的地方**，因为DAO中的每个方法都是对数据库的一次操作
  2. **Service中的方法才是对应一个业务逻辑**。也就是说我们需要在Service中的一方法中调用DAO的多个方法，而这些方法应该在一起事务中
  3. 处理**事务应该使用同一个Connection对象**，怎么让DAO的多个方法使用相同Connection呢？方法不能自己来获得Connection，而是由外界传递进去。通过JdbcUtils的getConnection()来获取传递即可，但是Service中不应该出现Connection，它应该只在DAO中出现
  4. 我们把**对事务的开启和关闭放到JdbcUtils**中，在**Service中调用JdbcUtils的方法来完成事务的处理**，但在Service中就不会再出现Connection这一“禁忌”了。添加`beginTransaction()`和`rollbackTransaction()`以及`commitTransaction()`方法
  5. DAO中的方法不用再让Service来传递Connection了。**DAO会主动从JdbcUtils中获取Connection对象**，这样，JdbcUtils成为了DAO和Service的中介！
  6. 让JdbcUtils可以**多线程**环境下被使用的最好方法是为每个线程提供一个Connection，这样每个线程都可以开启自己的事务了（利用**ThreadLocal**类）（学习Spring事务打基础）

* 示例：

  * TxQueryRunner这个类可以自己来处理连接的问题，无需外界传递！　
    * 通过JdbcUtils.getConnection()得到连接！有可能是事务连接，也可能是普通的连接！

    * JdbcUtils.releaseConnection()完成对连接的释放！如果是普通连接，关闭之！

  * 或使用QueryRunner

  ```java
  public class AccountDao {
  	public void updateBalance(String name, double balance) throws SQLException {
  		String sql = "update account set balance=balance+? where name=?";
  		Connection con = JdbcUtils.getConnection();
  		QueryRunner qr = new QueryRunner();
  		qr.update(con, sql, balance, name);
  	}
  }
  
  public class AccountService {
  	private AccountDao dao = new AccountDao();
  	
  	public void transfer(String from, String to, double balance) {
  		try {
  			JdbcUtils.beginTranscation();
  			dao.updateBalance(from, -balance);
  			dao.updateBalance(to, balance);
  			JdbcUtils.commitTransaction();
  		} catch(Exception e) {
  			try {
  				JdbcUtils.rollbackTransaction();
  			} catch (SQLException e1) {
  				throw new RuntimeException(e);
  ...
  //main
  AccountService as = new AccountService();
  as.transfer("zs", "ls", 100);
  ```

* **JdbcUtils3.0**

  ```java
  public class JdbcUtils {
  	private static DataSource ds = new ComboPooledDataSource();// 饿汉式
  	//它为null表示没有事务；它不为null表示有事务
  	//当开启事务时，需要给它赋值，让dao方法共享这个Connection；当结束事务时，需要给它赋值为null
      private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
  	
      public static DataSource getDataSource() {return ds;}
  	
  	//dao使用本方法来获取连接
  	public static Connection getConnection() throws SQLException {
  		Connection con = tl.get();
  		if(con != null) return con;//当前线程有事务连接，则返回
  		return ds.getConnection();//当前线程没有事务连接，则返回连接池新的con
  	}
  	
  	//开启事务
  	public static void beginTransaction() throws SQLException {
  		Connection con = tl.get();//获取当前线程的事务连接
  		if(con != null) throw new SQLException("已经开启了事务，不能重复开启！");
  		con = ds.getConnection();//给con赋值，表示开启了事务
  		con.setAutoCommit(false);//设置为手动提交
  		tl.set(con);//把当前事务连接放到tl中
  	}
  	
  	//提交事务
  	public static void commitTransaction() throws SQLException {
  		Connection con = tl.get();//获取当前线程的事务连接
  		if(con  null) throw new SQLException("没有事务不能提交！");
  		con.commit();//提交事务
  		con.close();//关闭连接
  		con = null;//表示事务结束！
  		tl.remove();
  	}
  	
  	//回滚事务
  	public static void rollbackTransaction() throws SQLException {
  		Connection con = tl.get();//获取当前线程的事务连接
  		if(con  null) throw new SQLException("没有事务不能回滚！");
  		con.rollback();
  		con.close();
  		con = null;
  		tl.remove();
  	}
  	
  	//释放Connection
  	public static void releaseConnection(Connection connection) throws SQLException{
  		Connection con = tl.get();//获取当前线程的事务连接
  		if(connection != con) {//如果参数连接与当前事务连接不同，说明这不是当前事务，可以关闭
  			if(connection != null &&!connection.isClosed()) {//参数连接没有关闭则关闭之
  				connection.close();
  	...
  ```





## 7.4 面向接口编程(UserDao)

- DAO（Data Access Object）模式就是写一个类，把**访问数据库的代码封装**起来。在数据库与业务逻辑之间

  - 实体域，即操作的对象，例如我们操作的表是user表，那么就需要先写一个User类；
  - DAO模式需要先提供一个DAO接口；
  - 然后再提供一个DAO接口的实现类；
  - 再编写一个DAO工厂，Service通过工厂来获取DAO实现。

- 把UserDao修改为接口，然后把原来的UserDao修改类名为UserDaoImpl

  修改UserService中对UserDao的实例化：private UserDao userDao = DaoFactory.getUserDao()

  创建DaoFactory，提供getUserDao()

## 7.5 ORM(**对象关系映射**)

## 7.6 时间问题

- 领域对象（domain）中的所有属性不能出现java.sql包下的东西！即不能使用java.sql.Date；

  ResultSet#getDate()返回的是java.sql.Date()

  PreparedStatement#setDate(int, Date)，其中第二个参数也是java.sql.Date

- 数据库类型与java中类型的对应关系

  - java.**util.Date**--->java.**sql**.Date、Time、Timestamp

    - 把util的Date转换成毫秒值

      使用毫秒值创建sql的Date、Time、Timestamp

  - java.sql.Date、Time、Timestamp--->java.util.Date

    - 这一步不需要处理了：因为java.sql.Date是java.util.Date子类

  ```java
  java.util.Date date = new java.util.Date();
  long l = date.getTime();
  java.sql.Date sqlDate = new java.sql.Date(l);
  ```

## 7.7 大数据

- 目标：把mp3保存到数据库中。在my.ini中修改如下配置！max_allowed_packet=20M

- 所谓大数据，就是大的字节数据，或大的字符数据。标准SQL中提供了如下类型来保存大数据类型

  - clob(text)：**字符串类型**；tinytext(2^8-1B)、text(2^16-1B)、mediumtext(2^24-1B)、longtext(2^32-1B)
    - SQL中提供clob、blob，但是MySQL中没有clob，通过text替代
  - blob：**字节类型**；tinytext(2^8-1B)、text(2^16-1B)、mediumtext(2^24-1B)、longtext(2^32-1B)

- 数据库表为

  ```mysql
  CREATE TABLE tab_bin(
  	id 	INT 	PRIMARY KEY AUTO_INCREMENT,
  	filename	VARCHAR(100),
  	data 	MEDIUMBLOB
  );
  ```

- 向数据库**插入二进制数据**需要使用PreparedStatement为原**setBinaryStream**(int, InputSteam)方法来完成

  ```java
  con = JdbcUtils.getConnection();
  String sql = "insert into tab_bin(filename,data) values(?, ?)";
  pstmt = con.prepareStatement(sql);
  pstmt.setString(1, "a.jpg");
  InputStream in = new FileInputStream("f:\\a.jpg");
  pstmt.setBinaryStream(2, in);
  pstmt.executeUpdate();
  ```

- **读取二进制数据**，需要在查询后使用**ResultSet**类的**getBinaryStream**()方法来获取输入流对象

  ```java
  con = JdbcUtils.getConnection();
  String sql = "select filename,data from tab_bin where id=?";
  pstmt = con.prepareStatement(sql);
  pstmt.setInt(1, 1);
  rs = pstmt.executeQuery();
  rs.next();
  
  String filename = rs.getString("filename");
  OutputStream out = new FileOutputStream("F:\\" + filename);
  
  InputStream in = rs.getBinaryStream("data");
  IOUtils.copy(in, out);
  out.close();
  ```

## 7.8 批处理

- 批处理只针对更新（**增、删、改**）语句，批处理没有查询什么事儿

- MySQL的批处理也需要通过参数来打开：在四大参数之一url添加**rewriteBatchedStatements=true**

- Statement批处理。可以多次调用Statement类的addBatch(String sql)方法，把需要执行的所有SQL语句添加到一个“批”中，然后调用Statement类的executeBatch()方法来执行当前“批”中的语句

  - void addBatch(String sql)：添加一条语句到“批”中
  - int[] executeBatch()：执行“批”中所有语句。返回值表示每条语句所影响的行数据
  - void clearBatch()：清空“批”中的所有语句

- **PreparedStatement批处理**。因为每个PreparedStatement对象都绑定一条SQL模板。所以向PreparedStatement中添加的不是SQL语句，而是**给“?”赋值**

  - void addBatch()；添加“批”
  - int[] executeBatch()：执行“批”。返回值表示每条语句所影响的行数据

  ```JAVA
  con = JdbcUtils.getConnection();
  String sql = "insert into stu values(?,?,?,?)";
  pstmt = con.prepareStatement(sql);
  for(int i = 0; i < 10; i++) {
      pstmt.setString(1, "S_10" + i);
      pstmt.setString(2, "stu" + i);
      pstmt.setInt(3, 20 + i);
      pstmt.setString(4, i % 2  0 ? "male" : "female");
      pstmt.addBatch();
  }
  pstmt.executeBatch();
  ```



## 8.3 装饰者模式(Wrapper)

- 对象增强的手段：

  - **继承**：会使**类增多**

    - 特点：增强的内容是死的、被增强的对象也是死的

      ```java
      class 咖啡{}
      class 加奶咖啡 extends 咖啡类 {}
      class 加糖咖啡 extends 咖啡类 {}
      ...
      class 加糖加奶咖啡 extends 咖啡类 {}  
      ```

  - **装饰者模式**（不知被增强对象具体类型，”**是你还有你、一切拜托你**“）

    - 特点：增强的内容是死的、被增强的对象可以是任意的。如IO流

      ```java
      class 咖啡{}
      class 加奶咖啡类 extends 咖啡 {}
      class 加糖咖啡类 extends 咖啡 {}
      ...
      咖啡 a = new 咖啡();
      咖啡 b = new 加奶咖啡(a);//对a进行修饰，就是给a加奶
      咖啡 c = new 加糖咖啡(b);
      ```

      ```java
      class MyConnection implements Connection{//是你		//或extends
          private Connection con;//还有你				   //底层对象，被增强对象
          public MyConnection(Connection con){             //通过构造器传递底层对象
              this.con=con;
          }
          //一切拜托你
          public PreparedStatement preparedStatement(){	 //若是继承，则不用写
              return con.preparedStatement();
          }
          //增强点
          public void close(){
              归还给连接池；
          }
      }
      ```

  - 动态代理

    - 特点：增强的内容可以任意（事务处理）、被增强的对象也是任意的（Service）



# 



## 11 分页

* 分页的优点：只查询一页，不用查询所有页！

  ```latex
  第N页/共M页　首页 上一页 1 2 3 4 5 6 7 8 9 10　下一页 尾页 [ ] go
  ```

* **页面的数据都是由Servlet传递来的！**

  * **当前页**：currentPage(**cp**)；

    * pc：如果页面没有传递当前页码，那么Servlet默认是第一页，或者按页面传递的来准！

  * **总页数**：totalPages(**tp**)：总记录数/每页记录数

    * **总记录数**：totalRecored(**tr**)：dao来获取，SELECT COUNT(*) FROM t_customer;

    * **每页记录数**：page size(**ps**)：业务数据或叫系统数据！（14中设置为10行）

  * **当前页数据**：**beanList**：dao来获取，SELECT * FROM t_customer LIMIT X,Y;

  * **url**

* **分页Bean的设计**

  * 这些分页数据总要在各层之间来回的传递，将其封装到一个javabean中，如PageBean.java

    ```java
    private int cp;//当前页码，current Page
    //private int tp;//总页数，total page；可以不需要set方法，只保留get方法通过计算得出
    private int tr;//总记录数，total record
    private int ps;//每页记录数，page size
    private List<T> beanList;//当前页记录
    ```

* **分页在各层中的处理**

  * **页面**：给出分页相关的链接们
    * 页面需要给Servlet提供：有可能传递**当前页码cp**
  * **Servlet**：**创建PageBean**对象并给属性赋初值，然后传递给页面
    * 需要给Dao传递**当前页码cp**、**每页记录数ps**
  * Service：略
  * **Dao**：
    * **tr**：SELECT COUNT(*) FROM t_customer;
    * **beanList**：SELECT * FROM t_customer LIMIE X,Y;

* **显示分页页码列表**

  * 首页/上一页/下一页/尾页

    ```jsp
    <a href="${pb.url }&pc=1">首页</a>
    <c:if test="${pb.pc > 1 }">
    	<a href="${pb.url }&pc=${pb.pc-1}">上一页</a>
    </c:if>
    <c:if test="${pb.pc < pb.tp }">
    	<a href="${pb.url }&pc=${pb.pc+1}">下一页</a>
    </c:if>
    <a href="${pb.url }&pc=${pb.tp}">尾页</a>
    ```

  * 11 12 13 14 15 (16) 17 18 19 20【最多显示10页】【当前页在页码中位置定为16(前-5后+4)】
    * 只需要**当前页码cp**即可确定页码列表，推算出**begin（cp-5）**和**end（cp+4）**即可
    * 计算流程：
      1. 如果总页数<=10（列表长度），那么begin=1，end=总页数
      2. 否则使用公式计算；begin=pc-5, end=pc + 4
        1. 头溢出：当begin<1时，让begin=1
        2. 尾溢出：当end>tp时，让end=tp

    ```jsp
    <c:choose>
    	<%-- 如果总页数不足10页，那么把所有的页数都显示出来！ --%>
    	<c:when test="${pb.tp <= 10 }">
    		<c:set var="begin" value="1" />
    		<c:set var="end" value="${pb.tp }" />
    	</c:when>
    	<c:otherwise>
    		<%-- 当总页数>10时，通过公式计算出begin和end --%>
    		<c:set var="begin" value="${pb.pc-5 }" />
    		<c:set var="end" value="${pb.pc+4 }" />	
    		<%-- 头溢出 --%>
    		<c:if test="${begin < 1 }">
    			<c:set var="begin" value="1" />
    			<c:set var="end" value="10" />
    		</c:if>	
    		<%-- 尾溢出 --%>
    		<c:if test="${end > pb.tp }">
    			<c:set var="begin" value="${pb.tp - 9 }" />
    			<c:set var="end" value="${pb.tp }" />
    		</c:if>	
    	</c:otherwise>
    </c:choose>
    <%-- 循环遍历页码列表 --%>
    <c:forEach var="i" begin="${begin }" end="${end }">
    	<c:choose>
    		<c:when test="${i eq pb.pc }">
    			[${i }]
    		</c:when>
    		<c:otherwise>
    			<a href="${pb.url }&pc=${i}">[${i }]</a>	
    		</c:otherwise>
    	</c:choose>
    </c:forEach>
    ```

* **在超链接中要保留参数**

  * 当使用多条件查询后，然后在点击第2 页时，这个第2页超链接没有条件了，所以会丢失条件，所以我们需要在页面上的所有链接都要保留条件
  * 把条件以一个字符串的形式保存到PageBean的**url**中！这个任务交给Servlet！
    * 表单请求改为GET，即可以在链接后带参数（记得设置请求编码）
    * 将请求获取的参数中带pc的截取掉，使用jsp链接自带的后缀pc

## 14 客户关系管理系统

### 14.1 架构的搭建

1. 导入原型（只有页面，没有功能）



### 14.2 编码中问题

1. 导入原型
   - jstl标签库导入后再运行查看页面
2. 创建包和类(按照公司名.项目名.domain/dao/service/web.servlet来创建)
   - domain中创建和数据库中字段一致的成员变量并提供get/set方法，默认构造可省略
   - dao中提供**TxQueryRunner对象**进行数据库的增删查改
     - 增删改需要提供 1.**SQL字符串模板**；2.**params数组设置(Object型)**；3.**qr执行update(*,*)**
     - 查询还需要提供 1.**返回值ResultSetHandler实现类**；2.**qr执行update(sql,实现类,params)**
     - **多条件组合查询：使用参数来拼凑SQL语句中WHERE 1=1语句**
       - 判断CNAME是否有值，在WHERE子句中添加一个AND条件（StringBuilder）
       - 同时，设置一个参数（ArrayList）
   - service中依赖dao对象，由于这个项目没有什么业务，只有传值的作用
   - Servlet中依赖service对象，**继承BaseServlet**(**注解配置**)，**请求处理方法**的签名必须**与service相同**
     - **提交参数转换为javabean对象**：利用CommonUtils.toBean(Map,Class)或BeanUtils.populate(Map,Class)
     - 添加主键值UUID
     - request域属性的保存
     - 返回String让BaseServlet来决定转发、重定向、下载等
   - JSP页面c标签中forEach运用(iteams、var)、if运用
     - input标签method的运用，来提供请求处理方法参数
