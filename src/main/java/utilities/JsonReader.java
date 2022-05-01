package utilities;


import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * This class is used to read the data from json test data files
 *
 * author Jey
 */
public class JsonReader {

    private Object testData;
    private FileReader reader;
    private JSONObject jsonObject;
    private JSONParser jsonParser=new JSONParser();
    private Logger logger = LoggerFactory.getLogger(JsonReader.class);

    public JSONObject jsonReader(File filename, String eventName) {

        jsonParser = new JSONParser();
        try {
            reader = new FileReader(filename);
        } catch (FileNotFoundException e) {
            logger.error("Json Test data file not found:" + e);
        }
        try {
            //Read JSON file
            jsonObject = (JSONObject) jsonParser.parse(reader);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        testData = jsonObject.get(eventName);
        return (JSONObject) testData;
    }
}
