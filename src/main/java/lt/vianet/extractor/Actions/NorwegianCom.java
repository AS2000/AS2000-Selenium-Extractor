package lt.vianet.extractor.Actions;

import lt.vianet.extractor.cleaning_process.ReplaceTabWithSpaces;
import lt.vianet.extractor.cleaning_process.TrimSpaces;
import lt.vianet.extractor.extraction.DataExtraction;
import lt.vianet.extractor.extraction.FlightQty;
import lt.vianet.extractor.extraction.GeneratePageLink;
import lt.vianet.extractor.flight_class.FlightNorwegianCom;
import lt.vianet.extractor.io.SaveHTMLData;
import lt.vianet.extractor.io.URLReader;
import lt.vianet.extractor.page_adapters.WebPage;

import java.util.ArrayList;
import java.util.List;

public class NorwegianCom {

    public void doActions() {

        getNorwegianCom();
    }

    private void getNorwegianCom() {
        String departureDay;

        String departureMonth = "201810";
        int depatureFirstDay = 1;
        int depatureLastDay = 31;

        for (int i = depatureFirstDay; i <= depatureLastDay; i++) {

            if (i < 10) {
                departureDay = "0" + i;
            } else {
                departureDay = "" + i;
            }


            WebPage webPage = new WebPage(generateLink("OSL", "RIX", departureMonth, departureDay));
            webPage.setEncoding("UTF-8");

            extractHTML(webPage);
            cleanHTML(webPage);

            checkFlightQty(webPage);


            List<FlightNorwegianCom> flightList = new ArrayList<>();

            for (int y = 0; y < webPage.getFlightNumbers().size(); y++) {

                flightList.add(createFlightObject("OSL", "RIX", departureMonth, departureDay, webPage.getFlightNumbers().get(y).toString()));
            }

            fillFlightObjectsWithData(flightList, webPage);

            for (int k = 0; k < flightList.size(); k++) {
                saveData(webPage, flightList.get(k));
            }

        }

    }

    private String generateLink(String departureAirport, String arrivalAirport, String departureMonth, String departureDay) {

        return new GeneratePageLink(departureAirport, arrivalAirport, departureMonth, departureDay).getLink();
    }


    private void extractHTML(WebPage webPage) {

        new URLReader(webPage).readThePage();
    }

    private void checkFlightQty(WebPage webPage) {

        new FlightQty(webPage).getFlightQty();
    }


    private FlightNorwegianCom createFlightObject(String departureAirport, String arrivalAirport, String departureMonth, String departureDay, String flightNumber) {

        return new FlightNorwegianCom(departureAirport, arrivalAirport, departureMonth, departureDay, flightNumber);
    }


    private void fillFlightObjectsWithData(List<FlightNorwegianCom> flightList, WebPage webPage) {

        DataExtraction dex = new DataExtraction(webPage);


        for (int z = 0; z < flightList.size(); z++) {

            dex.getData(flightList.get(z));

        }
    }


    private void cleanHTML(WebPage webPage) {

        webPage = new ReplaceTabWithSpaces(webPage).cleanTheHTML();
        new TrimSpaces(webPage).getTrimedHTML();

    }


    private void saveHTML(WebPage webPage) {

        new SaveHTMLData(webPage).saveHTML();
    }

    private void saveData(WebPage webPage, FlightNorwegianCom flight) {

        new SaveHTMLData(webPage, flight).saveData();
    }

}
