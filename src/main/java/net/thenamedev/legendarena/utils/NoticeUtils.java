package net.thenamedev.legendarena.utils;

import java.util.*;

/**
 * @author TheNameMan
 */
public class NoticeUtils {

    public static boolean isCloseToBFTN() {
        Calendar c = Calendar.getInstance();
        boolean isBFTN = false;
        if(c.get(Calendar.DAY_OF_MONTH) == 20 || c.get(Calendar.DAY_OF_MONTH) == 21 || c.get(Calendar.DAY_OF_MONTH) == 22 || c.get(Calendar.DAY_OF_MONTH) == 23 || c.get(Calendar.DAY_OF_MONTH) == 24 || c.get(Calendar.DAY_OF_MONTH) == 25 || c.get(Calendar.DAY_OF_MONTH) == 26)
            isBFTN = true;
        return isBFTN;
    }

}
