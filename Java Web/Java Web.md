# Java Web 概述

* JavaWeb：使用Java语言开发基于互联网的项目

* 软件架构
  * B/S：客户端/服务器（Client/Server），例如QQ
    * 优点：安全性比较好，大型应用游戏用户体验好
    * 缺点：需要编写服务器端程序，以及客户端程序。开发、安装、部署、维护麻烦
  * C/S：浏览器/服务器（Browser/Server）
    * 优点：只需要编写服务器端程序。开发、安装、部署、维护简单
    * 缺点：安全性较差，大型应用游戏用户体验差，对硬件要求高（服务器）

* B/S架构详解

  * 资源分类

    * **静态资源**

      * 所有用户访问，得到的结果是一样的。如：文本，图片，音频、视频、**HTML、CSS、JavaScript**

        如果用户请求的是静态资源，那么服务器会**直接将静态资源发送给浏览器**。浏览器中内置了静态资源的解析引擎，可以展示静态资源

    * **动态资源**

      * 所有用户访问，得到的结果可能不一样。如：jsp/servlet、php、asp...

        如果用户请求的是动态资源，那么服务器会执行**动态资源**，**转换为静态资源**，**再发送给浏览器**



# 1 XML

## 1.1 XML简介（了解）

- XML（EXtensible Markup Language）：**可扩展标记型语言**。
  - **可扩展**：xml中标签可以**自定义**，可以写中文的标签 `<person></person`
- 功能：**存储数据**
    - 配置文件
   - 在网络中传输，跨平台
 - xml与html的区别（都是w3c万维网联盟发布的）
    - xml标签都是自定义的，html标签是预定义
    - xml的语法严格，html语法松散
    - xml主要用于存储数据的，html是展示数据

## 1.2 XML的语法

**基本语法（严格！）**

* xml文档的后缀名`.xml`
* xml**第一行（顶格）**必须定义为文档声明
* xml文档中有且仅有**一个根标签**
* **属性值**必须使用**引号**（单双都可）引起来
* **标签**必须**正确关闭**
* xml**标签名**称**区分大小写**

**1、文档声明**

```xml
<?xml version="1.0" encoding="utf-8"?>
```

- 格式：**第一行第一列**（顶格）：`<?xml 属性列表 ?>`
- 属性列表：
  - `version`：版本号，必须的属性。虽然有1.1版本，但不向下兼容，所以大多使用1.0版本
  - `encoding`：编码方式。告知解析引擎当前文档使用的字符集，默认值：`ISO-8859-1`，可设置`gbk`、`utf-8`等
  - `standalone`：是否独立。取值如下：
      * yes：不依赖其他文件
      * no：依赖其他文件

**2、标签定义规则**

* 名称可以包含字母、数字以及其他的字符 
* 名称不能以数字或者标点符号开始 
* 名称不能以字母 xml（或者 XML、Xml 等等）开始 
* 名称不能包含空格

**3、属性**

* id属性值唯一

**4、特殊字符**

* 转义字符：`&lt;` `&gt;` `&amp;`等

**5、CDATA区（了解）**

   * 解决多个字符都需要转义的操作 ，把特殊字符，当做文本内容，而不是标签
   * 格式：`<![CDATA[ 内容 ]]>`。例如，`<![CDATA[ <b>if(a<b && b<c && d>f) {}</b> ]]>`

**6、PI指令（了解）**

* 结合CSS，写法：`<?xml-stylesheet type="text/css" href="css的路径"?>`。给xml设置样式，但只对英文标签起作用

**7、注释**

* 同html：`<!--注释-->`



## 1.3 XML的约束（看懂）

> 浏览器只负责校验xml的语法，不负责校验约束

- 约束：**规定xml文档的书写规则**
- 作为框架的使用者（程序员）：
   1. 能够在xml中引入约束文档
   2. 能够简单的读懂约束文档

- 分类：
  1. DTD：一种简单的约束技术
  2. Schema：一种复杂的约束技术

### 1.3.1 DTD约束

- **引入**

  * 内部dtd：将约束规则定义在xml文档中

      ```dtd
      <!DOCTYPE 根标签名  [
      	<!ELEMENT person (name,age)>
      	<!ELEMENT name (#PCDATA)>
      	<!ELEMENT age (#PCDATA)>
      ]>
      ```

  * 外部dtd：将约束的规则定义在外部的dtd文件中
      * 本地：`<!DOCTYPE 根标签名 SYSTEM "dtd文件的位置">`
      * 网络：`<!DOCTYPE 根标签名 PUBLIC "dtd文件名字" "dtd文件的位置URL">`

- 定义步骤：

  1. 创建一个文件 **后缀名 .dtd**

  2. 看xml中有多少个元素 ，有几个元素，在dtd文件中写几个 `<!ELEMENT>`

  3. 定义元素

     - 简单元素：没有子元素  **`<!ELEMENT 元素名称 约束>`**，约束取值如下
       - `(#PCDATA)`：约束name是字符串类型
       - `EMPTY`：元素为空（没有内容），如`<sex></sex>`
       - `ANY`：任意
     - 复杂元素：有子元素的元素  **`<!ELEMENT 元素名称 (子元素)>`**
       - 子元素直接使用**逗号进行隔开**：表示**元素出现的顺序**
       - 子元素直接使用**|隔开**：表示元素**只能出现其中的任意一个**
       - 子元素出现次数如下
         - `?`：表示出现0次或1次
         - `*`：表示出现0次或多次
         - `+`：出现1次或多次

  4. 定义属性

     - 语法

       ```dtd
       <!ATTLIST 元素名称
       		属性名称 属性类型 属性的约束
       >
       ```

       * 属性类型如下：
         * `CDATA`：字符串
         * `枚举`：表示只能在一定的范围内出现值，但是只能每次出现其中的一个；(aa|bb|cc)，红绿灯
         * `ID`：值只能是字母或者下划线开头，且使用时这个属性的值不能重复
       * 属性的约束如下：
         - `#REQUIRED`：属性必须存在
         - `#IMPLIED`：属性可有可无
         - `#FIXED`: 表示一个固定值 #FIXED "AAA"
         - `直接值`：不写属性，使用直接值；写了属性，使用设置那个值

  5. 定义实体

     - 语法：` <!ENTITY 实体名称 "实体的值">`

     - 使用实体：&实体名称;（比如 &TEST;）

     - 注意：定义实体需要写在内部dtd里面，如果写在外部的dtd里面，有某些浏览器下，内容得不到




### 1.3.2 schema约束

> schema**符合xml的语法**，xml语句
>
> 一个xml中可以**有多个schema**，多个schema使用**名称空间**区分（类似于java包名）
>
> dtd里面有PCDATA类型，但是在schema里面可以支持**更多的数据类型**，比如年龄只能是整数

* **引入**
  1. 填写xml文档的根元素：`<students`
  2. 引入xsi前缀，如：`xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"`，表示xml是一个被约束文件
  3. 为每一个xsd约束声明一个前缀，作为标识：`xmlns:name="http://www.itcast.cn/xml"`
  4. 引入xsd文件地址路径：`xsi:schemaLocation="http://www.itcast.cn/xml  student.xsd">`



* 定义步骤
  1. 创建一个schema文件 后缀名是 **.xsd**，根节点 `<schema>`，有如下属性：

     - `xmlns`：表示当前xml文件是一个约束文件
     - `targetNamespace`：使用schema约束文件，直接通过这个地址引入约束文件
     - `elementFormDefault="qualified"`

  2. 看xml中有**多少个元素**，写几个`<element>`

     - **复杂元素**

       ```xml
       <element name="person">
       	<complexType>
       		<sequence>
       			<element name="name" type="string"></element>
       			<element name="age" type="int"></element>
       		</sequence>
                <attribute name="id1" type="int" use="required"></attribute>
       		- name: 属性名称
       		- type：属性类型 int stirng
       		- use：属性是否必须出现 required
       	</complexType>
       </element>
       ```

       - 复杂元素指示器：
         - `sequence`：表示元素的出现的顺序
         - `all`: 元素只能出现一次
         - `choice`：元素只能出现其中的一个
         - `maxOccurs="unbounded"`： 表示元素的出现的最多次数；同理有min~
         - `any`:表示任意元素
       - 可以约束属性：写在复杂元素里面，`</complexType>`之前

     - **简单元素写在复杂元素里**，如上





## 1.4 XML的解析

* 操作xml文档
  1. 解析(读取)：将文档中的数据读取到内存中
  2. 写入：将内存中的数据保存到xml文档中。持久化的存储
* ==**解析xml的方式：**==
  1. ==**DOM**：将标记语言文档**一次性加载**进内存，在内存中形成一颗**dom树**==，如下图
    * 优点：操作方便，可以对文档进行**CRUD**的所有操作
    * 缺点：文件过大，造成**内存溢出**
  2. ==**SAX**：**逐行读取**，基于**事件驱动**的==，如下图
    * 优点：文件过大，**不会造成内存溢出**
    * 缺点：**只能读取**，不能增删改
* xml常见的解析器：
  - JAXP：sun公司提供的解析器，支持dom和sax两种思想。不用
  - DOM4J：一款非常优秀的解析器
  - **Jsoup**：jsoup 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容。它提供了一套非常省力的API，可通过DOM，CSS以及类似于jQuery的操作方法来取出和操作数据。
  - PULL：Android操作系统内置的解析器，sax方式的。

![](images\dom解析过程.PNG)

![](images\sax解析过程.PNG)

### 1.4.1 Jsoup

* 快速入门步骤：
    1. 导入jar包，记得`add as library`
    2. 获取Document对象
    3. 获取对应的标签Element对象
    4. 获取数据
* **Jsoup**：工具类，可以解析html或xml文档，返回Document
    * parse：解析html或xml文档，返回Document
        * `parse(File file, String charsetName)`：解析xml或html文件的。
        * `parse(String html)`：解析xml或html字符串
        * `parse(URL url, int timeoutMillis)`：通过网络路径获取指定的html或xml的文档对象，**爬虫**
* **Document**：文档对象。代表内存中的dom树。继承自Element对象
    * 获取Element对象
        * `getElementsByTag(String tagName)`：根据**标签名称**获取元素对象集合
        * `getElementsByAttribute(String key)`：根据**属性名称**获取元素对象集合
        * `getElementsByAttributeValue(String key, String value)`：根据对应的**属性名和属性值**获取元素对象集合
        * `getElementById(String id)`：根据**id**属性值获取唯一的element对象，用得不多
* **Elements**：元素Element对象的集合。可以当做 `ArrayList<Element>`来使用
* **Element**：元素对象
    * 获取**子元素对象**
        * `getElementsByTag(String tagName)`：根据标签名称获取元素对象集合
        * `getElementsByAttribute(String key)`：根据属性名称获取元素对象集合
        * `getElementsByAttributeValue(String key, String value)`：根据对应的属性名和属性值获取元素对象集合
        * `getElementById(String id)`：根据id属性值获取唯一的element对象
    * 获取**属性值**
      * `String attr(String key)`：根据属性名称获取属性值
    * 获取**文本**内容
      * `String text()`：获取文本内容
      * `String html()`：获取标签体的所有内容(包括字标签的字符串内容)
* **Node**：节点对象，是Document和Element的父类

* **快捷查询方式**：

  * selector：通过`Document`或`Element`来调用即可，一般用前者
       * 使用的方法：`Elements select(String cssQuery)`

       * 语法：参考`Selector`类中定义的语法，和CSS选择器语法一致

         ```java
         //1.获取student.xml的path
         String path = JsoupDemo5.class.getClassLoader().getResource("student.xml").getPath();
         //2.获取Document对象
         Document document = Jsoup.parse(new File(path), "utf-8");
         
         //3.查询name标签
         Elements elements = document.select("name");
         System.out.println(elements);
         //4.查询id值为itcast的元素
         Elements elements1 = document.select("#itcast");
         System.out.println(elements1);
         //5.获取student标签并且number属性值为heima_0001的直接age子标签
         //5.1.获取student标签并且number属性值为heima_0001
         Elements elements2 = document.select("student[number=\"heima_0001\"]");
         System.out.println(elements2);
         //5.2获取student标签并且number属性值为heima_0001的直接age子标签
         Elements elements3 = document.select("student[number=\"heima_0001\"] > age");
         System.out.println(elements3);
         ```

  * XPath：XPath即为XML路径语言，它是一种用来确定XML（标准通用标记语言的子集）文档中某部分位置的语言
    * 使用Jsoup的Xpath需要额外导入jar包。

    * 查询w3cshool参考手册，使用xpath的语法完成查询

      ```java
      //1.获取student.xml的path
      String path = JsoupDe mo6.class.getClassLoader().getResource("student.xml").getPath();
      //2.获取Document对象
      Document document = Jsoup.parse(new File(path), "utf-8");
      //3.根据document对象，创建JXDocument对象
      JXDocument jxDocument = new JXDocument(document);
      
      //4.结合xpath语法查询
      //4.1查询所有student标签
      List<JXNode> jxNodes = jxDocument.selN("//student");
      //4.2查询所有student标签下的name标签
      List<JXNode> jxNodes2 = jxDocument.selN("//student/name");
      //4.3查询student标签下带有id属性的name标签
      List<JXNode> jxNodes3 = jxDocument.selN("//student/name[@id]");
      //4.4查询student标签下带有id属性的name标签 并且id属性值为itcast
      List<JXNode> jxNodes4 = jxDocument.selN("//student/name[@id='itcast']");
      
      //可以通过
      getElement() //将JXNode转为Element对象;
      getTextVal() //获取文本
      ......
      ```


### 1.4.2 dom4j

- dom4j是一个组织针对xml解析，提供的解析器 dom4j，需要导入dom4j提供jar包。eclipse操作如下：

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

  - document里面的方法 `getRootElement()` ：获取根节点 返回的是Element接口，父接口也是Node

  - element和Node里的方法：

    - `getParent()`：获取父节点

    - `addElement()`：添加标签

    - `element(qname)`：表示获取标签下面的**第一个子标签**，qname为标签名称

    - `elements(qname)`：获取标签下面是这个**名称的所有子标签**（一层），返回**List集合**

    - `elements()`：获取标签下面的**所有一层子标签**，，返回**List集合**

      **解析是从上到下解析**

