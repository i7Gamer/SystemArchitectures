package at.fhv.itb.sem5.sa;

import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import java.io.Serializable;

public class MedianFilter extends ImageSubject implements ImageObserver, Serializable {

    private int size = 7;
    private eMedianFilterShape shape = eMedianFilterShape.SQUARE;
    private transient MedianFilterShape medianFilterShape = MedianFilterDescriptor.MEDIAN_MASK_SQUARE;

    public eMedianFilterShape getShape() {
        return shape;
    }

    public void setShape(eMedianFilterShape shape) {
        this.shape = shape;
        updateShape();
    }

    private void updateShape() {
        switch (this.shape) {
            case SQUARE:
                setMedianFilterShape(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
                break;
            case PLUS:
                setMedianFilterShape(MedianFilterDescriptor.MEDIAN_MASK_PLUS);
                break;
            case X:
                setMedianFilterShape(MedianFilterDescriptor.MEDIAN_MASK_X);
                break;
            case SQUARE_SEPARABLE:
                setMedianFilterShape(MedianFilterDescriptor.MEDIAN_MASK_SQUARE_SEPARABLE);
                break;
        }
    }

    private void setMedianFilterShape(MedianFilterShape medianFilterShape) {
        this.medianFilterShape = medianFilterShape;
        changed(new ImageEvent(this, cache));
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        changed(new ImageEvent(this, cache));
    }

    @Override
    public void changed(ImageEvent event) {
        cache = event.getValue();

        cache = MedianFilterDescriptor.create(cache, medianFilterShape, size, null);

        ImageEvent newEvent = new ImageEvent(this, cache);
        fireChanged(newEvent);
    }
}
