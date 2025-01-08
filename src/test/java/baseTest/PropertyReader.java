package baseTest;

import java.io.*;
import java.util.Properties;

public class PropertyReader {

    public static Properties prop;
    public static PropertyReader instance;

    private PropertyReader() throws IOException {
        prop= getPropertyFile();
    }
    public static PropertyReader getInstance() throws IOException {
        if(instance == null){
            instance = new PropertyReader();
        }
        return instance;
    }

    private Properties getPropertyFile() throws IOException {
        String configPath = "./src/test/resources/Configuration/config.properties";
        prop = new Properties();

        try {
            InputStream ip = new FileInputStream(configPath);
            prop.load(ip);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public String readProperty(String key){
        return prop.getProperty(key);

    }
}
