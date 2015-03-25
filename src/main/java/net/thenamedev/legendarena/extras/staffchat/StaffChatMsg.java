package net.thenamedev.legendarena.extras.staffchat;

import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.entity.Player;

/**
 * Created by thepixeldev on 3/24/15.
 */
public class StaffChatMsg {

    public StaffChatMsg(String m, SCType c, Rank r, Player p) {
        if(c == SCType.PUBLIC) {
            mainChat(m, p);
            return;
        }
        //
    }

    private void mainChat(String msg, Player p) {
        //
    }

}
