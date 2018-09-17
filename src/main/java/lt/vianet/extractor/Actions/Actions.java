package lt.vianet.extractor.Actions;

import lt.vianet.extractor.cleaning_process.ReplaceTabWithSpaces;
import lt.vianet.extractor.cleaning_process.TrimSpaces;
import lt.vianet.extractor.io.SaveHTMLData;
import lt.vianet.extractor.io.URLReader;
import lt.vianet.extractor.page_adapters.WebPage;

public class Actions {

    public void startApp() {

        doActions();
    }

    private void doActions() {

        WebPage webPage = new WebPage("https://skrendu.lt/");
        webPage.setEncoding("UTF-8");

         webPage = cleanHTML(getHTML(webPage));

        saveHTML(webPage);
    }


    private WebPage getHTML(WebPage webPage) {

        return new URLReader(webPage).readThePage();
    }


    private WebPage cleanHTML(WebPage webPage) {

        webPage = new ReplaceTabWithSpaces(webPage).cleanTheHTML();
        webPage = new TrimSpaces(webPage).getTrimedHTML();

        return webPage;
    }


    private void saveHTML(WebPage webPage) {

        new SaveHTMLData(webPage).saveHTML();
    }

}
