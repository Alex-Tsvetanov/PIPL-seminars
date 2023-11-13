package lab4.StringBuilder_and_PushPullStringBuilderObserver;

import javax.activity.InvalidActivityException;

public class WrongMethodException extends InvalidActivityException {
    public WrongMethodException() {
        super("Push/pull method doesn't correspond to the mode of the StringBuilder");
    }
}
