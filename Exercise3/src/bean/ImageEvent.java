package bean;

import javax.media.jai.PlanarImage;
import java.util.EventObject;

public class ImageEvent extends EventObject {
    private PlanarImage value;

    public ImageEvent(Object source, PlanarImage value) {
        super(source);
        this.value = value;
    }

    public PlanarImage getValue() {
        return this.value;
    }

    public void setValue(PlanarImage image) {
        this.value = image;
    }
}
