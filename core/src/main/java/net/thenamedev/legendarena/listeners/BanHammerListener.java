package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendarena.core.*;
import net.thenamedev.legendarena.extras.banhammer.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import org.jetbrains.annotations.*;

/**
 * @author TheNameMan
 */
public class BanHammerListener implements Listener {

    @EventHandler
    public void onEntityClick(@NotNull PlayerInteractEntityEvent ev) {
        Player staff = ev.getPlayer();
        if(!BanHammerManager.hasBanHammer(staff))
            return; //they don't have the ban hammer active, return out and do nothing
        if(!(ev.getRightClicked() instanceof Player)) {
            return;
        }
        @NotNull Player target = (Player) ev.getRightClicked();
        if(!Rank.getRank(staff, Rank.Mod))
            return;
        if(Rank.getRank(target, Rank.Mod)) {
            staff.sendMessage(ChatColor.RED + "You cannot ban other staff!");
            return;
        }
        staff.performCommand("ban " + target.getName().toLowerCase() + " The (literal) ban hammer has spoken!");
    }

}
