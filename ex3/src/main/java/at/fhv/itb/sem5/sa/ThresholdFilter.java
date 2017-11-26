package at.fhv.itb.sem5.sa;

import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ThresholdDescriptor;
import java.io.Serializable;

public class ThresholdFilter extends ImageSubject implements ImageObserver, Serializable {

    private double low = 0;
    private double high = 30;
    private double constant = 255;

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
        changed(new ImageEvent(this, cache));
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
        changed(new ImageEvent(this, cache));
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
        changed(new ImageEvent(this, cache));
    }

    @Override
    public void changed(ImageEvent event) {
        cache = event.getValue();

        double arrLow[] = {low};
        double arrHigh[] = {high};
        double arrConstant[] = {constant};

        RenderedOp renderableOp = ThresholdDescriptor.create(cache, arrLow, arrHigh, arrConstant, null);
        ImageEvent changedEvent = new ImageEvent(this, renderableOp.createInstance());
        fireChanged(changedEvent);
    }
}
