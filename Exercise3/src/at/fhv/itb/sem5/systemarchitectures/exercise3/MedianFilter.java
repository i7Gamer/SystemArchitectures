package at.fhv.itb.sem5.systemarchitectures.exercise3;

import at.fhv.itb.sem5.lib.pmp.filter.DataTransformationFilter2;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import java.security.InvalidParameterException;

public class MedianFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    private final MedianFilterShape medianFilterShape;
    private final int medianFilterSize;


    public MedianFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, MedianFilterShape medianFilterShape, int medianFilterSize) throws InvalidParameterException {
        super(input, output);
        this.medianFilterShape = medianFilterShape;
        this.medianFilterSize = medianFilterSize;
    }

    public MedianFilter(Readable<PlanarImage> input, MedianFilterShape medianFilterShape, int medianFilterSize) throws InvalidParameterException {
        super(input);
        this.medianFilterShape = medianFilterShape;
        this.medianFilterSize = medianFilterSize;
    }

    public MedianFilter(Writeable<PlanarImage> output, MedianFilterShape medianFilterShape, int medianFilterSize) throws InvalidParameterException {
        super(output);
        this.medianFilterShape = medianFilterShape;
        this.medianFilterSize = medianFilterSize;
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        return MedianFilterDescriptor.create(entity, medianFilterShape, medianFilterSize, null);
    }
}
