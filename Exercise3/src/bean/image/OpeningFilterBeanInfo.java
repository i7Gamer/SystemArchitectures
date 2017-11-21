package bean.image;

import java.beans.*;
import java.lang.reflect.Method;

public class OpeningFilterBeanInfo extends SimpleBeanInfo {

    public OpeningFilterBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class sourceClass = OpeningFilter.class;
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
            Method method = OpeningFilter.class.getMethod("changed", ImageEvent.class);

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
                    new PropertyDescriptor("amountOfOpenings", OpeningFilter.class)
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
