package lt.vianet.extractor.flight_class;


public interface IFlight {

    public String getDepartureAirport();
    public String getArrivalAirport();

    public String getDepartureMonth();
    public String getDepartureDay();
    public String getFlightNumber();

    public void setArrivalDate(String arrivalDate);
    public String getArrivalDate();

    public void setConnectionAirport(String connectionAirport);
    public String getConnectionAirport();

    public void setDepartureTime(String departureTime);
    public String getDepartureTime();

    public void setArrivalTime(String arrivalTime);
    public String getArrivalTime();

    public void setBoundTag(String arrivalTime);
    public String getBoundTag();

    public void setCheapestPrice(double cheapestPrice);
    public double getCheapestPrice();
}
