package lab3;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Monster[] monsters = new Monster[5];
        Random generator = new Random(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        for (int i = 0; i < monsters.length; i++) {
            monsters[i] = MonsterFactory.generateMonster(generator);
        }
        float sum = Arrays.stream(monsters).map(Monster::attack).reduce(Float::sum).get();
        System.out.println(sum);
        if (Arrays.stream(monsters).map(Monster::attack).reduce(Float::sum).get() >= 50) {
            System.out.println("The hero has died.");
        }
        else {
            System.out.println("The hero is still alive.");
        }
    }
}
