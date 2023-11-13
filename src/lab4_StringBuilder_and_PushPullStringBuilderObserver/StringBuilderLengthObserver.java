package lab4_StringBuilder_and_PushPullStringBuilderObserver;

public class StringBuilderLengthObserver implements StringBuilderObserver {
    @Override
    public void call(String str) {
        System.out.println(str.length());
    }
}
