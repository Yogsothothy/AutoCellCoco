package com.coco.utils;

import com.coco.celldata.Cell;
import com.coco.celldata.CellField;
import com.coco.celldata.CovidCell;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * ClassName:CSVUtil
 * Package:com.coco.utils
 * Description:
 *
 * @Author Coco
 * @Create 2024/4/13 17:06
 * @Version 1.0
 */
public class CSVUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 从外部csv文件中读取数据
     * TODO 把csv的数据直接加载到tempField里
     */
    public static void ReadField() {
        String fileName = "a.csv";
        CellField field = CellField.getInstance();

        try (FileInputStream fis = new FileInputStream(fileName);
             InputStreamReader isr = new InputStreamReader(fis,
                     StandardCharsets.UTF_8);
             CSVReader reader = new CSVReader(isr)) {
            String[] nextLine;
            int x = 0;
            while ((nextLine = reader.readNext()) != null) {
                Cell[][] tempField = field.getTempField();
                int y = 0;
                for (String e : nextLine) {
                    if (nextLine.length == 1){
                        CovidCell.plazas =objectMapper.readValue(nextLine[0],CovidCell.plazas.getClass());
                        break;
                    }
                    tempField[x][y] = objectMapper.readValue(e, CovidCell.class);
                    y++;
                }
                x++;
            }
        } catch (CsvValidationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建一个csv，将field里的数据保存进去
     * 以行号和列号标记元胞位置，用json保存元胞状态
     * 要输入文件名
     */
    public static void WriteField(String fileName, CellField cellField) {
        Cell[][] field = cellField.getTempField();
        try (FileOutputStream fos = new FileOutputStream(fileName + ".csv");
             OutputStreamWriter osw = new OutputStreamWriter(fos,
                     StandardCharsets.UTF_8);
             CSVWriter writer = new CSVWriter(osw)) {
            for (Cell[] cells : field) {
                String[] cellsJson = new String[cellField.getHeight()];
                for (int i = 0; i < cells.length; i++) {
                    cellsJson[i] = objectMapper.writeValueAsString(cells[i]);
                }
                writer.writeNext(cellsJson);
            }
            writer.writeNext(new String[]{objectMapper.writeValueAsString(CovidCell.plazas)});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
