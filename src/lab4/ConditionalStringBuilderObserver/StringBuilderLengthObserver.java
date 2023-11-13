package lab4.ConditionalStringBuilderObserver;

public class StringBuilderLengthObserver extends StringBuilderObserver {
    @Override
    public void call(String str) {
        System.out.println(str.length());
    }

    @Override
    boolean shouldCall(String oldStr, String newStr) {
        return oldStr.length() != newStr.length();
    }
}
