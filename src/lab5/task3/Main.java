package lab5.task3;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /*
        Напишете програма на Java, за имплементиране на ламбда изрази, за да проверите дали списъкът от низове е само с цифри, или само букви, или само специални символи.
        */
        String[] test = new String[] {
                "aaaaaaa",
                "1111111",
                "#######",
                "a1#a1#a"/*,
                "a1a1a1a1",
                "1#1#1#1#",
                "a#a#a#a#"*/
        };
        System.out.println(
                Arrays.stream(test).allMatch(
                        x -> {
                            int check = x.chars().reduce(7, (res, element) -> {
                                int isDigit = ('0' <= element && element <= '9') ? 1 : 0;
                                int isLetter = (('a' <= element && element <= 'z') || ('A' <= element && element <= 'Z')) ? 1 : 0;
                                int isSpecialChar = (2 - (isDigit + isLetter)) / 2;

                                return res & ((isDigit) + (isLetter << 1) + (isSpecialChar << 2));
                            });
                            return check == 1 || check == 2 || check == 4;
                        }
                )
        );
    }
}
