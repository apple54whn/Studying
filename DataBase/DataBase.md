# 1 DataBase 简介

- 数据库就是用来**存储和管理数据的仓库**，本质上是一个文件系统，以文件的方式存在服务器的电脑上的。所有的**关系型数据库**都可以使用通用的 SQL 语句进行管理。
- 数据库的特点：
  * **持久化存储数据**的。其实数据库就是一个文件系统
  * **方便存储和管理**数据
  * 使用了**统一的方式**操作数据库 -- **SQL**
- 常见数据库
  - **MySQL**：**开源免费**的小型的数据库，**功能强大**。已经被 Oracle 收购了。MySQL6.x 版本也开始收费。
  - **Oracle**：收费的大型数据库，Oracle 公司的产品。收购了 Sun 和 MySql
  - SQL Server：MicroSoft 公司收费的中型的数据库。C#、.net 等语言常使用
  - DB2 ：IBM 公司的数据库产品，收费的。常应用在银行系统中
  - SQLite: 嵌入式的小型数据库，应用在手机端，如：Android
- **RDBMS（关系型数据库管理系统）** 

  - n个**DataBase**（文件夹）
    - n个**table**（文件），其中有定义表的列名和列类型的表结构
      - n个**表记录**：一行一行的数据记录
- **MySQL重要文件夹**
  - C:\Program Files\MySQL\MySQL Server 8.0：**DBMS管理程序**
  - C:\ProgramData\MySQL\MySQL Server 8.0\data：**DBMS数据库文件**（卸载MySQL时不会删除）
    - 每个目录表示一个数据库，MySQl8的每个数据库目录下会有0~N个扩展名为ibd的table文件
- **MySQL重要文件**
  - C:\Program Files\MySQL\MySQL Server 8.0\bin\ **mysqld**.exe：**服务器程序**，必须先启动它

  - C:\Program Files\MySQL\MySQL Server 8.0\bin\ **mysql**.exe：**客户端程序**操作服务器，服务器需先开启

    > mysqld 是 MySQL 的主程序，服务器端。mysql 是 MySQL 的命令行工具，客户端。 

  - C:\ProgramData\MySQL\MySQL Server 8.0\ ==**my.ini**==：**服务器配置文件**，之前版本放在安装目录中bin下

    - 配置MySQL的端口：==**默认为3306**==（一般不建议修改）
    - 配置字符编码：（一般不建议修改）
      - [mysql]下配置默认客户端编码：default-character-set=gbk
      - [mysqld]下配置默认服务器编码：character-set-server=utf8
    - 配置二进制数据大小上限：（一般不建议修改）
      - 在[mysqld]下配置：max_allowed_packet=8M
- ==**服务器操作**==（我的服务名称为mysql8）
  - 开启服务器(必须保证mysql为windows服务)：**`net start mysql8`**，mysqld.exe进程存在
  - 关闭服务器(必须保证mysql为windows服务)：**`net stop mysql8`**，mysqld.exe进程不存在
- ==**客户端操作**==
  - 登录服务器：**`mysql -uroot -p123 `**或**`mysql -uroot -p`**，然后输入密码
    - 远程登录：**` mysql -h 127.0.0.1 -uroot -p`**，`-h`和`IP`分开，`loclahost`不用分开，其他同上
      - 还有一种写全称的：`mysql --host=ip地址 --user=用户名 --password=密码`
  - 退出服务器：**`exit`或`quit`**



# 2 SQL

* **结构化查询语言**(Structured Query Language)

  * 是一种所有==**关系型数据库**的**查询规范**==，不同的数据库都支持。 
  * **通用**的数据库操作语言，可以用在不同的数据库中。 
  * 不同的数据库 SQL 语句有一些**区别**，称为方言

* **SQL通用语法**

  * SQL语句可以在**单行或多行**书写，以**分号结尾**
  * 可使用空格和缩进来增强语句的可读性
  * MySQL不区别大小写，**关键字建议使用大写**
  * 注释的三种写法
    * 单行：`--空格`；多行：`/* */`；mysql特有：`#`

* SQL语句**分类**

  * **DDL**（Data Definition Language）：**数据定义语言**

    **数据库或表**的操作，创建、删除、修改库、表

  * **DML**（Data Manipulation Language）：**数据操作语言**

    对**表**的**记录**进行更新（增、删、改）

  * **DQL**（Data Query Language）：**数据查询语言**，对**表**的**记录**进行查询

  * DCL（Data Control Language）：数据控制语言，对用户权限的设置 

