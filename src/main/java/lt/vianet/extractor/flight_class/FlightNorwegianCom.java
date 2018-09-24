package lt.vianet.extractor.flight_class;

public class FlightNorwegianCom extends Flight implements IFlightNorwegianCom {
    private String departureAirport;
    private String arrivalAirport;
    private String departureMonth;
    private String departureDay;
    private String departureTime;
    private String arrivalTime;
    private String flightNumber;

    public FlightNorwegianCom() {
    }

    public FlightNorwegianCom(String departureAirport, String arrivalAirport, String departureMonth, String departureDay, String flightNumber) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureMonth = departureMonth;
        this.departureDay = departureDay;
        this.flightNumber = flightNumber;
    }

    public void setDepartureMonth(String departureMonth) {
        this.departureMonth = departureMonth;
    }

    public String getDepartureMonth() {
        return departureMonth;
    }

    public void setDepartureDay(String departureDay) {
        this.departureDay = departureDay;
    }

    public String getDepartureDay() {
        return departureDay;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }
}
