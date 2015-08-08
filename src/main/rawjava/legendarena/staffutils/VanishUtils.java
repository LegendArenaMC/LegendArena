/*
 * This plugin is copyright Legend Arena, 2014-current. See LICENSE.md for full license file.
 */

package legendarena.staffutils;

import legendapi.message.Message;
import legendapi.utils.ChatUtils;
import legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class VanishUtils {

    private static final ArrayList<UUID> vanishedPlayers = new ArrayList<>();

    /**
     * Toggle vanish for a player
     * @param p The player to toggle
     * @param vanishMsg Should we show the player a "You have vanished" message or not?
     */
    public static void toggleVanish(Player p, boolean vanishMsg) {
        if(!Rank.MOD.isRanked(p) && vanishMsg)
            return; //do nothing, since it isn't an internal vanish

        if(isVanished(p)) {
            //unvanish the player

            unvanish(p);
            if(vanishMsg) {
                p.sendMessage(ChatUtils.getCustomMsg("Vanish") + "You have unvanished.");
                new Message().append(ChatUtils.getCustomMsg("Staff") + "Player " + p.getName() + " has unvanished.").broadcast(Rank.MOD);
            }
        } else {
            //vanish the player

            vanish(p);
            if(vanishMsg) {
                p.sendMessage(ChatUtils.getCustomMsg("Vanish") + "You have vanished. Poof.");
                new Message().append(ChatUtils.getCustomMsg("Staff") + "Player " + p.getName() + " has vanished. Poof.").broadcast(Rank.MOD);
            }
        }
    }

    /**
     * Is a player vanished?
     * @param p The player to check
     */
    public static boolean isVanished(Player p) {
        return vanishedPlayers.contains(p.getUniqueId());
    }

    /**
     * Hide all currently vanished players from a player. Best used on join.
     * @param p The player to hide vanished players from
     */
    public static void hideVanishedPlayersFrom(Player p) {
        for(UUID v : vanishedPlayers) {
            if(Bukkit.getPlayer(v) == null)
                continue; //assume the vanished player is not on the server
            p.hidePlayer(Bukkit.getPlayer(v));
        }
    }

    ////////////////////////////////////
    //                                //
    //         INTERNAL STUFF         //
    //                                //
    ////////////////////////////////////

    /**
     * Internal unvanish function
     */
    private static void unvanish(Player p) {
        for(Player o : Bukkit.getOnlinePlayers())
            if(!o.canSee(p))
                o.showPlayer(p);
    }

    /**
     * Internal vanish function
     */
    private static void vanish(Player p) {
        for(Player o : Bukkit.getOnlinePlayers())
            if(o.canSee(p))
                if(!Rank.MOD.isRanked(o))
                    o.hidePlayer(p);
    }

}
