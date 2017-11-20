package at.fhv.itb.sem5.systemarchitectures.exercise3;

import java.beans.*;
import java.lang.reflect.Method;

public class ImageViewerInfo extends SimpleBeanInfo {

    public ImageViewerInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class aClass = ImageViewer.class;
            String var3 = "image";
            Class imageListenerClass = ImageListener.class;
            String[] events = new String[]{"imageChanged"};
            String addImageListener = "addImageListener";
            String removeImageListener = "removeImageListener";
            EventSetDescriptor descriptor = new EventSetDescriptor(aClass, var3, imageListenerClass, events, addImageListener, removeImageListener);
            return new EventSetDescriptor[]{descriptor};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class aClass = ImageViewer.class;
            Class[] classes = new Class[]{ImageEvent.class};
            String imageChanged = "imageChanged";
            Method method = aClass.getMethod(imageChanged, classes);
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
