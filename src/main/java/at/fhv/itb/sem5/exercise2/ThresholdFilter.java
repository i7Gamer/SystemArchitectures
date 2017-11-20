package at.fhv.itb.sem5.exercise2;

import at.fhv.itb.sem5.lib.pmp.filter.DataTransformationFilter2;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ThresholdDescriptor;
import java.security.InvalidParameterException;

public class ThresholdFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    private final double[] low;
    private final double[] high;
    private final double[] constant;

    public ThresholdFilter(double[] low, double[] high, double[] constant, Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
        super(input, output);
        this.low = low;
        this.high = high;
        this.constant = constant;
    }

    public ThresholdFilter(double[] low, double[] high, double[] constant, Readable<PlanarImage> input) throws InvalidParameterException {
        super(input);
        this.low = low;
        this.high = high;
        this.constant = constant;
    }

    public ThresholdFilter(double[] low, double[] high, double[] constant, Writeable<PlanarImage> output) throws InvalidParameterException {
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
