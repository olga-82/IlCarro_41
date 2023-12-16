package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.Browser;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;

import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class AplicationManager {

   Logger logger = LoggerFactory.getLogger(AplicationManager.class);
  //  WebDriver wd;
  WebDriver wd;
    HelperUser user;
    HelperCar car;
    HelperSearch search;
    Properties properties;

    public HelperSearch getSearch() {
        return search;
    }

    String browser;

    public HelperCar getCar() {
        return car;
    }

    public  AplicationManager(String browser) {
        properties = new Properties();
        this.browser = browser;

    }

    public HelperUser getUser() {

        return user;
    }

    public String getEmail() {
        return properties.getProperty("web.email");
    }
    public String getPassword() {
        return properties.getProperty("web.password");
    }

    @BeforeSuite(alwaysRun = true)
    public void init() throws IOException {
       // properties.load(new FileReader(new File("src/test/resources/prod.properties")));
        String target=System.getProperty("target","prod");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        if (browser.equals(Browser.CHROME.browserName())){
            ChromeOptions options = new ChromeOptions();
            // options.addArguments("--headless=new");
            WebDriver original = new ChromeDriver(options);
            WebDriverListener listener = new WdListener ();
           wd = new EventFiringDecorator(listener).decorate(original);
            logger.warn(browser);
        } else if (browser.equals(Browser.FIREFOX.browserName())){
            FirefoxOptions options = new FirefoxOptions();
           // options.addArguments("--headless");
            WebDriver original = new FirefoxDriver(options);
            WebDriverListener listener = new WdListener();
            wd = new EventFiringDecorator(listener).decorate(original);
            logger.warn(browser);
        }
//        } else if (browser.equals(BrowserType.SAFARI)) {
//            wd=new EventFiringWebDriver(new SafariDriver());
//            logger.info("Test start on Safari");
//
//
//        }
        user = new HelperUser(wd);
        car = new HelperCar(wd);
        search=new HelperSearch(wd);

        wd.manage().window().maximize();
//        wd.navigate().to("https://ilcarro.web.app/search)");
        wd.navigate().to(properties.getProperty("web.baseUrl"));
        wd.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));

    }

    @AfterSuite(alwaysRun = true)

    public void tearDown() {
        wd.quit();

    }


}