package legendarena.hub

import legendapi.message.Message
import legendapi.message.MessageType
import legendapi.utils.Cooldown
import legendapi.utils.MenuUtils
import legendapi.utils.Rank
import legendarena.hub.menu.MainMenu
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

class HubWarper : Listener {

    EventHandler(ignoreCancelled = true)
    public fun listenForDrop(ev: PlayerDropItemEvent) {
        if (isExempt(ev.getPlayer().getUniqueId())) return
        ev.setCancelled(true)
    }

    EventHandler
    public fun listenForInteract(ev: PlayerInteractEvent) {
        try {
            if (!isExempt(ev.getPlayer().getUniqueId()))
                ev.setCancelled(true)
            if (ev.getAction() !== Action.RIGHT_CLICK_AIR && ev.getAction() !== Action.RIGHT_CLICK_BLOCK)
                return
            if (ev.getItem() == getCustomization()) {
                if (cooldown.containsKey(ev.getPlayer().getUniqueId())) {
                    val c = cooldown.get(ev.getPlayer().getUniqueId())
                    if (!c.done()) {
                        Message(MessageType.ACTIONBAR).append(ChatColor.RED + "Ow, that hurts! :( ( " + c.getTimeRemaining() + ChatColor.RED + " )").send(ev.getPlayer())
                        return
                    }
                }
                MainMenu.show(ev.getPlayer())
                if (!ev.isCancelled())
                    ev.setCancelled(true)
                cooldown.put(ev.getPlayer().getUniqueId(), CooldownUtils.getCooldown(2))
            }
        } catch (ex: Exception) {
            //ignore
        }

    }

    public class InitPlayers : Runnable {

        override fun run() {
            for (p in Bukkit.getOnlinePlayers()) {
                if (exempt.contains(p.getUniqueId())) continue
                if (!Rank.isRanked(p, Rank.ADMIN)) p.getInventory().clear()
                p.getInventory().setItem(4, getCustomization())
                //TODO: Obligatory "Fucking fix me you moron Pixel"
                //fuck you too async (this is just a memory leak waiting to happen, by the way)
                Bukkit.getScheduler().runTask(Bukkit.getPluginManager().getPlugin("LegendArena"), object : Runnable {
                    override fun run() {
                        p.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 100000, 1, true))
                    }
                })
            }
        }

    }

    companion object {

        private val exempt = ArrayList<UUID>()
        private val cooldown = HashMap<UUID, Cooldown>()

        public fun toggleExemption(p: UUID) {
            if (isExempt(p))
                exempt.remove(p)
            else
                exempt.add(p)
        }

        public fun isExempt(p: UUID): Boolean {
            return exempt.contains(p)
        }

        private fun getCustomization(): ItemStack {
            return MenuUtils.createItem(Material.NETHER_STAR, ChatColor.GREEN + "Main Menu", "")
        }
    }
}
