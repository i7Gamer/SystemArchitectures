package at.fhv.itb.sem5.sa;

import javax.media.jai.JAI;
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
            cache = Files.exists(Paths.get(link)) ? JAI.create("fileload", link) : null;
            ImageEvent event = new ImageEvent(this, cache);
            fireChanged(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
