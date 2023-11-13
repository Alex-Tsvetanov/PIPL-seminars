package lab4.StringBuilder_and_PushPullStringBuilderObserver;

public class StringBuilderLengthObserver extends StringBuilderObserver {
    @Override
    public void call(String str) {
        System.out.println(str.length());
    }
}
