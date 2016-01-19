/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.chat;

import legendarena.api.message.Message;
import legendarena.api.utils.ChatUtils;
import legendarena.api.utils.Rank;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Notification {

    public static void alert(String msg) {
        new Message().append(ChatUtils.getCustomMsg("Notice") + ChatColor.RED + msg).broadcast(Rank.MOD);
    }

    public static void alertDevs(String msg) {
        new Message().append(ChatUtils.getCustomMsg("Dev Notice") + ChatColor.LIGHT_PURPLE + msg).broadcast(Rank.DEV);
    }

    public static void global(String msg) {
        new Message().append(ChatUtils.getCustomMsg("Alert") + ChatColor.RED + msg).broadcast();
    }

    public static void alertSingle(Player p, String msg) {
        new Message().append(ChatUtils.getCustomMsg("Notice") + ChatColor.RED + msg).send(p);
    }

}
