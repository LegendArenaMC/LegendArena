package net.thenamedev.legendarena;import net.thenamedev.legendarena.utils.InitUtils;import org.bukkit.Bukkit;import org.bukkit.plugin.java.JavaPlugin;/** * @author ThePixelDev */public class LegendArena extends JavaPlugin {    public void onEnable() {        InitUtils.pluginInit();    }    public void onDisable() {        saveConfig();        Bukkit.getScheduler().cancelTasks(this);    }}