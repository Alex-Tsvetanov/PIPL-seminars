package lab2;

import java.util.Arrays;

public class Main {
    static int[] sortAndFilter(int[] array, int key) {
        return Arrays.stream(array).filter(x->x<=key).sorted().toArray();
    }
    public static void main(String[] args) {
        System.out.print(Arrays.toString(sortAndFilter(new int[]{4, 9, 8, 7, 6, 5, 1, 2, 3}, 7)));
    }
}
