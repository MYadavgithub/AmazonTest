import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseCLass {
    public static WebDriver driver;

    public WebDriver initialisation(){
        String chromedriverpath = System.getProperty("user.dir")+ "/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",chromedriverpath);
         driver = new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
         return driver;
    }

}
