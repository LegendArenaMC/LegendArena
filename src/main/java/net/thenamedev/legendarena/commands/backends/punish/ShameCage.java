package net.thenamedev.legendarena.commands.backends.punish;

import net.thenamedev.legendapi.utils.ChatUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Welcome, to the CAGE OF SHAME! MWHWHAHAHAHAA-*cough*-AHAHAHAHAHAHAHAHAHAHAaaaa... heh...
 *
 * @author ThePixelDev
 */
public class ShameCage {

    public static void shame(Player p) {
        //Freeze.frozenPlayers.add(p.getUniqueId());
        p.teleport(new Location(p.getWorld(), p.getLocation().getX(), p.getLocation().getY() + 10, p.getLocation().getZ()));
        p.getLocation().getBlock().setType(Material.AIR);
        p.getLocation().getBlock().getRelative(0, 1, 0).setType(Material.AIR);
        //BEGIN GLASS PLACING FRENZY
        p.getLocation().getBlock().getRelative(0, -1, 0).setType(Material.GLASS);
        p.getLocation().getBlock().getRelative(-1, -1, 0).setType(Material.GLASS);
        p.getLocation().getBlock().getRelative(0, -1, -1).setType(Material.GLASS);
        p.getLocation().getBlock().getRelative(-1, -1, -1).setType(Material.GLASS);
        p.getLocation().getBlock().getRelative(0, -1, 0).setType(Material.GLASS);
    }

    public static void run(CommandSender sender, Player target) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Sorry - you can only do this as a player :(");
            return; //Do nothing if it's not a player
        }

        shame(target);

        sender.sendMessage(ChatUtils.getCustomMsg("Punish") + "Placed \"" + target.getName() + "\" in a cage of shame.");

    }

}
