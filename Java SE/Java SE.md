[TOC]

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

  * ==**引用数据类型**：**字符串** 、数组 、类 、 接口 、Lambda==

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

  * ==**取余和取模**（C、C++、java、JavaScript中%是取余，Python中%是取模），只对于整数有意义==

    ==区别在于第一步的商**趋于0(取余)**、**趋于负无穷(取模)**，**取余和取模同符号数结果相同**==

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

* **==可变参数==，**写方法中参数个数不明确时可用（同js中arguments类似），**底层为数组**

  * 注意：一个方法**只能有一个**可变参数，且需放在参数列表的**末尾**

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

    * 在switch语句中，如果case的后面**不写break**，将出现穿透现象，也就是**不会再判断下一个case**的值，直接向后运行，直到**遇到break**，或者**整体switch结束**。

  * **case后面的值不可重复，否则编译失败**

  * switch语句格式可以很灵活，**前后顺序可以颠倒**，**break语句也可以省略（例如，季节）**

* **循环语句**

  * for：==无论布尔表达式是否满足，**步进表达式都会执行**==（三角形图形，上右，下左）

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

  * **数组**： 存储数据**长度固定**的容器，多个数据的**数据类型要一致（存在自动类型转换）**。是**引用数据类型**

    * 注意：==数组**对象**有**定长**特性，**长度一旦指定，不可更改**，**不要把变量名看成数组**==

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
    int[] arr = new int[3];
    int[][] arr = new int[m][];
    int[][] arr = new int[m][n];
    ```

  * 静态初始化（指定内容）

    * 也有元素默认值，但是之后又把大括号内容赋值给数组了

    ```java
    int[] arr = new int[]{1,2,3};
    int[] arr = {1,2,3};//上述的简化版
    int[][] arr = new int[][]{{...},{...}};
    int[][] arr = {{...},{...}};
    ```


* ==**【注意】字符串有length()方法，数组有length属性**==

* 数组内存图

  ![](images\04-两个引用指向同一个数组的内存图.png)

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

  ![](images\02-两个对象使用同一个方法的内存图.png)

* **两个引用指向同一对象**的内存图

  ![](images\03-两个引用指向同一个对象的内存图.png)

* **引用类型作为参数传递到方法中，传递的是地址值**

  ![](images\04-使用对象类型作为方法的参数.png)

* **使用引用类型作为方法的返回值，返回值就是对象的地址值**

  ![](images\05-使用对象类型作为方法的返回值.png)

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

* **this关键字**含义（可用于访问本类的成员变量、成员方法、另一个构造方法。**只能有一个，且在第一句**）
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

  * **命名**不同
    * 构造方法：类名一致
    * 成员方法：自定义
  * **返回值类型**不同
    * 构造方法：无返回值类型
    * 成员方法：void或者确定的数据类型
  * **调用**不同
    ​	构造方法：new 关键字调用
    ​	成员方法：对象.成员方法名调用

  * **作用**不同
    * 构造方法：创建对象
    * 成员方法：执行某具体功能



### 2.3.2 static

如果一个成员使用了static关键字，那么这个成员不再属于自己，而是**属于所在类**，**多个对象共享这个成员**。

- 修饰**类的成员**(成员变量，成员方法，代码块等)

  - ==**类第一次被加载（或第一次创建对象或访问静态数据成员）**==时执行，可以通**类名.静态成员**直接调用

- **静态代码块（静态初始化块）**——用来**一次性对静态成员变量赋值**

  - **构造代码块（初始化块）**：**构造类的对象**时执行对非静态成员变量的赋值

- **静态使用的注意事项**

  1. 静态方法只能访问静态成员，==**静态不能直接访问非静态**==。（非静态既可以访问静态，又可以访问非静态）
  2. 静态方法中不可以使用 this或者super关键字，因为其代表**对象，静态不能使用对象调用
  3. 主函数是静态的

- 静态内存图

  ![](images\03-静态的内存图.png)










### 2.3.3 继承

* 由来
  * 继承主要**解决**的问题是：**共性抽取**。
  * 继承描述的是事物之间的所属关系，这种关系是： **is-a 的关系**。父类更通用，子类更具体。

* **定义**：**继承**就是**子类继承父类的属性和行为**，使得子类对象具有与父类相同的属性、相同的行为。

  * 子类可以**直接访问**父类中的**非私有的属性和行为**。

* 好处：
  * **提高代码的复用性**。 
  * 类与类之间产生了关系，是**多态的前提**。 

* **super关键字**（用于子类访问父类的成员变量、成员方法、构造方法）
  * 子类**每个构造方法中均有默认的super()**调用父类的空参构造。手动调用父类构造会覆盖默认的super()
  * 在构造方法中super只能有**一个**，在子类构造方法里的**第一个语句**，所以不能和this同时出现
  * 子父类出现同名成员，在**子类中需要访问父类中非私有成员**时，需要**使用 super 关键字，修饰父类成员**

* ==继承后**成员变量**：若重名则**就近**使用，使用super区分父类变量。**编译看左边，运行看左边**==

  * 子父类中出现了**同名**的**成员变量**，则创建子类对象时，访问有两种方式：
    * **直接通过子类对象访问**成员变量：**=左边对象是谁**，就优先用谁，没有则向**上**找
    * **间接通过成员方法访问**成员变量：该**方法属于谁**，就优先用谁，没有则向**上**找

* ==继承后**构造方法**：无影响，但是子类构造方法默认调用父类构造方法==

  - **子类构造必须先调用父类构造方法进行初始化**，不写则默认**赠送super()**，写则用写的**指定super**调用
  - 构造方法的名字是与类名一致的。所以子类是**无法继承**父类**构造方法**的

* ==继承后**成员方法**：重名，子类重写父类方法。**编译看左边，运行看右边**==

  * 在父子类的继承关系中，创建子类对象，访问成员方法的规则：
    * **创建的对象(new)是谁，就优先用谁**，没有则向**上**找

* ==**方法重写 (Override)**==：子类中出现与父类**一模一样的方法**时，会出现**覆盖**效果，也称为重写或者复写。声明不变，**重新实现**。注意事项如下：==（一般特点：**越大方**、**越好**(异常继承中)、**越分工明确**）==

  * 必须要保证**权限大于等于父类权限**。 

  * **返回值引用类型（小于等于父类范围，如Object、String），基本类型必须一致**

  * **函数名**和**参数列表**要**一模一样**。 

    ![](images\方法重写.PNG)

  虽然在子类中可以存在与父类中**private**和**static**方法相同名称的方法，和类相关，所以并**不是重写**

* **super、this内存图**

  父类空间优先于子类对象产生。在每次创建子类对象时，先初始化父类空间，再创建其子类对象本身。目的在于子类对象中包含了其对应的父类空 间，便可以包含其父类的成员，如果父类成员非private修饰，则子类可以随意使用父类成员。代码体现在子类的构 造方法调用时，一定先调用父类的构造方法。

  ![](images\03-super与this的内存图.png)

* Java中**继承的特征**

  * Java只支持**单继承**，不支持多继承
  * Java支持**多层继承**(继承体系)。顶层父类是Object类，所有的类默认继承Object，作为父类。 
  * 子类和父类是一种相对的概念。





### 2.3.4 抽象类（abstract）

* **抽象方法**：**没有方法主体**（没有大括号）的方法，用`abstract`修饰，`;`结束
* **抽象类**：==**包含抽象方法的类必须声明为抽象类**，但**抽象类可以不包含任何抽象方法**==
  * **抽象类不能被实例化**（可能包含抽象方法，但没有方法体无法调用）
  * 可以**创建子类继承`(extends)`**并**重写父类所有抽象方法**，否则该子类须声明为抽象类（最终必有实现类否则无意义）
* **抽象类成员特点：**
  1. 成员变量：有变量，有常量
  2. 成员方法：有抽象，有非抽象
  3. **构造方法**：有，用于**子类创建对象时初始化父类成员**（因为子类构造中，有默认的`super()`.）
  4. 静态代码块：可以有



### 2.3.5 接口（interface）

* Java中接口就是多个**类的公共规范标准**，是方法的集合。是**引用数据类型**，用`interface`修饰，也会被编译成`.class`文件

* 接口中包含的内容有：

  * ==JDK7：**常量（静态）**、**抽象方法**==

    * 接口中成员变量必须是**赋值的常量**且**静态**的，默认修饰符`public static final`（大写、下划线）
    * 接口中成员方法必须是抽象方法，默认修饰符`public abstract`

  * ==JDK8：**默认方法（default ）**、**静态方法（static）**==

    * 默认方法：供**实现类直接调用**或者**重写**。可以解决接口升级问题、拼接函数模型

      ```java
      public default 返回值类型 method(/*参数列表*/) { //default不可省略，public可省略
          //方法体     
      }
      ```

    * 静态方法：**接口直接调用**，不能用接口实现类对象来调用（因为可能实现多接口）

      ```java
      public static 返回值类型 method(/*参数列表*/) { //static不可省略，public可省略
          //方法体         
      }
      ```

  * ==JDK9：**私有方法**==

    * 私有方法：向上抽取重复代码，只能供**接口自己**中的**默认方法**或者**静态方法调用**

      ```java
      private 返回值类型 method(/*参数列表*/) { 
          //方法体     
      }
      ```

      ```java
      private static 返回值类型 method(/*参数列表*/) {
          //方法体         
      }
      ```

* 同样的，接口不可以被实例化，需要定义一个类**实现`(implements)`接口中所有方法**，如果这个类是**抽象类，实现部分**即可

* **注意：**

  * 接口**没有静态代码块**或**构造方法**（其实现类继承`Object`，提供无参构造）
  * 一个类的直接父类是唯一的，但一个**类可以实现多个接口**（用“,”隔开）
  * 实现类实现的多个接口中，存在**重复的抽象方法**，那么**只需重写一次**即可
  * 实现类实现的多个接口中，存在**重复的默认方法**，那么**必须重写**冲突的默认方法
  * 实现类的**直接父类中的方法**和**接口中默认方法产生冲突**，则优先**使用父类**中的方法

* **类与接口关系总结**：

  1. 类与类：继承关系，只能单继承，可以多层继承
  2. 类与接口：实现关系，可以单实现，也可以多实现，可以在继承一个类时实现多个接口
  3. 接口与接口：继承关系，可以单继承，也可以多继承
     - 多继承中，如果父接口中的默认方法（抽象方法）有重名的，那么子接口须重写一次。



### 2.3.6 多态

* **多态**： 是指**一个对象有多种形态**。 

* **前提（重点）**

  * **继承**或者**实现**【二选一】 

  * **方法的重写**【意义体现：不重写，无意义】 

  * **父类引用指向子类对象**【格式体现】 

    ```java
    父类 对象名 = new 子类();
    接口名 对象名 = new 实现类();
    ```

* **多态中成员访问特点（继承中也一致）**

  * ==成员变量：编译看左边，运行看左边==
  * ==静态方法或变量：编译看左边，运行看左边（与类相关）==
  * ==**成员方法**：编译看**左**边，运行看**右**边（**依赖对象，因为有方法重写**）==
  * ==构造方法：创建子类对象时访问父类构造方法对父类成员进行初始化==

* 对象的**向上转型**，一定是**安全**的。但**无法访问子类特有方法**，可以限制对特有功能的访问

  * 其实就是多态的写法，如`Fu f = new Zi();`由小范围转向大范围，类似自动类型转换

* 对象的**向下转型**，**不一定安全`(ClassCastException)`**。将父类对象**还原**为**==本来的子类对象==**

  * `Zi zi = (Zi) f;`由大范围转向小范围，类似强制类型转换
  * `instanceof`用于**判断引用数据类型的具体类型**，返回布尔值，如`if(f instanceof Zi){}`

* 笔记本USB接口案例

  ![](images\06-笔记本电脑案例分析.png)

### 2.3.7 一个对象的实例化过程

1. JVM加载`main()`所属的类的`.class`文件，**若有基类则先加载基类**（总是在使用时加载其`.class`文件）

   执行**根基类**对应`.class`中**static初始化**（<u>静态成员变量和静态代码块，按其定义顺序执行</u>)，然后是下一个派生类的static初始化，以此类推。（类**第一次**被加载或创建对象或访问静态数据成员时）

2. 在用构造器**创建对象**时，在**堆中为对象分配空间**，执行构造器初始化

   1. 先根据隐藏的第一行**`super()`**来到**根基类**的无参构造器
   2. 执行根基类的**非静态成员变量的默认初始化，显式初始化**和**构造代码块的初始化**（<u>后俩按其定义顺序执行</u>）
   3. 然后执行根基类**构造器定义的初始化**
   4. 然后执行下一个派生类的`super()`后的以上操作

3. 初始化完毕后，将地址**赋值**给引用变量。

【注意】方法重写

```java
public class Father{
    private int i = test();
    private static int j = method();

    static{
        System.out.print("1 ");
    }
    public Father(){
        System.out.print("2 ");
    }
    {
        System.out.print("3 ");
    }
    public int test() {
        System.out.print("4 ");
        return 1;
    }
    public static int method() {
        System.out.print("5 ");
        return 1;
    }
}
```

```java
public class Son extends Father{
    private int i = test();
    private static int j = method();

    static{
        System.out.print("6 ");
    }
    public Son(){
        System.out.print("7 ");
    }
    {
        System.out.print("8 ");
    }
    public int test(){
        System.out.print("9 ");
        return 1;
    }
    public static int method(){
        System.out.print("10 ");
        return 1;
    }

