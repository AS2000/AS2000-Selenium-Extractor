package lt.vianet.extractor.io;

import java.io.*;

public class FlysasComPageDataFile {


    public String getFlysasComPageData() {

        return loadList();
    }

    // --------- Duomenu ATKURIMAS ---------

    private String loadList() {

        String FILE_NAME = "";

        try {
            File jarFile = new File("");
            FILE_NAME += "" + jarFile.getAbsolutePath() + File.separator + "WEB_html_TwoWay_08-14.txt";
            File file = new File(FILE_NAME);

            // Patikrinimas ar failas egzistuoja
            if (file.exists()) {

            } else {
                // jei failas neegzituoja, tai yra sukuriamas, kad nekiltu klaidos


                System.out.println(" Failas nerastas!");
//                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        StringBuilder htmlString = new StringBuilder();

        try {

            FileInputStream is = new FileInputStream(FILE_NAME);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "utf-8"));

            // Perkeliam visus irasus i ArrayList
            String readline;
            while ((readline = reader.readLine()) != null) {

                htmlString.append(readline).append("\n");
            }

        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

        return htmlString.toString();
    }
}


