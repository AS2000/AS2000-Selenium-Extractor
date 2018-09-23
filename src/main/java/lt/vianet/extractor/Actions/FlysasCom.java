package lt.vianet.extractor.Actions;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

// import org.openqa.selenium.support.ui.Select;

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

            getHTMLusingSelenium();

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

    private void getHTMLusingSelenium() {

        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver\\geckodriver.exe");

        String flightFrom = "ARN";
        String flightTo = "LHR";

        WebDriver driver = new FirefoxDriver();
        driver.get("https://classic.flysas.com/en/uk/");
        String day = "8";

//        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

        setOneWayFlight(driver);
        setDestinationFromTo(driver, flightFrom, flightTo);
        setFlightDate(driver, day);


        String idTag = "suspiciousActivity";
        waitForPageToReload(driver, idTag);

        acceptJsAlert(driver);


//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


        System.out.println("url: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());


// driver.getPageSource(); : String – This method returns the Source Code of the page. Accepts nothing as a parameter and returns a String value.
// driver.close();
    }


    private void setOneWayFlight(WebDriver driver) {

        String buttonOneWayID = "uniform-ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_ceptravelTypeSelector_oneway";

        WebElement queryFrom = driver.findElement(By.id(buttonOneWayID));
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        queryFrom.click();
    }


    private void setDestinationFromTo(WebDriver driver, String flightFrom, String flightTo) {
        String fieldFrom = "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtFrom";
        String fieldTo = "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtTo";

        WebElement queryFrom = driver.findElement(By.name(fieldFrom));
        queryFrom.sendKeys(flightFrom);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id(flightFrom)).click();

        WebElement queryTo = driver.findElement(By.name(fieldTo));
        queryTo.sendKeys(flightTo);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id(flightTo)).click();
    }


    private void setFlightDate(WebDriver driver, String day) {
// Select Month

        // Open Date Widget
        driver.findElement(By.xpath("//form//input[@class='flOutDate hasDatepicker']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Select Next Month
        driver.findElement(By.xpath("//a/span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Select Day
        driver.findElement(By.linkText(day)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Press Select Button
        driver.findElement(By.xpath("//a[@id='ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_Searchbtn_ButtonLink']")).sendKeys(Keys.RETURN);
    }


    private void waitForPageToReload(WebDriver driver, String idTag) {

        // http://qaru.site/questions/1143117/selenium-ajax-wait-if-ajax-returns-no-elements

        WebElement myDynamicElement = (new WebDriverWait(driver, 30))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.id("suspiciousActivity"));
                    }
                });
    }


    void acceptJsAlert(WebDriver driver) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();

    }

}

/*        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };

*/
