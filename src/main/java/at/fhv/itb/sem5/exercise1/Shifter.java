package at.fhv.itb.sem5.exercise1;

import pmp.filter.DataTransformationFilter3;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by timorzipa on 23.10.17.
 */
public class Shifter extends DataTransformationFilter3<LinkedList<String>,LinkedList<String>> {

    public Shifter(Readable<LinkedList<String>> input, Writeable<LinkedList<String>> output) throws InvalidParameterException {
        super(input, output);
    }

    @Override
    protected ArrayList<LinkedList<String>> process(LinkedList<String> entity) {

        ArrayList<LinkedList<String>> array = new ArrayList<>();

        for(int i = 0; i < entity.size(); i++){

            LinkedList<String> copy = new LinkedList<>(entity);
            array.add(copy);

            String s = entity.removeFirst();
            entity.addLast(s);
        }

        return array;
    }
}
