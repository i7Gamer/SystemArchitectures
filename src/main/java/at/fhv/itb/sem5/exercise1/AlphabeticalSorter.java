package at.fhv.itb.sem5.exercise1;

import javafx.collections.transformation.SortedList;
import javafx.util.Pair;
import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.*;

/**
 * Created by timorzipa on 24.10.17.
 */
public class AlphabeticalSorter extends DataCompositionFilter<Pair<List<List<String>>,Long>,Collection<Pair<List<String>,Long>>> {

    public AlphabeticalSorter(Readable<Pair<List<List<String>>, Long>> input, Writeable<Collection<Pair<List<String>, Long>>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    protected boolean fillEntity(Pair<List<List<String>>, Long> nextVal, Collection<Pair<List<String>, Long>> entity) {

        Collection<Pair<List<String>,Long>> temp = new PriorityQueue<>();

        nextVal.getKey().forEach(l -> temp.add(new Pair<>(l, nextVal.getValue())));

        return entity.addAll(temp);
    }

    @Override
    protected Collection<Pair<List<String>, Long>> getNewEntityObject() {
        return new PriorityQueue<>();
    }
}
