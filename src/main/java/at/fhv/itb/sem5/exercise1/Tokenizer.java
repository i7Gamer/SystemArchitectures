package at.fhv.itb.sem5.exercise1;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Created by timorzipa on 23.10.17.
 */
public class Tokenizer extends DataTransformationFilter2<String, LinkedList<String>> {

    public Tokenizer(Readable<String> input, Writeable<LinkedList<String>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    protected LinkedList<String> process(String entity) {
        return new LinkedList(Arrays.asList(entity.trim().split("\\s")));
    }
}
