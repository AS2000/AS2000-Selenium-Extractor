package lt.vianet.extractor.Actions;

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
        depatureFirstDay = 1;
        depatureLastDay = 31;

        for (int i = depatureFirstDay; i <= depatureLastDay; i++) {

            if (i < 10) {
                departureDay = "0" + i;
            } else {
                departureDay = "" + i;
            }

//
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
}
