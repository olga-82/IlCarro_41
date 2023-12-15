package tests;

import manager.ProviderData;
import manager.TestNgListeners;
import models.User;
import models.UserLombok;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.lang.reflect.Method;

@Listeners(TestNgListeners.class)
public class LoginTests extends TestBase {
 @AfterMethod(alwaysRun = true)
    public void precondition(Method method){
        if (flagNeedLogout) {
            TestBase.app.getUser().logout();
            flagNeedLogout = false;
            logger.info("flagNeedLogout = " + flagNeedLogout);
            logger.info("method info: " + method.getName());
        } else if (flagNeedOpenMainPage) {
            TestBase.app.getUser().buttonLogo();
            flagNeedOpenMainPage = false;
            logger.info("flagNeedOpenMainPage = " + flagNeedOpenMainPage);
        }


    }
    @Test(groups = {"positive"},dataProvider="userDto",dataProviderClass = ProviderData.class)
    public void loginPositiveUser(User user) {
       TestBase.app.getUser().login(user);
        flagNeedLogout=true;
        logger.info("flagNeedLogout = " + flagNeedLogout);
        logger.info(" loginPositiveUser starts with credentials "
                + user.getEmail() + " " + user. getPassword());
        TestBase.app.getUser().isElementPresent(By.xpath("//h1[.='Logged in']")) ;
        TestBase.app.getUser().buttonOk();


    }
    @Test(groups = {"positive"},dataProvider="userDto",dataProviderClass = ProviderData.class)
    public void loginPositiveUser2(User user) {
        TestBase.app.getUser().login(user);
        flagNeedLogout=true;
        TestBase.app.getUser().isElementPresent(By.xpath("//h1[.='Logged in']")) ;
        TestBase.app.getUser().buttonOk();

    }
    @Test
    public void loginPositiveUserProps() {

        app.getUser().login(app.getEmail(),app.getPassword());
        flagNeedLogout=true;
        app.getUser().isElementPresent(By.xpath("//h1[.='Logged in']")) ;
        app.getUser().buttonOk();

    }
    @Test(groups = {"positive"},dataProvider="userDto",dataProviderClass = ProviderData.class)
    public void loginPositiveUserDTO(User user) {
        TestBase.app.getUser().login(user);
        flagNeedLogout=true;
        TestBase.app.getUser().isElementPresent(By.xpath("//h1[.='Logged in']")) ;
        TestBase.app.getUser().buttonOk();

    }

    @Test(groups = {"positive"})
    public void loginPositiveUserLombok() {
        UserLombok user =  UserLombok.builder()
                .email("nefr42@gmail.com")
                .password("Rita12345$")
                 .build();
      TestBase.app.getUser().loginLombok(user);
      flagNeedLogout=true;
        TestBase.app.getUser().isElementPresent(By.xpath("//h1[.='Logged in']")) ;
        TestBase.app.getUser().buttonOk();

    }
    @Test(groups = {"negative"})
    public void loginNegativeWrongEmail() {
        UserLombok user =  UserLombok.builder()
                .email("nefr4@gmail.com")
                .password("Rita12345$")
                .build();
        TestBase.app.getUser().loginLombok(user);
        flagNeedOpenMainPage=true;
        TestBase.app.getUser().isElementPresent(By.xpath("//h1[.='Login failed']")) ;
        TestBase.app.getUser().buttonOk();

    }
    @Test(groups = {"negative"})
    public void loginNegativeWrongPassword() {
        UserLombok user =  UserLombok.builder()
                .email("nefr42@gmail.com")
                .password(" ")
                .build();
        TestBase.app.getUser().loginLombok(user);
        flagNeedOpenMainPage=true;

        TestBase.app.getUser().isElementPresent(By.xpath("//h1[.='Login failed']")) ;
        TestBase.app.getUser().buttonOk();

    }


//    @AfterMethod
//    public void postcondition(){
//
//            app.getUser().buttonOk();
//
//    }

}
