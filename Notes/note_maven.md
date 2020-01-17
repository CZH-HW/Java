
# Maven 笔记

## Maven 简介

Maven 是意第绪语，依地语（犹太人使用的国际语），表示专家的意思。

Maven 是一个项目管理工具，可以对 Java 项目进行构建、依赖管理。Maven 利用一个中央信息片段管理一个项目的构建、报告和文档等步骤。

Maven 也可被用于构建和管理各种项目，例如 C#，Ruby，Scala 和其他语言编写的项目。Maven 曾是 Jakarta 项目的子项目，现为由 Apache 软件基金会主持的独立 Apache 项目。


### Maven 功能

- 构建
- 文档生成
- 报告
- 依赖
- SCMs
- 发布
- 分发
- 邮件列表

### Maven 约定配置

Maven 提倡使用一个共同的标准目录结构，Maven 使用约定优于配置的原则，尽可能的遵守如下所示的目录结构：

| 目录 | 目的 |
|----|----|
|`${basedir}`	                            | 存放 pom.xml 和所有的子目录 |
|`${basedir}/src/main/java`	                | 项目的 java 源代码 |
|`${basedir}/src/main/resources`	        | 项目的资源，比如说 property 文件，springmvc.xml |
|`${basedir}/src/test/java`	                | 项目的测试类，比如说 Junit 代码 |
|`${basedir}/src/test/resources`	        | 测试用的资源 |
|`${basedir}/src/main/webapp/WEB-INF`	    | web 应用文件目录，web 项目的信息，比如存放 web.xml、本地图片、jsp 视图页面 |
|`${basedir}/target`	                    | 打包输出目录 |
|`${basedir}/target/classes`	            | 编译输出目录 |
|`${basedir}/target/test-classes`	        | 测试编译输出目录 |
|`Test.java`	                            | Maven 只会自动运行符合该命名规则的测试类 |
|`~/.m2/repository`	                        | Maven 默认的本地仓库目录位置，这个也可修改及指定新的目录 |


Maven 的主要用处：

1. 相同的项目结构
   + 有一个 pom.xml 用于维护当前项目都用了哪些 jar 包
   + 所有的 java 代码都放在`${basedir}/src/main/java`下面
   + 所有的测试代码都放在`${basedir}/src/test/java` 下面
  
2. 统一维护 jar 包

比如说有 3  个Java 项目，这些项目都不是 maven 风格。那么这 3 个项目，就会各自维护一套 jar 包。 而其中有些 jar 包是相同的。

而 maven 风格的项目，首先把所有的 jar 包都放在一个统一管理的“仓库”（目录）里，然后哪个项目需要用到这个 jar 包，只需要给出 jar 包的名称和版本号就行了，这样 jar 包就实现了共享。

<插入图片>


如图所示，在`pom.xml`文件里，表示用到了 hive-jdbc 的 jar 包，版本号是 1.1.0。

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_16.png)


---


## Maven 的下载与配置

Maven 是基于 Java 的工具，首先需要安装 JDK 

Maven 的下载地址：http://maven.apache.org/download.cgi

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_3.png)


将下载好的文件解压后放入自己指定的目录下，并配置环境变量

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_4.png)

启动 cmd 或 powershell，输入`mvn -v`后有如下输出即可

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_5.png)


---

## Maven 仓库

Maven 仓库就是用于存放项目需要的 jar 包，Maven 采用一个本地仓库，多个项目的方式，让多个项目共享一个本地仓库里相同的 jar 包。

### Maven 本地仓库

修改`apache-maven-3.6.3/conf/settings.xml`配置文件

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_6.png)

如下图所示修改仓库的具体路径

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_7.png)


### Maven 中央仓库

Maven 中央仓库是由 Maven 社区提供的仓库，其中包含了大量常用的库。

由于国外网络访问较慢，所以修改为国内的镜像仓库

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_8.png)


---

## Maven 创建 Maven 项目

Maven 作为一种工具，可以创建标准化的 Maven 风格 Java 项目。 

> 注意：在实际工作中，很少有机会直接通过 Maven 命令行来做到这一点。 通常都是在 Eclipse 或者 IDEA 中，通过集成 Maven 的方式来创建 Java 项目。

