package legendarena

import legendapi.utils.KotlinUtils
import legendapi.utils.LegendAPIUtils
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
import java.util.*

class LegendArena : KotlinUtils() {

    public final var devMode: Boolean = true

    override fun onEnable() {
        var p = Bukkit.getPluginManager().getPlugin("LegendArena")
        var setup = SetupUtils(p)

        // ACTUAL SETUP STUFF

        setup.announceStatus("Setting up configuration...")

        setupConfig()

        setup.announceStatus("Setting up commands...")

        setup.registerCommand(Staff(), "staff")
        setup.registerCommand(Dev(), "dev")
        setup.registerCommand(Chat(), "chat")
        setup.registerCommand(Help(), "help")
        setup.registerCommand(Firework(), "firework")
        setup.registerCommand(Shadow(), "shadow")
        setup.registerCommand(StaffList(), "stafflist")
        setup.registerCommand(MOTDTools(), "motd")
        setup.registerCommand(Gadgets(), "gadgets")
        setup.registerCommand(Autoban(), "autoban")
        setup.registerCommand(EmeraldCmd(), "emeralds")

        setup.announceStatus("Setting up listeners...")

        setup.registerListener(ChatListener())
        setup.registerListener(ServerPingListener())
        setup.registerListener(HubListeners())
        setup.registerListener(PlayerJoinListener())
        setup.registerListener(JumpPad.JumpPadListener())
        setup.registerListener(BlockPlaceListener())
        setup.registerListener(PlayerDamageListener())

        //this took me more time to figure out than I wish to admit.

        setup.registerListener(ChatMenuListener())
        setup.registerListener(MinigameMenuListener())
        setup.registerListener(JumpPadMenuListener())
        setup.registerListener(StaffMenuListener())

        setup.announceStatus("Setting up timers...")

        setup.registerNonAsyncTimer(HubWarper.InitPlayers(), 10)

        setup.announceStatus("Setting up aliases...")

        setup.setAliases("chat", "sc", "c", "say")
        setup.setAliases("stafflist", "sl", "liststaff")
        setup.setAliases("firework", "fw")
        setup.setAliases("gadgets", "gs")

        setup.announceStatus("Finishing up...")

        VersionUtils.setVersion("LegendArena", "1.1-SNAPSHOT")
        VersionUtils.setVersion("KotlinLoader", "0.12.613")

        setup.announceStatus("LegendArena v" + p.getDescription().getVersion() + " fully loaded.")
    }

    internal fun setupConfig() {
        var config = ConfigUtils.config
        var configVer = 3
        config.setConfigVersion(configVer)
        config.addDefault("debug", true)
        config.addDefault("enable.lobbyServer", true)
        config.addDefault("enable.staffHub", true)
        config.addDefault("enable.warp", true)
        config.addDefault("hub.world", "world")
        config.addDefault("hub.location.note", "Leave the following blank for the default world spawn location.")
        config.addDefault("hub.location.x", "")
        config.addDefault("hub.location.y", "")
        config.addDefault("hub.location.z", "")
        config.addDefault("staffhubhub.world", "world")
        config.addDefault("staffhub.location.note", "As with the Hub world location, leave the following blank for default world spawn location.")
        config.addDefault("staffhub.location.x", "")
        config.addDefault("staffhub.location.y", "")
        config.addDefault("staffhub.location.z", "")
        config.addDefault("emeralds.storage", "sqlite")
        config.addDefault("emeralds.table", "emeralds")
        config.addDefault("emeralds.mysql.host", "127.0.0.1")
        config.addDefault("emeralds.mysql.password", "sup3rs3cr3tp@ssw0rd")
        config.addDefault("emeralds.mysql.database", "emeralds")
        config.addDefault("emeralds.sqlite.file", "emeralds.db")
        var founders = ArrayList<String>();
        founders.add("ThePixelDev")
        founders.add("ZRaptor22")
        config.addDefault("founders", founders)
        config.upgradeIfConfVersionIsNot(configVer)
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTasks(Bukkit.getPluginManager().getPlugin("LegendArena"))
    }

}