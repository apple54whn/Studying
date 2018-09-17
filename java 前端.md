# 1 HTML

* HyperText Markup Language：超文本标记语言，网页语言

* **规范**

  ```
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

  `<span></span>`   **行内组合元素**

  

  `<pre></pre>`   **预格式文本**，保留原有格式

  `&lt;&gt;&amp;&nbsp;`   **转义字符**(<>&空格)

  `<!--注释-->`   **注释**

  `<hr/>`   **横线**

  `<br/>`   **换行**

  

* **HTML样式(style)**

  * **字体、颜色、尺寸**

  ```
  <p style="font-family: Consolas;color: white;font-size: 20px;">学习HTML</p>
  ```

  * **文本对齐、背景颜色**

  ```
  <p style="text-align: center;background-color: gray">HTML</p>
  ```

* **列表标签**

  `<ol>` `<ul>` `<li>`   **有序、无序、列表** type='1'或'a'或'i'或'A'或'I'  type='circle'等

  `<dl>` `<dt>` `<dd>`   **定义列表、定义项目、描述**

* **图像标签**

  `<img src = 'test.jpg' width='400' height='300'alt='测试图片'/>` 

* **超链接标签**

  `<a href = 'https://www.baidu.com' target='_blank'>百度链接</a>`默认是_self，不需要跳转时src值填#

  在**定位资源**时：

  ```
  <a name='top'>top</a>
  <a href='#top'>returnTop</a>
  ```

* **表格标签**

  ```
  <table border='1' cellpading='10' cellspacing='10'> <!--边框粗细、边沿与内容间距、单元格间距，但是推荐在CSS中修改-->
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

    * **action**：提交到地址，默认提交到当前的页面 
    * **method**：常用的有两种  get和post，默认是get请求 
      * **get和post区别**
        1. get请求地址栏会携带提交的数据，post不会携带（请求体里面。在第七天时候讲http协议时候）
        2. get请求安全级别较低，post较高
        3. get请求数据大小的限制，post没有限制
    * **enctype**：一般请求下不需要这个属性，做**文件上传**时候需要设置这个属性 

  * **输入项**`<input type="输入项类型">`

    * **text**：普通 (name属性)

    * **password**：密码(name属性)

    * **radio**：单选(name属性且相同，value) checked=true/false

    * **checkbox**：多选(name属性且相同，value) checked=true/false

    * **file**：上传文件

    * **hidden**：隐藏

    * **submit**：提交按钮

      ```
      <input type="submit"/>
      <input type="submit" value="注册"/>
      ```

      * 使用图片提交`<input type="image" src="图片路径"/> `
      * 重置按钮`<input type="reset"/> `

    * **button**:普通按钮，和JS使用

      

    * **下拉输入项**

      **multiple**、**autofocus**、**size**

      ```
      <select name="birth">
      	<option value="1991" selected>1991</option>
      	<option value="1992">1992</option>
      	<option value="1993">1993</option>
      </select>
      ```

    * **文本域**

      ```
      <textarea cols="10" rows="10"></textarea>
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

  ```
  <frameset rows="80,*">                        //把页面划分成上下两部分 
       <frame name="top" src="a.html">             //上面页面
       <frameset cols="150,*">                     //把下面部分划分成左右两部分
  		<frame name="lower_left" src="b.html">  //左边的页面
  		<frame name="lower_right" src="c.html"> //右边的页面
  	</frameset> 
  </frameset> 
  ```

  点击左边的页面超链接，内容显示在右边的页面中

  ```
  <a href="01-hello.html" target="right">超链接1</a>
  ```







# 2 CSS

## 2.1 简介、组合方式

* cascading style sheets，层叠样式表（一层一层的，很多的属性和属性值），将网页内容和样式进行分离

* **分类（三种结合方式）**

  1. 在每个html标签上面都有一个 **style属性**，把css和html结合在一起

     `<div style="background-color:red;color:green;">`

  2. 使用html的一个**style元素**实现，写在head里面

     ```
     <style type="text/css">	
     	div {
     		background-color:blue;
     		color: red;
     	}		
      </style>
     ```

  3. 使用头标签 **link，引入外部css文件**

      ```
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
   | A + B | 满足条件：**B**是A的**下一个兄弟节点**（AB有相同的父结点，并且B紧跟在A的后面） |
   | A ~ B | 满足条件：**B**是A之后的**兄弟节点中的任意一个**（AB有相同的父节点，B在A之后，但不一定是紧挨着A） |

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
  2. 内边距（**padding**:20px|auto;）
     - 统一设置
     - 单独设置（padding-top、padding-bottom、padding-left、padding-right）
  3. 外边距（**margin**:20px|auto）



