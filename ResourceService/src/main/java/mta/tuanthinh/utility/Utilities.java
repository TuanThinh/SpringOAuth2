package mta.tuanthinh.utility;

import org.springframework.security.core.context.SecurityContextHolder;

public class Utilities {
	public static String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
