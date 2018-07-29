package JavaAdvanced.StreamApi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class ExportToExcel {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        String resourcePath = projectPath + "/src/JavaAdvanced/StreamApi/resources/";
        String inputPath = resourcePath + "StudentData.txt";
        String outputPath = resourcePath + "StudentData.xlsx";

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(inputPath));
             FileOutputStream fileOutputStream = new FileOutputStream(outputPath)) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Student data");
            int rowNum = 0;
            String rowData;

            while (null != (rowData = bufferedReader.readLine())) {
                Row row = sheet.createRow(rowNum++);
                String[] rowCellsData = rowData.split("\\t");
                int cellNum = 0;

                for (String cellData : rowCellsData) {
                    Cell cell = row.createCell(cellNum++);
                    cell.setCellValue(cellData);
                }
            }
            workbook.write(fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}