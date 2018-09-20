package lt.vianet.extractor.extraction;

public class GeneratePageLink {
    private String departureAirport;
    private String arrivalAirport;
    private String departureMonth;
    private String departureDay;


    public GeneratePageLink() {
    }

    public GeneratePageLink(String departureAirport, String arrivalAirport, String departureMonth, String departureDay) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureMonth = departureMonth;
        this.departureDay = departureDay;
    }


    public String getLink() {

        return generateLink();
    }

    private String generateLink() {

        String link = "https://www.norwegian.com/us/ipc/availability/avaday?" +
                "D_City=" + departureAirport + "&A_City=" + arrivalAirport +
                //one way:
                "&TripType=1" +
                "&D_Day=" + departureDay + "&D_Month=" + departureMonth + "&D_SelectedDay=" + departureDay + "&R_Day=" +
                "&IncludeTransit=false" + "&CurrencyCode=USD";

        return link;

    }
}
