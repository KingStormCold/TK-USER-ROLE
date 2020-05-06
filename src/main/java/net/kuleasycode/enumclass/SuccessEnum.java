package net.kuleasycode.enumclass;

import lombok.Getter;

@Getter
public enum SuccessEnum {
	ADD_USER("SC00", "Add user success."),
	UPDATE_USER("SC01", "Update user success."),
	ADD_ROLE("SC02", "Add role success."),
	UPDATE_ROLE("SC03", "Update role success."),
	;

	private String key;
	
	private String value;

	private SuccessEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}
}
