package legendarena.listeners

import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.Cooldown
import legendapi.utils.MenuUtils
import legendapi.utils.VersionUtils
import legendarena.LegendArena
import legendarena.hub.HubWarper
import legendarena.hub.menu.MainMenu
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.util.*

public class HubListeners : Listener {

    private val cooldown = HashMap<UUID, Cooldown>()

    /**
     * WUBBBBBB
     *
     * ...sorry, I couldn't resist
     */
    EventHandler(ignoreCancelled = true)
    public fun listenForDrop(ev: PlayerDropItemEvent) {
        if(HubWarper.isExempt(ev.getPlayer().getUniqueId())) return
        ev.setCancelled(true)
    }

    EventHandler
    public fun listenForInteract(ev: PlayerInteractEvent) {
        try {
            if(ev.getItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "EnderBow"))
                if(ev.getAction().equals(Action.RIGHT_CLICK_AIR) || ev.getAction().equals(Action.RIGHT_CLICK_BLOCK))
                    return
            if(!HubWarper.isExempt(ev.getPlayer().getUniqueId()) && !LegendArena().devMode)
                ev.setCancelled(true)
            if(ev.getAction() !== Action.RIGHT_CLICK_AIR && ev.getAction() !== Action.RIGHT_CLICK_BLOCK)
                return
            if(ev.getItem().getItemMeta().getDisplayName().equals(HubWarper.getMainMenu(ev.getPlayer().getName()).getItemMeta().getDisplayName())) {
                if(cooldown.containsKey(ev.getPlayer().getUniqueId())) {
                    val c = cooldown.get(ev.getPlayer().getUniqueId())
                    if(!c.done()) {
                        Message(MessageType.ACTIONBAR).append("" + ChatColor.RED + "Ow, that hurts! :( ( " + c.getTimeRemaining() + ChatColor.RED + " )").send(ev.getPlayer())
                        return
                    }
                }
                MainMenu().show(ev.getPlayer())
                if(!ev.isCancelled())
                    ev.setCancelled(true)
                cooldown.put(ev.getPlayer().getUniqueId(), Cooldown(1.0))
            }
        } catch(ex: Exception) {
            if(!HubWarper.isExempt(ev.getPlayer().getUniqueId()) && !LegendArena().devMode)
                ev.setCancelled(true)
        }


    }

}