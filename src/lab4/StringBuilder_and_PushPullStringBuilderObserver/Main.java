package lab4.StringBuilder_and_PushPullStringBuilderObserver;

import lab4.ConditionalStringBuilderObserver.WrongMethodException;

public class Main {
    public static void main(String[] args) {
        PushPullStringBuilderObservable s = new PushPullStringBuilderObservable(PushPullStringBuilderObservable.ObserverMode.PUSH);
        s.subscribe(new StringBuilderChangeObserver());
        s.subscribe(new StringBuilderLengthObserver());
        s.subscribe(StringBuilderObserver.from((str -> System.out.println("functions are handlers too"))));
        try {
            s.push('a');
            s.push('b');
            s.push('c');
            s.changeMode();
            s.pull();
            s.pull();
        }
        catch (WrongMethodException e) {
            System.out.println(e.getMessage());
        }
    }
}
