package net.thenamedev.legendarena;import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;import net.thenamedev.legendapi.utils.ChatUtils;import net.thenamedev.legendarena.extras.staffchat.SCType;import net.thenamedev.legendarena.minigame.chickenmayhem.ChickenMayhem;import net.thenamedev.legendarena.utils.InitUtils;import org.bukkit.Bukkit;import org.bukkit.configuration.file.FileConfiguration;import org.bukkit.plugin.java.JavaPlugin;import java.util.Calendar;/** * @author ThePixelDev */public class LegendArena extends JavaPlugin {    private static boolean isChatMuted = false;    public static FileConfiguration config = null;    public static void toggleChatMute() {        isChatMuted = !isChatMuted;    }    public static boolean isChatMuted() {        return isChatMuted;    }    public void onEnable() {        if(Calendar.getInstance().get(Calendar.MONTH) == 3 && Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 0) {            Bukkit.getScheduler().runTaskTimer(this, new Runnable() {                int a = 10;                public void run() {                    if(a == 0) {                        return;                    }                    ChatUtils.broadcast("ChatColor.RED + \"Hello world!\""); //love, Pixel <3                    a--;                }            }, 40, 40);        }        InitUtils.pluginInit();        config = Bukkit.getPluginManager().getPlugin("LegendArena").getConfig();    }    public void onDisable() {        try {            ChickenMayhem.end();        } catch(MistakesWereMadeException ignore) {}        saveConfig();        SCType.clearStaffchatTypes();        Bukkit.getScheduler().cancelTasks(this);    }}