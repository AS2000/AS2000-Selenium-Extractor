package lt.vianet.extractor.page_adapters;

import java.util.List;

public interface IWebPage {

    public void setPageName(String web);

    public String getPageName();

    public String getPageLink();

    public String getHTML();

    public void setHTML(String html);

    public String getEncoding();

    public void setEncoding(String encoding);

    public void setFlightNumbers(List flightNumbers);

    public List getFlightNumbers();



}
