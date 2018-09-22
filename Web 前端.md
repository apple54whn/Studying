# 1 HTML

* HyperText Markup Language：超文本标记语言，网页语言

* **规范**

  ```html
  <!DOCTYPE html>
  <html lang='en'>
  	<head>
  		<meta charset='utf-8'>
  		<title>标题</title>
  	</head>
  	<body>
  	</body>
  </html>
  ```

* **文字、注释标签**

  `<h1>标题</h1>`    **标题**，取值1~6从大到小，自动在其前后加**空行**

  `<p style="color: blue;size: 14px;">html</p>`   **段落**，自动在其前后加**空行**，是**块级元素**

  `<div></div>`   **块级元素**

  `<span></span>`   **内联元素**



​      `<pre></pre>`   **预格式文本**，保留原有格式

​      `&lt;&gt;&amp;&nbsp;`   **转义字符**(<>&空格)

​      `<!--注释-->`   **注释**

​      `<hr/>`   **横线**

​      `<br/>`   **换行**

* HTML样式(style)尽量使用CSS来操作

  * 字体、颜色、尺寸

  ```html
  <p style="font-family: Consolas;color: white;font-size: 20px;">学习HTML</p>
  ```

  * 文本对齐、背景颜色

  ```html
  <p style="text-align: center;background-color: gray">HTML</p>
  ```

* **列表标签**

  `<ol>` `<ul>` `<li>`   **有序、无序、列表** **type**='1'或'a'或'i'或'A'或'I'或type='circle'等；**start**；**reversed**

  `<dl>` `<dt>` `<dd>`   **定义列表、定义项目、描述**

* **图像标签**

  `<img src = 'test.jpg' width='400' height='300'alt='无法显示'/>` 

* **超链接标签**

  `<a href='https://www.qq.com' target='_blank'>百度链接</a>`默认是_self，不需要跳转时href值填#

  在**定位资源**时：

  ```html
  <a name='top'>top</a>
  <a href='#top'>returnTop</a>
  ```

* **表格标签**

  ```html
  <table border='1' cellpadding='10' cellspacing='10' bgcolor="aqua"> <!--表格框、边沿与内容间距、单元格间距，背景颜色，但是推荐在CSS中修改-->
  	<caption>表格标题</caption>
  	<thead>
  		<tr>
  			<th colspan='3'>姓名（跨三列）</th>
  		</tr>
  	</thead>
  	<tbody>
  		<tr>
  			<td rowspan='3'>张三（跨三行）</td>
  		</tr>
  	</tbody>
  	<tfoot>
  		<tr>
  			<td>备注</td>
  		</tr>
  	<tfoot>
  </table>
  ```

  table还有**width**、**height**属性,不赞成使用

* **表单标签**

  * 提交数据到服务器 

  * `<form></form>`: 定义一个表单的范围 

    * **action**：提交到地址，默认提交到当前的页面（可以是一个页面，也可以是后台代码）
    * **method**：常用的有两种  get和post，默认是get请求 
      * **get和post区别**
        1. get请求地址栏会携带提交的数据，post不会携带（请求体里面。在第七天时候讲http协议时候）
        2. get请求安全级别较低，post较高
        3. get请求数据大小的限制，post没有限制
    * **enctype**：一般请求下不需要这个属性，做**文件上传**时候需要设置这个属性 

  * **输入项**`<input type="输入项类型">`

    ```html
    <input maxlength="" size="" readonly="" placeholder="" required autofocus >
    	  内容最大长度   框框大小    只读	     占位符            必填   自动获得焦点
    ```

    * **text**：普通 (**name属性**)

    * **password**：密码(**name属性**)

    * **radio**：单选(**name属性相同**，value) checked=true/false

    * **checkbox**：多选(**name属性相同**，value) checked=true/false

    * **file**：上传文件(**name属性**)

    * **hidden**：隐藏(**name属性**，value)

    * **submit**：提交按钮

      ```html
      <input type="submit"/>
      <input type="submit" value="注册"/>
      ```

      * 使用图片提交`<input type="image" src="图片路径"/> `
      * 重置按钮`<input type="reset"/> `

    * **button**:普通按钮，和JS使用

    * **reset**:重置按钮

    * **image**:图片提交按钮

  * **下拉输入项select(name属性)**

    ```html
    <select name="birth" multiple  size="2">
        				 可以多选   可见选项数目
    	<option value="1991" selected>1991</option>
    	<option value="1992">1992</option>
    	<option value="1993">1993</option>
    </select>
    ```

  * **文本域textarea(name属性)**目前都不用了

    ```html
    <textarea cols="10" rows="10">我是...</textarea>
    ```

* 其他标签

  `<b>`**粗体**`</b>`  

  `<i>`***斜体***`</i>`   

  `<strong>`**加重语气**`</strong>` 

  `<em>`**着重文字**`</em>`   

  `<del>`**删除线**`</del>`  ，不赞成使用`<s></s>`

  `<sup>`**上标**`</sup>`

  `<sub>`**下标**`</sub>`

  `<u>`下划线`</u>`   不赞成使用

  `<label for="id_name">`**input 元素定义标记** `</label>`  

* 框架标签

  ```html
  <frameset rows="80,*">                        //把页面划分成上下两部分 
       <frame name="top" src="a.html">             //上面页面
       <frameset cols="150,*">                     //把下面部分划分成左右两部分
  		<frame name="lower_left" src="b.html">  //左边的页面
  		<frame name="lower_right" src="c.html"> //右边的页面
  	</frameset> 
  </frameset> 
  ```

  点击左边的页面超链接，内容显示在右边的页面中

  ```html
  <a href="01-hello.html" target="right">超链接1</a>
  ```







# 2 CSS

## 2.1 简介、组合方式

* cascading style sheets，层叠样式表（一层一层的，很多的属性和属性值），将网页内容和样式进行分离

* **分类（三种结合方式）**

  1. 在每个html标签上面都有一个 **style属性**，把css和html结合在一起

     `<div style="background-color:red;color:green;">`

  2. 使用html的一个**style元素**实现，写在head里面

     ```css
     <style type="text/css">	
     	div {
     		background-color:blue;
     		color: red;
     	}		
      </style>
     ```

  3. 使用头标签 **link，引入外部css文件**

      ```css
      <link rel="stylesheet" type="text/css" href="css文件的路径" />
      ```

* **优先级**

  ​	由上到下，由外到内。优先级由低到高。 **后加载的优先级高，**就近原则或叠加

* **格式**  选择器名称，选择器名称... { 属性名：属性值；属性名：属性值；…….}



## 2.2 css的**基本选择器**（三种）

1. **标签选择器**         `div{...}`
2. **class选择器**        `.className{...}`
3. **id选择器**             `#idName{...}`
   - **优先级**   **style > id选择器 > class选择器 > 标签选择器**
     - 有一个特别的语法可以让一条规则**总是**优先于其他规则：`border: none !important;`



## 2.3 css的扩展选择器

