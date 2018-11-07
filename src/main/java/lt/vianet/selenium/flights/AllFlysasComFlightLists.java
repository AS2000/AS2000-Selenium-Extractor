package lt.vianet.selenium.flight_class;

import java.util.ArrayList;
import java.util.List;

public class AllFlysasComFlightLists implements IAllFlysasComFlightLists {

    private List<IFlight> flightList = new ArrayList<IFlight>();


    private List<IFlight> directFlightListForward;
    private List<IFlight> directFlightListReturn;
    private List<IFlightFlysasComList> connectedFlightListForward;
    private List<IFlightFlysasComList> connectedFlightListReturn;


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


    public List<IFlightFlysasComList> getConnectedFlightListForward() {
        return connectedFlightListForward;
    }

    public void setConnectedFlightListForward(List<IFlightFlysasComList> connectedFlightListForward) {
        this.connectedFlightListForward = connectedFlightListForward;
    }

    public List<IFlightFlysasComList> getConnectedFlightListReturn() {
        return connectedFlightListReturn;
    }

    public void setConnectedFlightListReturn(List<IFlightFlysasComList> connectedFlightListReturn) {
        this.connectedFlightListReturn = connectedFlightListReturn;
    }
}
