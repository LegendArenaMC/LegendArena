package legendarena

import legendapi.utils.SetupUtils
import legendarena.commands.Staff
import legendarena.listeners.ServerPingListener
import org.bukkit.plugin.java.JavaPlugin

class LegendArena: JavaPlugin() {

    override fun onEnable() {
        var setup = SetupUtils(this)
        setup.announceStatus("Setting up commands...")
        setup.registerCommand(Staff(), "staff")
        //TODO: Commands
        setup.announceStatus("Setting up listeners...")
        setup.registerListener(ServerPingListener())
        //TODO: Listeners
        setup.announceStatus("Setting up timers...")
        //TODO: Timers
        setup.announceStatus("Setting up aliases...")
        //TODO: Aliases
    }

    override fun onDisable() {
        //
    }

}