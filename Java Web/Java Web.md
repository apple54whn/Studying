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



# 第一部分 Web前端

# 1 HTML

- HyperText Markup Language：超文本标记语言，网页语言

- **规范**

  ```html
  <!DOCTYPE html>
  <html lang='en'> <!--字符集-->
  	<head>
  		<meta charset='utf-8'>
  		<title>标题</title>
  	</head>
  	<body>
  	</body>
  </html>
  ```

- **文字、注释标签**

  `<h1>标题</h1>`    **标题**，取值1~6从大到小。是==**块级标签**==，自动在其前后加==**空行**==

  `<p style="color: blue;size: 14px;">html</p>`   **段落**，是==**块级标签**==，自动在其前后加==**空行**==

  `<div></div>`   ==**块级标签**==，自动==**换行**==

  `<span></span>`   **内联标签**，一行内显示



​	`<pre></pre>`   **预格式文本**，保留原有格式

​	`<hr/>`   **横线**

​	`<br/>`   **换行**

​	`&lt;`、`&gt;`、`&amp;`、**`&nbsp;`**、`&copy;`、`&reg;`  分别为：<、>、 **空格**、 &、 &copy;、 &reg;  **特殊字符**

​	`<!--注释-->`   **注释**

* 文字修饰标签

  `<b>`**粗体**`</b>`  

  `<i>`***斜体***`</i>`   

  `<strong>`**强调文本**`</strong>` 

  `<em>`***强调文本***`</em>`  

  `<header>`页眉`</header`：语义标签，只提高可读性

  `<footer>`页脚`</footer`：语义标签，只提高可读性

  `<del>`**删除线**`</del>`  ，不赞成使用`<s></s>`

  `<sup>`**上标**`</sup>`

  `<sub>`**下标**`</sub>`

  `<u>`下划线`</u>`   不赞成使用

* HTML样式(style)尽量使用CSS来操作

  - 字体、颜色、尺寸：`<p style="font-family: Consolas;color: white;font-size: 20px;">内容</p>`

  - 文本对齐、背景颜色：`<p style="text-align: center;background-color: gray">内容</p>`

* **列表标签**

  `<ol>` `<ul>` `<li>`   **有序、无序、列表**，**`reversed`**列表倒序

  *  `type='1'或'a'或'i'或'A'或'I'`请使用CSS替代；`start`HTML5不支持，请使用CSS替代；

  `<dl>` `<dt>` `<dd>`   **列表**带有**项目**和**描述**

* **图像标签**

  `<img src = 'test.jpg' width='400' height='300'alt='无法显示'/>` 

  * 相对路径：以`.`开头，如`./`代表当前目录；`../`代表上级目录

* **超链接标签**

  `<a href='https://www.qq.com' target='_blank'>新窗口打开QQ</a>`：`target`默认是`_self`；

  * 在**定位资源**时：（填`#`默认跳转当前页）

    ```html
    <a id='top'>top</a>
    <a href='#top'>returnTop</a>
    ```

  * `href`中添加`javascript:void(0);`==可以**禁止跳转**==

  * 在**发送邮件**时：`<a href="mailto:xxx@gmail.com">联系我们</a>`

  * 可以**包裹img标签**

* **表格标签**

  ```html
  <!--表格框。      边沿与内容间距、单元格间距，背景颜色，HTML5不支持，推荐在CSS中改变。width、height属性也不赞成使用-->
  <table border='1' cellpadding='10' cellspacing='10' bgcolor="aqua"> 
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
      </tfoot>
  </table>
  ```

  > 注意：表格中占多行的只属于第一行，占多列同理

* **表单标签**：提交用户数据到服务器 

  - `<form></form>`: 定义一个表单的范围 

    - **action**：提交到地址，默认提交到当前的页面（可以是一个页面，也可以是后台代码）
    - **method**：常用的有两种  get和post，默认是get请求 
      - ==**get和post区别**==
        1. get请求地址栏会携带提交的数据（封装到请求行中），post不会携带（封装在请求体里面，http协议）
        2. get请求数据有大小的限制，post没有限制
        3. get请求安全级别较低，post较高
    - **enctype**：一般请求下不需要这个属性，做**文件上传**时候需要设置这个属性 

  - **输入项**`<input type="输入项类型">`

    ```html
    <input maxlength=""      size=""     placeholder=""  readonly  required autofocus ><!--推荐用CSS修改部分-->
        内容最大长度    以字符数计的可见宽度    占位符          只读	   必填   自动获得焦点
    ```

    - `<label for="input_id">`姓名：`</label>`：`for`和`input中id`对应，点`label`区域则`input`获得焦点

    - **text**：文本 (**指定name属性**)

    - **password**：密码(**指定name属性**)

    - **radio**：单选(**指定相同name属性**，**value**) ，checked[=checked]，判断时可以通过true或false

    - **checkbox**：多选(**指定相同name属性**，**value**) ，checked[=checked]，判断时可以通过true或false

      > 注意：**checked、selected、readonly、required、autofocus值填任意都是代表选中等其字面意思**

    - **file**：上传文件(**指定name属性**)

    - **hidden**：隐藏域(**指定name属性**，**value**)：页面看不到但数据会被提交

    - ==**submit**==：提交按钮，点击后跳转至指定action或本页

      还可以获取==**form**==，使用**其`submit()`方法，返回false（true或无返回值都将跳转）**！用于Ajax提交表单

      可以使用==**普通button**==，配合JS来使用Ajax提交表单

      ```html
      <input type="submit"/> <!--默认为提交（中文环境）、submit（英文环境）-->
      <input type="submit" value="注册"/>
      ```

    - **image**：图片提交按钮`<input type="image" src="图片路径"/> `

    - **reset**：重置按钮`<input type="reset"/> `，也可以指定value更改按钮显示信息

    - **button**：普通按钮，和JS使用

    - HTML5新添加的type类型

        - **date**：定义 date 控件（包括年、月、日，不包括时间）
        - time：定义用于输入时间的控件（不带时区）
        - **datetime-local**：定义 date 和 time 控件（包括年、月、日、时、分、秒、几分之一秒，不带时区）
        - **email**：定义用于 e-mail 地址的字段，会用正则自动校验
        - **url**：定义用于输入 URL 的字段，会用正则自动校验
        - **number**：定义用于输入数字的字段
        - color：定义拾色器 
        - range：定义用于精确值不重要的输入数字的控件（比如 slider 控件）

  - **下拉输入项select(指定name属性，option中指定value属性)**，不指定value则默认为标签中数据

    ```html
    <select name="birth" multiple  size="2">
        				 可以多选   可见选项数目
    	<option value="1991" selected>1991</option>
    	<option value="1992">1992</option>
    	<option value="1993">1993</option>
    </select>
    ```

  - 文本域textarea(**指定name属性**)：目前都不用了

    ```html
    <textarea cols="10" rows="10">我是...</textarea>
    ```

> 框架标签，不赞成使用
>
> ```html
> <frameset rows="80,*">                        //把页面划分成上下两部分 
>      <frame name="top" src="a.html">             //上面页面
>      <frameset cols="150,*">                     //把下面部分划分成左右两部分
> 		<frame name="lower_left" src="b.html">  //左边的页面
> 		<frame name="lower_right" src="c.html"> //右边的页面
> 	</frameset> 
> </frameset> 
> ```
>
> 点击左边的页面超链接，内容显示在右边的页面中
>
> ```html
> <a href="01-hello.html" target="right">超链接1</a>
> ```







# 2 CSS

CSS（cascading style sheets，**层叠样式表**）：多个样式可以作用在同一个html元素上，同时生效。将网页内容和样式进行分离

## 2.1 组合方式

- **分类（三种结合方式）**

  1. **内联样式**：在每个html标签上面都有一个 **style属性**，把css和html结合在一起，不推荐使用

     `<div style="background-color:red;color:green;">`

  2. **内部样式**：在**head标签内**，使用**style标签**实现

     ```css
     <style>	/* type="text/css"可以省略 */
     	div {
     		background-color:blue;
     		color: red;
     	}		
      </style>
     ```

  3. **外部样式**：在**head标签内**，使用**link标签**引入外部资源

     ```css
     <link rel="stylesheet" href="css文件的路径" /> /* type="text/css"可以省略 */
     ```

     - style标签中也可以引入css文件，`@import "css/a.css";`或`@import url(css/a.css);`，中间有空格，不常用

- **优先级**：**相同的设置**，由上到下，由外到内，优先级由低到高。 **后加载的优先级高，**就近原则或叠加



## 2.2 CSS的**基本选择器**（三种）

1. **id选择器**             `#idName{...}`
2. **class选择器**        `.className{...}`
3. **标签选择器**         `div{...}`
   - **优先级**   **style > id选择器 > class选择器 > 标签选择器**
     - 有一个特别的语法可以让一条规则**总是**优先于其他规则：`border: none !important;`

* **格式**

  ```css
  选择器名称，选择器名称... {
      属性名：属性值；
      属性名：属性值；
      ……
  }
  ```


## 2.3 CSS的扩展选择器

> \* 选择所有元素：*{ ... }

### 2.3.1 属性选择器

使用场景：input标签等。值要加引号

| 属性                    | 选择                                                         |
| ----------------------- | ------------------------------------------------------------ |
| **[attr]**              | 带有以 attr 命名的属性的元素                                 |
| **[attr=value]**        | 带有以 attr 命名的，且值为"value"的属性的元素                |
| [attr~=value]           | 带有以 attr 命名的属性的元素，并且该属性是一个**以空格作为分隔的值列表**，其中至少一个值为"value" |
| [attr\|=value]          | 带有以 attr 命名的属性的元素，**属性值为“value”或是以“value-”为前缀**（"-"为连字符，Unicode编码为U+002D）开头。用来匹配语言简写代码（如zh-CN，zh-TW可以用zh作为value） |
| [attr^=value]           | 表示带有以 attr 命名的，且值是以"value"**开头**的属性的元素  |
| [attr$=value]           | 表示带有以 attr 命名的，且值是以"value"**结尾**的属性的元素  |
| [attr*=value]           | 表示带有以 attr 命名的，且值**包含**有"value"的属性的元素    |
| [attr operator value i] | 在带有属性值的属性选型选择器表达式的右括号（]括号）前添加**用空格间隔开**的字母i（或I）可以忽略属性值的大小写（ASCII字符范围内的字母） |

