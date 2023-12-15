package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SearchTests extends TestBase{
@AfterMethod(alwaysRun = true)
public void postMethod(){

    app.getUser().buttonLogo();
}

    @Test(groups = {"positive"})
    public void SearchTestsDateString() {

  app.getSearch().fillSearchForm("Tel Aviv","12/10/2023","12/20/2023");
  app.getSearch().pause(2000);
   Assert.assertTrue( app.getSearch().isCarPresent());



    }
    @Test(groups = {"positive"})
    public void SearchTestsFormPeriodDaysDatePicker(){

        app.getSearch().fillSearchFormPeriodDaysDatePicker("Tel Aviv","12/15/2023","12/21/2023");
        app.getSearch().pause(2000);
       Assert.assertTrue( app.getSearch().isCarPresent());
    }

    @Test(groups = {"positive"})
    public void SearchTestsMyMethod(){
        app.getSearch().fillSearchForm3();
        app.getSearch().pause(2000);
        Assert.assertTrue( app.getSearch().isCarPresent());
    }
    @Test(groups = {"positive"})
    public void SearchTestsFormPeriodYearsDate(){

        app.getSearch().fillSearchFormPeriodYearsDate("Tel Aviv","12/12/2023","03/21/2024");
        app.getSearch().pause(2000);
        Assert.assertTrue( app.getSearch().isCarPresent());
    }



}
