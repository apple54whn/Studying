# 1 入门

## 1.1 环境搭建等基础

* **命令提示符**

  | 命令含义         | 命令提示符（cmd）          | Windows PowerShell |
  | ---------------- | -------------------------- | ------------------ |
  | 切换盘符         | c: **或** C:               | 同左               |
  | 进入文件夹       | cd 文件夹名称              | 同左               |
  | 进入多级文件夹   | cd 文件夹1\文件夹2\文件夹3 | 同左               |
  | 返回上一级       | cd ..                      | 同左               |
  | 直接回根路径     | cd \ **或** cd /           | 同左               |
  | 查看当前目录内容 | dir                        | dir **或** ls      |
  | 清屏             | cls                        | cls **或** clear   |
  | 删除文件         | del                        | del **或** rm      |
  | 退出             | exit                       | exit               |

* **JVM**

  * JVM（Java Virtual Machine ）：Java虚拟机，简称JVM，是运行所有Java程序的假想计算机，是Java程序的 运行环境，是Java 具吸引力的特性之一。我们编写的Java代码，都运行在 JVM 之上。 
  * **跨平台**：任何软件的运行，都必须要运行在操作系统之上，而我们用Java编写的软件可以运行在任何的操作系 统上，这个特性称为**Java语言的跨平台特性**。该特性是**由JVM实现的**，我们编写的程序运行在JVM上，而**JVM** 运行在操作系统上，**不具有跨平台特性**，每个操作系统下都有不同版本的虚拟机。

* **JRE 和 JDK** 

  * JRE(Java Runtime Environment)：是Java程序的运行时环境，包含 **JVM** 和**运行时所需要的核心类库** 。
  * JDK (Java Development Kit)：是Java程序开发工具包，包含 **JRE** 和**开发人员使用的工具**。

* 安装及环境变量配置

  * JAVA_HOME
  * Path

* 编译与运行

  * **编译**：**javac** Test.java
  * **运行**：**java** Test

## 1.2 关键字

* 完全**小写**的字母（**main不是**）

* 在增强版的记事本当中（例如Notepad++）有特殊颜色

   public 、 class 、 static 、  void 、

## 1.3 标识符

* 指在程序中，我们**自己定义内容**。比如类的名字、方法的名字和变量的名字等等
  * **命名规则**： 硬性要求
    * 标识符可以包含 英文字母26个(区分大小写) 、 0-9数字 、 $（美元符号） 和 _（下划线） 。 
    * 标识符不能以数字开头。 
    * 标识符不能是关键字
  * **命名规范**： 软性建议
    * 类名规范：首字母大写，后面每个单词首字母大写（大驼峰式）。 
    * 方法名规范： 首字母小写，后面每个单词首字母大写（小驼峰式）。 
    * 变量名规范：全部小写。 



## 1.4 数据类型

**字节**是我们常见的计算机中**最小存储单元**

* **分类** 

  * **基本数据类型**：整数型 、 浮点型 、 字符型 、 布尔型 

    | 数据类型     | 关键字         | 内存占用 | 取值范围              |
    | ------------ | -------------- | -------- | --------------------- |
    | 字节型       | byte           | 1个字节  | -128~127              |
    | 短整型       | short          | 2个字节  | -32768~32767          |
    | 整型         | int（默认）    | 4个字节  | -2^31^~2^31^-1        |
    | 长整型       | long           | 8个字节  | -2^63^~2^63^-1        |
    | 单精度浮点数 | ﬂoat           | 4个字节  | 1.4013E-45~3.4028E+38 |
    | 双精度浮点数 | double（默认） | 8个字节  | 4.9E-324~1.7977E+308  |
    | 字符型       | char           | 2个字节  | 0~65535               |
    | 布尔类型     | boolean        | 1个字节  | true，false           |

  * **引用数据类型**：**字符串** 、数组 、类 、 接口 、Lambda

* 注意事项

  * 字符串不是基本类型，而是引用类型。
  * **浮点型**可能只是一个**近似值**，并非精确的值。
  * **数据范围与字节数不一定相关**，如**float数据范围比long更加广泛**，但是float是4字节，long是8字节。
  * 浮点数当中默认类型是double。如果一定要使用float类型，需要加上一个后缀F。
    如果是整数，默认为int类型，如果一定要使用long类型，需要加上一个后缀L。推荐使用大写字母。
  * 字符对应ASCII码（0~127）：48—'0'、65—'A'、97—'a'

* **数据类型转换**

  * **自动类型转换**(隐式)，范围**小**的类型向范围**大**的**类型提升**，如下：

    ==**参与运算时byte，short，char**——>**int**——>**long**——>**float**——>**double**(boolean不参与)==

    * ==**编译器的常量优化**==
      * 对于byte、short、char三种类型来说，若右侧赋值的**数值(不能为变量)**没有超过范围，那么javac编译器会自动隐含得帮我们**补上(byte) (short) (char)** ，否则编译报错。
      * 在给变量进行赋值时，若右侧的**表达式都是常量**，没有任何变量，那么javac编译器将会直接将若干个常量表达式计算得到结果。并根据是否超过范围决定编译成功与否。

    ```java
    byte a = 3;
    byte b = 4;
    byte c = a + b;//运算期间byte类型变量自动提升为int，但int类型不能赋值给byte类型，因此编译失败。
    byte d = 3 + 4;//常量在编译的时候（javac），已经确定了 3+4 的结果并没有超过byte类型的取值范围，可以赋值给d，成功
    
    char ch = 'A';
    System.out.println(ch + 1);//66
    ```

  * **强制类型转换**(显式)。一般不推荐使用，有可能发生**精度损失**(浮点转成整数，直接取消小数)，**数据溢出**

    ```java
    int a = 3;
    byte b = (byte)a;
    ```

    ==**在使用+=、-=、*=、/=、%=运算符进行赋值时，强制类型转换会自动完成**==



## 1.5 常量

- **整数**常量、**浮点数**常量、**字符**常量、**字符串**常量、**布尔**常量、**空**常量

## 1.6 变量

* 格式： 数据类型 变量名 = 数据值;
* **注意**：
  * 变量名称：在同一个大括号范围内（**作用域内**），变量的名字不可以相同。 
  * 变量赋值：定义的变量，**不赋值不能使用**。
  * 对于**byte或者short**类型变量，注意其**取值范围**
  * 对于float或者long类型变量，**后缀F、L**不能丢



## 1.7 运算符

* **算术运算符**：【+】【-】【*】【/】【%】【++】【--】

  * ++、--：混合运算时比较麻烦，在单独使用时没有区别，只有**变量**才能使用，常量不能使用

    * 变量前：先算后用
    * 变量后：先用后算

  * +在字符串中的操作： 连接、拼接字符串

    ```java
    int a = 10;
    int b = 20;
    String str = "hello";
    System.out.println(a + b + str);//30hello
    System.out.println(str+a+b);//hello1020
    ```

  * **取余和取模**（C、C++、java中%是取余，Python中%是取模），只对于整数有意义

    区别在于第一步的商**趋于0(取余)**、**趋于负无穷(取模)**，**取余和取模同符号数结果相同**

    ```java
    取余(结果符号取决于被除数)				取模(结果符号取决于模数)
    5%3=2；					 			5%3=2；
    -5%-3=-2;							 -5%-3=-2；
    5%-3=2;								 5%-3=-1；
    -5%3=-2;							 -5%3=1；
    ```

* **赋值运算符**

  * 基本赋值运算符：【=】

  * 复合赋值运算符：【+=】【-=】【*=】【/=】【%=】

    ==**在使用+=、-=、*=、/=、%=复合运算符进行赋值时，强制类型转换会自动完成**==

* **比较运算符**：【==】【<】【>】【<=】【>=】【!=】，结果是布尔值，不能连写
* **逻辑运算符**：是用来连接两个布尔类型结果的运算符，结果是布尔值，可以连写
  * 【**!**】：取反
  * 【**&&**】：短路与，符号左边是false，右边不再运算；若是【&】需运算完
  * 【**||**】：短路或，符号左边是true，右边不再运算；若是【|】需运算完

* **三元运算符**
  * 数据类型 变量名 = 布尔类型表达式？结果1：结果2
    * 必须同时保证结果1和结果2符合左侧数据类型要求
    * 三元运算符的结果必须被使用。赋值或打印

* **位运算符**

  `<<`左移，左边移走部分舍去，**右边补0**

  `>>`右移，右边移走部分舍去，**左边空位按原符号为补0(正数)或1(负数)**

  `>>>`无符号右移，不考虑正负，**左边补0**



## 1.8 方法

* **方法定义格式** 

  ```java
  修饰符 返回值类型 方法名 （参数列表）｛
  	方法体;
  	return 返回值;//停止方法，将返回值返回给调用处
  }
  ```

  * 方法调用

    ```java
    method();
    ```

  * 方法定义**注意事项**： 

    * 方法必须定义在一个**类中** 
    * 方法不能定义在另一个方法的里面，**不能嵌套**
    * 一个方法可以有多个return语句，但必须保证**同时只有一个return会被执行到**，return后不能有语句

* ==**方法重载(overload)**：同一类中**方法名相同**，**参数列表不同**(类型 或 个数 或 多类型顺序 不同)==

  ==与返回值类型无关、与参数名无关==

* **可变参数，**写方法中参数个数不明确时可用（同js中arguments类似），**可看做数组**

  ```java
  public static void sum(int...arr) {
  	int sum = 0;
  	for(int a:arr) {
  		sum+=a;
  	}
  	System.out.println(sum);
  }
  ```

* 

## 1.9  JShell脚本工具 

* JShell**脚本**工具是JDK9的新特性。当我们编写的代码非常少的时候，而又不愿意编写类，main方法，也不愿意去编译和运行，这个时候可以使用JShell工具，**一步一步运行代码**。
* 命令行直接输入`JShell`命令，退出`/exit`



## 1.10 流程控制

* **顺序结构**

* **判断语句**

  * If
  * if...else...
  * if...else if...else...
    * if else 和 三元运算符互换：取两数最大值

* **选择语句**

  * switch语句中**表达式数据类型可以是：byte、short、char、int；enum（枚举）、String(JDK7后)**

    ```java
    switch (表达式) {
        case 常量值1:
            语句1；
                break;
        case 常量值2:
            语句2；
                break;
            ......
        default:
            默认语句
            break;//default的break可省略不写，但不建议
    }
    ```

  * case的**穿透性**

    * 在switch语句中，如果case的后面不写break，将出现穿透现象，也就是**不会再判断下一个case**的值，直接向后运行，直到**遇到break**，或者**整体switch结束**。

  * **case后面的值不可重复，否则编译失败**

  * switch语句格式可以很灵活，**前后顺序可以颠倒**，**break语句也可以省略（例如，季节）**

* **循环语句**

  * for（三角形图形，上右，下左）

    ```java
    for(初始化表达式; 布尔表达式; 步进表达式){
        循环体; 
    }
    ```

  * while

    ```java
    初始化表达式;
    while(布尔表达式){
        循环体; 
        步进表达式;
    }
    ```

  * do...while

    ```java
    初始化表达式;     
    do {
    	循环体;
    	步进表达式;
    } while(布尔表达式);
    ```

  * **for 和 while 的区别**

    * 控制条件语句所控制的那个**变量**，在for循环结束后，就不能再被访问到了，而while循环结束还可以继续使用，如果你想**继续使用，就用while**，**否则推荐使用for**。原因是for循环结束，该变量就从内存中消失，能够提高内存的使用效率。 
    * 在**已知循环次数**的时候使用推荐使用**for**，**循环次数未知**的时推荐使用**while**。
    * do while 绝对会执行一次。

* **跳出语句**

  * **break**：**终止switch**或者**循环**，用在if中没用
  * **continue**：**结束本次循环**，**继续下一次**的循环

* 扩展知识点

  * 死循环：循环中的条件永远为true，死循环的是永不结束的循环。（**死循环后语句执行不到，编译失败**）
  * 嵌套循环：一个循环的循环体是另一个循环



## 1.11 数组

* **容器**：将多个数据存储到一起，每个数据称为该容器的元素

  * **数组**： 存储数据**长度固定**的容器，保证多个数据的**数据类型要一致（常量优化）**。是**引用数据类型**

    * 注意：数组**对象**有**定长**特性，**长度一旦指定，不可更改**，**不要把变量看成数组**

      ```java
      int[] arr1 = new int[3];
      System.out.println(arr1.length);//3
      arr1 = new int[5];
      System.out.println(arr1.length);//5
      ```

* **初始化**：

  * 动态初始化（**必须指定长度，二维数组可以只指定第一个**）

    * ==元素**默认值**：整数类型—0；浮点类型—0.0；字符类型—‘\u0000’；布尔类型—false；引用类型—null==

    ```java
    数组存储的数据类型[] 数组名字 = new 数组存储的数据类型[长度];
    数组存储的数据类型[] [] 数组名字 = new 数组存储的数据类型[长度] [长度];
    ```

  * 静态初始化（指定内容）

    * 也有元素默认值，但是之后又把大括号内容赋值给数组了

    ```java
    数据类型[] 数组名 = {元素1,元素2,元素3...};//省略格式不能分两步写
    数据类型[] 数组名 = {{元素1,元素2,元素3...},{元素1,元素2,元素3...}};
    数据类型[] 数组名 = new 数据类型[]{元素1,元素2,元素3...};
    ```


* ==**【注意】字符串有length()方法，数组有length属性**==

* 数组内存图

  ![](F:\GitHub\Studying\Java\images\04-两个引用指向同一个数组的内存图.png)

* 数组反转

  ```java
  public static void reverseArray(int[] arr){
      for (int i = 0; i < arr.length/2; i++) {
          int temp = arr[i];
          arr[i] = arr[arr.length-1-i];
          arr[arr.length-1-i]= temp;
      }
  }
  //或
  public static void reverseArray(int[] arr){
      for (int min=0,max=arr.length-1;min<max;min++,max--) {
          int temp = arr[min];
          arr[min] = arr[max];
          arr[max]= temp;
      }
  }
  ```

* 选择排序：找剩余数组元素中最小的，放在首位

  ```java
  public static void selectSort(int[] arr) {
      for (int i = 0; i < arr.length - 1; i++) {
          for (int j = i + 1; j < arr.length; j++) {
              if (arr[i] > arr[j]) {
                  int temp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = temp;
  ...
  ```

* 冒泡排序：相邻中最大的。。。

  ```java
  public static void bubbleSort(int[] arr){
      for (int i = 0; i < arr.length-1; i++) {
          for(int j=0;j<arr.length-i-1;j++){
              if (arr[j]>arr[j+1]){
                  int temp = arr[j];
                  arr[j] = arr[j+1];
                  arr[j+1] = temp;
  ...
  ```


## 1.12 Java内存划分

1. ==**栈（stack）**：存放的都是**方法中的局部变量**。**方法运行一定要在栈中**==
   - 局部变量：方法的参数，或方法{}内部的变量
   - 作用域：一旦**超出作用域**，立刻从栈内存中**消失**
2. ==**堆（heap）**：凡是**new出来的**，都在堆内存中==
   - 堆内存里东西都有一个**地址值**：16进制
   - 堆内存里数据都有**默认值**。规则如下：
     - **整数类型—0；浮点类型—0.0；字符类型—‘\u0000’；布尔类型—false；引用类型—null**
3. ==**方法区（Method Area）**：存储**.class相关信息**，包含**方法的信息**==
4. 本地方法栈（Native Method Stack）：与操作系统相关
5. 寄存器（PC Register）：与CPU相关



## 1.13 习题

* 对角线画圈

  ```java
  public static void printPic(int num){
      for (int i = 0; i < num; i++) {
          for (int j = 0; j < num; j++) {
              if(j==i||j==num-1-i){
                  System.out.print("O");
              } else {
                  System.out.print("*");
              }
          }
          System.out.println();
      }
  }
  ```

* 定义round方法，接收一位小数，实现四舍五入运算，并返回结果（+0.5，强转int）

  ```java
  public static int round(double d){
      return (int) (d+0.5);
  }
  ```

