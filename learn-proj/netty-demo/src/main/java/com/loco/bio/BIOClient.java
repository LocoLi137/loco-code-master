package com.loco.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @auther LociLi
 */
public class BIOClient {
    private final static String exit = "exit";

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 6666);
        OutputStream outputStream = socket.getOutputStream();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            if (exit.equals(line)) {
                break;
            }
            outputStream.write(line.getBytes());
            outputStream.flush();
        }

        outputStream.close();
    }
}
