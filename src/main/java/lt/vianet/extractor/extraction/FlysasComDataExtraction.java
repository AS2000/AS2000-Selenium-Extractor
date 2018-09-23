package lt.vianet.extractor.extraction;

import lt.vianet.extractor.page_adapters.WebPage;

public class FlysasComDataExtraction {
    private WebPage webPage;

    public FlysasComDataExtraction() {
    }

    public FlysasComDataExtraction(WebPage webPage) {

        this.webPage = webPage;
    }


    public void getFlightsData() {

        extractFlightData();
    }


    private void extractFlightData() {

        getFlightObjectList();




    }

    private void getFlightObjectList() {



    }

}
