package net.thenamedev.legendapi.user;

import net.thenamedev.legendapi.tokens.TokenCore;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created on 4/17/15
 *
 * @author ThePixelDev
 */
public class Users {

    private static HashMap<String, User> users = new HashMap<>();

    public static HashMap<String, User> getUsers() {
        return users;
    }

    public static void update(final String player) {
        if(Bukkit.getPlayer(player) == null)
            throw new NullPointerException("That player hasn't ever joined (or isn't logged in)!");
        if(users.containsKey(player))
            users.remove(player);
        users.put(player, new User() {
            public Player player() {
                return Bukkit.getPlayer(player);
            }

            public Rank rank() {
                return Rank.getRank(player());
            }

            public int tokens() {
                return TokenCore.getTokens(player());
            }
        });
    }

}
