package lt.vianet.extractor.io;

import lt.vianet.extractor.cleaning_process.CleanWebDomain;
import lt.vianet.extractor.page_adapters.WebPage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveHTMLData {
    private WebPage webPage;

    public SaveHTMLData(){
    }

    public SaveHTMLData(WebPage webPage){
        this.webPage = webPage;
    }


    final private static String DATA_FOLDER_NAME = "data";
    private static String NAME_OF_SAVE_FILE;

    public void saveHTML() {
        saveHTMLtoFile();
    }


    private void saveHTMLtoFile() {

        createDirectory();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmm");
        String day_date = sdf.format(new Date());

        String pageName = new CleanWebDomain().getCleanWebDomain(webPage.getPageName());

        NAME_OF_SAVE_FILE = "data_" + day_date + "_" + pageName + ".txt";

        try {
            FileWriter writer = new FileWriter(DATA_FOLDER_NAME + File.separator + NAME_OF_SAVE_FILE, false);

            if (webPage.getHTML() != null) {
                writer.write(webPage.getHTML());
            }
            //	Uzdarome irasyma i faila
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
