package com.loco.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * description: buffer 特性
 * author: Loco.Li
 */
public class BufferDemo {

    static String classpath =System.getProperty("user.dir") + "\\netty-demo\\";
    public static void main(String[] args) throws Exception {
//        byteBufferPutAndGet();
//        readOnlyBuffer();
        superBuffer();
    }

    /**
     *  MappedByteBuffer 可让文件直接在内存(堆外内存)修改, 操作系统不需要额外拷贝
     */
    private static void superBuffer() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile(classpath+"super01.txt", "rw");
        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * 参数 1: FileChannel.MapMode.READ_WRITE 使用的读写模式
         * 参数 2： 0 ： 可以直接修改的起始位置
         * 参数 3: 5: 是映射到内存的大小(不是索引位置) ,即将 1.txt 的多少个字节映射到内存
         * 可以直接修改的范围就是 0-5
         * 实际类型 DirectByteBuffer
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');
//        mappedByteBuffer.put(5, (byte) 'Y');//IndexOutOfBoundsException
        randomAccessFile.close();
        System.out.println("你真棒啊哥们，修改成功了");
    }

    private static void readOnlyBuffer(){
        ByteBuffer buffer = ByteBuffer.allocate(64);
        for(int i = 0; i < 64; i++) {
            buffer.put((byte)i);
        }
        buffer.flip(); //切换读模式
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();//得到只读buffer
        System.out.println(readOnlyBuffer.getClass());
        //读取
        while (readOnlyBuffer.hasRemaining()) {
            System.out.println(readOnlyBuffer.get());
        }
        readOnlyBuffer.put((byte)100); //ReadOnlyBufferException
    }

    /**
     * ByteBuffer 支持类型化的 put 和 get, put 放入的是什么数据类型，get 就应该使用相应的数据类型来取出，否则可能有 BufferUnderflowException 异常。
     */
    private static void byteBufferPutAndGet(){
        ByteBuffer buffer = ByteBuffer.allocate(64);
        //类型化方式放入数据
        buffer.putInt(100);
        buffer.putLong(9);
        buffer.putChar('李');
        buffer.putShort((short) 4);
        buffer.putFloat(1.3f);
        //取出
        buffer.flip();
        System.out.println();
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getFloat());
    }
}