* 统计字符出现次数。利用容量为26的数组保存字符出现次数

  ```java
  public static void showTimes(char[] chs){
      int[] count = new int[26];
      for (int i = 0; i < chs.length; i++) {
          count[chs[i] - 97]++;
      }
      for (int i=0,ch=97 ;i<count.length;i++,ch++){
          if(count[i]!=0){
              System.out.println("符号"+(char)ch+"出现的次数:"+count[i]);
          }
      }
  }
  ```






# 2 类与对象

## 2.1 什么是类、对象

* **类**：是一组相关**属性**和**行为**的集合
  * **属性**：就是该事物的**状态信息**。
  * **行为**：就是该事物**能够做什么**
* **对象**：是**一类事物**的**具体体现**
* **类与对象的关系**
  * 类是对一类事物的描述，是抽象的
  * 对象是一类事物的实例，是具体的
  * ==**类是对象的模板，对象是类的实体**==

* **类的定义格式**

  ```java
  public class ClassName {
      //成员变量，对应事物的属性，描述事物的状态信息
      //构造方法：创建事物对象
      //成员方法，对应事物的行为，描述事物能做什么
  }
  ```

* **对象的使用**

  * 创建对象：`类名 对象名 = new 类名();`
  * 使用对象访问类中的成员：`对象名.成员变量； 对象名.成员方法()`

* 匿名对象（了解）：没有对象名的对象。若一个对象只需要使用唯一的一次，就可以使用匿名对象。

  * 创建匿名对象直接调用方法，没有变量名；匿名对象可以作为方法的参数和返回值。

* ==**成员变量的默认值**：整数类型—0；浮点类型—0.0；字符类型—‘\u0000’；布尔类型—false；引用类型—null==

* **两个对象使用同一方法**的内存图

  对象调用方法时，根据对象中方法标记（地址值），去类中寻找方法信息。这样哪怕是多个对象，方法信息 只保存一份，节约内存空间。 

  ![](F:\GitHub\Studying\Java\images\02-两个对象使用同一个方法的内存图.png)

* **两个引用指向同一对象**的内存图

  ![](F:\GitHub\Studying\Java\images\03-两个引用指向同一个对象的内存图.png)

* **引用类型作为参数传递到方法中，传递的是地址值**

  ![](F:\GitHub\Studying\Java\images\04-使用对象类型作为方法的参数.png)

* **使用引用类型作为方法的返回值，返回值就是对象的地址值**

  ![](F:\GitHub\Studying\Java\images\05-使用对象类型作为方法的返回值.png)

## 2.2 成员变量与局部变量

变量根据**定义位置**的不同，我们给变量起了不同的名字，成员变量与局部变量区别如下：

* ==在类中定义的**位置不同**（重点）== 
  * 成员变量：**类中，方法外** 
  * 局部变量：**方法中或者方法声明上(形式参数)** 
* ==**作用范围不一样**（重点）== 
  * 成员变量：**类中** 
  * 局部变量：**方法中**
* ==**初始化值的不同**（重点）== 
  * 成员变量：**有默认值** 
  * 局部变量：**没有默认值。必须先定义，赋值，最后使用** 
* ==在**内存中的位置不同**（了解）== 
  * 成员变量：**堆**内存 
  * 局部变量：**栈**内存 
* ==**生命周期不同** （了解）== 
  * 成员变量：**随着对象**的创建而存在，随着对象被垃圾回收而消失
  * 局部变量：**随着方法**的调用而存在，随着方法的调用完毕而消失 



## 2.3 面向对象

* 面向对象特点：**封装、继承、多态**

### 2.3.1 封装

* 原则：将**属性隐藏**起来，若需要访问某个属性，**提供公共方法**对其访问
* 封装的步骤：
  1. 使用 **private** 关键字来修饰成员变量
  2. 对需要访问的成员变量，提供对应的一对 **getXxx（isXxx）** 方法 、 **setXxx** 方法

* **private关键字**含义
  * private是一个权限修饰符，代表**最小权限**。 
  * 可以修饰**成员变量**和**成员方法**。 
  * 被private修饰后的成员变量和成员方法，只在**本类中才能访问**。 

* **this关键字**含义（解决成员变量与局部变量名称冲突而被隐藏的问题）
  * this代表**所在类的当前对象的引用**（地址值），即**对象自己的引用**。
    * 记住 ：==方法**被哪个对象调用**，方法中的this**就代表那个对象**。即谁在调用，this就代表谁。== 

* **构造方法**(**创建对象时**构造方法用来**初始化该对象**，给对象的**成员变量赋初始值**)
  - 构造方法名与类名相同
  - 构造方法名前面没有返回值类型
  - 构造方法不能return一个具体返回值，可以有return;但是没意义
  - 类中没有定义构造方法，**系统会自动创建一个默认的无参构造方法**，一旦定义构造方法，系统就不提供了
  - 构造方法也可以重载
* 标准代码—**JavaBean**
  * JavaBean 是 Java语言编写类的一种标准规范。符合 JavaBean 的类，要求类必须是**具体的和公共的**，并且具有**无参数的构造方法**，提供用来操作成员变量的 **set 和 get** 方法。
* **构造方法与成员方法的区别**
  * **作用**不同
    * 构造方法：创建对象
    * 成员方法：执行某具体功能
  * **命名**不同
    * 构造方法：类名一致
    * 成员方法：自定义
  * **返回值类型**不同
    * 构造方法：无返回值类型
    * 成员方法：void或者确定的数据类型
  * **调用**不同
    ​	构造方法：new 关键字调用
    ​	成员方法：对象.成员方法名调用
* 
* 
* 
* 
* 
* 
* 
* **this关键字**
  1. 明确的访问类的成员变量，解决与局部变量名称冲突问题
  2. 通过this关键字调用成员方法(可以省略)
  3. **只能在构造方法中调用**this来调用其他构造方法，并且必须**位于第一行且只能出现一次**，不能互相调用
* **垃圾回收**(当一个对象在内存中被释放时，它的**finalize()方法**会被自动调用)

  1. 等待Java虚拟机自动垃圾回收
  2. **调用`System.gc()`方法**通知Java虚拟机立即进行垃圾回收)
* **static关键字---**修饰**类的成员**(成员变量，成员方法，代码块等)

  * 随着**类的加载而存在**，被所有对象共享，可以通过类名直接调用(类名.成员)


  * **静态代码块**---类加载时执行(只有一次)，用于对类的成员变量初始化

      * **静态使用的注意事项**

          1. 静态方法只能访问静态成员。（非静态既可以访问静态，又可以访问非静态）
          2. 静态方法中不可以使用 this或者super关键字
          3. 主函数是静态的

- **内部类**

  * **特点**：
    * 内部类可以**直接访问**外部类中的成员(持有外部类引用，外部类名.this)
    * 外部类要访问内部类，必须**建立内部类对象**

  1. **成员内部类**(`Outer.Inner in = new Outer().new Inner();`)直接访问外部类中内部类成员
  2. **静态内部类**(`Outer.Inner in = new Outer.Inner();`)**不用创建外部类对象**，相当于外部类
     * 静态内部类只能访问外部类中的静态成员
     * 静态内部类中可以定义静态成员，**非静态内部类中不允许定义静态成员**
  3. **方法内部类(局部内部类)**(在方法中定义内部类并创建对象调用内部类中方法)
     * 局部内部类访问**变量必须加final**修饰，因为局部变量使用完毕就消失，而堆内存的数据不会立即消失

- **匿名内部类**(局部内部类的简化形式)

   * **使用场景：**方法的参数是接口类型，并且接口中的方法不超过三个

   * 前提：**内部类必须继承或实现一个外部类或接口**

     ```
     new 父类(参数列表)或 父接口(){
         //匿名内部类实现部分
     }
     ```

     ```
     animalShout(new Animal() {
     	@Override
     	public void shout() {
     		System.out.println("hehe");
     	}
     });//方法调用
     或
     new Animal() {
     	@Override
     	public void shout() {
     		System.out.println("hahahhahah");
     	}
     }.shout();
     ```

- **继承**(只支持单继承，但支持多实现，用逗号隔开)

   * 重写父类方法时不能比其**访问权限更严格**(`@override`)
   * `super`关键字用于**访问父类成员**(调用父类构造方法时必须位于子类构造方法第一行，只能出现一次)
   * 子类的构造方法中一定会**调用父类的构造方法**(默认是无参构造方法)

- **final关键字**(修饰类、变量、方法，有无法改变、最终的意思)
   -  **类**：不能被继承
   -  **变量**(成员变量和局部变量)：常量，只能赋值一次(需要在定义时赋值)
   -  **方法**：不能被子类重写

- **抽象类**
   * 抽象方法：不含方法体(**没有大括号**)的方法，用`abstract`修饰
   * **包含抽象方法的类必须声明为抽象类**，但**抽象类可以不包含任何抽象方法**
   * **抽象类不可以被实例化**(有可能包含抽象方法，没有方法体无法被调用)，可以**创建子类并实现抽象方法**
   * **成员特点：**
     1. 成员变量：有变量，有常量
     2. 成员方法：有抽象，有非抽象
     3. 构造方法：有，用于子类访问父类数据的初始化

- **接口**
   -  **一个抽象类中所有方法都是抽象的**，这个类可以定义为接口，用`interface`关键字声明
   -  同样的，接口不可以被实例化，需要定义一个类使用`implements`关键字**实现接口中所有方法**，如果这个类是**抽象类，实现部分**即可。
   -  **成员特点：**
      1. **成员变量**：只能是**常量且是静态**的，默认修饰符`public static final`
      2. **成员方法**：只能是**抽象**的，默认修饰符`public abstract`
      3. **构造方法**：**无**，其实现类继承`Object`，提供无参构造
   -  **总结**：
      1. 类与类：继承关系，只能单继承，可以多层继承
      2. 类与接口：实现关系，可以单实现，也可以多实现，可以在继承一个类时实现多个接口
      3. 接口与接口：继承关系，可以单继承，也可以多继承

- **多态**

   * **父类或接口的引用指向其子类对象**(一个对象，两种形态)

     ```
     Animal a = new Cat();//自动类型提升，向上转型，限制对特有功能访问
     ```

   * `instanceof`用于**判断引用数据类型的具体类型**

     ```
     a instanceof Cat;//Boolean
     ```

   * **多态时成员特点：**

     1. 构造方法：子类构造都会默认访问父类构造
     2. 静态方法和变量：编译、运行看左边
     3. 成员变量：编译、运行看左边
     4. **成员方法**：编译看**左边**，运行看**右边**(依赖对象)









# 3 包

- **package**

  - 专门用于存放类，在声明时使用`package`语句，并且声明只能位于Java源文件的**第一行**

    ```java
    package cn.itcast.chapter01; //package关键字声明包
    public class Hello{...}
    ```

  - 生成与包名对应目录(目录中可以是绝对地址、相对地址、“.”可以表示当前目录)，运行时要加上包名

    ```java
    javac -d [目录] Hello.java
    cd 目录
    java cn.itcast.chapter01.Hello
    ```

- **import**

  - 导包，import一般出现在package后，类定义之前

    ```java
    import cn.itcast.chapter01.Hello;//导入MAIN类
    import cn.itcast.chapter01.*;//导入包中所有类
    ```

  - **Java中常用包**

    ```java
    java.lang:Java语言核心类，如String、Math、System、Thread等，系统自动导入，无需import
    java.util:工具类、集合类，如Arrays、List、Set等
    java.net:网络编程相关类和接口
    java.io:输入输出相关类和接口
    java.awt:构建图形界面（GUI）相关类和接口
    ```

- **jar**

  - Java Archive File，Java档案文件，是一种压缩文件，独立于任何操作系统

  - **步骤：**

    1. **编译生成**与包名对应目录的**class文件**

       ```
       java -d . Hello.java
       ```

    2. 利用**jar命令**将cn及其目录下的文件都**压缩成jar包**

       ```
       jar -cvf Hello.jar cn
       ```

    3. 由于目前jar包中没有**主清单属性**，修改jar包`META-INF`中`MANIFEST.MF`文件，**指定main方法所在类**

       ```
       Main-Class: cn.itcast.chapter01.Hello	//注意“:”后有空格
       ```

    4. **运行jar包**

       ```
       java -jar Hello.jar
       ```

    5. **解压jar包**

       ```
       jar -xvf Hello.jar
       ```

- **访问控制**

  - **权限修饰符**

    - **private(类访问级别)**：被修饰的类成员只能被**该类其他成员**访问
    - **default(包访问级别)**：类或类成员没有修饰符为默认访问级别，只能被**本包中其他类**访问
    - **protected(子类访问级别)**：被修饰的类成员能被**本包中其他类**、**不同包中该类子类**访问
    - **public(公共访问级别)**：所有都能访问

  - **访问控制级别**

    | 访问范围 | private | default | protected | public |
    | :------: | :-----: | :-----: | :-------: | :----: |
    |  同一类  |    √    |    √    |     √     |   √    |
    |  同一包  |         |    √    |     √     |   √    |
    |   子类   |         |         |     √     |   √    |
    |   全局   |         |         |           |   √    |



# 4 Java API

- API（Application Programming Interface），应用程序编程接口

## 4.1 Scanner（java.util）

* 可以解析基本类型和字符串的简单文本扫描器。 System.in 系统输入指的是通过**键盘录入**数据。 

  ```java
  Scanner sc = new Scanner(System.in); 
  int a = sc.nextInt(); //将输入信息的下一个标记扫描为一个 int 值。 
  String a = sc.next(); //查找并返回来自此扫描器的下一个完整标记。
  ```



## 4.2 Random（java.util）

- 生成随机数的两个构造方法

  - `Random()`                          创建一个伪随机数生成器，因为种子随机所以每个对象产生**随机数不同**
  - `Random(long seed)`         创建一个伪随机数生成器，因为种子一定，每个对象产生**随机数序列相同**

- Random类的方法

  - `int nextInt()/nextLong()/nextFloat()/nextDouble()/nextBoolean()`  随机生成~类型所有范围内的随机数
  - `int nextInt(int n)`   **随机生成[0,n)之间的随机数**

- 生成**任意范围内的随机数**[]

  ```java
  Random r = new Random();
  r.nextInt(end-start+1)+start;//+1是为了保留右极限
  ```

- 每次生成10组6位随机验证码

  ```java
  char[] chs = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'A', 'B', 'C', 'D' };
  int len = chs.length;
  Random r = new Random();
  for (int i = 0; i < 10; i++) { //10组
      System.out.print("随机验证码：");
      for (int j = 0; j < 6; j++) { //6位
          int index = r.nextInt(len);
          System.out.print(chs[index]);
      }
      System.out.println();
  }
  ```


## 6.1 Object

- Object是所有类的父类，每个类的直接或间接继承自该类 ，有且只有一个无参构造方法。所以子类构造方法默认访问父类的构造是无参构造

- **方法：**

  1. `toString()`返回对象的字符串表示，默认是由类的全名+'@'+哈希值的十六进制表示，一般子类重写

     ```
     getClass().getName()+"@"toHexString(hashCode());
     
     ```

  2. `equals()`比较两个对象是否相同，默认情况下比较的是地址值是否相同，一般子类重写

  3. `hashCode()`返回对象的哈希值，不是实际地址值，可以理解为地址值

  4. `getClass()`返回对象的字节码文件对象，反射中讲解

  5. `finalize()`垃圾回收，不确定时间，可以调用`System.gc()`立即回收

  6. `clone()`实现对象克隆，包括成员变量的数据复制，但是它和两个引用指向同一个对象是有区别的 

- **注意问题**

  - 父类是抽象类，**子类不是抽象类**时才需要**重新**父类所有方法
  - **直接输出** 一个对象名称，默认调用该对象的`toString()`方法
  - **【面试】**==和equals()区别：
    1. `==`：**基本类型**：比较值是否相等；**引用类型**：比较地址值是否相等
    2. `equals()`：只能比较**引用类型**，比较地址值是否相等，可以根据需要重写

## 6.2 String

