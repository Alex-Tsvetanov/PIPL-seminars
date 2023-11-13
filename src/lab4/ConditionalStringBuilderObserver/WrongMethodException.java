package lab4.ConditionalStringBuilderObserver;

import javax.activity.InvalidActivityException;

public class WrongMethodException extends InvalidActivityException {
    public WrongMethodException() {
        super("Push/pull method doesn't correspond to the mode of the StringBuilder");
    }
}
