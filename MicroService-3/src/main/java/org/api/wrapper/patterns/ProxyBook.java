package org.api.wrapper.patterns;

public class ProxyBook implements Book {

	private String title;
	private String author;
	private RealBook realBook;

	public ProxyBook(String title, String author) {
		this.title = title;
		this.author = author;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String getContent() {
		if (realBook == null) {
			realBook = new RealBook(title, author);
		}
		return realBook.getContent();
	}

}
