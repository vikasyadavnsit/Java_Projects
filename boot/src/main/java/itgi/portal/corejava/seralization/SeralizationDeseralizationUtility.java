package itgi.portal.corejava.seralization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SeralizationDeseralizationUtility {

	public static void main(String[] args) {

		try (FileOutputStream fos = new FileOutputStream("ser.bat");

				ObjectOutputStream oos = new ObjectOutputStream(fos);) {
			SeralizationWrapper wrapper = new SeralizationWrapper(30, "I", "am", "Cool", 99);
			System.out.println("Before seralization : - " + wrapper);
			oos.writeObject(wrapper);
		} catch (Exception e) {
			System.out.println(e);
		}

		try (FileInputStream fis = new FileInputStream("ser.bat");
				ObjectInputStream ois = new ObjectInputStream(fis);) {
			SeralizationWrapper wrapper = (SeralizationWrapper) ois.readObject();
			System.out.println("After deseralizaion : - " + wrapper);
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
