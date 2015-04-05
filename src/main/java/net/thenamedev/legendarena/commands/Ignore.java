package net.thenamedev.legendarena.commands;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created on 4/4/2015
 *
 * @author ThePixelDev
 */
public class Ignore {

    private static HashMap<UUID, List<UUID>> ignore = new HashMap<>();

    public static boolean isIgnored(Player by, Player target) {
        if(!ignore.containsKey(by.getUniqueId()))
            return false;
        List<UUID> ignored = ignore.get(by.getUniqueId());
        return ignored.contains(target.getUniqueId());
    }

    public static void ignore(Player by, Player target) {
        if(!ignore.containsKey(by.getUniqueId()))
            ignore.put(by.getUniqueId(), new ArrayList<UUID>());
        List<UUID> ignored = ignore.get(by.getUniqueId());
        ignored.add(target.getUniqueId());
        ignore.put(by.getUniqueId(), ignored);
    }

    //

}
