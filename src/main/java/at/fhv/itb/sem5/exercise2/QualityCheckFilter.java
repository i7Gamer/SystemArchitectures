package at.fhv.itb.sem5.exercise2;

import calccentroidsfilter.Coordinate;
import pmp.filter.Sink;
import pmp.interfaces.Readable;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;

public class QualityCheckFilter extends Sink<ArrayList<Coordinate>> {

    private final int x;
    private final int y;
    private final String fileName;

    public QualityCheckFilter(int x, int y, String fileName) throws InvalidParameterException {
        super();
        this.x = x;
        this.y = y;
        this.fileName = fileName;
    }

    public QualityCheckFilter(Readable<ArrayList<Coordinate>> input, int x, int y, String fileName) throws InvalidParameterException {
        super(input);
        this.x = x;
        this.y = y;
        this.fileName = fileName;
    }

    @Override
    public void write(ArrayList<Coordinate> coordinates) throws StreamCorruptedException {
        if(coordinates == null) {
            return;
        }

        File outputFile = new File(fileName);

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile)))
        {
            for(Coordinate coordinate : coordinates) {
                bufferedWriter.write("x: " + coordinate._x + ", y: " + coordinate._y + System.lineSeparator());
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
