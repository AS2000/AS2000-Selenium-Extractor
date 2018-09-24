package lt.vianet.extractor.flight_class;


public interface IFlight {

    public String getDepartureAirport();

    public void setDepartureAirport(String departureAirport);

    public String getArrivalAirport();

    public void setArrivalAirport(String arrivalAirport);

    public String getFlightNumber();

    public String getConnectionAirport();

    public void setConnectionAirport(String connectionAirport);

    public String getBoundTag();

    public void setBoundTag(String arrivalTime);

    public double getCheapestPrice();

    public void setCheapestPrice(double cheapestPrice);
}
