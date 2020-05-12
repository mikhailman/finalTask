package by.verishko.kefir.view;

import javax.servlet.jsp.tagext.TagSupport;

public class LocaleTag extends TagSupport {
    public static String localeValue(String attribute, String cookie) {
        String locale = null;
        if (attribute != null && attribute.length() > 0) {
            locale = attribute;
        } else {
            locale = cookie;
        }
        return locale;
    }
}
