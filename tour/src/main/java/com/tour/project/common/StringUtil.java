package com.tour.project.common;

import java.util.List;

public class StringUtil {

	public static boolean isEmpty(Object obj) {
		if (obj == null) return true;
		return false;
	}
	
	public static boolean isEmpty(List<Object> list) {
		if (list == null || list.isEmpty() == true) {
			return true;
		}
		return false;
	}
}
