package lab3;

import java.util.Random;

public class MonsterFactory {
    public static Monster generateMonster(Random generator) {
        switch(generator.nextInt(3)) {
            case 0:
                return new Zombie();
            case 1:
                return new Vampire();
            case 2:
                return new Dragon();
        }
        return null;
    }
}
