package com.test.ServerSocket;

import java.io.IOException;
import java.net.ServerSocket;

public class Application {

    public static void main(String[] args) throws IOException,InterruptedException
    {
        ServerSocketTask serverSocketTask = new ServerSocketTask();
        Thread serverThread = new Thread(serverSocketTask);
        serverThread.start();
        serverThread.join();
    }
}
