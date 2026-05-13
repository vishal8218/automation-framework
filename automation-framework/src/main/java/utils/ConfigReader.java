package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader() {
        prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "/src/test/resources/config.properties"
            );
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getSignUpUrl() {
        return prop.getProperty("base.signUp");
    }
    public String getFromEmail()
    {
    	return prop.getProperty("fromEmail");
    }
    public String getToEmail()
    {
    	return prop.getProperty("toEmail");
    }
    public String getEmailPass()
    {
    	return prop.getProperty("password");
    }
    public String getTriviaAdminUrl()
    {
    	return prop.getProperty("base.triviaUrl");
    }
    public String getAdminEmail()
    {
    	return prop.getProperty("adminEmail");

    }
    public String getAdminPass()
    {
    	return prop.getProperty("adminPass");

    }
}
