package at.fhv.itb.sem5.exercise1;

import javafx.util.Pair;
import pmp.filter.DataTransformationFilter3;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by timorzipa on 23.10.17.
 */
public class Shifter extends DataTransformationFilter3<Pair<LinkedList<String>,Long>, Pair<LinkedList<String>,Long>> {

    public Shifter(Readable<Pair<LinkedList<String>, Long>> input, Writeable<Pair<LinkedList<String>, Long>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    protected ArrayList<Pair<LinkedList<String>, Long>> process(Pair<LinkedList<String>, Long> entity) {
        ArrayList<Pair<LinkedList<String>,Long>> array = new ArrayList<>();

        for(int i = 0; i < entity.getKey().size(); i++){

            Pair<LinkedList<String>,Long> copy = new Pair<>(entity.getKey(),entity.getValue());
            array.add(copy);

            String s = entity.getKey().removeFirst();
            entity.getKey().addLast(s);
        }

        return array;
    }
}
