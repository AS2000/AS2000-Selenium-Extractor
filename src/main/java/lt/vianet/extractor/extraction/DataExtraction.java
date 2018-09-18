package lt.vianet.extractor.extraction;

import lt.vianet.extractor.flight_class.Flight;
import lt.vianet.extractor.page_adapters.WebPage;

public class DataExtraction {
    private WebPage webPage;

    public DataExtraction() {
    }

    public DataExtraction(WebPage webPage) {
        this.webPage = webPage;
    }

    public Flight getData(Flight flight) {

        return extractDataFromHTML(flight);
    }


    private Flight extractDataFromHTML(Flight flight) {
        String markerTag;

        markerTag = "\"depdest\"";
        flight.setDepartureTime(extractTime(flight.getFlightNumber(), markerTag));

        markerTag = "\"arrdest\"";
        flight.setArrivalTime(extractTime(flight.getFlightNumber(), markerTag));

        flight.setCheapestPrice(extractPrice(flight.getFlightNumber(), markerTag));

        return flight;
    }


    private String extractTime(String boundTag, String markerTag) {

        StringBuffer buffer = new StringBuffer();

        buffer.append(webPage.getHTML());

        try {

            // Start of search in the String
            int startBlock = buffer.indexOf(boundTag, 0);

            int startMark = buffer.indexOf(markerTag, startBlock);
            if (startMark > 0 && startMark < buffer.length()) {
                int startDiv = buffer.indexOf("<div class=\"content emphasize\">", startMark);
                int endDiv = buffer.indexOf("</div", startMark);

                if ((startDiv > 0) && (startDiv < (buffer.length() - 31)) && (endDiv < buffer.length())) {
                    String time = buffer.substring(startDiv + 31, endDiv);

                    return time;
                }
            }

        } catch (StringIndexOutOfBoundsException siobe) {
            System.out.println("You catched: " + siobe.getMessage());
        }

        return "";
    }


    private double extractPrice(String boundTag, String markerTag) {

        StringBuffer buffer = new StringBuffer();

        buffer.append(webPage.getHTML());

        try {

            // Start of search in the String
            int startBlock = buffer.indexOf(boundTag, 0);

            int startMark = buffer.indexOf(markerTag, startBlock);
            if (startMark > 0 && startMark < buffer.length()) {
                int startDiv = buffer.indexOf("<label class=\"label seatsokfare\" title=\"USD\">", startMark);
                int endDiv = buffer.indexOf("</label", startMark);

                if ((startDiv > 0) && (startDiv < (buffer.length() - 45)) && (endDiv < buffer.length())) {
                    double price = Double.parseDouble(buffer.substring(startDiv + 45, endDiv));

                    return price;
                }
            }

        } catch (StringIndexOutOfBoundsException siobe) {
            System.out.println("You catched: " + siobe.getMessage());
        }

        return 0;
    }


}


