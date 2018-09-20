package lt.vianet.extractor.cleaning_process;

import lt.vianet.extractor.page_adapters.WebPage;

public class TrimSpaces {
    private WebPage webPage;

    public TrimSpaces() {
    }

    public TrimSpaces(WebPage webPage) {
        this.webPage = webPage;
    }


    public WebPage getTrimedHTML() {

        return timmHTML();
    }

    private WebPage timmHTML() {

        // trimm Spaces
        String withSpaces = webPage.getHTML();

        webPage.setHTML(withSpaces.replaceAll("\\s\\s+", "  "));

        return webPage;
    }

}
