package at.fhv.itb.sem5.exercise3;

import at.fhv.itb.sem5.lib.pmp.filter.DataTransformationFilter2;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import java.security.InvalidParameterException;

public class OpeningFilter extends DataTransformationFilter2<PlanarImage, PlanarImage> {

    private final float[] kernelMatrix;
    private final int amountOfErodesAndDilates;

    public OpeningFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output, float[] kernelMatrix, int amountOfErodesAndDilates) throws InvalidParameterException {
        super(input, output);
        this.kernelMatrix = kernelMatrix;
        this.amountOfErodesAndDilates = amountOfErodesAndDilates;
    }

    public OpeningFilter(Readable<PlanarImage> input, float[] kernelMatrix, int amountOfErodesAndDilates) throws InvalidParameterException {
        super(input);
        this.kernelMatrix = kernelMatrix;
        this.amountOfErodesAndDilates = amountOfErodesAndDilates;
    }

    public OpeningFilter(Writeable<PlanarImage> output, float[] kernelMatrix, int amountOfErodesAndDilates) throws InvalidParameterException {
        super(output);
        this.kernelMatrix = kernelMatrix;
        this.amountOfErodesAndDilates = amountOfErodesAndDilates;
    }

    @Override
    protected PlanarImage process(PlanarImage entity) {
        KernelJAI kernel = new KernelJAI(5, 5, kernelMatrix);

        for (int i = 0; i < amountOfErodesAndDilates; i++) {
            entity = ErodeDescriptor.create(entity, kernel, null);
        }

        for (int i = 0; i < amountOfErodesAndDilates; i++) {
            entity = DilateDescriptor.create(entity, kernel, null);
        }
        return entity;
    }
}