* **查询操作**

  查询所有/第一个/第二个name元素里面的值(**getText**())：

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

- **添加操作**

  在第一个p1标签**末尾添加**一个元素 `<sex>nv</sex>`

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

  在**特定位置添加**元素。第一个p1下面的age标签之前添加 `<school>ecit.edu.cn</schlool>`

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

- **修改节点**

  修改第一个p1下面的age元素的值 `<age>30</age>`。age.**setText**("value")

- **删除节点**

  删除第一个p1下面的`<school>ecit</school>`元素

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

- **获取属性**

  获取第一个p1里面的属性为id1的**值**

  ```java
  Element p1 = root.element("p1");
  System.out.println(p1.attributeValue("id1"));
  ```



- **XPath**：可以**直接获取到某个元素**

- * AAA：选取 AAA元素的**所有子节点**
  * /AAA：选取**根元素AAA**。假如路径起始于正斜杠( / )，则此路径始终代表到某元素的绝对路径！
  * AAA/BBB：选取属于 **AAA的子元素的所有 BBB元素**
  * //AAA：选取**所有 AAA子元素**，而不管它们在文档中的位置
  * AAA//BBB：选择属于 bookstore 元素的后代的所有 book 元素，而不管它们位于 bookstore 之下的什么位置。

  - **/***：这层所有元素

    **//***：所有元素

  - **BBB[1]**：　表示第一个BBB元素
    **BBB[last()]**：表示最后一个BBB元素

  - **//BBB[@id]**： 表示只要BBB元素上面有id属性，都得到

  - **//BBB[@id='b1']** 表示元素名称是BBB,在BBB上面有id属性，并且id的属性值是b1

- 使用dom4j支持XPath具体操作*

  默认的情况下，dom4j不支持，引入支持XPath的jar包，lib中使用` jaxen-1.1-beta-6.jar`并导入

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



### 1.4.3 jaxp

- jaxp是javase的一部分，在jdk的javax.xml.parsers包里有四个类

#### 1、DOM

- **DocumentBuilderFactory**： 解析器工厂（抽象类），通过**`newInstance()`**方法获取实例

- **DocumentBuilder**  : 解析器类（抽象类），通过`DocumentBuilderFactory.newDocumentBuilder()`方法获取

  - **方法** ：**`parse("xml路径")`**，解析xml ，返回` Document 对象，也是一个接口，父节点是Node

  - **Document里面方法：**

    - `getElementsByTagName(String tagname)` ：得到标签，返回集合 NodeList

    - `createElement(String tagName)`：创建标签

    - `createTextNode(String data)` ：创建文本

    - `appendChild(Node newChild)`：把文本添加到标签下面

    - `removeChild(Node oldChild)` ：删除节点

    - `getParentNode()` ：获取父节点

    - `getChildNodes()`：获取子节点，java中没有兼容问题

    - `getTextContent()`：得到标签里面的内容

    - **NodeList list**：`getLength()`、`item(int index)`

      ```java
      for(int i=0;i<list.getLength();i++) {
      	list.item(i)
      }
      ```

- **查询操作**

  查询xml中所有的name元素的值、第一个name的值

  ```java
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

  ```java
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

  ```java
  //......
  Node node = document.getElementsByTagName("sex").item(0);
  node.setTextContent("man");
  //......
  ```

- **删除节点(通过父节点)**

  ```java
  //......
  Node sex = document.getElementsByTagName("sex").item(0);
  Node parentnode = sex.getParentNode();
  parentnode.removeChild(sex);
  //......
  ```

- **遍历节点**

  ```java
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


#### 2、SAX

- **SAXParserFactory**: 解析器工厂（抽象类），通过**`newInstance()`**方法获取实例

- **SAXParser**：解析器类（抽象类），通过`SAXParserFactory.newSAXParser()` 方法获得
  - **方法**：**`parse(File f, DefaultHandler dh)`**，参数：xml的路径；事件处理器
    - **`DefaultHandler`**：创建一个类，继承事件处理器的类，重写里面的三个方法

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







# 2 Tomcat服务器

* **服务器**：**安装**了**服务器软件**的**计算机**

  * **服务器软件**：接收用户的请求，处理请求，做出响应
  * **web服务器软件**：接收用户的请求，处理请求，做出响应。
    * 在web服务器软件中，可以**部署web项目**，让用户通过浏览器来访问这些项目
    * web容器（动态资源的容器）

* 常见与Java相关的web服务器软件：

  * webLogic：oracle公司，大型的JavaEE服务器，支持所有的JavaEE规范，收费的。

  * webSphere：IBM公司，大型的JavaEE服务器，支持所有的JavaEE规范，收费的。

  * JBOSS：JBOSS公司的，大型的JavaEE服务器，支持所有的JavaEE规范，收费的。

  * **Tomcat**：Apache基金组织，中小型的JavaEE服务器，仅仅支持少量的JavaEE规范如servlet/jsp。开源的，免费的。

    > JavaEE：Java语言在企业级开发中使用的技术规范的总和，一共规定了13项大的规范

