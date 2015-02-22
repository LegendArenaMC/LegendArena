package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.menu.*;
import net.thenamedev.legendarena.extras.menu.core.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class Particle implements CommandExecutor {

    public static final Menu particlemenu = new ParticleMenu(Bukkit.getPluginManager().getPlugin("LegendArena"));

    @NotNull
    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public boolean onCommand(@NotNull CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.VIP)) {
            Rank.noPermissions(sender, Rank.VIP);
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
