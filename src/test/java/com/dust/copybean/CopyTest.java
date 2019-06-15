package com.dust.copybean;

import com.dust.copybean.entity.SourceA;
import com.dust.copybean.entity.TargetA;


/**
 * 复制测试
 */
public class CopyTest {

    public static void main(String[] args) {
//        normalCopy();
//        System.out.println('z' - 'A');
//        WordTree wordTree = new WordTree();
//        wordTree.append("hello");
//        wordTree.append("world");
//        wordTree.append("how");
//        System.out.println(wordTree);
        String str = "hello";
        String skip = "he";
        System.out.println(str.substring(skip.length()));
    }

    private static void normalCopy() {
        SourceA source = new SourceA();
        source.setAmount(1.2);
        source.setId(1);
        source.setUsername("admin");
        System.out.println(source);

        TargetA targetA = new TargetA();
        CopyEye eye = new CopyEye();
        eye.copy(source, targetA,
                SourceA.class, TargetA.class);
        System.out.println(targetA);
    }



}
