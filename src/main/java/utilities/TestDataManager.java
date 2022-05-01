package utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;


/**
 * This class is used to read the test data file details from Test data manager file
 *
 * author Jey
 */
public class TestDataManager {

    private static Properties testDataFile = new Properties();
    private InputStream inputStream;
    private static String testDataPath="src\\test\\resources\\testdata\\dataset\\";
    private String dataManagerPath= "src\\test\\resources\\testdata\\manager\\datamanager.properties";
    private Logger logger = LoggerFactory.getLogger(TestDataManager.class);

    public TestDataManager(){
        try{
            this.inputStream = new FileInputStream(dataManagerPath);
            this.testDataFile.load(inputStream);
        }catch (FileNotFoundException fe){
            logger.error("data manager file not found" + fe);
        }catch (IOException e){
            logger.error("Error Reading property"+ e);
        }
    }

    public File getLoginData(){
        return new File(new String(testDataPath+testDataFile.getProperty("LOGIN")));
    }

    public File getRegisterData(){
        return new File(new String(testDataPath+testDataFile.getProperty("REGISTER")));
    }

    public File getMakeData(){
        return new File(new String(testDataPath+testDataFile.getProperty("MAKE")));
    }

    public File getCommentData(){
        return new File(new String(testDataPath+testDataFile.getProperty("COMMENT")));
    }
}
