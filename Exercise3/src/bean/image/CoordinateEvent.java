package bean.image;

import at.fhv.itb.sem5.lib.calccentroidsfilter.Coordinate;

import java.util.EventObject;
import java.util.List;

public class CoordinateEvent extends EventObject{
    private List<Coordinate> value;

    public CoordinateEvent(Object source, List<Coordinate> value) {
        super(source);
        this.value = value;
    }

    public List<Coordinate> getValue() {
        return this.value;
    }

    public void setValue(List<Coordinate> coordinateList) {
        this.value = coordinateList;
    }
}
