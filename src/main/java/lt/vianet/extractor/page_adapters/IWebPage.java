package lt.vianet.extractor.page_adapters;

public interface IWebPage {

    public String getPageName();

    public String getHTML();

    public void setHTML(String html);

    public String getEncoding();

    public void setEncoding(String encoding);
}