### 2.3.2 组合选择器

| 组合  | 选择                                                         |
| ----- | ------------------------------------------------------------ |
| A,B   | 选择**所有A元素和B元素**                                     |
| A B   | ==**后代选择器**==（**B**是A的所有==**后代结点**==，如子节点或者孙节点），可能多个 |
| A > B | ==**子选择器**==（**B**是A的==**直接子节点**==），可能多个   |
| A + B | ==**相邻兄弟选择器**==（AB有相同的父结点，**B**是A的**紧跟着的兄弟节点**），只能一个 |
| A ~ B | ==**一般兄弟选择器**==（AB有相同的父节点，**B**是A**之后**的**所有兄弟节点**），可能多个 |

### 2.3.3 伪类选择器（:）

- 添加到选择器的关键字，**指定要选择的元素的特殊状态**。`td:first-child{}`：first-child**相对的是td的父元素**
  - a标签：未被访问**`:link`**、鼠标悬浮**`:hover`**、鼠标点击不放**`:active`**、已被访问**`:visited`**
  - input标签：获得焦点`:focus`、被选中`:checked`
  - 具体查看文档

### 2.3.4 伪元素选择器（::）

- 匹配处于相关的确定位置的一个或多个元素，例如每个段落的**第一个字**，或者某个元素之前生成的内容 
  - 块元素中第一行`::first-line`、块元素中第一行第一个字`::first-letter`
  - 具体查看文档



## 2.4 CSS的属性

### 2.4.1 字体属性

- **`font-family`(字体)**
- **`font-size`(尺寸)**：普通文本（比如段落）的默认大小是16 px
  - `[length]`：设置为一个**固定的值**，单位**`px`**
  - `xx-small`、`x-small`、`small`、**`medium`**(默认)、`large`、`x-large`、`xx-large` 
  - `%`：设置为基于父元素的一个百分比值
  - `inherit`：规定应该从父元素继承字体尺寸
- **`font-style`(样式)**：`normal`(正常，默认值)、`italic`(斜体)、`oblique`(倾斜)、`inherit`
- **`font-weight`(粗细)**：`normal`(400，默认值) 、`bold`(700) ；指定`100~900`

### 2.4.2 文本属性

- <span name="color">**`color`(颜色)**</span>：`#值1值2值3`00~FF，红绿蓝占比；`rgb( , , )`红绿蓝占比；`rgba(,,,)`红绿蓝透明占比；直接写名称
- **`text-align`(对齐)**：`left` 、`right` 、`center` 、`justify`(两端对齐)、`inherit`
- **`line-height`(行高)**：`normal`(默认)、*`[number]`*、*`[length]`*、*`%`*、`inherit`
- **`vertical-align`**(**垂直对齐**)：`top` 、`bottom` ；相对父元素：`text-top` 、`text-bottom` 、`middle` 
- **`text-transform`**(大小写)：`capitalize`(每个单词大写开头) 、`uppercase` 、`lowercase`、`inherit`
- **`text-decoration`**(修饰)：`underline`、`overline`、`line-through`、`blink`(闪烁的)、`inherit`
- **`text-indent`**(缩进)：*`[length]`*、*`%`*、`inherit`
- `word-spacing`(字间距)：`normal`(默认)、*`[length]`*、`inherit`
- `letter-spacing`(字符间距)：`normal`(默认)、*`[length]`*、`inherit`

### 2.4.3 尺寸属性

* **`width`(宽度)**：`auto`(默认，浏览器计算)、*`[length]`*、*`%`*、`inherit`
* **`height`(高度)**：`auto`(默认，浏览器计算)、*`[length]`*、*`%`*、`inherit`
* 

### 2.4.4 背景属性

* **`background`(背景)**：下列color、image、repeat、position等都可以，复合属性。注意**内部、外部CSS的路径问题**

- **`background-color`(背景颜色)**：<a href="#color">同文本中`color`</a>
- **`background-image`(背景图片)**：`background-image: url(test.gif)`
- **`background-repeat`**(**背景重复**)：`repeat-x`(水平方向上重复 )、`repeat-y`、`no-repeat` 
- **`background-position`**(**背景定位**)：
  - 关键字：`center` 、`top` 、`bottom` 、`left` 、`right` （可两两组合）
  - 百分数值：`background-position:0% 0%;或50% 50%;或66% 33%;或100% 100%;`
  - 长度值(元素内边距区左上角的偏移。偏移点是图像的左上角 )：`50px 100px;`
- **`background-attachment`**(背景附属)：scroll(默认滚动) 、fixed(固定)

### 2.4.5 边框属性

- **`border`(宽度、样式、颜色)**：`2px,solid,blue;`，复合属性
- `border-radius`(**圆角边框**)：*`[length]`*、`%`
- **`border-style`**(样式)：`none`(默认)、`solid`(实线)、`dashed`(虚线)、`double`(双线)、`dotted`(点状边框)...
  - 按照上右下左、上(右左)下、(上下)(右左)、全部
- **`border-width`**：2px、0.1em、（或thin 、medium(默认) 、thick ）
  - 按照上右下左、上(右左)下、(上下)(右左)、全部
- **`border-color`**： `blue`、`rgb()`、`#000000`、transparent(透明)
  - 按照上右下左、上(右左)下、(上下)(右左)、全部

### 2.4.6 表格属性

- **`border-collapse`**(表格的边框合并)：`separate`(默认分开)、`collapse` (合并为单一边框)

### 2.4.6 列表属性

- list-style-type：circle(空圆) 、disc(实圆) 、square(实方块) 、decimal (数字) 等等
- list-style-position：outside (默认列表标记放置文本以外)、inside
- list-style-image：`url("test.gif");`
- **list-style**：`square inside url('/i/eg_arrow.gif'`

### 2.4.7 光标属性(cursor)

- auto(默认) ：浏览器设置的光标
- default：默认为箭头
- pointer：指示链接的手型
- text：文本
- wait：沙漏、转圈、表
- help：箭头带问号
- move：四方箭头
- crosshair：十字架
- url：自定义光标的url

### 2.4.8 其他

- text-decoration(a标签的)：none即为无下划线
- **CSS的display属性：规定元素应该生成的框的类型**
  - **none**：此元素**不会被显示**
  - **block**：此元素将**显示为块级元素**，此元素前后会带有换行符
  - **inline**：默认。此元素会被显示为**内联元素**，元素前后没有换行符



### 2.4.9 盒子模型(控制布局)

- 在进行布局前需要把数据封装到一块一块的区域内（div）
  1. ==外边距（**margin**:20px|auto）==

  2. ==边框（**border:**2px solid blue;）==

     - 统一设置
     - 单独设置（border-top、border-bottom、border-left、border-right）

  3. ==内边距（**padding**:20px|auto;）==：第二个参数auto和居中类似。

     - 统一设置

     - 单独设置（padding-top、padding-bottom、padding-left、padding-right）

     - ==默认情况**内边距改变**会**改变盒子大小**==，解决这个问题可以添加以下属性

       - `box-sizing:border-box`：指定宽度和高度（最小/最大属性）确定最终元素边框box大小


### 2.4.10 定位属性

#### 1、float

- ==当一个元素浮动之后，它会被**移出正常的文档流**，然后向左或者向右**平移**，一直平移直到碰到了所处的**容器的边框**，或者碰到**另外一个浮动的元素**。==

  - 当框 1 向左浮动时，它脱离文档流并且向左移动，直到它的左边缘碰到包含框的左边缘，覆盖框2

  - 如果把三个框都向左浮动，那框1向左浮动直到碰到包含框，另外两个框向左浮动直到碰到前一个浮动框

    ![](F:\GitHub\Studying\Java Web\images\css_positioning_floating_left_example.png)

  - 如果包含框太窄，无法容纳水平排列的三个浮动元素，那么其它浮动块向下移动，直到有足够的空间；如果浮动元素的高度不同，那么当它们向下移动时可能被其它浮动元素“卡住”

    ![](F:\GitHub\Studying\Java Web\images\css_positioning_floating_left_example_2.png)
- `float: none | left | right;` ：对象向左边|右边浮动

  - `clear: none | left | right | both;`：规定元素的哪一侧不允许其他浮动元素

#### 2、position

- **position**的属性值：
  - **absolute** ：生成**绝对定位的元素**，相对于 static 定位以外的第一个**父元素**进行定位。 
    - 可以使用top、bottom等属性进行定位
  - **relative** ：生成**相对定位**的元素，相对于**其正常位置**进行定位 
    - 可以使用top、bottom等属性进行定位







# 3 JavaScript

* JavaScript是一门==**客户端脚本语言**==
  * 运行在**客户端浏览器**中的。每一个浏览器都有JavaScript的解析引擎
  * **脚本语言**：**不需要编译**，直接就可以被浏览器解析执行了

- JavaScript是==基于**对象**和**事件**驱动的语言==
- **和Java区别**

  - JavaScript 是**基于对象**的，java是面向对象
  - JavaScript是**弱类型**的语言，java是强类型的语言
  - JavaScript只需**解析**就可以执行，而java需要先编译成字节码文件，再执行
- **特点：**
  - 交互性：信息的动态交互
  - **安全性**：**js不能访问本地磁盘的文件**
  - 跨平台性：能够支持js的浏览器，都可以运行
- **组成**：==**ECMAScript** + **BOM** + **DOM**==

