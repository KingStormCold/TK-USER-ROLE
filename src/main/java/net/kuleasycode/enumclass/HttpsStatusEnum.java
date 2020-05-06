package net.kuleasycode.enumclass;

import lombok.Getter;

@Getter
public enum HttpsStatusEnum {
	_200("200", "OK."),
	_400("400", "Bad Request."),
	_500("500", "Internal Server Error."),
	_404("404", "Not found");

	private String key;
	
	private String value;

	private HttpsStatusEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
}
