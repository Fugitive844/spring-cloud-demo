package win.ycsweb.test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @ClassName AnnotationParsing
 * @Description TODO
 * @Author yangchongshun
 * @Date 2019/6/10 16:08
 * @Version v1.0
 */
public class AnnotationParsing {

    public static void main(String[] args) {
        try {
            for (Method method : AnnotationParsing.class
                    .getClassLoader()
                    .loadClass(("win.ycsweb.test.AnnotationExample"))
                    .getMethods()) {
                // checks if MethodInfo annotation is present for the method
                if (method.isAnnotationPresent(win.ycsweb.test.MethodInfo.class)) {
                    try {
                        // iterates all the annotations available in the method
                        for (Annotation anno : method.getDeclaredAnnotations()) {
                            System.out.println("注解-------------'"+ method + "====== " + anno);
                        }
                        MethodInfo methodAnno = method.getAnnotation(MethodInfo.class);
                        if (methodAnno.revision() == 1) {
                            System.out.println("注解============= "+ method);
                        }

                    } catch (Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
