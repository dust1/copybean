package com.dust.copybean.utils;

import java.util.Locale;

/**
 * 字符串处理类
 */
public class StringUtil {

    /**
     * 首字母大写
     * <p>
     *     只支持开头是英文字母的单词。<br>
     *     开头是下划线的不变
     * </p>
     */
    public static String firstCapital(String word) {
        String first = word.substring(0, 1).toUpperCase(Locale.ENGLISH);
        return first + word.substring(1);
    }

}
