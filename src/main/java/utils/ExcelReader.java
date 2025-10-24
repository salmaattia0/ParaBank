package utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReader {

    private static final String BILL_PAY_FILE = "src/main/resources/BillData.xlsx";
    private static final String SHEET_NAME = "Sheet1";

    private static Object[][] readExcelData() {
        Object[][] data = new Object[0][0];

        try (FileInputStream fileInputStream = new FileInputStream(new File(BILL_PAY_FILE));
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);
            if (sheet == null) return data;

            int rowCount = sheet.getPhysicalNumberOfRows();
            DataFormatter formatter = new DataFormatter();

            data = new Object[rowCount - 1][1]; // عمود واحد فقط (Amount)

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                data[i - 1][0] = formatter.formatCellValue(row.getCell(0));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    @DataProvider(name = "billPayData")
    public static Object[][] billPayDataProvider() {
        return readExcelData();
    }
}
