package lt.vianet.selenium.actions;

import lt.vianet.selenium.extraction.FlysasComDataExtraction;
import lt.vianet.selenium.flights.AllFlysasComFlightLists;
import lt.vianet.selenium.io.SaveDataFlysasCom;
import lt.vianet.selenium.page_adapters.WebPage;
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

        String departureAirport = "ARN";
        String arrivalAirport = "LHR";
        String connectionAirport = "OSL";

        String pageURL = "https://classic.flysas.com/en/uk/";
        String dayForward = "8";
        String dayReturn = "14";

        //All Flight List
        AllFlysasComFlightLists allFlysasComFlightLists = new AllFlysasComFlightLists();

// ARN (Stockholm) to LHR (London) departing 08 d. and returning 14 d.
// Only data for flights that are direct or have a connection at Oslo should be accepted.

        WebPage webPage = new WebPage("https://book.flysas.com/");

        // returns to the web page scan
        webPage.setHTML(getHTMLusingSelenium(departureAirport, arrivalAirport, pageURL, dayForward, dayReturn));

        // loads Data from File (for tests)
//        webPage.setHTML(new FlysasComPageDataFile().getFlysasComPageData());

        webPage.setEncoding("utf-8");

        allFlysasComFlightLists = fillFlysasData(webPage, departureAirport, arrivalAirport, connectionAirport, dayForward, dayReturn, allFlysasComFlightLists);

        //Save Data to File
        new SaveDataFlysasCom(allFlysasComFlightLists).saveData();
    }


    private String getHTMLusingSelenium(String departureAirport, String arrivalAirport, String pageURL, String dayForward, String dayReturn) {

        //http://toolsqa.com/selenium-webdriver/browser-commands/
        System.setProperty("webdriver.gecko.driver", "C:\\Program Files\\geckodriver\\geckodriver.exe");

        WebDriver driver = new FirefoxDriver();
        driver.get(pageURL);

//        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

        Boolean oneWayFlight;

        if (dayReturn.equals("0")) {

            oneWayFlight = true;

        } else {
            oneWayFlight = false;
        }


        if (oneWayFlight == true) {

            setOneWayFlight(driver);
            setDestinationFromTo(driver, departureAirport, arrivalAirport);
            setDestinationFromTo(driver, departureAirport, arrivalAirport);

        } else {
            setRoundFlight(driver);
            setDestinationFromTo(driver, departureAirport, arrivalAirport);
            setFlightDate(driver, dayForward, dayReturn);
        }


        String idTag = "suspiciousActivity";
        waitForPageToReload(driver, idTag);

        acceptJsAlert(driver);

        idTag = "ECONBGRules";
        waitForPageToReload(driver, idTag);

        String pageHTML = getHTMLfromPage(driver);

//        driver.close();

        return pageHTML;
    }


    private void setOneWayFlight(WebDriver driver) {

        String buttonOneWayID = "uniform-ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_ceptravelTypeSelector_oneway";

        WebElement queryFrom = driver.findElement(By.id(buttonOneWayID));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        queryFrom.click();
    }


    private void setRoundFlight(WebDriver driver) {

        String buttonRoundTripID = "uniform-ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_ceptravelTypeSelector_roundtrip";

        WebElement queryFrom = driver.findElement(By.id(buttonRoundTripID));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        queryFrom.click();
    }


    private void setDestinationFromTo(WebDriver driver, String flightFrom, String flightTo) {
        String fieldFrom = "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtFrom";
        String fieldTo = "ctl00$FullRegion$MainRegion$ContentRegion$ContentFullRegion$ContentLeftRegion$CEPGroup1$CEPActive$cepNDPRevBookingArea$predictiveSearch$txtTo";

        WebElement queryFrom = driver.findElement(By.name(fieldFrom));
        queryFrom.sendKeys(flightFrom);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.findElement(By.id(flightFrom)).click();

        WebElement queryTo = driver.findElement(By.name(fieldTo));
        queryTo.sendKeys(flightTo);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
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

        // Press SEARCH Button
        driver.findElement(By.xpath("//a[@id='ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_Searchbtn_ButtonLink']")).sendKeys(Keys.RETURN);
    }


    private void setFlightDate(WebDriver driver, String dayForward, String dayReturn) {
// Select Month

//OUTWARD: (Forward):
        // Open Date Widget
        driver.findElement(By.xpath("//form//input[@class='flOutDate hasDatepicker']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Select Next Month
        driver.findElement(By.xpath("//a/span[@class='ui-icon ui-icon-circle-triangle-e']")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Select Day
        driver.findElement(By.linkText(dayForward)).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//CLICKING: <div id="ui-datepicker-div" - to release the Return Date Widget

        WebElement elem2 = driver.findElement(By.xpath("//div[@class='ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all ui-helper-hidden-accessible']"));
        elem2.click();

//RETURN:
        // Open Date Widget
        driver.findElement(By.xpath("//form//input[@class='flInDate hasDatepicker']")).click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        // Select Day
        driver.findElement(By.linkText(dayReturn)).click();
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        // Press SEARCH Button
        driver.findElement(By.xpath("//a[@id='ctl00_FullRegion_MainRegion_ContentRegion_ContentFullRegion_ContentLeftRegion_CEPGroup1_CEPActive_cepNDPRevBookingArea_Searchbtn_ButtonLink']")).sendKeys(Keys.RETURN);
    }

//WAIT for page to reload
    private void waitForPageToReload(WebDriver driver, String idTag) {

        // http://qaru.site/questions/1143117/selenium-ajax-wait-if-ajax-returns-no-elements

        WebElement myDynamicElement = (new WebDriverWait(driver, 30))
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.id(idTag));
                    }
                });
    }

// Accept Alert Message
    void acceptJsAlert(WebDriver driver) {
        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();
        alert.accept();

    }


    private String getHTMLfromPage(WebDriver driver) {

        return driver.getPageSource();
    }


    private AllFlysasComFlightLists fillFlysasData(WebPage webPage, String departureAirport, String arrivalAirport, String connectionAirport, String dayForward, String dayReturn, AllFlysasComFlightLists allFlysasComFlightLists) {

        return (new FlysasComDataExtraction(webPage, departureAirport, arrivalAirport, connectionAirport, dayForward, dayReturn, allFlysasComFlightLists).getFlightsData());
    }
}

