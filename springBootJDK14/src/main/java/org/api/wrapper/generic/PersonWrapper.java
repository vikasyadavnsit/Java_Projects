package org.api.wrapper.generic;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

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
@RedisHash("PersonWrapper")
public class PersonWrapper implements Serializable {
	private static final long serialVersionUID = 5033630767775062693L;
	@Id
	private String id;
	@Indexed
	private String firstname;
	private String lastname;
	private AddressWrapper address;
	private Date dob;
}