1. **属性选择器**

   | 属性         | 选择                                                        |
   | ------------ | ----------------------------------------------------------- |
   | [attr]       | 包含 attr 属性的**所有元素**                                |
   | [attr=val]   | 选择 attr 属性被赋值**为 val **的所有元素                   |
   | [attr~=val]  | 选择 attr 属性里被空白分开的字符串里**其中一个是val**的元素 |
   | [attr\|=val] | 选择attr属性的值是 **`val` 或值以 `val-` 开头**的元素       |
   | [attr^=val]  | 选择attr属性的值**以 `val` 开头（包括 `val`）**的元素       |
   | [attr$=val]  | 选择attr属性的值**以 `val` 结尾（包括 `val`）**的元素       |
   | [attr*=val]  | 选择attr属性的值中包含**子字符串 `val` **的元素             |

2. **组合选择器**

   | 组合  | 选择                                                         |
   | ----- | ------------------------------------------------------------ |
   | A,B   | 满足A（和/或）B的任意元素                                    |
   | A B   | 满足条件：**B**是A的**后代结点**（B是A的子节点，或者A的子节点的子节点） |
   | A > B | 满足条件：**B**是A的**直接子节点**                           |
   | A + B | 满足条件：**下一个兄弟选择器**（AB有相同的父结点，**B**是A的**紧跟着的兄弟节点**） |
   | A ~ B | 满足条件：**一般兄弟选择器**（AB有相同的父节点，**B**是A之后的**所有兄弟节点**） |

3. **伪类选择器（:）**

   * 匹配处于确定状态的一个或多个元素 ，被鼠标指针悬停的元素，或当前被选中或未选中的复选框，或元素是DOM树中一父节点的第一个子节点

   * **超链接伪类**

     未被访问状态（**link**）、鼠标悬停状态（**hover**）、鼠标点击不放（**active**）、已被访问过（**visited**）

4. **伪元素选择器（::）**

   * 匹配处于相关的确定位置的一个或多个元素，例如每个段落的**第一个字**，或者某个元素之前生成的内容 



## 2.4 盒子模型

* 在进行布局前需要把数据封装到一块一块的区域内（div）
  1. 边框（**border:**2px solid blue;）
     * 统一设置
     * 单独设置（border-top、border-bottom、border-left、border-right）
  2. 内边距（**padding**:20px|auto;）：第二个参数auto和居中类似
     - 统一设置
     - 单独设置（padding-top、padding-bottom、padding-left、padding-right）
  3. 外边距（**margin**:20px|auto）

* **CSS的display属性：规定元素应该生成的框的类型**
  * **none**：此元素**不会被显示**
  * **block**：此元素将**显示为块级元素**，此元素前后会带有换行符
  * **inline**：默认。此元素会被显示为**内联元素**，元素前后没有换行符

## 2.5 CSS的布局（浮动）float

* **浮动元素**会**脱离正常的文档布局流**，并**吸附**到其**父容器的左边或右边**或**另一个浮动元素的边框**。在正常布局中位于该浮动元素之下的内容，此时会围绕着浮动元素，填满其右侧的空间
  * 当框 1 向左浮动时，它脱离文档流并且向左移动，直到它的左边缘碰到包含框的左边缘，覆盖框2
  * 如果把三个框都向左浮动，那框1向左浮动直到碰到包含框，另外两个框向左浮动直到碰到前一个浮动框
  * 如果包含框太窄，无法容纳水平排列的三个浮动元素，那么其它浮动块向下移动，直到有足够的空间；如果浮动元素的高度不同，那么当它们向下移动时可能被其它浮动元素“卡住”

* `float:none|left|right;`        对象象左边|右边浮动
  * `clear:none|left|right|both;`    规定元素的哪一侧不允许其他浮动元素

## 2.6 CSS的布局（定位）position

* **position**的属性值：
  * **absolute** ：生成**绝对定位的元素**，相对于 static 定位以外的第一个**父元素**进行定位。 
    * 可以使用top、bottom等属性进行定位
  * **relative** ：生成**相对定位**的元素，相对于**其正常位置**进行定位 
    * 可以使用top、bottom等属性进行定位

## 2.7 常用样式属性

### 2.7.1 字体属性

* **font-family**：字体

* **font-size**(字体大小)：

  - 普通文本（比如段落）的默认大小是 **16 像素** (16**px**=1em) 

  - 使用 **em 来设置字体大小**

  - 结合使用百分比和 EM，根据body

    ```css
    body {font-size:100%;}
    h1 {font-size:3.75em;}
    p {font-size:0.875em;}
    ```

* **font-style**：normal(正常显示)、italic(斜体显示)、oblique(倾斜显示)

* **font-weight**(字体加粗)：lighter 、normal(400) 、bold(700) 、bolder 、（100~900）

### 2.7.2 文本属性

* **color**：`rgba(,,,)`
* **text-align**：left 、right 、center 、justify 
* **text-transform**(文本大小写)：capitalize 、uppercase 、lowercase 
* **text-decoration**(文本修饰)：underline 、overline 、line-through 、blink 
* **text-indent**(缩进)：px、em
* **vertical-align**(垂直对齐)：top 、bottom 、text-top 、text-bottom 、middle 
* word-spacing(字间距)
* letter-spacing(字符间距)

### 2.7.3 背景属性

* **background-color**
* **background-image**：`background-image: url(test.gif)`
* **background-repeat**(背景重复)：repeat-x(水平方向上重复 ) 、repeat-y 、no-repeat 
* **background-position**(背景定位)：
  * 关键字：center 、top 、bottom 、left 、right （可两两组合）
  * 百分数值：`background-position:0% 0%;或50% 50%;或66% 33%;或100% 100%;`
  * 长度值(元素内边距区左上角的偏移。偏移点是图像的左上角 )：`50px 100px;`
* **background-attachment**(背景附属)：scroll(默认滚动) 、fixed(固定)

### 2.7.4 边框属性

* **border**：`2px,solid,blue;`（宽度、样式、颜色）
* **border-style**(样式)：none(默认)、solid、dashed、double、dotted等
  * 按照上右下左、上(右左)下、(上下)(右左)、全部
* **border-width**：2px、0.1em、（或thin 、medium(默认) 、thick ）
  * 按照上右下左、上(右左)下、(上下)(右左)、全部
* **border-color：** blue、rgb()、#000000、**transparent**(透明)
  * 按照上右下左、上(右左)下、(上下)(右左)、全部



* **border-collapse**(表格的边框合并)：separate(默认分开)、collapse (合并为单一边框)

### 2.7.5 列表属性

* list-style-type：circle(空圆) 、disc(实圆) 、square(实方块) 、decimal (数字) 等等
* list-style-position：outside (默认列表标记放置文本以外)、inside
* list-style-image：`url("test.gif");`
* **list-style**：`square inside url('/i/eg_arrow.gif'`

### 2.7.6 光标属性(cursor)

* auto(默认) ：浏览器设置的光标
* default：默认为箭头
* pointer：指示链接的手型
* text：文本
* wait：沙漏、转圈、表
* help：箭头带问号
* move：四方箭头
* crosshair：十字架
* url：自定义光标的url

