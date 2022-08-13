import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AmazonTest extends BaseCLass{


     @BeforeClass
     public void OpenWebsite(){
       initialisation();
     }

     WatchesPage WP;
     ProductDetailsHelper ProductPageObject;

    @Test(priority = 1)
    public void getAmazonSearchResults() throws InterruptedException {

          WP = new WatchesPage();
          WP.FilterWatches();
          driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
          ProductPageObject = new ProductDetailsHelper();
          if(ProductPageObject.isWatchPresent(5)) {
              ProductPageObject.productdetail(5);
          }
          if(ProductPageObject.isWatchPresent(10)) {
            ProductPageObject.productdetail(10);
          }
          if(ProductPageObject.isWatchPresent(15)) {
            ProductPageObject.productdetail(15);
          }
          ProductPageObject.printWatchDetails();
         // ProductPageObject.ValidateWatchDetails(FinalMap);
    }

    @Test(priority = 2)
    public void ValidateAmazonResults(){
        SoftAssert softAssert = new SoftAssert();
        for(Map.Entry<String, Map<String, String>> entryset : ProductPageObject.FinalMap.entrySet()){
            String Watchnumber = entryset.getKey();
            HashMap value1 = (HashMap) entryset.getValue();
            Iterator it = value1.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry pair = (Map.Entry) it.next();
                String key = (String) pair.getKey();
                String detailsvalue = (String) pair.getValue();

                if(key.trim().equals("Brand")){
                    softAssert.assertEquals(detailsvalue,"Titan","Brand is not Titan ");
                }
                else if(key.trim().equalsIgnoreCase("Display Type")){
                    softAssert.assertEquals(detailsvalue,"Analog","Display Type is not Analog");
                }
                else if(key.trim().equals("Band Material")){
                    softAssert.assertEquals(detailsvalue,"Leather","Band material is not leather");
                }
                else if(key.trim().equals("Discount Percentage")){
                    String discount = detailsvalue.replace("%","");
                    int discountfinal = Math.abs(Integer.parseInt(discount));
                    softAssert.assertTrue(discountfinal>=25, ""+Watchnumber+" discount is not greater than or equal to 25% ");
                }
                else if(key.trim().equals("Delivery details")){
                    softAssert.assertTrue(detailsvalue.contains("FREE delivery"),""+Watchnumber+" Free delivery option is not available");
                }
                softAssert.assertAll();
            }
        }
    }

    @AfterClass
    public void close(){
        driver.close();
    }



}

