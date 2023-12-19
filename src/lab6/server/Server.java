package lab6.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

public class Server
{
    public static void main(String[] args)
    {
        new Server().start();
    }

    public static class ServerRunnable implements Runnable
    {
        private final Socket client;
        private final Function<String, Boolean> onMessage;
        private final Function<Socket, Boolean> onQuit;
        private final Function<Socket, Boolean> onConnect;

        public ServerRunnable(Socket client, Function<String, Boolean> onMessage, Function<Socket, Boolean> onQuit, Function<Socket, Boolean> onConnect)
        {
            this.client = client;
            this.onConnect = onConnect;
            this.onMessage = onMessage;
            this.onQuit = onQuit;
        }

        @Override
        public void run()
        {
            this.onConnect.apply(this.client);
            try (Scanner in = new Scanner(client.getInputStream());
                 PrintStream out = new PrintStream(client.getOutputStream()))
            {
                while (true)
                {
                    try {
                        String line = in.nextLine();
                        if (line.contains("quit"))
                        {
                            onQuit.apply(this.client);
                            return;
                        }
                        this.onMessage.apply(line);
                    }
                    catch (NoSuchElementException e) {
                        System.out.println("Client forcefully left");
                        break;
                    }
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Server() { }

    public void start()
    {
        try
        {
            ServerSocket serverSocket = new ServerSocket(8080);
            ThreadPool pool = new ThreadPool(2);

            while (true)
            {
                Socket client = serverSocket.accept();
                pool.queueUp(client);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}