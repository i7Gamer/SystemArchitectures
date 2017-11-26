package bean.image;

import java.util.EventListener;

public interface CoordinateObserver extends EventListener {

    void changed(CoordinateEvent event);
}
