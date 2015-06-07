package legendarena.emeralds;

import net.milkbowl.vault.economy.Economy;
import legendarena.api.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.logging.Logger;

/**
 * TO USE THIS YOU REQUIRE VAULT AND SOME ECONOMY PLUGIN!
 * @author ThePixelDev
 */
public class EmeraldsCore {

    private static Economy econ = null;
    private static boolean init = false;

    public static void init() {
        if(init && econ != null)
            return; //silently exit as the economy field is already setup, and not null
        if(setupEconomy())
            init = true;
        else {
            try {
                Bukkit.getPluginManager().getPlugin("Vault").getConfig();
            } catch(Exception ex) {
                Logger l = Bukkit.getLogger();
                l.warning("===============================================");
                l.warning("The Emeralds system will NOT work without Vault,");
                l.warning("and some kind of economy plugin. You can get");
                l.warning("Vault here:");
                l.warning("(fyi, Essentials has a built-in economy system)");
                l.warning("===============================================");
                l.warning("    http://dev.bukkit.org/bukkit-plugins/vault/");
                l.warning("===============================================");
            }
        }
    }

    /**
     * Never ask how the fuck this works, or it will just randomly quit working. Because magic.
     */
    private static boolean setupEconomy() {
        if(Bukkit.getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if(rsp == null)
            return false;
        econ = rsp.getProvider();
        return econ != null;
    }

    protected static Economy getEcon() {
        return econ;
    }

    @Deprecated
    public static int getEmeralds(Player p) {
        if(econ == null || !econ.isEnabled())
            return 0;
        return (int) Math.round(econ.getBalance(p));
    }

    @Deprecated
    public static void removeEmeralds(Player p, int amount, boolean showMsg) {
        if(amount < 1)
            throw new NullPointerException();
        getEcon().withdrawPlayer(p, amount);
        if(showMsg)
            p.sendMessage(ChatUtils.getCustomMsg("Emeralds") + "You lost " + ChatColor.DARK_PURPLE + amount + ChatColor.LIGHT_PURPLE + " token(s). You now have " + ChatColor.DARK_PURPLE + getEmeralds(p) + ChatColor.LIGHT_PURPLE + " emerald(s).");
    }

    @Deprecated
    public static void addEmeralds(Player p, int amount, boolean showMsg) {
        if(amount < 1)
            throw new NullPointerException();
        econ.depositPlayer(p, amount);
        int newAmount = getEmeralds(p);
        if(showMsg)
            p.sendMessage(ChatUtils.getCustomMsg("Emeralds") + "You gained " + ChatColor.DARK_PURPLE + amount + ChatColor.LIGHT_PURPLE + " token(s)! You now have " + ChatColor.DARK_PURPLE + getEmeralds(p) + ChatColor.LIGHT_PURPLE + " emerald(s)!");
    }

    @Deprecated
    public static void resetEmeralds(Player p, boolean showMsg, String resetter) {
        if(resetter.equals(""))
            resetter = "*CONSOLE*";
        removeEmeralds(p, getEmeralds(p), false);
        if(showMsg)
            p.sendMessage(ChatUtils.getCustomMsg("Emeralds") + "Aww. You lost all your emeralds, due to a reset of your account by " + ChatColor.DARK_PURPLE + resetter + ChatColor.LIGHT_PURPLE + ".");
    }

}
