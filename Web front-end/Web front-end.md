



# 1 HTML

HyperText Markup Language：超文本标记语言

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    
</body>
</html>
```

## 根元素（html）

-   `<html>`：表示一个HTML文档的**根（顶级元素）**，所以它也被称为根元素，所有其他元素必须是此元素的后代。
    -   `xmlns`：指派文档的 XML 命名空间。默认是`http://www.w3.org/1999/xhtml`。只在XHTML中必要。

## 文档元数据

元数据（Metadata）含有页面的相关信息，包括样式、脚本及数据，能帮助一些软件（例如 搜索引擎SEO、浏览器 等等）更好地运用和渲染页面。对于样式和脚本的元数据，可以直接在网页里定义，也可以链接到包含相关信息的外部文件。

-   `<base>` ：指定用于一个文档中包含的所有**相对 URL 的根 URL**。一份中只能有一个。一个文档的基本 URL，可以通过使用 `document.baseURI` 的 JS 脚本查询。[详细查看](https://developer.mozilla.org/zh-CN/docs/Web/HTML/Element/base)
-   `<head>`：**规定文档相关的配置信息（元数据）**，包括文档的标题，引用的文档样式和脚本等。
    -   `<meta>`：该元素表示那些不能由其它HTML元相关元素 (`<base>`, `<link>`, `<script>`, `<style>` 或 `<title>`) 之一表示的任何**元数据信息**。
        -   
    -   `<title>`： 该元素定义文档的**标题**，显示在浏览器的标题栏或标签页上。它只可以包含文本，若是包含有标签，则包含的任何标签都不会被解释。
    -   `<link>`：该元素规定了外部资源与当前文档的关系。这个元素最常于链接 CSS 样式表，还能被用来创建站点图标（比如PC端的“favicon”图标和移动设备上用以显示在主屏幕的图标）甚至一些其他事情。
    -   `<style>`：该元素包含文档的样式信息或者文档的部分内容。默认情况下，该标签的样式信息通常是 CSS 的格式。

## 分区根元素（body）

-   `<body>`：该元素表示文档的内容。`document.body`属性提供了可以轻松访问文档的 body 元素的脚本。

## 内容分区（逻辑分区）

内容分区元素允许你将文档内容从**逻辑上进行组织划分**。使用包括页眉(header)、页脚(footer)、导航(nav)和标题(h1~h6)等分区元素，来为页面内容创建明确的大纲，以便区分各个章节的内容。

-   



## 主要

- **文字、注释标签**

  `<h1>标题</h1>`    **标题**，取值1~6从大到小。是==**块级标签**==，自动在其前后加==**空行**==

  `<p style="color: blue;size: 14px;">html</p>`   **段落**，是==**块级标签**==，自动在其前后加==**空行**==

  `<div></div>`   ==**块级标签**==，自动==**换行**==

  `<span></span>`   **内联标签**，一行内显示

  `<pre></pre>`   **预格式**文本，保留原有格式

  `<hr/>`   **横线**

  `<br/>`   **换行**

  `&lt;`、`&gt;`、`&amp;`、**`&nbsp;`**、`&copy;`、`&reg;`  分别为：<、>、 **空格**、 &、 &copy;、 &reg;  **特殊字符**

  `<!--注释-->`   **注释**

- 文字修饰标签

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

- HTML样式(style)尽量使用CSS来操作

  - 字体、颜色、尺寸：`<p style="font-family: Consolas;color: white;font-size: 20px;">内容</p>`
  - 文本对齐、背景颜色：`<p style="text-align: center;background-color: gray">内容</p>`

- **列表标签**

  `<ol>` `<ul>` `<li>`   **有序、无序、列表**，**`reversed`**列表倒序

  - `type='1'或'a'或'i'或'A'或'I'`请使用CSS替代；`start`HTML5不支持，请使用CSS替代；

  `<dl>` `<dt>` `<dd>`   **列表**带有**项目**和**描述**

- **图像标签**

  `<img src = 'test.jpg' width='400' height='300'alt='无法显示'/>` 

  - 相对路径：以`.`开头，如`./`代表当前目录；`../`代表上级目录

- **超链接标签**

  `<a href='https://www.qq.com' target='_blank'>新窗口打开QQ</a>`：`target`默认是`_self`；

  - 在**定位资源**时：（填`#`默认跳转当前页）

    ```html
    <a id='top'>top</a>
    <a href='#top'>returnTop</a>
    ```

  - `href`中添加`javascript:void(0);`==可以**禁止跳转**==

  - 在**发送邮件**时：`<a href="mailto:xxx@gmail.com">联系我们</a>`

  - 可以**包裹img标签**

- **表格标签**

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

- **表单标签**：提交用户数据到服务器 

  - `<form></form>`: 定义一个表单的范围 

    - **action**：提交到地址，默认提交到当前的页面（可以是一个页面，也可以是后台代码）

    - **method**：常用的有两种  get和post，默认是get请求 

      - ==**get和post区别**==

        1. get请求地址栏会携带提交的数据（封装到请求行中），post不会携带（封装在请求体里面，http协议）
        2. get请求数据有大小的限制，post没有限制
        3. get请求安全级别较低，post较高

      - form表单不支持其他请求方式，使用时需要在input中添加一个**`_method`属性**，Ajax方式示例：

        注意：Java后端需**配置`HiddenHttpMethodFilter`**

        ```javascript
        $.ajax({
            url:"/user",
            type: "POST",
            data: {
                "_method":"DELETE",
                "body":$("#form1").serialize()
            },
            success : function (href) {
                location.href = href;
            }
        })
        ```

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

      ```html
      <a onclick="document:loginForm.submit()">登陆</a> <!--这样也可以提交，但是会跳转。了解-->
      ```

      可以使用==**普通button**==，配合JS来使用Ajax提交表单。**type需指定为button**，否则浏览器会将值设置为submit

      ```html
      <input type="submit"/> <!--默认为提交（中文环境）、submit（英文环境）-->
      <input type="submit" value="注册"/>
      ```

      可以使用form的`onsubmit`事件，如`onsubmit = "return checkForm()"`，必须加return，并且方法返回boolean类型，true提交，false不提交

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
>   <frame name="top" src="a.html">             //上面页面
>   <frameset cols="150,*">                     //把下面部分划分成左右两部分
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



## 2.2 CSS的基本选择器（三种）

1. **id选择器**             `#idName{...}`
2. **class选择器**        `.className{...}`
3. **标签选择器**         `div{...}`
   - **优先级**   **style > id选择器 > class选择器 > 标签选择器**
     - 有一个特别的语法可以让一条规则**总是**优先于其他规则：`border: none !important;`

- **格式**

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

- **`width`(宽度)**：`auto`(默认，浏览器计算)、*`[length]`*、*`%`*、`inherit`
- **`height`(高度)**：`auto`(默认，浏览器计算)、*`[length]`*、*`%`*、`inherit`
- 

### 2.4.4 背景属性

- **`background`(背景)**：下列color、image、repeat、position等都可以，复合属性。注意**内部、外部CSS的路径问题**

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
  1. ==外边距（**margin**:20px | auto）==：第二个参数auto和居中类似。
  2. ==边框（**border:**2px solid blue;）==
     - 统一设置
     - 单独设置（border-top、border-bottom、border-left、border-right）
  3. ==内边距（**padding**: 20px | auto;）==：第二个参数auto和居中类似。
     - 统一设置
     - 单独设置（padding-top、padding-bottom、padding-left、padding-right）
     - ==默认情况**内边距改变**会**改变盒子大小**==，解决这个问题可以添加以下属性
       - `box-sizing:border-box`：指定宽度和高度（最小/最大属性）确定最终元素边框box大小

### 2.4.10 定位属性

#### 1、float

- ==当一个元素浮动之后，它会被**移出正常的文档流**，然后向左或者向右**平移**，一直平移直到碰到了所处的**容器的边框**，或者碰到**另外一个浮动的元素**。==

  - 当框 1 向左浮动时，它脱离文档流并且向左移动，直到它的左边缘碰到包含框的左边缘，覆盖框2

  - 如果把三个框都向左浮动，那框1向左浮动直到碰到包含框，另外两个框向左浮动直到碰到前一个浮动框

    ![](images\css_positioning_floating_left_example.png)

  - 如果包含框太窄，无法容纳水平排列的三个浮动元素，那么其它浮动块向下移动，直到有足够的空间；如果浮动元素的高度不同，那么当它们向下移动时可能被其它浮动元素“卡住”

    ![](images\css_positioning_floating_left_example_2.png)

- `float: none | left | right;` ：对象向左边|右边浮动

  - `clear: none | left | right | both;`：规定元素的哪一侧不允许其他浮动元素

#### 2、position

- **position**的属性值：
  - **absolute** ：生成**绝对定位的元素**，相对于 static 定位以外的第一个**父元素**进行定位。 
    - 可以使用top、bottom等属性进行定位
  - **relative** ：生成**相对定位**的元素，相对于**其正常位置**进行定位 
    - 可以使用top、bottom等属性进行定位



## 2.5 CSS 框架

### 1 bootstrap

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

- 步骤

  1. 定义布局容器：`container`、`.container-fluid`
  2. 定义行：`row`
  3. 定义列：`col-xs-*`、`col-sm-*`、`col-md-*`、`col-lg-*`、`hidden-**`（可以让元素在某个屏幕大小设备**不显示**）

#### 1.1 布局容器

- Bootstrap 需要为页面内容和栅格系统包裹一个容器
  - `.container` **类**用于固定宽度（根据不同设备左右有固定留白，但xs没有留白）并支持响应式布局的容器
  - `.container-fluid` **类**用于 100% 宽度，占据全部视口（viewport）的容器

#### 1.2 栅格系统

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

#### 1.3 全局CSS样式、组件、插件

**全局CSS样式**

- 按钮： `<button>` (建议使用)、`<a>`、 `<input>` 。`class="btn btn-default"`
- 图片：`img-responsive"`、`img-rounded`(方)、`img-circle`(圆)、`img-thumbnail`(相框)
- 表格：`table`、`table-bordered`、`table-hover`
- 表单：

**组件**

- 导航条
- 分页：！！！

**插件**

- 轮播图

#### 1.4 其他详细的看文档去吧！



### 2 AdminLTE

- AdminLTE简介

  AdminLTE是一款建立在bootstrap和jquery之上的开源的模板主题工具，它提供了一系列响应的、 可重复使用的组件，并内置了多个模板页面；同时自适应多种屏幕分辨率，兼容PC和移动端。通 过AdminLTE，我们可以快速的创建一个响应式的Html5网站。AdminLTE框架在网页架构与设计 上，有很大的辅助作用，尤其是前端架构设计师，用好AdminLTE 不但美观，而且可以免去写很大 CSS与JS的工作量。从GitHub获取[AdminLTE源码](https://github.com/almasaeed2010/AdminLTE)

  AdminLTE依赖于两个框架Bootstrap3与JQuery1.11+

- AdminLTE结构介绍

  ```
  AdminLTE/ 
  ├── dist/ 
  │   ├── CSS/ 
  │   ├── JS 
  │   ├── img 
  ├── build/ 
  │   ├── less/ 
  │   │   ├── AdminLTE's Less files 
  │   └── Bootstrap-less/ (Only for reference. No modifications have been made) 
  │       ├── mixins/
  │       ├── variables.less 
  │       ├── mixins.less 
  └── plugins/    
      ├── All the customized plugins CSS and JS files
  ```

- AdminLTE布局与皮肤

  - 布局

    `.wrapper`包住了body下的所有代码

    `.main-header`里是网站的logo和导航栏的代码

    `.main-sidebar`里是用户面板和侧边栏菜单的代码

    `.content-wrapper`里是页面的页面和内容区域的代码 

    `.main-footer`里是页脚的代码

    `.control-sidebar`里是页面右侧侧边栏区域的代码

  - 布局选项

    `fixed`：固定

    `layout-boxed`：盒子布局

    `layout-top-nav`：顶部隐藏

    `sidebar-collapse`：侧边栏隐藏

    `sidebar-mini`：侧边栏隐藏时有小图标

  - 皮肤

    `skin-blue`：蓝色

    `skin-black`：黑色

    `skin-purple`：紫色

    `skin-yellow`：黄色 

    `skin-red`：红色

    `skin-green`：绿色



- **AdminLTE2-IT黑马-定制版**

  传智播客研究院针对英文版本AdminLTE进行了汉化，并优化与定制了部分页面，方便我们的学习 与使用。后续SSM综合练习课程中使用的界面就是基于AdminLTE2-IT黑马-定制版。从GitHub[获取源码](https://github.com/itheima2017/adminlte2-itheima)，也可以[在线进行浏览](http://research.itcast.cn/adminlte2-itcast/release/dist/pages/all-admin-index.html)。

  minLTE2-IT黑马-定制版是基于FIS3进行开发，在目录结构中assets、modules、pages、 plugins都是前端开发时所使用到的，最终发布的就是release。所以对于我们使用AdminLTE2-IT黑 马-定制版来说，我们只需要**关注release目录**下的结构就可以。

  在release目录下有**css、img、pages、plugins**目录。前两者就不在解决pages是产生的一些定制的页面，而plugins中是相关的插件，例如jquery、bootstrap等相关的css与js文件。





# 3 JavaScript

* 文法（词法，语法）：直接量、关键字、运算符
* 语义（一一对应语法）：语法和语义则是表达式、语句、函数、对象、模块
* 运行时：
  * 数据结构（类型如对象；实例如应用和机制）
  * 算法（执行过程）：事件循环，微任务的执行，函数的执行，语句级的执行



- JavaScript是一门==**客户端脚本语言**==
  - 运行在**客户端浏览器**中的。每一个浏览器都有JavaScript的解析引擎
  - **脚本语言**：**不需要编译**，直接就可以被浏览器解析执行了

- JavaScript是==基于**对象**和**事件**驱动的语言==？？？
- **和Java区别**
  - JavaScript 是**基于对象**的？？？，java是面向对象
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

- 创建：

  - `var fun = new Function(形式参数列表,方法体)`;  括号里面的都必须是string类型。忘掉吧！

  - **函数声明**

    ```javascript
    function 方法名称(形式参数列表){ 
        方法体
    }
    ```

  - **函数表达式**

    ```javascript
    var 方法名 = function(形式参数列表){ //匿名函数
        方法体
    }
    ```

  > **函数声明**在JS解析时进行函数提升，因此在同一个作用域内，不管函数声明在哪里定义，该函数都可以进行调用。
  >
  > **函数表达式**的值是在JS运行时确定，并且在表达式赋值完成后，该函数才能调用。

- 方法：

- **属性**：`length`代表**形参的个数**

- 特点：

  1. 方法定义时，形参的类型`var`不用写，返回值类型`var`也不写。
  2. ==方法是一个**对象**，如果定义名称相同的方法，会**覆盖**（可以从对象引用理解），所以**没有方法重载**==
  3. ==在JavaScript中，方法的**调用**只与方法的**名称有关**，和参数列表无关（传递多少个参数无所谓）==
  4. 在方法声明中有一个隐藏的**内置对象**（数组）**`arguments`**，**封装所有的实际参数**。同Java中可变参数类似

- 调用：`方法名称(实际参数列表);`

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
  - `pop()`：删除最后一个元素，**返回删除的那个元素**
  - `splice(index,n)`：**从index位置开始删除n个元素**
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
  - `toLocaleString()`：返回当前`date`对象**对应的时间本地字符串格式**。不调用默认为英文格式
  - `getTime()`：**获取时间戳的毫秒值**。返回的是1970.1.1零点至今的毫秒数
    - 使用毫秒数处理缓存的效果（没有缓存）`http://www.baidu.com?毫秒数`
  - 获取当前的**年**：`getFullYear()`
  - 获取当前的**月**(0~11)：`getMonth()+1`
  - 获取一个月中的某一**日**(1~31)：`getDate()`
  - 获取一**周**中的某一天(0~6)：`.getDay()`，周日为0
  - 获取当前的小**时**(0~23)：`getHours()`
  - 获取当前的**分**钟(0~59)：`getMinutes()`
  - 获取当前的**秒**(0~59)：`getSecondes()`

### 3.2.4 Math

- 里面的都是**静态**方法，使用可以直接使用`Math.方法()`
- 属性：
  - `PI`：π

- 方法：
  - `random()`：得到 **[0.0,1.0)** 之间的随机数（伪随机数）
  - `ceil(x)`：对数进行向上舍入，大于等于 x，并且与它最接近的**整数**
  - `floor(x)`：对数进行向下舍入，小于等于 x，且与 x 最接近的**整数**
  - `round(x)`：把数四舍五入为最接近的**整数**

### 3.2.5 基本类型的包装对象

- Number

- String

  - 创建对象`var str = "abc";`
  - 属性：`length`，字符串长度
  - **方法：**

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

  - `^`：开始
  - `$`：结束

- **正则对象**：

  1. 创建
  2. `var reg = new RegExp("正则表达式");`，需要注意字符串中的转义字符
  3. `var reg = /正则表达式/;`使用最多
  4. 方法	`test(参数)`：验证指定的字符串是否符合正则定义的规范，返回boolean类型值

### 3.2.7 Global

- 特点：**全局对象**，这个Global中封装的方法不需要对象就可以`方法名();`**直接调用**。  

- 方法：

  - `encodeURI()`：对字符进行url编码，返回另一个字符。不编码字符有82个

  - `decodeURI()`：对字符进行url解码，返回另一个字符

  - `encodeURIComponent()`：对字符进行url编码，编码的字符更多。不编码字符有71个

  - `decodeURIComponent()`：对字符进行url解码

  - `parseInt()`：将字符串转为数字；进制转换
    ​    * 逐一判断每一个字符是否是数字，直到不是数字为止，**将前边数字部分**转为number，第一个也不是数字则为NaN
    ​    * `parseInt('11',2)`，返回3

    - `isNaN()`：判断一个值是否是NaN
      - NaN六亲不认，连自己都不认。NaN参与的`==`比较全部返回`false`

  - `eval()`：==将JavaScript字符串，作为脚本代码来执行==，若字符串不是脚本代码则不执行

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

### 3.2.9 JSON

随着ES5的发布，新的标准内置了JSON对象，用于JSON对象和JSON字符串之间的转换操作。JSON作为全局对象，我们可以直接调用，它有如下两个函数：

* `JSON.parse(string)`：负责将字符串转换为JOSN对象并返回

* `JSON.stringify(value[, replacer[, space]])`：负责将数据转换为JSON字符串并返回

  value就是我们需要转换的对象。可以是null, boolean, number, string, JSONObject, JSONArray

  replacer可以是一个数组或者一个函数，如果传入一个数组，则相当于一个白名单，只有出现在数组中的属性名对应的键值对才会出现结果中，如果指定一个函数，我们可以实现一些逻辑来过滤或更改将要出现在结果中的数据

  最后一个参数是缩进空格，我们可以指定一个数字，表示缩进几个空格，还可以指定一个字符串，表示以指定字符串代替空格出现在缩进中



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

  - **核心 DOM** - 针对任何结构化文档的标准模型
    - **Node**：原生节点对象，以下节点对象都继承于此
    - **Document**：文档对象，整个文档树的顶层节点
    - DocumentType：`doctype`标签（比如`<!DOCTYPE html>`
    - **Element**：元素对象，网页的各种HTML标签（比如`<body>`、`<a>`等）
    - **Attribute**：属性对象，网页元素的属性（比如`class="right"`）
    - **Text**：文本对象，标签之间或标签包含的文本
    - Comment：注释对象
    - DocumentFragment：文档的片段
  - XML DOM - 针对 XML 文档的标准模型
  - **HTML DOM** - 针对 HTML 文档的标准模型

- 解析过程：根据html的层级结构，在内存中分配一个树形结构，需要把html中的每部分封装成对象

  ![](images\ct_htmltree.png)

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

- `NodeList`实例是一个类似数组不是数组的对象，它的成员是节点对象。通过以下方法可以得到`NodeList`实例
  - `Node.childNodes`：说了别用！省的没注意空格！
  - `document.querySelectorAll()`等节点搜索方法
- 属性：
  - `length`，NodeList 实例包含的节点数量
- 方法：
  - `forEach`，也可以使用for循环。没pop、pust等方法哦！
  - `item(index)`：接受一个整数值作为参数，表示成员的位置，返回该位置上的成员。
- 。。。懒得看了

#### 2 HTMLCollection 接口（了解）

- `HTMLCollection`是一个节点对象的集合，只能包含元素节点（element），不能包含其他类型的节点。它的返回值是一个类似数组的对象，但是与`NodeList`接口不同，`HTMLCollection`没有`forEach`方法，只能使用`for`循环遍历
- 返回`HTMLCollection`实例的，主要是一些`Document`对象的集合属性，比如`document.links`、`docuement.forms`、`document.images`、`document.styleSheets`、`document.scripts`等
- `HTMLCollection`实例都是动态集合，节点的变化会实时反映在集合中
- 属性
  - `length`：返回`HTMLCollection`实例包含的成员数量
- 方法
  - `item()`：接受一个整数值作为参数，表示成员的位置，返回该位置上的成员
  - `namedItem()`：参数是一个字符串，表示`id`属性或`name`属性的值，返回对应的元素节点。如果没有则返回`null`

#### 3 ParentNode 接口

- 只有元素节点（element）、文档节点（document）和文档片段节点（documentFragment）拥有子节点，因此只有这三类节点会继承`ParentNode`接口
- 属性
  - `children`：返回一个`HTMLCollection`实例，成员是当前节点的所有**元素子节点**。该属性只读。
  - `firstElementChild`：当前节点的第一个元素子节点。如果没有任何元素子节点，则返回`null`
  - `lastElementChild`：当前节点的最后一个元素子节点，如果不存在任何元素子节点，则返回`null`
  - `childElementCount`：返回一个整数表示当前节点的所有元素子节点的数目。如果不包含任何元素子节点则返回`0`
- ==方法（如下方法都没有返回值）==
  - **`append()`**：为当前节点的最后一个元素子节点后追加**一个或多个子节点**。可以添加元素子节点、文本子节点
  - **`prepend()`**：为当前节点的的第一个元素子节点前追加**一个或多个子节点**。同append()方法
    - 若是若`newNode`为DOM中已存在的，相当于**剪贴**

#### 4 ChildNode 接口

- 如果一个节点有父节点，那么该节点就继承了`ChildNode`接口
- ==方法（都是本节点调用方法）==
  - `remove()`：==用于从父节点**移除当前节点**，**自己调用删除自己**！因为已知本节点有父节点==
  - `before()`：当前节点的**前**面，插入**一个或多个**同级节点，两者拥有相同的父节点。可以插入元素节点、文本节点
  - `after()`：在当前节点的**后**面，插入**一个或多个**同级节点，两者拥有相同的父节点。同before()方法
  - `replaceWith()`：使用参数节点，**替换**当前节点。参数可以是元素节点，也可以是文本节点

### 3.4.2 Document

- 获取：在html dom模型中可以使用`window`对象来获取，也可以省略。**继承了Node、ParentNode等接口**

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
  - `innerText`：同上，但是只显示文本代码，不带标签的！！，设置内容也不会解析为HTML
  - `textContent`：显示文本或插入的是文本时使用来替代上面方法。原样显示，不像上面方法会转为`&**;`来显示
  - `value`：代表的是元素的value属性，一般用于**`input`标签值的获取**，**`select`**标签值也可以使用
  - `style`：用来读写该元素的行内样式信息，配合CSS。如display可取值none、block、inner
    - 也可以**提前定义好**类选择器的样式，通过元素的`className`属性来设置其`class`属性值。
- **方法：**
  - **`getAttribute("name")`**：获取属性里面的值
  - **`setAttribute("name","value")`**：设置属性的值
  - **`removeAttribute("name")`**：删除属性，**不能删除value属性**
- **获取标签下面的子标签**的唯一有效办法，使用父节点**`getElementsByTagName()`**方法，不使用childNodes属性



### 3.4.5 Event

Event Handlers：

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

    必须写`return 函数名`否则不能获取到返回的boolean值

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

其他的查看W3C中Event对象吧



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

- 语法：**`$(CSS选择器)`**或**`jQuery(CSS选择器)`**，jQuery对象内部以**==数组==存储匹配的数据**，若只有一个，索引号为0

- 对象**转换**（jQuery对象和JS对象无法互相操作属性和方法）

  - JS-->jQuery：**`$(JS对象)`**
  - jQuery-->JS：**`$(选择器)[0]`或`$(选择器).get(0)`**

- 绑定事件（去掉JS事件中on即可，并给事件方法传入function对象）

  ```javascript
  $("#id1").click(function(){
     //。。。 
  });
  ```

- **入口函数**（DOM树加载完毕执行）：区别在于onload只能定义一次，否则覆盖；而ready可以定义多次

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
  - **`val()`**：获取/设置元素的**value属性值**，常用于**`input`**标签，**`select`**标签值也可以使用
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

  - key为索引；value/this是每一个元素对象（JS对象）；
  - 回调函数返回值为false相当于break；返回值为true相当于continue

- **`$.each(object,[callback])`**：遍历任意对象（JS数组等也可以）。

  ```javascript
  $.each(arr,function (key,value) {});
  ```

- **`for...of`**：jQuery3.0后提供的方式，同ES6中使用方法一致

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
       1. easing：用来指定切换效果，默认是"swing"，可用参数"linear"
          ​    * swing：动画执行时效果是 先慢，中间快，最后又慢
          - linear：动画执行时速度是匀速的
    2. fn：在动画完成时执行的函数，每个元素执行一次。
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

- 增强JQuery的功能

- 实现方式	

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

- **同步和异步**：客户端和服务器端相互通信的基础上，**客户端发送请求到服务器端**

  - **同步**：客户端必须**等待服务器端的响应**，在等待的期间客户端不能做其他操作
  - **异步**：客户端**不需要等待服务器端的响应**，在服务器处理请求的过程中，客户端可以进行其他的操作

- Ajax 是一种在无需重新加载整个网页的情况下，能够更新部分网页的技术。通过在后台与服务器进行少量数据交换，Ajax 可以使网页实现异步更新。这意味着可以在不重新加载整个网页的情况下，对网页的某部分进行更新，提升用户的体验。传统的网页（不使用 Ajax）如果需要更新内容，必须重载整个网页页面。

- Ajax**运行原理**

  页面发起请求，会**将请求发送给==浏览器内核中的Ajax引擎==**，Ajax引擎会提交请求到服务器端，在这段时间里，客户端可以任意进行任意操作，**直到服务器端将数据返回给Ajax引擎后**，会**触发**你设置的**事件**，从而**执行自定义的js逻辑代码**完成某种页面功能

- Ajax应用场景

  - 谷歌/百度的搜索框自动补全
  - 用户注册时（校验用户名是否被注册过）
  - 下拉框联动

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
      //contentType: 设置发送信息的MIME类型，可以不设置，默认application/x-www-form-urlencoded
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

  **`serialize()`：jQuery中序列表单内容为字符串**。可用于Ajax提交表单

  Ajax的其他delete、put等查看HTML章节介绍

## 5.4 JSON

- JSON（JavaScript Object Notation）：JavaScript对象表示法

- JSON是一种**与语言无关**的**数据交换的格式**，作用：

  - JSON现在多用于**存储**和**交换文本信息**的语法
  - 进行数据的**传输**
  - JSON 比 XML 更小、更**快**，更易解析

- 语法

  1. 基本规则

     - 数据在**名称/值**对中，**键用双引号（严格模式，但在js中可以不用）**引起来
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

- 常见的JSON解析器：Jsonlib，Gson（谷歌），fastjson（阿里），==**jackson**==（Spring内置）
- 使用步骤
  1. **导**入jackson的相关jar**包**
  2. 创建Jackson**核心对象 `ObjectMapper`**
  3. 如下：

### 5.5.1 Java对象转换JSON

- 调用ObjectMapper的相关方法进行转换
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

- 调用ObjectMapper的相关方法进行转换：`readValue(json字符串数据,Class)`

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

- 服务器响应的数据，在客户端使用时，要想当做**json数据格式使用**。有两种解决方案：
  1. `$.get()`或`$.post()`或`$.ajax()`：将参数**dataType**指定为"json"
  2. 在**服务器端设置MIME类型**`response.setContentType("application/json;charset=utf-8");`

### 2 站内搜索









# 6 Vue

> 读音 /vjuː/，类似于 view

* [Vue](https://cn.vuejs.org/)是一套用于构建用户界面的**渐进式框架**（根据需要选则特性）。与其它大型框架不同的是，Vue 被设计为可以自底向上逐层应用。Vue 的核心库只关注**视图层**，不仅易于上手，还便于与第三方库或既有项目整合。另一方面，当与现代化的工具链以及各种支持类库结合使用时，Vue 也完全能够为复杂的单页应用提供驱动。Vue 不支持 IE8 及以下版本

* Vue的使用

  * 在html页面使用**script**引入vue.js的库即可使用
  * 使用**npm管理依赖**。使用webpack打包工具对vue.js应用打包。大型应用推荐此方案。
  * **Vue-CLI脚手架**。使用vue.js官方提供的CLI脚本架很方便去创建vue.js工程雏形。

* Vue借鉴了**MVVM**模式，MVVM拆分解释为：

  * Model：负责数据存储

  * View：负责页面展示

  * View Model：**负责业务逻辑处理**（比如Ajax请求等），对数据进行加工后交给视图展示

    MVVM要解决的问题是**将业务逻辑代码**与**视图代码**进行完全**分离**，使各自的职责更加清晰，后期代码维护更加简单

    ![1550506617716](images/1550506617716.png)

  

  

## 1 Vue 语法

```html
<!DOCTYPE html>
<html lang="zh">
    <head>
        <meta charset="utf-8" />
        <title>快速入门</title>
        <script src="js/vuejs-2.5.16.js"></script>
    </head>
    <body>
        <!--相当于MVVM中View视图-->
        <div id="app">
            {{message}}<!--vue的插值表达式，数据的双向绑定-->
            {{flag?"正确":"错误"}}
            {{Number.parseInt(number)++}}
        </div>
    </body>
    <script>
        //相当于MVVM中ViewModel
        const vue = new Vue({
            el:"#app",//由Vue的VM接管id为app的区域
            data:{ //Model数据
                message:"Hello Vue 777",
                flag:false,
                number:100,
            }
        });
    </script>
</html>
```

### 1.1 插值表达式`{{}}`

数据绑定最常见的形式就是使用“Mustache”语法 (双大括号) 的文本插值，Mustache 标签将会被替代为对应数据对象上属性的值。无论何时，绑定的数据对象上属性发生了改变，插值处的内容都会更新。Vue.js 提供了完全的 JavaScript 表达式支持。

```html
{{ number + 1 }}
{{ ok ? 'YES' : 'NO' }}
```

这些表达式会**在所属 Vue 实例的数据作用域下作为 JavaScript 被解析**。有个限制就是，**每个绑定都只能包含单个表达式**，所以下面的例子都不会生效。不能写在dom的属性中！！！

```html
<!-- 这是语句，不是表达式 -->
{{ var a = 1 }}
<!-- 流控制也不会生效，请使用三元表达式 -->
{{ if (ok) { return message } }}
```



### 1.2 `v-text`与`v-html`

解决网速慢时**插值表达式闪烁问题（显示了插值表达式，体验很不好）**，使用`v-text`

```html
<body>
    <div id="app">
        <div v-html="message"></div> <!--解析HTML展示-->
        <div v-text="message"></div> <!--原样文本展示-->
    </div>
</body>
<script>
    //view model
    new Vue({
        el:"#app",
        data:{
            message:'<h1>hello</h1>'
        }
    });

    //传统JS，HTML略
    onload = function () {
        document.getElementById("div1").innerHTML = '<h1>hello</h1>';//解析HTML展示
        document.getElementById("div2").innerText = '<h1>hello</h1>';//原样文本展示
    }
</script>
```



### 1.3 `v-bind:` & `:`

`v‐bind`可以将数据对象绑定在dom的**任意属性中**。但是没有双向绑定特性！

`v‐bind`可以给dom对象绑定**一个或多个特性**，例如动态绑定style和class

```html
<body>
    <div id="app">
        <a v-bind:href="a1">qq</a>
        <div v‐bind:style="{ fontSize: size + 'px' }"></div>
    </div>
</body>
<script>
    //view model
    new Vue({
        el:"#app",
        data:{
            a1: 'https://www.qq.com',
            size: 16
        }
    });
</script>
```



### 1.4 `v-model`

**双向绑定**。仅能在以下元素中使用：`input` `select` `textarea` `componets`(Vue中组件)

```html
<body>
    <div id="app">
        用户名：<input type="text" name="username" v-model="user.username">
        密  码：<input type="text" name="password" v-model="user.password"><!--为了展示方便，不用password-->
        <input type="button" @click="fun" value="获取">
    </div>
</body>
<script>
    //view model
    new Vue({
        el:"#app",
        data:{
            user:{username:"",password:""}
        },
        methods:{
            fun:function () {
                alert(this.user.username+"====="+this.user.password);
                this.user.username = 'zhangsan';
                this.user.password = '123456';
            }
        }
    });
</script>
```



### 1.5 `v-on:`&`@`

可以用 `v-on` 指令监听 DOM **事件**，并在触发时运行一些 JavaScript 代码。`v-on:事件名称`简化写法`@事件名称`

* `v-on:click`：点击事件

  ```html
  <body>
      <div id="app">
          {{message}}  
          <button v-on:click="fun('改变的内容')">vue的onclick</button>
      </div>
  </body>
  <script>
      //view model
      new Vue({
          el:"#app",
          data:{
              message:"hello vue"
          },
          methods: {
              fun:function(msg){
                  alert("hello");
                  this.message = msg;
              },
          }
      })
  </script>
  ```

* `v-on:keydown`：键盘按下事件和阻止事件默认行为

  ```html
  <body>
      <div id="app">
          Vue:<input type="text" v-on:keydown="fun1($event)"><hr/> <!--不传递evnet也可以实现-->
          JS:<input type="text" onkeydown="fun0();">
      </div>
  </body>
  <script>
      // view model
      new Vue({
          el:"#app",
          methods:{
              fun1:function (event) { //不传递evnet也可以实现
                  let keyCode = event.keyCode;//过时了。。。
                  if (keyCode<48 || keyCode>57){
                      event.preventDefault();
                  }
              }
          }
      });
      //原生JS
      function fun0() {
          //window、document、event对象都可以不定义直接使用
          let keyCode = event.keyCode;//过时了。。。
          if (keyCode<48 || keyCode>57){
              event.preventDefault();//通知浏览器不要执行与事件关联的默认动作，此案例为键盘按下事件则不会显示按的符号
              //但是没啥用，写中文时直接回车就把字母显示在对input框中了
          }
      }
  </script>
  ```

* `v-on:mouseover`鼠标移到到目标上面

  ```html
  <body>
      Vue:<div id="app">
      <div v-on:mouseover="vfun1();" id="div">
          <textarea @mouseover="vfun2($event);">这是一个文件域</textarea> <!--不传递evnet也可以实现-->
      </div>
      </div>
      JS:<div>
      <div onmouseover="fun1()" id="div">
          <textarea onmouseover="fun2()">这是一个文件域</textarea>
      </div>
      </div>
  
  </body>
  <script>
      //view model
      new Vue({
          el:"#app",
          methods:{
              vfun1:function () {
                  alert("div");
              },
              vfun2:function (event) {//不传递evnet也可以实现
                  alert("textarea");
                  event.stopPropagation();
              }
          }
      });
  
      //由于div包裹textarea，在鼠标移动到textarea上会先后弹出textarea、div，解决方法如下
      function fun1() {
          alert("div")
      }
      function fun2() {
          alert("textarea");
          event.stopPropagation();//利用event对象不再派发事件的方法，停止事件传播
      }
  </script>
  ```

* **事件修饰符**

  Vue.js 为 v-on 提供了事件修饰符来处理 DOM 事件细节，如：event.preventDefault() 或 event.stopPropagation()。

  Vue.js通过由点(.)表示的指令后缀来调用修饰符：

  * `.stop`：停止事件传播，不再派发事件
  * `.prevent`：停止事件的默认动作
  * `.capture`
  * `.self`
  * `.once`

  ```html
  <!--案例1：不让表单提交-->
  <body>
      Vue:<div id="app">
      <form action="http://www.qq.com" method="post" @submit.prevent> <!--.stop类似-->
          <input type="submit" value="提交">
      </form>
      </div>
  
      JS: <div>
      <form action="http://www.qq.com" method="post" onsubmit="return checkForm();">
          <input type="submit" value="提交">
      </form>
      </div>
  </body>
  <script>
      //view model
      new Vue({
          el:"#app",
      });
  
      //原生JS
      function checkForm() {
          return false;
      };
  </script>
  ```

* **按键修饰符**

  Vue 允许为 `v-on` 在监听**键盘事件时**添加**按键修饰符**：

  全部的按键别名：

  * `.enter`
  * `.tab`
  * `.delete` (捕获 "删除" 和 "退格" 键)
  * `.esc`
  * `.space`
  * `.up`
  * `.down`
  * `.left`
  * `.right`
  * `.ctrl`
  * `.alt`
  * `.shift`
  * `.meta`

  ```html
  <body>
      <div id="app">
          <input type="text" v-on:keyup.enter="fun1">
  
          <p><input @keyup.alt.67="clear"><!-- Alt + C -->
  
          <div @click.ctrl="doSomething">Do something</div><!-- Ctrl + Click -->
      </div>
  
      <script>
          new Vue({
              el:'#app', //表示当前vue对象接管了div区域
              methods:{
                  fun1:function(){
                      alert("你按了回车");
                  }
              }
          });
      </script>
  </body>
  ```





### 1.6 `v-for`

`index`可以获取到索引（从0开始），但是只能写在后面（最后？）。可以根据index控制奇偶显示

```html
<!--对象的遍历。其中第一个参数为值，第二个为键！！！参数名称可以任意器，如(a,b)-->
<tr v-for="(value,key) in product">
```

```html
<body>
    <div id="app">
        <table border="1">
            <tr>
                <td>序号号</td>
                <td>编号</td>
                <td>名称</td>
                <td>价格</td>
            </tr>
            <tr v-for="(product,index) in products">
                <!--也可以指定主键，不知道干啥用的？？？   v-for="(p,i) in products":key="i"  -->
                <td>{{index}}</td>
                <td>{{product.id}}</td>
                <td>{{product.name}}</td>
                <td>{{product.price}}</td>
            </tr>
        </table>
    </div>
</body>
<script>
    //view model
    new Vue({
        el:"#app",
        data:{
            products:[
                {id:1,name:'笔记本电脑',price:8888},
                {id:2,name:'手机',price:6666},
                {id:1,name:'电视',price:88888}
            ]
        }
    });
</script>
```

### 1.7 `v-if`,`v-else`&`v-show`

`v-if`是根据表达式的值来决定是否渲染元素，效果一样，但是这个是整个标签都不渲染，网页源码中没有了这段代码

`v-show`是根据表达式的值来切换元素的display css属性，效果一样，但是这个只是display属性值为none

```html
<!--名称为heima的加背景色-->
<div v‐if="item.user.uname=='heima'" style="background: chartreuse">
    {{index}}‐{{item.user.uname}}‐{{item.user.age}}
</div>
<div v‐else="">
    {{index}}‐{{item.user.uname}}‐{{item.user.age}}
</div>
<!--js略-->
```

```html
<body>
    <div id="app">
        <span v-if="flag">传智播客</span>
        <span v-show="flag">itcast</span>
        <button @click="toggle">切换</button>
    </div>
</body>
<script>
    //view model
    new Vue({
        el: '#app', 
        data: {
            flag: false
        },
        methods: {
            toggle: function () {
                this.flag = !this.flag;
            }
        }
    });
</script>
```



## 2 Vue 生命周期

每个 Vue 应用都是通过用 `Vue` 函数创建一个新的 **Vue 实例**开始的：

```js
var vm = new Vue({
  // 选项
})
```

虽然没有完全遵循 [MVVM 模型](https://zh.wikipedia.org/wiki/MVVM)，但是 Vue 的设计也受到了它的启发。因此在文档中经常会使用 `vm` (ViewModel 的缩写) 这个变量名表示 Vue 实例。

当一个 Vue 实例被创建时，它向 Vue 的**响应式系统**中加入了其 `data` 对象中能找到的所有的属性。当这些属性的值发生改变时，视图将会产生“响应”，即匹配更新为新的值。当这些数据改变时，视图会进行重渲染。值得注意的是只有当实例被创建时 `data` 中存在的属性才是**响应式**的。也就是说如果你添加一个新的属性，比如：

```js
vm.b = 'hi'
```

那么对 `b` 的改动将不会触发任何视图的更新。如果你知道你会在晚些时候需要一个属性，但是一开始它为空或不存在，那么你仅需要设置一些初始值。



生命周期图示

![](images/lifecycle.png)

Vue在生命周期中有这些状态，`beforeCreate`,`created`,`beforeMount`,`mounted`,`beforeUpdate`,`updated`,`beforeDestroy`,`destroyed`。Vue
在实例化的过程中，会调用这些生命周期的钩子，给我们提供了执行自定义逻辑的机会。那么，在这些vue钩子
中，vue实例到底执行了那些操作，我们先看下面执行的例子

```html
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>生命周期</title>
        <script src="js/vuejs-2.5.16.js"></script>
    </head>
    <body>
        <div id="app">
            {{message}}
        </div>
        <script>
            var vm = new Vue({
                el: "#app",
                data: {
                    message: 'hello world'
                },
                beforeCreate: function() {
                    console.log(this);
                    showData('创建vue实例前', this);
                },
                created: function() {
                    showData('创建vue实例后', this);
                },
                beforeMount: function() {
                    showData('挂载到dom前', this);
                },
                mounted: function() {
                    showData('挂载到dom后', this);
                },
                beforeUpdate: function() {
                    showData('数据变化更新前', this);
                },
                updated: function() {
                    showData('数据变化更新后', this);
                },
                beforeDestroy: function() {
                    vm.test = "3333";
                    showData('vue实例销毁前', this);
                },
                destroyed: function() {
                    showData('vue实例销毁后', this);
                }
            });
            function realDom() {
                console.log('真实dom结构：' + document.getElementById('app').innerHTML);
            }
            function showData(process, obj) {
                console.log(process);
                console.log('data 数据：' + obj.message)
                console.log('挂载的对象：')
                console.log(obj.$el)
                realDom();
                console.log('------------------')
                console.log('------------------')
            }
            vm.message="good...";
            vm.$destroy();
        </script>
    </body>
</html>
```

vue对象初始化过程中，会执行到beforeCreate,created,beforeMount,mounted 这几个钩子的内容：

* `beforeCreate` ：数据还没有监听，没有绑定到vue对象实例，同时也没有挂载对象
* `created` ：数据已经绑定到了对象实例，但是还没有挂载对象
* `beforeMount`: 模板已经编译好了，根据数据和模板已经生成了对应的元素对象，将数据对象关联到了对象的el属性，el属性是一个HTMLElement对象，也就是这个阶段，vue实例通过原生的createElement等方法来创建这个html片段，准备注入到我们vue实例指明的el属性所对应的挂载点
* `mounted` : 将el的内容挂载到了el，相当于我们在jquery执行了(el).html(el),生成页面上真正的dom，上面我们就会发现dom的元素和我们el的元素是一致的。在此之后，我们能够用方法来获取到el元素下的dom对象，并进行各种操作

* 当我们的data发生改变时，会调用beforeUpdate和updated方法
  * `beforeUpdate` ：数据更新到dom之前，我们可以看到$el对象已经修改，但是我们页面上dom的数据还没有发生改变
  * `updated`: dom结构会通过虚拟dom的原则，找到需要更新页面dom结构最小路径，将改变更新到dom上面，完成更新
* `beforeDestroy` , `destroed` : 实例的销毁，vue实例还是存在的，只是解绑了事件的监听还有watcher对象数据与view的绑定，即数据驱动



## 3 Vue Ajax — axios

> vue-resource是Vue.js的插件提供了使用XMLHttpRequest或JSONP进行Web请求和处理响应的服务。 当vue更新
> 到2.0之后，作者就宣告不再对vue-resource更新，而是推荐的axios，在这里大家[了解一下vue-resource](https://github.com/pagekit/vue-resource)就可以。

[Axios](https://github.com/axios/axios) 是一个基于 promise 的 HTTP 库，可以用在浏览器和 node.js 中

### 3.1 引入 axios

如果使用es6，只需要安装axios模块之后

```
import axios from 'axios';
//安装方法
npm install axios
//或
bower install axios
```

也可以用script引入

```html
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
```

### 3.2 GET请求

```js
//通过给定的ID来发送请求
axios.get('/user?ID=12345')
    .then(function (response) {
    console.log(response);
})
    .catch(function (err) {
    console.log(err);
});

//以上请求也可以通过这种方式来发送
axios.get('/user', {
    params: {
        ID: 12345
    }
})
    .then(function (response) {
    console.log(response);
})
    .catch(function (err) {
    console.log(err);
});
```

### 3.3 POST请求

```js
axios.post('/user',{
    firstName:'Fred',
    lastName:'Flintstone'
})
    .then(function(res){
    console.log(res);
})
    .catch(function(err){
    console.log(err);
});
```



为方便起见，为所有支持的请求方法提供了别名

axios.get(url[, config])

axios.post(url[, data[, config]])

axios.put(url[, data[, config]])

axios.delete(url[, config])

axios.request(config)

axios.head(url[, config])

axios.patch(url[, data[, config]])



## 4 综合案例

完成用户的查询与修改操作（使用JPA）

### 4.1 后端

```sql
CREATE DATABASE vuejsdemo;
USE vuejsdemo;
CREATE TABLE USER(
    id INT PRIMARY KEY AUTO_INCREMENT,
    age INT,
    username VARCHAR(20),
    PASSWORD VARCHAR(50),
    email VARCHAR(50),
    sex VARCHAR(20)
)
```

```java
@Entity
//解决JPA实体类用jackson转JSON的问题；使用fastJson就没有此问题
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","fieldHandler"})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String sex;
    private int age;
    private String email;
    //......
}
```

使用JPA，所有Repository直接继承。省略了service层，直接写controller

```java
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/findById")
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @PostMapping("/update")
    @Transactional//省略了service层代码
    @Rollback(false)
    public void update(@RequestBody User user){
        System.out.println(user);
        userRepository.save(user);//本应该在Repository接口中定义更新方法，利用JPQL来实现
    }
}
```

### 4.2 前端

```js
new Vue({
    el: "#app",
    data: {
        user: {
            username: '',
            password: '',
            sex: '',
            age: '',
            email: ''
        },
        userList: []
    },
    methods: {
        findAll: function () {
            //在当前方法中定义一个对象，表明是vue对象
            let _this = this;
            axios.get('/user/findAll')
                .then(function (response) {
                _this.userList = response.data;
            })
                .catch(function (error) {
                console.log(error);
            })
        },
        findById: function (userId) {
            let _this = this;
            axios.get('/user/findById',{params:{id:userId}})
                .then(function (response) {
                _this.user = response.data;
                $("#myModal").modal("show");//模态窗口？？？
            })
                .catch(function (error) {
                console.log(error);
            })
        },
        update: function (user) {
            let _this = this;
            axios.post('/user/update',_this.user)
                .then(function (response) {
                _this.findAll();
            })
                .catch(function (error) {
                console.log(error);
            })
        }
    },
    created: function () {
        this.findAll();
    }
});
```

HTML绑定v-model、v-for等省略。利用好什么周期方法！模态窗口时什么？？？





# 7 Node.js

## 1 简介

Node.js 是一个基于 Chrome V8 引擎的 **JavaScript 运行环境**。 Node.js 使用了一个**事件驱动**、**非阻塞式 I/O 的模型**，使其轻量又高效。在特定领域性能出色，比如用 Node.js 实现消息推送、状态监控等的业务功能非常合适。

传统意义上的 JavaScript 运行在浏览器上，Chrome 使用的 JavaScript 引擎是 V8，Node.js 是一个运行在服务端的框架，它的底层就使用了 V8 引擎，这样就可以使用javascript去编写一些服务端的程序，这样也就实现了用javaScript去开发服务端的功能，这样做的好处就是前端和后端都采用javascript，即开发一份js程序即可以运行在前端也可以运行的服务端，这样比一个应用使用多种语言在开发效率上要高。

在[Node.js官网](https://nodejs.org/zh-cn/)下载所属平台的安装包或二进制文件，安装后在命令行输入`node -v`查看版本

## 2 快速入门

### 2.1 控制台输出

我们现在做个最简单的小例子，演示如何在控制台输出，创建文本文件demo1.js,代码内容

```js
var a=1;
var b=2;
console.log(a+b);
```

我们在命令提示符下输入命令

```shell
node demo1.js
```

### 2.2 使用函数

创建文本文件demo2.js

```js
var c=add(100,200);
console.log(c);
function add(a,b){
	return a+b;
}
```

命令提示符输入命令。运行后看到输出结果为300

```sh
node demo2.js
```

### 2.3 模块化编程

创建文本文件demo3_1.js

```js
exports.add=function(a,b){
	return a+b;
}
```

创建文本文件demo3_2.js

```js
var demo= require('./demo3_1');//ES5的语法
console.log(demo.add(400,600));
```

我们在命令提示符下输入命令。结果为1000

```sh
node demo3_2.js
```

### 2.4 创建web服务器

创建文本文件demo4.js

```js
var http = require('http');
http.createServer(function (request, response) {
    // 发送 HTTP 头部 
    // HTTP 状态值: 200 : OK
    // 内容类型: text/plain
    response.writeHead(200, {'Content-Type': 'text/plain'});
    // 发送响应数据 "Hello World"
    response.end('Hello World\n');
}).listen(8888);
// 终端打印如下信息
console.log('Server running at http://127.0.0.1:8888/');
```

**`http`为node内置的web模块**

我们在命令提示符下输入命令

```sh
node demo4.js
```

服务启动后，我们打开浏览器，输入网址http://localhost:8888/

即可看到网页输出结果Hello World。Ctrl+c 终止运行。

### 2.5 理解服务端渲染

我们创建demo5.js  ，将上边的例子写成循环的形式

```js
var http = require('http');
http.createServer(function (request, response) {
    // 发送 HTTP 头部 
    // HTTP 状态值: 200 : OK
    // 内容类型: text/plain
    response.writeHead(200, {'Content-Type': 'text/plain'});
    // 发送响应数据 "Hello World"
	for(var i=0;i<10;i++){
		response.write('Hello World\n');//write和end区别
	}  
	response.end('');	
}).listen(8888);
// 终端打印如下信息
console.log('Server running at http://127.0.0.1:8888/');
```

我们在命令提示符下输入命令启动服务

```sh
node demo5.js
```

浏览器地址栏输入http://127.0.0.1:8888即可看到查询结果。

我们右键“查看源代码”发现，并没有我们写的for循环语句，而是直接的10条Hello World ，这就说明这个循环是在服务端完成的，而非浏览器（客户端）来完成。这与我们原来的JSP很是相似。

### 2.6 接收参数

创建demo6.js

```js
var http = require('http');
var url = require('url');
http.createServer(function(request, response){
    response.writeHead(200, {'Content-Type': 'text/plain'});
    // 解析 url 参数
    var params = url.parse(request.url, true).query;
    response.write("name:" + params.name);
    response.write("\n");
    response.end();
}).listen(8888);
console.log('Server running at http://127.0.0.1:8888/');
```

我们在命令提示符下输入命令

```sh
node demo6.js
```

在浏览器测试结果



## 3 包资源管理器NPM

### 3.1 NPM简介

NPM全称Node Package Manager，他是Node包管理和分发工具。其实我们可以把NPM理解为前端的Maven。我们通过NPM可以很方便地下载js库，管理前端工程。最近版本的Node.js已经集成了npm工具，`npm -v`可查看当前npm版本



### 3.2 NPM命令

#### 3.2.1 初始化工程

`init`命令是**工程初始化**命令。建立一个空文件夹，在命令提示符进入该文件夹执行命令初始化`npm init`

按照提示输入相关信息，如果是用默认值则直接回车即可。

* name: 项目名称
* version: 项目版本号
* description: 项目描述
* keywords: {Array}关键词，便于用户搜索到我们的项目

最后会生成`package.json`文件，这个是**包的配置文件**，相当于maven的`pom.xml`我们之后也可以根据需要进行修改。

#### 3.2.2 本地安装

install命令用于安装某个模块，如果我们想安装`express`模块（node的web框架），输出命令如下`npm install express`

出现黄色的是警告信息，可以忽略，请放心，你已经成功执行了该命令。

在该目录下已经出现了一个`node_modules`文件夹 和`package-lock.json`

`node_modules`文件夹用于存放下载的js库（相当于maven的本地仓库）

`package-lock.json`是当 `node_modules` 或 `package.json` 发生变化时自动生成的文件。这个文件主要功能是确定当前安装的包的依赖，以便后续重新安装的时候生成相同的依赖，而忽略项目开发过程中有些依赖已经发生的更新。

我们再打开 `package.json` 文件，发现刚才下载的express已经添加到依赖列表中了.

关于版本号定义：

* 指定版本：比如`1.2.2`，遵循“`大版本.次要版本.小版本`”的格式规定，安装时只安装指定版本。
* 波浪号（tilde）+指定版本：比如`~1.2.2`，表示安装`1.2.x`的最新版本（不低于`1.2.2`），但是不安装1.3.x，也就是说安装时**不改变大版本号和次要版本号**。
* 插入号（caret）+指定版本：比如`ˆ1.2.2`，表示安装`1.x.x`的最新版本（不低于1.2.2），但是不安装2.x.x，也就是说安装时**不改变大版本号**。需要注意的是，*如果大版本号为0，则插入号的行为与波浪号相同*，这是因为此时处于开发阶段，即使是次要版本号变动，也可能带来程序的不兼容。
* **latest**：安装最新版本。



#### 3.2.3 全局安装

刚才我们使用的是本地安装，会将js库安装在当前目录，而使用全局安装会将库安装到你的全局目录下。

如果你不知道你的全局目录在哪里，执行命令`npm config ls`或`npm root -g`

我的全局目录在`C:/用户/[用户名]/AppData/Roming/npm/node_meodules`。为了方便对依赖包管理，我们将管理包的路径设置在单独的地方，本教程将安装目录设置在node.js的目录下，创建`npm_modules`和`npm_cache`，执行下边的命令：

```
npm config set prefix "C:\Develop\nodejs\npm_modules"
npm config set cache "C:\Develop\nodejs\npm_cache"
```

比如我们全局安装jquery,  输入以下命令`npm install jquery -g`



#### 3.2.4 批量下载

我们从网上下载某些代码，发现只有`package.json`，没有`node_modules`文件夹，这时我们需要通过命令重新下载这些js库

进入目录（package.json所在的目录）输入命令`npm install`，此时，npm会自动下载`package.json`中依赖的js库.



#### 3.2.5 淘宝NPM镜像

有时我们使用npm下载资源会很慢，所以我们可以安装一个cnmp(淘宝镜像)来加快下载速度。输入如下命令：

`npm install -g cnpm --registry=https://registry.npm.taobao.org`

安装后使用`.\cnpm -v`查看cnpm版本（在`npm_modules`目录下执行）

安装nrm`.\cnpm install -g nrm`（在`npm_modules`目录下执行）

`.\nrm ls `查看镜像后，使用`nrm use XXX`切换镜像（在`npm_modules`目录下执行）

配置环境变量后即可在任意目录下执行

- 配置`NODE_HOME = C:\Program Files\nodejs`
- Path中配置`%NODE_HOME%;%NODE_HOME%\npm_modules;`



#### 3.2.6 运行工程

如果我们想运行某个工程，则使用run命令

如果`package.json`中定义的脚本如下

* `dev`是开发阶段测试运行
* `build`是构建编译工程
* `lint` 是运行js代码检测 

我们现在来试一下运行dev，命令行输入`npm run dev`



#### 3.2.7 编译工程

我们接下来，测试一个代码的编译，编译后我们就可以将工程部署到nginx中啦~

编译后的代码会放在**dist**文件夹中，首先我们先删除dist文件夹中的文件，进入命令提示符输入命令`npm run build`

生成后我们会发现只有个**静态页面**，和一个**static**文件夹

这种工程我们称之为单页Web应用（single page web application，**SPA**），就是只有一张Web页面的应用，是加载单个HTML 页面并在用户与应用程序交互时动态更新该页面的Web应用程序。

这里其实是调用了webpack来实现打包的，关于webpack我们后续的章节进行介绍



## 4 Webpack

### 4.1 Webpack简介

Webpack 是一个前端资源加载/打包工具。它将根据模块的依赖关系进行静态分析，然后将这些模块按照指定的规则生成对应的静态资源。

![1550510683323](images/1550510683323.png)

Webpack好处如下：

- **模块化开发**

  程序员在开发时可以分模块创建不同的js、 css等小文件方便开发，最后使用webpack将这些小文件打包成一个文件，**减少了http的请求次数**。webpack可以实现按需打包，为了避免出现打包文件过大可以打包成多个文件。

- **编译typescript、ES6等高级js语法**

- **CSS预编译**

  webpack允许在开发中使用Sass 和 Less等原生CSS的扩展技术，通过sass-loader、less-loader将Sass 和 Less的语法编译成浏览器可识别的CSS语法。

webpack的缺点：配置有些繁琐，文档不丰富



### 4.2 Webpack安装

全局安装（4.0以后的版本需要安装webpack-cli）。本地安装只需去掉`-g`，即可在当前目录安装

```
npm install webpack -g
npm install webpack-cli -g
```

安装webpack指定的版本，只需在上述命令中webpack后指定版本如`...webpack@3.6.0...`

安装后查看版本号

```
webpack -v
```

### 4.3 快速入门

#### 4.3.1  JS打包

（1）创建src文件夹，创建bar.js

```js
exports.info=function(str){
   document.write(str);
}
```

（2）src下创建logic.js

```js
exports.add=function(a,b){
	return a+b;
}
```

（3）src下创建main.js

```js
var bar= require('./bar');
var logic= require('./logic');
bar.info( 'Hello world!'+ logic.add(100,200));
```

（4）创建配置文件webpack.config.js  ，该文件与src处于同级目录

```js
var path = require("path");
module.exports = {
	entry: './src/main.js',
	output: {
		path: path.resolve(__dirname, './dist'),
		filename: 'bundle.js'
	}
};
```

以上代码的意思是：读取当前目录下src文件夹中的main.js（入口文件）内容，把对应的js文件打包，打包后的文件放入当前目录的dist文件夹下，打包后的js文件名为bundle.js

（5）执行编译命令

```
webpack
```

执行后查看bundle.js 会发现里面包含了上面两个js文件的内容

（7）创建index.html ,引用bundle.js

```html
<!doctype html>
<html>
  <head>  
  </head>
  <body>   
    <script src="dist/bundle.js"></script>
  </body>
</html>
```

测试调用index.html，会发现有内容输出



#### 4.3.2 CSS打包

（1）安装style-loader和 css-loader

Webpack 本身只能处理 JavaScript 模块，如果要处理其他类型的文件，就需要使用 loader 进行转换。

Loader 可以理解为是模块和资源的转换器，它本身是一个函数，接受源文件作为参数，返回转换的结果。这样，我们就可以通过 require 来加载任何类型的模块或文件，比如 CoffeeScript、 JSX、 LESS 或图片。首先我们需要安装相关Loader插件，css-loader 是将 css 装载到 javascript；style-loader 是让 javascript 认识css

```
cnpm install style-loader css-loader --save-dev
```

（2）修改webpack.config.js

```js
var path = require("path");
module.exports = {
	entry: './src/main.js',
	output: {
		path: path.resolve(__dirname, './dist'),
		filename: 'bundle.js'
	},
	module: {
		rules: [  
            {  
                test: /\.css$/,  
                use: ['style-loader', 'css-loader']
            }  
        ]  
	}
};
```

（3）在src文件夹创建css文件夹,css文件夹下创建css1

```css
body{
 background:red;
}
```

（4）修改main.js ，引入css1.css

```
require('./css1.css');
```

（5）重新运行webpack

（6）运行index.html看看背景是不是变成红色啦？



#### 4.3.3 结合Vue打包

1. 定义`module01.js`，此文件就是一个模块，定义了一些方法

   ```js
   //定义add函数
   function add(x, y) {
       return x + y;
   }
   
   //定义add2函数
   function add2(x, y) {
       return x + y + 2;
   }
   
   //定义add3函数
   exports.add3 = function (x, y) {
       return x + y + 2;
   }
   
   //要导出的方法
   // module.exports.add = add;
   // module.exports.add2 = add2;
   module.exports = {add,add2};//多个方法这样导出更方便！或每次定义方法时导出，如add3
   ```

2. 定义`main.js`，是本程序的js主文件，名称任意取。包括如下内容：

   1. 引用`module01.js`模块
   2. 引用`vue.min.js`模块（它也一个模块）
   3. 将html页面中构建vue实例的代码放在`main.js`中（总之html中不再有js代码）

   ```js
   //如下都是ES5的导入方法！
   var {add} = require('./module01.js');//可以省略.js，但是必须带上./前缀表示当前目录！
   var module01 = require('./module01.js');//也可以全部导入
   var Vue = require('./vue.min.js');
   
   var VM = new Vue({
       el: "#app",//表示当前vue对象接管app的div区域
       data: {
           name: '黑马程序员',// 相当于是MVVM中的Model这个角色
           num1: 0,
           num2: 0,
           result: 0,
       },
       methods: {
           change: function () {
               //这里使用了导入的model01.js文件中的add方法
               this.result = module01.add2(Number.parseInt(this.num1), Number.parseInt(this.num2));
           }
       }
   });
   ```

3. 打包测试

   1. 进入程序即js文件所在目录，执行`webpack main.js build.js`，将`main.js`打包输出为`build.js`文件。也可以定义webpack.config.js配置打包方式
   2. 在HTML中引用`<script src="build.js"></script>`



#### 4.3.4 webpack-dev-server

webpack-dev-server开发服务器，它的功能可以实现热加载并且自动刷新浏览器。

1. 创建一个新的程序目录，这里我们创建`webpacktest`目录，将webpack入门程序的代码拷贝进来，并在目录下创建`src`目录、`dist`目录。将`main.js`，`module01.js`和`vue.min.js`拷贝到src目录。html文件拷贝到当前目录下。

2. 使用 webpack-dev-server需要**安装webpack**、 **webpack-dev-server**和 **html-webpack-plugin**三个包在当前程序目录

   `cnpm install webpack@3.6.0 webpack-dev-server@2.9.1 html-webpack-plugin@2.30.1 --save-dev`

   安装完成后程序目录出现**`package.json`文件**，此文件中记录了程序的**依赖信息**（上面三个）

   还有**`node_modules`文件夹**，有993个文件或文件夹！！！存放本程序所**依赖的包**！

3. **配置webpack-dev-server**，在`package.json`中配置script（运行命令），最终内容如下

   ```json
   {
     "scripts": {
       "dev": "webpack-dev-server --inline --hot --open --port 5008"
     },
     "devDependencies": {
       "html-webpack-plugin": "^2.30.1",
       "webpack": "^3.6.0",
       "webpack-dev-server": "^2.9.1"
     }
   }
   ```

   `scripts`：可执行的命令

   `--inline`：自动刷新；`--hot`：热加载；`--open`：自动在默认浏览器打开；`--port`：指定端口；

   `--host`：可以指定服务器的 ip，不指定则为127.0.0.1，如果对外发布则填写公网ip地址

   `devDependencies`：开发人员在开发过程中所需要的依赖

4. **配置`webpack.config.js`**，是webpack的配置文件，在此文件中可以配置应用的入口文件、输出配置、插件等，其中要实现热加载自动刷新功能需要配置html-webpack-plugin插件。html-webpack-plugin的作用是根据html模板在内存生成html文件，它的工作原理是**根据模板文件在内存中生成一个index.html文件**。

   1. 配置**模板文件**。将原来的`vue.html`作为模板文件，为了和内存中的`index.html`文件名区别，注意将`vue.html`中的所有`script`标签去掉

   2. 在`webpack.config.js`（与`package.json`同目录）中配置`html-webpack-plugin`插件

      ```js
      //引用html-webpack-plugin插件，作用是根据html模板在内存生成html文件，它的工作原理是根据模板文件在内存中生成一个index.html文件。
      var htmlwp = require('html-webpack-plugin');
      module.exports = {
          entry: './src/main.js',  //指定打包的入口文件
          output: {
              path: __dirname + '/dist',  // 注意：__dirname表示webpack.config.js所在目录的绝对路径
              filename: 'build.js'		   //输出文件
          },
          devtool: 'eval-source-map',//Debug调试
          plugins: [
              new htmlwp({
                  title: '首页',  //生成的页面标题<head><title>首页</title></head>
                  filename: 'index.html', //webpack-dev-server在内存中生成的文件名称，自动将build注入到这个页面底部，才能实现自动刷新功能
                  template: 'vue.html' //根据vue.html这个模板来生成(这个文件请程序员自己生成)
              })
          ]
      };
      ```

5. 运行

   - 在`webpacktest`目录，执行**`npm run dev`**。

   - 或使用webstorm，右键`package.json`文件，选择`“Show npm Scripts”`，双击dev即可

     【注意】dev就是在`package.json`中配置的`webpack-dev-server......`命令。

     启动成功自动打开浏览器。修改src中的任意文件内容，自动加载并刷新浏览器。

6. Debug调试

   使用了webpack之后就不能采用传统js的调试方法在chrome中打断点（因为打包了！内容发生变化）

   webpack提供devtool进行调试，它是基于sourcemap的方式，在调试时会生成一个map文件，其内容记录生成文件和源文件的内容映射，即生成文件中的哪个位置对应源文件中的哪个位置，有了sourcemap就可以在调试时看到源代码。

   - 在webpack.config.js中配置：`devtool: 'eval‐source‐map',`具体查看上面的代码
   - 在js中跟踪代码的位置上添加**debugger**，开启浏览器开发者工具……





# 8 ES6

## 8.1 什么是ES6

编程语言JavaScript是ECMAScript的实现和扩展 。ECMAScript是由ECMA（一个类似W3C的标准组织）参与进行标准化的语法规范。ECMAScript定义了：

[语言语法](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Lexical_grammar) – 语法解析规则、关键字、语句、声明、运算符等。

[类型](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Data_structures) – 布尔型、数字、字符串、对象等。

[原型和继承](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Inheritance_and_the_prototype_chain)

内建对象和函数的[标准库](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects) – [JSON](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON)、[Math](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math)、[数组方法](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array)、[对象自省方法](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object)等。

ECMAScript标准不定义HTML或CSS的相关功能，也不定义类似DOM（文档对象模型）的[Web API](https://developer.mozilla.org/en-US/docs/Web/API)，这些都在独立的标准中进行定义。ECMAScript涵盖了各种环境中JS的使用场景，无论是浏览器环境还是类似[node.js](http://nodejs.org/)的非浏览器环境。

2009年发布的改进版本ES5，引入了[Object.create()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/create)、[Object.defineProperty()](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/defineProperty)、[getters](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/get)和[setters](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions/set)、[严格模式](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Strict_mode)以及[JSON](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/JSON)对象。

ECMAScript 6.0（以下简称ES6）是JavaScript语言的下一代标准，2015年6月正式发布。它的目标，是使得JavaScript语言可以用来编写复杂的大型应用程序，成为企业级开发语言。

## 8.2 Node.js中使用ES6

ES6+ 太棒了,但是很多高级功能node是不支持的,就需要使用babel转换成ES5

 （1）babel转换配置,项目根目录添加.babelrc 文件

```json
{
  "presets" : ['es2015']
}
```

（2）安装es6转换模块。但是好像过时了`deprecate`

```
cnpm install babel-preset-es2015 --save-dev
```

（3）全局安装命令行工具

```
cnpm install  babel-cli -g
```

（4）使用

```
babel-node js文件名
```



## 8.3 语法新特性

### 8.3.1 变量声明let

我们都是知道在ES6以前，var关键字声明变量。无论声明在何处，都会被视为声明在**函数的最顶部**(**不在函数内即在全局作用域的最顶部**)。这就是函数变量提升例如

```js
  function aa() {
    if(bool) {
        var test = 'hello man'
    } else {
        console.log(test)
    }
  }
```

以上的代码实际上是:

```js
function aa() {
    var test // 变量提升
    if(bool) {
        test = 'hello man'
    } else {
        //此处访问test 值为undefined
        console.log(test)
    }
    //此处访问test 值为undefined
  }
```

所以不用关心bool是否为true or false。实际上，无论如何test都会被创建声明。

接下来ES6主角登场：

我们通常用let和const来声明，let表示变量、const表示常量。let和const都是块级作用域。怎么理解这个块级作用域？在一个函数内部 ，在一个代码块内部。看以下代码

```js
 function aa() {
    if(bool) {
       let test = 'hello man'
    } else {
        //test 在此处访问不到
        console.log(test)
    }
  }
```

### 8.3.2 常量声明const

const 用于声明常量，看以下代码

```js
const name = 'lux'
name = 'joe' //再次赋值此时会报错
```



### 8.3.3 模板字符串

es6模板字符简直是开发者的福音啊，解决了ES5在字符串功能上的痛点。

第一个用途，基本的字符串格式化。将表达式嵌入字符串中进行拼接。用`${}`来界定。

```js
    //es5 
    var name = 'lux'
    console.log('hello' + name)
    //es6
    const name = 'lux'
    console.log(`hello ${name}`) //hello lux
```

第二个用途，在ES5时我们通过反斜杠(`\`)来做多行字符串或者字符串一行行拼接。ES6反引号(**``**)直接搞定。

```js
    // es5
    var msg = "Hi \
    man!"
    // es6
    const template = `<div>
        <span>hello world</span>
    </div>`
```

### 8.3.4 函数默认参数

ES6为参数提供了默认值。在定义函数时便初始化了这个参数，以便在参数没有被传递进去时使用。

看例子代码

```js
    function action(num = 200) {
        console.log(num)
    }
    action() //200
    action(300) //300
```

### 8.3.5 箭头函数

ES6很有意思的一部分就是函数的快捷写法。也就是箭头函数。箭头函数最直观的三个特点。

1不需要function关键字来创建函数

2省略return关键字。当**大括号中只有一句代码**，可以**省略大括号和return**

3继承当前上下文的 this 关键字

看下面代码（ES6）

```js
 (response,message) => {
    .......
 }
```

相当于ES5代码

```js
function(response,message){
    ......
}
```

### 8.3.6 对象初始化简写

ES5我们对于对象都是以键值对的形式书写，是有可能出现键值对重名的。例如

```js
function people(name, age) {
    return {
        name: name,
        age: age
    };
}
```

以上代码可以简写为

```js
function people(name, age) {
    return {
        name,
        age
    };
}
```



### 8.3.7 解构

数组和对象是JS中最常用也是最重要表示形式。为了简化提取信息，ES6新增了解构，是将一个数据结构分解为更小的部分的过程

ES5我们提取对象中的信息形式如下

```js
const people = {
    name: 'lux',
    age: 20
}
const name = people.name
const age = people.age
console.log(name + ' --- ' + age)
```

是不是觉得很熟悉，没错，在ES6之前我们就是这样获取对象信息的，一个一个获取。现在，ES6的解构能让我们从对象或者数组里取出数据存为变量，例如

```js
//对象
const people = {
    name: 'lux',
    age: 20
}
const { name, age } = people
console.log(`${name} --- ${age}`)
//数组
const color = ['red', 'blue']
const [first, second] = color;//就得用first/second...
console.log(first) //'red'
console.log(second) //'blue'
```

### 8.3.8 Spread Operator`...`

ES6中另外一个好玩的特性就是Spread Operator 也是三个点儿`...`接下来就展示一下它的用途。 **组装**对象或者数组

```js
//数组
const color = ['red', 'yellow']
const colorful = [...color, 'green', 'pink']
console.log(colorful) //[red, yellow, green, pink]

//对象
const alp = { fist: 'a', second: 'b'}
const alphabets = { ...alp, third: 'c' }
console.log(alphabets) //{ "fist": "a", "second": "b", "third": "c"
```

### 8.3.9 import 和 export

> ES5中导出/导入
>
> ```js
> exports.add = () =>{
>     console.log('hello...add');
> }
> ```
>
> ```js
> let {add} = require('./test1');
> let test1 = require('./test1');
> add();
> test1.add();
> ```

import导入模块、export导出模块（在导出.vue中vue模块时，可以使用`export default`...）

test1.js

```js
export let add = () =>{
    console.log('hello...add');
}
// export {add as ad};这样统一写也可以，可以起别名
```



test2.js

```js
import {add} from './test1';
import * as test1 from './demo9.js';

add();
test1.add();
```

注意：node(v10.x)还是不支持import关键字，所以我们需要使用babel的命令行工具来执行（配置详见6.2小节内容）

```
babel-node demo9
```





### 8.3.10 Promise

==Promise 是**异步编程的一种解决方案（将异步请求用Promise包裹）**==，比传统的解决方案–回调函数和事件——更合理和更强大。它由社区最早提出和实现，ES6将其写进了语言标准，统一了语法，原生提供了Promise。

Promise是ES6提供的用于异步处理的对象，因为axios提交是异步提交，这里使用promise作为返回值。Promise使用方法如下：

Promise对象在处理过程中有三种状态：

* pending：进行中
* resolved：操作成功
* rejected: 操作失败

Promise的构建方法如下：

```js
const promise = new Promise(function(resolve,reject){
    //...TODO...
    if(操作成功){
        resolve(value);
    }else{
        reject(error);
    }
})
```

上边的构造方法function(resolve,reject)执行流程如下：
1）方法执行一些业务逻辑。
2）如果操作成功将Promise的状态由pending变为resolved，并将操作结果传出去
3）如果操作失败会将promise的状态由pending变为rejected，并将失败结果传出去。

上边说的操作成功将操作结果传给谁了呢？操作失败将失败结果传给谁了呢？通过promise的then、catch来指定

```js
promise.then(function (result) {
    console.log('操作成功：' + result);
});
promise.catch(function (reason) {
    console.log('操作失败：' + reason);
});
```



例如：

定义一个方法，返回promise对象

```js
testpromise(i){
    return new Promise((resolve,reject)=>{
        if(i % 2==0){
            resolve('成功了')
        }else{
            reject('拒绝了')
        }
    })
}
```

调用此方法：向方法传入偶数、奇数进行测试

```js
this.testpromise(3).then(res=>{//在then中对成功结果进行处理
    alert(res)
}).catch(res=>{//在catch中对操作失败结果进行处理
    alert(res)
})
```







