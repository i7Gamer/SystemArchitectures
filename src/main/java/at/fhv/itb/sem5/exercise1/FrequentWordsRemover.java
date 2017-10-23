package at.fhv.itb.sem5.exercise1;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.security.InvalidParameterException;
import java.util.Collection;
import java.util.List;

/**
 * Created by timorzipa on 23.10.17.
 */
public class FrequentWordsRemover extends DataTransformationFilter1<List<List<String>>>{
    private Collection<String> frequentWords;

    public FrequentWordsRemover(Readable<List<List<String>>> input, Writeable<List<List<String>>> output, Collection<String> frequentWords) throws InvalidParameterException, FileNotFoundException {
        super(input, output);
        this.frequentWords = frequentWords;
    }

    @Override
    protected void process(List<List<String>> entity) {
        for(List<String> list : entity){
            if(frequentWords.contains(list.get(0))){
                entity.remove(list);
            }
        }
    }
}
