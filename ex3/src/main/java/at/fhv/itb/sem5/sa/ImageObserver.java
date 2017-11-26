package at.fhv.itb.sem5.sa;

import java.util.EventListener;

public interface ImageObserver extends EventListener {
    void changed(ImageEvent event);
}
