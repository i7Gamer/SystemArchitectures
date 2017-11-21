package bean.image;

import event.image.ImageEvent;
import event.image.ImageObserver;
import event.image.ImageSubject;

import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import java.io.Serializable;

public class OpeningFilter extends ImageSubject implements ImageObserver, Serializable {

    private final float kernelMatrix[] = {
            1, 1, 1,
            1, 1, 1,
            1, 1, 1
    };
    private int amountOfOpenings = 1;

    public int getAmountOfOpenings() {
        return amountOfOpenings;
    }

    public void setAmountOfOpenings(int amountOfOpenings) {
        this.amountOfOpenings = amountOfOpenings;
    }

    @Override
    public void changed(ImageEvent event) {
        PlanarImage image = event.getValue();

        KernelJAI kernel = new KernelJAI(3, 3, kernelMatrix);

        for (int i = 0; i < amountOfOpenings; i++) {
            image = ErodeDescriptor.create(image, kernel, null);
        }

        for (int i = 0; i < amountOfOpenings; i++) {
            image = DilateDescriptor.create(image, kernel, null);
        }

        ImageEvent changed = new ImageEvent(this, image);
        fireChanged(changed);
    }
}
