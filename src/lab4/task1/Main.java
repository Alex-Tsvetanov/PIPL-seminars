package lab4.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Read/Write?");
        Scanner in = new Scanner(System.in);
        switch (in.nextLine()) {
            case "Read":
                Object value = readObjects();
                break;
            case "Write":
                writeObjects(in);
                break;
        }
    }

    private static final String filePath = "person.bin";

    private static void writeObjects(Scanner in) {
        System.out.println("Number of people?");
        final int n = in.nextInt();
        ArrayList<Person> people = new ArrayList<>();
        for (int i = 0 ; i < n ; i ++) {
            System.out.print("Name? "); in.nextLine();
            String name = in.nextLine();
            System.out.print("Birth? ");
            LocalDate birth = LocalDate.parse(in.nextLine());

            people.add(new Person(name, birth));
        }
        try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(filePath))))
        {
            out.writeObject(people);
            for (Person person : people) {
                System.out.println(person);
            }
        }
        catch (IOException e)
        {
            // handle IO Exception
            System.out.println("IOException: " + e.getMessage());
        }
    }

    private static Person[] readObjects() {
        ArrayList<Person> people = null;
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(Paths.get(filePath))))
        {
            people = (ArrayList<Person>) in.readObject();
        }
        catch (IOException e) {
            // handle IO Exception
            System.out.println("IOException: " + e.getMessage());
        }
        catch (ClassNotFoundException e) {
            // handle IO Exception
            System.out.println("ClassNotFoundException: " + e.getMessage());
        }
        if (people != null) {
            for (Person person : people) {
                System.out.println(person);
            }
            return people.toArray(new Person[0]);
        } else {
            return new Person[0];
        }
    }
}
