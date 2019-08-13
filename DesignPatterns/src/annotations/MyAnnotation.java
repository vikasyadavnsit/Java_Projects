package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

//@Target(ElementType.TYPE)
//@Inherited
//@Documented

@Retention(RetentionPolicy.CLASS)
public @interface MyAnnotation {
   String name() default  "jack";
   int count() default  999;
   String [] tags() default {"test", "abc", "tes"};
   
}
