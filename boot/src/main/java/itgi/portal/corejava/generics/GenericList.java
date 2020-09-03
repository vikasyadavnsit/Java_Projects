package itgi.portal.corejava.generics;

public interface GenericList<U> {
	
	U get(Integer i);

	Integer add(U obj);

	Boolean delete(int i) throws IllegalAccessException;

	Boolean delete(U obj);
}
