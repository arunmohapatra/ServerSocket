package com.test.ServerSocket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketImpl;

public class ServerSocketTask implements Runnable {

    ServerSocket serverSocket;
    int serverPort = 0;

    public ServerSocketTask(){
        try{
            this.serverSocket = new ServerSocket(0);
            int bindingPort = serverSocket.getLocalPort();
            this.setServerPort(bindingPort);
        }
        catch (IOException exp){
            System.out.println("Unable to bind any port");
        }
    }

    public void run(){
        try {
            System.out.println("Listening on port : "+this.serverPort);
            Socket clientSocket = this.serverSocket.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            while (true) {
                //Wait for read
                String incomingMessage = (String) objectInputStream.readObject();
                processClientMessage(incomingMessage);

                Thread.sleep(5000);

                //Write response back
                objectOutputStream.writeObject("hi");
            }
        }
        catch (IOException | ClassNotFoundException | InterruptedException exp){
            exp.printStackTrace();
        }
    }

    public synchronized int getServerPort() {
        return serverPort;
    }

    public synchronized void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    private void processClientMessage(String message){
        System.out.println("Message from client : "+message);
    }
}
