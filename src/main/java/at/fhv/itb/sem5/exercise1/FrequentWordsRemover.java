package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by timorzipa on 23.10.17.
 */
public class FrequentWordsRemover extends DataTransformationFilter1<Pair<List<List<String>>,Long>>{
    private Collection<String> frequentWords;

    public FrequentWordsRemover(Readable<Pair<List<List<String>>, Long>> input, Writeable<Pair<List<List<String>>, Long>> output) throws InvalidParameterException {
        super(input, output);
        this.frequentWords = frequentWords;
    }

    @Override
    protected void process(Pair<List<List<String>>,Long> entity) {
        entity.getKey().removeIf(c -> frequentWords.contains(c.get(0)));
    }
}