> Chrome进入Console途径：1、`Ctrl + Shift + J`；2、`Ctrl + Shift + I`再选择Console面板



## 3.1 基本语法

- **与html的结合方式：**

  - **内部引入**

    ```html
    <script>  
    js代码; 
    </script>
    ```

    - 在HTML标签中写入`<img src="img/1.jpg"  id="img1" onclick="javascript:fun2();">`

  - **外部引入**

    ```html
    <script src="js/a.js"></script>    <!--  type="text/javascript"可省略  -->
    <script src="js/b.js"></script>
    ```

  > JavaScript在HTML中定义的位置会影响执行顺序，所以推荐定义在 **`</body>` 闭合标签之前**

- 注释与Java一致：单行：`//注释`；多行：`/* */`

- 语句以`;`结尾，如果一行只有一条语句则`;`可以省略 (不建议)

- **数据类型**

  - **`number`(数值)**：不区分整数和小数、**`NaN`**（not a number）
  - **`string`(字符串)**：单引和双引都可以
  - **`boolean`(布尔)**：true和false
  - **`undefined`(未定义)**：若变量未初始化，则默认赋值为undefined
  - **`null`(空)**：即此处的值为空。一个对象为空的占位符

  - **`object`(对象)**：各种值组成的集合，可以分成三个子类型
    - `object`(狭义的对象)
    - `array`(数组)
    - `function`(函数)

  **`typeof()`**：查看当前变量的数据类型。

  - 对**null返回Object**，Js最初的错误被ECMAScript沿用。现在，null 被认为是对象的占位符，从而解释了这一矛盾

- **变量（弱类型）**：区分大小写，但是变量一般都用小写字母开头的驼峰表示法表示

  - **变量提升**：JavaScript 引擎的工作方式是，**先**解析代码，**获取**所有**被声明的变量**，然后再一行一行地运行

    ```javascript
    //原本语句如下：
    console.log(a); //不会报错，会输出undefined
    var a = 1;
    
    //真正运行的代码如下：
    var a;
    console.log(a); //会输出undefined，表示a已经声明，但为赋值
    a = 1;
    ```

  - ==**JavaScript的全局变量和局部变量**==
    - **全局变量**：在**script标签里面**定义一个变量，这个变量在页面中js部分都可以使用
      1. 在任何==**方法之外**==放置的var语句；`var foo = value;`
      2. 给**全局对象**添加一个**属性**：`windows.foo = value;`
      3. 直接**使用未经声明**的变量(隐式全局变量)：`foo = value;`，不建议使用
    - **局部变量**：在**方法内部**定义一个变量，只能在方法内部使用：`var foo = value;`

  - **块级作用域问题（大括号括起来的区域）**，区块用在循环、判断、function中

    - JavaScript没有块级作用域，所以推荐在每个函数开头部分声明所有变量
    - ES6提供了新的关键字`let`声明一个块级作用域的变量

- **运算符**

  - 一元运算符(同Java)：`++`(自增)、`--`(自减)、`+`(正号)、`-`(负号)
    - ==注意：在JS中，如果**运算数不是运算符所要求的类型**，那么js引擎会**自动的将运算数进行类型转换**==
      ​    ​    ​    ​    ​    * 其他类型转`number`：
      ​    ​    ​    ​    ​        * `string`转`number`：**按照字面值转换**。如果字面值不是数字，则转为**NaN**（不是数字的数字）
      ​    ​    ​    ​    ​        * `boolean`转`number`：`true`转为**1**，`false`转为**0**
  - 算术运算符(同Java)：`+`、`-`、`*`、`/`、`%`(取余)
  - 三元运算符(同Java)：`表达式? 值1:值2;`
  - 赋值运算符：`=`、`+=`、`-+`
  - ==**比较运算符**==：`>`、`<`、`>=`、`<=`、`==`、`===`(**全等于**)，比较方式如下：
    - **类型相同**：**直接比较**
      ​    ​    - 字符串：按照**字典顺序**(ASCII)比较。按位逐一比较，直到得出大小为止。
      ​      - **类型不同**：先进行**类型转换**，再比较
      ​                - `==`比较的只是值(会执行类型转换)
      ​            - `===`**：**==全等于。在比较之前，**先判断类型**，如果类型**不一样**，则直接**返回false**==
  - ==**逻辑运算符**==：`&&`、`||`、`!`
    - 其他类型转`boolean`
      - `number`：`0`或`NaN`为假，其他为真
      - `string`：除了空字符串(`""`)，其他都是`true`。空格占位的不是空字符串
      - `null`&`undefined`:都是`false`
      - 对象：所有对象都为`true`。判断空指针异常（若是字符串都不用判断长度）时直接写`if ( obj )`

- **流程控制语句**

  - if...else...(同Java)
  - while...、do...while...(同Java)
  - **switch**
    - Java中`switch`可以接收的数据类型：`byte`、`short`、`char`、`int`、`Enum`(1.5) 、`String`(1.7)
    - JavaScript中`switch`可以==接收**任意**的**原始数据类型**==
  - **for**：var定义的变量不是局部变量，可以用let来来限制作用范围。==无论布尔表达式是否满足，**步进表达式都会执行**==
    - `for...in`：==遍历**数组**或者**对象**的**属性**==：`for (变量 in 对象)`
    - `for...of`：遍历`iterable`类型，如`Array`、`Map`和`Set`的==**值**==，`for (变量 of 对象)`
  - 跳出循环方法：（`break`、`continue`只针对最内层循环）
    - `break`语句用于跳出代码块或循环
    - `continue`语句用于立即终止本轮循环，返回循环结构的头部，开始下一轮循环
    - `label`标签通常与`break`语句和`continue`语句配合使用，跳出特定的循环



## 3.2 基本对象

### 3.2.1 Function

> 函数(方法)对象

* 创建：

     * `var fun = new Function(形式参数列表,方法体)`;  括号里面的都必须是string类型。忘掉吧！

     * **函数声明**

          ```javascript
          function 方法名称(形式参数列表){ 
              方法体
          }
          ```

     * **函数表达式**

          ```javascript
          var 方法名 = function(形式参数列表){ //匿名函数
              方法体
          }
          ```

     > **函数声明**在JS解析时进行函数提升，因此在同一个作用域内，不管函数声明在哪里定义，该函数都可以进行调用。
     >
     > **函数表达式**的值是在JS运行时确定，并且在表达式赋值完成后，该函数才能调用。

* 方法：

* **属性**：`length`代表**形参的个数**

* 特点：
    1. 方法定义时，形参的类型`var`不用写，返回值类型`var`也不写。
    2. ==方法是一个**对象**，如果定义名称相同的方法，会**覆盖**（可以从对象引用理解），所以**没有方法重载**==
    3. ==在JavaScript中，方法的**调用**只与方法的**名称有关**，和参数列表无关（传递多少个参数无所谓）==
    4. 在方法声明中有一个隐藏的**内置对象**（数组）**`arguments`**，**封装所有的实际参数**。同Java中可变参数类似

* 调用：`方法名称(实际参数列表);`

    > **Js中不存在函数重载！**调用的是**最后一个方法！**可以把**传递的参数保存到 `arguments`数组里**面来模拟函数重载

    ```javascript
    var add = function(){
        let sum = 0;
        for(let i = 0;i<arguments.length;i++){
            sum+=arguments[i];
        }
        return sum;
    }
    ```

### 3.2.2 Array

> 数组对象，继承自`iterable`类型

- 创建：
  1. `var arr = new Array(元素列表);`
  2. `var arr = new Array(默认长度);`，不设置长度时arr为空的数组
  3. `var arr = [元素列表];`
- 方法
    - `join(参数)`：将数组中的元素**按照指定的分隔符拼接**为**字符串**。不传参数默认按`,`拼接（document.write）
    - `push()`：向数组的**末尾添加一个或更多元素**，并**返回新的长度**。
        - 如果添加的是一个**数组**，这个时候把数组当做一个**整体字符串**添加进去
    - `pop()`：表示 删除最后一个元素，**返回删除的那个元素**
    - `reverse()`：颠倒数组中的元素的顺序，改变原有数组顺序
    - `concat`：数组的连接
- 属性：`length`：数组的长度=最大角标+1
    - ==与Java不同的是**可以设置值为0**从而清空，Java中是==`final`==不可修改==
    - 使用`for ... in`遍历的是**属性**，即`length`
    - 使用`for ... of`遍历的是值，ES6添加的方法
- 特点：JavaScript中，==数组**元素的类型可变**，**数组长度可变**。==

### 3.2.3 Date

- 创建：`var date = new Date();`

- 方法：
    * `toLocaleString()`：返回当前`date`对象**对应的时间本地字符串格式**。不调用默认为英文格式
    * `getTime()`：**获取时间戳的毫秒值**。返回的是1970.1.1零点至今的毫秒数
      * 使用毫秒数处理缓存的效果（没有缓存）`http://www.baidu.com?毫秒数`
    * 获取当前的**年**：`getFullYear()`
    * 获取当前的**月**(0~11)：`getMonth()+1`
    * 获取一个月中的某一**日**(1~31)：`getDate()`
    * 获取一**周**中的某一天(0~6)：`.getDay()`，周日为0
    * 获取当前的小**时**(0~23)：`getHours()`
    * 获取当前的**分**钟(0~59)：`getMinutes()`
    * 获取当前的**秒**(0~59)：`getSecondes()`

### 3.2.4 Math

* 里面的都是**静态**方法，使用可以直接使用`Math.方法()`
* 属性：
  * `PI`：π

- 方法：
    - `random()`：得到 **[0.0,1.0)** 之间的随机数（伪随机数）
    - `ceil(x)`：对数进行向上舍入，大于等于 x，并且与它最接近的**整数**
    - `floor(x)`：对数进行向下舍入，小于等于 x，且与 x 最接近的**整数**
    - `round(x)`：把数四舍五入为最接近的**整数**

