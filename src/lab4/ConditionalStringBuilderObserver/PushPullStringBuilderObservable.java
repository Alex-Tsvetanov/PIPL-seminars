package lab4.ConditionalStringBuilderObserver;

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

    void push(char symbol) throws WrongMethodException {
        if (mode == ObserverMode.PUSH) {
            this.setStr(symbol + getStr());
        }
        else
            throw new WrongMethodException();
    }
    void pull() throws WrongMethodException {
        if (mode == ObserverMode.PULL) {
            String newStr = getStr().substring(0, getStr().length() - 1);
            this.setStr(newStr);
        } else
            throw new WrongMethodException();
    }
}

