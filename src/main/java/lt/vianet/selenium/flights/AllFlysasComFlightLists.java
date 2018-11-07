package lt.vianet.selenium.flights;

import java.util.ArrayList;
import java.util.List;

public class AllFlysasComFlightLists implements IAllFlysasComFlightLists {

    private List<IFlight> flightList = new ArrayList<IFlight>();


    private List<IFlight> directFlightListForward;
    private List<IFlight> directFlightListReturn;
    private List<IFlightList> connectedFlightListForward;
    private List<IFlightList> connectedFlightListReturn;


    public List<IFlight> getDirectFlightListForward() {
        return directFlightListForward;
    }

    public void setDirectFlightListForward(List<IFlight> directFlightListForward) {
        this.directFlightListForward = directFlightListForward;
    }

    public List<IFlight> getDirectFlightListReturn() {
        return directFlightListReturn;
    }

    public void setDirectFlightListReturn(List<IFlight> directFlightListReturn) {
        this.directFlightListReturn = directFlightListReturn;
    }


    public List<IFlightList> getConnectedFlightListForward() {
        return connectedFlightListForward;
    }

    public void setConnectedFlightListForward(List<IFlightList> connectedFlightListForward) {
        this.connectedFlightListForward = connectedFlightListForward;
    }

    public List<IFlightList> getConnectedFlightListReturn() {
        return connectedFlightListReturn;
    }

    public void setConnectedFlightListReturn(List<IFlightList> connectedFlightListReturn) {
        this.connectedFlightListReturn = connectedFlightListReturn;
    }
}