## 2.1 DDL

### 2.1.1 数据库(DATABASE)

* Create
  * ==**创建**==数据库(CREATE DATABASE)

    ```mysql
    CREATE DATABASE [IF NOT EXISTS] 数据库名 [CHARSET=UTF8];
    ```

* Retrieve

  * ==**查看**所有数据库==(SHOW DATABASES)

    ```mysql
    SHOW DATABASES;
    ```

  * ==**查看**数据库**定义信息**==，包括创建语句和字符集

    ```mysql
    SHOW CREATE DATABASE 数据库名;
    ```

* Update

  * ==**修改**数据库**编码**==

    ```mysql
    ALTER DATABASE 数据库名 CHARACTER  SET UTF8;
    ```

* Delete

  * ==**删除**数据库==(DROP DATABASE)

    ```mysql
    DROP DATABASE [IF EXISTS] 数据库名;
    ```

* 使用

  * ==**切换**数据库==(USE 数据库名)

    ```mysql
    USE 数据库名;
    ```

  * ==查看**当前使用**的数据库==，MySQL特有

    ```mysql
    SELECT DATABASE();
    ```

### 2.1.2 数据类型(列类型)

* ==**int**==：整型

  float：浮点型

  ==**double()**==：浮点型，例如double(5,2)表示最多5位，其中必须有2位小数，即最大值为999.99

  **decimal**：浮点型，在表示钱方面使用该类型，因为不会出现精度缺失问题

  **char**：固定长度字符串类型； char(255)，数据的长度不足指定长度，补空格到指定长度！

  ==**varchar()**==：可变长度**字符**串类型； varchar(65535)

  ==**date**==：日期类型，格式为yyyy-MM-dd，只有年月日，没时分秒

  **time**：时间类型，格式为hh:mm:ss

  **datetime**：同时可以表示日期和时间，格式为yyyy-MM-dd hh:mm:ss

  ==**timestamp**==：同上，若不给这个字段赋值，或赋值为null，则默认使用当前系统时间

* text(clob)：**大字符串类型**；tinytext(2^ 8-1B)、text(2^ 16-1B)、mediumtext(2^24-1 B)、longtext(2^32-1B)

  blob：**大字节类型**；tinyblob(2^ 8-1B)、blob(2^ 16-1B)、mediumblob(2^ 24-1B)、longblob(2^32-1B)

* ==**字符和日期**型数据应包含在**单引号**中==。MySQL 中也可以使用双引号做为分隔符。 

### 2.1.3 表(TABLE)

* Create

  * ==**创建表**==(CREATE TABLE)

    ```mysql
    CREATE TABLE [IF NOT EXISTS] 表名(
      列名 列类型,
      列名 列类型,
      ...
      列名 列类型
    );
    ```

    ==**复制表**==结构

    ```mysql
    CREATE TABLE 表名 LIKE 被复制的表名;
    ```

* Retrieve

  * ==**查看**当前数据库中**所有表名称**==(SHOW TABLES)

    ```mysql
    SHOW TABLES;
    ```

    ==**查看表结构**==(DESC)

    ```mysql
    DESC 表名;
    ```

    ==**查看**指定表的**定义信息**==，包括SQL创建语句、字符集

    ```mysql
    SHOW CREATE TABLE 表名;
    ```

* ==**删除表**==(DROP TABLE)

  ```mysql
  DROP TABLE [IF EXISTS] 表名;
  ```

* ==**修改表**==：前缀：ALTER TABLE 表名

  * ==修改**表名称**==(RENAME TO)

    ```mysql
    ALTER TABLE 原表名 RENAME TO 新表名;
    ```

  * ==**修改**表的**字符集**==

    ```mysql
    ALTER TABLE 表名 CHARACTER  SET UTF8;
    ```

  * ==修改之**添加列**==(ADD)

    ```mysql
    ALTER TABLE 表名 ADD (
        列名 列类型,
        列名 列类型,
        ...
    );
    ```

  * ==修改之**删除列**==(DROP)

    ```mysql
    ALTER TABLE 表名 DROP 列名;
    ```

  * ==修改之**修改列名类型**==(CHANGE)

    ```mysql
    ALTER TABLE 表名 CHANGE 原列名 新列名 列类型 主键自增长 非空约束; -- 新的类型可能会影响到已存在数据
    ```

  * ==修改之**修改列类型**==(MODIFY)

    ```mysql
    ALTER TABLE 表名 MODIFY 列名 列类型 主键自增长 非空约束 -- /新的类型可能会影响到已存在数据
    ```

