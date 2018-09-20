package lt.vianet.extractor.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class FlysasCom {

    public void doActions() {

        getFlysasCom();
    }

    private void getFlysasCom() {
        String departureMonth;
        String departureDay;
        int depatureFirstDay;
        int depatureLastDay;

        departureMonth = "201810";
        depatureFirstDay = 8;
        // 8 - 14
        depatureLastDay = 8;

        for (int i = depatureFirstDay; i <= depatureLastDay; i++) {

            if (i < 10) {
                departureDay = "0" + i;
            } else {
                departureDay = "" + i;
            }
// ARN (Stockholm) to LHR (London) departing 2018-10-08 and returning 2018-10-14.
// Only data for flights that are direct or have a connection at Oslo should be accepted.

            getHTMLusingSelenide();

//http://toolsqa.com/selenium-webdriver/browser-commands/


//                WebPage webPage = new WebPage(generateLink("OSL", "RIX", departureMonth, departureDay));
//                webPage.setEncoding("UTF-8");
//
//                extractHTML(webPage);
//                cleanHTML(webPage);
//
//                checkFlightQty(webPage);
//
//
//                List<Flight> flightList = new ArrayList<>();
//
//                for (int y = 0; y < webPage.getFlightNumbers().size(); y++) {
//
//                    flightList.add(createFlightObject("OSL", "RIX", departureMonth, departureDay, webPage.getFlightNumbers().get(y).toString()));
//                }
//
//                fillFlightObjectsWithData(flightList, webPage);
//
//                for (int k = 0; k < flightList.size(); k++) {
//                    saveData(webPage, flightList.get(k));
//                }

        }
    }

    private void getHTMLusingSelenide() {

        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        driver.get("https://classic.flysas.com/en/uk/");
        String fieldFrom = "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtFrom";
        String fieldTo = "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtTo";

        WebElement queryFrom = driver.findElement(By.name(fieldFrom));
        queryFrom.sendKeys("ARN");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("ARN")).click();

        WebElement queryTo = driver.findElement(By.name(fieldTo));
        queryTo.sendKeys("LHR");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("LHR")).click();


// driver.getPageSource(); : String – This method returns the Source Code of the page. Accepts nothing as a parameter and returns a String value.
// driver.close();
    }
}