### 3.2.5 基本类型的包装对象

* Number

* String
  * 创建对象`var str = "abc";`
  * 属性：`length`，字符串长度
  * **方法：**

  1. **与html相关的方法**
     - `bold()`：加粗
     - `fontcolor()`: 设置字符串的颜色
     - `fontsize()`: 设置字体的大小
     - `link()`: 将字符串显示成超链接 `str4.link("hello.html")`
     - `sub()` `sup()`: 下标和上标
  2. **与Java相似的方法**
     - `concat()`: 连接字符串
     - `charAt()`:返回指定指定位置的字符串，若字符位置不存在，返回空字符串
     - `indexOf()`：返回字符串位置
     - `split()`：切分字符串，成数组
     - `replace()`：替换字符串，传递两个参数：原始字符、要替换成的字符
     - `substr()`：从第几位开始，向后截取几位
     - `substring()`：**[**从第几位开始，到第几位结束**)**

- Boolean：`var flag = new Boolean(true);`，不传值时默认为`false`

### 3.2.6 RegExp

> 正则表达式对象

- **正则表达式**：定义**字符串的组成规则**。

  1. **单个字符**：如 `a`、`ab`(a或b)、`a-zA-Z0-9_`。特殊符号代表特殊含义的单个字符：
       - `\d`：单个数字字符 [0-9]
       - `\w`：单个单词字符[a-zA-Z0-9_]
   2. **量词符号**：
         - `?`：表示出现0次或1次
         - `*`：表示出现0次或多次
         - `+`：出现1次或多次
         - `{m,n}`：表示 m<= 数量 <= n
            - m如果缺省： `{,n}`最多n次
            - n如果缺省：`{m,}`最少m次
  3. **开始结束符号**
    * `^`：开始
    * `$`：结束

- **正则对象**：

  1. 创建
    1. `var reg = new RegExp("正则表达式");`，需要注意字符串中的转义字符
    2. `var reg = /正则表达式/;`使用最多
  2. 方法	`test(参数)`：验证指定的字符串是否符合正则定义的规范，返回boolean类型值


### 3.2.7 Global

* 特点：**全局对象**，这个Global中封装的方法不需要对象就可以`方法名();`**直接调用**。  

* 方法：

  * `encodeURI()`：对字符进行url编码，返回另一个字符。不编码字符有82个
  * `decodeURI()`：对字符进行url解码，返回另一个字符
  * `encodeURIComponent()`：对字符进行url编码，编码的字符更多。不编码字符有71个
  * `decodeURIComponent()`：对字符进行url解码
  * `parseInt()`：将字符串转为数字；进制转换
    ​    * 逐一判断每一个字符是否是数字，直到不是数字为止，**将前边数字部分**转为number，第一个也不是数字则为NaN
    ​    * `parseInt('11',2)`，返回3
      * `isNaN()`：判断一个值是否是NaN
            * NaN六亲不认，连自己都不认。NaN参与的`==`比较全部返回`false`

  * `eval()`：==将JavaScript字符串，作为脚本代码来执行==，若字符串不是脚本代码则不执行

    ```javascript
    var str = "alert('1234');";
    alert(str);    //alert('1234');
    eval(str);    //1234
    ```

   - URL编码
         - 传智播客 =  %E4%BC%A0%E6%99%BA%E6%92%AD%E5%AE%A2



### 3.2.8 其他函数

- 自调函数：定义()()，第一个小括号是函数定义，第二个小括号是函数调用
- 回调函数：作为参数传递的函数 
- **闭包**：指的是词法表示包括不被计算的变量的函数，也就是说，**函数可以使用函数之外定义的变量**
- 。。。

## 3.3 BOM

> BOM(browser object model)：浏览器对象模型。对象(首字母大写)的引用有如下：

- ==**window**==

  - **全局对象**（直接使用属性、方法）

  - **属性：**

    - ==**可以获取其他BOM对象**==：navigator、location、history、screen等
    - ==**可以获取DOM对象**==
    - **`opener`：返回对创建此窗口的窗口的引用** 

  - **方法：**(可以不带window)

    - `alert()`：显示带有一段**消息**和一个**确认按钮**的警告框。一般调试还是用**`console.log()`**

    - **`confirm()`**：带有一段**消息**以及**确认、取消按钮**的对话框。返回`boolean`：`window.confirm("确认删除？"); `

    - `prompt()`：显示可**提示用户输入**的对话框：`prompt("输入您的年龄",0); `

    - **`open()`**：打开一个新的窗口，并**返回此对象**：`window.open(URL,name,features,replace)`，都是可选参数

    - **`close()`**：关闭窗口

    - **定时器：**

      - **`setInterval(code,millisec)`**：**每**隔millisec毫秒执行一次**函数**或**代码串**，传方法的**引用不用带引号**

      - **`clearInterval()`**：清除`setInterval`设置的定时器

        ```javascript
        var id = setInterval("alert('123');",3000);
        clearInterval(id);
        ```

      - **`setTimeout(code,millisec)`**：在millisec毫秒后**只执行一次函数**或**代码串**，传方法的**引用不用带引号**

      - **`clearTimeout()`**：清除`setTimeout`设置的定时器

        ```javascript
        var id = setTimeout("alert('abc');",4000);
        clearTimeout(id);
        ```

- ==**location**==：当前URL信息

  - 属性
    - **`href`**：设置或返回完整的 URL：`location.href="https://www.baidu.com"`
    - `search`：设置或返回从？开始的URL（即查询部分）
  - 方法
    - **`reload()`**：**重新加载**当前文档

- ==**history**==：包含用户（在**浏览器窗口中**）访问过的 URL，不是历史记录

  - 属性：
    - `length`：返回当前窗口历史列表中的 **URL 数量**
  - 方法
    - `back()`：加载 history 列表中的**前一个** URL
    - `forward()`：加载 history 列表中的**下一个** URL
    - `go(number | URL)`：加载 history 列表中的某个**具体**页面。`go(-1);`、`go(1);`等同于上述方法，可以传其他值

- navigator： 浏览器的信息`document.write(navigator.appName);//浏览器名称`

- screen：客户端显示屏幕的信息   `document.write(screen.width+"*"+screen.height);`



## 3.4 DOM

> DOM(document object model)：将标记语言**文档**的各个组成部分，封装为**对象**。可以使用这些对象，对标记语言文档进行CRUD的动态操作

- W3C DOM 标准被分为 3 个不同的部分：

  * **核心 DOM** - 针对任何结构化文档的标准模型
    * **Node**：原生节点对象，以下节点对象都继承于此
    * **Document**：文档对象，整个文档树的顶层节点
    * DocumentType：`doctype`标签（比如`<!DOCTYPE html>`
    * **Element**：元素对象，网页的各种HTML标签（比如`<body>`、`<a>`等）
    * **Attribute**：属性对象，网页元素的属性（比如`class="right"`）
    * **Text**：文本对象，标签之间或标签包含的文本
    * Comment：注释对象
    * DocumentFragment：文档的片段
  * XML DOM - 针对 XML 文档的标准模型
  * **HTML DOM** - 针对 HTML 文档的标准模型

- 解析过程：根据html的层级结构，在内存中分配一个树形结构，需要把html中的每部分封装成对象

  ![](F:\GitHub\Studying\Java Web\images\ct_htmltree.png)

### 3.4.1 Node

> 节点对象，其他对象的父对象。所有dom对象都可以被认为是一个节点

- 属性：
  - `nodeType`：一个整数值表示==**节点的类型**==。如Element为1、Attribute为2、Text为3、Document为9
  - `nodeName`：**节点的名称**。如Element为大写标签名、Attribute为属性名、Text为`#text`、Document为`#document`
  - `nodeValue`：一个字符串，表示**当前节点**本身的**文本值**。只有Attribute、Text、Comment有值，其余返回`null`
  - `textContent `：**当前**节点和它的**所有后代**节点的==**所有文本内容**==，自动**忽略**当前节点内部的 **HTML 标签**
  - `baseURI`：一个字符串，表示当前网页的绝对路径
  - `ownerDocument`：当前节点所在的顶层文档对象，document本身的这个属性为`null`。与`getRootNode()`一样作用
  - `previousSiblin `：当前节点==**前**==面的、距离最近的一个**同级节点**，没有同级节点则返回`null`
  - `nextSibling`：紧跟在当前节点==**后**==面的第一个**同级节点**，没有同级节点则返回`null`
  - **`parentNode`**：==当前节点的**父节点**==，可能有三种类型，如Document、Element、DocumentFragment
  - `parentElement`：当前节点的**父元素节点**，排除了上述类型中首尾两个
- 方法(CRUD DOM树)：
  - **`appendChild(newNode)`**：==将其作为**最后一个子节点**，**插入**当前节点==。若`newNode`为DOM中已存在的，相当于**剪贴**
  - `insertBefore(newNode,oldNode)`：节点之前插入一个新的节点，没有insertAfter()方法可以结合`nextSibling`实现
  - `removeChild(Node)`：通过父节点删除指定子节点，并返回被删除的节点。不存在DOM中，但在内存中仍可使用
  - `replaceChild(newNode,oldNode)`：通过父节点用新节点替换一个子节点
  - **`cloneNode(boolean b)`**：**复制节点返回新节点**，boolean表示**是否复制子节点**，会丧失该节点上的事件回调函数

- > `childNodes `：当前节点的所有子节点的`NodeList`集合，但是包括Text、Commnet！空格之类的都包括！别用！
  >
  > `hasChildNodes()`：当前节点是否有子节点，也是包括所有类型节点！空格也算！别用！

#### 1 NodeList 接口（了解）

* `NodeList`实例是一个类似数组不是数组的对象，它的成员是节点对象。通过以下方法可以得到`NodeList`实例
  * `Node.childNodes`：说了别用！省的没注意空格！
  * `document.querySelectorAll()`等节点搜索方法