    public static void main(String[] args) { //也可以放在其他类中
        Son son1 = new Son();//5 1 10 6 9 3 2 9 8 7 
        System.out.println();
        Son son2 = new Son();//9 3 2 9 8 7 
    }
}
```







## <span name="final">2.4 final 关键字</span>

* **ﬁnal**：用于**修饰不可改变内容**。可以用于修饰类、方法和变量。 
  * **类**：被修饰的类，**不能被继承**。 
  * **方法**：被修饰的方法，**不能被重写**。 
  * **局部变量**：
    * 基本类型：只能**赋值一次**，**不能再更改**。
    * 引用类型：只能指向一个对象，**地址不能再更改**。不影响内部成员变量的修改
  * **成员变量**
    * 由于成员变量有默认值，用final修饰后**必须手动赋值**，否则编译失败
    * 要么**显示初始化**；要么通过**全部的构造方法赋值**（并删除setter）
* **abstract和final不能同时使用**。abstract修饰类、方法是需要被实现、重写的，与final矛盾
* **abstract和static不能同时使用**。abstract修饰的方法没有方法体，static修饰的方法可以类名调用，编译失败



## 2.5 package

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



## 2.6 权限修饰符

- **权限修饰符（访问控制）**

  - **private（类访问级别）**：被修饰的类成员只能被**该类其他成员**访问
  - **default（包访问级别）**：类或类成员没有修饰符为默认访问级别，只能被**本包中其他类**访问
  - **protected（子类访问级别）**：被修饰的类成员能被**本包中其他类**、**不同包中该类子类**访问
  - **public（公共访问级别）**：所有都能访问

- **访问控制级别**

  |  访问范围  | private | default | protected | public |
  | :--------: | :-----: | :-----: | :-------: | :----: |
  |   同一类   |    √    |    √    |     √     |   √    |
  |   同一包   |         |    √    |     √     |   √    |
  | 不同包子类 |         |         |     √     |   √    |
  |    全局    |         |         |           |   √    |



## 2.7 内部类

- **内部类**：将**一个类A定义在另一个类B里面**，里面的那个类A就称为内部类，B则称为外部类。 

- 内部类仍然是一个独立的类，在编译之后会内部类会被编译成独立的.class文件，但是前面冠以外部类的类名 和\$符号 。比如，`Person$Heart.class`

- 特点：（铁扇公主肚子里的孙猴子）

  - **内部类可以直接访问外部类的成员**，**包括私有成员**。 
  - **外部类要访问**内部类的成员，必须要**建立内部类的对象**。

- 分类：

  - **成员内部类**：：定义在**类中方法外**的类

    * **`Outer.Inner in = new Outer().new Inner();`**直接访问外部类中内部类成员

    * 如果出现了**重名**现象，**访问外部类成员变量**格式是：**`外部类名称.this.外部类成员变量名`**

      【面试】

      ```java
      class Outer {
          public int num = 10;
      
          class Inner {
              public int num = 20;
      
              public viod show() {
                  int num  = 30;
      
                  System.out.println(num);//30
                  System.out.println(this.num);//20
                  System.out.println(Outer.this.num);//10
              }
          }
      }
      ```

  - **静态内部类**(`Outer.Inner in = new Outer.Inner();`)**不用创建外部类对象**，相当于外部类

    - 静态内部类只能访问外部类中的静态成员

    - 静态内部类中可以定义静态成员，==**非静态内部类中不允许定义静态成员**==

      【面试】

      ```java
      public class Test {
          public void func() {
              //位置1
          }
      
          class Inner {
          }
      
          public static void main(String[] args) {
              Test test = new Test();
              //位置2
          }
          //在位置1写 new Inner()  可以，外部类访问内部类要创建内部类对象
          //在位置2写 new Inner()  不可以，main方法时静态的，不能调用非静态内部类
          //在位置2写 new test.Inner()  不可以，不是静态内部类，可以test.new Inner()
          //在位置2写 new Test.Inner()  不可以，类名调用只能调用静态成员，除非内部类为静态
      }
      ```

  - **局部内部类**（包含**匿名内部类**）：定义在**方法内部**的类，只有==**当前所属方法才能使用它**==

    - 局部内部类，如果**访问所在方法的局部变量**，那么这个局部变量必须是【有效**final**的】

      备注：从Java 8+开始，只要局部变量事实不变，那么final关键字可以省略。

      > new出来的对象在堆内存当中。
      >
      > 局部变量是跟着方法走的，在栈内存当中。
      >
      > 方法运行结束之后，立刻出栈，局部变量就会立刻消失。
      >
      > 但是new出来的对象会在堆当中持续存在，直到垃圾回收消失。

  - ==**匿名内部类**：是内部类的简化写法。它的本质是一个**带具体实现的父类或者父接口的匿名的子类对象**==

    - **前提**：匿名内部类必须**继承一个父类**或者**实现一个父接口**。 

    ```java
    接口名称 对象名 = new 接口名称() { //new代表创建对象的动作；接口名称就是匿名内部类需要实现哪个接口
        // 覆盖重写所有抽象方法
    }; //{...}这才是匿名内部类的内容
    ```

    * **使用场景：**方法的参数是接口或抽象类，并且其中的方法不超过三个，嫌麻烦为了不写实现类或子类
    * 注意几点问题：
      * **匿名内部类**，在【**创建对象**】的时候，**只能使用唯一一次**。如果希望多次创建对象，而且类的内容一样的话，那么就需要使用单独定义的实现类了。
      * **匿名对象**，在【**调用方法**】的时候，**只能调用唯一一次**。如果希望同一个对象，调用多次方法，那么必须**给对象起个名字**。
      * 匿名内部类是省略了【实现类/子类名称】，但是匿名对象是省略了【对象名称】

- 定义一个类的时候，权限修饰符规则：

  * 外部类：public / (default)

  * 成员内部类：public / protected / (default) / private

  * **局部内部类**：什么都不能写

    ```java
    public class Demo {
        public static void main(String[] args) {
            new Demo(){ //匿名对象，匿名内部类
                void show(){
                    System.out.println("hello");
                }
            }.show();//hello
            //若是给对象起名，并用该名调用show方法，则编译不通过，因为父类中没有此方法，当前所属方法才能使用它
        }
    }
    ```




# 3 Java API

- API（Application Programming Interface），应用程序编程接口

## 3.1 Object（java.lang）

- Object是Java语言中的根类，所有类的父类（直接或间接继承），所有对象（包括数组）都实现这个类的方法。有且只有一个无参构造方法，在对象实例化的时候，最终找的父类就是Object。

- **方法：**

  1. **`toString()`**返回对象的字符串表示。直接打印输出一个对象名称，默认调用该方法

     - **默认打印的地址值**是由类的全名+'@'+哈希值的十六进制表示，没意义所以一般由**子类重写**

       `getClass().getName()+"@"toHexString(hashCode());`

  2. **`equals()`**比较两个对象是否相同，底层用的是**`==`**。`String`重写了该方法

     - **默认**情况下**比较的是对象地址值**是否相同，没意义所以一般由**子类重写**，注意多态向下转型问题
       - 与自身比较；
       - null或不属同一类；
       - 向下转型比较（基本类型用==，引用类型用**`Objects.equals()`**）

  3. **`hashCode()`**返回**对象的哈希值**，十进制整数，不是实际地址值，是**逻辑地址值**

     - 使用**Set、Map中键**时需要**给添加的自定义类重写**`hashCode()`和`equals()`

  4. `getClass()`返回对象的字节码文件对象，反射中讲解

  5. `finalize()`垃圾回收，不确定时间，可以调用`System.gc()`立即回收

  6. `clone()`实现对象克隆，包括成员变量的数据复制，但是它和两个引用指向同一个对象是有区别的 

- **【面试】**==和equals()区别：

  1. `==`：**基本类型**：比较值是否相等；**引用类型**：比较地址值是否相等
  2. `equals()`：只能比较**引用类型**，比较地址值是否相等，可以根据需要重写

### <span name="Objects">3.1.1 Objects工具类</span>

在**JDK7**添加的，它提供了一些静态方法来**操作对象**，这些方法是null-save（空指针安全的）或null-tolerant（容忍空指针的），计算对象的`toString`、`hashCode`、`equals`、`requireNonNull`等等。

```java
public static String toString(Object o) {  //Objects.toString()源码
    return String.valueOf(o);
}
public static int hashCode(Object o) {    //Objects.hashCode()源码
    return o != null ? o.hashCode() : 0;
}
public static boolean equals(Object a, Object b) {  //Objects.equals()源码
    return (a == b) || (a != null && a.equals(b));  
}
public static <T> T requireNonNull(T obj[, String message]) {   //Objects.requireNonNull()源码
    if (obj == null)
        throw new NullPointerException([message]);
    return obj;
}
```



## 3.2 Scanner（java.util）

* 可以解析基本类型和字符串的简单文本扫描器。 System.in 系统输入指的是通过**键盘录入**数据。 

  ```java
  Scanner sc = new Scanner(System.in); 
  int a = sc.nextInt(); //将输入信息的下一个标记扫描为一个 int 值。 
  String a = sc.next(); //查找并返回来自此扫描器的下一个完整标记。
  ```



## 3.3 Random（java.util）

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



## 3.4 Math（java.lang）

- Math工具类是针对数学运算进行操作的类，提供了大量**静态方法**完成数学相关操作

- 常用方法

  - 绝对值**abs**                                               `abs(int/long/float/double a)`
  - 向上取整，**取大的ceil**，返回double       `ceil(double a)` ，-2.4变为-2.0
  - 向下取整，**取小的floor**，返回double     `floor(double a)`，-2.4变为-3.0
  - 四舍五入**round**                                      `long/int round(double a/float a)`-4.9四舍五入后为-5
  - 两数据中大着                                         `max(int a,int b)`
  - a的b次幂**pow**                                        `double pow(double a,double b)`
  - 随机数[0,1)**random**                               `double random()`
  - 平方根**sqrt**                                            `double sqrt(double a)`

- `Math.PI`：近似圆周率

- 任意**整数**范围的随机数 

  ```java
  (int)(Math.random()*(end-start+1))+start; //+1是为了包括右极限
  ```



## 3.5 Arrays（java.util）

- Arrays工具类是针对**数组进行操作**的工具类，包括排序和查找等大量**静态方法**
- 常用方法
  - `Arrays.toString(int[] arr)`                           将**数组转为字符串**，如[1, 2, 3]
  - `Arrays.sort(int[] arr,[ T[] a, Comparator<? super T> c ])`    给数组**排序**，无返回值
    - 若是数值，默认按升序
    - 若是字符串，默认按字母升序，先大写后小写，和ASCII表一致
    - 若是**自定义类型**，这个自定义类型需要**`Comparable`或`Comparator`接口的支持**
  - `Arrays.binarySearch(int[] arr,int key)`    **二分查找** 
  - `Arrays.asList(int/String等[] arr)`              **数组转集合**，**长度不能变**

## 3.6 String（java.lang）

- **特点**

  1. ==String字符串是**常量**，**字符串的值在创建后不能被更改**==（注意：这里指==**new对象中的内容不能改变**，不是**引用**不**能改变**==），其`overrid`了Object类的`equals()`方法。

  2. 正由于字符串的不可变性，所以它可以==**共享使用**==。

     ==**字符串常量池**==：程序中直接写上的==**双引号字符串**==，就在字符串常量池中，JDK1.7之后它在**堆**中

     字符串若是==**变量相加，先开空间再拼接**==；若是==**常量先加，然后在常量池中找**==，==有就返回，没有就创建==。

     ```java
     String str1 = "hello";
     String str2 = "hello";
     char[] charArray = {'h','e','l','l','o'};
     String str3 = new String(charArray);
     String str4 = "world";
     String str5 = "helloworld";
     
     System.out.println(str1==str2);//true
     System.out.println(str1==str3);//false，同理str2!=str3
     System.out.println(str5==str1+str4);//false
     System.out.println(str5=="hello"+"world");//true
     System.out.println(s3.equals(s1+s2));//true
     ```

     ![](images\01-字符串的常量池.png)

  3. 字符串效果上相当于char[]字符数组，但是==**底层原理被final修饰的byte[]字节数组**==。

- 构造方法

  - `String()`：初始化一个新创建的 String 对象，使其表示一个空字符序列

  - `String(char[] value [, int offset ,int count])`：分配一个新的 String，它包含取自字符数组参数全部（或一个子数组）的字符

  - `String(byte[] bytes[, int offset, int length][, String charsetName])`：通过使用指定的字符集解码指定的 byte （或子）数组，构造一个新的 String

  - `String s = "hello"`：直接创建新的 String

  - **字面值**作为**字符串对象**（新创建的字符串是该参数字符串的副本）和通过**构造方法**创建对象的不同

     `String s = new String("hello")`和`String s = "hello"`,前者创建两对象，后者创建一个对象

- String常用方法(**不改变原String值**)

  - **判断功能**

    - `boolean isEmpty()`						          字符串长度是否为0

    - `boolean contains(CharSequence cs)`                       字符串中是否包含指定字符序列

    - `boolean equals(Object anObject)`          ==字符串与指定字符串是否相等(**内容**)，推荐**常量放前面**==

      `boolean equalsIgnoreCase(String str)`                 ==字符串与指定字符串是否相等，忽略大小写==

      `boolean startsWith(String prefix[,int toffset])` 从指定索引开始的子串是否以prefix开始

    - `boolean endsWith(String suffix)`                             字符串是否以suffix后缀结尾

  - **获取功能**

    - `int length()`								   ==获取字符串长度==

    - `String concat(String str)`                                        ==将字符串**拼接，本字符串不变**==

    - `char charAt(int index)`                                              ==获取指定索引的字符==

    - `int indexOf(int ch/String str)`                              ==获取指定字符/字符串第一次/出现的索引==

    - `int indexOf(int ch/String str,int fromIndex)`   ==获取指定~从**[某索引开始**第一次出现的索引==

      `ch`是`int`类型原因：'a'和97都能代表'a'。  (`lastIndexOF(...)`表示最后一次出现的索引)

    - `String substring(int startIndex,int endIndex)` ==获取从**[start到end)**的子串,没end时到结尾==

  - **转换功能**

    - **`static`**`String valueOf(int i/char[] chs)`       **静态方法**将int型和**字符数组型**数据转为字符串
    - `String toLowerCase() /toUpperCase()`                     将所有字符都转换为小写/大写,本身不变
    - `byte[] getBytes()`                                                        ==将字符串转换为字节数组==
    - `char[] toCharArray()`                                                  ==将字符串**转换为字符数组**==

  - **其他功能**

    - ==**替换**(将所有old字符或字符串替换为新~~)，CharSequence是个接口，包括字符串。敏感词汇过滤==

      `String replace(CharSequence/char target, CharSequence/char replacement)`

    - **去空格**(去掉字符串**首尾**空格)

      `String trim()`

    - **按字典比较**(按字典顺序比较，在str前返回负数，在str后返回正数，否则返回0)

      `int compareTo(String str)/compareIgnoreCase(String str)`

    - ==**拆分**（根据给定**正则表达式regex**的匹配拆分），返回字符串数组，不包括regex字符串==

      ==若要用英文句点”.“切分，必须写”\\\\.“（两个反斜杠）==

      `String[] split(String regex)`

* 练习题

  * 字符串遍历 `char charAt(int index)`或`char[] toCharArray()`

  * 统计大串中小串出现的次数 `int indexOf(String str,int fromIndex) `或`substring`

  * 把字符串的首字母转成大写，其他小写 `String substring(int start,int end)`

  * 把一个小数转换为保留两位小数的字符串，不考虑四舍五入：加空串转为字符串，再用split即可

    * 若考虑四舍五入，则可以将保留的数转为整数，再根据后一位值对整数进行加1与否

  * 字符串必须只有字母和数字，如下是不是这种情况的：

    `(ch<='0'||ch>='9') && (ch<='A'||ch>='Z') && (ch<='a'||ch>='z') `







## 3.7 StringBuilder（java.lang）

- `StringBuilder`(字符串缓冲区)，又称为**可变字符序列**，表示**字符容器**，其**内容和长度可变**，同步，线程安全。没有`override`Object类的`equals()`方法，不能像String类对象可以用操作符+进行连接。

- 它的**底层**拥有一个**数组**用来存放字符串内容，进行字符串拼接时，直接在数组中加入新内容。StringBuilder会**自动维护数组的扩容**。默认16字符空间，超过自动扩充。

- 构造方法：

  - `StringBuilder();`构造一个空的StringBuilder容器。
  - `StringBuilder(String str)`：构造一个StringBuilder容器，并将字符串添加进去。

- StringBuilder常用方法(**==在原StringBuilder对象中改变并返回本身==**，原对象也改变)

  - **添加**
    - `StringBuilder append(...)`        ==添加**任意类型数据**的**字符串形式**到末尾，并**返回当前对象**自身==
    - `StringBuilder insert(int offset,String str)`          在字符串中的**offset位置**插入**指定参数**
  - **删除**
    - `StringBuilder deleteCharAt(int index)`                           删除指定位置字符
    - `StringBuilder delete(int start,int end)`                       删除从**[start到end)**的字符串
  - **替换**
    - `StringBuilder replace(int start,int end,String s)`    从**[start到end)**的对象替换为指定字符串
    - `void setCharAt(int index,char ch)`                                  **替换指定位置的字符**
  - **反转**
    - `StringBuilder reverse()`                                                       反转
  - **截取(返回值类型不再是StringBuilder本身)**
    - `String substring(int start,int end)`                             截取子串，没有end参数时截取到尾部
  - **其他**
    - `void setLength(int newLength)`                                          设置StringBuilder长度
    - `public String toString()`：                                       ==将当前StringBuilder对象**转换为String对象**==

- **==String和StringBuilder转换==**

  1. **String ---> StringBuilder**			通过构造函数传入或构造好之后append()

  2. **StringBuilder ---> String**                     调用`toString()`或String类的`valueOf()`静态方法

     ```java
     String str1 = sb.toString();
     String str2 = String.valueOf(sb);
     ```

- **【面试】**

  1. **==String、StringBuffer、StringBuilder区别==**

     - **String内容、长度不可变，后两个可变**。字符串拼接时，总是会在内存中创建一个新对象，影响性能
     - **StringBuffer是同步的，数据安全，效率低；StringBuilder是不同步的，数据不安全，效率高**

  2. **==StringBuilder和数组的区别==**

     - **二者都可以看做装其他数据的容器，但是StringBuilder的数据最终是一个字符串数据，而数组可以存放多种类型的数据，但必须是同一种数据类型的**

  3. **参数传递问题**

     - 基本数据类型：形式参数的改变不影响实际参数

     - 引用数据类型：形式参数的改变影响实际参数

     - 注意：（==以下画图即可理解，如下图==）

       ==**包装类、String作为引用数据类型，是常量，具有不可变性。**==

       **StringBuffer、StringBuilder在赋值时不改变内容，调用方法时改变**

       **==【重点】在于String内容不可变并且变量相加需要开空间再拼接，StringBuilder等内容可以变==**

     ```java
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

     ![](images\形参实参问题.png)



## 3.8 日期时间类

### 3.8.1 Date（java.util）

- **Date**用于表示日期和时间，可以精确到==**毫秒**==

- **时间戳**是指格林威治时间1970年01月01日00时00分00秒起至现在的总==**秒**==数

  - **构造方法**（只有两个没过时）

    - `Date()`                   用于创建**当前**日期时间的Date对象

    - `Date(long date)` 用于创建**指定**时间的Date对象，date为指定毫秒值（也可用时间戳/1000）

      ```java
      Date date = new Date();
      System.out.println(date); //Sun Oct 28 15:49:55 CST 2018
      		
      long date = System.currentTimeMillis();//当前时间戳的毫秒值
      Date dd = new Date(date);
      System.out.println(dd); //Sun Oct 28 15:49:55 CST 2018
      ```

  - **成员方法**

    - `getTime()`          返回此对象的**时间戳的毫秒值**
    - `setTime()`          设置此对象的**时间戳的毫秒值**

### 3.8.2 DateFormat（java.text）

- 针**对日期进行格式化**和针**对字符串进行解析**的**抽象类**，使用其子类**`SimpleDateFormat` **

  - 构造方法(无参构造为默认格式)

    - ==**指定格式构造**==**`SimpleDateFormat sdf = new SimpleDateFormat(String pattern)`**
    - **pattern**：指定格式，一般为 **`yyyy年MM月dd日 HH：mm：ss`**，详细的查看API

  - **格式化**`String format(Date d)`：Date--->String     

    ```java
    Date date = new Date(); //Sun Oct 28 16:46:42 CST 2018
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH：mm：ss");
    String s = format.format(date);
    System.out.println(s); //2018年10月28日 16：45：43
    ```

  - **解析**`Date parse(String str)`：String--->Date    

    该方法声明了一个异常，如果字符串和构造方法中格式不一致，程序会抛出`ParseException`

    ```java
    String s = "2018年10月28日 16：45：43";
    SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH：mm：ss");
    Date parse = format.parse(s); //try...catch处理或throws处理
    System.out.println(parse); //Sun Oct 28 16:46:42 CST 2018
    ```

- 出生多少天

  ```java
  Scanner sc = new Scanner(System.in);
  //System.out.println("出生天数计算（不足一天按一天算）");
  //System.out.println("输入您的出生年月日（例，2000年01月01日）：");
  String birth = sc.next();
  SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
  Date date = format.parse(birth);
  long birthTime = date.getTime();
  int day = (int) Math.ceil((System.currentTimeMillis()-birthTime)/86400000.0); //或new Date().getTime();
  //System.out.println("您出生"+day+"天了。");
  ```



### 3.8.3 Calendar（java.util）

* 日历类，是**抽象类**。在Date后出现，替换掉了许多Date的方法。该类将所有可能用到的时间信息封装为**静态成员变量**，方便获取。日历类就是方便获取各个时间属性的。

- **调用静态方法获取一个Calendar对象**

  ```java
  Calendar calendar = Calendar.gerInstance();
  ```

- 提供了如下字段等：

  - YEAR、MONTH、DAY_OF_MONTH（DATE）、DAY_OF_WEEK（周日为1,1~7）
  - HOUR、HOUR_OF_DAY、MINUTE、SECOND

- **常用方法**(注意，**MONTH从0开始**)

  - **获取指定日历字段**的**值**

    `int get(int field)`

  - 设置**指定日历字段**的**值**

    `void set(int field,int value)`

  - 设置**日历字段的值**(年月日时分秒等)

    `void set(int year,int month,int date,int hourofday,int minute,int second)`

  - 根据日历规则，为指定日历字段**加上**或**减去指定时间量**

    `void add(int field,int amount)`

  - **得到**对应的**Date对象**

    `Date getTime()`

  - 使用给定的 `Date` 设置此 Calendar 的时间

    `void setTime(Date date)`

- 计算任意二月有多少天

  ```java
  public static int getNumOfDay(int year) {
      Calendar c = Calendar.getInstance();
      c.set(year,2,1);
      c.add(Calendar.DATE,-1);
      return c.get(Calendar.DAY_OF_MONTH);
  }
  ```

- **【面试】**
  - Date类用来表示某个特定的瞬间，能够精确到毫秒。而在实际应用中，往往需要把一个日期中的年、月、日等信息单独返回进行显示或处理，这个类中的大部分方法都已被标记过时。Calendar类基本取代了Date类，该类中定义了一系列用于完成日期和时间字段操作的方法。 
    Calendar的`getTime()`方法)返回一个表示Calendar时间值的Date对象，同时Calendar有一个`setTime(Date date)`方法接收一个Date对象，将Date对象表示的时间值设置给Calendar对象，通过这两个方法就可以完成Date和Calendar对象之间的转换。



## 3.9 System（java.lang）

- System定义了一些与**系统相关**的属性和方法，并且都是**静**态的
- 常用方法
  - `void exit(int status)`                     退出当前运行的JVM，status表示状态码，非0表示异常终止
  - `void gc()`                                            运行垃圾回收器并立即回收垃圾
    - **垃圾回收**(当一个对象在内存中被释放时，它的**finalize()方法**会被自动调用)
      1. 等待Java虚拟机自动垃圾回收
      2. **调用`System.gc()`方法**通知Java虚拟机立即进行垃圾回收)
  - `long currentTimeMillis()`                **获取当前时间戳的毫秒值**，东八区应加8个小时。测试程序效率
  - `void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)`：**数组拷贝**
    - `arraycopy(arr1, 1, arr2, 3, 2)`：从arr1的1开始长度为2的数据复制到arr2从3开始的地方



## 3.10 包装类（java.lang）

- 为了让基本类型的数据**进行更多的操作**，Java就为每种基本类型提供了对应的包装类类型 

  | 基本数据类型 | 引用数据类型  |
  | :----------: | :-----------: |
  |     byte     |     Byte      |
  |    short     |     Short     |
  |     char     | **Character** |
  |     int      |  **Integer**  |
  |     long     |     Long      |
  |    float     |     Float     |
  |    double    |    Double     |
  |   boolean    |    Boolean    |

- **装箱与拆箱**

  - 装箱：将基本数据类型的值转为引用数据类型，提供如下静态方法，其他包装类同理。看懂即可
    - `Integer.valueOf(int num)`
    - `Integer.valueOf(String str)`，字符串必须是基本类型字符串，否则`NumberFormatException`
  - 拆箱：将引用数据类型的值转为基本数据类型，提供如下成员方法，其他包装类同理。看懂即可
    -  `intValue()`该方法将Integer对象转为int

- **自动拆装箱**：**JDK 5.0后支持**基本类型数据和包装类型数据之间可以自动互相转换

- **==基本类型与字符串转换==**

  - 基本类型—>字符串：
    - **`基本类型的值+""`**：最简单方法，常用
    - **String类的静态方法`valueOf(参数)`**
    - **包装类的静态方法`toString(参数)`**方法，不是Object类的`toString()`方法，重载
  - 字符串—>基本类型：
    - **包装类的静态方法`valueOf(参数)`转包装类后再转基本类型**
    - **包装类的静态方法`parseXxx()`**

- **进制转换**

  - `static String toString(int num,int radix)`               十进制到其他进制`toString`
  - `static int parseInt(String s,int radix)`                   其他进制到十进制`parseInt`

- **【面试】Integer数据直接赋值时，注意-128~127之间的数据缓冲池问题**

- **注意：**

  1. 包装类都重写Object类中的`toString()` 方法，以**字符串**形式返回包装类的基本数据类型的值
  2. 除了Character外，包装类都有`valueOf(String s)`方法，根据String类型参数创建包装类对象
  3. 除了Character外，包装类都有`parseXXX(String s)`的静态方法，将字符串转为基本类型数据

- - 

- 


## 3.11 BigInteger

- 针对大整数的运算

## 3.12 BigDecimal

- 浮点数据做运算，会丢失精度。所以，针对浮点数据的操作建议采用BigDecimal(**金融相关的项目**)

- - 









# 4 容器（container）

- **对象数组：**数组可以存储基本数据类型和引用类型，存储引用类型的数组就叫**对象数组**

- **容器(container)**

  - **由来**  Java-->面向对象-->操作很多对象-->存储-->容器
  - **容器与数组的区别**
    1. **长度**     数组固定，容器（集合）可变
    2. **内容**     数组可以是基本类型和引用类型，容器（集合）只能是引用类型
    3. **元素内容**     数组只能存储同一类型，容器（集合）可以存储不同类型(一般也是同一种类型)

- **继承体系结构**

  ![](images\容器.png)

## 4.1 数据结构

### 4.1.1 栈

* **栈（stack）**：又称堆栈，它是**运算受限的线性表**，其限制是仅允许在栈的一端进行插入和删除操作，不允许在其他任何位置进行添加、查找、删除等操作。

  * **后进先出（LIFO）**。例如，弹夹，浏览器回退

  * **栈的入口、出口的都是栈的顶端位置**。

    ![](images\堆栈.png)

* **压栈**：就是**存**元素。

* **弹栈**：就是**取**元素。

### 4.1.2 队列

- **队列（queue）**：简称队，它同堆栈一样，也是一种**运算受限的线性表**，其限制是仅允许在表的一端进行插入，而在表的另一端进行删除。
  - **先进先出**。例如，小火车过山洞，车头先进去，车尾后进去；车头先出来，车尾后出来。
  - **队列的入口、出口各占一侧**。例如，下图中的左侧为入口，右侧为出口。

