package utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVReader {

    @DataProvider(name = "openNewAccountData")
    public static Iterator<Object[]> getDataFromCSV() throws Exception {
        String filePath = "src/main/resources/OpenNewAccountData.csv";
        List<Object[]> data = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
                continue;
            }
            if (line.trim().isEmpty()) continue;

            String[] values = line.split(",");
            if (values.length < 3) continue;

            data.add(new Object[]{values[0], values[1], values[2]});
        }
        br.close();

        return data.iterator();
    }
}
