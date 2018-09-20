package lt.vianet.extractor.flight_class;

public class Flight implements IFlight {
    private String departureAirport;
    private String arrivalAirport;
    private String departureMonth;
    private String departureDay;
    private String arrivalDate;
    private String connectionAirport;
    private String departureTime;
    private String arrivalTime;
    private String boundTag;
    private double cheapestPrice;
    private String flightNumber;

    public Flight() {
    }

    public Flight(String departureAirport, String arrivalAirport, String departureMonth, String departureDay, String flightNumber) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureMonth = departureMonth;
        this.departureDay = departureDay;
        this.flightNumber = flightNumber;
    }


    @Override
    public String getDepartureAirport() {
        return departureAirport;
    }

    @Override
    public String getArrivalAirport() {
        return arrivalAirport;
    }

    @Override
    public String getDepartureMonth() {
        return departureMonth;
    }

    @Override
    public String getDepartureDay() {
        return departureDay;
    }

    @Override
    public String getFlightNumber() {
        return flightNumber;
    }

    @Override
    public String getArrivalDate() {
        return arrivalDate;
    }

    @Override
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    @Override
    public String getConnectionAirport() {
        return connectionAirport;
    }

    @Override
    public void setConnectionAirport(String connectionAirport) {
        this.connectionAirport = connectionAirport;
    }

    @Override
    public String getDepartureTime() {
        return departureTime;
    }

    @Override
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    @Override
    public String getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String getBoundTag() {
        return boundTag;
    }

    @Override
    public void setBoundTag(String boundTag) {
        this.boundTag = boundTag;
    }

    @Override
    public double getCheapestPrice() {
        return cheapestPrice;
    }

    @Override
    public void setCheapestPrice(double cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }

}
