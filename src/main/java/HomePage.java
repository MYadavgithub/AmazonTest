import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePage extends BaseCLass {


    @FindBy(xpath="//input[@id='twotabsearchtextbox']")
    WebElement search;
    @FindBy(xpath = "//input[@id='nav-search-submit-button']")
    WebElement SearchButton;


    public HomePage() {
        PageFactory.initElements(driver,this);
    }

    public void SearchWristWatches(){
        driver.manage().window().maximize();
        driver.get("https://amazon.in/");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        search.sendKeys("Wrist watches");
        SearchButton.click();
    }



}
