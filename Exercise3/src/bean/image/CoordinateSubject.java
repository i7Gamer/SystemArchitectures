package bean.image;

import at.fhv.itb.sem5.lib.calccentroidsfilter.Coordinate;

import java.util.List;
import java.util.Vector;

public abstract class CoordinateSubject {

    protected List<Coordinate> cache = null;

    private Vector<CoordinateObserver> observers = new Vector<>();

    public void addListener(CoordinateObserver observer) {
        this.observers.addElement(observer);
    }

    public void removeListener(CoordinateObserver observer) {
        this.observers.removeElement(observer);
    }

    protected void fireChanged(CoordinateEvent event) {
        Vector<CoordinateObserver> copyListeners;

        synchronized (this) {
            copyListeners = new Vector<>(this.observers);
        }

        copyListeners.forEach(observer -> observer.changed(event));
    }
}