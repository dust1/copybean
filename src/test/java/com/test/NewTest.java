package com.test;

import com.dust.copy.Copy;
import com.test.entity.SourceA;
import com.test.entity.TargetA;

public class NewTest {

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
