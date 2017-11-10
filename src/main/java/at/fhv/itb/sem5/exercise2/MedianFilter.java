package at.fhv.itb.sem5.exercise2;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import java.security.InvalidParameterException;

public class MedianFilter extends DataTransformationFilter2<PlanarImage, PlanarImage>{

    private final MedianFilterShape medianFilterShape;
    private final int medianFilterSize;


    public MedianFilter(MedianFilterShape medianFilterShape, int medianFilterSize, Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
        this.medianFilterShape = medianFilterShape;
        this.medianFilterSize = medianFilterSize;
    }

    public MedianFilter(MedianFilterShape medianFilterShape, int medianFilterSize, Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
        this.medianFilterShape = medianFilterShape;
        this.medianFilterSize = medianFilterSize;
    }

    public MedianFilter(MedianFilterShape medianFilterShape, int medianFilterSize, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(output);
        this.medianFilterShape = medianFilterShape;
        this.medianFilterSize = medianFilterSize;
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        return MedianFilterDescriptor.create(entity, medianFilterShape, medianFilterSize, null);
    }
}
