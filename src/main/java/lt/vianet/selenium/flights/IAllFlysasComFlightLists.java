package lt.vianet.selenium.flights;

import java.util.List;

public interface IAllFlysasComFlightLists {

    public List<IFlight> getDirectFlightListForward();

    public void setDirectFlightListForward(List<IFlight> directFlightListForward);

    public List<IFlight> getDirectFlightListReturn();

    public void setDirectFlightListReturn(List<IFlight> directFlightListReturn);

    public List<IFlightList> getConnectedFlightListForward();

    public void setConnectedFlightListForward(List<IFlightList> connectedFlightListForward);

    public List<IFlightList> getConnectedFlightListReturn();

    public void setConnectedFlightListReturn(List<IFlightList> connectedFlightListReturn);
}
