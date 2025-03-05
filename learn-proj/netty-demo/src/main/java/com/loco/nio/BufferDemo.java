package com.loco.nio;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * description: buffer 特性
 * author: Loco.Li
 */
public class BufferDemo {

    static String classpath =System.getProperty("user.dir") + "\\netty-demo\\";
    public static void main(String[] args) throws Exception {
//        byteBufferPutAndGet();
//        readOnlyBuffer();
//        superBuffer();
        scatteringAndGathering();
    }

    private static void scatteringAndGathering() throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);
        serverSocketChannel.socket().bind(inetSocketAddress);
        //创建 buffer 数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);
        //等客户端连接(telnet 127.0.0.1 7000)
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8; //假定从客户端接收 8 个字节
        //循环的读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength ) {
                long l = socketChannel.read(byteBuffers);
                byteRead += (int) l; //累计读取的字节数
                System.out.println("byteRead=" + byteRead);
                //使用流打印, 看看当前的这个 buffer 的 position 和 limit
                Arrays.stream(byteBuffers)
                        .map(buffer -> "position=" + buffer.position() + ", limit=" +buffer.limit())
                        .forEach(System.out::println);
            }
            //将所有的 buffer 进行 flip
            Arrays.asList(byteBuffers).forEach(ByteBuffer::flip);
            //将数据读出显示到客户端
            long byteWirte = 0;
            while (byteWirte < messageLength) {
                long l = socketChannel.write(byteBuffers); //
                byteWirte += l;
            }
            //将所有的 buffer 进行 clear
            Arrays.asList(byteBuffers).forEach(ByteBuffer::clear);
            System.out.println("byteRead:=" + byteRead + ", byteWrite=" + byteWirte + ", message length" + messageLength);
        }

    }

    /**
     *  MappedByteBuffer 可让文件直接在内存(堆外内存)修改, 操作系统不需要额外拷贝
     */
    private static void superBuffer() throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile(classpath+"super01.txt", "rw");
        //获取对应的通道
        FileChannel channel = randomAccessFile.getChannel();
        /*
          参数 1: FileChannel.MapMode.READ_WRITE 使用的读写模式
          参数 2： 0 ： 可以直接修改的起始位置
          参数 3: 5: 是映射到内存的大小(不是索引位置) ,即将 1.txt 的多少个字节映射到内存
          可以直接修改的范围就是 0-5
          实际类型 DirectByteBuffer
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
