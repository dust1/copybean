package com.dust.copybean.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassUtils {

    /**
     * 获取目录下对应类的实现类或子类
     * @param clazz
     * @return
     * @throws ClassNotFoundException
     */
    public static <T> List<Class<T>> getAllAssignedClass(Class<T> clazz) throws ClassNotFoundException {
        List<Class<T>> results = new ArrayList<>();
        for (Class<T> c : getClass(clazz)) {
            if (clazz.isAssignableFrom(c) && !clazz.equals(c)) {
                results.add(c);
            }
        }
        return results;
    }

    private static <T> List<Class<T>> getClass(Class<T> clazz) throws ClassNotFoundException {
        String packageName = clazz.getPackageName();
        String path = packageName.replace(".", "/");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(path);
        return getClass(new File(Objects.requireNonNull(url).getFile()), packageName);
    }

    private static <T> List<Class<T>> getClass(File file, String packageName) throws ClassNotFoundException {
        List<Class<T>> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        for (File f : Objects.requireNonNull(file.listFiles())) {
            if (f.isDirectory()) {
                list.addAll(getClass(f, packageName + "." + f.getName()));
            }
            String name = f.getName();
            if (name.endsWith(".class")) {
                list.add((Class<T>) Class.forName(packageName + "." + name.substring(0, name.length() - 6)));
            }
        }
        return list;
    }

}