Maven 使用原型 archetype 插件创建项目。要创建一个简单的 Java 应用，我们将使用 maven-archetype-quickstart 插件

在 cmd 或者 powershell 中使用`cd 项目目录`命令到指定项目目录，执行如下命令
```powershell
mvn archetype:generate -DgroupId=com.czh.bank -DartifactId=bank -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```
参数说明：
- `archetype:generate`：表示创建个项目
- `-DgroupId`：项目包名，一般是公司网址反写+项目名称
- `-DartifactId`：项目名称（模块名）
- `-DarchetypeArtifactId`：项目类型
- `-DinteractiveMode`：是否使用交互模式

### Maven 项目结构

上述命令执行成功后生成的项目结构目录：

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_9.png)

- Java 代码文件在`/src/main/java/com/czh/bank`目录下
- 测试代码文件在`/src/main/java/com/czh/bank`目录下
- 图片、属性等文件在`src/main/resources`目录下（上述例子需要手动创建）
- `pom.xml`文件和`/src`目录都在`/bank`目录下
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_10.png)

在`/src/main/java/com/czh/bank`目录下有一个`App.java`文件，代码如下：

```java
package com.czh.bank;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
```

在`/src/test/java/com/czh/bank`目录下有一个`AppTest.java`文件，代码如下：

```java
package com.czh.bank;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
}
```

### Maven 常用命令

- 打包：`mvn package`
- 编译：`mvn compile`
- 清空：`mvn clean`（清除编译后目录，默认是`target目录`）
- 运行测试：`mvn test`
- 安装 jar 包到本地仓库中：`mvn install jar包名`
- 执行：`java -cp /path/to/target/classes/ com.czh.bank.App`（需要先执行编译）
- 项目文档：`mvn site`（文档可使用`target/site/`目录下的 html 文件查看）


### Maven 快照（SNAPSHOT）

在 Nexus 仓库中，一个仓库一般分为 public(Release) 仓和 SNAPSHOT 仓，前者存放正式版本，后者存放快照版本。
如果在项目配置文件中（无论是 build.gradle 还是 pom.xml ）指定的版本号带有`-SNAPSHOT`后缀，比如版本号为`Junit-4.10-SNAPSHOT`，那么打出的包就是一个快照版本。

快照版本和正式版本的主要区别：本地获取这些依赖的机制有所不同。
1. 假设你依赖一个库的正式版本，构建的时候构建工具会先在本地仓库中查找是否已经有了这个依赖库，如果没有的话才会去远程仓库中去拉取。所以假设你发布了`Junit-4.10.jar`到了远程仓库，有一个项目依赖了这个库，它第一次构建的时候会把该库从远程仓库中下载到本地仓库缓存，以后再次构建都不会去访问远程仓库了。所以如果你修改了代码，向远程仓库中发布了新的软件包，但仍然叫`Junit-4.10.jar`，那么依赖这个库的项目就无法得到最新更新。你只有在重新发布的时候升级版本，比如叫做`Junit-4.11.jar`，然后通知依赖该库的项目组也修改依赖版本为`Junit-4.11`，这样才能使用到你最新添加的功能。
> 正式版本方式在团队内部开发的时候会变的特别麻烦。假设有两个小组负责维护两个组件，example-service 和 example-ui，其中 example-ui 项目依赖于 example-service。而这两个项目每天都会构建多次，每次构建你都要升级 example-service 的版本。

2. 这个时候 SNAPSHOT 版本就派上用场了。每天日常构建时你可以构建 example-service 的快照版本，比如`example-service-1.0-SNAPSHOT.jar`，而 example-ui 依赖该快照版本。每次 example-ui 构建时，会优先去远程仓库中查看是否有最新的`example-service-1.0-SNAPSHOT.jar`，如果有则下载下来使用。即使本地仓库中已经有了`example-service-1.0-SNAPSHOT.jar`，它也会尝试去远程仓库中查看同名的jar是否是最新的。

