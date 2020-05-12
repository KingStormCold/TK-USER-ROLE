package net.kuleasycode.enumclass;

import lombok.Getter;

@Getter
public enum FailEnum {
	NOT_FOUND_USER("F01", "Not found user."),
	NOT_EMPTY("F02", "Input is not empty."),
	BAD_REQUEST_PHONE("F03", "Max length of phone is 10 or 11 and only number."),
	BAD_REQUEST_EMAIL("F04", "Email is invalid."),
	BAD_REQUEST_ENABLED("F05", "Enabled invalid."),
	BAD_REQUEST_PASSWORD("F06", "Password is not empty."),
	ERROR_PASSWORD("F07", "You can not update password for this user."),

	;

	private String key;
	
	private String value;

	private FailEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
