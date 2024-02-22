package com.loco.singleton;

/**
 * @description: 饿汉式
 * @author: Loco.Li
 * @email: 563235247@qq.com
 * @date: 2024/2/22 12:23
 */
public class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() {

    }

    public static EagerSingleton getInstance() {
        return instance;
    }
}
