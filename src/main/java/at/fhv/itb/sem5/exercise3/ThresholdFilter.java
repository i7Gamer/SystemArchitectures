package at.fhv.itb.sem5.exercise3;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ThresholdDescriptor;
import java.security.InvalidParameterException;

public class ThresholdFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    double[] low;
    double[] high;
    double[] constant;

    public ThresholdFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, double[] low, double[] high, double[] constant) throws InvalidParameterException {
        super(input, output);
        this.low = low;
        this.high = high;
        this.constant = constant;
    }

    public ThresholdFilter(Readable<PlanarImage> input, double[] low, double[] high, double[] constant) throws InvalidParameterException {
        super(input);
        this.low = low;
        this.high = high;
        this.constant = constant;
    }

    public ThresholdFilter(Writeable<PlanarImage> output, double[] low, double[] high, double[] constant) throws InvalidParameterException {
        super(output);
        this.low = low;
        this.high = high;
        this.constant = constant;
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        RenderedOp renderableOp = ThresholdDescriptor.create(entity, low, high, constant, null);
        return renderableOp.createInstance();
    }
}
