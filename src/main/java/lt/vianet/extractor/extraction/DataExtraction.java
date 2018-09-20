package lt.vianet.extractor.extraction;

import lt.vianet.extractor.flight_class.Flight;
import lt.vianet.extractor.page_adapters.WebPage;

import static lt.vianet.extractor.Actions.Actions.CURRANCY;

public class DataExtraction {
    private static int START_BLOCK_MARKER;
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


        extractTime(flight);


        extractPrice(flight);


        return flight;
    }


    private void extractTime(Flight flight) {

        StringBuffer buffer = new StringBuffer();
        buffer.append(webPage.getHTML());

        String markerTag;
        START_BLOCK_MARKER = 0;
        String timeTagMarker = "";

        markerTag = "\"depdest\"";
        timeTagMarker = "<td class=" + markerTag + " title=\"" + flight.getFlightNumber() + "\">";

        // Start of search in the String
        flight.setDepartureTime(getTime(buffer, timeTagMarker));

        markerTag = "\"arrdest\"";
        timeTagMarker = "<td class=" + markerTag + ">";

        // Start of search in the String
        flight.setArrivalTime(getTime(buffer, timeTagMarker));
    }


    private String getTime(StringBuffer buffer, String timeTagMarker) {

        try {

            int startMark = buffer.indexOf(timeTagMarker, START_BLOCK_MARKER);

            if (startMark > 0 && startMark < buffer.length()) {
                int startDiv = buffer.indexOf("<div class=\"content emphasize\">", startMark);
                int endDiv = buffer.indexOf("</div", startDiv);

                if ((startDiv > 0) && (startDiv < (buffer.length() - 31)) && (endDiv < buffer.length())) {

                    START_BLOCK_MARKER = startMark;
                    return buffer.substring(startDiv + 31, endDiv);
                }
            }

        } catch (StringIndexOutOfBoundsException siobe) {
            System.out.println("You catched: " + siobe.getMessage());
        }
        return "";
    }


    private void extractPrice(Flight flight) {

        StringBuffer buffer = new StringBuffer();

        buffer.append(webPage.getHTML());

        flight.setCheapestPrice(getPrice());
    }


    private double getPrice() {

        StringBuffer buffer = new StringBuffer();

        buffer.append(webPage.getHTML());

        try {

            // Start of search in the String

            int startMark = START_BLOCK_MARKER;
            if (startMark > 0 && startMark < buffer.length()) {
                int startDiv = buffer.indexOf("title=\"" + CURRANCY + "\">", startMark);
                int endDiv = buffer.indexOf("</label", startMark);

                if ((startDiv > 0) && (startDiv < (buffer.length() - 12)) && (endDiv < buffer.length())) {
                    double price = Double.parseDouble(buffer.substring(startDiv + 12, endDiv));

                    START_BLOCK_MARKER = endDiv;
                    return price;
                }
            }
        } catch (StringIndexOutOfBoundsException siobe) {
            System.out.println("You catched: " + siobe.getMessage());
        }
        return 0;
    }
}


