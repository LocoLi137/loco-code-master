package com.loco.singleton;

/**
 * @description:
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/2/22 12:25
 */
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {

    }

    public static synchronized  LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
