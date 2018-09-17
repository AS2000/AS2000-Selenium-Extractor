package lt.vianet.extractor.cleaning_process;

import lt.vianet.extractor.page_adapters.WebPage;


public class ReplaceTabWithSpaces {
    private WebPage webPage;

    public ReplaceTabWithSpaces() {
    }

    public ReplaceTabWithSpaces(WebPage webPage) {
        this.webPage = webPage;
    }


    public WebPage cleanTheHTML() {

        return replaceStyleTagsWithSpaces();
    }

    private WebPage replaceStyleTagsWithSpaces() {
        // remove Tabs
        String withTabs = webPage.getHTML();

        webPage.setHTML(withTabs.replaceAll("\u0009", " "));

        return webPage;
    }
}
