package java8.concurrency;

import java.util.ArrayList;
import java.util.Arrays;

public class IteratorVsForEach {

	public static void main(String[] args) {

		ArrayList<Integer> al = new ArrayList<>(Arrays.asList(9, 2, 2, 3, 4, 5, 6, 7));

//		for (int a : al) {
//			System.out.println(a + " ");
//			al.remove(1);
//		}
//		

//		Iterator<Integer> itr = al.iterator();
//		boolean b = false;
//
//		while (itr.hasNext()) {
//			System.out.println(itr.next());
//			itr.remove();
//			if (!b) {
//				al.add(33);
//				b = !b;
//			}
//		}

//		boolean b = false;
//		for (int i = 0; i < al.size(); i++) {
//			System.out.println(al.get(i));
//			if (!b) {
//				al.add(33);
//				b = !b;
//			}
//		}

		ArrayList<Integer> al2 = (ArrayList<Integer>) al.clone();
		al.add(323);

		for (int a : al2) {
			System.out.println(a + " ");
		}

	}

}
