/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendapi.client;

import legendapi.utils.ConfigUtils;
import legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class Client {

    private Player p;
    private UUID u;
    private ConfigUtils conf;

    public Client(Player p) {
        this.p = p;
        this.u = p.getUniqueId();

        File tmpFolder = new File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath(), "players");
        //we don't care about the output because we're just making a folder to put player info in as flat files temporarily
        //noinspection ResultOfMethodCallIgnored
        tmpFolder.mkdirs();
        File tmpFile = new File(Bukkit.getPluginManager().getPlugin("LegendArena").getDataFolder().getAbsolutePath(), "players" + File.separator + u.toString() + ".yml");

        this.conf = new ConfigUtils(Bukkit.getPluginManager().getPlugin("LegendArena"), tmpFile);
    }

    public boolean isBanned() {
        return false;
    }

    public Rank getRank() {
        if(Rank.DEV.isRanked(p))
            return Rank.DEV;
        else if(Rank.FOUNDER.isRanked(p))
            return Rank.FOUNDER;
        else if(Rank.ADMIN.isRanked(p))
            return Rank.ADMIN;
        else if(Rank.MOD.isRanked(p))
            return Rank.MOD;
        else if(Rank.HELPER.isRanked(p))
            return Rank.HELPER;
        else if(Rank.VIP.isRanked(p))
            return Rank.VIP;
        else if(Rank.MEMBERPLUS.isRanked(p))
            return Rank.MEMBERPLUS;
        else return Rank.MEMBER;
    }

    public void setEmeralds(int amount) {
        if(getEmeralds() == amount) return;

        if(getEmeralds() < amount)
            p.sendMessage("");
    }

    public int getEmeralds() {
        return Integer.parseInt((String) conf.get("emeralds"));
    }

}
