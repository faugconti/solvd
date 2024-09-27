package delivery.util.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Reflection {

    public static void printClassInfo(Class<?> classInput) {
        System.out.println();
        System.out.println("[Class name: " + classInput.getName()+"]");
        System.out.println("[Modifiers: " + Modifier.toString(classInput.getModifiers())+"]");
        System.out.println();

        printFields(classInput);
        printConstructors(classInput);
        printMethods(classInput);
    }

    private static void printFields(Class<?> className) {
        System.out.println("Fields:");
        Field[] fields = className.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("[Modifier=" + Modifier.toString(field.getModifiers()) + "]" +"[Type="+
                    field.getType().getSimpleName() + "]" + "[Name="+field.getName()+"]");
        }
        System.out.println();
    }

    private static void printConstructors(Class<?> className) {
        System.out.println("Constructors:");
        Constructor<?>[] constructors = className.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println("[Modifier=" + Modifier.toString(constructor.getModifiers()) +"]"+ "\n[Name=" +
                    className.getSimpleName()+"]" + "\n[Parameters="+Arrays.toString(constructor.getParameterTypes())+"]");
        }
        System.out.println();
    }

    private static void printMethods(Class<?> className) {
        System.out.println("Methods:");
        Method[] methods = className.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("[Modifier=" + Modifier.toString(method.getModifiers()) + "]" +"[ReturnType="+
                    method.getReturnType().getSimpleName() + "]" +"[Name="+ method.getName() +"]"+"[Parameters="+
                    Arrays.toString(method.getParameterTypes())+"]");
        }
        System.out.println();
    }


    public static Object createNewObject(String className, Object... args) throws Exception {
        Class<?> classInput = Class.forName(className);
        Class<?>[] parameterTypes = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            parameterTypes[i] = args[i].getClass();
        }
        Constructor<?> constructor = classInput.getConstructor(parameterTypes);
        return constructor.newInstance(args);
    }


    //TODO: Method for calling another method

}
