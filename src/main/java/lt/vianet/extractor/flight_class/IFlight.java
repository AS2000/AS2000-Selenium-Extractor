package lt.vianet.extractor.flight_class;


import java.util.Date;

public interface IFlight {

    public void setDepartureAirport(String departureAirport);

    public String getDepartureAirport();

    public void setArrivalAirport(String arrivalAirport);

    public String getArrivalAirport();

    public String getFlightNumber();

    public String getConnectionAirport();

    public void setConnectionAirport(String connectionAirport);

    public String getBoundTag();

    public void setBoundTag(String arrivalTime);

    public double getCheapestPrice();

    public void setCheapestPrice(double cheapestPrice);
}
