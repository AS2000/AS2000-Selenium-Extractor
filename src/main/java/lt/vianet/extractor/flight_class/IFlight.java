package lt.vianet.extractor.flight_class;


public interface IFlight {

    public String getDepartureAirport();

    public String getArrivalAirport();

    public String getDepartureMonth();

    public String getDepartureDay();

    public String getFlightNumber();

    public String getArrivalDate();

    public void setArrivalDate(String arrivalDate);

    public String getConnectionAirport();

    public void setConnectionAirport(String connectionAirport);

    public String getDepartureTime();

    public void setDepartureTime(String departureTime);

    public String getArrivalTime();

    public void setArrivalTime(String arrivalTime);

    public String getBoundTag();

    public void setBoundTag(String arrivalTime);

    public double getCheapestPrice();

    public void setCheapestPrice(double cheapestPrice);
}
