# lx-code-generator

支持java，go语言的实体，dao代码生成，支持的数据库驱动:mysql,postgre,sqlserver

数据库驱动配置示例

```
#mysql驱动配置
dao.url=jdbc:mysql://localhost:3306/test?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8&zeroDateTimeBehavior=CONVERT_TO_NULL&useInformationSchema=true&nullCatalogMeansCurrent=true
dao.username=root
dao.password=root
dao.driverClassName=com.mysql.cj.jdbc.Driver

#postgre驱动配置
dao.url=jdbc:postgresql://localhost:5432/test?useUnicode=true&characterEncoding=utf-8&autoReconnect=true
dao.username=postgres
dao.password=postgres
dao.driverClassName=org.postgresql.Driver

#sqlserver驱动配置
dao.url=jdbc:sqlserver://localhost:1433;DatabaseName=test
dao.username=root
dao.password=root
dao.driverClassName=com.microsoft.sqlserver.jdbc.SQLServerDriver
```



## java代码自动生成配置示例

代码生成运行CodeGenerator

```
dao.url=jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf-8&autoReconnect=true
dao.username=postgres
dao.password=postgres
dao.driverClassName=org.postgresql.Driver

#项目路径
dao.basePath=D:\\projects\\laoxin\\example
#代码生成语言，不配置默认java
dao.language=java
#基础包路径
dao.basePackage=com.laoxin.test
#mapper所在包路径
dao.mapperPackage=com.laoxin.test
#所属模块
dao.moduleName=sys
#数据库名称
dao.databaseName=postgres
#模式名称
dao.schema=sys
#表名,多个表名用英文逗号隔开
dao.tables=user,role
#Entity实体忽略生成的字段
dao.ingoreEntityFileds=
#自动生成的模块 entity,mapper,daomanager,service,controller
dao.genernateModules=entity,mapper,daomanager
```

## go代码自动生成配置示例

代码生成运行CodeGenerator

```
dao.url=jdbc:postgresql://localhost:5432/postgres?useUnicode=true&characterEncoding=utf-8&autoReconnect=true
dao.username=postgres
dao.password=postgres
dao.driverClassName=org.postgresql.Driver

#项目路径
dao.basePath=./go
#代码生成语言，不配置默认java
dao.language=go
#基础包路径,go.mod定义的module
dao.basePackage=laoxin/auth
#数据库名称
dao.databaseName=postgres
#模式名称
dao.schema=sys
#表名,多个表名用英文逗号隔开
dao.tables=user,role
#Entity实体忽略生成的字段
dao.ingoreEntityFileds=
```

