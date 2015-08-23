package legendarena

import legendapi.utils.*
import legendarena.commands.*
import legendarena.commands.staff.*
import legendarena.commands.staff.punish.*
import legendarena.hub.HubWarper
import legendarena.hub.JumpPad
import legendarena.hub.menu.MinigameMenu
import legendarena.hub.menu.ParticleMenu
import legendarena.hub.particles.ParticleCore
import legendarena.listeners.*
import legendarena.listeners.menu.*
import legendarena.scoreboard.ScoreboardSystem
import legendarena.utils.ConfigUtils
import org.bukkit.Bukkit
import java.util.*

class LegendArena : KotlinUtils() {

    @Deprecated
    /**
     * Use the config key "enable.devserver" instead.
     */
    public final var devMode: Boolean = if(ConfigUtils.config.get("enable.devserver") == null) false else ConfigUtils.config.get("enable.devserver") as Boolean

    override fun onEnable() {
        var p = Bukkit.getPluginManager().getPlugin("LegendArena")

        var setup = SetupUtils(p)

        // ACTUAL SETUP STUFF

        setup.announceStatus("Setting up configuration...")

        setupConfig()

        setup.announceStatus("Setting up staff commands...")

        setup.registerCommand(Staff(), "staff")
        setup.registerCommand(Dev(), "dev")
        setup.registerCommand(Chat(), "chat")
        setup.registerCommand(Shadow(), "shadow")
        setup.registerCommand(MOTDTools(), "motd")
        setup.registerCommand(Gadgets(), "gadgets")
        setup.registerCommand(Autoban(), "autoban")

        setup.announceStatus("Setting up user commands...")

        setup.registerCommand(EmeraldCmd(), "emeralds")
        setup.registerCommand(StaffList(), "stafflist")
        setup.registerCommand(Help(), "help")
        setup.registerCommand(Firework(), "firework")

        setup.announceStatus("Setting up punish commands...")

        setup.registerCommand(Ban(), "ban")

        setup.announceStatus("Setting up timers...")

        setup.registerNonAsyncTimer(ParticleCore(), 2)
        setup.registerNonAsyncTimer(ScoreboardSystem.Timer(), 5)

        setup.announceStatus("Setting up listeners...")

        setup.registerListener(ChatListener())
        setup.registerListener(ServerPingListener())
        setup.registerListener(HubListeners())
        setup.registerListener(PlayerJoinListener())
        setup.registerListener(JumpPad.JumpPadListener())
        setup.registerListener(PlayerMoveListener())
        setup.registerListener(AntiHungerListener())
        setup.registerListener(BlockPlaceListener())
        setup.registerListener(PlayerDamageListener())

        //this took me more time to figure out than I wish to admit.

        setup.registerListener(MinigameMenu().Listener())
        setup.registerListener(MainMenuListener())
        setup.registerListener(Tag.TagListener())
        setup.registerListener(ParticleMenu().Listener())

        setup.announceStatus("Setting up aliases...")

        setup.setAliases("chat", "sc", "c", "say")
        setup.setAliases("stafflist", "sl", "liststaff")
        setup.setAliases("firework", "fw")
        setup.setAliases("gadgets", "gs")

        setup.announceStatus("Finishing up...")

        VersionUtils.setVersion("LegendArena", "1.1-SNAPSHOT")
        VersionUtils.setVersion("Kotlin", "0.12.613")
        ScoreboardSystem.init()
        fixRankShit()

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
        config.addDefault("hub.hubworld", "world")
        config.addDefault("emeralds.storage", "sqlite")
        config.addDefault("emeralds.table", "emeralds")
        config.addDefault("emeralds.mysql.host", "127.0.0.1")
        config.addDefault("emeralds.mysql.password", "sup3rs3cr3tp@ssw0rd")
        config.addDefault("emeralds.mysql.database", "emeralds")
        config.addDefault("emeralds.sqlite.file", "emeralds.db")
        config.addDefault("enable.devserver", false)
        var founders = ArrayList<String>()
        founders.add("ThePixelDev")
        founders.add("ZRaptor22")
        config.addDefault("founders", founders)
        config.genIfDoesNotExist("configVersion")
    }

    private fun fixRankShit() {
        //fucking /reload breaking the scoreboard system DansGame
        for(p in Bukkit.getOnlinePlayers())
            ScoreboardSystem.setRank(p, RankUtils.getRank(p))
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTasks(Bukkit.getPluginManager().getPlugin("LegendArena"))
        ConfigUtils.config.saveConfig()
    }

}