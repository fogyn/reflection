import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class Main {


    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        TestClass testClass = new TestClass("test", 10, "annotation111");
        // задание 1
        System.out.println("Название полей");
        printFieldName(TestClass.class);
        System.out.println("---------------------");
        // задание 2
        System.out.println("Название методов");
        printMethodName(TestClass.class);
        System.out.println("---------------------");
        // задание 3
        System.out.println();
        System.out.println("Аннотации");
        getAnnotationValue(TestClass.class, testClass);
        System.out.println("---------------------");

        //4
        TestA testA = new TestA("родительС", 11, "тест - было", 10);
        TestB testB = new TestB("тест - стало", 20, "родитель В(С)", 22);
        System.out.println(testA.getNameTest());
        System.out.println(testA.getAgeTest());
        System.out.println(testA.getNameC());
        System.out.println(testA.getAgeC());

        setField(TestB.class, TestA.class, testB, testA);
        System.out.println("Установка новых значений в testA, взятых из testB");
        System.out.println(testA.getNameTest());
        System.out.println(testA.getAgeTest());
        System.out.println(testA.getNameC());
        System.out.println(testA.getAgeC());
        System.out.println("---------------------");

        //5
        System.out.println();
        System.out.println("--------");
        System.out.println("Поиск метода с учетом наследования");
        //printAllMethod(TestA.class);
        Method method = getMethod(TestA.class, "getNameC", 1);
        if (method != null) {
            System.out.println("Метод - " + method.getName() + " есть.");
            System.out.println("----------------");
        } else {
            System.out.println("Метод Не нашли");
            System.out.println("--------------");
        }

        // задание 6
        TestParamNew a = new TestParamNew();
        System.out.println("БЫл массив - ");
        for (String s : a.getParams()) {
            System.out.print(s + ", ");
        }
        System.out.println();
        System.out.println("---");
        setFieldByAnnotationParam(TestParam.class, a);

        System.out.println("Стал массив");
        for (String s : a.getParams()) {
            System.out.print(s + ", ");
        }

    }
    //

    public static void printAllMethod(Class<?> aClass) {

        for (Method method : aClass.getSuperclass().getDeclaredMethods()) {
            System.out.println(method.getName());
        }

    }

    public static void setFieldByAnnotationParam(Class<?> aClass, Object a) throws InvocationTargetException, IllegalAccessException {
        for (Field field : aClass.getDeclaredFields()) {
            Annotation[] annotations = field.getAnnotations();
            //ищем нужную аннотацию
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getName().equals("AnnotationParam")) {
                    // получаем данные поля аннотации
                    String[] mas = ((AnnotationParam) annotation).key();
                    // ищем поле с соответствующим типом данных
                    for (Field field2 : a.getClass().getDeclaredFields()) {

                        String type = field2.getType().toString();
                        if (type.contains("[")) {
                            for (Method method2 : a.getClass().getDeclaredMethods()) {
                                // ищем метод устанавливающий значение
                                if (method2.getName().toLowerCase().contains("set") && method2.getName().toLowerCase().contains(field2.getName())) {
                                    method2.invoke(a, (Object) mas);
                                    break;
                                }
                            }
                        }
                    }
                }

            }

        }
    }


    public static void getAnnotationValue(Class<?> className, TestClass test) throws IllegalAccessException {

        for (Field field : className.getDeclaredFields()) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType().getName().equals("TestAnnotation")) {
                    field.setAccessible(true);
                    Class<?> returnType = field.getType();
                    returnType.cast(field.get(test));

                    String s = annotation.annotationType().getName();
                    System.out.println("передаем значение поля помеченного аннотацией - " + s);

                    String value = (String) field.get(test);
                    System.out.println(value);

                }

            }

        }

    }

    public static void printAnnotations(Annotation[] annotations) {
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }
    }


    public static void printFieldName(Class<?> className) {
        for (Field field : className.getDeclaredFields()) {
            System.out.println(field.getName());
            //System.out.println(field.getType());
        }
    }

    public static void printMethodName(Class<?> className) {
        for (Method method : className.getDeclaredMethods()) {
            System.out.println(method.getName());
        }
    }

    public static Method getMethod(Class<?> aClass, String methodName, int depth) {

        for (Method method : aClass.getDeclaredMethods()) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }
        if(depth==0){
            return null;
        }
        depth--;
        return getMethod(aClass.getSuperclass(), methodName, depth);
    }

    public static void setField(Class<?> bClass, Class<?> aClass, Object b, Object a) throws InvocationTargetException, IllegalAccessException {
        for (Method method : bClass.getDeclaredMethods()) {
            if (method.getName().contains("get")) {
                //System.out.println(method.getName());
                Class<?> returnType = method.getReturnType();
                Object returnValue = method.invoke(b, (Object[]) null);

                if (returnType.getName().contains("String")) {
                    String s1 = (String) returnValue;
                    Method methodA = getMethod(aClass, method.getName().replaceAll("get", "set"), 1);
                    if (methodA != null) {
                        methodA.invoke(a, s1);
                    }

                    //System.out.println(methodA.getName());
                }
                if (returnType.getName().contains("int")) {
                    int i1 = (int) returnValue;
                    Method methodA = getMethod(aClass, method.getName().replaceAll("get", "set"), 1);
                    if (methodA != null) {
                        methodA.invoke(a, i1);
                    }

                }
            }

        }
    }
}
