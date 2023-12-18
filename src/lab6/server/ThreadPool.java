package lab6.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class ThreadPool {
    private final Thread[] threads;
    private ArrayList<Socket> sockets;

    public <CustomRunnable extends Runnable> ThreadPool(int size) {
        this.threads = new Thread[size];
        this.sockets = new ArrayList<>();
    }

    public Thread getNewThread(int i) {
        return new Thread(new Server.ServerRunnable(
                this.sockets.get(i),
                this::onMessage,
                this::onClientLeft
        ));
    }

    public void addNewClient(Socket newClient) {
        this.sockets.add(newClient);
        if(this.sockets.size() <= this.threads.length) {
            this.threads[this.sockets.size() - 1] = getNewThread(this.sockets.size() - 1);
            this.threads[this.sockets.size() - 1].start();
        }
    }
    public Boolean onClientLeft(Socket client) {
        int threadToBecomeFree = this.sockets.indexOf(client);
        this.threads[threadToBecomeFree].stop();
        return sockets.remove(client);
    }

    public Boolean onMessage(String line) {
        for (int i = 0; i < this.threads.length && i < this.sockets.size() ; i ++) {
            try {
                new PrintStream(this.sockets.get(i).getOutputStream()).println(line);
                System.out.println(line);
            }
            catch (IOException e) {
                this.onClientLeft(this.sockets.get(i));
                System.out.println("Unable to message client " + i + ". Connection with the client was terminated.");
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
