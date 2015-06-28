package legendarena

import legendapi.utils.SetupUtils
import legendarena.commands.Dev
import legendarena.commands.Staff
import legendarena.commands.Chat
import legendarena.commands.Fly
import legendarena.listeners.ChatListener
import legendarena.listeners.PlayerJoinListener
import legendarena.listeners.ServerPingListener
import org.bukkit.plugin.java.JavaPlugin

class LegendArena : JavaPlugin() {

    override fun onEnable() {
        var setup = SetupUtils(this)

        // ACTUAL FUCKING SETUP STUFF

        setup.announceStatus("Setting up commands...")

        setup.registerCommand(Staff(), "staff")
        setup.registerCommand(Dev(), "dev")
        setup.registerCommand(Fly(), "fly")
        setup.registerCommand(Chat(), "chat")

        setup.announceStatus("Setting up listeners...")

        setup.registerListener(ChatListener())
        setup.registerListener(ServerPingListener())
        setup.registerListener(PlayerJoinListener())

        setup.announceStatus("Setting up timers...")

        setup.announceStatus("Setting up aliases...")

        setup.setAliases("chat", "sc", "c", "say")
    }

    override fun onDisable() {
        //
    }

}