* 属性：
  * `length`，NodeList 实例包含的节点数量
* 方法：
  * `forEach`，也可以使用for循环。没pop、pust等方法哦！
  * `item(index)`：接受一个整数值作为参数，表示成员的位置，返回该位置上的成员。
* 。。。懒得看了

#### 2 HTMLCollection 接口（了解）

* `HTMLCollection`是一个节点对象的集合，只能包含元素节点（element），不能包含其他类型的节点。它的返回值是一个类似数组的对象，但是与`NodeList`接口不同，`HTMLCollection`没有`forEach`方法，只能使用`for`循环遍历
* 返回`HTMLCollection`实例的，主要是一些`Document`对象的集合属性，比如`document.links`、`docuement.forms`、`document.images`、`document.styleSheets`、`document.scripts`等
* `HTMLCollection`实例都是动态集合，节点的变化会实时反映在集合中
* 属性
  * `length`：返回`HTMLCollection`实例包含的成员数量
* 方法
  * `item()`：接受一个整数值作为参数，表示成员的位置，返回该位置上的成员
  * `namedItem()`：参数是一个字符串，表示`id`属性或`name`属性的值，返回对应的元素节点。如果没有则返回`null`

#### 3 ParentNode 接口

* 只有元素节点（element）、文档节点（document）和文档片段节点（documentFragment）拥有子节点，因此只有这三类节点会继承`ParentNode`接口
* 属性
  * `children`：返回一个`HTMLCollection`实例，成员是当前节点的所有**元素子节点**。该属性只读。
  * `firstElementChild`：当前节点的第一个元素子节点。如果没有任何元素子节点，则返回`null`
  * `lastElementChild`：当前节点的最后一个元素子节点，如果不存在任何元素子节点，则返回`null`
  * `childElementCount`：返回一个整数表示当前节点的所有元素子节点的数目。如果不包含任何元素子节点则返回`0`
* ==方法（如下方法都没有返回值）==
  * **`append()`**：为当前节点的最后一个元素子节点后追加**一个或多个子节点**。可以添加元素子节点、文本子节点
  * **`prepend()`**：为当前节点的的第一个元素子节点前追加**一个或多个子节点**。同append()方法
    * 若是若`newNode`为DOM中已存在的，相当于**剪贴**

#### 4 ChildNode 接口

* 如果一个节点有父节点，那么该节点就继承了`ChildNode`接口
* ==方法（都是本节点调用方法）==
  * `remove()`：==用于从父节点**移除当前节点**，**自己调用删除自己**！因为已知本节点有父节点==
  * `before()`：当前节点的**前**面，插入**一个或多个**同级节点，两者拥有相同的父节点。可以插入元素节点、文本节点
  * `after()`：在当前节点的**后**面，插入**一个或多个**同级节点，两者拥有相同的父节点。同before()方法
  * `replaceWith()`：使用参数节点，**替换**当前节点。参数可以是元素节点，也可以是文本节点

### 3.4.2 Document

* 获取：在html dom模型中可以使用`window`对象来获取，也可以省略。**继承了Node、ParentNode等接口**

- **方法：**

  - **write**()：向页面输出变量（值）、html代码
  - 获取Element对象（**除了`~ById()`，以下方法还可以用在`Element`对象上**）
    - **`querySelector()`**：CSS选择器为参数，如果有多个节点满足匹配条件，则返回**第一个匹配的节点**，没有返回null
    - **`querySelectorAll()`**：返回一个`NodeList`对象，包含**所有匹配给定选择器的节点**。用法同上
      - 【注意】不支持CSS伪元素选择器和伪类选择器
    - **`getElementById("id")`**：id属性获取一个**元素对象**，效率比querySelector()根据ID获取对象高
    - **`getElementsByClassName("className")`**：标签的class的属性值得到元素集合（**`HTMLCollection`实例**）
    - **`getElementsByTagName("tagName")`**：标签名称得到元素集合（**`HTMLCollection`实例**）
    - **``getElementsByName("name")``**：标签的name属性值得到元素集合（**`NodeList`实例**），radio、checkBox等
      - 只有一个标签时通过`document.getElementsBy(Tag/Class)Name("input")[0]`获取元素对象
  - 创建其他DOM对象
    - **`createElement("元素名称")`**：创建**元素节点**，参数为tagName属性，对HTML不区分大小写，但不能加尖括号
    - **`createTextNode("文本内容")`**：创建**文本节点**，可以在内容中添加标签。不能对属性赋值，不会转义单双引
    - `createAttribute(name)`：创建拥有指定名称的属性节点，并返回新的 Attr 对象。通过Node来设置属性
    - `createComment()`：创建注释节点

  【注意】由于我们现在访问的是本地文件，js安全性，谷歌浏览器安全级别很高，不允许访问本地文件。在实际开发中，没有这样的问题，实际中不可能访问本地的文件。

### 3.4.3 Element

- 获取：通过`document`来获取和创建
- 属性
  - **`innerHTML`**：返回一个字符串，等同于**该元素包含的所有 HTML 代码**，该属性**可读写**。用来设置某个节点的内容
  - `textContent`：显示文本或插入的是文本时使用来替代上面方法。原样显示，不像上面方法会转为`&**;`来显示
  - `value`：代表的是元素的value属性，一般用于**`input`标签值的获取**
  - `style`：用来读写该元素的行内样式信息，配合CSS。如display可取值none、block、inner
    - 也可以**提前定义好**类选择器的样式，通过元素的`className`属性来设置其`class`属性值。
- **方法：**
  - **`getAttribute("name")`**：获取属性里面的值

  - **`setAttribute("name","value")`**：设置属性的值

  - **`removeAttribute("name")`**：删除属性，**不能删除value属性**
- **获取标签下面的子标签**的唯一有效办法，使用父节点**`getElementsByTagName()`**方法，不使用childNodes属性



### 3.4.5 Event

- **点击事件**

  - **`onclick`**：点击事件(按钮) 
  - **`ondblclick`**：双击事件

- **焦点事件**(表单校验)

  - **`onfocus`**：组件获得焦点事件(输入框/单选/多选/下拉) ，光标闪动
  - **`onblur`**：组件失去焦点事件(输入框/单选/多选/下拉) 

- **改变事件**

  - **`onchange`：域的内容改变**/选择的值发生变化事件(输入框/下拉) 
  - **`onselect`**：文本被选中

- **表单事件**

  - **`onsubmit`**：表单提交按钮按下时触发的事件(**表单校验**)**在form后注册函数，有返回值true/false**，控制提交与否
  - **`onreset`**：重置按钮按下时

- **加载事件**

  - **`onload`**：文档被浏览器**加载**时触发，**只能写一次**；一般在body标签中注册函数；或用window来调用

    ```javascript
    window.onload = function () {
        document.getElementById("btn").onclick = function () {
            alert("haha");
        }
    }
    ```

- **鼠标事件**

  - **`onmouseover()`**：鼠标移动到组件上时触发
  - **`onmouseout`**：鼠标移出组件时触发
  - **`onmousemove`**：鼠标移动就触发
  - **`onmousedown`**：鼠标按键按下时触发
    - 定义方法时，定义一个形参来接收`event`对象，它的`button`属性可以获取鼠标哪个按钮被点击(0，1，2)
  - **`onmouseup`**：鼠标按键松开时触发

- 键盘事件

  - `onkeydown`：某个键盘按键被按下
  - `onkeyup`：某个键盘按键被松开
  - `onkeypress`：某个键盘按键被按下并松开



## 3.5 案例

### 3.6.1 动态显示时间

```javascript
let ele = document.getElementById("h1");
function setTime() {
    let s = new Date().toLocaleString();
    ele.innerText = s;
}
setInterval(setTime,1000);
```

### 3.6.2 轮播图(正常不这么做)

```javascript
var i = 1;
function changeImg () {
    i++;
    if ( i > 3 )
        i = 1;
    document.getElementById("img1").src = "img/" + i + ".jpg";
}
setInterval(changeImg, 5000);
```

### 3.6.3 定时弹出广告(style.display)

```javascript
showTime = setTimeout(showAd, 3000);//定义为全局变量

function showAd() {
    document.getElementById("adImg").style.display = "block";
    clearTimeout(showTime);
    hiddenTime = setTimeout(hiddenAd, 3000);
}

function hiddenAd() {
    document.getElementById("adImg").style.display = "none";
    clearTimeout(hiddenTime);
}
```

### 3.6.4 动态增删表格

```javascript
document.getElementById("btn").onclick = function () {
    let id = document.getElementById("id").value;
    let name = document.getElementById("name").value;
    let sex = document.getElementById("sex").value;
    //thead
    let tbd = document.getElementsByTagName("thead")[0];

    document.getElementById("btn").onclick = function () {
        let id = document.getElementById("id").value;
        let name = document.getElementById("name").value;
        let sex = document.getElementById("sex").value;
        
        let thd = document.getElementsByTagName("thead")[0];

        thd.innerHTML += "<tr>\n" +
            "        <td>" + id + "</td>\n" +
            "        <td>" + name + "</td>\n" +
            "        <td>" + sex + "</td>\n" +
            "        <td><a href=\"javascript:void(0);\" onclick=\"delTr(this);\" >删除</a></td>\n" +
            "    </tr>";
        //使用方法一个个添加也行，但是麻烦

    }
    function delTr(obj) {
        var parentNode = obj.parentNode.parentNode.parentNode;
        var deleteNode = obj.parentNode.parentNode;
        parentNode.removeChild(deleteNode);
    }
}
```

### 3.6.5 全选/全不选/反选/高亮

