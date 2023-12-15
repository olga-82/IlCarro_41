package tests;

import manager.TestNgListeners;
import models.Car;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class AddNewCar extends TestBase {

    @BeforeMethod(alwaysRun = true)
    public void Preconditions() {
        if (!TestBase.app.getUser().isLogged())


            app.getUser().login(app.getEmail(), app.getPassword());
            app.getUser().buttonOk();
//
//
//        TestBase.app.getUser().login(new User()
//                    .withEmail("nefr42@gmail.com")
//                    .withPassword("Rita12345$"));
//             TestBase.app.getUser().buttonOk();

    }
    @Test(groups = {"positive"})
    public void addNewCarPositive() {
        int i = (int) (System.currentTimeMillis() / 1000) % 3600;
     Car car = Car.builder()
             .location("Tel Aviv")
             .make("KIA")
             .model("Sportage")
             .year("2009")
             .fuel("Petrol")
             .seats("5")
             .carClass("B")
             .carRegNumber("110-200-"+i)
             .price("150")
             .about("")
             .build();
     TestBase.app.getUser().pause(5000);
     TestBase.app.getCar().openCarForm();
     TestBase.app.getCar().fillCarForm(car);
     TestBase.app.getUser().submitLogin();
     flagNeedLogout=true;
     Assert.assertTrue(TestBase.app.getUser().isAddCarSuccess());
     TestBase.app.getCar().btnCarSuccess();



    } @AfterMethod(alwaysRun = true)
    public void postCondAfterMethod(Method method) {
        if (flagNeedLogout) {
            TestBase.app.getUser().logout();
            flagNeedLogout = false;
            logger.info("flagNeedLogout = " + flagNeedLogout);
            logger.info("method info: " + method.getName());
        }

    }



}
//h1[. ='Car added']

//button[.='Show car']
//button[.='Add another car']

//*[@id="mat-dialog-3"]/app-error/div/div/button[3]

//*[@id="mat-dialog-3"]/app-error/div/div/button[1]