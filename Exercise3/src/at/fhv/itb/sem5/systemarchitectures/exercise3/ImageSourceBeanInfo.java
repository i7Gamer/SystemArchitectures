package at.fhv.itb.sem5.systemarchitectures.exercise3;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ImageSourceBeanInfo extends SimpleBeanInfo {

    public ImageSourceBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class imageSourceClass = ImageSource.class;
            String var3 = "image";
            Class imageListenerClass = ImageListener.class;
            String[] events = new String[]{"imageChanged"};
            String addImageListener = "addImageListener";
            String removeImageListener = "removeImageListener";
            EventSetDescriptor descriptor = new EventSetDescriptor(imageSourceClass, var3, imageListenerClass, events, addImageListener, removeImageListener);
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
