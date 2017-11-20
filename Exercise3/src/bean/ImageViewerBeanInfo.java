package bean;

import java.beans.*;
import java.lang.reflect.Method;

public class ImageViewerBeanInfo extends SimpleBeanInfo {

    public ImageViewerBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            return new EventSetDescriptor[]{};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Method method = ImageViewer.class.getMethod("imageChanged", ImageEvent.class);

            ParameterDescriptor[] parameterDescriptors = new ParameterDescriptor[]{new ParameterDescriptor()};
            MethodDescriptor methodDescriptor = new MethodDescriptor(method, parameterDescriptors);

            return new MethodDescriptor[]{methodDescriptor};

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            return new PropertyDescriptor[]{};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
