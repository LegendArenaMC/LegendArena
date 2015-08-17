/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * ...thing
 */
public class StaticCupidArrowStorage {

    private static ArrayList<UUID> cupidArrows = new ArrayList<>();

    public static boolean isCupidArrow(Player p) {
        return cupidArrows.contains(p.getUniqueId());
    }

    public static void setCupidArrow(Player p, boolean addOrRemove) {
        if(addOrRemove)
            //add
            cupidArrows.add(p.getUniqueId());
        else
            //remove
            cupidArrows.remove(p.getUniqueId());
    }

}
