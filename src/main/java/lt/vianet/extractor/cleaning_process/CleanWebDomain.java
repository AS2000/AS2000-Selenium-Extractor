package lt.vianet.extractor.cleaning_process;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CleanWebDomain {
    public String getCleanWebDomain(String domainName) {

        return cleanWebDomain(domainName);
    }

    private String cleanWebDomain(String domainName) {

        //https://docs.oracle.com/javase/6/docs/api/java/util/regex/Pattern.html#bs
        Pattern pattern = Pattern.compile("([a-zA-Z_0-9]{1,}[.]*[a-zA-Z_0-9]{2,}+[.][a-zA-Z]{2,8}+)");
        Matcher m = pattern.matcher(domainName.toString());

        while (m.find()) {
            domainName = m.group();
        }

        return domainName;
    }

}
