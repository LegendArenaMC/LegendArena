package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.banhammer.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

/**
 * @author ThePixelDev
 */
public class BanHammerListener implements Listener {

    @EventHandler
    public void onEntityClick(PlayerInteractEntityEvent ev) {
        Player staff = ev.getPlayer();
        if(!BanHammerManager.hasBanHammer(staff))
            return; //they don't have the ban hammer active, return out and do nothing
        if(!(ev.getRightClicked() instanceof Player)) {
            return;
        }
        Player target = (Player) ev.getRightClicked();
        if(!Rank.getRank(staff, Rank.Mod))
            return;
        if(Rank.getRank(target, Rank.Mod)) {
            staff.sendMessage(ChatColor.RED + "You cannot ban other staff!");
            return;
        }
        staff.performCommand("ban " + target.getName().toLowerCase() + " The ban hammer has spoken!");
    }

}
