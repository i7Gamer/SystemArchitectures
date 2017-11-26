package at.fhv.itb.sem5.sa;

import at.fhv.itb.sem5.sa.ex2.calccentroidsfilter.Coordinate;

import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.*;

public class CalcCentroidsFilter extends CoordinateSubject implements ImageObserver, Serializable {

    private HashMap<Coordinate, Boolean> _general = new HashMap<>();
    private LinkedList<ArrayList<Coordinate>> _figures = new LinkedList<>();
    private PlanarImage imageCache = null;

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
        changed(new ImageEvent(this, imageCache));
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
        changed(new ImageEvent(this, imageCache));
    }

    private int offsetX = 0;
    private int offsetY = 0;

    @Override
    public void changed(ImageEvent event) {
        imageCache = event.getValue();

        BufferedImage bi = event.getValue().getAsBufferedImage();
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int p = bi.getRaster().getSample(x, y, 0);
                if (p == 255 && !_general.containsKey(new Coordinate(x, y))) {
                    getNextFigure(bi, x, y);        //if there is a not visited white pixel, save all pixels belonging to the same figure
                }
            }
        }

        List<Coordinate> result = calculateCentroids();    //calculate the centroids of all figures

        CoordinateEvent newEvent = new CoordinateEvent(this, result);
        fireChanged(newEvent);
    }

    private ArrayList<Coordinate> calculateCentroids() {
        ArrayList<Coordinate> centroids = new ArrayList<>();
        int i = 0;
        for (ArrayList<Coordinate> figure : _figures) {
            LinkedList<Integer> xValues = new LinkedList<>();
            LinkedList<Integer> yValues = new LinkedList<>();

            for (Coordinate c : figure) {
                xValues.add(c._x);
                yValues.add(c._y);
            }

            Collections.sort(xValues);
            Collections.sort(yValues);

            int xMedian = xValues.get(xValues.size() / 2);
            int yMedian = yValues.get(yValues.size() / 2);

            centroids.add(new Coordinate(xMedian + offsetX, yMedian + offsetY));

            i++;
        }
        return centroids;
    }

    private void getNextFigure(BufferedImage img, int x, int y) {
        ArrayList<Coordinate> figure = new ArrayList<Coordinate>();
        _general.put(new Coordinate(x, y), true);
        figure.add(new Coordinate(x, y));

        addConnectedComponents(img, figure, x, y);

        _figures.add(figure);
    }

    private void addConnectedComponents(BufferedImage img, ArrayList<Coordinate> figure, int x, int y) {
        if (x - 1 >= 0 && !_general.containsKey((new Coordinate(x - 1, y))) && img.getRaster().getSample(x - 1, y, 0) == 255) {
            _general.put(new Coordinate(x - 1, y), true);
            figure.add(new Coordinate(x - 1, y));
            addConnectedComponents(img, figure, x - 1, y);
        }
        if (x + 1 < img.getWidth() && !_general.containsKey((new Coordinate(x + 1, y))) && img.getRaster().getSample(x + 1, y, 0) == 255) {
            _general.put(new Coordinate(x + 1, y), true);
            figure.add(new Coordinate(x + 1, y));
            addConnectedComponents(img, figure, x + 1, y);
        }
        if (y - 1 >= 0 && !_general.containsKey((new Coordinate(x, y - 1))) && img.getRaster().getSample(x, y - 1, 0) == 255) {
            _general.put(new Coordinate(x, y - 1), true);
            figure.add(new Coordinate(x, y - 1));
            addConnectedComponents(img, figure, x, y - 1);
        }
        if (y + 1 < img.getHeight() && !_general.containsKey((new Coordinate(x, y + 1))) && img.getRaster().getSample(x, y + 1, 0) == 255) {
            _general.put(new Coordinate(x, y + 1), true);
            figure.add(new Coordinate(x, y + 1));
            addConnectedComponents(img, figure, x, y + 1);
        }
    }
}
