package bean;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Vector;

public class ImageSource implements Serializable {

    private String link = "";
    private Vector<ImageListener> listeners = new Vector<>();

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        try {
            fireImageEvent(Files.exists(Paths.get(link)) ? JAI.create("fileload", link) : null);
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

    protected void fireImageEvent(PlanarImage image) {
        ImageEvent e = new ImageEvent(this, image);

        Vector<ImageListener> copyListeners;
        synchronized (this) {
            copyListeners = new Vector<>(this.listeners);
        }
        copyListeners.forEach(imageListener -> imageListener.imageChanged(e));
    }
}
