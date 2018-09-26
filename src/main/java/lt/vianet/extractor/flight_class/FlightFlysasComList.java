package lt.vianet.extractor.flight_class;

import java.util.ArrayList;
import java.util.List;

public class FlightFlysasComList implements IFlightFlysasComList {

    private int listID;
    private String bookingClass;
    private double cheapestPrice;
    private double tax;
    private double priceWitoutTax;
    private double fee;

    private List<FlightFlysasCom> flightList = new ArrayList<FlightFlysasCom>();

    // Constructor without price
    public FlightFlysasComList(int listID, String bookingClass, FlightFlysasCom flight) {
        this.listID = listID;
        this.bookingClass = bookingClass;
        this.flightList.add(flight);
    }

    // Constructor with price
    public FlightFlysasComList(int listID, String bookingClass, FlightFlysasCom flight, double cheapestPrice, double tax, double priceWitoutTax, double fee) {
        this.listID = listID;
        this.bookingClass = bookingClass;
        this.flightList.add(flight);
        this.cheapestPrice = cheapestPrice;
        this.tax = tax;
        this.priceWitoutTax = priceWitoutTax;
        this.fee = fee;
    }


    public int getListID() {
        return listID;
    }

    public String getBookingClass() {
        return bookingClass;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public List<FlightFlysasCom> getFlightsList() {
        return flightList;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getPriceWithoutTax() {
        return priceWitoutTax;
    }

    public void setPriceWithoutTax(double priceWitoutTax) {
        this.priceWitoutTax = priceWitoutTax;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(double cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
    }


}
