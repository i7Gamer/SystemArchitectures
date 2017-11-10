package at.fhv.itb.sem5.exercise2;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.StreamCorruptedException;

public class ImageSource extends Source<PlanarImage> {

    private final String op;
    private final String link;

    public ImageSource(String op, String link){
        super();
        this.link = link;
        this.op = op;
    }

    public ImageSource(String op, String link, Writeable<PlanarImage> output) {
        super(output);
        this.link = link;
        this.op = op;
    }


    @Override
    public PlanarImage read() throws StreamCorruptedException {
        if(ENDING_SIGNAL == null){
            ENDING_SIGNAL = 1;
            return JAI.create(op, link);
        }
        return null;
    }
}