- **特点**

  1. String字符串是常量，一旦创建后内容和长度都无法改变（注意：这里指**new对象空间中的内容不能改变**，不是**引用**不**能改变**），其`overrid`了Object类的`equals()`方法。

  2. **字面值**作为**字符串对象**和通过**构造方法**创建对象的不同

     `String s = new String(char[] value,int offset,int count)`

     `String s = new String("hello")`和`String s = "hello"`,前者创建两个对象，后者创建一个对象

  3. 字符串若是**变量相加，先开空间再拼接**；若是**常量先加，然后在常量池中找**，有就返回，没有就创建

     ```
     String str1 = "hello";
     String str2 = "hello";
     System.out.println(str1.equals(str2));//true
     System.out.println(str1==str2);//true
     
     ```

     ```
     String s1 = "hello";
     String s2 = "world";
     String s3 = "helloworld";
     System.out.println(s3.equals(s1+s2));//true
     System.out.println(s3==s1+s2);//false
     System.out.println(s3.equals("hello"+"world"));//true
     System.out.println(s3=="hello"+"world");//true
     
     ```

- String常用方法(**不改变原String值**)

  - **判断功能**

    - `boolean isEmpty()`						       字符串长度是否为0

    - `boolean contains(CharSwquence cs)`                      字符串中是否包含指定字符序列

    - `boolean equals(Object anObject)`                          字符串与指定字符串是否相等(**内容**)

      `boolean equalsIgnoreCase(String str)`                 字符串与指定字符串是否相等，忽略大小写

      `boolean startWith(String prefix)`	  	                字符串是否以prefix前缀开始

      `boolean startWith(String prefix,int toffset)`  字符串从指定索引开始的子串是否以prefix开始

    - `boolean endWith(String suffix)`                             字符串是否以suffix后缀结尾

  - **获取功能**

    - `int length()`								 获取字符串长度

    - `char charAt(int index)`                                             获取指定索引的字符

    - `int indexOf(int ch/String str)`    获取指定字符/字符串第一次/出现的索引

    - `int indexOf(int ch/String str,int fromIndex)`   获取指定~~从**[指定索引开始**第一次出现的索引

      `ch`是`int`类型原因：'a'和97都能代表'a'。  (`lastIndexOF(...)`表示最后一次出现的索引)

    - `String substring(int startIndex,int endIndex)`  获取从**[start到end)**的子串,没有end时到结尾

  - **转换功能**

    - **`static`**`String valueOf(int i/char[] chs)`             **静态方法**将int型和**字符数组型**数据转为字符串
    - `String toLowerCase() /toUpperCase()`                     将所有字符都转换为小写/大写,本身不变
    - `byte[] getBytes()`                                                        将字符串转换为字节数组
    - `char[] toCharArray()`                                                  将字符串转换为字符数组
    - `String concat(String str)`                                         将字符串拼接

  - **其他功能**

    - **替换**(将所有old字符或字符串替换为新~~)

      `String replace(char/String old,char/String new)`

    - **去空格**(去掉字符串**首尾**空格)

      `String trim()`

    - **按字典比较**(按字典顺序比较，在str前返回负数，在str后返回正数，否则返回0)

      `int compareTo(String str)/compareIgnoreCase(String str)`

    - **拆分**(根据给定正则表达式regex的匹配拆分)，返回字符串数组，不包括regex字符串

      `String[] split(String regex)`

## 6.3 StringBuffer(StringBuilder)

- `StringBuffer`(字符串缓冲区)，表示字符容器，其**内容和长度可变**，同步，线程安全。没有`override`Object类的`equals()`方法，不能像String类对象可以用操作符+进行连接。

  ```
  StringBuffer sb = new StringBuffer();
  
  ```

- StringBuffer常用方法(**在原StringBuffer对象中改变并返回本身**，原对象也改变)

  - **添加**
    - `StringBuffer append(String str)`                                   将**指定参数**添加到Stringbuffer对象**末端**
    - `StringBuffer insert(int offset,String str)`               在字符串中的**offset位置**插入**指定参数**
  - **删除**
    - `StringBuffer deleteCharAt(int index)`                           删除指定位置字符
    - `StringBuffer delete(int start,int end)`                       删除从**[start到end)**的字符串
  - **替换**
    - `StringBuffer replace(int start,int end,String s)`    从**[start到end)**的对象替换为指定字符串
    - `void setCharAt(int index,char ch)`                                  **替换指定位置的字符**
  - **反转**
    - `StringBuffer reverse()`                                                       反转
  - **截取(返回值类型不再是StringBuffer本身)**
    - `String substring(int start,int end)`                               截取子串，没有end参数时截取到尾部
  - **其他**
    - `void setLength(int newLength)`                                          设置StringBuffer长度

- **String和StringBuffer转换**

  1. **String ---> StringBuffer**			通过构造函数传入或构造好之后append()

  2. **StringBuffer ---> String**                     调用`toString()`或String类的`valueOf()`静态方法

     ```
     String str1 = sb.toString();
     String str2 = String.valueOf(sb);
     
     ```

- **【面试】**

  1. **String、StringBuffer、StringBuilder区别**

     - **String内容、长度不可变，后两个可变**
     - **StringBuffer是同步的，数据安全，效率低；StringBuilder是不同步的，数据不安全，效率高**

  2. **StringBuffer和数组的区别**

     - **二者都可以看做装其他数据的容器，但是StringBuffer的数据最终是一个字符串数据，而数组可以存放多种类型的数据，但必须是同一种数据类型的**

  3. **参数传递问题**

     - 基本数据类型：形式参数的改变不影响实际参数

     - 引用数据类型：形式参数的改变影响实际参数

     - 注意：**String作为引用数据类型，但在参数传递时效果和基本类型一样**

       ```
       **StringBuffer、StringBuilder在赋值时不改变内容，调用方法时改变**
       
       ```

       **【重点】在于String内容不可变并且变量相加需要开空间再拼接，StringBuffer等内容可以变**

     ```
       String s1 = "hello";
       String s2 = "world";
       System.out.println(s1+"---"+s2);//hello---world
       change(s1,s2);
       System.out.println(s1+"---"+s2);//hello---world
       
       StringBuffer sb1 = new StringBuffer("hello");
       StringBuffer sb2 = new StringBuffer("world");
       System.out.println(sb1+"---"+sb2);//hello---world
       change(sb1,sb2);
       System.out.println(sb1+"---"+sb2);//hello---worldworld
       
       public static void change(String s1, String s2) {
       	s1 = s2;
       	s2 = s1+s2;
       }
       public static void change(StringBuffer sb1, StringBuffer sb2) {
       	sb1 = sb2;
       	sb2 = sb1.append(sb2);
       }
     
     ```

       ![](D:\Typora\photo\形参实参问题.png)

## 6.4 包装类（java.lang）

- 为了让基本类型的数据进行更多的操作，Java就为每种基本类型提供了对应的包装类类型 

  | 基本数据类型 | 引用数据类型 |
  | :----------: | :----------: |
  |     byte     |     Byte     |
  |     char     |  Character   |
  |    short     |    Short     |
  |     int      |   Integer    |
  |     long     |     Long     |
  |    float     |    Float     |
  |    double    |    Double    |
  |   boolean    |   Boolean    |

- **装箱与拆箱**

  - 装箱：将基本数据类型的值转为引用数据类型
  - 拆箱：将引用数据类型的值转为基本数据类型 `intValue()`将Integer转为int
  - JDK 5.0后支持自动拆装箱

- String和int的转换

  - String--->int        `Integer.parseInt("100")` 或  `Integer.valueof("100")`包装类型再转int
  - int--->String        `Integer.toString(100)` 或 `String.valueOf(100)`

- **进制转换**

  - `static String toString(int num,int radix)`               十进制到其他进制`toString`
  - `static int parseInt(String s,int radix)`                   其他进制到十进制`parseInt`

- **【面试】Integer数据直接赋值时，注意-128~127之间的数据缓冲池问题**

- **注意：**

  1. 包装类都重写Object类中的`toString()` 方法，以**字符串**形式返回包装类的基本数据类型的值
  2. 除了Character外，包装类都有`valueOf(String s)`方法，根据String类型参数创建包装类对象
  3. 除了Character外，包装类都有`parseXXX(String s)`的静态方法，将字符串转为基本类型数据

## 6.5 System

- System定义了一些与系统相关的属性和方法，并且都是静态的
- 常用方法
  - `void exit(int status)`                     退出当前运行的JVM，status表示状态码，非0表示异常终止
  - `void gc()`                                            运行垃圾回收器并立即回收垃圾
  - `long currentTimeMillis()`                获取当前时间戳，东八区应加8个小时
  - `arraycopy(arr1, 1, arr2, 3, 2)`    复制数组，从arr1的1开始长度为2的数据复制到arr2从3开始的地方

## 6.6 Arrays

- Arrays工具类是针对数组进行操作的工具类，包括排序和查找等功能
- 常用方法
  - `Arrays.toString(int[] arr)`                           将数组转为字符串
  - `Arrays.sort(int[] arr)`                                   给数组排序
  - `Arrays.binarySearch(int[] arr,int key)`    二分查找 
  - `Arrays.asList(int/String等[] arr)`              数组转集合，**长度不能变**

## 6.7 Math

- 针对数学运算进行操作的类

- 常用方法

  - 绝对值**abs**                               `abs(int/long/float/double a)`
  - 向上取整，取大的**ceil**            `ceil(double a)`
  - 向下取整，取小的**floor**          `floor(double a)`
  - 两数据中大着                           `max(int a,int b)`
  - a的b次幂**pow**                          `double pow(double a,double b)`
  - 随机数[0,1)**random**                `double random()`
  - 四舍五入**round**                        `long/int round(double a/float a)`-4.9四舍五入后为-5
  - 平方根**sqrt**                                `double sqrt(double a)`

- 任意**整数**范围的随机数 

  ```java
  (int)(Math.random()*(end-start+1))+start; //+1是为了包括右极限
  ```

- - 

## 6.9 BigInteger

- 针对大整数的运算

## 6.10 BigDecimal

- 浮点数据做运算，会丢失精度。所以，针对浮点数据的操作建议采用BigDecimal(**金融相关的项目**)

## 6.11 Date/Calendar/DateFormat

- java.util.**Date**用于表示日期和时间，可以精确到毫秒

  - **构造方法**（只有两个没过时）

    - `Date()`                      用于创建**当前**日期时间的Date对象

    - `Date(long date)`     用于创建**指定**时间的Date对象，date为时间戳

      ```
      Date d = new Date();
      System.out.println(d);
      		
      long date = System.currentTimeMillis();
      Date dd = new Date(date);
      System.out.println(dd);
      
      ```

  - **成员方法**

    - `setTime()`          设置此对象的**时间戳**
    - `getTime()`          返回此对象的**时间戳**

- java.util.**Calendar**用于完成日期和时间字段的操作，通过特定方法设置和读取日期的特定部分，是**抽象类**

  - **调用静态方法获取一个Calendar对象**

    ```
    Calendar calendar = Calendar.gerInstance();
    
    ```

  - **常用方法**(注意，MONTH从0开始)

    - **获取指定日历字段**的值(Calendar.YEAR/MONTH/DATE/HOUR_OF_DAY/MINUTE/SECOND等)

      `int get(int field)`

    - **设置指定日历字段**的值(Calendar.YEAR/MONTH/DATE/HOUR_OF_DAY/MINUTE/SECOND等)

      `void set(int field,int value)`

    - **设置日历字段的值(年月日时分秒等)**

      `void set(int year,int month,int date,int hourofday,int minute,int second)`

    - **根据日历规则，为指定日历字段加上或减去指定时间量**

      `void add(int field,int amount)`

  - **Date和Calendar之间转换**(利用Calendar对象的方法)

    - Date--->Calendar     接收一个Date对象并将其表示时间值**赋值给Calendar对象**

    - Calendar--->Date     **返回**表示Calendar时间值的**Date对象**

      ```
      Date d = new Date(9532418005450l);
      calendar.setTime(d);
      System.out.println(calendar.get(Calendar.YEAR));//2272
      
      System.out.println(calendar.getTime());//Mon Jul 24 15:49:16 CST 2018
      
      ```

  - 来世界多少天

    ```
    System.out.println("enter your birthday:(year/month/date)");
    Scanner sc = new Scanner(System.in);
    int year = sc.nextInt();
    int month = sc.nextInt();
    int date = sc.nextInt();
    
    Calendar calendar = Calendar.getInstance();
    calendar.set(year, month, date, 0, 0, 0);
    Date d = calendar.getTime();
    long bMillis = d.getTime();
    long nMillis = System.currentTimeMillis();
    long value = nMillis - bMillis;
    System.out.println("你来这个世界"+value/86400000+"天了");
    
    ```

  - 计算任意二月有多少天

    ```
    public static void howManyDays(int year) {
    	Calendar cal = Calendar.getInstance();
    	cal.set(year, 2, 1);
    	cal.add(Calendar.DATE, -1);
    	System.out.println(cal.get(Calendar.DATE));
    }
    
    ```

- java.text.**DateFormat**针**对日期进行格式化**和针**对字符串进行解析**的抽象类，使用其子类`SimpleDateFormat` 

  - 构造方法(无参构造为默认格式)

    - 指定格式构造`SimpleDateFormat sdf = new SimpleDateFormat(String pattern)`

      - Date--->String     **格式化**`format(Date d)`

        ```
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String s = sdf.format(d);//对日期格式化为字符串
        System.out.println(s);
        
        ```

      - String--->Date     **解析**`parse(String str)`

        ```
        String str = "2018-07-24 16:48:54";
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd- HH:mm:ss");
        d = sdf2.parse(str);//try...catch处理
        System.out.println(d);
        
        ```

- **【面试】**

  - Date类用来表示某个特定的瞬间，能够精确到毫秒。而在实际应用中，往往需要把一个日期中的年、月、日等信息单独返回进行显示或处理，这个类中的大部分方法都已被标记过时。Calendar类基本取代了Date类，该类中定义了一系列用于完成日期和时间字段操作的方法。 
    Calendar的`getTime()`方法)返回一个表示Calendar时间值的Date对象，同时Calendar有一个`setTime(Date date)`方法接收一个Date对象，将Date对象表示的时间值设置给Calendar对象，通过这两个方法就可以完成Date和Calendar对象之间的转换。











# 3 异常

* `Throwable`类

  	|

  	|——`Error`类，java运行时**系统内部错误**或**资源耗尽**错误，紧靠修改程序不能恢复执行，比较严重
​	
  	|
​	
  	|——`Exception`类
​	
  			|
​	
  			|——`RuntimeException`**由程序错误导致的异常，修改代码**
​	
  			|
​	
  			|——**编译时异常，必须处理，否则编译失败**。程序没有问题，像I/O错误等

  * Java语言规范将派生于**Error**类和**RuntimeException**类的所有异常称为**非受查异常**，其他为**受查异常**

* `Throwable`类中常用方法

  * String `getMessage()`			返回此throwable的详细消息字符串
  	 void `pritnStackTrace()`		将此throwable及其追踪输出至标准输出流
  	void `printStackTrace()`		将此throwable及其追踪输出至指定输出流

* **异常捕获**

  ```
  try {
  	int result = 4/0;
  	System.out.println(result);
  } catch (Exception e) {
  	System.out.println("捕获的异常信息："+e.getMessage());
  } finally {
  	System.out.println("finally语句");
  }
  System.out.println("继续执行。。。");
  ```

  * `try`中异常语句后的代码不被执行，必须要执行的可以放在`finally`中，如**释放系统资源**。但是当在`try…catch`中执行`System.exit(0)`(表示退出当前Java虚拟机)，任何代码都不会执行

* **自定义异常**

  * 继承自Exception或RuntimeException，只要提供无参构造和一个带参构造即可

* **注意**

  * 运行时异常和编译时异常区别？
    * RuntimeException可以处理也可以不处理
    * 编译时异常编译器会对其进行检查，出现异常必须处理，否则无法通过编译
  * 父类的方法有异常抛出，子类重写的方法在抛出异常时必须要小于等于父类的异常
  * 父类的方法没有异常抛出，子类重写的方法不能有异常抛出
  * 父类的方法抛出多个异常，子类重写的方法必须比父类少或者小

