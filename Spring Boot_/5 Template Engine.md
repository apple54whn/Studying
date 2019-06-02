# 1 FreeMarker

freemarker是一个用Java开发的模板引擎。模板+数据模型=输出

![1550741167891](../Spring%20Boot_/images/1550741167891.png)

Freemaker并不关心数据的来源，只是根据模板的内容，将数据模型在模板中显示并输出文件（通常为html，也可以生成其它格式的文本文件）

数据模型：数据模型在java中可以是基本类型也可以List、Map、Pojo等复杂类型

## 1 入门

创建Spring Boot+Freemarker工程用于测试模板。依赖如下：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-freemarker</artifactId>
</dependency>
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
<!--HTTP客户端。类似HttpClient-->
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>okhttp</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-io</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
</dependency>
```

配置application.yml和 logback-spring.xml

```yaml
server:
  port: 8088 #服务端口

spring:
  application:
    name: test‐freemarker #指定服务名
  freemarker:
    cache: false #关闭模板缓存，方便测试
    settings:
      template_update_delay: 0 #检查模板更新延迟时间，设置为0表示立即检查，如果时间大于0会有缓存不方便进行模板测试
```

创建模型类

```java
@Data
public class Student {
    private String name;//姓名
    private int age;//年龄
    private Date birthday;//生日
    private Float money;//钱包
    private List<Student> friends;//朋友列表
    private Student bestFriend;//最好的朋友
}
```

**创建模板**：在`src/main/resources/templates`下存放模板`.ftl`

```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf‐8">
        <title>Hello World!</title>
    </head>
    <body>
        Hello ${name}!
    </body>
</html>
```

启动类略



## 2 FreeMaker基础

定义模型数据

```java
@RequestMapping("/test1")
    public String freemarker(Map<String, Object> map) {

        //向数据模型放数据
        //1.String
        map.put("name", "黑马程序员");
        Student stu1 = new Student();
        stu1.setName("小明");
        stu1.setAge(18);
        stu1.setMoney(1000.86F);
        stu1.setBirthday(new Date());
        Student stu2 = new Student();
        stu2.setName("小红");
        stu2.setMoney(200.1F);
        stu2.setAge(19);
        stu2.setBirthday(new Date());
        List<Student> friends = new ArrayList<>();
        friends.add(stu1);
        stu2.setFriends(friends);
        stu2.setBestFriend(stu1);
        //2.List
        List<Student> stus = new ArrayList<>();
        stus.add(stu1);
        stus.add(stu2);

        //3.对象
        map.put("stus", stus);

        //4.Map
        HashMap<String, Student> stuMap = new HashMap<>();
        stuMap.put("stu1", stu1);
        stuMap.put("stu2", stu2);
        map.put("stu1", stu1);

        //向数据模型放数据
        map.put("stuMap", stuMap);

        //返回模板文件名称
        return "test1";
    }
```



### 2.1 核心指令

```
1、注释，即<#‐‐和‐‐>，介于其之间的内容会被freemarker忽略
2、插值（Interpolation）：即${..}部分,freemarker会用真实的值代替${..}
3、FTL指令：和HTML标记类似，名字前加#予以区分，Freemarker会解析标签中的表达式或逻辑。
4、文本，仅文本信息，这些不是freemarker的注释、插值、FTL指令的内容会被freemarker忽略解析，直接输出内容。
```

- assign：此指令用于在页面上定义一个变量

  - 定义简单类型

    ```
    <#assign linkman="周先生">
    联系人：${linkman}
    ```

  - 定义对象类型

    ```
    <#assign info={"mobile":"13301231212",'address':'北京市昌平区王府街'} >
    电话：${info.mobile}  地址：${info.address}
    ```

- include：此指令用于模板文件的嵌套

  - 创建模板文件head.ftl

  - 我们修改test.ftl，在模板文件中使用include指令引入刚才我们建立的模板

    ```
    <#include "head.ftl">
    ```

- **#if**：判断（可以用==、=）

  ```
  <#if success==true>
  你已通过实名认证
  <#else>  
  你未通过实名认证
  </#if>
  ```

  在代码中对str变量赋值

  ```java
  map.put("success", true);
  ```

- **#list**：遍历（xx_index为循环的下标，从0开始）

  ```
  <#list goodsList as goods>
  	${goods_index+1} 商品名称： ${goods.name} 价格：${goods.price}<br>
  </#list>
  ```

  

### 2.3 内建函数

内建函数语法格式： `变量?函数名称`

- 获取集合大小

  ```
  共  ${goodsList?size}  条记录
  ```

- 转换JSON字符串为对象

  ```
  <#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
  <#assign data=text?eval />
  开户行：${data.bank}  账号：${data.account}
  ```

- 日期格式化

  ```
  当前日期：${today?date} <br>
  当前时间：${today?time} <br>   
  当前日期+时间：${today?datetime} <br>        
  日期格式化：  ${today?string("yyyy年MM月")}
  ```

- 数字转换为字符串：数字会以每三位一个分隔符显示，不需要这个分隔符，使用内建函数c

  ```
  累计积分：${point?c}
  ```

### 2.4 空值处理

**空值处理运算符**：在模板中使用了变量但是在代码中没有对变量赋值，那么运行生成时会抛出异常

- 判断某变量**是否存在**:“`??`”。如果该变量存在,返回true,否则返回false

  ```
  <#if aaa??>
  aaa变量存在
  <#else>
  aaa变量不存在
  </#if>
  ```

- **缺失变量默认值**:“`!`”：使用!对null值做转换处理，值可以自定义。**如果是嵌套对象则建议使用（）括起来，表示其中任意为null都做处理**

  ```
  ${aaa!'-'}
   <#-- 当aaa为null则返回！后边的内容-  -->
  ```

### 2.5 运算符

- 算数运算符：`+, - , * , / , %`
- 逻辑运算符：`&&、||、!`，只能作用于布尔值
- 比较运算符
  - `=`或者`==`:判断两个值是否相等. 
  - `!=`:判断两个值是否不等. 
  - `>`或者`gt`:判断左边值是否大于右边值（有时需要括号括起来，否则和标签的小于号对应上，下面的同理）
  - `>=`或者`gte`:判断左边值是否大于等于右边值 
  - `<`或者`lt`:判断左边值是否小于右边值 
  - `<=`或者`lte`:判断左边值是否小于等于右边值 

**注意**:  =和!=可以用于字符串,数值和日期来比较是否相等,但=和!=两边必须是相同类型的值,否则会产生错误,而且FreeMarker是精确比较,"x","x ","X"是不等的.其它的运行符可以作用于数字和日期,但不能作用于字符串,大部分的时候,使用gt等字母运算符代替>会有更好的效果,因为 FreeMarker会把>解释成FTL标签的结束字符,当然,也可以使用括号来避免这种情况,如:<#if (x>y)> 

```ftl
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Hello World!</title>
</head>
<body>
Hello ${name}!
<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
    <#list stus as stu>
        <tr>
            <td>${stu_index + 1}</td>
            <td <#if stu.name =='小明'>style="background:red;"</#if>>${stu.name}</td>
            <td>${stu.age}</td>
            <td >${stu.money}</td>
        </tr>
    </#list>

