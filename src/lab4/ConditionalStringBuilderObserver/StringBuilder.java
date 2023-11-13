package lab4.ConditionalStringBuilderObserver;

import java.util.ArrayList;
import java.util.Map;

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

    protected void setStr(String newStr) {
        final String oldStr = this.str;
        if (oldStr.compareTo(newStr) == 0)
            return;
        this.str = newStr;
        for (StringBuilderObserver handler : observers) {
            if (handler.shouldCall(oldStr, newStr))
                handler.call(this.str);
        }
    }

    public void subscribe(StringBuilderObserver handler) {
        observers.add(handler);
    }
}
