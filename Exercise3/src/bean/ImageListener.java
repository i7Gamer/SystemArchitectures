package bean;

import java.util.EventListener;

public interface ImageListener extends EventListener {
    void imageChanged(ImageEvent e);
}
