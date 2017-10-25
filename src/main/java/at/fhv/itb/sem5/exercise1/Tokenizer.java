package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by timorzipa on 23.10.17.
 */
public class Tokenizer extends DataTransformationFilter2<Pair<String, Long>, Pair<List<String>, Long>> {

    private String splitRegex = "\\s";

    public Tokenizer(Readable<Pair<String, Long>> input, Writeable<Pair<List<String>, Long>> output) throws InvalidParameterException {
        super(input, output);
    }

    public Tokenizer(Readable<Pair<String, Long>> input) throws InvalidParameterException {
        super(input);
    }

    public Tokenizer(Writeable<Pair<List<String>, Long>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected Pair<List<String>, Long> process(Pair<String, Long> entity) {
        return new Pair<>(new LinkedList<>(Arrays.asList(entity.getKey().trim().split(splitRegex))), entity.getValue());
    }

    public void setSplitRegex(String splitRegex) {
        this.splitRegex = splitRegex;
    }
}
