package lt.vianet.selenium.page_adapters;

import java.util.List;

public class WebPage implements IWebPage {
    private String link;
    private String web;
    private String encoding;
    private String html;
    private List<String> flightNumbers;

    public WebPage(String link) {
        this.link = link;
    }

    @Override
    public String getPageName() {
        return web;
    }

    @Override
    public void setPageName(String web) {
        this.web = web;
    }

    @Override
    public String getPageLink() {
        return link;
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

    public List getFlightNumbers() {
        return flightNumbers;
    }

    public void setFlightNumbers(List flightNumbers) {
        this.flightNumbers = flightNumbers;
    }

}
