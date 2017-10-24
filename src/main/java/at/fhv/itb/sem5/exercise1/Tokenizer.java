package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by timorzipa on 23.10.17.
 */
public class Tokenizer extends DataTransformationFilter2<Pair<String,Long>, Pair<LinkedList<String>,Long>> {

    public Tokenizer(Readable<Pair<String, Long>> input, Writeable<Pair<LinkedList<String>, Long>> output) throws InvalidParameterException {
        super(input, output);
    }


    @Override
    protected Pair<LinkedList<String>, Long> process(Pair<String, Long> entity) {
        return new Pair<>(new LinkedList(Arrays.asList(entity.getKey().trim().split("\\s"))), entity.getValue());
    }


}
