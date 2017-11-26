package at.fhv.itb.sem5.sa;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;

public class ROIFilter extends ImageSubject implements ImageObserver, Serializable {

    private int offsetX = 0;
    private int offsetY = 40;
    private int width = 448;
    private int height = 110;

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
        changed(new ImageEvent(this, cache));
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        changed(new ImageEvent(this, cache));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        changed(new ImageEvent(this, cache));
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        changed(new ImageEvent(this, cache));
    }

    @Override
    public void changed(ImageEvent event) {
        Rectangle rectangle = new Rectangle(offsetX, offsetY, width, height);
        cache = event.getValue();
        PlanarImage planarImage = PlanarImage.wrapRenderedImage(cache.getAsBufferedImage(rectangle, cache.getColorModel()));
        planarImage.setProperty("offsetX", rectangle.x);
        planarImage.setProperty("offsetY", rectangle.y);

        ImageEvent newEvent = new ImageEvent(this, planarImage);
        fireChanged(newEvent);
    }
}