### 2.7.7 其他

* text-decoration(a标签的)：none即为无下划线
* display





# 3 JavaScript

## 3.1 简介

* Js是基于<span style="color:red">**对象**</span>和<span style="color:red">**事件**</span>驱动的语言，应用与客户端（提供好了很多对象，可以直接拿过来使用）

* **和Java区别**

  * JavaScript 是**基于对象**的，java是面向对象
  * JavaScript是**弱类型**的语言，java是强类型的语言
  * JavaScript只需**解析**就可以执行，而java需要先编译成字节码文件，再执行

* **特点：**

  * 交互性：信息的动态交互
  * **安全性**：**js不能访问本地磁盘的文件**
  * 跨平台性：能够支持js的浏览器，都可以运行

* **组成：**

  1. ECMAScript
  2. BOM
  3. DOM

* **js和html的结合方式：**

  * 和HTML同一页下载script标签中

    ```html
    <script type="text/javascript">  
    js代码; 
    </script>
    ```

  * 在HTML标签中写入

    ```html
    <img src="img/1.jpg"  id="img1" onclick="javascript:fun2()">
    ```

  * 外部引入

    ```html
    <script type="text/javascript" src="1.js"></script>
    ```

* **Js的原始类型(五个)**和声明变量

  * number：**数字**

  * string：**字符串**

  * boolean：**布尔**

  * object：变量是一种Object类型或Null类型

  * undefined：定义变量未赋值

    **typeof()**：查看当前变量的数据类型，对null返回Object

* **Js的运算符**

  * **不区分整数和小数**

    ```javascript
    var j = 123;
    alert(j/1000*1000);  //123
    ```

  * **字符串的相加(**字符串连接)和**相减**(运算)

    * **NaN**:**表示不是一个数字**

  * **boolean类型**也可以操作

    * 设置成**true**，相当于这个值是**1**;设置成**false**，相当于这个值是**0**

  * **== 和 === 区别（只用===）**

    * == 比较的只是值(会执行类型转换)， === 比较的是**值和类型**

* **Js循环语句**

  * while、do...while

  * for

  * for...in：**遍历数组或者对象的*属性*（对数组或者对象的属性进行循环操作）**

    ```javascript
    for (变量 in 对象) {//指定的变量可以是数组元素，也可以是对象的属性
        在此执行代码
    }
    ```

* **直接向页面输出的语句**

  * 可以向页面输出变量，固定值和html代码

    ```javascript
    document.write("aaa");
    document.wirte("<hr/>");
    ```

* **Js的函数**

  1. ```javascript
     function 方法名(参数列表) {
     	方法体;
     	返回值可有可无（根据实际需要）;
     }
     ```

  2. **匿名函数**

     ```javascript
     var funcName = function(参数列表) {
     	方法体和返回值;
     }
     ```

* **Js的全局变量和局部变量**

  * **全局变量**：在**script标签里面**定义一个变量，这个变量在页面中js部分都可以使用
    1. 在任何**函数之外**放置的var语句；`var foo = value;`
    2. 给**全局对象**添加一个**属性**：`windos.foo = value;`
    3. 直接**使用未经声明**的变量(隐式全局变量)：`foo = value;`
  * **局部变量**：在**方法内部**定义一个变量，只能在方法内部使用
  * **块级作用域问题**
    * Js没有块级作用域，所以推荐在每个函数开头部分声明所有变量
    * ES6提供了新的关键字let声明一个块级作用域的变量

* **script标签放置在 `</body>` 闭合标签之前 **

* **Js函数重载？没有！！！**



## 3.2 对象和函数

### 3.2.1 String对象

* 创建对象`var str = "abc";`
* **属性：length**(字符串长度)
* **方法：**
  1. **与html相关的方法**
     - bold()：加粗
     - fontcolor(): 设置字符串的颜色
     - fontsize(): 设置字体的大小

     - link(): 将字符串显示成超链接 `str4.link("hello.html")`

     - sub() sup(): 下标和上标
  2. **与Java相似的方法**
     - concat(): 连接字符串
     - charAt():返回指定指定位置的字符串，若字符位置不存在，返回空字符串
     - indexOf()：返回字符串位置
     - split()：切分字符串，成数组
     - replace()：替换字符串，传递两个参数：原始字符、要替换成的字符
     - substr()：从第几位开始，向后截取几位
     - substring()：**[**从第几位开始，到第几位结束**)**

### 3.2.2 Array对象

* 创建数组（三种）**存放不同的数据类型的数据**
  * `var arr = [1,2,3];   var arr = [1,"4",true];`
  * `var arr = new Array();`
  * `var arr2 = new Array(3,4,5); //定义一个数组，数组里面的元素是3 4 5` 
    - `var arr1 = new Array(5); arr1[0] = "1"; //定义一个数组，数组的长度是5`
* **特点**：**长度可变**、**length=最大角标+1(切记)**
* **属性：length**（数组的长度），与Java不同的是**可以设置值为0**从而清空，Java中是final不可修改
* for in：遍历的是**属性**
* 方法：
  * concat：数组的连接
  * join()：根据指定的字符**分割数组**`document.write(arr13.join("-"));`,**默认逗号**隔开
  * push()：向数组末尾添加**元素**，**返回数组的新的长度**
    * 如果添加的是一个**数组**，这个时候把数组当做一个**整体字符串**添加进去
  * pop()：表示 删除最后一个元素，**返回删除的那个元素**
  * reverse()：颠倒数组中的元素的顺序，改变原有数组顺序

### 3.2.3 Boolean对象

* `var flag = new Boolean(true);`**不传值时默认为false**

### 3.2.4 Date对象

* 在java里面获取当前时间 `Date date = new Date();`
  格式化`toLocaleString(); //2015年4月17日 11:17:12`

* **Js里面获取当前时间(toLocalString())**

  ```javascript
  var date = new Date();
  document.write(date);  // Fri Apr 17 10:47:46 UTC+0800 2015 
  document.write(date.toLocaleString());//转换成习惯的格式
  ```

* **获取当前的年方法**

  ```javascript
  date.getFullYear()：得到当前的年
  ```

* **获取当前的月方法(0~11)**

  ```javascript
  var month = date.getMonth()+1;
  ```

* **获取当前的日**

  ```javascript
  var day = date.getDate();
  ```

* **获取当前的星期(0~6)**

  ```javascript
  var week = date.getDay();//周日为0
  ```

* **获取当前的小时**

  ```javascript
  var hour = date.getHours();
  ```

* **获取当前的分钟**

  ```javascript
  var minutes = date.getMinutes();
  ```

* **获取当前的秒**

  ```javascript
  var second = date.getSecondes();
  ```

* **获取毫秒数(时间戳)**

  ```javascript
  var time = date.getTime();//返回的是1970.1.1至今的毫秒数
  //使用毫秒数处理缓存的效果（不有缓存）；http://www.baidu.com?毫秒数
  ```

### 3.2.5 Math对象

