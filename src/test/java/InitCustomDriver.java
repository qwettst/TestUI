import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.logging.Logger;

public class InitCustomDriver {
    private static Logger logger = Logger.getLogger(InitCustomDriver.class.getName());
    private static InitCustomDriver obj=null;
    private static WebDriver driver=null;

    private InitCustomDriver() {
    }

    public static InitCustomDriver create_driver(){
        if(obj==null){
            obj=new InitCustomDriver();
            obj.initDriver();
        }
        return obj;
    }

    public void initDriver(){
        logger.info("Creating driver");
        System.setProperty("webdriver.gecko.driver", "C:/GDriver/geckodriver.exe");
        driver=new FirefoxDriver();
        logger.info("Driver created");
    }

    public void closeDriver(){
        logger.info("Close driver");
        if(driver != null){
            driver.quit();
            logger.info("Driver destroyed");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
