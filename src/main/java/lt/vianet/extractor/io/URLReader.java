package lt.vianet.extractor.io;

import lt.vianet.extractor.page_adapters.WebPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLReader {
    private WebPage webPage;

    public URLReader() {
    }

    public URLReader(WebPage webPage) {
        this.webPage = webPage;
    }

    public WebPage readThePage() {

        return getPlainText();
    }

    private WebPage getPlainText() {
        StringBuffer buffer = new StringBuffer();
        try {
            String url = webPage.getPageName();

            URL web = new URL(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(web.openStream(), webPage.getEncoding()));

            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                buffer.append(inputLine + " ");
            }
            reader.close();

            webPage.setHTML(buffer.toString());

        } catch (MalformedURLException mue) {
            System.out.println("You catched: MalformedURLException");
        } catch (IOException ioe) {
            System.out.println("You catched: IOException");
        }
        return webPage;
    }
}
