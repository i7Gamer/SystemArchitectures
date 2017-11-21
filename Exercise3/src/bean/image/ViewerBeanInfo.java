package bean.image;

import event.image.ImageEvent;

import java.beans.*;
import java.lang.reflect.Method;

public class ViewerBeanInfo extends SimpleBeanInfo {

    public ViewerBeanInfo() {
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
            Method method = Viewer.class.getMethod("changed", ImageEvent.class);

            ParameterDescriptor parameterDescriptors[] = {new ParameterDescriptor()};
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