```javascript
let inputs = document.getElementsByClassName("check-box");
//全选
document.getElementById("selectAll").onclick = function () {
    for (let i in inputs) {
        inputs[i].checked = true;
    }
}
//全不选
document.getElementById("notSelect").onclick = function () {
    for (let i in inputs) {
        inputs[i].checked = false;
    }
}
//反选
document.getElementById("reverseSelect").onclick = function () {
    for (let i in inputs) {
        inputs[i].checked = !inputs[i].checked;
    }
}
//左上角选择
document.getElementById("ck1").onclick = function () {
    for (let i in inputs){
        inputs[i].checked = this.checked;
    }
}
//表格行高亮
let trs = document.getElementsByTagName("tr");
for (let t in trs){
    trs[t].onmouseover = function () {
        this.style.backgroundColor = "gray";
    }
    trs[t].onmouseout = function () {
        this.style.backgroundColor = "white";
    }
}
```

### 3.6.6 表单校验

```javascript
window.onload = function () {
    //表单校验
    document.getElementById("form").onsubmit = function () {
        return checkUsername();//可添加密码校验
    }
    document.getElementById("username").onblur = checkUsername;//可添加密码校验
   
}
//用户名校验
function checkUsername() {
    var username = document.getElementById("username").value;
    var reg = /^\w{6,12}$/;
    let flag = reg.test(username);
    if (flag) {
        document.getElementById("usernameSpan").innerHTML = "✔";
    } else {
        document.getElementById("usernameSpan").innerHTML = "用户名格式有误";
    }
    return flag;
}
//可添加密码校验等等
。。。。。
```

### 3.6.7 左右列表

- select、option、multiple、selected=true/false、appendChild()剪贴的length变化

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

### 3.6.8 表格隔行换色(tBodies、rows)

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

### 3.6.9 省市联动(this.value)

```javascript
let arr = [['陕西', '西安', '商洛', '延安', '安康'],
           ['河北', '石家庄', '廊坊', '秦皇岛', '雄安'],
           ['广东', '深圳', '珠海', '广州', '不知道']];

document.getElementById("sheng").onchange = function () {
    let option = document.getElementById("shi");//不能直接使用innerHTML来赋值
    option.innerHTML = "";//每次改变时要清空
    for (let i in arr) {
        if (arr[i][0] === this.value) {
            for (let j = 1; j < arr[i].length; j++) {
                option.innerHTML += "<option>" + arr[i][j] + "</option>";
            }
        }
    }
}
```







# 4 jQuery

> JavaScript框架，简化JS开发，本质上就是一些JS文件，封装了JS的原生代码
>
> 不带min的和带min的版本区别在于格式、缩进、大小、注释。一个用于开发环境，一个用于生产环境

- 区别：

  - **jQuery的加载比js快**，jQuery在dom树结构绘制完毕就会加载；而js在整个页面加载完毕才加载
  - jQuery没有覆盖问题，并且按顺序执行；而js存在覆盖问题


## 4.1 基本语法

* 语法：**`$(CSS选择器)`**或**`jQuery(CSS选择器)`**，jQuery对象内部以**==数组==存储匹配的数据**，若只有一个，索引号为0

* 对象**转换**（jQuery对象和JS对象无法互相操作属性和方法）

  * JS-->jQuery：**`$(JS对象)`**
  * jQuery-->JS：**`$(选择器)[0]`或`$(选择器).get(0)`**

* 绑定事件（去掉JS事件中on即可，并给事件方法传入function对象）

  ```javascript
  $("#id1").click(function(){
     //。。。 
  });
  ```

* **入口函数**（DOM树加载完毕执行）：区别在于onload只能定义一次，否则覆盖；而ready可以定义多次

  ```javascript
  //$(document).ready(function(){   //精简如下
  $(function(){
      
  });
  ```

## 4.2 选择器

> 在字符串中

- **基本选择器**

  - **#id**
  - **.class**
  - **element**
  - **\*** ：选择所有元素

- **组合选择器**

  | 组合  | 选择                                                         |
  | ----- | ------------------------------------------------------------ |
  | A,B   | 选择**所有A元素和B元素**                                     |
  | A B   | ==**后代选择器**==（**B**是A的所有**后代结点**，如子节点或者孙节点），可能多个 |
  | A > B | ==**子选择器**==（**B**是A的**直接子节点**），可能多个       |
  | A + B | ==**相邻兄弟选择器**==（AB有相同的父结点，**B**是A的**紧跟着的兄弟节点**），只能一个 |
  | A ~ B | ==**一般兄弟选择器**==（AB有相同的父节点，**B**是A**之后**的**所有兄弟节点**），可能多个 |

- **属性选择器**（值要加引号）

  | 属性                                     | 选择                                                         |
  | ---------------------------------------- | ------------------------------------------------------------ |
  | ==**[attr]**==                           | 带有以 attr 命名的属性的元素                                 |
  | ==**[attr='value']**==                   | 带有以 attr 命名的，且**值为"value"**的属性的元素。          |
  | ==**[attr!='value']**==                  | 带有以 attr 命名的，且**值不为"value"**的属性的元素或**没有这个属性**的元素 |
  | [attr~=value]                            | 带有以 attr 命名的属性的元素，并且该属性是一个**以空格作为分隔的值列表**，其中至少一个值为"value" |
  | [attr\|=value]                           | 带有以 attr 命名的属性的元素，**属性值为“value”或是以“value-”为前缀**（"-"为连字符，Unicode编码为U+002D）开头。用来匹配语言简写代码（如zh-CN，zh-TW可以用zh作为value） |
  | ==[**attr^=value**]==                    | 表示带有以 attr 命名的，且值是以"value"**开头**的属性的元素  |
  | ==[**attr$=value**]==                    | 表示带有以 attr 命名的，且值是以"value"**结尾**的属性的元素  |
  | ==[**attr*=value**]==                    | 表示带有以 attr 命名的，且值**包含**有"value"的属性的元素    |
  | ==\[attr1='value1'][attr2='value2']...== | **复合属性**选择器，需要同时满足多个条件时使用。             |

- **基本过滤选择器**

  - **`:first`**：首元素选择器，获得选择的元素中的第一个元素
  - **`:last`**：尾元素选择器，获得选择的元素中的最后一个元素
  - **`:not`**(selector)：非元素选择器，不包括指定内容的元素。如：1234:not(3)--->124
  - **`:even`**：偶数选择器，从0开始计数。操作索引号，页面显示奇数项
  - **`:odd`**：奇数选择器，从0开始计数
  - **`:eq`**(index)：等于索引选择器，等于指定索引元素
  - **`:gt`**(index)：大于索引选择器，大于指定索引元素
  - **`:lt`**(index)：小于索引选择器，小于指定索引元素
  - **`:header`**：标题选择器，获得标题（h1~h6）元素，固定写法
  - `:animated`：获得动画的
  - `:focus`：获得焦点
  - `:first-child`：第一个子元素
  - `:last-child`：最后一个子元素
  - ……

- **表单属性过滤选择器**

  - **`:enabled`**：可用元素选择器，获得可用元素
  - **`:disabled`**：不可用元素选择器，获得不可用元素
  - **`:checked`**：选中选择器（radio、checkbox）
  - **`:selected`**：选中选择器（select）
  - `:input`：匹配所有 input, textarea, select 和 button 元素

- 内容过滤选择器

  - :contains：匹配包含指定文本的元素

## 4.3 DOM操作

- **内容操作**
  - **`html()`**：获取/设置元素的**标签体**内容   `<a><font>内容</font></a>`  --> `<font>内容</font>`
  - **`text()`**：获取/设置元素的**标签体纯文本**内容   `<a><font>内容</font></a>` --> `内容`，设置时将font也删去了
  - **`val()`**：获取/设置元素的**value属性值**，常用于input标签
- **属性操作**
  1. **通用属性操作**（获取传递1个字符串；设置传递2个字符串；删除传递1个字符串）
     - **`attr()`**: 获取/设置元素的属性
     - **`removeAttr()`**:删除属性
     - **`prop()`**:获取/设置元素的属性
     - **`removeProp()`**:删除属性
       - ==attr和prop**区别**？==
         - 如果操作的是**元素**的**固有属性**，则建议**使用prop**。如checked，selected、disabled、href、src等之类
         - 如果操作的是元素**自定义属性**，则建议**使用attr**。若上述属性在未设置属性值时使用attr，返回undefined
  2. **对class属性操作**
     - **`addClass()`**:添加class属性值
     - **`removeClass()`**:删除class属性值
     - **`toggleClass()`**:**切换**class属性，综合上面2个方法，类似开关
       - toggleClass("one")：判断如果元素对象上存在class="one"，则将属性值one删除掉。否则添加
     - **`css()`**：传递1个字符串为获取值；2个为设置值
- **CRUD操作**（除过empty，其他方法JS原生都有实现，empty可以利用`innerHTML=""`实现）
  - 内部插入（父子），对已存在的元素为剪贴
    - **`append(content)`** ：在A元素结尾追加B
    - **`prepend(content)`**：在A元素开头追加B
    - `appendTo(content)`：在B元素结尾追加A
    - `prependTo(content)`：在B元素开头追加A
  - 外部插入（兄弟），对已存在的元素为剪贴
    - **`before(content)`**：在A元素之前插入B
    - **`after(content)`**：在A元素之后插入B
    - `insertBefore(content)`：在B元素之前插入A
    - `insertAfter(content)`：在B元素之后插入A
  - 删除
    - **`empty()`**：**清空**匹配元素中所有的**子节点（所有Node）**，但是保留当前对象以及其属性节点
    - **`remove([expr])`**：expr筛选元素。**删除所有匹配的元素，事件数据也会删除**，不提供值为删除自己
    - `detach([expr])`：expr筛选元素。**删除所有匹配的元素，事件数据会保留**
  - 替换
    - `replaceWith(html)`：把匹配的元素替换为指定元素，用B替换A
    - `replaceAll(html)`：相反，用A替换B
    - `clone([flag])`：复制，克隆匹配的DOM元素并且选中这些克隆的副本。QQ表情案例
      - flag为true副本具有与真身一样的事件处理能力，默认不用填写代表为false

## 4.4 遍历

