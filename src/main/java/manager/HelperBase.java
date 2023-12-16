package manager;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }
    public void click(By locator) {

        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        WebElement element = wd.findElement(locator);
       // element.click();
        element.clear();
        element.sendKeys(text);
    }
    public void typeEmailLogin(By locator, String text) {
        WebElement element = wd.findElement(locator);
        element.click();
        WebElement element2 = wd.findElement(locator);
        element2.click();
        element.clear();
        element.sendKeys(text);

    }
    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public boolean isElementPresent(By locator) {
        return wd.findElements(locator).size() > 0;
    }

    public void logout() {
        click(By.xpath("//a[@class='navigation-link ng-star-inserted']"));
    }
    public boolean isLogged() {
        return isElementPresent(By.xpath("//a[@href='/logout?url=%2Fsearch']"));

    }
    public  void takeScreenshot(String link) {
        File tmp=((TakesScreenshot)wd).getScreenshotAs(OutputType.FILE);
        File screenshot=new File(link);
        try {
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

