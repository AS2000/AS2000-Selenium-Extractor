package lt.vianet.extractor.flight_class;


public interface IFlightNorwegianCom extends IFlight {


    public String getDepartureMonth();

    public void setDepartureMonth(String departureMonth);

    public String getDepartureDay();

    public void setDepartureDay(String departureDay);

    public String getDepartureTime();

    public void setDepartureTime(String departureTime);

    public String getArrivalTime();

    public void setArrivalTime(String arrivalTime);


}
