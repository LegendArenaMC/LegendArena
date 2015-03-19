package net.thenamedev.legendarena.extras;

/**
 * Created on 3/19/2015
 *
 * @author ThePixelDev
 */
public class SwearFilter {

    private static final String[] swearList = {
            "fuck",
            "bitch",
            "shit",
            "crap",
            "whore"
    };

    public static String getFilteredMsg(String msg) {
        String parsedMsg = msg;
        for(String a : swearList) {
            parsedMsg = parsedMsg.replace(a, "****");
        }
        return parsedMsg;
    }

}
