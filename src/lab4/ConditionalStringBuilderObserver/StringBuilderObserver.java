package lab4.ConditionalStringBuilderObserver;
import java.util.function.Consumer;

public abstract class StringBuilderObserver {
    static StringBuilderObserver from(Consumer<String> f) {
        return new StringBuilderObserver() {
            @Override
            void call(String str) {
                f.accept(str);
            }

            @Override
            boolean shouldCall(String oldStr, String newStr) {
                return true;
            }
        };
    }

    abstract void call(String str);

    abstract boolean shouldCall(String oldStr, String newStr);
}
