package ua.com.alevel.carjson;

import java.io.File;
import java.util.Map;


public class App {
    public static void main(String[] args) {
        JsonBuilder jsonBuilder = new JsonBuilder();


        Car toyota = new Car("Toyota", "Camry", new String []  {"red", "white" },
                "km/h", 220, 2.5,
                6000, "11.8:1", 5);

        File jsonFile = new File("src//main//resources//car.json");
        Map<String, Object> map = jsonBuilder.MapWrite(toyota);
        jsonBuilder.createJsonFile(jsonFile, map);
    }


}
