package lt.vianet.extractor.flight_class;

import java.util.List;

public interface IAllFlysasComFlightLists {

    public List<FlightFlysasCom> getDirectFlightListForward();

    public void setDirectFlightListForward(List<FlightFlysasCom> directFlightListForward);

    public List<FlightFlysasCom> getDirectFlightListReturn();

    public void setDirectFlightListReturn(List<FlightFlysasCom> directFlightListReturn);

    public List<FlightFlysasComList> getConnectedFlightListForward();

    public void setConnectedFlightListForward(List<FlightFlysasComList> connectedFlightListForward);

    public List<FlightFlysasComList> getConnectedFlightListReturn();

    public void setConnectedFlightListReturn(List<FlightFlysasComList> connectedFlightListReturn);
}
