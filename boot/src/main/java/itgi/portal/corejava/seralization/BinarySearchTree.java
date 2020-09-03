package itgi.portal.corejava.seralization;

import java.io.Serializable;

public class BinarySearchTree implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer data;
	BinarySearchTree left;
	BinarySearchTree right;

	BinarySearchTree(int data) {
		this.data = data;
		left = null;
		right = null;
	}

	void add(int data) {
		BinarySearchTree temp = new BinarySearchTree(data);
		BinarySearchTree node = this;

		while (node != null) {
			if (temp.data < node.data) {
				if (node.left == null) {
					node.left = temp;
					break;
				} else
					node = node.left;
			} else {
				if (node.right == null) {
					node.right = temp;
					break;
				} else
					node = node.right;
			}
		}
	}

	@Override
	public String toString() {
		return "BinarySearchTree [data=" + data + ", left=" + left + ", right=" + right + "]";
	}

}
