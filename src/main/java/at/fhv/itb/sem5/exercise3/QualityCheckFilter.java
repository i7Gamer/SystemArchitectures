package at.fhv.itb.sem5.exercise3;

import at.fhv.itb.sem5.lib.calccentroidsfilter.Coordinate;
import at.fhv.itb.sem5.lib.pmp.filter.Sink;
import at.fhv.itb.sem5.lib.pmp.interfaces.Readable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QualityCheckFilter extends Sink<ArrayList<Coordinate>> {

    private final String fileName;
    private final List<Coordinate> expected;
    private final int toleranceX;
    private final int toleranceY;

    public QualityCheckFilter(int x, int y, String fileName, List<Coordinate> expected, int toleranceX, int toleranceY) throws InvalidParameterException {
        super();
        this.fileName = fileName;
        this.expected = expected;
        this.toleranceX = toleranceX;
        this.toleranceY = toleranceY;
    }

    public QualityCheckFilter(Readable<ArrayList<Coordinate>> input, String fileName, List<Coordinate> expected, int toleranceX, int toleranceY) throws InvalidParameterException {
        super(input);
        this.fileName = fileName;
        this.expected = expected;
        this.toleranceX = toleranceX;
        this.toleranceY = toleranceY;
    }

    @Override
    public void write(ArrayList<Coordinate> foundCoordinates) throws StreamCorruptedException {
        if (foundCoordinates == null) {
            return;
        }


        File outputFile = new File(fileName);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (Coordinate foundCoordinate : foundCoordinates) {
                // check tolerance
                List<Coordinate> result = expected.stream().filter(coordinate -> Math.abs(foundCoordinate._x - coordinate._x) <= toleranceX
                        && Math.abs(foundCoordinate._y - coordinate._y) <= toleranceY).collect(Collectors.toList());

                if (result.size() == 0) {
                    bufferedWriter.write("Found: " + "x: " + foundCoordinate._x + ", y: " + foundCoordinate._y + "\tExpected: None expected found." + System.lineSeparator());
                } else if (result.size() > 1) {
                    bufferedWriter.write("Found: " + "x: " + foundCoordinate._x + ", y: " + foundCoordinate._y + "\tExpected: Mulitple expected found." + System.lineSeparator());
                } else {
                    bufferedWriter.write("Found: " + "x: " + foundCoordinate._x + ", y: " + foundCoordinate._y
                            + "\tExpected: x:" + result.get(0)._x + ", y: " + result.get(0)._y + System.lineSeparator());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
