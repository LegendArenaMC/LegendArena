package net.thenamedev.legendarena.commands.backends.punish;

import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Freeze command.
 *
 * @author ThePixelDev
 */
public class Freeze {

    public static final ArrayList<UUID> frozenPlayers = new ArrayList<>();

    public static void run(CommandSender sender, String target) {
        ////////////////////////////////////////////
        //                                        //
        //              SANITY CHECKS             //
        //                                        //
        ////////////////////////////////////////////

        if(!Rank.isRanked(sender, Rank.MOD)) {
            sender.sendMessage(Rank.noPermissions(Rank.MOD));
            return;
        }
        if(target.equals("")) {
            sender.sendMessage(ChatUtils.getCustomMsg("Punish") + "Usage: /punish freeze <player>");
            return;
        }

        ////////////////////////////////////////////
        //                                        //
        //           THE ACTUAL DAMN CODE         //
        //                                        //
        ////////////////////////////////////////////

        if(Bukkit.getPlayer(target) == null) {
            sender.sendMessage(ChatUtils.getCustomMsg("Punish") + "The player \"" + target + "\" isn't online/has never joined!");
            return;
        }

        Player targetReal = Bukkit.getPlayer(target);

        if(frozenPlayers.contains(targetReal.getUniqueId())) {
            frozenPlayers.remove(targetReal.getUniqueId());
            sender.sendMessage(ChatUtils.getCustomMsg("Punish") + "Player \"" + targetReal.getName() + "\" successfully unfrozen!");
            return;
        }

        frozenPlayers.add(targetReal.getUniqueId());
        sender.sendMessage(ChatUtils.getCustomMsg("Punish") + "Player \"" + targetReal.getName() + "\" successfully frozen!");
    }

}
