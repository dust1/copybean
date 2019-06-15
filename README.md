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