[TOC]



# 1 XML

## 1.1 表单提交方式

- form与submit提交

- form与button提交

  ```javascript
  var form1 = function(){
      var form1 = document.getElementById("form1");
      form1.action = "hello.html";
      form1.submit();
  }
  
  ```

- 使用超链接提交

  ```html
  <a href="hello.html?username=123456">使用超链接提交</a>
  ```

## 1.2 XML简介（了解）

- eXtensible Markup Language：**可扩展标记型语言**。
  - 标记型语言：html是标记型语言；xml也使用标签来操作
  - 可扩展：html里面的标签是固定，每个标签都有特定的含义；标签可以自己定义,可以写中文的标签 `<person></person`、`<猫></猫>`
  - html是用于显示数据，xml也可以显示数据，xml主要功能，为了**存储传输数据**
  - 使用都是1.0版本，（1.1版本不能向下兼容）
- **XML的应用**
  - **不同的系统之间传输数据**
  - 用来表示生活中**有关系的数据**
  - 经常用在**配置文件**

## 1.3 **XML的语法**

### 1.3.1 **xml的文档声明**

- 必须**第一行第一列**

  ```xml
  <?xml version="1.0" encoding="utf-8"?>
  ```

- 属性：version（版本）、encoding（gbk  utf-8  iso8859-1(不包含中文)）

  	    standalone：是否需要依赖其他文件 yes/no

- **xml的中文乱码问题解决**：保存时候的编码和设置打开时候的编码一致

### 1.3.2 **定义元素（标签）**

- 标签定义有开始必须要有结束`<person></person>`
- 标签没有内容，可以在标签内结束`<aa/>`
- 标签可以嵌套，必须要合理嵌套 `<aa><bb></bb></aa>`
- 一个xml中，只能有**一个根标签**，其他标签都是这个标签下面的标签
- 在xml中把**空格和换行都当成内容**来解析，与HTML区别
- xml标签**可以是中文**
- xml中标签的名称规则
  1. xml代码**区分大小写**
  2. xml的标签不能以数字和下划线(_)开头
  3. xml的标签不能以xml、XML、Xml等开头
  4. xml的标签不能包含空格和冒号

### 1.3.3 **定义属性**

```
<person id1="aaa" id2="bbb"></person>

```

- 属性定义的要求：
  1. 一个标签上可以有多个属性
  2. 属性名称不能相同
  3. 属性名称和属性值之间使用= ，属性值使用引号包起来 （可以是单引号，也可以是双引号 ）
  4. xml属性的名称规范和元素的名称规范一致

### 1.3.4 **注释**

```
<!-- xml的注释,不能嵌套 -->
```

### 1.3.5 **特殊字符（转义字符）**

- `&lt;` `&gt;` `&amp;`

### 1.3.6 CDATA区（了解）

- 解决多个字符都需要转义的操作 ，把特殊字符，当做文本内容，而不是标签
- 写法：`<![CDATA[内容]]>`，例如，`<![CDATA[ <b>if(a<b && b<c && d>f) {}</b> ]]>`

### 1.3.7 PI指令（处理指令）（了解）

- 可以在xml中设置样式，只能对英文标签名称起作用，对于中文的标签名称不起作用的
- 写法：`<?xml-stylesheet type="text/css" href="css的路径"?>`





## 1.4 XML的约束（dtd、schema看懂）

- 定义一个**person的xml文件**，只想要这个文件里面保存人的信息，比如name age等，但是如果在xml文件中
  写了一个**标签<猫>**，发现可以正常显示，因为符合语法规范。但是猫肯定不是人的信息，xml的标签是自定义的，需要技术来规定xml中只能出现的元素，这个时候需要约束
- **xml的约束的技术：** **dtd约束** 和 **schema约束** （看懂）

### 1.4.1 dtd约束

- 浏览器只负责校验xml的语法，不负责校验约束

