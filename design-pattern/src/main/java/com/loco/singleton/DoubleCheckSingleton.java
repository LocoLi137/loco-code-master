package com.loco.singleton;

/**
 * @description: 双检锁
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/2/22 12:35
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {

    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class){
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
