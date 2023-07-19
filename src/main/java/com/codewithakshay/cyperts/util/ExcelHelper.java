package com.codewithakshay.cyperts.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.codewithakshay.cyperts.entity.Employees;

public class ExcelHelper {

	public static String supportedFileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static String[] headerContent = { "id", "name", "city", "dept", "age" };
	public static String sheetName = "Employees";

	public static boolean isItExcelFile(MultipartFile file) {
		if (!supportedFileType.equals(file.getContentType()))
			return false;
		return true;
	}

	public static List<Employees> excelToEmployeeConverter(InputStream stream) {
		try {
			Workbook book = new XSSFWorkbook(stream);
			Sheet sheet = book.getSheet(sheetName);
			Iterator<Row> rows = sheet.iterator();

			List<Employees> empList = new ArrayList<>();
			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Employees employees = new Employees();

				int cellIdx = 0;
				while (cellsInRow.hasNext()) {
					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {
					case 0:
						employees.setId((long) currentCell.getNumericCellValue());
						break;

					case 1:
						employees.setName(currentCell.getStringCellValue());
						break;

					case 2:
						employees.setDept(currentCell.getStringCellValue());
						break;

					case 3:
						employees.setCity(currentCell.getStringCellValue());
						break;
					case 4:
						employees.setAge((int) currentCell.getNumericCellValue());
						break;

					default:
						break;
					}

					cellIdx++;
				}

				empList.add(employees);
			}

			book.close();

			return empList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}

}
