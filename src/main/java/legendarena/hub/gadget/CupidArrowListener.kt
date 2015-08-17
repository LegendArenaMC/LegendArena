/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.hub.gadget

import legendapi.utils.Cooldown
import legendapi.utils.PluginUtils
import legendarena.hub.particles.ParticleCore
import legendarena.listeners.PlayerDamageListener
import legendarena.utils.StaticCupidArrowStorage
import org.bukkit.ChatColor
import org.bukkit.entity.Arrow
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerInteractEvent
import java.util.*

public class CupidArrowListener : Listener {

    private var cooldown = HashMap<UUID, Cooldown>()

    EventHandler
    public fun listenForArrowHit(ev: EntityDamageByEntityEvent) {
        if(ev.getEntity() !is Player) return
        var p = ev.getEntity() as Player
        if(ev.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return

        if(StaticCupidArrowStorage.isCupidArrow(ev.getDamager() as Player)) {
            ev.setCancelled(true)
            StaticCupidArrowStorage.setCupidArrow(ev.getDamager() as Player, false)

            var currentParticles = ParticleCore.getParticles(p.getUniqueId())

            ParticleCore.setParticles(ParticleCore.ParticleType.HEART, p)

            Thread(Runnable(
                fun run() {
                    Thread.sleep(15000)

                    if(currentParticles == null)
                        ParticleCore.clearParticles(p.getUniqueId())
                    else
                        ParticleCore.setParticles(currentParticles, p)
                }
            )).start()
        }
    }

    EventHandler
    public fun listenForInteract(ev: PlayerInteractEvent) {
        try {
            if(ev.getItem().getItemMeta().getDisplayName().equals("" + ChatColor.GREEN + "Cupid Arrow")) {
                ev.setCancelled(true)

                if(cooldown.containsKey(ev.getPlayer().getUniqueId())) {
                    var c = cooldown.get(ev.getPlayer().getUniqueId())
                    if(!c.done()) {
                        ev.getPlayer().sendMessage(c.getTimeRemaining())
                        return
                    }
                }

                var projectile = PluginUtils.fireArrow(ev.getPlayer())
                StaticCupidArrowStorage.setCupidArrow(ev.getPlayer(), false)
            }
        } catch(ex: Exception) {
            //do nothing
        }
    }

}