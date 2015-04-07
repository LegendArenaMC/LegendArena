package net.thenamedev.legendapi.tokens;

import net.milkbowl.vault.economy.Economy;
import net.thenamedev.legendapi.LegendAPI;
import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.ChatUtils;
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
        if(amount < 1)
            throw new NullPointerException();
        getEcon().withdrawPlayer(p, amount);
        if(!silence)
            if(getTokens(p) < 10000 && getTokens(p) + amount >= 10000)
                ChatUtils.broadcast(ChatColor.GREEN + "Aww, " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.LIGHT_PURPLE + " went below 10k tokens. I feel sad now.");
        if(showMsg && !silence)
            p.sendMessage(PluginUtils.msgNormal + "You lost " + ChatColor.DARK_PURPLE + amount + ChatColor.LIGHT_PURPLE + " token(s). You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.LIGHT_PURPLE + " token(s).");
    }

    public static void addTokens(Player p, int amount, boolean showMsg, boolean silence) {
        if(amount < 1)
            throw new NullPointerException();
        econ.depositPlayer(p, amount);
        int newAmount = getTokens(p);
        if(newAmount - amount < 10000 && newAmount > 10000) {
            if(!silence)
                ChatUtils.broadcast(ChatColor.GREEN + "Congratlations to " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.LIGHT_PURPLE + " for getting " + ChatColor.BOLD + "10k tokens!");
        }
        if(showMsg && !silence)
            p.sendMessage(PluginUtils.msgNormal + "You gained " + ChatColor.DARK_PURPLE + amount + ChatColor.LIGHT_PURPLE + " token(s)! You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.LIGHT_PURPLE + " token(s)!");
    }

    public static void resetTokens(Player p, boolean showMsg, String resetter) {
        if(resetter.equals(""))
            resetter = "*CONSOLE*";
        int pTokens = getTokens(p);
        removeTokens(p, pTokens, false, true);
        p.sendMessage(PluginUtils.msgNormal + "Aww. Your tokens was reset to 0 by " + ChatColor.DARK_PURPLE + resetter + ChatColor.LIGHT_PURPLE + ".");
    }

}
