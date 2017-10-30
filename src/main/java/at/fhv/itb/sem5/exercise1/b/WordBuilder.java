package at.fhv.itb.sem5.exercise1.b;

import com.google.common.collect.EvictingQueue;
import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class WordBuilder extends DataCompositionFilter<Character, ArrayList<Character>> {

    EvictingQueue evictingQueue = EvictingQueue.create(100);

    public WordBuilder(Readable<Character> input, Writeable<ArrayList<Character>> output) throws InvalidParameterException {
        super(input, output);
    }

    public WordBuilder(Readable<Character> input) throws InvalidParameterException {
        super(input);
    }

    public WordBuilder(Writeable<ArrayList<Character>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Character nextVal, ArrayList<Character> entity) {
        if (Character.isDefined(nextVal) && nextVal != ' ' && nextVal != '\n' && nextVal != '\r') {
            entity.add(nextVal);
            return false;
        } else {
            evictingQueue.add(entity);
            return true;
        }
    }

    @Override
    protected ArrayList<Character> getNewEntityObject() {
        return new ArrayList<>();
    }
}