![](images\队列.png)

### 4.1.3 数组

- **数组（Array）**：是有序的元素序列，数组是在内存中开辟一段连续的空间，并在此空间存放元素。
  - ==**查询快**==：**通过索引**，可以快速访问指定位置的元素。
  - ==**增删慢**：需要把增删元素后面的元素**移位**。**Java中利用的是**==**`System.copyArray(...)`**

### 4.1.4 链表

* **链表（linked list）**：由一系列**结点（node）**组成，包括：存储数据元素的**数据域（date）**，存储**后继结点地址（next）**的指针域。Java中所有链表都是双向链表，即每个结点还存放着**前驱结点的地址（previous）**。
  * ==**查找慢**==：想查找某个元素，需要通过连接的节点，依次向后查找指定元素。
  * ==**增删快**==：只需要修改连接下个元素和连接上个元素的地址即可。

### 4.1.4 散列集

### 4.1.5 红黑树

* **二叉树（binary tree）**：是每个结点不超过2个分支且每个结点只能有一个父节点的**有序树** 。
* **二叉排序树**或者是一棵空树，或者是具有下列性质的二叉树：
  * 若左子树不空，则左子树上所有结点的值均小于它的根结点的值；
  * 若右子树不空，则右子树上所有结点的值均大于它的根结点的值；
  * 左、右子树也分别为二叉排序树；
  * 没有键值相等的节点。
* **红黑树**：是一种**自平衡二叉查找树**。红黑树是每个节点都带有颜色属性的二叉查找树，颜色或红色或黑色
  * 约束如下：
    * 根节点是黑色。
    * 节点是红色或黑色。
    * 每个叶节点（NIL节点，空节点）是黑色的。
    * 每个红色节点的两个子节点都是黑色。(从每个叶子到根的所有路径上不能有两个连续的红色节点)
    * 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点。
  * 特点：**查询速度特别快**,趋近平衡树,查找叶子元素最少和最多次数不多于二倍



## 4.2 Collection接口（java.util）

- Collection是所有**单列集合**的**父接口**，因此在Collection中定义了单列集合(List和Set)通用的一些方法，这些方法可用于操作所有的单列集合。由于**其`Override`了`toString()`方法**，所以可以直接打印其内容。
- **常用方法:**
  - **添加(==add==；addAll)**
    - `boolean add(Object o)`
    - `boolean addAll(Collection c)`
  - **删除(==clear==；==remove==；removeAll)**
    - `void clear()`：删除集合中所有元素，但集合还存在，如[]
    - `boolean remove(Object o)`
    - `boolean removeAll(Collection)`
  - **判断(==isEmpty==；==contains==；containsAll)**
    - `boolean isEmpty()`
    - `boolean contains(Object o)` 底层是equals方法，需重写
    - `boolean containsAll(Collection c)`  底层是equals方法，需重写
  - **获取容量(==size==)**
    - `int size()`
  - **返回迭代器(==iterator==)Collection特有的**
    - `Iterator iterator()`
  - 把**集合**中的元素，存储到**数组**中(**==toArray==**)
    - `public Object[] toArray()`

### 4.2.1 Collections（java.util）

- 针对**Collection**进行操作的**工具类**

- **【面试】Collection和Collections的区别**

  - Collection 是单列集合的顶层接口，有两个子接口List和Set
  - B:Collections 是针对集合进行操作的工具类，可以对集合进行排序和查找等

- **常见静态方法**

  - `public static <T> boolean addAll(Collection<T> c, T... elements)  `**==添加一些元素==**

  - `public static void shuffle(List<?> list)`  **==打乱集合顺序，洗牌==**

  - `public static <T> void sort(List<T> list)` **==元素按照默认规则排序，默认自然排序==**

    - **自然排序(元素具备比较性)**：元素所属的类实现**==Comparable接口==**，重写**`compareTo()`**方法

      **`this - 参数`：升序，反之降序**

  - `public static <T> void sort(List<T> list，Comparator<? super T> )`**==元素按照指定规则排序==**

    - **比较器排序(集合具备比较性)**：方法接收==**Comparator实现类对象**==，重写**`compare(O o1,O o2)`**方法

      **`o1-o2`升序，`o2-o1`降序**

      ```java
      //可以根据多条规则排序
      public int compare(Student o1, Student o2) {
          int result = o1.getAge() - o2.getAge();
          result = result == 0 ? o1.getName().charAt(0) - o2.getName().charAt(0) : result;
          return result;
      }
      ```

      ```java
      //lambda改写
      Collections.sort(list, (o1, o2) -> {
          int result = o1.getAge() - o2.getAge();
          result = result == 0 ? o1.getName().compareTo(o2.getName()) : result;
          return result;
      });
      ```

  - `public static <T> int binarySearch(List<?> list,T key)` 

  - `public static <T> T max(Collection<?> coll)` 

  - `public static void reverse(List<?> list)` 

- 不用Vector可以用Collections提供的静态方法**(Collection.synchronizedCollection/List/Map/Set)**

  ```java
  List<String> list = Collections.synchronizedList(new ArrayList<>());
  ```




## 4.3 Iterator接口（java.util）

* **迭代**：即**==Collection集合元素的通用获取方式==**。在取元素之前先要判断集合中有没有元素，如果有，就把这个元素取出来，继续在判断，如果还有就再取出出来。一直把集合中的所有元素全部取出。这种取出方式专业术语称为迭代。

* `public Iterator iterator()`: **获取==集合对应的迭代器==**，用来遍历集合中的元素的。

  * 这个接口也有泛型，但是跟着所属集合走，集合是什么泛型，迭代器就是什么泛型

* `public E next()`:返回迭代的**下一个元素**。

* `public boolean hasNext()`:如果**仍有元素可以迭代**，则**返回 true**。

  ```java
  Iterator<String> iterator = list.iterator();
  while (iterator.hasNext()) {
      System.out.println(iterator.next());
  }
  ```

- **迭代器原理**：Iterator迭代器对象在遍历集合时，内部采用指针的方式来跟踪集合中的元素。在调用Iterator的next方法之前，迭代器的索引位于第一个元素之前，不指向任何元素，当第一次调用迭代器的next方法后，迭代器的索引会向后移动一位，指向第一个元素并将该元素返回，当再次~。依此类推，直到hasNext方法返回false，表示到达了集合的末尾，终止对元素的遍历。

### 4.3.1 for each循环

`Collection<E> extends Iterable<E>`，（**所有Collection**）实现此接口允许对象成为“**foreach**”语句目标。

* 增强for循环(也称for each循环)是**JDK1.5**以后出来的一个高级for循环，专门用来==遍历**数组**和**Collection**==的。它的内部**原理其实是个Iterator迭代器**，所以在遍历的过程中，**==不能对Collection中的元素进行增删操作==**。

  ```java
  ArrayList<String> arraylist = new ArrayList<>();
  for(String s:arraylist){
  	System.out.println(s);
  }
  ```



### 4.3.2 并发修改异常

- `ConcurrentModificationException`现象：**迭代器遍历集合，集合修改集合元素**

- 原因：迭代器依赖于集合，而集合的改变导致迭代器预期的迭代次数发生改变

- 解决：

  - ==**迭代器遍历，并利用Iterator的remove方法**==
  - **集合遍历，集合修改**(利用get()和size())，增加元素在集合末尾

  - 删除元素时并跟上break语句



## 4.4 泛型

> 集合中是可以存放任意对象的，只要把对象存储集合后，那么这时他们都会被提升成Object类型。当我们在取出每一个对象并且进行相应的操作时必须采用类型转换。有可能强转引发运行时`ClassCastException`。
>
> Collection虽然可以存储各种对象，但实际上通常Collection只存储同一类型对象。例如都是存储字符串对象。因此在JDK5之后，新增了**泛型**(**Generic**)语法，让你在设计API时可以指定类或方法支持泛型，这样我们使用API的时候也变得更为简洁，并得到了编译时期的语法检查。

* **泛型**：可以在类或方法中预支地使用未知的类型。
* **泛型的好处：**
  * 将**运行时期**的ClassCastException，转移到了**编译时期**变成了编译失败。
  * **避免了类型强转**的麻烦。

> tips:泛型是数据类型的一部分，我们将类名与泛型合并一起看做数据类型。

* **泛型的定义与使用**

  * 泛型，用来灵活地**将数据类型应用**到不同的**类**、**方法**、**接口**当中。**将数据类型作为参数进行传递**。

  * **含有泛型的类**（在**创建对象时**就确定泛型的类型）

    ```java
    修饰符 class 类名 <代表泛型的变量> {  } //格式
    public class MyGenericClass<E> {//没有E类型，在这里代表 未知的一种数据类型 未来传递什么就是什么类型
        private E e;
    
        public void setE(E e) {
            this.e = e;
        }
        public E getE() {
            return e;
        }
    }
    ```

    ```java
    MyGenericClass<String> my = new MyGenericClass<String>(); //创建对象时就确定泛型的类型
    ```

  * **含有泛型的方法**（**调用方法传递数据时**确定泛型的类型）

    ```java
    修饰符 <代表泛型的变量> 返回值类型 方法名(参数){  } //格式
    public class MyGenericMethod {	  
        public <E> void show(E e) {
            System.out.println(e.getClass());
        }
    
        public static <E> E show2(E e) {	
            return e;
        }
    }
    ```

    ```java
    MyGenericMethod mm = new MyGenericMethod();
    mm.show("aaa"); //调用方法传递数据时确定泛型的类型
    mm.show(1);
    ```

  * **含有泛型的接口**（1.**实现接口时**确定泛型的类型；2.始终不确定泛型，直到**创建对象时**确定泛型的类型）

    ```java
    修饰符 interface 接口名 <代表泛型的变量> {  } //格式
    public interface MyGenericInterface<E>{
    	public abstract void add(E e);
    	
    	public abstract E getE();  
    }
    ```

    ```java
    public class MyImp1 implements MyGenericInterface<String> { //实现接口时确定泛型的类型
        @Override
        public void add(String e) { /*省略...*/ }
    
        @Override
        public String getE() { /*省略...*/ }
    }
    ```

    ```java
    public class MyImp2<E> implements MyGenericInterface<E> { //始终不确定泛型
        @Override
        public void add(E e) { /*省略...*/ }
        
    	@Override
        public E getE() { /*省略...*/ }
    }
    MyImp2<String>  my = new MyImp2<String>();  //直到创建对象时确定泛型的类型
    my.add("aa");
    ```

* **泛型通配符**

  * 当**使用泛型类或者接口**时，**传递的数据中**，**泛型类型不确定**，可以通过通配符<?>表示。但是一旦使用泛型的通配符后，只能使用Object类中的共性方法，集合中元素自身方法无法使用。此时只能接收数据,不能往该集合中存储数据。
  * 使用方式：不能创建对象时使用，只能**作为方法的参数使用**

  ```java
  public static void main(String[] args) {
      Collection<Intger> list1 = new ArrayList<Integer>();
      getElement(list1);
      Collection<String> list2 = new ArrayList<String>();
      getElement(list2);
  }
  public static void getElement(Collection<?> coll){}//？代表可以接收任意类型
  ```

* **通配符高级使用**----**受限泛型**

  * 之前设置泛型的时候，实际上是可以任意设置的，只要是类就可以设置。但是在JAVA的泛型中可以指定一个泛型的**上限**和**下限**。

  * **泛型的上限**：

    - **格式**： `类型名称 <? extends 类 > 对象名称`
    - **意义**：只能接收该类型及其子类

    **泛型的下限**：

    - **格式**： `类型名称 <? super 类 > 对象名称`
    - **意义**：只能接收该类型及其父类型

    ```java
    //现已知Object类，String 类，Number类，Integer类，其中Number是Integer的父类
    public static void main(String[] args) {
        Collection<Integer> list1 = new ArrayList<Integer>();
        Collection<String> list2 = new ArrayList<String>();
        Collection<Number> list3 = new ArrayList<Number>();
        Collection<Object> list4 = new ArrayList<Object>();
    
        getElement1(list1);
        getElement1(list2);//报错
        getElement1(list3);
        getElement1(list4);//报错
    
        getElement2(list1);//报错
        getElement2(list2);//报错
        getElement2(list3);
        getElement2(list4);
    
    }
    public static void getElement1(Collection<? extends Number> coll){}
    public static void getElement2(Collection<? super Number> coll){}
    ```





## 4.5 List

* ==**元素有序(存入和取出顺序一致)**，通过**索引**来访问指定元素，**允许**出现**重复**元素==
  * 由于有索引，所以==**List集合特有遍历功能**get()和size()结合的**普通for循环**==。还有迭代器、for each。
  * 其实现类都重写了`toString()`方法

- **特有方法**
  - **添加(==add==；addAll)**
    - `void add(int index,E e)`
    - `void addAll([int index,]Collection c)`
  - **删除(==remove==)**
    - `Object remove(int index)`
  - **获取(==get==；indexOf；lastIndexOf；subList)**
    - `Object get(index)`
    - `int indexOf(Object o)`
    - `int lastIndexOf(Object o)`
    - `List subList(int fromIndex,int toIndex)`
  - **修改替换(==set==)**
    - `Object set(int index,E e)`

### 4.5.1 ArrayList

- ==**底层是数组，查询快，增删慢**==。==**不同步，线程不安全，效率高**==
- 常用方法：没有特殊方法

### 4.5.2 LinkedList

- ==**底层是双向循环链表，查询慢，增删快**==。==**不同步，线程不安全，效率高**==
- 重写了`toString()`方法
- **特有方法（==操作首位元素==）**
  - **添加**
    - `void addFirst(E e)`等效于`push(E e)`
    - `void addLast(Object o)`
  - **删除**
    - `Object removeFirst()`等效于`pop(E e)`
    - `Object removeLast()`
  - **获取**
    - `Object getFirst()`
    - `Objcet getLast()`

## 4.6 Set

* ==元素**唯一**==。与`Collection`方法一致，**==没有索引，只可以迭代器或for each==**
* ==使用Set集合保存**自定义对象**，这个对象**必须重写**`hashCode()`和`equal()`方法==
* 其实现类都重写了`toString()`方法

### 4.6.1 HashSet

- ==底层数据结构是**哈希表**(**元素为链表或红黑树的数组**，实际上是一个HashMap实例)，查询快。**自动按HashCode排序**，但迭代出的元素顺序和存入顺序**不一致**。==

  - 哈希表：在**JDK1.8之前**，哈希表底层采用**数组+链表**实现，即使用链表处理冲突，同一hash值的链表都存储在一个链表里。但是当位于一个桶中的元素较多，即hash值相等的元素较多时，通过key值依次查找的效率较低。而**JDK1.8中**，哈希表存储采用**数组+链表+红黑树**实现，当链表长度超过阈值（8）时，将链表转换为红黑树，这样大大减少了查找时间。

    - "重地"和""通话""元素不同，但哈希值相同，**哈希冲突**

    ![](images\哈希表.png)

- ==**哈希表元素唯一性**底层依赖两个方法==：**`hashCode()`和`equals()`**。

  - 要使用HashSet**存储自定义类型对象**，必须**==重写==**这两方法来建立属于当前对象的比较方式

![](images\哈希流程图.png)

### 4.6.2 LinkedHashSet

- 继承HashSet，底层数据结构是**==链表和哈希表（数组+链表/红黑树），元素迭代顺序和存入顺序一致==**

### 4.6.3 TreeSet

- 底层数据结构是==**红黑树(是一个自平衡二叉树)，并且有序**，使用TreeSet保存自定义元素，这个元素**必须实现Comparable接口**或构造时**必须提供Comparator实现类**==

  - 元素唯一性通过红黑树存储时确定，相同元素丢弃， **根据比较的返回值是否是0来决定**
  - 元素的顺序通过红黑树存储，并通过**中（根）序遍历展示**

- **保证元素的排列方式：**

  1. **自然排序(元素具备比较性)**

     让元素所属的类实现**Comparable接口**，写compareTo方法

  2. **比较器排序(集合具备比较性)**

     让集合构造方法接收**Comparator**的**实现类对象**，重写compare方法

     **s1-s2升序，s2-s1降序**



## 4.7 Map

- ==**将键映射到值的对象**==。一个映射不能包含重复的键，每个键最多只能映射到一个值（==**键唯一，值可重复**==）

- ==使用Map集合存储**自定义对象作为key的元素**，必须**重写**`hashCode()`和`equals()`方法==

- 其实现类都重写了`toString()`方法

