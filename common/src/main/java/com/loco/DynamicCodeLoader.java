package com.loco;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 动态加载其他数据源的java代码，完成方法调用后删除
 */


public class DynamicCodeLoader {


    public static void main(String[] args) {
        // 假设这里是从数据库中读取到的代码字符串

        String code = "public class DynamicClass { public void sayHello() { System.out.println(\"Hello, dynamic code!\"); }}";

        try {
            // 将代码字符串写入Java文件
            String filename = "DynamicClass.java";
            File file = new File(filename);
            FileWriter writer = new FileWriter(file);
            writer.write(code);
            writer.close();

            // 编译Java文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            compiler.run(null, null, null, filename);

            // 加载编译后的类
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(".").toURI().toURL()});
            Class<?> dynamicClass = Class.forName("DynamicClass", true, classLoader);
            Object dynamicObject = dynamicClass.getDeclaredConstructor().newInstance();

            // 调用动态代码中的方法
            Method method = dynamicClass.getMethod("sayHello");
            method.invoke(dynamicObject);

            // 删除临时文件
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