* 里面的都是**静态**方法，使用可以直接使用 Math.方法()
* **属性：**
  * PI：圆周率
* **方法：**
  * ceil(x): 向上舍入
  * floor(x)：向下舍入
  * round(x)：四舍五入
  * random()：得到**[0.0,1.0)**随机数（伪随机数）

### 3.2.6 全局函数

* 由于**不属于任何一个对象**，**直接写名称**使用

* **方法：**

  * **eval()** ： 执行js代码（如果字符串是一个js代码，使用方法直接执行）

    ```javascript
    var str = "alert('1234');";
    alert(str);    //alert('1234');
    eval(str);    //1234
    ```

  * **encodeURI()** ：对字符进行编码，返回另一个字符。不编码字符有82个

    **decodeURI()**  ：对字符进行解码，返回另一个字符

    encodeURIComponent() 和 decodeURIComponent()。不编码字符有71个

  * **isNaN()**:判断当前字符串是否**不是数字**

  * **parseInt() parseFloat()**：类型转换、进制转换

    ```javascript
    var str3 = "123";
    document.write(parseInt(str3)+1);
    //进制转换
    parseInt('11',2)//3
    ```

### 3.2.7 函数重载

* **Js中不存在函数重载！**调用**最后一个方法！**把**传递的参数保存到 arguments数组里**面来模拟函数重载

  ```javascript
  var add = function(){
      let sum = 0;
      for(let i = 0;i<arguments.length;i++){
          sum+=arguments[i];
      }
      return sum;
  }
  ```

### 3.2.8 其他函数

* 自调函数：定义()()，第一个小括号是函数定义，第二个小括号是函数调用
* 回调函数：作为参数传递的函数 
* …………

.

## 3.3 BOM

* browser object model:浏览器对象模型

* **对象：**

  * navigator： 获取客户机的信息（**浏览器的信息**）`document.write(navigator.appName);`

  * screen: 获取屏幕的信息   `document.write(screen.width+"*"+screen.height);`

  * **location**: 请求url信息

    * **href属性**   
      * 获取请求的url地址：`document.write(location.href); `
      * 设置url地址：`<input type='button' value='跳转'onclick="JavaScript:location.href='xxx.html'")`

  * **history**：请求的url的历史记录

    * 到上一个url：`history.back();` `history.go(-1);`
    * 到下一个url：`history.forWard();` `history.go(1);`

  * **window（重要）**

    * **顶层对象**（所用的bom对象都是在window里面操作的,包含以上对象）

    * **属性：**

      * **opener：返回对创建此窗口的窗口的引用** 

    * **方法：**(可以不带window)

      * **window.alert()** : 页面弹出一个框，显示内容，可省略window.一般还是用**console.log()**

      * **confirm()**： 确认框，返回值是boolean   `var flag = window.confirm("确认删除？"); `

      * **prompt()**：输入的对话框   `window.prompt("输入您的年龄",0); `

      * **open()**：打开一个新的窗口   `window.open("url地址","","height=300 width=400"); `

      * close()：关闭窗口(浏览器兼容性比较差)

      * **定时器：**

        * **setInterval**("function",millisec)：每隔millisec毫秒执行一次代码串

        * **clearInterval**(): 清除setInterval设置的定时器

          ```javascript
          var id = setInterval("alert('123');",3000);//通过setInterval有一个返回值
          clearInterval(id1);
          ```

        * **setTimeout**("function",millisec)：在millisec毫秒后执行且只执行一次代码串

        * **clearTimeout**() : 清除setTimeout设置的定时器

          ```javascript
          javascriptvar id = setTimeout("alert('abc');",4000);
          clearTimeout(id);
          ```

## 3.4 DOM

* document object model：**文档**（超文本文档）**对象**（提供了属性和方法）**模型**（使用属性和方法操作文档）

* 解析过程：根据html的层级结构，在内存中分配一个树形结构，需要把html中的每部分封装成对象

  * **Node节点对象**：这个对象是**这些对象的父对象**；在对象里面找不到想要的方法时从这里面找
    * **document**对象：整个文档
      * **element**对象：标签对象
        * **attribute**对象
        * **text**对象

### 3.4.1 document对象（表示整个文档）

* **方法：**

  * **write**()：向页面输出变量（值）、html代码

  * **getElementById**()：通过id得到一个元素（标签）**对象**

    ```javascript
    var input = document.getElementById("Id");
    input1.value = "bbbbb";///向input里面设置一个值value
    alert(input1.value); //标签对象.属性名称
    ```

  * **getElementsByName**()：通过标签的name属性值得到标签集合（**数组**）

  * **getElementsByClassName**()：通过标签的class的属性值得到标签集合（**数组**）

  * **getElementsByTagName**("标签名称")：通过标签名称得到元素集合（**数组**）

    只有一个标签时通过`document.getElementsBy(Tag)Name("input")[0]`获取元素（标签）对象

  【注意】由于我们现在访问的是本地文件，js安全性，谷歌浏览器安全级别很高，不允许访问本地文件。在实际开发中，没有这样的问题，实际中不可能访问本地的文件。

### 3.4.2 **element对象（元素对象，标签）**

* 要操作element对象，首先必须要获取到element。使用document里面相应的方法获取

* **方法：**

  * **getAttribute("name")**：获取属性里面的值

    ```javascript
    var input = document.getElementById("inputid");
    alert(input1.getAttribute("value"));
    ```

  * **setAttribute("name","value")**：设置属性的值

  * **removeAttribute("name")**：删除属性，**不能删除value属性**

* **获取标签下面的子标签**的唯一有效办法，使用父节点**getElementsByTagName**方法，不使用childNodes

### 3.4.3 Node对象

* 使用dom解析html时候，需要html里面的**标签**、**属性**和**文本**都封装成对象

  * **标签**节点对应的值
    **nodeType： 1**
    nodeName： 大写标签名称  比如SPAN
    nodeValue: null
  * **属性**节点对应的值

    **nodeType： 2**
    nodeName： 属性名称
    nodeValue: 属性的值

  * **文本**节点对应的值

    **nodeType： 3**
    nodeName： #text
    nodeValue: 文本内容

* 关系：

  * 父节点

    parentNode：得到父节点

  * 子节点

    childNodes：得到所有子节点，**兼容性很差**（chrome中空格和换行也算成子节点）

    firstChild：获取第一个子节点，**兼容性很差**

    lastChild：获取最后一个子节点，**兼容性很差**

  * 同辈节点

    nextSibling: 返回一个给定节点的下一个兄弟节点，**兼容性很差**

    previousSibling：返回一个给定节点的上一个兄弟节点，**兼容性很差**

### 3.4.4 操作DOM树

- **document.createElement("标签名称")**

- **document.createTextNode("文本内容")**


* **appendChild()**：添加子节点到末尾，**类似于剪贴**的效果

* **insertBefore(newNode,oldNode)**：在某个节点之前插入一个新的节点，没有insertAfter()方法

* **removeChild()**：通过父节点删除，不能自己删除自己   `ul31.removeChild(li24)`