- **`.each(callback)`**：**jQuery对象使用**（选择器获取后使用），遍历后的值可能为JS对象，注意方法调用区别

  ```javascript
  $(arr).each(function (key,value) {});
  ```

  * key为索引；value/this是每一个元素对象（JS对象）；
  * 回调函数返回值为false相当于break；返回值为true相当于continue

- **`$.each(object,[callback])`**：遍历任意对象（JS数组等也可以）。

  ```javascript
  $.each(arr,function (key,value) {});
  ```

* **`for...of`**：jQuery3.0后提供的方式，同ES6中使用方法一致

## 4.5 事件

- **页面加载：**
  - **ready(fn)**：有时标签绑定一个事件但是函数不执行，原因就是DOM没有加载完毕。可以放在/body之前，或onload里

    ```javascript
    //$(document).ready(function(){   //精简如下
    $(function(){
        
    });
    ```

- **事件绑定**：

  - jquery标准的绑定方式：`jq对象.事件方法(回调函数)；`

    - 注：如果调用事件方法，不传递回调函数，则会触发浏览器默认行为，如触发事件。

      `表单对象.submit();`：让表单提交

  - on绑定事件/off解除绑定

    - `jq对象.on("事件名称",回调函数)`
    - `jq对象.off("事件名称")`
      - 如果off方法不传递任何参数，则将组件上的所有事件全部解绑

  - toggle事件切换

    - `jq对象.toggle(fn1,fn2...)`：当单击jq对象对应的组件后，会执行fn1.第二次点击会执行fn2.....

    - 注意：1.9版本 .toggle() 方法删除,jQuery Migrate（迁移）插件可以恢复此功能。

      ```javascript
       <script src="../js/jquery-migrate-1.0.0.js" type="text/javascript" charset="utf-8"></script>
      ```

- 鼠标事件
  - mouseover
  - mouseout
  - hover(overfn, outfn)
  - toggle
  - 同js方法

## 4.6 动画

- 显示/隐藏
  - **show**([speed,[easing],[fn]])
    1. speed：动画的速度。三个预定义的值("slow","normal", "fast")或表示动画时长的毫秒数值(如：1000)
        2. easing：用来指定切换效果，默认是"swing"，可用参数"linear"
           ​    * swing：动画执行时效果是 先慢，中间快，最后又慢
              * linear：动画执行时速度是匀速的
     3. fn：在动画完成时执行的函数，每个元素执行一次。
  - **hide**([speed,[easing],[fn]])
  - **toggle**([speed,[easing],[fn]])
- 滑动显示/隐藏
  - **slideDown**([speed,[easing],[fn]])
  - **slideUp**([speed,[easing],[fn]])
  - **slideToggle**([speed,[easing],[fn]])
- 淡入淡出
  - **fadeIn**([speed,[easing],[fn]])
  - **fadeOut**([speed,[easing],[fn]])
  - **fadeToggle**([speed,[easing],[fn]])
  - fadeTo([[s],o,[e],[fn]])：调整元素不透明度



## 4.7 插件

* 增强JQuery的功能

* 实现方式	

  1. `$.fn.extend(object)` ：增强通过jQuery获取的对象的功能，如获取$("#id")对象，并增强其功能

     ```javascript
     //1.定义jqeury的对象插件
     $.fn.extend({
         //定义了一个check()方法。所有的jq对象都可以调用该方法
         check:function () {
             //让复选框选中        
             this.prop("checked",true);//this:调用该方法的jq对象
         },
         uncheck:function () {
             //让复选框不选中
             this.prop("checked",false);
         }
     });
     
     $(function () {
         $("#btn-check").click(function () {
             //获取复选框对象
             $("input[type='checkbox']").check();
         });
     
         $("#btn-uncheck").click(function () {
             //获取复选框对象
             $("input[type='checkbox']").uncheck()
         });
     });
     ```

  2. `$.extend(object)`：增强jQuery对象自身的功能，$或jQuery

    ```javascript
    $.extend({
        max:function (a,b) {
            //返回两数中的较大值
            return a >= b ? a:b;
        },
        min:function (a,b) {
            //返回两数中的较小值
            return a <= b ? a:b;
        }
    });
    //调用全局方法
    var max = $.max(4,3); //4
    var min = $.min(1,2); //1
    ```


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
    $("tbody>tr:even").css("background-color","yello");//偶数行设置
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

- 依赖jQuery库，所以先导入jQuery再导入validate，最后导入国际化信息库(提示为中文)

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

- **Ajax**(asynchronous javascript and xml)：异步的javascript 和xml。它能**使用javascript 异步访问服务器**

* **同步和异步**：客户端和服务器端相互通信的基础上，**客户端发送请求到服务器端**
  * **同步**：客户端必须**等待服务器端的响应**，在等待的期间客户端不能做其他操作
  * **异步**：客户端**不需要等待服务器端的响应**，在服务器处理请求的过程中，客户端可以进行其他的操作
* Ajax 是一种在无需重新加载整个网页的情况下，能够更新部分网页的技术。通过在后台与服务器进行少量数据交换，Ajax 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新，提升用户的体验。传统的网页（不使用 Ajax）如果需要更新内容，必须重载整个网页页面。

* Ajax**运行原理**

  页面发起请求，会**将请求发送给==浏览器内核中的Ajax引擎==**，Ajax引擎会提交请求到服务器端，在这段时间里，客户端可以任意进行任意操作，**直到服务器端将数据返回给Ajax引擎后**，会**触发**你设置的**事件**，从而**执行自定义的js逻辑代码**完成某种页面功能

* Ajax应用场景
  * 谷歌/百度的搜索框自动补全
  * 用户注册时（校验用户名是否被注册过）
  * 下拉框联动

## 5.2 js原生的Ajax技术(了解)

- js原生的Ajax其实就是围绕浏览器内内置的Ajax引擎对象进行学习的，使用js原生的Ajax完成异步操作：

  1. 创建Ajax引擎对象XHR

     ```javascript
     var xmlHttp = new XMLHttpRequest();
     //var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");//IE5和IE6,现在基本没了吧
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



## 5.3 jQuery的Ajax技术(重点)

- jQuery是一个优秀的js框架，自然对js原生的ajax进行了封装，封装后的ajax的操作方法更简洁，功能更强大，与ajax操作相关的jQuery方法有如下几种，但开发中经常使用的有三种

  **`$.ajax( url [, settings ] )`**：是ajax在jquery中的**底层实现，最复杂，最强功能**。url必须有，也可在settings中设置

  ```javascript
  $.ajax({
      url: "AjaxServlet", //请求路径，不加/
      type: "POST", //请求方式
      async: true, //是否异步
      data: {"name": "zhangsan", "age": 22},
      success: function (data) {
          alert(data.name);
      },
      error: function () {
          alert("请求失败");
      },
      dataType: "json" //服务器返回的数据类型，若不指定S将根据HTTP包MIME信息来智能判断
  });
  ```

  **`$.get( url [, data ] [, success ] [, dataType ] )`**：发送get请求

  **`$.post( url [, data ] [, success ] [, dataType ] )`**：发送post请求

  ```javascript
  $.get( //$.post和get没区别
      "AjaxServlet",  //url：待载入页面的URL地址，不加/
      {"name":"zhangsan","age":22},  //data：待发送 Key/value 参数
      function (data) {  //callback：载入成功时回调函数;data是接收服务端发送的数据
          alert(data.name+":"+data.age)
      },
      "json"  //type：返回内容格式xml, html, script, json, text, _default
  );
  ```

  `$.getJSON( url [, data ] [, success ] )`：getJSON专门用于请求json数据

  `$.getScript(url[,callback])`

  `load(url[,data][,callback])`

  - GET和POST提交基本差不多，有一个地方不一样就是提交的数据是中文的话，Servlet需要编码，解码
    - 若是POST提交，可以设置request.setCharacterEncoding("utf-8")或者不用管，Ajax本身就解决了
    - 若是GET提交，则需要编码解码

  **`serialize()`：序列表单内容为字符串**。可用于Ajax提交表单



## 5.4 JSON

* JSON（JavaScript Object Notation）：JavaScript对象表示法

- JSON是一种**与语言无关**的**数据交换的格式**，作用：

  - JSON现在多用于**存储**和**交换文本信息**的语法
  - 进行数据的**传输**
  - JSON 比 XML 更小、更**快**，更易解析

- 语法

  1. 基本规则

     - 数据在**名称/值**对中，**键用双引号**引起来
     - 值的取值类型：
       1. 数字（整数或浮点数）
       2. 字符串（在双引号中）
       3. 逻辑值（true 或 false）
       4. 数组（在方括号中）	`{"persons":[{},{}]}`
       5. 对象（在花括号中） `{"address":{"province"："陕西"....}}`
       6. null
     - 数据由**逗号分隔**：多个键值对由逗号分隔
     - **花括号保存对象**：使用{}定义json 格式
     - **方括号保存数组**：[]

  2. 获取数据：JSON 是JavaScript的原生内容，也就是**JavaScript**可以**直接取**出JSON 对象中的数据

     1. `JSON对象.键名`

     2. `JSON对象["键名"]`

     3. `数组对象[索引]`

     4. 遍历：

        1. `for...in`：遍历**属性**，即key字符串。普通for循环也可以

           ```javascript
           for(let key in person){    
               //alert(key + ":" + person.key); //这样不能获取到。因为key值为字符串，相当于person."name"
               alert(key+":"+person[key]);
           }
           ```

        2. `for...of`：遍历**值**

           ```javascript
           for(let value of person){
               alert(value.name);
           }
           ```

## 5.5 JSON和Java对象的转换

* 常见的JSON解析器：Jsonlib，Gson（谷歌），fastjson（阿里），==**jackson**==（Spring内置）
* 使用步骤
  1. **导**入jackson的相关jar**包**
  2. 创建Jackson**核心对象 `ObjectMapper`**
  3. 如下：

### 5.5.1 Java对象转换JSON

* 调用ObjectMapper的相关方法进行转换
  1. 转换方法
     1. **`writeValueAsString(obj)`**：**将对象转为json字符串**
     2. **`writeValue(参数1，obj)`**，参数1如下：
        - **File**：将obj对象转换为JSON字符串，并保存到指定的文件中
        - **Writer**：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
        - **OutputStream**：将obj对象转换为JSON字符串，并将json数据填充到字节输出流中
  2. 注解（在JavaBean的属性上注解）
     1. `@JsonIgnore`：排除属性
     2. **`@JsonFormat(pattern = "yyyy-MM-dd")`**：属性值得格式化，pattern自定义
  3. 复杂java对象转换
     1. List：数组
     2. Map：对象格式一致

### 5.5.2 JSON转为Java对象

* 调用ObjectMapper的相关方法进行转换：`readValue(json字符串数据,Class)`

## 5.5 案例

### 1 异步校验用户名是否存在

```javascript
$("#inputusername").blur(function () {
    var username = $(this).val();
    //期望服务器响应回的数据格式：{"userExsit":true,"msg":"此用户名太受欢迎,请更换一个"}
    //                         {"userExsit":false,"msg":"用户名可用"}
    $.post(
        "/CheckServlet",
        {"username":username},
        function(data){
            if(data.userExsit){
                $("#usernameTag").text(data.msg);
                $("#usernameTag").css("color","red")
            } else {
                $("#usernameTag").text(data.msg);
                $("#usernameTag").css("color","green")
            }
        },
        "json"
    )
});
```

```java
//设置响应的数据格式为json
response.setContentType("application/json;charset=utf-8");
String username = request.getParameter("username");//获取用户名
//期望服务器响应回的数据格式：{"userExsit":true,"msg":"此用户名太受欢迎,请更换一个"}
//                         {"userExsit":false,"msg":"用户名可用"}
Map<String,Object> map = new HashMap<String,Object>();
if("tom".equals(username)){ //存在    
    map.put("userExsit",true);
    map.put("msg","此用户名太受欢迎,请更换一个");
}else{ //不存在
    map.put("userExsit",false);
    map.put("msg","用户名可用");
}

