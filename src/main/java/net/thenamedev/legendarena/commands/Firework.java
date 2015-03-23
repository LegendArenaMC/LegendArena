package net.thenamedev.legendarena.commands;import net.thenamedev.legendapi.tokens.TokenCore;import net.thenamedev.legendapi.utils.ChatUtils;import net.thenamedev.legendapi.utils.Cooldown;import net.thenamedev.legendapi.utils.PluginUtils;import net.thenamedev.legendapi.utils.OldRank;import org.bukkit.ChatColor;import org.bukkit.command.Command;import org.bukkit.command.CommandExecutor;import org.bukkit.command.CommandSender;import org.bukkit.entity.Player;import java.util.HashMap;import java.util.UUID;/** * @author TheNameMan */public class Firework implements CommandExecutor {    private HashMap<UUID, Cooldown> cooldown = new HashMap<>();    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {        if(!(sender instanceof Player)) {            sender.sendMessage("Sorry - you can only do this as a player :(");            return true;        }        if(!OldRank.getRank(sender, OldRank.VIP)) {            if(TokenCore.getTokens((Player) sender) < 15) {                sender.sendMessage(PluginUtils.msgWarning + "You don't have enough tokens to use this!");                return true;            }            TokenCore.removeTokens((Player) sender, 15, true, false);        }        if(cooldown.containsKey(((Player) sender).getUniqueId()) && !cooldown.get(((Player) sender).getUniqueId()).done()) {            sender.sendMessage(cooldown.get(((Player) sender).getUniqueId()).getTimeRemaining());            return true;        }        ChatUtils.shootFireworks(sender);        sender.sendMessage(ChatColor.GREEN + "Yaaay, fireworks!");        //3 second cooldown        cooldown.put(((Player) sender).getUniqueId(), new Cooldown(3));        return true;    }}