package org.api.wrapper.generic;

public class ResourceNotFoundWrapper {

	private String code;
	private String message;
	private String description;
	private String time;

	public ResourceNotFoundWrapper() {

	}

	public ResourceNotFoundWrapper(String code, String message, String description, String time) {
		super();
		this.code = code;
		this.message = message;
		this.description = description;
		this.time = time;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