- **步骤：**

  - 创建一个文件 **后缀名 .dtd**

  1. 看xml中有多少个元素 ，有几个元素，在dtd文件中写几个 <!ELEMENT>

  2. 判断元素是简单元素还是复杂元素

     - 复杂元素：有子元素的元素  **<!ELEMENT 元素名称 (子元素)>**
     - 简单元素：没有子元素  **<!ELEMENT 元素名称 (#PCDATA)>**

  3. 需要在xml文件中引入dtd文件

     1. 引入外部的dtd文件

        `<!DOCTYPE 根元素名称 SYSTEM "dtd文件的路径">`

     2. 使用内部的dtd文件

        ```
        <!DOCTYPE 根元素名称 [
        	<!ELEMENT person (name,age)>
        	<!ELEMENT name (#PCDATA)>
        	<!ELEMENT age (#PCDATA)>
        ]>
        
        ```

     3. 使用外部的dtd文件（网络上的dtd文件）

        `<!DOCTYPE 根元素 PUBLIC "DTD名称" "DTD文档的URL">`

### 1.4.2 **使用dtd定义元素**

- **语法**： **<!ELEMENT 元素名 约束>**
- **简单元素**：没有子元素的元素  **<!ELEMENT 元素名称 (#PCDATA)>**
  - (#PCDATA): 约束name是字符串类型
  - EMPTY : 元素为空（没有内容）`<sex></sex>`
  - ANY:任意
- **复杂元素**：**<!ELEMENT 元素名称 (子元素)>**，子元素只能出现一次
  - **表示子元素出现的次数**：
    - +：表示一次或者多次
    - ?：表示零次或者一次
    - *：表示零次或者多次
  - 子元素直接使用**逗号进行隔开**：表示**元素出现的顺序**
  - 子元素直接使用**|隔开**：表示元素**只能出现其中的任意一个**

### 1.4.3 使用dtd定义属性

- **语法：** 

  ```
  <!ATTLIST 元素名称
  			属性名称 属性类型 属性的约束
  >
  
  ```

- **属性类型:**

  - CDATA: 字符串
  - 枚举 ： 表示只能在一定的范围内出现值，但是只能每次出现其中的一个；(aa|bb|cc)，红绿灯
  - ID: 值只能是字母或者下划线开头

- **属性的约束:**

  - `#REQUIRED`：属性必须存在
  - `#IMPLIED`：属性可有可无
  - `#FIXED`: 表示一个固定值 #FIXED "AAA"
  - 直接值：不写属性，使用直接值；写了属性，使用设置那个值

### 1.4.4 实体的定义

- 语法： <!ENTITY 实体名称 "实体的值">
- 使用实体：&实体名称;（比如 &TEST;）
- 注意：定义实体需要写在内部dtd里面，如果写在外部的dtd里面，有某些浏览器下，内容得不到

### 1.4.5 schema约束

- schema**符合xml的语法**，xml语句

- 一个xml中可以**有多个schema**，多个schema使用**名称空间**区分（类似于java包名）

- dtd里面有PCDATA类型，但是在schema里面可以支持**更多的数据类型**

  比如 年龄 只能是整数，在schema可以直接定义一个整数类型

- schema**语法更加复杂**，schema目前不能替代dtd

### 1.4.6 schema快速入门

- 创建一个schema文件 后缀名是 **.xsd**，根节点 `<schema>`

- 在schema根节点有如下属性：

  - xmlns：表示当前xml文件是一个约束文件
  - targetNamespace：使用schema约束文件，直接通过这个地址引入约束文件
  - elementFormDefault="qualified"

- **步骤：**

  1. 看xml中有**多少个元素**，写几个`<element>`

  2. 看简单元素和复杂元素：

     - **复杂元素**

       ```
       <element name="person">
       	<complexType>
       		<sequence>
       			<element name="name" type="string"></element>
       			<element name="age" type="int"></element>
       		</sequence>
       	</complexType>
       </element>
       
       
       ```

     - **简单元素写在复杂元素里**，如上

  3. 在xml中引入约束文件

     ```
     xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"//表示xml是一个被约束文件
     xmlns="http://www.example.org/1"//是约束文档里面 targetNamespace
     xsi:schemaLocation="http://www.example.org/1 1.xsd">//targetNamespace 约束文档的地址路径
     
     ```

- 复杂元素指示器：

  - sequence：表示元素的出现的顺序
  - all: 元素只能出现一次
  - choice：元素只能出现其中的一个
  - maxOccurs="unbounded"： 表示元素的出现的次数
  - any:表示任意元素

- 可以约束属性：写在复杂元素里面，`</complexType>`之前

  ```
  <attribute name="id1" type="int" use="required"></attribute>
  	- name: 属性名称
  	- type：属性类型 int stirng
  	- use：属性是否必须出现 required
  
  ```




## * 1.5 XML的解析（dom、sax）

- js使用dom解析标记型文档？

  根据html的层级结构，在**内存中**分配一个树形结构，把html的标签，属性和文本都封装成对象
  document对象、element对象、属性对象、文本对象、Node节点对象

- **xml的解析方式**（技术）：**dom 和 sax**

- **DOM方式解析：**

  - 根据xml的层级结构在内存中分配一个树形结构，把xml的标签，属性和文本都封装成对象
  - 缺点：如果文件过大，造成内存溢出
  - 优点：很方便实现增删改操作

  ![](D:\Typora\photo\dom解析过程.PNG)

- **sax方式解析：**

  - 采用事件驱动，边读边解析。从上到下，一行一行的解析，解析到某一个对象，返回对象名称
  - 缺点：不能实现增删改操作
  - 优点：如果文件过大，不会造成内存溢出，方便实现查询操作

  ![](D:\Typora\photo\sax解析过程.PNG)

- 想要解析xml，首先需要解析器，不同的公司和组织通过api方式提供

  - sun公司提供了针对dom和sax解析器  **jaxp**
  - dom4j组织，针对dom和sax解析器    **dom4j**（**实际开发中使用**）
  - jdom组织，针对dom和sax解析器     jdom

## * 1.6 jaxp

- jaxp是javase的一部分，在jdk的javax.xml.parsers包里有四个类

### 1.6.1 **dom**

- **DocumentBuilderFactory**： 解析器工厂（抽象类）

  通过**newInstance()**方法 获取 DocumentBuilderFactory 的实例

- **DocumentBuilder**  : 解析器类（抽象类）

  从 DocumentBuilderFactory.**newDocumentBuilder()** 方法获取

  - **方法** ：**parse("xml路径")**，可以解析xml ，返回是 Document 整个文档，也是一个接口，父节点是Node，如果在document里面找不到想要的方法，到Node里面去找

    - **document里面方法：**

      - getElementsByTagName(String tagname) ：得到标签，返回集合 NodeList

      - createElement(String tagName)：创建标签

      - createTextNode(String data) ：创建文本

      - appendChild(Node newChild) ：把文本添加到标签下面

      - removeChild(Node oldChild) ：删除节点

      - getParentNode() ：获取父节点

      - getChildNodes()：获取子节点，java中没有兼容问题

      - getTextContent()：得到标签里面的内容

      - **NodeList list**：getLength()、item(int index)

        ```
        for(int i=0;i<list.getLength();i++) {
        	list.item(i)
        }
        
        ```

- **查询操作**

  查询xml中所有的name元素的值、第一个name的值

  ```
  //创建解析器工厂
  DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
  //根据解析器工厂创建解析器
  DocumentBuilder builder = builderFactory.newDocumentBuilder();
  //解析xml返回document
  Document document =  builder.parse("src\\cn\\itcast\\jaxp\\1.xml");
  
  
  //得到所有的name元素
  NodeList list = document.getElementsByTagName("name");
  //返回集合，遍历集合，得到每一个name元素
  for(int i=0;i<list.getLength();i++) {
  	Node node = list.item(i);
  	System.out.println(node.getTextContent());
  }
  //第一个name的值
  Node node = list.item(0);
  System.out.println(node.getTextContent());
  
  ```

- **添加节点（回写）**

  在第一个p1下面（末尾）添加 `<sex>nv</sex>`

  ```
  //......
  Node node = document.getElementsByTagName("p1").item(0);
  Element element =  document.createElement("sex");//创建sex标签
  Text text = document.createTextNode("nv");//创建文本
  element.appendChild(text);
  node.appendChild(element);
  
  //回写xml
  TransformerFactory factory = TransformerFactory.newInstance();
  Transformer transformer = factory.newTransformer();
  transformer.transform(new DOMSource(document), new StreamResult("1.xml"));
  
  ```

- **修改节点内容**

  ```
  //......
  Node node = document.getElementsByTagName("sex").item(0);
  node.setTextContent("man");
  //......
  
  ```

- **删除节点(通过父节点)**

  ```
  //......
  Node sex = document.getElementsByTagName("sex").item(0);
  Node parentnode = sex.getParentNode();
  parentnode.removeChild(sex);
  //......
  
  ```

- **遍历节点**

  ```
  //......
  private static void loop(Node node) {
  	if (node.getNodeType() == Node.ELEMENT_NODE) {//判断是否是标签
  		System.out.println(node.getNodeName());
  	}
  	NodeList list = node.getChildNodes();
  	for (int i = 0; i < list.getLength(); i++) {
  		loop(list.item(i));
  	}
  }
  
  ```


### 1.6.2 sax

- **SAXParserFactory**: 解析器工厂（抽象类）

  通过**newInstance()**方法获取

- **SAXParser**：解析器类（抽象类）

  通过SAXParserFactory.**newSAXParser()** 方法获得

  - **方法**：**parse(File f, DefaultHandler dh)**    参数：xml的路径；事件处理器
    - **DefaultHandler**：创建一个类，继承事件处理器的类，重写里面的三个方法

- **查询操作**（sax方式不能实现增删改操作）

  1. **获取到所有的name元素的值**
     - 定义一个成员变量 **flag= false**
     - 判断开始方法是否是name元素，如果是name元素，把flag值设置成true
     - 如果flag值是true，在characters方法里面打印内容
     - 当执行到结束方法时候，把flag值设置成false
  2. **获取第一个name元素的值**
     - 定义一个成员变量 **index=1**
     - 在结束方法时候，index++
     - 在characters方法里面判断，判断flag=true 并且 index===1，再打印内容

## * 1.7 dom4j

- dom4j，是一个组织，针对xml解析，提供解析器 dom4j，不是javase的一部分，需要导入dom4j提供jar包

  1. 创建一个文件夹 lib
  2. 复制jar包到lib下面
  3. 右键点击jar包，build path -- add to build path
  4. 看到jar包，变成奶瓶样子，表示导入成功

- **得到document**：

  ```java
  SAXReader reader = new SAXReader();
    
  Document document = reader.read(url);
  ```

- **方法：**

  - document的父接口是Node，如果在document里面找不到想要的方法，到Node里面去找

  - document里面的方法 **getRootElement**() ：获取根节点 返回的是Element接口，父接口也是Node

  - element和Node里的方法：

    - **getParent**()：获取父节点

    - **addElement**()：添加标签

    - **element(qname)**：表示获取标签下面的**第一个子标签**，qname为标签名称

    - **elements(qname)**：获取标签下面是这个**名称的所有子标签**（一层），返回**List集合**

    - **elements()**：获取标签下面的**所有一层子标签**，，返回**List集合**

      **解析是从上到下解析**

### 1.7.1 **查询操作**

- 查询所有/第一个/第二个name元素里面的值(**getText**())：

  ```java
  SAXReader reader = new SAXReader();
  Document document = reader.read("1.xml");
  Element root = document.getRootElement();
  
  //所有name元素里面的值
  List<Element> p1 = root.elements("p1");
  for (Element e : p1) {
  	Element name = e.element("name");
  	System.out.println(name.getText());
  }
  //第一个name元素里面的值
  Element p1 = root.element("p1");
  Element name = p1.element("name");
  System.out.println(name.getText());
  //第二个name元素里面的值
  List<Element> p1 = root.elements("p1");
  Element name = p1.get(1).element("name");//List集合遍历中get()方法
  System.out.println(name.getText());
  
  ```

### 1.7.2 **添加操作**

- 在第一个p1标签**末尾添加**一个元素 `<sex>nv</sex>`

  ```java
  SAXReader reader = new SAXReader();
  Document document = reader.read("1.xml");
  Element root = document.getRootElement();
  
  Element p1 = root.element("p1");
  Element sex = p1.addElement("sex");
  sex.setText("woman");
  		
  //回写xml
  OutputFormat format = OutputFormat.createPrettyPrint();//带缩进格式的
  XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("1.xml"), format);
  xmlWriter.write(document);
  xmlWriter.close();
  
  ```

- 在**特定位置添加**元素。第一个p1下面的age标签之前添加 `<school>ecit.edu.cn</schlool>`

  ```java
  SAXReader reader = new SAXReader();
  Document document = reader.read("1.xml");
  Element root = document.getRootElement();
  			
  Element p1 = root.element("p1");
  List<Element> list = p1.elements();
  Element school = DocumentHelper.createElement("school");//DocumentHelper类创建element
  school.setText("ecit.edu.cn");
  list.add(1, school);//List集合的add方法添加
  	
  //回写xml
  OutputFormat format = OutputFormat.createPrettyPrint();
  XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("1.xml"), format);
  xmlWriter.write(document);
  xmlWriter.close();
  
  ```

  可以对得到document的操作和 回写xml的操作，封装成方法；把传递的文件路径，封装成一个常量；可以提高开发速度，可以提交代码可维护性；比如想要修改文件路径（名称），这个时候只需要修改常量的值就可以。

### 1.7.3 **修改节点**

- 修改第一个p1下面的age元素的值 `<age>30</age>`

  age.**setText**("value")

### 1.7.4 **删除节点**

- 删除第一个p1下面的`<school>ecit</school>`元素

  1. 利用elements方法返回**List**集合的**remove**(index)方法删除

     ```java
     Element p1 = root.element("p1");
     List<Element> list = p1.elements();
     list.remove(1);
     ```

  2. 利用父节点删除子节点

     ```java
     Element p1 = root.element("p1");
     Element school = p1.element("school");
     p1.remove(school);
     ```

### 1.7.5 **获取属性**

- 获取第一个p1里面的属性为id1的**值**

  ```java
  Element p1 = root.element("p1");
  System.out.println(p1.attributeValue("id1"));
  ```



### 1.7.6 XPath

- **可以直接获取到某个元素** 

  1. **/AAA/DDD/BBB**： 表示**一层一层**的，AAA下面 DDD下面的BBB

  2. **//BBB**：表示和这个名称相同，表示只要名称是BBB，都得到

  3. **/***：这层所有元素

     **//***：所有元素

  4. **BBB[1]**：　表示第一个BBB元素
     **BBB[last()]**：表示最后一个BBB元素

  5. **//BBB[@id]**： 表示只要BBB元素上面有id属性，都得到

  6. **//BBB[@id='b1']** 表示元素名称是BBB,在BBB上面有id属性，并且id的属性值是b1

- 使用dom4j支持XPath具体操作*

  默认的情况下，dom4j不支持，引入支持XPath的jar包，lib中使用 **jaxen**-1.1-beta-6.jar并导入

- **方法：**

  - **selectSingleNode**("XPath表达式")：获取一个节点
  - **selectNodes**("XPath表达式")：获取多个节点

- 案例：

  - **查询xml中所有name元素的值**

    ```java
    SAXReader reader = new SAXReader();
    Document document = reader.read("1.xml");
    List<Node> list = document.selectNodes("//name");
    for(Node node :list) {
    	System.out.println(node.getText());
    }
    ```

  - **获取第一个p1下面的name的值**

    ```java
    SAXReader reader = new SAXReader();
    Document document = reader.read("1.xml");
    Node p1Name = document.selectSingleNode("//p1[@id1='hehe']/name");
    System.out.println(p1Name.getText());
    ```

### 1.7.7 实现简单的学生管理系统





# 2 软件体系结构、Tomcat、Web应用、HTTP

## 2.1 软件体系结构

### 2.1.1 常见软件体系结构

* B/S：客户端/服务器（Client/Server），例如QQ
  * 需要编写服务器端程序，以及客户端程序
  * 缺点：软件更新时需要同时更新客户端和服务器端两端，比较麻烦
  * 优点：安全性比较好
* C/S：浏览器/服务器（Browser/Server）
  * 优点：只需要编写服务器端程序
  * 缺点：安全性较差

### 2.1.2 Web资源

* html：静态资源（浏览器可以看懂）
* JSP/Servlet：动态资源（需要先转换成HTML，再给浏览器看。，它**可以有变量**）
* 访问Web资源：URL=**协议名://域名:端口/路径**     **http的默认端口80**

### 2.1.3 Web服务器

* Web服务器的作用是接收客户端的请求，给客户端作出响应。对于JavaWeb程序，还需要有JSP/Servlet容器，JSP/Servlet容器的基本功能是把动态资源转换成静态资源 
* 我们需要使用的是**Web服务器**和**JSP/Servlet容器**，通常这两者会集于一身
* **Tomcat**（Apache）：当前应用最广的JavaWeb服务器 

## 2.2 Tomcat

* Tomcat**服务器**由Apache提供，开源免费。由于Sun和其他公司参与到了Tomcat的开发中，所以最新的JSP/Servlet规范总是能在Tomcat中体现出来，**是JSP/Servlet容器**。我们课程中使用Tomcat7。Tomcat7支持Servlet3.0，而Tomcat6只支持Servlet2.5！ 
  * 安装版：一台电脑上只能安装一个Tomcat
  * 解压版：无需安装，解压即可用，解压多少份都可以
* **启动和关闭Tomcat:**
  * 在启动Tomcat之前，我们必须要配置环境变量 JAVA_HOME、CATALANA_HOME(安装板Tomcat路径) 
  * 启动：%CATALANA_HOME%\bin中**startup.bat**
  * 关闭：%CATALANA_HOME%\bin中**shutdown.bat**
* 配置端口号：打开%CATALANA_HOME%\conf\server.xml文件，默认为8080
* 进入Tomcat主页：http://localhost:8080或http://127.0.0.1:8080
* **目录结构：**
  * bin：二进制可执行文件。startup.bat、shutdown.bat
  * conf：4个配置文件
    * server.xml：配置整个服务器信息。例如修改端口号，添加虚拟主机等
    * tomcatusers.xml：存储tomcat用户的文件，保存用户名及密码，以及用户的角色信息 
    * web.xml：部署描述符文件，这个文件中注册了很多MIME类型，即文档类型
    * context.xml：对所有应用的统一配置，通常我们不会去配置它
  * lib：类库，jar包
  * logs：日志
  * temp：临时文件
  * webapps：项目存放
  * work：运行时生成的文件，最终运行的文件都在这里。通过webapps中的项目生成的，java，class等

## 2.3 Web应用（有重点）

* **静态网站（*****）：

  * 在webapps目录下创建一个目录（命名必须不包含中文和空格），这个目录称之为**项目目录**
  * 在项目目录下创建一个**html文件**

* **动态网站（*****）：

  * |—webapps

    	|—hello   应用目录

    		|—index.html   **应用资源**，可以放在文件夹中
	​		
    		|—WEB-INF   大写，这个目录下的东西是**无法通过浏览器直接访问**
	​		
    			|—web.xml   应用程序的部署描述符文件，可以在该文件中对应用进行**配置** 
	​		
    			|—classes   存放**class文件**的目录
	​		
    			|—lib   存放**jar包**的目录

* 配置外部应用（了解）

  原来我们的项目放到webapps下，现在我放到外面，也希望tomcat可以找到它 

  * conf/server.xml：打开server.xml文件，找到`<Host>`元素，在其中添加`<Context>`元素 

    ```xml
    <Context path="hello1" docBase="C:/hello"/>   //应用名称和应用绝对地址
    ```

  * conf/catalana/localhost：在该目录下创建hello1.xml文件

    ```xml
    <Context docBase="C:/hello"/>     //文件名为应用名称
    ```

* 映射虚拟主机（了解）

  我们的目标是，在浏览器中输出：<http://www.hello.cn>就可以访问我们的项目

  1. 修改Tomcat端口号为80

  2. 在本机上可以解析域名为127.0.0.1，这需要修改C:\WINDOWS\system32\drivers\etc\hosts文件，添加对<http://www.hello.cn>和127.0.01的绑定关系

  3. 在server.xml文件中添加一个`<Host>`（主机）

     ```xml
     <Host name="www.hello.cn" appBase="F:/hello" unpackWARs="true" autoDeploy="true">
     </Host>
     //1.虚拟主机名 2.当前虚拟主机的应用程序存放目录 
     //3.目录下创建名为ROOT的应用，因为一个主机只可以有一个名为ROOT的应用,访问是可以不给出应用名称
     ```

* 理解server.xml（了解）

  |—Server：根元素，表示整个**服务器的配置信息**
    	|—Service：在Server中只能有一个，表示**服务** 
  ​    		|—Connector：可以有N个，它表示**连接**
  ​    		|—Engine：只能有一个，表示**引擎**，它是Service组件的核心
  ​      			|—Host：可以有N个，每个表示一个**虚拟主机** 
  ​        			|—Context：可以有N个，每个表示一个**应用**，外部应用必须配置





## * 2.4 HTTP协议（重点）

* HTTP协议是Hyper Text Transfer Protocol（超文本传输协议）的缩写,是用于从万维网（WWW:World Wide Web ）服务器传输超文本到本地浏览器的传送协议。
* HTTP是一个基于TCP/IP通信协议来传递数据（HTML 文件, 图片文件, 查询结果等） 
* **注意：**
  1. HTTP是**无连接**：无连接的含义是限制每次连接只处理一个请求
  2. HTTP是**媒体独立**的：这意味着，只要客户端和服务器知道如何处理的数据内容，任何类型的数据都可以通过HTTP发送。客户端以及服务器指定使用适合的**MIME**-type内容类型 
  3. HTTP是**无状态**：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快 

### * 2.4.1 请求协议

1. **请求行（request line）**

2. **请求头（header）**

3. **空行**

4. **请求体**


* **GET请求**

——————————————————————————————————————————————————

**GET /index.jsp HTTP/1.1**    //请求首行，GET请求，无请求体，请求服务器路径为/index.jsp，协议为1.1

**Host: localhost:8080** //请求的主机名为localhost、端口号为8080

**User-Agent**: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)

        Chrome/70.0.3514.0 Safari/537.36 //OS、浏览器相关信息

**Accept-Language**: zh-CN,zh;q=0.9,en-GB;q=0.8,en;q=0.7,ja-JP;q=0.6,ja;q=0.5 //当前客户端支持的语言 

Connection: keep-alive //客户端支持的链接方式，保持一段时间链接，默认为3000ms

Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8

Accept-Encoding: gzip, deflate, br //告诉服务器，当前客户端可以接收的文档类型,`*/*`表示都可以

Cookie: Idea-bc6d4892=fe76d3e6-e4aa-46b9-90ea-7af2596fd5f9; 

        SESSIONID=11F5C9E6CC76B6DF6DB544432A0546FB

——————————————————————————————————————————————————



* **POST请求**

——————————————————————————————————————————————————

**POST /index.jsp HTTP/1.1**

Host: localhost:8080

**Content-Type**: application/x-www-form-urlencoded //**表单的数据类型**，说明会使用url格式编码数据；url编码的数据都是以“%”为前缀，后面跟随两位的16进制

**Referer**: http://localhost:8080/login.html //请求来自哪个页面，例如你在百度上点击链接到了这里，那么Referer:http://www.baidu.com；如果你是在浏览器的地址栏中直接输入的地址，那么就没有Referer这个请求头了。可以用于防盗链，下载页面用的多

User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3514.0 Safari/537.36



username=apple54whn&password=asd123 //**请求体内容**

——————————————————————————————————————————————————



### * 2.4.2 响应协议

1. **响应行**
2. **响应头**
3. **空行**
4. **响应体**

——————————————————————————————————————————————————

**HTTP/1.1 200 OK** //响应**协议**为HTTP1.1，**响应码**为200，表示请求成功，OK是对响应码的解释 

**Content-Type**: text/html;charset=UTF-8 //响应内容的MIME类型 

Server: Apache-Coyote/1.1 //服务器的版本信息

Content-Length: 106 //响应体为106字节 

Set-Cookie: JSESSIONID=C97E2B4C55553EAB46079A4F263435A4; Path=/hello //响应给客户端的Cookie 

Date: Thu, 16 Aug 2018 07:20:30 GMT //响应的时间，这可能会有8小时的时区差



`<html>...</html>`

——————————————————————————————————————————————————

### 2.4.3 状态码

* 200：请求成功，浏览器会把响应体内容（通常是html）显示在浏览器中
* 404：请求的资源没有找到，说明客户端错误的请求了不存在的资源
* 500：请求资源找到了，但服务器内部出现了错误
* 302：**重定向**，当响应码为302时，表示服务器会发送一个响应头**Location**，它指定了新请求的URL地址。服务器要求浏览器重新再发一个请求。
* 304：当用户第一次请求index.**html**时，服务器会添加一个名为**Last-Modified响应头**，这个头说明了index.html的最后修改时间，浏览器会把index.html内容，以及最后响应时间缓存下来。当用户第二次请求index.html时，在请求中包含一个名为**If-Modified-Since请求头**，它的值就是第一次请求时服务器通过Last-Modified响应头发送给浏览器的值，即index.html最后的修改时间，If-Modified-Since请求头就是在告诉服务器，我这里浏览器缓存的index.html最后修改时间是这个，您看看现在的index.html最后修改时间是不是这个，如果还是，那么您就不用再响应这个index.html内容了，我会把缓存的内容直接显示出来。而服务器端会获取If-Modified-Since值，与index.html的当前最后修改时间比对，如果相同，服务器会发响应码304，表示index.html与浏览器上次缓存的相同，无需再次发送，浏览器可以显示自己的缓存页面，如果比对不同，那么说明index.html已经做了修改，服务器会响应200。

### 2.4.4 其他响应头

* 告诉浏览器不要缓存的响应头：Expires: -1；Cache-Control: no-cache； l  Pragma: no-cache；

* **自动刷新**响应头，浏览器会在3秒之后请求该url  Refresh: 3;url=http://www.itcast.cn 

* HTML中指定响应头：

  * 在HTML页面中可以使用`<meta http-equiv="" content="">`来指定响应头

    ```html
    <meta http-equiv="Refresh" content="3;url=http://www.itcast.cn">
    ```



# 3 Servlet

* **Servlet是JavaWeb三大组件之一**（Servlet、Filter、Listener），是用来处理客户端请求的动态资源
* Servlet的任务有：**接收请求数据、处理请求、完成响应**
* 如何让**浏览器访问Servlet**？

  给Servlet指定一个**Servlet路径**，浏览器通过访问Servlet路径来访问。一般写在**web.xml**中或直接**注解**


## 3.1 实现Servlet的方式

1. 实现javax.servlet.Servlet接口

2. 继承javax.servlet.GenericServlet抽象类

3. 继承javax.servlet.http.HttpServlet抽象类 

   通常我们会去继承HttpServlet类来完成我们的Servlet，但学习还要从javax.servlet.Servlet接口开始

### 3.1.1 实现javax.servlet.Servlet接口

```java
//初始化，在Servlet对象创建后马上执行，只执行一次
public void init(ServletConfig servletConfig) throws ServletException {
	System.out.println("init...");
}
//服务，每次处理请求会被调用
public void service(ServletRequest servletRequest, ServletResponse servletResponse) 
		throws ServletException,  IOException {
		System.out.println("service...");
		servletResponse.getWriter().write("hello servlet.");
		
}
//销毁，服务器关闭，在Servlet被销毁之前调用，只会被调用一次
public void destroy() {
	System.out.println("destroy...");
}
//获取Servlet配置信息
public ServletConfig getServletConfig() {
	return null;
}
//获取Servlet信息
public String getServletInfo() {
    return "我是一个牛逼的Servlet";
}
```

* **配置web.xml**

```xml
<servlet>
    <servlet-name>daohao</servlet-name>//相同代号
    <servlet-class>cn.itcast01.AServlet</servlet-class>//要访问的类
</servlet>
    
<servlet-mapping>
    <servlet-name>daohao</servlet-name>//相同代号
    <url-pattern>/MyServlet</url-pattern>//设置URL
</servlet-mapping>

//访问时：http://localhost:8080/MyServlet
```

* **生命周期方法**

  void init(ServletConfig)：**创建Servlet对象后**立即执行初始化方法（1次）；

  void service(ServletRequest request, ServletResponse response)：每次处理请求时都会被调用；

  void destroy()：服务器关闭，**销毁Servlet对象前**执行释放资源的方法（1次）；

* **特性：单例**，一个类只有一个对象；当然可能存在**多个Servlet类**！**线程不安全**的，所以它的**效率高**！  

* Servlet**类**由我们来写，但**对象由服务器来创建**，并且**由服务器来调用相应的方法**

### 3.1.2 继承javax.servlet.GenericServlet抽象类

* GenericServlet是Servlet接口的实现类，但它是一个**抽象类**，它**唯一的抽象方法就是service()方法**
  *  GenericServlet实现了Servlet方法（4个）
* GenericServlet**实现了ServletConfig接口**（4个方法）
*  GenericServlet**添加了init()方法**
  * 该方法会被init(ServletConfig)方法调用
  * 如果希望对Servlet进行初始化，那么应该覆盖init()方法，而不是init(ServletConfig)方法

### 3.1.3 继承javax.servlet.http.HttpServlet抽象类

* HttpServlet是GenericServlet接口的实现类，但它也是一个**抽象类**

* **HttpServlet时序**
  * Tomcat**调用Servlet**生命周期方法service(ServletRequest sr, ServletResponse srr)对参数进行**强转**为HTTP协议相关的参数类型
  * 然后**调用本类的service**(HttpServletRequest sr, HttpServletResponse srr)
  * 获取请求方法，**根据请求方式**来调用doGet()或doPost()，需自己重写，否则调用到该方法时返回**405**

```java
//注解的方式直接配置web.xml
@WebServlet(name = "ServletAuto" ,urlPatterns = "/") //访问时写全名字
public class ServletAuto extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().write("hello auto");
    }
}
```

### 3.1.4 ServletConfig接口

* ServletConfig是Servlet中的init()方法的参数类型，服务器会在调用init()方法时传递ServletConfig对象给init()

  **ServletConfig对象封装**了Servlet在web.xml中的**配置信息**，它对应`<servlet>`元素

  ```xml
  <servlet>
      <servlet-name>daihao1</servlet-name>
      <servlet-class>cn.itcast01.AServlet</servlet-class>
      <init-param>
          <param-name>name</param-name>
          <param-value>zhangsan</param-value>
      </init-param>
  </servlet>
  <servlet-mapping>
      <servlet-name>daihao1</servlet-name>
      <url-pattern>/Aservlet</url-pattern>
  </servlet-mapping>
  ```

* API如下：

    * String **getInitParameter**(String name)：获取**初始化参数**
    * Enumeration **getInitParameterNames**()：获取**所有初始化参数的名称**
    * ServletContext **getServletContext**()：**获取ServletContext对象**，这个对象稍后介绍
    * String getServletName()：获取Servlet配置名，即`<servlet-name>`的值；不常用

## 3.2 Servlet细节

### 3.2.1 Servlet与线程安全

* 因为一个类型的Servlet只有一个实例对象，那么就有可能会现时出一个Servlet同时处理多个请求，所以是线程不安全的
* **不要在Servlet中创建成员！创建局部变量即可**（方法中创建即可）
* **可以创建无状态成员**（成员方法就是）
* **可以创建有状态的成员，但状态必须为只读的**（对成员变量只提供get方法）

### 3.2.2 让服务器在启动时就创建Servlet

* 在`<servlet>`中配置`<load-on-startup>`，其中给出一个**非负整数**即可，数字**越小优先级越高**

### 3.2.3 url-pattern 

* `<url-pattern>`是`<servlet-mapping>`的子元素，用来指定Servlet的访问路径，即**URL**。必须以**“/”开头**！ 

  1. 可以在`<servlet-mapping>`中给出多个`<url-pattern>`，无论访问哪个都是同一个

  2. 还可以在`<url-pattern>`中使用**通配符**(星号“*”)，星号可以匹配任何URL**前缀或后缀**，不能放在中间

     **优先匹配具体的**`<url-pattern>` 

### 3.2.4 web.xml文件的继承（了解） 

* 在${CATALINA_HOME}\conf\web.xml中，相当于写到了每个项目的web.xml中，它是所有web.xml的父文件

  ```xml
  //它的优先级最低，如果一个请求没有人处理，那么它来处理！它显示404
  //当访问路径不存在时，会执行该Servlet！其实我们在访问index.html时也是在执行这个Servlet
  <servlet-name>default</servlet-name>
  <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
  <servlet-name>default</servlet-name>
  <url-pattern>/</url-pattern>//匹配所有URL,优先级最低
  ```

  ```xml
  //任何URL后缀为jsp的访问，都会执行名为jsp的Servlet
  <servlet-name>jsp</servlet-name>
  <url-pattern>*.jsp</url-pattern>
  <url-pattern>*.jspx</url-pattern>
  ```

  ```xml
  <session-timeout>30</session-timeout>//session的默认超时时间为30分钟
  ```

  ```xml
  //MIME类型用来标识网络上资源的媒体类型
  <mime-mapping>
      <extension>bmp</extension>
      <mime-type>image/bmp</mime-type>
  </mime-mapping>
  ```

  ```xml
  //在应用的web.xml中如果没有对<welcome-file-list>进行覆盖，那么默认主页为...
  <welcome-file-list>
      <welcome-file>index.html</welcome-file>
      <welcome-file>index.htm</welcome-file>
      <welcome-file>index.jsp</welcome-file>
  </welcome-file-list>
  ```

## 3.3 ServletContext（重要）

* 服务器会为每个应用/WEB站点创建一个ServletContext对象，**服务器启动时**完成，**销毁在服务器关闭时**完成
* **一个项目只有一个ServletContext对象**，我们可以在N多个Servlet中来获取这个唯一的对象，以传递数据

### 3.3.1 获取ServletContext

* 在**Servlet**中，通过init传递的**ServletConfig**参数的**getServletContext()**方法获取
* 在**GenericServlet**或**HttpServlet**中，GenericServlet有**getServletContext()**方法获取
* 在ServletContextEvent中，有getServletContext()方法获取

### 3.3.2 域对象功能(四大域对象)

* Java Web四大域对象：
  * application(当前web应用)：**ServletContext**
  * session(一次会话)：**HttpSession**
  * request(一次请求)：**ServletRequest**
  * page(jsp有效)：**PageContext**

* 所有域对象都有**存取数据**的功能，因为域对象内部有一个**Map**集合

  * void **setAttribute**(String name, Object value)：**存储**一个域属性
  * Object **getAttribute**(String name)：**获取**ServletContext中的数据
  * void **removeAttribute**(String name)：移除ServletContext中的域属性，name指定域属性不存在不动
  * Enumeration **getAttributeNames**()：获取所有域属性的名称

* **获取应用/WEB站点初始化参数（getInitParameter()）**

  * Servlet也可以获取初始化参数，但它是局部的参数(一个Servlet只能获取自己的初始化参数，不能获取别人的，即初始化参数只为一个Servlet准备)

  * 可以配置**应用的初始化参数**，为所有Servlet而用！这需要使用ServletContext才能使用

  * 可以使用ServletContext来获取在web.xml文件中配置的**应用初始化参数**！注意，应用初始化参数与Servlet初始化参数不同

    ```xml
    <context-param>
        <param-name>name</param-name>
        <param-value>zhangsan</param-value>
    </context-param>
    ```

    ```java
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext servletContext = this.getServletContext();
        String msg =(String)servletContext.getInitParameter("name");
        response.getWriter().write(msg);
    }
    ```

* **获取资源相关方法**

  * **获取真实路径（getRealPath(String path)）**

    ```java
    ServletContext servletContext = this.getServletContext();
    String realPath = servletContext.getRealPath("index.jsp");
    ```

  * **通过类加载器获取资源**

    * 类路径对一个JavaWeb项目而言，就是/WEB-INF/**classes**和/WEB-INF/lib/每个**jar**包！

      由于src下的文件最终都会放在classes文件夹下，所以类加载器获取的**目录应根据classes判断**

      ```java
      ClassLoader cl = this.getClass().getClassLoader();
      InputStream is = cl.getResourceAsStream("\\cn\\itcast01\\test.txt");
      ```

    * Class类的getResourceAsStream(String path)

      ```java
      InputStream is = this.getClass().getResourceAsStream("/../../index.jsp");
      ```

      1. 路径以“/”开头，相对classes路径
      2. 路径不以“/”开头，相对当前class文件所有路径,例如在cn.itcast.servlet.MyServlet中执行，那么相对/classes/cn/itcast/servlet/路径

  * 获取资源输入流

    ```java
    InputStream inputStream = servletContext.getResourceAsStream("index");
    ```

  * 获取指定目录下所有资源路径，必须加“**/**”

    ```java
    Set set = servletContext.getResourcePaths("/WEB-INF");
    ```

### 3.3.3 网站访问量

```java
ServletContext servletContext = this.getServletContext();
Integer count = (Integer) servletContext.getAttribute("count");
if(count==null){
    servletContext.setAttribute("count",1);
}else {
    servletContext.setAttribute("count",count+1);
}
count = (Integer) servletContext.getAttribute("count");
resp.getWriter().write("invite:"+count+"times");
```





# <span style="color:white;background:black;">4 Response&Request&编码&路径</span>

* 服务器处理请求的流程
  * 服务器每次收到请求时，都会为这个请求开辟一个**新的线程**
  * 服务器会把客户端的请求数据封装到request对象中，**request就是请求数据的载体**
  * 服务器还会创建**response**对象，这个对象与客户端连接在一起，它可以**用来向客户端发送响应**

## 4.1 Response

* response**类型**为**HttpServletResponse**

  ServletResponse-->与协议无关的类型；HttpServletResponse-->与http协议相关的类型

### 4.1.1 响应行中状态码的方法(sendError、sendStatus)

* **sendError(int sc)** --> 发送**错误状态码**，例如404、500
* **sendError(int sc, String msg)** --> 也是发送错误状态码，还可以带一个错误信息！
* **setStatus(int sc)** --> **设置此响应的状态码**，可以用来发送302、200或者404等

### 4.1.2 响应头（Content-Type、Refresh、Location等）

* 头就是一个**键值对！**可能会存在一个头（一个名称，一个值），也可能会存在一个头（一个名称，多个值！）
  * **<span style="background:yellow">setHeader(String n, String val)</span>**：适用于<span style="background:yellow">**单值**</span>的响应头，例如：response.setHeader("aaa", "AAA")
  * addHeader(String name, String value)：适用于多值的响应头，为每个值分别调用
  * setIntHeader(String name, int value)：适用于单值的int类型的响应头
  * addIntHeader(String name, int value)：适用于多值的int类型的响应头
  * setDateHeader(String name, long value)：适用于单值毫秒类型的响应头。expires过期时间
  * addDateHeader(String name, long value)：适用于多值的毫秒类型的响应头

* `<meta>`**标签代替响应头**

  ```html
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  ```

* **Location案例**

  ```java
  response.setStatus(302);
  response.setHeader("Location","https://www.baidu.com");
  //response.addHeader("Location","/AServlet");
  
  //利用sendRedirect()方法
  response.sendRedirect("/AServlet");
  ```

* **Refresh案例**（3秒后跳转至另一个页面）也可以理解为定时重定向

  ```java
  response.getWriter().write("after 3s ...");
  //url也可以改为www.baidu.com，若不设置这个参数，就跳转至当前页面
  response.setHeader("Refresh","3,url='DServlet'");
  ```

* **禁用浏览器缓存**(Cache-Control、pragma、expires)

  ```java
  response.setHeader("Cache-Control","no-cache");
  response.setHeader("Pragma", "no-cache");//编译指示
  response.setDateHeader("Expires", -1);//失效时间
  ```

### 4.1.3 响应体（通常是html、也可以是图片）

* response的两个流（不能同时使用，否则抛**IllegalStateException**异常）

  * **ServletOutputStream**，用来向客户端发送**字节数据**

    ```java
    ServletOutputStream out = resopnse.getOutputStream()
    ```

  * **PrintWriter**，用来向客户端发送**字符数据**！需要**设置编码**

    ```java
    PrintWriter writer = response.getWriter()
    ```





## 4.2 Request

* 封装了客户端所有的**请求数据**

### 4.2.1 **获取常用信息**

* **获取客户端IP**，用于封IP	<span style="font-family:monaco;color:red;font-weight:bold">String ip = request.getRemoteAddr();</span>
* **获取请求方式**，GET、POST等    <span style="font-family:monaco;color:red;font-weight:bold">String Method = request.getMethod();</span>

### 4.2.2 **获取(HTTP)请求头**

* <span style="font-family:monaco;color:red">String getHeader(String name)</span>，适用于**单值头**

  int getIntHeader(String name)，适用于单值int类型的请求头

  long getDateHeader(String name)，适用于单值毫秒类型的请求头

  Enumeration &lt;String&gt; getHeaders(String name)，适用于多值请求头

* **通过User-Agent识别用户浏览器类型**

  ```java
  String ip = request.getRemoteAddr();
  String method = request.getMethod();
  String userAgent= request.getHeader("User-Agent");
  if(userAgent.toLowerCase().contains("chrome")){
      System.out.println("你好"+ip+"你用的是谷歌浏览器");
  }else if (userAgent.toLowerCase().contains("firefox")) {
      System.out.println("你好" + ip + "你用的是火狐浏览器");
  }else if (userAgent.toLowerCase().contains("msie")){
      System.out.println("你好"+ip+"你用的是ie浏览器");
  }
  ```

* **防盗链**，若不是通过本站超链接发出的，发送错误状态码404。**Referer请求头吧表示请求的来源**

  ```java
  String referer = request.getHeader("Referer");
  ```


### 4.2.3 **获取请求URL**

* http://localhost:8080/day10_2/AServlet?username=xxx&password=yyy

* String getScheme()：获取协议，http
  String getServerName()：获取服务器名，localhost
  String getServerPort()：获取服务器端口，8080
  String **getContextPath()**：获取**项目名**，/day10_2
  String getServletPath()：获取Servlet路径，/AServlet
  String getQueryString()：获取参数部分，即问号后面的部分。username=xxx&password=yyy
  String getRequestURI()：获取请求URI，等于项目名+Servlet路径。/day10_2/AServlet
  String getRequestURL()：获取请求URL，等于不包含参数的整个请求路径。



### 4.2.4 获取请求参数

* 由客户端发送给服务器的！有可能是在请求体中（POST），也可能是在URL之后（GET）

  有一个参数一个值的，还有一个参数多个值

* <span style="font-family:monaco;color:red;font-weight:bold">String getParameter(String name)</span>：获取指定名称的请求参数值，适用于单值请求参数
  String[] getParameterValues(String name)：获取指定名称的请求参数值，适用于多值请求参数
  Enumeration< String>getParameterNames()：获取所有请求参数名称
  <span style="font-family:monaco;color:red;font-weight:bold">Map<String,String[]> getParameterMap()</span>：获取所有请求参数，key为参数名，value为参数值。

* **案例**

  ```html
  <a href="/CServlet?username=hehe&password=haha">点击这里</a><br/>
  
  <form action="/CServlet", method="post">
      username:<input type="text" name="username"><br/>
      password:<input type="password" name="password"><br/>
      hobby:<input type="checkbox" name="hobby" value="cf">吃饭
      hobby:<input type="checkbox" name="hobby" value="sj">睡觉
      hobby:<input type="checkbox" name="hobby" value="ddd">打豆豆
      <br/><input type="submit" value="提交">
  </form>
  ```

  ```java
  String username = request.getParameter("username");
  String password = request.getParameter("password");
  System.out.println(username+","+password);
  ```

  ```java
  String username = request.getParameter("username");
  System.out.println("username:"+username);
  String password = request.getParameter("password");
  System.out.println("password:"+password);
  Map<String, String[]> map = request.getParameterMap();
  Set<Map.Entry<String,String[]>> set = map.entrySet();
  for(Map.Entry<String,String[]> entry:set){
      System.out.println(entry.getKey()+":"+ Arrays.toString(entry.getValue()));
  }
  ```



### 4.2.5 请求转发和请求包含

* 有时一个请求需要多个Servlet协作才能完成，所以需要在一个Servlet跳到另一个Servlet，与重定向不同
* **<span style="font-family:monaco;color:red">一个请求</span>**跨多个Servlet，需要使用转发和包含。
  请求转发：由下一个Servlet完成响应体！当前Servlet可以设置响应头！（<span style="font-family:monaco;color:red;font-weight:bold">留头不留体</span>）
  请求包含：由两个Servlet共同未完成响应体！（<span style="font-family:monaco;color:red;font-weight:bold">都留</span>）
  无论是请求转发还是请求包含，都在一个请求范围内！使用同一个request和response！

* RequestDispatcher rd = <span style="font-family:monaco;color:red;font-weight:bold">request.getRequestDispatcher("/BServlet")</span>;  参数是被转发或包含的Servlet的路径

  <span style="font-family:monaco;color:red;font-weight:bold">请求转发：rd.forward(request,response)</span>

  请求包含：rd.include(request,response)

  ```java
  //OneServlet
  response.setHeader("AAA","aaa");
  request.getRequestDispatcher("/TwoServlet").forward(request,response);
  
  //TwoServlet
  response.setHeader("Two","222");
  response.getWriter().write("<h1>hello twoservlet</h1>");
  ```

* **请求转发和重定向的区别**

  * 请求转发是**一个请求一次响应**，而重定向是**两次请求两次响应**
  * 请求转发**地址栏不变化**，而重定向会显示**后一个请求的地址**
  * 请求转发只能**转发到本项目其他Servlet**，而重定向不只能重定向到本项目的其他Servlet，还能定向到其他项目
  * 请求转发是**服务器端行为**，只需给出转发的Servlet路径，而重定向需要给出requestURI，即包含项目名！

* 请求转发和重定向效率是转发高！因为是一个请求！

  * 需要地址栏发生变化，那么必须使用重定向！
  * 需要在下一个Servlet中获取request域中的数据，必须要使用转发！





## 4.3 编码

* **常见字符编码**：iso-8859-1(不支持中文)、gb2312、gbk、gb18030(系统默认编码，中国的国标码)、utf-8(万国码，支持全世界的编码，所以我们使用这个)

### 4.3.1 响应编码

* 使用**response.getWriter()**来向客户端发送**字符数据**时，如果在之前没有设置编码，**默认使用iso-8859-1**，因为iso不支持中文，一定乱码
* 使用<span style="font-family:monaco;color:red;font-weight:bold">response.setCharacterEncoding()</span>来**设置字符流的编码**为gbk或utf-8，当然我们通常会选择utf-8。这样使用response.getWriter()发送的字符就是使用utf-8编码的。但还是会出现乱码！因为浏览器并不知道服务器发送过来的是什么编码的数据！这时浏览器会使用gbk来解码，所以乱码！
* 使用<span style="font-family:monaco;color:red;font-weight:bold">response.setHeader("Content-type","text/html;charset=utf-8")</span>来设置响应头，通知浏览器服务器这边使用的是utf-8编码，而且在调用setHeader()后，还会**自动执行setCharacterEncding()**方法。这样浏览器会使用utf-8解码，所以就不会乱码了！
* **快捷方法是**：<span style="font-family:monaco;color:red;font-weight:bold">response.setContentType("text/html;charset=utf-8")</span>

### 4.3.2 请求编码

* 客户端发送给服务器的**请求参数**是什么编码：客户端首先要打开一个页面，然后在页面中提交表单或点击超链接！在请求**这个页面**时，服务器响应的**编码**是什么，那么客户端发送请求时的编码就是什么

* **服务器端默认使用ISO-8859-1来解码**！所以这一定会出现乱码的！因为iso不支持中文

* **请求编码处理**分为两种：GET和POST：GET请求参数不在请求体中，而POST请求参数在请求体中

  * **GET请求编码处理**：

    在server.xml中配置URIEncoding=utf-8（不能使用）

    ```java
    String username = request.getParameter("name");
    byte[] bys = username.getBytes("ISO-8859-1");
    username = new String(bys, "utf-8");
    ```

  * **POST请求编码处理：**

    ```java
    request.setCharacterEncoding("utf-8");//在获取参数之前调用,或同GET使用的方法
    ```

### 4.3.3 URL编码

* 表单的类型：Content-Type: application/x-www-form-urlencoded，就是把中文转换成%后面跟两位的16进制

  为什么要用它：在客户端和服务器之间传递中文时需要把它转换成网络适合的方式

* **它不是字符编码**，它是用来在客户端与服务器之间传递参数用的一种方式

* URL编码需要先指定一种字符编码，把**字符串解码后，得到byte[]，然后把小于0的字节+256，再转换成16进制。前面再添加一个%**

* **POST**请求默认就使用URL编码！tomcat会自动使用URL解码

  ```java
  String username = URLEncoder.encode(username, "utf-8");//URL编码：
  String username = URLDecoder.decode(username, "utf-8");//URL解码：
  ```

  最后我们需要把链接中的中文参数，使用url来编码！学了jsp就可以

### 4.3.4 路径

* web.xml中< url-pattern>路径，（叫它Servlet路径！）

  * 要么以“*”开关，要么为“/”开头
* **转发和包含路径**
  * **以“/”开头**：相对当前项目路径，例如：`http://localhost:8080/项目名/　request.getRequestdispacher("/BServlet").for...();`
  * 不以“/”开头：相对当前Servlet路径。 `request.getRequestdispacher("/BServlet").for...();`，假如当前Servlet是：http://localhost:8080/项目名/servlet/AServlet

* 重定向路径（客户端路径）

  * 以“/”开头：相对当前主机，例如：http://localhost:8080/，所以需要自己手动添加项目名，例如；`response.sendRedirect("/day10_1/Bservlet");`

* 与重定向相同，都是客户端路径！需要添加项目名

  ```html
  <form action="/day10_1/AServlet">
  <a href="/day10_/AServlet"></a><!--如果不以“/”开头，那么相对当前页面所在路径-->
  ```

* ServletContext获取资源路径

  * 相对当前项目目录，即当然index.jsp所在目录

* ClassLoader获取资源路径

  * 相对classes目录

* Class获取资源路径

  * 以“/”开头相对classes目录
  * 不以“/”开头相对当前.class文件所在目录。





# 5 JSP（一种特殊的Servlet）

## 5.1 JSP基础

### 5.1.1 JSP的介绍

* JSP其实就是Servlet
  * Servlet：
    * 优点：动态资源，可以编程
    *  缺点：不适合设置html响应体，需要大量的response.getWriter().print("< html>")
  * html：
    * 优点：不用为输出html标签而发愁
    * 缺点：html是静态页面，不能包含动态信息
  * JSP(java server pages)：
    * 优点：在原有html的基础上添加java脚本，构成jsp页面

### 5.1.2 JSP和Servlet的分工

* Servlet：作为请求中**处理数据**的环节
* JSP：作为**请求发起页面**，例如显示表单、超链接。作为**请求结束页面**，例如显示数据

### 5.1.3 JSP的组成

* <span style="font-family:monaco;color:red;font-weight:bold">JSP= html + Java脚本 + JSP标签(指令)</span>
* JSP中无需创建即可使用的对象一共有9个，被称之为**9大内置对象**。例如：request对象、out对象

* 3种Java脚本：
  * <span style="font-family:monaco;color:red;font-weight:bold"><%...%></span>：**java代码片段**(常用)，用于定义0~N条Java语句！**方法内能写什么**，它就可以放什么！
  * <span style="font-family:monaco;color:red;font-weight:bold"><%=...%></span>：**java表达式**，用于输出(常用)，用于**输出一条表达式（或变量）的结果**。response.getWriter().print( ... );这里能放什么，它就可以放什么！
  * <%!...%>：**声明**，用来创建类的成员变量和成员方法(**基本不用**，但容易被考到)，**类体中可以放什么**，它就可以放什么！

### 5.1.4 **JSP原理**

* **当客户端第一次访问JSP：**
  * 当jsp页面第一次被访问时，<span style="font-family:monaco;color:red;font-weight:bold">服务器会把jsp编译成java文件</span>（这个java其实继承一个servlet类）
  * 然后再<span style="font-family:monaco;color:red;font-weight:bold">把java编译成.class</span>
  * 然后<span style="font-family:monaco;color:red;font-weight:bold">创建该类JSP(Servlet)对象</span>
  * 最后<span style="font-family:monaco;color:red;font-weight:bold">调用它的service()方法</span>（第二次请求同一jsp时，直接调用service()方法）

### 5.1.5 JSP的注释

*  <span style="font-family:monaco;color:red;font-weight:bold"><%-- ... --%></span>：当服务器把jsp编译成java文件时已经忽略了注释部分！不会发送至客户端

  但是HTML中注释会发送并可以从查看源代码中找到，虽然页面中不显示

* JSP对应的Java文件：java脚本直接显示，html输出用write，变量输出用print



## 5.2 Cookie

### 5.2.1 Http协议与Cookie（了解）

* Cookie是<span style="font-family:monaco;color:red;font-weight:bold">HTTP协议制定</span>的！先由**服务器保存Cookie到浏览器**，在下次浏览器请求服务器时把上一次请求得到Cookie再**归还**给服务器

  * 由服务器创建保存到客户端浏览器的**一个键值对**！服务器保存Cookie的响应头：Set-Cookie: aaa=AAA  Set-Cookie: bbb=BBB

    ```java
    response.addHeader("Set-Cookie","aaa=AAA");response.addHeader("Set-Cookie", "bbb=BBB");
    ```

  * 当浏览器请求服务器时，会把该浏览器保存的Cookie随请求发送给服务器。浏览器归还Cookie的请求头

    ```
    Cookie: aaa=AAA; bbb=BBB
    ```

* Http协议规定（保证不给浏览器太大压力）

  * 1个Cookie最大4KB
  * 1个服务器最多向1个浏览器保存**20**个Cookie
  * 1个浏览器最多可以保存**300**个Cookie

### 5.2.2 Cookie的用途（不能跨浏览器）

* 服务器使用Cookie来跟踪客户端状态！
  * 保存购物车(购物车中的商品不能使用request保存，因为它是一个用户向服务器发送的多个请求信息)
  * 显示上次登录名(也是一个用户多个请求)

### 5.2.3 JavaWeb中使用Cookie

* 原始方式（了解）

  * 使用response发送Set-Cookie响应头
  * 使用request获取Cookie请求头

* <span style="font-family:monaco;color:red;font-weight:bold">便捷方式（精通）</span>

  * 使用<span style="font-family:monaco;color:red;font-weight:bold">repsonse.addCookie()</span>方法向浏览器**保存Cookie**
  * 使用<span style="font-family:monaco;color:red;font-weight:bold">request.getCookies()</span>方法获取浏览器**归还的Cookie**，需要判断是否null

* 一个jsp保存cookie,另一个jsp获取浏览器归还的cookie

  ```jsp
  <%
      Cookie cookie1 = new Cookie("AAA","aaa");
      response.addCookie(cookie1);
      Cookie cookie2 = new Cookie("BBB","bbb");
      response.addCookie(cookie2);
  %>
  ```

  ```jsp
  <%
      Cookie[] cookies = request.getCookies();
      if(cookies!=null){
          for(Cookie cookie:cookies){
              out.print(cookie.getName()+"="+cookie.getValue()+"<br/>");
          }
      }
  %>
  ```

### 5.2.4 Cookie详解

* Cookie不只有name和value两个属性

* Cookie的<span style="font-family:monaco;color:red;font-weight:bold">maxAge</span>（掌握）：Cookie的最大生命，即Cookie可保存的最大时长。以**秒**为单位，例如：cookie.setMaxAge(60)表示这个Cookie会被浏览器保存到硬盘上60秒

  * maxAge>0：浏览器会把Cookie保存到客户机硬盘上，有效时长为maxAge的值决定。
  * maxAge<0：Cookie只在浏览器内存中存在，当用户关闭浏览器时，浏览器进程结束，Cookie也就死亡。
  * maxAge=0：浏览器会马上删除这个Cookie！

  ```jsp
  <%
  	Cookie cookie1 = new Cookie("AAA","aaa");
  	cookie1.setMaxAge(60);
      response.addCookie(cookie1);
  %>
  ```

* Cookie的<span style="font-family:monaco;color:red;font-weight:bold">path</span>（理解）：

  * Cookie的path并**不是设置这个Cookie在客户端的保存路径**！！！
  * Cookie的path**由服务器创建Cookie时设置**
  * 当浏览器访问服务器某个路径时，**需要归还哪些Cookie给服务器呢**？这**由Cookie的path决定**
  * 浏览器<span style="font-family:monaco;color:red;font-weight:bold">访问服务器的路径，如果包含(理解为contains)某个Cookie的路径</span>，那么就会归还这个Cookie

  * **例如：**

    ```
    aCookie.path=/day11_1/; 
    bCookie.path=/day11_1/jsps/; 
    cCookie.path=/day11_1/jsps/cookie/;
    访问：/day11_1/index.jsp时，归还：aCookie
    访问：/day11_1/jsps/a.jsp时，归还：aCookie、bCookie
    访问：/day11_1/jsps/cookie/b.jsp时，归还：aCookie、bCookie、cCookie
    ```

  * Cookie的path默认值：**当前访问路径的父路径**。例如访问`/day11_1/jsps/a.jsp`时，响应的cookie，那么这个cookie的默认path为`/day11_1/jsps/`

* Cookie的domain（域，了解）

  * domain用来指定Cookie的域名！当多个二级域中共享Cookie时才有用。
    * 例如；www.baidu.com、zhidao.baidu.com、news.baidu.com、tieba.baidu.com之间共享Cookie时可以使用domain
      * 设置domain为：cookie.setDomain(".baidu.com");
      * 设置path为：cookie.setPath("/");

* **Cookie中不能存在中文**！！！在new对象时就出错





## 5.3 HttpSession

### 5.3.1 HttpSession概述

* HttpSession是由<span style="font-family:monaco;color:red;font-weight:bold">JavaWeb提供</span>的，用来**会话跟踪**的类。session是<span style="font-family:monaco;color:red;font-weight:bold">服务器端对象</span>，保存在服务器端！！！
* HttpSession是**Servlet三大域对象之一**(request、session、application(ServletContext))，所以它也有setAttribute()、getAttribute()、removeAttribute()方法
* HttpSession**底层依赖Cookie**，**或是URL重写**！

### 5.3.2 HttpSession的作用

* **会话范围**：会话范围是某个用户从首次访问服务器开始，到该用户关闭浏览器结束！

  **会话**：一个用户对服务器的多次连贯性请求！所谓连贯性请求，就是该用户多次请求中间没有关闭浏览器！

* 服务器会**为每个客户端创建一个session对象**，session就好比客户在服务器端的账户，它们被服务器保存到一个Map中，这个**Map被称之为session缓存**！

* Servlet中得到session对象：<span style="font-family:monaco;color:red;font-weight:bold">HttpSession session = request.getSession();</span>

   Jsp中得到session对象：<span style="font-family:monaco;color:red;font-weight:bold">session是jsp内置对象</span>，不用创建就可以直接使用！

* session域相关方法：
  * void **setAttribute**(String name, Object value);
  * Object **getAttribute**(String name);
  * void **removeAttribute**(String name);

### 5.3.3 HttpSession原理（理解）

* 服务器不会马上给你创建session，在第一次获取session时，才会创建！request.getSession();

* request.getSession()方法：
  * 获取Cookie中的JSESSIONID：
    * 如果JSESSIONID不存在，创建session并保存起来，把新创建的JSESSIONID保存到Cookie中
    * 如果JSESSIONID存在，通过JSESSIONID查找session对象，如果没有查找到，创建session并保存起来，把新创建的JSESSIONID保存到Cookie中
    * 如果JSESSIONID存在，通过JSESSIONID查找到了session对象，那么就不会再创建session对象了
    * 返回session
  * 如果创建了新的session，**浏览器会得到一个包含了JSESSIONID的Cookie**，这个Cookie的生命为-1，即只在浏览器内存中存在，如果不关闭浏览器，那么Cookie就一直存在。
  * 下次请求时，再次执行request.getSession()方法时，因为可以通过Cookie中的JSESSIONID找到session对象，所以与上一次请求使用的是同一session对象。

* request.getSession(false)、request.getSession(true)、request.getSession()，后两个方法效果相同

  第一个方法：如果session缓存中(如果cookie不存在)，不存在session，那么返回null，不会创建session对象



### 5.3.4 HttpSession其他方法

  * String **getId**()：获取sessionId（UUID）；

  * int **getMaxInactiveInterval**()：获取session可以的最大不活动时间（秒），默认为30分钟。当session在30分钟内没有使用，那么Tomcat会在session池中移除这个session；

      * web.xml中配置session的最大不活动时间

        ```xml
            <session-config>
                <session-timeout>30</session-timeout>
            </session-config>
        ```

  * void **invalidate**()：让session失效！调用这个方法会被session失效，当session失效后，客户端再次请求，服务器会给客户端创建一个新的session，并在响应中给客户端新session的sessionId；**退出按钮**

  * boolean **isNew**()：查看session是否为新。当客户端第一次请求时，服务器为客户端创建session，但这时服务器还没有响应客户端，也就是还没有把sessionId响应给客户端时，这时session的状态为新。

    `request.getSession().isNew()`判断是新创建的还是返回的Session

### 5.3.5 URL重写（理解）

* 就是把所有的页面中的路径，都使用response.encodeURL("..")处理一下！

  * session依赖Cookie，目的是让客户端发出请求时归还sessionId，这样才能找到对应的session
  * 如果**客户端禁用了Cookie**，那么就无法得到sessionId，那么session也就无用了！
  * 也可以**使用URL重写**来替代Cookie
    * 让网站的所有超链接、表单中都添加一个特殊的请求参数，即sessionId
    * 这样服务器可以通过获取请求参数得到sessionId，从而找到session对象。
  * response.encodeURL(String url)
    * 该方法会对url进行智能的重写：当请求中没有归还sessionid这个cookie，那么该方法会重写url，否则不重写！当然url必须是指向本站的url。



### 例1 演示session中会话的多次请求中共享数据

* AServlet：向session域中保存数据（利用JSP）

* BServlet：从session域中获取数据（利用JSP）

  ```jsp
  <%session.setAttribute("AAA","aaa");%>
  ```

  ```jsp
  <%=session.getAttribute("AAA")%>
  ```

### * 例2 用户登录加验证码（精通）

* 案例相关页面和Servlet
  * login.jsp：登录页面
  * succ1.jsp：只有登录成功才能访问的页面
  * succ2.jsp：只有登录成功才能访问的页面 
  * LoginServlet：校验用户是否登录成功！

* 各页面和Servlet内容
  * login.jsp：提供登录表单，提交表单请求LoginServlet
  * LoginServlet：获取请求参数，校验用户是否登录成功
  * 失败：保存错误信息到request域，转发到login.jsp(login.jsp显示request域中的错误信息)
  * 成功：保存用户信息到session域中，重定向到succ1.jsp页面，显示session域中的用户信息
  * succ1.jsp：从session域获取用户信息，如果不存在，显示“您还没有登录”。存在则显示用户信息
  * succ2.jsp：从session域获取用户信息，如果不存在，显示“您还没有登录”。存在则显示用户信息

  只要用户没有关闭浏览器，session就一直存在，那么保存在session中的用户信息也就一起存在！那么用户访问succ1和succ2就会通过

* 添加图片验证码，利用VerifyCode.jar包来完成其Servlet

  * VerifyCodeServlet：生成验证码

```java
//LoginServlet.java
//验证码
String yanzhengma = request.getParameter("VerifyCode");
String code = ((String) request.getSession().getAttribute("text"));
if(!yanzhengma.equalsIgnoreCase(code)){
    request.setAttribute("message","验证码错误");
    request.getRequestDispatcher("/Login/login.jsp").forward(request,response);
    return;
}

request.setCharacterEncoding("utf-8");//POST请求编码的解析采用utf-8
String username = request.getParameter("username");//获取表单数据
String password = request.getParameter("password");//获取表单数据
if(username.equals("hehe")&&password.equals("123")){//判断是否可以登录
    //保存Cookie，并发送至客户端；
    Cookie cookie = new Cookie("username",username);
    cookie.setMaxAge(60*60*24);
    response.addCookie(cookie);
    //设置Session域的属性并重定向至登录成功页面
    HttpSession session = request.getSession();
    session.setAttribute("username",username);
    response.sendRedirect("/Login/succ1.jsp");
} else {
    //若登录失败，在Request域中保存失败的属性，并请求转发至登录界面
    request.setAttribute("message","用户名或密码错误");
    request.getRequestDispatcher("/Login/login.jsp").forward(request,response);
}


//VerifyCodeServlet.java
VerifyCode verifyCode = new VerifyCode();
BufferedImage bi = verifyCode.getImage();
request.getSession().setAttribute("text",verifyCode.getText());
VerifyCode.output(bi,response.getOutputStream());
```

```jsp
<%-- login.jsp --%>
<%
    String username = "";
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                username = cookie.getValue();
            }
        }
    }
%>
<h1>登录</h1>
<form action="/LoginServlet" method="post">
    用户名：<input type="text" name="username" value="<%=username%>"><br/>
    密　码：<input type="password" name="password"><br/>
    验证码：<input type="text" name="VerifyCode" size="3">
    <img  id="img1" src="/VerifyCodeServlet" alt="VarifyCodeImage" >
    <a href="javascript:change1();">换一张</a><br/>
    <input type="submit" value="登录">
</form>
<%
    String message = "";
    if (request.getAttribute("message") != null) {
        message = (String) request.getAttribute("message");
    }
%>
<p style="color: red;font-weight: bold"><%=message%></p>

<!--改变验证码的Js代码-->
<script>
    var change1 = function () {
        var imgId = document.getElementById("img1");
        imgId.src="/VerifyCodeServlet?"+new Date().getTime();//改变请求参数，图片才会刷新
    }
</script>
```

```jsp
<%-- succ1.jsp --%>
<%
    String username = (String) session.getAttribute("username");
    if (username == null) {
        request.setAttribute("message", "您还没有登录，请登录后再尝试。");
        request.getRequestDispatcher("/Login/login.jsp").forward(request, response);
        return;
    }
%>
<h1 style="color: gray;"><%=username%>，登录成功</h1>
```







## 5.4 JSP其他

### 5.4.1 JSP九大内置对象

- application（ServletContext）：即ServletContext类的对象
- session（HttpSession）：即HttpSession类的对象，不是每个JSP页面中都可以使用，如果在某个JSP页面中设置<%@page session=”false”%>，说明这个页面不能使用session
- request（HttpServletRequest）：即HttpServletRequest类的对象
- response（HttpServletResponse）：即HttpServletResponse类的对象
- pageContext（PageContext）：页面上下文对象，它是最后一个没讲的域对象
- page（当前JSP的真身类型）：当前JSP页面的“this”，即当前对象；
- config（ServletConfig）：对应“真身”中的ServletConfig

- out（JspWriter）：等同与response.getWriter()，用来向客户端发送文本数据
- exception（Throwable）：只有在错误页面中可以使用这个对象



- pageContext：一个顶9个！

  Servlet中有三大域，而JSP中有四大域，它就是最后一个域对象！

  - ServletContext：整个应用程序
  - session：整个会话(一个会话中只有一个用户)
  - request：一个请求链！
  - pageContext：一个jsp页面！这个域是在**当前jsp页面**和当前jsp页面中使用的**标签**之间共享数据！
    - 域对象
    - **代理其他域**：pageContext.setAttribute("xxx", "XXX", PageContext.SESSION_SCOPE);
    - **全域查找**：pageContext.findAttribute("xxx");**从小到大**，依次（四大域对象）查找！
    - **获取其他8个内置对象**



### 5.4.2 JSP三大指令

**一个jsp页面中，可以有0~N个指令的定义！可以放在任意位置**

* **page**    最复杂    **<%@page language="java" info="xxx"...%>**

  * pageEncoding和contentType

    * pageEncoding：它指定当前jsp页面的编码，只要不说谎，就不会有乱码！在服务器要把jsp编译成.java时需要使用pageEncoding!
    * contentType：它表示添加一个响应头：Content-Type！等同与response.setContentType("text/html;charset=utf-8");
      * 如果两个属性只提供一个，那么另一个的默认值为设置那一个。如果两个属性都没有，那么默认为iso

  * import：导包！可以出现多次

  * errorPage和isErrorPage

    * errorPage：当前页面如果抛出异常，那么要转发到哪一个页面，由errorPage来指定

    * isErrorPage：它指定当前页面是否为处理错误的页面！当该属性为true时，这个页面会设置状态码为500！而且这个页面可以使用9大内置对象中的**exception**!

      ```xml
      <error-page>
          <error-code>404</error-code>
          <location>/error/errorPage.jsp</location>
      </error-page>
      <error-page>
          <error-code>500</error-code>
          <location>/error/errorPage.jsp</location>
      </error-page>
      <error-page>
          <exception-type>java.lang.RuntimeException</exception-type>
          <location>/index.jsp</location>
      </error-page>
      ```

  * autoFlush和buffer

    * autoFlush：指定jsp的输出流缓冲区满时，是否自动刷新！默认为true，如果为false，那么在缓冲区满时抛出异常！
    * buffer：指定缓冲区大小，默认为8kb，通常不需要修改！

  * isELIgnored：是否忽略el表达式，默认值为false，不忽略，即支持！

  * 基本没用：

    * language：指定当前jsp编译后的语言类型，默认值为java。
    * info：信息！
    * isThreadSafe：当前的jsp是否支持并发访问！值为false，没人用
    * session：当前页面是否支持session，如果为false，那么当前页面就没有session这个内置对象！
    * extends：让jsp生成的servlet去继承该属性指定的类！

* **include**    静态包含

  * 与RequestDispatcher的include()方法的功能相似！
  * < %@include%> 它是在jsp编译成java文件时完成的！他们共同生成一个java(就是一个servlet)文件，然后再生成一个class！
  * RequestDispatcher的include()是一个方法，包含和被包含的是两个servlet，即两个.class！他们只是把响应的内容在运行时合并了！
  * 作用：把页面分解了，使用包含的方式组合在一起，这样一个页面中不变的部分，就是一个独立jsp，而我们只需要处理变化的页面。

* **taglib**    导入标签库，两个属性

  * prefix：指定标签库在本页面中的前缀！由我们自己来起名称！

  * uri: 指定标签库的位置！

    <%@taglib prefix="s" uri="/struts-tags"%> 前缀的用法< s:text>

### 5.4.3 JSP动作标签

* 这些jsp的动作标签，**与html提供的标签有本质的区别**

  * 动作标签是由tomcat(服务器)来解释执行！它与java代码一样，都是在服务器端执行的！
  * html由浏览器来执行！
    * < jsp:forward>：**转发**！它与RequestDispatcher的forward方法是一样的，一个是在Servlet中使用，一个是在jsp中使用！
    * < jsp:include>：**包含**：它与RequestDispatcher的include方法是一样的，一个是在Servlet中使用，一个是在jsp中使用！

      * < %@include>和< jsp:include>有什么不同！

    * < jsp:param>：它用来作为forward和include的子标签！用来给转发或包含的页面传递参数！

### 5.4.4 JavaBean

* JavaBean是一种规范，也就是对类的要求。

* JavaBean的规范
  1. 必须要有一个**默认构造器**（无参）
  2. **提供get/set方法**，如果只有get方法，那么这个属性是只读属性！
  3. 属性：有get/set方法的成员，还**可以没有成员**，只有get/set方法。**属性名称由get/set方法来决定**！而不是成员名称！
  4. 方法名称满足一定的规范，那么它就是属性！boolean类型的属性，它的读方法可以是is开头，也可以是get开头！

* 内省（了解）：目标是得到JavaBean属性的读、写**方法的反射对象**，通过反射对JavaBean属性**进行操作**的一组API

  * 内省类 --> Bean信息 --> 属性描述符 --> 属性的get/set对应的Method！ --- > 可以反射了！

* **commons-beanutils**，它是依赖内省完成！

  * 导包：commons-beanutils.jar、commons-logging.jar

    ```java
    BeanUtils.getProperty(Object bean, String propertyName)
    BeanUtils.setProperty(Object bean, String propertyName, String propertyValue)
    ```

    ```java
    Class c = Class.forName("Person");
    Object bean = c.newInstance();
    BeanUtils.setProperty(bean,"name","张三");
    System.out.println(BeanUtils.getProperty(bean,"name"));//张三
    System.out.println(bean);//Person{name='张三', age=0, gender='null'}
    ```

* 将map中的数据封装到Bean中

  ```java
  BeanUtils.populate(Map map, Object bean)//map转为bean
  //或自己封装的工具
  CommontUtils.toBean(Map map, Class class)
  ```




## 5.5 EL表达式

### 5.5.1 EL是JSP内置的表达式语言

* JSP2.0开始，不让再使用java脚本，而是使用el表达式和动态标签来替代java脚本！
* <span style="font-family:monaco;color:red;font-weight:bold">EL替代的是<%= ... %></span>，也就是说，**EL只能做输出**！

### 5.5.2 EL表达式读取四大域

  * **${xxx}**，**全域查找**名为xxx的属性（从小到大），如果**不存在，输出空字符串**，而不是null。
  * **${pageScope.xxx}**、 **${requestScope.xxx}**、**${sessionScope.xxx}**、**${applicationScope.xxx}**，指定域获取属性！

### 5.5.3 JavaBean导航

```jsp
<%
	Address address = new Address();
	address.setCity("北京");
	address.setStreet("西三旗");
	
	Employee emp = new Employee();
	emp.setName("李小四");
	emp.setSalary(123456);
	emp.setAddress(address);
	
	request.setAttribute("emp", emp);
  %>

<h3>使用el获取request域的emp</h3>
${requestScope.emp.address.street }
```

### 5.5.4 11个EL内置对象

* EL可以输出的东西都在11个内置对象中！11个内置对象，其中**10个是Map**！**pageContext**不是map，它就是PageContext类型，1个项9个。
  * 我们已经学习了四个

  * param：对应参数，它是一个Map，其中key参数名，value是参数值，适用于单值的参数。

  * paramValues：对应参数，它是一个Map，其中key参数名，value是多个参数值，适用于多值的参数。

  * header：对应请求头，它是一个Map，其中key表示头名称，value是单个头值，适用于单值请求头

  * headerValues：对应请求头，它是一个Map，其中key表示头名称，value是多个头值，适用于多值请求头

  * initParam：获取< context-param>内的参数！

    ```xml
    <context-param>
        <param-name>xxx</param-name>
        <param-value>XXX</param-value>
    </context-param>
    <context-param>
        <param-name>yyy</param-name>
        <param-value>YYY</param-value>
    </context-param>
    ```

    ```jsp
    ${initParam.xxx}  
    ```

  * cookie：Map<**String,Cookie**>类型，其中key是cookie的name，value是cookie对象。 ${cookie.username.value}

  * <span style="font-family:monaco;color:red;font-weight:bold">pageContext</span>：它是PageContext类型！<span style="font-family:monaco;color:red;font-weight:bold">${pageContext.request.contextPath}</span>获取项目名

### 5.5.5 EL函数库（由JSTL提供的）

* 导入标签库：<%@ tablib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

  ```jsp
  <%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <%
  String[] strs = {"a", "b","c"};
  List list = new ArrayList();
  list.add("a");
  pageContext.setAttribute("arr", strs);
  pageContext.setAttribute("list", list);
  %>
  
  ${fn:length(arr) }<br/><!--3-->
  ${fn:length(list) }<br/><!--1-->
  ${fn:toLowerCase("Hello") }<br/> <!-- hello -->
  ${fn:toUpperCase("Hello") }<br/> <!-- HELLO -->
  ${fn:contains("abc", "a")}<br/><!-- true -->
  ${fn:containsIgnoreCase("abc", "Ab")}<br/><!-- true -->
  ${fn:contains(arr, "a")}<br/><!-- true -->
  ${fn:containsIgnoreCase(list, "A")}<br/><!-- true -->
  ${fn:endsWith("Hello.java", ".java")}<br/><!-- true -->
  ${fn:startsWith("Hello.java", "Hell")}<br/><!-- true -->
  ${fn:indexOf("Hello-World", "-")}<br/><!-- 5 -->
  ${fn:join(arr, ";")}<br/><!-- a;b;c -->
  ${fn:replace("Hello-World", "-", "+")}<br/><!-- Hello+World -->
  ${fn:join(fn:split("a;b;c;", ";"), "-")}<br/><!-- a-b-c -->
  
  ${fn:substring("0123456789", 6, 9)}<br/><!-- 678 -->
  ${fn:substring("0123456789", 5, -1)}<br/><!-- 56789 -->
  ${fn:substringAfter("Hello-World", "-")}<br/><!-- World -->
  ${fn:substringBefore("Hello-World", "-")}<br/><!-- Hello -->
  ${fn:trim("     a b c     ")}<br/><!-- a b c -->
  ${fn:escapeXml("<html></html>")}<br/> <!-- <html></html> -->
  
  ```

* 自定义函数库

  1. 写一个java类，类中可以定义0~N个方法，但必须是static，而且有返回值的！

  2. 在WEB-INF目录下创建一个tld文件

     ```tld
     <function>
     	<name>fun</name>
     		<function-class>cn.itcast.fn.MyFunction</function-class>
     		<function-signature>java.lang.String fun()</function-signature>
     </function>
     ```

  3. 在jsp页面中导入标签库
     <%@ taglib prefix="it" uri="/WEB-INF/tlds/itcast.tld" %>

  4. 在jsp页面中使用自定义的函数：${it:fun() }

## * 5.6 JSTL标签库

### 5.6.1 JSTL概述

  * JSP Standard Tag Library,**apache**的东西，依赖EL，对其进行了扩展

  * 使用JSTL需要导入jstl1.2.jar

  * 四大库：

    <span style="font-family:monaco;color:red;font-weight:bold">core</span>：核心库，重点
    **fmt**：格式化：日期、数字
    sql：过时
    xml：过时

### 5.6.2 导入标签库

* jar包
* 在jsp页面中：<%@taglib prefix="前缀" uri="路径"%>

### 5.6.3 core（c标签）

* **< c:out>**：**输出（几乎不用）**`<c:out value="${AAA}"/>`
  * value：可以是字符串常量，也可以是EL表达式
  * default：当要输出的内容为null时，会输出default指定的值
  * escapeXml：默认值为true，表示**转义**！防止攻击
* **< c:set>**：**设置(创建域的属性)**`<c:set var="AAA" value="asd" scope="session"/>`
  * var：变量名
  * value：变量值，可以是EL表达式
  * scope：域，默认为page，可选值：page、request、session、application

  * **< c:remove>**：**删除域变量**`<c:remove var="AAA" scope="session"/>`
    * var：变量名
    * scope：如果**不给出scope，表示删除所有域**中的该名称变量；如果**指定了域**，只删除该域的变量。

*  **< c:url>**
    * **value**：指定一个路径！它会在**路径前面自动添加项目名**

      * `<c:url value="/index.jsp"/>`，它会输出/day13_1/index.jsp

    * **子标签**：**< c:param>**，用来**给url后面添加参数**，例如：

      ```jsp
      <c:url value="/index.jsp">
        <c:param name="username" value="张三"/>  <!--可以对参数进行url编码！！-->
      < /c:url>
      //结果为：/day13_1/index.jsp?username=%ED%2C%3F%ED%2C%3F
      ```

    * var：指定变量名，一旦添加了这个属性，那么url标签就不会再输出到页面，而是把生成url保存到域中。

    * scope：它与var一起使用，用来保存url。

*  **< c:if>**：对应java中的if语句

    * <c:if test="布尔类型">...< /c:if>，当test为值时，执行标签体内容！

      ```jsp
      <c:set var="a" value="hello"/>
      <c:if test="${not empty a }">
      	<c:out value="${a }"/>
      </c:if>
      ```

* **< c:choose>**：它对应java中的if/else if/ ... /else

  ```jsp
  <c:set var="score" value="${param.score }"/>
  <c:choose>
  	<c:when test="${score > 100 || score < 0}">错误的分数：${score }</c:when>
  	<c:when test="${score >= 90 }">A级</c:when>
  	<c:when test="${score >= 80 }">B级</c:when>
  	<c:when test="${score >= 70 }">C级</c:when>
  	<c:when test="${score >= 60 }">D级</c:when>
  	<c:otherwise>E级</c:otherwise>
  </c:choose>
  ```

* **< c: forEach>**：它用来循环遍历数组、集合，计数方式来循环(for)！

  ```jsp
  <c:forEach var="i" begin="1" end="10" step="2">//循环变量、开始、结束、设置步长(默认1)
      ${i}
  </c:forEach>
  //13579
  ```

  ```jsp
  <c:forEach items="${strs }" var="str">//循环目标、变量
   ${str }<br/>
  </c:forEach>
  ```

  * **循环状态**：可以使用**varStatus**来创建循环状态变量

    ```jsp
    <c:forEach var="item" items="${ns }" varStatus="vs">
    	<c:if test="${vs.first }">第一行：</c:if>
    	<c:if test="${vs.last }">最后一行：</c:if>
    	<c:out value="第${vs.count }行: "/>
    	<c:out value="[${vs.index }]: "/>
    	<c:out value="name: ${vs.current }"/><br/>
    </c:forEach>
    //count：循环元素的个数
    //index：循环元素的下标
    //first：是否为第一个元素
    //last：是否为最后一个元素
    //current：当前元素
    ```


### 5.6.4 I18N（fmt标签）

* 格式化库

  * <fmt:**formatDate** value="" pattern="">

    * value：指定一个Date类型的变量

    * pattern：用来指定输出的模板！例如：yyyy-MM-dd HH:mm:ss

      ```jsp
      <%
      	Date date = new Date();
      	pageContext.setAttribute("d", date);
      %>
      <fmt:formatDate value="${d }" pattern="yyyy-MM-dd HH:mm:ss"/>
      ```

  * <fmt:**formatNumber** value="${num1}" pattern="0.00">

    *   保留小数点后2位，它会四舍五入！如果不足2位，以0补位！

  * <fmt:**formatNumber** value="${num1}" pattern="#.##">

    *   保留小数点后2位，它会四舍五入！如果不足2位，不补位！



# 6 Listener(监听器)

* 监听器：

  * 它是一个**接口**，内容由我们来实现
  * 它需要**注册**，例如注册在按钮上

  * 监听器中的方法，会在特殊**事件发生时被调用**

* 观察者模式：

  * 事件源（被监听对象）：小偷

  * 事件（事件源行为）：偷东西

  * 监听器（用于监听事件源的对象）：警察，监听器中的方法：抓捕

* Java Web中监听器（8大）：
  * **ServletContext**

    * 生命周期监听：**ServletContextListener**，它有两个方法，一个在出生时调用，一个在死亡时调用
      * void contextInitialized(ServletContextEvent sce)：创建Servletcontext时
      * void contextDestroyed(ServletContextEvent sce)：销毁Servletcontext时
    * 属性监听：**ServletContextAttributeListener**，它有三个方法，在添加、替换、移除属性时调用
      * void attributeAdded(ServletContextAttributeEvent event)：添加属性时
      * void attributeReplaced(ServletContextAttributeEvent event)：替换属性时
      * void attributeRemoved(ServletContextAttributeEvent event)：移除属性时

  * **HttpSession**

    * 生命周期监听：**HttpSessionListener**，它有两个方法，一个在出生时调用，一个在死亡时调用

      * void sessionCreated(HttpSessionEvent se)：创建session时
      * void sessionDestroyed(HttpSessionEvent se)：销毁session时

    * 属性监听：**HttpSessioniAttributeListener**，它有三个方法，在添加、替换、移除属性时调用

      * void attributeAdded(HttpSessionBindingEvent event)：添加属性时
      * void attributeReplaced(HttpSessionBindingEvent event)：替换属性时
      * void attributeRemoved(HttpSessionBindingEvent event)：移除属性时

    * **感知监听**：用来**添加到JavaBean上（需实现接口）**，**不需要在web.xml中注册**

      * **HttpSessionBindingListener**：添加到javabean上，javabean就知道自己是否添加到session中

      * **HttpSessionActivationListener**：监听JavaBean(实现序列化接口)是否随Session被钝化、活化

        * Session的序列化：context.xml中打开被注释掉的Manager即可不允许Session序列化

        * Tomcat会在session长时间不被使用时**钝化**session对象，所谓钝化session，就是把session通过序列化的方式**保存到硬盘文件中**。当用户再使用session时，Tomcat还会把钝化的对象再活化session，所谓活化就是把硬盘文件中的session在反序列化回内存

          ```xml
          <!--配置Tomcat钝化session参数;放到tomcat\conf\catalina\localhost\项目名-->
          <Context>
          	<Manager className="org.apache.catalina.session.PersistentManager" maxIdleSwap="1">
          		<Store className="org.apache.catalina.session.FileStore" directory="mysession"/>
          	</Manager>
          </Context>
          ```

  * **ServletRequest**

    * 生命周期监听：**ServletRequestListener**，它有两个方法，一个在出生时调用，一个在死亡时调用
      * void requestInitialized(ServletRequestEvent sre)：创建request时
      * void requestDestroyed(ServletRequestEvent sre)：销毁request时
    * 属性监听：**ServletRequestAttributeListener**，它有三个方法，在添加、替换、移除属性时调用
      * void attributeAdded(ServletRequestAttributeEvent srae)：添加属性时
      * void attributeReplaced(ServletRequestAttributeEvent srae)：替换属性时
      * void attributeRemoved(ServletRequestAttributeEvent srae)：移除属性时

* Java Web中完成编写监听器

  * 写一个监听器类(实现某个监听器接口)；
  * 注册，在web.xml中配置来完成注册

* 事件对象

  * ServletContextEvent：ServletContext getServletContext()

  * ServletContextAttributeEvent：

    - ServletContext getServletContext()；

    - String getName()：获取属性名

    - Object getValue()：获取属性值

  * HttpSessionEvent：HttpSession getSession()

  * HttpSessionBindingEvent：略

  * ServletRequestEvent ：

    * ServletContext getServletContext()；
    * ServletRequest getServletRequest()；

  * ServletRequestAttributeEvent ：略



# 7 Filter(过滤器)

* Java Web三大组件都需要在web.xml中进行配置

  Servlet、Listener(2个感知监听器不需要配置)、Filter

* 过滤器：它会在**一组资源**（jsp、servlet、.css、.html等等）**的前面执行**！它可以让请求得到目标资源，也可以不让请求达到！**过滤器有<span style="font-family:monaco;color:red;font-weight:bold">拦截请求</span>的能力**！

* **过滤器如何编写：**（**单例**）

  1. 写一个类**实现Filter接口**

  2. 在web.xml中进行**配置**

     ```xml
     <filter>
       <filter-name>xxx</filter-name>
       <filter-class>cn.itcast.web.filter.AFitler</fitler-class>
     </servlet>
     <fitler-mapping>
       <filter-name>xxx</filter-name>
       <url-pattern>/*</url-pattern>
     </filter-mapping>
     ```

* **Filter接口**

  * void **init**(FilterConfig)：创建之后，马上执行；Filter会在服务器启动时就创建！
  * void **destory**()：销毁之前执行！在服务器关闭时销毁
  * void <span style="font-family:monaco;color:red;font-weight:bold">doFilter**(ServletRequest,ServletResponse,**FilterChain)</span>：每次过滤时都会执行

* FilterConfig-->与ServletConfig相似
    * 获取初始化参数：getInitParameter()
    * 获取过滤器名称：getFilterName()
    * 获取appliction：getServletContext()

  * **FilterChain**

      * <span style="font-family:monaco;color:red;font-weight:bold">doFilter(ServletRequest, ServletResponse)</span>：**放行**！**相当于调用了目标Servlet的service()方法**！



* **多过滤器**

  * **FilterChain#doFilter()方法：执行目标资源，或是执行下一个过滤器！**

    如果没有下一个过滤器那么执行的是目标资源，如果有，那么就执行下一个过滤器！

* 过滤器的四种拦截方式，在< filter-mapping>中进行配置!

*   < dispatcher>**REQUEST**< /dispatcher>默认的！拦截请求
    < dispatcher>**FORWARD**< /dispatcher> 拦截转发
    < dispatcher>**INCLUDE**< /dispatcher> 拦截包含
    < dispatcher>**ERROR**< /dispatcher> 拦截错误

* 

* 

* 

* 多个过滤器的执行顺序

  < filter-mapping>的**配置顺序决定了过滤器的执行顺序**！

* 过滤器的应用场景：

  * **执行目标资源之前做预处理工作**，例如设置编码，这种试通常都会**放行**，在目标资源执行前做准备工作
  * **通过条件判断是否放行**，例如校验当前用户是否已经登录，或者用户IP是否已经被禁用
  * **在目标资源执行后**，做一些**后续的特殊处理**工作，例如把目标资源输出的数据进行处理

## 7.1 分IP统计网站访问次数

* 统计工作需要在所有资源之前都执行，那么就可以放到**Filter**中了,不做拦截操作！**获取map并保存数据**
* 用Map<String,Integer>来装载统计的数据,整个网站只需要一个**Map**即可！使用**ServletContextListener**，在服务器启动时完成创建，并保存到ServletContext中
* 打印Map中的数据

## 7.2 粗粒度权限控制（拦截是否登录、拦截用户名admin权限）

* RBAC(基于角色的权限控制)细粒度权限控制

  * tb_user
  * tb_role
  * tb_userrole
  * tb_menu(增、删、改、查)
  * tb_rolemenu

* 我们给出三个页面：index.jsp、user.jsp、admin.jsp。

  * index.jsp：谁都可以访问，没有限制；

  * user.jsp：只有登录用户才能访问；

  * admin.jsp：只有管理员才能访问。

* 在Servlet中判断登录成功后保存信息到HTTPSession中，创建两个Filter，分别过滤user和admin的Session。Filter中Session的获取需将req强转为Http后才可以得到

## 7.3 全站编码问题(增强request)

```java
//装饰Request
public class EncodingRequest extends HttpServletRequestWrapper {
	private HttpServletRequest req;
	
	public EncodingRequest(HttpServletRequest request) {
		super(request);
		this.req = request;
	}
	public String getParameter(String name) {
		String value = req.getParameter(name);
		// 处理编码问题
		try {
			value = new String(value.getBytes("iso-8859-1"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return value;
	}
}
```

```java
public void doFilter(ServletRequest request, ServletResponse response,
                     FilterChain chain) throws IOException, ServletException {
    request.setCharacterEncoding("utf-8");   // 处理post请求编码问题
    
    HttpServletRequest req = (HttpServletRequest) request;
		/*
		 * 调包request
		 * 1. 写一个request的装饰类
		 * 2. 在放行时，使用我们自己的request
		 */
    if(req.getMethod().equals("GET")) {
        EncodingRequest er = new EncodingRequest(req);
        chain.doFilter(er, response);
    } else if(req.getMethod().equals("POST")) {
        chain.doFilter(request, response);
    }
}
```



## 7.4 页面静态化(增强response)

```java
public class StaticResponse extends HttpServletResponseWrapper {
	private PrintWriter pw;
	//String path：html文件路径！
	public StaticResponse(HttpServletResponse response, String path) 
			throws FileNotFoundException, UnsupportedEncodingException {
		super(response);
		// 创建一个与html文件路径在一起的流对象
		pw = new PrintWriter(path, "utf-8");
	}
	public PrintWriter getWriter() {
		// 返回一个与html绑定在一起的printWriter对象
		// jsp会使用它进行输出，这样数据都输出到html文件了。
		return pw;
	}
}
```

```java
public class StaticFilter implements Filter {
	private FilterConfig config;
	public void destroy() {}
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}
	public void doFilter(ServletRequest request, 
			ServletResponse response, FilterChain chain) 
					throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/*
		 * 1. 第一次访问时，查找请求对应的html页面是否存在，如果存在重定向到html
		 * 2. 如果不存在，放行！把servlet访问数据库后，输出给客户端的数据保存到一个html文件中
		 *   再重定向到html
		 * 一、获取category参数！
		 * category有四种可能：null --> null.html....
		 * html页面的保存路径, htmls目录下
		 * 判断对应的html文件是否存在，如果存在，直接重定向！
		 */
		String category = request.getParameter("category");
		String htmlPage = category + ".html";//得到对应的文件名称
		String htmlPath = config.getServletContext().getRealPath("/htmls");//得到文件目录
		File destFile = new File(htmlPath, htmlPage);
		if(destFile.exists()) {//如果文件存在
			// 重定向到这个文件
			res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
			return;
		}
		/*
		 * 二、如果html文件不存在，我们要生成html
		 * * 调包response，让它的getWriter()与一个html文件绑定，那么show.jsp的输出就到了html中
		 */
		StaticResponse sr = new StaticResponse(res, destFile.getAbsolutePath());
		chain.doFilter(request, sr);//放行，即生成了html文件
		// 这时页面已经存在，重定向到html文件
		res.sendRedirect(req.getContextPath() + "/htmls/" + htmlPage);
	}
}
```



# 9 国际化

* 一般内容排版都不一样，尽量再写一份HTML页面

* **ResourceBundle**类（**通过加载Local实例**来获取配置文件中的内容）

  下面是两个配置文件内容：（配置文件名称：基本名称(都得相同)+Local部分+.properties）

  * res_zh_CN.properties

  * res_en_US.properties

    ```java
    ResourceBundle rb = ResourceBundle.getBundle("res", new Locale("zh", "CN" ));
    String username = rb.getString("username");//姓名
    ```

* **Locale类**

  * 创建Locale类对象，一个Locale对象表示的就是**语言和国家**

    ```java
    //zh、en表示语言，而CN、US表示国家
    new Locale(“zh”, “CN”)；
    new Locale(“en”, “US”)；
    ```

  * Local.getDefault：获得此Java虚拟机实例的当前默认语言环境

* 

# 10 MVC设计模式

* 它不是java独有，所有的B/S结构的项目都在使用它！
* M -- **model** 模型(自己写代码，JavaBean，完成具体业务工作，如开启、转账等)
* V -- **View**  视图(jsp)
* C -- **Controller** 控制器(Servlet)

# 11 JavaWeb三层架构

* Web层 --> 与Web相关的内容(Servlet，JSP，Servlet相关API：request、response、session、ServletContext)
* 业务层 --> 业务对象(Service)
* 数据层 --> 操作数据库(DAO Data Access Object，数据访问对象)(对数据库的操作，不能跳出到DAO之外)

## 11.1 登录注册

### 11.1.1 分析功能

* **Domain**：
  * User（对应数据库，还要对应所有表单）
    * username、password、verifyCode
* **Dao**：
  - UserDao --> 与用户相关的数据类
* **Service**：
  - UserService --> 与用户相关的业务类
* **Web.Servlet**：
  - LoginServlet
  - RegistServlet

* **JSP**：
    * login.jsp  --> 登录表单
    * regist.jsp --> 注册表单
    * index.jsp -->  主页(只有登录成功才能看到)

* Exception



* 数据库设计
    * users.xml

      ```xml
      <users>
        <user username="xxx" password="xxx"/>
        <user username="xxx" password="xxx"/>
      </users>
      ```

### 11.1.2 注册

* **regist.jsp**：完成regist.jsp的基本功能！
* **RegistServlet**：封装表单数据到User对象中，调用service的regist()方法
  * 如果这个方法没有出问题，输出“注册成功”
  * 如果这个方法抛出了异常，把错误信息保存到request域，转发到regist.jsp（回显错误信息）
* **UserService#regist()**：无返回值，注册失败抛出一个自定义异常。调用UserDao的findByUsername()方法
  * 如果已被注册，抛出异常（“用户名已被注册！”）
  * 没被注册则添加用户
* **UserDao**：通过业务分析，得到结果：需要提供两个方法
  * 按用户名查询用户对象：User findByUsername(String username)
  * 插入一个用户到数据库中：void add(User user)

#### 11.1.2.1 给注册添加验证码

* **VerifyCodeServlet**
  1. BufferedImage getImage()：获取随机验证码图片
  2. String getText()：把验证码图片上的文本保存到session中
  3. static output(BfferedImage, OutputStream)：把图片写入到response的outputStream中
* **regist.jsp**
  1. 添加< img src="指向Servlet"  />
  2. 添加一个文本框，用来输入验证码
  3. “看不清，换一张”，是一个超链接。把上面的< img>的src重新再次指向Servlet！为了处理浏览器的缓存，需要使用时间来做参数！
* **修改RegistServlet**
  * 校验验证码！
    * 错误：保存表单数据到request域、保存错误信息到request域，转发回regist.jsp
    * 正确：什么都不做，向下执行原来代码！

#### 11.1.2.2 服务器端表单（输入）校验

* 我们把这段校验，放到获取表单数据之后，验证码校验之前！
  1. 使用Map类型来装载错误信息！（那个字段出错，就给哪个字段添加错误信息）
     - key：表单项名称，例如：username、password、verifyCode
     - value：
       - 非空：用户名不能为空，或者是“密码不能为空”
       - 长度：用户名长度必须在3~20之间　密码长度必须在3~20之间
  2. 判断map是否为空（长度是否为0），如果不空，说明有错误存在，保存map到request域，保存form到request域（回显），转发回regist.jsp
  3. 在regist.jsp页面中，显示map中的错误信息。${map.username}

### 11.1.3 登录

* login.jsp --> 登录表单！

* LoginServlet --> 
  1. 获取表单数据，封装到User中
  2. 调用service的login()方法，传递form过去！
  3. 如果service的login()方法，没有抛出异常！返回一个User对象！
     - 有异常：获取异常信息，保存到request域，保存form，转发到login.jsp
     - 没异常：保存返回的user对象到session中！！！重定向到welcome.jsp(显示当前用户信息！)

* UserService#login()
  * public User login(User form) {...}
  * 使用用户名查询数据库，得到返回的User
    * 返回为null，抛出异常，异常信息为（用户名不存在）
    * 返回不为null，获取查询出来的user的password与form的password进行比较！如果不同：抛出异常（密码错误！）；如果相同，返回查询结果！

* UserDao
  * 通过用户名查询用户！(已经存在了，不用再写了！)



### 11.1.4 代码

#### 11.1.4.1 domain

* javabean类，`User.java`

#### 11.1.4.2 dao

```java
//UserDao.java
private String path = "F:\\users.xml";
//查找用户名是否存在，根据返回User对象的值来判断
public User findByUsername(String username)  {
    SAXReader saxReader = new SAXReader();//dom4j来查询，记得导包
    try {
        Document document = saxReader.read(path);//获取document
        Element element = (Element)document.selectSingleNode(
            "//user[@username='"+username+"']");//XPath来查找（导包），记得属性值带''
        if (element==null)//若没找到，User值为null并返回
            return null;
        //找到则封装数据到User对象并返回
        User user = new User();
        user.setUsername(element.attributeValue("username"));//根据属性值来获取
        user.setPassword(element.attributeValue("passwrod"));
        return user;
    } catch (DocumentException e) {
        throw new RuntimeException();//抛RuntimeException异常，无需处理
    }
}
//添加用户
public void addUser(User user) {
    SAXReader saxReader = new SAXReader();//dom4j，记得导包
    try {
        Document document = saxReader.read(path);
        Element root = document.getRootElement();//获取根节点users
        Element us = root.addElement("user");//添加user节点
        us.addAttribute("username",user.getUsername());//给user节点添加属性及值
        us.addAttribute("password",user.getPassword());
        //回写xml
        OutputFormat format = OutputFormat.createPrettyPrint();//带格式
        //format.setTrimText(true);//不需要这个也行
        try {
            //需要的字符输出流是带编码的，只能通过OutputStreamWriter转换流实现
            XMLWriter xmlWriter = new XMLWriter(
                new OutputStreamWriter(new FileOutputStream(path),
                                       "utf-8"),format);
            xmlWriter.write(document);
            xmlWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);//抛RuntimeException异常，无需处理
        }
    } catch (DocumentException e) {
        throw new RuntimeException(e);//抛RuntimeException异常，无需处理
    }
}
```

#### 11.1.4.3 Service

```java
//UserService
UserDao userDao = new UserDao();//依赖dao层
//注册，通过封装过的User对象注册
public void regist(User user) throws  MyException {
    User _user = userDao.findByUsername(user.getUsername());//调用findByUsername
    if(_user!=null)//若返回null，抛已被注册异常
        throw new MyException("用户名"+user.getUsername()+"，已被注册");
    userDao.addUser(user);//否则添加User对象来注册
}
//登录
public User login(User user) throws MyException {
    User _user =  userDao.findByUsername(user.getUsername());//调用findByUsername
    if(_user==null) {//若返回null，抛用户名不存在异常
        throw new MyException("用户名不存在");
    } else if(_user.getPassword().equals(_user.getPassword())){//抛密码错误异常
        throw new MyException("密码错误");
    }
    return _user;//能找到则返回数据库中User对象
}
```

#### 11.1.4.4 Web.Servlet

```java
//LoginServlet.java
UserService userService = new UserService();//依赖UserService
//封装表单数据到bean
User user = new User();
try {
    BeanUtils.populate(user,request.getParameterMap());
} catch (Exception e) {
    throw new RuntimeException(e);
}
try {
    User _user = userService.login(user);//调用login方法，传入封装的User对象
    request.getSession().setAttribute("succUser",_user);//不抛，则保存数据库中User对象信息
    response.sendRedirect(request.getContextPath()+"/user/welcome.jsp");//重定向到欢迎界面
} catch (MyException e){//找不到则抛异常
    request.setAttribute("error",e.getMessage());//保存异常信息到Request域中
    request.setAttribute("user",user);//保存表单中User对象到Request域中，回显
    request.getRequestDispatcher("/user/login.jsp").forward(request,response);//请求转发
}

//RegistServlet.java
request.setCharacterEncoding("utf-8");//设置请求编码为utf-8（POST请求可以这样写，POP则麻烦点）
response.setContentType("text/html;charset=utf-8");//设置响应编码为utf-8
//封装表单到bean
UserService userService = new UserService();//依赖UserService
User user = new User();
try {
    BeanUtils.populate(user, request.getParameterMap());
} catch (Exception e) {
    throw new RuntimeException(e);
}
//校验，用map保存所有错误信息
Map<String, String> map = new HashMap<>();
//用户名校验
String username = user.getUsername();
if (username == null || username.trim().isEmpty())
    map.put("username", " 用户名不能为空");
else if (username.length() < 3 || username.length() > 15)
    map.put("username", " 用户名长度必须在3~16之间");
//密码校验
String password = user.getPassword();
if (password == null || password.trim().isEmpty())
    map.put("password", " 密码不能为空");
else if (password.length() < 3 || password.length() > 15)
    map.put("password", " 密码长度必须在8~16之间");
//验证码校验
String verifyCode = user.getVerifyCode();
HttpSession session = request.getSession();
if (verifyCode == null || verifyCode.trim().isEmpty())
    map.put("verifyCode", " 验证码不能为空");
else if (!verifyCode.equalsIgnoreCase((String) session.getAttribute("vcode")))
    map.put("verifyCode", " 验证码错误");
//判断map，若map为空则没有错误，继续执行，否则返回
if (map != null && map.size() > 0) {
    request.setAttribute("user", user);//在Request域中保存封装后的表单User对象
    request.setAttribute("map", map);//在Request域中保存map对象
    request.getRequestDispatcher("/user/regist.jsp").forward(request, response);//转发
    return;//有错误所以返回，不能再向下执行
}
//表单校验正确后执行如下
try {
    userService.regist(user);//调用regist方法，传入封装表单的User对象
    response.getWriter().write("注册成功，<a href='" + request.getContextPath() + "/user/login.jsp'>点击这里进行登录</a>");//若没抛异常，注册成功，在RegistServlet显示此信息
} catch (MyException e) {//抛异常后执行...
    request.setAttribute("error", e.getMessage());//保存异常信息到Request域
    request.setAttribute("user", user);//保存封装的表单对象到Request域
    request.getRequestDispatcher("/user/regist.jsp").forward(request, response);//转发
}

//VerifyCodeServlet.java
VerifyCode verifyCode = new VerifyCode();
BufferedImage bi = verifyCode.getImage();
request.getSession().setAttribute("vcode",verifyCode.getText());
VerifyCode.output(bi,response.getOutputStream());
```

#### 11.1.4.5 JSP

```jsp
<h1>登录</h1>
<form action="${pageContext.request.contextPath}/LoginServlet" method="post">
    用户名：<input type="text" name="username" value="${user.username}"><br/>
    密　码：<input type="password" name="password" value="${user.password}"><br/>
    <input type="submit" value="登录"><br/>
    <p style="color: red;">${requestScope.error}</p>
    
    
<h1>注册</h1>
<form action="${pageContext.request.contextPath}/RegistServlet" method="post">
    用户名：<input type="text" name="username" value="${user.username}"><span style="color: red;">${map.username}</span><br/>
    密　码：<input type="password" name="password" value="${user.password}"><span style="color: red;">${map.passwrod}</span><br/>
    验证码：<input type="text" name="verifyCode" value="${user.verifyCode}" size="3">
    <img id="img1" src="${pageContext.request.contextPath}/VerifyCodeServlet" border="1">
    <a href="javascript:exchange1()">看不清，换一张</a><span style="color: red;">${map.verifyCode}</span><br/>
    <input type="submit" value="注册"><br/>
    <p style="color: red;">${requestScope.error}</p>
</form>
<script>
    var exchange1 = function () {
        var img1 = document.getElementById("img1");
        img1.src="${pageContext.request.contextPath}/VerifyCodeServlet?"+new Date().getTime();
    }
</script>
```