## 2.5 CSS的布局（浮动）float

* `float:none|left|right`        文本流向对象的左边|右边

## 2.6 CSS的布局（定位）

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

    ```
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





# 3 JavaScript

## 3.1 简介

* Js是基于**对象**和**事件**驱动的语言，应用与客户端（提供好了很多对象，可以直接拿过来使用）

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

  1. ```
     <script type="text/javascript">  
     js代码; 
     </script>
     ```

  2. ```
     <script type="text/javascript" src="1.js"></script>
     ```

* **Js的原始类型(五个)**和声明变量

  * number：**数字**

  * string：**字符串**

  * boolean：**布尔**

  * null：对象引用为空

  * undefined：定义变量未赋值

    **typeof()**：查看当前变量的数据类型，对null返回Object

* **Js的运算符**

  * **不区分整数和小数**

    ```
    var j = 123;
    alert(j/1000*1000);  //123
    ```

  * **字符串的相加(**字符串连接)和**相减**(运算)

    * **NaN**:**表示不是一个数字**

  * **boolean类型**也可以操作

    * 设置成**true**，相当于这个值是**1**;设置成**false**，相当于这个值是**0**

  * **== 和 === 区别（只用===）**

    * == 比较的只是值， === 比较的是**值和类型**

* **Js循环语句**

  * while、do...while

  * for

  * for...in：**遍历数组或者对象的*属性*（对数组或者对象的属性进行循环操作）**

    ```
    for (变量 in 对象) {//指定的变量可以是数组元素，也可以是对象的属性
        在此执行代码
    }
    ```

    

* **直接向页面输出的语句**

  * 可以向页面输出变量，固定值和html代码

   ```
    document.write("aaa");
    document.wirte("<hr/>");
   ```

* **Js的数组(存放不同的数据类型的数据)**

  1. `var arr = [1,2,3];   var arr = [1,"4",true];`
  2. `var arr2 = new Array(3,4,5); //定义一个数组，数组里面的元素是3 4 5` 
     - `var arr1 = new Array(5); arr1[0] = "1"; //定义一个数组，数组的长度是5`

  * **属性  length**：获取到数组的长度

* **Js的函数**

  1. ```
     function 方法名(参数列表) {
     	方法体;
     	返回值可有可无（根据实际需要）;
     }
     ```

  2. **匿名函数**

     ```
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

* 创建数组（三种），看3.1
* **属性：length**（数组的长度）
* 方法：
  * concat：数组的连接
  * join()：根据指定的字符**分割数组**`document.write(arr13.join("-"));`,默认逗号隔开
  * push()：向数组末尾添加**元素**，**返回数组的新的长度**
    * 如果添加的是一个**数组**，这个时候把数组当做一个**整体字符串**添加进去
  * pop()：表示 删除最后一个元素，**返回删除的那个元素**
  * reverse()：颠倒数组中的元素的顺序，改变原有数组顺序

### 3.2.3 Date对象

* 在java里面获取当前时间 `Date date = new Date();`
  格式化`toLocaleString(); //2015年4月17日 11:17:12`

* **Js里面获取当前时间(toLocalString())**

  ```
  var date = new Date();
  document.write(date);  // Fri Apr 17 10:47:46 UTC+0800 2015 
  document.write(date.toLocaleString());//转换成习惯的格式
  ```

* **获取当前的年方法**

  ```
  date.getFullYear()：得到当前的年
  ```

