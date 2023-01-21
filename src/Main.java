import com.sun.javafx.collections.MappingChange;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        //setting chromeDriver location
        String currentDir = System.getProperty("user.dir");
        String path = currentDir + "\\Resources\\chromedriver.exe"; //for windows double backslash
        System.out.println(currentDir);
        System.out.println(path);

        System.setProperty("webdriver.chrome.driver", path);

        // We want to ignore Notification and Location popup when launching home page
        // notification - for Notification; geolocation = for Location
        // make Map - put Key, Value - put - make and call object

        Map<String, Integer> contentSettings = new HashMap<String, Integer>();
        Map<String, Object> profile = new HashMap<String, Object>();
        Map<String, Object> prefs = new HashMap<String, Object>();

        contentSettings.put("notifications", 2);
        contentSettings.put("geolocation", 2);
        profile.put("managed_default_content_settings", contentSettings);
        prefs.put("profile", profile);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);

        driver.get("https://chaldal.com/");
        driver.manage().window().maximize();

        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[2]/div/div/div[2]/div[1]/div/div/div[3]")).click();

        // Array list -- to work with multiple Tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles()); //windowHandles takes all tabs
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(5000); //delay creation before next step
        driver.close(); // closes current tab
        driver.switchTo().window(tabs.get(0));

        //Explicit wait - for single element
//        WebDriverWait wait = new WebDriverWait(driver, 10); //making constructor
//        WebElement certainElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(""))); //calling object for the element
//        certainElement.click();


        // Implicit wait - for all elements of a page
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // we want to search item egg
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[1]/div[1]/form/div/div[1]/input")).sendKeys("egg");
        driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[1]/div[1]/form/div/div[1]/input")).sendKeys(Keys.ENTER);
        Thread.sleep(4000);

        // adding an item to bag using mouse hover
        WebElement noodles = driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/section/div/div/div/div/section/div[3]/div[2]/div[2]/div/div/div[1]/img"));
        Actions action = new Actions(driver);
        action.moveToElement(noodles).perform();
        Thread.sleep(3000);
        action.click(noodles).perform();

        // going back to home page using navigator
        driver.navigate().back();

        // getting homePage header text
        String text = driver.findElement(By.xpath("/html/body/div[2]/div/div[6]/section/div/div/div[1]/div/div/h2/span[2]")).getText();
        System.out.println(text);

        Thread.sleep(2000);
        driver.quit(); // closes browser


    }

}