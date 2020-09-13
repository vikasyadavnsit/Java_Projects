package org.api.java.seralization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ExternalizationUtility {

	public static void main(String[] args) {

		/*
		 * Externalizable extends Serializable. So if we implement
		 * Externalizable interface and override its writeExternal() and
		 * readExternal() methods, then our first preference is given to these
		 * methods over the default serialization mechanism provided by the JVM.
		 * These methods supersede customized implementations of writeObject and
		 * readObject methods. So if we also provide writeObject() and
		 * readObject(), then they will be ignored
		 */

		// readExternal with throw OptinalDataException when, it will not able
		// to parse the object or field

		try (FileOutputStream fos = new FileOutputStream("extr.bat");
				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			SeralizationWrapper wrapper = new SeralizationWrapper(30, "I", "am", "Cool", 99);
			System.out.println("Before seralization : - " + wrapper);
			oos.writeObject(wrapper);
		} catch (Exception e) {
			System.out.println(e);
		}

		try (FileInputStream fis = new FileInputStream("extr.bat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			SeralizationWrapper wrapper = (SeralizationWrapper) ois.readObject();
			System.out.println("After deseralizaion : - " + wrapper);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
