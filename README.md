# CopyBean
一个简单的对JavaBean当中字段的值进行复制的工具。

当前可以复制的变量类型：

1. 基本数据类型以及String类
2. 数组
3. Collection、Map和他们的子类

## 如何使用

首先您需要创建复制相关的配置Configuration:<br>
在当前工具中，您必须指定源类型与目标类型，同时可以指定对应的参数映射
```java

class Test {
    
    //简单的创建配置
    public Configuration getConf(Class<?> sourceType, Class<?> targetType) {
        return Configuration.of(sourceType, targetType);
    }
    
    //当然您也可以指定变量名之间的映射规则
    public Configuration getConf(Class<?> sourceType, Class<?> targetType,
           Function<String, String> router) {
        return Configuration.of(sourceType, targetType, router);
    }
    
}
```

对应映射规则您可以编写您自己的Function<String, String>函数，虽然大部分时候都用不到。

当您获取了Configuration对象后就可以创建复制类了：
```java
class Test {
    
    public Copy getCopy(Configuration configuration) {
        return Copy.getInstance(configuration);
    }
    
}
```

使用也是非常的简单，Copy对外只有一个开放的方法，那就是"复制"。
```java

class Test {
    
    private Copy copy;
    
    //就是如此的简单
    public void copy(Object source, Object target) {
        copy.copy(source, target);
    }
}

```

您可以将初始化完成的Copy对象放置在任何地方以供您重复使用.