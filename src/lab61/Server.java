package lab61;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int maxClients = 3;

    public static void main(String[] args) {
        new Server().start();
    }

    private List<PrintStream> clients;
    private final Object clientsLock;
    private ExecutorService threadPool;

    private class ServerRunnable implements Runnable {
        private final Socket client;

        public ServerRunnable(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try (Scanner in = new Scanner(client.getInputStream());
                 PrintStream out = new PrintStream(client.getOutputStream())) {

                synchronized (clientsLock) {
                    if (clients.size() >= maxClients) {
                        out.println("Chat is full. Try again later.");
                        return;
                    }
                    clients.add(out);
                }

                while (true) {
                    String line = in.nextLine();
                    if (line.contains("quit")) {
                        synchronized (clientsLock) {
                            clients.remove(out);
                            return;
                        }
                    }
                    synchronized (clientsLock) {
                        for (PrintStream c : clients)
                            c.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Server() {
        clients = new ArrayList<>();
        clientsLock = new Object();
        threadPool = Executors.newFixedThreadPool(maxClients);
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Socket client = serverSocket.accept();
                if (clients.size() < maxClients) {
                    threadPool.execute(new ServerRunnable(client));
                } else {
                    PrintStream out = new PrintStream(client.getOutputStream());
                    out.println("Chat is full. Try again later.");
                    client.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}