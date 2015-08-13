package legendapi.utils;

import org.bukkit.entity.Player;

/**
 * Use this class to check if a player is a founder or developer.
 */
public class SpecialStaffListUtils {

    private static final String[] devUUIDList = {
            "70336328-0de7-430e-8cba-2779e2a05ab5" //Pixel
    };

    private static final String[] founderUUIDList = {
            "2dec56e8-5548-4d89-8967-ee0da35f9874", //Jaden
            "70336328-0de7-430e-8cba-2779e2a05ab5" //Pixel
    };

    public static boolean isDeveloper(Player p) {
        for(String s : devUUIDList)
            if(s.toLowerCase().equals(p.getUniqueId().toString()))
                return true;

        //for loop didn't find them in the devUUIDList
        return false;
    }

    public static boolean isFounder(Player p) {
        for(String s : founderUUIDList)
            if(s.toLowerCase().equals(p.getUniqueId().toString()))
                return true;

        //for loop didn't find them in the founderUUIDList
        return false;
    }

}
