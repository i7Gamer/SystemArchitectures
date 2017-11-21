package bean.image;

import javax.media.jai.KernelJAI;
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
        changed(new ImageEvent(this, cache));
    }

    @Override
    public void changed(ImageEvent event) {
        cache = event.getValue();

        KernelJAI kernel = new KernelJAI(3, 3, kernelMatrix);

        for (int i = 0; i < amountOfClosings; i++) {
            cache = DilateDescriptor.create(cache, kernel, null);
        }

        for (int i = 0; i < amountOfClosings; i++) {
            cache = ErodeDescriptor.create(cache, kernel, null);
        }

        ImageEvent changed = new ImageEvent(this, cache);
        fireChanged(changed);
    }
}
