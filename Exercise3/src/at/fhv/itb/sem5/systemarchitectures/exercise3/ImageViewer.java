package at.fhv.itb.sem5.systemarchitectures.exercise3;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;

public class ImageViewer extends Canvas implements ImageListener, Serializable {

    private PlanarImage image;

    public ImageViewer() {
    }

    public void paint(Graphics var1) {

        if (image != null) {
            Dimension var2 = this.getSize();
            int var3 = var2.height;
            int var4 = var2.width;

            Image i = image.getAsBufferedImage();

            var1.drawImage(i, 0, 0, var4, var3, null);
        }
    }

    @Override
    public void imageChanged(ImageEvent e) {

        image = e.getValue();

        if (image != null) {
            this.setSize(image.getWidth(), image.getHeight());
            this.repaint();
        }
    }
}
