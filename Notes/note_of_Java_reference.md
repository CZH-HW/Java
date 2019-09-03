目录
[TOC]

# Java

## Java中的隐藏和覆盖

#### 编译时类型和运行时类型

Java的引用变量有两个类型，一个是编译时类型，一个是运行时类型

- 编译时类型：由声明该变量时使用的类型决定

- 运行时类型：由该变量指向的对象类型决定

如果编译时类型和运行时类型不一致，会出现所谓的多态
    
以下面的例子为例:  
    `Father  f  =  new  Son();  //Son是Father的子类`
引用变量`f`就会出现编译时类型和运行时类型不一致的情况：
&emsp;&emsp;编译时是`Father`类型的，运行时是`Son`类型的

当变量的编译时类型和运行时类型不一致时:
- 通过变量访问它所引用的对象的实例时，该实例变量的值由声明该变量的类型决定。
- 通过变量访问它所引用的对象的方法时，该方法的行为由所引用的对象实际类型所决定。
<br>

#### 隐藏和覆盖

- 覆盖：子类重写父类的方法，要求方法名和参数类型完全一样(参数不能是子类)，返回值和异常比父类小或者相同(即为父类的子类)，访问修饰符比父类大或者相同。

&emsp;&emsp;**覆盖是对于实例方法而言的**，方法不能交叉覆盖：
&emsp;&emsp;&emsp;&emsp;1. 子类实例方法不能覆盖父类的静态方法；
&emsp;&emsp;&emsp;&emsp;2. 子类的静态方法也不能覆盖父类的实例方法(编译时报错)

- 隐藏：父类和子类拥有相同名字的属性或者方法（方法隐藏只有一种形式，就是父类和子类存在相同的静态方法）时，父类的同名的属性或者方法形式上不见了，实际是还是存在的。

&emsp;&emsp;**隐藏是对于静态方法和成员变量（静态变量和实例变量）而言的**：
&emsp;&emsp;&emsp;&emsp;1. **当发生隐藏的时候，声明类型是什么类，就调用对应类的属性或者方法，而不会发生动态绑定**
&emsp;&emsp;&emsp;&emsp;2. 属性只能被隐藏，不能被覆盖
&emsp;&emsp;&emsp;&emsp;3. 变量可以交叉隐藏：子类实例变量/静态变量可以隐藏父类的实例/静态变量
<br>

#### 隐藏和覆盖的区别

- 被隐藏的属性:
在子类被强制转换成父类后，访问的是父类中的属性
在无强制转换时子类要访问父类的属性使用super关键字
<br>
- 被覆盖的方法:
在子类被强制转换成父类后，调用的还是子类自身的方法
子类要是想访问父类的方法，可以使用super关键字

**RTTI(run time type identification，运行时类型检查)**
RTTI只针对覆盖，不针对隐藏：因为覆盖是动态绑定，是受RTTI约束的，隐藏不受RTTI约束

代码例子：
```java
public class Test {
    public static void main(String[] args)  {
    	Circle circle = new Circle();   //本类引用指向本类对象
        Shape shape = new Circle();     //父类引用指向子类对象(会有隐藏和覆盖)
        
        System.out.println(circle.name);
        circle.printType();
        circle.printName();
        //以上都是调用Circle类的方法和引用
       
        System.out.println(shape.name);     //调用父类被隐藏的name属性
        shape.printType();                  //调用子类printType的方法
        shape.printName();                  //调用父类隐藏的printName方法 
    }
}
 
class Shape {
    public String name = "shape";
     
    public Shape(){
        System.out.println("shape constructor");
    }
     
    public void printType() {
        System.out.println("this is shape");
    }
     
    public static void printName() {
        System.out.println("shape");
    }
}
 
class Circle extends Shape {
    public String name = "circle"; //父类属性被隐藏
     
    public Circle() {
        System.out.println("circle constructor");
    }
   
    //对父类实例方法的覆盖
    public void printType() {
        System.out.println("this is circle");
    }
    
   //对父类静态方法的隐藏  
    public static void printName() {
        System.out.println("circle");
    }
}
```



