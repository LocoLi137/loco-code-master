package com.loco.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @auther LociLi
 */
public class NIOClient {
    public static void main(String[] args) throws Exception {
        run();
    }

    public static void run() throws Exception{
        //得到一个网络通道
        try (SocketChannel socketChannel = SocketChannel.open()) {
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //提供服务器端的 ip 和 端口
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
            //连接服务器
            if (!socketChannel.connect(inetSocketAddress)) {
                while (!socketChannel.finishConnect()) {
                    System.out.println("因为连接需要时间，客户端不会阻塞，可以做其它工作..");
                }
            }
            //...如果连接成功，就发送数据
            String str = "不哥们 你真帅啊~";
            //Wraps a byte array into a buffer

            ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
            //发送数据，将 buffer 数据写入 channel
            socketChannel.write(buffer);
        }
        System.in.read();
    }
}
