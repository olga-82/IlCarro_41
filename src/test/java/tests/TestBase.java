package tests;

import manager.AplicationManager;
import manager.TestNgListeners;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.lang.reflect.Method;
@Listeners(TestNgListeners.class)
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);
  static   AplicationManager app = new AplicationManager
          (  System.getProperty("browser", Browser.CHROME.browserName())
          );

    boolean flagNeedLogout = false;
    boolean flagNeedOpenMainPage = false;


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws IOException {
        app.init();
    }



    @AfterSuite(alwaysRun = true)

    public void stop() {
      // app.tearDown();

    }
  public void startLogger(Method method) {
    logger.info(method.getName() + "  is started ");
  }
  @AfterMethod(alwaysRun = true)
  public void stopLogger() {
    logger.info("================================================================================================================");
  }



}
