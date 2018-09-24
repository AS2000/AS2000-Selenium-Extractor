package lt.vianet.extractor.extraction;

import lt.vianet.extractor.flight_class.FlightFlysasCom;
import lt.vianet.extractor.page_adapters.WebPage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.apache.http.protocol.HttpDateGenerator.GMT;


public class FlysasComDataExtraction {
    private WebPage webPage;
    private String departureAirport;
    private String arrivalAirport;
    private String dayForward;
    private String dayReturn;

    public FlysasComDataExtraction() {
    }

    public FlysasComDataExtraction(WebPage webPage) {

        this.webPage = webPage;
    }

    public FlysasComDataExtraction(WebPage webPage, String departureAirport, String arrivalAirport, String dayForward, String dayReturn) {

        this.webPage = webPage;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.dayForward = dayForward;
        this.dayReturn = dayReturn;
    }


    public void getFlightsData() {

        extractFlightData();
    }


    private void extractFlightData() {

        getFlightObjectList();


    }

    private void getFlightObjectList() {

        List<FlightFlysasCom> flightList = new ArrayList<FlightFlysasCom>();
        StringBuffer buffer = new StringBuffer();
        buffer.append(webPage.getHTML());

        DateFormat flyghtDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.UK);
        flyghtDateFormat.setTimeZone(GMT);

        String departureAirportFromPage;
        String arrivalAirportFromPage;

        Date departureDate = null;
        Date arrivalDate = null;

        int flightObjectMark = 1;

        while ((flightObjectMark = buffer.indexOf("var segment = new Object();", flightObjectMark)) > 0 && flightObjectMark < buffer.length()){

            departureAirportFromPage = buffer.substring((flightObjectMark = buffer.indexOf("segment.bLocation   = \"", flightObjectMark) + 23), (flightObjectMark += 3));
            arrivalAirportFromPage = buffer.substring((flightObjectMark = buffer.indexOf("segment.eLocation   = \"", flightObjectMark) + 23), (flightObjectMark += 3));

            try {
                departureDate = flyghtDateFormat.parse(buffer.substring((flightObjectMark = buffer.indexOf("segment.bDate = \"", flightObjectMark) + 17), (flightObjectMark += 28)));
                arrivalDate = flyghtDateFormat.parse(buffer.substring((flightObjectMark = buffer.indexOf("segment.eDate =\t\"", flightObjectMark) + 17), (flightObjectMark += 28)));
            } catch (ParseException pe){
                System.out.println("You catched Parse Exception: " + pe);
            }
            flightList.add(new FlightFlysasCom(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate));
        }
        System.out.println("Objects Qty.: " + flightList.size());


    }

}
