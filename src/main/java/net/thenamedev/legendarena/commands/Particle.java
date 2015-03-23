package net.thenamedev.legendarena.commands;

import net.thenamedev.legendapi.utils.Cooldown;
import net.thenamedev.legendapi.utils.OldRank;
import net.thenamedev.legendarena.extras.menu.ParticleMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author TheNameMan
 */
public class Particle implements CommandExecutor {

    public static final ParticleMenu particlemenu = new ParticleMenu(Bukkit.getPluginManager().getPlugin("LegendArena"));

    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!OldRank.getRank(sender, OldRank.MemberPlus)) {
            OldRank.noPermissions(sender, OldRank.MemberPlus);
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
