package net.kuleasycode.enumclass;

import lombok.Getter;

@Getter
public enum FailEnum {
	NOT_FOUND_USER("F01", "Not found user."),
	NOT_EMPTY("F02", "Input is not empty.");

	private String key;
	
	private String value;

	private FailEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
