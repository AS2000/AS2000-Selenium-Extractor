package lt.vianet.extractor.flight_class;

import java.util.ArrayList;
import java.util.List;

public class AllFlysasComFlightLists implements IAllFlysasComFlightLists{

    private List<FlightFlysasCom> flightList = new ArrayList<FlightFlysasCom>();


    private List<FlightFlysasCom> directFlightListForward;
    private List<FlightFlysasCom> directFlightListReturn;
    private List<FlightFlysasComList> connectedFlightListForward;
    private List<FlightFlysasComList> connectedFlightListReturn;


    public List<FlightFlysasCom> getDirectFlightListForward() {
        return directFlightListForward;
    }

    public void setDirectFlightListForward(List<FlightFlysasCom> directFlightListForward) {
        this.directFlightListForward = directFlightListForward;
    }

    public List<FlightFlysasCom> getDirectFlightListReturn() {
        return directFlightListReturn;
    }

    public void setDirectFlightListReturn(List<FlightFlysasCom> directFlightListReturn) {
        this.directFlightListReturn = directFlightListReturn;
    }

    public List<FlightFlysasComList> getConnectedFlightListForward() {
        return connectedFlightListForward;
    }

    public void setConnectedFlightListForward(List<FlightFlysasComList> connectedFlightListForward) {
        this.connectedFlightListForward = connectedFlightListForward;
    }

    public List<FlightFlysasComList> getConnectedFlightListReturn() {
        return connectedFlightListReturn;
    }

    public void setConnectedFlightListReturn(List<FlightFlysasComList> connectedFlightListReturn) {
        this.connectedFlightListReturn = connectedFlightListReturn;
    }
}
