package at.fhv.itb.sem5.exercise1.a;

import at.fhv.itb.sem5.lib.pmp.filter.DataTransformationFilter3;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Shifter extends DataTransformationFilter3<Line, Line> {

    public Shifter(Readable<Line> input, Writeable<Line> output) throws InvalidParameterException {
        super(input, output);
    }

    public Shifter(Readable<Line> input) throws InvalidParameterException {
        super(input);
    }

    public Shifter(Writeable<Line> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<Line> process(Line entity) {
        ArrayList<Line> array = new ArrayList<>();

        for (int i = 0; i < entity.words.size(); i++) {
            array.add(entity);
            entity = entity.shift();
        }

        return array;
    }
}
