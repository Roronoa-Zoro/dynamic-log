
### log组件支持
1. logback
2. log4j2

### 支持的场景
默认支持使用SpringRequest里面获取header的值

### 使用方式
#### logback   
在logback.xml配置添加一个filter
```xml
<configuration scan="false" debug="false">
    <turboFilter class="com.illegalaccess.log.dynamic.core.logback.LogbackDynamicLogFilter" />
    
    <root level="error">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

#### log4j2使用
在log4j2.xml添加filter
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN" monitorInterval="60">
    <Properties>
        <Property name="LOG_PATTERN">%d{yyyy-MM-dd'T'HH:mm:ss.SSS}|%-5p|%c|%logger{36}(%F:%L)|%m%n</Property>
    </Properties>
    <Filters>
        <DynamicLogFilter onMatch="ACCEPT" onMismatch="DENY" />
    </Filters>

    <Appenders>
        <Console name="console" target="SYSTEM_OUT" follow="true">
            <PatternLayout pattern="${LOG_PATTERN}"/>
        </Console>
    </Appenders>

    <Loggers>
        <Root level="info" additivity="false">
            <AppenderRef ref="console"/>
        </Root>
    </Loggers>
</Configuration>
```

#### 在http请求头添加属性
X-Debug: true


#### 在代码中添加需要在动态显示的日志
```java
public class DemoController {
    // slf4j logger
    private Logger log = LoggerFactory.getLogger(DemoController.class);

    public void demo() {
        if (log.isDebugEnabled()) {
            log.info("dynamic log show");
        }
        // other business logic
    }

}
```

### 不支持的场景
1. 定时任务触发
2. MQ消费场景

#### 可扩展的方式
1. 根据自己的场景，集成自己的系统，可以调用
```java
DynamicLogContext.enableDebug();
```
即可开启debug日志，调用完成需要清理context
```java
DynamicLogContext.clear();
```

2. 如果系统使用其他的框架，如struts等，需要自己实现如下接口：
```java
LogLevelDecider
```   
然后把实现类放到META-INF/services/目录下，文件名字是class的全路径名，里面的值也是类的全路径名   
