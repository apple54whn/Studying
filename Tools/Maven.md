# 1 Maven简介

是apache下的一个开源项目，是纯java开发，用于对java项目进行**构建**、**依赖管理**

* **项目构建**：一个项目从编写源代码到编译、测试、运行、打包、部署、运行的过程
  * Maven项目构建过程：
    * 清理(**clean**)——>编译(**compile**)——>测试(test)——>报告——>打包(**package**)——>部署
    * 运行一个Maven工程(web项目)的命令：tomcat:run
* **依赖管理**：java项目所依赖jar包的规范化管理
  * Maven项目的jar包只需在**pom.xml**添加jar包的**坐标**，自动从Maven仓库下载jar包、运行



Maven文件夹目录

​	|——bin：mvn.bat(run方式运行项目)、mvnDebug.bat(debug方式运行项目)

​	|——boot：Maven运行需要类加载器

​	|——conf：**settings.xml**(整个Maven工具核心配置文件。配置本地仓库)

​	|——lib：Maven运行依赖的jar包



# 2 Maven项目工程目录

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





# 3 常用的Maven命令

* **tomcat:run**：在**当前项目的路径**中执行后，运行Maven工程项目

* mvn **clean**：删除target及其内容
* mvn **compile**：只编译了main目录的文件
* mvn **test**：只编译test目录文件并运行
* mvn **package**：根据项目类型打包
* mvn **install**：把项目发布到本地仓库，web项目不用(因为是war包)，一般java项目用来打jar包



# 4 Maven的生命周期（了解）

compile——>test——>package——>install——deploy（按顺序）

* Clean生命周期：clean命令
* Default生命周期：上面4个
* Site生命周期：mvn site生成项目的站点文档

命令和生命周期的阶段的关系：不同的生命周期的命令可以同时执行(mvn clean package)



# 5 依赖管理

* 在pom.xml中，添加dependency标签，并填写坐标。
  * 可以在Maven repository网站上查找
  * 在本地重建索引，以索引方式搜索

* **依赖范围**（A依赖B，需要在A的pom.xml文件中添加B的坐标，同时指定依赖范围）
  * Compile：编译范围，指A在编译时依赖B，为默认依赖范围。在编译、测试、运行、打包时需要
    * 如：struts2-core
  * **Provided**：依赖只有在当JDK或者一个容器已经提供该依赖后才使用，在编译、测试时需要
    * 如：jsp-api.jar   servlet-api.jar
  * Runtime：在测试、运行、打包时需要
    * 如：数据库驱动包
  * Test：只测试时需要
    * 如：JUnit.jar

* 依赖传递：只添加一个依赖，有关这个依赖的依赖都添加过来了
* 解决依赖冲突：
  * 调解：
    * 第一声明者优先原则（先声明的用）
    * 路径近者优先原则（A依赖spring-bean1，A依赖B依赖spring-bean2，则应该用1）
  * 排除依赖：
  * 锁定版本：