//将map转为json，并且传递给客户端
ObjectMapper mapper = new ObjectMapper();
mapper.writeValue(response.getWriter(),map);
```

* 服务器响应的数据，在客户端使用时，要想当做**json数据格式使用**。有两种解决方案：
  1. `$.get()`或`$.post()`或`$.ajax()`：将参数**dataType**指定为"json"
  2. 在**服务器端设置MIME类型**`response.setContentType("application/json;charset=utf-8");`

### 2 站内搜索









# 6 bootstrap

> 详细内容访问[Bootstrap中文网](http://www.bootcss.com/)

- **引入依赖**

  - css：`bootstrap.css`
  - js：`jquery.js`、`popper.js`(用于弹窗、提示、下拉菜单。版本3没有这个)、`bootstrap.js`

- **响应式布局**：一个网站可以兼容多个终端

  ```html
  <meta charset="UTF-8">
  <!--响应式 meta 标签;viewport宽度；初始缩放值；最小/最大缩放值；是否允许用户缩放-->
  <!--还有minimum-scale；maximum-scale；user-scalable=true/false-->
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!--文档兼容模式-->
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  ```

  - viewport
    - 移动设备上的就是**设备的屏幕上能用来显示我们的网页的那一块区域**
  - px
    - css中1px并不等于设备的1px

* 步骤
  1. 定义布局容器：`container`、`.container-fluid`
  2. 定义行：`row`
  3. 定义列：`col-xs-*`、`col-sm-*`、`col-md-*`、`col-lg-*`、`hidden-**`（可以让元素在某个屏幕大小设备**不显示**）

## 6.1 布局容器

- Bootstrap 需要为页面内容和栅格系统包裹一个容器
  - `.container` **类**用于固定宽度（根据不同设备左右有固定留白，但xs没有留白）并支持响应式布局的容器
  - `.container-fluid` **类**用于 100% 宽度，占据全部视口（viewport）的容器

## 6.2 栅格系统

Bootstrap提供了一套响应式、移动设备优先的流式栅格系统，随着屏幕或视口(viewport)尺寸的增加，系统会自动分为最多**12列**

- 栅格系统用于通过一系列的行（row）与列（column）的组合来创建页面布局，你的内容就可以放入这些创建好的布局中。工作**原理**如下：
  - “行（row）”必须包含在 `.container` （固定宽度）或 `.container-fluid` （100% 宽度）中，以便为其赋予合适的排列（**aligment**）和内补（**padding**）。
  - 通过“行（row）”在水平方向创建一组“列（column）”。
  - 你的内容应当放置于“列（column）”内，并且，只有“列（column）”可以作为“行（row）”的直接子元素。
  - 类似 `.row` 和 `.col-xs-4` 这种预定义的类，可以用来快速创建栅格布局。Bootstrap 源码中定义的 mixin 也可以用来创建语义化的布局。
  - 通过为“**列（column）”设置 `padding` 属性**，从而创建列与列之间的间隔（gutter）。通过**为 `.row` 元素设置负值 `margin`** 从而抵消掉为 `.container` 元素设置的 `padding`，也就间接为“行（row）”所包含的“列（column）”抵消掉了`padding`。==多列嵌套时，可以通过==`padding:0`==来取消内边距，使得元素占满viewport==
  - 负值的`margin`就是下面的示例为什么是向外突出的原因。在栅格列中的内容排成一行。
  - 栅格系统中的列是通过指定1到12的值来表示其**跨越的范围**。例如，三个等宽的列可以使用三个 `.col-xs-4` 来创建。
  - 如果一“行（row）”中包含了的“列（column）”大于 12，多余的“列（column）”所在的元素将被作为一个整体**另起一行排列**。
  - **向上兼容且不向下兼容**：**栅格类适用于与屏幕宽度大于或等于分界点大小的设备** ， 并且**针对小屏幕设备覆盖栅格类（可能每个列占一行）**。 因此，在元素上应用任何 `.col-md-*`栅格类适用于与屏幕宽度大于或等于分界点大小的设备 ， 并且针对小屏幕设备覆盖栅格类。 因此，在元素上应用任何 `.col-lg-*`不存在， 也影响大屏幕设备。

## 6.3 全局CSS样式、组件、插件

**全局CSS样式**

* 按钮： `<button>` (建议使用)、`<a>`、 `<input>` 。`class="btn btn-default"`
* 图片：`img-responsive"`、`img-rounded`(方)、`img-circle`(圆)、`img-thumbnail`(相框)
* 表格：`table`、`table-bordered`、`table-hover`
* 表单：

**组件**

* 导航条
* 分页：！！！

**插件**

* 轮播图

## 6.4 其他详细的看文档去吧！





# 第二部分 Web后端

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
>   //它的优先级最低，如果一个请求没有人处理，那么它来处理！它显示404
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
     - 路径定义规则：
         - `/xxx`：路径匹配
         - `/xxx/xxx`：多层路径，称之为目录结构
         - `*.do`：扩展名匹配，*前不能有/，否则报错
         - `/*`：通配符，优先级低。其他都匹配不到才匹配



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
  * 获取**请求URL**：`StringBuffer getRequestURL()`，如`http://localhost/day14/demo1`。**统一资源定位符**
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

* **相对路径**：通过相对路径不可以确定唯一资源，以**`./`开头路径**。如：`./index.html`
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




## 5.4 文件下载

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



## 6.3 用户登录加验证码

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





# 7 JSP及架构

## 7.1 JSP

### 7.1.1 JSP简介

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

### 7.1.2 JSP的脚本

* JSP= html + Java脚本 + JSP标签(指令)
* 3种Java脚本：
  * `<%...%>`：定义的java代码，在service方法中。**service方法**中可以定义什么，该脚本中就可以定义什么。
  * `<%=...%>`：定义的java代码，会输出到页面上。**输出语句**中可以定义什么，该脚本中就可以定义什么
  * `<%!...%>`：定义的java代码，在jsp转换后的java**类的成员位置**
* `<%-- ... --%>`：**注释**，也可以注释HTML。当服务器把jsp编译成java文件时已经忽略了注释部分！**不会发送至客户端**
  * 但是HTML中注释会发送并可以从查看源代码中找到，虽然页面中不显示
* JSP对应的Java文件：java脚本直接显示，html输出用write，变量输出用print

### 7.1.3 **JSP九大内置对象**

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




### 7.1.4 JSP三大指令

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



### 7.1.5 EL表达式

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

### 7.1.6 JSTL标签库

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



## 7.2 MVC框架

- MVC框架
  - M -- **Model** 模型(JavaBean)：具体业务操作
  - V -- **View**  视图(JSP)：展示数据
  - C -- **Controller** 控制器(Servlet)：获取客户端输入，调用模型，将数据交给视图
- 优点：
  1. 耦合性低，方便维护，可以利于分工协作 
  2. 重用性高
- 缺点：
  1. 使得项目架构变得复杂，对开发人员要求高

## 7.3 JavaWeb三层架构

- 表示层（Web层） --> 与Web相关的内容。**Servlet充当Controller**
- 业务层（Service层） --> 处理业务逻辑的
- 数据层（DAO层） --> 操作数据库存储文件，不能跳出到DAO之外(Data Access Object，数据访问对象)

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



### 7.3.2 登录注册

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



# 8 Filter、Listener

## 8.1 Filter

* web中的过滤器：当访问服务器的资源时，过滤器可以**拦截请求**，增强Request、Response
* 过滤器的作用：一般用于完成**通用的操作**。如：**登录验证**、统一**编码处理**、**敏感字符过滤**……

### 8.1.1 快速入门

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

### 8.1.2 Filter细节

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

### 8.1.3 案例

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

# 10 国际化

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




