package at.fhv.itb.sem5.exercise3;

import at.fhv.itb.sem5.lib.pmp.filter.ForwardingFilter;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.InvalidParameterException;

public class SaveFilter extends ForwardingFilter<PlanarImage> {

    private final String fileName;

    public SaveFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, String fileName) throws InvalidParameterException {
        super(input, output);
        this.fileName = fileName;
    }

    public SaveFilter(Readable<PlanarImage> input, String fileName) throws InvalidParameterException {
        super(input);
        this.fileName = fileName;
    }

    public SaveFilter(Writeable<PlanarImage> output, String fileName) throws InvalidParameterException {
        super(output);
        this.fileName = fileName;
    }

    @Override
    protected boolean forward(PlanarImage entity) {
        try {
            // retrieve image
            BufferedImage bi = entity.getAsBufferedImage();
            File output = new File(fileName);
            ImageIO.write(bi, "png", output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}