* **Tomcat服务器**

  1. 下载：[Tomcat官网](https://tomcat.apache.org/)，下载二进制文件中核心文件解压版。（以8.0为例）

  2. 安装：解压安装，目录建议不要有中文和空格

  3. 卸载：删除目录即可

     ![](F:\GitHub\Studying\Java Web\images\Tomcat.png)

  4. **启动**：**`startup.bat`**。访问：http://localhost:8080 或 http://127.0.0.1:8080 或 替换为服务器所在的IP

     - 若出现启动时黑窗口一闪而过，则需要正确配置`JAVA_HOME`环境变量
     - 若出现启动报错，如端口已被占用，
       - 可以杀死占用的进程（`netstat -ano`查找PID）
       - 在`server.xml`中的`<Connector>`标签修改自身的端口号。可将Tomcat端口号修改为80(HTTP协议默认)

  5. **关闭**：**`shutdown.bat`或在启动窗口`Ctrl+C`**。不建议强制关闭窗口来关闭

  6. 配置

     - **部署项目的方式**

       1. **直接将项目放到webapps目录下**；或打为**war包**后放入，war包会自动解压缩，删除war包后自动删除项目

          `/hello`：项目访问路径-->==**虚拟目录（IDEA中Application context，若为“/”则访问时不用写项目路径）**==

       2. 不用复制，**配置server.xml**，在`<Host>`元素中添加`<Context>`元素

          `<Context docBase="C:\hello" path="/hello" />`，分别为项目的绝对路径、虚拟目录

       3. 在`conf/catalana/localhost`目录下创建`虚拟目录名称.xml`文件，也称**热部署**。

          `<Context docBase="C:\hello">`

* **静态项目和动态项目**

  * 静态项目：如，在webapps目录下创建一个目录（项目目录），在目录中创建一个html文件

  * ==**动态项目**==：在webapps目录下放置如下：

    |—hello   项目目录

    ​	|—index.html   **应用资源**，可以放在文件夹中
    ​		
      	|—==WEB-INF==   大写，这个目录下的东西是**无法通过浏览器直接访问**
    ​		
      		|—**web.xml**   web项目的核心配置文件，对项目进行**配置**。可以用**注解替代**
    ​		
      		|—**classes**   存放**class字节码文件**的目录
    ​		
      		|—**lib**   放置依赖的**jar包**

* **IDEA与tomcat的相关配置**

  * IDEA会为每一个tomcat部署的项目**单独建立一份配置文件**
    * 控制台的LOG：`Using CATALINA_BASE:"C:\Users\Conanan\.IntelliJIdea2018.2\system\tomcat\test"`
  * 工作空间项目和tomcat部署的web项目
    * tomcat真正访问的是“tomcat部署的web项目”，从上述目录配置文件中即可找到目录的配置，"tomcat部署的web项目"对应着"工作空间项目" 的web目录下的所有资源
    * WEB-INF目录下的资源不能被浏览器直接访问。
  * 断点调试：使用"小虫子"启动 dubug 启动


> **理解server.xml（了解）**
>
> |—Server：根元素，表示整个**服务器的配置信息**
>   	|—Service：在Server中只能有一个，表示**服务** 
> ​    		|—Connector：可以有N个，它表示**连接**
> ​    		|—Engine：只能有一个，表示**引擎**，它是Service组件的核心
> ​      			|—Host：可以有N个，每个表示一个**虚拟主机** 
> ​        			|—Context：可以有N个，每个表示一个**应用**，外部应用必须部署，也可以用如下第3种

> web.xml文件的继承（了解） 
>
> - 在${CATALINA_HOME}\conf\web.xml中，相当于写到了每个项目的web.xml中，它是所有web.xml的父文件
>
>   ```xml
>   //它的优先级最低，如果一个请求没有人处理，那么它来处理！
>   //当访问路径不存在时，会执行该Servlet！其实我们在访问index.html时也是在执行这个Servlet
>   <servlet-name>default</servlet-name>
>   <servlet-class>org.apache.catalina.servlets.DefaultServlet</servlet-class>
>   <servlet-name>default</servlet-name>
>   <url-pattern>/</url-pattern>//匹配所有URL,优先级最低
>   ```
>
>   ```xml
>   //任何URL后缀为jsp的访问，都会执行名为jsp的Servlet
>   <servlet-name>jsp</servlet-name>
>   <url-pattern>*.jsp</url-pattern>
>   <url-pattern>*.jspx</url-pattern>
>   ```
>
>   ```xml
>   <session-timeout>30</session-timeout>//session的默认超时时间为30分钟
>   ```
>
>   ```xml
>   //MIME类型用来标识网络上资源的媒体类型
>   <mime-mapping>
>       <extension>bmp</extension>
>       <mime-type>image/bmp</mime-type>
>   </mime-mapping>
>   ```
>
>   ```xml
>   //在应用的web.xml中如果没有对<welcome-file-list>进行覆盖，那么默认主页为...
>   <welcome-file-list>
>       <welcome-file>index.html</welcome-file>
>       <welcome-file>index.htm</welcome-file>
>       <welcome-file>index.jsp</welcome-file>
>   </welcome-file-list>
>   ```

> 映射虚拟主机（了解）：我们的目标是，在浏览器中输出：<http://www.hello.cn>就可以访问我们的项目
>
> 1. 修改Tomcat端口号为80
>
> 2. 在本机上可以解析域名为127.0.0.1，这需要修改C:\WINDOWS\system32\drivers\etc\hosts文件，添加对<http://www.hello.cn>和127.0.01的绑定关系
>
> 3. 在server.xml文件中添加一个`<Host>`（主机）
>
>    ```xml
>    <Host name="www.hello.cn" appBase="F:/hello" unpackWARs="true" autoDeploy="true">
>    </Host>
>    //1.虚拟主机名 2.当前虚拟主机的应用程序存放目录 
>    //3.目录下创建名为ROOT的应用，因为一个主机只可以有一个名为ROOT的应用,访问是可以不给出应用名称
>    ```





# 3 Servlet

> Servlet（server applet）：运行在服务器端的小程序。是**JavaWeb三大组件之一**（Servlet、Filter、Listener），可以**接收请求数据、处理请求、完成响应**

* ==Servlet就是一个**接口**，定义了**Java类能被**浏览器访问到(**或者说被tomcat识别**)的**规则**。==

## 3.1 实现Servlet的方式

> **浏览器访问Servlet**？给Servlet指定一个**路径**，浏览器通过访问路径来访问。一般写在**web.xml**中或直接**注解**

1. 实现`javax.servlet.Servlet`接口

2. 继承`javax.servlet.GenericServlet`抽象类

3. 继承`javax.servlet.http.HttpServlet`抽象类 

   通常我们会去继承`HttpServlet`类来完成我们的Servlet，但学习还要从`javax.servlet.Servlet`接口开始

### 3.1.1 实现javax.servlet.Servlet接口

```java
//初始化，在Servlet对象创建后马上执行，只执行一次
public void init(ServletConfig servletConfig) throws ServletException {
	System.out.println("init...");
}
//获取Servlet配置信息,
public ServletConfig getServletConfig() {
	return null;
}
//服务，每次处理请求会被调用
public void service(ServletRequest servletRequest, ServletResponse servletResponse) 
		throws ServletException,  IOException {
		System.out.println("service...");
		servletResponse.getWriter().write("hello servlet.");	
}
//获取Servlet信息，如版本，作者等。一般不会去实现
public String getServletInfo() {
	return null;
}
//销毁，服务器正常关闭，在Servlet被销毁之前调用，只会被调用一次
public void destroy() {
	System.out.println("destroy...");
}
```

* **配置web.xml**

```xml
<servlet>
    <servlet-name>name</servlet-name>//相同代号
    <servlet-class>cn.itcast01.AServlet</servlet-class>//要访问的类，全类名
</servlet>
<servlet-mapping>
    <servlet-name>name</servlet-name>//相同代号
    <url-pattern>/MyServlet</url-pattern>//设置URL
</servlet-mapping>
<!--  访问时：http://localhost:8080/MyServlet ，前提是IDEA中Application context配置的是“/” -->
```

* ==**执行原理**==

  1. 当Tomcat服务器接受到客户端浏览器的请求后，会解析请求URL路径，获取访问的Servlet的资源路径

  2. 查找web.xml文件，是否有对应的`<url-pattern>`标签体内容。

  3. 如果有，则通过`<servlet-name>`找到对应的`<servlet-class>`全类名

  4. Tomcat会将字节码文件加载进内存，并且通过反射创建其对象，调用其方法

     ==即Servlet**类**由我们来写，但**对象由服务器来创建**，并且**由服务器来调用相应的方法**==

* ==**生命周期方法**==

  * `void init(ServletConfig)`：**创建Servlet对象==后==**立即执行初始化方法（只1次）；
    * Servlet的`init`方法只执行一次，说明**一个Servlet在内存中只存在一个对象(==单例==)**
      * 多用户同时访问可能存在线程安全问题
      * 解决：尽量不要在Servlet中定义成员变量。即使定义了成员变量，也不要对其修改值
    * Servlet什么时候被创建？
      1. **默认**情况下，**第一次被访问时**，Servlet被创建。即`<load-on-startup>`的值默认为负数。
      2. 可以**配置**执行Servlet的**创建时机**，在注解中配置`loadOnStartup`，给出一个**非负整数**即可，数字**越小优先级越高**。

  * `void service(ServletRequest request, ServletResponse response)`：**每次处理请求**时都会被调用；

  * `void destroy()`：服务器**正常关闭**，**销毁Servlet对象==前==**执行释放资源的方法（只1次）；

### 3.1.2 继承javax.servlet.GenericServlet抽象类

* `GenericServlet`是`Servlet`接口的**实现类**，但它是一个**抽象类**，它**唯一的抽象方法就是`service()`方法**，它将Servlet接口中的其他4个方法做了空实现。
* GenericServlet**实现了ServletConfig接口**（4个方法）
* GenericServlet**添加了`init()`方法**，该方法会**被`init(ServletConfig)`方法调用**

  如果希望对Servlet进行初始化，那么**应该重写`init()`方法**，而不是`init(ServletConfig)`方法

### 3.1.3 继承javax.servlet.http.HttpServlet抽象类

* `HttpServlet`是`GenericServlet`接口的实现类，但它也是一个**抽象类**。对HTTP协议的一种封装，简化操作

* **HttpServlet处理请求顺序**
  1. Tomcat**调用HttpServlet继承的**生命周期方法`service(ServletRequest sr, ServletResponse srr)`对参数进行**强转**为HTTP协议相关的参数类型
  2. 然后**调用HttpServlet本类的`service(HttpServletRequest sr, HttpServletResponse srr)`**方法
  3. 获取请求方法，**根据请求方式**来调用`doGet()`或`doPost()`或其他，需自己**==重写==**，否则调用到该方法时返回**`405`**

  ```java
  //注解的方式直接配置web.xml
  @WebServlet("/ServletAuto")
  public class ServletAuto extends HttpServlet {
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
       throws ServletException,IOException {
  
      }
  
      protected void doGet(HttpServletRequest request, HttpServletResponse response) 
          throws ServletException, IOException {
          response.getWriter().write("hello auto");
      }
  }
  ```


### 3.1.4 ServletConfig接口（了解）

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

## 3.2 Servlet3.0

> JavaEE 6.0 版本及之后的新版本都支持

* 好处：

   * 支持**注解配置**。可以**不需要web.xml了**。

* 步骤：
  1. 创建JavaEE项目，选择Servlet的版本3.0以上，可以不创建web.xml

  2. 定义一个类，实现Servlet接口并重写方法

  3. 在类上使用**@WebServlet**注解，进行配置。**`@WebServlet("资源路径")`**，可以省略`value`或`urlPatterns`

     ```JAVA
     @Target({ElementType.TYPE})
     @Retention(RetentionPolicy.RUNTIME)
     @Documented
     public @interface WebServlet {
         String name() default "";//相当于<Servlet-name>
     
         String[] value() default {};//代表urlPatterns()属性配置
     
         String[] urlPatterns() default {};//相当于<url-pattern>
     
         int loadOnStartup() default -1;//相当于<load-on-startup>
         WebInitParam[] initParams() default {};
         boolean asyncSupported() default false;
         String smallIcon() default "";
         String largeIcon() default "";
         String description() default "";
         String displayName() default "";
     }
     ```

* **urlpartten**：**Servlet访问路径**，用**value**替代了，所以可以不写

     - **一个Servlet**可以定义**多个访问路径** ： `@WebServlet({"/d4","/dd4","/ddd4"})`
     - **路径定义规则**：
         - **`/`**：==**仅不会匹配.jsp**，需要**释放静态资源**==，（查看Tomcat中web.xml中介绍）
             - 它的**优先级最低**，如果一个请求没有人处理，会执行该DefaultServlet！其实我们在访问index.html时也是在执行该DefaultServlet。
             - **不会重写其他Servlet**：即不匹配JSP是因为任何URL后缀为jsp的访问，都会执行名为jsp的Servlet
         - **`/*`**：带通配符（匹配所有**路径型和后缀型**URL，包括.html、.jsp等），==**不建议使用**，**除非用在Filter中**==
             - **会重写其他Servlet**：在访问jsp时会报404
         - `/xxx`：路径匹配
         - `/xxx/xxx`：多层路径，称之为目录结构
         - `*.do`：扩展名匹配，*前不能有/，否则报错



## 3.3 ServletContext

* Java Web四大域对象（前三个为Servlet三大域对象）：

  * application(当前web应用)：**ServletContext**

  * session(一次会话)：**HttpSession**

  * request(一次请求)：**ServletRequest**

  * page(jsp有效)：**PageContext**

* ServletContext：**代表整个web应用**，可以**和程序的容器**(服务器)来**通信**，服务器会为每个应用/WEB站点创建一个ServletContext对象。**创建在服务器启动后**完成，**销毁在服务器正常吃关闭前**完成。**一个项目只有一个ServletContext对象**，我们可以在N多个Servlet中来获取这个唯一的对象，以传递数据
* 获取
  * 通过**`HttpServlet`**或`GenericServlet`获取：`this.getServletContext();`
  * 通过**`request`**对象获取：`request.getServletContext();`	
* 功能：
  1. **获取MIME类型**：`String getMimeType(String file)  `
       * MIME类型：在互联网通信过程中定义的一种文件数据类型。格式：`大类型/小类型`，`text/html`、`image/jpeg`
  2. **域对象**：**共享数据**。ServletContext对象范围是**所有用户所有请求的数据**
     * `getAttribute(String name)`：**获取**域对象中的数据
     * `setAttribute(String name,Object value)`：**存储**一个域属性
     * `removeAttribute(String name)`：**移除**域对象中的域属性，name指定域属性不存在时不变化
     * `Enumeration getAttributeNames()`：获取所有域属性的名称`
  3. ==获取**文件的真实**(服务器)**路径**==：`String getRealPath(String path)`，即当前Web应用下的资源路径，例子如下：
       - web目录下资源访问：`getRealPath("/b.txt")`
       - WEB-INF目录下的资源访问：`getRealPath("/WEB-INF/c.txt")`
       - src目录下的资源访问：`getRealPath("/WEB-INF/classes/a.txt")`，由于src中的文件最终都会放在classes下


* ==**获取资源相关方法对比**==

  * **获取真实(服务器)路径：`getRealPath(String path)`**，可查找范围最广泛
  * **通过类加载器获取资源**：`getResourceAsStream(String path);`，根据classes或src来判断路径
    * `/WEB-INF/classes`（src中的文件最终都会放在classes下）和`/WEB-INF/lib`每个**jar**包！
  * **Class类获取资源**：`InputStream getResourceAsStream(String path)`，
    * 路径以`/`开头，相对classes路径；路径不以`/`开头，相对当前class文件路径

* 网站访问量

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


> 获取应用/WEB站点初始化参数（getInitParameter()）
>
> - Servlet也可以获取初始化参数，但它是局部的参数(一个Servlet只能获取自己的初始化参数，不能获取别人的，即初始化参数只为一个Servlet准备)
>
> - 可以配置**应用的初始化参数**，为所有Servlet而用！这需要使用ServletContext才能使用
>
> - 可以使用ServletContext来获取在web.xml文件中配置的**应用初始化参数**！注意，应用初始化参数与Servlet初始化参数不同（Spring中有使用）
>
>   ```xml
>   <context-param>
>       <param-name>name</param-name>
>       <param-value>zhangsan</param-value>
>   </context-param>
>   ```
>
>   ```java
>   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
>       ServletContext servletContext = this.getServletContext();
>       String msg =(String)servletContext.getInitParameter("name");
>       response.getWriter().write(msg);
>   }
>   ```

​	





# 4 HTTP

- HTTP（Hyper Text Transfer Protocol）：**超文本传输协议**，是用于从万维网服务器传输超文本到本地浏览器的传送协议。

  HTTP是一个基于**TCP/IP**通信协议来**传递数据**（HTML文件, 图片文件, 查询结果等），默认端口号为**80**

- 注意：

  1. HTTP是**无状态**：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快
  2. HTTP是**媒体独立**的：这意味着，只要客户端和服务器知道如何处理的数据内容，任何类型的数据都可以通过HTTP发送。客户端以及服务器指定使用适合的**MIME**-type内容类型 

- 历史版本：

  - 1.0：**每一次请求响应都会建立新的连接**
  - 1.1：**复用连接**



## 4.1 请求数据格式

> HTTP协议有**7种**请求方式，常用的有2种
>
> - GET：
>   1. **请求参数在请求行中**，在url后。相对不太安全
>   2. 请求的url长度有限制的
> - POST：
>   1. **请求参数在请求体中**。相对安全
>   2. 请求的url长度没有限制的

1. **请求行**：`请求方式 请求url 请求协议/版本`
2. **请求头**：`请求头名称: 请求头值`，客户端浏览器告诉服务器一些信息
3. **空行**
4. **请求体(POST有)**：：封装POST请求消息的请求参数

`GET /login.html?name=zhangsan HTTP/1.1` **请求行**：GET请求，请求URL和请求参数，协议及版本

`POST /login.html HTTP/1.1`  【POST】**请求行**：POST请求，请求URL，协议及版本

`Host: localhost`  请求的主机IP，端口号

`Connection: keep-alive`  复用连接，默认为3000ms

`Upgrade-Insecure-Requests: 1`

`Content-Type: application/x-www-form-urlencoded`【POST】表单的数据类型，会使用URL格式编码数据

**`User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.102 Safari/537.36` ** 浏览器告诉服务器，**本机的OS、浏览器相关信息**，解决浏览器的兼容性问题

**`Referer: http://localhost/login.html` ** **请求来自哪里**，地址栏输入的没有这个参数。用于防盗链，统计工作

`Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8`

`Accept-Encoding: gzip, deflate, br` 告诉服务器，当前客户端可以接收的文档类型,`*/*`表示都可以；接收的压缩格式

`Accept-Language: zh-CN,zh;q=0.9,en-GB;q=0.8,en;q=0.7,ja-JP;q=0.6,ja;q=0.5`  当前客户端支持的语言 

`Cookie: Idea-bc6d4892=fe76d3e6-e4aa-46b9-90ea-7af2596fd5f9; `

`JSESSIONID=BC49375C243D10410E648C0B736DD3B1`



`name=zhangsan`   【POST】**请求体内容**



## 4.2 响应数据格式

1. **响应行**：`协议/版本 响应状态码 状态码描述`
2. **响应头**：`头名称：值`
3. **空行**
4. **响应体**

**`HTTP/1.1 200 OK`**   响应**协议**为HTTP1.1，**状态码**为200，表示请求成功，OK是对状态码的解释 

**`Content-Type: text/html;charset=UTF-8`**   服务器告诉客户端 **响应体的MIME类型，编码格式** 

**`Content-disposition:`**  服务器告诉客户端**以什么格式打开响应体数据**

​          值可以是`in-line`：默认值，在**当前页面内打开**；`attachment:filename=xxx`：以**附件形式打开**响应体，**文件下载**

`Server: Apache-Coyote/1.1` //服务器的版本信息

`Content-Length: 106` //响应体为106字节 

`Set-Cookie: JSESSIONID=C97E2B4C55553EAB46079A4F263435A4; Path=/hello` //响应给客户端的Cookie 

`Date: Thu, 16 Aug 2018 07:20:30 GMT` //响应的时间，这可能会有8小时的时区差



`<html>...</html>`等  **响应体，传输的数据**



**状态码**：服务器告诉客户端浏览器本次请求和响应的一个状态。都是3位数字

* 1xx：服务器接收客户端消息，但没有接受完成，等待一段时间后，发送1xx状态码。很少出现
* **2xx**：**成功**。代表：**200**。很少出现，因为绝大部分都是成功，直接显示响应体内容
* **3xx**：**重定向**。代表：**302(重定向)**，**304(访问缓存)**
* 4xx：**客户端错误**。代表如下：
     * **404**（请求路径**没有对应的资源**） 
        * **405**：请求方式**没有对应的doXxx**方法
* 5xx：**服务器端错误**。代表：**500**(**服务器内部出现异常**)

> 302：**重定向**，当响应码为302时，表示服务器会发送一个响应头**Location**，它指定了新请求的URL地址，服务器要求浏览器重新再发一个请求。
>
> 304：当用户第一次请求index.**html**时，服务器会添加一个名为**Last-Modified响应头**，这个头说明了index.html的最后修改时间，浏览器会把index.html内容，以及最后响应时间缓存下来。当用户第二次请求index.html时，在请求中包含一个名为**If-Modified-Since请求头**，它的值就是第一次请求时服务器通过Last-Modified响应头发送给浏览器的值，即index.html最后的修改时间，而服务器端会获取If-Modified-Since值，与index.html的当前最后修改时间比对，如果相同，服务器会发响应码304，表示index.html与浏览器上次缓存的相同，无需再次发送，浏览器可以显示自己的缓存页面，如果比对不同，那么说明index.html已经做了修改，服务器会响应200。

其他响应头

- 告诉浏览器**不要缓存**的响应头：Expires: -1；Cache-Control: no-cache； l  Pragma: no-cache；

- **自动刷新**响应头，浏览器会在3秒之后请求该url  Refresh: 3;url=http://www.itcast.cn 

- HTML中指定响应头，使用`<meta http-equiv="" content="">`

  ```html
  <meta http-equiv="Refresh" content="3;url=http://www.itcast.cn">
  ```



# 5 Request、Response

> 在`javax.servlet`和`javax.servlet.http`(主要用这个)包下

* ==**服务器处理请求**的流程==
  1. 服务器每次收到请求时，都会为这个请求开辟一个**新的线程**，并根据请求**URL中的资源路径**，**创建**对应的**Servlet对象**
  2. 服务器会**创建request对象**并**封装客户端的请求数据**；还会**创建response对象**，用来**封装向客户端发送的响应数据 **
  3. 服务器**将**request和response**对象传递给**Servlet对象的**`service(...)`方法**，并**调用**该方法

