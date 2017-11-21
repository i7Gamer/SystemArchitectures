package bean.image;

import event.image.ImageObserver;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class SourceBeanInfo extends SimpleBeanInfo {

    public SourceBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class sourceClass = Source.class;
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
            return new MethodDescriptor[]{};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("link", Source.class);
            return new PropertyDescriptor[]{propertyDescriptor};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
