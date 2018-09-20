package lt.vianet.extractor.io;

import lt.vianet.extractor.cleaning_process.CleanWebDomain;
import lt.vianet.extractor.flight_class.Flight;
import lt.vianet.extractor.page_adapters.WebPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveHTMLData {
    final private static String DATA_FOLDER_NAME = "extraction_data";
    private static String NAME_OF_SAVE_FILE;
    private WebPage webPage;
    private Flight flight;

    public SaveHTMLData() {
    }

    public SaveHTMLData(WebPage webPage) {
        this.webPage = webPage;
    }

    public SaveHTMLData(Flight flight) {
        this.flight = flight;
    }
    public SaveHTMLData(WebPage webPage, Flight flight) {
        this.webPage = webPage;
        this.flight = flight;
    }

    public void saveHTML() {
        saveHTMLtoFile();
    }

    public void saveData() {
        saveDatatoFile();
    }


    private void saveHTMLtoFile() {

        createDirectory();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
        String day_date = sdf.format(new Date());

        String pageName = new CleanWebDomain().getCleanWebDomain(webPage.getPageLink());

        NAME_OF_SAVE_FILE = "data_" + day_date + "_html_" + pageName + ".txt";

        try {
            FileWriter writer = new FileWriter(DATA_FOLDER_NAME + File.separator + NAME_OF_SAVE_FILE, false);

            if (webPage.getHTML() != null) {
                writer.write(webPage.getHTML());
            }
            //	Close writing to File
            writer.close();
        } catch (IOException e) {
            //TODO change Exception sout to log
            System.out.println(e.getMessage());
        }
    }


    private void saveDatatoFile() {

        createDirectory();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
        String day_date = sdf.format(new Date());

        String pageName = new CleanWebDomain().getCleanWebDomain(webPage.getPageLink());

        NAME_OF_SAVE_FILE = "data_" + day_date + "_flight_" + pageName + ".txt";

        writeDatatoFile(flight);
    }


    private void writeDatatoFile(Flight flight) {
        try {
            FileWriter writer = new FileWriter(DATA_FOLDER_NAME + File.separator + NAME_OF_SAVE_FILE, true);

            if (flight.getDepartureMonth() != null && flight.getDepartureDay() != null) {
                writer.write("Depature Date: " + flight.getDepartureMonth() + flight.getDepartureDay());
            }

            if (flight.getDepartureAirport() != null && flight.getArrivalAirport() != null) {
                writer.write(" -- Depature Airport: " + flight.getDepartureAirport() + " -- Arrival Airport: " + flight.getArrivalAirport());
            }

            if (flight.getDepartureTime() != null) {
                writer.write(" -- Depature Time: " + flight.getDepartureTime());
            }
            if (flight.getArrivalTime() != null) {
                writer.write(" -- Arrival Time: " + flight.getArrivalTime());
            }
            if (flight.getCheapestPrice() > 0) {
                writer.write("  -- Flight Price (USD): " + flight.getCheapestPrice());
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
