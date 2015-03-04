package net.thenamedev.legendapi.tokens;

import net.milkbowl.vault.economy.*;
import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.*;
import net.thenamedev.legendarena.extras.staffchat.StaffChat;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;

/**
 * TO USE THIS YOU REQUIRE VAULT AND SOME ECONOMY PLUGIN!
 * @author TheNameMan
 */
public class TokenCore {

    private static Economy econ = null;

    private static boolean init = false;

    public static final double ver = 1.1;
    public static final String verName = "Nighttime"; //reference to "nightly" builds of programs.. if that makes any sense

    public static void init() {
        if(init)
            return;
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
            p.sendMessage(PluginUtils.msgNormal + "You lost " + ChatColor.DARK_PURPLE + amount + ChatColor.GREEN + " tokens! You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.GREEN + " tokens.");
    }

    public static void addTokens(Player p, int amount, boolean showMsg, boolean silence) {
        if(amount < 1)
            throw new NullPointerException();
        econ.depositPlayer(p, amount);
        int newAmount = getTokens(p);
        if(amount >= 500)
            StaffChat.notice("Someone just got 500 tokens (or more) in one addTokens() call - are they cheating? (if this was a staff-run command operation, you can safely ignore this message)", "Tokens System");
        if(newAmount - amount < 10000 && newAmount > 10000) {
            if(!silence)
                ChatUtils.broadcast(ChatColor.GREEN + "Congratlations to " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.GREEN + " for getting " + ChatColor.BOLD + "10k tokens!");
        }
        if(showMsg && !silence)
            p.sendMessage(PluginUtils.msgNormal + "You gained " + ChatColor.DARK_PURPLE + amount + ChatColor.GREEN + " tokens! You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.GREEN + " tokens!");
    }

}
