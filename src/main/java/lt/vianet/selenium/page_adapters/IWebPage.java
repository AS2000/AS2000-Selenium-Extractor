package lt.vianet.selenium.page_adapters;

import java.util.List;

public interface IWebPage {

    public String getPageName();

    public void setPageName(String web);

    public String getPageLink();

    public String getHTML();

    public void setHTML(String html);

    public String getEncoding();

    public void setEncoding(String encoding);

    public List getFlightNumbers();

    public void setFlightNumbers(List flightNumbers);


}
