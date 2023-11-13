package lab4_StringBuilder_and_PushPullStringBuilderObserver;

public class PushPullStringBuilderObservable extends StringBuilder implements Observable {

    public PushPullStringBuilderObservable(ObserverMode mode) {
        super();
        this.mode = mode;
    }

    public void changeMode() {
        if (mode == ObserverMode.PULL)
            mode = ObserverMode.PUSH;
        if (mode == ObserverMode.PUSH)
            mode = ObserverMode.PULL;
    }

    public enum ObserverMode {
        PUSH,
        PULL
    }

    private ObserverMode mode;

    void push(char symbol) {
        if (mode == ObserverMode.PUSH) {
            this.setStr(symbol + getStr());
        } else throw new RuntimeException();
    }
    void pull() {
        if (mode == ObserverMode.PULL) {
            String newStr = getStr().substring(0, getStr().length() - 1);
            this.setStr(newStr);
        } else throw new RuntimeException();
    }
}

