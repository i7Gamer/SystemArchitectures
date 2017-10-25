package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.DataTransformationFilter3;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Shifter extends DataTransformationFilter3<Pair<List<String>, Long>, Pair<List<String>, Long>> {

    public Shifter(Readable<Pair<List<String>, Long>> input, Writeable<Pair<List<String>, Long>> output) throws InvalidParameterException {
        super(input, output);
    }

    public Shifter(Readable<Pair<List<String>, Long>> input) throws InvalidParameterException {
        super(input);
    }

    public Shifter(Writeable<Pair<List<String>, Long>> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<Pair<List<String>, Long>> process(Pair<List<String>, Long> entity) {
        ArrayList<Pair<List<String>, Long>> array = new ArrayList<>();

        for (int i = 0; i < entity.getKey().size(); i++) {
            List<String> wordsCopy = new ArrayList<>(entity.getKey());
            array.add(new Pair<>(wordsCopy, entity.getValue()));

            //remove first and insert at end
            entity.getKey().add(entity.getKey().remove(0));
        }

        return array;
    }
}
