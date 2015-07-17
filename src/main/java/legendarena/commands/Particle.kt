package legendarena.commands

import legendapi.utils.Cooldown
import legendapi.utils.Rank
import legendapi.utils.RankUtils
import legendarena.hub.menu.ParticleMenu
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Particle : CommandExecutor {

    private val cooldown = HashMap<UUID, Cooldown>()

    override fun onCommand(sender: CommandSender, command: Command, s: String, args: Array<String>): Boolean {
        if(!Rank.MEMBERPLUS.isRanked(sender)) {
            sender.sendMessage(RankUtils.noPermissions(Rank.MEMBERPLUS))
            return true
        }
        if(cooldown.containsKey((sender as Player).getUniqueId()) && !cooldown.get(sender.getUniqueId()).done()) {
            sender.sendMessage(cooldown.get(sender.getUniqueId()).getTimeRemaining())
            return true
        }
        ParticleMenu().show(sender)
        //1 second cooldown
        cooldown.put(sender.getUniqueId(), Cooldown(1.0))
        return true
    }

}