import com.coco.TestPerson;
import com.coco.application.Viewer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import javafx.application.Application;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TestMain {
    public static void main(String[] args) {
        Application.launch(Viewer.class);
//        Application.launch(RightClickButtonExample.class);
    }

    @Test
    public void testPossibility() {
        double pR = 1.0 / 30.0;
        // 以某个概率转换为死者
        //TODO 通过计算概率得出一个值来使得 感染者的死亡率为9/1000
        double finalP = 9.0 / 1000.0;
        double pD = finalP / (1.0 - pR);
        System.out.println(pD);
    }

    @Test
    public void testCsvWrite() throws IOException {


        String[] entries = {"book", "coin", "pencil", "cup"};
        String[][] a = {entries, entries, entries};
        String fileName = "items.csv";

        try (FileOutputStream fos = new FileOutputStream(fileName);
             OutputStreamWriter osw = new OutputStreamWriter(fos,
                     StandardCharsets.UTF_8);
             CSVWriter writer = new CSVWriter(osw)) {
            for (String[] line : a) {
                writer.writeNext(line);
            }
        }


    }

    @Test
    public void testCsv2() throws IOException, CsvValidationException {

        String fileName = "test_csv.csv";

        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] nextLine;

            while ((nextLine = reader.readNext()) != null) {

                for (String e : nextLine) {
                    System.out.format(e + ", ");
                }
                System.out.println();
            }
        }
    }
    @Test
    public void testJackson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = "{\"name\":\"1\",\"age\":0,\"pet\":{\"name\":\"12\"}}";
        TestPerson testPerson = objectMapper.readValue(jsonString, TestPerson.class);
        System.out.println(testPerson.getPet());
    }
}



