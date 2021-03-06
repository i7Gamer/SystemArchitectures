package at.fhv.itb.sem5.exercise1.a;

import at.fhv.itb.sem5.lib.pmp.filter.DataTransformationFilter2;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;
import javafx.util.Pair;

import java.security.InvalidParameterException;

/**
 * Created by timorzipa on 23.10.17.
 */
public class Tokenizer extends DataTransformationFilter2<Pair<String, Long>, Line> {

    public Tokenizer(Readable<Pair<String, Long>> input, Writeable<Line> output) throws InvalidParameterException {
        super(input, output);
    }

    public Tokenizer(Readable<Pair<String, Long>> input) throws InvalidParameterException {
        super(input);
    }

    public Tokenizer(Writeable<Line> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected Line process(Pair<String, Long> entity) {
        Line l = new Line();
        l.index = entity.getValue();
        for (String s : entity.getKey().trim().split("\\s")) {
            l.append(s);
        }
        return l;
    }
}