* **【面试】**

  1. `throw`和`throws`区别

     - throw：在方法体中，后面跟异常对象名，并且只能是一个，方法体内处理

       		 抛出的是**异常对象** ，说明这里**肯定有异常**产生。一般用于自定义异常，体现在选择语句中

       	         `throw new MyException();`

     - throws：在方法声明上，后面跟异常的类名，可以是多个，调用者处理

       		 **声明方法有异常**，是一种**可能性**，这个异常不一定会产生

                     `public static void main(String[] args) throws Exception{}`

  2. `final`,`finally`,`finallize`区别

     - `final`：最终意思，可以修饰类、成员变量、成员方法

       	    修饰类：类不可被继承
            
               修饰成员变量：变量是常量
            
               修饰成员方法：不能被重写

     - `finally`：异常处理，用于释放资源，finally中的代码一定会被执行，除非执行之前jvm退出

     - `finalize`：Object类的一个方法，用于垃圾回收

  3. 如果`catch`里有`return`，请问`finally`还执行吗？如果执行，在`return`前还是后

     * 会执行，在return前，其实在中间

       ```
       public static int fin() {
       	int a = 10;
       	try {
       		System.out.println(a/0);//执行到此转catch
       		a = 20;
       	} catch (Exception e) {
       		a = 30;
       		return a;//形成return 30路径，当finally执行完，执行return 30
       	} finally {
       		a = 40;
       		//return a;理论上最终值为40，因为只能通过一个return返回，但是eclipse异常提醒
       	}
       	return a;
       }
       ```

  4. **多catch时，父类catch放在最下面**




# 5 并发

## 5.1 多线程及实现

- **进程**：正在运行的程序（直译）。进程是系统进行资源分配和调用的独立单位，每一个进程都有它自己的内存空间和系统资源。一个进程中至少有一个线程。**多进程提高CPU使用率**。


- **线程**：进程中一个负责程序执行的控制单元（执行路径），是程序使用CPU的最基本单位。


- **多线程**：一个进程中可以有多执行路径（不是提高程序执行速度，**提高程序的使用率**）。

    	好处：解决多部分同时运行的问题

    	弊端：线程太多效率降低

- **并行和并发**

    **并行：逻辑上**同时发生，指在某一个**时间内**同时运行多个程序。
    **并发：物理上**同时发生，指在某一个**时间点**同时运行多个程序。

- **应用程序的执行都是CPU在做着快速切换完成的，这个切换是随机的**。

- **线程调度模型**

    1. **分时调度模型**   所有线程轮流使用CPU的使用权，平均分配每个线程占用CPU的时间片
    2. **抢占式调度模型**   优先让优先级高的线程使用CPU，如果线程的优先级相同，那么会随机选择一个，优先级高的线程获取的CPU 时间片相对多一些（Java使用）



- **Java程序运行原理（多线程）**

    由Java命令启动JVM（相当于启动了一个进程），接着由该进程创建启动多个线程，至少两个线程可以分析出来：**执行main()函数的主线程**，该线程的任务代码都定义在main函数中，**负责垃圾回收的线程**。


- **多线程的实现方式**:（目的：**开启一条执行路径，去运行指定的代码和其他代码实现同时运行**。运行的指定代码就是这个执行路径的任务，**jvm创建的主线程任务定义在主函数中**。自定义线程任务在哪？Thread类用于描述线程，线程是需要任务的，所以Thread类也是对任务的描述。**这个任务就是通过Thread类中的run方法来体现**。也就是说，**run方法就是封装自定义线程运行任务的函数**）

  - **继承Thread类**

    1. 继承Thread类
    2. 覆盖(override)Thread类的run方法，将线程的任务代码封装到run方法中
    3. 创建Thread类的子类对象
    4. 调用***start***方法开启线程并调用线程任务的***run***方法执行

  - **实现Runnable接口（常用）**

    1. 定义类实现Runnable接口
    2. 覆盖(override)接口中的run方法，将线程的任务代码封装到run方法中
    3. 通过Thread类创建线程对象，并将Runnable接口的子类对象作为Thread类的构造函数的参数进行传递。***因为线程的任务都封装在Runnable接口子类对象的run方法中，所以要在线程对象创建时就必须明确要运行的任务***
    4. 调用start方法开启线程并调用线程任务的run方法执行

  - **区别（实现Runnable接口的好处）**
    1. 避免了java单继承的局限性
    2. 适合多个相同程序的代码去处理同一个资源的情况，将线程的任务从线程的子类中分离出来，进行了单独的封装，按照面向对象的思想将任务封装成对象

    ```
    public class Demo extends Thread{
    	private String name;
    	Demo(String name){
    		super(name);//改线程名称
    		//this.name = name;
    	}
    	public void run() {
    		for(int i=0;i<10;i++) {
    			//int[] arr =arr[3];
    			//arr[3];//异常
    			System.out.println(i+Thread.currentThread.getName());//
    		}
    	}
    }
    public class duoxiancheng {
    	public static void main(String[] args) {
    		Demo d1 = new Demo("旺财");
    		Demo d2 = new Demo("hehe");
    		d1.start();
    		d2.start();
    		//System.out.println(4/0);//异常
    		for(int i=0;i<10;i++) {
    			System.out.println(i+Thread.currentThread.getName());//
    		}
    	}
    }
    ```

    可以通过`Thread的getName()`获取线程名(如：Thread-0)

    通过`Thread.currentThread.getName()`获取当前运行线程

    通过`super(name)`改线程名称,也可以通过`setName()`修改

    ```
    public class Ticket implements Runnable{
    	
    	private int num = 100;
    	public void run() {
    		sale();
    	}
    	public void sale() {
    		while(true) {
    			if(num>0)
    				System.out.println(Thread.currentThread().getName()+" "+num--);
    		}
    	}
    }
    public class duoxianchengDemo {
    	public static void main(String[] args) {
             Ticket t = new Ticket();
    		new Thread(t).start();
    		new Thread(t).start();
    		new Thread(t).start();
    		new Thread(t).start();
    	}
    }
    ```

- **匿名内部类实现多线程**

  * **继承Thread类**

    ```
    new Thread() {
    	@Override
    	public void run() {
    		for (int i = 0; i < 100; i++) {
    			System.out.println(getName() + "---" + i);
    		}
    	}
    }.start();
    ```

  * **实现Runnable接口**

    ```
    new Thread(new Runnable() {
    	@Override
    	public void run() {
    		for (int i = 0; i < 100; i++) {
    			System.out.println(Thread.currentThread().getName() + "---" + i);
    		}
    	}
    }).start();
    ```



## 5.2 线程调度

- 线程**优先级**	

  Java线程默认优先级是5(1-10低到高,Thread的**静态**常量NORM_PRIORITY(MIN/MAX))。通过`getPriority()`获取，通过`setPriority()`设置。

- 线程**休眠**（暂停执行，是**静态方法**，控制**当前运行的线程**休眠，**阻塞该线程**，休眠结束回到就绪状态）

  通过`Thread.sleep(long millis)`设置(静态方法)

- 线程**让步**（暂停当前正在执行的线程对象，不会阻塞该线程，**转为就绪状态**，调度器重新调度）

  通过`Thread.yield()`设置

- 线程**插队**（等待该线程终止）

  线程调用方法`th_1.join()`，只有th_1该线程执行完毕，其他线程才可以抢占资源。

- **后台线程（守护线程，如坦克大战）**（当前正在运行的线程都是后台线程时，JVM退出，该方法必须**在启动线程前调用**）

  通过`th.setDaemon(true)`设置

- **中断**线程（把线程的状态终止，并抛出一个InterruptedException）

  通过`th.interrupt()`设置



## 5.3 同步

* **买票问题**
  1. 相同的票出现多次：CPU的一次操作必须是原子性的
  2. 出现负数的票：随机性和延迟导致


* **线程安全问题产生原因**

  1. 多个线程在操作共享数据
  2. 操作共享数据的代码有多条

     **当一个线程在执行操作共享数据的多条代码过程中，其他线程参与了运算，就会导致**

* **线程安全问题解决思路**

          **将多条操作共享数据的线程代码封装起来**，当有线程执行这些代码时，其他线程不可以参与运算，必须要当前线程把这些代码都执行完毕后，其他线程才可以参与运算

* **同步的优缺点：**

  - **好处**：解决线程的安全问题
  - **弊端**：相对降**低效率**，因为同步外的线程都会判断同步锁；若有同步嵌套容易产生**死锁**

* **同步代码块**


  * 在java中，**建议使用同步代码块**解决，(**前提**：必须有**多个线程并使用同一锁**)

    ```
    public class Ticket implements Runnable {
    	private int ticket = 100;
    	Object obj = new Object();
    	
    	@Override
    	public void run() {
    		while(true) { //循环要同步执行的代码
    			synchronized(对象)//锁可使用Object obj = new Object();
    				try {
    					Thread.sleep(10);
    				} catch (InterruptedException e) {
    					e.printStackTrace();
    				}
    				if(ticket>0) {
    				   System.out.println(Thread.currentThread().getName()+"---"+ticket--);
    				}
    				else
    					break;
    			}
    		}
    	}
    }
    ```

* **同步方法**

  * **同步方法和同步方法的锁(this)**

    ```
    public synchronized void sale() {
    	if(num>0)
    		System.out.println(Thread.currentThread().getName()+" "+num--);
    }
    ```

  * **静态同步方法**使用的锁是**该方法所属类的字节码文件对象**。可以用`getClass`方法获取，也可以用`类名.class`

* **Lock对象**（jdk5之后提供的新的锁对象，是个接口，用其实现类`Reentrant`）

  `void lock()`:获取锁

  `void unlock()`:释放锁

  ```
  private int count = 100;
  private Lock lock= new ReentrantLock();
  
  @Override
  public void run() {
  	while(true) {
  		try {
  			lock.lock();
  			if(count>0) {
  			System.out.println(Thread.currentThread().getName()+" 正在卖第" + count-- + "张票" );
  			}
  		} finally {
  			lock.unlock();
  		}
  	}
  }
  ```



* **死锁问题(哲学家就餐)**

  * 指两个或两个以上的线程在执行的过程中，因**争夺资源**产生的一种**互相等待**现象

    ```
    public class DieLock implements Runnable{
    	private boolean flag;
    	public DieLock(boolean flag) {
    		this.flag = flag;
    	}
    	@Override
    	public void run() {
    		if(flag) {
    			synchronized(MyLock.obja) {
    				System.out.println("if obja");
    				synchronized (MyLock.objb) {
    					System.out.println("if objb");
    				}
    			}
    		}
    		else {
    			synchronized (MyLock.objb) {
    				System.out.println("else objb");
    				synchronized (MyLock.obja) {
    					System.out.println("else obja");
    				}
    			}
    		}
    	}
    }
    ```


## 5.4 多线程通信

* **生产者消费者问题**（不同种类的线程对**同一资源**的操作）

* **当要求设置和获取线程的资源是同一个，在外界把资源创建出来，通过构造方法传递给其他类**

* **等待唤醒机制**

  Object类中提供了三个方法：（这些方法必须通过**锁对象调用**，由于任意锁所以定义在Object）

  * `wait():`等待并立即释放锁，将来唤醒是从这里唤醒
  * `notify():`唤醒单个线程，并不立即执行，还是得抢处理机资源
  * `notifyAll():`唤醒所有线程

  ```
  synchronized (s) {
  		if(!s.flag)
  			try {
  				s.wait();
  			} catch (InterruptedException e) {
  						e.printStackTrace();
  			}
  		System.out.println(s.name+"---------"+s.age);
  				
  		s.flag = false;
  		s.notify();
  }
  
  synchronized (s) {
  		if(s.flag)
  			try {
  				s.wait();
  			} catch (InterruptedException e) {
  				e.printStackTrace();
  			}
  				
  		if(count%2==0) {
  			s.name = "张三";
  			s.age = 3;
  		}
  		else {
  			s.name = "李四";
  			s.age = 4;
  		}
  		count++;
  		s.flag = true;
  		s.notify();
  				
  }
  ```

* **优化后的等待唤醒代码**

  ```
  	private String name;
  	private int age;
  	private boolean flag;
  	
  	public synchronized void set(String name,int age) {
  		if(flag) {
  			try {
  				this.wait();//有就等待
  			} catch (InterruptedException e) {
  				e.printStackTrace();
  			}
  		}
  		this.name = name;
  		this.age = age;
  		
  		this.flag = true;//设置完改标志位
  		this.notify();//唤醒
  	}
  	
  	public synchronized void get() {
  		if(!flag) {
  			try {
  				this.wait();//没有就等待
  			} catch (InterruptedException e) {
  				e.printStackTrace();
  			}
  		}
  		System.out.println(this.name+"---"+this.age);
  		this.flag = false;//输出完就没有了，改标志位
  		this.notify();//并唤醒
  
  	}
  ```

  ​

## 5.5 线程组（ThreadGroup）

* 批量操作线程，线程默认情况下属于main线程组

```
ThreadGroup tg = new ThreadGroup("新的线程组");
MyThread mt = new MyThread();
		
Thread t1 = new Thread(tg,mt,"线程1");
Thread t2 = new Thread(tg,mt,"线程2");
System.out.println(t1.getThreadGroup().getName()+"--"+t2.getThreadGroup().getName());
tg.setDaemon(true);
tg.setMaxPriority(10);
```

## 5.6 线程池

* 程序启动一个新线程成本是比较高的，以为它要涉及到与操作系统进行交互，而使用线程池可以很好的提高性能，尤其是当程序中要创建大量生存期很短的线程时。（一开始产生一些线程，用完回收）

* **Executors工厂类产生线程池**

  `public static ExecutorService newFixedThreadPool(int nThread)`//静态方法

```
		ExecutorService pool = Executors.newFixedThreadPool(2);//一个线程池对象，2个线程
		//可以执行Runnable对象或者Callable对象代表的线程
		pool.submit(new MyRunnable());
		pool.submit(new MyRunnable());
		//结束线程池
		pool.shutdown()
```

* **实现Callable来实现多线程**（有返回值，可以抛异常）

## 5.7 定时器



## 面试题

- **run()和start()的区别?**

  	run():仅仅是封装被线程执行的代码，直接调用是普通方法。

  	start():首先启动了线程，然后再由jvm去调用该线程的run()方法。

- **线程的生命周期**

  ![](D:\Typora\photo\线程状态.PNG)







# 7 容器 

* **对象数组：**数组可以存储基本数据类型和引用类型，存储引用类型的数组就叫**对象数组**

* **容器(container)**
  * **由来**  Java-->面向对象-->操作很多对象-->存储-->容器
  * **区别**
    1. **长度**     数组固定，容器（集合）可变
    2. **内容**     数组可以是基本类型和引用类型，容器（集合）只能是引用类型
    3. **元素内容**     数组只能存储同一类型，容器（集合）可以存储不同类型(一般也是同一种类型)

* **继承体系结构**

  ![](F:\GitHub\Studying\Java\images\容器.png)

## 7.1 Collection

* **独立元素的序列**

* **常用方法:**

  * **添加(add/addAll)**
    * `boolean add(Object o)`
    * `boolean addAll(Collection c)`
  * **删除(clear/remove/removeAll)**
    * `void clear()`
    * `boolean remove(Object o)`
    * `boolean removeAll(Collection)`
  * **判断(isEmpty/contains/containsAll)**
    * `boolean isEmpty()`
    * `boolean contains(Object o)` 底层是equals方法，需重写
    * `boolean containsAll(Collection c)`  底层是equals方法，需重写
  * **获取容量(size)**
    * `int size()`
  * **返回迭代器(iterator)Collection特有的**
    * `Iterator iterator()`

* **Iterator**

  ```
  Iterator it = arraylist.iterator();
  while(it.hasNext()){
      System.out.println(it.next());
  }
  ```

* **foreach循环(用于遍历数组或容器中元素)**

  * **【注意】只能访问集合中元素，不能修改**

  ```
  ArrayList<String> arraylist = new ArrayList<>();
  //......
  for(String s:arraylist){
  	System.out.println(s);
  }
  ```

* **并发修改异常**(ConcurrentModificationException)

  * 现象：**迭代器遍历集合，集合修改集合元素**

  * 原因：迭代器依赖于集合，而集合的改变导致迭代器预期的迭代次数发生改变

  * 解决：1.**迭代器遍历，迭代器修改**(利用ListIterator)，增加元素在迭代的元素之后

     	            2.**集合遍历，集合修改**(利用get()和size())，增加元素在集合末尾
  
            3.删除元素时并跟上break语句，或利用Iterator的remove方法

### 7.1.1 List

