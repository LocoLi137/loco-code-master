package com.loco.nio.eg1groupchat;

import java.io.IOException;
import java.lang.invoke.VarHandle;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

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
        //启动客户端
        GroupChatClient chatClient = new GroupChatClient();

        //启动线程
        new Thread(() -> {
           while (true) {
               chatClient.readMsg();
               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }).start();

        //发送数据给服务点
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            chatClient.sendMsg(line);
        }
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

    //向服务器发送消息
    public void sendMsg(String msg) {
        msg = userName + "说" + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取从服务器端回复的消息
    public void readMsg(){
        try {
            int readChannels = selector.select();
            if (readChannels > 0) {
                //有可用的读通道
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        //得到相关通道
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        channel.read(buffer);
                        channel.read(buffer);
//                        if (bytesRead > 0) {
                            String msg = new String(buffer.array());
//                            String msg = new String(buffer.array(), 0, bytesRead); // 仅读取实际字节数
                            System.out.println(msg.trim());
//                        }
                    }
                    iterator.remove();
                }
            } else {
//                System.out.println("没有可用通道");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
