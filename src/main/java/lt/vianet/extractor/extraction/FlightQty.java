package lt.vianet.extractor.extraction;

import lt.vianet.extractor.page_adapters.WebPage;

import java.util.ArrayList;
import java.util.List;

public class FlightQty {
    private WebPage webPage;

    public FlightQty() {
    }

    public FlightQty(WebPage webPage) {

        this.webPage = webPage;
    }


    public void getFlightQty() {

        calculateFlightQty();
    }

    private void calculateFlightQty() {

        StringBuffer buffer = new StringBuffer();
        buffer.append(webPage.getHTML());

        List<String> flightNumbers = new ArrayList<>();

        String markerTag = "<td class=\"depdest\" title=\"";
        String markerEndTag = "\">";
        String flightNumber = "";

        int startMark = buffer.indexOf(markerTag, 0);
        int endMark = buffer.indexOf(markerEndTag, startMark);
        while (startMark > 0 && startMark < buffer.length() && endMark < buffer.length()) {


            if (startMark > 0 && startMark < buffer.length() && endMark < buffer.length()) {

                flightNumber = buffer.substring(startMark + 27, endMark);

                if (flightNumbers.size() == 0) {
                    flightNumbers.add(flightNumber);
                }

                if (flightNumbers.size() > 0 && (!flightNumbers.get(flightNumbers.size() - 1).equals(flightNumber))) {
                    flightNumbers.add(flightNumber);
                }


            }
            startMark = buffer.indexOf(markerTag, startMark + 27);
            endMark = buffer.indexOf(markerEndTag, startMark);
        }
        webPage.setFlightNumbers(flightNumbers);
    }
}
