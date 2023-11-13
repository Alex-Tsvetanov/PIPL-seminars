package lab4_StringBuilder_and_PushPullStringBuilderObserver;

import java.util.ArrayList;

public class StringBuilder {
    private String str;
    private final ArrayList<StringBuilderObserver> observers;

    StringBuilder() {
        str = "";
        observers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return str;
    }

    public String getStr() {
        return str;
    }

    protected void setStr(String str) {
        this.str = str;
        for (StringBuilderObserver handler : observers) {
            handler.call(str);
        }
    }

    public void subscribe(StringBuilderObserver handler) {
        observers.add(handler);
    }
}