* **replaceChild(newNode,oldNode)**：通过父节点替换，不能自己替换自己

* **cloneNode(boolean)**：复制节点，boolean表示**是否复制子节点**

  把ul列表复制到另外一个div里面

  1. 获取到ul
  2. 执行复制方法 cloneNode方法复制 true
  3. 把复制之后的内容放到div里面去



* **innerHTML属性**

  * 这个属性不是dom的组成部分，但是大多数浏览器都支持的属性

  * 作用：

    1. 获取标签的文本内容
    2. 向标签里面设置内容（可以是html代码）

    ```javascript
    var span1 = document.getElementById("sid");
    alert(span1.innerHTML);
    
    var div11 = document.getElementById("div11");
    div11.innerHTML = "<h1>AAAAA</h1>";
    ```

## 3.5 事件(event对象)

* onload：文档被浏览器**加载**时触发，**只能写一次**;**一般在body标签中注册函数**；所有的其他操作（**匿名方式**）

  ```javascript
  window.onload = function () {
      document.getElementById("btn").onclick = function () {
          alert("haha");
      }
  }
  ```

* **onsubmit**：表单**提交**时触发的事件(**表单校验**)**在form后注册函数，有返回值true/false**

* **onChange：域的内容改变**/选择的值发生变化事件(输入框/下拉) 

* **点击、改变事件**

  * **onclick：**鼠标点击事件(按钮) 
  * **ondblclick**：鼠标双击事件

* **焦点事件**(表单校验)

  * **onFocus：**组件获得焦点事件(输入框/单选/多选/下拉) ，光标闪动
  * **onBlur：**组件失去焦点事件(输入框/单选/多选/下拉) 

* **鼠标事件**

  * **onmouseover**：鼠标移动到组件上时触发
  * **onmouseout**：鼠标移出组件时触发
  * **onmousemove**：鼠标移动就触发
  * **onmousedown**：鼠标按键按下时触发
  * **onmouseup**：鼠标按键松开时触发

* 键盘事件
  * onkeydown：某个键盘按键被按下
  * onkeyup：某个键盘按键被松开
  * onkeypress：某个键盘按键被按下并松开



## 3.6 案例

### 3.6.1 在末尾添加节点

1. 获取到`ul`标签
2. 创建`li`标签：`document.createElement("标签名称")`
3. 创建文本节点：`document.createTextNode("文本内容")`
4. 把文本添加到`li`下面：`appendChild()`
5. 把`li`添加到`ul`末尾：`appendChild()`

### 3.6.2 动态显示时间

* Date、toLocalString、innerHTML、setInterval

### 3.6.3 左右列表

* select、option、multiple、selected=true/false、appendChild()剪贴的length变化

```javascript
var left = document.getElementById("left");
var leftlist = left.getElementsByTagName("option");
var right = document.getElementById("right");
var rightlist = right.getElementsByTagName("option");

var toRight = function () {
    for (let i = 0; i < leftlist.length; i++) {
        if (leftlist[i].selected) {
            right.appendChild(leftlist[i]);
            i--;
        }
    }
}
var allToRight = function () {
	for (let i = 0; i < leftlist.length; i++) {
		right.appendChild(leftlist[i]);
		i--;
	}
}
```

### 3.6.4 动态生成表格

```javascript
var show = function(){
    var row = document.getElementById("row").value;
    var col = document.getElementById("col").value;
    alert(row+"---"+col);
    var tab="";
    for(let i =0;i<row;i++){
        tab+="<table border='1'><tr>";
        for(let j=0;j<col;j++){
            tab+="<td>aa</td>";
        }
        tab+="</tr></table>";
    }
    var div1 = document.getElementById("div1");
    div1.innerHTML = tab; 
}
```

### 3.6.5 表单校验1(return)

```html
<form method="post" action="" onsubmit= "return fun1()">
```

```javascript
var fun1 = function () {
    var username = document.getElementById("username").value;
    if(username==="") {
            document.getElementById("usernameText").innerHTML = "请填写用户名";
            return false;
    }
}
```

### 3.6.6 表单校验2

```html
密码：<input type="password" id="password" name="password" onfocus="onfocus1('password','密码必填')" onblur="onblur1('password','密码不能为空')">
<span id="passwordText" class="tipText"></span><br/>

确认密码：<input type="password" id="rePassword" name="rePassword" onfocus="onfocus1('rePassword','**必填')" onblur="onblur1('rePassword','两次密码不一致')">
<span id="rePasswordText" class="tipText"></span><br/>
```

```javascript
//获得焦点时，校验是否为空，并在tip中填写灰色提示信息
var onfocus1 = function (id,msg) {
    var userValue = document.getElementById(id).value;
    if (userValue === "") {
      document.getElementById(id+"Text").innerHTML = "<span style='color: gray;'>"+msg+"</span>";
    }
}

//失去焦点时，先判断是否是重复密码框
var onblur1 = function (id,msg) {
  if(id==="rePassword"){
    if (document.getElementById(id).value!==document.getElementById("password").value){
       document.getElementById(id+"Text").innerHTML = msg;
    } else {
       document.getElementById(id+"Text").innerHTML = "";
    }
  } else {
    var userValue = document.getElementById(id).value;
    if (userValue === "") {
       document.getElementById(id+"Text").innerHTML = msg;
    } else {
       document.getElementById(id+"Text").innerHTML = "";
    }
  }
}
```

### 3.6.7 轮播图(正常不这么做)

```javascript
//注册onload事件
var init = function () {
    setInterval("changeImg()", 5000);
}
var i = 1;
var changeImg = function () {
    i++;
    document.getElementById("img1").src = "img/" + i + ".jpg";
    if (i == 3) {i = 0;}
}
```

### 3.6.8 定时弹出广告(style.display)

```javascript
//注册onload事件
var init = function () {
	showTime = setTimeout("showAd()",1000);
}
var showAd = function () {
    document.getElementById("adImg").style.display="block";
    clearTimeout(showTime);
    hiddenTime = setTimeout("hiddenAd()",5000);
}
var hiddenAd = function () {
    document.getElementById("adImg").style.display="none";
    clearTimeout(hiddenTime);
}
```

### 3.6.9 表格隔行换色(tBodies、rows)

```javascript
//这样也可以获得行数
//var tbody = document.getElementsByTagName("tbody")[0];
//var rows = tbody.getElementsByTagName("tr").length;

var tb = document.getElementById("table1");
var rows = tb.tBodies[0].rows.length;
for (let i = 0; i < rows; i++) {
    if (i % 2 === 0) {
        tb.tBodies[0].rows[i].style.backgroundColor = "gray";
    } else {
        tb.tBodies[0].rows[i].style.backgroundColor = "yellow";
    }
}
```

### 3.6.10 表格高亮显示(style.background)

```html
<tr id="tr0" onmouseover="change('tr0','over')" onmouseout="change('tr0','out')">*</tr>
```

```javascript
var changeColor = function (id,flag) {
    if (flag==='over'){
        var tr = document.getElementById(id);
        tr.style.backgroundColor = "gray";
    } else if (flag==='out') {
        var tr = document.getElementById(id);
        tr.style.backgroundColor = "white";
    }
}
```

