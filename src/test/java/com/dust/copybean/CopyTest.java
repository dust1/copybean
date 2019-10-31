package com.dust.copybean;

import com.dust.copybean.entity.SourceA;
import com.dust.copybean.entity.TargetA;

import java.util.*;


/**
 * 复制测试
 */
public class CopyTest {

    public static void main(String[] args) {
        normalCopy();
    }


    private static void normalCopy() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        Map<String, String> map = new HashMap<>();
        map.put("s  ", "111");
        SourceA source = new SourceA();
        source.setAmount(1.2);
        source.setId(1);
        source.setUsername("admin");
        source.setMap(map);
        source.setNums(new int[] {1, 2, 3, 4});
        source.setList(list);
        TargetA target = new TargetA();

        Configuration configuration = Configuration.of(SourceA.class, TargetA.class);
        Copy copy = Copy.getInstance(configuration);
        copy.copy(source, target);

        System.out.println(target.getMap());
    }



}
