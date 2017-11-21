package bean.image;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

public class CalcCentroidsFilterBeanInfo {


    public CalcCentroidsFilterBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class sourceClass = CalcCentroidsFilter.class;
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
            Method method = CalcCentroidsFilter.class.getMethod("changed", ImageEvent.class);

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
                    new PropertyDescriptor("toleranceY", CalcCentroidsFilter.class),
                    new PropertyDescriptor("toleranceX", CalcCentroidsFilter.class)

            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
