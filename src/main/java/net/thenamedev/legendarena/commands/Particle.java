package net.thenamedev.legendarena.commands;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.menu.*;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;

/**
 * @author TheNameMan
 */
public class Particle implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.VIP)) {
            Rank.noPermissions(sender, Rank.VIP);
            return true;
        }
        sender.sendMessage(ChatColor.RED + "THIS IS A WORK IN PROGRESS FEATURE. EXPECT BUGS, AND EXPECT THEM TO KILL THINGS. ESPECIALLY COWS.");
        MenuInv.particlemenu.show((Player) sender);
        return true;
    }

}