</table>
<br/><br/>
输出stu1的学生信息：<br/>
姓名：${stuMap['stu1'].name}<br/>
年龄：${stuMap['stu1'].age}<br/>
输出stu1的学生信息：<br/>
姓名：${stu1.name}<br/>
年龄：${stu1.age}<br/>
遍历输出两个学生信息：<br/>
<table>
    <tr>
        <td>序号</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>钱包</td>
    </tr>
<#list stuMap?keys as k>
<tr>
    <td>${k_index + 1}</td>
    <td>${stuMap[k].name}</td>
    <td>${stuMap[k].age}</td>
    <td >${stuMap[k].money}</td>
</tr>
</#list>
</table>
</br>
<table>
    <tr>
        <td>姓名</td>
        <td>年龄</td>
        <td>出生日期</td>
        <td>钱包</td>
        <td>最好的朋友</td>
        <td>朋友个数</td>
        <td>朋友列表</td>
    </tr>
    <#if stus??>
    <#list stus as stu>
        <tr>
            <td>${stu.name!''}</td>
            <td>${stu.age}</td>
            <td>${(stu.birthday?date)!''}</td>
            <td>${stu.money}</td>
            <td>${(stu.bestFriend.name)!''}</td>
            <td>${(stu.friends?size)!0}</td>
            <td>
                <#if stu.friends??>
                <#list stu.friends as firend>
                    ${firend.name!''}<br/>
                </#list>
                </#if>
            </td>
        </tr>
    </#list>
    </#if>

</table>
<br/>
<#assign text="{'bank':'工商银行','account':'10101920201920212'}" />
<#assign data=text?eval />
开户行：${data.bank}  账号：${data.account}

</body>
</html>
```



## 3 静态化测试

### 3.1 使用模板文件静态化

需要将上面的resources目录复制到test中，配置文件可以删掉

```java
@Test
public void generateHtml() throws IOException, TemplateException {
    //1.创建一个配置对象
    Configuration configuration = new Configuration(Configuration.getVersion());
    //2.设置模板所在目录
    String classpath = this.getClass().getResource("/").getPath();
    configuration.setDirectoryForTemplateLoading(new File(classpath+"/templates/"));
    //3.设置字符集
    configuration.setDefaultEncoding("UTF-8");
    //4.获取模板对象
    Template template = configuration.getTemplate("test1.ftl");
    //5.创建数据模型
    Map map = this.getMap();//之前写的数据模型
    //7.输出
    template.process(map,new FileWriter("d:\\test1.html"));
}
```



### 3.2 使用模板字符串静态化

模板文件可能是用户输入的字符串（或者通过模板文件获取字符串），就可以这样来做。比较灵活

```java
@Test
public void generateHtml2() throws IOException, TemplateException {
    //1.创建一个配置对象
    Configuration configuration = new Configuration(Configuration.getVersion());
    //2.模板字符串
    String templateString="" +
        "<html>\n" +
        " <head></head>\n" +
        " <body>\n" +
        " 名称：${name}\n" +
        " </body>\n" +
        "</html>";
    //3.字符串模板加载器加载模板字符串
    StringTemplateLoader loader = new StringTemplateLoader();
    loader.putTemplate("template",templateString);
    //4.配置字符串模板加载器
    configuration.setTemplateLoader(loader);
    //5.获取模板对象
    Template template = configuration.getTemplate("template", "UTF-8");
    //6.创建数据模型
    Map map = this.getMap();//之前写的数据模型
    //7.输出
    template.process(map,new FileWriter("d:\\test2.html"));
}
```





# 2 Thymeleaf

