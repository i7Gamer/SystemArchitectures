package bean.image;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;

public class Viewer extends Canvas implements ImageObserver, Serializable {

    private PlanarImage image = null;

    public Viewer() {
        this.setSize(200, 100);
        this.repaint();
    }

    public void paint(Graphics var1) {

        if (image != null) {
            this.setSize(image.getWidth(), image.getHeight());
            Dimension dimension = this.getSize();
            Image i = image.getAsBufferedImage();

            var1.drawImage(i, 0, 0, dimension.width, dimension.height, null);
        }
    }

    @Override
    public void changed(ImageEvent event) {
        image = event.getValue();

        if (image != null) {
            this.repaint();
        }
    }
}
