package at.fhv.itb.sem5.exercise3;

import java.util.EventListener;

public interface ImageListener extends EventListener {
    void imageChanged(ImageEvent e);
}