* **元素有序(存入和取出顺序一致)**，通过索引来访问指定元素，允许出现重复元素
* **特有方法**
  * **添加(add/addAll)**
    * `void add(int index,Object o)`
    * `void addAll(int index,Collection c)`
  * **删除(remove)**
    * `Object remove(int index)`
  * **获取(get/indexOf/lastIndexOf/subList)**
    * `Object get(index)`
    * `int indexOf(Object o)`
    * `int lastIndexOf(Object o)`
    * `List subList(int fromIndex,int toIndex)`
  * **修改替换(set)**
    * `Object set(int index,Object o)`
* **List集合特有遍历功能**get()和size()结合

#### ArrayList（java.util）

* **底层是数组，查询快，增删慢，线程不安全，效率高**

* **常用方法：没有特殊方法**

#### LinkedList

* **底层是双向循环链表，查询慢，增删快，线程不安全，效率高**

* **特有方法**
  * **添加**
    * `void addFirst(Object o)`
    * `void addLast(Object o)`
  * **删除**
    * `Object removeFirst()`
    * `Object removeLast()`
  * **获取**
    * `Object getFirst()`
    * `Objcet getLast()`

### 7.1.2 泛型

* **概述：**泛型是一种把明确类型的工作推迟到创建对象或者调用方法时才去明确的特殊的类型

* **好处：**
  1. 把运行时期的问题提前到了编译时期
  2. 避免了强制类型转换
  3. 优化程序设计，解决黄色警告线问题，让程序更安全

* 泛型高级通配符
  * `Collection<?> c5 = new ArrayList<Object>();` 
  * 泛型如果明确的写的时候，前后必须一致；**?表示任意的类型**都是可以的
  * **? extends E**: 向下限定，E及其子类 
  * **? super E**: 向上限定，E及其父类 

* 泛型方法：

  ```
  public static <T> void swap1(T[] arr ,int a,int b) {
  	T temp = arr[a];
  	arr[a] = arr[b];
  	arr[b] = temp;
  }
  ```


### 7.1.3 Set

* **特点：无序，唯一**

#### HashSet

* 底层数据结构是**哈希表**(**元素为链表的数组**)

* 哈希表底层依赖两个方法：`hashCode()`和`equals()`

  * **执行顺序：**

    首先比较哈希值是否相同：

            相同：继续执行`equals()`方法
        
                        返回true：元素重复了，不添加
        
                        返回false：直接把元素添加到集合
        
            不同：直接把元素添加到集合

* **元素唯一性：由`hashCode()`和`equals()`保证**

#### LinkedHashSet

* 底层数据结构是**链表和哈希表，元素迭代顺序和存入顺序一致**

#### TreeSet

* 底层数据结构是**红黑树(是一个自平衡二叉树)**

  * 元素唯一性通过红黑树存储时确定，相同元素丢弃， **根据比较的返回值是否是0来决定**
  * 元素的顺序通过红黑树存储，并通过中序遍历展示

* **保证元素的排列方式：**

  1. **自然排序(元素具备比较性)**

     让元素所属的类实现**Comparable接口**，写compareTo方法

  2. **比较器排序(集合具备比较性)**

     让集合构造方法接收**Comparator**的**实现类对象**，重写compare方法

     **s1-s2升序，s2-s1降序**

  ```
  TreeSet<Student> ts = new TreeSet<>(new Comparator<Student>() {
  	@Override
  	public int compare(Student o1, Student o2) {
  		int num1 = o2.getAge() - o1.getAge();
  		int num2 = num1 == 0 ? o1.getName().compareTo(o2.getName()) : num1;
  		return num2;
  	}
  });
  ```


## 7.2 Map

* **将键映射到值的对象**。一个映射不能包含重复的键，每个键最多只能映射到一个值（**键唯一，值可重复**）

* **常用方法**

  * **添加(put)**
    * `V put(K key,V value)` 
      1. 如果键是第一次存储，就直接存储元素，**返回以前的值**null
      2. 如果键不是第一次存储，就替换掉以前的值，并**返回以前的值**
  * **删除(clear/remove)**
    * `void clear()`                    删除所有映射关系
    * `V remove(Object key`)     根据键删除键值对元素，返回值
  * **获取(get/keySet/values/entrySet)**
    * `V get(Object key)`              根据键获取值
    * `Set<K> keySet()`                 返回所有键的集合
    * `Collection<V> values()`     返回所有值的集合
    * `Set<Map.Entry<K,V>> entrySet()`    返回映射关系的Set集合
  * **判断(isEmpty/containsKey/containsValue)**
    * `boolean isEmpty()`            是否为空
    * `boolean containsKey(Object key)`   是否包含指定键值
    * `boolean containsValue(Object value)`  是否包含指定值
  * **容量(size)**
    * `int size()`      返回映射中键值对的对数

