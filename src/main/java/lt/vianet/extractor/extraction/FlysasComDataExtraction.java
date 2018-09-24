package lt.vianet.extractor.extraction;

import lt.vianet.extractor.flight_class.FlightFlysasCom;
import lt.vianet.extractor.page_adapters.WebPage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


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
        //TODO fix problem with TimeZone: should be GMT (not EEST or EET)
        flyghtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        String departureAirportFromPage;
        String arrivalAirportFromPage;

        Date departureDate = null;
        Date arrivalDate = null;
        String closeTag = "";

        double cheapestPrice = 0.0;
        double tax = 0.0;
        double priceWitoutTax = 0.0;
        double fee = 0.0;

        int dataMark = 1;
        int dataMarkStart = 1;
        int dataMarkEnd = 1;
        String TempText = "";

        while ((dataMark = buffer.indexOf("var segment = new Object();", dataMark)) > 0 && dataMark < buffer.length()) {

            departureAirportFromPage = buffer.substring((dataMark = buffer.indexOf("segment.bLocation   = \"", dataMark) + 23), (dataMark += 3));
            arrivalAirportFromPage = buffer.substring((dataMark = buffer.indexOf("segment.eLocation   = \"", dataMark) + 23), (dataMark += 3));

            try {
                departureDate = flyghtDateFormat.parse(buffer.substring((dataMark = buffer.indexOf("segment.bDate = \"", dataMark) + 17), (dataMark += 28)));
                arrivalDate = flyghtDateFormat.parse(buffer.substring((dataMark = buffer.indexOf("segment.eDate =\t\"", dataMark) + 17), (dataMark += 28)));
                closeTag = "\'";

                cheapestPrice = Double.parseDouble(buffer.substring(((dataMark = buffer.indexOf("\'price\':\'", dataMark) + 9)), (dataMark = buffer.indexOf(closeTag, dataMark))));
                tax = Double.parseDouble(buffer.substring(((dataMark = buffer.indexOf("\'tax\':\'", dataMark) + 7)), (dataMark = buffer.indexOf(closeTag, dataMark))));
                priceWitoutTax = Double.parseDouble(buffer.substring(((dataMark = buffer.indexOf("\'priceWithoutTax\':\'", dataMark) + 19)), (dataMark = buffer.indexOf(closeTag, dataMark))));
                fee = Double.parseDouble(buffer.substring(((dataMark = buffer.indexOf("\'fee\':\'", dataMark) + 7)), (dataMark = buffer.indexOf(closeTag, dataMark))));

            } catch (ParseException pe) {
                System.out.println("You catched Parse Exception: " + pe);
            }


            flightList.add(new FlightFlysasCom(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate, cheapestPrice, tax, priceWitoutTax, fee));

        }
        System.out.println("Objects Qty.: " + flightList.size());


        printAll(flightList);

    }

    void printAll(List<FlightFlysasCom> flightList) {
        for (FlightFlysasCom flight : flightList) {
            System.out.println(flight.getDepartureAirport() + " " + flight.getArrivalAirport() + " " + flight.getDepartureDate() + " " + flight.getArrivalDate() + " " +
                    flight.getCheapestPrice() + " " + flight.getPriceWithoutTax() + " " + flight.getTax() + " " + flight.getFee());
        }
    }

}
