package utilites;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ParserJson {
    private File jsonData;

    public ParserJson(String nameJson) {
        jsonData = new File("src/main/resources/"+nameJson);
    }

    private  JSONParser jsonParser = new JSONParser();

    public String value(String parameter) {

        FileReader reader = null;
        try {
            reader = new FileReader(jsonData.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return (String) (jsonObject != null ? jsonObject.get(parameter) : null);
    }

}

