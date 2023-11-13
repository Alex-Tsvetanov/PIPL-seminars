package lab4_StringBuilder_and_PushPullStringBuilderObserver;

public class Main {
    public static void main(String[] args) {
        PushPullStringBuilderObservable s = new PushPullStringBuilderObservable(PushPullStringBuilderObservable.ObserverMode.PUSH);
        s.subscribe(new StringBuilderChangeObserver());
        s.subscribe(new StringBuilderLengthObserver());
        s.push('a');
        s.push('b');
        s.push('c');
        s.changeMode();
        s.pull();
        s.pull();
    }
}
