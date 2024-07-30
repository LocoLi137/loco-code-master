package com.loco.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

/**
 * description:
 * author: Loco.Li
 */
public class NIOFileChannel {
    public static void main(String[] args) throws Exception {
        //创建文件的输入流
        File file = new File(Objects.requireNonNull(NIOFileChannel.class.getClassLoader().getResource("file01.txt")).getPath());
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

}
