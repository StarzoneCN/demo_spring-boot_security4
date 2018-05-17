demo_spring-boot_security4
=
使用mybatis+mysql实现spring security4的Jdbc模式<br/>
![](https://codingstory.com.cn/content/images/2017/06/maxresdefault.jpg "spring security")

#### 关于JDBC数据库表格建立和数据插入
* <strong>`table.sql`</strong>为表格建立模板、<strong>`data.sql`</strong>为数据示例。<br/>
  * 自提交`98cc9c4`之后，放弃使用原先的表格形式（`table.sql`和`data.sql`），改用`Spring-Security`的标准表格形式（`table-normal.sql`和`data-normal.sql`）；
  * 原先的表格形式分组权限需要用户手动实现，而`Spring-Security`标准表格形式是`Spring-Security`提供的一个`UserDetailsServie`默认实现类`JdbcUserDetailsManager`所采用的表格形式；
  * `JdbcUserDetailsManager`实现更加全面，用户可以省略许多步骤；
  * 继承`JdbcUserDetailsManager`也可以定义用户自己的表格形式，只需要在`JdbcUserDetailsManager`的子类中定义用户自己表格形式的SQL语句就可以了；
* 数据库使用的是<strong>`MySql`</strong>，持久化框架使用的<strong>`mybatis`</strong>。<br/>
* src/test/resources/mybatis-generator/generatorConfig.xml中配置的是mybatis代码生成工具。<br/>
  * pom.xml中配置添加mybatis-generator插件才能使用。<br/>
  * 执行`mvn mybatis-generator:generate`即可生成对应entity、mapper和xml。


