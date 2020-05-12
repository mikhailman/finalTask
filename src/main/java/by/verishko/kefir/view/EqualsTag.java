package by.verishko.kefir.view;

import javax.servlet.jsp.tagext.TagSupport;

public class EqualsTag extends TagSupport {

    public static Boolean equalsValue(final String userValue,
                                      final Integer mapValue) {

        return userValue != null
                && userValue.length() > 0
                && userValue.equals(mapValue.toString());
    }

}