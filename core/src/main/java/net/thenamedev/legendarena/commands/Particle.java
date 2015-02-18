package net.thenamedev.legendarena.commands;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.menu.core.*;
import net.thenamedev.legendarena.utils.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

import java.util.*;

/**
 * @author TheNameMan
 */
public class Particle implements CommandExecutor {

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
        MenuInv.particlemenu.show((Player) sender);
        //3 second cooldown
        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(3));
        return true;
    }

}
