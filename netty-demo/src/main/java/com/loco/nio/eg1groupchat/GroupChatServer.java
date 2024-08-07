package com.loco.nio.eg1groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

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

    }

    //读取客户端消息
    private void readData(){

    }

    //转发消息给其它客户(通道)
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {

    }

}
