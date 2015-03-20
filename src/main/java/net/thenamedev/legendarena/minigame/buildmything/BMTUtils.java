package net.thenamedev.legendarena.minigame.buildmything;

import net.thenamedev.legendapi.utils.PlayerUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.UUID;

/**
 * Created on 3/4/2015
 *
 * @author ThePixelDev
 */
public class BMTUtils {

    private static Player builder;
    private static String word;
    private static Random r = new Random();

    public static void setBuilder(Player p) {
        if(builder != null) {
            for(Player a : Bukkit.getOnlinePlayers()) {
                if(!PlayerUtils.isInMinigame(a.getUniqueId())) continue;
                if(!(PlayerUtils.getPlayerMinigame(a.getUniqueId()) instanceof BuildMyThing)) continue;
                a.sendMessage(PluginUtils.msgNormal + "The round is now over! The word was \"" + ChatColor.DARK_PURPLE + word + ChatColor.LIGHT_PURPLE + "\" ");
            }
            ((Runnable) () -> {
                builder.teleport(getMainLocation());
                try {
                    Thread.sleep(5000);
                } catch(InterruptedException ex) {
                    //do nothing
                }
                builder = getRandomPlayer();
                word = getRandomWord();
                builder.sendMessage(PluginUtils.msgNormal + "You are now the BUILDER!");
                builder.teleport(getBuilderLocation());
                builder.playSound(builder.getLocation(), Sound.BLAZE_DEATH, 1, 1);
            }).run();
        } else {
            //
        }
    }

    private static Location getMainLocation() {
        return null;
    }

    private static Location getBuilderLocation() {
        return null;
    }

    public static Player getRandomPlayer() {
        Player returningPlayer = null;
        for(UUID u : PlayerUtils.getPlayers(new BuildMyThing())) {
            if(r.nextInt(5) == 2) {
                if(Bukkit.getPlayer(u) == builder)
                    continue;
                returningPlayer = Bukkit.getPlayer(u);
            }
        }
        return returningPlayer;
    }

    public static String getRandomWord() {
        String word1 = BMTWordUtils.wordList[r.nextInt(BMTWordUtils.wordList.length)];
        if(word1.equals(word))
            word1 = getRandomWord();
        return word1;
    }

}
