package lab4.ConditionalStringBuilderObserver;

public class StringBuilderChangeObserver extends StringBuilderObserver {
    @Override
    public void call(String str) {
        System.out.println(str);
    }

    @Override
    boolean shouldCall(String oldStr, String newStr) {
        return true;
    }
}
