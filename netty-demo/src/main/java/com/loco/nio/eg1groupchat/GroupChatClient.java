package com.loco.nio.eg1groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @auther LociLi
 */
public class GroupChatClient {
    private final String HOST = "127.0.0.1";
    private final int PORT = 8888;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public static void main(String[] args) {

    }

    //初始化工作
    public GroupChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            //非阻塞
            socketChannel.configureBlocking(false);
            //将 channel 注册到 selector
            socketChannel.register(selector, SelectionKey.OP_READ);
            //得到username
            userName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(userName + "client ok");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