- **常用方法**

  - **添加(==put==)**
    - `V put(K key,V value)` 
      1. 如果键是第一次存储，就直接存储元素，**返回以前的值**null
      2. 如果键不是第一次存储，就替换掉以前的值，并**返回以前的值**
  - **删除(==clear==；==remove==)**
    - `void clear()`                    删除所有映射关系
    - `V remove(Object key`)     根据键删除键值对元素，key为空返回null，否则返回值
  - **获取(==get==；keySet；values；==entrySet==；==Stream中foreach==**）
    - `V get(Object key)`              根据键获取值
    - `Set<K> keySet()`                 返回所有键的集合
    - `Collection<V> values()`     返回所有值的集合
    - `Set<Map.Entry<K,V>> entrySet()`    返回映射关系的Set集合
  - **判断(==isEmpty==；==containsKey==；containsValue)**
    - `boolean isEmpty()`            是否为空
    - `boolean containsKey(Object key)`   是否包含指定键值
    - `boolean containsValue(Object value)`  是否包含指定值
  - **容量(size)**
    - `int size()`      返回映射中键值对的对数

- **遍历方法**

  - ==**JDK1.8及以后推荐使用Stream的`foreach()`方法，lambda表达式**==

  - ==**键值对(entrySet()/getKey()/getValue()**，获取存储键值对对象的Set结合。推荐此方法。==

    Map集合一创建，就会在Map集合中创建一个Entry对象，用来记录键值对对象（`Map.Entry<K,V>`)

    ```java
    Set<Map.Entry<String, String>> set = hm.entrySet();
    for(Map.Entry<String, String> entry:set) {
    	System.out.println(entry);//1=hello
    	System.out.println(entry.getKey()+"---"+entry.getValue());//1---hello
    }
    ```

  - **键找值(keySet()/get())**

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

  - **values方法(存储Map中值的Collection集合)**

    ```java
    Collection<String> values = hm.values();
    for(String value:values){
        System.out.println(value);
    }
    ```

### 4.7.1 HashMap

- ==底层是**哈希表（数组+链表/红黑树）**，迭代出的元素顺序和存入顺序**不一致**。**自动按Key的HashCode排序**==
- HashMap和Hashtable区别
  - HashMap：线程不安全，效率高，允许null键和null值
  - Hashtable：线程安全，效率低，不允许null键和null值

### 4.7.2 LinkedHashMap

- 继承HashMap，==底层是**链表和哈希表**，迭代出的元素顺序和存入顺序**一致**==

### 4.7.3 TreeMap

- ==底层是**红黑树(自平衡二叉树)，**基本对象自动排序，若使用TreeMapt保存自定义元素，Key**必须实现Comparable接口**或构造时**必须提供Comparator实现类**==

  - 元素唯一性通过红黑树存储时确定，相同元素丢弃， **根据比较的返回值是否是0来决定**
  - 元素的顺序通过红黑树存储，并通过**中（根）序遍历展示**

- 保证元素的排列方式：

  1. **自然排序(元素具备比较性)**

     让元素所属的类实现**Comparable接口**，重写compareTo方法

  2. **比较器排序(集合具备比较性)**

     让集合构造方法接收**Comparator**的**实现类对象**，重写compare方法

     **s1-s2升序，s2-s1降序**



## 4.8 Properties

- `java.util.Properties ` 继承自` Hashtable` ，来表示一个持久的**属性集**。它使用**键值结构**存储数据，每个键及其对应值都是一个**字符串**。`Properties`可以**保存在流中或从流中加载**。
- ==**特有功能**==
  - `Object setProperty(String key,String value)`   添加元素，调用的父类的put方法
  - `String getProperty(String key)`   获取元素
  - `Set<String> stringPropertyNames()`   获取所有键的集合
- ==**和IO流结合的方法**==
  - 把**键值对形式的文本**文件内容**加载**到集合中
    - `void load(InputStream inStream)`：不能读取含有中文的键值对
    - `void load(Reader reader)`：能读取含有中文的键值对，**所以一般用字符流**
  - 把集合中的数据**存储**到文本文件中
    - `void store(OutputStream out,String comments)`：不能写中文
    - `void store(Writer writer,String comments)`：**可以写中文**
      - comments说明保存文字的用途，不能使用中文会乱码，默认是Unicode编码。一般空串`""`

> 文本中的数据，必须是键值对形式，默认就是字符串，不用加双引。可使用=、空格等分隔。#为注释。

```java
public static void myLoad() throws IOException {
	BufferedInputStream bis = new BufferedInputStream(new FileInputStream("prop.txt"));
	Properties prop = new Properties();
	prop.load(bis);
	Set<String> set = prop.stringPropertyNames();
	for (String key : set) {
		if (key.equals("lisi")) {
			prop.setProperty(key, "100");//还需要保存到指定文件，略。方法同下方myStore()。
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





## 4.9 JDK9对集合添加的优化

* Java 9，添加了几种集合工厂方法，更方便创建少量元素的Collection、Map实例。新的**List**、**Set**、**Map**的静态工厂方法可以更方便地创建集合的**不可变实例（存储个数确定时使用）**。

  ```java
  Set<String> str1=Set.of("a","b","c");  
  //str1.add("c");  这里编译的时候不会错，但是执行的时候会报错，因为是不可变的集合  
  System.out.println(str1);  
  Map<String,Integer> str2=Map.of("a",1,"b",2);  
  System.out.println(str2);  
  List<String> str3=List.of("a","b");  
  System.out.println(str3);  
  ```

  > 注意：
  >
  > 1、of()方法只是Map，List，Set这三个接口的静态方法，其父类接口和子类实现并没有这类方法，比如    HashSet，ArrayList等等；
  >
  > 2、返回的集合是不可变的；List不能使用Collections遍历
  >
  > 3、Set和Map接口在调用of方法时，不能有重复的元素，否则会抛出异常



## 习题

### 统计字符串中字符个数

```java
private static void getStringNum(String s) {
    TreeMap<Character, Integer> map = new TreeMap<>();
    char[] chars = s.toCharArray();
    for (char c : chars) {
        if (!map.containsKey(c)) {
            map.put(c, 1);
        } else {
            Integer integer = map.get(c);
            map.put(c, ++integer);
        }
    }
    System.out.println(map);
}
```



### 斗地主洗牌发牌看牌

* HashMap按牌从大到小存储索引和牌面，并将索引存储到ArrayList中
* 洗牌洗的是ArrayList。发牌时将ArrayList的索引发给TreeSet，以便排序
* 遍历TreeSet并在HashMap中取牌

```java
public static void getPuke() {
    Map<Integer, String> map = new HashMap<>();
    List<Integer> list = new ArrayList<>();
    map.put(0, "大王");
    map.put(1, "小王");
    list.add(0);
    list.add(1);
    List<String> shuZi = List.of("2", "A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3");
    List<String> huaSe = List.of("♠", "♥", "♣", "♦");
    int index = 2;
    for (String s : shuZi) {
        for (String h : huaSe) {
            map.put(index, h + s);
            list.add(index);
            index++;
        }
    }
    Collections.shuffle(list);

    TreeSet<Integer> player1 = new TreeSet<>();
    TreeSet<Integer> player2 = new TreeSet<>();
    TreeSet<Integer> player3 = new TreeSet<>();
    TreeSet<Integer> diPai = new TreeSet<>();

    for (int i = 0; i < list.size(); i++) {
        if (i >= 51) {
            diPai.add(list.get(i));
        } else if (i % 3 == 0) {
            player1.add(list.get(i));
        } else if (i % 3 == 1) {
            player2.add(list.get(i));
        } else if (i % 3 == 2) {
            player3.add(list.get(i));
        }
    }

    lookPuke("player1", player1, map);
    lookPuke("player2", player2, map);
    lookPuke("player3", player3, map);
    lookPuke("dipai", diPai, map);
}

private static void lookPuke(String name, TreeSet<Integer> player, Map<Integer, String> map) {
    System.out.print(name + "的牌：");
    //遍历TreeSet的Lambda表达式
    player.forEach(i -> {
        System.out.print(map.get(i) + " ");
    });
    System.out.println();
}
```





# 5 异常

* 异常指的是程序在执行过程中，出现的非正常的情况，最终会导致JVM的非正常停止。

## 5.1 异常的体系

* 异常的根类是`java.lang.Throwable`，其下有两个子类：`java.lang.Error`与`java.lang.Exception`，平常所说的异常指`java.lang.Exception`。

  ![](images\异常体系.png)

* **Throwable中的常用方法：**
  * `public void printStackTrace()`打印异常的**详细信息**。

    包含了异常的类型，异常的原因 ，还包括异常出现的位置，在开发和调试阶段，都得使用printStackTrace

  * `public String getMessage()`获取发生异常的**原因**。提示**给用户**的时候,就提示错误原因。

## 5.2 异常的分类

我们平常说的异常就是指Exception，因为这类异常一旦出现，我们就要对代码进行更正，修复程序。

**异常(Exception)的分类**：根据在编译时期还是运行时期去检查异常。Java语言规范将派生于**Error**类和**RuntimeException**类的所有异常称为**非受查异常**，其他为**受查异常**

- **编译时期异常**：checked异常。在编译时期就会检查，如果没有处理异常则编译失败。(IO、日期格式化异常)
- **运行时期异常**：runtime异常。在编译时期运行异常不会被检测(不报错)。在运行时期检查异常。(数学异常)

![](images\异常的分类.png)

​	

## 5.3 异常的处理

### 5.3.1 抛出异常throw

在编写程序时，我们必须要考虑程序出现问题的情况。比如，在**定义方法**时，方法需要**接受参数**。那么，当调用方法使用接受到的参数时，首先需要**先对参数数据进行合法的判断**，数据若**不合法**，就应该**告诉调用者**，传递合法的数据进来。这时需要使用**抛出异常**的方式来告诉调用者。

* 在java中，提供了一个throw关键字，==**throw用在方法内，抛出一个指定的异常对象**==

  * **创建一个异常对象**，封装一些提示信息(信息可以自己编写)。

  * 通过关键字**throw**将这个异常对象告知给调用者，并结束当前方法的执行

    ```java
    throw new 异常类名(参数);
    ```

* 注意：

  * throw关键字必须写在**方法的内部**
  * throw关键字后边new的对象必须是**Exception或者Exception的子类对象**

  * throw关键字抛出指定的异常对象,我们就必须处理这个异常对象
    * throw关键字后边创建的是**RuntimeException**或是**其子类对象**,**可以不处理**,默认交给JVM处理
    * throw关键字后边创建的是**编译异常**(写代码的时候报错),我们就**必须处理**,要么throws,要么try...catch

**【面试】**throw语句后不能跟其他代码，否则永远执行不到，编译错误

```java
try {
    throw new Exception();
    System.out.println("怎么也执行不到，编译失败");
} catch (Exception e) {
    e.printStackTrace();
}
```



### 5.3.2 Objects非空判断

Objects工具类提供的判断对象是否合法`Objects.requireNonNull`，为空则抛异常，否则返回该对象。<a href="#Objects">详见此</a>



### 5.3.3 声明异常 throws

==关键字**throws**运用于**方法声明之上**,用于**表示当前方法不处理异常**,而是**提醒**该方法的**调用者来处理异常**(抛出异常).==

```java
修饰符 返回值类型 方法名(参数) throws 异常类名1,异常类名2…{   }
```

* 注意：
  * throws关键字必须写在**方法声明处**
  * throws关键字后边声明的异常必须是**Exception或者是Exception的子类**
  * 方法内部如果**抛出了多个异常对象**,那么throws后边必须也**声明多个异常**
    * 如果抛出的多个异常对象有**子父类关系**,那么**直接声明父类异常即可**
  * **调用**了一个声明**抛出异常的方法**,我们就必须的**处理**声明的异常
    * 要么继续使用**throws**声明抛出,交给方法的调用者处理,最终交给JVM
    * 要么**try...catch**自己处理异常



### 5.3.4 捕获异常 try…catch

**捕获异常**：Java中对异常有针对性的语句进行捕获，可以对出现的异常进行指定方式的处理。

```java
try {
    //可能产生异常的代码
} catch (异常类名  变量名) {
    //异常的处理逻辑,异常异常对象之后,怎么处理异常对象
    //记录日志/打印异常信息/继续抛出异常
}
...
catch (异常类名 变量名) {

}
```

* 注意：
  * try中可能会抛出多个异常对象,那么就可以使用多个catch来处理这些异常对象
    * 如果try中产生了异常，那么就会执行catch中的异常处理逻辑，执行完毕catch中的处理逻辑,继续执行try...catch之后的代码
    * 如果try中没有产生异常，那么就不会执行catch中异常的处理逻辑，执行完try中的代码,继续执行try...catch之后的代码
* 多个异常使用捕获该如何处理
  * 多个异常分别处理
  * ==多个异常**一次捕获，多次处理**（若捕获的异常**有子父类关系**，**父类放下面**；**没有**可以放在**一个catch中**）==
  * 多个异常一次捕获一次处理
* **运行时异常**被抛出**可以不处理**。即不捕获也不声明抛出。







### 5.3.5 finally 代码块

**finally**：`try`中异常语句后的代码不被执行，**必须要执行的**可以放在`finally`中，如**释放系统资源**。但是当在`try...catch...`中**执行`System.exit(0)`**(表示**==退出当前Java虚拟机==**)，**`finally`==才不会执行==**

语法：`try...catch....finally`，不能单独使用

* ==如果**finally有return语句**，将**覆盖**原始的返回值，永远返回finally中的值。一般应避免该情况==

  ```java
  public static int fin() {
      int a = 10;
      try {
          return a;
      } catch (Exception e) {
          System.out.println(e.getMessage());
      } finally {
          a = 40;
          return a; //最终返回40
      }
  }
  ```


### 5.3.6 子父类异常注意事项

* ==父类的方法**抛出或不抛出**异常，子类重写的方法抛出的异常必须**小于等于父类抛出的异常**==
  * 父类的方法**没有异常抛出**，子类重写的方法**不能有异常抛出**。若产生异常则捕获处理
  * 父类的方法**抛出**一个或多个**异常**，**子类**重写的方法**抛出的异常**必须**与父类相同**或是**其子类**或**不抛**



## 5.4 自定义异常

- 自定义的异常类**继承`Exception`或`RuntimeException`**

- 定义**空参构造**方法和**带异常信息的构造**方法

  ```java
  public class MyException extends Exception/*RuntimeException*/ {
      public MyException() {
      }
  
      public MyException(String message) {
          super(message);
      }
  }
  ```




## 练习

* **【面试】**

  1. `throw`和`throws`区别

     - throw：在方法体中，并且抛出一个异常对象。程序执行到t此时立即停止，它后面的语句都不执行。

          抛出的是**异常对象** ，说明这里**肯定有异常**产生。一般用于自定义异常，体现在选择语句中

     - throws：在方法声明上，后面跟异常的类名，可以是多个，调用者处理

          **声明方法有异常**，是一种**可能性**，这个异常不一定会产生

  2. `final`,`finally`,`finallize`区别

     - `final`：最终意思，可以修饰类、成员变量、成员方法。<a href="#final">详见此</a>

     - `finally`：异常处理，用于释放资源，finally中的代码一定会被执行，除非执行之前jvm退出

     - `finalize`：Object类的一个方法，用于垃圾回收




# 6 多线程（并发）

## 6.1 并发与并行

* **并发**：指两个或多个事件在**同一个时间段内**发生，**逻辑上**同时发生

* **并行**：指两个或多个事件在**同一时刻**发生（同时发生），**物理上**同时发生



## 6.2 进程与线程

* ==**进程**：是指**内存中运行的一个应用程序**==，每个进程都有一个独立的内存空间，一个应用程序可以同时运行多个进程；**进程也是程序的一次执行过程，是系统运行程序的基本单位**；系统运行一个程序即是一个进程从创建、运行到消亡的过程。

* ==**线程**：**线程是进程中的一个执行单元**==，负责当前进程中程序的执行，一个进程中至少有一个线程。 **是程序使用CPU的最基本单位**。

  * **多线程**：**一个进程中有多个线程**的应用程序也可以称之为多线程程序。**提高程序运行效率、CPU使用率**。
    * 好处：效率高，多个线程间互不影响
    * 弊端：线程太多效率降低

  简而言之：一个程序运行后至少有一个进程，一个进程中可以包含多个线程 。应用程序的执行都是**CPU在多个线程间快速切换**完成的，这个切换是随机的。

* **线程调度模型**

    1. **分时调度模型**：所有线程轮流使用CPU的使用权，平均分配每个线程占用CPU的时间片
    2. ==**抢占式调度模型**==：优先让优先级高的线程使用CPU，如果线程的优先级相同，那么会随机选择一个，优先级高的线程获取的CPU时间片相对多一些（Java使用）



## 6.3 多线程的实现

### 6.3.1 多线程的原理

> **Java程序运行原理（多线程）**：由Java命令启动JVM（相当于启动了一个进程），接着由该进程创建启动多个线程，至少两个线程可以分析出来：**执行main()函数的主线程**，该线程的任务代码都定义在main函数中，**负责垃圾回收的线程**。

* ==多线程执行时，其实**每一个执行线程**都有一片自己**所属的栈内存**空间。进行**方法的压栈和弹栈**。==

![](images\栈内存原理图.png)

### 6.3.2 Thread（java.lang）

==**构造方法**==

- **`Thread()`**：分配一个新的线程对象。
- **`Thread(String name)`**：分配一个指定名字的新的线程对象
- **`Thread(Runnable target)`**：分配一个带有指定目标新的线程对象。
- **`Thread(Runnable target,String name)`**：分配一个带有指定目标新的线程对象并指定名字

**常用方法**

- `void run()`：**此线程要执行的任务在此处定义代码**
- `void start()`：**此线程开始执行**；Java虚拟机调用此线程的run方法
- `static Thread currentThread()`：返回对当前**正在执行的线程对象的引用**

- `String getName()`：**获取当前线程名称**

- `void setName()`：**设置当前线程名称**，或通过线程**类的有参构造设置**

- `static void sleep(long millis)`：使**当前正在执行的线程**以指定的毫秒数**暂停**


### 6.3.3 多线程的实现方式

- **继承Thread类**

  1. 继承Thread类。可以写无参和带参构造以便直接定义线程名称。
  2. @Override重写Thread类的`run()`方法，将线程的任务代码封装到`run()`方法中
  3. 创建Thread类的子类对象
  4. 调用**`start()`开启线程**，JVM调用该线程的**`run()`**方法执行（多次启动一个线程非法，即使执行完毕）
- **实现Runnable接口（常用）**

  1. 定义类实现Runnable接口
  2. @Override重写接口中的`run()`方法，将线程的任务代码封装到`run()`方法中
  3. 通过Thread类创建线程对象，并将Runnable接口的子类对象作为Thread类的构造函数的参数进行传递。**线程的任务都封装在Runnable接口实现类对象的run方法中，所以要在线程对象创建时就必须明确要运行的任务**
  4. 调用**`start()`开启线程**，JVM调用该线程的**`run()`**方法执行（多次启动一个线程非法，即使执行完毕）
- ==**区别（实现Runnable接口的好处）**==
  1. 避免了java**单继承的局限性**
  2. 适合多个相同程序的代码去**处理同一个资源**
  3. 增加程序的健壮性，实现**解耦**操作，代码可以被多个线程共享，**代码和线程独立**
  4. **线程池**只能放入实现Runable或Callable类线程，不能直接放入继承Thread的类 
- ==**run()和start()的区别**==
  - run()：仅仅是封装被线程执行的代码，直接调用是普通方法。
  - start()：首先启动了线程，然后再由jvm去调用该线程的run()方法。


### 6.3.4 匿名内部类实现多线程

* **继承Thread类**

  ```java
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

  ```java
  //lambda表达式实现
  new Thread(() -> {
      for (int i = 0; i < 100; i++) {
          System.out.println(Thread.currentThread().getName() + "---" + i);
      }
  }).start();
  //普通实现
  new Thread(new Runnable() {
  	@Override
  	public void run() {
  		for (int i = 0; i < 100; i++) {
  			System.out.println(Thread.currentThread().getName() + "---" + i);
  		}
  	}
  }).start();
  ```



## 6.4 线程安全

### 6.4.1 线程安全问题

* **买票问题**

  * 相同的票出现多次：CPU的一次操作必须是原子性的
  * 出现负数的票：随机性和延迟导致

* **线程安全问题产生原因**

  - 多个线程在操作共享数据

  - 操作共享数据的代码有多条

    **当一个线程在执行操作共享数据的多条代码过程中，其他线程参与了运算，就会导致**



### 6.4.2 线程同步

要解决上述多线程并发访问一个资源的安全性问题：也就是解决重复票与不存在票问题，Java中提供了**同步机制 (synchronized)**来解决。有三种方式完成同步操作：**同步代码块**、**同步方法**、**锁机制**。

* **同步的优缺点：**
  * **好处**：解决线程的安全问题
  * **弊端**：相对**降低效率**，因为同步外的线程都会判断同步锁；若有同步嵌套容易产生**死锁**

#### (1) 同步代码块

* `synchronized`关键字可以用于**方法中的某个区块中**，表示只对这个区块的**资源实行互斥访问**

  * **同步锁**：也称对象锁或对象**监视器**
    * 锁对象可以是**任意类型**
    * 多个线程对象要使用**同一把锁**

  ```java
  @Override
  public void run() {
      while (true) { //卖票窗口一直开着
          synchronized (this) {
              if (ticket > 0) { //有票才卖
                  try {
                      Thread.sleep(10);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  System.out.println(Thread.currentThread().getName() + "-->正在卖第" + ticket + "张票");
                  ticket--;
  ...
  ```

#### (2) 同步方法

* 使用`synchronized`修饰的方法就叫做同步方法，保证A线程执行该方法的时候其他线程只能在方法外等着。

  * **同步锁是谁**?
    * 对于**非static方法**，同步锁就是**this**
    * 对于**static方法**，我们使用当前方法所在类的字节码对象(**类名.class**)

  ```java
  public synchronized void sellTicket(){
       if (ticket > 0) { ....  }
  }
  ```



#### (3) Lock锁

* `java.util.concurrent.locks.Lock` **接口**机制提供了比synchronized代码块和synchronized方法更广泛的锁定操作，同步代码块/同步方法具有的功能Lock都有，除此之外更强大，更体现面向对象。 

* **Lock接口的实现类`ReentrantLock`**

* **Lock锁也称同步锁**，加锁与释放锁方法化了，如下：

  * public void **lock**()：**加同步锁**

  * public void **unlock**()：**释放同步锁**

    ```java
    private int count = 100;
    private Lock lock= new ReentrantLock();
    
    @Override
    public void run() {
        while(true) {
            lock.lock(); //加同步锁
            if (ticket > 0) { 
                try {
                    Thread.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "-->正在卖第" + ticket + "张票");
                    ticket--; 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock(); //无论程序是否异常，都会释放锁
                }
    ...
    ```



#### 死锁问题(哲学家就餐)

- 指两个或两个以上的线程在执行的过程中，因**争夺资源**产生的一种**互相等待**现象

  ```java
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
  ...
  ```






## 6.5 线程状态

### 6.5.1 线程状态概述

当线程被创建并启动以后，它既不是一启动就进入了执行状态，也不是一直处于执行状态。在线程的生命周期中， 有几种状态呢？在API中 **`java.lang.Thread.State`** **这个枚举中给出了六种线程状态**：

| 线程状态                    |                       导致状态发生条件                       |
| --------------------------- | :----------------------------------------------------------: |
| **NEW**(新建)               |       线程刚被创建，但是并未启动。还没调用start方法。        |
| **Runnable**(可运行)        | 线程可以在java虚拟机中运行的状态，可能正在运行自己代码，也可能没有，这取决于操作系统处理器。 |
| **Blocked**(锁阻塞)         | 当一个线程试图获取一个对象锁，而该对象锁被其他的线程持有，则该线程进入Blocked状 态；当该线程持有锁时，该线程将变成Runnable状态。 |
| **Waiting**(无限等待)       | 一个线程在等待另一个线程执行一个（唤醒）动作时，该线程进入Waiting状态。进入这个 状态后是不能自动唤醒的，必须等待另一个线程调用notify或者notifyAll方法才能够唤醒。 |
| **Timed Waiting**(计时等待) | 同waiting状态，有几个方法有超时参数，调用他们将进入Timed Waiting状态。这一状态将一直保持到超时期满或者接收到唤醒通知。带有超时参数的常用方法有Thread.sleep(1000) 、 Object.wait。 |
| **Terminated**(被终止)      | 因为run方法正常退出而死亡，或者因为没有捕获的异常终止了run方法而死亡。 |

![](images\线程状态.png)



### 6.5.2 线程调度

- 线程**优先级**：通过`getPriority()`获取，通过`setPriority()`设置。

  Java线程默认优先级是5(1-10低到高,Thread的**静态**常量NORM_PRIORITY(MIN/MAX))。

- 线程**让步**：通过静态方法`Thread.yield()`设置

  **暂停**当前正在执行的线程对象（系统指定的毫秒数），并执行其他线程。**转为就绪状态**，该线程不会失去任何监视器的所有权（不释放锁），不会阻塞该线程。不确保真正让出，很少用。

- ==线程**休眠**==：通过静态方法`Thread.sleep(long millis)`设置

  让当前正在执行的线程**休眠**（**暂停**执行）系统指定的毫秒数，该线程不丢失任何监视器的所属权（**不释放锁**），休眠结束回到**就绪状态**

- ==线程**插队**==：线程调用方法`th.join()`

  **等待该线程终止**，其他线程才可以抢占资源。

- **后台线程（守护线程，如坦克大战）**：通过`th.setDaemon(true)`设置

  当前正在运行的线程都是后台线程时，JVM退出，该方法必须**在启动线程前调用**

- ==**中断**线程==：通过`th.interrupt()`设置

  **请求终止线程**，仅设置了一个标志位，中断一个不在活动状态（阻塞）的线程没意义并会抛异常

  * 静态方法interrupted()-->**会清除中断标志位**
  * 普通方法isInterrupted()-->**不会清除中断标志位**







## 6.6 线程间通信

**多个线程在处理==同一个资源==，但是处理的动作（线程的任务）却不相同**。

多个线程在处理同一个资源，并且任务不同时，需要线程通信来帮助解决线程之间对同一个变量的使用或操作。 就 是多个线程在操作同一份数据时， 避免对同一共享变量的争夺。也就是我们需要通过一定的手段使各个线程能有效 的利用资源。而这种手段即—— 等待唤醒机制。

### 6.6.1 等待唤醒机制

就是在一个线程进行了规定操作后，就进入等待状态（wait()）， 等待其他线程执行完他们的指定代码过后 再将 其唤醒（notify()）;在有多个线程进行等待时， 如果需要，可以使用 notifyAll()来唤醒所有的等待线程。 wait/notify 就是线程间的一种协作机制。

* **Object类**（由于任意锁）中提供了三个方法：（这些方法必须通过**同一个锁对象在同步中调用**）
  * `wait([long timeout])`：==**等待**并立即**释放锁**，线程被阻塞。**被唤醒并获得锁后从这里执行后续代码**==
  * `notify()`：==随机唤醒单个线程，被通知线程不能立即恢复执行线程，**重新请求同步锁**==
  * `notifyAll()`：唤醒所有线程

> 哪怕只通知了一个等待的线程，被通知线程也不能立即恢复执行，因为它当初中断的地方是在同步块内，而 此刻它已经不持有锁，所以她需要再次尝试去获取锁（很可能面临其它线程的竞争），成功后才能在当初调 用 wait 方法之后的地方恢复执行。
> 总结如下：
> 如果能获取锁，线程就从 WAITING 状态变成 RUNNABLE 状态； 否则，从 wait set 出来，又进入 entry set，线程就从 WAITING 状态又变成 BLOCKED 状态 



### 6.6.2 生产者消费者问题

> 包子铺线程生产包子，吃货线程消费包子。当包子没有时（包子状态为false），吃货线程等待，包子铺线程生产包子 （即包子状态为true），并通知吃货线程（解除吃货的等待状态）,因为已经有包子了，那么包子铺线程进入等待状态。 接下来，吃货线程能否进一步执行则取决于锁的获取情况。如果吃货获取到锁，那么就执行吃包子动作，包子吃完（包 子状态为false），并通知包子铺线程（解除包子铺的等待状态）,吃货线程进入等待。包子铺线程能否进一步执行则取决于锁的获取情况

```java
public static void main(String[] args) {
    BaoZi baoZi = new BaoZi();
	//消费者
    new Thread(() -> {
        while (true) {
            synchronized (baoZi) {
                System.out.println("老板，买10个包子。");
                if (!baoZi.flag) {
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("包子真好吃，吃完了。");
                baoZi.flag = false;
                baoZi.notify();
                System.out.println("===================");
            }
        }
    }).start();
	//生产者
    new Thread(() -> {
        while (true) {
            synchronized (baoZi) {
                if (baoZi.flag) {
                    try {
                        baoZi.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("新包子出炉了！！！");
                baoZi.flag = true;
                baoZi.notify();
            }
        }
    }).start();
}
```





## 6.7 线程池

* **线程池**：其实就是一个**容纳多个线程的容器**，其中的**线程可以反复使用**，省去了频繁创建线程对象的操作， 无需反复创建线程而消耗过多资源。

  Java里面线程池的顶级接口是 `java.util.concurrent.Executor `，但是严格意义上讲它只是一个执行线程的工具。真正的线程池接口是 `java.util.concurrent.ExecutorService` 。

* 官方建议**使用`java.util.concurrent.Executors `线程池工厂类**来创建线程池对象

  * `static ExecutorService newFixedThreadPool(int nThreads)`：**返回线程池对象**。(创建的是有界线程池,也就是池中的线程个数可以指定最大数量)

  * `Future<?> submit(Runnable task)`：**获取**线程池中的某个**线程对象**，**并执行run()**。

    ```java
    ExecutorService pool = Executors.newFixedThreadPool(2);//创建一个线程池对象，2个线程
    //可以执行Runnable对象或者Callable对象代表的线程
    pool.submit(new MyRunnable()); //pool-1-thread-1创建了一个新的线程执行
    //线程池会一直开启,使用完了线程,会自动把线程归还给线程池,线程可以继续使用
    pool.submit(new MyRunnable()); //pool-1-thread-1创建了一个新的线程执行
    pool.submit(new MyRunnable()); //pool-1-thread-2创建了一个新的线程执行
    pool.shutdown() ////结束线程池,不建议执行
    ```

    * **实现Callable来实现多线程**（有返回值，可以抛异常）





# 7 IO

* 流，代表任何有能力产出数据的数据源对象或有能力接收数据的接收端对象，屏蔽了实际I/O设备处理细节

## 7.1 File

* File类是==**文件和目录路径名**==的抽象表示，主要用于文件和目录的创建、查找和删除等操作。

  File类时与系统无关的类，所有操作系统都可以使用。file(文件)、directory(文件夹/目录)、path(路径)

  * 路径（不区分大小写，在Windows中使用反斜杠，由于它又是转义字符，所以使用**两个反斜杠**表示）
    * **绝对路径**：**完整的路径，以盘符开始**
    * **相对路径**：简化的路径，相对**当前项目**的**根目录**

### 7.1.1 静态成员变量

* `static String pathSeparator` 与系统有关的==**路径分隔符**==，为了方便，它被表示为一个字符串。
* `static char pathSeparatorChar` 与系统有关的路径分隔符。
  * Windows中是分号“`;`”；Linux中是冒号“`:`” 
* `static String separator` 与系统有关的默认==**文件名称分隔符**==，为了方便，它被表示为一个字符串。
* `static char separatorChar` 与系统有关的默认名称分隔符。
  * Windows中是反斜杠“`\`”；Linux中是正斜杠“`/`” 

### 7.1.2 构造方法

- `File(String pathname)` 
  - `pathname`可以以**文件**或**文件夹**结尾；**相对**或**绝对**路径；**存在**或**不存在**；
- `File(File/String parent,String child)` 

### 7.1.3 常用方法

* **获取**
  - `String getAbsolutePath()`：返回此File的==**绝对路径名**==
  - `String getPath()`：将此**File转换为路径名**，==**构造中传递的路径**==。`toString`底层是`getPath`
  - `String getName()`：返回由此**File表示的文件或目录**的名称，==**构造中结尾部分文件或文件夹**==
  - `long length()`：返回由此**File表示的文件的长度**，==**构造中指定的文件的大小(字节)**==
    - 若构造给出的**路径不存在**或**路径为文件夹**，这个方法**返回0**
  - `long lastModified()`：返回此抽象路径名表示的==**文件最后一次被修改的时间(毫秒值)**==
  - `String getParent()`：==**父目录**==（除最后一个名称外）的路径名字符串；若没有指定父目录则返回 null。 
* **判断**
  - `boolean exists()/isFile()/isDirectory`：**判断路径是否==存在==/是否以==文件==结尾/是否以==目录==结尾**
    - 若路径不存在，直接调用后俩方法（这俩方法互斥）则都返回false，所以一般先判断路径是否存在。
  - `boolean canRead()/canWrite()/isHidden()`     **判断是否可读/可写/隐藏**
* **创建**
  * `boolean creatNewFile()`：当且仅当具有该名称的==**文件不存在时创建一个新的空文件**==
    * 文件路径必须存在，否则抛异常；只能创建文件；创建成功返回true，否则返回false
  * `File creatTempFile(String prefix,String suffix)`     **创建临时文件**
  * `boolean mkdir()`：**目录不存在时创建由此File表示的目录**。**单级目录**，创建时检查父目录存在否
  * `boolean mkdirs()`：==**目录不存在时创建由此File表示的目录，包括父目录**==。**单/多级目录**
* ==**删除文件或目录**==
  * `boolean delete()`
    1. 如果构造中没**写盘符路径**，默认在项目路径下(**相对路径**)
    2. 如果构造中路径不存在，不会删除
    3. 删除一个文件夹，文件夹中不能有内容 
    4. 不走回收站
* ==**重命名renameTo**==
  * `boolean renameTo(File dest)`     **路径相同为改名，路径不同为改名并剪切**
* ==**遍历目录**(带参为**文件名过滤器**，需匿名内部类实现)==
  * 遍历的是构造中的路径。若**路径不存在**或**不是目录结尾**，则抛出**空指针异常**
  * `String[] list([File[name]Filter filter])`：获取指定所有文件或文件夹**名称**的**字符串数组**
  * `File[] listFiles([File[name]Filter filter])`：获取指定所有文件或文件夹的**File数组**

### 7.1.4 文件过滤器

* `java.io.FileFilter`接口
  * `boolean accept(File pathname)  ` ：测试指定抽象路径名是否应该包含在某个路径名列表中。
    * `File pathname`：要测试的**抽象路径名(File对象)**
* `java.io.FilenameFilter`接口
  * `boolean accept(File dir, String name)`：测试指定文件是否应该包含在某一文件列表中。
    * `dir`：被找到的文件**所在的目录**。
    * `name`：**文件的名称**。
* 分析：`FileFilter`、`FilenameFilter`都是只有一个方法的接口，因此可以用**lambda表达式**简写。

* **批量修改文件名**

  ```java
  File srcFilePath = new File("D:\\lol");
  
  //以下4选1
  //FileFilter接口匿名内部类实现
  File[] files = file.listFiles(new FileFilter() {
      @Override
      public boolean accept(File f) {
          return f.isFile() && f.getName().endsWith(".txt");
      }
  });
  //FileFilter接口lambda实现
  File[] files = file.listFiles(f -> f.isFile() && f.getName().endsWith(".txt"));
  
  
  
  //FilenameFilter接口匿名内部类实现
  File[] fileList = srcFilePath.listFiles(new FilenameFilter() {
  	@Override
  	public boolean accept(File dir, String name) {
  		return new File(dir, name).isFile() && name.endsWith(".txt");
  	}
  });
  //FilenameFilter接口lambda实现
  File[] files = file.listFiles((dir, name) -> new File(dir,name).isFile()&&name.endsWith(".txt"));
  
  
  File destFilePath = new File("D:\\lol\\new");
  destFilePath.mkdirs();
  for (File file : fileList) {
  	String newName = "new_".concat(file.getName());
  	File newFile = new File(destFilePath, newName);
  	file.renameTo(newFile);
  }
  ```


### 7.1.5 递归

* **方法定义中调用方法本身的现象**。还分有直接递归和间接递归(a调b，b调c，c调a)。

* **注意：**

  1. 要**有出口**可以停止，否则就是死递归
  2. **次数不能过多**，否则内存溢出
  3. **构造方法不能**递归使用

* ==递归使用**前提**：**当调用方法的时候，方法主体不变，每次调用方法的参数不同**==

* **递归输出指定目录下指定文件的绝对路径**

  ```java
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

  ```java
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





## 7.2 IO分类

Java中I/O操作主要指使用`java.io`包下的内容进行输入、输出操作。**输入**叫做**读取**数据，**输出**叫做作**写出**数据。

根据数据的流向分为：

- **输入流** ：把数据从**`其他设备`**上读取到**`内存`**中的流。 
- **输出流** ：把数据从**`内存` **中写出到**`其他设备`**上的流。

根据数据的类型分为：

- **字节流** ：以字节为单位，读写数据的流。
- **字符流** ：以字符为单位，读写数据的流。

![](images\IO流.PNG)



## 7.3 InputStream、OutputStream

字节流可以传输任意文件数据。无论使用什么样的流对象，底层传输的始终为二进制数据，使用字节流。

`java.io.InputStream `**抽象类**是表示字节输入流的所有类的超类，可以读取字节信息到内存中。

`java.io.OutputStream `**抽象类**是表示字节输出流的所有类的超类，将指定的字节信息写出到目的地。

**`System.in 和 System.out`也分别是`InputStream、OutputStream`**，可作为参数传递

### 7.3.1 FileInputStream、FileOutputStream

**FileInputStream：文件输入流**

1. **创建字节输入流对象**

   - `FileInputStream fis = new FileInputStream(File file/String name)`

     > 当你创建一个流对象时，**必须传入一个文件路径**，如果没有该文件会抛出`FileNotFoundException` 

2. **调用`read()`方法**

   - `int read()`：一次读取一个**字节**，提升为int类型，读取到文件末尾，返回`-1`
   - `int read(byte[] b)`：一次读取b字节长的**字节数组(缓冲）**，返回读取到的有效字节个数，读取到末尾时返回`-1` 

3. **`close()`释放资源**(关闭流对象。垃圾回收、通知系统释放跟该文件相关资源)

   > 流的关闭原则：先开后关，后开先关

**FileOutputStream：文件输出流**

1. **创建字节输出流对象(第二个参数可选)**

   - `FileOutputStream fos = new FileOutputStream(File file/String name,boolean append)`

     > 如果**没有**这个文件**会创建**该文件。如果**有**这个文件，根据**append**变量的值决定（**true为追加**，**false为清空**），若**构造不提供**这个参数，默认会**清空**这个文件的数据。

2. **调用`write()`方法(将字符串转为字节数组，`getBytes()`)**

   - `void write(int b)`：一次写一个**字节**，虽然参数为int类型，但是只会保留一个字节的信息写出
   - `void write(byte[] bytes)`：一次写一个**字节数组**。也可以使用`String`类的**`getBytes()`**
   - `void write(byte[] b,int offset,int len)`： 一次写一个**字节数组的一部分**
     - `int len`中**len代表实际读取字节的长度**，每次读取替换上次字节数组

3. **`close()`释放资源**(由于流会占用内存所以关闭流对象。垃圾回收、通知系统释放跟该文件相关资源)

   > 流的关闭原则：先开后关，后开先关

> - 回车符`\r`和换行符`\n` ：
>   - 回车符：回到一行的开头（return）。
>   - 换行符：下一行（newline）。
> - 系统中的换行：
>   - Windows系统里，每行结尾是 `回车+换行` ，即`\r\n`；
>   - Unix系统里，每行结尾只有 `换行` ，即`\n`；
>   - Mac系统里，每行结尾是 `回车` ，即`\r`。从 Mac OS X开始与Linux统一。
> - 在计算机中中文的存储分两个字节
>   - 如果写的第一个字节是正数(0-127),那么显示的时候会查询ASCII表
>   - 如果写的第一个字节是负数，那第一个和第二个字节组成一个中文显示，查询系统默认码表(GBK)



### 7.3.2 BufferedInputStream、BufferedOutputStream

* **缓冲流**的基本原理是在创建流对象时，会**创建一个内置的默认大小的==缓冲区数组==**，**通过缓冲区读写**，减少系统IO次数，从而提高读写的效率。可以调用`flush`，或`close`自动刷新并关闭
* 构造方法（可以指定缓冲区大小，但一般不指定）
  * `BufferedInputStream(InputStream in[,int size])`：参数可以`FileInputStream`
  * `BufferedOutputStream(OutputStream out[,int size])`： 参数可以是`FileOuputStream`




## 7.4 Reader、Writer

`java.io.Reader`**抽象类**是表示用于读取字符流的所有类的超类，可以读取字符信息到内存中。

`java.io.Writer `**抽象类**是表示用于写出字符流的所有类的超类，将指定的字符信息写出到目的地。

### 7.4.1 字符编码

**字符编码`Character Encoding`** : 就是一套自然语言的字符与二进制数之间的对应规则。

- ==**编码**（人能看懂的变成看不懂的）**字符** --> **字节**==
- ==**解码**（人看不懂的变成看得懂的）**字节** --> **字符**==

**字符集`Charset`**：编码表。是系统支持的所有字符的集合，包括各国家文字、标点符号、图形符号、数字等。

* 当指定了**编码**，它所对应的**字符集**自然就指定了，所以**编码**才是我们最终要关心的

  ![](images\1_charset.jpg)

- ==**ASCII字符集**==
  - ASCII（American Standard Code for Information Interchange，美国信息交换标准代码）是基于拉丁字母的一套电脑编码系统，用于显示现代英语，主要包括控制字符（回车键、退格、换行键等）和可显示字符（英文大小写字符、阿拉伯数字和西文符号）。
  - 基本的ASCII字符集，使用**7位（bits）表示一个字符**，**共128字符**。ASCII的**扩展字符集使用8位**（bits）表示一个字符，共256字符，方便支持欧洲常用字符。
- **ISO-8859-1字符集**
  - 拉丁码表，别名Latin-1，用于显示欧洲使用的语言，包括荷兰、丹麦、德语、意大利语、西班牙语等。
  - ISO-8859-1使用单字节编码，兼容ASCII编码。
- **GBxxx字符集**
  - GB就是国标的意思，是为了显示中文而设计的一套字符集。
  - **GB2312**：简体中文码表。一个小于127的字符的意义与原来相同。但两个大于127的字符连在一起时，就表示一个汉字，这样大约可以组合了包含7000多个简体汉字，此外数学符号、罗马希腊的字母、日文的假名们都编进去了，连在ASCII里本来就有的数字、标点、字母都统统重新编了两个字节长的编码，这就是常说的"全角"字符，而原来在127号以下的那些就叫"半角"字符了。
  - ==**GBK**==：最常用的中文码表。是在GB2312标准基础上的扩展规范，使用了**双字节**编码方案，共收录了21003个汉字，完全兼容GB2312标准，同时支持繁体汉字以及日韩汉字等。
  - **GB18030**：最新的中文码表。收录汉字70244个，采用多字节编码，每个字可以由**1个、2个或4个字节**组成。支持中国国内少数民族的文字，同时支持繁体汉字以及日韩汉字等。
- ==**Unicode字符集**==
  - Unicode编码系统为表达任意语言的任意字符而设计，是业界的一种标准，也称为统一码、标准万国码。
  - 它**最多使用4个字节**的数字来表达每个字母、符号，或者文字。有三种编码方案，UTF-8、UTF-16和UTF-32。最为常用的UTF-8编码。
  - **UTF-8编码**，可以用来表示Unicode标准中任何字符，它是电子邮件、网页及其他存储或传送文字的应用中，优先采用的编码。互联网工程工作小组（IETF）要求所有互联网协议都必须支持UTF-8编码。所以，我们开发Web应用，也要使用UTF-8编码。它使用**一至四个字节**为每个字符编码，编码规则：
    1. 128个US-ASCII字符，只需一个字节编码。
    2. 拉丁文等字符，需要二个字节编码。 
    3. 大部分**常用字（含中文），使用三个字节**编码。
    4. 其他极少使用的Unicode辅助字符，使用四字节编码。



### 7.4.2 InputStreamReader、OutputStreamWriter

**转换流**：为了方便操作中文数据，将**字节**流**转**为**字符**流使用，它也是个字符流。==唯一可以**指定字符集**的流==

**InputStreamReader**

* 构造方法
  * `InputStreamReader(InputStream is)`：默认编码，IDE中默认为UTF-8
  * `InputStreamReader(InputStream is,String charsetName)`：指定字符集，不区分大小写
* 成员方法方法
  * `int read()`:一次读取一个字符
  * `int read(char[] chs)`:一次读取一个字符数组

**OutputStreamWriter**

* 构造方法
  * `OutputStreamWriter(OutputStream os)`：默认编码，IDE中默认为UTF-8
  * `OutputStreamWriter(OutputStream os,String charsetName)`：指定字符集，不区分大小写
* 成员方法
  * `void write(int c)`:写一个字符
  * `void write(char[] chs)`:写一个字符数组
  * `void write(char[] chs,int off,int len)`:写一个字符数组的一部分
  * `void write(String str)`:写一个字符串
  * `void write(String str,int off,int len)`:写一个字符串的一部分

```java
InputStreamReader isr = new InputStreamReader(new FileInputStream("test_1.txt"), "UTF-8");
OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("test_2.txt"), "UTF-8");
char[] chs = new char[1024];
int len = 0;
while ((len = isr.read(chs)) != -1) {
	osw.write(chs, 0, len);
}
isr.close();
osw.close();
```



### 7.4.3 FileReader、FileWriter

**简化构造方法编写**，但只能**读取系统默认编码**格式（IDEA中设置为UTF-8）的文件

**FileReader**

* 构造方法
  * `FileReader(File file/String fileName)`
* 成员方法
  * `int read(char ch)`：每次读取一个字符的数据，提升为int类型，读取到文件末尾，返回`-1`
  * `int read(char[] chs)`：每次读取`chs`的长度个字符到数组中，**返回读取到的有效字符个数**，读取到末尾时，返回`-1` 

**FileWriter**

* 构造方法
  * `FileWriter(File file/String fileName[, boolean append])`：默认编码，不能指定。是否续写
* 成员方法
  * `void write(int c)`:写一个字符
  * `void write(char[] chs)`:写一个字符数组
  * `void write(char[] chs,int off,int len)`:写一个字符数组的一部分
  * `void write(String str)`:写一个字符串
  * `void write(String str,int off,int len)`:写一个字符串的一部分



### 7.4.4 BufferedReader、BufferedWriter

* 缓冲流的基本原理是在创建流对象时，会**创建一个内置的默认大小的缓冲区数组**，**通过缓冲区读写**，减少系统IO次数，从而提高读写的效率。

* 构造方法（可以指定缓冲区大小，但一般不指定）

  * `BufferedReader(Reader in[,int size])`
  * `BufferedWriter(Writer out[,int size])`

* 特有成员方法

  * `String readLine()`:一次**读取一行字符串**，包含该行内容的字符串，**不包含任何行终止符**，如果已到达流末尾，则返回`null` 

  * `void newLine()`:写一个**换行符**，根据系统来决定换行符






### 7.4.5 关闭和刷新

因为字符流是先写入**内存缓冲区**的原因，如果不关闭输出流，无法写出字符到文件中。但是关闭的流对象，是无法继续写出数据的。如果我们既想写出数据，又想继续使用流，就需要`flush` 方法了。

- `flush` ：刷新缓冲区，流对象可以继续使用。
- `close `:先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了。



## 7.5 IO异常的处理

### 7.5.1 JDK7之前的处理

使用`try...catch...finally` 代码块，处理异常部分

```java
public class HandleException1 {
    public static void main(String[] args) {
        // 声明变量
        FileWriter fw = null;
        try {
            //创建流对象
            fw = new FileWriter("fw.txt");
            // 写出数据
            fw.write("黑马程序员"); //黑马程序员
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fw != null) { //若不做判断，有可能fw为null，就不能调用方法，抛空指针异常
                    fw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
```

### 7.5.2 JDK7的处理

使用JDK7优化后的**`try-with-resource` **语句（即**try后可以跟括号“()”**），该语句确保了==**每个资源在语句结束时自动关闭**==。所谓的资源（resource）是指在程序完成后，必须关闭的对象。格式如下：

```java
try (创建流对象语句，如果有多个使用';'隔开) {
	// 读写数据
} catch (IOException e) {
	e.printStackTrace();
}
```

### 7.5.3 JDK9的处理

JDK9中`try-with-resource` 的改进，对于**引入对象**的方式，支持的更加简洁。被引入的对象，同样可以自动关闭，无需手动close，我们来了解一下格式：(但是外部new的流对象/资源还得异常处理，此时用JDK7的即可)

```java
// 被final修饰的对象
final Resource resource1 = new Resource("resource1");
// 普通对象
Resource resource2 = new Resource("resource2");

// 引入方式：直接引入
try (resource1; resource2) {
     // 使用对象
}
```



## 7.6 习题

1. **复制文本文件的5种方式(采用字符流)**

   ```java
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

   ```java
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

   ```java
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

   ```java
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

   ```java
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

   ```java
   File srcPath = new File("day19-code\\src\\abc");
   File destPath = new File("day19-code\\src\\copy_abc");
   if (!destPath.exists())
       destPath.mkdirs();
   File[] fileList = srcPath.listFiles(f -> f.isFile() && f.getName().endsWith(".java"));
   for (File file : fileList) {
       String newName = file.getName().replace(".java", ".jad");
       File newFile = new File(destPath, newName);
       copyFile(file, newFile);//看下题
   }
   ```

7. **复制多级文件夹(整个文件夹复制到目的文件夹目录下**

   ```java
   public static void main(String[] args) throws IOException {
       File srcPath = new File("day19-code\\src\\abc");
       File destPath = new File("day19-code\\src\\copy_abc");
       copyDirectory(srcPath,destPath);
   }
   
   private static void copyDirectory(File srcPath, File destPath) throws IOException {
       if (srcPath.isDirectory()) {
           File newDirectory = new File(destPath, srcPath.getName());
           newDirectory.mkdirs();
           File[] files = srcPath.listFiles();
           for (File f : files) {
               copyDirectory(f, newDirectory);
           }
       } else {
           copyFile(srcPath, new File(destPath, srcPath.getName()));
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

8. **将一个文件中的字符串每一行排序后写入另一个文件中**

   ```java
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



## 7.7 序列化、反序列化

### 7.7.1 概述

Java 提供了一种对象**序列化**的机制。用一个字节序列可以表示一个对象，该字节序列包含该`对象的数据`、`对象的类型`和`对象中存储的属性`等信息。字节序列写出到文件之后，相当于文件中**持久保存**了一个对象的信息。 

反之，该字节序列还可以从文件中读取回来，重构对象，对它进行**反序列化**。`对象的数据`、`对象的类型`和`对象中存储的数据`信息，都可以用来在内存中创建对象。

![](images\3_xuliehua.jpg)



### 7.7.2 ObjectOutputStream、ObjectInputStream

**ObjectOutputStream：**把**对象**按照流的方式写入文本文件保存或者在网络中传输。对象的**序列化流**

* 构造方法
  * `public ObjectOutputStream(OutputStream out) `
* 成员方法
  * `final void writeObject (Object obj)`：**将指定的对象写出**

**ObjectInputStream：**把文本文件中或网络中的流对象数据还原成**对象**。对象的**反序列化流**

* 构造方法
  * `ObjectInputStream(InputStream in)`
* 成员方法
  * `final Object readObject ()`：**读取一个对象**

==一个**对象要想序列化**，必须满足两个条件==

* 该类必须**实现`java.io.Serializable ` 接口**，`Serializable` 是一个**标记接口（没有要实现的方法）**，不实现此接口的类将不会使任何状态序列化或反序列化，会抛出`NotSerializableException` 。
* **该类的所有属性必须是可序列化的**。如果**有一个属性不需要被序列化**的，则该属性必须注明是**瞬态**的，使**用`transient` 关键字修饰**。被static修饰的不能被序列化，不需要添加transient关键字。

**注意：**

> 对于JVM==可以**反序列化对象**，它必须是**能够找到class文件的类**==。如果找不到该类的class文件，则抛出异常 **`ClassNotFoundException`**。
>
> 另外，当JVM反序列化对象时，能找到class文件，但是==**class文件在序列化对象之后发生了修改**==，那么反序列化操作也会失败，**抛出一个`InvalidClassException`异常**。发生这个异常的原因如下：
>
> * 该类的序列版本号与从流中读取的类描述符的版本号不匹配 
> * 该类包含未知数据类型 
> * 该类没有可访问的无参数构造方法 
>
> `Serializable` 接口给==需要序列化的类，提供了一个**序列版本号**==。**`serialVersionUID` **该版本号的目的在于**验证序列化的对象和对应类是否版本匹配**。类型必须是`static final long  serialVersionUID ` 





## 7.8 PrintStream

平时我们在控制台打印输出，是调用`print`方法和`println`方法完成的，这两个方法都来自于`java.io.PrintStream`类，该类能够方便地打印各种数据类型的值，是一种便捷的输出方式。

- 只负责数据的输出，不负责数据的读取
- 永远不会抛出`IOException`
- 有特有方法`print`、`println`，输出任意类型值，以字符串显示，不会查询字符集

**构造方法**

* `public PrintStream(String fileName)`： 使用指定的文件名创建一个新的打印流。

**改变打印流向**

* `System.out`就是`PrintStream`类型的，只不过它的流向是系统规定的，打印在控制台上。不过，既然是流对象，我们就可以玩一个"小把戏"，改变它的流向。

* 使用`System.setOut`方法改变输出语句的目的地改为参数中传递的打印流的目的地

  `static void setOut(PrintStream out)`：重新分配“标准”输出流。

  ​          



## 7.9 其他流

### LineNumberReader

* **LineNumberReader使用**(行号从setLineNumber+1开始)

  ```java
  LineNumberReader lnr = new LineNumberReader(new FileReader("name.txt"));
  lnr.setLineNumber(-1);
  String line = null;
  while((line=lnr.readLine())!=null) {
  	System.out.println(lnr.getLineNumber()+":"+line);
  }
  lnr.close();
  ```

### 标准输入输出流(System.in、System.out)

* System类下面有这样的两个字段

  * in 标准输入流
  * out 标准输出流

* 三种键盘录入方式

  * A:main方法的args接收参数

  * B:**System.in通过BufferedReader进行包装**

     ```java
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     ```

  * C:Scanner

     `Scanner sc = new Scanner(System.in)`

* 输出语句的原理和如何使用字符流输出数据

  * 原理

    ```java
    System.out.println("helloworld");
                 
    PrintStream ps = System.out;
    ps.println("helloworld");   
    ```

  * **把System.out用字符缓冲流包装一下使用**

      ```java
      BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
      ```

### 随机访问流(RandomAccessFile)

* 可以按照文件指针的位置写数据和读数据 入流

* 构造方法

  ```java
  RandomAccessFile raf = new RandomAccessFile(File file/String name,String mode)//mode可以使rw
  ```

* 案例：

  * 写数据
  * 读数据
  * 获取和改变文件指针的位置

### 合并流(SequenceInputStream)

* 把多个输入流的数据写到一个输出流中。

* 构造方法：

  `SequenceInputStream(InputStream s1, InputStream s2)` 

  `SequenceInputStream(Enumeration<? extends InputStream> e)`

## 7.10 NIO（暂时未完成）

- **Java7中新加的类**

  - **Path接口**表示路径

  - **Paths类**中`Paths.get()`接收一个或多个字符串，表示路径，**可以不存在**

  - Files类中有静态方法

    - `public static long copy(Path source,OutputStream out)throws IOException`   **复制文件**

      ```java
      Files.copy(Paths.get("数据库.txt"), new BufferedOutputStream(new 			                   FileOutputStream("copy数据库.txt")));
      ```





# 8 网络编程

* **软件结构**
  * **C/S结构** ：全称为Client/Server结构，是指客户端和服务器结构。常见程序有ＱＱ、迅雷等软件。
  * **B/S结构** ：全称为Browser/Server结构，是指浏览器和服务器结构。常见浏览器有谷歌、火狐等。

## 8.1 网络编程概述

- **计算机网络**

  是指将地理位置不同的具有独立功能的多台计算机及其外部设备，通过通信线路连接起来，在网络操作系统，网络管理软件及网络通信协议的管理和协调下，实现资源共享和信息传递的计算机系统。

- **网络编程**

  就是用来实现网络互连的不同计算机上运行的程序间可以进行数据交换。

- **网络模型**

  - OSI（Open System Interconnection开放系统互连）参考模型
  - TCP/IP参考模型

  ![](images\网络参考模型.png)

  - 链路层：链路层是用于定义物理传输通道，通常是对某些**网络连接设备的驱动协议**，例如针对光纤、网线提供的驱动。
  - 网络层（互联网层）：网络层是整个TCP/IP协议的**核心**，它主要用于将传输的**数据进行分组**，将分组数据**发送到目标**计算机或者网络。
  - 传输层：主要使网络程序进行通信，在进行**网络通信**时，可以采用**TCP**协议，也可以采用**UDP**协议。
  - 应用层：主要负责**应用程序的协议**，例如HTTP协议、FTP协议等。

## 8.2 网络编程三要素

### 8.2.1 IP地址

- 指**互联网协议地址**（Internet Protocol Address）（网络中**计算机唯一标识**，**点分十进制**）

- 分类

  - IPv4：是一个**32位**的二进制数，通常被分为4个字节，表示成`a.b.c.d` 的形式，例如`192.168.65.100` 。其中a、b、c、d都是0~255之间的十进制整数，那么最多可以表示42亿个。

  - IPv6：由于互联网的蓬勃发展，IP地址的需求量愈来愈大，但是网络地址资源有限，使得IP的分配越发紧张。为了扩大地址空间，拟通过IPv6重新定义地址空间，采用**128位**地址长度，**每16个字节一组，分成8组十六进制数**，表示成`ABCD:EF01:2345:6789:ABCD:EF01:2345:6789`，号称可以为全世界的每一粒沙子编上一个网址，这样就解决了网络地址资源数量不够的问题。

  - **组成：网络号段+主机号段**

    A类：第一号段为网络号段+后三段的主机号段
    ​	一个网络号：256 * 256 * 256 = 16777216台主机
    B类：前二号段为网络号段+后二段的主机号段
    ​	一个网络号：256 * 256 = 65536台主机
    C类：前三号段为网络号段+后一段的主机号段
    ​	一个网络号：256台主机

  - **分类：**

    A类	`1.0.0.1---127.255.255.254`(1)10.X.X.X是私有地址(在互联网上不使用，在局域网络中的地址)

       		  					   (2)127.X.X.X是保留地址，用做循环测试用的。

    B类	`128.0.0.1---191.255.255.254`  172.16.0.0-172.31.255.255私有地址。169.254.X.X保留地址。
    C类	`192.0.0.1---223.255.255.254`	192.168.X.X是私有地址
    D类	`224.0.0.1---239.255.255.254`
    E类	`240.0.0.1---247.255.255.254`

  - **两个DOS命令：**
    `ipconfig` 查看本机IP地址等信息
    `ping` 后面跟IP地址。测试本机与指定的IP地址间的通信是否有问题

  - **特殊的IP地址：**
    `127.0.0.1`、`localhost` 回环地址(表示本机)
    `x.x.x.255` 广播地址
    `x.x.x.0` 网络地址

  - `InetAddress类`**的概述和使用**（方便对IP地址的获取和操作）

    - **如果一个类没有构造方法：**

      A:成员全部是静态的(Math,Arrays,Collections)

      B:单例设计模式(Runtime)

      C:类中有静态方法返回该类的对象(InetAddress)

  - **InetAddress/getByName/getHostName/getHostAddress使用**

    `public static InetAddress getByName(String host)`:根据**主机名**或**IP地址**字符串得到IP地址**对象**

    `public String getHostName()`：返回主机名

    `public String getHostAddress()`:返回IP地址字符串

    ```java
    InetAddress address = InetAddress.getByName("Conanan");//或"192.168.1.6"
    		
    String name = address.getHostName();
    String addr = address.getHostAddress();
    System.out.println(name+"---"+addr);
    ```



### 8.2.2 端口号（逻辑端口）

* **正在运行的程序的标识**

- **有效端口**：两个字节组成，0~ 65535，其中0~1024系统使用或保留端口。
  1. 每个网络程序都会至少有一个逻辑端口
  2. 用于标识进程的逻辑地址，不同进程的标识
  3. 有效端口：0~ 65535，其中0~ 1024系统使用或保留端口。


> **物理端口**：网卡口

* 常用端口号：
  * HTTP协议端口号：`80`
  * 数据库：MySQL`3306`、Oracle`1521`
  * Tomcat：`8080`



### 8.2.3 传输协议

**数据报**(Datagram):网络传输的基本单位 

* 通信的规则

- **UDP**：**用户数据报协议**(User Datagram Protocol)。UDP是**无连接通信协议**，即在数据传输时，数据的发送端和接收端不建立逻辑连接。由于使用UDP协议**消耗资源小，通信效率高**，所以通常都会用于音频、视频和普通数据的传输。但在使用UDP协议传送数据时，由于UDP的**面向无连接性，不能保证数据的完整性，不可靠**，因此在传输重要数据时不建议使用UDP协议。特点:**数据打包被限制在64kb以内**，超出这个范围就不能发送了。（聊天留言，在线视频，视频会议。发短信，邮局包裹）

- **TCP**：**传输控制协议** (Transmission Control Protocol)。TCP协议是**面向连接的通信协议**，即传输数据之前，在发送端和接收端建立逻辑连接，然后再传输数据，它提供了两台计算机之间**可靠无差错**的数据传输。（下载，浏览网页。打电话，QQ聊天(你在线吗,在线,就回应下,就开始聊天了)

  在TCP连接中必须要明确客户端与服务器端，由客户端向服务端发出连接请求，每次连接的创建都需要经过“三次握手”。

  - **三次握手**：TCP协议中，在发送数据的准备阶段，客户端与服务器之间的三次交互，以保证连接的可靠。
    - 第一次握手，客户端向服务器端发出连接请求，等待服务器确认。
    - 第二次握手，服务器端向客户端回送一个响应，通知客户端收到了连接请求。
    - 第三次握手，客户端再次向服务器端发送确认信息，确认连接。
  - 完成三次握手，连接建立后，客户端和服务器就可以开始进行数据传输了

### 8.2.4 Socket

* 网络上具有唯一标识的**IP地址**和**端口号**组合在一起才能构成唯一能识别的标识符**套接字**）

- **Socket原理机制**
  - 通信的两端都有Socket
  - 网络通信其实就是Socket间的通信
  - 数据在两个Socket间通过IO传输

## 8.3 发送接收数据

### 8.3.1 UDP(DatagramSocket、Datagrampacket)

* **UDP协议接收数据**

  1. 创建接收端Socket对象（`DatagramSocket`类）
  2. 创建一个数据包(接收容器)（`DatagramPacket`类）
  3. 调用Socket对象的接收方法接收数据（`ds.receive(dp)`）
  4. 解析数据包，并显示在控制台
  5. 释放资源(接收端应该一直开着)

  ```java
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

  ```java
  DatagramSocket ds = new DatagramSocket();
  
  byte[] bys = "helloworld".getBytes();
  InetAddress address = InetAddress.getByName("Conanan");
  int port = 10086;
  DatagramPacket dp = new DatagramPacket(bys, bys.length, address, port);
  
  ds.send(dp);
  ds.close();
  ```

* **多线程实现聊天室**

  ```java
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

### 8.3.2 TCP(Socket、ServerSocket)

* **java.net.ServerSocket**：这个类**实现了服务器套接字**，该对象等待通过网络的请求。

  * `ServerSocket(int port)` 使用该构造方法在**创建ServerSocket对象**时，就可以将其绑定到一个指定的端口号上，参数port就是端口号。
  * `Socket accept()` ：**侦听并接受连接**，返回一个新的Socket对象，用于和客户端实现通信。==**该方法会一直阻塞直到建立连接**==。 
  * `void close()` ：**关闭此套接字**。详细介绍如Socket中`close`方法

* **java.net.Socket**：该类**实现客户端套接字**，套接字指的是两台设备之间通讯的端点。

  - `Socket(String host, int port)`：**创建套接字对象**并将其连接到**指定主机**上的**指定端口号**
  - `InputStream getInputStream()` ： 返回此**套接字的输入流**。
    - 如果此Scoket具有相关联的通道，则生成的InputStream 的所有操作也关联该通道。
    - 关闭生成的InputStream也将关闭相关的Socket。
  - `OutputStream getOutputStream()` ： 返回此**套接字的输出流**。
    - 同上两条
  - `void close()` ：**关闭此套接字**。
    - 一旦一个socket被关闭，它不可再使用。
    - 关闭此socket也将关闭相关的InputStream和OutputStream 。 
  - `void shutdownOutput()` ： **禁用此套接字的输出流**。   
    - **任何先前写出的数据将被发送**，并且后跟 TCP 的正常连接**终止序列**。 

  > ==**服务器使用客户端的流和客户端交互**==
  >
  > ==IO流中各**read**方法在**没有输入可用时将阻塞**，并且**在通道中输入流是读不到结束标记**的，所以需要**shutdownOutput**方法来发送数据并**带上终止序列**。==

* **服务器端接收数据(ServerSocket)**

  1. 创建TCP服务器端的Socket对象
  2. 监听客户端连接
  3. 获取**套接字**的输入流，读取数据
  4. 释放资源

  ```java
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
  2. 获取**套接字**的输出流，写数据
  3. 释放资源

  ```java
  Socket s = new Socket("192.168.81.1", 60001);
  		
  OutputStream os = s.getOutputStream();
  os.write("hello java world".getBytes());
  		
  s.close();
  ```

* **文件上传优化**

  * **文件名称写死的问题**：建议使用系统时间优化，保证文件名称唯一

    ```java
    FileOutputStream fis = new FileOutputStream(System.currentTimeMillis()+".jpg")
    ```

  * **循环接收的问题**：服务端不应保存一个文件就关闭，需要不断的接收不同用户的文件，使用**循环**改进

    ```java
    // 让服务器一致处于监听状态。每次接收新的连接,创建一个Socket
    while（true）{
        Socket accept = serverSocket.accept();
        ......
    }
    ```

  * **效率问题**：服务端，在接收大文件时耗费时间长，此时不能接收其他用户上传，使用**多线程**技术优化

    ```java
    while（true）{
        Socket accept = serverSocket.accept();
        // accept 交给子线程处理.
        new Thread(() -> {
            ......
                InputStream bis = accept.getInputStream();
            ......
        }).start();
    }
    //并且由于接口中没有抛异常，所以实现类中也不能抛异常，只能try...catch
    ```

#### 上传文件带反馈（多线程）

```java
//Server
ServerSocket socket = new ServerSocket(8888);
while (true) { //服务端循环接收不同客户端请求
    Socket s = socket.accept(); //监听到客户端请求并建立连接
    new Thread(() -> {  //利用lambda实现多线程，让每个用户的线程不受其他线程影响。并由于接口没有抛异常，所以以下用try捕获
        BufferedOutputStream bos = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(s.getInputStream());
            File file = new File("F:\\upload"); //检查上传文件的目录是否存在
            if (!file.exists())
                file.mkdirs();
            String name = System.currentTimeMillis() + ".jpg"; //解决文件名写死问题
            bos = new BufferedOutputStream(new FileOutputStream(file+"\\"+name));
            //将从网络通道中的文件读取并写入磁盘
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
		   //回应客户端上传完毕
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write("上传完毕");
            bw.newLine();
            bw.flush();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                s.close(); //close本身也抛异常。由于是多线程，每个线程使用的不同socket，所以socket可以关闭
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }).start();
}

//Client
Socket s = new Socket("127.0.0.1",8888);

BufferedInputStream bis = new BufferedInputStream(new FileInputStream("day22-code\\src\\666.jpg"));
BufferedOutputStream bos = new BufferedOutputStream(s.getOutputStream());
//将本地磁盘文件读取并写入网络通道中
byte[] bytes = new byte[1024];
int len = 0;
while ((len=bis.read(bytes))!=-1){
    bos.write(bytes,0,len);
}
s.shutdownOutput();//发送数据并带上终止序列，以便服务器读取时可以读到终止序列
//读取网络通道中服务器回应的数据并打印
BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
String line = br.readLine();
System.out.println(line);

bis.close();
s.close();
```







# 9 函数式编程

在数学中，函数就是有输入量、输出量的一套计算方案，也就是“拿什么东西做什么事情”。相对而言，**面向对象**过分强调“必须**通过对象的形式来做事情**”，而**函数式思想**则尽量忽略面向对象的复杂语法——**强调做什么及结果，而不是以什么形式做**。

面向对象的思想：做一件事情,找一个能解决这个事情的对象,调用对象的方法,完成事情.

函数式编程思想：只要能获取到结果,谁去做的,怎么做的都不重要,重视的是结果,不重视过程

- 冗余的Runnable代码分析

  - Thread 类需要 Runnable 接口作为参数，其中的抽象 run 方法是用来指定线程任务内容的核心；
  - 为了指定 run 的方法体，不得不需要 Runnable 接口的实现类； 
  - 为了省去定义一个 RunnableImpl 实现类的麻烦，不得不使用匿名内部类； 
  - 必须覆盖重写抽象 run 方法，所以方法名称、方法参数、方法返回值不得不再写一遍，且不能写错； 
  - 而实际上，似乎只**有方法体才是关键所在**。 

- 匿名内部类的好处与弊端

  - 一方面，匿名内部类可以帮我们省去实现类的定义；
  - 另一方面，匿名内部类的语法——确实太复杂了！ 

- 编程思想转换

  **做什么**，而不是怎么做，只要能够更好地达 到目的，过程与形式其实并不重要。2014年3月Oracle所发布的Java 8中，加入了Lambda表达式的重量级新特性，为我们打开了新世界的大门。 



## 9.1 函数式接口

函数式接口在Java中是指：==**有且仅有一个抽象方法的接口**==。可以适用于Lambda使用的接口，只有确保接口为函数式接口，Java中的Lambda才能顺利地进行**推导**。 

> 备注：“语法糖”是指使用更加方便，但是原理不变的代码语法。例如在遍历集合时使用的for-each语法，其实 底层的实现原理仍然是迭代器，这便是“语法糖”。从应用层面来讲，Java中的Lambda可以被当做是匿名内部 类的“语法糖”，但是二者在原理上是不同的。

- **格式**

  ```java
  修饰符 interface 接口名称 { 
      /*public abstract*/ 返回值类型 方法名称(可选参数信息);     
      // 其他非抽象方法内容，默认方法，静态方法，私有方法
  }
  ```

- **@FunctionalInterface注解**

  Java 8中专门为函数式接口引入的注解，可用于一个接口的定义上。一旦使用该注解来定义接口，**编译器将会强制检查**该接口是否确实有且仅有一个抽象方法，否则将会报错。不使用该注解也可以定义函数式接口。



## 9.2 函数式编程

### 9.2.1 Lambda格式

- Lambda表达式的标准格式为

  ```java
  (参数类型 参数名称) ‐> { 代码语句 }
  ```

  - 小括号内的语法与传统方法**参数列表一致**：无参数则留空；多个参数则用逗号分隔。 
  - -> 是新引入的语法格式，代表**指向动作**。 
  - 大括号内的语法与传统**方法体**要求基本一致。 

- 无参数返回值

  ```java
  new Thread( () ‐> {
      	System.out.println("多线程任务执行！")).start(); // 启动线程
  	}
  ).start();	
  ```

- 有参数和返回值

  ```java
  Arrays.sort(students,(s1, s2)->{ //参数类型可以从students推导出，so可以省略
      return s1.getAge()-s2.getAge();
  });
  ```

  ```java
  Collections.sort(students, (o1, o2) -> o1.getAge()-o2.getAge()); //{}内只有一行表达式，可以省略{}和return
  ```

  ```java
  Arrays.sort(students, Comparator.comparingInt(Student::getAge)); //更简洁的写法,但{}中语句多时不能简写 
  ```

- **可推导可省略**：Lambda强调的是“做什么”而不是“怎么做”，凡可以根据上下文推导得知的信息，都可以省略。
  - **小括号内参数的类型**可以省略
  - 如果**小括号内有且仅有一个参**，则**小括号可以省略**
  - 如果**大括号内有且仅有一个语句**，则无论是否有返回值，都可以省略大括号、return关键字及语句分号。

- Lambda的语法非常简洁，完全没有面向对象复杂的束缚。但是使用时有几个问题需要特别注意： 

  - 使用Lambda必须具有==**函数式接口**==。

    无论是JDK内置的 Runnable 、 Comparator 接口还是自定义的接口，只有当接口中的抽象方法存在且唯一 时，才可以使用Lambda。 

  - 使用Lambda必须==**具有上下文推断**==。

    方法的参数或局部变量类型必须为**Lambda对应的接口类型**，才能使用Lambda作为该接口的实例。



### 9.2.2 Lambda的延迟执行

有些场景的代码执行后，结果不一定会被使用，从而造成性能浪费。而Lambda表达式是延迟执行的，这正好可以 作为解决方案，提升性能。 

- **性能浪费的日志案例**

  ```java
  public static void main(String[] args) throws FileNotFoundException {
      String a = "hello";
      String b = "world";
      log(1,a+b);
  }
  public static void log(int level,String msg) {
      if (level==1) {
          System.out.println(msg);
      }
  }
  ```

  > 无论级别是否满足要求，作为` log` 方法的第二个参数，三个字符串一定会首先被拼接并传入方法内，然后才会进行级别判断。如果级别不符合要求，那么字符串的拼接操作就白做了，存在**性能浪费**。
  >
  > 备注：**SLF4J**是应用非常广泛的日志框架，它在记录日志时为了解决这种性能浪费的问题，并不推荐首先进行字符串的拼接，而是**将字符串的若干部分作为可变参数传入方法中**，仅在日志级别**满足要求**的情况下**才会进行字符串拼接**。例如： LOGGER.debug("变量{}的取值为{}。", "os", "macOS") ，其中的大括号 {} 为占位符。如果满足日志级别要求，则会将“os”和“macOS”两个字符串依次拼接到大括号的位置；否则不会进行字 符串拼接。这也是一种可行解决方案，但Lambda可以做到更好

- **体验Lambda的更优写法**

  - 函数式接口

    ```java
    @FunctionalInterface public interface MessageBuilder {
        String buildMessage();
    }
    ```

  - 然后对 `log` 方法进行改造

    ```java
    public static void main(String[] args) throws FileNotFoundException {
        String a = "hello";
        String b = "world";
        log(1, () -> a + b );
    }
    public static void log(int level,MessageBuilder mb) {
        if (level==1) {
            System.out.println(mb.buildMessage());
        }
    }
    ```

    > 只有当级别满足要求的时候，才会进行三个字符串的拼接；否则三个字符串将不会进行拼接。 
    >
    > 证明：只需在lambda大括号中打印一个语句，并让`log`方法传递的参数改为2，发现打印语句不执行



## 9.3 常用的函数式接口

JDK提供了大量常用的函数式接口以丰富Lambda的典型使用场景，它们主要在**`java.util.function`**中提供。 

`java.lang.Runnable`、`java.util.Comparator`不再上述包中，但也是函数式接口

### 9.3.1 Supplier

`java.util.function.Supplier<T> `接口仅包含一个无参的方法： `T get() `。用来**获取一个泛型参数指定类型的==对象==数据**。由于这是一个函数式接口，这也就意味着对应的Lambda表达式**需要“对外提供”一个符合泛型类型**的对象数据。这个接口也称为==**生产型接口**==。

```java
//求数组元素最大值
public static void main(String[] args) {
    int[] arr = {1, 22, 55, 333, 66};
    int m = getMax(arr,() -> {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    });
    System.out.println(m);
}

public static int getMax(int[] arr,Supplier<Integer> supplier) {
    return supplier.get();
}
```

### <span name="Consumer">9.3.2 Consumer</span>

`java.util.function.Consumer<T> `接口则正好与`Supplier`接口相反，它不是生产一个数据。==**消费型接口**==。

- **抽象方法` void accept(T t) `**

  > 意为**消费一个指定泛型的数据**。

  ```java
  public static void main(String[] args) {
      String msg = "牛逼";
      consumeString(msg,s ‐> {
          //怎么处里消费随意
      }
  }
      
  public static void consumeString(String msg, Consumer<String> consumer) {
      consumer.accept(msg);        
  }           
  ```

- **默认方法`Consumer<T> andThen(Consumer<T> c)` **

  > 如果一个方法的参数和返回值全都是 Consumer 类型，那么就可以实现效果：**消费数据的时候，首先做一个操作， 然后再做一个操作，实现组合，哪个写前面则先消费**。返回值是本身，可以用链式编程，三个、四个操作都行。这个方法就是 Consumer 接口中的default方法 andThen 。

  ```java
  public static void main(String[] args) {
      /**
       * 下面的字符串数组当中存有多条信息，请按照格式“ 姓名：XX；性别：XX。 ”的格式将信息打印出来。
       * 要求将打印姓名的动作作为第一个Consumer接口的Lambda实例，将打印性别的动作作为第二个Consumer接口的Lambda实例，
       */
      String[] arr = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男"};
      consumerString(arr,
                     s -> System.out.print("姓名：" + s.split(",")[0] + "；"),
                     s -> System.out.println("性别：" + s.split(",")[1] + "。")
      );
      
  }
  
  public static void consumerString(String[] msg, Consumer<String> consumer1, Consumer<String> consumer2) {
      for (String s : msg) {
          consumer1.andThen(consumer2).accept(s);
      }
  }
  ```



### <span name="Predicate">9.3.3 Predicate</span>

有时候我们需要==**对某种类型的数据进行判断**==，从而得到一个boolean值结果。这时可以使用 `java.util.function.Predicate<T> `接口。 

- **抽象方法： `boolean test(T t)` **

  ```java
  //判断字符串长度是否大于3
  public static void main(String[] args) {
      String msg = "hello";
      boolean b = panduan(msg, s -> s.length() > 3);
      System.out.println(b);
  
  }
  public static boolean panduan(String s, Predicate<String> predicate){
      return predicate.test(s);
  }
  ```

- **默认方法：`Predicate<T> and(Predicate<? super T> other)` **，与

- **默认方法：`Predicate<T> or(Predicate<? super T> other)` **，或

- **默认方法：`Predicate<T> negate()` **，非

- **`static <T> Predicate<T> isEqual(Object targetRef)`**，相等，根据`Objects.equals(O,O)`

  ```java
  System.out.println(Predicate.isEqual("test").test("test"));//true，返回Predicate实例，之后也可以用其他方法
  ```

  ```java
  public static void main(String[] args) {
      String msg = "hello";
      boolean b = panduan(msg,s -> s.length() > 3,s -> s.contains("w"));
      System.out.println(b);
  
  }
  public static boolean panduan(String s, Predicate<String> p1,Predicate<String> p2){
      return p1.and(p2).test(s); //and
      //return p1.or(p2).test(s); //or
      //return p1.negate().and(p2).test(s); //negate
  }
  ```

  ```java
  public static void main(String[] args) {
      /**
           * 数组当中有多条“姓名+性别”的信息如下，请通过 Predicate 接口的拼装将符合要求的字符串筛选到集合 ArrayList 中
           * 需要同时满足两个条件：1. 必须为女生； 2. 姓名为4个字。
           */
      String[] array = {"迪丽热巴,女", "古力娜扎,女", "马尔扎哈,男", "赵丽颖,女"};
      ArrayList<String> list = toList(array,
                                      s -> s.split(",")[0].length() == 4,
                                      s -> "女".equals(s.split(",")[1])
      );
      System.out.println(list);
  }
  
  public static ArrayList<String> toList(String[] arr, Predicate<String> p1, Predicate<String> p2) {
      ArrayList<String> list = new ArrayList<>();
      for (String s : arr) {
          if (p1.and(p2).test(s))
              list.add(s);
      }
      return list;
  }
  ```

### <span name="Function">9.3.4 Function</span>

`java.util.function.Function<T,R>` 接口用来==**根据一个类型的数据得到另一个类型的数据**==，前者称为前置条件， 后者称为后置条件。 

- **抽象方法： `R apply(T t) `**：根据类型T的参数获取类型R的结果。如`String`转`Integer`

- **默认方法：`Function<T,V> andThen(Function<? super R,? extends V> after)`**，用来**组合操作**

  ```java
  public static void main(String[] args) {
      /**
           * 第一个操作是将字符串解析成为int数字，
           * 第二个操作是乘以10并转为字符串。两个操作通过 andThen 按照前后顺序组合到了一 起。
           */
      String msg = "1234";
      String string = change(msg, s -> Integer.valueOf(s), s -> (s * 10)+"" /*或String.valueOf()*/);
      System.out.println(string);
  }
  
  public static String change(String msg, Function<String, Integer> f1, Function<Integer, String> f2) {
      return f1.andThen(f2).apply(msg);
  }
  ```

  ```java
  public static void main(String[] args) {
      String str = "赵丽颖,20";
      //1. 将字符串截取数字年龄部分，得到字符串；
      //2. 将上一步的字符串转换成为int类型的数字；
      //3. 将上一步的int数字累加100，得到结果int数字
      int change = change(str,
                          s -> s.split(",")[1],
                          s -> Integer.parseInt(s),
                          i -> i + 100
      );
      System.out.println(change);
  }
  
  public static int change(String s, Function<String, String> f1,
                           Function<String, Integer> f2, Function<Integer, Integer> f3) {
      return f1.andThen(f2).andThen(f3).apply(s);
  }
  ```



## 9.4 方法引用

> 在使用Lambda表达式的时候，我们实际上传递进去的代码就是一种解决方案：拿什么参数做什么操作。那么考虑 一种情况：如果我们在Lambda中所指定的操作方案，已经有地方存在相同方案，那是否还有必要再写重复逻辑？ 

### 9.4.1 方法引用符

**双冒号`::` 为引用运算符**，而它所在的**表达式被称为方法引用**。如果Lambda要表达的函数方案已经存在于某个方 法的实现中，那么则可以通过双冒号来引用该方法作为Lambda的替代者。 

**语义分析**

`System.out `对象中有一个重载的 `println(String)` 方法恰好就是我们所需要的。那么对于 `printString` 方法的函数式接口参数，对比下面两种写法，完全等效：

* Lambda表达式写法： `s -> System.out.println(s);` 
* 方法引用写法： `System.out::println`

第一种语义是指：拿到参数之后经Lambda之手，继而传递给 `System.out.println` 方法去处理。 

第二种等效写法的语义是指：直接让 `System.out` 中的 `println` 方法来取代Lambda

> 注:Lambda 中 传递的参数 一定是方法引用中 的那个方法可以接收的类型,否则会抛出异常 

**推导与省略**

如果使用Lambda，那么根据“可推导就是可省略”的原则，**无需指定参数类型**，**也无需指定的重载形式**——它们都 将被自动推导。而如果使用方法引用，也是同样可以根据上下文进行推导。

函数式接口是Lambda的基础，而方法引用是Lambda的孪生兄弟。 

### 9.4.2 通过对象名引用成员方法

与上例相同。对象已经存在，方法也存在，可以使用`对象名::方法名`替代lambda

### 9.4.3 通过类名引用静态方法

类已经存在，静态方法也存在，可以使用`类名::静态方法`替代lambda

```java
public interface Calcable {
    int calc(int num);    
}
```

```java
public static void main(String[] args) {
    method(‐10, Math::abs);        
} 
private static void method(int num, Calcable lambda) {
    System.out.println(lambda.calc(num));        
}   
```

### 9.4.4 通过super引用成员方法 

如果存在继承关系，当Lambda中需要出现super调用时，也可以使用方法引用进行替代。

```java
@FunctionalInterface
public interface Greetable {
    void greet();    
}
```

```java
public class Human {
    public void sayHello() {
        System.out.println("Hello!");        
    }
}
```

```java
public class Man extends Human {
    @Override
    public void sayHello() {
        System.out.println("大家好,我是Man!");
    }
    //定义方法method,参数传递Greetable接口
    public void method(Greetable g){
        g.greet();
    }
    public void show(){ //在main中调用此方法时，会打印Hello！
        //method(()‐>super.sayHello());
        method(super::sayHello);
    }
}
```

### 9.4.5 通过this引用成员方法 

this代表当前对象，如果需要引用的方法就是当前类中的成员方法，那么可以使用`this::成员方法`的格式来使用方法引用

```java
@FunctionalInterface
public interface Richable {
    void buy();
}
```

```java
public class Husband {
    private void buyHouse() {
        System.out.println("买套房子");
    }
    private void marry(Richable lambda) {
        lambda.buy();
    }
    public void beHappy() { //main中调用此方法，会打印 买套房子
        //marry(() ‐> this.buyHouse());
        marry(this::buyHouse);
    }
}
```

### 9.4.6 类的构造器引用

由于构造器的名称与类名完全一样，并不固定。所以构造器引用使用`类名称::new` 的格式表示

```java
public class Person {
    private String name;
    ...
}
```

```java
@FunctionalInterface
public interface PersonBuilder {
    Person buildPerson(String name);
}
```

```java
public class Demo {
    public static void printName(String name, PersonBuilder builder) {
        System.out.println(builder.buildPerson(name).getName());
    }
    public static void main(String[] args) {
        //printName("赵丽颖", name ‐> new Person(name));
        printName("赵丽颖", Person::new);
    }
}
```

### 9.4.7 数组的构造器引用

数组也是`Object` 的子类对象，所以同样**具有构造器**，只是语法稍有不同

```java
@FunctionalInterface
public interface ArrayBuilder {
    int[] buildArray(int length);
}
```

```java
public class Demo {
    private static int[] initArray(int length, ArrayBuilder builder) {
        return builder.buildArray(length);
    }
    public static void main(String[] args) {
        //int[] array = initArray(10, length ‐> new int[length]);
        int[] array = initArray(10, int[]::new);
    }
}
```







## 习题

- Pedicate接口使用

  ```java
  Integer[] arr = {-12345, 9999, 520, 0, -38, -7758520, 941213};
  
  Predicate<Integer> p1 = i -> i >= 0; //使用lambda表达式创建Predicate对象p1,p1能判断整数是否是自然数
  Predicate<Integer> p2 = i -> Math.abs(i) > 100; //使用lambda表达式创建Predicate对象p2,p2能判断整数的绝对值是否大于100
  Predicate<Integer> p3 = i -> i % 2 == 0; //使用lambda表达式创建Predicate对象p3,p3能判断整数是否是偶数
  
  int count1 = 0;
  int count2 = 0;
  int count3 = 0;
  int count4 = 0;
  for (Integer i : arr) {
      if (p1.test(i))
          count1++;
      if (p1.negate().test(i))
          count2++;
      if (p2.and(p3).test(i))
          count3++;
      if (p1.negate().or(p3).test(i))
          count4++;
  }
  System.out.println("自然数个数:"+count1);
  System.out.println("负整数个数:"+count2);
  System.out.println("绝对值大于100的偶数的个数:"+count3);
  System.out.println("负整数或偶数的数的个数:"+count4);
  ```

- Function接口使用

  ```java
  /**
           * 1.	使用lambda表达式分别将以下功能封装到Function对象中
           * a)	求Integer类型ArrayList中所有元素的平均数
           * b)	将Map<String,Integer>中value存到ArrayList<Integer>中
           */
  Function<ArrayList<Integer>, Integer> avg = list -> {
      int sum = 0;
      for (Integer i : list) {
          sum += i;
      }
      return sum / list.size();
  };
  
  Function<Map<String, Integer>, ArrayList<Integer>> change = map -> {
      ArrayList<Integer> list = new ArrayList<>();
      map.entrySet().stream().forEach(s -> list.add(s.getValue()));//或用values，addAll
      return list;
  };
  
  HashMap<String, Integer> students = new HashMap<>();
  students.put("岑小村", 59);
  students.put("谷天洛", 82);
  students.put("渣渣辉", 98);
  students.put("蓝小月", 65);
  students.put("皮几万", 70);
  
  int avg_score = change.andThen(avg).apply(students);
  System.out.println(avg_score);
  ```




# 10 Stream API

在Java 8中得益于Lambda所带来的函数式编程，引入一个全新的Stream概念，用于**解决**已有**集合类库**既有弊端。

Java 8的Lambda让我们可以更加专注于做什么（What），而不是怎么做（How），这点此前已经结合内部类进行 了对比说明。结合集合中遍历操作，可以发现循环遍历的弊端：

* for循环的语法就是“怎么做” 
* for循环的循环体才是“做什么”

> 如果希望对集合中的元素进行筛选过滤：
>
> * 将集合A根据条件一过滤为子集B； 
> * 然后再根据条件二过滤为子集C。 
>
> 使用线性循环就意味着只能遍历一次，那么需多次循环来解决；并且循环是做事情的方式，而不是目的。

```java
//筛选以张开始，长度为3，遍历打印
list.stream()
    .filter(s->s.startsWith("张"))
    .filter(s->s.length()==3)
    .forEach(s -> System.out.println(s));
```



## 10.1 流式思想概述

> 注意：请暂时忘记对传统IO流的固有印象！

整体来看，流式思想类似于工厂车间的“**生产流水线**”。当需要**对多个元素进行操作（特别是多步操作）**的时候，考虑到性能及便利性，我们应该**首先拼好一个“模型”步骤方案**，然后再按照方案去**执行**它。

![](images\拼接流式模型.PNG)

这张图中展示了过滤、映射、跳过、计数等多步操作，这是一种**集合元素的处理方案**，而方案就是一种“**函数模 型**”。图中的**每一个方框都是一个“流”**，调用指定的方法，可以**从一个流模型转换为另一个流模型**。而右侧的数字 3是终结果。

==这里的 filter 、 map 、 skip 都是在**对函数模型进行操作**，**集合元素并没有真正被处理**。**只有当终结方法 count 执行**的时候，**整个模型才会按照指定策略执行操作**。而这得益于**Lambda的延迟执行特性**。==

> 备注：**“Stream流”其实是一个集合元素的函数模型**，它并不是集合，也不是数据结构，**其本身并不存储任何 元素（或其地址值）**。

**Stream（流）是一个来自数据源的元素队列**

* **元素**是**特定类型的对象**，形成一个队列。 Java中的Stream并不会存储元素，而是按需计算。 
* **数据源**：流的来源。 可以是集合，数组 等。 

和以前的Collection操作不同， Stream操作还有两个基础的特征： 

* **Pipelining**：中间操作都会**返回流对象本身**。 这样多个操作可以**串联**成一个管道， 如同流式风格（ﬂuent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。 
* **内部迭代**：以前对集合遍历都是通过Iterator或者增强for的方式，显式的在集合外部进行迭代， 这叫做外部迭 代。 **Stream提供了内部迭代的方式，流可以直接调用遍历方法**。

当使用一个流的时候，通常包括三个基本步骤：**获取一个数据源（source）**→ **数据转换**→**执行操作**获取想要的结 果，每次转换原有 Stream 对象不改变，返回一个新的 Stream 对象（可以有多次转换），这就允许对其操作可以 像链条一样排列，变成一个管道。 



## 10.2 获取Stream

> `java.util.stream.Stream<T>` 是Java 8新加入的常用的流接口。（这并不是一个函数式接口）。

**获取流的方式**

* `default Stream<E> stream() `：

  返回以此 **`Collection`** 为数据源的流，`Map`可以转为`Set`来使用

* `static <T> Stream<T> of(T... values)`：

  `Stream`接口的静态方法（可变参数），也可以获取**数组**的流

* 获取并发流：`collection.parallelStream()`、`stream.parallel()`

## 10.3 常用方法

> Stream属于管道流，**只能被使用一次**，数据从上一个Stream传到下一个Stream上，上一个Stream已经关闭

**延迟方法**：**返回值类型仍然是Stream接口自身**类型的方法，因此支持链式调用。（除了终结方法外，其余方法均为延迟方法。） 

* `Stream<T> filter(Predicate<? super T> predicate)`：

  返回==**与给定谓词匹配**==的此流的元素组成的流，<a href="#Predicate">接口</a>

* `<R> Stream<R> map(Function<? super T,? extends R> mapper)`：

  将流的元素==**转换(映射)**==到另一流，<a href="#Function">接口</a>

* `Stream<T> limit(long maxSize)`：

  对流进行==**截取前maxSize个**==

* `Stream<T> skip(long n)`：

  截取==**跳过前n个**==元素的流，若流当前长度小于等于n会得到长度为0的空流

* `static <T> Stream<T> concat(Stream<? extends T> a, Stream<? extends T> b)`：

  ==**合并流**==，静态方法

* `<R,A> R collect(Collector<? super T,A,R> collector)`：

  规约操作；转为List，如`Collectors.toList()`

**终结方法**：**返回值类型不再是 Stream 接口自身**类型的方法，因此不再支持类似 StringBuilder 那样的链式调用。本小节中，终结方法包括 `count` 和 `forEach` 方法。[其他查看API](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/stream/Stream.html)。

* `void forEach(Consumer<? super T> action)`：

  对此流的每个元素==**逐一处理**==。<a href="#Consumer">接口</a>

* `long count()`：

  返回此流中元素的==**数量**==

* `Object[] toArray()`：

  ==**转为数组**==，返回包含此流的元素的数组

* `<A> A[] toArray(IntFunction<A[]> generator)`：

  通过==**方法引用指定创建数组**==类型，如`String[]::new`

```java
public static void main(String[] args) {
    /**
         * 1. 第一个队伍只要名字为3个字的成员姓名；存储到一个新集合中。
         * 2. 第一个队伍筛选之后只要前3个人；存储到一个新集合中。
         * 3. 第二个队伍只要姓张的成员姓名；存储到一个新集合中。
         * 4. 第二个队伍筛选之后不要前2个人；存储到一个新集合中。
         * 5. 将两个队伍合并为一个队伍；存储到一个新集合中。
         * 6. 根据姓名创建 Person 对象；存储到一个新集合中。
         * 7. 打印整个队伍的Person对象信息。
         */
    ArrayList<String> one = new ArrayList<>();
    one.add("迪丽热巴");
    one.add("宋远桥");
    one.add("苏星河");
    one.add("石破天");
    one.add("石中玉");
    one.add("老子");
    one.add("庄子");
    one.add("洪七公");

    ArrayList<String> two = new ArrayList<>();
    two.add("古力娜扎");
    two.add("张无忌");
    two.add("赵丽颖");
    two.add("张三丰");
    two.add("尼古拉赵四");
    two.add("张天爱");
    two.add("张二狗");

    Stream<String> one2 = one.stream().filter(name -> name.length() == 3).limit(3);
    Stream<String> two2 = two.stream().filter(name -> name.startsWith("张")).skip(2);
    Stream.concat(one2, two2).map(name -> new Person(name)).forEach(name -> System.out.println(name));

}
```











# 11 测试

* 测试分类：
  - **黑盒测试**：不需要写代码，给输入值，看程序是否能够输出期望的值。
  - **白盒测试**：**需要写代码**的。关注**程序具体的执行流程**。

* Junit使用：白盒测试
  * 步骤：
    1. 定义一个测试类(测试用例)
      * 测试类名：被测试的类名+Test
      * 包名：xxx.xxx.xx.test		

    2. **定义测试方法：可以独立运行**
      * **方法名**：**test+测试的方法名**	 
      * **返回值**：**void**
      * **参数列表**：**空参**

    3. 给方法加**@Test**
    4. 导入**junit依赖环境**，`org.junit`

  * 判定结果：
    * **红色**：失败
    * **绿色**：成功
    * 一般我们会使用**断言**操作来处理结果
      * **`Assert.assertEquals(expected 期望的结果,actual 运算的结果);`**，支持各种类型数据

  * 补充：以下方法无论测试成功与否都会执行
    * **@Before**:
      * 修饰的方法会在**测试方法之前被自动执行**，用于资源申请`init`
    * **@After**:
      * 修饰的方法会在**测试方法执行之后自动被执行**，用于释放资源`close`



# 10 Java高级技术

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

    * classLoader.<span style="background-color:yellow">**getResource(name)**</span>

      **获取classes(out/src)下的任何URL资源**：name是资源路径，不能以/开头

      ```java
      URL url = clazz.getResource("test.txt")；
      String path = url.getPath();
      ```

    * ==**getResourceAsStream**==就是上面的进行了封装简化后的版本

## 10.2 反射（java.lang.reflect）

> 反射是框架设计的灵魂。框架：半成品软件。可以在框架的基础上进行软件开发，简化编码

* JAVA反射机制是在**运行状态中**，对于任意一个**类**，都能够**知道**这个类的**所有属性和方法**；对于任意一个**对象**，都能够**调用**它的任意一个**属性和方法**；**能够分析类能力的程序称为反射** 

* ==**原理：**==

  1. **将java文件保存到本地硬盘**
  2. **编译java文件，生成class文件**
  3. **jvm通过类加载器将class文件加载到内存中，用Class类表示（java中万事万物皆对象）**
  4. **得到了这个类之后，就可以得到class文件里面的所有内容**

* 好处

  * 可以在**程序运行过程中，操作这些对象**。如IDE中代码提示
  * 可以**解耦，提高程序的可扩展性**

* ==**获取字节码文件Class类型对象**==

  1. `Calss c = Student.class`通过**类名**得到。多用于参数传递
  2. `Class c = stu.getClass()`通过**对象**得到，此方法在Object类中定义。多用于对象获取字节码
  3. `Class c = Class.forName(String name)`：包括包名的全类名。多用于配置文件
     - `String getName();`获取全类名
     - `String getPackageName();`获取包名

  **同一个字节码文件(*.class)在一次程序运行过程中只会被加载一次，以上方式获取的Class对象都是同一个。**

* ==**通过反射分析类的能力**==

  * 包括有1.**域(成员变量)**；2.**构造器**；3.**方法**；

    * **不带Declare**返回类提供的**public**域、方法和构造器的**数组**，包括**超类的公有成员**

    * **带Declare**返回类提供的**全部**域、方法和构造器，**包括私有和保护成员**，但不包括超类的成员
      * 操作私有成员时**`setAccessible(flag)`**中flag设置为true

    ```java
    //若类中没有域(成员变量)或者Class对象描述的是基本类型或数组类型，则返回一个长度为0的数组
    Field get(Declare)Field(String name)
    Field[] get(Declare)Fields()
    
    Method get(Declare)Method(String name, Class<?>... parameterTypes)
    Method[] get(Declare)Methods()
    
    Constructor<T> get(Declare)Construcotr(Class<?>... parameterTypes)//返回一个构造器
    Constructor[] get(Declare)Construcotrs()
    
    setAccessible(boolean b)//为以上三种反射对象设置可访问标志，true为屏蔽java语言访问检查
        
    
    ```

    ```java
    // 单个构造,无参构造可不带.class;传递参数类型
    Constructor con = c.getConstructor(String.class, int.class, String.class);
    Object obj = con.newInstance("zhangsan", 33, "beijing");
    //Object o = c.newInstance();//若调用空参构造可以直接使用Class类的newInstance()方法。在Java9开始弃用
    String name = con.getName();//获取构造方法名称
     
    // 单个域、属性
    Field field = c.getDeclaredField("name");
    field.setAccessible(true);
    field.set(obj, "lisi");//set
    Object objField = field.get(obj);//get
    String name = field.getName();//获取域名即成员变量名
    System.out.println(objField);
    
    // 单个方法,不带参可不写.class;传递参数类型
    Method method = c.getDeclaredMethod("add", int.class, int.class);
    Object objMethod = method.invoke(obj, 10, 20);
    //操作静态方法时，第一个参数为null，不需要实例
    Object objMethod = method.invoke(null, 10, 20);
    //获取方法名
    String name = method.getName();
    System.out.println(objMethod);
    ```


### 10.2.1 通过配置文件运行类中方法

```java
public class Demo{
	//main
	public static void main(String[] args) throws Exception{
    	//之前的方法调用谁的方法创建谁的对象，属于硬编码
    	//通过Properties集合来加载class.txt配置文件
   	 	Properties properties = new Properties();
        ClassLoader classLoader = UserTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("prop.properties");
        properties.load(is);

    	//获取键值对数据
        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");

        Class c = Class.forName(className);
        Constructor con = c.getConstructor();
        Object o = con.newInstance();
        Method show = c.getMethod(methodName, String.class);
        show.invoke(o,"牛逼");
	}
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

## 10.3 动态代理

* **代理**：本来应该自己做的事情，却请了别人来做。**代理对象和目标对象需实现同一接口(有同样的方法)**

* **代理模式**（两者区别在于代理对象的生成模式）

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

# 11 注解（annotation）

## 11.1 注解简介

> 区别：
>
> - **注释**：在阅读程序时更清楚，给**程序员看的**
> - **注解**：给jvm看的，**给机器看的**

- 注解（Annotation）：也叫元数据，一种**代码**级别的**说明**。 JDK1.5之后的新特性，使用**`@Xxx`**表示
- 作用分类：

  - **编译检查**：通过代码里标识的注解让编译器能够实现基本的编译检查【@Override】
  - ==**代码分析**==：通过代码里标识的注解对代码进行分析【使用反射】
  - **编写文档**：通过代码里标识的注解生成文档【生成doc文档】
- JDK中预定义的一些注解

  * @**Override**：检测被该注解标注的方法是否是继承自父类(接口)的
  * @**Deprecated**：该注解标注的内容，表示已过时
  * @**SuppressWarnings**({数组})：压制警告。不让弹出黄线，变量、方法、类上都可以使用
    * 一般传递参数all  `@SuppressWarnings("all")`

## 11.2 自定义注解

- ==**自定义注解（了解，在框架中会使用即可）**==

  > 本质：注解本质上就是一个**接口**，该接口默认继承`java.lang.annotation.Annotation`接口

  - 格式

    ```java
    @元注解
    public @interface 注解名称{
    	属性列表;
    }
    ```

  * **属性**：接口中的**抽象方法**

    * 属性的**返回值类型**有下列取值：基本数据类型、String、枚举、注解、以上类型的数组
    * 定义了属性，在**使用时**需要给属性**赋值**
      1. 如果定义属性时使用**default**关键字给属性默认初始化值，则使用注解时，可不进行属性的赋值。
      2. 如果**只有一个属性需要赋值**，并且属性的名称是**value**，则value可以省略，直接定义值即可。
      3. **数组**赋值时，值使用**{}**包裹。如果数组中只有一个值，则{}可以省略

  * **元注解**：用于**描述注解的注解**

    * **@Target**：描述注解能够**作用的位置**，**属性ElementType枚举类取值**（类名调用）
      * TYPE：可以作用于类上
      * FIELD：可以作用于域（成员变量）上
      * METHOD：可以作用于方法上
    * **@Retention**：描述注解**被保留的阶段**，**属性RetentionPolicy枚举类取值**（类名调用）
      * SOURCE：注解只在`.java`源码级别可见
      * CLASS：注解在`.java`源码级别和`.class`字节码文件级别都可见
      * RUNTIME：当前被描述的注解，会保留到`.class`字节码文件中，并被JVM读取到
    * @Documented：描述注解是否被抽取到api文档中
    * @Inherited：描述注解是否被子类继承

    ```java
    @Target({ElementType.METHOD,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyAnno {
    	//注解的属性
    	String name();
    	int age() default 28;	
    }
    ```

## 11.3 注解的应用

- 目前而言最主流的应用：**代替配置文件**，例如改写反射中加载配置文件的例子

  - 配置文件与注解开发的优缺点：
    - 注解优点：**开发效率高、成本低**
    - 注解缺点：**耦合性大**、并且**不利于后期维护**

  ```java
  @Target({ElementType.TYPE,ElementType.METHOD})
  @Retention(RetentionPolicy.RUNTIME)
  public @interface Prop {
      String className();
      String methodName();
  }
  ```

  ```java
  @Prop(className = "test.Person",methodName = "show")
  public class UserTest1 {
  
      @Prop(className = "test.Person",methodName = "show")
      public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
          //获取类上注解对象，并调用方法获取值
          Class c = UserTest1.class;
          //Prop prop = (Prop) c.getAnnotation(Prop.class);
          //String className = prop.className();
          //String methodName = prop.methodName();
          
          //获取方法上注解对象，并调用方法获取值
          Method main = c.getMethod("main", String[].class);
          Prop prop = main.getAnnotation(Prop.class);
          String className = prop.className();
          String methodName = prop.methodName();
  	    
          //同反射一致
          Class clazz = Class.forName(className);
          Constructor con = clazz.getConstructor();
          Object o = con.newInstance();
          Method show = clazz.getMethod(methodName, String.class);
          show.invoke(o,"牛逼");
      }
  }
  ```

* 案例：简单的测试框架
  * 通过反射获取加了注解要被测试类的方法，`method.isAnnotationPresent(Prop.class)`
  * 通过返回值是否来执行该方法，若抛异常则通过IO流记录在文件中
* 小结：
  1. 以后大多数时候，我们会使用注解，而不是自定义注解
  2. 注解给谁用？
    1. 编译器
    2. 给解析程序用，如上面的测试框架程序
  3. 注解不是程序的一部分，可以理解为注解就是一个标签





# 12 面向对象思想设计原则及设计模式

## 12.1 设计原则

## 12.2 设计模式

### 12.2.1 **简单工厂模式（静态工厂方法模式）**

* 它定义一个**具体的工厂类**负责创建一些类的实例

* 优点：客户端不需要负责对象的创建，从而明确了各个类的职责
* 缺点：这个静态工厂类负责所有对象的创建，如果有新的对象增加，或者某些对象的创建方式不同，就需要不断的修改工厂类，不利于后期的维护

```java
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
### 12.2.2 **工厂方法模式**

* **抽象工厂类负责**定义创建对象的**接口**，具体对象的创建工作由继承抽象工厂的具体类实现

* **优点**：客户端不需要负责对象的创建，从而明确了各个类的职责，如果有新的对象增加，只需要增加一个具体的类和具体的工厂类即可，不影响已有的代码，后期维护容易，增强了系统的扩展性
* **缺点**：需要额外的编写代码，增加了工作量

```java
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
### 12.2.3 **单例设计模式**

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

      ```java
      private static Student s = new Student();
      private Student() {}
      public static Student getStudent() {
      	return s;
      }
      ```

    * **懒汉式**（用的时候才创建）（面试用，可能会出问题的单例模式）

      ```java
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

    ```java
    Runtime r = Runtime.getRuntime();
    r.exec("calc");
    r.exec("notepad");
    r.exec("shutdown -s -t 10000");//10000秒后关机
    r.exec("shutdown -a");//取消关机
    ```

### 12.2.4 模板设计模式

* 模版设计模式就是定义一个算法的骨架，而将具体的算法延迟到子类中来实现 
* **优点：**在定义算法骨架的同时，可以很灵活的实现具体的算法，满足用户灵活多变的需求
* **缺点：**如果算法骨架有修改的话，则需要修改抽象类 
* 例子：计算for循环需要时间、复制一个视频需要的时间
  * 解决：
    * 定义抽象类，其中有计算耗时的非抽象方法，方法中调用一个抽象方法
    * 需要测试的代码通过实现该抽象类并重写抽象方法
    * 在测试类中创建该实现类并调用计算耗时的方法

### 12.2.5 装饰设计模式

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


