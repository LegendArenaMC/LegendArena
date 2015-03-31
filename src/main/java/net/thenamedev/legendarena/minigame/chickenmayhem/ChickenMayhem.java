package net.thenamedev.legendarena.minigame.chickenmayhem;

import net.thenamedev.legendapi.exceptions.MistakesWereMadeException;
import net.thenamedev.legendapi.utils.ChatUtils;
import net.thenamedev.legendapi.utils.PluginUtils;
import net.thenamedev.legendapi.utils.Rank;
import net.thenamedev.legendarena.extras.hub.warp.HubWarper;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;

/**
 * Chicken Mayhem - A minigame in which breaking blocks spawns chickens (purely for asthetical reasons).<br><br>
 *
 * The player with the most blocks broken at the end wins.
 * @author ThePixelDev
 */
public class ChickenMayhem implements Listener {

    private static boolean isRunning = false;
    private static List<UUID> players = new ArrayList<>();
    private static HashMap<UUID, Integer> blocksBroken = new HashMap<>();
    private static Object[] leadingPlayer = { null, 0 };

    public static boolean isRunning() {
        return isRunning;
    }

    public static void start() {
        if(isRunning)
            throw new MistakesWereMadeException("The minigame is already running!");
        for(UUID u : players) {
            Player p2 = Bukkit.getPlayer(u);
            if(p2 == null) {
                players.remove(u);
                continue;
            }
            p2.sendMessage(ChatColor.YELLOW + "Chicken Mayhem " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + "The game is starting! Good luck!");
        }
        isRunning = true;
    }

    public static void start(Player starter) {
        if(isRunning)
            throw new MistakesWereMadeException("The minigame is already running!");
        for(UUID u : players) {
            Player p2 = Bukkit.getPlayer(u);
            if(p2 == null) {
                players.remove(u);
                continue;
            }
            p2.sendMessage(ChatColor.YELLOW + "Chicken Mayhem " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + "The game has been force started by " + Rank.getFormattedName(starter) + ChatColor.GREEN + " - good luck!");
        }
        isRunning = true;
    }

    public static void end() {
        if(!isRunning)
            throw new MistakesWereMadeException("The minigame is not currently running!");
        for(UUID u : players) {
            Player p2 = Bukkit.getPlayer(u);
            if(p2 == null) {
                players.remove(u);
                continue;
            }
            p2.sendMessage(ChatColor.YELLOW + "Chicken Mayhem " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + "The game has ended! The victor was " + Rank.getFormattedName(((Player) getLeadingPlayer()[0])) + ChatColor.GREEN + " with " + getLeadingPlayer()[1] + " blocks broken!");
            p2.teleport(Bukkit.getWorld("hub").getSpawnLocation());
        }
        isRunning = false;
        blocksBroken.clear();
    }

    public static void end(Player p) {
        if(!isRunning)
            throw new MistakesWereMadeException("The minigame is not currently running!");
        for(UUID u : players) {
            Player p2 = Bukkit.getPlayer(u);
            if(p2 == null) {
                players.remove(u);
                continue;
            }
            p2.sendMessage(ChatColor.YELLOW + "Chicken Mayhem " + ChatColor.GRAY + PluginUtils.chars[1] + ChatColor.GREEN + "The game was force ended by " + Rank.getFormattedName(p) + ChatColor.GREEN + "! The victor was " + Rank.getFormattedName(((Player) getLeadingPlayer()[0])) + ChatColor.GREEN + " with " + getLeadingPlayer()[1] + " blocks broken!");
            quit(p, true);
        }
        isRunning = false;
        blocksBroken.clear();
    }

    public static void join(Player p) {
        join(p, false);
    }

    public static void join(Player p, boolean silence) {
        if(isRunning)
            throw new MistakesWereMadeException("The minigame is currently running!");
        if(players.contains(p.getUniqueId()))
            throw new MistakesWereMadeException("The player is already in the minigame!");
        for(UUID u : players) {
            Player p2 = Bukkit.getPlayer(u);
            if(p2 == null) {
                players.remove(u);
                continue;
            }
            if(!silence)
                p2.sendMessage(ChatColor.GRAY + "Join " + PluginUtils.chars[1] + " " + Rank.getFormattedName(p));
        }
        p.teleport(Bukkit.getWorld("chickenmayhem").getSpawnLocation());
        players.add(p.getUniqueId());
        if(!HubWarper.isExempt(p.getUniqueId()))
            HubWarper.toggleExemption(p.getUniqueId());
    }

    public static void quit(Player p) {
        quit(p, false);
    }

    public static void quit(Player p, boolean silence) {
        if(!isRunning)
            throw new MistakesWereMadeException("A player can't quit a non-running game!");
        if(!players.contains(p.getUniqueId()))
            throw new MistakesWereMadeException("The player isn't in the minigame!");
        for(UUID u : players) {
            Player p2 = Bukkit.getPlayer(u);
            if(p2 == null) {
                players.remove(u);
                continue;
            }
            if(!silence)
                p2.sendMessage(ChatColor.GRAY + "Quit " + PluginUtils.chars[1] + " " + Rank.getFormattedName(p));
        }
        p.teleport(Bukkit.getWorld("hub").getSpawnLocation());
        players.remove(p.getUniqueId());
        if(HubWarper.isExempt(p.getUniqueId()))
            HubWarper.toggleExemption(p.getUniqueId());
    }

    /**
     * Formatting for the returned Object[] is as follows:<br><br>
     *
     * <ul>
     *     <li>The player (Player)</li>
     *     <li>The amount of blocks broken (Integer)</li>
     * </ul>
     * @return The Object[] if the game is running (and the HashMap contains objects), null otherwise
     */
    public static Object[] getLeadingPlayer() {
        if(!isRunning)
            return null; //sanity check
        HashMap<Integer, Player> leaders = new HashMap<>(); //yes, yes, yes, hacky workaround, but it works - if you know of a better way to do this, by all means, please submit a PR!
        for(UUID u : blocksBroken.keySet())
            leaders.put(blocksBroken.get(u), Bukkit.getPlayer(u)); //add to leaders scoreboard
        for(Integer i : leaders.keySet())
            return new Object[] { i, leaders.get(i) }; //return the first hit
        return null; //fallback
    }

    @EventHandler
    public void listenForBlockBreak(BlockBreakEvent ev) {
        if(!isRunning)
            return; //ignore the event
        if(!ev.getPlayer().getWorld().equals(Bukkit.getWorld("chickenmayhem")))
            return; //ignore the event
        if(!players.contains(ev.getPlayer().getUniqueId()))
            return; //ignore the event
        Random random = new Random();
        int randomInt = random.nextInt(5);
        while(randomInt == 0)
            randomInt = random.nextInt(5);
        for(int i = randomInt; i > 0; i--)
            ev.getBlock().getWorld().spawn(ev.getBlock().getLocation(), Chicken.class).setBaby();
    }

    @EventHandler
    public void listenForChat(AsyncPlayerChatEvent ev) {
        if(!isRunning)
            return; //ignore the event
        if(!ev.getPlayer().getWorld().equals(Bukkit.getWorld("chickenmayhem")))
            return; //ignore the event
        if(!players.contains(ev.getPlayer().getUniqueId()))
            return; //ignore the event
        ev.setCancelled(true);
        for(UUID u : players) {
            Player p2 = Bukkit.getPlayer(u);
            if(p2 == null) {
                players.remove(u);
                continue;
            }
            p2.sendMessage(ChatUtils.getFormattedChat(ev.getMessage(), ev.getPlayer()));
        }
    }
}
