package at.fhv.itb.sem5.exercise3;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class ImageSource implements Serializable {


    private String link;
    private PlanarImage image;
    private Vector<ImageListener> listeners = new Vector<>();

    public ImageSource() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        try {
            setImage(Files.exists(Paths.get(link)) ? JAI.create("fileload", link) : null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addImageListener(ImageListener var1) {
        this.listeners.addElement(var1);
    }

    public void removeImageListener(ImageListener var1) {
        this.listeners.removeElement(var1);
    }


    protected synchronized void fireImageEvent() {
        ImageEvent e = new ImageEvent(this, this.image);

        Vector<ImageListener> copyListeners;
        synchronized (this) {
            copyListeners = new Vector<>(this.listeners);
        }
        copyListeners.forEach(imageListener -> imageListener.imageChanged(e));
    }

    public PlanarImage getImage() {
        return image;
    }

    public void setImage(PlanarImage image) {
        this.image = image;
        fireImageEvent();
    }
}
