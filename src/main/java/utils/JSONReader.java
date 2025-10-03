package utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

public class JSONReader {

    public static class TransferData {
        private String fromAccount;
        private String toAccount;
        private String amount;

        public String getFromAccount() { return fromAccount; }
        public String getToAccount() { return toAccount; }
        public String getAmount() { return amount; }
    }

    @DataProvider(name = "transferData")
    public Object[][] getTransferFundsData() throws Exception {
        Gson gson = new Gson();
        Reader reader = new FileReader("src/main/resources/TransferData.json");

        Type listType = new TypeToken<List<TransferData>>() {}.getType();
        List<TransferData> dataList = gson.fromJson(reader, listType);

        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }
        return data;
    }
}

