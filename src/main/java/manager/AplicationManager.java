package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AplicationManager {

   Logger logger = LoggerFactory.getLogger(AplicationManager.class);
  //  WebDriver wd;
  EventFiringWebDriver wd;
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
        if(browser.equals(BrowserType.CHROME)) {
            wd = new EventFiringWebDriver(new ChromeDriver());
            logger.info("Test start on Chrome");
        }else if(browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
            logger.info("Test start on FireFox");
        } else if (browser.equals(BrowserType.SAFARI)) {
            wd=new EventFiringWebDriver(new SafariDriver());
            logger.info("Test start on Safari");


        }
        user = new HelperUser(wd);
        car = new HelperCar(wd);
        search=new HelperSearch(wd);
        wd.register(new WdListener());

        wd.manage().window().maximize();
//        wd.navigate().to("https://ilcarro.web.app/search)");
        wd.navigate().to(properties.getProperty("web.baseUrl"));
        wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @AfterSuite(alwaysRun = true)

    public void tearDown() {
        wd.quit();

    }


}