package lt.vianet.extractor.flight_class;

public class Flight implements IFlight {
    private String departureAirport;
    private String arrivalAirport;
    private String connectionAirport;
    private String boundTag;
    private double cheapestPrice;
    private String flightNumber;

    public Flight() {
    }

    public Flight(String departureAirport, String arrivalAirport, String flightNumber) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.flightNumber = flightNumber;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getConnectionAirport() {
        return connectionAirport;
    }

    public void setConnectionAirport(String connectionAirport) {
        this.connectionAirport = connectionAirport;
    }

    public String getBoundTag() {
        return boundTag;
    }

    public void setBoundTag(String boundTag) {
        this.boundTag = boundTag;
    }

    public double getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(double cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }

}
