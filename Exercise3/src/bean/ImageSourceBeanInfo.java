package bean;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ImageSourceBeanInfo extends SimpleBeanInfo {

    public ImageSourceBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class sourceClass = ImageSource.class;
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
            return new MethodDescriptor[]{};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("link", ImageSource.class);
            return new PropertyDescriptor[]{propertyDescriptor};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
