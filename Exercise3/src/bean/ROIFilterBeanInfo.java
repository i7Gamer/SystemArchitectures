package bean;

import java.beans.*;
import java.lang.reflect.Method;

public class ROIFilterBeanInfo extends SimpleBeanInfo {

    public ROIFilterBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class sourceClass = ROIFilter.class;
            String eventSetName = "image";
            Class listenerType = ImageListener.class;
            String[] listenerMethodNames = new String[]{"imageChanged"};
            String addListenerMethodName = "addImageListener";
            String removeListenerMethodName = "removeImageListener";

            EventSetDescriptor descriptor = new EventSetDescriptor(
                    sourceClass,
                    eventSetName,
                    listenerType,
                    listenerMethodNames,
                    addListenerMethodName,
                    removeListenerMethodName
            );

            return new EventSetDescriptor[]{descriptor};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Method method = ROIFilter.class.getMethod("imageChanged", ImageEvent.class);

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
            return new PropertyDescriptor[]{
                    new PropertyDescriptor("offsetX", ROIFilter.class),
                    new PropertyDescriptor("offsetY", ROIFilter.class),
                    new PropertyDescriptor("width", ROIFilter.class),
                    new PropertyDescriptor("height", ROIFilter.class)
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