## 2.2 DML

* ==**插入数据**(INSERT INTO)==

  ```mysql
  INSERT INTO 表名(
      列名1,列名2, ...
  ) VALUES(列值1, 列值2, ...),(列值1, 列值2, ...);-- 直接插入多行数据
  -- 列名和表名要一一对应
  -- 没有指定的列等同于插入null值,插入记录总是插入一行
  ```

  ```mysql
  INSERT INTO 表名 
  VALUES(列值1, 列值2);
  -- 插入所有列。值的顺序，必须与表创建时给出的列的顺序相同
  ```

  ==**蠕虫复制**：将一张已经存在的表中的数据复制到另一张表中==

  ```mysql
  INSERT INTO 表名1 SELECT * FROM 表名2; -- 复制所有
  INSERT INTO 表名1(列1, 列2) SELECT 列1, 列2 FROM 表名2; -- 复制部分列
  ```

* ==**删除数据**(DELETE FROM)==

  ```mysql
  DELETE FROM 表名 [WHERE 条件];-- 若不加条件，则删除所有记录
  ```

  ==**删除表中所有数据**(TRUNCATE)==

  ```mysql
  TRUNCATE TABLE 表名; -- truncate 相当于删除表，再创建一张相同结构的表
  ```

* ==**修改数据**(UPDATE...SET)==

  ```mysql
  UPDATE 表名 SET 列名1=列值1, 列名2=列值2, ... [WHERE 条件] -- 不加条件则将表中所有记录修改
  ```




## 2.3 DQL

* <span style="color:red;font-weight:bold">查询不会修改数据库表记录！</span>
* <span style="color:red;font-weight:bold">顺序：SELECT、FROM、WHERE、GROUP BY、HAVING、ORDER BY、LIMIT</span>

### 2.3.1 基本查询(SELECT...FROM)

