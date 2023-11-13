package lab4.StringBuilder_and_PushPullStringBuilderObserver;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class StringBuilderObserver {
    static StringBuilderObserver from(Consumer<String> f) {
        return new StringBuilderObserver() {
            @Override
            void call(String str) {
                f.accept(str);
            }
        };
    }
    abstract void call(String str);
}
