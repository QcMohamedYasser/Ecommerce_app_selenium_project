import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.ClickAndHoldAction;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Ecommerce_Tests {
    WebDriver driver;
 SoftAssert soft =new SoftAssert();
    @BeforeTest
    public void open_browser(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofNanos(3));
    }
    @Test
    public void verify_mobile_list_page(){
        driver.navigate().to("http://live.techpanda.org/index.php/");
        String text=  driver.findElement(By.className("page-title")).getText();
        System.out.println(text);
        driver.findElement(By.className("level0")).click();
        Select category= new Select(driver.findElement(By.cssSelector("select[title=\"Sort By\"]")));
        category.selectByIndex(1);
    }
@Test
public void verify_product_cost()
    {
        driver.navigate().to("http://live.techpanda.org/index.php/");
        driver.findElement(By.className("level0")).click();
        String expected=driver.findElement(By.xpath("//ul[@class=\"products-grid products-grid--max-4-col first last odd\"]//li[3]//div[@class=\"price-box\"]")).getText();
        System.out.println(expected);
        driver.findElement(By.xpath("//ul[@class=\"products-grid products-grid--max-4-col first last odd\"]//li[3]")).click();
        String actual=driver.findElement(By.xpath("//div[@class=\"price-box\"]")).getText();
         soft.assertEquals(actual,expected,"true price");
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void verify_add_products_from_store(){
        driver.navigate().to("http://live.techpanda.org/index.php/");
        driver.findElement(By.className("level0")).click();
        driver.findElement(By.xpath("//ul[@class=\"products-grid products-grid--max-4-col first last odd\"]//li[3]//button[@class=\"button btn-cart\"]")).click();
        driver.findElement(By.xpath("//input[@class=\"input-text qty\"]")).clear();
        driver.findElement(By.xpath("//input[@class=\"input-text qty\"]")).sendKeys("1000");
        driver.findElement(By.xpath("//button[@class=\"button btn-update\"]")).click();
       String error_msg= driver.findElement(By.xpath("//li[@class=\"error-msg\"]")).getText();
        System.out.println(error_msg);
        driver.findElement(By.id("empty_cart_button")).click();
        String empty=driver.findElement(By.xpath("//div[@class=\"page-title\"]")).getText();

        System.out.println(empty);
    }
    @Test
    public void compare_2_products() throws InterruptedException {
        driver.navigate().to("http://live.techpanda.org/index.php/");
        driver.findElement(By.className("level0")).click();
        driver.findElement(By.xpath("//ul[@class=\"products-grid products-grid--max-4-col first last odd\"]//li[3]//li[2]")).click();
        driver.findElement(By.xpath("//ul[@class=\"products-grid products-grid--max-4-col first last odd\"]//li[2]//li[2]")).click();
        String parent =driver.getWindowHandle();
        driver.findElement(By.xpath("//div[@class=\"block-content\"]//button[@class=\"button\"]")).click();
            Thread.sleep(7000);
        Set<String> all_windows=driver.getWindowHandles();
        for (String window:all_windows)
        {
            if (!window.equalsIgnoreCase(parent))
            {
                driver.switchTo().window(window);
            }
        }

       String msg= driver.findElement(By.xpath("//div[@class=\"page-title title-buttons\"] ")).getText();
        System.out.println(msg);
        driver.findElement(By.xpath("//*[@id=\"top\"]/body/div[1]/div[2]/button/span/span")).click();
                  driver.switchTo().window(parent);


        }

@Test
public void create_account() throws InterruptedException {
   driver.navigate().to("http://live.techpanda.org/index.php/");
    driver.findElement(By.xpath("//div[@class=\"footer\"]//div[4]//li[1]//a[@title=\"My Account\"]")).click();

    driver.findElement(By.cssSelector("a[title=\"Create an Account\"]")).click();
    Thread.sleep(2000);
    driver.findElement(By.id("firstname")).sendKeys("Mohamed");
    driver.findElement(By.id("middlename")).sendKeys("yasser");
    driver.findElement(By.id("lastname")).sendKeys("Mohamed");
    driver.findElement(By.id("email_address")).sendKeys("hamoya40@yahoo.com");
    driver.findElement(By.id("password")).sendKeys("Mohamed123");
    driver.findElement(By.id("confirmation")).sendKeys("Mohamed123");
    driver.findElement(By.id("is_subscribed")).click();
    driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button")).click();


}


@Test
public void prush_prodduct_using_email() throws InterruptedException {

/*
    driver.navigate().to("http://live.techpanda.org/index.php/");
    driver.findElement(By.xpath("//div[@class=\"footer\"]//div[4]//li[1]//a[@title=\"My Account\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("hamoya40@yahoo.com");
    driver.findElement(By.id("pass")).sendKeys("Mohamed123");
    driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);

    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div[1]/div/div[2]/ul/li[8]/a")).click();
   // dynamic value
   /* driver.findElement(By.xpath("//li[@class=\"level0 nav-2 last\"]")).click();*/
    //*/
    driver.navigate().to("http://live.techpanda.org/index.php/");
    driver.findElement(By.xpath("//div[@class=\"footer\"]//div[4]//li[1]//a[@title=\"My Account\"]")).click();

    driver.findElement(By.id("email")).sendKeys("hamoya40@yahoo.com");
    driver.findElement(By.id("pass")).sendKeys("Mohamed123");
    driver.findElement(By.id("pass")).sendKeys(Keys.ENTER);
    driver.findElement(By.xpath("//li[@class=\"level0 nav-2 last\"]")).click();
    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div[1]/div[2]/ul/li[2]/div/div[3]/ul/li[1]/a")).click();

    driver.findElement(By.xpath("//*[@id=\"wishlist-view-form\"]/div/div/button[1]/span/span")).click();
    driver.findElement(By.id("email_address")).sendKeys("hamoya40@yahoo.com");
    driver.findElement(By.xpath("//*[@id=\"form-validate\"]/div[2]/button/span/span")).click();
    String txt=driver.findElement(By.xpath("//*[@id=\"top\"]/body/div/div/div[2]/div/div[2]/div/div[1]/ul/li/ul/li/span")).getText();

    System.out.println(txt);

    driver.findElement(By.xpath("//*[contains(@id,\"item_\")]/td[5]/div/button")).click();

    driver.findElement(By.xpath("//*[@id=\"top\"]/body/div[1]/div/div[2]/div/div/div/div[1]/ul/li/button")).click();

    driver.findElement(By.name("billing[firstname]")).sendKeys("Mohamed");
    driver.findElement(By.name("billing[middlename]")).sendKeys("yasser");
    driver.findElement(By.name("billing[lastname]")).sendKeys("Mohamed");

    driver.findElement(By.id("billing:street1")).sendKeys("ABC");

    Select state=new Select(driver.findElement(By.id("billing:region_id")));
    state.selectByIndex(43);
    driver.findElement(By.id("billing:postcode")).sendKeys("542896");
    Select country=new Select(driver.findElement(By.id("billing:country_id")));
    country.selectByValue("US");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    driver.findElement(By.id("billing:telephone")).sendKeys("12345678");
   driver.findElement(By.id("billing:use_for_shipping_yes")).isSelected();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
   driver.findElement(By.xpath("//*[@id=\"billing-buttons-container\"]/button/span/span")).click();
}




    @AfterTest
   public void close_browser()
    {
        driver.quit();
    }
}
