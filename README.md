# CopyBean
一个简单的对JavaBean当中字段的值进行复制的工具。

当前可以复制的变量类型：

1. 基本数据类型以及String类
2. 基本数据类型以及String类组成的数组

要求：
类型必须相同

## 如何使用

该工具十分方便使用，下面是一个简单的例子,@Data使用的是lombok插件
```java

@Data
public class SourceA {

    private int id;
    private String username;
    private double amount;
    private int[] nums;

}

@Data
public class TargetA {

    private int id;

    private String username;

    private double amount;

    private int[] nums;
}

public class Test {

    public static void main(String[] args) {
        SourceA sourceA = getSource();
        TargetA targetA = getTarget();

        Copy copy = Copy.create();
        copy.copy(sourceA, targetA);

        System.out.println(sourceA.toString());
        System.out.println(targetA.toString());
    }

    private static SourceA getSource() {
        SourceA source = new SourceA();
        source.setAmount(1.2);
        source.setId(1);
        source.setUsername("admin");
        source.setNums(new int[] {1, 2, 3, 4});
        return source;
    }

    private static TargetA getTarget() {
        return new TargetA();
    }

}

//out
//SourceA(id=1, username=admin, amount=1.2, nums=[1, 2, 3, 4])
//TargetA(id=1, username=admin, amount=1.2, nums=[1, 2, 3, 4])

```
你可以将Copy保存起来随时复用

## 注解
有时候你可能想将相同类型，不同命名的变量进行复制，这时候你可以使用CopyTag注解
```java

class TestObject {
    
    @CopyTag(target = "newName")
    private String name;
}

```
注意：该注解只能标注在原数据的变量中。

这样复制处理器会在读取的时候自动采用你设置的变量名而不是默认相同名称