package se.blacklemon.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesConfig {

    public String getProperty(String propertyName, String fileName) throws IOException {
        String propertyFileName = fileName;
        Properties properties = new Properties();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream != null){
            properties.load(inputStream);
        }
        else {
            throw new FileNotFoundException("Properties file " +  fileName + " not found in the classpath");
        }

         return properties.getProperty(propertyName);
    }
}
