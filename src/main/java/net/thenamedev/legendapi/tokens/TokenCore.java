package net.thenamedev.legendapi.tokens;

import net.milkbowl.vault.economy.Economy;
import net.thenamedev.legendapi.LegendAPI;
import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;


/**
 * TO USE THIS YOU REQUIRE VAULT AND SOME ECONOMY PLUGIN!
 * @author ThePixelDev
 */
public class TokenCore {

    private static Economy econ = null;
    private static boolean init = false;
    @Deprecated
    public static final String ver = LegendAPI.apiVersion;
    @Deprecated
    public static final String verName = LegendAPI.versionName;

    public static void init() {
        if(init && econ != null)
            return; //silently exit as the economy field is already setup, and not null
        if(!setupEconomy())
            throw new MistakesWereMadeException("Get Vault to use the Tokens system!");
        init = true;
    }

    private static boolean setupEconomy() {
        if(Bukkit.getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if(rsp == null)
            return false;
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEcon() {
        return econ;
    }

    public static int getTokens(Player p) {
        if(econ == null || !econ.isEnabled())
            return 0;
        return (int) Math.round(econ.getBalance(p));
    }

    public static void removeTokens(Player p, int amount, boolean showMsg, boolean silence) {
        removeTokens(p, amount, silence);
    }

    public static void addTokens(Player p, int amount, boolean showMsg, boolean silence) {
        addTokens(p, amount, silence);
    }

    public static void removeTokens(Player p, int amount, boolean showMsg) {
        if(amount < 1)
            throw new NullPointerException();
        getEcon().withdrawPlayer(p, amount);
        if(showMsg)
            p.sendMessage(PluginUtils.msgNormal + "You lost " + ChatColor.DARK_PURPLE + amount + ChatColor.LIGHT_PURPLE + " token(s). You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.LIGHT_PURPLE + " token(s).");
    }

    public static void addTokens(Player p, int amount, boolean showMsg) {
        if(amount < 1)
            throw new NullPointerException();
        econ.depositPlayer(p, amount);
        int newAmount = getTokens(p);
        if(showMsg)
            p.sendMessage(PluginUtils.msgNormal + "You gained " + ChatColor.DARK_PURPLE + amount + ChatColor.LIGHT_PURPLE + " token(s)! You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.LIGHT_PURPLE + " token(s)!");
    }

    public static void resetTokens(Player p, boolean showMsg, String resetter) {
        if(resetter.equals(""))
            resetter = "*CONSOLE*";
        removeTokens(p, getTokens(p), false);
        p.sendMessage(PluginUtils.msgNormal + "Aww. Your tokens was reset to 0 by " + ChatColor.DARK_PURPLE + resetter + ChatColor.LIGHT_PURPLE + ".");
    }

}
