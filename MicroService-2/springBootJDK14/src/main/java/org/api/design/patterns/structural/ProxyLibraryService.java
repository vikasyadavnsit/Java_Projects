package org.api.design.patterns.structural;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.api.wrapper.patterns.Book;
import org.api.wrapper.patterns.ProxyBook;

public class ProxyLibraryService {

	public Map<String, String> findContentByAuthorAndTitle(String author, String title) {
		return filterByTitle(filterByAuthor(findAllBooks(), author), title)
				.collect(Collectors.toMap(Book::getTitle, Book::getContent));
	}

	private Stream<Book> findAllBooks() {
		return Stream.of(new ProxyBook("Design Patterns", "Gabor Laszlo Hajba"),
				new ProxyBook("Design Patterns", "GoF"), new ProxyBook("Python 3 in Anger", "Gabor Laszlo Hajba"),
				new ProxyBook("Design Patterns", "Head First"));
	}

	private Stream<Book> filterByAuthor(Stream<Book> books, String author) {
		return books.filter(b -> author.equalsIgnoreCase(b.getAuthor()));
	}

	private Stream<Book> filterByTitle(Stream<Book> books, String title) {
		return books.filter(b -> title.equalsIgnoreCase(b.getTitle()));
	}

}
