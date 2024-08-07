package com.loco.nio.eg1groupchat;

import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
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
                int count = selector.select(2000); //监听两秒，返回接收到的事件
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            //监听到 accept , 将该 channel 注册到selector
                            SocketChannel sc = listenChannel.accept();
                            sc.register(selector, SelectionKey.OP_ACCEPT);
                            System.out.println(sc.getRemoteAddress() + " online ~");
                        }
                        if (key.isReadable()) {
                            //监听到 read 事件, TODO 处理读
                        }

                        //手动移除当前 key ，防止重复处理
                        iterator.remove();

                    }

                } else {
                    System.out.println("无事发生， 等待~");
                }
            }


        } catch (Exception e) {

        }
    }

    //读取客户端消息
    private void readData(){

    }

    //转发消息给其它客户(通道)
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {

    }

}
