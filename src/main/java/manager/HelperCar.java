package manager;

import models.Car;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperCar extends HelperBase {
    public HelperCar(WebDriver wd) {
        super(wd);
    }

    public void openCarForm() {
        click(By.xpath("//*[.=' Let the car work ']"));

    }

    public void fillCarForm(Car car) {
        // if (isCarFormPresent()==false)  return;
        typeLocation(car.getLocation());
        type(By.id("make"), car.getMake());
        type(By.id("model"), car.getModel());
        type(By.id("year"), car.getYear());
        select(By.id("fuel"), car.getFuel());
        type(By.id("seats"), car.getSeats());
        type(By.id("class"), car.getCarClass());
       // clickFillCarRegNumber();
         type(By.id("serialNumber"), car.getCarRegNumber());
        type(By.id("price"), car.getPrice());
        // type(By.id("about"), car.getAbout());

    }

    public void typeLocation(String address) {
        type(By.id("pickUpPlace"), address);
        click(By.cssSelector("div.pac-item"));

    }

    public void select(By locator, String options) {
        new Select(wd.findElement(locator)).selectByVisibleText(options);


    }

    public boolean isCarFormPresent() {
        return new WebDriverWait(wd, Duration.ofSeconds(20))
                .until(ExpectedConditions.textToBePresentInElement
                        (wd.findElement(By.cssSelector("h2")), "details"));
    }

    public void clickFillCarRegNumber(String text) {
        WebElement rect = wd.findElement(By.id("serialNumber"));

        Actions actions = new Actions(wd);
        actions.moveToElement(rect).click().perform();
        rect.clear();
        rect.sendKeys(text);
    }
    public  void btnCarSuccess(){
        click(By.xpath("//button[.='Add another car']"));
    }

}