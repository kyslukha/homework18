package ua.com.alevel.carjson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class JsonBuilder {


    public static final String ENTER = "\n";
    public static final String COMMA = ",";
    public static final String Tabulation = "\t";
    public static final String BracesRight = "{";
    public static final String BracesLeft = "}";
    public static final String POINTS = ":";
    public static final String QUOTES = "\"";
    public static final String squareBraceRight = "[";
    public static final String squareBraceLeft = "]";

    void createJsonFile(File file, Map<String, Object> map) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            startWrite(fileWriter);
            writeMain(map, fileWriter);
            endWrite(fileWriter);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void startWrite(FileWriter writer) throws IOException {
        writer.write(Tabulation + BracesRight + ENTER);
    }

    private void endWrite(FileWriter writer) throws IOException {
        writer.write( Tabulation + BracesLeft);
    }

    public static Map<String, Object> MapWrite(Car car) {
        Map<String, Object> carCharacteristics = new TreeMap<>();
        Map<String, Object> speedCharacteristics = new TreeMap<>();
        Map<String, Object> engineCharacteristics = new TreeMap<>();


        carCharacteristics.put("brand", car.getBrand());
        carCharacteristics.put("model", car.getModel());
        carCharacteristics.put("colors", car.getColors());
        carCharacteristics.put("maxSpeed", speedCharacteristics);
        carCharacteristics.put("engine", engineCharacteristics);
        carCharacteristics.put("seatingCapacity", car.getSeatingCapacity());

        speedCharacteristics.put("unit", car.getSpeedUnit());
        speedCharacteristics.put("value", car.getSpeedValue());

        engineCharacteristics.put("volume", car.getVolume());
        engineCharacteristics.put("rpm", car.getRpm());
        engineCharacteristics.put("compressionRatio", car.getCompressionRatio());

        return carCharacteristics;
    }


    private void writeMain(Map<String, Object> map, FileWriter writer) throws IOException {
        int counter = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            StringBuilder valueWrite = new StringBuilder();
            StringBuilder keyWrite = formation(entry.getKey());
            if (entry.getValue() instanceof Map) {
                writer.write(Tabulation + keyWrite + POINTS + BracesRight + ENTER);
                writeMain((Map<String, Object>) entry.getValue(), writer);
                writer.append(Tabulation + BracesLeft);
            } else {
                StringBuilder value = formation(entry.getValue());
                valueWrite.append(Tabulation).append(keyWrite).append(POINTS).append(value);
                writer.write(valueWrite.toString());
            }
            counter++;
            if (counter < map.size()) {
                writer.append(COMMA + ENTER);
            } else {
                writer.append(ENTER);
            }
        }
    }


    private StringBuilder formation(Object object) {
        Integer counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        if (object.getClass().isArray()) {
            String[] array = ((String[]) object);
            stringBuilder.append(squareBraceRight + ENTER);
            for (String arrSub : array) {
                counter++;
                stringBuilder.append(  Tabulation+ QUOTES + arrSub + QUOTES );

                if (counter <= array.length -1) {
                    stringBuilder.append(COMMA + ENTER);

                }


            }
            stringBuilder.append(ENTER + Tabulation + squareBraceLeft);
        } else if (object instanceof Integer || object instanceof Double) {
            stringBuilder.append(object);
        } else {
            stringBuilder.append(QUOTES).append(object).append(QUOTES);
        }
        return stringBuilder;
    }



}