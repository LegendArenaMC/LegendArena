package legendarena

import legendapi.utils.SetupUtils
import legendarena.commands.*
import legendarena.listeners.ChatListener
import legendarena.listeners.PlayerJoinListener
import legendarena.listeners.ServerPingListener
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class LegendArena {

    protected fun onEnable() {
        var setup = SetupUtils(Bukkit.getPluginManager().getPlugin("LegendArena"))

        // ACTUAL FUCKING SETUP STUFF

        setup.announceStatus("Setting up commands...")

        setup.registerCommand(Staff(), "staff")
        setup.registerCommand(Dev(), "dev")
        setup.registerCommand(Particle(), "particles")
        setup.registerCommand(Chat(), "chat")
        setup.registerCommand(Help(), "help")
        setup.registerCommand(Firework(), "firework")
        setup.registerCommand(Warp(), "warp")
        setup.registerCommand(StaffList(), "stafflist")
        setup.registerCommand(MOTDTools(), "motd")
        setup.registerCommand(Gadgets(), "gadgets")
        setup.registerCommand(EmeraldCmd(), "emeralds")

        setup.announceStatus("Setting up listeners...")

        setup.registerListener(ChatListener())
        setup.registerListener(ServerPingListener())
        setup.registerListener(PlayerJoinListener())

        setup.announceStatus("Setting up timers...")

        setup.announceStatus("Setting up aliases...")

        setup.setAliases("chat", "sc", "c", "say")
        setup.setAliases("stafflist", "sl", "liststaff")
        setup.setAliases("particles", "ps", "particle")
        setup.setAliases("firework", "fw")
        setup.setAliases("gadgets", "gs")
    }

    protected fun onDisable() {
        Bukkit.getScheduler().cancelTasks(Bukkit.getPluginManager().getPlugin("LegendArena"))
    }

}