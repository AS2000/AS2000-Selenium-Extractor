package lt.vianet.selenium.extraction;

import lt.vianet.selenium.flights.*;
import lt.vianet.selenium.page_adapters.WebPage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class FlysasComDataExtraction {
    private WebPage webPage;
    private String departureAirport;
    private String arrivalAirport;
    private String connectionAirport;
    private String dayForward;
    private String dayReturn;
    AllFlysasComFlightLists allFlysasComFlightLists;

    public FlysasComDataExtraction() {
    }

    public FlysasComDataExtraction(WebPage webPage) {

        this.webPage = webPage;
    }

    public FlysasComDataExtraction(WebPage webPage, String departureAirport, String arrivalAirport, String connectionAirport, String dayForward, String dayReturn, AllFlysasComFlightLists allFlysasComFlightLists) {

        this.webPage = webPage;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.connectionAirport = connectionAirport;
        this.dayForward = dayForward;
        this.dayReturn = dayReturn;
        this.allFlysasComFlightLists = allFlysasComFlightLists;
    }


    public AllFlysasComFlightLists getFlightsData() {

        return getFlightObjectList();
    }


    private AllFlysasComFlightLists getFlightObjectList() {

        List<IFlight> directFlightListForward = new ArrayList<IFlight>();
        List<IFlight> directFlightListReturn = new ArrayList<IFlight>();
        List<IFlightList> connectedFlightListForward = new ArrayList<IFlightList>();
        List<IFlightList> connectedFlightListReturn = new ArrayList<IFlightList>();

        StringBuffer buffer = new StringBuffer();
        buffer.append(webPage.getHTML());

        StringBuffer blockBuffer = new StringBuffer();

        DateFormat flyghtDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        flyghtDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        String departureAirportFromPage;
        String arrivalAirportFromPage;

        Date departureDate = null;
        Date arrivalDate = null;
        String bookingClass = "";
        int listID = 0;
        String closeTag = "\'";

        double cheapestPrice = 0.0;
        double tax = 0.0;
        double priceWitoutTax = 0.0;
        double fee = 0.0;

        int dataMark = 1;
        int dataMarkTemp = 1;
        int connectedBlockMarkStart = 1;
        int connectedBlockMarkEnd = 1;


        while ((dataMark = buffer.indexOf("var segment = new Object();", dataMark)) > 0 && dataMark < buffer.length()) {
            try {
                bookingClass = buffer.substring((dataMark = buffer.indexOf("segment.bookingClass = \"", dataMark) + 24), (dataMark += 1));
                listID = Integer.parseInt(buffer.substring((dataMark = buffer.indexOf("segment.flightId = \"", dataMark) + 20), (dataMark = buffer.indexOf("\"", dataMark))));

                departureAirportFromPage = buffer.substring((dataMark = buffer.indexOf("segment.bLocation   = \"", dataMark) + 23), (dataMark += 3));
                arrivalAirportFromPage = buffer.substring((dataMark = buffer.indexOf("segment.eLocation   = \"", dataMark) + 23), (dataMark += 3));
                departureDate = flyghtDateFormat.parse(buffer.substring((dataMark = buffer.indexOf("segment.bDate = \"", dataMark) + 17), (dataMark += 28)));
                arrivalDate = flyghtDateFormat.parse(buffer.substring((dataMark = buffer.indexOf("segment.eDate =\t\"", dataMark) + 17), (dataMark += 28)));

                // DIRECT FORWARD Flight
                if (departureAirport.equals(departureAirportFromPage) && arrivalAirport.equals(arrivalAirportFromPage)) {

                    //This Code checks is there is price after this flight, if no -> next one
                    int priceStringMarker = buffer.indexOf("\'price\'", dataMark);

                    if (priceStringMarker < 0 || (priceStringMarker - dataMark) > 150) {
                        // try next flight
                        continue;
                    }

                    dataMarkTemp = dataMark;
                    cheapestPrice = Double.parseDouble(buffer.substring(((dataMark = buffer.indexOf("\'price\':\'", dataMark) + 9)), (dataMark = buffer.indexOf(closeTag, dataMark))));

                    // Check for Direct Flights without price, if found: skip and restore search point (dataMark)
                    if (dataMark < 0) {
                        dataMark = dataMarkTemp;
                        continue;
                    }

                    tax = Double.parseDouble(buffer.substring((dataMark = buffer.indexOf("\'tax\':\'", dataMark) + 7), (dataMark = buffer.indexOf(closeTag, dataMark))));
                    priceWitoutTax = Double.parseDouble(buffer.substring((dataMark = buffer.indexOf("\'priceWithoutTax\':\'", dataMark) + 19), (dataMark = buffer.indexOf(closeTag, dataMark))));
                    fee = Double.parseDouble(buffer.substring((dataMark = buffer.indexOf("\'fee\':\'", dataMark) + 7), (dataMark = buffer.indexOf(closeTag, dataMark))));

                    directFlightListForward.add(new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate, cheapestPrice, tax, priceWitoutTax, fee));
                    continue;
                }

                // DIRECT RETURN Flight
                if (departureAirport.equals(arrivalAirportFromPage) && arrivalAirport.equals(departureAirportFromPage)) {

                    //This Code checks is there is price after this flight, if no -> next one
                    int priceStringMarker = buffer.indexOf("\'price\':\'", dataMark);

                    if (priceStringMarker < 0 || (priceStringMarker - dataMark) > 150) {
                        // try next flight
                        continue;
                    }

                    dataMarkTemp = dataMark;
                    cheapestPrice = Double.parseDouble(buffer.substring((dataMark = buffer.indexOf("\'price\':\'", dataMark) + 9), (dataMark = buffer.indexOf(closeTag, dataMark))));

                    // Check for Direct Flights without price, if found: skip and restore search point (dataMark)
                    if (dataMark < 0) {
                        dataMark = dataMarkTemp;
                        continue;
                    }
                    tax = Double.parseDouble(buffer.substring((dataMark = buffer.indexOf("\'tax\':\'", dataMark) + 7), (dataMark = buffer.indexOf(closeTag, dataMark))));
                    priceWitoutTax = Double.parseDouble(buffer.substring((dataMark = buffer.indexOf("\'priceWithoutTax\':\'", dataMark) + 19), (dataMark = buffer.indexOf(closeTag, dataMark))));
                    fee = Double.parseDouble(buffer.substring((dataMark = buffer.indexOf("\'fee\':\'", dataMark) + 7), (dataMark = buffer.indexOf(closeTag, dataMark))));

                    directFlightListReturn.add(new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate, cheapestPrice, tax, priceWitoutTax, fee));
                    continue;
                }

                // CONNECTION Flight
                if (departureAirportFromPage.equals(connectionAirport) || arrivalAirportFromPage.equals(connectionAirport)) {
                    // Indexing Connected Flights Block
                    connectedBlockMarkStart = dataMark;
                    connectedBlockMarkEnd = buffer.indexOf("theREV_AVD_COForm.addRecommendation", connectedBlockMarkStart);

                    //cleaning in the next iterations
                    blockBuffer.setLength(0);
                    // Connected Flights Block
                    blockBuffer.append(buffer.substring(connectedBlockMarkStart, connectedBlockMarkEnd));

                    dataMark = 0;
                    cheapestPrice = Double.parseDouble(blockBuffer.substring((dataMark = blockBuffer.indexOf("\'price\':\'", dataMark) + 9), (dataMark = blockBuffer.indexOf(closeTag, dataMark))));
                    tax = Double.parseDouble(blockBuffer.substring((dataMark = blockBuffer.indexOf("\'tax\':\'", dataMark) + 7), (dataMark = blockBuffer.indexOf(closeTag, dataMark))));
                    priceWitoutTax = Double.parseDouble(blockBuffer.substring((dataMark = blockBuffer.indexOf("\'priceWithoutTax\':\'", dataMark) + 19), (dataMark = blockBuffer.indexOf(closeTag, dataMark))));
                    fee = Double.parseDouble(blockBuffer.substring((dataMark = blockBuffer.indexOf("\'fee\':\'", dataMark) + 7), (dataMark = blockBuffer.indexOf(closeTag, dataMark))));

                    //After price was taked returning Marker to the Block Beginnig to start Flight Data Extraction
                    dataMark = 1;

                    while (dataMark > 0 && dataMark < blockBuffer.length()) {
                        // CONNECTED FORWARD Flight
                        if ((departureAirport.equals(departureAirportFromPage) && connectionAirport.equals(arrivalAirportFromPage)) || (connectionAirport.equals(departureAirportFromPage) && arrivalAirport.equals(arrivalAirportFromPage))) {

                            if (connectedFlightListForward.size() != 0) {

                                for (int i = 0; i < connectedFlightListForward.size(); i++) {

                                    if (connectedFlightListForward.get(i).getListID() == listID && connectedFlightListForward.get(i).getBookingClass().equals(bookingClass)) {

                                        connectedFlightListForward.get(i).getFlightsList().add(new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate));

                                        break;

                                    } else if (i == (connectedFlightListForward.size() - 1)) {

                                        //Needed if it will be more then 2 Connected Flights
                                        connectedFlightListForward.add(new FlightList(listID, bookingClass, new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate), cheapestPrice, tax, priceWitoutTax, fee));

                                        break;
                                    }
                                }

                            } else {
                                connectedFlightListForward.add(new FlightList(listID, bookingClass, new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate), cheapestPrice, tax, priceWitoutTax, fee));
                            }
                        }

                        if ((arrivalAirport.equals(departureAirportFromPage) && connectionAirport.equals(departureAirportFromPage)) || (connectionAirport.equals(departureAirportFromPage) && departureAirport.equals(arrivalAirportFromPage))) {

                            if (connectedFlightListReturn.size() != 0) {

                                for (int i = 0; i < connectedFlightListReturn.size(); i++) {

                                    if (connectedFlightListReturn.get(i).getListID() == listID && connectedFlightListReturn.get(i).getBookingClass().equals(bookingClass)) {

                                        connectedFlightListReturn.get(i).getFlightsList().add(new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate));

                                        break;

                                    } else if (i == (connectedFlightListReturn.size() - 1)) {

                                        //Needed if it will be more then 2 Connected Flights
                                        connectedFlightListReturn.add(new FlightList(listID, bookingClass, new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate), cheapestPrice, tax, priceWitoutTax, fee));

                                        break;
                                    }
                                }

                            } else {
                                connectedFlightListReturn.add(new FlightList(listID, bookingClass, new Flight(departureAirportFromPage, arrivalAirportFromPage, departureDate, arrivalDate), cheapestPrice, tax, priceWitoutTax, fee));
                            }
                        }


                        dataMark = blockBuffer.indexOf("segment.bookingClass = \"", dataMark);

                        // Check for the end of the Block
                        if (dataMark < 0) {
                            dataMark = connectedBlockMarkEnd;
                            break;
                        }

                        bookingClass = blockBuffer.substring((dataMark += 24), (dataMark += 1));
                        listID = Integer.parseInt(blockBuffer.substring((dataMark = blockBuffer.indexOf("segment.flightId = \"", dataMark) + 20), (dataMark = blockBuffer.indexOf("\"", dataMark))));

                        departureAirportFromPage = blockBuffer.substring((dataMark = blockBuffer.indexOf("segment.bLocation   = \"", dataMark) + 23), (dataMark += 3));
                        arrivalAirportFromPage = blockBuffer.substring((dataMark = blockBuffer.indexOf("segment.eLocation   = \"", dataMark) + 23), (dataMark += 3));
                        departureDate = flyghtDateFormat.parse(blockBuffer.substring((dataMark = blockBuffer.indexOf("segment.bDate = \"", dataMark) + 17), (dataMark += 28)));
                        arrivalDate = flyghtDateFormat.parse(blockBuffer.substring((dataMark = blockBuffer.indexOf("segment.eDate =\t\"", dataMark) + 17), (dataMark += 28)));
                    }

                    dataMark = connectedBlockMarkEnd;
                }

            } catch (ParseException pe) {
                System.out.println("You catched Parse Exception: " + pe);
            }
        }


        allFlysasComFlightLists.setConnectedFlightListForward(connectedFlightListForward);
        allFlysasComFlightLists.setConnectedFlightListReturn(connectedFlightListReturn);
        allFlysasComFlightLists.setDirectFlightListForward(directFlightListForward);
        allFlysasComFlightLists.setDirectFlightListReturn(directFlightListReturn);

        return allFlysasComFlightLists;
    }

}
