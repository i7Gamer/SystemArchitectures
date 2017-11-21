package bean.image;

import java.util.EventListener;

public interface ImageObserver extends EventListener {
    void changed(ImageEvent event);
}
