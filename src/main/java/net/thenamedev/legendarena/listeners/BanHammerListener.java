package net.thenamedev.legendarena.listeners;

import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.banhammer.BanHammerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

/**
 * @author ThePixelDev
 */
public class BanHammerListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onEntityClick(PlayerInteractEntityEvent ev) {
        Player staff = ev.getPlayer();
        if(!BanHammerManager.hasBanHammer(staff))
            return; //they don't have the ban hammer active, return out and do nothing
        if(!(ev.getRightClicked() instanceof Player))
            return;
        Player target = (Player) ev.getRightClicked();
        if(!Rank.getRank(staff, Rank.Mod))
            return;
        if(Rank.getRank(target, Rank.Mod)) {
            staff.sendMessage(PluginUtils.msgNormal + "You must be fun at parties.");
            return;
        }
        staff.performCommand("ban " + target.getName().toLowerCase() + " The ban hammer has spoken!");
    }

}
