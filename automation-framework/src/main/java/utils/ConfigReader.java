package utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;
    String path="/src/test/resources/config.properties";

    public ConfigReader() {
        this.prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + path
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
    public String getDbUrl()
    {
    	return prop.getProperty("db.url");
    }
    public String getCred()
    {
    	return prop.getProperty("firebase.credentialFilePath");
    }
    public String getCoachPortfolioLink()
    {
    	return prop.getProperty("base.coachPortfolio");
    }
    public String getBrowser()
    {
    	return prop.getProperty("browser");
    }
    public String getSignInUrl()
    {
    	return prop.getProperty("base.signIn");
    }
    
}