* <span style="color:red;font-weight:bold">字段(列)控制</span>

  * <span style="color:red;font-weight:bold">查询列(SELECT...FROM)</span>

    ```mysql
    SELECT * FROM 表名; -- 其中“*”表示查询所有列
    ```

    ```mysql
    SELECT 列1 [, 列2, ... 列N] FROM 表名; -- 查询指定列
    ```

  * <span style="color:red;font-weight:bold">去重复(DISTINCT</span>

    ```mysql
    SELECT DISTINCT 列1 [, 列2, ... 列N]  FROM 表名; -- 结果集完全重复
    ```

* <span style="color:red;font-weight:bold">列运算</span>

  * 数字类型的列可以做<span style="color:red;font-weight:bold">加、减、乘、除</span>运算

    ```mysql
    SELECT sal*1.5 FROM emp;
    SELECT sal+comm FROM emp;
    ```

    字符串类型可以做<span style="color:red;font-weight:bold">拼接运算</span>

    ```mysql
    SELECT CONCAT('$', sal) FROM emp;
    ```

  * <span style="color:red;font-weight:bold">替换NULL值</span>

    ```mysql
    SELECT IFNULL(comm, 0)+1000 FROM emp; -- 如果comm中存在NULL值，那么当成0来运算
    ```

  * <span style="color:red;font-weight:bold">给列起别名</span>

    ```mysql
    SELECT IFNULL(comm, 0)+1000 AS 奖金 FROM emp; -- AS可以省略
    ```

### 2.3.2 条件查询(WHERE)

* 运算符

  | 比较运算符             | 说明                                                         |
  | ---------------------- | ------------------------------------------------------------ |
  | >、<、<=、>=、=、<>    | **<>在 SQL 中表示不等于**，在 mysql 中也可以使用!=。没有==   |
  | **BETWEEN...AND**      | 在一个范围之内，如：between 100 and 200 相当于条件在 [100 到 200] 之间 |
  | **IN**、ALL、ANY(集合) | 集合表示多个值，使用逗号分隔。ALL为所有，ANY为任意一个即可，可用最值替代 |
  | **IS [NOT] NULL**      | 查询某一列为[不为] NULL 的值，注：不能写=NULL                |
  | **LIKE**               | 模糊查询：_匹配一个任意字符；%匹配0~N个任意字符              |

  | 逻辑运算符     | 说明                                 |
  | -------------- | ------------------------------------ |
  | **AND**或 &&   | 与，SQL 中建议使用前者，后者并不通用 |
  | **OR** 或 \|\| | 或                                   |
  | **NOT**或 !    | 非                                   |

  ```mysql
  WHERE age >= 18 AND age <= 80
  WHERE age BETWEEN 18 AND 80
  WHERE name='zhangSan' OR name='liSi'
  WHERE name IN ('zhangSan', 'liSi')
  WHERE age IS NULL -- 不能使用等号
  WHERE age IS NOT NULL
  ```

  ```mysql
  SELECT * FROM emp WHERE ename LIKE '张_'; -- 姓张，名字由两个字组成的员工
  SELECT * FROM emp WHERE ename LIKE '___'; -- 姓名由3个字组成的员工
  
  SELECT * FROM emp WHERE ename LIKE '张%'; -- 查询的是姓张的所有员工
  SELECT * FROM emp WHERE ename LIKE '%阿%';-- 查询姓名中间带有阿字的员工
  SELECT * FROM emp WHERE ename LIKE '%'; -- 条件不存在，如果姓名为NULL的查询不出来
  ```

### 2.3.3 分组查询(GROUP BY)

* GROUP BY 将分组字段结果中相同内容作为一组，并且返回每组的第一条数据，一般分组会跟**聚合函数**一起使用

> ==WHERE 和 HAVING 区别：==
>
> * **WHERE：分组前过滤数据；HAVING：分组后过滤数据**
> * **WHERE后不可以使用聚合函数，HAVING后可以使用聚合函数**

- **记录使用某一列进行<span style="color:red;font-weight:bold">分组(GROUP BY)</span>，然后查询组信息**

  ```mysql
  SELECT deptno, COUNT(*) FROM emp GROUP BY deptno; -- deptno分组，查询部门编号和每个部门记录
  SELECT job, MAX(SAL) FROM emp GROUP BY job; -- 使用job分组，查询每种工作的最高工资
  ```

- <span style="color:red;font-weight:bold">组条件(HAVING)</span>

  ```mysql
  SELECT deptno, COUNT(*) FROM emp GROUP BY deptno HAVING COUNT(*) > 3;-- 以部门分组...。条件为记录数大于3
  ```

### 2.3.4 聚合函数(列的纵向运算)

* 注意：聚合函数的计算，==**排除了null值**==。结果为单行单列的值。也可以修改聚合函数列名
  * 解决：**选择不包含null的列（主键）**；`IFNULL`函数

- <span style="color:red;font-weight:bold">COUNT</span>

  ```mysql
  SELECT COUNT(*) FROM emp; -- 计算emp表中所有列中只要有一列不为空的记录的行数，一般用主键代替*更好
  SELECT COUNT(comm) FROM emp; -- 计算emp表中comm列不为NULL的记录的行数
  ```

- <span style="color:red;font-weight:bold">MAX</span>

  ```mysql
  SELECT MAX(sal) FROM emp; -- 查询最高工资
  ```

- <span style="color:red;font-weight:bold">MIN</span>

  ```mysql
  SELECT MIN(sal) FROM emp; -- 查询最低工资
  ```

- <span style="color:red;font-weight:bold">SUM</span>

  ```mysql
  SELECT SUM(sal) FROM emp; -- 查询工资合计
  ```

- <span style="color:red;font-weight:bold">AVG</span>

  ```mysql
  SELECT AVG(sal) FROM emp; -- 查询平均工资
  ```



### 2.3.5 排序(ORDER BY...*SC)

* <span style="color:red;font-weight:bold">升序(ORDER BY...ASC)</span>

  ```mysql
  SELECT * FROM WHERE emp ORDER BY sal ASC; -- ASC可以省略
  ```

* <span style="color:red;font-weight:bold">降序(ORDER BY...DESC)</span>

  ```mysql
  SELECT * FROM WHERE emp ORDER BY comm DESC; -- DESC不能省略
  ```

* 多字段作为排序条件

  ```mysql
  SELECT * FROM WHERE emp ORDER BY sal ASC, comm DESC; -- 按sal升序排，如果sal相同，按comm降序排
  ```

###  2.3.6 分页查询(LIMIT)

* MySQL的方言LIMIT用来限定查询结果的**起始行**，以及**总行数**：**`开始的索引 = (当前页-1) * 每页记录数`**

  第一个参数是0可以省略；最后不足总行数的话，有多少显示多少。

  ```mysql
  /*1. 一页的记录数：10行;2. 查询第3页*/
  select * from emp limit 20, 10;
  ```





## 2.4 DCL

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


# 3 约束

- 对表中的数据进行限制，保证数据的正确性、有效性和完整性。用来**约束列**

## 3.1 主键约束(PRIMARY KEY)

- 特点：**非空**、**唯一**、**被外键引用**。一般设置id为主键，不是业务字段（身份证等不建议做主键），一张表只能有一个字段设置

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

### 3.1.1 主键自增长(AUTO_INCREMENT)

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

## 3.2 非空约束(NOT NULL)

- 因为某些列**不能设置为NULL**值，所以可以对列添加非空约束。可以设置**默认约束**

  - 创建表时设置

    ```mysql
    CREATE TABLE stu(
    		sid INT PRIMARY KEY AUTO_INCREMENT,
    		sname VARCHAR(20) NOT NULL DEFAULT `王八蛋`, -- 默认值，不设置时使用默认值
    );
    ```

  * 修改表时设置

    ```mysql
    ALTER TABLE stu MODIFY name VARCHAR(20) NOT NULL; -- 或用CHANGE，需添加新名称
    ```

  * 删除

    ```mysql
    ALTER TABLE stu MODIFY name VARCHAR(20); -- 或用CHANGE，需添加新名称
    ```

## 3.3 唯一约束(UNIQUE)

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


## 3.4 外键约束(FOREIGN KEY)

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

* **外键的级联（谨慎使用）**：在修改和删除主表的主键时，同时更新或删除副表的外键值，称为级联操作。

  ```mysql
  create talbe emp (
  	...
      deptno int,
      CONSTRAINT fk_emp_dpet FOREIGN KEY(dpetno) REFERENCES dpet(dpetno) on update cascade on delete cascade 
  );
  ```




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

* ==在多的一方（从表）建立**外键**，指向一的一方（主表）的**主键**==

### 4.4.3 多对多关系

- ==需要使用**中间表**，在中间表中使用两个**外键**，分别引用其他两个表的主键==

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

* 第一范式（1NF）：==**每一列都是不可分割的原子数据项**==

![](F:\GitHub\Studying\DataBase\images\1NF.PNG)

### 4.2.2 第二范式（2NF）

* 第二范式（2NF）：在1NF的基础上==**非主属性必须完全依赖于码**（在1NF基础上<u>消除非主属性对主码的**部分函数依赖**</u>）==

  > ==**码**：如果在一张表中，**一个属性(属性组)，被其他所有属性所完全依赖**==，则称这个属性(属性组)为该表的码
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

![](F:\GitHub\Studying\DataBase\images\2NF.PNG)

### 4.2.3 第三范式（3NF）

* 第三范式（3NF）：在2NF基础上==**任何非主属性不依赖于其它非主属性**（在2NF基础上<u>消除非主属性对于码的**传递函数依赖**</u>）==

![](F:\GitHub\Studying\DataBase\images\3NF.PNG)







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



# 5 多表查询

> 合并结果集(了解)
>
>  * 要求被合并的表中（结果集），列的类型和列数相同，局限性大
>      * UNION，去除重复行
>      * UNION ALL，不去除重复行
>

* 需要==**去除笛卡尔积中无用的数据**===

## 5.1 连接查询

### 5.1.1 内连接(JOIN...ON)

* 内连接查询出的所有记录==**都满足条件**==

* ==显式内连接 (**[INNER] JOIN...ON**)==

    ```mysql
    SELECT * FROM 表1 (AS) 别名1 INNER JOIN 表2 别名2 ON 别名1.xx=别名2.xx -- 还有不等关系
    ```

* 隐式内连接( , WHERE)

    ```mysql
    SELECT * FROM 表1 别名1, 表2 别名2 WHERE 别名1.xx=别名2.xx -- 还有不等关系
    ```


### 5.1.2 外连接(* JOIN...ON)

* ==**左外**(**LEFT [OUTER] JOIN...ON**)==

  **左表记录**无论是否满足条件**都会查询出**，而**右表满足条件才能查出**。左表中不满条件的记录，右表补**NULL**

  ```mysql
  SELECT * FROM 表1 别名1 LEFT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx -- 还有不等关系
  ```

* ==**右外**(**RIGHT [OUTER] JOIN...ON**)==

  **右表记录**无论是否满足条件**都会查询出**，而**左表满足条件才能查出**。右表中不满条件的记录，左表补**NULL**

  ```mysql
  SELECT * FROM 表1 别名1 RIGHT OUTER JOIN 表2 别名2 ON 别名1.xx=别名2.xx -- 还有不等关系
  ```


> 全连接：可以使用UNION来完成全链接

## 5.2 子查询

* 有查询的嵌套，内部的查询称为子查询  （看SELECT关键字的个数！）

* ==**FROM**后作为**表**存在==，或用普通内连接添加多个条件来查询

  - **多行多列**

    ```mysql
    SELECT * FROM 表1 别名1 , (SELECT ....) 别名2 WHERE 条件
    ```

* ==**WHERE**后作为**条件**存在==

  * **单行单列**：运算符为 >、<、>=、<=、=、<>

    ```mysql
    SELECT * FROM 表1 别名1 WHERE 列1 [=、>、<、>=、<=、!=] (SELECT 列 FROM 表2 别名2 WHERE 条件)
    ```

  * **多行单列**：运算符为 IN、ALL、ANY

    ```mysql
    SELECT * FROM 表1 别名1 WHERE 列1 [IN, ALL, ANY] (SELECT 列 FROM 表2 别名2 WHERE 条件)
    ```




## 5.3 DQL练习

### 1 单表查询

* 找出奖金高于工资60%的员工

  ```mysql
  SELECT * 
  FROM emp
  WHERE COMM>SAL*0.6;
  ```

* 找出部门编号10所有经理，部门编号20中所有销售员，即不是经理又不是销售员但其工资大或等于20000的...

  ```mysql
  select * 
  from emp
  where (DEPTNO=10 AND JOB='经理') OR (DEPTNO=20 AND JOB='销售员') OR (JOB NOT IN('经理','销售员') AND SAL>=20000);
  ```

* 查询2000年入职的员工

  ```mysql
  select * 
  from emp
  WHERE HIREDATE LIKE '2002%';
  ```

* 查询所有员工详细信息，用工资降序排序，如果工资相同使用入职日期升序排序

  ```mysql
  select * 
  from emp
  order by SAL desc,HIREDATE ASC;
  ```

* 查询每种工作的最高工资、最低工资、平均工资、人数

  ```mysql
  select JOB,AVG(SAL) 平均工资,MAX(SAL) AS 最高工资,MIN(SAL)最低工资,COUNT(*) 人数 
  from emp
  group by JOB;
  ```

* 有奖金的工种

  ```mysql
  select JOB 
  FROM emp
  where COMM IS NOT NULL
  group by JOB;
  ```

* 显示非销售人员工作名称以及从事同一工作雇员的月工资的总和，并且要满足从事同一工作的雇员的月工资合计大于50000，输出结果按月工资的合计升序排列

  ```mysql
  select JOB,SUM(SAL) 
  FROM EMP
  GROUP BY JOB
  HAVING SUM(SAL)>50000 AND JOB<>'销售员'
  ORDER BY SUM(SAL) ASC;
  ```

### 2 多表查询

* 查出至少有一个员工的部门。显示部门编号、部门名称、部门位置、部门人数

  ```mysql
  SELECT d.deptno,d.dname,d.loc,t.cnt
  FROM dept d 
  	JOIN (SELECT emp.deptno, COUNT(*) cnt FROM emp GROUP BY emp.deptno HAVING COUNT(*)>1) t 
  	ON d.deptno = t.deptno;
  ```

* 列出薪金比关羽高的所有员工

  ```mysql
  SELECT * 
  FROM emp
  WHERE sal>(SELECT sal FROM emp WHERE ename='关羽')
  ```

* 列出所有员工的姓名及其直接上级的姓名

  ```mysql
  SELECT e.ename, IFNULL(m.ename, 'BOSS') 领导
  FROM emp e LEFT JOIN emp m
  ON e.mgr=m.empno;
  ```

* 列出受雇日期早于直接上级的所有员工的编号、姓名、部门名称(**三张表**)

  ```mysql
  SELECT e.empno, e.ename, d.dname
  FROM emp e 
  	JOIN emp m ON e.mgr=m.empno 
  	JOIN dept d ON e.deptno=d.deptno
  WHERE e.hiredate<m.hiredate;
  ```

* 列出薪金高于公司平均薪金的所有员工信息，所在部门名称，上级领导，工资等级(**四张表**)

  ```mysql
  SELECT emp.*,dept.dname,e2.ename,salgrade.grade
  FROM emp  
  	LEFT JOIN dept ON emp.deptno = dept.deptno 
  	LEFT JOIN emp e2  ON emp.mgr = e2.empno
  	LEFT JOIN salgrade ON emp.sal BETWEEN salgrade.losal AND salgrade.hisal 
  WHERE emp.sal>(SELECT AVG(emp.sal) FROM emp);
  ```

* ==**查出年份、利润、年度增长比**==

  ```mysql
  SELECT E1.*,IFNULL(CONCAT((E1.zz-E2.zz)/E2.zz*100,'%'),0)
  FROM lirun E1 
  	JOIN lirun E2 ON E1.`year`=E2.`year`+1
  ORDER BY E1.`year` ASC;
  ```





# 6 事务(transaction)

- 如果一个包含多个步骤的业务操作，被事务管理，那么这些操作**要么同时成功，要么同时失败**。
- MySQL中操作事务
  - 开启事务：**`start transaction`**
  - 结束事务：**`commit`**（没有问题则提交）或**`rollback`**（有问题则回滚）
- **MySQL数据库中事务默认自动提交**
  - 事务提交的两种方式：
    - 自动提交：
      - **mysql就是自动提交的**
      - **一条DML(增删改)**语句会自动提交一次事务。
    - 手动提交：
      - **Oracle 数据库默认是手动提交事务**
      - 需要**先开启事务，再提交**
  - 修改事务的默认提交方式：
    - 查看事务的默认提交方式(MySQL命令)：`SELECT @@autocommit;` -- 1 代表自动提交；0 代表手动提交
    - 修改默认提交方式： `set @@autocommit = 0;`

## ==6.1 事务的四大特性（ACID）==

- **原子性**（Atomicity）：事务中所有操作是不可再分割的原子单位。事务中所有操作要么全部执行成功，要么全部执行失败。
- ***一致性**（Consistency）：事务执行后，数据库状态保持一致。如转账业务，无论事务执行成功与否，参与转账的两个账号余额之和应该是不变的。
- **隔离性**（Isolation）：隔离性是指在并发操作中，不同事务之间应该隔离开来，使每个并发中的事务不会相互干扰。
- **持久性**（Durability）：一旦事务提交成功，它对数据库的改变必须是永久的，即使出现系统故障。

## 6.2 事务的隔离级别（了解）

* 概念：如果多个事务操作同一批数据，则会引发一些问题，设置不同的隔离级别就可以解决这些问题。

- 并发事务**问题**

  - **脏读**：一个事务读取到了另一个事务中**尚未提交的数据** 
  - **不可重复读**：一个事务中**两次读取的数据==内容==不一致**，要求的是一个事务中多次读取时数据是一致的，这是事务update 时引发的问题 
  - **幻读**（虚读）：一个事务中**两次读取的数据的==数量==不一致**，要求在一个事务多次读取的数据的数量是一致 的，这是 insert 或 delete 时引发的问题。

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

- 演示：通过转账示例，每次设置不同的隔离级别，甲乙两方分别开启事务，可查看到不同的结果。MySQL中不能演示幻读。

  - read uncommitted：在一次事务中甲方执行转账操作未提交时，乙方也可查询到账情况（脏读、不可重复读）

  - read committed：在一次事务中甲方执行转账操作只有提交后，乙方才能查询到账情况，两次结果不同（不可重复读）

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



# 7 JDBC

* JDBC（Java DataBase Connectivity）就是Java数据库连接，说白了就是用Java语言来操作数据库
* **本质**：其实是官方（sun公司）定义的一套==**操作所有关系型数据库的规则（接口）**==。各个数据库厂商去实现这套接口，提供**数据库驱动jar包**。我们可以使用这套接口（JDBC）编程，真正执行的代码是驱动jar包中的实现类。

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
      	//在使用6以上版本时，会碰到时区异常以及SSl警告，需要在url添加：?serverTimezone=UTC&useSSL=false
      
  
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
> //ds.setUrl("jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC&useSSL=false");
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
          <property name="jdbcUrl">jdbc:mysql://localhost:3306/mydb?serverTimezone=UTC</property>
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
  url=jdbc:mysql://127.0.0.1:3306/db3?serverTimezone=UTC&useSSL=false
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
   
       public static DataSource getDataSource(){
           return dataSource;
       }
   }
   ```


# 8 Spring JDBC

Spring框架对JDBC的简单封装，提供了一个**JdbcTemplate**对象简化JDBC的开发

* **导包**：5个jar包

* **创建JdbcTemplate对象**：依赖于数据源**`DataSource`**，`JdbcTemplate template = new JdbcTemplate(dateSource);`

* **调用JdbcTemplate的方法**来完成CRUD的操作

  * `update()`：执行**DML**语句。增、删、改语句
  * `query()`：查询结果，将结果封装为==**JavaBean对象的List集合**==。JavaBean属性使用**包装类型**最好，可以存储null。
    - query的参数：RowMapper函数式接口
      - 一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装
      - **`new BeanPropertyRowMapper<>(类型.class)`**（不用在尖括号中添加泛型）
  * `queryForObject()`：查询结果，将结果封装为==**对象**==，一般用于聚合函数的查询
  * `queryForMap()`：查询并将结果的列名作为key(String)、值作为value(Object)封装为一个==**Map**==集合
    * 注意：这个方法查询的结果集长度只能是1
  * `queryForList()`：查询结果将结果集封装为==**List**==集合
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
















# 10 其他

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



## 10.2 BaseServlet

* 缘由：
  * 我们希望在**一个Servlet中可以有多个请求处理方法**！

  * 客户端发送请求时，必须**多给出一个参数，用来说明要调用的方法**
    请求处理方法的签名必须与service相同，即返回值和参数，以及声明的异常都相同！

    **客户端必须传递名为method的参数**！

  ```java
  public class BaseServlet extends HttpServlet {
  	@Override
  	public void service(HttpServletRequest request, HttpServletResponse response)
  			throws ServletException, IOException {
  		response.setContentType("text/html;charset=UTF-8");//处理响应编码
  		request.setCharacterEncoding("UTF-8");//处理请求编码
  		
  		//1. 获取客户端发送的method参数，它是用户想调用的方法 
  		String methodName = request.getParameter("method");
  		Method method = null;
  		//2. 通过方法名称获取Method对象(反射)
  		try {
  			method = this.getClass().getMethod(methodName,
  					HttpServletRequest.class, HttpServletResponse.class);
  		} catch (Exception e) {
  			throw new RuntimeException("您要调用的方法："+methodName + "它不存在！", e);
  		}
  		//3. 通过method对象来调用它
  		try {
              //根据方法的返回值来决定转发、重定向、下载等等操作
  			String result = (String)method.invoke(this, request, response);
  			if(result != null && !result.trim().isEmpty()) {//如果请求处理方法返回不为空
  				int index = result.indexOf(":");//获取第一个冒号的位置
  				if(index == -1) {//如果没有冒号，使用转发
  					request.getRequestDispatcher(result).forward(request, response);
  				} else {//如果存在冒号
  					String start = result.substring(0, index);//分割出前缀
  					String path = result.substring(index + 1);//分割出路径
  					if(start.equals("f")) {//前缀为f表示转发
  					  request.getRequestDispatcher(path).forward(request, response);
  					} else if(start.equals("r")) {//前缀为r表示重定向（需要项目名）
  				      response.sendRedirect(request.getContextPath() + path);
  					}
  				}
  			}
  		} catch (Exception e) {
  			throw new RuntimeException(e);
  		}
  	}
  }
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
  		if(con == null) throw new SQLException("没有事务不能提交！");
  		con.commit();//提交事务
  		con.close();//关闭连接
  		con = null;//表示事务结束！
  		tl.remove();
  	}
  	
  	//回滚事务
  	public static void rollbackTransaction() throws SQLException {
  		Connection con = tl.get();//获取当前线程的事务连接
  		if(con == null) throw new SQLException("没有事务不能回滚！");
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
      pstmt.setString(4, i % 2 == 0 ? "male" : "female");
      pstmt.addBatch();
  }
  pstmt.executeBatch();
  ```



# 8.3 装饰者模式(Wrapper)

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



# 11 分页

* 分页的优点：只查询一页，不用查询所有页！

  ```latex
  第N页/共M页　首页 上一页 1 2 3 4 5 6 7 8 9 10　下一页 尾页 [ ] go
  ```

## 11.1 分页数据

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

# 14 客户关系管理系统

## 14.1 架构的搭建

1. 导入原型（只有页面，没有功能）



## 14.2 编码中问题

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