### 3.6.11 全选/反选

```javascript
//checkbox
var checkAll = function (id) {
    var ckAll = document.getElementById(id);
    var ck = document.getElementsByClassName('ck');
    if (ckAll.checked){
        // alert(tr.checked);
        for(let i=0;i<ck.length;i++){
            ck[i].checked=true;
        }
    } else {
        for (let i =0;i<ck.length;i++){
            ck[i].checked=false;
        }
    }
}
//button
var reCheck = function () {
    var ck = document.getElementsByClassName("ck");
    for(let i=0;i<ck.length;i++){
        if (ck[i].checked){
            ck[i].checked=false;
        } else {
            ck[i].checked=true;

        }
    }
}
```

### 3.6.12 省市联动(this.value,options)

```javascript
var change1 = function (obj) {
    var arr = [['陕西','西安','商洛','延安','安康'],
               ['河北','石家庄','廊坊','秦皇岛','雄安'],
               ['广东','深圳','珠海','广州','不知道']];
    var shi = document.getElementById('shi');
    shi.options.length=0;
    //var value = shi.getElementsByTagName("option");
    //for(let i=0;i<value.length;i++){
    //    shi.remove(value[i]);
    //    i--;//记得删除时本身就减少了，和java迭代器修改参数时问题一样
    //}
    for (let i=0;i<arr.length;i++){
        if(arr[i][0]===obj){
            for(let j = 1;j<arr[i].length;j++){
                var option = document.createElement("option");
                var text = document.createTextNode(arr[i][j]);
                option.appendChild(text);
                shi.appendChild(option);
            }
        }
    }
}
//传值<select name="sheng" onchange="change1(this.value)"></select>
```







# 4 jQuery

## 4.1 jQuery概述

* 是JavaScript的一个库，不带min的和带min的版本区别在于格式

* 区别：

  * **jQuery的加载比js快**，jQuery在dom树结构绘制完毕就会加载；而js在整个页面加载完毕才加载
  * jQuery没有覆盖问题，并且按顺序执行；而js存在覆盖问题

* 基本语法：**jQuery(选择器)**、**$(选择器)**，jQuery对象内部以**数组**存放匹配的数据，若只有一个，索引号为0

  * jQuery对象和DOM对象转换(jQuery对象和js对象无法互相操作属性和方法)
    * DOM-->jQuery：**jQuery(DOM对象)**
    * jQuery-->DOM：**`$(选择器)[0]或$(选择器).get(0)`**

* **页面加载：**

  * **ready**()：用于页面加载成功后执行，与window.onload()一样。可以简写如下

    ```javascript
    $(document).ready(function(){
        alert("jQuery页面加载");
    });
    
    $(function () {
        alert("hello")
    });
    ```

## 4.2 选择器

* **基本选择器**

  * **#id**
  * **.class**
  * **element**
  * **\*** ：选择所有元素

* **组合选择器**

  | 组合  | 选择                                                         |
  | ----- | ------------------------------------------------------------ |
  | A,B   | 满足A（和/或）B的任意元素                                    |
  | A B   | 满足条件：**B**是A的**后代结点**（B是A的子节点，或者A的子节点的子节点） |
  | A > B | 满足条件：**B**是A的**直接子节点**                           |
  | A + B | 满足条件：**下一个兄弟选择器**（AB有相同的父结点，**B**是A的**紧跟着的兄弟节点**） |
  | A ~ B | 满足条件：**一般兄弟选择器**（AB有相同的父节点，**B**是A之后的**所有兄弟节点**） |

* **属性选择器**

  | 属性         | 选择                                                        |
  | ------------ | ----------------------------------------------------------- |
  | [attr]       | 包含 attr 属性的**所有元素**                                |
  | [attr=val]   | 选择 attr 属性被赋值**为 val** 的所有元素                   |
  | [attr~=val]  | 选择 attr 属性里被空白分开的字符串里**其中一个是val**的元素 |
  | [attr\|=val] | 选择attr属性的值是 **val 或值以 val- 开头**的元素           |
  | [attr^=val]  | 选择attr属性的值**以 val 开头（包括 val）**的元素           |
  | [attr$=val]  | 选择attr属性的值**以 val 结尾（包括 val）**的元素           |
  | [attr*=val]  | 选择attr属性的值中包含**子字符串 val** 的元素               |

* **基本过滤选择器**

  * :**first**
  * :**last**
  * :not(...)：删除指定内容。如：1234:not(3)--->124
  * :**even**：偶数，从0开始计数。操作索引号，页面显示奇数项
  * :**odd**：奇数
  * :eq(index)：指定第几个
  * :gt(index)：大于n个
  * :lt(index)：小于n个
  * :header：获得标题
  * :animated：获得动画的
  * :focus：获得焦点
  * :first-child：第一个子元素
  * :last-child：最后一个子元素
  * ……

* 内容过滤选择器

  - :contains：匹配包含指定文本的元素

* **表单属性过滤选择器**

  * :input：匹配所有 input, textarea, select 和 button 元素（其他的只匹配自己）
  * :enabled：可用
  * :disabled：不可用
  * **:checked**：选中（radio、checkbox）
  * **:selected**：选择（select）

## 4.3 属性

* **属性操作**：从jQuery 1.6开始，**尚未设置的属性该.attr()方法返回undefined**。**检索和修改DOM属性，如checked，selected或disabled等boolean值**元素形式的元素状态，使用**.prop()**方法
  * **.attr(name)** 
  * .attr(properties) 
  * **.attr(key, value)** 
  * .attr(key, fn) 
  * .removeAttr(name) 
  * **.prop用法同attr**

* **CSS类**
  - **.addClass**(class)
  - **.removeClass**(class)
  - **.toggleClass**(class)
    - **.css**(name | properties | [name,val | fn])：自己练习用，开发时不用，用上面那个
    - 操作CSS的方法有：
      - 设置CSS样式属性`css(name, value)` 设置一个CSS样式属性
      - 通过attr属性设置 / 获取 style属性 `attr('style', 'color:red');` 添加style属性
      - 设置class属性`addClass(class)`等

* **HTML**代码/文本/值
  * **.html**( [val | fn] ) ：带标签，获取值/设置值
  * **.text**( [val | fn] ) ：不带标签，获取值/设置值
  * **.val**( [val | fn | arr] ) ：获取属性的值

## 4.4 遍历

- **.each(callback)**：**jQuery对象使用**（选择器获取后使用）

  ```javascript
  $(arr).each(function (key,value) {});
  ```

- **$.each(object,[callback])**：遍历任意对象。callback可设置function(k,v)

  ```javascript
  $.each(arr,function (key,value) {});
  ```


## 4.5 DOM操作

* 内部插入

  * **append**(content) ：内部结尾处，将B追加到A里面去
  * **appendTo**(content)：内部结尾处，将A追加到B里面去
  * prepend(content)：内部开始处，将B追加到A里面去

  - prependTo(content)：内部开始处，将A追加到B里面去

