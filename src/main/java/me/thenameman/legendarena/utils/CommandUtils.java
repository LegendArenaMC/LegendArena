package me.thenameman.legendarena.utils;import org.bukkit.*;/** * @author TheNameMan */public class CommandUtils {    public static String formattedCmd(String name, boolean header) {        return header ? ChatColor.LIGHT_PURPLE + "-•- [" + ChatColor.LIGHT_PURPLE + ChatColor.stripColor(name) + ChatColor.LIGHT_PURPLE + "] -•-" : ChatColor.BLUE + "-•- " + ChatColor.stripColor(name) + ChatColor.BLUE + " -•-";    }}