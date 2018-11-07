package lt.vianet.selenium.flights;

import java.util.ArrayList;
import java.util.List;

public class FlightList implements IFlightList {

    private int listID;
    private String bookingClass;
    private double cheapestPrice;
    private double tax;
    private double priceWitoutTax;
    private double fee;

    private List<IFlight> flightList = new ArrayList<IFlight>();

    // Constructor without price
    public FlightList(int listID, String bookingClass, Flight flight) {
        this.listID = listID;
        this.bookingClass = bookingClass;
        this.flightList.add(flight);
    }

    // Constructor with price
    public FlightList(int listID, String bookingClass, Flight flight, double cheapestPrice, double tax, double priceWitoutTax, double fee) {
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

    public List<IFlight> getFlightsList() {
        return flightList;
    }


    public double getCheapestPrice() {
        return cheapestPrice;
    }

    public void setCheapestPrice(double cheapestPrice) {
        this.cheapestPrice = cheapestPrice;
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



}
