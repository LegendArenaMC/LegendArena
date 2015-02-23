package net.thenamedev.legendapi.tokens;

import net.milkbowl.vault.economy.*;
import net.thenamedev.legendapi.utils.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;

/**
 * TO USE THIS YOU REQUIRE VAULT AND SOME ECONOMY PLUGIN!
 * @author TheNameMan
 */
public class TokenCore {

    public static Economy econ = null;

    private static boolean init = false;
    public static final double ver = 1.0;

    public static void init() {
        if(init)
            return;
        if(!setupEconomy()) {
            throw new NullPointerException("Get Vault to use the Tokens system!");
        }
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

    public static int getTokens(Player p) {
        return (int) Math.round(econ.getBalance(p));
    }

    public static void removeTokens(Player p, int amount, boolean showMsg) {
        if(amount < 1)
            throw new NullPointerException();
        econ.withdrawPlayer(p, amount);
        if(showMsg)
            p.sendMessage(PluginUtils.msgNormal + "You lost " + ChatColor.DARK_PURPLE + amount + ChatColor.GREEN + " tokens! You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.GREEN + " tokens!");
    }

    public static void addTokens(Player p, int amount, boolean showMsg) {
        if(amount < 1)
        throw new NullPointerException();
        econ.depositPlayer(p, amount);
        if(showMsg)
            p.sendMessage(PluginUtils.msgNormal + "You gained " + ChatColor.DARK_PURPLE + amount + ChatColor.GREEN + " tokens! You now have " + ChatColor.DARK_PURPLE + getTokens(p) + ChatColor.GREEN + " tokens!");
    }

}
