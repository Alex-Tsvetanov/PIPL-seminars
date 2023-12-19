package lab6.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class ThreadPool {
    private final Object mutex;
    private final Thread[] threads;
    private final Socket[] activeClients;
    private final Queue<Socket> sockets;

    public ThreadPool(int size) {
        this.mutex = new Object();
        this.threads = new Thread[size];
        this.activeClients = new Socket[size];
        this.sockets = new LinkedList<>();
    }
    private Integer getFreeThread() {
        for (int i = 0 ; i < this.threads.length ; i ++) {
            if (this.threads[i] == null) {
                return i;
            }
        }
        return null;
    }

    private void stopThread(int threadId) {
        System.out.println("Thread " + threadId + " to be stopped.");
        System.out.println("Thread " + threadId + " is being stopped.");
        this.threads[threadId].interrupt();
        System.out.println("Thread " + threadId + " has been stopped.");
        this.activeClients[threadId] = null;
        this.threads[threadId] = null;
        System.out.println("Thread and active clients were cleaned up.");
        if (!this.sockets.isEmpty()) {
            System.out.println("Clients are available in the queue.");
            synchronized (this.mutex) {
                Socket waitingClient = this.sockets.poll();
                assignClient(waitingClient, threadId);
            }
        }
    }

    private void assignClient(Socket waitingClient, int threadId) {
        System.out.println("Creating a thread with client " + waitingClient);
        this.threads[threadId] = new Thread(new Server.ServerRunnable(
                waitingClient,
                this::onMessage,
                (Socket client) -> this.onClientLeft(client, threadId),
                this::onClientConnected
        ));
        this.activeClients[threadId] = waitingClient;
        this.threads[threadId].start();
        System.out.println("Client " + waitingClient + " is assigned to " + threadId);
    }

    public Boolean queueUp(Socket newClient) {
        synchronized (this.mutex) {
            this.sockets.add(newClient);
        }
        System.out.println("Client " + newClient + " is queued up.");
        Integer freeThread = getFreeThread();
        if (freeThread != null) {
            synchronized (this.mutex) {
                Socket waitingClient = this.sockets.poll();
                assignClient(waitingClient, freeThread);
                System.out.println("Client " + waitingClient + " is assigned to " + freeThread);
                return true;
            }
        }
        return false;
    }
    public Boolean onClientConnected(Socket newClient) {
        System.out.println("Client " + newClient + " connected.");
        return true;
    }
    public Boolean onClientLeft(Socket quitedClient, Integer threadId) {
        System.out.println(quitedClient + " has left.");
        if (threadId == null) {
            for (threadId = 0; threadId < this.threads.length; threadId++) {
                if (this.activeClients[threadId] == quitedClient) {
                    break;
                }
            }
        }
        if (threadId == this.threads.length)
            return false;
        System.out.println("Thread " + threadId + " is now free.");
        stopThread(threadId);
        return true;
    }

    public Boolean onMessage(String line) {
        System.out.println("CHAT LOG: " + line);
        for (int i = 0; i < this.activeClients.length ; i ++) {
            // System.out.println("Checking thread " + i);
            if (this.activeClients[i] == null)
                continue;
            // System.out.println("Thread " + i + " is active.");
            try {
                // System.out.println("Thread " + i + " handles client " + this.activeClients[i]);
                new PrintStream(this.activeClients[i].getOutputStream()).println(line);
            }
            catch (IOException e) {
                this.onClientLeft(this.activeClients[i], null);
                System.out.println("Unable to message client " + i + ". Connection with the client was terminated.");
                System.out.println(e.getMessage());
            }
        }
        return true;
    }
}
