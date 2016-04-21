package legendarena.api.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;

/**
 * Use this class to check if a player is a founder or developer.
 */
public class SpecialStaffListUtils {

    /*
     * To get a player's UUID, use <a href="https://gist.github.com/Cryptkeeper/02812c2d4c2286e87440">this Bash script</a>.<br><br>
     *
     * (thanks Crypt!)
     */

    private static final ArrayList<String> devUUIDList = new ArrayList<String>() {{
            add("2dec56e8-5548-4d89-8967-ee0da35f9874"); //Jaden
            add("70336328-0de7-430e-8cba-2779e2a05ab5"); //Odin
    }};

    private static final ArrayList<String> founderUUIDList = new ArrayList<String>() {{
            add("2dec56e8-5548-4d89-8967-ee0da35f9874"); //Jaden
            add("70336328-0de7-430e-8cba-2779e2a05ab5"); //Odin
    }};

    public static boolean isDeveloper(Player p) {
        return devUUIDList.contains(p.getUniqueId().toString());
    }

    public static boolean isFounder(Player p) {
        return founderUUIDList.contains(p.getUniqueId().toString());
    }

}
