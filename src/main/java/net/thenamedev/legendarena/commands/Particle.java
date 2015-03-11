package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.utils.Cooldown;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.menu.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class Particle implements CommandExecutor {

    public static final ParticleMenu particlemenu = new ParticleMenu(Bukkit.getPluginManager().getPlugin("LegendArena"));

    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.MemberPlus)) {
            Rank.noPermissions(sender, Rank.MemberPlus);
            return true;
        }
        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());
            return true;
        }
        particlemenu.show((Player) sender);
        //3 second cooldown
        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(3));
        return true;
    }

}