* **获取当前的月方法(0~11)**

  ```
  var month = date.getMonth()+1;
  ```

* **获取当前的日**

  ```
  var day = date.getDate();
  ```

* **获取当前的星期(0~6)**

  ```
  var week = date.getDay();//周日为0
  ```

* **获取当前的小时**

  ```
  var hour = date.getHours();
  ```

* **获取当前的分钟**

  ```
  var minutes = date.getMinutes();
  ```

* **获取当前的秒**

  ```
  var second = date.getSecondes();
  ```

* **获取毫秒数(时间戳)**

  ```
  var time = date.getTime();//返回的是1970.1.1至今的毫秒数
  //使用毫秒数处理缓存的效果（不有缓存）；http://www.baidu.com?毫秒数
  ```

### 3.2.4 Math对象

* 里面的都是**静态**方法，使用可以直接使用 Math.方法()
* **属性：**
  * PI：圆周率
* **方法：**
  * ceil(x): 向上舍入
  * floor(x)：向下舍入
  * round(x)：四舍五入
  * random()：得到**[0.0,1.0)**随机数（伪随机数）

### 3.2.5 全局函数

* 由于**不属于任何一个对象**，**直接写名称**使用

* **方法：**

  * **eval()** ： 执行js代码（如果字符串是一个js代码，使用方法直接执行）

    ```
    var str = "alert('1234');";
    alert(str);    //alert('1234');
    eval(str);    //1234
    ```

  * **encodeURI()** ：对字符进行编码，返回另一个字符。不编码字符有82个

    **decodeURI()**  ：对字符进行解码，返回另一个字符

    encodeURIComponent() 和 decodeURIComponent()。不编码字符有71个

  * **isNaN()**:判断当前字符串是否**不是数字**

  * **parseInt()**：类型转换

    ```
    var str3 = "123";
    document.write(parseInt(str3)+1);
    ```

### 3.2.6 函数重载

* **Js中不存在函数重载！**调用**最后一个方法！**把**传递的参数保存到 arguments数组里**面来模拟函数重载

  ```
  var add = function(){
      let sum = 0;
      for(let i = 0;i<arguments.length;i++){
          sum+=arguments[i];
      }
      return sum;
  }
  ```

### 3.2.7 其他函数

* 自调函数：定义()()，第一个小括号是函数定义，第二个小括号是函数调用
* 回调函数：作为参数传递的函数 
* …………

.

## 3.3 BOM

* browser object model:浏览器对象模型

* **对象：**

  * navigator： 获取客户机的信息（**浏览器的信息**）`document.write(navigator.appName);`

  * screen: 获取屏幕的信息   `document.write(screen.width+"*"+screen.height);`

  * location: 请求url地址,href属性   

    * 获取请求的url地址：`document.write(location.href); `
    * 设置url地址：`document.write("<input type='button' value='跳转' onclick='tiaozhuan();'")`

  * history：请求的url的历史记录

    * 到上一个url：`history.back();` `history.go(-1);`
    * 到下一个url：`history.forWard();` `history.go(1);`

  * **window（重要）**

    * **顶层对象**（所用的bom对象都是在window里面操作的,包含以上对象）

    * **属性：**

      * **opener：返回对创建此窗口的窗口的引用** 

    * **方法：**(可以不带window)

      * **window.alert()** : 页面弹出一个框，显示内容，可省略window

      * **confirm()**： 确认框，返回值是boolean   `var flag = window.confirm("确认删除？"); `

      * **prompt()**：输入的对话框   `window.prompt("输入您的年龄",0); `

      * **open()**：打开一个新的窗口   `window.open("url地址","","height=300 width=400"); `

      * close()：关闭窗口(浏览器兼容性比较差)

      * **定时器：**

        * **setInterval**("Js代码",millisec)：每隔millisec毫秒执行一次代码串

        * **clearInterval**(): 清除setInterval设置的定时器

          ```
          var id = setInterval("alert('123');",3000);//通过setInterval会有一个返回值
          clearInterval(id1);
          ```

        * **setTimeout**("Js代码",millisec)：在millisec毫秒后执行且只执行一次代码串

        * **clearTimeout**() : 清除setTimeout设置的定时器

          ```
          var id = setTimeout("alert('abc');",4000);
          clearTimeout(id);
          ```

