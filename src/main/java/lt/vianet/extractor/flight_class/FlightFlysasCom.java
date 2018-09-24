package lt.vianet.extractor.flight_class;

import java.util.Date;

public class FlightFlysasCom extends Flight implements IFlightFlysasCom {
    private String departureAirport;
    private String arrivalAirport;
    private Date departureDate;
    private Date arrivalDate;
    private String flightNumber;
    private double tax;
    private double priceWitoutTax;
    private double fee;

    public FlightFlysasCom() {
    }

    public FlightFlysasCom(String departureAirport, String arrivalAirport, Date departureDate, Date arrivalDate) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    public FlightFlysasCom(String departureAirport, String arrivalAirport, Date departureDate, String flightNumber) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.flightNumber = flightNumber;
    }


    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }


    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    public void setPriceWithoutTax(double priceWitoutTax) {
        this.priceWitoutTax = priceWitoutTax;
    }

    public double getPriceWithoutTax() {
        return priceWitoutTax;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }
}
