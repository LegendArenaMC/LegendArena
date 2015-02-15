package net.thenamedev.legendarena.commands;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.menu.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class Particle implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.VIP)) {
            Rank.noPermissions(sender, Rank.VIP);
            return true;
        }
        MenuInv.particlemenu.show((Player) sender);
        return true;
    }

}
