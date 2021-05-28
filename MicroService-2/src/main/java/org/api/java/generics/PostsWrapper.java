package org.api.java.generics;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostsWrapper implements Serializable {

	private static final long serialVersionUID = -6436781559321793834L;

	private int userId;
	private int id;
	private String title;
	private String body;
}
