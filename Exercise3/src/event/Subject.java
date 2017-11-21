package event;

import java.util.EventObject;
import java.util.Vector;

public abstract class Subject<TEvent extends EventObject, TObserver extends Observer<TEvent>> {
    private Vector<TObserver> observers = new Vector<>();

    public void addListener(TObserver observer) {
        this.observers.addElement(observer);
    }

    public void removeListener(TObserver observer) {
        this.observers.removeElement(observer);
    }

    protected void fireChanged(TEvent event) {
        Vector<TObserver> copyListeners;

        synchronized (this) {
            copyListeners = new Vector<>(this.observers);
        }

        copyListeners.forEach(observer -> observer.changed(event));
    }
}
