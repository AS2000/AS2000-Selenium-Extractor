package lt.vianet.extractor.flight_class;


public interface IFlightNorwegianCom extends IFlight {


    public void setDepartureMonth(String departureMonth);

    public String getDepartureMonth();

    public void setDepartureDay(String departureDay);

    public String getDepartureDay();

    public void setDepartureTime(String departureTime);

    public String getDepartureTime();

    public void setArrivalTime(String arrivalTime);

    public String getArrivalTime();



}
