package bean.image;

import event.image.ImageEvent;
import event.image.ImageObserver;
import event.image.ImageSubject;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import java.io.Serializable;

public class ClosingFilter extends ImageSubject implements ImageObserver, Serializable {

    private final float kernelMatrix[] = {
            1, 1, 1,
            1, 1, 1,
            1, 1, 1
    };
    private int amountOfClosings = 1;

    public int getAmountOfClosings() {
        return amountOfClosings;
    }

    public void setAmountOfClosings(int amountOfClosings) {
        this.amountOfClosings = amountOfClosings;
    }

    @Override
    public void changed(ImageEvent event) {
        PlanarImage image = event.getValue();

        KernelJAI kernel = new KernelJAI(3, 3, kernelMatrix);

        for (int i = 0; i < amountOfClosings; i++) {
            image = DilateDescriptor.create(image, kernel, null);
        }

        for (int i = 0; i < amountOfClosings; i++) {
            image = ErodeDescriptor.create(image, kernel, null);
        }

        ImageEvent changed = new ImageEvent(this, image);
        fireChanged(changed);
    }
}
