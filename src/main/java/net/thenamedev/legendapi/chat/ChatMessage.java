package net.thenamedev.legendapi.chat;

import net.thenamedev.legendapi.exceptions.DoYouEvenKnowWhatYourDoingException;
import net.thenamedev.legendapi.exceptions.NotEnoughPermissionsException;
import net.thenamedev.legendapi.utils.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Created on 4/6/2015
 *
 * @author ThePixelDev
 */
public class ChatMessage {

    private Rank r;
    private Player p;
    private Channels c;
    private String m;

    public ChatMessage(Channels c, String m, Player p) {
        this.m = m;
        this.c = c;
        this.p = p;
        this.r = Rank.getRank(p);
    }

    public void run() {
        if(p == null || m.equals("") || r == null || c == null)
            throw new DoYouEvenKnowWhatYourDoingException();
        if(!Rank.isRanked(p, c.getRankRequired()))
            throw new NotEnoughPermissionsException();
        for(Player p1 : Bukkit.getOnlinePlayers()) {
            if(!Rank.isRanked(p1, c.getRankRequired()))
                continue;
            if(ChatSystem.ignoresStaffChat(p1.getUniqueId()))
                continue;
            p1.sendMessage(String.format(c.getMsgFormat(), Rank.getFormattedName(p), m));
        }
    }

}
