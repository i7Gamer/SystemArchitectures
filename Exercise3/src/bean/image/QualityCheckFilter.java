package bean.image;

import at.fhv.itb.sem5.lib.calccentroidsfilter.Coordinate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QualityCheckFilter extends CoordinateSubject implements CoordinateObserver, Serializable {

    private String savePath = "";
    private int toleranceY = 5;
    private int toleranceX = 5;


    public int getToleranceY() {
        return toleranceY;
    }

    public void setToleranceY(int toleranceY) {
        this.toleranceY = toleranceY;
        changed(new CoordinateEvent(this, cache));
    }

    public int getToleranceX() {
        return toleranceX;
    }

    public void setToleranceX(int toleranceX) {
        this.toleranceX = toleranceX;
        changed(new CoordinateEvent(this, cache));
    }


    private List<Coordinate>expected = Arrays.asList(new Coordinate(73, 77),
                new Coordinate(110, 80),
                new Coordinate(202, 80),
                new Coordinate(265, 79),
                new Coordinate(330, 81),
                new Coordinate(396, 81));

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
        changed(new CoordinateEvent(this, cache));
    }

    @Override
    public void changed(CoordinateEvent event) {
        cache = event.getValue();

        File outputFile = new File(savePath);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (Coordinate foundCoordinate : event.getValue()) {
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

        CoordinateEvent coordinateEvent = new CoordinateEvent(this, cache);
        fireChanged(coordinateEvent);
    }
}
