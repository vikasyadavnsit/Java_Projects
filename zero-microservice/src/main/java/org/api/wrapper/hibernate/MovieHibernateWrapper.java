package org.api.wrapper.hibernate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MovieHibernateWrapper {
	private int movieId;
	private String title;
	private String overview;
}
