package org.api.java.seralization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SeralizeBinaryTree {

	public static void main(String[] args) {
		BinarySearchTree bst = new BinarySearchTree(10);
		bst.add(20);
		bst.add(2);
		bst.add(15);
		bst.add(9);
		bst.add(4);
		System.out.println(bst.toString());

		try (FileOutputStream fos = new FileOutputStream("test.bat");
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(bst);
			System.out.println("Object has been seralized.");
		} catch (Exception e) {
			System.out.println(e);
		}

		BinarySearchTree bstSeralized = null;

		try (FileInputStream fis = new FileInputStream("test.bat");
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			bstSeralized = (BinarySearchTree) ois.readObject();
			System.out.println("Object has bee deseralized.");
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println(bstSeralized.toString());
	}

}
