package bean.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class SaveFilter extends ImageSubject implements ImageObserver, Serializable {

    private String savePath = "";

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
        changed(new ImageEvent(this, cache));
    }

    @Override
    public void changed(ImageEvent event) {
        cache = event.getValue();

        try {
            // retrieve image
            BufferedImage bi = cache.getAsBufferedImage();
            File output = new File(savePath);
            ImageIO.write(bi, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ImageEvent newEvent = new ImageEvent(this, cache);
        fireChanged(newEvent);
    }
}
