package utils;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

public class JSONUtils {

    private static Gson gson = new Gson();

    public static String convertMapToJSONString(Map <String, Object> map) {

        return gson.toJson(map);
    }

    public static JSONObject readRequestFile(String filePath) {
        JSONObject jsonObject = new JSONObject();
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePath));
             jsonObject = (JSONObject)obj;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonObject;
    }
    public static void saveResponseToFile(Response response, String fileName) {

        try (FileWriter writer = new FileWriter("src/test/resources/response_JSON" + fileName)) {
            writer.write(response.asPrettyString());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
