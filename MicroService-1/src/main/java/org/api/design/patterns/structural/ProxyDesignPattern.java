package org.api.design.patterns.structural;

public class ProxyDesignPattern {
	public static void main(String[] args) {

		// Protection Proxy Design Pattern
		//Browser browser = new Browser();
		//browser.sendRequest();
		//[values -> google.com, xyz.com or anything]
		

		// Virtual Proxy Design Pattern
		new ProxyLibraryService().findContentByAuthorAndTitle("GoF", "Design Patterns").entrySet()
				.forEach(b -> System.out.println(b.getKey() + " --> " + b.getValue()));
	}
}
