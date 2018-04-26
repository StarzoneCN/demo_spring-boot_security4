demo_spring-boot_security4
=
使用mybatis+mysql实现spring security4的Jdbc模式<br/>
![](https://codingstory.com.cn/content/images/2017/06/maxresdefault.jpg "spring security")

####关于JDBC数据库表格建立和数据插入
* <strong>`table.sql`</strong>为表格建立模板、<strong>`data.sql`</strong>为数据示例。<br/>
* 数据库使用的是<strong>`MySql`</strong>，持久化框架使用的<strong>`mybatis`</strong>。<br/>
* src/test/resources/mybatis-generator/generatorConfig.xml中配置的是mybatis代码生成工具。<br/>
pom.xml中配置添加mybatis-generator插件才能使用。<br/>
执行`mvn mybatis-generator:generate`即可生成对应entity、mapper和xml。


