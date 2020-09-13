package org.api.annotations.test;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE, ElementType.CONSTRUCTOR })
@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation {
	String name() default "test";

	int count() default 0;

	String[] tags() default { "tag1", "tag2" };
}