## 5.1 Request

### 5.1.1 Request继承体系

​	`ServletRequest`           -- 接口
​		|	继承
​	`HttpServletRequest`	   -- 接口
​		|	实现
​	`org.apache.catalina.connector.RequestFacade`  --类  (Tomcat编写的)

以上体系Response也适用

### 5.1.2 获取请求消息数据

* 获取**请求行**数据：如`GET /day14/login?name=zhangsan HTTP/1.1`

  * 获取请求方式：`String getMethod()`，如`GET` 。HttpServlet已经在内部使用了，以后用不到
  * 获取**请求URL**：`StringBuffer getRequestURL()`，如`http://localhost/day14/login/a.html`。**统一资源定位符**
  * ==获取**请求URI**==：`String getRequestURI()`，如`/day14/login`。**统一资源标识符**，它**包括URL**
  * ==获取**虚拟目录**==：`String getContextPath()`，如`/day14`
  * 获取Servlet路径：`String getServletPath()`，如`/login`
  * 获取get方式请求参数：`String getQueryString()`，如`name=zhangsan`，以后用不到
  * 获取协议及版本：`String getProtocol()`，如`HTTP/1.1`
  * 获取客户机的**IP地址**：`String getRemoteAddr()`

* 获取**请求头**数据

  * ==通过**请求头的名称获取请求头的值**==：`String getHeader(String name)`，不区分大小写。如User-Agent、Referer
  * 获取所有的请求头名称：`Enumeration<String> getHeaderNames()`：

* 获取**请求体**数据（只有POST请求方式，才有请求体，在请求体中封装了POST请求的请求参数）

  * 步骤：

    1. **获取流对象**

       * `BufferedReader getReader()`：获取**字符输入流**，只能操作字符数据

       * `ServletInputStream getInputStream()`：获取**字节输入流**，可以操作所有类型数据。继承了InputStream
          * 在**文件上传**知识点后讲解

    2. **再从流对象中拿数据**

### 5.1.3 获取**请求参数通用方**式

* ==获取**请求参数通用方**式==（不论get还是post请求方式都可以使用）

  * **`String getParameter(String name)`**：==根据**参数名称获取参数值**==    username=zs&password=123

  * `String[] getParameterValues(String name)`：根据参数名称获取参数值的**数组** hobby=xx&hobby=game

  * **`Map<String,String[]> getParameterMap()`**：==获取**所有参数的map集合**==

  * `Enumeration<String> getParameterNames()`：获取所有请求的参数名称

  * ==**中文乱码问题**：==

    * GET方式：Tomcat 8及以上版本已经将get方式乱码问题解决了
    * POST方式：会乱码。在获取参数前，**设置request的编码`request.setCharacterEncoding("utf-8");`**

  * 获取的**参数封装为JavaBean**可以利用Apache提供的`commons-beanutils-1.8.0`工具类，**简化数据封装**

      * JavaBean：标准的Java类，功能为封装数据。要求如下：

          1. **类**必须被**public**修饰
          2. 必须提供**公有的空参的构造器**
          3. 属性一般使用private修饰
          4. 对属性提供**公共get(或is)和set方法**，若只有get方法或set方法，那么这个属性是只读或只写属性！
             * **属性**：**setter和getter方法截取后的产物，与成员变量名无关**。getName() --> Name--> name

      * 方法

        * `setProperty(Object bean, String propName)`

        * `getProperty(Object bean,String propName,String propValue)`：操作的是**属性**

        * **`populate(Object obj , Map map)`**：将**map**集合的键值对信息，封装到对应的**JavaBean**对象中

          ```java
          User user = new User();
          Map<String, String[]> map = request.getParameterMap();
          BeanUtils.populate(user,map); //populate需要try...catch
          ```

### 5.1.4 请求转发

* ==**请求转发**：一种在服务器**内部**的资源跳转方式==

  * 步骤：

    1. **request获取请求转发器对象**：**`RequestDispatcher getRequestDispatcher(String path)`**，**Servlet路径**

    2. 使用RequestDispatcher对象来进行**转发**：`forward(ServletRequest request, ServletResponse response) `

         - 请求包含：`include(request,response)`

              > 请求转发：由下一个Servlet完成响应体！当前Servlet可以设置响应头！（<span style="font-family:monaco;color:red;font-weight:bold">留头不留体</span>）
              > 请求包含：由两个Servlet共同未完成响应体！（<span style="font-family:monaco;color:red;font-weight:bold">都留</span>）

  * 特点：

    1. 浏览器**地址栏路径不发生变化**
    2. 转发是**一次请求一次响应**，使用同一个request和response！
    3. 只能转发到**当前服务器内部资源**中。

### 5.1.5 request域

* 域对象：一个有作用范围的对象，可以在范围内共享数据
* ==**request域**：代表**一次请求的范围**，一般用于**请求转发**的多个资源中**共享数据**==
   * 方法：
      1. **`void setAttribute(String name,Object obj)`**：存储数据
      2. **`Object getAttitude(String name)`**：通过键获取值
      3. **`void removeAttribute(String name)`**：通过键移除键值对

### 5.1.6 获取ServletContext

* `ServletContext getServletContext()`



> 用户登录案例需求：
>
> 1.编写login.html登录页面：username & password 两个输入框
>
> * login.html中form表单的action路径的写法：**虚拟目录+Servlet的资源路径**
>
> 2.使用Druid数据库连接池技术,操作mysql，day14数据库中user表
>
> 3.使用JdbcTemplate技术封装JDBC
>
> 4.登录成功跳转到SuccessServlet展示：登录成功！用户名,欢迎您
>
> 5.登录失败跳转到FailServlet展示：登录失败，用户名或密码错误





## 5.2 Response

* response**类型**为**`HttpServletResponse`**

  `ServletResponse`-->与协议无关的类型；`HttpServletResponse`-->与http协议相关的类型

### 5.2.1 设置响应行中状态码

* **<span style="background:yellow">setStatus(int sc)</span>** --> **设置此响应的状态码**，可以用来发送302、200或者404、500等
  * sendError(int sc [,String msg]) --> 发送错误状态码，还可以带一个错误信息！

### 5.2.2 设置响应头

头就是一个**键值对！**可能会存在一个头（一个名称，一个值），也可能会存在一个头（一个名称，多个值！）
* **<span style="background:yellow">setHeader(String n, String val)</span>**：适用于<span style="background:yellow">**单值**</span>的响应头，例如：response.setHeader("aaa", "AAA")

  * addHeader(String name, String value)：适用于多值的响应头，为每个值分别调用

  * `<meta>`**标签代替响应头**

    ```html
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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

### 5.2.3 设置响应体

> response的两个流，用来向客户端发送数据（不能同时使用，否则抛**IllegalStateException**异常）
>

* **ServletOutputStream（字节输出流）：`resopnse.getOutputStream()`**

* **PrintWriter（字符输出流，需要设置编码）：`response.getWriter()`**，不需要刷新缓冲区


### 5.2.4 重定向

* ==**重定向**：**资源跳转的方式**==

* 设置**状态码**为302，设置**响应头**`Location`

  ```java
  response.setStatus(302);
  response.setHeader("location","/day15/responseDemo2");
  ```

* ==简单的重定向方法==：**`response.sendRedirect()`**

  ```java
  response.sendRedirect("/day15/responseDemo2");
  ```

* ==**forward和redirect的区别**==

  * 转发的特点：forward
    1. **地址栏路径不变**
    2. 转发是**一次请求**，可以**使用request域**来共享数据
    3. 转发只能**访问当前服务器下的资源**
  * 重定向的特点：redirect
    1. **地址栏**发生**变**化
    2. 重定向是**两次请求**。**不能使用request域**来共享数据
    3. 重定向**可以访问其他站点**(服务器)的资源


### 5.2.5 路径的写法

* **相对路径**：通过相对路径不可以确定唯一资源，以**`./`开头（或不写它）的路径**。如：`./index.html`
     * 规则：找到当前资源所属目录和目标资源所属目录之间的相对位置关系。`./`：当前目录；`../`:后退一级目录
* **绝对路径**：通过绝对路径可以确定唯一资源，以**`/`开头的路径**。如：`/day15/responseDemo2`
  * 规则：判断定义的路径是**给谁用**的？**判断将来请求从哪儿发出**
    * **给客户端浏览器使用**：需要**加虚拟目录**(项目的访问路径)，建议**动态获取：`request.getContextPath()`**
      * `<a>`，`<form>`，`redirect路径`。。。
    * **给服务器使用**：**不需要加虚拟目录**
        * `forward路径`

### 5.2.6 输出字符数据到浏览器

> 由于浏览器默认使用GBK或GB2312，而服务器获取的**流**默认使用ISO8859-1，不支持中文。且编码解码不同，必乱码。

* **==获取字符输出流之前==**设置该**流的默认编码**，告诉浏览器**响应体使用的编码**。设置后一步代码时会自动执行上一步，所以可省略

  ```java
  response.setCharacterEncoding("utf-8");//可省略
  response.setHeader("Content-type","text/html;charset=utf-8");//封装的简单方法如下
  ```

* **==简单方法==：`response.setContentType("text/html;charset=utf-8")`**

### 5.2.7 输出字节数据到浏览器

* `getBytes()`，获取字节输出流外，其他和输出字符数据没什么区别

### 5.2.8 验证码

> 本质：图片
> 目的：**防止恶意表单注册**

```java
//1.创建验证码图片对象
BufferedImage image = new BufferedImage(100,50,BufferedImage.TYPE_INT_RGB);

//2.美化图片
//2.1 填充背景色
Graphics g = image.getGraphics();//画笔对象
g.setColor(Color.PINK);//设置画笔颜色
g.fillRect(0,0,width,height);

//2.2画边框
g.setColor(Color.BLUE);
g.drawRect(0,0,width - 1,height - 1);

String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
//生成随机角标
Random ran = new Random();
for (int i = 1; i <= 4; i++) {
    int index = ran.nextInt(str.length());
    char ch = str.charAt(index);//随机字符
    g.drawString(ch+"",width/5*i,height/2);//写验证码
}

//2.4画干扰线
g.setColor(Color.GREEN);
//随机生成坐标点
for (int i = 0; i < 10; i++) {
    int x1 = ran.nextInt(width);
    int x2 = ran.nextInt(width);
    int y1 = ran.nextInt(height);
    int y2 = ran.nextInt(height);
    g.drawLine(x1,y1,x2,y2);
}

