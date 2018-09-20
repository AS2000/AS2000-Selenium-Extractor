package lt.vianet.extractor.Actions;

public class Actions {
    public static final String CURRANCY = "USD";

    public void startApp() {

        doActions();
    }

    private void doActions() {
        
        new NorwegianCom().doActions();

        new FlysasCom().doActions();
    }
}
