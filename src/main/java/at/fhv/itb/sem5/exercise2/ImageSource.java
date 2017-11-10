package at.fhv.itb.sem5.exercise2;

import pmp.filter.Sink;
import pmp.filter.Source;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.StreamCorruptedException;

public class ImageSource extends Source<PlanarImage> {

    String op;
    String link;

    public ImageSource(String op, String link){
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
