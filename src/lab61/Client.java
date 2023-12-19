package lab61;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        new Client().start();
    }

    private String myName;
    private volatile boolean shouldStop;

    public Client() {
        myName = null;
        shouldStop = false;
    }

    public void start() {
        try {
            Socket server = new Socket("localhost", 8080);

            Scanner in = new Scanner(server.getInputStream());
            PrintStream out = new PrintStream(server.getOutputStream());

            Thread reader = new Thread(() -> {
                while (!shouldStop) {
                    if (in.hasNextLine()) {
                        String line = in.nextLine();
                        if (!line.startsWith(myName))
                            System.out.println(line);
                    }
                }
            });

            Thread writer = new Thread(() -> {
                Scanner console = new Scanner(System.in);

                System.out.println("Enter name: ");
                myName = console.nextLine();

                String line = null;

                while (true) {
                    line = console.nextLine();
                    out.println(myName + ": " + line);
                    if (line.contains("quit")) {
                        shouldStop = true;
                        try {
                            server.close();
                            in.close();
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                }
            });

            reader.start();
            writer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}