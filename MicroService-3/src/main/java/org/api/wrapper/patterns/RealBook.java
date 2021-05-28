package org.api.wrapper.patterns;

import java.text.MessageFormat;

public class RealBook implements Book {

	private final String title;
	private final String author;
	private String content;

	public RealBook(final String title, final String author) {
		this.title = title;
		this.author = author;
		loadContentFromDatabase(title, author);
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
		return content;
	}

	private void loadContentFromDatabase(String title, String author) {
		System.out.println("Loading content from database...");
		try {
			Thread.sleep(1500);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.content = MessageFormat.format("Interesting and very large content of {0} by {1}", title, author);
	}

}
