package lt.vianet.extractor.page_adapters;

public class WebPage implements IWebPage {
    private String web;
    private String encoding;
    private String html;

    public WebPage(String web) {
        this.web = web;
    }

    @Override
    public String getPageName() {
        return web;
    }

    @Override
    public String getHTML() {
        return html;
    }

    @Override
    public void setHTML(String html) {
        this.html = html;
    }

    @Override
    public String getEncoding() {
        return encoding;
    }

    @Override
    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
