package com.loco.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * description:
 * author: Loco.Li
 */
public class NIOFileChannel {

    static String classpath =System.getProperty("user.dir") + "\\netty-demo\\";

    public static void main(String[] args){
        try {
//            writeFile();
//            readFile();
            readAndWrite();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeFile() throws Exception {
        String str = """
            不哥们你真帅啊！！
            不哥们你真帅啊！！
            不哥们你真帅啊！！
            """;
        FileOutputStream fos = new FileOutputStream(classpath+"file01.txt");
        FileChannel fileChannel = fos.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        byteBuffer.flip(); //切换读模式
        fileChannel.write(byteBuffer);
        fos.close();
    }

    private static void readFile() throws Exception {
        //创建文件的输入流
        File file = new File(classpath+"file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        //通过 fileInputStream 获取对应的 FileChannel -> 实际类型 FileChannelImpl
        FileChannel fileChannel = fileInputStream.getChannel();
        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        //将 通道的数据读入到 Buffer
        fileChannel.read(byteBuffer);
        //将 byteBuffer 的 字节数据 转成 String
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    public static void readAndWrite() throws Exception {
        FileInputStream fileInputStream = new FileInputStream(classpath + "file01.txt");
        FileChannel fileChannel01 = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream(classpath + "file02.txt");
        FileChannel fileChannel02 = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true) { //循环读取
            byteBuffer.clear(); //清空 buffer
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read =" + read);
            if(read == -1) { //表示读完
                break;
            }
            //将 buffer 中的数据写入到 fileChannel02 -- 2.txt
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }
        //关闭相关的流
        fileInputStream.close();
        fileOutputStream.close();
    }
}
