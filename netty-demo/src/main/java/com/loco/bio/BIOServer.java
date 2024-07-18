package com.loco.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @auther LociLi
 */
public class BIOServer {

    public static void main(String[] args) throws IOException {
        //1.创建线程池
        //2.如果有客户端链接，则创建创建线程与之通信
        try (
                ExecutorService pool = Executors.newCachedThreadPool();
                ServerSocket serverSocket = new ServerSocket(6666);
        ) {
            System.out.println("server started");
            while (true) {
                //监听，阻塞等待客户端链接
                Socket accept = serverSocket.accept();

                System.out.println(accept.getLocalAddress().getHostAddress() + ":" + accept.getPort() + "accept connected");
                //创建创建线程与之通信
                pool.execute(() -> {
                    handler(accept);
                });
            }
        }

    }

    private static void handler(Socket socket) {

        try (
                //通过socket获取输入流
                InputStream inputStream = socket.getInputStream();
                ) {
            byte[] bytes = new byte[1024];
            //循环读取客户端发送的数据
            while (true) {
                int read = inputStream.read(bytes);
                if (read == -1) {
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ": " + new String(bytes, 0, read)); // 输出客户端发送的数据
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
