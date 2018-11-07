package lt.vianet.selenium.flights;

import java.util.List;

public interface IFlightList {

    public int getListID();

    public String getBookingClass();

    public void setListID(int listID);

    public List<IFlight> getFlightsList();


    public double getCheapestPrice();

    public void setCheapestPrice(double cheapestPrice);

    public double getTax();

    public void setTax(double tax);

    public double getPriceWithoutTax();

    public void setPriceWithoutTax(double priceWitoutTax);

    public double getFee();

    public void setFee(double fee);


}
