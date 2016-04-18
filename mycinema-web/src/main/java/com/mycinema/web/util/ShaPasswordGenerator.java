package com.mycinema.web.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class ShaPasswordGenerator {
	
	public static void main(String[] args) {
		ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder();
		String encodedPassword = passwordEncoder.encodePassword("igor", null);
		
		System.out.println(encodedPassword);
	}
}
