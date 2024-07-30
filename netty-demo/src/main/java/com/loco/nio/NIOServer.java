package com.loco.nio;

import java.io.IOException;
import java.nio.IntBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * description:
 * author: Loco.Li
 */
public class NIOServer {
    public static void main(String[] args) {
        allocateTest();
    }
    //一个整型的Buffer静态变量
    static IntBuffer intBuffer = null;
    public static void allocateTest()
    {
        //创建了一个Intbuffer实例对象
        intBuffer = IntBuffer.allocate(20);
        System.out.println("------------after allocate------------------");
        System.out.println("position=" + intBuffer.position());
        System.out.println("limit=" + intBuffer.limit());
        System.out.println("capacity=" + intBuffer.capacity());
    }
}
