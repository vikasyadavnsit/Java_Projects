package java8;

public class IslandOfIsolation {

	IslandOfIsolation i;

	public static void main(String[] args) {

		IslandOfIsolation t1 = new IslandOfIsolation();
		IslandOfIsolation t2 = new IslandOfIsolation();

		t1.i = t2;
		t2.i = t1;

		t1 = t2 = null;

		System.gc();
	}

	@Override
	protected void finalize() {
		System.out.println("Garbage Collector Called");
	}

}
