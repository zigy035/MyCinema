package com.mycinema.web.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiMultiSheetTest {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		int numberOfBooks = 31;
		int booksPerSheet = 10;
		int moduo = numberOfBooks % booksPerSheet;
		int sheetCount = numberOfBooks / booksPerSheet + ((moduo > 0) ? 1 : 0);
		
		List<Book> books = new ArrayList<Book>();
		for (int i=0; i<numberOfBooks ; i++) {
			books.add(new Book("isbn" + (i+1), "title" + (i+1), "author" + (i+1)));
		}

		XSSFWorkbook workbook = new XSSFWorkbook();
		for (int i=0; i<sheetCount; i++) {
			XSSFSheet sheet = workbook.createSheet("Page " + (i+1));
			int rowCounter = 0;
			int start = 10*i;
			int end = (moduo > 0 && i+1 == sheetCount) ? start + moduo : 10*(i+1);
			for (int j=start; j<end; j++) {
				Row row = sheet.createRow(++rowCounter);
				Book book = books.get(j);

				int cellCount = 0;
				for (Field field : Book.class.getDeclaredFields()) {
					Cell cell = row.createCell(++cellCount);
					Object value;
					try {
						value = field.get(book);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						workbook.close();
						return;
					}
					if (value instanceof String) {
						cell.setCellValue((String) value);
					} else if (value instanceof Integer) {
						cell.setCellValue((Integer) value);
					}
				}
			}
		}

		try (FileOutputStream outputStream = new FileOutputStream("Books.xlsx")) {
			workbook.write(outputStream);
		}
		workbook.close();

		System.out.println("Completed!");
	}
}

class Book {

	String isbn;
	String title;
	String author;

	public Book(String isbn, String title, String author) {
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}
}
