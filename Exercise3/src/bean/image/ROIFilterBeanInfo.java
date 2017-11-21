package bean.image;

import event.image.ImageEvent;
import event.image.ImageObserver;

import java.beans.*;
import java.lang.reflect.Method;

public class ROIFilterBeanInfo extends SimpleBeanInfo {

    public ROIFilterBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class sourceClass = ROIFilter.class;
            String eventSetName = "image";
            Class listenerType = ImageObserver.class;
            String listenerMethodNames[] = {"changed"};
            String addListenerMethodName = "addListener";
            String removeListenerMethodName = "removeListener";

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
            Method method = ROIFilter.class.getMethod("changed", ImageEvent.class);

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
