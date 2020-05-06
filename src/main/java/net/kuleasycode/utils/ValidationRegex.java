package net.kuleasycode.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {
	
	public static final Pattern PATTERN_PHONE = Pattern.compile("^[0-9]{10,11}+$");
	public static final Pattern PATTERN_EMAIL = Pattern.compile("^(.+)@(.+)$");
	
	public static boolean checkPhoneNumber(final String phone) {
		Matcher matcher = PATTERN_PHONE.matcher(phone);
		return matcher.matches();
	}
	
	public static boolean checkEmail(final String email) {
		Matcher matcher = PATTERN_EMAIL.matcher(email);
		return matcher.matches();
	}
}
