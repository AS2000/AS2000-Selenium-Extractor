package lt.vianet.extractor.io;

import lt.vianet.extractor.cleaning_process.CleanWebDomain;
import lt.vianet.extractor.flight_class.AllFlysasComFlightLists;
import lt.vianet.extractor.page_adapters.WebPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveDataFlysasCom {
    final private static String DATA_FOLDER_NAME = "extraction_data";
    private static String NAME_OF_SAVE_FILE;
    private WebPage webPage;
    AllFlysasComFlightLists allFlysasComFlightLists;

    public SaveDataFlysasCom() {
    }

    public SaveDataFlysasCom(AllFlysasComFlightLists allFlysasComFlightLists) {
        this.allFlysasComFlightLists = allFlysasComFlightLists;
    }

    public SaveDataFlysasCom(WebPage webPage) {
        this.webPage = webPage;
    }


    public void saveData() {
        saveDatatoFile();
    }


    private void saveDatatoFile() {

        createDirectory();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
        String day_date = sdf.format(new Date());

        String pageName = "FlysasCom";

        NAME_OF_SAVE_FILE = "data_" + day_date + "_FlySas.com_Flights_" + pageName + ".txt";

        writeDatatoFile();
    }


    private void writeDatatoFile() {
        try {
            FileWriter writer = new FileWriter(DATA_FOLDER_NAME + File.separator + NAME_OF_SAVE_FILE, true);


            for (int i = 0; i < allFlysasComFlightLists.getDirectFlightListForward().size(); i++) {

                String flightString = ""
                        + allFlysasComFlightLists.getDirectFlightListForward().get(i).getCheapestPrice()
                        + "  " + allFlysasComFlightLists.getDirectFlightListForward().get(i).getPriceWithoutTax()
                        + "  " + allFlysasComFlightLists.getDirectFlightListForward().get(i).getTax()
                        + "  " + allFlysasComFlightLists.getDirectFlightListForward().get(i).getFee()
                        + "  " + allFlysasComFlightLists.getDirectFlightListForward().get(i).getDepartureAirport()
                        + "  " + allFlysasComFlightLists.getDirectFlightListForward().get(i).getDepartureDate()
                        + "  " + allFlysasComFlightLists.getDirectFlightListForward().get(i).getArrivalAirport()
                        + "  " + allFlysasComFlightLists.getDirectFlightListForward().get(i).getArrivalDate();

                writer.write("  Direct Flight Forward: " + flightString);
            }


            for (int i = 0; i < allFlysasComFlightLists.getDirectFlightListReturn().size(); i++) {

                String flightString = ""
                        + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getCheapestPrice()
                        + "  " + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getPriceWithoutTax()
                        + "  " + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getTax()
                        + "  " + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getFee()
                        + "  " + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getDepartureAirport()
                        + "  " + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getDepartureDate()
                        + "  " + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getArrivalAirport()
                        + "  " + allFlysasComFlightLists.getDirectFlightListReturn().get(i).getArrivalDate();

                writer.write("  Direct Flight Return: " + flightString + "\n");
            }


            for (int i = 0; i < allFlysasComFlightLists.getConnectedFlightListForward().size(); i++) {

                String flightString = ""
                        + "  Connected Flight Price: "
                        + allFlysasComFlightLists.getConnectedFlightListForward().get(i).getCheapestPrice()
                        + "  " + allFlysasComFlightLists.getConnectedFlightListForward().get(i).getPriceWithoutTax()
                        + "  " + allFlysasComFlightLists.getConnectedFlightListForward().get(i).getTax()
                        + "  " + allFlysasComFlightLists.getConnectedFlightListForward().get(i).getFee();
                writer.write("  Connected Flight Forward:" + flightString);
//                flightString = "First Flight: " + allFlysasComFlightLists.getConnectedFlightListForward().get(i).getFlightsList().get(0)
//                        + "Second Flight: " + allFlysasComFlightLists.getConnectedFlightListForward().get(i).getFlightsList().get(1)
//                        + "";


                writer.write("Connected Flight Forward:" + flightString + "");
            }



            for (int i = 0; i < allFlysasComFlightLists.getConnectedFlightListReturn().size(); i++) {

                String flightString = ""
                        + "  Connected Flight Price: "
                        + allFlysasComFlightLists.getConnectedFlightListReturn().get(i).getCheapestPrice()
                        + "  " + allFlysasComFlightLists.getConnectedFlightListReturn().get(i).getPriceWithoutTax()
                        + "  " + allFlysasComFlightLists.getConnectedFlightListReturn().get(i).getTax()
                        + "  " + allFlysasComFlightLists.getConnectedFlightListReturn().get(i).getFee();

                writer.write("  Connected Flight Forward:" + flightString);
//                flightString = "First Flight: " + allFlysasComFlightLists.getConnectedFlightListReturn().get(i).getFlightsList().get(0).toString()
//                        + "Second Flight: " + allFlysasComFlightLists.getConnectedFlightListReturn().get(i).getFlightsList().get(1).toString()
//                        + "";

                writer.write("  Connected Flight Forward: " + flightString + "");
            }

            writer.write(System.lineSeparator());

            //	Close writing to File
            writer.close();
        } catch (IOException e) {
            //TODO change Exception sout to log
            System.out.println(e.getMessage());
        }
    }


    private void createDirectory() {
        // ========= Patikrinimas ar yra sukurtas duomenu saugojimo katalogas, jei ne - sukuriamas =========

        try {
            File directory = new File(DATA_FOLDER_NAME);

            // Patikrinimas ar yra backup katalogas
            if (!directory.exists()) {

                // Sukuriame direktorija
                directory.mkdir();
            }
            // Patikriname ar yra Isimtis
        } catch (SecurityException se) {
            //TODO change Exception sout to log
            System.out.println(se.getMessage());
        }
    }


}
