package lab5.task2;

import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        String[] test = new String[]{
          "1",
          "22",
          "333",
          "4444"
        };
        // Solution 1:
        System.out.println(Arrays.stream(test).min(Comparator.comparing(String::length)).get().length());
        System.out.println(Arrays.stream(test).max(Comparator.comparing(String::length)).get().length());
        // Solution 2:
        System.out.println(Arrays.stream(test).map(String::length).min(Integer::compareTo).get());
        System.out.println(Arrays.stream(test).map(String::length).max(Integer::compareTo).get());
    }
}
