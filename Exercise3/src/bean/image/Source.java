package bean.image;

import event.image.ImageEvent;
import event.image.ImageSubject;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Source extends ImageSubject implements Serializable {

    private String link = "";

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
        try {
            PlanarImage image = Files.exists(Paths.get(link)) ? JAI.create("fileload", link) : null;
            ImageEvent event = new ImageEvent(this, image);
            fireChanged(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
