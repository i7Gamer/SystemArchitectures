package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;

public class ImageViewer extends Canvas implements ImageListener, Serializable {

    private PlanarImage image = null;

    public ImageViewer() {
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
    public void imageChanged(ImageEvent e) {

        image = e.getValue();

        if (image != null) {
            this.repaint();
        }
    }
}
