package at.fhv.itb.sem5.sa;

import javax.media.jai.PlanarImage;
import java.util.Vector;

public abstract class ImageSubject {

    protected PlanarImage cache = null;

    private Vector<ImageObserver> observers = new Vector<>();

    public void addListener(ImageObserver observer) {
        this.observers.addElement(observer);
    }

    public void removeListener(ImageObserver observer) {
        this.observers.removeElement(observer);
    }

    protected void fireChanged(ImageEvent event) {
        Vector<ImageObserver> copyListeners;

        synchronized (this) {
            copyListeners = new Vector<>(this.observers);
        }

        copyListeners.forEach(observer -> observer.changed(event));
    }
}