## 3.4 DOM

* document object model：**文档**（超文本文档）**对象**（提供了属性和方法）**模型**（使用属性和方法操作文档）

* 解析过程:

  * 根据html的层级结构，在内存中分配一个树形结构，需要把html中的每部分封装成对象

    * document对象：整个文档
    * element对象：标签对象
    * 属性对象

      - 文本对象
      - **Node节点对象**：这个对象是**这些对象的父对象**；在对象里面找不到想要的方法时从这里面找

### 3.4.1 document对象（表示整个的文档）

* **方法：**

  * **write**()：向页面输出变量（值）、html代码

  * **getElementById**()：通过id得到一个元素（标签）**对象**

    ```
    var input = document.getElementById("Id");
    input1.value = "bbbbb";///向input里面设置一个值value
    alert(input1.value); //标签对象.属性名称
    ```

  * **getElementsByName**()：通过标签的name属性值得到标签集合（数组）

  * **getElementsByClassName**()：通过标签的class的属性值得到标签集合（数组）

  * **getElementsByTagName**("标签名称")：通过标签名称得到元素集合（数组）

    只有一个标签时通过`document.getElementsBy(Tag)Name("input")[0]`获取元素（标签）对象

  【注意】由于我们现在访问的是本地文件，js安全性，谷歌浏览器安全级别很高，不允许访问本地文件。在实际开发中，没有这样的问题，实际中不可能访问本地的文件。

### 3.4.2**element对象（元素对象）**

* 要操作element对象，首先必须要获取到element。使用document里面相应的方法获取

* **方法：**

  * **getAttribute("name")**：获取属性里面的值

    ```
    var input = document.getElementById("inputid");
    alert(input1.getAttribute("value"));
    ```

  * **setAttribute("name","value")**：设置属性的值

  * **removeAttribute("name")**：删除属性，**不能删除value属性**

* **获取标签下面的子标签**的唯一有效办法，使用父节点**getElementsByTagName**方法，不使用childNodes

### 3.4.3**Node对象的属性**

* 使用dom解析html时候，需要html里面的**标签**、**属性**和**文本**都封装成对象

  * **标签**节点对应的值
    **nodeType： 1**
    nodeName： 大写标签名称  比如SPAN
    nodeValue: null
  * **属性**节点对应的值

  ​        **nodeType： 2**
  	nodeName： 属性名称
  	nodeValue: 属性的值

  * **文本**节点对应的值

  ​        **nodeType： 3**
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

* **appendChild**：添加子节点到末尾，**类似于剪贴**的效果

* **insertBefore(newNode,oldNode)**：在某个节点之前插入一个新的节点，没有insertAfter()方法

  1. 获取到li13标签
  2. 创建li
  3. 创建文本
  4. 把文本添加到li下面
  5. 获取到ul
  6. 把li添加到ul下面

* **removeChild**：通过父节点删除，不能自己删除自己   `ul31.removeChild(li24)`

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

    ```
    var span1 = document.getElementById("sid");
    alert(span1.innerHTML);
    
    var div11 = document.getElementById("div11");
    div11.innerHTML = "<h1>AAAAA</h1>";
    ```

    

## 3.5 事件

* **onClick：**鼠标点击事件(按钮) 
* **onChange：**选择的值发生变化事件(输入框/下拉) 
* **onFocus：**组件获得焦点事件(输入框/单选/多选/下拉) ，光标闪动
* **onBlur：**组件失去焦点事件(输入框/单选/多选/下拉) 
* **onsubmit**：表单提交时触发的事件 
* onload：文档被浏览器加载时触发. body标签
* onmouseover：鼠标移动到组件上时触发
* onmouseout：鼠标移出组件时触发





## 3.6 案例

### 3.6.1 在末尾添加节点

