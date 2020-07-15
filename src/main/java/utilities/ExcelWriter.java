package utilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;

public class ExcelWriter {

    public static void writeJSONToExcel(ArrayList<String> headers, HashMap<Integer, ArrayList> values) {
        // Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Details");

        // This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        Object[] objArr1 = headers.toArray();
        data.put("1", objArr1);
        for (int k = 0; k < values.size(); k++) {
            Object[] objArr2 = values.get(k).toArray();
            data.put("" + 2 + k, objArr2);
        }

        // Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "test.xlsx"));   //creating a new file instance
            workbook.write(out);
            out.close();
            System.out.println("Json is written successfully on Excel having file name as :- test.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeScoresToExcel(ArrayList<String> headers, HashMap<Integer, ArrayList> values) {
        // Blank workbook
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Create a blank sheet
        XSSFSheet sheet = workbook.createSheet("Details");

        // This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        Object[] objArr1 = headers.toArray();
        data.put("1", objArr1);
        for (int k = 0; k < values.size(); k++) {
            Object[] objArr2 = values.get(k).toArray();
            data.put("" + 2 + k, objArr2);
        }

        // Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            // this creates a new row in the sheet
            Row row = sheet.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                // this line creates a cell in the next column of that row
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "test.xlsx"));   //creating a new file instance
            workbook.write(out);
            out.close();
            System.out.println("Json is written successfully on Excel having file name as :- test.xlsx");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
