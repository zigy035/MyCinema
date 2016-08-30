package com.mycinema.web.util;

import java.util.UUID;

public class UIDGenerator {
	
	public static void main(String[] args) {
		// e19c6cff-d4d0-4196-b889-d5e756eafe68
//		System.out.println(UUID.randomUUID().toString());
		
		String uid = UUID.randomUUID().toString();
		StringBuilder builder = new StringBuilder();
		for (int i=0; i<uid.length(); i++) {
			char ch = uid.charAt(i);
			if (ch != '-') {
				if (Character.isLetter(ch)) {
					builder.append(Character.getNumericValue(ch));
				} else {
					builder.append(ch);
				}
			}
		}
		
		System.out.println("uid: " + uid);
		System.out.println("new uid: " + builder.toString());
	}
}
