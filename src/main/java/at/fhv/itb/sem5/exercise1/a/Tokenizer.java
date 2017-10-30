package at.fhv.itb.sem5.exercise1.a;

import javafx.util.Pair;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;

public class Tokenizer extends DataTransformationFilter2<Pair<String, Long>, Line> {

    private String splitRegex = "\\s";

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
        for (String s : entity.getKey().trim().split(splitRegex)) {
            l.append(s);
        }
        return l;
    }

    public void setSplitRegex(String splitRegex) {
        this.splitRegex = splitRegex;
    }
}
