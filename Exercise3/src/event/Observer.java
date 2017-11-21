package event;

import java.util.EventListener;
import java.util.EventObject;

public interface Observer<Event extends EventObject> extends EventListener {
    void changed(Event event);
}
