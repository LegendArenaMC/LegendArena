package legendarena.listeners

import legendarena.api.message.Message
import legendarena.api.message.MessageType
import legendarena.api.utils.Cooldown
import legendarena.api.utils.MenuUtils
import legendarena.api.utils.VersionUtils
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

class HubListeners : Listener {

    private val cooldown = HashMap<UUID, Cooldown>()

    @EventHandler(ignoreCancelled = true) fun listenForDrop(ev: PlayerDropItemEvent) {
        if(HubWarper.isExempt(ev.player.uniqueId)) return
        ev.isCancelled = true
    }

    @EventHandler fun listenForInteract(ev: PlayerInteractEvent) {
        try {
            if(ev.item.itemMeta.displayName.equals("" + ChatColor.GREEN + "EnderBow"))
                if(ev.action.equals(Action.RIGHT_CLICK_AIR) || ev.action.equals(Action.RIGHT_CLICK_BLOCK))
                    return
            if(!HubWarper.isExempt(ev.player.uniqueId))
                ev.isCancelled = true
            if(ev.action !== Action.RIGHT_CLICK_AIR && ev.action !== Action.RIGHT_CLICK_BLOCK)
                return
            if(ev.item.itemMeta.displayName.equals(HubWarper.getMainMenu(ev.player.name).itemMeta.displayName)) {
                if(cooldown.containsKey(ev.player.uniqueId)) {
                    val c = cooldown[ev.player.uniqueId]
                    if(!c!!.done()) {
                        Message(MessageType.ACTIONBAR).append("" + ChatColor.RED + "Ow, that hurts! :( ( " + c.getTimeRemaining() + ChatColor.RED + " )").send(ev.player)
                        return
                    }
                }
                MainMenu().show(ev.player)
                if(!ev.isCancelled)
                    ev.isCancelled = true
                cooldown.put(ev.player.uniqueId, Cooldown(1.0))
            }
        } catch(ex: Exception) {
            if(!HubWarper.isExempt(ev.player.uniqueId))
                ev.isCancelled = true
        }


    }

}