package com.mycinema.web.util;

import java.util.UUID;

public class CinemaAppUtils {
	
	public static void main(String[] args) {
		generateUIDs(9);
		displayMatrix(10, 10);
		splitPositionList("");
	}

	private static void splitPositionList(String str) {
		String [] strArray = str.split("-");
		System.out.println("Size: " + strArray.length);
	}
	
	private static void displayMatrix(int rows, int columns) {
		for (int i = 0; i < rows; i++)
		{
			char row = (char) ('A' + i);
			System.out.print(String.valueOf(row) + ' ');
			for (int j = 0; j < columns; j++) {
				System.out.print((String.valueOf(row) + (j+1) + ' '));
			}
			System.out.println();
		}
	}
	
	private static void generateUIDs(int count) {
		for (int i=0; i<count; i++) {
			System.out.println(UUID.randomUUID().toString());
		}
	}
	
}
