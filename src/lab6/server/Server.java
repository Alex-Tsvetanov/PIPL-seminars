package lab6.server;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
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

        public ServerRunnable(Socket client, Function<String, Boolean> onMessage, Function<Socket, Boolean> onQuit)
        {
            this.client = client;
            this.onMessage = onMessage;
            this.onQuit = onQuit;
        }

        @Override
        public void run()
        {
            try (Scanner in = new Scanner(client.getInputStream());
                 PrintStream out = new PrintStream(client.getOutputStream()))
            {
                while (true)
                {
                    String line = in.nextLine();
                    if (line.contains("quit"))
                    {
                        onQuit.apply(this.client);
                        return;
                    }
                    this.onMessage.apply(line);
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
            ThreadPool pool = new ThreadPool(3);

            while (true)
            {
                Socket client = serverSocket.accept();
                pool.addNewClient(client);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}