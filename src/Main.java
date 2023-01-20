import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {

    public static WebDriver driver;

    public static void main(String[] args) {

        String currentDir = System.getProperty("user.dir");
        String path = currentDir + "\\Resources\\chromedriver.exe";
        System.out.println(currentDir);
        System.out.println(path);

        System.setProperty("webdriver.chrome.driver", path);

        // We want to ignore Notification and Location popup when launching home page
        // notification - for Notification; geolocation = for Location
        // make Map - put Key, Value -

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

    }

}