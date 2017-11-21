package bean.image;

import event.image.ImageEvent;
import event.image.ImageObserver;
import event.image.ImageSubject;

import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.ThresholdDescriptor;
import java.io.Serializable;

public class ThresholdFilter extends ImageSubject implements ImageObserver, Serializable {

    private double low = 0;
    private double high = 30;
    private double constant = 255;

    private PlanarImage input = null;

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
        changed(new ImageEvent(this, input));
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
        changed(new ImageEvent(this, input));
    }

    public double getConstant() {
        return constant;
    }

    public void setConstant(double constant) {
        this.constant = constant;
        changed(new ImageEvent(this, input));
    }

    @Override
    public void changed(ImageEvent event) {
        input = event.getValue();

        double arrLow[] = {low};
        double arrHigh[] = {high};
        double arrConstant[] = {constant};

        RenderedOp renderableOp = ThresholdDescriptor.create(input, arrLow, arrHigh, arrConstant, null);
        ImageEvent changedEvent = new ImageEvent(this, renderableOp.createInstance());
        fireChanged(changedEvent);
    }
}
