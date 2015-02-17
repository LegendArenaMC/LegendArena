package net.thenamedev.legendarena.commands.staff;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.banhammer.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class BanHammer implements CommandExecutor {

    public boolean onCommand(@NotNull CommandSender sender, Command command, String s, String[] args) {
        if(!Rank.getRank(sender, Rank.Mod)) {
            Rank.noPermissions(sender, Rank.Mod);
            return true;
        }
        if(!(sender instanceof Player))
            return true;
        ((Player) sender).getInventory().addItem(BanHammerManager.getBanHammerItem(sender.getName()));
        return true;
    }

}
