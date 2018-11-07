package lt.vianet.selenium.flight_class;

import java.util.List;

public interface IAllFlysasComFlightLists {

    public List<IFlight> getDirectFlightListForward();

    public void setDirectFlightListForward(List<IFlight> directFlightListForward);

    public List<IFlight> getDirectFlightListReturn();

    public void setDirectFlightListReturn(List<IFlight> directFlightListReturn);

    public List<IFlightFlysasComList> getConnectedFlightListForward();

    public void setConnectedFlightListForward(List<IFlightFlysasComList> connectedFlightListForward);

    public List<IFlightFlysasComList> getConnectedFlightListReturn();

    public void setConnectedFlightListReturn(List<IFlightFlysasComList> connectedFlightListReturn);
}
