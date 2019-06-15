# CopyBean
一个简单的对JavaBean当中字段的值进行复制的工具。

## 直接复制
该方法直接对两个JavaBean之间相同名称、相同类型的字段的值进行复制。使用方法如下：
```java
class Test {
    
   /**
    * source - 源对象
    * target - 目标对象
    * Source.class - 源对象的类型
    * Target.class - 目标对象的类型
    */
    public void test() {
        CopyEye eye = new CopyEye();
        eye.copy(source, targetA,
            Source.class, Target.class);
    }
    
}
```
## 自定义规则
你也可以通过继承Configuration抽象类来实现自己的字段映射规则。<br>
⚠️:当前的配置规则只是适用于source。<br>
相当于source的字段名称在对target查询时要先经过toTargetField(sourceFieldName:String)的转换。
```java

class MyConfig extends Configuration {
    
    private String skip;
    
    public MyConfig(String skip) {
        this.skip = skip;
    }
    
   /**
    * 如果存在前缀，则忽略
    */
    public String toTargetField(String sourceFieldName) {
        return sourceFieldName.startsWith(skip)
            ? sourceFieldName.substring(skip.length())
            : sourceFieldName;
    }
    
}

class Test {
    public void test() {
        CopyEye eye = new CopyEye();
        MyConfig config = new MyConfig("is");
        eye.setConfig(eye);
        /*
          调用复制方法
         */
    }
}

```
