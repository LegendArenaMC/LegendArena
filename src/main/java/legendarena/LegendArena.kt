package legendarena

import legendapi.utils.KotlinUtils
import legendapi.utils.SetupUtils
import legendapi.utils.VersionUtils
import legendarena.commands.*
import legendarena.commands.staff.*
import legendarena.hub.HubWarper
import legendarena.hub.JumpPad
import legendarena.listeners.*
import legendarena.listeners.menu.*
import legendarena.utils.ConfigUtils
import org.bukkit.Bukkit

class LegendArena : KotlinUtils() {

    public final var devMode: Boolean = true

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
        setup.registerListener(JumpPad.JumpPadListener())
        setup.registerListener(BlockPlaceListener())

        //this took me more time to figure out than I wish to admit.

        setup.registerListener(MainMenuListener())
        setup.registerListener(ChatMenuListener())
        setup.registerListener(MinigameMenuListener())
        setup.registerListener(JumpPadMenuListener())
        setup.registerListener(StaffMenuListener())

        setup.announceStatus("Setting up timers...")

        setup.registerNonAsyncTimer(HubWarper.InitPlayers(), 10)

        setup.announceStatus("Setting up aliases...")

        setup.setAliases("chat", "sc", "c", "say")
        setup.setAliases("stafflist", "sl", "liststaff")
        setup.setAliases("particles", "ps", "particle")
        setup.setAliases("firework", "fw")
        setup.setAliases("gadgets", "gs")

        setup.announceStatus("Running extra setup stuff...")

        ConfigUtils.init();
        VersionUtils.setVersion("LegendArena", "1.0-SNAPSHOT")
        VersionUtils.setVersion("Kotlin", "0.12.613")
        VersionUtils.setVersion("KotlinLoader", VersionUtils.getVersion("Kotlin")) //compatibility stuff if other plugins want to use KotlinLoader instead of Kotlin for the version String getter (..thing)
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTasks(Bukkit.getPluginManager().getPlugin("LegendArena"))
        ConfigUtils.saveConfig();
    }

}