import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import java.util.*;
import java.util.List;

public class ProductDetailsHelper extends WatchesPage {


    String brand;
    String Displaytype;
    String BandMaterial;
    String DiscountPercentage;
    String FinalPrice;
    String Delivery;


    public HashMap<String, Map<String,String>> FinalMap = new HashMap<>();


    public Boolean isWatchPresent(int position){
        try{
            driver.findElement(By.xpath("//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_"+position+"']"));
            return true;
        }
        catch (Exception e){
            System.out.println("Product not found");
            return false;
        }
    }

    public void productdetail(int pos) throws InterruptedException {

        HashMap<String,String> WatchDetails = new HashMap<>();
        String HomePage_handle = driver.getWindowHandle();
        //span[@id='BEST_DEAL_B01KQJVPUI-label']//span[@class='a-badge-text'][normalize-space()='Limited time deal']
        WebElement product = driver.findElement(By.xpath("//div[@class='s-widget-container s-spacing-small s-widget-container-height-small celwidget slot=MAIN template=SEARCH_RESULTS widgetId=search-results_"+pos+"']"));
        product.click();
        Thread.sleep(6000);
        Set<String> handles =  driver.getWindowHandles();
        //Iterator<String> I = handles.iterator();
        List<String> windowhandles = new ArrayList<>(handles);

        //while(I.hasNext()) {
        for (int k=1; k<windowhandles.size();k++) {
            String productpage_handle = windowhandles.get(k);
            if (!HomePage_handle.equals(productpage_handle)) {
                driver.switchTo().window(productpage_handle);
                WebElement table = driver.findElement((By) By.xpath("//table[@id='technicalSpecifications_section_1']/tbody"));
                List<WebElement> TotalRowsList = table.findElements(By.tagName("tr"));
                int num_rows = TotalRowsList.size();
                for (int i = 1; i <= num_rows; i++) {
                    if ((driver.findElement(By.xpath("//table[@id='technicalSpecifications_section_1']/tbody/tr[" + i + "]/th")).getText()).equals("Brand")) {
                        brand = driver.findElement(By.xpath("//table[@id='technicalSpecifications_section_1']/tbody/tr[" + i + "]/td")).getText();
                        //System.out.println("Brand: "+ brand);
                    }
                    if ((driver.findElement(By.xpath("//table[@id='technicalSpecifications_section_1']/tbody/tr[" + i + "]/th")).getText()).equals("Display Type")) {
                        Displaytype = driver.findElement(By.xpath("//table[@id='technicalSpecifications_section_1']/tbody/tr[" + i + "]/td")).getText();
                        // System.out.println("Display Type"+ Displaytype	);
                    }
                    if ((driver.findElement(By.xpath("//table[@id='technicalSpecifications_section_1']/tbody/tr[" + i + "]/th")).getText()).equals("Band Material")) {
                        BandMaterial = driver.findElement(By.xpath("//table[@id='technicalSpecifications_section_1']/tbody/tr[" + i + "]/td")).getText();
                        // System.out.println("Band Material: "+ BandMaterial);
                    }
                }

                try {
                    if (driver.findElement(By.xpath("//td[normalize-space()='You Save:']")).getText().equals("You Save:")) {
                        String DiscountedPrice = driver.findElement(By.xpath("//span[@class='a-color-price']")).getText();
                        DiscountPercentage = DiscountedPrice.substring(DiscountedPrice.indexOf("(") + 1, DiscountedPrice.indexOf(")"));
                        FinalPrice = driver.findElement(By.xpath("//span[@class='a-price a-text-price a-size-medium']")).getText();
                    }
                }
                 catch (Exception e){
                    DiscountPercentage = driver.findElement(By.xpath("//span[@class='a-size-large a-color-price savingPriceOverride aok-align-center reinventPriceSavingsPercentageMargin savingsPercentage']")).getText();
                    FinalPrice = driver.findElement(By.xpath("//span[@class='a-price aok-align-center reinventPricePriceToPayMargin priceToPay']//span[@class='a-price-whole']")).getText();
                }
            }

                Delivery =driver.findElement(By.xpath("//span[@data-csa-c-type='element']")).getText();
                driver.close();
            }
        WatchDetails.put("Brand ", brand);
        WatchDetails.put("Display Type ", Displaytype);
        WatchDetails.put("Band Material ", BandMaterial);
        WatchDetails.put("Discount Percentage ", DiscountPercentage);
        WatchDetails.put("Final Price ", FinalPrice);
        WatchDetails.put("Delivery details ", Delivery);

        FinalMap.put("Watch "+pos+" ",WatchDetails);
        driver.switchTo().window(HomePage_handle);
        
    }



    public void printWatchDetails(){
        for(Map.Entry<String, Map<String, String>> entryset : FinalMap.entrySet()){
            String Watch_number = entryset.getKey();
            System.out.println("Details of "+ Watch_number +":");
            HashMap value = (HashMap) entryset.getValue();
            Iterator it = value.entrySet().iterator();
            while (it.hasNext()){
                Map.Entry pair = (Map.Entry) it.next();
                System.out.println(pair.getKey()+"= "+pair.getValue());
            }
            System.out.println();
        }
    }

//    public void ValidateWatchDetails(HashMap<String,Map<String,String>> map){
//        SoftAssert softAssert = new SoftAssert();
//        for(Map.Entry<String, Map<String, String>> entryset : map.entrySet()){
//            HashMap value1 = (HashMap) entryset.getValue();
//            Iterator it = value1.entrySet().iterator();
//            while (it.hasNext()){
//                Map.Entry pair = (Map.Entry) it.next();
//                String key = (String) pair.getKey();
//                String detailsvalue = (String) pair.getValue();
//
//                if(key.trim().equals("Brand")){
//                    softAssert.assertEquals(detailsvalue,"Titan","Brand is not Titan ");
//                }
//                else if(key.trim().equalsIgnoreCase("Display Type")){
//                    softAssert.assertEquals(detailsvalue,"Analog","Display Type is not Analog");
//                }
//                else if(key.trim().equals("Band Material")){
//                    softAssert.assertEquals(detailsvalue,"Leather","Band material is not leather");
//                }
//                else if(key.trim().equals("Discount Percentage")){
//                    String discount = detailsvalue.replace("%","");
//                    int discountfinal = Math.abs(Integer.parseInt(discount));
//                    softAssert.assertTrue(discountfinal>=25, "discount is not greater than or equal to 25%");
//                }
//                else if(key.trim().equals("Delivery details")){
//                    softAssert.assertTrue(detailsvalue.contains("FREE delivery"),"Free delivery option is not available");
//                }
//                softAssert.assertAll();
//            }
//        }
//    }
   }

