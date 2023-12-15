package tests;

import manager.ProviderData;
import manager.TestNgListeners;
import models.User;
import models.UserLombok;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
@Listeners(TestNgListeners.class)
public class RegistrationTests extends TestBase {


    @AfterMethod(alwaysRun = true)
    public void postCondAfterMethod(Method method) {
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

    @Test (groups = {"positive"},dataProvider="userDtoRegPositive",dataProviderClass = ProviderData.class)
    public void registrationPositive(User user) {

       app.getUser().openRegestrationForm();
       app.getUser().fillRegistrationFormWith(user);
       app.getUser().submitLogin();
        logger.info("registrationPositive starts with credentials "
                + user.getEmail() + " " + user. getPassword());
        flagNeedLogout = true;
        logger.info("flagNeedLogout = " + flagNeedLogout);
        Assert.assertTrue(TestBase.app.getUser().isLoggedSuccess());
       app.getUser().buttonOk();


    }
    @Test(groups = {"positive"},dataProvider="userDtoCSV",dataProviderClass = ProviderData.class)
    public void registrationPositiveCSV(User user) {

        TestBase.app.getUser().openRegestrationForm();
        TestBase.app.getUser().fillRegistrationFormWith(user);
        TestBase.app.getUser().submitLogin();
        logger.info("registrationPositive starts with credentials "
                + user.getEmail() + " " + user. getPassword());
        flagNeedLogout = true;
        logger.info("flagNeedLogout = " + flagNeedLogout);
        Assert.assertTrue(TestBase.app.getUser().isLoggedSuccess());
        app.getUser().buttonOk();


    }
    @Test(groups = {"negative"},dataProvider="userDtoRegNegative",dataProviderClass = ProviderData.class)
    public void registrationNegativeWrongPassword(User user) {
        TestBase.app.getUser().openRegestrationForm();
        TestBase.app.getUser().fillRegistrationFormWith(user);
        TestBase.app.getUser().submitLogin();
        flagNeedOpenMainPage=true;
        logger.info("flagNeedOpenMainPage = " + flagNeedOpenMainPage);
        Assert.assertTrue(TestBase.app.getUser().isLoggedErrorWrongPassword());


      //  Password must contain 1 uppercase letter, 1 lowercase letter, 1 number and one special symbol of [@$#^&amp;*!]
    }
    @Test(groups = {"negative"})
    public void registrationNegativeWrongEmail() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        User user = new User()
                .withName("Sally")
                .withLastName("Rotten")
                .withEmail("nefr"+i+"gmail.com")
                .withPassword("Rita12300$");
        TestBase.app.getUser().openRegestrationForm();
        TestBase.app.getUser().fillRegistrationFormWith(user);
        TestBase.app.getUser().submitLogin();
        flagNeedOpenMainPage=true;
        logger.info("flagNeedOpenMainPage = " + flagNeedOpenMainPage);
        Assert.assertTrue(TestBase.app.getUser().isLoggedErrorWrongEmail());
       // app.getUser().buttonLogo();




    }
    @Test(groups = {"negative"})
    public void registrationNegativeEmptyPassword() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
        UserLombok userLombok =  UserLombok.builder()
                .name("Sally")
                .lastName("Rotten")
                .email("nefr"+i+"@gmail.com")
                .password("")
                .build();
        TestBase.app.getUser().openRegestrationForm();
        TestBase.app.getUser().fillRegistrationFormLombok(userLombok);
        TestBase.app.getUser().submitLogin();
        flagNeedOpenMainPage=true;
        logger.info("flagNeedOpenMainPage = " + flagNeedOpenMainPage);
        Assert.assertTrue(TestBase.app.getUser().isLoggedErrorWrongPassword());
       // app.getUser().buttonLogo();

    }



    @AfterMethod(alwaysRun = true)
    public void postcondition(){

      //  app.getUser().buttonOk();

    }

}
