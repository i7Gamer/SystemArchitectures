package at.fhv.itb.sem5.exercise3;

import java.beans.*;
import java.lang.reflect.Method;

public class ImageSourceInfo extends SimpleBeanInfo {

    public ImageSourceInfo() {
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
            Class imageSourceClass = ImageSource.class;
            Class[] classes = new Class[]{ImageEvent.class};
            String imageChanged = "imageChanged";
            Method method = imageSourceClass.getMethod(imageChanged, classes);
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
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("link", ImageSource.class);
            return new PropertyDescriptor[]{propertyDescriptor};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
