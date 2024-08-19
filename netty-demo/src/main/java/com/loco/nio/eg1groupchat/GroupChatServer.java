package com.loco.nio.eg1groupchat;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Set;

/**
 * description:
 * author: Loco.Li
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private final int PORT = 8888;
    public static void main(String[] args) {
        //创建服务器对象
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }

    //初始化工作
    public GroupChatServer() {
        try {
            //得到选择器
            selector = Selector.open();
            //绑定端口
            listenChannel = ServerSocketChannel.open();
            listenChannel.bind(new InetSocketAddress(PORT));
            //设置非阻塞模式
            listenChannel.configureBlocking(false);
            ///将该 listenChannel 注册到 selector
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //监听
    public void listen(){
        try {
            while (true) {
                int count = selector.select(); //监听两秒，返回接收到的事件
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            //监听到 accept , 将该 channel 注册到selector
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + " online ~");
                        }
                        if (key.isReadable()) {
                            //监听到 read 事件, TODO 处理读
                            readData(key);
                        }
                        //手动移除当前 key ，防止重复处理
                        iterator.remove();
                    }
                } else {
                    System.out.println("无事发生， 等待~");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //读取客户端消息
    private void readData(SelectionKey key) throws IOException {
        SocketChannel channel = null;
        try {
            //得到channel
            channel = (SocketChannel) key.channel();
            //创建buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read = channel.read(buffer);
            if (read > 0) {
                String msg = new String(buffer.array(), 0, read);
                System.out.println("from client :" + msg); //服务端 输出本次拿到的消息,只读取实际字节
                //向其他客户端转发消息
                send2OtherClients(msg, channel);
            }
        } catch (IOException e) {
            System.out.println(channel.getRemoteAddress() + " 离线了");
            //取消注册
            key.cancel();
            //关闭通道
            channel.close();
            e.printStackTrace();
        }
    }

    //转发消息给其它客户(通道)
    private void send2OtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中~");
        //遍历所有注册到 selector 上的 socketChannel 并排除self
        for (SelectionKey key : selector.keys()) {//通过 key 取出对应 socketChannel
            Channel targetChannel = key.channel();
            //排除自己
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                //将 msg 存储到 buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

                dest.write(buffer);
            }
        }
    }

}