//3.将图片输出到页面展示
ImageIO.write(image,"jpg",response.getOutputStream());
```

```javascript
//点击图片更换下一张
document.getElementById("verifyCode").onclick = function () {
    //加时间戳的毫秒值
    var date = new Date().getTime();
    this.src = "/day15/checkCodeServlet?" + date;
}
```



## 5.3 URL编码

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




## 案例 文件下载

* 文件下载需求：

  1. 页面显示超链接
  2. 点击超链接后弹出下载提示框
  3. 完成图片文件下载

* 分析：

  1. 超链接指向的资源如果能够被浏览器解析，则在浏览器中展示，如果不能解析，则弹出下载提示框。不满足需求
  2. 要求任何资源都必须弹出下载提示框
  3. 使用响应头设置资源的打开方式：`content-disposition:attachment;filename=xxx`

* 步骤：

  1. 定义页面，编辑超链接href属性，指向Servlet，**传递资源名称`?filename=xxx`**
  2. 定义Servlet
    1. 获取文件名称
    2. 使用字节输入流加载文件进内存
    3. 指定response的响应头： `content-disposition:attachment;filename=xxx`
    4. 将数据写出到response输出流

* 中文文件名问题

  * 解决思路：
    1. 获取客户端使用的浏览器版本信息
    2. 根据不同的版本信息，设置filename的编码方式不同

  ```java
  //1.获取请求参数，文件名称
  String filename = request.getParameter("filename");
  //2.使用字节输入流加载文件进内存
  //2.1找到文件服务器路径
  String realPath = this.getServletContext().getRealPath("/img/" + filename);
  //2.2用字节流关联
  FileInputStream fis = new FileInputStream(realPath);
  
  //3.设置response的响应头
  //3.1设置响应头类型：content-type
  String mimeType = servletContext.getMimeType(filename);//获取文件的mime类型
  response.setHeader("content-type",mimeType);
  //3.2设置响应头打开方式:content-disposition
  
  //解决中文文件名问题
  //1.获取user-agent请求头、
  String agent = request.getHeader("user-agent");
  //2.使用工具类方法编码文件名即可
  filename = DownLoadUtils.getFileName(agent, filename);
  response.setHeader("content-disposition","attachment;filename="+filename);
  
  //4.将输入流的数据写出到输出流中
  ServletOutputStream sos = response.getOutputStream();
  byte[] buff = new byte[1024 * 8];
  int len = 0;
  while((len = fis.read(buff)) != -1){
      sos.write(buff,0,len);
  }
  fis.close();
  ```






# 6 会话技术

* 会话：一次会话中包含**多次请求和响应**
  * ==一次会话：**浏览器第一次给服务器资源发送请求**，会话建立，**直到有一方断开为止**==
* 功能：在一次会话的范围内的多次请求间，**共享数据**
* 方式：
  * **客户端会话**技术：**Cookie**
   * **服务器端会话**技术：**Session**

## 6.1 Cookie

* **Cookie**：客户端会话技术，先由**服务器保存Cookie到浏览器**，在下次浏览器请求服务器时把Cookie再**归还**给服务器

* 使用步骤：

  1. **创建**Cookie对象，绑定数据：`new Cookie(String name, String value)`
      - 若Cookie名称相同，值会被覆盖。也可以修改值：`setValue(String value)`
  2. **Respongse添加并发送**Cookie对象：`response.addCookie(Cookie cookie)`
  3. **获取**Cookie，拿到数据：`Cookie[]  request.getCookies()`

* 实现原理：基于响应头`set-cookie`和请求头`cookie`实现

* Cookie**细节**

  1. ==**一次**可不**可以发送多个Cookie**？==

    - 可以创建多个Cookie对象，使用response调用多次addCookie方法发送cookie即可。

  2. ==Cookie在浏览器中**保存多长时间？**==

    - **默认**情况下，**当浏览器关闭后，Cookie数据被销毁**
    - ==**持久化存储**==：**`setMaxAge(int seconds)`**，单位为秒
        - **负**数：**默认值**
        - **零**：浏览器会**马上删除**这个Cookie
        - **正**数：将Cookie数据写到硬盘的文件中，并**指定cookie存活时间**，时间到后，Cookie文件自动失效

  3. Cookie**能不能存中文**？**用URL编解码**

    * 在**tomcat 8及之后**，cookie**支持中文数据**。**特殊字符还是不支持**，建议使用**URL**编码存储，URL解码解析

    * 在tomcat 8 之前 cookie中不能直接存储中文数据。需要将中文数据转码，一般采用**URL编码**

  4. ==Cookie**共享问题**？==

    - 假设在**一个Tomcat服务器中**，部署了多个web项目，**哪些请求需要携带哪些Cookie给服务器呢**？

      * **默认不设置path**情况下，只会在请求了 设置Cookie的Servlet的同级和子目录才会携带Cookie中存储的数据

         ```
         aCookie.path = /day11_1; 
         bCookie.path = /day11_1/jsps; 
         访问：/day11_1/index.jsp时，携带：aCookie
         访问：/day11_1/jsps/a.jsp时，携带：aCookie、bCookie
         ```

      * 如果**要共享**需设置Cookie的获取范围：**`setPath(String path)`，path设置为`/`**，代表当前Tomcat

    - **不同的Tomcat**服务器间Cookie**共享**问题？

      - **`setDomain(String path)`**：如果**设置一级域名相同**，那么**多个服务器之间cookie可以共享**

        `setDomain(".baidu.com")`，那么`tieba.baidu.com`和`news.baidu.com`中cookie可以共享

* Cookie的**特点**

  1. ==cookie**存储数据在客户端浏览器(不安全)，不能跨浏览器**==

  2. HTTP协议规定：浏览器对于单个cookie 的大小限制(**4kb**) ，对同一个域名下的总cookie数量限制(**20个**)

* Cookie的作用

  * Cookie一般用于存储**少量**的**不太敏感**的数据

  * 在**不登录的情况下**，完成服务器对客户端的身份识别。如关闭搜索提示框，显示上次登录名

    * 上次访问时间案例
      1. 访问一个Servlet，如果是第一次访问，则提示：您好，欢迎您首次访问
      2. 如果不是第一次访问，则提示：欢迎回来，您上次访问时间为:显示时间字符串

      ```java
      response.setContentType("text/html;charset=utf-8");
      Cookie[] cookies = request.getCookies();
      boolean flag = false;
      
      //URL编码时间
      Date date = new Date();
      SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
      String s = format.format(date);
      s = URLEncoder.encode(s, "utf-8");
      
      for (Cookie cookie : cookies) {
          //不是第一次访问
          if ("last_time".equals(cookie.getName())){
              //URL解码并显示
              String value = URLDecoder.decode(cookie.getValue(), "utf-8");
              response.getWriter().write("欢迎再次回来！您上次访问时间为："+value);
              //修改last_time的cookie值，并发送
              cookie.setValue(s);
              response.addCookie(cookie);
              flag = true;//不是第一次访问，设置标记
              break;
          }
      }
      //第一次访问
      if (!flag) {
          //创建Cookie并设置存活时间，发送Cookie
          Cookie time = new Cookie("last_time", s);
          time.setMaxAge(30*24*60*60);
          response.addCookie(time);
          response.getWriter().write("欢迎您首次访问");
      }
      ```


## 6.2 Session

* Session：**服务器端会话技术**，在**一次会话的多次请求间共享数据**，将数据**保存在服务器端的对象**中。使用`HttpSession`类

* ==Session的实现是**依赖于Cookie**==，或URL重写(了解)

* **获取**HttpSession对象：`HttpSession session = request.getSession();`

* **使用**HttpSession**域对象**

  * `Object getAttribute(String name)`
  * `void setAttribute(String name, Object value)`
  * `void removeAttribute(String name)`

* Session细节

  1. ==当**客户端关闭后**，**服务器不关闭**，两次获取Session**对象是否为同一个**？==

     - **默认情况下不是同一个**

     - **如果需要相同**，则可以**创建Cookie**，键为`JSESSIONID`，值为**`getId()`**，设置`setMaxAge()`，让Cookie持久化

       ```java
       HttpSession session = request.getSession();
       Cookie c = new Cookie("JSESSIONID",session.getId());
       c.setMaxAge(60*60);
       response.addCookie(c);
       ```

  2. ==**客户端不关闭**，**服务器关闭后**，两次获取的Session**对象是否为同一个**吗？==

     - **不是同一个**，但是要**确保数据不丢失**。**Tomcat自动完成**以下工作
       * Session的**钝化**：在服务器正常关闭之前，将Session对象系列化到硬盘上。Tomcat中**work**目录下
           * IDEA会钝化到单独创建的配置文件中work目录，但是重启服务器后先删除work目录导致不能活化
       * Session的**活化**：在服务器启动后，将Session文件转化为内存中的Session对象即可。

  3. ==Session什么时候被销毁？即**失效时间**==

     1. **服务器关闭**

     2. session对象调用**`invalidate()`**就失效。可以通过`isNew()`方法判断Session是否是新的，还没响应给客户端

     3. session默认失效时间 **30分钟**。Tomcat中web.xml配置修改如下

       ```xml
       <session-config>
            <session-timeout>30</session-timeout>
        </session-config>
       ```

* Session的特点

  * session用于**存储一次会话的多次请求的数据**，**存在服务器端**
  * session可以存储**任意类型**，**任意大小**的数据

* session与Cookie的**区别**：

  1. session存储数据在服务器端，Cookie在客户端
  2. session数据安全，Cookie相对于不安全
  3. session没有数据大小限制，Cookie有限制







> ### HttpSession原理（理解）
>
> - 服务器不会马上给你创建session，在第一次获取session时，才会创建！request.getSession();
>
> - request.getSession()方法：
>
>   - 获取Cookie中的JSESSIONID：
>     - 如果JSESSIONID不存在，创建session并保存起来，把新创建的JSESSIONID保存到Cookie中
>     - 如果JSESSIONID存在，通过JSESSIONID查找session对象，如果没有查找到，创建session并保存起来，把新创建的JSESSIONID保存到Cookie中
>     - 如果JSESSIONID存在，通过JSESSIONID查找到了session对象，那么就不会再创建session对象了，返回session
>   - 如果创建了新的session，**浏览器会得到一个包含了JSESSIONID的Cookie**，这个Cookie的生命为默认-1
>   - 下次请求时，再次执行request.getSession()方法时，因为可以通过Cookie中的JSESSIONID找到session对象，所以与上一次请求使用的是同一session对象。
>
> - request.getSession(false)、request.getSession(true)、request.getSession()，后两个方法效果相同
>
>   第一个方法：如果session缓存中(如果cookie不存在)，不存在session，那么返回null，不会创建session对象

> ### URL重写（理解）
>
> - 就是把所有的页面中的路径，都使用response.encodeURL("..")处理一下！
>   - session依赖Cookie，目的是让客户端发出请求时归还sessionId，这样才能找到对应的session
>   - 如果**客户端禁用了Cookie**，那么就无法得到sessionId，那么session也就无用了！
>   - 也可以**使用URL重写**来替代Cookie
>     - 让网站的所有超链接、表单中都添加一个特殊的请求参数，即sessionId
>     - 这样服务器可以通过获取请求参数得到sessionId，从而找到session对象。
>   - response.encodeURL(String url)
>     - 该方法会对url进行智能的重写：当请求中没有归还sessionid这个cookie，那么该方法会重写url，否则不重写！当然url必须是指向本站的url。



## 案例 用户登录加验证码

- 案例相关页面和Servlet

  - login.jsp：登录页面
  - succ1.jsp：只有登录成功才能访问的页面
  - succ2.jsp：只有登录成功才能访问的页面 
  - LoginServlet：校验用户是否登录成功！

- 各页面和Servlet内容

  - login.jsp：提供登录表单，提交表单请求LoginServlet
  - LoginServlet：获取请求参数，校验用户是否登录成功
  - 失败：保存错误信息到request域，转发到login.jsp(login.jsp显示request域中的错误信息)
  - 成功：保存用户信息到session域中，重定向到succ1.jsp页面，显示session域中的用户信息
  - succ1.jsp：从session域获取用户信息，如果不存在，显示“您还没有登录”。存在则显示用户信息
  - succ2.jsp：从session域获取用户信息，如果不存在，显示“您还没有登录”。存在则显示用户信息

  只要用户没有关闭浏览器，session就一直存在，那么保存在session中的用户信息也就一起存在！那么用户访问succ1和succ2就会通过

- 添加图片验证码，利用VerifyCode.jar包来完成其Servlet，验证码只能使用一次

  - VerifyCodeServlet：生成验证码

```java
//VerifyCodeServlet.java
VerifyCode verifyCode = new VerifyCode();
BufferedImage bi = verifyCode.getImage();
request.getSession().setAttribute("text",verifyCode.getText());
VerifyCode.output(bi,response.getOutputStream());

//LoginServlet.java
//验证码
String yanzhengma = request.getParameter("VerifyCode");//还应该判断值不为""
//获取生成的验证码的文本值
HttpSession session = request.getSession();
String code = ((String) session.getAttribute("text"));
//使用完立刻删除Session中的验证码文本值，保证验证码只能使用一次
session.removeAttribute("text");
if(!yanzhengma.equalsIgnoreCase(code)){
    request.setAttribute("message","验证码错误");
    request.getRequestDispatcher("/Login/login.jsp").forward(request,response);
    return;
}

