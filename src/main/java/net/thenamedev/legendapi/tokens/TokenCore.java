package net.thenamedev.legendapi.tokens;

import lib.PatPeter.SQLibrary.Database;
import lib.PatPeter.SQLibrary.H2;
import lib.PatPeter.SQLibrary.SQLite;
import net.milkbowl.vault.economy.Economy;
import net.thenamedev.legendapi.exceptions.BoosterAlreadyActive;
import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.HashMap;
import java.util.UUID;

/**
 * TO USE THIS YOU REQUIRE VAULT AND SOME ECONOMY PLUGIN!
 * @author ThePixelDev
 */
public class TokenCore {

    private static Economy econ = null;
    private static boolean init = false;
    public static boolean canUseBoosters = false;
    public static final String ver = "0.9";
    public static final String verName = "Pre-Liftoff";

    public static final Database boosterDB = new SQLite(Bukkit.getLogger(), "TokensDB", Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath(), "");
    private static final HashMap<UUID, Integer> boosters = new HashMap<>();

    public static void init() {
        if(init && econ != null)
            return; //silently exit as the economy field is already setup, and not null
        if(!setupEconomy())
            throw new MistakesWereMadeException("Get Vault to use the Tokens system!");
        if(!econ.isEnabled())
            throw new MistakesWereMadeException("The Vault economy is not enabled! [do you have an economy plugin installed?]");
        canUseBoosters = boosterDB.open();
        init = true;
    }

    private static boolean setupEconomy() {
        if(Bukkit.getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if(rsp == null)
            return false;
        econ = rsp.getProvider();
        return !(econ == null);
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
                ChatUtils.broadcast(ChatColor.GREEN + "Aww, " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.GREEN + " went below 10k tokens. I feel sad now.");
        if(showMsg && !silence)
            p.sendMessage(PluginUtils.msgNormal + "You lost " + ChatColor.DARK_PURPLE + amount + ChatColor.GREEN + " tokens. You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.GREEN + " tokens.");
    }

    public static void addTokens(Player p, int amount, boolean showMsg, boolean silence) {
        if(amount < 1)
            throw new NullPointerException();
        econ.depositPlayer(p, amount);
        int newAmount = getTokens(p);
        if(newAmount - amount < 10000 && newAmount > 10000) {
            if(!silence)
                ChatUtils.broadcast(ChatColor.GREEN + "Congratlations to " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.GREEN + " for getting " + ChatColor.BOLD + "10k tokens!");
        }
        if(showMsg && !silence)
            p.sendMessage(PluginUtils.msgNormal + "You gained " + ChatColor.DARK_PURPLE + amount + ChatColor.GREEN + " tokens! You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.GREEN + " tokens!");
    }

    public static void resetTokens(Player p, boolean showMsg, String resetter) {
        if(resetter.equals(""))
            throw new MistakesWereMadeException("Resetter cannot be null!");
        int pTokens = getTokens(p);
        removeTokens(p, pTokens, false, true);
        p.sendMessage(PluginUtils.msgNormal + "Aww. Your tokens was reset to 0 by " + resetter + ".");
    }

    /**
     * THE FIRST INTEGER IS HOURS, NOT MINUTES.
     * @param p The player to target
     *
     * @throws net.thenamedev.legendapi.exceptions.MistakesWereMadeException If any fields are null
     */
    public static void addBooster(Player p, BoosterInfo info) {
        if(!canUseBoosters)
            throw new MistakesWereMadeException("Boosters cannot be used! [Do you have the SQLibrary plugin?]");
        if(info == null)
            throw new MistakesWereMadeException("Booster info cannot be null");
        int hours = info.hours();
        int power = info.power();
        if(hours < 1)
            throw new MistakesWereMadeException("Hours cannot be less than zero");
        if(p == null)
            throw new MistakesWereMadeException("Player cannot be null");
        if(boosters.containsKey(p.getUniqueId()))
            throw new BoosterAlreadyActive("The player already has a booster active!");
        boosters.put(p.getUniqueId(), hours);
        p.sendMessage(PluginUtils.msgNormal + "Your booster has been activated! Booster info: " + ChatColor.DARK_PURPLE + (boosters.get(p.getUniqueId())) + " hours /\\ " + power + "x more coins");
    }

}