## Java抽象类

#### 抽象类的特点：
1. 抽象方法一定在抽象类中，抽象类中可以没有抽象方法。
2. 抽象方法和抽象类都必须被`abstract`关键字修饰。
3. 抽象类不可以用new创建对象（不能实例化），因为调用抽象方法没意义。
4. 抽象类中的抽象方法要被使用，必须由子类复写起所有的抽象方法后，建立子类对象调用。
   如果子类只覆盖了部分抽象方法，那么该子类还是一个抽象类。

注意`abstract`关键字，和哪些关键字不能共存：
- `final`：被`final`修饰的类不能有子类——>而被`abstract`修饰的类一定是一个父类。
- `private`: 抽象类中私有的抽象方法，不被子类所知，就无法被复写——>而抽象方法出现就是需要被复写。
- `static`：如果`static`可以修饰抽象方法，那么连对象都省了，直接类名调用就可以了——>而抽象方法运行没意义。

#### 抽象类和接口的区别：
1. 子类只能继承一个抽象类，不能继承多个（类的单继承）
    子类可以实现多个接口（接口多继承）
2. 抽象类可以定义public,protected,package,private、静态和非静态属性、final和非final属性
    但是接口中声明的属性，只能是public、静态、final的，即便没有显式的声明
注：抽象类和接口都可以有实体方法。 接口中的实体方法，叫做**默认方法**


## Java内部类

#### 什么是内部类
可以将一个类的定义放在里另一个类的内部，这就是内部类。
广义上我们将内部类分为四种：成员内部类、静态内部类、局部（方法）内部类、匿名内部类。
```java
// 我是一个外部类（外部是相对内部而言）
public class Outer{
	// 我是一个内部类
	class Inner{
	//...
	}
}
```

#### 为什么要用内部类：
> 使用内部类最吸引人的原因是：每个内部类都能独立地继承一个（接口的）实现，所以无论外围类是否已经继承了某个（接口的）实现，对于内部类都没有影响。——《Think in java》

内部类拥有类的基本特征(eg：可以继承父类，实现接口)。在实际问题中我们会遇到一些接口无法解决或难以解决的问题，此时我们可以使用内部类继承某个具体的或抽象的类，间接解决类无法多继承引起的一系列问题。（注：内部类可以嵌套内部类，但是这极大的破换了代码的结构，这里不推荐使用。）
1. 内部类可以使用多个实例，每个实例都有自己的状态信息，并且与其他外围对象的信息相互独立。
2. 内部类并没有令人迷惑的“is-a”关系，他就是一个独立的实体。
3. 内部类提供了更好的封装（封装性更好），除了该外围类，其他类都不能访问。
4. 内部类可以访问外部类的私有数据
5. 创建内部类对象的时刻并不依赖于外围类对象的创建。

```java
/**
    Outer类继承了ClassA，实现了IFunctionA
*/
public class Outer extends ClassA implements IFunctionA{ 
	/**
	*	Inner类继承了ClassB，实现了IFunctionB
	*/
	public class Inner extends ClassB implements IfunctionB{
	//
	} 
}
```


#### 成员内部类

