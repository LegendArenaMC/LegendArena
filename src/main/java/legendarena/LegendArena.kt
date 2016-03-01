package legendarena

import legendarena.api.utils.*
import legendarena.chat.Notification
import legendarena.commands.*
import legendarena.commands.staff.*
import legendarena.commands.staff.punish.*
import legendarena.hub.HubWarper
import legendarena.hub.JumpPad
import legendarena.hub.menu.MinigameMenu
import legendarena.hub.menu.ParticleMenu
import legendarena.hub.menu.Tag
import legendarena.hub.particles.ParticleCore
import legendarena.listeners.*
import legendarena.listeners.menu.*
import legendarena.scoreboard.ScoreboardSystem
import legendarena.utils.ConfigUtils
import org.bukkit.Bukkit
import java.util.*

class LegendArena : KotlinUtils() {

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
        setup.registerCommand(Report(), "report")

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

        VersionUtils.setVersion("LegendArena", "1.3-SNAPSHOT")
        VersionUtils.setVersion("Kotlin", "1.0.0")
        ScoreboardSystem.init()
        fixRankShit()

        setup.announceStatus("LegendArena v${p.description.version} fully loaded.")
    }

    internal fun setupConfig() {
        var config = ConfigUtils.config
        config.addDefault("debug", true)
        config.addDefault("enable.lobbyServer", true)
        config.addDefault("hub.hubworld", "world")
        config.addDefault("emeralds.storage", "sqlite")
        config.addDefault("emeralds.table", "emeralds")
        config.addDefault("emeralds.mysql.host", "127.0.0.1")
        config.addDefault("emeralds.mysql.password", "sup3rs3cr3tp@ssw0rd")
        config.addDefault("emeralds.mysql.database", "emeralds")
        config.addDefault("emeralds.sqlite.file", "emeralds.db")
        config.addDefault("enable.devserver", false)
        config.genIfDoesNotExist("debug")
    }

    private fun fixRankShit() {
        //fuck reload
        for(p in Bukkit.getOnlinePlayers())
            ScoreboardSystem.setRank(p, RankUtils.getRank(p))
    }

    override fun onDisable() {
        Bukkit.getScheduler().cancelTasks(Bukkit.getPluginManager().getPlugin("LegendArena"))
        ConfigUtils.config.saveConfig()
    }

}