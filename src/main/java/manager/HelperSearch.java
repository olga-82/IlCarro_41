package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HelperSearch extends HelperBase {
    public HelperSearch(WebDriver wd) {
        super(wd);
    }

    public void fillCity(String city) {
        type(By.id("city"), city);
        click(By.cssSelector("div.pac-item"));

    }

    public void selectPeriodDays(String from, String to) {
        // click(By.id("dates"));
        type(By.id("dates"), from + " - " + to);
        // wd.findElement(By.id("dates")).submit();
    }

    public void selectPeriodDaysDatePicker(String daysFrom, String daysTo) {
        String[] startDate = daysFrom.split("/");
        String[] endDate = daysTo.split("/");
        click(By.id("dates"));
//        click(By.xpath("//div[.=' " + startDate[1] + " ']"));
//        click(By.xpath("//div[.=' " + endDate[1] + " ']"));
        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
        String LocatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
        click(By.xpath(locatorStartDate));
        click(By.xpath(LocatorEndDate));
    }

    public void selectPeriodMothsDatePicker(String daysFrom, String daysTo) {
        int fromNowToStart = 0, fromStartToEnd = 0;
        String[] startDate = daysFrom.split("/");
        String[] endDate = daysTo.split("/");
        click(By.id("dates"));
        fromStartToEnd = Integer.parseInt(endDate[0]) - Integer.parseInt(startDate[0]);
        if (LocalDate.now().getMonthValue() != Integer.parseInt(startDate[0])) {
            fromNowToStart = Integer.parseInt(startDate[0]) - LocalDate.now().getMonthValue();
        }
        for (int i = 0; i < fromNowToStart; i++) {

            click(By.xpath("//*[@aria-label='Next month']"));
            pause(1000);
        }
        String locatorStartDate = String.format("//div[.=' %s ']", startDate[1]);
        String LocatorEndDate = String.format("//div[.=' %s ']", endDate[1]);
        click(By.xpath(locatorStartDate));
        pause(1000);

        for (int i = 0; i < fromStartToEnd; i++) {

            click(By.xpath("//*[@aria-label='Next month']"));
            pause(1000);
        }
        click(By.xpath(LocatorEndDate));
        pause(2000);
    }

    public void selectPeriodYearsDatePicker(String daysFrom, String daysTo) {
        LocalDate startDate = LocalDate.parse(daysFrom, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate endDate = LocalDate.parse(daysTo, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate nowDate = LocalDate.now();
        String locatorStartDate = String.format("//div[.=' %s ']", startDate.getDayOfMonth());
        String LocatorEndDate = String.format("//div[.=' %s ']", endDate.getDayOfMonth());
        click(By.id("dates"));

        int startToEndMonth=startDate.getYear()-nowDate.getYear()==0?
        startDate.getMonthValue()-nowDate.getMonthValue():
                12-nowDate.getMonthValue()+startDate.getMonthValue();

        for (int i = 0; i < startToEndMonth; i++) {

            click(By.xpath("//*[@aria-label='Next month']"));
            pause(1000);
        }
        click(By.xpath(locatorStartDate));
        startToEndMonth =endDate.getYear()-startDate.getYear()==0 ?
        endDate.getMonthValue()-startDate.getMonthValue():
                12 - startDate.getMonthValue()+endDate.getMonthValue();


        for (int i = 0; i < startToEndMonth; i++) {

            click(By.xpath("//*[@aria-label='Next month']"));
            pause(1000);
        }

        click(By.xpath(LocatorEndDate));
    }



    public void fillSearchForm(String city, String from, String to) {
        fillCity(city);
        selectPeriodDays(from, to);
        click(By.xpath("//button[@type='submit']"));
    }
//12/10/2023 - 12/22/2023


    public void fillSearchFormPeriodDaysDatePicker(String city,String from, String to) {
        fillCity(city);

        selectPeriodDaysDatePicker(from, to);
//        click(By.id("dates"));
//        isFormSearchDatePresent();
////        click(By.xpath("//td[@aria-label ='December 14, 2023']"));
////        click(By.xpath("//td[@aria-label ='December 24, 2023']"));
        click(By.xpath("//button[@type='submit']"));
    }

    public void fillSearchForm3() {
        fillCity("Tel Aviv");
        click(By.id("dates"));
        isFormSearchDatePresent();
        click(By.xpath("//*[@aria-label='Next month']"));
        click(By.xpath("//td[@aria-label ='January 5, 2024']"));
        click(By.xpath("//td[@aria-label ='January 15, 2024']"));
        click(By.xpath("//button[@type='submit']"));
    }

    public void fillSearchFormPeriodYearsDate(String city, String from, String to) {
        fillCity(city);
        selectPeriodYearsDatePicker(from,to);
        click(By.xpath("//button[@type='submit']"));
    }

    public boolean isCarPresent() {
        return isElementPresent(By.xpath("//span[text()='Chevrolet Comaro']"));

    }

    public boolean isFormSearchDatePresent() {
        return isElementPresent(By.xpath("//*[@id='sat-datepicker-0']"));


    }


}


//input[@id='city']
//input[@id='dates']

//button[@type='submit']

//12/8/2023 - 12/22/2023