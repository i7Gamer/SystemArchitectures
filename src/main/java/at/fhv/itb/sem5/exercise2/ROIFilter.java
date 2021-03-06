package at.fhv.itb.sem5.exercise2;

import at.fhv.itb.sem5.lib.pmp.filter.DataTransformationFilter2;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import java.awt.*;
import java.security.InvalidParameterException;

public class ROIFilter extends DataTransformationFilter2<PlanarImage, PlanarImage>{

    private final Rectangle rectangle;

    public ROIFilter(Rectangle rectangle, Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
        this.rectangle = rectangle;
    }

    public ROIFilter(Rectangle rectangle, Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
        this.rectangle = rectangle;
    }

    public ROIFilter(Rectangle rectangle, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
        this.rectangle = rectangle;
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        PlanarImage planarImage = PlanarImage.wrapRenderedImage(entity.getAsBufferedImage(rectangle,entity.getColorModel()));
        planarImage.setProperty("offsetX",rectangle.x);
        planarImage.setProperty("offsetY",rectangle.y);
        return planarImage;
    }
}
