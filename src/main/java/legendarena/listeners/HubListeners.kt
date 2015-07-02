package legendarena.listeners

import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.Cooldown
import legendarena.hub.HubWarper
import legendarena.hub.menu.MainMenu
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.util.*

class HubListeners : Listener {

    private val cooldown = HashMap<UUID, Cooldown>()

    /**
     * WUBBBBBB

     * ...sorry, I couldn't resist.
     */
    EventHandler(ignoreCancelled = true)
    public fun listenForDrop(ev: PlayerDropItemEvent) {
        if(HubWarper.isExempt(ev.getPlayer().getUniqueId())) return
        ev.setCancelled(true)
    }

    EventHandler
    public fun listenForInteract(ev: PlayerInteractEvent) {
        try {
            if(!HubWarper.isExempt(ev.getPlayer().getUniqueId()))
                ev.setCancelled(true)
            if(ev.getAction() !== Action.RIGHT_CLICK_AIR && ev.getAction() !== Action.RIGHT_CLICK_BLOCK)
                return
            if(ev.getItem() === HubWarper.getCustomization()) {
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
                cooldown.put(ev.getPlayer().getUniqueId(), Cooldown(2.0))
            }
        } catch(ex: Exception) {
            //ignore
        }


    }

}