1. 获取到`ul`标签
2. 创建`li`标签：`document.createElement("标签名称")`
3. 创建文本节点：`document.createTextNode("文本内容")`
4. 把文本添加到`li`下面：`appendChild()`
5. 把`li`添加到`ul`末尾：`appendChild()`

### 3.6.2 动态显示时间

* Date、toLocalString、innerHTML、setInterval

### 3.6.3 全选练习

```
var selectAll = function () {
    var inp1 = document.getElementsByClassName("inp1");
    for (let i = 0; i < inp1.length; i++) {
        inp1[i].checked = true;
        // inp1[i].checked = "checked";
    }
    var inpall = document.getElementById("all");
    inpall.checked = false;
}

var notSelectAll = function () {
    var inp1 = document.getElementsByClassName("inp1");
    for (let i = 0; i < inp1.length; i++) {
        inp1[i].checked = false;
    }
    var inpall = document.getElementById("all");
    inpall.checked = false;
}

var reverseSelect = function(){
    var inp1 = document.getElementsByClassName("inp1");
    for (let i = 0; i < inp1.length; i++) {
        inp1[i].checked = !inp1[i].checked;
    }
}

var method3 = function(){
    var inpall = document.getElementById("all");
    if(inpall.checked){
        selectAll();
        inpall.checked=true;
    }else{
        notSelectAll();
        inpall.checked=false;
    }
}
```

### 3.6.4 左右下拉列表

* select、option、multiple、selected=true/false、appendChild()剪贴的length变化

```
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

### 3.6.5 省市联动

* select、**onchange**()、添加时注意原本若已有option则删除、removeChild()时注意length变化

```
var arr = new Array(4);
arr[0] = ["陕西", "西安", "安康", "宝鸡", "渭南"];
arr[1] = ["广东", "广州", "深圳", "东莞", "佛山"];
arr[2] = ["西藏", "拉萨", "日客则", "墨脱"];
arr[3] = ["河北", "唐山", "秦皇岛", "张家口", "承德"];

var select = function (obj) {
	var shi = document.getElementsByName("shi")[0];
	var option = shi.getElementsByTagName("option");
	if (option.length !== 0) {
		for (let i = 0; i < option.length; i++) {
			shi.removeChild(option[i]);
			i--;
		}
	}
	for (let i = 0; i < arr.length; i++) {
		if (arr[i][0] === obj.value) {
			for (let j = 1; j < arr[i].length; j++) {
				var newoption = document.createElement("option");
				var newtext = document.createTextNode(arr[i][j]);
				newoption.appendChild(newtext);
				shi.appendChild(newoption);
			}
		}
	}
}
```

### 3.6.6 动态生成表格

```
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







# 4 jQuery

* 不带min的和带min的版本区别在于格式

* 基本语法：**jQuery(选择器)**、**$(选择器)**，jQuery对象内部以**数组**存放匹配的数据，若只有一个，索引号为0

  * jQuery对象和DOM对象转换
    * DOM-->jQuery：**jQuery(DOM对象)**
    * jQuery-->DOM：**$username[0]**

* **基本操作：**

  * **ready()**：用于页面加载成功后执行，与window.onload()一样

    ```
    $(document).ready(function(){
        alert("jQuery页面加载");
    });
    ```

* **选择器：**

  * 基本选择器：
    * **#id**
    * **.class**
    * **element**
  * 层级选择器：
    * A    B：A的后代
    * A > B：A的儿子
    * A + B：A的下一个兄弟
    * A ~ B：A的兄弟
  * 基本过滤选择器
    * :first
    * :last
    * :not(...)：删除指定内容。如：1234:not(3)--->124
    * :even：偶数，从0开始计数。操作索引号，页面显示奇数项
    * :odd：奇数
    * ;eq(index)：指定第几个
    * ;gt(index)：大于n个
    * ;lt(index)：小于n个
    * :header：获得标题
    * :animated：获得动画的
    * :focus：获得焦点
  * 属性选择器:
    * 同CSS
  * 表单属性选择器
    * :enabled：可用
    * :disabled：不可用
    * :checked：选中（radio、checkbox）
    * :selected：选择（select）

* 案例

  * 隔行换色：
    * 

  











# 