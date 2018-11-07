package lt.vianet.selenium.flights;


import java.util.Date;

public interface IFlight {

    public String getDepartureAirport();

    public void setDepartureAirport(String departureAirport);

    public String getArrivalAirport();

    public void setArrivalAirport(String arrivalAirport);


    public Date getDepartureDate();

    public void setDepartureDate(Date departureDate);

    public Date getArrivalDate();

    public void setArrivalDate(Date arrivalDate);


    public double getCheapestPrice();

    public void setCheapestPrice(double cheapestPrice);

    public double getTax();

    public void setTax(double tax);

    public double getPriceWithoutTax();

    public void setPriceWithoutTax(double priceWitoutTax);

    public double getFee();

    public void setFee(double fee);


}















/*

public interface IFlight {



    public String getFlightNumber();

    public String getConnectionAirport();

    public void setConnectionAirport(String connectionAirport);

    public String getBoundTag();

    public void setBoundTag(String arrivalTime);


}
*/