```java
// 外部类、成员内部类的定义
public class Outer {

    private int outerVariable = 1;
    private int commonVariable = 2;
    private static int outerStaticVariable = 3;
    //省略getter/setter
     
    // 成员方法(实例方法)
    public void outerMethod() {
        System.out.println("我是外部类的outerMethod方法");
    }

    // 静态方法
    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }

    // 内部类
    public class Inner {
    
        private int commonVariable = 20;

        // 构造方法
        public Inner() {
        }

        // 成员方法，访问外部类信息（属性、方法）
        public void innerShow() {
            //当和外部类冲突时，直接引用属性名，是内部类的成员属性
            System.out.println("内部的commonVariable:" + commonVariable);
            //内部类访问外部属性
            System.out.println("outerVariable:" + outerVariable);
            //当和外部类属性名重叠时，可通过外部类名.this.属性名
            System.out.println("外部的commonVariable:" + Outer.this.commonVariable）;
            System.out.println("outerStaticVariable:" + outerStaticVariable);
            //访问外部类的方法
            outerMethod();
            outerStaticMethod();
        }
    }
    
    // 外部类访问内部类信息
    public void outerShow() {
        Inner inner = new Inner();
        inner.innerShow();
    }
}
```

```java
// 其他类使用成员内部类
public class Other {
    
    public static void main(String[] args) {
        //外部类对象
        Outer outer = new Outer();
        //创造内部类对象
        Outer.Inner inner = outer.new Inner();
        inner.innerShow();
        /*
        * 可在Outer中定义get方法，获得Inner对象,那么使用时，只需outer.getInnerInstance()即可。
        * public Inner getInnerInstance(Inner类的构造方法参数){
        *   return new Inner(参数);
        * }
        */
    }
}
```
<br>


**小结**：_成员内部类当成Outer外部类的成员信息存在的_
- 可以是任何的访问修饰符
- **内部类的内部不能有静态信息**
- 内部类也是类,该继承继承，该重写重写，该重载重载，this和super随便用
- 外部类如何访问内部类信息，必须new实例化内部类后再打点访问
- 内部类可以直接使用外部类的任何信息，如果属性或者方法发生冲突，调用外部类.this.属性或者方法
- 其他类访问内部类需要先实例化外部类，new 外部类.new 内部类


#### 静态内部类

```java
// 外部类、内部类定义
public class Outer {

    private int outerVariable = 1;
    // 外部类定义的属性(重名)
    private int commonVariable = 2;   
    private static int outerStaticVariable = 3;

    static {
        System.out.println("Outer的静态块被执行了……");
    }

    // 成员方法
    public void outerMothod() {
        System.out.println("我是外部类的outerMethod方法");
    }

    // 静态方法
    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }


    // 静态内部类
    public static class Inner {
        // 成员信息
        private int innerVariable = 10;
        private int commonVariable = 20;

        static {
            System.out.println("Outer.Inner的静态块执行了……");
        }

        private static int innerStaticVariable = 30;

        // 成员方法    
        public void innerShow() {
            System.out.println("innerVariable:" + innerVariable);
            System.out.println("内部的commonVariable:" + commonVariable);
            System.out.println("outerStaticVariable:"+outerStaticVariable);
            outerStaticMethod();
        }

        // 静态方法
        public static void innerStaticShow() {
        	// 被调用时会先加载Outer类
            outerStaticMethod();
            System.out.println("outerStaticVariable"+outerStaticVariable);
        }
    }

    // 外部类的内部如何和内部类打交道
    public static void callInner() {
        System.out.println(Inner.innerStaticVariable);
        Inner.innerStaticShow();
    }
}
```

```java
// 其他类使用成员内部类
public class Other {

    public static void main(String[] args) {
        //访问静态内部类的静态方法，Inner类被加载,此时外部类未被加载，独立存在，不依赖于外围类。
        Outer.Inner.innerStaticShow();
        //访问静态内部类的成员方法
        Outer.Inner oi = new Outer.Inner();
        oi.innerShow();
    }
}
```
<br>

**小结**：_和成员内部类对比理解（区别异同）_
- 内部可以包含任意的信息。
- 静态内部类的方法只能访问外部类的static关联的信息。
- 利用`外部类.内部类 引用 = new 外部类.内部类();` 然后利用`引用.成员信息(属性、方法)`调用。
- 访问内部类的静态信息，直接`外部类.内部类.静态信息`就可以了。
- 静态内部类可以独立存在，不依赖于其他外围类


#### 局部内部类

