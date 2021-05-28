package org.api.java.basics;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class WaysToCreateObjectInstance implements Cloneable, Serializable {

	protected int a;

	WaysToCreateObjectInstance(int a) {
	}

	WaysToCreateObjectInstance() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public static void main(String[] args) {

		// ******************************** Using Reflection to create instance but here
		// instance can only be created if
		// the object class have a public default constructor

		try {
			Class c = Class.forName("java8.VariableArguments");
			VariableArguments obj = (VariableArguments) c.newInstance();
			obj.sum(23, 3, 2, 4, 2, 1);
		} catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		// ********************************* Using Clone to create instance but here
		// constructor on new clone object is
		// not called

		WaysToCreateObjectInstance t = new WaysToCreateObjectInstance(10);
		t.a = 10;

		try {
			WaysToCreateObjectInstance t2 = (WaysToCreateObjectInstance) t.clone();
			System.out.println(t2.a);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		// ************************************ Using Deseralization and Serialization
		// to create new Instance but here
		// constructor is not called of the object class

		try {

			// Serialization
			WaysToCreateObjectInstance t3 = new WaysToCreateObjectInstance();
			t3.a = 500;
			FileOutputStream f = new FileOutputStream("test.txt");
			ObjectOutputStream oos = new ObjectOutputStream(f);
			oos.writeObject(t3);
			oos.close();
			f.close();

			// Deseralization
			WaysToCreateObjectInstance t4;
			FileInputStream fis = new FileInputStream("test.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			t4 = (WaysToCreateObjectInstance) ois.readObject();
			ois.close();
			fis.close();
			System.out.println(t4.a);

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		// ************************** Using New Instace method of a constructor class

		try {
			Constructor<WaysToCreateObjectInstance> Tobj = WaysToCreateObjectInstance.class.getDeclaredConstructor();
			WaysToCreateObjectInstance t5 = (WaysToCreateObjectInstance) Tobj.newInstance();
			System.out.println(t5.a);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException | InvocationTargetException | IllegalArgumentException
				| IllegalAccessException e) {
			e.printStackTrace();
		}

	}
}
