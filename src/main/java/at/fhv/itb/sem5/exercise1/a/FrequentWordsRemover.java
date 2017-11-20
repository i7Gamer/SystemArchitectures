package at.fhv.itb.sem5.exercise1.a;

import at.fhv.itb.sem5.lib.pmp.filter.ForwardingFilter;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.Set;

public class FrequentWordsRemover extends ForwardingFilter<Line> {
    private Set<String> frequentWords;

    public FrequentWordsRemover(Readable<Line> input, Writeable<Line> output, Set<String> frequentWords) throws InvalidParameterException {
        super(input, output);
        this.frequentWords = frequentWords;
    }

    public FrequentWordsRemover(Readable<Line> input, Set<String> frequentWords) throws InvalidParameterException {
        super(input);
        this.frequentWords = frequentWords;
    }

    public FrequentWordsRemover(Writeable<Line> output, Set<String> frequentWords) throws InvalidParameterException {
        super(output);
        this.frequentWords = frequentWords;
    }

    @Override
    protected boolean forward(Line entity) {
        return !frequentWords.contains(entity.words.get(0).toLowerCase());
    }
}