```java
public class Outer {

    private int outerVariable = 1;
    private int commonVariable = 2;
    private static int outerStaticVariable = 3;

    public void outerMethod() {
        System.out.println("我是外部类的outerMethod方法");
    }

    public static void outerStaticMethod() {
        System.out.println("我是外部类的outerStaticMethod静态方法");
    }

    // 程序的入口
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.outerCreatMethod(100);
    }

    // 成员方法，内部定义局部内部类
    public void outerCreatMethod(int value) {

        boolean sex = false;

        // 局部内部类，类前不能有访问修饰符   
        class Inner {
            private int innerVariable = 10;
            private int commonVariable = 20;
			
            // 局部内部类方法
            public void innerShow() {
                System.out.println("innerVariable:" + innerVariable);
                //局部变量
                System.out.println("是否男性:" + sex);
                System.out.println("参数value:" + value);
                //调用外部类的信息
                System.out.println("outerVariable:" + outerVariable);
                System.out.println("内部的commonVariable:" + commonVariable);
                System.out.println("外部的commonVariable:" + Outer.this.commonVariable);
                System.out.println("outerStaticVariable:" + outerStaticVariable);
                outerMethod();
                outerStaticMethod();
            }
        }
        //局部内部类只能在方法内使用
        Inner inner = new Inner();
        inner.innerShow();
    }
}
```
<br>

**小结** _局部内有很多局限，应注意作用域_
- 类前不能有访问修饰符
- 仅在此方法内使用
- 无法创造静态信息
- 可以直接访问方法内的局部变量和参数（有限制：1.直接被final修饰的变量；2.已被赋值且始终未改变的变量（有且仅有赋值一次）,引用指向不能改变），但是不能更改
- 可以随意的访问外部类的任何信息


#### 匿名内部类

```java

// 接口中方法默认为public 
public interface IAnimal{
	void speak();
}

// 匿名内部类的使用
public class Outer {

    public static IAnimal getInnerInstance(String speak){
        // 返回一个接口的匿名子类对象
        return new IAnimal(){
            @Override
            public void speak(){
                System.out.println(speak);
            }};
        	//注意上一行的分号必须有
    }
    
    public static void main(String[] args){
    	//调用的speak()是重写后的speak方法。
        Outer.getInnerInstance("小狗汪汪汪！").speak();
    }
}
```
<br>

**小结** _匿名内部类常常被用来重写某个或某些方法_
- **匿名内部类本质上是一个重写或实现了父类或接口的子类对象**
- 匿名内部类是没有访问修饰符的。
- 使用匿名内部类时，这个new之后的类首先是要存在的，其次我们要重写new后的类的某个或某些方法。
- 匿名内部类访问方法参数时也有和局部内部类同样的限制。
- 匿名内部类没有构造方法。
<br>

**匿名内部类嵌套使用**
```java
public class NoNameInnerClassHomework {
	public static void main(String[] args) {
		InnerClass.show().test().show();// 要求在控制台输出”HelloWorld”
	}
}
 
abstract class Demo {
	public abstract void show();// 抽象方法,继承它的子类必然要重写这个方法，而且要输出”HelloWorld”
}
 
interface Inter {
	Demo test();//Inter接口里的方法没有方法体，继承了它的子类肯定也要重写这个方法，返回值是Demo类型
}
 
 
class InnerClass {
//补齐代码 	
	static Inter show() {/*分析：InnerClass.show()，说明InnerClass里存在静态show方法，
                            而且它的返回值能调用test方法，那么得是个Inter类的对象，
                            而匿名内部类实质正好就是写在方法中的实现了重写的子类对象对象*/
		return new Inter() {
			public Demo test() {//实现Inter接口里的方法
				return new Demo() {	
					public void show() {//重写Demo里的抽象方法
						System.out.println("Hello,World");
					}
				};
			}//test方法括号
		};
	}//show方法括号
}
```




