* **遍历方法**

  * **键找值(keySet()/get())**

    ```java
    Set<String> set = hm.keySet();
    for(String key:set) {
    	System.out.println(key+"---"+hm.get(key));
    }
    //或
    Iterator<String> it = set.iterator();
    while(it.hasNext()) {
    	String key  = it.next();
    	String value = hm.get(key);
    	System.out.println(key+"---"+value);
    }
    ```

  * **键值对(entrySet()/getKey()/getValue()**

    ```java
    Set<Map.Entry<String, String>> set = hm.entrySet();
    for(Map.Entry<String, String> entry:set) {
    	System.out.println(entry);//1=hello
    	System.out.println(entry.getKey()+"---"+entry.getValue());//1---hello
    }
    ```

  * **values方法(存储Map中值得Collection集合)**

    ```
    Collection<String> values = hm.values();
    for(String value:values){
        System.out.println(value);
    }
    ```

### 7.2.1 HashMap

* 底层是哈希表，迭代出的元素顺序和存入顺序不一致
* HashMap和Hashtable区别
  * HashMap：线程不安全，效率高，允许null键和null值
  * Hashtable：线程安全，效率低，不允许null键和null值

### 7.2.2 LinkedHashMap

* 底层是链表和哈希表，迭代出的元素顺序和存入顺序**一致**

### 7.2.3 TreeMap

* **底层是红黑树(自平衡二叉树)**

* 保证元素的排列方式：

  1. **自然排序(元素具备比较性)**

     让元素所属的类实现**Comparable接口**，写compareTo方法

  2. **比较器排序(集合具备比较性)**

     让集合构造方法接收**Comparator**的**实现类对象**，重写compare方法

     **s1-s2升序，s2-s1降序**

## 7.3 Collections

* 针对集合进行操作的**工具类**

* **【面试】Collection和Collections的区别**
  * Collection 是单列集合的顶层接口，有两个子接口List和Set
  * B:Collections 是针对集合进行操作的工具类，可以对集合进行排序和查找等

* **常见方法**
  * `public static <T> void sort(List<T> list)` **默认自然排序**
  * `public static <T> int binarySearch(List<?> list,T key)` 
  * `public static <T> T max(Collection<?> coll)` 
  * `public static void reverse(List<?> list)` 
  * `public static void shuffle(List<?> list)`  **洗牌，打乱**

* 不用Vector可以用Collections提供的静态方法**(Collection.synchronizedCollection/List/Map/Set)**

  ```
  List<String> list = Collections.synchronizedList(new ArrayList<>());
  ```


## 7.4 斗地主洗牌发牌看牌

```
public static void main(String[] args) {
	/*
	 * HashMap，键为0~53，值为花色数字，按花色和数字从小到大存入
	 * ArrayList，值为0~53
	 */
	String[] colors = { "♦", "♣", "♥", "♠" };
	String[] numbers = { "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2" };
	HashMap<Integer, String> hm = new HashMap<>();
	ArrayList<Integer> al = new ArrayList<>();
	for (int i = 0, index = 0; i < numbers.length; i++) {
		for (int j = 0; j < colors.length; j++) {
			hm.put(index, colors[j].concat(numbers[i]));
			al.add(index);
			index++;
		}
	}
	hm.put(52, "小王");
	hm.put(53, "大王");
	al.add(52);
	al.add(53);
	Collections.shuffle(al);//洗牌，将ArrayList值打乱
	
	/*
	 * 发牌，发的是打乱的ArrayList的值，存入TreeSet中以便直接排好序
	 */
	TreeSet<Integer> player1 = new TreeSet<>();
	TreeSet<Integer> player2 = new TreeSet<>();
	TreeSet<Integer> player3 = new TreeSet<>();
	TreeSet<Integer> dipai = new TreeSet<>();
	for (int i = 0; i < al.size(); i++) {
		if (i >= al.size() - 3)
			dipai.add(al.get(i));
		else if (i % 3 == 0)
			player1.add(al.get(i));
		else if (i % 3 == 1)
			player2.add(al.get(i));
		else
			player3.add(al.get(i));
	}
	lookPoker("P1", player1, hm);
	lookPoker("P2", player2, hm);
	lookPoker("P3", player3, hm);
	lookPoker("底牌", dipai, hm);
}

	/*
	 * 看牌，通过遍历排序好的TreeSet，在HashMap中找值
	 */
	public static void lookPoker(String name, TreeSet<Integer> player, 
			HashMap<Integer, String> hm) {
		System.out.print(name + "的牌是：");
		for (Integer key : player) {
			System.out.print(hm.get(key) + " ");
		}
		System.out.println();
	}
```

## 7.5 Properties

* 是一个集合类，**Hashtable的子类** （游戏进度保存加载）

* **特有功能**

  * `Object setProperty(String key,String value)`   添加元素，调用的父类的put方法
  * `String getProperty(String key)`   获取元素
  * `Set<String> stringPropertyNames()`   获取所有键的集合

* **和IO流结合的方法**

  * 把**键值对形式的文本**文件内容**加载**到集合中

                `void load(Reader reader)`
                  
                `void load(InputStream inStream)`

  * 把集合中的数据**存储**到文本文件中

                `void store(Writer writer,String comments)`
                  
                `void store(OutputStream out,String comments)`

  ```
  public static void myLoad() throws IOException {
  	BufferedInputStream bis = new BufferedInputStream(new FileInputStream("prop.txt"));
  	Properties prop = new Properties();
  	prop.load(bis);
  	Set<String> set = prop.stringPropertyNames();
  	for (String key : set) {
  		if (key.equals("lisi")) {
  			prop.setProperty(key, "100");
  			break;
  		}
  	}
  	bis.close();
  }
  
  public static void myStore() throws IOException {
  	Properties prop = new Properties();
  	prop.setProperty("zhangsan", "3");
  	prop.setProperty("lisi", "4");
  	prop.setProperty("wangwu", "5");
  	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("prop.txt"));
  	prop.store(bos, "mystore");
  	bos.close();
  }
  ```

* **利用配置文件限制玩游戏次数**

  ```
  Properties prop = new Properties();
  InputStream is = new BufferedInputStream(new FileInputStream("prop.txt"));
  prop.load(is);
  is.close();
  String s = prop.getProperty("count");
  int count = Integer.parseInt(s);//prop.txt中从0开始
  		
  if(count>=3) {
  	System.out.println("付费！");
  	System.exit(0);
  }else {
  	count++;
  	prop.setProperty("count", String.valueOf(count));
  	OutputStream os = new BufferedOutputStream(new FileOutputStream("prop.txt"));
  	prop.store(os, null);
  	os.close();
  	System.out.println("nihao!");
  }
  ```


## 7.6 总结

* **容器分类**

**|——Collection（单列集合）**

        **|——List（有序,可重复）**
    
                     **|——ArrayList**
    
                            底层数据结构是数组，查询快，增删慢。线程不安全，效率高
    
                     **|——Vector**
    
                            底层数据结构是数组，查询快，增删慢。 线程安全，效率低
    
                     **|——LinkedList**
    
                            底层数据结构是链表，查询慢，增删快。线程不安全，效率高
    
         **|——Set（无序,唯一）**
    
                     **|——HashSet**
    
                            底层数据结构是哈希表。哈希表依赖两个方法`hashCode()`和`equals()`**（唯一性）**
    
                            执行顺序：首先判断`hashCode()`是否相等
    
                                                       是：执行`equals()`方法
    
                                                               返回true：重复，不添加
    
                                                               返回false：添加到集合
    
                                                       否：直接添加到集合
    
                              最终：开发中自动生成`hashCode()`和`equals()`
    
                            **|——LinkedHashSet**
    
                                   底层数据结构是链表（有序，存取顺序）和哈希表（唯一）
    
                     **|——TreeSet**
    
                            底层数据结构是红黑树（是一种自平衡二叉树）
    
                            如何保证元素有序的呢?
    
                                   自然排序(元素具备比较性)——让元素所属类实现Comparable接口 
    
                                   比较器排序(集合具备比较性)——让集合接收一个Comparator的实现类对象
    
                            如何保证元素唯一性的呢?
    
                                   根据比较的返回值是否是0来决定

**|——Map（双列集合）（其数据结构仅仅针对键有效，与值无关）（键唯一，值可重复）**

        **|——HashMap**
    
                 底层数据结构是哈希表。不同步，线程不安全，效率高
    
                 哈希表依赖两个方法`hashCode()`和`equals()`**（唯一性）**
    
                            执行顺序：首先判断`hashCode()`是否相等
    
                                                       是：执行`equals()`方法
    
                                                               返回true：重复，不添加
    
                                                               返回false：添加到集合
    
                                                       否：直接添加到集合
    
                              最终：开发中自动生成`hashCode()`和`equals()`
    
                **|——LinkedHashMap**
    
                       底层数据结构是链表（有序，存取顺序）和哈希表（唯一）
    
        **|——Hashtable**
    
                 底层数据结构是哈希表。同步，线程安全，效率低
    
                 哈希表依赖两个方法`hashCode()`和`equals()`**（唯一性）**
    
                            执行顺序：首先判断`hashCode()`是否相等
    
                                                       是：执行`equals()`方法
    
                                                               返回true：重复，不添加
    
                                                               返回false：添加到集合
    
                                                       否：直接添加到集合
    
                              最终：开发中自动生成`hashCode()`和`equals()`
    
        **|——TreeMap**
    
                   底层数据结构是红黑树（是一种自平衡二叉树）
    
                   如何保证元素有序的呢?
    
                            自然排序(元素具备比较性)——让元素所属类实现Comparable接口 
    
                            比较器排序(集合具备比较性)——让集合接收一个Comparator的实现类对象
    
                    如何保证元素唯一性的呢?
    
                            根据比较的返回值是否是0来决定





# 8 IO流

* 流，代表任何有能力产出数据的数据源对象或有能力接收数据的接收端对象，屏蔽了实际I/O设备处理细节
* **读入、写出**

## 8.1 File

* **FilePath(文件路径)**对这个类描述更确切

* 构造方法

  - `File filePath = new File(String pathname)`   根据路径创建File对象
  - `File filePath = new File(File/String parent,String child)`   根据目录/父File和子文件创建File对象

* 常用方法

  * **创建creatNewFile/mkdir/mkdirs**
    * `boolean creatNewFile()`     **创建文件**，返回创建与否，若存在则不创建返回false
    * `File creatTempFile(String prefix,String suffix)`     **创建临时文件**
    * `boolean mkdir()`     创建**文件路径**，若存在则不创建
    * `boolean mkdirs()`     创建**文件路径，若父路径不存在自动创建出来**
  * **删除delete**
    * `boolean delete()`
      1. 如果创建文件或者文件夹没写盘符路径，默认在项目路径下(相对路径)
      2. 删除一个文件夹，文件夹中不能有内容 
      3. Java中的删除不走回收站
  * **重命名renameTo**
    * `boolean renameTo(File dest)`     **路径相同为改名，路径不同为改名并剪切**
  * **判断**
    * `boolean exists()/isFile()/isDirectory`     **判断是否存在/是文件/是目录**
    * `boolean canRead()/canWrite()/isHidden()`     **判断是否可读/可写/隐藏**
  * **获取**
    * `String getPath()/getAbsolutePath()/getName()`     **获取相对路径/绝对路径/文件名**
    * `long length()/lastModified()`     **获取长度(字节数)/最后一次修改时间(毫秒值)**
  * **高级获取(带参为文件名过滤器，需匿名内部类实现)**
    * `String[] list(FilenameFilter filter)`     获取指定目录下所有文件或文件夹**名称**的**字符串数组**
    * `File[] listFile(FilenameFilter filter)`     获取指定目录下所有文件或文件夹的**File数组**

* **批量修改文件名**

  ```
  File srcFilePath = new File("D:\\lol");
  File[] fileList = srcFilePath.listFiles(new FilenameFilter() {
  	@Override
  	public boolean accept(File dir, String name) {
  		return new File(dir, name).isFile() && name.endsWith(".txt");
  	}
  });
  
  File destFilePath = new File("D:\\lol\\new");
  destFilePath.mkdirs();
  for (File file : fileList) {
  	String newName = "new_".concat(file.getName());
  	File newFile = new File(destFilePath, newName);
  	file.renameTo(newFile);
  }
  ```

## 8.2 递归

* **方法定义中调用方法本身的现象** 

* **注意：**

  1. 要**有出口**，否则就是死递归
  2. **次数不能过多**，否则内存溢出
  3. **构造方法不能**递归使用

* **递归输出指定目录下指定文件的绝对路径**

  ```
  public static void getFile(File srcFilePath) {
  	File[] fileList = srcFilePath.listFiles();
  	for (File file : fileList) {
  		if (file.isDirectory())
  			getFile(file);
  		else {
  			if (file.getName().endsWith(".txt"))
  				System.out.println(file.getAbsolutePath());
  		}
  	}
  }
  ```

* **递归删除指定目录及其包含所有文件、文件夹**

  ```
  public static void deleteDir(File srcFilePath) {
  	File[] fileList = srcFilePath.listFiles();
  	for(File file:fileList) {
  		if(file.isDirectory())
  			deleteDir(file);
  		else
  			file.delete();
  	}
  	srcFilePath.delete();
  }
  ```



## 8.2 InputStream、OutputStream

* **FileInputStream**

  - **操作步骤：**
    1. **创建字节输入流对象**
       - `FileInputStream fis = new FileInputStream(File file/String name)`
    2. **调用`read()`方法**
       - `int read()`   一次读取一个**字节**
       - `int read(byte[] b)`   一次读取一个**字节数组**
    3. **释放资源**(关闭流对象。垃圾回收、通知系统释放跟该文件相关资源)

* **FileOutputStream**

  * **操作步骤**
    1. **创建字节输出流对象(第二个参数可选)**
       * `FileOutputStream fos = new FileOutputStream(File file/String name,boolean append)`
    2. **调用`write()`方法(将字符串转为字节数组，`getBytes()`)**
       * `void write(int b)`     一次写一个**字节**
       * `void write(byte[] bytes)`     一次写一个**字节数组**
       * `void write(byte[] b,int offset,int len)`     一次写一个**字节数组的一部分**
         * `int len`中**len代表实际读取字节的长度**，每次读取替换上次字节数组
    3. **释放资源**(关闭流对象。垃圾回收、通知系统释放跟该文件相关资源)

* 注意**数据的换行**：使用**\n**高级记事本换行但是记事本不换行，需使用**\r\n**

* **计算机是如何识别什么时候该把两个字节转换为一个中文呢?**

  	在计算机中中文的存储分两个字节：

         		第一个字节肯定是负数。
        
         		第二个字节常见的是负数，可能有正数。但是没影响。

* **字节缓冲区流**（java自带的带缓冲区的字节类（称为缓冲区类或高效类） ，用法同上

* **复制文件**

```java
public static void main(String[] args) throws IOException  {
	FileInputStream fis = new FileInputStream("test_1.txt");
	FileOutputStream fos = new FileOutputStream("tes_2.txt");
	//BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test_1.txt"));
	//BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("text_2.txt"));
	int b = 0;
	while((b=fis.read())!=-1) { //bis
		fos.write(b);           //bos
	}
	fis.close();                //bis
	fos.close();                //bos 
}
```

```java
public static void main(String[] args) throws IOException  {
	FileInputStream fis = new FileInputStream("test_1.txt");
	FileOutputStream fos = new FileOutputStream("test_2.txt");
	//BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test_1.txt"));
	//BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("text_2.txt"));
	byte[] bytes = new byte[1024];
	int len =0 ;
	while((len=fis.read(bytes))!=-1) {   //bis
		fos.write(bytes,0,len);          //bos
	}
	fis.close();                         //bis
	fos.close();                         //bos
}
```



## 8.3 Reader、Writer

* **转换流**：为了方便操作中文数据，将字节流转为字符流使用，它也是个字符流

* **字符流 = 字节流 + 编码表**

* **编码表：由字符和对应的数值组成的一张表**

  * Unicode（国际标准码，所有文字都用两个表示。Java就使用的是这个）
  * UTF-8（最多用三个字节表示一个字符，能用一个用一个，然后两个，三个）
  * ASCII（最高位符号位，其余七位为数值位） 
  * GBK（中国的中文编码表升级，融合更多的中文文字符号）
  * GB18030（GBK的取代版本）

* **编码**（看懂的变成看不懂的）String -- byte[]

  **解码**（看不懂的变成看得懂的）byte[] -- String

* **InputStreamReader**

  * 构造方法
    * `InputStreamReader(InputStream is)`:默认编码，GBK
    * `InputStreamReader(InputStream is,String charsetName)`:指定编码
  * 方法
    * `void write(int c)`:写一个字符
    * `void write(char[] chs)`:写一个字符数组
    * `void write(char[] chs,int off,int len)`:写一个字符数组的一部分
    * `void write(String str)`:写一个字符串
    * `void write(String str,int off,int len)`:写一个字符串的一部分

* **OutputStreamWriter**

  * 构造方法
    * `OutputStreamWriter(OutputStream os)`:默认编码，GBK
    * `OutputStreamWriter(OutputStream os,String charsetName)`:指定编码。
  * 方法
    * `int read()`:一次读取一个字符
    * `int read(char[] chs)`:一次读取一个字符数组

  ```
  InputStreamReader isr = new InputStreamReader(new FileInputStream("test_1.txt"));
  OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("test_2.txt"));
  char[] chs = new char[1024];
  int len = 0;
  while ((len = isr.read(chs)) != -1) {
  	osw.write(chs, 0, len);
  }
  isr.close();
  osw.close();
  ```

* **FileReader、FileWriter**继承自**InputStreamReader**、**OutputStreamWriter**，简化编写

* **BufferedReader**

  * `String readLine()`:一次**读取一行字符串**，包含该行内容的字符串，**不包含任何行终止符**，如果已到达流末尾，则返回 null 

* **BufferedWriter**

  * `void newLine()`:写一个**换行符**，根据系统来决定换行符
  * `void write(String line)`:一次写一个字符串



* **【面试】close()和flush()的区别?**
  * close()关闭流对象，但是先刷新一次缓冲区。关闭之后，流对象不可以继续再使用了
  * flush()仅仅刷新缓冲区,刷新之后，流对象还可以继续使用。


## 8.4 总结

1. **复制文本文件的5种方式(采用字符流)**

   ```
   //基本字符流一次读写一个字符
   private static void method1(String srcFilePath, String destFilePath) throws IOException {
   	FileReader fr = new FileReader(srcFilePath);
   	FileWriter fw = new FileWriter(destFilePath);
   	int ch = 0;
   	while ((ch = fr.read()) != -1) {
   		fw.write(ch);
   	}
   	fr.close();
   	fw.close();
   }
   
   //基本字符流一次读写一个字符数组
   private static void method2(String srcFilePath, String destFilePath) throws IOException {
   	FileReader fr = new FileReader(srcFilePath);
   	FileWriter fw = new FileWriter(destFilePath);
   	char[] chs = new char[1024];
   	int len = 0;
   	while((len=fr.read(chs))!=-1) {
   		fw.write(chs, 0, len);
   	}
   	fr.close();
   	fw.close();
   }
   
   //字符缓冲流一次读写一个字符
   private static void method3(String srcFilePath, String destFilePath) throws IOException {
   	BufferedReader br = new BufferedReader(new FileReader(srcFilePath));
   	BufferedWriter bw = new BufferedWriter(new FileWriter(destFilePath));		
   	int ch = 0;
   	while((ch=br.read())!=-1) {
   		bw.write(ch);
   	}		
   	br.close();
   	bw.close();
   }
   
   //字符缓冲流一次读写一个字符数组
   private static void method4(String srcFilePath, String destFilePath) throws IOException {
   	BufferedReader br = new BufferedReader(new FileReader(srcFilePath));
   	BufferedWriter bw = new BufferedWriter(new FileWriter(destFilePath));		
   	char[] chs = new char[1024];
   	int len =0 ;
   	while((len=br.read(chs))!=-1) {
   		bw.write(chs, 0, len);
   	}
   	br.close();
   	bw.close();
   }
   
   //字符缓冲流一次读写一个字符串
   private static void method5(String srcFilePath, String destFilePath) throws IOException {
   	BufferedReader br = new BufferedReader(new FileReader(srcFilePath));
   	BufferedWriter bw = new BufferedWriter(new FileWriter(destFilePath));		
   	String line = null;
   	while((line=br.readLine())!=null) {
   		bw.write(line);
   		bw.newLine();
   		bw.flush();
   	}
   	br.close();
   	bw.close();
   }
   ```

2. **复制图片(二进制流数据) 4种方式(字节流)**

   ```
   //字节流一次读写一个字节
   private static void method1(String srcFilePath, String destFilePath) throws IOException {
   	FileInputStream fis = new FileInputStream(srcFilePath);
   	FileOutputStream fos = new FileOutputStream(destFilePath);
   	int by = 0;
   	while((by=fis.read())!=-1) {
   		fos.write(by);
   	}
   	fis.close();
   	fos.close();
   }
   
   //字节流一次读写一个字节数组
   private static void method2(String srcFilePath, String destFilePath) throws IOException {
   	FileInputStream fis = new FileInputStream(srcFilePath);
   	FileOutputStream fos = new FileOutputStream(destFilePath);
   	byte[] bys = new byte[1024];
   	int len =0;
   	while((len=fis.read(bys))!=-1) {
   		fos.write(bys,0,len);
   	}
   	fis.close();
   	fos.close();
   }
   
   //缓冲字节流一次读取一个字节
   private static void method3(String srcFilePath, String destFilePath) throws IOException {
   	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFilePath));
       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
   	int by = 0;
   	while((by=bis.read())!=-1) {
   		bos.write(by);
   	}
   	bis.close();
   	bos.close();
   }
   
   //缓冲字节流一次读取一个字节数组
   private static void method4(String srcFilePath, String destFilePath) throws IOException {
   	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFilePath));       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
   	byte[] bys = new byte[1024];
   	int len =0;
   	while((len=bis.read(bys))!=-1) {
   		bos.write(bys,0,len);
   	}
   	bis.close();
   	bos.close();
   }
   ```

3. **把集合中的数据存储到文本文件中**

   ```
   ArrayList<Student> arrayList = new ArrayList<>();
   arrayList.add(new Student("zhangsan", 23));
   arrayList.add(new Student("lisi", 24));
   arrayList.add(new Student("wangwu", 25));
   BufferedWriter bw = new BufferedWriter(new FileWriter("collection.txt"));
   for (Student s : arrayList) {
   	bw.write(s.getName() + ":" + s.getAge());
   	bw.newLine();
   	bw.flush();
   }
   bw.close();
   ```

4. **把文本文件中的数据读取到集合并遍历集合**

   ```
   BufferedReader br = new BufferedReader(new FileReader("test_1.txt"));
   ArrayList<String> arrayList = new ArrayList<>();
   String line = null;
   while((line=br.readLine())!=null) {
   	arrayList.add(line);
   }
   for(String s :arrayList) 
   	System.out.println(s);
   ```

5. **在一个存储人名的文件中随机输出一个人名** 

   ```
   ArrayList<String> arrayList = new ArrayList<>();
   BufferedReader br = new BufferedReader(new FileReader("name.txt"));
   String line = null;
   while((line=br.readLine())!=null) {
   	arrayList.add(line);
   }
   br.close();
   Random r = new Random();
   int random = r.nextInt(arrayList.size());
   System.out.println("幸运的猪："+arrayList.get(random));
   ```

6. **复制单级文件夹中指定的文件并修改名称**

   ```
   File srcFilePath = new File("java");
   File destFilePath = new File("copy_jad");
   if(!destFilePath.exists())
   	destFilePath.mkdirs();
   File[] fileList = srcFilePath.listFiles();
   for(File file:fileList) {
   	if(file.isFile()&&file.getName().endsWith(".java")) {
   		String newName = file.getName().replace(".java", ".jad");
   		File newFile = new File(destFilePath,newName);
   		copyFile(file,newFile);
   	}
   }
   ```

7. **复制多级文件夹(整个文件夹复制到目的文件夹目录下**

   ```
   public static void main(String[] args) throws IOException {
   	File srcFilePath = new File("duo_java");
   	File destFilePath = new File("copy_duo_java");
   	copyFloder(srcFilePath, destFilePath);
   }
   
   private static void copyFloder(File srcFilePath, File destFilePath) throws IOException {
   	if(srcFilePath.isDirectory()) {
   		File newFloder = new File(destFilePath,srcFilePath.getName());
   		newFloder.mkdirs();
   		File[] fileList = srcFilePath.listFiles();
   		for(File file:fileList) 
   			copyFloder(file, newFloder);
   	
   	}else {
   		File newFile = new File(destFilePath,srcFilePath.getName());
   		copyFile(srcFilePath,newFile);
   	}
   }
   
   private static void copyFile(File srcFilePath, File destFilePath) throws IOException {
   	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFilePath));
       BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(destFilePath));
   	byte[] bys = new byte[1024];
   	int len = 0;
   	while((len=bis.read(bys))!=-1) {
   		bos.write(bys,0,len);
   	}
   	bis.close();
   	bos.close();
   }
   ```

8. **将一个文件中的字符串排序后写入另一个文件中**

   ```
   BufferedReader br = new BufferedReader(new FileReader("StringDemo.txt"));
   BufferedWriter bw = new BufferedWriter(new FileWriter("newStringDemo.txt"));
   String line = null;
   while((line=br.readLine())!=null) {
   	char[] chs = line.toCharArray();
   	Arrays.sort(chs);
   	bw.write(String.valueOf(chs));//String s = new String(chs);
   	bw.newLine();
   	bw.flush();
   }
   br.close();
   bw.close();
   ```


## 8.5 其他流

### 8.5.1 LineNumberReader

* **LineNumberReader使用**(行号从setLineNumber+1开始)

  ```
  LineNumberReader lnr = new LineNumberReader(new FileReader("name.txt"));
  lnr.setLineNumber(-1);
  String line = null;
  while((line=lnr.readLine())!=null) {
  	System.out.println(lnr.getLineNumber()+":"+line);
  }
  lnr.close();
  ```

### 8.5.2 打印流（printStream、printWriter）

* 字节打印流(printStream)，字符打印流(printWriter)
* 特点：
  1. 只有写数据，没有读数据；只操作目的地,不操作数据源 
  2. 可以操作任意类型的数据
  3. 如果启用了**自动刷新**，在调用`println()`方法的时候，能够换行并刷新
  4. 可以直接操作文件（基本流）（基本上File前缀的都可以，只要构造方法有String和File就支持）

```
BufferedReader br = new BufferedReader(new FileReader("a.txt"));
PrintWriter pw = new PrintWriter(new FileWriter("b.txt"),true);
String line = null;
while((line=br.readLine())!=null) {
	pw.println(line);
}
pw.close();
br.close();
```

### 8.5.3 标准输入输出流(System.in、System.out)

* System类下面有这样的两个字段

  * in 标准输入流
  * out 标准输出流

* 三种键盘录入方式

  * A:main方法的args接收参数

  *  B:System.in通过BufferedReader进行包装

     `BufferedReader br = new BufferedReader(new InputStreamReader(System.in))`

  *  C:Scanner

     `Scanner sc = new Scanner(System.in)`

* 输出语句的原理和如何使用字符流输出数据

  * 原理

    ```
    System.out.println("helloworld");
                 
    PrintStream ps = System.out;
    ps.println("helloworld");   
    ```

  *  把System.out用字符缓冲流包装一下使用

          `BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))`

### 8.5.4 随机访问流(RandomAccessFile)

* 可以按照文件指针的位置写数据和读数据 入流

* 构造方法

  ```
  RandomAccessFile raf = new RandomAccessFile(File file/String name,String mode)//mode可以使rw
  ```

* 案例：

  * 写数据
  * 读数据
  * 获取和改变文件指针的位置

### 8.5.5 合并流(SequenceInputStream)

* 把多个输入流的数据写到一个输出流中。

* 构造方法：

  `SequenceInputStream(InputStream s1, InputStream s2)` 

  `SequenceInputStream(Enumeration<? extends InputStream> e)`

### 8.5.6 序列化流ObjectOutputStream

* 序列化流：把对象按照流的方式写入文本文件或者在网络中传输    对象——流数据

  ```
  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(File file/String name))
  ```

  ```
  ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("oos.txt"));
  Student s = new Student("张三", 33);
  oos.writeObject(s);
  oos.close();
  ```

* 反序列化流：把文本文件中或网络中的流对象数据还原成对象         流数据——对象

  ```
  ObjectInputStream ois = new ObjectInputStream(new FileInputStream("oos.txt"));
  Object o = ois.readObject();
  ois.close();
  System.out.println(o);
  ```

* **实现序列化：**

  * 让被序列化对象所属类实现序列化接口(java.io.**Serializable**)，为标记接口，没有需要重写的方法

    ```
    public class Student implements Serializable{...}
    ```

  * 让对象成员**不被序列化**：用`transient`修饰（最后显示为默认值）

* **注意：**

  * 把数据写到文件后，**再去修改类**会产生问题 （在类文件中，**给出一个固定或自动变化的序列化id值**）

## 8.6 NIO

- **Java7中新加的类**
  - **Path接口**表示路径

  - **Paths类**中`Paths.get()`接收一个或多个字符串，表示路径，**可以不存在**

  - Files类中有静态方法

    - `public static long copy(Path source,OutputStream out)throws IOException`   **复制文件**

      ```
      Files.copy(Paths.get("数据库.txt"), new BufferedOutputStream(new 			                   FileOutputStream("copy数据库.txt")));
      ```





# 9 网络编程

## 9.1 网络编程概述

- **计算机网络**

  是指将地理位置不同的具有独立功能的多台计算机及其外部设备，通过通信线路连接起来，在网络操作系统，网络管理软件及网络通信协议的管理和协调下，实现资源共享和信息传递的计算机系统。

- **网络编程**

  就是用来实现网络互连的不同计算机上运行的程序间可以进行数据交换。

- **网络模型**

  - OSI（Open System Interconnection开放系统互连）参考模型
  - TCP/IP参考模型

  ![](D:\Typora\photo\网络参考模型.png)


## 9.2 网络编程三要素

### 9.2.1 IP地址

- （网络中**计算机唯一标识**，点分十进制）

  - **组成：网络号段+主机号段**

    A类：第一号段为网络号段+后三段的主机号段
    ​	一个网络号：256 * 256 * 256 = 16777216台主机
    B类：前二号段为网络号段+后二段的主机号段
    ​	一个网络号：256 * 256 = 65536台主机
    C类：前三号段为网络号段+后一段的主机号段
    ​	一个网络号：256台主机

  - **分类：**

    A类	1.0.0.1---127.255.255.254		(1)10.X.X.X是私有地址(在互联网上不使用，在局域网络中的地址)

       		  						(2)127.X.X.X是保留地址，用做循环测试用的。

    B类	128.0.0.1---191.255.255.254	172.16.0.0-172.31.255.255是私有地址。169.254.X.X是保留地址。
    C类	192.0.0.1---223.255.255.254	192.168.X.X是私有地址
    D类	224.0.0.1---239.255.255.254 	
    E类	240.0.0.1---247.255.255.254

  - **两个DOS命令：**
    ipconfig 查看本机IP地址
    ​		ping 后面跟IP地址。测试本机与指定的IP地址间的通信是否有问题

  - **特殊的IP地址：**
    127.0.0.1 回环地址(表示本机)
    ​	x.x.x.255 广播地址
    ​	x.x.x.0 网络地址

  - `InetAddress类`**的概述和使用**（方便对IP地址的获取和操作）

    - **如果一个类没有构造方法：**

      A:成员全部是静态的(Math,Arrays,Collections)

      B:单例设计模式(Runtime)

      C:类中有静态方法返回该类的对象(InetAddress)

  - **InetAddress/getByName/getHostName/getHostAddress使用**

    `public static InetAddress getByName(String host)`:根据**主机名**或者**IP地址**的字符串得到IP地址**对象**

    `public String getHostName()`：返回主机名

    `public String getHostAddress()`:返回IP地址字符串

    ```
    InetAddress address = InetAddress.getByName("Conanan");//或"192.168.1.6"
    		
    String name = address.getHostName();
    String addr = address.getHostAddress();
    System.out.println(name+"---"+addr);
    ```



### 9.2.2 端口号

* 正在运行的**程序的标识**

- **有效端口**：0~65535，其中0~1024系统使用或保留端口。

- **物理端口**：网卡口

- **逻辑端口**：我们指的就是逻辑端口
  1. 每个网络程序都会至少有一个逻辑端口
  2. 用于标识进程的逻辑地址，不同进程的标识
  3. 有效端口：0~65535，其中0~1024系统使用或保留端口。



### 9.2.3 传输协议

* 通信的规则

- **UDP**（将数据源和目的封装成数据包中，不需要建立连接；每个数据报的大小在限制在64k；因无连接，是不可靠协议；不需要建立连接，速度快）（聊天留言，在线视频，视频会议，发短信，邮局包裹）
  1. 把数据打包
  2. 数据有限制
  3. 不建立连接
  4. 速度快
  5. 不可靠
- **TCP**（建立连接，形成传输数据的通道；在连接中进行大数据量传输；通过三次握手完成连接，是可靠协议；必须建立连接，效率会稍低）（下载，打电话，QQ聊天(你在线吗,在线,就回应下,就开始聊天了)
  1. 建立连接通道
  2. 数据无限制
  3. 速度慢
  4. 可靠

### 9.2.4 Socket

* 网络上具有唯一标识的**IP地址**和**端口号**组合在一起才能构成唯一能识别的标识符**套接字**）

- **Socket原理机制**
  - 通信的两端都有Socket
  - 网络通信其实就是Socket间的通信
  - 数据在两个Socket间通过IO传输

## 9.3 发送接收数据

### 9.3.1 UDP(DatagramSocket、Datagrampacket)

* **UDP协议接收数据**

  1. 创建接收端Socket对象（`DatagramSocket`类）
  2. 创建一个数据包(接收容器)（`DatagramPacket`类）
  3. 调用Socket对象的接收方法接收数据（`ds.receive(dp)`）
  4. 解析数据包，并显示在控制台
  5. 释放资源(接收端应该一直开着)

  ```
  DatagramSocket ds = new DatagramSocket(10086);
  
  byte[] bys = new byte[1024];
  DatagramPacket dp = new DatagramPacket(bys, bys.length);
  
  ds.receive(dp);
  
  String ip = dp.getAddress().getHostAddress();
  String msg = new String(dp.getData(), 0, dp.getLength());
  System.out.println(ip+":"+msg);
  
  //ds.close();//接收端应该一直开着
  ```

* **UDP协议发送数据**

  1. 创建发送端Socket对象（`DatagramSocket`类）
  2. 创建数据，并把数据打包（`DatagramPacket`类）
  3. 调用Socket对象的发送方法发送数据包（`ds.send(dp)`）
  4. 释放资源

  ```
  DatagramSocket ds = new DatagramSocket();
  
  byte[] bys = "helloworld".getBytes();
  InetAddress address = InetAddress.getByName("Conanan");
  int port = 10086;
  DatagramPacket dp = new DatagramPacket(bys, bys.length, address, port);
  
  ds.send(dp);
  ds.close();
  ```

* **多线程实现聊天室**

  ```
  //主线程
  public class MainDemo {
  	public static void main(String[] args) throws IOException {
  		DatagramSocket receiveSocket = new DatagramSocket(10086);
  		DatagramSocket sendSocket = new DatagramSocket(10000);
  
  		ReceiveThread rt = new ReceiveThread(receiveSocket);
  		SendThread st = new SendThread(sendSocket);
  		Thread t1 = new Thread(rt);
  		Thread t2 = new Thread(st);
  		t1.start();
  		t2.start();
  		......
  }
  //接收端
  public class ReceiveThread implements Runnable {
  	private DatagramSocket ds;
  	public ReceiveThread(DatagramSocket ds) {
  		this.ds = ds;
  	}
  	@Override
  	public void run() {
  		while (true) {
  			byte[] bys = new byte[1024];
  			DatagramPacket dp = new DatagramPacket(bys, bys.length);
  			try {
  				ds.receive(dp);
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
  			String msg = new String(dp.getData(), 0, dp.getLength());
  			System.out.println("ip:"+dp.getAddress()+",port:"+dp.getPort()+",msg:"+msg);
  
  //发送端
  public class SendThread implements Runnable {
  	private DatagramSocket ds;
  	public SendThread(DatagramSocket ds) {
  		this.ds = ds;
  	}
  	@Override
  	public void run() {
  		Scanner sc = new Scanner(System.in);
  		String str = sc.nextLine();
  		while (!str.equals(null)) {
  			if (str.equals("886")) {
  				ds.close();
  				break;
  			} else {
  				byte[] bys = str.getBytes();
  				InetAddress address = null;
  				try {
  					address = InetAddress.getByName("Conanan");
  					DatagramPacket dp=new DatagramPacket(bys, bys.length, address, 10086);
  					ds.send(dp);
  					str = sc.nextLine();
  				} catch (IOException e) {
  					e.printStackTrace();
  
  ```

### 9.3.2 TCP(Socket、ServerSocket)

* **服务器端接收数据(ServerSocket)**

  1. 创建TCP服务器端的Socket对象
  2. 监听客户端连接
  3. 获取**通道**的输入流，读取数据
  4. 释放资源

  ```
  ServerSocket ss = new ServerSocket(60001);
  		
  Socket s = ss.accept();
  		
  InputStream is = s.getInputStream();
  byte[] bys = new byte[1024];
  int len = is.read(bys);
  System.out.println(new String(bys,0,len));
  		
  s.close();
  ```

* **客户端发送数据(Socket)**

  1. 创建TCP客户端的Socket对象
  2. 获取**通道**的输出流，写数据
  3. 释放资源

  ```
  Socket s = new Socket("192.168.81.1", 60001);
  		
  OutputStream os = s.getOutputStream();
  os.write("hello java world".getBytes());
  		
  s.close();
  ```

#### 9.3.2.1 客户端键盘录入服务器写到文本文件并从通道有反馈

```
//Server
ServerSocket ss = new ServerSocket(6789);
Socket s = ss.accept();

BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
BufferedWriter bw = new BufferedWriter(new FileWriter("test1.txt"));
		
String line = null;
while((line=br.readLine())!=null) {
	bw.write(line);
	bw.newLine();
	bw.flush();
}
	
BufferedWriter buw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
buw.write("写入完毕");
buw.newLine();
buw.flush();
		
s.close();
bw.close();

//Client
Socket s = new Socket("192.168.81.1", 6789);

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

String line = null;
while ((line = br.readLine()) != null) {
	if (line.equals("over"))
		break;
	bw.write(line);
	bw.newLine();
	bw.flush();
}

s.shutdownOutput();//标记，禁用此套接字的输出流

BufferedReader bur = new BufferedReader(new InputStreamReader(s.getInputStream()));
String msg = bur.readLine();
System.out.println(msg);

s.close();
```

#### 9.3.2.2 客户端读取文本文件服务器控制台输出

```java
//ServerSocket
ServerSocket ss = new ServerSocket(8888);
Socket s = ss.accept();

BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

String line = null;
while ((line = br.readLine()) != null) {
	bw.write(line);
	bw.newLine();
	bw.flush();
}
s.close();

//Socket
Socket s = new Socket(InetAddress.getByName("Conanan").getHostAddress(), 8888);

BufferedReader br = new BufferedReader(new FileReader("test.java"));
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

String line = null;
while ((line = br.readLine()) != null) {
	bw.write(line);
	bw.newLine();
	bw.flush();
}
br.close();
s.close();
```

#### 9.3.2.3 上传文本文件带反馈

```
//Server
ServerSocket ss = new ServerSocket(8888);
Socket s = ss.accept();

BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
BufferedWriter bw = new BufferedWriter(new FileWriter("上传文件.java"));

String line = null;
while ((line = br.readLine()) != null) {
	bw.write(line);
	bw.newLine();
	bw.flush();
}

BufferedWriter buw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
buw.write("上传成功！");
buw.newLine();
buw.flush();

bw.close();
s.close();

//Client
Socket s = new Socket("192.168.81.1", 8888);

BufferedReader br = new BufferedReader(new FileReader("test.java"));
BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

String line = null;
while ((line = br.readLine()) != null) {
	bw.write(line);
	bw.newLine();
	bw.flush();
}

s.shutdownOutput();
BufferedReader bur = new BufferedReader(new InputStreamReader(s.getInputStream()));
String msg = bur.readLine();
System.out.println(msg);

s.close();
br.close();
```

#### 9.3.2.4 上传其他文件带反馈

```
//Server
ServerSocket ss = new ServerSocket(8888);
Socket s = ss.accept();

BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("上传图片.bmp"));

byte[] bys = new byte[1024];
int len = 0;
while ((len = bis.read(bys)) != -1) {
	bos.write(bys, 0, len);
	bos.flush();
}

BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
bw.write("图片上传完毕");
bw.newLine();
bw.flush();

bos.close();
s.close();

//Client
Socket s = new Socket("192.168.81.1", 8888);
BufferedInputStream bis = new BufferedInputStream(new FileInputStream("test.bmp"));
BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());

byte[] bys = new byte[1024];
int len = 0;
while ((len = bis.read(bys)) != -1) {
	bos.write(bys, 0, len);
	bos.flush();
}

s.shutdownOutput();

BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
String msg = br.readLine();
System.out.println(msg);

bis.close();
s.close();
```

#### 9.3.2.5 多客户上传文件(多线程)

* **需要多线程修改服务器中复制文件部分**

```
//Server
ServerSocket ss = new ServerSocket(8888);
while (true) {
	Socket s = ss.accept();
	new Thread(new UseServerThread(s)).start();
}

//UseServerThread
public class UseServerThread implements Runnable {
	private Socket s;
	public UseServerThread(Socket s) {
		this.s = s;
	}
	@Override
	public void run() {
		try {
			BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
			String newName = System.currentTimeMillis() + ".bmp";
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(newName));

			byte[] bys = new byte[1024];
			int len = 0;
			while ((len = bis.read(bys)) != -1) {
				bos.write(bys, 0, len);
				bos.flush();
			}

		   BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			bw.write("上传完毕");
			bw.newLine();
			bw.flush();

			bos.close();
			s.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

//Client
Socket s = new Socket("192.168.81.1", 8888);
BufferedInputStream bis = new BufferedInputStream(new FileInputStream("要上传的文件.txt"));
BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());

byte[] bys = new byte[1024];
int len = 0;
while ((len = bis.read(bys)) != -1) {
	bos.write(bys, 0, len);
	bos.flush();
}

s.shutdownOutput();
BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
String msg = br.readLine();
System.out.println(msg);

bis.close();
s.close();
```





# 10 Java其他技术

## 10.1 类加载器

* 当程序要使用某个类时，如果该类还未被加载到内存中，则系统会通过加载，连接，初始化三步来实现对这个类进行初始化 

  1. **加载** 
     - 就是指将class文件读入内存，并为之创建一个Class对象
     - 任何类被使用时系统都会建立一个Class对象。
  2. **连接**
     - 验证 是否有正确的内部结构，并和其他类协调一致
     - 准备 负责为类的静态成员分配内存，并设置默认初始化值
     - 解析 将类的二进制数据中的符号引用替换为直接引用
  3. **初始化**
     - 就是我们以前讲过的初始化步骤

* **类初始化时机**

  * 创建类的实例
  * 访问类的静态变量，或者为静态变量赋值
  * 调用类的静态方法
  * 使用反射方式来强制创建某个类或接口对应的`java.lang.Class`对象
  * 初始化某个类的子类
  * 直接使用java.exe命令来运行某个主类

* **类加载器**（负责将**.class文件加载**到内存中，并为之**生成**对应的**Class对象**）

  * Bootstrap ClassLoader**根类加载器（引导类加载器）**

    负责**Java核心类的加载**比如System,String等。在JDK中JRE的lib目录下rt.jar文件中

  * Extension ClassLoader**扩展类加载器**

    负责**JRE的扩展目录中jar包**的加载。在JDK中JRE的lib目录下ext目录

  * System(Application) ClassLoader**系统类加载器**

    负责在**JVM启动时加载来自java命令的class文件**，以及`classpath`环境变量所指定的jar包和类路径

* **<span style="background-color:yellow">类加载器的方法</span>**

  * <span style="background-color:yellow">**获得类加载器：clazz.getClassLoader()**</span>

    * **获取classes(out/src)下的任何URL资源**：classLoader.<span style="background-color:yellow">**getResource(name)**</span>

      * name是资源路径，<span style="background-color:yellow">**相对于类加载器的路径**</span>(使用的那个类的类加载器)，<span style="background-color:yellow">**若带/则为相对classes**</span>

      ```java
      URL url = clazz.getResource("test.txt")；
      String path = url.getPath();
      ```

      * getResourceAsStream就是上面的进行了封装简化后的版本

## 10.2 反射(java.lang.reflect)

* JAVA反射机制是在**运行状态中**，对于任意一个**类**，都能够**知道**这个类的**所有属性和方法**；对于任意一个**对象**，都能够**调用**它的任意一个**属性和方法**；**能够分析类能力的程序称为反射** 

* **原理（************）：**

  1. **将java文件保存到本地硬盘**
  2. **编译java文件，生成class文件**
  3. **jvm通过类加载器将class文件加载到内存中，用Class类表示（java中万事万物皆对象）**
  4. **得到了这个类之后，就可以得到class文件里面的所有内容**

* **获取字节码文件Class类型对象**(因为解剖使用的就是Class类中的方法)

  1. `Calss c2 = Student.class`通过**类名**得到
  2. `Class c1 = stu.getClass()`通过**对象**得到
  3. `Class c3 = Class.forName(String className)   `**字符串配置到配置文件中,优先使用**

* **通过反射分析类的能力**

  * 包括有1.**域(成员变量)**；2.**方法**；3.**构造器**

    * **不带Declare**返回类提供的**public**域、方法和构造器的**数组**，包括**超类的共有成员**

    * **带Declare**返回类提供的**全部**域、方法和构造器，**包括私有和保护成员**，但不包括超类的成员

      操作私有成员时**setAccessible(flag)**中flag设置为true

    ```java
    //若类中没有域(成员变量)或者Class对象描述的是基本类型或数组类型，则返回一个长度为0的数组
    Field[] get(Declare)Fields()
    Field get(Declare)Field(String fieldName)
    
    Method[] get(Declare)Methods()
    
    Constructor[] get(Declare)Construcotrs()
    Constructor<T> get(Declare)Construcotr(Class<?>... parameterTypes)//返回一个构造器
    
    setAccessible(boolean b)//为反射对象(1,2,3)设置可访问标志，true为屏蔽java语言访问检查
    ```

    ```java
    // 单个构造,无参构造可不带.class;传递参数类型
    Constructor con = c.getConstructor(String.class, int.class, String.class);
    Object obj = con.newInstance("zhangsan", 33, "beijing");
     
    // 单个域、属性
    Field field = c.getDeclaredField("name");
    field.setAccessible(true);
    field.set(obj, "lisi");
    Object objField = field.get(obj);
    System.out.println(objField);
    
    // 单个方法,不带参可不写.class;传递参数类型
    Method method = c.getDeclaredMethod("add", int.class, int.class);
    Object objMethod = method.invoke(obj, 10, 20);
    //操作静态方法时，第一个参数为null，不需要实例
    //Object objMethod = method.invoke(null, 10, 20);
    System.out.println(objMethod);
    ```


### 10.2.1 通过配置文件运行类中方法

```java
//class.txt
ClassName:cn.itcast.通过反射运行配置文件内容.Student
MethodName:love

//1.Student.java;		2.Teacher.java		3.Worker.java
public class Student{
    public void love("学生爱Java");//"老师爱C++"	"工人爱啥"
}

//main
public static void main(String[] args) throws Exception{
    //之前的方法调用谁的方法创建谁的对象，属于硬编码
    
    //通过Properties集合来加载class.txt配置文件
    Properties prop = new Properties();
    FileReader fr = new FileReader("class.txt");
    prop.load(fr);
    fr.close();
    
    //利用反射获取键值对数据
    String className = prop.getProperty("ClassName");
    String MethodName = prop.getProperty("MethodName");
    Class c = Class.forName(className);
    Constructor con = c.getConstructor();
    Object obj = con.newInstance():
    Method method = c.getMethod("love");
    method.invoke(obj);
    
}
```

### 10.2.2 通过反射越过泛型检查

* ArrayList`<Integer>`对象，添加一个字符串数据 
* **泛型机制是给编译器看的，运行时没有**。通过看add底层代码发现传入的是`<E>`是Object型

```java
ArrayList<Integer> arrayList = new ArrayList<>();
arrayList.add(10);
		
Class c = arrayList.getClass();//获取对象所属类的字节码文件对象
Method method = c.getMethod("add", Object.class);
method.invoke(arrayList, "hello");
```

### 10.2.3 通过反射给任意的一个对象的任意的属性赋值为指定的值

```java
public void setProperty(Object obj, String propertyName, Object value) throws Exception{
	Class c= obj.getClass();
	Field field = c.getDeclaredField(propertyName);
	field.setAccessible(true);
	field.set(obj, value);
}

public static void main(String[] args) throws Exception {
	Tool t = new Tool();
	Student s = new Student();
	t.setProperty(s, "name", "张三");
	t.setProperty(s, "age", 10);
	s.show();
}
```

## 10.3 动态代理(重点)

* **代理**：本来应该自己做的事情，却请了别人来做。**代理对象和对象需实现同一接口(有同样的方法)**
* **动态代理**：**程序运行过程**，在**内存中**动态的为目标对象**创建**一个虚拟的代理对象

  * 在Java中`java.lang.reflect`包下提供了一个`Proxy`类和一个`InvocationHandler`接口，通过使用这个类和接口就可以生成动态代理对象。JDK提供的代理**只能针对接口做代理**，我们有更强大的代理**cglib**

  * **创建指定接口的代理类对象实例**（类加载器，Class对象数组，调用处理器）

    ```java
    static Object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHander hander)
    //其中loader是与目标对象相同的类加载器
    //interfaces是接口的字节码对象数组 new Class<?>[]{interface.class}
    ```

  * 其中第三个参数为**调用处理器**，是实现了`InvocationHandler`接口的类对象，重写invoke方法


```java
public static void main(String[] args) {
  public final Target target = new Target();

    //动态创建代理对象
  TargetInterface proxy = (TargetInterface) Proxy.newProxyInstance(
      target.getClass().getClassLoader(), 
      target.getClass().getInterfaces(), 
      new InvocationHandler() {
            @Override
            //被执行几次?---看代理对象调用方法几次;   代理对象调用接口相应方法 都是调用invoke
            /** proxy:是代理对象
			  * method:代表的是目标方法的字节码对象
			  * args:代表是调用目标方法时参数
			  */
       public Object invoke(Object proxy,Method method,Object[] args) throws Throwable{
              //反射知识点
              Object invoke = method.invoke(target, args);//目标对象的相应方法
              //retrun返回的值给代理对象
              return invoke;
            }
        }
  );

  proxy.method1();//调用invoke---Method：目标对象的method1方法  args：null  返回值null
  String method2 = proxy.method2();//调用invoke---Method:目标对象的method2方法 
}
```



## 10.4 注解(annotation)

* 注解就是符合一定格式的语法 @xxxx

* 区别：

  * 注释：在阅读程序时更清楚----给程序员看的
  * 注解：给jvm看的，**给机器看的**

* 目前而言最主流的应用：**代替配置文件**

  * 配置文件与注解开发的优缺点：
    * 注解优点：**开发效率高、成本低**
    * 注解缺点：**耦合性大**、并且**不利于后期维护**

* JDK5的注解

  * @Override：重写；帮助开发人员检查是否正确覆盖父类的方法，其实是给编译器看的
  * @SuppressWarnings({数组})：压制警告；不让弹出黄线，变量、方法、类上都可以使用
  * @Deprecated：标注过时
    * 发现的问题：不同的注解只能在不同的位置使用(方法上、字段上、类上)

* 自定义注解（了解，在框架中会使用即可）

  1. 编写一个注解：

     1. 关键字：@interface

     2. 注解的属性：

        语法：返回值 名称();

        注意：如果属性的名字是value，并且注解的属性只有一个，那么在使用注解时可以省略value

     ```java
     @Target({ElementType.METHOD,ElementType.TYPE})
     @Retention(RetentionPolicy.RUNTIME)
     public @interface MyAnno {
     	//注解的属性
     	String name();
     	int age() default 28;	
     }
     ```

  2. 解析使用了注解的类

     - 元注解：代表修饰注解的注解，作用：限制定义的注解的特性
     - @Retention
       - SOURCE:	注解只在源码级别可见
         CLASS：注解在字节码文件级别和源码级别都可见
         RUNTIME：注解在整个运行阶段都可见
     - @Target：代表注解修饰的范围：类上使用，方法上使用，字段上使用
       - FIELD:字段上可用此注解
         METHOD:方法上可以用此注解
         TYPE:类/接口上可以使用此注解

     ```java
     //解析show方法上面的@MyAnno;直接的目的是 获得show方法上的@MyAnno中的参数
     
     //获得show方法的字节码对象
     Class clazz = MyAnnoTest.class;
     Method method = clazz.getMethod("show", String.class);
     //获得show方法上的@MyAnno
     MyAnno annotation = method.getAnnotation(MyAnno.class);
     //获得@MyAnno上的属性值
     System.out.println(annotation.name());//zhangsan
     System.out.println(annotation.age());//28
     //根据业务需求写逻辑代码
     ```

* 模拟@Test

      ```java
  //获得TestDemo
  Class clazz = TestDemo.class;
  //获得所有的方法
  Method[] methods = clazz.getMethods();
  if(methods!=null){
  ​    //获得注解使用了@MyTest的方法
  ​    for(Method method:methods){
  ​        //判断该方法是否使用了@MyTest注解
  ​        boolean annotationPresent = method.isAnnotationPresent(MyTest.class);
  ​        if(annotationPresent){
  ​            //该方法使用MyTest注解了
  ​            method.invoke(clazz.newInstance(), null);
  ​        }
  ​    }
  }
  ​    ```


# 11 面向对象思想设计原则及设计模式

## 11.1 设计原则

## 11.2 设计模式

### 11.2.1 **简单工厂模式（静态工厂方法模式）**

* 它定义一个**具体的工厂类**负责创建一些类的实例

* 优点：客户端不需要负责对象的创建，从而明确了各个类的职责
* 缺点：这个静态工厂类负责所有对象的创建，如果有新的对象增加，或者某些对象的创建方式不同，就需要不断的修改工厂类，不利于后期的维护

```
public abstract class Animal {
	public abstract void eat();
}
public class Dog extends Animal {
	@Override
	public void eat() {
		System.out.println("狗吃肉");
	}
}
public class AnimalFactory {
	private AnimalFactory() {};
	public static Animal creatAnimal(String type) {
		if(type.equals("dog")) {
			return new Dog();
		}
		else if(type.equals("cat")) {
			return new Cat();
		}
		return null;
	}
}
	//静态方法，类名调用
```
### 11.2.2 **工厂方法模式**

* **抽象工厂类负责**定义创建对象的**接口**，具体对象的创建工作由继承抽象工厂的具体类实现

* **优点**：客户端不需要负责对象的创建，从而明确了各个类的职责，如果有新的对象增加，只需要增加一个具体的类和具体的工厂类即可，不影响已有的代码，后期维护容易，增强了系统的扩展性
* **缺点**：需要额外的编写代码，增加了工作量

```
public abstract class Animal {
	public abstract void eat();
}
public class Dog extends Animal {
	@Override
	public void eat() {
		System.out.println("狗吃肉");
	}
}
public interface Factory {
	public abstract Animal creatAnimal();
}
public class DogFactory implements Factory {
	@Override
	public Animal creatAnimal() {
		return new Dog();
	}
}

public static void main(String[] args) {
	Factory f = new DogFactory();
	Animal a = f.creatAnimal();
	a.eat();
}
```
### 11.2.3 **单例设计模式**

* 确保类在内存中**只有一个对象**，该实例必须**自动创建**，并且**对外提供**

  * **优点**：在系统内存中只存在一个对象，因此可以节约系统资源，对于一些需要频繁创建和销毁的对象单例模式无疑可以提高系统的性能

  * **缺点**：

    	没有抽象层，因此扩展很难；

    	职责过重，在一定程序上违背了单一职责

  * **实现**：

    1. 构造方法私有
    2. 在成员位置自己创建一个对象，加**私有**
    3. 通过一个公共的方法提供访问，加**静态**

    * **饿汉式**（类一加载就创建对象）（开发用，是不会出问题的单例模式）

      ```
      private static Student s = new Student();
      private Student() {}
      public static Student getStudent() {
      	return s;
      }
      ```

    * **懒汉式**（用的时候才创建）（面试用，可能会出问题的单例模式）

      ```
      private static Teacher t = null;
      private Teacher() {}
      public static Teacher getTeacher() {
      	if(t==null) {
      		t=new Teacher();
      	}
      	return t;
      }
      ```

  * **Runtime类（饿汉式）**每个Java应用程序都有一个Runtime类实例，使应用程序能够与其运行的环境相连

    `public Process exec(String command) throws IOException`在单独的进程中执行指定的字符串命令

    ```
    		Runtime r = Runtime.getRuntime();
    		r.exec("calc");
    		r.exec("notepad");
    		r.exec("shutdown -s -t 10000");//10000秒后关机
    		r.exec("shutdown -a");//取消关机
    ```

### 11.2.4 模板设计模式

* 模版设计模式就是定义一个算法的骨架，而将具体的算法延迟到子类中来实现 
* **优点：**在定义算法骨架的同时，可以很灵活的实现具体的算法，满足用户灵活多变的需求
* **缺点：**如果算法骨架有修改的话，则需要修改抽象类 
* 例子：计算for循环需要时间、复制一个视频需要的时间
  * 解决：
    * 定义抽象类，其中有计算耗时的非抽象方法，方法中调用一个抽象方法
    * 需要测试的代码通过实现该抽象类并重写抽象方法
    * 在测试类中创建该实现类并调用计算耗时的方法

### 11.2.5 装饰设计模式

* 装饰模式就是使用被装饰类的一个子类的实例，在客户端将这个子类的实例交给装饰类。是继承的替代方案 
* 优点：使用装饰模式，可以提供比继承更灵活的扩展对象的功能，它可以动态的添加对象的功能，并且可以随意的组合这些功能 
* 缺点：正因为可以随意组合，所以就可能出现一些不合理的逻辑 
* 例子：IO流中
  * `BufferedReader br = new BufferedReader(new InputStreamReader(System.in))`

## 面试题

* **单例模式的思想是什么？（保证类在内存中只有一个对象）写一个代码体现（写懒汉式）**

  * **懒加载（延迟加载）**
  * **线程安全问题**
    - 是否多线程环境	是
    	 是否有共享数据	是
    	是否有多条语句操作共享数据	是

  ```java
  private Teacher() {}
  private static Teacher t = null;
  public synchronized static Teacher getTeacher() {
      if(t==null) {
          t=new Teacher();
      }
      return t;
  }
  ```




# 12 测试

## 12.1 Junit

* 单元测试：测试对象是 是一个类中的**方法**

  junit不是javase的一部分，想要使用导入jar包

  * IDEA中要导入 **junit-4.12.jar** 和 **hamcrest-core-1.3.jar**

* 单元测试方法时候，方法命名规则 **public void 方法名() {}**

  ```
  //使用注解方式运行测试方法
  @Test
  public void testAdd1() {
  	TestJunit test01 = new TestJunit();
  	test01.testAdd(2, 3);
  }
  ```

  选中方法名称，右键运行 点击run as --- junit  test，要运行类中的多个测试方法，点击类中的其他位置

  当出现绿色条，表示方法测试通过；当出现了红棕色条，表示方法测试不通过

  - @Ignore ：表示这个方法不进行单元测试
  - @Before: 在**每个**方法之前运行
  - @After：在**每个**方法之后运行

* 断言（了解）

  * Assert.assertEquals("测试期望的值", "方法运行的实际的值")