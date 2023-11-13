package lab4.StringBuilder_and_PushPullStringBuilderObserver;

public class StringBuilderChangeObserver extends StringBuilderObserver {
    @Override
    public void call(String str) {
        System.out.println(str);
    }
}
