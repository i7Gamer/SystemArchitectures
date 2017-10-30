package at.fhv.itb.sem5.exercise1.a;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class AlphabeticalSorter extends DataCompositionFilter<Line, List<Line>> {

    public AlphabeticalSorter(Readable<Line> input, Writeable<List<Line>> output) throws InvalidParameterException {
        super(input, output);
    }

    public AlphabeticalSorter(Readable<Line> input) throws InvalidParameterException {
        super(input);
    }

    public AlphabeticalSorter(Writeable<List<Line>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Line nextVal, List<Line> entity) {
        if (nextVal == null) {
            entity.sort(Line::compareTo);
            return true;
        }
        entity.add(nextVal);
        return false;
    }

    @Override
    protected List<Line> getNewEntityObject() {
        return new ArrayList<>();
    }
}