3. 有的人可能会问，这样不就不能充分利用本地仓库的缓存机制了吗？别着急，Maven 比我们想象中的要聪明。在配置 Maven 的 Repository 的时候中有个配置项，可以配置对于 SNAPSHOT 版本向远程仓库中查找的频率。频率共有四种，分别是**always、daily、interval、never**。
    - 当本地仓库中存在需要的依赖项目时，always 是每次都去远程仓库查看是否有更新，
    - daily 是只在第一次的时候查看是否有更新，当天的其它时候则不会查看；
    - interval 允许设置一个分钟为单位的间隔时间，在这个间隔时间内只会去远程仓库中查找一次，
    - never 是不会去远程仓库中查找（这种就和正式版本的行为一样了）


Maven版本的配置方式为：

```xml
<repository>
    <id>myRepository</id>
    <url>...</url>
    <snapshots>
        <enabled>true</enabled>
        <!--填写快照更新频率-->
        <updatePolicy>XXX</updatePolicy>
    </snapshots>
</repository>
```

---

## IDEA Maven

### IDEA设置

进入 IDEA settings：
File -> Settings -> Build, Execute, Deployment -> Build Tools -> Maven
修改 Maven home directory 和 User settings file 的目录后，Local repository 会被自动识别。
这样 IDEA 中的 Maven 就是使用的本地库（可以减少从网上下载相关资料的数量，加快 Maven 项目创建和安装的速度）

![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_11.png)


### IDEA Maven Project

1. 新建项目：File -> New -> Project

2. 选择 Maven 项目：左边选择 Maven -> 勾选 Create from archetype -> 选择 org.apache.maven.archetypes:maven-archetype-quickstart -> Next
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_12.png)
> archetype 原型，是一个 Maven 插件，准确说是一个项目模板，它的任务是根据模板创建一个项目结构。我们这里使用 quickstart 原型插件创建一个简单的 java 应用程序

3. Maven 项目参数：Name、GroupID、ArtifactID
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_13.png)

4. Maven 目录
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_14.png)

5. Maven Import：Maven 项目创建了，IDEA 还会做一些初始化的工作

6. 可以得到一个经典的 Maven 项目，并且有一个附送的 App.java 类
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_15.png)

7. IDEA Maven 增加依赖 jar 包
在`pox.xml`文件添加<dependency>即可，IDEA import Changes
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_16.png)


### IDEA Maven Web

1. 新建项目：File -> New -> Project

2. 选择 Maven 项目：左边选择 Maven -> 勾选 Create from archetype -> 选择 org.apache.maven.archetypes:maven-archetype-webapp -> Next
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_17.png)

3. Maven 项目参数：Name、GroupID、ArtifactID

4. 得到一个经典的 Maven Web 项目，初始化后的项目结构如下图所示
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_18.png)

5. 添加项目目录：在 main 目录下创建 java 和 resources 目录，在 src 目录下创建 test 目录，并在 test 目录下依次创建 java 和 resources 目录

6. 给创建的目录添加标记，具体目录及其标记，项目结构如下
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_19.png)

7. 设置 Facets：对 Web Module Deployment Descriptor（选择 web.xml 文件） 和 Web Resource Directories（选择 resources 目录） 进行设置

1. 设置 Artifacts：
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_21.png)
![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_22.png)


8. 配置 Tomcat（轻量级 Web 应用服务器）
    - 下载压缩包
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_24.png)
    
    - 解压缩包，放入某个路径下
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_25.png)

    - 配置环境变量
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_26.png)
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_27.png)

    - 在 cmd 中输入`catalina run`，在浏览器地址栏输入http://localhost:8080，如果能够出现 Tomcat 页面，说明配置成功
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_28.png)
 
    - 点击 Add Configuration 选择 Tomcat 本地服务器，在 Server 选项里配置 Tomcat 本地路径，在 Deployment 中添加前面设置的 ArtifactsS
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_29.png)
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_30.png)
    ![](https://github.com/CZH-HW/CloudImg/raw/master/Java/Maven_31.png)
    > 可以将 Server 选项中 On 'Update' action 设置成 Update classes and resources，这样就进行了热布置，不用你重启tomcat，前台的也是，你修改jsp,css,js什么的都可以直接进行热部署，你只要自己刷新一下你的页面就行了

9. 运行此项目，IDEA 会直接启动浏览器直接进行访问得到`index.jsp`文件中指定的显示内容