request.setCharacterEncoding("utf-8");//POST请求编码的解析采用utf-8
String username = request.getParameter("username");//获取表单数据，可以用map转bean的工具类
String password = request.getParameter("password");//获取表单数据
if(username.equals("hehe")&&password.equals("123")){//判断是否可以登录，可以改为数据库操作
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

<!--显示错误信息-->
<p style="color: red;font-weight: bold">
    <%=request.getAttribute("message")== null ? "" : request.getAttribute("message")%>
</p>

<!--改变验证码的Js代码，点击图片更换下一张-->
<script>
    document.getElementById("img1").onclick = function () {
        //加时间戳的毫秒值
        var date = new Date().getTime();
        this.src = "/VerifyCodeServlet?" + date;
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





# 7 JSP

## 7.1 JSP简介

* JSP（java server pages）其实就是Servlet，用于简化Servlet的书写
  - Servlet：
    - 优点：动态资源，可以编程
    - 缺点：不适合设置html响应体，需要大量的response.getWriter().print("< html>")
  - html：
    - 优点：不用为输出html标签而发愁
    - 缺点：html是静态页面，不能包含动态信息
  - JSP：
    - 优点：在原有html的基础上添加java脚本，构成jsp页面

* **JSP原理**
  * 当**jsp页面第一次被访问**时，**服务器会把jsp编译成java文件**（这个java其实**继承一个servlet**类）
  * 然后再把**java编译成.class**
  * 然后**创建该类JSP(Servlet)对象**
  * 最后**调用它的service()方法**（第二次请求同一jsp时，直接调用service()方法）
* JSP和Servlet的分工
  - Servlet：作为请求中**处理数据**的环节
  - JSP：作为**请求发起页面**，例如显示表单、超链接。作为**请求结束页面**，例如显示数据

## 7.2 JSP的脚本

* JSP= html + Java脚本 + JSP标签(指令)
* 3种Java脚本：
  * `<%...%>`：定义的java代码，在service方法中。**service方法**中可以定义什么，该脚本中就可以定义什么。
  * `<%=...%>`：定义的java代码，会输出到页面上。**输出语句**中可以定义什么，该脚本中就可以定义什么
  * `<%!...%>`：定义的java代码，在jsp转换后的java**类的成员位置**
* `<%-- ... --%>`：**注释**，也可以注释HTML。当服务器把jsp编译成java文件时已经忽略了注释部分！**不会发送至客户端**
  * 但是HTML中注释会发送并可以从查看源代码中找到，虽然页面中不显示
* JSP对应的Java文件：java脚本直接显示，html输出用write，变量输出用print

## 7.3 **JSP九大内置对象**

- pageContext（PageContext）：当前页面共享数据。页面上下文对象，可以获取其他8个对象，一个顶9个！
- application（ServletContext）：整个应用程序，所有用户间共享数据
- session（HttpSession）：一次会话的多个请求间。在JSP页面中设置`<%@page session=”false”%>`就不能使用Session
- request（HttpServletRequest）：一次请求访问的多个资源（请求转发）
- response（HttpServletResponse）：响应对象
- out（JspWriter）：用来向客户端发送文本数据。**先执行response.getWriter()，后执行out**
- page（当前JSP的类型即Servlet对象）：当前JSP页面的“this”，即Servlet对象；
- config（ServletConfig）：对应“真身”中的ServletConfig
- exception（Throwable）：只有在错误页面中可以使用这个对象，即`isErrorPage`为true



> Servlet中有三大域，而JSP中有四大域，它就是最后一个域对象！
>
> - ServletContext：整个应用程序
> - session：整个会话(一个会话中只有一个用户)
> - request：一个请求链！
> - pageContext：一个jsp页面！这个域是在**当前jsp页面**和当前jsp页面中使用的**标签**之间共享数据！

- PageContext域对象

  - **代理其他域**：pageContext.setAttribute("xxx", "XXX", PageContext.SESSION_SCOPE);

  - **全域查找**：pageContext.findAttribute("xxx")；**从小到大**，依次（四大域对象）查找！




## 7.4 JSP三大指令

作用：用于**配置JSP页面**，**导入资源文件**。一个jsp页面中，可以有0~N个指令的定义！可以放在任意位置

格式：`<%@ 指令名称 属性名1=属性值1 属性名2=属性值2 ... %>`

- **page**

  - **`contentType`**和`pageEncoding`

    - `contentType`：**添加一个响应头，等同于**`response.setContentType("text/html;charset=utf-8");`
    - `pageEncoding`：它指定当前jsp页面的编码，在服务器要把jsp编译成.java时需要使用`pageEncoding`!
    - 如果两个属性只提供一个，那么另一个的默认值为设置那一个。如果两个属性都没有，那么默认为iso

  - `import`：**导包**！可以出现多次

  - `language`：指定当前jsp编译后的语言类型，默认值为java。

  - `errorPage`和`isErrorPage`

    - `errorPage`：当前页面如果抛出异常，那么要转发到哪一个页面，由errorPage来指定

    - `isErrorPage`：它指定当前页面是否为处理错误的页面！当该属性为true时，这个页面会设置状态码为500！而且这个页面可以使用9大内置对象中的**`exception`**!

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

  - 基本没用：

    - autoFlush和buffer
      - autoFlush：指定jsp的输出流缓冲区满时，是否自动刷新！默认为true，如果为false，那么在缓冲区满时抛出异常！
      - buffer：指定缓冲区大小，默认为8kb，通常不需要修改！
    - isELIgnored：是否忽略el表达式，默认值为false，不忽略，即支持！

    - info：信息！
    - isThreadSafe：当前的jsp是否支持并发访问！值为false，没人用
    - session：当前页面是否支持session，如果为false，那么当前页面就没有session这个内置对象！
    - extends：让jsp生成的servlet去继承该属性指定的类！

- **include**：静态**包含**

  - 作用：把页面分解，使用包含的方式组合在一起，这样一个页面中不变的部分，就是一个独立jsp，而我们只需要处理变化的页面。
  - 与RequestDispatcher的include()方法的功能相似！
    - `<%@include%>`它是在jsp编译成java文件时完成的！他们共同生成一个java文件，然后再生成一个class！
    - RequestDispatcher的`include()`是一个方法，包含和被包含的是两个servlet，即两个.class！他们只是把响应的内容在运行时合并了！

- **taglib**：导入标签库，`<%@taglib prefix="s" uri="/struts-tags"%> 前缀的用法< s:text>`

  - `prefix`：指定标签库在本页面中的**前缀**！自定义！

  - `uri`: 指定标签库的**位置**！


> JSP动作标签
>
> 与html提供的标签有本质的区别，动作标签是由服务器来解释执行！它与java代码一样，都是在服务器端执行的！
>
> html由浏览器来执行！
>
> - `<jsp:forward>`：**转发**！它与RequestDispatcher的forward方法是一样的，一个在Servlet中使用，一个在jsp中使用
> - `<jsp:include>`：**包含**：它与RequestDispatcher的include方法是一样的，一个在Servlet中使用，一个在jsp中使用
>   - < %@include>和< jsp:include>有什么不同！
> - `<jsp:param>`：它用来作为forward和include的子标签！用来给转发或包含的页面传递参数！



## 7.5 EL表达式

1. 概念：**EL（Expression Language）** 表达式语言
2. 作用：**替换**和**简化jsp页面**中**java代码的编写**。EL替代的是`<%= ... %>`，也就是说，**EL只能做输出**
3. 语法：`${表达式}`
4. 注意：
  * **JSP默认支持EL表达式的**。如果要**忽略**EL表达式
    1. 设置JSP中page指令中：`isELIgnored="true"` 忽略当前JSP页面中所有的EL表达式
    2. `\${表达式}` ：忽略当前这个EL表达式
5. 使用：
   1. **运算**：
     * 运算符：
       1. 算数运算符： `+` `-` `*` `/(或div)` `%(或mod)`
       2. 比较运算符： `>` `<` `>=` `<=` `==` `!=`
       3. 逻辑运算符： `&&(或and)` `||(或or)` `!(或not)`
       4. 空运算符： `empty`
         * 功能：用于判断字符串、集合、数组对象是否为null或者长度是否为0
         * `${empty list}`：判断字符串、集合、数组对象是否为null或者长度为0
         * `${not empty str}`：表示判断字符串、集合、数组对象是否不为null 并且 长度>0
   2. **获取值**

     3. el表达式**只能**从**域对象**中获取值

     4. 语法：

            1. `${域名称.键名}`：从指定域中获取指定键的值

                 * 域名称：
                   1. `pageScope`		--> pageContext
                   2. `requestScope` 	--> request
                   3. `sessionScope` 	--> session
                   4. `applicationScope` --> application（ServletContext）
                 * 举例：在request域中存储了`name=张三`。获取：`${requestScope.name}`，没有就展示空串
                    2. `${键名}`：表示**依次从最小的域中**查找是否有该键对应的值，直到找到为止。
                    3. 获取对象、List集合、Map集合的值
                  1. **对象**：`${域名称.键名.属性名}`
                        * 本质上会去调用对象的`getter方法`。属性和成员变量不同，只有getter、setter方法的称**逻辑视图**

                  2. **List**集合：`${域名称.键名[索引]}`

                  3. **Map**集合：`${域名称.键名["key名称"]}`或`${域名称.键名.key名称}`

   5. 隐式对象：
      * el表达式中有11个隐式对象
          * **pageContext**：
              * 获取其他9个内置对象，有个cookie它获取不到
              * `${pageContext.request.contextPath}`：动态获取**虚拟目录**

## 7.6 JSTL标签库

1. 概念：JavaServer Pages Tag Library  **JSP标准标签库**。是由Apache组织提供的开源的免费的jsp标签`<标签>`

2. 作用：用于简化和替换jsp页面上的java代码

3. 使用步骤：

    1. 导入jstl相关jar包
    2. 引入标签库，taglib指令：  `<%@ taglib %>`
    3. 使用标签

4. 常用的JSTL标签

    1. `if`：相当于java代码的if语句

        1. 属性：`test`必须属性，接受boolean表达式
            1. 如果表达式为true，则显示if标签体内容，如果为false，则不显示标签体内容
            2. 一般情况下，test属性值会**结合el表达式**一起使用
        2. 注意：`c:if`标签**没有else**情况，想要else情况，则可以在定义一个`c:if`标签

        ```jsp
        <c:if test="${not empty list}">  <%-- number % 2 != 0 --%>
            <p>遍历集合</p>
        </c:if>
        ```

    2. `choose`：相当于java代码的switch语句

        1. 使用choose标签声明         			相当于switch声明
        2. 使用when标签做判断         			相当于case
        3. 使用otherwise标签做其他情况的声明    	相当于default

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

    3. `foreach`：相当于java代码的for语句

        ```jsp
        <c:forEach var="i" begin="1" end="10" step="2">//循环变量、开始、结束、设置步长(默认1)
            ${i}<br/>
        </c:forEach>
        //13579
        
        <c:forEach items="${strs }" var="str">//循环目标、变量
            ${str }<br/>
        </c:forEach>
        
        //可以使用varStatus来创建循环状态变量，用于遍历容器   
        <c:forEach var="item" items="${ns }" varStatus="vs">
            <c:if test="${vs.first }">第一行：</c:if>               <!--first：是否为第一个元素-->
            <c:if test="${vs.last }">最后一行：</c:if>              <!--last：是否为最后一个元素-->
            <c:out value="第${vs.count }行: "/>                     <!--count：循环的次数，从1开始-->
            <c:out value="[${vs.index }]: "/>                      <!--index：循环元素的索引-->
            <c:out value="name: ${vs.current }"/><br/>             <!--current：当前元素。或直接用var变量-->
        </c:forEach>
        ```

  * 需求：在request域中有一个存有User对象的List集合。需要使用jstl+el将list集合数据展示到jsp页面的表格table中

    ```jsp
    <table border="1">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>年龄</th>
            <th>生日</th>
        </tr>
        <c:forEach items="${list}" var="user" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.birStr}</td>
            </tr>
        </c:forEach>
    </table>
    ```





# 8 三层架构及MVC模型

三层架构

- **表现层**（Web层） --> 与Web相关的内容。**Servlet充当Controller**
- **业务逻辑层**（Service层） --> 处理业务逻辑的
- **数据访问层**（DAO层） --> 操作数据库存储文件，不能跳出到DAO之外(Data Access Object，数据访问对象)

------

MVC模型
- M -- **Model** 模型(JavaBean)：具体业务操作
- V -- **View**  视图(JSP)：展示数据
- C -- **Controller** 控制器(Servlet)：获取客户端输入，调用模型，将数据交给视图

优点：
1. 耦合性低，方便维护，可以利于分工协作 
2. 重用性高

缺点：

1. 使得项目架构变得复杂，对开发人员要求高

### 7.3.1 用户信息管理

- 登陆
  1. `login.jsp`页面的验证码通过请求`VerifyCodeServlet`来获取，并通过JS点击更换（src后随机参数）
  2. 提交并请求`LoginServlet`，获取`Session`域中的验证码与请求参数对比（随后删除`Session`确保验证码一次性）
  3. 通过请求参数查询用户是否存在，存在将查询到的用户信息保存至`Request域`并`转发至index.jsp回显`用户信息
  4. 用户不存在则保存提示信息至`Request域`并`转发至login.jsp回显`提示信息
- 查
  1. 查询所有（分页后可以删除这个）
     1. 请求`ListUserServlet`，将所有用户保存至`Request域`中，并转发至`list.jsp`
  2. **分页查询（将如下数据封装为PageBean）**
     1. 客户端提供
        1. `currentPage`：当前页码
        2. `rows`：每页展示行数
     2. 服务端提供
        1. `totalCount`：总记录数
        2. `totalPage`：总页数。`totalCount % rows == 0 ? totalCount/rows : totalCount/rows +1;`
        3. `List<T> list`：查询的数据集合。需要提供`startCount = (currentPage-1)*rows`和`rows`
     3. 前端页面（只显示10页）
        1. 总页数小于等于10，直接显示
        2. 否则根据currentPage-5，currentPage+4来规划
        3. 注意头溢出、尾溢出时，要考虑必须显示10页，来规划另一端页码
  3. **多条件查询**（where 1=1；like及%；判断有值才拼接；Mybatis会优化），添加在分页功能中
- 增
  1. `list.jsp`中增加按钮链接至`add.jsp`，提交数据并请求`AddUserServlet`
  2. 编码，封装请求参数并在数据库中添加数据，重定向至`ListUserServlet`
- 删
  1. 单独删除（利用ID删除）
     1. danhang删除按钮`绑定JS函数`并`传递用户ID`
     2. 通过`confirm`提示并设置`Location.href`为`DeleteUserServlet?id=**`
     3. 通过传递的ID在数据库中删除数据，重定向至`ListUserServlet`
  2. **多选删除（form包裹，submit()，getParameterValues()）**
     1. 用`form包裹CheckBox`，将CheckBox赋`name为ids`，`value为用户的ID`
     2. 给`删除选中按钮添加JS函数`，先判断选中个数大于0，再提示并利用`form的submit()方法`提交action至`DeleteSelectServlet`
     3. `DeleteSelectServlet`通过`getParameterValues`获取ids数组，并遍历删除，`重定向至ListUserServlet`
     4. 全选/全不选参考JS中习题
- **改（利用ID回显，利用ID更新）**
  1. 修改按钮设置`href`，`传递用户ID`，请求`findUserByIdServlet`
  2. 通过ID在数据库中查找用户，保存至`Request域`并`转发至update.jsp`，回显数据（`ID用隐藏input`），配合C标签
  3. 提交数据并请求`UpdateUserServlet`，封装请求参数，并更新数据（根据ID），重定向至`ListUserServlet`



### 7.3.2 登录注册（旧）

#### 1、分析功能

- **Domain**：
  - User（对应数据库，还要对应所有表单）
    - username、password、verifyCode
- **Dao**：
  - UserDao --> 与用户相关的数据类
- **Service**：
  - UserService --> 与用户相关的业务类
- **Web.Servlet**：
  - LoginServlet
  - RegistServlet
- **JSP**：
  - login.jsp  --> 登录表单
  - regist.jsp --> 注册表单
  - index.jsp -->  主页(只有登录成功才能看到)
- Exception



- 数据库设计

  - users.xml

    ```xml
    <users>
      <user username="xxx" password="xxx"/>
      <user username="xxx" password="xxx"/>
    </users>
    ```

#### 2、注册

- **regist.jsp**：完成regist.jsp的基本功能！
- **RegistServlet**：封装表单数据到User对象中，调用service的regist()方法
  - 如果这个方法没有出问题，输出“注册成功”
  - 如果这个方法抛出了异常，把错误信息保存到request域，转发到regist.jsp（回显错误信息）
- **UserService#regist()**：无返回值，注册失败抛出一个自定义异常。调用UserDao的findByUsername()方法
  - 如果已被注册，抛出异常（“用户名已被注册！”）
  - 没被注册则添加用户
- **UserDao**：通过业务分析，得到结果：需要提供两个方法
  - 按用户名查询用户对象：User findByUsername(String username)
  - 插入一个用户到数据库中：void add(User user)

#### 3、给注册添加验证码

- **VerifyCodeServlet**
  1. BufferedImage getImage()：获取随机验证码图片
  2. String getText()：把验证码图片上的文本保存到session中
  3. static output(BfferedImage, OutputStream)：把图片写入到response的outputStream中
- **regist.jsp**
  1. 添加< img src="指向Servlet"  />
  2. 添加一个文本框，用来输入验证码
  3. “看不清，换一张”，是一个超链接。把上面的< img>的src重新再次指向Servlet！为了处理浏览器的缓存，需要使用时间来做参数！
- **修改RegistServlet**
  - 校验验证码！
    - 错误：保存表单数据到request域、保存错误信息到request域，转发回regist.jsp
    - 正确：什么都不做，向下执行原来代码！

#### 4、服务器端表单校验

- 我们把这段校验，放到获取表单数据之后，验证码校验之前！
  1. 使用Map类型来装载错误信息！（那个字段出错，就给哪个字段添加错误信息）
     - key：表单项名称，例如：username、password、verifyCode
     - value：
       - 非空：用户名不能为空，或者是“密码不能为空”
       - 长度：用户名长度必须在3~20之间　密码长度必须在3~20之间
  2. 判断map是否为空（长度是否为0），如果不空，说明有错误存在，保存map到request域，保存form到request域（回显），转发回regist.jsp
  3. 在regist.jsp页面中，显示map中的错误信息。${map.username}

#### 5、登录

- login.jsp --> 登录表单！
- LoginServlet --> 
  1. 获取表单数据，封装到User中
  2. 调用service的login()方法，传递form过去！
  3. 如果service的login()方法，没有抛出异常！返回一个User对象！
     - 有异常：获取异常信息，保存到request域，保存form，转发到login.jsp
     - 没异常：保存返回的user对象到session中！！！重定向到welcome.jsp(显示当前用户信息！)
- UserService#login()
  - public User login(User form) {...}
  - 使用用户名查询数据库，得到返回的User
    - 返回为null，抛出异常，异常信息为（用户名不存在）
    - 返回不为null，获取查询出来的user的password与form的password进行比较！如果不同：抛出异常（密码错误！）；如果相同，返回查询结果！
- UserDao
  - 通过用户名查询用户！(已经存在了，不用再写了！)



#### 6、代码

**domain**

- javabean类，`User.java`

**dao**

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

**Service**

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

**Web.Servlet**

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

**JSP**

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



# 9 Filter、Listener

## 9.1 Filter

* web中的过滤器：当访问服务器的资源时，过滤器可以**拦截请求**，增强Request、Response
* 过滤器的作用：一般用于完成**通用的操作**。如：**登录验证**、统一**编码处理**、**敏感字符过滤**……

### 9.1.1 快速入门

 1. 定义一个类，**实现接口Filter**

2. **复写方法**

3. **配置拦截路径**

  1. 注解

     ```java
     @WebFilter("/*")//访问所有资源之前，都会执行该过滤器。同Servlet一致，urlPatterns和value一致，可用value替代并省略
     public class FilterDemo implements Filter {
         @Override
         public void init(FilterConfig filterConfig) throws ServletException { }
     
         @Override
         public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
             System.out.println("filterDemo被执行了....");
             filterChain.doFilter(servletRequest,servletResponse);//放行
         }
     
         @Override
         public void destroy() {  }
     }
     ```

  2. web.xml

     ```xml
     <filter>
         <filter-name>demo</filter-name>
         <filter-class>cn.itcast.web.filter.FilterDemo</filter-class>
     </filter>
     <filter-mapping>
         <filter-name>demo</filter-name>
         <url-pattern>/*</url-pattern>    <!-- 拦截路径 -->
     </filter-mapping>
     ```

### 9.1.2 Filter细节

1. ==过滤器**配置**详解==
   1. **拦截路径配置**：`value[]`或`urlPatterns[]`属性
      1. 具体资源路径：`/index.jsp`只有访问index.jsp资源时，过滤器才会被执行
      2. 拦截目录：`/user/*`访问/user下的所有资源时，过滤器都会被执行
      3. 后缀名拦截：`*.jsp`访问所有后缀名为jsp资源时，过滤器都会被执行
      4. 拦截所有资源：`/*`访问所有资源时，过滤器都会被执行
   2. **拦截方式配置**：**资源被访问的方式**，`dispatcherTypes[]`属性
      - 注解配置：
        1. `REQUEST`：默认值。浏览器**直接请求**资源
        2. `FORWARD`：**转发**访问资源
        3. `INCLUDE`：包含访问资源
        4. `ERROR`：错误跳转资源
        5. `ASYNC`：异步访问资源，a    synchronize
      - web.xml配置
        - 设置`<dispatcher></dispatcher>`标签即可
2. ==过滤器**执行流程**==
   1. 执行**过滤器**
   2. 执行**放行后的资源**
   3. 回来执行过滤器**放行代码后的代码**
3. ==过滤器**生命周期方法**==
   1. `init(FilterConfig)`：在**服务器启动后**，会创建Filter对象，然后调用`init`方法。**只执行一次**，用于加载资源
   2. `doFilter(ServletRequest,ServletResponse,FilterChain)`：**每一次请求被拦截**时执行。可以执行多次
      - 放行后的**`Request`和`Response`还是同一个**，类似转发
   3. `destroy()`：在**服务器正常关闭前**，Filter对象被销毁。**只执行一次**，用于释放资源
4. ==**过滤器链**（配置多个过滤器）==，如果没有下一个过滤器那么执行的是目标资源，否则就执行下一个过滤器！如净水器过滤
   1. 执行顺序：如果有两个过滤器：过滤器1和过滤器2
      1. 过滤器1
      2. 过滤器2
      3. 资源执行
      4. 过滤器2
      5. 过滤器1 
   2. 过滤器先后顺序问题：
        1. 注解配置：按照类名的字符串按每个字符比较规则比较，值小的先执行。如：AFilter和BFilter，AFilter就先执行
        2. web.xml配置： `<filter-mapping>`谁定义在上边，谁先执行

### 案例

#### 1、登陆验证（权限控制）

1. 需求：

   1. 访问usermanagement案例的资源。验证其是否登录
   2. 如果登录了，则直接放行。
     3. 如果没有登录，则跳转到登录页面，提示"您尚未登录，请先登录"

2. 分析

   1. 将实现Filter接口的非HTTP相关**Request强转为HTTP相关**的，并获取`getRequestURI()`
   2. 将包含了**登陆**相关的**资源放行**，要注意排除掉 **`css/js/图片/验证码`**等资源
   3. 若不是登陆相关的资源，**获取`Session`域**中**登陆成功后保存的`user`属性**，**判断**它值不为`null`则放行
   4. 否则在**`Request域`**中保存相关**提示信息并转发**至`login.jsp`

   ```java
   @WebFilter("/*")
   public class LoginFilter implements Filter {
       
       public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
           
           HttpServletRequest request = (HttpServletRequest) req; //强制转换，否则没有如下方法
           String uri = request.getRequestURI(); //获取资源请求路径
           
           //判断是否包含登录相关资源路径,要注意排除掉 css/js/图片/验证码等资源
           if (uri.contains("/login.jsp") || uri.contains("/loginServlet") || uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/checkCodeServlet")) {
               chain.doFilter(req, resp);//包含，用户就是想登录。放行
           } else {  //不包含，需要验证用户是否登录。从获取session中获取user
               Object user = request.getSession().getAttribute("user");
               if (user != null) { //登录了。放行
                   chain.doFilter(req, resp);
               } else { //没有登录。跳转登录页面            
                   request.setAttribute("login_msg", "您尚未登录，请登录");
                   request.getRequestDispatcher("/login.jsp").forward(request, resp);
               }
           }
       }
       public void init(FilterConfig config) throws ServletException {}
       public void destroy() {}
   }
   ```



#### 2、敏感词汇过滤（动态代理）

* 需求：

  * 对usermanagement案例录入的数据进行敏感词汇过滤
  * 敏感词汇如笨蛋、傻瓜。如果是敏感词汇，替换为***

* 分析：

  1. 由于拦截的和放行的Request、Response为同一个，可以对getParameter()获取的参数修改后再保存至Request中，但是没有这种现有方法
  2. 可以采用**动态代理**对Request对象进行增强，增强获取参数相关方法
  3. 放行，传递代理对象

  ```java
  @WebFilter("/*")
  public class SensitiveWordsFilter implements Filter {
  
      public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
          //1.创建代理对象，增强getParameter方法
          ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
              @Override
              public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                  //增强getParameter方法
                  //判断是否是getParameter方法
                  if (method.getName().equals("getParameter")) {
                      //增强返回值
                      //获取返回值
                      String value = (String) method.invoke(req, args);
                      if (value != null) {
                          for (String str : list) {
                              if (value.contains(str)) {
                                  value = value.replaceAll(str, "***");
                              }
                          }
                      }
                      return value;
                  }
                  //判断方法名是否是 getParameterMap
                  //判断方法名是否是 getParameterValue
                  //不是上述方法，则原样调用
                  return method.invoke(req, args);
              }
          });
          //2.放行
          chain.doFilter(proxy_req, resp);
      }
  
      private List<String> list = new ArrayList<String>();//敏感词汇集合
      public void init(FilterConfig config) throws ServletException {
  
          try {
              //1.获取文件真实路径
              ServletContext servletContext = config.getServletContext();
              String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");
              //2.读取文件
              BufferedReader br = new BufferedReader(new FileReader(realPath));
              //3.将文件的每一行数据添加到list中
              String line = null;
              while ((line = br.readLine()) != null) {
                  list.add(line);
              }
              br.close();           
          } catch (Exception e) {
              e.printStackTrace();
          }
      }
      public void destroy() {}
  }
  ```

- **增强对象的功能**：可以采用**设计模式（一些通用的解决固定问题的方式）**

  - **装饰模式**：可参考后续两个例子的代码，此处不再详细介绍。如IO流		

  - **代理模式**（两者区别在于代理对象的生成模式）

    1. 静态代理：有一个类文件描述代理模式

    2. ==**动态代理**==：**程序运行过程**，在**内存中**动态的为目标对象**创建**一个虚拟的代理对象

       `java.lang.reflect`包下提供了一个`Proxy`类和一个`InvocationHandler`接口，通过使用这个类和接口就可以生成动态代理对象。JDK提供的代理**只能针对实现统一接口的类做代理**，我们有更强大的代理**cglib**

       - 实现步骤：
         1. 代理对象和真实对象实现相同的接口
         2. `代理对象 = Proxy.newProxyInstance();`
         3. 使用代理对象调用方法
         4. 增强方法

       - **创建指定接口的代理类对象实例**（类加载器，Class对象数组，调用处理器）

         ```java
         static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHander hander)
         //其中loader是与目标对象相同的类加载器
         //interfaces是接口的字节码对象数组 new Class<?>[]{interface.class}
         ```

         其中第三个参数为**调用处理器**，是实现了`InvocationHandler`接口的类对象，重写`invoke`方法

         ```java
         public static void main(String[] args) {
             public final Target target = new Target();//被代理的目标对象
         
             //动态创建代理对象
             TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
                 target.getClass().getClassLoader(), 
                 target.getClass().getInterfaces(), 
                 new InvocationHandler() {
                     @Override
                     //被执行几次?---看代理对象调用方法几次;代理对象调用接口相应方法 都是调用该invoke方法
                     /** proxy:是代理对象，不用
         			 * method:代理对象调用的方法被封装为Method对象
         			 * args:代理对象调用方法时传递的实际参数，封装为数组，即参数列表
         			 */
                     public Object invoke(Object proxy,Method method,Object[] args) throws Throwable{
                         //反射知识点。使用目标对象调用目标方法并传递参数，返回目标方法的返回值
                         Object obj = method.invoke(target, args);
                         //retrun返回的值给代理对象
                         return invoke;
                     }
                 }
             );
         	//调用代理对象
             proxy.method1();//调用invoke---Method：目标对象的method1方法  args：null  返回值null
             String method2 = proxy.method2();//调用invoke---Method:目标对象的method2方法 
         }
         ```

       - 增强方式：（invoke方法中）
         1. 增强**返回值**：通过**对`return`返回值的修改**
         2. 增强**参数列**表：通过**`method.getName()`判断要增强的方法**，并对**参数`args[]`数组进行修改**
         3. 增强**方法体**执行逻辑：**反射方法`invoke()`执行前后修改**



#### 3、分IP统计网站访问次数

- 统计工作需要在所有资源之前都执行，那么就可以放到**Filter**中了,不做拦截操作！**获取map并保存数据**
- 用Map<String,Integer>来装载统计的数据,整个网站只需要一个**Map**即可！使用**ServletContextListener**，在服务器启动时完成创建，并保存到ServletContext中
- 打印Map中的数据

#### 4、全站编码问题(增强request)

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

#### 5、页面静态化(增强response)

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



## 8.2 Listener

> web的三大组件之一

* 事件监听机制
  * 事件源（被监听对象）：小偷
  * 事件（事件源行为）：偷东西
  * 监听器（用于监听事件源的对象）：警察，监听器中的方法：抓捕
  * 注册监听：将事件、事件源、监听器绑定在一起。 当事件源上发生某个事件后，执行监听器中的方法

* 监听器：

  * 它是一个**接口**，内容由我们来实现
  * 它需要**注册**，例如注册在按钮上
  * 监听器中的方法，会在特殊**事件发生时被调用**

* Java Web中完成编写监听器（以后写监听器机会很少！）

  - 写一个监听器类(实现某个监听器接口)，重写方法

  - 注册，即配置。

  - 可以通过`getInitParameter()`获取初始化参数，加载资源文件

    - 注解：`@WebListener`

    - web.xml

      ```xml
      <listener>
          <listener-class>cn.itcast.web.listener.ContextLoaderListener</listener-class>
      </listener>
      <!--还可以指定初始化参数-->
      <context-param>
          <param-name>contextConfig</param-name>
          <param-value>/WEB-INF/classes/prop.xml</param-value>
      </context-param>
      ```

* Java Web中监听器（8大）：
  * **ServletContext**

    * 生命周期监听：<span style="background:yellow">**ServletContextListener**</span>，有两个方法，一个在服务器启动后调用，一个在服务器正常关闭前调用
      * `void contextInitialized(ServletContextEvent sce)`：**ServletContext对象创建后**会调用该方法
      * `void contextDestroyed(ServletContextEvent sce)`：**ServletContext对象被销毁之前**会调用该方法
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

* 事件对象

  * **ServletContextEvent**：`ServletContext getServletContext()`，**可以获取`ServletContext`**

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





# 第三部分 案例

# 黑马旅游网案例

## 9.1 准备工作

* Maven项目的**导入**。选择`pom.xml`文件
* 通过tomcat7的插件**启动**项目（可以在配置中设置好），不同版本Tomcat也不一样。`tomcat7:run`
* 技术选型
  * Web层
    * Servlet：前端控制器
    * html：视图。互联网应用为了访问速度，使用HTML采用json数据交互。若是OA等项目，可以JSP
    * Filter：过滤器
    * BeanUtils：数据封装
    * Jackson：json序列化工具
  * Service层
    * Javamail：java发送邮件工具
    * Redis：nosql内存数据库
    * Jedis：java的redis客户端
  * Dao层
    * Mysql：数据库
    * Druid：数据库连接池
    * JdbcTemplate：jdbc的工具
* 创建数据库：创建库、使用库、复制创建表、插入数据的语句执行即可

## 9.2 BaseServlet的抽取

对一张表的请求写太多Servlet没必要，可以继承HttpServlet并重写service方法，**根据请求URI的不同来调用不同方法（反射）**。类似REST，但是不是使用GET、POST、DELETE、PUT等。

```java
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI(); //  /travel/user/add       
        String methodName = uri.substring(uri.lastIndexOf('/') + 1); //获取方法名称
        try {
            //this是谁调用我？我代表谁。即代表被请求的那个继承于此的Servlet
            Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);//利用反射获取方法对象Method
            //method.setAccessible(true); //暴力反射没必要，可以将继承后的Servlet中方法声明为public即可
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    /**
     * 直接将传入的对象序列化为json，并且写回客户端
     * @param obj
     */
    public void writeValue(Object obj,HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),obj);
    }
    /**
     * 将传入的对象序列化为json，返回
     * @param obj
     * @return
     */
    public String writeValueAsString(Object obj) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
```



## 9.3 注册、激活、登陆(回显)、退出

![](images\注册登录.png)



## 9.4 分类数据展示

对于大多时候不改变的数据，应使用**Redis缓存**优化。**展示类别的cname，并携带类别的cid值**

![](images\分类数据展示.png)



## 9.5 旅游线路分页展示

根据携带的cid来请求查找（可封装为JS库）；全部使用**Ajax请求**（封装为方法），利用JavaScript方法传递**cid，还有currentPage和pageSize(可以不提供)**；

![](images\旅游路线分页展示.png)

分页中只显示10页，代码如下：

```javascript
let begin;
let end;
if (data.totalPage < 10) { //总页数不足10页
    begin = 1;
    end = data.totalPage;
} else {
    begin = data.currentPage - 5;
    end = data.currentPage + 4;
    if (begin < 1) { //头溢出
        begin = 1;
        end = begin + 9;//10
    }
    if (end > data.totalPage) { //尾溢出
        end = data.totalPage;
        begin = end - 9;
    }
}
//之后根据begin、end来遍历即可
```

## 9.6 旅游线路名称模糊查询

将搜索按钮绑定onclick事件，**获取`input`标签`value`的==cname==值**和**链接中的==cid==**，利用**location.href属性拼接并转到route_list.html页**

在route_list.html中解码获取**cname**的值，获取**id**的值。**添加参数（前端页面中cname为字符串，需添加引号）**至分页展示代码的前端、后端中（都要修改）

> 注意：
>
> 1、获取请求参数时，==值为**字符串"null"**的也要过滤掉==
>
> 2、Dao层中条件查询（1=1、拼接sql、params）
>
> 3、Tomcat7以及之前的需要设置GET请求编码，`new String(s.getBytes("iso8859-1"),"utf-8")`

## 9.7 旅游线路详情

route_list.html中每个查看详情传递rid，在route_detail.html中获取并请求后端查询。

后端通过分别查询四张表来获取Route、RouteImg、Seller对象和收藏次数count，后三个保存至Route对象中，以json响应

前端解析json数据展示即可

## 9.8 旅游线路收藏

![](images\收藏线路.png)

​	

# Linux

> Linux是基于Unix的，是一种自由和开放源码的操作系统，存在着许多不同的Linux版本，但它们都使用了Linux内核。

Linux的版本分为两种：

* **内核版本**：是指在Linus领导下的内核小组开发维护的系统内核的版本号 ；

* **发行版本**：是一些组织和公司根据自己发行版的不同而自定的 ；

![](images\linux目录结构.png)

## 常用命令

> 标识符如\$前带`~`为用户主目录：`conanan@Conanan:~$`；带`/`为根目录
>
> Linux中，前缀带`.`的都是隐藏目录或文件



**切换目录**

* **`cd`**：`~`(进入用户主目录)；`/`(进入根目录)；`..`(上一层)；`-`(上一个目录)；`app

**创建目录和移除目录**

- **`mkdir [-p]`**(创建级联路径)、`rmdir`(只用于没有子路径的路径)

**列出文件列表**

* **`ls [-a]` **、**`ll`(`-h`友好显示文件大小)**、 `dir`

**浏览文件**

* `cat`(全部内容)、`more`(分页，回车下一行，空格下一页)、`less`(pgup、pgdn)、**`tail`(`-n`显示后n行，`-f`动态查看)**

**文件操作**

* **`rm`(`-r`递归删除，`-f`不用询问，`-rf`慎用)**。以下俩同样可以有这三个后缀

* `cp`(**复制**，需要指定源文件名或文件夹名与目标文件名或文件夹名)，若是**文件夹名**则只复制源文件夹**里面**的数据

* `mv`(**移动**或者**重命名**)。

* **`tar`**(**打包**但不做压缩，解包)

  * -**c**：创建一个新tar文件

    -**v**：显示运行过程的信息

    -**f**：指定文件名

    -**z**：**调用gzip压缩命令**进行压缩、解压

    -t：查看压缩文件的内容

    -**x**：解开tar文件

  * **打包**：`tar –cvf xxx.tar ./*`

  * **打包并且压缩**：`tar –zcvf xxx.tar.gz ./*`

  * **解压**：`tar –zxvf xxx.tar`、`tar -zxvf xxx.tar.gz -C /usr/aaa`(`-C`指定路径)

* `grep`：查找**符合条件的字符串**，用法`grep [选项]... PATTERN [FILE]...`

  * `grep lang anaconda-ks.cfg`  在文件中查找lang

    `–color`高亮显示，`-A5`当前行和后5行，`-b5`当前行和前5行

**其他常用命令**

* `pwd`：显示当前所在目录
* `touch a.txt`  ：创建一个空文件
* `wget`：下载资料



**重定向输出>和>>**

* `>` 重定向输出，覆盖原有内容。`ifconfig > ifconfig.txt`
* `>>` 重定向输出，有追加功能。`ifconfig >> ifconfig.txt`

**管道 |**：将一个命令的输出用作另一个命令的输入

* `ls --help | more`：分页查询帮助信息

**命令执行控制 &&**：命令之间使用 && 连接，实现逻辑与的功能。`mkdir test && cd test`

**网络通讯命令**

* `ifconfig`  显示或设置网络设备
* `ifconfig eth0 up` 启用eth0网卡
* `ifconfig eth0 down`停用eth0网卡
* `ping`   探测网络是否通畅
* `netstat` 查看网络端口。`netstat -an | grep 3306` 查询3306端口占用情况

**系统管理命令**

* `date` 显示当前系统时间；`date -s "2014-01-01 10:10:10"`设置系统时间
* `df [–h]` (友好)显示磁盘大小等信息
* `free [-m]` (以mb单位)显示内存状态
* `top` **动态显示负载，执行中的程序等信息**
* `clear` 清屏
* `ps` 正在运行的某个进程的状态
  * `ps –ef`  查看所有进程
  * `ps –ef | grep ssh`**查找某一进程**
* `kill` **杀掉某一进程**
  * `kill 2868`  杀掉2868编号的进程
  * `kill -9 2868`  强制杀死进程
* `du [-h]` (友好)显示目录或文件的大小
* `who` 显示目前登入系统的用户信息
* `hostname` 查看当前主机名。修改：`vi /etc/sysconfig/network`，没找到
* `uname -a` 显示本机详细信息。内核名称(类别)，主机名，内核版本号，内核版本，内核编译日期，硬件名，处理器类型，硬件平台类型，操作系统名称

## Linux的用户和组

**用户的管理**

* `useradd`  添加一个用户
  * `useradd test` 添加test用户
  * `useradd test -d /home/test -g publicc`  指定用户home目录、指定组
* `passwd`  设置、修改密码
  * `passwd test`  为test用户设置密码
* `userdel`  删除一个用户
  * `userdel test` 删除test用户(不会删除home目录)
  * **`userdel –r test`**  删除用户以及home目录
* 切换登陆
  * `ssh -l test -p 22 192.168.19.128`
  * `su – 用户名`

**组管理**：创建一个新用户user时，若没有指定他所属于的组，就建立一个和该用户同名的私有组。创建用户时也可以指定所在组。

* `groupadd`  创建组
  * `groupadd public` 创建一个名为public的组
* `groupdel` 删除组，如果该组有用户成员，必须先删除用户才能删除组
  * `groupdel public`

**id、su命令**

* `id`：查看一个用户的UID和GID。`id [选项]... [用户名]`

* `su`：切换用户。`su [选项]... [-][用户 [参数]... ]`

  * `su u1`  切换到u1用户
  * `su - u1` 切换到u1用户，并且将环境也切换到u1用户的环境（推荐使用）

* 账户文件

  * /etc/passwd  用户文件

    /etc/shadow  密码文件

    /etc/group  组信息文件

## Linux的权限命令

![](images\linux权限.png)

| 属主(user) |      |      | 属组(group) |      |      | 其他用户 |      |      |
| :--------: | :--: | :--: | :---------: | :--: | :--: | :------: | :--: | :--: |
|     r      |  w   |  x   |      r      |  w   |  x   |    r     |  w   |  x   |
|     4      |  2   |  1   |      4      |  2   |  1   |    4     |  2   |  1   |

Linux三种文件类型

* 普通文件： 包括文本文件、数据文件、可执行的二进制程序文件等
* 目录文件： Linux系统把目录看成是一种特殊的文件，利用它构成文件系统的树型结构
* 设备文件： Linux系统把每一个设备都看成是一个文件

**文件类型标识**

* **普通文件`-`**
* **目录`d`**
* **符号链接`l`**：进入etc可以查看，相当于快捷方式
* 字符设备文件（c） 
* 块设备文件（s） 
* 套接字（s） 
* 命名管道（p）

**文件权限管理（可以为0~7）**

* `chmod` **变更文件或目录的权限**。`chmod 755 a.txt`或`chmod u=rwx,g=rx,o=rx a.txt`
  * 变更文件或目录改文件所属用户和组：
    * `chown user:group a.txt`：变更当前的目录或文件的所属用户和组
    * `chown -R user:group dir`：变更目录中的所有的子目录及文件的所属用户和组

## Linux远程连接

SecureCRT软件：按`Alt+P`可打开sftp来传输文件，如`put d:\zookeeper-3.4.6.tar.gz`

XShell

Mac或Linux中的SSH命令

* `ssh 用户名@IP`，之后会让输密码
* 传输文件：`scp D:\zookeeper-3.4.6.tar.gz root@192.168.25.129:/root/也可以指定文件名称扩展名`

sftp：从Windows上传文件到Linux中



## Vi和Vim编辑器

* **正常模式**：按**Esc**键
* **可视模式**：正常模式下按v键
* **插入模式**：正常模式下按 `i` 、`a`、`o`键
  * `i` 在当前位置**前**插入；`I` 在当前**行首**插入
  * `a` 在当前位置**后**插入；`A` 在当前行尾插入
  * `o` 在当前行之**后插入一行**；`O` 在当前行之**前插入一行**



文件编辑

* 打开文件：`vim file`
* 退出：正常模式下按`:q`；不保存强制退出`:q!`；保存并退出`:wq`
* 删除一行：`dd`



# Nginx

> [engine x] 能够使用Nginx搭建**Tomcat集群**，完成==**负载均衡**==
>
> 负载均衡服务器分为两种一种是通过硬件实现的负载均衡服务器，简称硬负载例如：f5。另一种是通过软件来实现的负载均衡，简称软负载:例如apache和nginx。硬负载和软负载相比前者作用的网络层次比较多可以作用到socket接口的数据链路层对发出的请求进行分组转发但是价格成本比较贵，而软负载作用的层次在http协议层之上可以对http请求进行分组转发并且因为是开源的所以几乎是0成本，并且阿里巴巴，京东等电商网站使用的都是Nginx服务器。

**Nginx+Tomcat集群配置**

1. **安装两个Tomcat**：win和Linux都是解压即可。

2. 在service.xml中**修改为不同的端口**

   1. 修改关闭端口为`<Server port="8006" shutdown="SHUTDOWN">`
   2. 修改启动端口为`<Connector port="8081" protocol="HTTP/1.1"`
   3. 修改AJP连接端口为`<Connector port="8010" protocol="AJP/1.3" redirectPort="8443" />`

3. 发布项目到俩Tomcat中，并启动Tomcat。但是此时访问时端口号不同，地址不同，解决如下：

4. **Nginx安装**（默认端口80，win解压即可）双击可打开，关闭需杀掉进程可执行命令`nginx -s stop`

5. **配置Nginx**`conf/nginx.conf`，在nginx目录下，不关闭nginx**重新加载配置文件**`nginx -s reload`

   ```yaml
   upstream server_list{  //服务器列表
   	server localhost:8080;
   	server localhost:8081;
   }
   server {
   	listen       80;  //端口
   	server_name  localhost;  //域名
   	
   	location / {
   		root   html;
   		proxy_pass   http://server_list; //代理
   		index  index.html index.htm;
        }
   ```

6. Tomcat集群的**Session共享**

   - 每一个用户只访问同一台服务器，upstream server_list中添加`ip_hash`，给上述服务器列表添加权重`weight=10`
   - Session共享
     - 使用Tomcat的广播机制实现（不推荐，Tomcat越多性能越低，需要彼此知道彼此），修改Tomcat中`server.xml`
       1. 取消Tomcat的Engine下`<Cluster className="org.apache.catalina.ha.tcp.SimpleTcpCluster"/>`的注释，若有不同的集群，还需添加其他标签
       2. Tomcat的web.xml中添加`<distributable/>`节点即可
     - **使用Redis缓存Session**（推荐）




# 国际化

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