* 外部插入

  * after(content):外部，将B追加到A后面
  * before(content):外部，将A追加到B前面
  * insertAfter(content):外部，将A追加到B后面
  * insertBefore(content)::外部，将A追加到B前面

* 复制：clone([flag])，克隆匹配的DOM元素并且选中这些克隆的副本

  * flag为true副本具有与真身一样的事件处理能力，默认不用填写代表为false

* 删除

  * **empty**()：**清空**匹配元素中所有的**子节点（所有Node）**
  * **remove**([expr])：expr筛选元素。**删除所有匹配的元素，事件数据也会删除**
  * detach([expr])：expr筛选元素。**删除所有匹配的元素，事件数据会保留**

* 替换
  * replaceWith(html)：把匹配的元素替换为指定元素，用B替换A
  * replaceAll(html)：相反，用A替换B

* wrap(content)

* unwrap()

* wrapAll(content)

* wrapInner(content)

   

## 4.6 事件

* **页面加载：**
  * **ready(fn)**
  * 有时标签绑定一个事件但是函数不执行，原因就是DOM没有加载完毕。可以放在/body之前，或onload里
* **事件绑定**：（或直接绑定）
  * bind(type,[data],fn)
  * unbind(type,[data],fn)
* 鼠标事件
  * mouseover
  * mouseout
  * hover(overfn, outfn)
  * toggle
  * 同js方法

## 4.7 动画

* 显示/隐藏

  * show([s],[e],[fn])

  * hide([s],[e],[fn])

  * toggle([s],[e],[fn])

* 滑动显示/隐藏

  * slideDown([s],[e],[fn]) 

  * slideUp([s,[e],[fn]]) 

  * slideToggle([s],[e],[fn])

* 淡入淡出

  * fadeIn([s],[e],[fn]) 

  * fadeOut([s],[e],[fn]) 

  * fadeToggle([s,[e],[fn]])
  * fadeTo([[s],o,[e],[fn]])：调整元素不透明度



## 4.8 jQuery重写案例

### 4.8.1 定时弹出广告(show()/hide())

```javascript
$(function () {
    showTime = setTimeout("showAd()", 1000);
})
var showAd =function () {
    $("#adImg").show(500);
    clearTimeout(showTime);
    hiddenTime = setTimeout("hiddenAd()",5000);
}
var hiddenAd = function () {
    $("#adImg").hide(1000);
    clearTimeout(hiddenTime);
}
```

### 4.8.2 隔行换色(.css)

```javascript
$(function () {
    // $("tbody>tr:even").css("background-color","red");//偶数行设置
    $("tbody>tr:odd").css("background-color","red");//奇数行设置
})
```

### 4.8.3 全选全不选(attr/prop)

```javascript
$(function () {
    //全选/不选使用prop属性
    $("#ckAll").click(function () {
        $("tbody input").prop("checked", this.checked);

    })
	//反选只会转为DOM对象后再操作
    $("#reCk").click(function () {
        $("tbody input").each(function () {
            this.checked = !this.checked;
        })
    })
});
```

### 4.8.4 省市联动

```javascript
$(function () {
    var arr = new Array();
    arr[0] = ['西安', '商洛', '延安', '安康'];
    arr[1] = ['石家庄', '廊坊', '秦皇岛', '雄安'];
    arr[2] = ['深圳', '珠海', '广州', '不知道'];

    $("#sheng").change(function () {
        $("#shi option").remove();
        // $("#shi").empty();

        var value = parseInt(this.value);
        $.each(arr,function (k,v) {
            if(k===value){
                $(v).each(function (k,v) {
                    var text = document.createTextNode(v);
                    var option = document.createElement("option");
                    option.appendChild(text);
                    $("#shi").append(option);
                    // $(option).appendTo($("#shi"));
                })；
            }
        });
    });
});
```

### 4.8.5 左右列表(双击和selected有关)

```javascript
$(function () {
    //按键
    $("#toRight").click(function () {
        // $("#right").append($("#left option:selected"));
        $("#left option:selected").appendTo($("#right"));
    });
    $("#toLeft").click(function () {
        // $("#left").append($("#right option:selected"));
        $("#right option:selected").appendTo($("#left"));
    });
    $("#allToRight").click(function () {
        $("#left option").appendTo($("#right"));
    });
    $("#allToLeft").click(function () {
        $("#right option").appendTo($("#left"));
    });

    //双击
    $("#left").dblclick(function () {
        $("#left option:selected").appendTo($("#right"));
    });
    $("#right").dblclick(function () {
        $("#right option:selected").appendTo($("#left"));
    });
});
```



## 4.9 使用validate完成表单校验

* 依赖jQuery库，所以先导入jQuery再导入validate，最后导入国际化信息库(提示为中文)

```javascript
$(function () {
    $("#form1").validate({
        rules: {
            username: {
                required: true,
                minlength: 6,
            } , password: {
                required: true,
                maxlength: 16,
                digits: true
            } , rePassword:{
                required:true,
                equalTo:"[name='password']"
            } , email:{
                email:true
            }
        },
        messages: {
            username: {
                required: "用户名不能为空",
                minlength: "用户名不能少于6位",
            } , password: {
                required: "密码不能为空",
                maxlength: "密码不能多于16位",
                digits: "密码只能是数字"
            } , rePassword:{
                required:"确认密码不能为空",
                equalTo:"两次输入的密码不一致"
            } , email:{
                email:"邮箱格式不正确"
            }
        },
        errorElement: "label", //用来创建错误提示信息标签,validate插件默认的就是label
        success: function(label) { //验证成功后的执行的回调函数
        //label指向上面那个错误提示信息标签label
            label.text(" ") //清空错误提示消息
            	.addClass("success"); //加上自定义的success类
        }
    });
});
```

```html
<!--单选，多选标签得在其后面加label标签，否则提示信息位置不正确-->
<label for="sex" class="error" style="display: none;"></label>
```







# 5 Ajax

## 5.1 Ajax概述

* Ajax(asynchronous javascript and xml)：异步的js和xml。它能**使用js异步访问服务器**

### 5.1.1 什么是同步，什么是异步

* 同步：客户端发送请求到服务器端，**当服务器返回响应之前**，客户端都处于**等待**卡死状态
* 异步：客户端发送请求到服务器端，**无论服务器是否返回响应**，客户端都可以**随意做其他事情**，不会被卡死

### 5.1.2 Ajax运行原理

* 页面发起请求，会**将请求发送给浏览器内核中的Ajax引擎**，Ajax引擎会提交请求到服务器端，在这段时间里，客户端可以任意进行任意操作，**直到服务器端将数据返回给Ajax引擎后**，会**触发**你设置的**事件**，从而**执行自定义的js逻辑代码**完成某种页面功能

### 5.1.3 Ajax应用场景

- 谷歌/百度的搜索框自动补全
- 用户注册时（校验用户名是否被注册过）
- 下拉框联动

## 5.2 js原生的Ajax技术(了解)

