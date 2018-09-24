package lt.vianet.extractor.flight_class;


import java.util.Date;

public interface IFlightFlysasCom extends IFlight {

    public Date getDepartureDate();

    public void setDepartureDate(Date departureDate);

    public Date getArrivalDate();

    public void setArrivalDate(Date arrivalDate);

    public void setTax(double tax);

    public double getTax();

    public void setPriceWithoutTax(double priceWitoutTax);

    public double getPriceWithoutTax();

    public void setFee(double fee);

    public double getFee();






}
