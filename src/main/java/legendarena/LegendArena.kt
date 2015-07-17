package legendarena

import legendapi.utils.KotlinUtils
import legendapi.utils.SetupUtils
import legendapi.utils.VersionUtils
import legendarena.commands.*
import legendarena.commands.staff.*
import legendarena.hub.HubWarper
import legendarena.listeners.ChatListener
import legendarena.listeners.HubListeners
import legendarena.listeners.PlayerJoinListener
import legendarena.listeners.ServerPingListener
import legendarena.listeners.menu.*
import org.bukkit.Bukkit

class LegendArena : KotlinUtils() {

    override fun onEnable() {
        var setup = SetupUtils(Bukkit.getPluginManager().getPlugin("LegendArena"))

        // ACTUAL SETUP STUFF

        setup.announceStatus("Setting up commands...")

        setup.registerCommand(Staff(), "staff")
        setup.registerCommand(Dev(), "dev")
        setup.registerCommand(Particle(), "particles")
        setup.registerCommand(Chat(), "chat")
        setup.registerCommand(Help(), "help")
        setup.registerCommand(Firework(), "firework")
        setup.registerCommand(Warp(), "warp")
        setup.registerCommand(Shadow(), "shadow")
        setup.registerCommand(StaffList(), "stafflist")
        setup.registerCommand(MOTDTools(), "motd")
        setup.registerCommand(Gadgets(), "gadgets")
        setup.registerCommand(EmeraldCmd(), "emeralds")

        setup.announceStatus("Setting up listeners...")

        setup.registerListener(ChatListener())
        setup.registerListener(ServerPingListener())
        setup.registerListener(HubListeners())
        setup.registerListener(PlayerJoinListener())

        //this took me more time to figure out than I wish to admit.

        setup.registerListener(MainMenuListener())
        setup.registerListener(ChatMenuListener())
        setup.registerListener(MinigameMenuListener())
        setup.registerListener(StaffMenuListener())

        setup.announceStatus("Setting up timers...")

        setup.registerTimer(HubWarper.InitPlayers(), 10)

        setup.announceStatus("Setting up aliases...")

        setup.setAliases("chat", "sc", "c", "say")
        setup.setAliases("stafflist", "sl", "liststaff")
        setup.setAliases("particles", "ps", "particle")
        setup.setAliases("firework", "fw")
        setup.setAliases("gadgets", "gs")

        setup.announceStatus("Running extra setup stuff...")

        VersionUtils.setVersion("LegendArena", "1.0-SNAPSHOT")
        VersionUtils.setVersion("Kotlin", "0.12.613")
        VersionUtils.setVersion("KotlinLoader", VersionUtils.getVersion("Kotlin")) //compatibility stuff if other plugins want to use KotlinLoader instead of Kotlin
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTasks(Bukkit.getPluginManager().getPlugin("LegendArena"))
    }

}