* js原生的Ajax其实就是围绕浏览器内内置的Ajax引擎对象进行学习的，使用js原生的Ajax完成异步操作：

  1. 创建Ajax引擎对象

     ```javascript
     var xmlHttp = new XMLHttpRequest();
     var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//IE5和IE6,现在基本没了吧
     ```

  2. 为Ajax引擎对象**绑定监听onreadystatechange**（监听服务器已将数据响应给引擎），每当 **readyState** 改变时，就会触发 onreadystatechange 事件

     ```javascript
     xmlhttp.onreadystatechange=function(){
     	if (xmlhttp.readyState==4 && xmlhttp.status==200){
         	document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
     		//还有responseXml这个方法
     	}
     }
     /*    readyState有如下5个状态
      - 0: 请求未初始化
      - 1: 服务器连接已建立
      - 2: 请求已接收
      - 3: 请求处理中
      - 4: 请求已完成，且响应已就绪*/
     
     /*  status情况
       - 200: "OK"
       - 404: 未找到页面*/
     ```

  3. 绑定提交地址**open**

     ```javascript
     xmlHttp.open("GET", "/day23_1/AServlet", true);
     //- 请求方式：可以是GET或POST
     //- 请求的URL：指定服务器端资源，例如；/day23_1/AServlet
     //- 请求是否为异步：如果为true表示发送异步请求，否则同步请求！
     ```

  4. 发送请求

     ```javascript
     //如果是发送POST请求，需要设置Content-Type请求头
     xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
     xmlHttp.send();//POST请求参数写在方法里；如果是GET请求参数可以写在open中URL后
     ```

  5. 接受响应数据：写在onreadystatechange事件函数中

## 5.3 Json

* json是一种**与语言无关**的**数据交换的格式**，作用：
  * 使用ajax进行前后台数据交换
  * 移动端与服务端的数据交换

* json有两种格式：
  * **对象**格式：{"key1":obj,"key2":obj,"key3":obj...}
  * **数组/集合**格式：[obj,obj,obj...]
  * 注意：对象格式和数组格式**可以互相嵌套**；json的**key是字符串**，json的**value是Object**

* json的解析：
  * **json是js的原生内容**，也就意味着**js可以直接取出**json对象中的数据

## 5.4 jQuery的Ajax技术(重点)

* jQuery是一个优秀的js框架，自然对js原生的ajax进行了封装，封装后的ajax的操作方法更简洁，功能更强大，与ajax操作相关的jQuery方法有如下几种，但开发中经常使用的有三种

  <pre style="background:yellow">$.ajax(url[,settings])：是ajax在jquery中的底层实现,最复杂,最强功能.</pre> 

  ```javascript
  $.ajax("/AjaxServlet",
         {
      type: "POST",
      async: true,//是否异步
      data: {"name": "zhangsan", "age": 22},
      success: function (data) {
          alert(data.name);
      },
      error: function () {
          alert("请求失败");
      },
      dataType: "json"
  
  });
  ```

  <pre style="background:yellow">$.get(url[,data][,callback][,type])</pre>

  <pre style="background:yellow">$.post(url[,data][,callback][,type])</pre>

  ```javascript
  $.get(
      "/AjaxServlet",  //url：待载入页面的URL地址
      {"name":"zhangsan","age":22},  //data：待发送 Key/value 参数
      function (data) {  //callback：载入成功时回调函数;data是接收服务端发送的数据
          alert(data.name+":"+data.age)
      },
      "json"  //type：返回内容格式xml, html, script, json, text, _default
  );
  ```

  <pre style="background:yellow">$.getJSON(url[,data][,callback])：getJSON专门用于请求json数据</pre>

  <pre>$.getScript(url[,callback])</pre>

  <pre>load(url[,data][,callback])</pre>

  * GET和POST提交基本差不多，有一个地方不一样就是提交的数据是中文的话，Servlet需要编码，解码
    * 若是POST提交，可以设置request.setCharacterEncoding("utf-8")或者不用管，Ajax本身就解决了
    * 若是GET提交，则需要编码解码



#6 bootstrap4

* 引入依赖

  * css：bootstrap.css
  * js：jquery.js、popper.js(用于弹窗、提示、下拉菜单)、bootstrap.js

* 响应式布局：一个网站可以兼容多个终端

  ```html
  <meta charset="UTF-8">
  <!--响应式 meta 标签;viewport宽度；初始缩放值；最小/最大缩放值；是否允许用户缩放-->
  <!--还有minimum-scale；maximum-scale；user-scalable=true/false-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!--文档兼容模式-->
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  ```

  * viewport
    * 移动设备上的就是**设备的屏幕上能用来显示我们的网页的那一块区域**
  * px
    * css中1px并不等于设备的1px

## 6.1 布局容器

* Bootstrap 需要为页面内容和栅格系统包裹一个容器
  * `.container` **类**用于固定宽度并支持响应式布局的容器
  * `.container-fluid` **类**用于 100% 宽度，占据全部视口（viewport）的容器

## 6.2 栅格系统

Bootstrap 提供了一套响应式、移动设备优先的流式栅格系统，随着屏幕或视口（viewport）尺寸的增加，系统会自动分为最多**12列**

* 栅格系统用于通过一系列的行（row）与列（column）的组合来创建页面布局，你的内容就可以放入这些创建好的布局中。工作原理如下：
  * “行（row）”必须包含在 `.container` （固定宽度）或 `.container-fluid` （100% 宽度）中，以便为其赋予合适的排列（aligment）和内补（padding）。
  * 通过“行（row）”在水平方向创建一组“列（column）”。
  * 你的内容应当放置于“列（column）”内，并且，只有“列（column）”可以作为行（row）”的直接子元素。
  * 类似 `.row` 和 `.col-xs-4` 这种预定义的类，可以用来快速创建栅格布局。Bootstrap 源码中定义的 mixin 也可以用来创建语义化的布局。
  * 通过为“**列（column）”设置 `padding` 属性**，从而创建列与列之间的间隔（gutter）。通过**为 `.row` 元素设置负值 `margin`** 从而抵消掉为 `.container` 元素设置的 `padding`，也就间接为“行（row）”所包含的“列（column）”抵消掉了`padding`。
  * 负值的 margin就是下面的示例为什么是向外突出的原因。在栅格列中的内容排成一行。
  * 栅格系统中的列是通过指定1到12的值来表示其**跨越的范围**。例如，三个等宽的列可以使用三个 `.col-xs-4` 来创建。
  * 如果一“行（row）”中包含了的“列（column）”大于 12，多余的“列（column）”所在的元素将被作为一个整体另起一行排列。
  * 栅格类适用于与屏幕宽度大于或等于分界点大小的设备 ， 并且针对小屏幕设备覆盖栅格类。 因此，在元素上应用任何 `.col-md-*`栅格类适用于与屏幕宽度大于或等于分界点大小的设备 ， 并且针对小屏幕设备覆盖栅格类。 因此，在元素上应用任何 `.col-lg-*`不存在， 也影响大屏幕设备。

## 6.3 其他详细的看文档去吧！

