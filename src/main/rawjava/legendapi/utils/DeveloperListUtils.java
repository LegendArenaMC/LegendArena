package legendapi.utils;

import org.bukkit.entity.Player;

public class DeveloperListUtils {

    private static final String[] devUUIDList = {
            "70336328-0de7-430e-8cba-2779e2a05ab5" //ThePixelDev
    };

    public static boolean isDeveloper(Player p) {
        for(String s : devUUIDList) {
            if(s.toLowerCase().equals(p.getUniqueId().toString())) {
                return true;
            }
        }

        //for loop didn't find them in the devUUIDList, return out with false
        return false;
    }

}
