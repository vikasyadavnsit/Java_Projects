package java8;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectionTest {

	ReflectionTest() {
	}

	ReflectionTest(String a) {
		this.a = a;
	}

	private static String a = "test";
	protected final long b = 23424243;
	public Double d = new Double(234234.23424);

	private void show() {
		System.out.println("Private Show");
	}

	protected int show(int data) {
		System.out.println("Private Show Data : " + data);
		return data;
	}
}

public class ReflectionAPI {

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchFieldException, SecurityException {

		ReflectionTest rt = new ReflectionTest();

		Class obj = rt.getClass();
		Constructor[] c = obj.getDeclaredConstructors();
		System.out.println("Constructors Length " + c.length);

		for (Constructor temp : c) {
			System.out.println(temp.getModifiers() + " - " + temp.getName() + " - " + temp.getParameterCount());
		}

		Method[] m = obj.getDeclaredMethods();
		for (Method temp : m) {
			System.out.println(temp.getModifiers() + "-" + temp.getName() + "-" + temp.getParameterCount());
			temp.setAccessible(true);
			if (temp.getParameterCount() == 0)
				temp.invoke(rt);
			else {
				int rs = (int) temp.invoke(rt, 100);
				System.out.println("Resut is " + rs);
			}
		}

		Field f = obj.getDeclaredField("a");
		f.setAccessible(true);
		System.out.println("Field a : " + f.getType());
		System.out.println("Field a Value : " + f.get(rt));

	}

}
