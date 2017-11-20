package bean;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public class ROIFilter implements ImageListener, Serializable {

    private int offsetX = 0;
    private int offsetY = 40;
    private int width = 448;
    private int height = 110;

    private PlanarImage input = null;

    private Vector<ImageListener> listeners = new Vector<>();

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
        imageChanged(new ImageEvent(this, input));
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        imageChanged(new ImageEvent(this, input));
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        imageChanged(new ImageEvent(this, input));
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        imageChanged(new ImageEvent(this, input));
    }

    public void addImageListener(ImageListener var1) {
        this.listeners.addElement(var1);
    }

    public void removeImageListener(ImageListener var1) {
        this.listeners.removeElement(var1);
    }

    protected void fireImageEvent(PlanarImage image) {
        ImageEvent e = new ImageEvent(this, image);

        Vector<ImageListener> copyListeners;
        synchronized (this) {
            copyListeners = new Vector<>(this.listeners);
        }
        copyListeners.forEach(imageListener -> imageListener.imageChanged(e));
    }

    @Override
    public void imageChanged(ImageEvent e) {
        Rectangle rectangle = new Rectangle(offsetX, offsetY, width, height);
        input = e.getValue();
        PlanarImage planarImage = PlanarImage.wrapRenderedImage(input.getAsBufferedImage(rectangle, input.getColorModel()));
        planarImage.setProperty("offsetX", rectangle.x);
        planarImage.setProperty("offsetY", rectangle.y);

        fireImageEvent(planarImage);
    }
}
