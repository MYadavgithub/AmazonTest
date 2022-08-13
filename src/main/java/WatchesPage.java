import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WatchesPage extends HomePage {

    public HomePage hp;

    @FindBy(xpath = "//li[@id='p_89/Titan']//i[@class='a-icon a-icon-checkbox']")
    WebElement Brand;

    @FindBy(xpath = "//span[contains(text(),'25% Off or more')]")
    WebElement Discount;

    @FindBy(xpath = "//li[@id='p_n_feature_seven_browse-bin/1480900031']//i[@class='a-icon a-icon-checkbox']")
    WebElement Display_type;

    @FindBy(xpath = "//li[@id='p_n_material_browse/1480907031']//i[@class='a-icon a-icon-checkbox']")
    WebElement Material;

    public void FilterWatches() throws InterruptedException {
        hp = new HomePage();
        hp.SearchWristWatches();
        Thread.sleep(5000);
        Brand.click();
        Thread.sleep(5000);
        Discount.click();
        Thread.sleep(5000);
        Display_type.click();
        Thread.sleep(5000);
        Material.click();

    }



}
