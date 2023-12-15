package manager;

import models.User;
import models.UserLombok;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HelperUser extends HelperBase {

    public HelperUser(WebDriver wd) {
        super(wd);
    }
    public void openLoginForm() {
        wd.findElement(By.xpath(" //a[.=' Log in ']")).click();
    }
     public void openRegestrationForm() {
        wd.findElement(By.xpath("//*[.=' Sign up ']")).click();
    }

    public void fillLoginForm(String email, String password) {
        type(By.xpath(" //input[@id='email']"), email);
        type(By.xpath(" //input[@id='password']"), password);
    }


    public void submitLogin() {
     // wd.findElement (By.xpath(" //button[@type='submit']")).submit();
       wd.findElement (By.xpath(" //button[@type='submit']")).click();
    }
    public  void buttonOk() {
        click(By.xpath(" //button[@class='positive-button ng-star-inserted']"));
    }
    public void buttonLogo() {
        click(By.xpath(" /html/body/app-root/app-navigator/div[1]/a[1]"));
    }

    public void fillLoginForm(User user) {
        type(By.xpath(" //input[@id='email']"), user.getEmail());
        type(By.xpath(" //input[@id='password']"), user.getPassword());
    }
    public void fillRegistrationFormLombok(UserLombok user) {
        type(By.xpath(" //input[@id='name']"), user.getName());
        type(By.xpath(" //input[@id='lastName']"), user.getLastName());
        type(By.xpath(" //input[@id='email']"), user.getEmail());
        type(By.xpath(" //input[@id='password']"), user.getPassword());
        clickCheckbox();

    }
    public void fillRegistrationFormWith(User user) {
        type(By.xpath(" //input[@id='name']"), user.getName());
        type(By.xpath(" //input[@id='lastName']"), user.getLastName());
        type(By.xpath(" //input[@id='email']"), user.getEmail());
        type(By.xpath(" //input[@id='password']"), user.getPassword());
        clickCheckbox();

    }

    public void clickCheckbox() {
        // click(By.cssSelector("label[for='terms-of-use']"));
        JavascriptExecutor js = (JavascriptExecutor) wd;
        js.executeScript("document.querySelector('#terms-of-use').click();");

//        Rectangle rect = wd.findElement(By.cssSelector("div.checkbox-container")).getRect();
//        int x = rect.getX()+5;
//        int y = rect.getY()+ rect.getHeight()/4;
//        Actions actions = new Actions(wd);
//        actions.moveByOffset(x,y).click().perform();
    }



    public void login(User user) {
        openLoginForm();
        fillLoginForm(user);
        submitLogin();


    }
    public void loginLombok(UserLombok user) {
        openLoginForm();
        fillLoginForm(user.getEmail(), user.getPassword());
        submitLogin();

    }
    public boolean isLoggedSuccess()

    {
        return isElementPresent(By.xpath("//h2[contains(text(), 'success')]"));
    }
    public boolean isLoggedErrorWrongPassword() {
        return isElementPresent(By.xpath("//div/app-registration/form/div[4]/div/div"));
    }
    public boolean isAddCarSuccess() {

        return isElementPresent(By.xpath("//h1[. ='Car added']"));
    }
    public boolean isMessageError() {
        return isElementPresent(By.xpath("//div*[.='Password must contain 1 uppercase letter, 1 lowercase letter," +
                " 1 number and one special symbol of [@$#^&amp;*!]]"));
    }


    public boolean isLoggedErrorWrongEmail() {
        return isElementPresent(By.xpath("//div/app-registration/form/div[3]/div/div[1]"));
    }
    public boolean  isRegistrationErrorWrongPassword() {
        return isElementPresent(By.xpath("//div[@class ='ng-star-inserted']"));
    }

    public void login(String email, String password) {
        openLoginForm();
        fillLoginForm(email, password);
        submitLogin();

    }
}

//div[@class ='ng-star-inserted']