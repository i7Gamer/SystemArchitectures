package at.fhv.itb.sem5.exercise1.b;

import at.fhv.itb.sem5.exercise1.a.Line;
import at.fhv.itb.sem5.lib.pmp.filter.DataCompositionFilter;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;
import at.fhv.itb.sem5.lib.pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class LineBuilder extends DataCompositionFilter<ArrayList<Character>, Line> {

    Long lineCounter = 0L;
    int maxLength = 0;
    String temp = null;

    public LineBuilder(Readable<ArrayList<Character>> input, Writeable<Line> output, int maxLength) throws InvalidParameterException {
        super(input, output);
        this.maxLength = maxLength;
    }

    public LineBuilder(Readable<ArrayList<Character>> input, int maxLength) throws InvalidParameterException {
        super(input);
        this.maxLength = maxLength;
    }

    public LineBuilder(Writeable<Line> output, int maxLength) throws InvalidParameterException {
        super(output);
        this.maxLength = maxLength;
    }

    @Override
    protected boolean fillEntity(ArrayList<Character> nextVal, Line entity) {
        if (nextVal == null) {
            return true;
        }

        StringBuilder wordBuilder = new StringBuilder();

        for (Character c : nextVal) {
            wordBuilder.append(c);
        }

        String word = wordBuilder.toString();

        if (entity.getLength() + word.length() > maxLength) {
            temp = word;
            return true;
        } else {
            entity.append(word);
            return false;
        }
    }

    @Override
    protected Line getNewEntityObject() {
        Line alignedLine = new Line();
        if (temp != null) {
            alignedLine.append(temp);
            temp = null;
        }
        lineCounter++;
        alignedLine.setIndex(lineCounter);
        return alignedLine;
    }
}
