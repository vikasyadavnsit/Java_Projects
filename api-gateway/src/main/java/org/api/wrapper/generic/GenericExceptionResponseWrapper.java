package org.api.wrapper.generic;

public class GenericExceptionResponseWrapper {

	private long timestamp;
	private int status;
	private String errorMessage;
	private String requestedURI;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRequestedURI() {
		return requestedURI;
	}

	public void setRequestedURI(final String requestedURI) {
		this.requestedURI = requestedURI;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long l) {
		this.timestamp = l;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
